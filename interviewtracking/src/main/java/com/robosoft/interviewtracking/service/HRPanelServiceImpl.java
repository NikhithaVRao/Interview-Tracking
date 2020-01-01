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
//public void sendEmailToPanelists(MailDto mailDto) throws MessagingException
//{
//	SimpleMailMessage msg = new SimpleMailMessage();
//}

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

@Override
public ResponseEntity<InterviewProcessDto> addStatus(String interviewId,InterviewProcessDto interviewDto) {
	InterviewProcessModel interviewProcessModel = intRepo.findByInterviewId(interviewId);
	if(!(interviewId.equalsIgnoreCase("rejected"))) {
		interviewProcessModel.setStatus(interviewDto.getStatus());
		interviewProcessModel.setRound(interviewDto.getRound());
		interviewProcessModel.setCreateTimestamp(interviewProcessModel.getCreateTimestamp());
		
		intRepo.save(interviewProcessModel);
		
		interviewDto.setId(interviewProcessModel.getId());
		interviewDto.setInterviewId(interviewProcessModel.getInterviewId());
		interviewDto.setAssigneeId(interviewProcessModel.getAssigneeId());
		interviewDto.setEmployeeId(interviewProcessModel.getEmployeeId());
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

@Override
public ResponseEntity addFinalResult(String interviewId, String finalResult) 
{

	CandidateModel candidateModelData = candidateRepository.findByInterviewId(interviewId);
	if(candidateModelData == null)
	{
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	else
	{
		if(candidateModelData.getFinalResult() == null)
		{
			candidateModelData.setFinalResult(finalResult);
			candidateModelData.setAttemptCount(candidateModelData.getAttemptCount() + 1);
			candidateModelData.setUpdateTimestamp(candidateModelData.getUpdateTimestamp());
			candidateRepository.save(candidateModelData);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	
		else if (finalResult == "selected" || finalResult == "rejected")
		{
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
	
		else
		{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
}
	

@Override
public ResponseEntity updateFinalResult(String interviewId, String finalResult)
{
	CandidateModel candidateModelData = candidateRepository.findByInterviewId(interviewId);
		
	if(candidateModelData == null)
	{
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	else
	{
		if(finalResult == "selected" || finalResult == "rejected")
		{
			candidateModelData.setFinalResult(finalResult);
			candidateModelData.setUpdateTimestamp(candidateModelData.getUpdateTimestamp());
			candidateRepository.save(candidateModelData);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}

}
