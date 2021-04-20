package com.CandidateManagement.dao;

import java.util.List;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.CandidateManagement.models.Candidate;

@Component
public class candidateJdbcDAO implements DAO<Candidate>{

    private static final Logger log = LoggerFactory.getLogger(candidateJdbcDAO.class);
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Candidate> rowMapper = (rs,rowNum) -> {
		Candidate candidate = new Candidate();
		candidate.setId(rs.getLong("id"));
		candidate.setName(rs.getString("name"));
		candidate.setEmail(rs.getString("email"));
		candidate.setJobTitle(rs.getString("jobTitle"));
		candidate.setPhone(rs.getString("phone"));
		candidate.setImageUrl(rs.getString("imageUrl"));
		candidate.setJoiningDate(rs.getString("joiningDate"));
		candidate.setCollegeName(rs.getString("collegeName"));
		candidate.setJoiningJocation(rs.getString("joiningLocation"));
		return candidate;
	};
    public candidateJdbcDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Candidate> getCandidates() {
		String sql="SELECT * FROM candidate;";
		return jdbcTemplate.query(sql, rowMapper);
		
	}

	@Override
	public void create(Candidate candidate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Candidate candidate, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
