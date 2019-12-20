package com.robosoft.interviewtracking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillsDto {
		private int candidateId;
		private String SkillName;
		private boolean isDeleted;
	}


