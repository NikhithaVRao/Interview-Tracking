package com.robosoft.interviewtracking.model;



import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Table(name = "candidate")
@Component
public class CandidateModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	private String address;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column
	@NotNull
	private String qualification;
	
	@Column(name = "total_experience")
	private int totalExperience;
	
	@Column(name = "notice_period")
	private int noticePeriod;
	
	@Column(name = "salary_expectation")
	private float salaryExpectation;
	
	@Column(name = "able_to_relocate")
	private boolean ableToRelocate;
	
	@Column(name = "current_salary")
	private float currentSalary;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "phone_number")
	@NotNull
	private String phoneNumber;

	@Column
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9]+(@[A-Za-z0-9-]+\\.[a-zA-Z]+)+{1}?")
	//@Pattern(regexp = "([a-zA-Z0-9_.]+@[a-zA-Z0-9]+[\\.a-zA-Z])+")
	private String email;
	
	@Column(name = "create_timestamp")
	@CreationTimestamp
	private Timestamp createTimestamp;
	
	@Column(name = "update_timestamp")
	@UpdateTimestamp
	private Timestamp updateTimestamp;
	
	@Column(name = "deleted")
	private boolean isDeleted;
	
	@Column(name = "shortlisted")
	private boolean isShortListed;
	
	@Column(name = "unique_id")
	@NotNull	
	private String uniqueId;
	
	@Column(name = "previous_company")
	private String previousCompany;
	
	@Column(name = "attempt_count")
	private int attemptCount;
	
	@Column(name = "carrier_start_date")
	private String carrierStartDate;
	
	@Column(name = "applicant_type")
	private String applicantType;
	
	@Column(name = "post_applied")
	private String postApplied;
	
	@Column(name = "referal_id")
	private String referalId;

	@Column(name = "final_result")
	private String finalResult;
	

	public CandidateModel()
	{
		super();
	}
	
	@Column(name = "hr_status")
	private String applyStatus;
	
	@Column(name = "event")
	private String event;
	
	@Column(name = "interview_id")
	private String interviewId;
		
}
