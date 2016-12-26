package com.journaldev.spring;

import com.journaldev.spring.model.Test;
import com.journaldev.spring.model.Question;
import com.journaldev.spring.model.Answer;
import com.journaldev.spring.service.TestService;
import com.journaldev.spring.service.QuestionService;
import com.journaldev.spring.service.AnswerService;
import jdk.internal.util.xml.impl.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
	
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
	
	@RequestMapping(value = "/tests", method = RequestMethod.GET)
	public String listTests(Model model) {
		model.addAttribute("test", new Test());
		model.addAttribute("listTests", this.testService.listTests());
		return "test";
	}

	@RequestMapping(value= "/test/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("test") Test t){

		if(t.getId() == 0){
			//new person, add it
			this.testService.addTest(t);
		}else{
			//existing person, call update
			this.testService.updateTest(t);
		}
		return "redirect:/";

	}

	@RequestMapping("/runTest/{id}")
	public ModelAndView runTest(@PathVariable("id") int id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test");
		mav.addObject("test", this.testService.getTestById(id));
		List<Question> qs = this.questionService.listByTestID(id);
		for (Question q: qs
			 ) {
			q.setAnswers(this.answerService.listByQuestionID(q.getId()));
		}
		mav.addObject("question", qs);
		return mav;
	}

	@RequestMapping("/result/{id}")
	public ModelAndView result(@PathVariable("id") int id, @RequestParam(value = "checking") int[] answers){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("resultTest");
		List<Question> qs = this.questionService.listByTestID(id);
		Map<Integer, com.journaldev.spring.Pair> mapUser = new HashMap<Integer, com.journaldev.spring.Pair>();
		for (Question q: qs) {
			mapUser.put(q.getId(),new com.journaldev.spring.Pair(0,0));
		}
		for (int a: answers) {
			Answer ans = this.answerService.getAnswerById(a);
			if(ans.getIs_right()){
				mapUser.put(ans.getId_question(), mapUser.get(ans.getId_question()).incRight());
			}else {
				mapUser.put(ans.getId_question(), mapUser.get(ans.getId_question()).incWrong());
			}
		}

		Double result=0.0;

		for (Map.Entry<Integer, com.journaldev.spring.Pair> entry: mapUser.entrySet()) {
			if(entry.getValue().getRight() == this.questionService.getQuestionById(entry.getKey()).getRight_answers() &&
					entry.getValue().getWrong()== 0	)
			{result+=1;}
			if(entry.getValue().getRight() == (this.questionService.getQuestionById(entry.getKey()).getRight_answers()-1) &&
					(entry.getValue().getWrong()== 0) && (entry.getValue().getRight()>0))
			{result+=0.2;}
			if(entry.getValue().getRight() == this.questionService.getQuestionById(entry.getKey()).getRight_answers() &&
					entry.getValue().getWrong()== 1)
			{result+=0.2;}
			else {result += 0;}
		}

		int prores = (int)Math.round((result/qs.size())*100);
		mav.addObject("result", prores);
		mav.addObject("test", this.testService.getTestById(id));
		return mav;
	}

	@RequestMapping("/removeTest/{id}")
    public String removeTest(@PathVariable("id") int id){
		
        this.testService.removeTest(id);
        List<Question> qs =this.questionService.listByTestID(id);
		for (Question q:qs) {
			List<Answer> ans = this.answerService.listByQuestionID(q.getId());
			for ( Answer a:ans) {
				this.answerService.removeAnswer(a.getId());
			}
			this.questionService.removeQuestion(q.getId());
		}

		return "redirect:/";
    }

	@RequestMapping("/editTest/{id}")
	public ModelAndView editTest(@PathVariable("id") int id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("AddTest");
		mav.addObject("test", this.testService.getTestById(id));
		List<Question> qs = this.questionService.listByTestID(id);
		for (Question q: qs
				) {
			q.setAnswers(this.answerService.listByQuestionID(q.getId()));
		}
		mav.addObject("question", qs);
		return mav;
	}

	@RequestMapping(value= "/editTest/{id}/test", method = RequestMethod.POST)
	public String addTest(@ModelAttribute("test") Test t){

		if(t.getId() == 0){
			//new test, add it
			this.testService.addTest(t);
		}else{
			//existing test, call update
			this.testService.updateTest(t);
		}

		return "redirect:/editTest/{id}";
	}

}
