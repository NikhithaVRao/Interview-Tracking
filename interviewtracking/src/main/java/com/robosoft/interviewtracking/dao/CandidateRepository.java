package com.robosoft.interviewtracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.robosoft.interviewtracking.model.CandidateModel;

public interface CandidateRepository extends JpaRepository<CandidateModel, Integer> {

		CandidateModel findByUniqueId(String uniqueId);
		
//		@Query("Select c.email from CandidateModel c where c.isShortListed = true")
//		List<String> getMail();
//		
////		@Query("Select c.name from CandidateModel c where c.isShortListed = true")
////		List<String> getName();
		
}
