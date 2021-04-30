package com.CandidateManagement.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<List<Candidate>> getAllCandidates(){
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
    
    @PostMapping("/add")
	public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate){
		logger.info("Candidate Controller Called-- Add Candidate");
		Candidate newCandidate = dao.create(candidate);
		return new ResponseEntity<>(newCandidate,HttpStatus.CREATED);
	}
    
    @PutMapping("/update")
    public ResponseEntity<Candidate> updateCandidate(@RequestBody Candidate candidate) {
		logger.info("Candidate Controller Called-- Update Candidate");
    	System.out.println("in update");
        int index=dao.updateCandidate(candidate,candidate.getId());
        if(index>0) {
        	return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);        
    }
    
    @DeleteMapping("/delete/{id}/{deletedById}")
    public ResponseEntity<?> deleteCandidate(@PathVariable(value="id") int id, @PathVariable(value="deletedById") String deletedById) {
		logger.info("Candidate Controller Called-- Delete Candidate");
        dao.delete(id,deletedById);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
