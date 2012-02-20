package com.mapflow.webapp.filter;

import java.util.Locale;

import javax.servlet.jsp.jstl.core.Config;

import junit.framework.TestCase;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.mapflow.geo.common.constants.Constants;

public class LocaleFilterTest extends TestCase {

	private LocaleFilter filter = null;

	@Override
	protected void setUp() throws Exception {
		filter = new LocaleFilter();
		filter.init(new MockFilterConfig());
	}

	public void testSetLocaleInSessionWhenSessionIsNull() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("locale", "es");

		final MockHttpServletResponse response = new MockHttpServletResponse();
		filter.doFilter(request, response, new MockFilterChain());

		// no session, should result in null
		assertNull(request.getSession().getAttribute(
				Constants.PREFERRED_LOCALE_KEY));
		// thread locale should always have it, regardless of session
		assertNotNull(LocaleContextHolder.getLocale());
	}

	public void testSetLocaleInSessionWhenSessionNotNull() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("locale", "es");

		final MockHttpServletResponse response = new MockHttpServletResponse();
		request.setSession(new MockHttpSession(null));

		filter.doFilter(request, response, new MockFilterChain());

		// session not null, should result in not null
		final Locale locale = (Locale) request.getSession().getAttribute(
				Constants.PREFERRED_LOCALE_KEY);
		assertNotNull(locale);
		assertNotNull(LocaleContextHolder.getLocale());
		assertEquals(new Locale("es"), locale);
	}

	public void testSetInvalidLocale() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("locale", "foo");

		final MockHttpServletResponse response = new MockHttpServletResponse();
		request.setSession(new MockHttpSession(null));

		filter.doFilter(request, response, new MockFilterChain());

		// a locale will get set regardless - there's no such thing as an
		// invalid one
		assertNotNull(request.getSession().getAttribute(
				Constants.PREFERRED_LOCALE_KEY));
	}

	public void testJstlLocaleIsSet() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("locale", "es");

		final MockHttpServletResponse response = new MockHttpServletResponse();
		request.setSession(new MockHttpSession(null));

		filter.doFilter(request, response, new MockFilterChain());

		assertNotNull(Config.get(request.getSession(), Config.FMT_LOCALE));
	}

	public void testLocaleAndCountry() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(new MockHttpSession());
		request.addParameter("locale", "zh_TW");

		final MockHttpServletResponse response = new MockHttpServletResponse();
		filter.doFilter(request, response, new MockFilterChain());

		// session not null, should result in not null
		final Locale locale = (Locale) request.getSession().getAttribute(
				Constants.PREFERRED_LOCALE_KEY);
		assertNotNull(locale);
		assertEquals(new Locale("zh", "TW"), locale);
	}
}
