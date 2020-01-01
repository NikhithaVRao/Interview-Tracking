package com.robosoft.interviewtracking.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.InterviewTrackingRepository;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.model.InterviewProcessModel;

@Service
public class InterviewProcessServiceImpl implements InterviewProcessService{
	@Autowired
	InterviewTrackingRepository intrepo;

	/* to add interview details for candidate */
	@Override
	public ResponseEntity<InterviewProcessDto> addInterviewDetails(String interviewId, InterviewProcessDto interview) {

		InterviewProcessModel intmodel1 = intrepo.findByInterviewId(interviewId);

		System.out.println(intmodel1);
		if(intmodel1.getStatus() == null || intmodel1.getStatus().contains("selected"))
		{
		InterviewProcessModel intmodel = new InterviewProcessModel();
		intmodel.setInterviewId(interviewId);
		intmodel.setAssigneeId(interview.getAssigneeId());
//		intmodel.setCreateTimestamp(interview.getCreate_timestamp());
//		intmodel.setUpdateTimestamp(interview.getUpdate_timestamp());
		intmodel.setEmployeeId(interview.getEmployeeId());
		intmodel.setRound(interview.getRound()); 
		
		intrepo.save(intmodel);
		
		interview.setId(intmodel.getId());
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
			if(intmodel1.getStatus().contains("rejected")) 
				return new ResponseEntity<InterviewProcessDto>(HttpStatus.NOT_ACCEPTABLE);
			
			else
				return new ResponseEntity<InterviewProcessDto>(HttpStatus.NOT_IMPLEMENTED);
		
		}
	
	}
	 
	
}	
	
	
