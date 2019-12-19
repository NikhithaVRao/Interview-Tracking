package com.robosoft.interviewtracking.service;

import java.util.ArrayList;
import java.util.List;

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
	public CandidateModel setModel(CandidateModel cmodel, CandidateDto candidate)
	{
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
		 
		 List<SkillsModel> smod = skillsRep.findAllByCandidateId(cmodel.getId());
		 
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
public ResponseEntity<CandidateDto> addCandidate(CandidateDto candidateDto) {
	
		CandidateModel candidateRepObj = candidateRepository.findByUniqueId(candidateDto.getUniqueId());
		CandidateModel candidateModel = new CandidateModel();
		if(candidateRepObj == null)
		{
		/* To fetch list of int of experience from candidate dto */
		List<Integer> exp = new ArrayList<Integer>();
		exp = candidateDto.getExperience();
			
		int sumOfExperience = 0;
		candidateModel.setId(candidateDto.getId());
		candidateModel.setCreateTimestamp(candidateDto.getCreateTimestamp());
		/* to input other dto attributes into model*/
		candidateModel = setModel(candidateModel, candidateDto);
		candidateModel.setAttemptCount(candidateDto.getAttemptCount()+1);
		
		/* To add total experience */
		for(int experience = 0; experience < exp.size(); experience++)
		{
			sumOfExperience += exp.get(experience);
			candidateModel.setTotalExperience(sumOfExperience);
		}
		/* Exception to handle mandatory fields */
	//	try {
		 candidateModel = candidateRepository.save(candidateModel);
		//}
//		catch(Exception e)
//		{
//			//throw new CustomException(100,"This field is mandatory");
//			e.printStackTrace();
//		}
		 		 
		 candidateDto.setId(candidateModel.getId());
 		 candidateDto.setCreateTimestamp(candidateModel.getUpdateTimestamp());
		 candidateDto.setUpdateTimestamp(candidateModel.getUpdateTimestamp());
		 candidateDto.setTotalExperience(candidateModel.getTotalExperience());
		 candidateDto.setAttemptCount(candidateModel.getAttemptCount());
		 		 
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
		else
		{
			candidateRepObj.setAttemptCount(candidateRepObj.getAttemptCount()+1);
			updateCandidate(candidateRepObj.getId(), candidateDto);
			
			candidateRepository.save(candidateRepObj);
			 
			 candidateDto.setId(candidateRepObj.getId());
			 candidateDto.setCreateTimestamp(candidateRepObj.getCreateTimestamp());
			 candidateDto.setUpdateTimestamp(candidateRepObj.getUpdateTimestamp());
			 candidateDto.setAttemptCount(candidateRepObj.getAttemptCount());

		}
		 return new ResponseEntity<CandidateDto>(candidateDto, HttpStatus.ACCEPTED);
	}	

/* To get shortlisted candidate */
public List<CandidateDto> getShortlistedCandidate(int experience, String skills)
{
	List<SkillsModel> skillModel = skillsRep.getShortlisted(skills, experience);
	
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
		CandidateDto  candidateDto =  new CandidateDto();		
		candidateDto.setId(skillModel.get(i).getCandidateId());
		candidateDto.setSkills(skillsList);
		candidateDto.setExperience( experienceList);
		
		shortListedId = skillModel.get(i).getCandidateId()	;
		
		CandidateModel candidateRepObj = candidateRepository.findById(shortListedId).get();
		
		candidateRepObj.setShortListed(true);
		candidateRepository.save(candidateRepObj);

		/* to set model objects to dto */
		candidateDto =  setDto(candidateDto,candidateRepObj);
		candidateList.add(candidateDto);    
	} 
return candidateList;
	}

/* To update candidate */

public ResponseEntity<CandidateDto> updateCandidate(int id, CandidateDto candidateDto)
{
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
			 
			System.out.println(oldSkill.toString());
		}
	}
	
	
	for(int skillObj = 0; skillObj < skillsRepObj.size(); skillObj++) {
		SkillsModel oldSkill = skillsRepObj.get(skillObj);
		for(int newSkill = 0; newSkill < skills.size(); newSkill++) {
			if(oldSkill.getSkillName().equals(skills.get(newSkill))) {
				oldSkill.setExperience(exp.get(newSkill));
				skillsRep.save(oldSkill);
				System.out.println(oldSkill.toString());
				break;
			}
		}
	}
	
	skillsRepObj = skillsRep.findAllByCandidateId(id);
	for(int expirience = 0; expirience < skillsRepObj.size(); expirience++ ) {
		SkillsModel oldSkill = skillsRepObj.get(expirience);
		sumOfExperience += oldSkill.getExperience();
	}
	CandidateModel candidateRepObj = candidateRepository.findById(id).get();
	candidateRepObj = setModel(candidateRepObj, candidateDto);
	candidateRepObj.setTotalExperience(sumOfExperience);
	
	 
	candidateRepObj = candidateRepository.save(candidateRepObj);

	 candidateDto = setDto(candidateDto, candidateRepObj);


	return new ResponseEntity<>(candidateDto, HttpStatus.ACCEPTED);
}

/* To delete skills */
@SuppressWarnings("rawtypes")
public  ResponseEntity deleteSkills(int id, String skills)
{
	List<SkillsModel> skillsRepObj =  skillsRep.findByCandidateIdAndSkillName(id, skills);
	
	if(skillsRepObj == null)
		throw new CustomException(100,"Cannot delete the non existing skills");
	
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




