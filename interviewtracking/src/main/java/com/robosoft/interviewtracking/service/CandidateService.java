package com.robosoft.interviewtracking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.robosoft.interviewtracking.dto.Candidate;


public interface CandidateService {

	ResponseEntity<Candidate> addCandidate(Candidate candidate);

	List<Candidate> getShortlistedCandidate(int experience, String skills);

	ResponseEntity<Candidate> updateCandidate(int id, Candidate candidate);

	@SuppressWarnings("rawtypes")
	ResponseEntity deleteSkills(int id, String skills);

//	void sendEmail(MailDto mailDto) throws MessagingException;
}
