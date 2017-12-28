package org.com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.com.model.Job;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;
 
	@Override
	public void addJob(Job job) {
		Session session = sessionFactory.getCurrentSession();
		session.save(job);
		
	}

	@Override
	public List<Job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job"); //select * from job
 		return query.list(); //list of all objects
	}

	@Override
	public Job getJob(int jobId) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.get(Job.class, jobId);
		return job;
	}
	
	

}
