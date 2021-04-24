package com.CandidateManagement.dao;

import com.CandidateManagement.models.Candidate;

import java.util.*;

public interface DAOTrends<Candidate> {
	
	public List<Map<String,String>> getCountLocation();	

	public List<Map<String,String>> getCountSkills();	


}
