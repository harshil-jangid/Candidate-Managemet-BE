package com.CandidateManagement.dao;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.fasterxml.jackson.databind.node.ArrayNode;

public interface DAOTrends<Candidate> {
	
	public ArrayNode getCountLocation() throws NoRecordFound;	

	public ArrayNode getCountSkills() throws NoRecordFound;	


}
