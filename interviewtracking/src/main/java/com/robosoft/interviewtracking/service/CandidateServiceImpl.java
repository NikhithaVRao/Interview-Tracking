package com.robosoft.interviewtracking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.CandidateRepository;
import com.robosoft.interviewtracking.dao.SkillsRepository;
import com.robosoft.interviewtracking.dto.CandidateDto;
import com.robosoft.interviewtracking.exception.CustomException;
import com.robosoft.interviewtracking.model.CandidateModel;
import com.robosoft.interviewtracking.model.SkillsModel;


@Service
public class CandidateServiceImpl implements CandidateService{
	@Autowired
	CandidateRepository candidateRepository;
	
	@Autowired
	SkillsRepository skillsRep;
	
//	@Autowired
//    private JavaMailSender javaMailSender;
		
	/* method to implement getter setter to save in model class */ 
	public CandidateModel setModel(CandidateModel candidateModel, CandidateDto candidateDto)
	{
		if(candidateDto.getName() != null) {
			candidateModel.setName(candidateDto.getName());
		}
		if(candidateDto.getDateOfBirth() != null) {
			candidateModel.setDateOfBirth(candidateDto.getDateOfBirth());
		}
		if(candidateDto.getAddress() != null) {
			candidateModel.setAddress(candidateDto.getAddress());
		}
		
		if(candidateDto.getQualification() != null) {
			candidateModel.setQualification(candidateDto.getQualification());
		}
		
//		if(candidateDto.getTotalExperience() != 0) {
//			candidateModel.setTotalExperience(candidateDto.getTotalExperience());
//		}
		
		if(candidateDto.getNoticePeriod() != 0) {
			candidateModel.setNoticePeriod(candidateDto.getNoticePeriod());
		}
		
		if(candidateDto.getSalaryExpectation() != 0) {
			candidateModel.setSalaryExpectation(candidateDto.getSalaryExpectation());
		}
		
		if(candidateDto.getCurrentSalary() != 0) {
			candidateModel.setCurrentSalary(candidateDto.getCurrentSalary());
		}
		 
		if(candidateDto.getGender() != null) {
			candidateModel.setGender(candidateDto.getGender());
		}
		
		if(candidateDto.getPhone() != null) {
			candidateModel.setPhoneNumber(candidateDto.getPhone());
		}
		
		if(candidateDto.getEmail() != null) {
			candidateModel.setEmail(candidateDto.getEmail());
		}
		
		if(candidateDto.getUniqueId() != null) {
			candidateModel.setUniqueId(candidateDto.getUniqueId());
		}
		
		if(candidateDto.getApplicantType() != null) {
			candidateModel.setApplicantType(candidateDto.getApplicantType());
		}
		
		if(candidateDto.getPostApplied() != null) {
			candidateModel.setPostApplied(candidateDto.getPostApplied());
		}
		
		if(candidateDto.getPreviousCompany() != null) {
			candidateModel.setPreviousCompany(candidateDto.getPreviousCompany());
		}
		
		if(candidateDto.getCarrierStartDate() != null) {
			candidateModel.setCarrierStartDate(candidateDto.getCarrierStartDate());
		}
		
		if(candidateDto.getReferalId() != null) {
			candidateModel.setReferalId(candidateDto.getReferalId());
		}
		
		if(candidateDto.getEvent() != null)
		{
			candidateModel.setEvent(candidateDto.getEvent());
		}
		
		try {
			 candidateModel = candidateRepository.save(candidateModel);
			}
 
			catch(Exception e)
			{
				throw new CustomException(100,"This field is mandatory");
			}
		
		return candidateModel;
	}
	
	/* method to implement getter setter to get dto response */ 
	public CandidateDto setDto(CandidateDto candidateDto, CandidateModel candidateModel)
	{
		candidateDto.setId(candidateModel.getId());
		 candidateDto.setName(candidateModel.getName());
		 candidateDto.setAddress(candidateModel.getAddress());
		 candidateDto.setDateOfBirth(candidateModel.getDateOfBirth());
		 candidateDto.setAddress(candidateModel.getAddress());
		 candidateDto.setQualification(candidateModel.getQualification());
		 candidateDto.setTotalExperience(candidateModel.getTotalExperience());
		 candidateDto.setNoticePeriod(candidateModel.getNoticePeriod());
		 candidateDto.setSalaryExpectation(candidateModel.getSalaryExpectation());
		 candidateDto.setCurrentSalary(candidateModel.getCurrentSalary());
		 candidateDto.setGender(candidateModel.getGender());
		 candidateDto.setPhone(candidateModel.getPhoneNumber());
		 candidateDto.setEmail(candidateModel.getEmail());
		 candidateDto.setUniqueId(candidateModel.getUniqueId());
		 candidateDto.setApplicantType(candidateModel.getApplicantType());
		 candidateDto.setPostApplied(candidateModel.getPostApplied());
		 candidateDto.setAttemptCount(candidateModel.getAttemptCount());
		 candidateDto.setCreateTimestamp(candidateModel.getCreateTimestamp());
		 candidateDto.setUpdateTimestamp(candidateModel.getUpdateTimestamp());
		 candidateDto.setPreviousCompany(candidateModel.getPreviousCompany());
		 candidateDto.setReferalId(candidateModel.getReferalId());
		 candidateDto.setCarrierStartDate(candidateModel.getCarrierStartDate());
		 candidateDto.setShortListed(candidateModel.isShortListed());
		 candidateDto.setTotalExperience(candidateModel.getTotalExperience());
		 candidateDto.setEvent(candidateModel.getEvent());
		 candidateDto.setInterviewId(candidateModel.getInterviewId());
		 
		 List<SkillsModel> smod = skillsRep.findAllByCandidateId(candidateModel.getId());
		 
		 List<String> skills = new ArrayList<String>();
		 List<Integer> exp = new ArrayList<Integer>();
		 for(int skill = 0; skill < smod.size(); skill++) {
			 SkillsModel skillModel = smod.get(skill);
			 skills.add(skillModel.getSkillName());
			 exp.add(skillModel.getExperience());
		 }
		 candidateDto.setSkills(skills);
		 candidateDto.setExperience(exp);
		 return candidateDto;
	}
	
	/* to add candidate details */
public ResponseEntity<CandidateDto> addCandidate(CandidateDto candidateDto) {
	
		CandidateModel candidateRepObj = candidateRepository.findByUniqueId(candidateDto.getUniqueId());
		CandidateModel candidateModel = new CandidateModel();
		if(candidateRepObj == null) {
		/* To fetch list of int of experience from candidate dto */
		List<Integer> exp = new ArrayList<Integer>();
		exp = candidateDto.getExperience();
			
		int sumOfExperience = 0;
		candidateModel.setId(candidateDto.getId());
		candidateModel.setCreateTimestamp(candidateDto.getCreateTimestamp());
		/* To add total experience */
		for(int experience = 0; experience < exp.size(); experience++)
		{
			sumOfExperience += exp.get(experience);
			candidateModel.setTotalExperience(sumOfExperience);
		}
		/* to input other dto attributes into model*/
		candidateModel = setModel(candidateModel, candidateDto);
	//	candidateModel.setAttemptCount(candidateDto.getAttemptCount()+1);
		
		
		/* Exception to handle mandatory fields */
		try {
		 candidateModel = candidateRepository.save(candidateModel);
		}
		catch(Exception e)
		{
			throw new CustomException(100,"This field is mandatory");
		}
		 		 
		 candidateDto.setId(candidateModel.getId());
 		 candidateDto.setCreateTimestamp(candidateModel.getUpdateTimestamp());
		 candidateDto.setUpdateTimestamp(candidateModel.getUpdateTimestamp());
		 candidateDto.setTotalExperience(candidateModel.getTotalExperience());
	//	 candidateDto.setAttemptCount(candidateModel.getAttemptCount());
		 		 
		/* To fetch list of strings of skills from candidate dto */
		List<String> skills = new ArrayList<String>();
		skills = candidateDto.getSkills();
			
		/* To add skills to skill table */
		for(int i = 0 ; i < skills.size() ; i++)
		{
			SkillsModel sm = new SkillsModel();
			sm.setCandidateId(candidateDto.getId());
			sm.setSkillName(skills.get(i));
			sm.setExperience(exp.get(i));
			skillsRep.save(sm);
		}  
		}

		
		

		else if(candidateRepObj.getFinalResult().equalsIgnoreCase("rejected"))
		{ 

			updateCandidate(candidateRepObj.getId(), candidateDto);
			candidateDto.setId(candidateRepObj.getId());
			candidateDto.setCreateTimestamp(candidateRepObj.getCreateTimestamp());
			candidateDto.setUpdateTimestamp(candidateRepObj.getUpdateTimestamp());
			candidateDto.setFinalResult(candidateRepObj.getFinalResult());
		}
		else {
			
			candidateRepObj.setReferalId(candidateRepObj.getReferalId() +"," + candidateDto.getReferalId());
			candidateRepObj = candidateRepository.save(candidateRepObj);
			candidateDto.setId(candidateRepObj.getId());
			candidateDto.setCreateTimestamp(candidateRepObj.getCreateTimestamp());
			candidateDto.setUpdateTimestamp(candidateRepObj.getUpdateTimestamp());
			candidateDto.setReferalId(candidateRepObj.getReferalId());
		}
		 return new ResponseEntity<>(candidateDto, HttpStatus.ACCEPTED);
	}	

/* To get shortlisted candidate */
public List<CandidateDto> getShortlistedCandidate(int experience, String skills)
{
	List<SkillsModel> skillModel = skillsRep.getShortlisted(skills, experience);
	
	List<CandidateDto> candidateList = new ArrayList<CandidateDto>();
		
	if(skillModel == null)
		throw new CustomException(100,"Invalid");
	
	/* to fetch candidate details for shortlisted candidate id */
	
	int shortListedId = 0;
	for(int i = 0 ; i < skillModel.size() ; i++)
	{
		CandidateDto  candidateDto =  new CandidateDto();		

		shortListedId = skillModel.get(i).getCandidateId()	;
		
		CandidateModel candidateRepObj;
		
		try {
			candidateRepObj = candidateRepository.findById(shortListedId).get();
		}
		catch(NoSuchElementException e) {
			throw new CustomException(101,"Invalid Id");
		}
		
	//	System.out.println(candidateRepObj); 
		candidateRepObj.setShortListed(true);
		
		
	
		candidateRepository.save(candidateRepObj);
		
		
		//to set rejected as a value gor final status field
		List<CandidateModel> candidateRepObjList = candidateRepository.findByShortlisted();
		List<CandidateModel> candidateModelObj = new ArrayList<CandidateModel>();
		
		for(int a = 0; a < candidateRepObjList.size(); a++)
		{
			candidateRepObjList.get(a).setFinalResult("rejected");
			candidateRepository.save(candidateRepObjList.get(a));
		}
		
		/* to set model objects to dto */
		candidateDto =  setDto(candidateDto,candidateRepObj);
		candidateList.add(candidateDto);    
		
		
	} 
	
	
	
	List<CandidateModel> idList = candidateRepository.findShorlistedId();
	for(int j = 0; j < idList.size(); j++)
	{
		CandidateModel candidateRepObj1 = idList.get(j);
		
		String interviewId = (String.valueOf(LocalDate.now())+" - "+ (j+1));
		
		candidateRepObj1.setInterviewId(interviewId);
		candidateRepository.save(candidateRepObj1);
		
		

	}
return candidateList;
	}

/* To update candidate */

public ResponseEntity<CandidateDto> updateCandidate(int id, CandidateDto candidateDto)
{
	
	CandidateModel candidateRepObj;
	try {
		candidateRepObj = candidateRepository.findById(id).get();
	}
	catch(NoSuchElementException e) {
		throw new CustomException(101,"Invalid ID");
	}
	/*List for experience and skills */
	List<Integer> exp = new ArrayList<Integer>();
	exp = candidateDto.getExperience();
	List<String> skills = new ArrayList<String>();
	skills = candidateDto.getSkills();
	int sumOfExperience = 0;
	List<SkillsModel> skillsRepObj = skillsRep.findAllByCandidateId(id);
	/* to compare new skills with old skills and if already present dont update else update */
	for(int newSkill = 0; newSkill < skills.size(); newSkill++) {
		int flag = 0;
		SkillsModel oldSkill = new SkillsModel();
		for(int skillObj = 0; skillObj < skillsRepObj.size(); skillObj++) {
			oldSkill = skillsRepObj.get(skillObj);
			if(skills.get(newSkill).equals(oldSkill.getSkillName())) {
				flag = 1;
				break;
			}
		}
		if(flag == 0) {
			
			oldSkill.setCandidateId(id);
			oldSkill.setSkillName(skills.get(newSkill));
			oldSkill.setExperience(exp.get(newSkill));
			
			 skillsRep.save(oldSkill);
			 
			 candidateDto.setId(oldSkill.getCandidateId());
		}
	}
	
	
	for(int skillObj = 0; skillObj < skillsRepObj.size(); skillObj++) {
		SkillsModel oldSkill = skillsRepObj.get(skillObj);
		for(int newSkill = 0; newSkill < skills.size(); newSkill++) {
			if(oldSkill.getSkillName().equals(skills.get(newSkill))) {
				oldSkill.setExperience(exp.get(newSkill));
				skillsRep.save(oldSkill);
				break;
			}
		}
	}
	
	skillsRepObj = skillsRep.findAllByCandidateId(id);
	for(int expirience = 0; expirience < skillsRepObj.size(); expirience++ ) {
		SkillsModel oldSkill = skillsRepObj.get(expirience);
		sumOfExperience += oldSkill.getExperience();
	}
	candidateRepObj = setModel(candidateRepObj, candidateDto);
	candidateRepObj.setTotalExperience(sumOfExperience);
	candidateRepObj.setFinalResult(null);
	 
	try {
		candidateRepObj = candidateRepository.save(candidateRepObj);
	}
	catch(Exception e) {
		throw new CustomException(100,"This field is mandatory");
	}

	 candidateDto = setDto(candidateDto, candidateRepObj);


	return new ResponseEntity<>(candidateDto, HttpStatus.ACCEPTED);
}

/* To delete skills */
@SuppressWarnings("rawtypes")
public  ResponseEntity deleteSkills(int id, String skills)
{
	List<SkillsModel> skillsRepObj =  skillsRep.findByCandidateIdAndSkillName(id, skills);
	
	if(skillsRepObj == null)
		throw new CustomException(102,"Cannot delete the non existing skills");
	
	 CandidateModel candidateRepObj = candidateRepository.findById(id).get();
	 SkillsModel skillsModel = skillsRepObj.get(0);
	 candidateRepObj.setTotalExperience(candidateRepObj.getTotalExperience() - skillsModel.getExperience());
	 skillsRep.delete(skillsModel);
	 candidateRepository.save(candidateRepObj);
	return new ResponseEntity<>(HttpStatus.ACCEPTED);
}



/* send mail to candidate */
//public void sendEmail(MailDto mailDto) throws MessagingException
//{
////	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
////	
////	MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true);
//	SimpleMailMessage msg = new SimpleMailMessage();
//	
//	 List<String> cmodelList = candidateRepository.getMail();
//	 List<String> nameList = candidateRepository.getName();
//	 for(int i = 0 ; i < cmodelList.size() ; i++)
//		{
//		 System.out.println("val ="+cmodelList.get(i));
//		 msg.setTo(cmodelList.get(i));
//
//		 msg.setSubject("ShortListed Candidate");
//		
//		 msg.setText("Hello "+ nameList.get(i) +" \nYou have been shortlised to attend interview on "+ mailDto.getDate() + "at" + mailDto.getTime() + " at robosoft campus.");
//
//		 javaMailSender.send(msg);
//		}
}




