package com.robosoft.interviewtracking.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;

import com.robosoft.interviewtracking.dto.CommentsDto;
import com.robosoft.interviewtracking.dto.HRPanelDto;
import com.robosoft.interviewtracking.dto.MailDto;
import com.robosoft.interviewtracking.model.CommentModel;

public interface HRPanelService {

	ResponseEntity<HRPanelDto> addHRPanel(HRPanelDto hrPanelDto);

	void sendEmailToCandidate(MailDto mailDto) throws MessagingException;

	void sendEmailToPanelists(MailDto mailDto) throws MessagingException;

	ResponseEntity<CommentsDto> getComment(String interviewId);
}
