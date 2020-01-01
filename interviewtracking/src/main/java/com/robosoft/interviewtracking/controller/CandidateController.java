 package com.robosoft.interviewtracking.controller;



import java.util.List;

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

import com.robosoft.interviewtracking.service.CandidateService;


@Controller("/candidate")
public class CandidateController {

	@Autowired
	CandidateService candidateService;
	
	
	@RequestMapping
	@ResponseBody
	public String test() {
	System.out.println("==================app startup================");
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
		return candidateService.updateCandidate(id, candidate);
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
