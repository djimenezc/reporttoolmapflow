package com.mapflow.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mapflow.geo.common.constants.Constants;
import com.mapflow.geo.common.manager.UserManager;

/**
 * Simple class to retrieve a list of users from the database.
 * <p/>
 * <p>
 * <a href="UserController.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/admin/users*")
public class UserController {

	private UserManager mgr = null;

	@Autowired
	public void setUserManager(final UserManager userManager) {
		mgr = userManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(
			@RequestParam(required = false, value = "q") final String query)
			throws Exception {
		return new ModelAndView("admin/userList", Constants.USER_LIST,
				mgr.search(query));
	}
}
