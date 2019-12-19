package com.robosoft.interviewtracking.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HRPanelDto {

	private int id;
	
	private String employeeId;
	
	private String name;
	
	private String designation;
	
	private Timestamp createTimestamp;
	
	private Timestamp updateTimestamp;
	
	private String email;
	
	private boolean isDeleted;
}
