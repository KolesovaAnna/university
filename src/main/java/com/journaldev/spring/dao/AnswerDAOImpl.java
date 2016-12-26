package com.journaldev.spring.dao;

import com.journaldev.spring.model.Answer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerDAOImpl implements AnswerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(AnswerDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addAnswer(Answer a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(a);
		logger.info("Answer saved successfully, Answer Details="+a);
	}

	@Override
	public void updateAnswer(Answer a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(a);
		logger.info("Answer updated successfully, Answer Details="+a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> listByQuestionID(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Answer> answersList = session.createQuery("from Answer where id_question = :id").setParameter("id", id).list();
		for(Answer a: answersList){
			logger.info("Answer List::"+a);
		}
		return answersList;
	}

	@Override
	public Answer getAnswerById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Answer a= (Answer) session.load(Answer.class, new Integer(id));
		logger.info("Answer loaded successfully, Answer details="+a);
		return a;
	}

	@Override
	public void removeAnswer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Answer a = (Answer) session.load(Answer.class, new Integer(id));
		if(null != a){
			session.delete(a);
		}
		logger.info("Answer deleted successfully, Answer details="+a);
	}

}
