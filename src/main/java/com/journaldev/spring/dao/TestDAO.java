package com.journaldev.spring.dao;

import com.journaldev.spring.model.Test;

import java.util.List;

public interface TestDAO {
	void addTest(Test t);
	void updateTest(Test t);
	List<Test> listTests();
	Test getTestById(int id);
	void removeTest(int id);
}
