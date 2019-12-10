package com.robosoft.interviewtracking.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
	
	private int id;
	
	private String name;

	private String address;

	@JsonFormat(pattern = "dd.mm.yyyy")
	private Date dateOfBirth;

	private String qualification;

	private int totalExperience;
	
	private List<Integer> experience = new ArrayList<Integer>();

	private int noticePeriod;

	private float salaryExpectation;
	
	private boolean ableToRelocate;

	private float currentSalary;

	private List<String> skills = new ArrayList<String>();
	
	private String gender;
	
	private String phone;
	
	private String email;
	
	private boolean isDeleted;
	
	private boolean isShortListed;
	
	private Timestamp createTimestamp;

	private Timestamp updateTimestamp;

	private String uniqueId;
	
	private String previousCompany;
	
	private int attemptCount;
	
	private String carrierStartDate;
	
	private String applicantType;
	
	private String postApplied;
	
	private String referalId;

	
	




	


}
