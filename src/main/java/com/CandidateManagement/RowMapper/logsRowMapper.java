package com.CandidateManagement.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.CandidateManagement.models.Logs;


public class logsRowMapper implements RowMapper<Logs> {
	
	@Override
 	public Logs mapRow(ResultSet rs, int rowNum) throws SQLException {
	 	Logs log = new Logs();
	 	log.setId(rs.getInt("id"));
	 	log.setAction(rs.getString("action"));
	 	log.setEmail(rs.getString("email"));
	 	log.setCandidateId(rs.getInt("candidateId"));
	 	log.setTimeStamp(rs.getString("timeStamp"));
	 	log.setOldValue(rs.getString("oldValue"));
	 	log.setNewValue(rs.getString("newValue"));

		return log;
	}
}
