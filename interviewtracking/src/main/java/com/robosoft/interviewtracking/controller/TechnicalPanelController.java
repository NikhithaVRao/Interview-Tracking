package com.robosoft.interviewtracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robosoft.interviewtracking.dto.CommentsDto;
import com.robosoft.interviewtracking.dto.TechnicalPanelDto;
import com.robosoft.interviewtracking.service.TechPanelService;

@Controller("/")
public class TechnicalPanelController {
	@Autowired
	TechPanelService techService;
	
	/*To add technical panel */
	@PostMapping(value = "techPanel")
	@ResponseBody 
	public TechnicalPanelDto addTechnicalPanel(@RequestBody TechnicalPanelDto techPanelDto)
	{
		return techService.addTechnicalPanel(techPanelDto);
	}
	
/* To shortlist panelists */
	
	@GetMapping(value = "techPanel")
	@ResponseBody 
	public List<TechnicalPanelDto> getPanelists(@RequestHeader("panelId") String panelId, @RequestHeader("expertise") String expertise)
	{
		return techService.getPanelist(panelId, expertise);
	}
	
/* To post comments into comments table */
	
	@PostMapping(value = "techPanel/comments")
	public ResponseEntity<CommentsDto> addComments(@RequestBody CommentsDto comments)
	{
		System.out.println(comments);
		return techService.addComments(comments);
	}
	
}
