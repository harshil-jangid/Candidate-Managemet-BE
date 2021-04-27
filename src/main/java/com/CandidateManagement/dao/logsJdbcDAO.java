package com.CandidateManagement.dao;

import org.springframework.stereotype.Component;

import com.CandidateManagement.RowMapper.logsRowMapper;
import com.CandidateManagement.models.Logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class logsJdbcDAO implements DAOLogs {
	
	
	private static final Logger logger = LoggerFactory.getLogger(logsJdbcDAO.class);

    private JdbcTemplate jdbcTemplate;
    
    public logsJdbcDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Logs addLog(Logs log) {
    	
        logger.info("Inside add log");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("date is"+formatter.format(date));
        
        log.setTimeStamp(formatter.format(date));
        String sql = "INSERT into logs(action,email,candidateId,timeStamp,oldValue,newValue) VALUES (?,?,?,?,?,?)";
        int index = jdbcTemplate.update(sql, new Object[]{log.getAction(), log.getEmail(), log.getCandidateId(), log.getTimeStamp(), log.getOldValue(), log.getNewValue()});
        
        String sql1="SELECT * FROM logs;";
        List<Logs> logs=jdbcTemplate.query(sql1,new logsRowMapper());
        log.setId(logs.get(logs.size() - 1).getId());
        
        return log;
    }

    @Override
    public List<Logs> getLogs() {
        logger.info("inside get all logs");
        String sql = "SELECT * from logs";
        List<Logs> logs = jdbcTemplate.query(sql,new logsRowMapper());
        return logs;
    }
	

}
