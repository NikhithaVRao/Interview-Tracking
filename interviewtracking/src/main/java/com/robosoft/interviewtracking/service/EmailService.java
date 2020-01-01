package com.robosoft.interviewtracking.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.dto.MailDto;
import com.robosoft.interviewtracking.model.CandidateModel;

public interface EmailService 
{
	void sendMail(MailDto mail);
	ResponseEntity<HttpStatus> mailToAprovedCandidate(MailDto mail);
	//ResponseEntity<HttpStatus> mailToShortListedCandidate(CandidateModel canObj, InterviewProcessDto interview);
	ResponseEntity<HttpStatus> mailToCheckAvailability(String empId, MailDto mailData);

}
