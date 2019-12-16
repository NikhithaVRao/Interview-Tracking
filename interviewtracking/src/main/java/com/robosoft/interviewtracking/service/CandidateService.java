package com.robosoft.interviewtracking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.robosoft.interviewtracking.dto.CandidateDto;


public interface CandidateService {

	ResponseEntity<CandidateDto> addCandidate(CandidateDto candidate);

	List<CandidateDto> getShortlistedCandidate(int experience, String skills);

	ResponseEntity<CandidateDto> updateCandidate(int id, CandidateDto candidate);

	@SuppressWarnings("rawtypes")
	ResponseEntity deleteSkills(int id, String skills);

//	void sendEmail(MailDto mailDto) throws MessagingException;
}
