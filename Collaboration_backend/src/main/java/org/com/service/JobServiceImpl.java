package org.com.service;

import java.util.List;

import org.com.dao.JobDAO;
import org.com.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobDAO jobDAO;
	
	@Override
	public void addJob(Job job) {
		jobDAO.addJob(job);

	}

	@Override
	public List<Job> getAllJobs() {
		
		return jobDAO.getAllJobs();
	}

	@Override
	public Job getJob(int jobId) {
		return jobDAO.getJob(jobId);
	}


}
