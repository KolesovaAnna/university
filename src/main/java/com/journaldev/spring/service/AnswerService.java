package com.journaldev.spring.service;

import com.journaldev.spring.model.Answer;

import java.util.List;

public interface AnswerService {
	void addAnswer(Answer a);
	void updateAnswer(Answer a);
	Answer getAnswerById(int id);
	List<Answer> listByQuestionID(int id);
	void removeAnswer(int id);
}
