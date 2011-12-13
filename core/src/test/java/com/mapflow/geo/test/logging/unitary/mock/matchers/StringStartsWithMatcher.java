package com.mapflow.geo.test.logging.unitary.mock.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class StringStartsWithMatcher extends TypeSafeMatcher<String> {

  private final String prefix;

  public StringStartsWithMatcher(final String prefix) {
    this.prefix = prefix;
  }

  @Override
  public final void describeTo(final Description description) {

    description.appendText("a string starting with ").appendValue(prefix);
  }

  @Override
  public final boolean matchesSafely(final String s) {
    return s.startsWith(prefix);
  }
}
