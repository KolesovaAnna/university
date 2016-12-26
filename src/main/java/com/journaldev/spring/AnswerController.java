package com.journaldev.spring;

import com.journaldev.spring.model.Answer;
import com.journaldev.spring.model.Question;
import com.journaldev.spring.service.AnswerService;
import com.journaldev.spring.service.QuestionService;
import com.journaldev.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AnswerController {
	
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


	@RequestMapping(value= "/editAnswer", method = RequestMethod.POST)
	public String editAnswer(@ModelAttribute("answer") Answer a){
		if(a.getId() == 0){
			//new test, add it
			this.answerService.addAnswer(a);
		}else{
			//existing test, call update
			this.answerService.updateAnswer(a);
		}
		return "redirect:/editTest/"+this.questionService.getQuestionById(a.getId_question()).getId_test();
	}

	@RequestMapping(value= "/addAnswer", method = RequestMethod.POST)
	public String addAnswer(@ModelAttribute("answer") Answer a){
		a.setСhoice("1000");
		a.setId(true);
		if(a.getId() == 0){
		this.answerService.addAnswer(a);}
		return "redirect:/editTest/"+this.questionService.getQuestionById(a.getId_question()).getId_test();
	}

	@RequestMapping("/removeAnswer/{id}")
	public String removeAnswer(@PathVariable("id") int id){
		Answer a = this.answerService.getAnswerById(id);
		int testId = this.questionService.getQuestionById(a.getId_question()).getId_test();
		this.answerService.removeAnswer(id);
		return "redirect:/editTest/"+testId;
	}
}
