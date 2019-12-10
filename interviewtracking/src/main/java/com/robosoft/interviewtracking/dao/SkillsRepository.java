package com.robosoft.interviewtracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.robosoft.interviewtracking.model.SkillsModel;

public interface SkillsRepository extends JpaRepository<SkillsModel, Integer> {
	
	@Query("Select s from SkillsModel s where s.skillName = :skillName and s.experience >= :experience and isDeleted = 0")
	List<SkillsModel> findByCriteria(@Param("skillName") String skillName, @Param("experience") int experience);

	/*To delete by id and skills */
	List<SkillsModel> findByCandidateIdAndSkillName(int id, String skills);
	
	List<SkillsModel> findAllByCandidateId(int candidateId);
}













//SkillNameAndExperienceGreaterThanEqual