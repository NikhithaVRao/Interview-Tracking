package com.robosoft.interviewtracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robosoft.interviewtracking.dto.Candidate;
import com.robosoft.interviewtracking.service.InterviewProcessService;

@Controller("/interview")
public class InterviewTrackingController {
	
	@Autowired
	InterviewProcessService interviewProcessService ;
	
	
	@PostMapping(value="interview")
	@ResponseBody
	public List<Integer> interview()
	{
		return interviewProcessService.add();
	}


}
