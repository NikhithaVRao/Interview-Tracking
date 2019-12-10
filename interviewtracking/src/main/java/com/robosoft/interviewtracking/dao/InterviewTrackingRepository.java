package com.robosoft.interviewtracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.robosoft.interviewtracking.model.CandidateModel;



public interface InterviewTrackingRepository extends JpaRepository<CandidateModel, Integer> {

	}
