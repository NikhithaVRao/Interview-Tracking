package com.robosoft.interviewtracking.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
	private String status = "null";
	
	@Column
	private String round; 
	
	@Column(name = "interview_id")
	private String interviewId;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "create_timestamp")
	@CreationTimestamp
	private Timestamp createTimestamp;
	
	@Column(name = "update_timestamp")
	@UpdateTimestamp
	private Timestamp updateTimestamp;
	
	@Column(name = "deleted")
	private boolean isDeleted;
	
	@Column(name = "date")
	private Date interviewDate;
}
