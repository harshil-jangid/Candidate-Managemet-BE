package com.CandidateManagement.dao;

import java.util.List;

import com.CandidateManagement.models.Logs;

public interface DAOLogs {
	
	public Logs addLog(Logs log);
	
    public List<Logs> getLogs();

}
