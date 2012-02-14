package com.mapflow.webapp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.compass.gps.CompassGps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.mapflow.geo.common.constants.Constants;

public class UserControllerTest extends BaseControllerTestCase {

  @Autowired
  private CompassGps compassGps;
  @Autowired
  private UserController c;

  @Test
  public void testHandleRequest() throws Exception {
    final ModelAndView mav = c.handleRequest(null);
    final Map<?, ?> m = mav.getModel();
    assertNotNull(m.get(Constants.USER_LIST));
    assertEquals("admin/userList", mav.getViewName());
  }

  @Test
  public void testSearch() throws Exception {
    compassGps.index();
    final ModelAndView mav = c.handleRequest("admin");
    final Map<?, ?> m = mav.getModel();
    final List<?> results = (List<?>) m.get(Constants.USER_LIST);
    assertNotNull(results);
    assertTrue(results.size() >= 1);
    assertEquals("admin/userList", mav.getViewName());
  }
}
