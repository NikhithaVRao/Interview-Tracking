package com.robosoft.interviewtracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robosoft.interviewtracking.model.HRPanelModel;

public interface HRPanelRepository extends JpaRepository<HRPanelModel, Integer>{

	
	@Query("Select c.email from CandidateModel c where c.isShortListed = true")
	List<String> getMail();
	
	@Query("Select c.name from CandidateModel c where c.isShortListed = true")
	List<String> getName();
}
