package com.robosoft.interviewtracking.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.CommentsRepository;
import com.robosoft.interviewtracking.dao.HRPanelRepository;
import com.robosoft.interviewtracking.dao.InterviewTrackingRepository;
import com.robosoft.interviewtracking.dto.CommentsDto;
import com.robosoft.interviewtracking.dto.HRPanelDto;
import com.robosoft.interviewtracking.dto.MailDto;
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
	CommentsRepository commentsRepsitory;
	
	@Autowired
	InterviewTrackingRepository intrepo;
 /* To add HR panel */

public ResponseEntity<HRPanelDto> addHRPanel(HRPanelDto hrPanelDto)
{
	HRPanelModel hrModel = new HRPanelModel();
	hrModel.setName(hrPanelDto.getName());
	hrModel.setEmail(hrPanelDto.getEmail());
	hrModel.setDesignation(hrPanelDto.getDesignation());
	
	hrRepository.save(hrModel);
	
	hrPanelDto.setId(hrModel.getId());
	hrPanelDto.setCreate_timestamp(hrModel.getCreateTimestamp());
	hrPanelDto.setUpdate_timestamp(hrModel.getUpdateTimestamp());
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
public void sendEmailToPanelists(MailDto mailDto) throws MessagingException
{
	SimpleMailMessage msg = new SimpleMailMessage();
}

@Override
public ResponseEntity<CommentsDto> getComment(String interviewId) {
	CommentModel comments = commentsRepsitory.findByInterviewId(interviewId, true);
	CommentsDto commentDto = new CommentsDto();
	commentDto.setId(comments.getId());
	commentDto.setInterviewId(comments.getInterviewId());
	commentDto.setRound(comments.getRound());
	commentDto.setComments(comments.getComments());
	return new ResponseEntity<>(commentDto, HttpStatus.OK);
}

@Override
public ResponseEntity addStatus(String interviewId, boolean status) {
	InterviewProcessModel interviewProcessModel = intrepo.findByInterviewId(interviewId);
	interviewProcessModel.setStatus(status);
	intrepo.save(interviewProcessModel);
	return new ResponseEntity<>(HttpStatus.OK);
}

}
