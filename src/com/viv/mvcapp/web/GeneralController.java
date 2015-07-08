package com.viv.mvcapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GeneralController {

	@RequestMapping(value="/*", method = RequestMethod.POST)
	public ModelAndView showNavBar(ModelAndView mv) {
		mv.setViewName("NavBar");
		
		return mv;
	}

}
