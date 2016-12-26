package com.journaldev.spring;

import com.journaldev.spring.model.Test;
import com.journaldev.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	private TestService testService;
	
	@Autowired(required=true)
	@Qualifier(value="testService")
	public void setPersonService(TestService ts){
		this.testService = ts;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView listTests() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("test", new Test());
		mav.addObject("listTests", this.testService.listTests());
		return mav;
	}
}
