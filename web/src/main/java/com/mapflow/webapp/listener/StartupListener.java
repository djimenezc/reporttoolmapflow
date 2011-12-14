package com.mapflow.webapp.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.service.LookupManager;
import org.compass.gps.CompassGps;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mapflow.geo.common.constants.Constants;

/**
 * <p>
 * StartupListener class used to initialize and database settings and populate any application-wide
 * drop-downs.
 * <p/>
 * <p>
 * Keep in mind that this listener is executed outside of OpenSessionInViewFilter, so if you're
 * using Hibernate you'll have to explicitly initialize all loaded data at the GenericDao or service
 * level to avoid LazyInitializationException. Hibernate.initialize() works well for doing this.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StartupListener implements ServletContextListener {

  private static final Log log = LogFactory.getLog(StartupListener.class);

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public void contextInitialized(final ServletContextEvent event) {
    log.debug("Initializing context...");

    final ServletContext context = event.getServletContext();

    // Orion starts Servlets before Listeners, so check if the config
    // object already exists
    Map<String, Object> config = (HashMap<String, Object>) context.getAttribute(Constants.CONFIG);

    if (config == null) {
      config = new HashMap<String, Object>();
    }

    if (context.getInitParameter(Constants.CSS_THEME) != null) {
      config.put(Constants.CSS_THEME, context.getInitParameter(Constants.CSS_THEME));
    }

    final ApplicationContext ctx =
      WebApplicationContextUtils.getRequiredWebApplicationContext(context);

    /*
     * String[] beans = ctx.getBeanDefinitionNames(); for (String bean : beans) { log.debug(bean); }
     */

    PasswordEncoder passwordEncoder = null;
    try {
      final ProviderManager provider =
        (ProviderManager) ctx
          .getBean("org.springframework.security.authentication.ProviderManager#0");
      for (final Object o : provider.getProviders()) {
        final AuthenticationProvider p = (AuthenticationProvider) o;
        if (p instanceof RememberMeAuthenticationProvider) {
          config.put("rememberMeEnabled", Boolean.TRUE);
        }
        else if (ctx.getBean("passwordEncoder") != null) {
          passwordEncoder = (PasswordEncoder) ctx.getBean("passwordEncoder");
        }
      }
    }
    catch (final NoSuchBeanDefinitionException n) {
      log.debug("authenticationManager bean not found, assuming test and ignoring...");
      // ignore, should only happen when testing
    }

    context.setAttribute(Constants.CONFIG, config);

    // output the retrieved values for the Init and Context Parameters
    if (log.isDebugEnabled()) {
      log.debug("Remember Me Enabled? " + config.get("rememberMeEnabled"));
      if (passwordEncoder != null) {
        log.debug("Password Encoder: " + passwordEncoder.getClass().getSimpleName());
      }
      log.debug("Populating drop-downs...");
    }

    setupContext(context);
  }

  /**
   * This method uses the LookupManager to lookup available roles from the data layer.
   * 
   * @param context
   *          The servlet context
   */
  public static void setupContext(final ServletContext context) {
    final ApplicationContext ctx =
      WebApplicationContextUtils.getRequiredWebApplicationContext(context);
    final LookupManager mgr = (LookupManager) ctx.getBean("lookupManager");

    // get list of possible roles
    context.setAttribute(Constants.AVAILABLE_ROLES, mgr.getAllRoles());
    log.debug("Drop-down initialization complete [OK]");

    final CompassGps compassGps = ctx.getBean(CompassGps.class);
    compassGps.index();
  }

  /**
   * Shutdown servlet context (currently a no-op method).
   * 
   * @param servletContextEvent
   *          The servlet context event
   */
  @Override
  public void contextDestroyed(final ServletContextEvent servletContextEvent) {
    // LogFactory.release(Thread.currentThread().getContextClassLoader());
    // Commented out the above call to avoid warning when SLF4J in classpath.
    // WARN: The method class org.apache.commons.logging.impl.SLF4JLogFactory#release() was invoked.
    // WARN: Please see http://www.slf4j.org/codes.html for an explanation.
  }
}
