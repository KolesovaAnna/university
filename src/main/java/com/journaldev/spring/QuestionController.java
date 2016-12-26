package com.journaldev.spring;

import com.journaldev.spring.model.Answer;
import com.journaldev.spring.model.Question;
import com.journaldev.spring.model.Test;
import com.journaldev.spring.service.AnswerService;
import com.journaldev.spring.service.QuestionService;
import com.journaldev.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {
	
	private TestService testService;
	private QuestionService questionService;
	private AnswerService answerService;
	
	@Autowired(required=true)
	@Qualifier(value="testService")
	public void setTestService(TestService ts){
		this.testService = ts;
	}

	@Autowired(required=true)
	@Qualifier(value="answerService")
	public void setAnswerService(AnswerService as){
		this.answerService = as;
	}

	@Autowired(required=true)
	@Qualifier(value="questionService")
	public void setQuestionService(QuestionService qs){
		this.questionService = qs;
	}


	@RequestMapping(value= "/editQuestion", method = RequestMethod.POST)
	public String editQuestion(@ModelAttribute("question") Question q){

		if(q.getId() == 0){
			//new test, add it
			this.questionService.addQuestion(q);
		}else{
			//existing test, call update
			this.questionService.updateQuestion(q);
		}

		return "redirect:/editTest/"+q.getId_test();
	}

	@RequestMapping(value= "/addQuestion", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute("question") Question q){
		if(q.getId() == 0){
		this.questionService.addQuestion(q);}
		return "redirect:/editTest/"+q.getId_test();
	}

	@RequestMapping("/removeQuestion/{id}")
	public String removeQuestion(@PathVariable("id") int id){

		Question q = this.questionService.getQuestionById(id);
		List<Answer> ans = this.answerService.listByQuestionID(id);
		if(!ans.isEmpty()) {
			System.out.print("Tell me you here");
			for (Answer a : ans) {
				this.answerService.removeAnswer(a.getId());
			}
		}
		int testId = q.getId_test();
		this.questionService.removeQuestion(id);
		return "redirect:/editTest/"+testId;
	}
}
