package com.mapflow.webapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {
		"classpath:/spring/applicationContext-resources.xml",
		"classpath:/spring/applicationContext-dao.xml",
		"classpath:/spring/applicationContext-service.xml",
		"classpath*:/spring/applicationContext.xml",
		"/WEB-INF/spring/applicationContext*.xml",
		"/WEB-INF/dispatcher-servlet.xml" })
public abstract class BaseControllerTestCase extends
		AbstractTransactionalJUnit4SpringContextTests {

	protected transient final Log log = LogFactory.getLog(getClass());
	private int smtpPort = 25250;

	protected int getSmtpPort() {
		return smtpPort;
	}

	public MockHttpServletRequest newGet(final String url) {
		return new MockHttpServletRequest("GET", url);
	}

	/**
	 * Convenience methods to make tests simpler
	 * 
	 * @param url
	 *            the URL to post to
	 * @return a MockHttpServletRequest with a POST to the specified URL
	 */
	public MockHttpServletRequest newPost(final String url) {
		return new MockHttpServletRequest("POST", url);
	}

	@Before
	public void onSetUp() {
		smtpPort = smtpPort + (int) (Math.random() * 100);
		// change the port on the mailSender so it doesn't conflict with an
		// existing SMTP server on localhost
		final JavaMailSenderImpl mailSender = (JavaMailSenderImpl) applicationContext
				.getBean("mailSender");
		mailSender.setPort(getSmtpPort());
		mailSender.setHost("localhost");
	}
}
