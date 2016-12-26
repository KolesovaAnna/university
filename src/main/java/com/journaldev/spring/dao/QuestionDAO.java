package com.journaldev.spring.dao;

import com.journaldev.spring.model.Question;

import java.util.List;

public interface QuestionDAO {
	void addQuestion(Question q);
	void updateQuestion(Question q);
	Question getQuestionById(int id);
	List<Question> listByTestID(int id);
	void removeQuestion(int id);
}
