package com.mapflow.geo.common.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.annotation.ExpectedException;

import com.mapflow.geo.common.constants.Constants;
import com.mapflow.geo.common.dao.UserDao;
import com.mapflow.geo.common.manager.UserManager;
import com.mapflow.geo.common.model.Role;
import com.mapflow.geo.common.model.entities.User;
import com.mapflow.geo.common.service.UserSecurityAdvice;

@RunWith(JMock.class)
public class UserSecurityAdviceTest {

  Mockery context = new JUnit4Mockery();
  UserDao userDao = null;
  ApplicationContext ctx = null;
  SecurityContext initialSecurityContext = null;

  @Before
  public void setUp() throws Exception {
    // store initial security context for later restoration
    initialSecurityContext = SecurityContextHolder.getContext();

    final SecurityContext context = new SecurityContextImpl();
    final User user = new User("user");
    user.setId(1L);
    user.setPassword("password");
    user.addRole(new Role(Constants.USER_ROLE));

    final UsernamePasswordAuthenticationToken token =
      new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
        user.getAuthorities());
    token.setDetails(user);
    context.setAuthentication(token);
    SecurityContextHolder.setContext(context);
  }

  @After
  public void tearDown() {
    SecurityContextHolder.setContext(initialSecurityContext);
  }

  @Test
  public void testAddUserWithoutAdminRole() throws Exception {
    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    assertTrue(auth.isAuthenticated());
    final UserManager userManager = makeInterceptedTarget();
    final User user = new User("admin");
    user.setId(2L);

    try {
      userManager.saveUser(user);
      fail("AccessDeniedException not thrown");
    }
    catch (final AccessDeniedException expected) {
      assertNotNull(expected);
      Assert.assertEquals(expected.getMessage(), UserSecurityAdvice.ACCESS_DENIED);
    }
  }

  @Test
  public void testAddUserAsAdmin() throws Exception {
    final SecurityContext securityContext = new SecurityContextImpl();
    final User user = new User("admin");
    user.setId(2L);
    user.setPassword("password");
    user.addRole(new Role(Constants.ADMIN_ROLE));
    final UsernamePasswordAuthenticationToken token =
      new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
        user.getAuthorities());
    token.setDetails(user);
    securityContext.setAuthentication(token);
    SecurityContextHolder.setContext(securityContext);

    final UserManager userManager = makeInterceptedTarget();
    final User adminUser = new User("admin");
    adminUser.setId(2L);

    context.checking(new Expectations() {

      {
        one(userDao).saveUser(with(same(adminUser)));
      }
    });

    userManager.saveUser(adminUser);
  }

  @Test
  @ExpectedException(AccessDeniedException.class)
  public void testUpdateUserProfile() throws Exception {
    final UserManager userManager = makeInterceptedTarget();
    final User user = new User("user");
    user.setId(4L);
    user.getRolesList().add(new Role(Constants.USER_ROLE));

    context.checking(new Expectations() {

      {
//        one(userDao).saveUser(with(same(user)));
      }
    });

    try {
      userManager.saveUser(user);
    }
    catch (AccessDeniedException e) {
    }
  }

  // Test fix to http://issues.appfuse.org/browse/APF-96
  @Test
  public void testChangeToAdminRoleFromUserRole() throws Exception {
    final UserManager userManager = makeInterceptedTarget();
    final User user = new User("user");
    user.setId(1L);
    user.getRolesList().add(new Role(Constants.ADMIN_ROLE));

    try {
      userManager.saveUser(user);
      fail("AccessDeniedException not thrown");
    }
    catch (final AccessDeniedException expected) {
      assertNotNull(expected);
      assertEquals(expected.getMessage(), UserSecurityAdvice.ACCESS_DENIED);
    }
  }

  // Test fix to http://issues.appfuse.org/browse/APF-96
  @Test
  public void testAddAdminRoleWhenAlreadyHasUserRole() throws Exception {
    final UserManager userManager = makeInterceptedTarget();
    final User user = new User("user");
    user.setId(1L);
    user.getRolesList().add(new Role(Constants.ADMIN_ROLE));
    user.getRolesList().add(new Role(Constants.USER_ROLE));

    try {
      userManager.saveUser(user);
      fail("AccessDeniedException not thrown");
    }
    catch (final AccessDeniedException expected) {
      assertNotNull(expected);
      assertEquals(expected.getMessage(), UserSecurityAdvice.ACCESS_DENIED);
    }
  }

  // Test fix to http://issues.appfuse.org/browse/APF-96
  @Test
  public void testAddUserRoleWhenHasAdminRole() throws Exception {
    final SecurityContext securityContext = new SecurityContextImpl();
    final User user1 = new User("user");
    user1.setId(1L);
    user1.setPassword("password");
    user1.addRole(new Role(Constants.ADMIN_ROLE));
    final UsernamePasswordAuthenticationToken token =
      new UsernamePasswordAuthenticationToken(user1.getUsername(), user1.getPassword(),
        user1.getAuthorities());
    token.setDetails(user1);
    securityContext.setAuthentication(token);
    SecurityContextHolder.setContext(securityContext);

    final UserManager userManager = makeInterceptedTarget();
    final User user = new User("user");
    user.setId(1L);
    user.getRolesList().add(new Role(Constants.ADMIN_ROLE));
    user.getRolesList().add(new Role(Constants.USER_ROLE));

    context.checking(new Expectations() {

      {
        one(userDao).saveUser(with(same(user)));
      }
    });

    userManager.saveUser(user);
  }

  // Test fix to http://issues.appfuse.org/browse/APF-96
  @Test
  @ExpectedException(AccessDeniedException.class)
  public void testUpdateUserWithUserRole() throws Exception {
    final UserManager userManager = makeInterceptedTarget();
    final User user = new User("user");
    user.setId(3L);
    user.getRolesList().add(new Role(Constants.USER_ROLE));

    context.checking(new Expectations() {

      {
        // one(userDao).saveUser(with(same(user)));
      }
    });

    try {
      userManager.saveUser(user);
    }
    catch (AccessDeniedException e) {
    }
  }

  private UserManager makeInterceptedTarget() {
    ctx = new ClassPathXmlApplicationContext("/spring/applicationContext-test.xml");

    final UserManager userManager = (UserManager) ctx.getBean("target");

    // Mock the userDao
    userDao = context.mock(UserDao.class);
    userManager.setUserDao(userDao);

    return userManager;
  }
}
