 package com.robosoft.interviewtracking.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class InterviewProcessDto {

	private int id;
	
	private int assigneeId;
	
	private int employeeId;
	
	private String status;
	
	private String round;
	
	private Date interviewDate;
	
	private Time time;
	
	private String interviewId;
	
	private String comments;
	
	private Timestamp create_timestamp;
	
	private Timestamp update_timestamp;
	
	private boolean isDeleted;
}
