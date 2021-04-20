package com.CandidateManagement.dao;

import com.CandidateManagement.models.Candidate;
import java.util.List;
import java.util.*;

public interface DAO<Candidate> {
	
	List<Candidate> getCandidates();
	
	void create(Candidate candidate);
		
	void update(Candidate candidate, int id);
	
	void delete(int id);
}
