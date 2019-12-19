package com.robosoft.interviewtracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.robosoft.interviewtracking.model.InterviewProcessModel;



public interface InterviewTrackingRepository extends JpaRepository<InterviewProcessModel, Integer> {

	@Query("Select c from InterviewProcessModel c where c.candidateId = :id")
	InterviewProcessModel findByCandidateId(@Param("id")int candidateId);

	InterviewProcessModel findByInterviewId(String interviewId); 
	}
