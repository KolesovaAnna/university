package com.journaldev.spring.dao;

import com.journaldev.spring.model.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDAOImpl implements TestDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(TestDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addTest(Test t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(t);
		logger.info("Test saved successfully, Test Details="+t);
	}

	@Override
	public void updateTest(Test t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(t);
		logger.info("Test updated successfully, Test Details="+t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> listTests() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Test> testsList = session.createQuery("from Test").list();
		for(Test t : testsList){
			logger.info("Test List::"+t);
		}
		return testsList;
	}

	@Override
	public Test getTestById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Test t = (Test) session.load(Test.class, new Integer(id));
		logger.info("Test loaded successfully, Test details="+t);
		return t;
	}

	@Override
	public void removeTest(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Test t = (Test) session.load(Test.class, new Integer(id));
		if(null != t){
			session.delete(t);
		}
		logger.info("Test deleted successfully, test details="+t);
	}

}
