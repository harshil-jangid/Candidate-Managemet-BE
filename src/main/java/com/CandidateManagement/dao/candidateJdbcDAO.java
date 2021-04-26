package com.CandidateManagement.dao;

import java.util.List;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.CandidateManagement.RowMapper.candidateRowMapper;
import com.CandidateManagement.models.Candidate;
import com.CandidateManagement.models.Logs;

@Component
public class candidateJdbcDAO implements DAO<Candidate>{

    private static final Logger log = LoggerFactory.getLogger(candidateJdbcDAO.class);
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    DAOLogs dao;

	
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
	public Candidate create(Candidate candidate) {
		
		String sql  = "INSERT into candidate(id,name,email,jobTitle,phone,imageUrl,joiningDate,collegeName,joiningLocation,skill,description,createdBy,lastUpdatedBy) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        int index = jdbcTemplate.update(sql, new Object[] {candidate.getId(),candidate.getName(),candidate.getEmail(),candidate.getJobTitle(),candidate.getPhone(),candidate.getImageUrl(),candidate.getJoiningDate(),candidate.getCollegeName(),candidate.getJoiningLocation(),candidate.getSkill(),candidate.getDescription(),candidate.getCreatedBy(),candidate.getLastUpdatedBy()});
        String sql1="SELECT * FROM candidate;";
        List<Candidate> candidates=jdbcTemplate.query(sql1,new candidateRowMapper());
        candidate.setId(candidates.get(candidates.size() - 1).getId());

        Logs newLog = new Logs();
        newLog.setEmail(candidate.getCreatedBy());
        newLog.setAction("Added new Candidate");
        newLog.setId((int)candidate.getId());
        dao.addLog(newLog);
        return candidate;
 

	}

	@Override
	public int updateCandidate(Candidate candidate, int id) {
		String sql = "UPDATE candidate set name=?,email=?,jobTitle=?,phone=?,imageUrl=?,joiningDate=?,collegeName=?,joiningLocation=?,skill=?,description=?,createdBy=?,lastUpdatedBy=? where id=?";
		int index=jdbcTemplate.update(sql,new Object[] {candidate.getName(),candidate.getEmail(),candidate.getJobTitle(),candidate.getPhone(),candidate.getImageUrl(),candidate.getJoiningDate(),candidate.getCollegeName(),candidate.getJoiningLocation(),candidate.getSkill(),candidate.getDescription(),candidate.getCreatedBy(),candidate.getLastUpdatedBy(),id});
		if(index==1) {
			log.info("Course Updated");
		}
		 Logs newLog = new Logs();

	        newLog.setCandidateId(id);

	        newLog.setAction("Updated this Candidate");

	        newLog.setEmail(candidate.getLastUpdatedBy());	        
	        dao.addLog(newLog);
		return index;
	}

	@Override
	public void delete(int id, String deletedById) {
		String sql = "DELETE FROM candidate WHERE id =?";
        
        int index = jdbcTemplate.update(sql, new Object[] {id});

        Logs newLog = new Logs();
        
        newLog.setEmail(deletedById);	        
        newLog.setCandidateId(id);
        newLog.setAction("Deleted this Candidate");
        dao.addLog(newLog);
	}
	
}
