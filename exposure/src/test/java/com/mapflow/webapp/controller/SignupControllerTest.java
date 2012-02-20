package com.mapflow.webapp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.subethamail.wiser.Wiser;

import com.mapflow.geo.common.constants.Constants;
import com.mapflow.geo.common.model.Address;
import com.mapflow.geo.common.model.entities.User;

public class SignupControllerTest extends BaseControllerTestCase {

	@Autowired
	private final SignupController c = null;

	@Test
	public void testDisplayForm() throws Exception {
		final User user = c.showForm();
		assertNotNull(user);
	}

	@Test
	public void testSignupUser() throws Exception {
		final MockHttpServletRequest request = newPost("/signup.html");

		final Address address = new Address();
		address.setCity("Denver");
		address.setProvince("Colorado");
		address.setCountry("USA");
		address.setPostalCode("80210");

		final User user = new User();
		user.setAddress(address);

		user.setUsername("self-registered");
		user.setPassword("Password1");
		user.setConfirmPassword("Password1");
		user.setFirstName("First");
		user.setLastName("Last");
		user.setEmail("self-registered@raibledesigns.com");
		user.setWebsite("http://raibledesigns.com");
		user.setPasswordHint("Password is one with you.");

		final HttpServletResponse response = new MockHttpServletResponse();

		// start SMTP Server
		final Wiser wiser = new Wiser();
		wiser.setPort(getSmtpPort());
		wiser.start();

		final BindingResult errors = new DataBinder(user).getBindingResult();
		c.onSubmit(user, errors, request, response);
		assertFalse("errors returned in model", errors.hasErrors());

		// verify an account information e-mail was sent
		wiser.stop();
		assertTrue(wiser.getMessages().size() == 1);

		// verify that success messages are in the request
		assertNotNull(request.getSession().getAttribute("successMessages"));
		assertNotNull(request.getSession().getAttribute(Constants.REGISTERED));

		SecurityContextHolder.getContext().setAuthentication(null);
	}
}
