package com.robosoft.interviewtracking.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Table(name = "interview")
@Component
public class InterviewProcessModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "assignee_id")
	private int assigneeId;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column
	private boolean status;
	
	@Column
	private String comments;

	@Column
	private String round; 
	
	@Column(name = "candidate_id")
	private int candidateId;
	
	@Column(name = "interview_id")
	private String interviewId;
	
	@Column(name = "create_timestamp")
	private Timestamp createTimestamp;
	
	@Column(name = "update_timestamp")
	private Timestamp updateTimestamp;
	
	@Column(name = "deleted")
	private boolean isDeleted;
}
