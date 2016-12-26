package com.journaldev.spring.service;

import com.journaldev.spring.dao.AnswerDAO;
import com.journaldev.spring.model.Answer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	private AnswerDAO answerDAO;

	public void setAnswerDAO(AnswerDAO answerDAO) {
		this.answerDAO = answerDAO;
	}

	@Override
	@Transactional
	public void addAnswer(Answer q) {
		this.answerDAO.addAnswer(q);
	}

	@Override
	@Transactional
	public void updateAnswer(Answer q) {
		this.answerDAO.updateAnswer(q);
	}

	@Override
	@Transactional
	public List<Answer> listByQuestionID(int id) {
		return this.answerDAO.listByQuestionID(id);
	}

	@Override
	@Transactional
	public Answer getAnswerById(int id) {
		return this.answerDAO.getAnswerById(id);
	}

	@Override
	@Transactional
	public void removeAnswer(int id) {
		this.answerDAO.removeAnswer(id);
	}

}
