package com.CandidateManagement.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.dao.DAO;
import com.CandidateManagement.models.Candidate;

@RestController
public class CandidateController {
	@Autowired
    DAO<Candidate> dao ;
    
	private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @GetMapping(path="/all")
	public ResponseEntity<List<Candidate>> getAllCandidates(@RequestHeader(value="Authorization", required=true)String token){
		logger.info("Candidate Controller Called-- GetAll");
		List<Candidate> candidates = null;
		try {
			candidates = new ArrayList<Candidate>();
			candidates=dao.getCandidates();
		} catch (NoRecordFound e) {
			logger.error("Candidate Controller Called-- No Record Found");		
		}
		return new ResponseEntity<>(candidates, HttpStatus.OK);
	}
    
    @PostMapping(path="/add/{currentUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate, @PathVariable(value="currentUser") String currentUser,@RequestHeader(value="Authorization", required=true)String token){
		logger.info("Candidate Controller Called-- Add Candidate");
		candidate.setCreatedBy(currentUser);
		candidate.setLastUpdatedBy(currentUser);
		Candidate newCandidate = dao.create(candidate);
		return new ResponseEntity<>(newCandidate,HttpStatus.CREATED);
	}
    
    @PutMapping(path="/update/{currentUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Candidate> updateCandidate(@RequestBody Candidate candidate,  @PathVariable(value="currentUser") String currentUser,@RequestHeader(value="Authorization", required=true)String token) {
		logger.info("Candidate Controller Called-- Update Candidate");
		candidate.setLastUpdatedBy(currentUser);
        int index=dao.updateCandidate(candidate,candidate.getId());
        System.out.println(index);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    @DeleteMapping("/delete/{id}/{deletedById}")
    public ResponseEntity<?> deleteCandidate(@PathVariable(value="id") int id, @PathVariable(value="deletedById") String deletedById) {
		logger.info("Candidate Controller Called-- Delete Candidate");
        int index=dao.delete(id,deletedById);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
