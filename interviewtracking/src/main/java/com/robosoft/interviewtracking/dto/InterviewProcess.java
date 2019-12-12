 package com.robosoft.interviewtracking.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class InterviewProcess {

	private int id;
	
	private int assigneeId;
	
	private int employeeId;
	
	private boolean status;
	
	private String round;
	
	private String comments;

	private int candidateId;
	
	private String interviewId;
	
	private Timestamp create_timestamp;
	
	private Timestamp update_timestamp;
	
	private boolean isDeleted;
}
