package test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;

import com.viv.mvcapp.filters.NameCheckFilter;

public class NameCheckFilterTest {
	
	@Test
	public void testJohnDoeInput() throws IOException, ServletException {
		NameCheckFilter filter = new NameCheckFilter();
		MockFilterConfig config = new MockFilterConfig();
		MockServletContext context = new MockServletContext();
		context.addInitParameter("adminUser", "Vivien Ngo");
		MockHttpServletRequest request = new MockHttpServletRequest(context);
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockFilterChain chain = new MockFilterChain();
		
		request.addParameter("firstName", "John");
		request.addParameter("lastName", "Doe");
		request.addParameter("formSubmit", "true");
		
		filter.setFilterConfig(config);
		filter.doFilter(request, response, chain);
		
		String message = (String)request.getAttribute("message");
		assertEquals("unexpected message attribute", "You should use something more creative than ", message);
		
	}
	
	@Test
	public void testAdminInput() throws IOException, ServletException {
		NameCheckFilter filter = new NameCheckFilter();
		MockFilterConfig config = new MockFilterConfig();
		MockServletContext context = new MockServletContext();
		context.addInitParameter("adminUser", "Vivien Ngo");
		MockHttpServletRequest request = new MockHttpServletRequest(context);
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockFilterChain chain = new MockFilterChain();
		
		request.addParameter("firstName", "Vivien");
		request.addParameter("lastName", "Ngo");
		request.addParameter("formSubmit", "true");
		
		filter.setFilterConfig(config);
		filter.doFilter(request, response, chain);
		
		String userName = (String)request.getSession().getAttribute("userSession");
		assertEquals("unexpected name for admin", "Vivien Ngo (Admin)", userName);
	}
	
	@Test
	public void testScriptInput() throws IOException, ServletException{
		NameCheckFilter filter = new NameCheckFilter();
		MockFilterConfig config = new MockFilterConfig();
		MockServletContext context = new MockServletContext();
		context.addInitParameter("adminUser", "Vivien Ngo");
		MockHttpServletRequest request = new MockHttpServletRequest(context);
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockFilterChain chain = new MockFilterChain();
		
		request.addParameter("firstName", "<script>kill();");
		request.addParameter("lastName", "</script>");
		request.addParameter("formSubmit", "true");
		
		filter.setFilterConfig(config);
		filter.doFilter(request, response, chain);
		
		boolean inputContainsScript = (Boolean)request.getAttribute("inputContainsScript");
		assertTrue("did not catch script", inputContainsScript);
		
		
		chain = new MockFilterChain();
		response = new MockHttpServletResponse();		
		request = new MockHttpServletRequest(context);
		
		request.addParameter("firstName", "friendly");
		request.addParameter("lastName", "text");
		request.addParameter("formSubmit", "true");
		
		filter.setFilterConfig(config);
		filter.doFilter(request, response, chain);
		
		inputContainsScript = (Boolean)request.getAttribute("inputContainsScript");
		assertFalse("incorrectly caught script", inputContainsScript);
		
	}
	
}
