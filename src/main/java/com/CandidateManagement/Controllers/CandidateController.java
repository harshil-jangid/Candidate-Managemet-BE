package com.CandidateManagement.Controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.CandidateManagement.dao.DAO;
import com.CandidateManagement.dao.candidateJdbcDAO;
import com.CandidateManagement.models.Candidate;

@RestController
public class CandidateController {
	@Autowired
    DAO<Candidate> dao ;

//    @GetMapping(path ="/candidates")
//    public List<Candidate> getCandidates() {
//        System.out.println("in controllers");
//        List<Opportunity>loadOpportunities = new ArrayList<Opportunity>();
//        loadOpportunities = OpportunityDao.getOpportunities();
//		return loadOpportunities;
//    }
    
    @GetMapping(path="/all")
	public ResponseEntity<List<Candidate>> getAllCandidates(){
    	System.out.println("in controllers");
		List<Candidate> candidates = new ArrayList<Candidate>();
		candidates=dao.getCandidates();
		return new ResponseEntity<>(candidates, HttpStatus.OK);
	}
    
    @PostMapping("/add")
	public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate){
    	System.out.println("In add controller");
		Candidate newCandidate = dao.create(candidate);
		return new ResponseEntity<>(newCandidate,HttpStatus.CREATED);
	}
    
    @PutMapping("/update")
    public ResponseEntity<Candidate> updateCandidate(@RequestBody Candidate candidate) {
    	System.out.println("in update");
        int index=dao.updateCandidate(candidate,candidate.getId());
        if(index>0) {
        	return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);        
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable("id") int id) {
    	System.out.println("in delete controller");
        dao.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
