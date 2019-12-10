package com.robosoft.interviewtracking.service;

import java.util.List;

import com.robosoft.interviewtracking.dto.Candidate;
import com.robosoft.interviewtracking.dto.HRPanel;
import com.robosoft.interviewtracking.dto.TechnicalPanel;



public interface InterviewService {

	Candidate addCandidate(Candidate candidate);

	List<Candidate> getShortlistedCandidate(int experience, String skills);

	Candidate updateCandidate(int id, Candidate candidate);

	void deleteSkills(int id, String skills);

	TechnicalPanel addTechnicalPanel(TechnicalPanel techPanelDto);

	HRPanel addHRPanel(HRPanel hrPanelDto);

//	Candidate updateCandidate(int id, Candidate candidate);

	//List<Candidate> getShortlistedCandidate(int experienceInYear, String qualification, String skillName);

	//SkillsDto addSkillsForCandidate(SkillsDto sdto);

	//List<Candidate> getShortlistedCandidate(int experienceInYear, String qualification, SkillsModel skillName);

}
