package com.robosoft.interviewtracking.dto;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsDto {

	private int id; 
	
	private String interviewId;

	private String round;  

	private String comments; 
}
