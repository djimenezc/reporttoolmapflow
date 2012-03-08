package com.mapflow.webapp.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.mapflow.geo.common.constants.Constants;
import com.mapflow.geo.common.manager.UserManager;
import com.mapflow.geo.common.model.entities.User;
import com.mapflow.geo.common.service.MailEngine;

/**
 * Implementation of <strong>SimpleFormController</strong> that contains
 * convenience methods for subclasses. For example, getting the current user and
 * saving messages/errors. This class is intended to be a base class for all
 * Form controllers.
 * <p>
 * <a href="BaseFormController.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseFormController implements ServletContextAware {

	protected final transient Log log = LogFactory.getLog(getClass());
	public static final String MESSAGES_KEY = "successMessages";
	private UserManager userManager = null;
	protected MailEngine mailEngine = null;
	protected SimpleMailMessage message = null;
	protected String templateName = "accountCreated.vm";
	protected String cancelView;
	protected String successView;

	private MessageSourceAccessor messages;
	private ServletContext servletContext;

	@Autowired(required = false)
	Validator validator;

	public final String getCancelView() {
		// Default to successView if cancelView is invalid
		if ((cancelView == null) || (cancelView.length() == 0)) {
			return getSuccessView();
		}
		return cancelView;
	}

	/**
	 * Convenience method to get the Configuration HashMap from the servlet
	 * context.
	 * 
	 * @return the user's populated form from the session
	 */
	@SuppressWarnings("rawtypes")
	public Map<?, ?> getConfiguration() {
		final Map<?, ?> config = (HashMap<?, ?>) servletContext
				.getAttribute(Constants.CONFIG);

		// so unit tests don't puke when nothing's been set
		if (config == null) {
			return new HashMap();
		}

		return config;
	}

	protected ServletContext getServletContext() {
		return servletContext;
	}

	public final String getSuccessView() {
		return successView;
	}

	/**
	 * Convenience method for getting a i18n key's value. Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable is
	 * not set in unit tests b/c there's no DispatchServlet Request.
	 * 
	 * @param msgKey
	 * @param locale
	 *            the current locale
	 * @return
	 */
	public String getText(final String msgKey, final Locale locale) {
		return messages.getMessage(msgKey, locale);
	}

	/**
	 * Convenience method for getting a i18n key's value with arguments.
	 * 
	 * @param msgKey
	 * @param args
	 * @param locale
	 *            the current locale
	 * @return
	 */
	public String getText(final String msgKey, final Object[] args,
			final Locale locale) {
		return messages.getMessage(msgKey, args, locale);
	}

	/**
	 * Convenient method for getting a i18n key's value with a single string
	 * argument.
	 * 
	 * @param msgKey
	 * @param arg
	 * @param locale
	 *            the current locale
	 * @return
	 */
	public String getText(final String msgKey, final String arg,
			final Locale locale) {
		return getText(msgKey, new Object[] { arg }, locale);
	}

	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * Set up a custom property editor for converting form inputs to real
	 * objects
	 * 
	 * @param request
	 *            the current request
	 * @param binder
	 *            the data binder
	 */
	@InitBinder
	protected void initBinder(final HttpServletRequest request,
			final ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Integer.class, null,
				new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(
				Long.class, null, true));
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());

		SimpleDateFormat dateFormat;

		try {
			dateFormat = new SimpleDateFormat(getText("date.format",
					request.getLocale()));
		} catch (final Exception e) {

			dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		}
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(
				dateFormat, true));
	}

	@SuppressWarnings("unchecked")
	public void saveError(final HttpServletRequest request, final String error) {
		List<String> errors = (List<String>) request.getSession().getAttribute(
				"errors");
		if (errors == null) {
			errors = new ArrayList<String>();
		}
		errors.add(error);
		request.getSession().setAttribute("errors", errors);
	}

	@SuppressWarnings("unchecked")
	public void saveMessage(final HttpServletRequest request, final String msg) {
		List<String> messages = (List<String>) request.getSession()
				.getAttribute(MESSAGES_KEY);

		if (messages == null) {
			messages = new ArrayList<String>();
		}

		messages.add(msg);
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}

	/**
	 * Convenience message to send messages to users, includes app URL as
	 * footer.
	 * 
	 * @param user
	 *            the user to send a message to.
	 * @param msg
	 *            the message to send.
	 * @param url
	 *            the URL of the application.
	 */
	protected void sendUserMessage(final User user, final String msg,
			final String url) {
		if (log.isDebugEnabled()) {
			log.debug("sending e-mail to user [" + user.getEmail() + "]...");
		}

		message.setTo(user.getFullName() + "<" + user.getEmail() + ">");

		final Map<String, Serializable> model = new HashMap<String, Serializable>();
		model.put("user", user);

		// TODO: once you figure out how to get the global resource bundle in
		// WebWork, then figure it out here too. In the meantime, the Username
		// and Password labels are hard-coded into the template.
		// model.put("bundle", getTexts());
		model.put("message", msg);
		model.put("applicationURL", url);
		mailEngine.sendMessage(message, templateName, model);
	}

	public final BaseFormController setCancelView(final String cancelView) {
		this.cancelView = cancelView;
		return this;
	}

	@Autowired
	public void setMailEngine(final MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}

	@Autowired
	public void setMessage(final SimpleMailMessage message) {
		this.message = message;
	}

	@Autowired
	public void setMessages(final MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}

	@Override
	public void setServletContext(final ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public final BaseFormController setSuccessView(final String successView) {
		this.successView = successView;
		return this;
	}

	public void setTemplateName(final String templateName) {
		this.templateName = templateName;
	}

	@Autowired
	public void setUserManager(final UserManager userManager) {
		this.userManager = userManager;
	}
}