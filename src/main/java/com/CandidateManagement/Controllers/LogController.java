package com.CandidateManagement.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.dao.DAOLogs;
import com.CandidateManagement.models.Candidate;
import com.CandidateManagement.models.Logs;

import java.util.ArrayList;
import java.util.List;


@RestController
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    DAOLogs dao;

    @GetMapping(path="/audits")
    public ResponseEntity<List<Logs>> getAllLogs(@RequestHeader(value="Authorization", required=true)String token)
    {
        logger.info("inside get all logs controller");
        List<Logs> logs = new ArrayList<>();
        try {
            logs = dao.getLogs();
        } catch (NoRecordFound e) {
            logger.error("Log Controller Called-- No Logs Found");
        }
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @PostMapping(path="/addAudit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Logs> addLogs(@RequestBody Logs log,@RequestHeader(value="Authorization", required=true)String token){
        logger.info("Log Controller Caller-- Adding Log");
        Logs logss = dao.addLog(log);
        return new ResponseEntity<>(logss,HttpStatus.CREATED);
    }


}
