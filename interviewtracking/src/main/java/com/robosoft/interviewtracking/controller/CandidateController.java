 package com.robosoft.interviewtracking.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robosoft.interviewtracking.dto.*;
import com.robosoft.interviewtracking.exception.CustomException;
import com.robosoft.interviewtracking.service.CandidateService;


@Controller("/candidate")
public class CandidateController {

	@Autowired
	CandidateService candidateService;
	
	
	@RequestMapping
	@ResponseBody
	public String test() {
	System.out.println("==================app startup================");
//	
//	for(int i = 1; i < 5; i++)
//	{
//	String interviewId = (String.valueOf(LocalDate.now())+" - "+i);
//	System.out.println(interviewId);
//	}
//	
	
	return "App Started";
	}
	
	
	/* To add candidate */
	@PostMapping(value="candidate")
	@ResponseBody
	public ResponseEntity<CandidateDto> addCandidate(@RequestBody CandidateDto candidate)
	{
		return candidateService.addCandidate(candidate);
	}
	
	/* To update candidate model */
	@PatchMapping(value="candidate/{id}")
	@ResponseBody
	public ResponseEntity<CandidateDto> updateCandidate(@PathVariable("id") int id, @RequestBody CandidateDto candidate) 
	
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

	@DeleteMapping(value = "candidate")
	@ResponseBody
	public ResponseEntity deleteSkills(@RequestHeader("id") int id, @RequestHeader("skills") String skills)
	{
		return candidateService.deleteSkills(id, skills);
	
	}

		/* To shortlist */
	
	@GetMapping(value = "candidate")
	@ResponseBody 
	public List<CandidateDto> getShortlistedCandidate(@RequestHeader("experience") int experience, @RequestHeader("skills") String skills)
	{
		return candidateService.getShortlistedCandidate(experience, skills);
	} 
	
	
	
	
	
	
	
	
	
	
	
}
