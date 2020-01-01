package com.robosoft.interviewtracking.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.InterviewTrackingRepository;
import com.robosoft.interviewtracking.dao.TechnicalPanelRepository;
import com.robosoft.interviewtracking.dto.InterviewProcessDto;
import com.robosoft.interviewtracking.dto.TechnicalPanelDto;

import com.robosoft.interviewtracking.exception.CustomException;

import com.robosoft.interviewtracking.model.InterviewProcessModel;
import com.robosoft.interviewtracking.model.TechnicalPanelModel;

@Service
public class TechPanelServiceImpl implements TechPanelService
{
	@Autowired
	TechnicalPanelRepository techPanelRepository;
	@Autowired
	InterviewTrackingRepository interviewRepo;
	
	
	/* To add technical panel */
	public TechnicalPanelDto addTechnicalPanel(TechnicalPanelDto techPanelDto)
	{
		TechnicalPanelModel techPanelModel = new TechnicalPanelModel();
		
		techPanelModel.setName(techPanelDto.getName());
		techPanelModel.setEmail(techPanelDto.getEmail());
		techPanelModel.setEmployeeId(techPanelDto.getEmployeeId());
		techPanelModel.setDeleted(techPanelDto.isDeleted());
		techPanelModel.setEmployeeId(techPanelDto.getEmployeeId());
		
		
		/* To convert a list of string into a string object */
		String expertiseObj = techPanelDto.getExpertise().toString();
		
		techPanelModel.setExpertise(expertiseObj);
		try {
			techPanelRepository.save(techPanelModel);
		}
		catch(Exception e) {
			throw new CustomException(100,"All feilds are mandetary");
		}
		
		
		techPanelDto.setId(techPanelModel.getId());  
		techPanelDto.setCreate_timestamp(techPanelModel.getCreateTimestamp());
		techPanelDto.setUpdate_timestamp(techPanelModel.getUpdateTimestamp());
		
		return techPanelDto;
	}

	
/* comments being added by panelists */
	@Override
	public ResponseEntity<InterviewProcessDto> addComments(InterviewProcessDto interviewDto) {
		
		InterviewProcessModel interviewModel = interviewRepo.findByInterviewIdAndRound(interviewDto.getInterviewId(), interviewDto.getRound());
		if(interviewModel == null) {
			throw new CustomException(104,"Invalid ID or Round");
		}
		if(interviewDto.getInterviewId() != null)
		{
			interviewModel.setInterviewId(interviewDto.getInterviewId());
		}
		if(interviewDto.getRound() != null)
		{
			interviewModel.setRound(interviewDto.getRound());
		}
		if(interviewDto.getComments() != null)
		{
		interviewModel.setComments(interviewDto.getComments());
		}
		if(interviewDto.getEmployeeId() != 0)
		{
			interviewModel.setEmployeeId(interviewDto.getEmployeeId());
		}
		if(interviewDto.getAssigneeId() != 0)
		{
			interviewModel.setAssigneeId(interviewDto.getAssigneeId());
		}
		if(interviewDto.getStatus() != null)
		{
			interviewModel.setStatus(interviewDto.getStatus());
		}
	
		interviewModel.setUpdateTimestamp(interviewDto.getUpdate_timestamp());
		
		try {
			interviewModel = interviewRepo.save(interviewModel);
		}
		catch(Exception e) {
			throw new CustomException(100,"All feilds are mandetary");
		}
	
		
		interviewDto.setId(interviewModel.getId());
		interviewDto.setEmployeeId(interviewModel.getEmployeeId());
		interviewDto.setAssigneeId(interviewModel.getAssigneeId());
		interviewDto.setInterviewId(interviewModel.getInterviewId());
		interviewDto.setRound(interviewModel.getRound());
		interviewDto.setComments(interviewModel.getComments());
		interviewDto.setCreate_timestamp(interviewModel.getCreateTimestamp());
		interviewDto.setUpdate_timestamp(interviewModel.getUpdateTimestamp());
		
		
		return new ResponseEntity<InterviewProcessDto>(interviewDto, HttpStatus.ACCEPTED);
	}
	
	/* to check the availability of the panelists */
	@Override
	public ResponseEntity<TechnicalPanelDto> setAvailability(String employeeId,TechnicalPanelDto techPanelDto) {
		
		TechnicalPanelModel techModel =  techPanelRepository.findByEmployeeId(employeeId); 
		if(techModel == null) {
			throw new CustomException(101,"Invalid ID");
		}
		techModel.setAvailableMorning(techPanelDto.isAvailableMorning());
		techModel.setAvailableAfternoon(techPanelDto.isAvailableAfternoon());
		techModel.setAvailableEvening(techPanelDto.isAvailableEvening());
		try {
			techPanelRepository.save(techModel);
		}
		catch(Exception e) {
			throw new CustomException(100,"All feilds are mandetary");
		}
		
		
		techPanelDto.setId(techModel.getId());
		techPanelDto.setName(techModel.getName());
		techPanelDto.setEmployeeId(techModel.getEmployeeId());
		techPanelDto.setCreate_timestamp(techModel.getCreateTimestamp());
		techPanelDto.setUpdate_timestamp(techModel.getUpdateTimestamp());
		techPanelDto.setEmail(techModel.getEmail());
		
		String [] expertiseArray = techModel.getExpertise().split(",");
		List<String> expertiseList = Arrays.asList(expertiseArray);
		techPanelDto.setExpertise(expertiseList);
	   
		techPanelDto.setAvailableMorning(techModel.isAvailableMorning());
		techPanelDto.setAvailableAfternoon(techModel.isAvailableAfternoon());
		techPanelDto.setAvailableEvening(techModel.isAvailableEvening());
		
		return new ResponseEntity<TechnicalPanelDto>(techPanelDto, HttpStatus.ACCEPTED);
	}


	/* to fetch the details of available panelists for HR*/
	@Override
	public List<TechnicalPanelDto> getPanelists() {
		
		List<TechnicalPanelModel> techModel = techPanelRepository.findAll();
		
		List<TechnicalPanelDto> techList = new ArrayList<TechnicalPanelDto>();
		
		for(TechnicalPanelModel tpModel : techModel)
		{
		
			TechnicalPanelDto techPanel = new TechnicalPanelDto();
			
			techPanel.setId(tpModel.getId());
			techPanel.setEmployeeId(tpModel.getEmployeeId());
			techPanel.setName(tpModel.getName());
			techPanel.setEmail(tpModel.getEmail());
			techPanel.setCreate_timestamp(tpModel.getCreateTimestamp());
			techPanel.setUpdate_timestamp(tpModel.getUpdateTimestamp());
			techPanel.setAvailableMorning(tpModel.isAvailableMorning());
			techPanel.setAvailableAfternoon(tpModel.isAvailableAfternoon());
			techPanel.setAvailableEvening(tpModel.isAvailableEvening());
			/* to convert a string expertise into list */
			String [] expertiseArray = tpModel.getExpertise().split(",");
			List<String> expertiseList = Arrays.asList(expertiseArray);
			techPanel.setExpertise(expertiseList);
			
			techList.add(techPanel);
		}
 		return techList;
	}
}
