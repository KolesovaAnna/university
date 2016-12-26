package com.journaldev.spring.dao;

import com.journaldev.spring.model.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAOImpl implements QuestionDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addQuestion(Question q) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(q);
		logger.info("Question saved successfully, Question Details="+q);
	}

	@Override
	public void updateQuestion(Question q) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(q);
		logger.info("Question updated successfully, Question Details="+q);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> listByTestID(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Question> questionsList = session.createQuery("from Question where id_test = :id").setParameter("id", id).list();
		for(Question q: questionsList){
			logger.info("Question List::"+q);
		}
		return questionsList;
	}

	@Override
	public Question getQuestionById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Question q= (Question) session.load(Question.class, new Integer(id));
		logger.info("Question loaded successfully, Question details="+q);
		return q;
	}

	@Override
	public void removeQuestion(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Question q = (Question) session.load(Question.class, new Integer(id));
		if(null != q){
			session.delete(q);
		}
		logger.info("Question deleted successfully, Question details="+q);
	}

}
