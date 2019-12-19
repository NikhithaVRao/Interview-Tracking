package com.robosoft.interviewtracking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.robosoft.interviewtracking.dto.InterviewProcessDto;

public interface InterviewProcessService {

	ResponseEntity<InterviewProcessDto> addInterviewDetails(int candidateId, InterviewProcessDto interview);

	




}
