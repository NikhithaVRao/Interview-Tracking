package com.robosoft.interviewtracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.robosoft.interviewtracking.dto.MailDto;
import com.robosoft.interviewtracking.exception.CustomException;
import com.robosoft.interviewtracking.service.EmailService;


@Controller
public class EmailController 
{
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/finalMail",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpStatus> sendMail(@RequestBody MailDto mail)
	{

		try {
				 emailService.mailToAprovedCandidate(mail);
				 
			 }catch(Exception ex) 
			{
				 System.out.println(ex);
				 throw new CustomException(100, "mail not sent");
			}
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/availabilityMail",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpStatus> sendMailToPanelMember(@RequestHeader ("empID") String empId, @RequestBody MailDto mailData)
	{
	
		try {
			 emailService.mailToCheckAvailability(empId, mailData);
			 
		 }catch(Exception ex) 
		{
			 System.out.println(ex);
			 throw new CustomException(105, "mail not sent");
		}
		
		return new ResponseEntity<> (HttpStatus.OK);
	}

	
}
