 package com.robosoft.interviewtracking.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robosoft.interviewtracking.dto.*;
import com.robosoft.interviewtracking.exception.CustomException;
import com.robosoft.interviewtracking.service.CandidateService;
import com.robosoft.interviewtracking.service.HRPanelService;
import com.robosoft.interviewtracking.service.TechPanelService;


@Controller("/")
public class InterviewTrackingController {

	@Autowired
	CandidateService candidateService;
	
	@Autowired
	TechPanelService techService;
	
	@Autowired
	HRPanelService hrService;
	
	/* To add candidate */
	@RequestMapping(value="candidate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate)
	{
		return candidateService.addCandidate(candidate);
	}
	
	/* To update candidate model */
	@RequestMapping(value="candidate/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") int id, @RequestBody Candidate candidate) 
	
	{
		try {
		return candidateService.updateCandidate(id, candidate);
		}
		catch(NoSuchElementException e)
		{
			throw new CustomException(100,"Enter valid details");
		}
	}
	
	/* To delete skills */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "candidate", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity deleteSkills(@RequestHeader("id") int id, @RequestHeader("skills") String skills)
	{
		return candidateService.deleteSkills(id, skills);
	
	}

	
	@RequestMapping
	@ResponseBody
	public String test() {
	System.out.println("==================app startup================");
		return "App Started";
	}
	
	/* To shortlist */
	
	@RequestMapping(value = "candidate", method = RequestMethod.GET)
	@ResponseBody 
	public List<Candidate> getShortlistedCandidate(@RequestHeader("experience") int experience, @RequestHeader("skills") String skills)
	{
		return candidateService.getShortlistedCandidate(experience, skills);
	} 
	
	
	/*To add technical panel */
	@RequestMapping(value = "techPanel", method = RequestMethod.POST)
	@ResponseBody 
	public TechnicalPanel addTechnicalPanel(@RequestBody TechnicalPanel techPanelDto)
	{
		return techService.addTechnicalPanel(techPanelDto);
	}
	
	
	/* To add HR panel */
	@RequestMapping(value = "hrPanel", method = RequestMethod.POST)
	@ResponseBody 
	public ResponseEntity<HRPanel> addHRPanel(@RequestBody HRPanel hrPanelDto)
	{
		return hrService.addHRPanel(hrPanelDto);
	}
	
	
	/* To send an email to candidate*/ 
	@RequestMapping(value = "candidate/email", method = RequestMethod.POST)
	@ResponseBody 
	public void sendEmailToCandidate(@RequestBody MailDto mailDto)
	{
		 System.out.println("date = " +mailDto.getDate());
		try {
			hrService.sendEmailToCandidate(mailDto);
		} catch (MessagingException e) {
			throw new CustomException(100,"invalid mail");
		}
	}
	
	/* To shortlist panelists */
	
	@RequestMapping(value = "techPanel", method = RequestMethod.GET)
	@ResponseBody 
	public List<TechnicalPanel> getPanelists(@RequestHeader("panelId") String panelId, @RequestHeader("expertise") String expertise)
	{
		return techService.getPanelist(panelId, expertise);
	}
	
	
	/* To send an email to panelists*/
	@RequestMapping(value = "techPanel/email", method = RequestMethod.POST)
	@ResponseBody 
	public void sendEmailToPanelists(@RequestBody MailDto mailDto)
	{
		 System.out.println("date = " +mailDto.getDate());
		try {
			hrService.sendEmailToCandidate(mailDto);
		} catch (MessagingException e) {
			throw new CustomException(100,"invalid mail");
		}
	}
}
