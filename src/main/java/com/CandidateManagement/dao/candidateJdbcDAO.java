package com.CandidateManagement.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.RowMapper.candidateRowMapper;
import com.CandidateManagement.models.Candidate;
import com.CandidateManagement.models.Logs;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

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
	public List<Candidate> getCandidates() throws NoRecordFound {
		List<Candidate> candidates=new ArrayList<>();
		try {
			String sql="SELECT * FROM candidate order by id DESC;";
			candidates = jdbcTemplate.query(sql,new candidateRowMapper());
		}catch(Exception e) {
			throw (NoRecordFound)e;
		}
		return candidates;
		
	}
	

	@Override
	public Candidate create(Candidate candidate) {
		
		String sql  = "INSERT into candidate(id,name,email,jobTitle,phone,imageUrl,joiningDate,collegeName,joiningLocation,skill,description,createdBy,lastUpdatedBy) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
		String s= candidate.getJoiningDate();
		String str[] = s.split("-");
		candidate.setJoiningDate(str[0]);
		int index = jdbcTemplate.update(sql, new Object[] {candidate.getId(),candidate.getName(),candidate.getEmail(),candidate.getJobTitle(),candidate.getPhone(),candidate.getImageUrl(),candidate.getJoiningDate(),candidate.getCollegeName(),candidate.getJoiningLocation(),candidate.getSkill(),candidate.getDescription(),candidate.getCreatedBy(),candidate.getLastUpdatedBy()});
        String sql1="SELECT * FROM candidate;";
        
        List<Candidate> candidates=jdbcTemplate.query(sql1,new candidateRowMapper());
        candidate.setId(candidates.get(candidates.size() - 1).getId());

        Candidate oldCandidate= new Candidate();
        String newValue="";
		String oldValue="";
		ObjectMapper Obj = new ObjectMapper();
		try {
            String jsonStr  = Obj.writeValueAsString(candidate);
            System.out.println(jsonStr);
            newValue=jsonStr;
            jsonStr  = Obj.writeValueAsString(oldCandidate);
            oldValue=jsonStr;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Logs newLog = new Logs();
        newLog.setEmail(candidates.get(candidates.size() - 1).getCreatedBy());
        newLog.setOldValue(oldValue);
        newLog.setNewValue(newValue);
        newLog.setAction("Added new Candidate");
        newLog.setId((int)candidate.getId());
        dao.addLog(newLog);
        return candidate;
	}
	
	public Candidate returnOldValue(int id) throws NoRecordFound {
		Candidate old= new Candidate();
		try {
			String sqlString="Select * from candidate where id="+id+";";
			old = jdbcTemplate.queryForObject(sqlString, new candidateRowMapper());
		}catch(Exception e) {
			throw (NoRecordFound)e;
		}
		return old;
	}
	

	@Override
	public int updateCandidate(Candidate candidate, int id) {
		String sql = "UPDATE candidate set name=?,email=?,jobTitle=?,phone=?,imageUrl=?,joiningDate=?,collegeName=?,joiningLocation=?,skill=?,description=?,createdBy=?,lastUpdatedBy=? where id=?";
		String s= candidate.getJoiningDate();
		String str[] = s.split("-");
		candidate.setJoiningDate(str[0]);
		Candidate old = null;
		try {
			old = returnOldValue(id);
		} catch (NoRecordFound e1) {
			e1.printStackTrace();
		}
		int index=jdbcTemplate.update(sql,new Object[] {candidate.getName(),candidate.getEmail(),candidate.getJobTitle(),candidate.getPhone(),candidate.getImageUrl(),candidate.getJoiningDate(),candidate.getCollegeName(),candidate.getJoiningLocation(),candidate.getSkill(),candidate.getDescription(),candidate.getCreatedBy(),candidate.getLastUpdatedBy(),id});
		if(index==1) {
			log.info("Course Updated");
		}
		String newValue="";
		String oldValue="";
		ObjectMapper Obj = new ObjectMapper();
		
		try {
            String jsonStr  = Obj.writeValueAsString(candidate);
            System.out.println(jsonStr );
            newValue=jsonStr;
            jsonStr  = Obj.writeValueAsString(old);
            oldValue=jsonStr;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	
		Logs newLog = new Logs();
        newLog.setCandidateId(id);
        newLog.setNewValue(newValue);
        newLog.setOldValue(oldValue);
        newLog.setAction("Updated this Candidate");
        newLog.setEmail(candidate.getLastUpdatedBy());	        
        dao.addLog(newLog);
		return index;
	}

	@Override
	public int delete(int id, String deletedById) {
		String sql = "DELETE FROM candidate WHERE id =?";
		Candidate old = null;
		try {
			old = returnOldValue(id);
		} catch (NoRecordFound e1) {
			e1.printStackTrace();
		}

        int index = jdbcTemplate.update(sql, new Object[] {id});

        Candidate newCandidate= new Candidate();
        
        
        String newValue="";
		String oldValue="";
		ObjectMapper Obj = new ObjectMapper();
		
		try {
            String jsonStr  = Obj.writeValueAsString(newCandidate);
            System.out.println(jsonStr );
            newValue=jsonStr;
            jsonStr  = Obj.writeValueAsString(old);
            oldValue=jsonStr;
            System.out.println(oldValue);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
        Logs newLog = new Logs();   
        newLog.setEmail(deletedById);	        
        newLog.setCandidateId(id);
        newLog.setOldValue(oldValue);
        newLog.setNewValue(newValue);
        newLog.setAction("Deleted this Candidate");
        dao.addLog(newLog);
        return index;
	}
	
}
