package com.CandidateManagement.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.CandidateManagement.models.Candidate;

public class candidateRowMapper implements RowMapper<Candidate>{
	
 	@Override
 	public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
	 	Candidate candidate = new Candidate();
		candidate.setId(rs.getInt("id"));
		candidate.setName(rs.getString("name"));
		candidate.setEmail(rs.getString("email"));
		candidate.setJobTitle(rs.getString("jobTitle"));
		candidate.setPhone(rs.getString("phone"));
		candidate.setImageUrl(rs.getString("imageUrl"));
		candidate.setJoiningDate(rs.getString("joiningDate"));
		candidate.setCollegeName(rs.getString("collegeName"));
		candidate.setJoiningLocation(rs.getString("joiningLocation"));
		candidate.setSkill(rs.getString("skill"));
		candidate.setDescription(rs.getString("description"));
		candidate.setCreatedBy(rs.getString("createdBy"));
		candidate.setLastUpdatedBy(rs.getString("lastUpdatedBy"));

		return candidate;
 	}

}
