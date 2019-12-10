package com.robosoft.interviewtracking.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.TechnicalPanelRepository;
import com.robosoft.interviewtracking.dto.TechnicalPanel;
import com.robosoft.interviewtracking.model.TechnicalPanelModel;

@Service
public class TechPanelServiceImpl implements TechPanelService
{
	@Autowired
	TechnicalPanelRepository techPanelRepository;
	
	
	/* To add technical panel */
	public TechnicalPanel addTechnicalPanel(TechnicalPanel techPanelDto)
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

	
	public List<TechnicalPanel> getPanelist(String panelId, String expertise)
	{
		
		List<TechnicalPanelModel> techModel = techPanelRepository.findByExpertise(expertise);
		List<TechnicalPanel> techList = new ArrayList<TechnicalPanel>();
		
		for(TechnicalPanelModel tpmodel : techModel)
		{
		
			TechnicalPanel techPanel = new TechnicalPanel();
			
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
	
}
