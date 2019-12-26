package com.robosoft.interviewtracking.service;

import org.springframework.http.ResponseEntity;

import com.robosoft.interviewtracking.dto.InterviewProcessDto;

public interface InterviewProcessService {

	ResponseEntity<InterviewProcessDto> addInterviewDetails(String interviewId, InterviewProcessDto interview);

	




}
