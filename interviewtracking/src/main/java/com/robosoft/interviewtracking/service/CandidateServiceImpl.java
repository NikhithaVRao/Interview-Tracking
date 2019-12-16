package com.robosoft.interviewtracking.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
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

import ch.qos.logback.core.net.SyslogOutputStream;



@Service
/* To add candidate details */
public class CandidateServiceImpl implements CandidateService{
	@Autowired
	CandidateRepository candidateRepository;
	
	@Autowired
	SkillsRepository sRep;
	
//	@Autowired
//    private JavaMailSender javaMailSender;
	
	
	/* method to implement getter setter to save in model class */ 
	public CandidateModel setModel(CandidateModel cmodel, CandidateDto candidate)
	{
		//CandidateModel cmodel = new CandidateModel();		
		if(candidate.getName() != null) {
			cmodel.setName(candidate.getName());
		}
		if(candidate.getDateOfBirth() != null) {
			cmodel.setDateOfBirth(candidate.getDateOfBirth());
		}
		if(candidate.getAddress() != null) {
			cmodel.setAddress(candidate.getAddress());
		}
		
		if(candidate.getQualification() != null) {
			cmodel.setQualification(candidate.getQualification());
		}
		
		if(candidate.getTotalExperience() != 0) {
			cmodel.setTotalExperience(candidate.getTotalExperience());
		}
		
		if(candidate.getNoticePeriod() != 0) {
			cmodel.setNoticePeriod(candidate.getNoticePeriod());
		}
		
		if(candidate.getSalaryExpectation() != 0) {
			cmodel.setSalaryExpectation(candidate.getSalaryExpectation());
		}
		
		if(candidate.getCurrentSalary() != 0) {
			cmodel.setCurrentSalary(candidate.getCurrentSalary());
		}
		
		if(candidate.getGender() != null) {
			cmodel.setGender(candidate.getGender());
		}
		
		if(candidate.getPhone() != null) {
			cmodel.setPhoneNumber(candidate.getPhone());
		}
		
		if(candidate.getEmail() != null) {
			cmodel.setEmail(candidate.getEmail());
		}
		
		if(candidate.getUniqueId() != null) {
			cmodel.setUniqueId(candidate.getUniqueId());
		}
		
		if(candidate.getApplicantType() != null) {
			cmodel.setApplicantType(candidate.getApplicantType());
		}
		
		if(candidate.getPostApplied() != null) {
			cmodel.setPostApplied(candidate.getPostApplied());
		}
		
		if(candidate.getPreviousCompany() != null) {
			cmodel.setPreviousCompany(candidate.getPreviousCompany());
		}
		
		if(candidate.getCarrierStartDate() != null) {
			cmodel.setCarrierStartDate(candidate.getCarrierStartDate());
		}
		
		if(candidate.getReferalId() != null) {
			cmodel.setReferalId(candidate.getReferalId());
		}
		
		
		try {
			 cmodel = candidateRepository.save(cmodel);
			}
 
			catch(Exception e)
			{
				throw new CustomException(100,"This field is mandatory");
			}
		
		return cmodel;
	}
	
	/* method to implement getter setter to get dto response */ 
	public CandidateDto setDto(CandidateDto cdto, CandidateModel cmodel)
	{
		cdto.setId(cmodel.getId());
		 cdto.setName(cmodel.getName());
		 cdto.setAddress(cmodel.getAddress());
		 cdto.setDateOfBirth(cmodel.getDateOfBirth());
		 cdto.setAddress(cmodel.getAddress());
		 cdto.setQualification(cmodel.getQualification());
		 cdto.setTotalExperience(cmodel.getTotalExperience());
		 cdto.setNoticePeriod(cmodel.getNoticePeriod());
		 cdto.setSalaryExpectation(cmodel.getSalaryExpectation());
		 cdto.setCurrentSalary(cmodel.getCurrentSalary());
		 cdto.setGender(cmodel.getGender());
		 cdto.setPhone(cmodel.getPhoneNumber());
		 cdto.setEmail(cmodel.getEmail());
		 cdto.setUniqueId(cmodel.getUniqueId());
		 cdto.setApplicantType(cmodel.getApplicantType());
		 cdto.setPostApplied(cmodel.getPostApplied());
		 cdto.setAttemptCount(cmodel.getAttemptCount());
		 cdto.setCreateTimestamp(cmodel.getCreateTimestamp());
		 cdto.setUpdateTimestamp(cmodel.getUpdateTimestamp());
		 cdto.setPreviousCompany(cmodel.getPreviousCompany());
		 cdto.setReferalId(cmodel.getReferalId());
		 cdto.setCarrierStartDate(cmodel.getCarrierStartDate());
		 cdto.setShortListed(cmodel.isShortListed());
		 cdto.setTotalExperience(cmodel.getTotalExperience());
		 
		 List<SkillsModel> smod = sRep.findAllByCandidateId(cmodel.getId());
		 
		 List<String> skills = new ArrayList<String>();
		 List<Integer> exp = new ArrayList<Integer>();
		 for(int skill = 0; skill < smod.size(); skill++) {
			 SkillsModel skillModel = smod.get(skill);
			 skills.add(skillModel.getSkillName());
			 exp.add(skillModel.getExperience());
		 }
		 cdto.setSkills(skills);
		 cdto.setExperience(exp);
		 return cdto;
	}
	
	/* to add candidate details */
public ResponseEntity<CandidateDto> addCandidate(CandidateDto candidate) {
	
		CandidateModel candidateModel = candidateRepository.findByUniqueId(candidate.getUniqueId());
		CandidateModel cmodel = new CandidateModel();
		if(candidateModel == null)
		{
		/* To fetch list of int of experience from candidate dto */
		List<Integer> exp = new ArrayList<Integer>();
		exp = candidate.getExperience();
			
		int sumOfExperience = 0;
		cmodel.setId(candidate.getId());
		cmodel.setCreateTimestamp(candidate.getCreateTimestamp());
		/* to input other dto attributes into model*/
		cmodel = setModel(cmodel, candidate);
		cmodel.setAttemptCount(candidate.getAttemptCount()+1);
		
		/* To add total experience */
		for(int experience = 0; experience < exp.size(); experience++)
		{
			sumOfExperience += exp.get(experience);
			cmodel.setTotalExperience(sumOfExperience);
		}
		/* Exception to handle mandatory fields */
	//	try {
		 cmodel = candidateRepository.save(cmodel);
		//}
//		catch(Exception e)
//		{
//			//throw new CustomException(100,"This field is mandatory");
//			e.printStackTrace();
//		}
		 		 
		 candidate.setId(cmodel.getId());
 		 candidate.setCreateTimestamp(cmodel.getUpdateTimestamp());
		 candidate.setUpdateTimestamp(cmodel.getUpdateTimestamp());
		 candidate.setTotalExperience(cmodel.getTotalExperience());
		 candidate.setAttemptCount(cmodel.getAttemptCount());
		 		 
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
			
			candidateRepository.save(candidateModel);
			 
			 candidate.setId(candidateModel.getId());
			 candidate.setCreateTimestamp(candidateModel.getCreateTimestamp());
			 candidate.setUpdateTimestamp(candidateModel.getUpdateTimestamp());
			 candidate.setAttemptCount(candidateModel.getAttemptCount());

		}
		 return new ResponseEntity<CandidateDto>(candidate, HttpStatus.ACCEPTED);
	}	

/* To get shortlisted candidate */
public List<CandidateDto> getShortlistedCandidate(int experience, String skills)
{
	List<SkillsModel> skillModel = sRep.findByCriteria(skills, experience);
	
	List<CandidateDto> candidateList = new ArrayList<CandidateDto>();
		
	if(skillModel == null)
		throw new CustomException(100,"Invalid");
	
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
	
	/* to fetch candidate details for shortlisted candidate id */
	
	int shortListedId = 0;
	for(int i = 0 ; i < skillModel.size() ; i++)
	{
		CandidateDto  cdto =  new CandidateDto();		
		cdto.setId(skillModel.get(i).getCandidateId());
		cdto.setSkills(skillsList);
		cdto.setExperience( experienceList);
		
		shortListedId = skillModel.get(i).getCandidateId()	;
		
		CandidateModel cmodel = candidateRepository.findById(shortListedId).get();
		
		cmodel.setShortListed(true);
		candidateRepository.save(cmodel);

		/* to set model objects to dto */
		cdto =  setDto(cdto,cmodel);
		candidateList.add(cdto);    
	} 
return candidateList;
	}

/* To update candidate */

public ResponseEntity<CandidateDto> updateCandidate(int id, CandidateDto candidate)
{
	/*List for experience and skills */
	List<Integer> exp = new ArrayList<Integer>();
	exp = candidate.getExperience();
	List<String> skills = new ArrayList<String>();
	skills = candidate.getSkills();
	int sumOfExperience = 0;
	List<SkillsModel> smod = sRep.findAllByCandidateId(id);
	
	/* to compare new skills with old skills and if already present dont update else update */
	for(int newSkill = 0; newSkill < skills.size(); newSkill++) {
		int flag = 0;
		SkillsModel skillAtIndex;
		for(int skillObj = 0; skillObj < smod.size(); skillObj++) {
			skillAtIndex = smod.get(skillObj);
			if(skills.get(newSkill).equals(skillAtIndex.getSkillName())) {
				flag = 1;
				break;
			}
		}
		if(flag == 0) {
			skillAtIndex = sRep.save(new SkillsModel(0, id, skills.get(newSkill), exp.get(newSkill)));
			System.out.println(skillAtIndex.toString());
		}
	}
	
	
	for(int skillObj = 0; skillObj < smod.size(); skillObj++) {
		SkillsModel skillAtIndex = smod.get(skillObj);
		for(int newSkill = 0; newSkill < skills.size(); newSkill++) {
			if(skillAtIndex.getSkillName().equals(skills.get(newSkill))) {
				skillAtIndex.setExperience(exp.get(newSkill));
				sRep.save(skillAtIndex);
				System.out.println(skillAtIndex.toString());
				break;
			}
		}
	}
	
	smod = sRep.findAllByCandidateId(id);
	for(int expirience = 0; expirience < smod.size(); expirience++ ) {
		SkillsModel skillAtIndex = smod.get(expirience);
		sumOfExperience += skillAtIndex.getExperience();
	}
	CandidateModel cmodelData = candidateRepository.findById(id).get();
	cmodelData = setModel(cmodelData, candidate);
	cmodelData.setTotalExperience(sumOfExperience);
	
	 
	cmodelData = candidateRepository.save(cmodelData);

	 candidate = setDto(candidate, cmodelData);


	return new ResponseEntity<>(candidate, HttpStatus.ACCEPTED);
}

/* To delete skills */
@SuppressWarnings("rawtypes")
public  ResponseEntity deleteSkills(int id, String skills)
{
	List<SkillsModel> smodel =  sRep.findByCandidateIdAndSkillName(id, skills);
	
	if(smodel == null)
		throw new CustomException(100,"Cannot delete the non existing skills");
	
	 CandidateModel candidate = candidateRepository.findById(id).get();
	 SkillsModel smodelData = smodel.get(0);
	 candidate.setTotalExperience(candidate.getTotalExperience() - smodelData.getExperience());
	 sRep.delete(smodelData);
	 candidateRepository.save(candidate);
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




