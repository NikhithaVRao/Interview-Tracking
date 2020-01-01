package com.robosoft.interviewtracking.service;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;

import com.robosoft.interviewtracking.dto.HRPanelDto;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.dto.MailDto;

public interface HRPanelService {

	ResponseEntity<HRPanelDto> addHRPanel(HRPanelDto hrPanelDto);

	void sendEmailToCandidate(MailDto mailDto) throws MessagingException;

//	void sendEmailToPanelists(MailDto mailDto) throws MessagingException;

	ResponseEntity<InterviewProcessDto> getComment(String interviewId, String round);
	
	ResponseEntity<InterviewProcessDto> addStatus(String interviewId, InterviewProcessDto interviewDto);

	ResponseEntity addFinalResult(String interviewId, String finalResult);
	
	ResponseEntity updateFinalResult(String interviewId, String finalResult);
}
