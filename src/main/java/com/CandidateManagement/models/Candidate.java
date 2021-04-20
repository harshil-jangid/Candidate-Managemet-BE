package com.CandidateManagement.models;

import java.io.Serializable;

public class Candidate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private String jobTitle;
	private String phone;
	private String imageUrl;
	private String joiningDate;
	private String collegeName;
	private String joiningJocation;
	

	public Candidate() {}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getJobTitle() {
		return jobTitle;
	}



	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public String getJoiningDate() {
		return joiningDate;
	}



	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}



	public String getCollegeName() {
		return collegeName;
	}



	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}



	public String getJoiningJocation() {
		return joiningJocation;
	}



	public void setJoiningJocation(String joiningJocation) {
		this.joiningJocation = joiningJocation;
	}



	public Candidate(String name, String email, String jobTitle, String phone, String imageUrl,
			String joiningDate, String collegeName, String joiningJocation) {
		this.name = name;
		this.email = email;
		this.jobTitle = jobTitle;
		this.phone = phone;
		this.imageUrl = imageUrl;
		this.joiningDate = joiningDate;
		this.collegeName = collegeName;
		this.joiningJocation = joiningJocation;
	}



	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", email=" + email + ", jobTitle=" + jobTitle + ", phone="
				+ phone + ", imageUrl=" + imageUrl + ", joiningDate=" + joiningDate + ", collegeName=" + collegeName
				+ ", joiningJocation=" + joiningJocation + "]";
	}

	
}
