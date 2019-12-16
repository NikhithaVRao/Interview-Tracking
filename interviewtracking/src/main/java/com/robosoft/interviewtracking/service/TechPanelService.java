package com.robosoft.interviewtracking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.robosoft.interviewtracking.dto.CommentsDto;
import com.robosoft.interviewtracking.dto.TechnicalPanelDto;

public interface TechPanelService {
	
	TechnicalPanelDto addTechnicalPanel(TechnicalPanelDto techPanelDto);

	List<TechnicalPanelDto> getPanelist(String panelId, String expertise);

	ResponseEntity<CommentsDto> addComments(CommentsDto comments);

}
