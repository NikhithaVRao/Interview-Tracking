package com.robosoft.interviewtracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robosoft.interviewtracking.model.CandidateModel;

public interface CandidateRepository extends JpaRepository<CandidateModel, Integer> {

		CandidateModel findByUniqueId(String uniqueId);
		
		@Query("Select c.email from CandidateModel c where c.isShortListed = true")
		List<String> getMail();
		
		@Query("Select c.name from CandidateModel c where c.isShortListed = true")
		List<String> getName();
		
		@Query("select c from CandidateModel c where c.isShortListed = true")
		List<CandidateModel> findShorlistedId();

		CandidateModel findByInterviewId(String interviewId);
		
		List<CandidateModel> findByfinalResult(String var);
}
