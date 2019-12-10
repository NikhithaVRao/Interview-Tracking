package com.robosoft.interviewtracking.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "technical_panel")
@Data
@NoArgsConstructor
public class TechnicalPanelModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "expertise")
	private String expertise;
	
	@Column(name = "create_timestamp")
	@CreationTimestamp
	private Timestamp createTimestamp;
	
	@Column(name = "update_timestamp")
	@UpdateTimestamp
	private Timestamp updateTimestamp;
	
	@Column(name = "email")
	private String email;
	@Column(name = "deleted")
	private boolean isDeleted;
//	@Column
//	private String panelId;
}
