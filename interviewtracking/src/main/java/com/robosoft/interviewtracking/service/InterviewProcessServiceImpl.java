package com.robosoft.interviewtracking.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.CandidateRepository;
import com.robosoft.interviewtracking.dao.InterviewTrackingRepository;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;

import com.robosoft.interviewtracking.model.CandidateModel;

import com.robosoft.interviewtracking.exception.CustomException;

import com.robosoft.interviewtracking.model.InterviewProcessModel;

@Service
public class InterviewProcessServiceImpl implements InterviewProcessService{
	@Autowired
	InterviewTrackingRepository intrepo;
	
	@Autowired
	CandidateRepository canRepo;
	
	@Autowired
	EmailService mailService;
	
	/* to add interview details for candidate */
	@Override
	public ResponseEntity<InterviewProcessDto> addInterviewDetails(String interviewId, InterviewProcessDto interview) {

		InterviewProcessModel intmodel1 = intrepo.findByInterviewId(interviewId);

		CandidateModel canObj = canRepo.findByInterviewId(interviewId);
		
		System.out.println(intmodel1);
		if(intmodel1 == null || intmodel1.getStatus().equalsIgnoreCase("selected"))
		{
			
		InterviewProcessModel intmodel = new InterviewProcessModel();
		
		intmodel.setInterviewId(interviewId);
		intmodel.setAssigneeId(interview.getAssigneeId());
		intmodel.setEmployeeId(interview.getEmployeeId());
		intmodel.setRound(interview.getRound());

		try {
			intrepo.save(intmodel);
		}
		catch(Exception e) {
			throw new CustomException(100,"All feilds are mandetary");
		}
	

		interview.setId(intmodel.getId());
		interview.setAssigneeId(intmodel.getAssigneeId());
		interview.setCreate_timestamp(intmodel.getCreateTimestamp());
		interview.setUpdate_timestamp(intmodel.getUpdateTimestamp());
		interview.setEmployeeId(intmodel.getEmployeeId());
		interview.setRound(intmodel.getRound()); 
		interview.setInterviewId(intmodel.getInterviewId());
		
		
		mailService.mailToShortListedCandidate(canObj, interview);
		
		return new ResponseEntity<InterviewProcessDto>(interview, HttpStatus.ACCEPTED);
		}

		else
		{
			if(intmodel1.getStatus().contains("rejected")) 
				return new ResponseEntity<InterviewProcessDto>(HttpStatus.NOT_ACCEPTABLE);
			
			else
				return new ResponseEntity<InterviewProcessDto>(HttpStatus.NOT_IMPLEMENTED);
		
		}
		
	}
	 
	
}	
	
	
