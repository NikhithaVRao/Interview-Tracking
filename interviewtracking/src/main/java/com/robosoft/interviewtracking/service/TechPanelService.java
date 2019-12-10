package com.robosoft.interviewtracking.service;

import java.util.List;

import com.robosoft.interviewtracking.dto.TechnicalPanel;

public interface TechPanelService {
	
	TechnicalPanel addTechnicalPanel(TechnicalPanel techPanelDto);

	List<TechnicalPanel> getPanelist(String panelId, String expertise);

}
