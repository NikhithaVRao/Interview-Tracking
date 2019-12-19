package com.robosoft.interviewtracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.robosoft.interviewtracking.model.TechnicalPanelModel;

public interface TechnicalPanelRepository extends JpaRepository<TechnicalPanelModel, Integer>{

	@Query("Select t from TechnicalPanelModel t where t.employeeId = :employeeId")
	TechnicalPanelModel  findByEmployeeId(@Param("employeeId") String employeeId);
	
	List<TechnicalPanelModel> findAll();
}
