package com.robosoft.interviewtracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robosoft.interviewtracking.model.InterviewProcessModel;



public interface InterviewTrackingRepository extends JpaRepository<InterviewProcessModel, Integer> {

	@Query("Select c from InterviewProcessModel c where candidate_id = candidateId")
	InterviewProcessModel findByCandidateId(int candidateId); 
	}
