package com.CandidateManagement.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.dao.DAOLogs;
import com.CandidateManagement.models.Candidate;
import com.CandidateManagement.models.Logs;

import java.util.ArrayList;
import java.util.List;


@RestController
public class LogsController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogsController.class);

	@Autowired
    DAOLogs dao;

    @GetMapping(path = "/audits")
    public List<Logs> getAllLogs()
    {
        logger.info("inside get all logs controller");
        List<Logs> logs = new ArrayList<>();
        try {
			logs = dao.getLogs();
		} catch (NoRecordFound e) {
	  		logger.error("Log Controller Called-- No Logs Found");

		}

        return logs;
    }
    
    @PostMapping(path="/addAudit")
    public ResponseEntity<Logs> addLogs(@RequestBody Logs log){
        logger.info("Log Controller Caller-- Adding Log");
        Logs logss = dao.addLog(log);
        return new ResponseEntity<>(logss,HttpStatus.CREATED);
    }
    

}
