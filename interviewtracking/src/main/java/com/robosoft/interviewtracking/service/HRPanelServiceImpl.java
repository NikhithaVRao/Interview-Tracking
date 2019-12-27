package com.robosoft.interviewtracking.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.CandidateRepository;
import com.robosoft.interviewtracking.dao.CommentsRepository;
import com.robosoft.interviewtracking.dao.HRPanelRepository;
import com.robosoft.interviewtracking.dao.InterviewTrackingRepository;
import com.robosoft.interviewtracking.dto.CommentsDto;
import com.robosoft.interviewtracking.dto.HRPanelDto;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.dto.MailDto;
import com.robosoft.interviewtracking.model.CandidateModel;
import com.robosoft.interviewtracking.model.CommentModel;
import com.robosoft.interviewtracking.model.HRPanelModel;
import com.robosoft.interviewtracking.model.InterviewProcessModel;

@Service
public class HRPanelServiceImpl implements HRPanelService{
	
	
	@Autowired
	HRPanelRepository hrRepository;

	@Autowired
    private JavaMailSender javaMailSender;
	
	
	
	@Autowired
	InterviewTrackingRepository intRepo;
	
	@Autowired
	CandidateRepository candidateRepository;

 /* To add HR panel */
public ResponseEntity<HRPanelDto> addHRPanel(HRPanelDto hrPanelDto)
{
	HRPanelModel hrModel = new HRPanelModel();
	hrModel.setName(hrPanelDto.getName());
	hrModel.setEmail(hrPanelDto.getEmail());
	hrModel.setDesignation(hrPanelDto.getDesignation());
	
	hrRepository.save(hrModel);
	
	hrPanelDto.setId(hrModel.getId());
	hrPanelDto.setCreateTimestamp(hrModel.getCreateTimestamp());
	hrPanelDto.setUpdateTimestamp(hrModel.getUpdateTimestamp());
	return new ResponseEntity<>(hrPanelDto, HttpStatus.ACCEPTED);	
}

public void sendEmailToCandidate(MailDto mailDto) throws MessagingException
{
//	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//	
//	MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true);
	SimpleMailMessage msg = new SimpleMailMessage();
	
	 List<String> cmodelList = hrRepository.getMail();
	 List<String> nameList = hrRepository.getName();
	 for(int i = 0 ; i < cmodelList.size() ; i++)
		{
		 System.out.println("val ="+cmodelList.get(i));
		 msg.setTo(cmodelList.get(i));
 
		 msg.setSubject("ShortListed Candidate");
		
		 msg.setText("Hello "+ nameList.get(i) +" \nYou have been shortlised to attend interview on "+ mailDto.getDate() + "at" + mailDto.getTime() + " at robosoft campus.");

		 javaMailSender.send(msg);
		}
}

/* to fetch comments from database */
@Override
public ResponseEntity<InterviewProcessDto> getComment(String interviewId) {
	InterviewProcessModel interviewModel = intRepo.findByInterviewId(interviewId);
	InterviewProcessDto interviewProcessDto = new InterviewProcessDto();
	
	interviewProcessDto.setId(interviewModel.getId());
	interviewProcessDto.setInterviewId(interviewModel.getInterviewId());
	interviewProcessDto.setRound(interviewModel.getRound());
	interviewProcessDto.setAssigneeId(interviewModel.getAssigneeId());
	interviewProcessDto.setEmployeeId(interviewModel.getEmployeeId());
	interviewProcessDto.setStatus(interviewModel.getStatus());
	
	interviewProcessDto.setComments(interviewModel.getComments());
	return new ResponseEntity<>(interviewProcessDto, HttpStatus.OK);
}

/* to add status to the candidate wether he is selectd or not */ 
@Override
public ResponseEntity<InterviewProcessDto> addStatus(String interviewId,InterviewProcessDto interviewDto) {

	InterviewProcessModel interviewProcessModel = intRepo.findByInterviewIdAndRound(interviewId, interviewDto.getRound());
	
	if(!(interviewId.equalsIgnoreCase("rejected"))) {
		if(interviewDto.getInterviewId() != null)
		{
			interviewProcessModel.setInterviewId(interviewDto.getInterviewId());
		}
		if(interviewDto.getRound() != null)
		{
			interviewProcessModel.setRound(interviewDto.getRound());
		}
		if(interviewDto.getComments() != null)
		{
		interviewProcessModel.setComments(interviewDto.getComments());
		}
		if(interviewDto.getEmployeeId() != 0)
		{
			interviewProcessModel.setEmployeeId(interviewDto.getEmployeeId());
		}
		if(interviewDto.getAssigneeId() != 0)
		{
			interviewProcessModel.setAssigneeId(interviewDto.getAssigneeId());
		}
		if(interviewDto.getStatus() != null)
		{
			interviewProcessModel.setStatus(interviewDto.getStatus());
		}
		
		interviewProcessModel.setUpdateTimestamp(interviewDto.getUpdate_timestamp());
		
		intRepo.save(interviewProcessModel);
		
		interviewDto.setId(interviewProcessModel.getId());
		interviewDto.setInterviewId(interviewProcessModel.getInterviewId());
		interviewDto.setAssigneeId(interviewProcessModel.getAssigneeId());
		interviewDto.setEmployeeId(interviewProcessModel.getEmployeeId());
		interviewDto.setComments(interviewProcessModel.getComments());
		interviewDto.setCreate_timestamp(interviewProcessModel.getCreateTimestamp());
		interviewDto.setUpdate_timestamp(interviewProcessModel.getUpdateTimestamp());
		
		return new ResponseEntity<>(interviewDto, HttpStatus.OK);
	}
	else {
		CandidateModel candidateModel = candidateRepository.findByInterviewId(interviewId);
		candidateModel.setFinalResult(interviewDto.getStatus());
		candidateModel.setAttemptCount(candidateModel.getAttemptCount() + 1);
		candidateRepository.save(candidateModel);
		interviewProcessModel.setStatus(interviewDto.getStatus());
		intRepo.save(interviewProcessModel);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}

}
