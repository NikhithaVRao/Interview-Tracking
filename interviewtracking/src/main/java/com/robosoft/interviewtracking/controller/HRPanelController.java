package com.robosoft.interviewtracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robosoft.interviewtracking.dto.HRPanelDto;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.service.HRPanelService;

@Controller("/hrPanel")
public class HRPanelController {
	@Autowired
	HRPanelService hrService;
	
	/* To add HR panel */
	@PostMapping(value = "hrPanel")
	@ResponseBody 
	public ResponseEntity<HRPanelDto> addHRPanel(@RequestBody HRPanelDto hrPanelDto)
	{
		return hrService.addHRPanel(hrPanelDto);
	}
	
	
	@GetMapping(value = "hrpanel/getcomment")
	@ResponseBody
	public ResponseEntity<InterviewProcessDto> getComments(@RequestHeader("interviewid") String interviewId, @RequestHeader("round") String round){
		return hrService.getComment(interviewId, round);
	}
	
	
	@PostMapping(value = "hrpanel/addstatus")
	@ResponseBody
	public ResponseEntity<InterviewProcessDto> addStatus(@RequestHeader("interviewid") String interviewId, @RequestBody InterviewProcessDto interviewDto){
		return hrService.addStatus(interviewId, interviewDto);
	}
	
	@PostMapping(value = "hrpanel/addFinalResult")
	@ResponseBody
	public ResponseEntity addFinalResult(@RequestHeader("interviewId") String interviewId, @RequestBody String finalResult){
		return hrService.addFinalResult(interviewId, finalResult);
	}
	
	@PostMapping(value = "hrpanel/updateFinalResult")
	@ResponseBody
	public ResponseEntity updateFinalResult(@RequestHeader("interviewid") String interviewId, @RequestBody String finalResult){
		return hrService.updateFinalResult(interviewId, finalResult);
	}
}
