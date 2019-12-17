package com.robosoft.interviewtracking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.InterviewTrackingRepository;
import com.robosoft.interviewtracking.dto.CandidateDto;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.model.InterviewProcessModel;

@Service
public class InterviewProcessServiceImpl implements InterviewProcessService{
	@Autowired
	InterviewTrackingRepository intrepo;

	@Override
	public ResponseEntity<InterviewProcessDto> add(int candidateId, InterviewProcessDto interview) {

//		InterviewProcessModel intmodel = new InterviewProcessModel();
		System.out.println(candidateId);
		InterviewProcessModel intmodel1 = intrepo.findByCandidateId(candidateId);
		System.out.println(intmodel1);
		if(intmodel1 == null)
		{
		InterviewProcessModel intmodel = new InterviewProcessModel();
		intmodel.setCandidateId(candidateId);
		intmodel.setAssigneeId(interview.getAssigneeId());
		intmodel.setCreateTimestamp(interview.getCreate_timestamp());
		intmodel.setUpdateTimestamp(interview.getUpdate_timestamp());
		intmodel.setEmployeeId(interview.getEmployeeId());
		intmodel.setRound(interview.getRound());
		
		String interviewId = (String.valueOf(LocalDate.now())+" - "+String.valueOf(candidateId));
		intmodel.setInterviewId(interviewId);
		System.out.println(intmodel);
		intrepo.save(intmodel);
		
		interview.setId(intmodel.getId());
		interview.setCandidateId(intmodel.getCandidateId());
		interview.setAssigneeId(intmodel.getAssigneeId());
		interview.setCreate_timestamp(intmodel.getCreateTimestamp());
		interview.setUpdate_timestamp(intmodel.getUpdateTimestamp());
		interview.setEmployeeId(intmodel.getEmployeeId());
		interview.setRound(intmodel.getRound()); 
		interview.setInterviewId(intmodel.getInterviewId());
		return new ResponseEntity<InterviewProcessDto>(interview, HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<InterviewProcessDto>(HttpStatus.ALREADY_REPORTED);
		}
		
	}
	 
	
}	
	
	
