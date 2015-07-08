package com.viv.mvcapp.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.viv.mvcapp.web.FormController;

public class FormControllerTest {

	@Test
	public void testFinishForm() {
		FormController controller = new FormController();
		MockHttpServletRequest request = new MockHttpServletRequest();
		ModelAndView mv = new ModelAndView();
		
		request.addParameter("firstName", "Jane");
		request.addParameter("lastName", "Doe");
		request.addParameter("color", "green");
		
		mv = controller.finishForm(mv, request);
		
		ModelAndViewAssert.assertViewName(mv, "FormFinish");
		assertEquals("firstName not passed", "Jane", request.getAttribute("firstName"));
		assertEquals("lastName not passed", "Doe", request.getAttribute("lastName"));
		assertEquals("color not passed", "green", request.getAttribute("color"));
		assertNotNull("factText not passed", request.getAttribute("factText"));
		assertNotNull("factPic not passed", request.getAttribute("factPic"));
	}
	
}
