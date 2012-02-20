package com.mapflow.webapp.controller.stats;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mapflow.geo.common.constants.Constants;
import com.mapflow.geo.reporting.report.Report;

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
@RequestMapping("/stats/reports*")
public class ReportStatsController extends AbstractStatsController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(
			@RequestParam(required = false, value = "q") final String query)
			throws Exception {

		final List<Report> model = new ArrayList<Report>();

		return new ModelAndView("stats/reports", Constants.REPORT_LIST, model);
	}
}
