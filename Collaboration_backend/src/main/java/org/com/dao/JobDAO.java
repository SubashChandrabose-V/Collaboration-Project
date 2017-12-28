package org.com.dao;

import java.util.List;

import org.com.model.Job;

public interface JobDAO {
	
	void addJob(Job job);
	List<Job> getAllJobs();
	Job getJob(int jobId);

}
