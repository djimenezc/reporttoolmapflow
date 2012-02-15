package com.mapflow.webapp.controller.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.context.ServletContextAware;

import com.mapflow.geo.common.constants.Constants;
import com.mapflow.geo.common.manager.UserManager;
import com.mapflow.geo.stats.facade.StatsFacade;

/**
 * Implementation of <strong>SimpleFormController</strong> that contains convenience methods for
 * subclasses. For example, getting the current user and saving messages/errors. This class is
 * intended to be a base class for all Form controllers.
 * <p>
 * <a href="BaseFormController.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class AbstractStatsController implements ServletContextAware {

  protected final transient Log log = LogFactory.getLog(getClass());
  public static final String MESSAGES_KEY = "successMessages";
  private UserManager userManager = null;
  protected String cancelView;
  protected String successView;

  @Autowired
  protected StatsFacade statsFacade;
  
  private ServletContext servletContext;

  @Autowired(required = false)
  Validator validator;


  @Autowired
  public void setUserManager(final UserManager userManager) {
    this.userManager = userManager;
  }

  public UserManager getUserManager() {
    return userManager;
  }

  @SuppressWarnings("unchecked")
  public void saveError(final HttpServletRequest request, final String error) {
    List<String> errors = (List<String>) request.getSession().getAttribute("errors");
    if (errors == null) {
      errors = new ArrayList<String>();
    }
    errors.add(error);
    request.getSession().setAttribute("errors", errors);
  }

  @SuppressWarnings("unchecked")
  public void saveMessage(final HttpServletRequest request, final String msg) {
    List<String> messages = (List<String>) request.getSession().getAttribute(MESSAGES_KEY);

    if (messages == null) {
      messages = new ArrayList<String>();
    }

    messages.add(msg);
    request.getSession().setAttribute(MESSAGES_KEY, messages);
  }

  /**
   * Convenience method to get the Configuration HashMap from the servlet context.
   * 
   * @return the user's populated form from the session
   */
  @SuppressWarnings("rawtypes")
  public Map<?, ?> getConfiguration() {
    final Map<?, ?> config = (HashMap<?, ?>) servletContext.getAttribute(Constants.CONFIG);

    // so unit tests don't puke when nothing's been set
    if (config == null) {
      return new HashMap();
    }

    return config;
  }

  public final AbstractStatsController setCancelView(final String cancelView) {
    this.cancelView = cancelView;
    return this;
  }

  public final String getCancelView() {
    // Default to successView if cancelView is invalid
    if ((cancelView == null) || (cancelView.length() == 0)) {
      return getSuccessView();
    }
    return cancelView;
  }

  public final String getSuccessView() {
    return successView;
  }

  public final AbstractStatsController setSuccessView(final String successView) {
    this.successView = successView;
    return this;
  }

  @Override
  public void setServletContext(final ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  protected ServletContext getServletContext() {
    return servletContext;
  }
}
