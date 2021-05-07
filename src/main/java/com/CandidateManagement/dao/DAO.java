package com.CandidateManagement.dao;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.models.Candidate;
import java.util.List;

public interface DAO<Candidate> {
	
	public List<Candidate> getCandidates() throws NoRecordFound;
		
	public Candidate create(Candidate candidate);
		
	public int updateCandidate(Candidate candidate, int id);
	
	public int delete(int id, String deletedById);
}
