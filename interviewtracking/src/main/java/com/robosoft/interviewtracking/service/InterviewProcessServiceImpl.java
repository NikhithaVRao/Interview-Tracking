package com.robosoft.interviewtracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.CandidateRepository;
import com.robosoft.interviewtracking.dao.InterviewTrackingRepository;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;
<<<<<<< HEAD
import com.robosoft.interviewtracking.dto.MailDto;
import com.robosoft.interviewtracking.model.CandidateModel;
=======
import com.robosoft.interviewtracking.exception.CustomException;
>>>>>>> 9e77fe280a04186c97f72ef69311f4a7c30155d8
import com.robosoft.interviewtracking.model.InterviewProcessModel;

@Service
public class InterviewProcessServiceImpl implements InterviewProcessService{
	@Autowired
	InterviewTrackingRepository intrepo;

	@Autowired
	CandidateRepository canRepo;
	
	/* to add interview details for candidate */
	@Override
	public ResponseEntity<InterviewProcessDto> addInterviewDetails(String interviewId, InterviewProcessDto interview) {

		InterviewProcessModel intmodel1 = intrepo.findByInterviewId(interviewId);
<<<<<<< HEAD
		CandidateModel canObj = canRepo.findByInterviewId(interviewId);
		
		EmailService mailService = new EmailServiceImpl();
		
=======

>>>>>>> 9e77fe280a04186c97f72ef69311f4a7c30155d8
		if(intmodel1 == null || intmodel1.getStatus().contentEquals( "selected"))
		{
			
		InterviewProcessModel intmodel = new InterviewProcessModel();
		
		
		
		intmodel.setInterviewId(interviewId);
		intmodel.setAssigneeId(interview.getAssigneeId());
		intmodel.setEmployeeId(interview.getEmployeeId());
		intmodel.setRound(interview.getRound()); 
<<<<<<< HEAD
		
		intrepo.save(intmodel);
=======
		try {
			intrepo.save(intmodel);
		}
		catch(Exception e) {
			throw new CustomException(100,"All feilds are mandetary");
		}
	
>>>>>>> 9e77fe280a04186c97f72ef69311f4a7c30155d8
		interview.setId(intmodel.getId());
		interview.setAssigneeId(intmodel.getAssigneeId());
		interview.setCreate_timestamp(intmodel.getCreateTimestamp());
		interview.setUpdate_timestamp(intmodel.getUpdateTimestamp());
		interview.setEmployeeId(intmodel.getEmployeeId());
		interview.setRound(intmodel.getRound()); 
		interview.setInterviewId(intmodel.getInterviewId());
		
		
		
		
//		MailDto mailData = new MailDto();
//		mailData.setDate(interview.getDate());
//		mailData.setRound(interview.getRound());
//		mailData.setTime(interview.getTime());
//		mailData.setEmailID(canObj.getEmail());
//		mailData.setName(canObj.getName());
//		mailData.setInterviewDetail();
//		mailData.setSubject("Interview process information");
//		System.out.println(mailData);
//		mailService.sendMail(mailData);
		
		
		
		
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
	
	
