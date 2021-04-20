package com.CandidateManagement.dao;

import java.util.List;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.CandidateManagement.RowMapper.candidateRowMapper;
import com.CandidateManagement.models.Candidate;

@Component
public class candidateJdbcDAO implements DAO<Candidate>{

    private static final Logger log = LoggerFactory.getLogger(candidateJdbcDAO.class);
	private JdbcTemplate jdbcTemplate;
	
    public candidateJdbcDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Candidate> getCandidates() {
		String sql="SELECT * FROM candidate;";
		List<Candidate> candidates=jdbcTemplate.query(sql,new candidateRowMapper());
		return candidates;
		
	}

	@Override
	public void create(Candidate candidate) {
		String sql  = "INSERT into candidate(id,name,email,jobTitle,phone,imageUrl,joiningDate,collegeName,joiningLocation) VALUES (?,?,?,?,?,?,?,?,?);";
        int index = jdbcTemplate.update(sql, new Object[] {candidate.getId(),candidate.getName(),candidate.getEmail(),candidate.getJobTitle(),candidate.getPhone(),candidate.getImageUrl(),candidate.getJoiningDate(),candidate.getCollegeName(),candidate.getJoiningJocation()});
 

	}

	@Override
	public void update(Candidate candidate, int id) {
		String sql = "UPDATE candidate set name=?,email=?,jobTitle=?,phone=?,imageUrl=?,joiningDate=?,collegeName=?,joiningLocation=? where id=?";
		int index=jdbcTemplate.update(sql,new Object[] {candidate.getName(),candidate.getEmail(),candidate.getJobTitle(),candidate.getPhone(),candidate.getImageUrl(),candidate.getJoiningDate(),candidate.getCollegeName(),candidate.getJoiningJocation(),id});
		if(index==1) {
			log.info("Course Updated");
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM candidate WHERE id =?";
        int index = jdbcTemplate.update(sql, new Object[] {id});
	}
	
}
