package com.robosoft.interviewtracking.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.TechnicalPanelRepository;
import com.robosoft.interviewtracking.dto.CommentsDto;
import com.robosoft.interviewtracking.dto.TechnicalPanelDto;
import com.robosoft.interviewtracking.model.TechnicalPanelModel;

@Service
public class TechPanelServiceImpl implements TechPanelService
{
	@Autowired
	TechnicalPanelRepository techPanelRepository;
	
	
	/* To add technical panel */
	public TechnicalPanelDto addTechnicalPanel(TechnicalPanelDto techPanelDto)
	{
		TechnicalPanelModel techPanelModel = new TechnicalPanelModel();
		
		techPanelModel.setName(techPanelDto.getName());
		techPanelModel.setEmail(techPanelDto.getEmail());
		techPanelModel.setDeleted(techPanelDto.isDeleted());
		/* To convert a list of string into a string object */
		String expertiseObj = techPanelDto.getExpertise().toString();
		
		techPanelModel.setExpertise(expertiseObj);
		
		techPanelRepository.save(techPanelModel);
		
		techPanelDto.setId(techPanelModel.getId());
		techPanelDto.setCreate_timestamp(techPanelModel.getCreateTimestamp());
		techPanelDto.setUpdate_timestamp(techPanelModel.getUpdateTimestamp());
		
		return techPanelDto;
	}

	
	public List<TechnicalPanelDto> getPanelist(String panelId, String expertise)
	{
		
		List<TechnicalPanelModel> techModel = techPanelRepository.findByExpertise(expertise);
		List<TechnicalPanelDto> techList = new ArrayList<TechnicalPanelDto>();
		
		for(TechnicalPanelModel tpmodel : techModel)
		{
		
			TechnicalPanelDto techPanel = new TechnicalPanelDto();
			
//			tpmodel.setPanelId(panelId);
			techPanelRepository.save(tpmodel);
			
			techPanel.setId(tpmodel.getId());
			techPanel.setName(tpmodel.getName());
			techPanel.setEmail(tpmodel.getEmail());
			techPanel.setCreate_timestamp(tpmodel.getCreateTimestamp());
			techPanel.setUpdate_timestamp(tpmodel.getUpdateTimestamp());
			
			/* to convert a string expertise into list */
			String [] expertiseArray = tpmodel.getExpertise().split(",");
			List<String> expertiseList = Arrays.asList(expertiseArray);
			techPanel.setExpertise(expertiseList);
			
//			techPanel.setPanelId(tpmodel.getPanelId());
			techList.add(techPanel);
		}
 		return techList;
	}


	@Override
	public ResponseEntity<CommentsDto> addComments(CommentsDto comments) {
		
		CommentModel cmodel =  new CommentModel();
		
		return null;
	}
	
}
