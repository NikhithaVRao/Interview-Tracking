package com.robosoft.interviewtracking.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TechnicalPanel {

	private int id;

	private String employeeId;
	
	private String name;

	private List<String> expertise = new ArrayList<String>();

	private Timestamp create_timestamp;

	private Timestamp update_timestamp;

	private String email;

	private boolean isDeleted;
	
	private String panelId;
}
