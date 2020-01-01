package com.robosoft.interviewtracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.dto.TechnicalPanelDto;
import com.robosoft.interviewtracking.service.TechPanelService;

@Controller("/")
public class TechnicalPanelController {
	@Autowired
	TechPanelService techService;
	
	/*To add technical panel */
	@PostMapping(value = "techPanel")
	@ResponseBody 
	public TechnicalPanelDto addTechnicalPanel(@RequestBody TechnicalPanelDto techPanelDto)
	{
		return techService.addTechnicalPanel(techPanelDto);
	}
	
/* to set panelists availaibility */
	
	@PostMapping(value = "techPanel/availabilty")
	public ResponseEntity<TechnicalPanelDto> setAvailability(@RequestHeader ("employeeId") String employeeId ,@RequestBody TechnicalPanelDto techPanelDto)
	{
		return techService.setAvailability(employeeId,techPanelDto);
	}
	
/* To shortlist panelists */
	@GetMapping(value = "techPanel")
	@ResponseBody 
	public List<TechnicalPanelDto> getPanelists()
	{
		return techService.getPanelists();
	}
	
/* To post comments into comments table */
	
	@PostMapping(value = "techPanel/comments")
	public ResponseEntity<InterviewProcessDto> addComments(@RequestBody InterviewProcessDto interviewDto)
	{
		System.out.println(interviewDto);
		return techService.addComments(interviewDto);
	}
	
}
