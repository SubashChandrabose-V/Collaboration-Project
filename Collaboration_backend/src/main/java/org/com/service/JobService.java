package org.com.service;

import java.util.List;

import org.com.model.Job;

public interface JobService {

	void addJob(Job job);
	
	List<Job> getAllJobs();

	Job getJob(int jobId);
}
