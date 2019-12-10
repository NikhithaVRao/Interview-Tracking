package com.robosoft.interviewtracking.service;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;


import com.robosoft.interviewtracking.dto.HRPanel;
import com.robosoft.interviewtracking.dto.MailDto;

public interface HRPanelService {

	ResponseEntity<HRPanel> addHRPanel(HRPanel hrPanelDto);

	void sendEmailToCandidate(MailDto mailDto) throws MessagingException;

	void sendEmailToPanelists(MailDto mailDto) throws MessagingException;
}
