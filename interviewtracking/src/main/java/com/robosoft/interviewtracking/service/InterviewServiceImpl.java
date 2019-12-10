package com.robosoft.interviewtracking.service;

import com.robosoft.interviewtracking.model.SkillsModel;
import com.robosoft.interviewtracking.model.TechnicalPanelModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.exception.*;
import com.robosoft.interviewtracking.dao.HRPanelRepository;
import com.robosoft.interviewtracking.dao.InterviewTrackingRepository;
import com.robosoft.interviewtracking.dao.SkillsRepository;
import com.robosoft.interviewtracking.dao.TechnicalPanelRepository;
import com.robosoft.interviewtracking.dto.Candidate;
import com.robosoft.interviewtracking.dto.HRPanel;
import com.robosoft.interviewtracking.dto.TechnicalPanel;
import com.robosoft.interviewtracking.model.CandidateModel;
import com.robosoft.interviewtracking.model.HRPanelModel;

@Service
public class InterviewServiceImpl implements InterviewService{

	@Autowired
	InterviewTrackingRepository itRepository;
	@Autowired
	SkillsRepository sRep;
	@Autowired
	TechnicalPanelRepository techPanelRepository;
	@Autowired
	HRPanelRepository hrRepository;
	
	/* To add candidate details */
	public Candidate addCandidate(Candidate candidate) {
		
		CandidateModel candidateModel = itRepository.findByUniqueId(candidate.getUniqueId());
		CandidateModel cmodel = new CandidateModel();
		if(candidateModel == null)
		{
		/* To fetch list of int of experience from candidate dto */
		List<Integer> exp = new ArrayList<Integer>();
		exp = candidate.getExperience();
			
		int sumOfExperience = 0;
		cmodel.setId(candidate.getId());
		cmodel.setName(candidate.getName());
		cmodel.setDateOfBirth(candidate.getDateOfBirth());
		cmodel.setAddress(candidate.getAddress());
		cmodel.setQualification(candidate.getQualification());
		cmodel.setNoticePeriod(candidate.getNoticePeriod());
		cmodel.setSalaryExpectation(candidate.getSalaryExpectation());
		cmodel.setCurrentSalary(candidate.getCurrentSalary());
		cmodel.setGender(candidate.getGender());
		cmodel.setPhone(candidate.getPhone());
		cmodel.setEmail(candidate.getEmail());
		cmodel.setUniqueId(candidate.getUniqueId());
		cmodel.setApplicantType(candidate.getApplicantType());
		cmodel.setPostApplied(candidate.getPostApplied());
		cmodel.setPreviousCompany(candidate.getPreviousCompany());
		cmodel.setCarrierStartDate(candidate.getCarrierStartDate());
		cmodel.setReferalId(candidate.getReferalId());
		cmodel.setCreate_timestamp(candidate.getCreate_timestamp());
		cmodel.setUpdate_timestamp(candidate.getUpdate_timestamp());
		cmodel.setAttemptCount(candidate.getAttemptCount()+1);
		
		/* To add total experience */
		for(int experience = 0; experience < exp.size(); experience++)
		{
			sumOfExperience += exp.get(experience);
			cmodel.setTotalExperience(sumOfExperience);
		}
		/* Exception to handle mandatory fields */
		try {
		 cmodel = itRepository.save(cmodel);
		}
		catch(Exception e)
		{
			throw new CustomException(100,"This field is mandatory");
		}
		 		 
		 candidate.setId(cmodel.getId());
 		 candidate.setCreate_timestamp(cmodel.getCreate_timestamp());
		 candidate.setUpdate_timestamp(cmodel.getUpdate_timestamp());
		 candidate.setTotalExperience(cmodel.getTotalExperience());
		 		 
		/* To fetch list of strings of skills from candidate dto */
		List<String> skills = new ArrayList<String>();
		skills = candidate.getSkills();
			
		/* To add skills to skill table */
		for(int i = 0 ; i < skills.size() ; i++)
		{
			SkillsModel sm = new SkillsModel();
			sm.setCandidateId(candidate.getId());
			sm.setSkillName(skills.get(i));
			sm.setExperience(exp.get(i));
			sRep.save(sm);
		} 
	
		}
		else
		{
			candidateModel.setAttemptCount(candidateModel.getAttemptCount()+1);
			updateCandidate(candidateModel.getId(), candidate);
			
			 itRepository.save(candidateModel);
			 
			 candidate.setId(candidateModel.getId());
			 candidate.setAttemptCount(candidateModel.getAttemptCount());
			 candidate.setCreate_timestamp(candidateModel.getCreate_timestamp());
			 candidate.setUpdate_timestamp(candidateModel.getUpdate_timestamp());

		}
		 return candidate;
	}	
	
	/* To get shortlisted candidate */
public List<Candidate> getShortlistedCandidate(int experience, String skills)
{
	List<SkillsModel> skillModel = sRep.findByCriteria(skills, experience);
		
	if(skillModel == null)
		throw new CustomException(100,"Invalid");
	List<Candidate> cobj = new ArrayList<Candidate>();
	
	List<String> skillsList = new ArrayList<String>();
	String skill = "" ;
	
	List<Integer> experienceList = new ArrayList<Integer>();
	int exp = 0;
	
	for(int i = 0; i < skillModel.size(); i++)
	{
		skill = skillModel.get(i).getSkillName();
		exp = skillModel.get(i).getExperience();			
	}
	
	skillsList.add(skill);
	experienceList.add( exp);
	System.out.println(skill);
	
	for(int i = 0 ; i < skillModel.size() ; i++)
	{
		Candidate  cdto =  new Candidate();		
		cdto.setId(skillModel.get(i).getCandidateId());
		cdto.setSkills(skillsList);
		cdto.setExperience( experienceList);
		cobj.add(cdto);    
	} 
return cobj;
	}

/* To update candidate */

public Candidate updateCandidate(int id, Candidate candidate)
{
	/*List for experience an skills */
	List<Integer> exp = new ArrayList<Integer>();
	exp = candidate.getExperience();
	List<String> skills = new ArrayList<String>();
	skills = candidate.getSkills();
	int sumOfExperience = 0;
	
	CandidateModel cmodelData = itRepository.findById(id).get();
	cmodelData.setName(candidate.getName());
	cmodelData.setDateOfBirth(candidate.getDateOfBirth());
	cmodelData.setAddress(candidate.getAddress());
	cmodelData.setQualification(candidate.getQualification());
	cmodelData.setNoticePeriod(candidate.getNoticePeriod());
	cmodelData.setSalaryExpectation(candidate.getSalaryExpectation());
	cmodelData.setCurrentSalary(candidate.getCurrentSalary());
	cmodelData.setGender(candidate.getGender());
	cmodelData.setPhone(candidate.getPhone());
	cmodelData.setEmail(candidate.getEmail());
	
	/* To add total experience */
	for(int experience = 0; experience < exp.size(); experience++)
	{	
		sumOfExperience = cmodelData.getTotalExperience() + exp.get(experience);
		cmodelData.setTotalExperience(sumOfExperience);
	}
	 
		itRepository.save(cmodelData);
	
	 candidate.setId(cmodelData.getId());
	 candidate.setName(cmodelData.getName());
	 candidate.setDateOfBirth(cmodelData.getDateOfBirth());
	 candidate.setAddress(cmodelData.getAddress());
	 candidate.setQualification(cmodelData.getQualification());
	 candidate.setNoticePeriod(cmodelData.getNoticePeriod());
	 candidate.setSalaryExpectation(cmodelData.getSalaryExpectation());
	 candidate.setCurrentSalary(cmodelData.getCurrentSalary());
	 candidate.setGender(cmodelData.getGender());
	 candidate.setPhone(cmodelData.getPhone());
	 candidate.setEmail(cmodelData.getEmail());
	 candidate.setTotalExperience(cmodelData.getTotalExperience());
	 
	/* To update skills to skill table */

	for(int i = 0 ; i < skills.size() ; i++)
	{
		SkillsModel smodelData = new SkillsModel();
		smodelData.setCandidateId(candidate.getId());
		smodelData.setSkillName(skills.get(i));
		smodelData.setExperience(exp.get(i));
		sRep.save(smodelData);
	} 

	return candidate;
}

/* To delete skills */
public void deleteSkills(int id, String skills)
{
	SkillsModel smodel =  sRep.findByCandidateIdAndSkillName(id, skills);
	
	if(smodel == null)
		throw new CustomException(100,"Cannot delete the non existing skills");
	
	smodel.setDeleted(true);
	sRep.save(smodel);
}


/* To add technical panel */
public TechnicalPanel addTechnicalPanel(TechnicalPanel techPanelDto)
{
	TechnicalPanelModel techPanelModel = new TechnicalPanelModel();
	
	techPanelModel.setName(techPanelDto.getName());
	techPanelModel.setEmail(techPanelDto.getEmail());
	
	/* To convert a list of string into a string object */
	String expertiseObj = techPanelDto.getExpertise().toString();
	
	techPanelModel.setExpertise(expertiseObj);
	
	techPanelRepository.save(techPanelModel);
	
	techPanelDto.setId(techPanelModel.getId());
	techPanelDto.setCreate_timestamp(techPanelModel.getCreate_timestamp());
	techPanelDto.setUpdate_timestamp(techPanelModel.getUpdate_timestamp());
	
	return techPanelDto;
}

 /* To add HR panel */

public HRPanel addHRPanel(HRPanel hrPanelDto)
{
	HRPanelModel hrModel = new HRPanelModel();
	hrModel.setName(hrPanelDto.getName());
	hrModel.setEmail(hrPanelDto.getEmail());
	hrModel.setDesignation(hrPanelDto.getDesignation());
	
	hrRepository.save(hrModel);
	
	hrPanelDto.setId(hrModel.getId());
	hrPanelDto.setCreate_timestamp(hrModel.getCreate_timestamp());
	hrPanelDto.setUpdate_timestamp(hrModel.getUpdate_timestamp());
	return hrPanelDto;	
}
}