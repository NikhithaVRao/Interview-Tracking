package com.robosoft.interviewtracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.service.InterviewProcessService;

@Controller("/interview")
public class InterviewTrackingController {
	
	@Autowired
	InterviewProcessService interviewProcessService ;
	
	
	@PostMapping(value="interview")
	@ResponseBody
	public ResponseEntity<InterviewProcessDto> addInterviewDetails(@RequestHeader("id") String interviewId, @RequestBody InterviewProcessDto interview)
	{
		return interviewProcessService.addInterviewDetails(interviewId, interview);
	}


}
