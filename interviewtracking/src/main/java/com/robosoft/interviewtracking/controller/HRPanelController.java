package com.robosoft.interviewtracking.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robosoft.interviewtracking.dto.CommentsDto;
import com.robosoft.interviewtracking.dto.HRPanelDto;
import com.robosoft.interviewtracking.dto.MailDto;
import com.robosoft.interviewtracking.exception.CustomException;
import com.robosoft.interviewtracking.model.CommentModel;
import com.robosoft.interviewtracking.service.HRPanelService;

@Controller("/hrPanel")
public class HRPanelController {
	@Autowired
	HRPanelService hrService;
	
	/* To add HR panel */
	@PostMapping(value = "hrPanel")
	@ResponseBody 
	public ResponseEntity<HRPanelDto> addHRPanel(@RequestBody HRPanelDto hrPanelDto)
	{
		return hrService.addHRPanel(hrPanelDto);
	}
	
	
//	/* To send an email to candidate*/ 
//	@RequestMapping(value = "candidate/email", method = RequestMethod.POST)
//	@ResponseBody 
//	public void sendEmailToCandidate(@RequestBody MailDto mailDto)
//	{
//		 System.out.println("date = " +mailDto.getDate());
//		try {
//			hrService.sendEmailToCandidate(mailDto);
//		} catch (MessagingException e) {
//			throw new CustomException(100,"invalid mail");
//		}
//	}
//	
//	/* To send an email to panelists*/
//	@RequestMapping(value = "techPanel/email", method = RequestMethod.POST)
//	@ResponseBody 
//	public void sendEmailToPanelists(@RequestBody MailDto mailDto)
//	{
//		 System.out.println("date = " +mailDto.getDate());
//		try {
//			hrService.sendEmailToCandidate(mailDto);
//		} catch (MessagingException e) {
//			throw new CustomException(100,"invalid mail");
//		}
//	}
	@GetMapping(value = "hrpanel/getcomment")
	@ResponseBody
	public ResponseEntity<CommentsDto> getComments(@RequestHeader("interviewid") String interviewId){
		return hrService.getComment(interviewId);
	}
	
	@PostMapping(value = "hrpanel/addstatus")
	@ResponseBody
	public ResponseEntity addStatus(@RequestHeader("interviewid") String interviewId, @RequestHeader("status") boolean status){
		return hrService.addStatus(interviewId, status);
	}
}
