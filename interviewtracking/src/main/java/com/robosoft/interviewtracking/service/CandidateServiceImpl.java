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
import com.robosoft.interviewtracking.dto.Candidate;
import com.robosoft.interviewtracking.exception.CustomException;
import com.robosoft.interviewtracking.model.CandidateModel;
import com.robosoft.interviewtracking.model.SkillsModel;



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
	public CandidateModel setModel(CandidateModel cmodel, Candidate candidate)
	{
		//CandidateModel cmodel = new CandidateModel();		
		cmodel.setUpdateTimestamp(candidate.getUpdateTimestamp());
		cmodel.setName(candidate.getName());
		cmodel.setDateOfBirth(candidate.getDateOfBirth());
		cmodel.setAddress(candidate.getAddress());
		cmodel.setQualification(candidate.getQualification());
		cmodel.setNoticePeriod(candidate.getNoticePeriod());
		cmodel.setSalaryExpectation(candidate.getSalaryExpectation());
		cmodel.setCurrentSalary(candidate.getCurrentSalary());
		cmodel.setGender(candidate.getGender());
		cmodel.setPhoneNumber(candidate.getPhone());
		cmodel.setEmail(candidate.getEmail());
		cmodel.setUniqueId(candidate.getUniqueId());
		cmodel.setApplicantType(candidate.getApplicantType());
		cmodel.setPostApplied(candidate.getPostApplied());
		cmodel.setPreviousCompany(candidate.getPreviousCompany());
		cmodel.setCarrierStartDate(candidate.getCarrierStartDate());
		cmodel.setReferalId(candidate.getReferalId());
		
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
	public Candidate setDto(Candidate cdto, CandidateModel cmodel)
	{
		 cdto.setName(cmodel.getName());
		 cdto.setAddress(cmodel.getAddress());
		 cdto.setDateOfBirth(cmodel.getDateOfBirth());
		 cdto.setAddress(cmodel.getAddress());
		 cdto.setQualification(cmodel.getQualification());
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
		 
		 return cdto;
	}
	
	/* to add candidate details */
public ResponseEntity<Candidate> addCandidate(Candidate candidate) {
	
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
		 return new ResponseEntity<Candidate>(candidate, HttpStatus.ACCEPTED);
	}	

/* To get shortlisted candidate */
public List<Candidate> getShortlistedCandidate(int experience, String skills)
{
	List<SkillsModel> skillModel = sRep.findByCriteria(skills, experience);
	
	List<Candidate> candidateList = new ArrayList<Candidate>();
		
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
		Candidate  cdto =  new Candidate();		
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

public ResponseEntity<Candidate> updateCandidate(int id, Candidate candidate)
{
	/*List for experience an skills */
	List<Integer> exp = new ArrayList<Integer>();
	exp = candidate.getExperience();
	List<String> skills = new ArrayList<String>();
	skills = candidate.getSkills();
	int sumOfExperience = 0;
	
	CandidateModel cmodelData = candidateRepository.findById(id).get();
	cmodelData.setCreateTimestamp(cmodelData.getCreateTimestamp());
	cmodelData = setModel(cmodelData, candidate);
	
	/* To add total experience */ 
	for(int experience = 0; experience < exp.size(); experience++)
	{	
		sumOfExperience = cmodelData.getTotalExperience() + exp.get(experience);
		cmodelData.setTotalExperience(sumOfExperience);
	}
	 
	candidateRepository.save(cmodelData);
	
	 candidate.setId(cmodelData.getId());
	 candidate.setCreateTimestamp(cmodelData.getCreateTimestamp());
	 candidate.setUpdateTimestamp(cmodelData.getUpdateTimestamp());
	 candidate.setTotalExperience(cmodelData.getTotalExperience());
	 
	 int i = 0;
	/* To update skills to skill table */

	List<SkillsModel> smod = sRep.findAllByCandidateId(candidate.getId());
	 
	 for( i = 0 ; i < smod.size() ; i++)
		{
		 SkillsModel smodelData = smod.get(i);
//			smod.setCandidateId(cmodelData.getId());
		 smodelData.setSkillName(skills.get(i));
		 smodelData.setExperience(exp.get(i));
			sRep.save(smodelData);
		} 
	 
	 /* To add  extra skills */
	for( int j = i ; j < skills.size() ; j++)
	{
		SkillsModel smodelData = new SkillsModel();
		smodelData.setCandidateId(candidate.getId());
		smodelData.setSkillName(skills.get(j));
		smodelData.setExperience(exp.get(j));
		sRep.save(smodelData);
	} 

	return new ResponseEntity<>(candidate, HttpStatus.ACCEPTED);
}

/* To delete skills */
@SuppressWarnings("rawtypes")
public  ResponseEntity deleteSkills(int id, String skills)
{
	List<SkillsModel> smodel =  sRep.findByCandidateIdAndSkillName(id, skills);
	
	if(smodel == null)
		throw new CustomException(100,"Cannot delete the non existing skills");
	
	for(int i = 0 ; i < smodel.size() ; i++)
	{
	 SkillsModel smodelData = smodel.get(i);
	smodelData.setDeleted(true);
	sRep.save(smodelData);
	}
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




