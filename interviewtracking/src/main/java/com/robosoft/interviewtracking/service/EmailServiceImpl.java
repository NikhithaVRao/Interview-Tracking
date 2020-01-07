package com.robosoft.interviewtracking.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.CandidateRepository;
import com.robosoft.interviewtracking.dao.TechnicalPanelRepository;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.dto.MailDto;
import com.robosoft.interviewtracking.exception.CustomException;
import com.robosoft.interviewtracking.model.CandidateModel;
import com.robosoft.interviewtracking.model.TechnicalPanelModel;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class EmailServiceImpl  implements EmailService

{
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	CandidateRepository canRepo;
	
	
	@Autowired
	TechnicalPanelRepository techRepo;
	
	
	
	@Override
	public  void sendMail(MailDto mailData)
	{
		
		MimeMessage message = sender.createMimeMessage();
		try
		{
		
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setSubject(mailData.getSubject());
		helper.setText(mailData.getText());
		helper.setTo(mailData.getEmailID());
		sender.send(message);
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new CustomException(200, "mail not sent");
		}
	}

	
	
	@Override
	public ResponseEntity<HttpStatus> mailToAprovedCandidate(MailDto mailData) 
	{
		List<CandidateModel> obj = canRepo.findByfinalResult("selected");
		for(CandidateModel mail : obj)
		{
			mailData.setName(mail.getName());
			mailData.setEmailID(mail.getEmail());
			mailData.setConfirmationText();
			sendMail(mailData);
			
			
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
//	@Override
//	public  ResponseEntity<HttpStatus> mailToShortListedCandidate(CandidateModel canObj, InterviewProcessDto interview)
//	{
//		
//		
//		MailDto mailData = new MailDto();
//		mailData.setDate(interview.getDate());
//		mailData.setRound(interview.getRound());
//		mailData.setTime(interview.getTime());
//		mailData.setEmailID(canObj.getEmail());
//		mailData.setName(canObj.getName());
//		mailData.setInterviewDetail();
//		mailData.setSubject("Interview process information");
//		sendMail(mailData);
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

	
	
	
	@Override
	public ResponseEntity<HttpStatus> mailToCheckAvailability(String empId, MailDto mailData)
	{
		TechnicalPanelModel techObj = techRepo.findByEmployeeId(empId);
		
		mailData.setEmailID(techObj.getEmail());
		mailData.setName(techObj.getName());
		//mailData.setDate(mailData.getDate());
		//mailData.setRound(mailData.getRound());
		mailData.setTextToPanelAvailability();
		mailData.setSubject("Confirm your availability");
		sendMail(mailData);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}




