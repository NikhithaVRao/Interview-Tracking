package com.robosoft.interviewtracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class CommentModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(name = "interview_id")
	private String interviewId; 
	
	@Column(name = "round")
	private String round;  
	
	@Column(name = "comments")
	private String comments; 
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "assignee_id")
	private String assigneeId;
}
