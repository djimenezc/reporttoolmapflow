package org.appfuse.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mapflow.geo.common.model.User;

public class MockUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException,
    DataAccessException {
    return new User("testuser");
  }
}
