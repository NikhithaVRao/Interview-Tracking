package com.robosoft.interviewtracking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.robosoft.interviewtracking.dto.CommentsDto;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.dto.TechnicalPanelDto;

public interface TechPanelService {
	
	TechnicalPanelDto addTechnicalPanel(TechnicalPanelDto techPanelDto);

	

	ResponseEntity<TechnicalPanelDto> setAvailability(String employeeId, TechnicalPanelDto techPanelDto);

	List<TechnicalPanelDto> getPanelists();

	ResponseEntity<InterviewProcessDto> addComments(InterviewProcessDto interviewDto);
}
