package com.mapflow.geo.test.logging.unitary.mock.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.mapflow.geo.logging.model.LogMapdisplayTo;

public class LogIdEqual extends TypeSafeMatcher<LogMapdisplayTo> {

  private final String id;

  public LogIdEqual(final String name) {
    id = name;
  }

  @Override
  public final void describeTo(final Description description) {

    description.appendText("a name equals to ").appendValue(id);
  }

  @Override
  public final boolean matchesSafely(final LogMapdisplayTo log) {

    return log.getId().equals(id);
    // return false;
  }

}
