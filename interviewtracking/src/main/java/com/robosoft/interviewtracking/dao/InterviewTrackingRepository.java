package com.robosoft.interviewtracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robosoft.interviewtracking.model.CandidateModel;
import com.robosoft.interviewtracking.model.InterviewProcessModel;



public interface InterviewTrackingRepository extends JpaRepository<InterviewProcessModel, Integer> {

	@Query("Select c.id from CandidateModel c where c.isShortListed = true")
	int[]getId();
	//List<Integer> getId(); 
	}
