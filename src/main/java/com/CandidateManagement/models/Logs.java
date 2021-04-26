package com.CandidateManagement.models;

import java.io.Serializable;

public class Logs implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String action;
	private String email;
	private int candidateId;
	private String timeStamp;
	
	
	public Logs() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getCandidateId() {
		return candidateId;
	}


	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}


	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	public Logs(int id, String action, String email, int candidateId, String timeStamp) {
		this.id = id;
		this.action = action;
		this.email = email;
		this.candidateId = candidateId;
		this.timeStamp = timeStamp;
	}


	@Override
	public String toString() {
		return "Logs [id=" + id + ", action=" + action + ", email=" + email + ", candidateId=" + candidateId
				+ ", timeStamp=" + timeStamp + "]";
	}
	
	
	
	
}
