package com.journaldev.spring.service;

import com.journaldev.spring.dao.TestDAO;
import com.journaldev.spring.model.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
	
	private TestDAO testDAO;

	public void setTestDAO(TestDAO testDAO) {
		this.testDAO = testDAO;
	}

	@Override
	@Transactional
	public void addTest(Test t) {
		this.testDAO.addTest(t);
	}

	@Override
	@Transactional
	public void updateTest(Test t) {
		this.testDAO.updateTest(t);
	}

	@Override
	@Transactional
	public List<Test> listTests() {
		return this.testDAO.listTests();
	}

	@Override
	@Transactional
	public Test getTestById(int id) {
		return this.testDAO.getTestById(id);
	}

	@Override
	@Transactional
	public void removeTest(int id) {
		this.testDAO.removeTest(id);
	}

}
