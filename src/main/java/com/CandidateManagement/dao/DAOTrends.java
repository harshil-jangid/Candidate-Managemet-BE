package com.CandidateManagement.dao;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.models.Candidate;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.*;

public interface DAOTrends<Candidate> {
	
	public ArrayNode getCountLocation() throws NoRecordFound;	

	public ArrayNode getCountSkills() throws NoRecordFound;	


}
