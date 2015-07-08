package com.viv.mvcapp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.viv.mvcapp.domain.Factoid;
import com.viv.mvcapp.util.AnnotationCheckUtil;
import com.viv.mvcapp.util.FormUtil;


@Controller
@RequestMapping(value="/form")
public class FormController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView startForm(ModelAndView mv) {
		mv.setViewName("FormStart");
				
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView finishForm(ModelAndView mv, HttpServletRequest request) {
		request.setAttribute("firstName", request.getParameter("firstName"));
        request.setAttribute("lastName", request.getParameter("lastName"));
        request.setAttribute("color", request.getParameter("color"));
        
        Factoid fact = FormUtil.randomFact();
        request.setAttribute("factText", fact.getTxt());
        
        String factPic = fact.getPic();
        if(AnnotationCheckUtil.validFileName(fact)){
        	request.setAttribute("factPic", factPic);
        }
        
		mv.setViewName("FormFinish");
		
		return mv;
	}

}
