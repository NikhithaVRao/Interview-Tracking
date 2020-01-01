package com.robosoft.interviewtracking.dto;

import java.sql.Time;
import java.util.Date;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
	
	private @Getter @Setter String subject;
	private @Getter @Setter Date date;
	private @Getter @Setter Time time;
	private @Getter @Setter String round;
	private @Getter @Setter String designation;
	
	
	private @Getter @Setter String emailID;
	private @Getter @Setter String name;  
	private @Getter String text;


	public void setInterviewDetail() {
		
		this.text = "Dear " + this.getName() +",\r\n" + 
    			"\r\n" + 
    			"You are selected for "+this.getRound()+
    			", yours interview date is" + this.getDate()+ "at " + this.getTime() +".\r\n" + 
    			"\r\n" + 
    			"Thanks and Regards, \r\n" + 
    			"HRD\r\n" + 
    			"";
		
	}
	

	public void setConfirmationText()
	{
		this.text = "Dear " + this.getName() +",\r\n" + 
    			"\r\n" + 
    			"It is our pleasue to offer you the position of "+ this.getDesignation()+
    			" at Robosoft Technologies. Your date of joing is confirmed for " + this.getDate() +"." + 
    			"\r\n" + 
    			"You are required to report at the office at " +this.getTime() +" AM on the joining date.\r\n\r\n"
    					+ "We look forward to you joining us!\r\n\r\nBest Regards,\r\nTeam Human Possibilities";
	}
	
	public void setTextToPanelAvailability()
	{
		this.text = "Hello " +this.getName() +",\r\n" +
					"\r\n" +
				"Please confirm your availabilty if you are available to take interview on" +this.getDate()
				+", by hitting the following API link."
				+"\r\n\r\n"
				+"Regards,\r\nHR Team"
					;
	}
	
	public void setTextToPanel() {
		this.text = "Hello " +this.getName()+",\r\n"
				+"\r\n You are scheduled to take interview on " +this.date +" at " +this.time +".\r\n\r\n"
				+"Regards,\r\nHR Team";
	}


	@Override
	public String toString() {
		return "MailDto [subject=" + subject + ", date=" + date + ", time=" + time + ", round=" + round
				+ ", designation=" + designation + ", emailID=" + emailID + ", name=" + name + ", text=" + text + "]";
	}
	
	
}
