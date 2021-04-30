package com.CandidateManagement.Controllers;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.dao.DAOTrends;
import com.CandidateManagement.models.Candidate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@RestController
public class TrendsController {
	@Autowired
    DAOTrends<Candidate> dao ;
	private static final Logger logger = LoggerFactory.getLogger(TrendsController.class);


	@GetMapping(path = "/trends/location")
	@ResponseBody
	public ArrayNode getAlllocation() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode list = mapper.createArrayNode();
			try {
				list = dao.getCountLocation();
			} catch (NoRecordFound e) {
				logger.error("Trend Controller Called-- No Location Found");
			}
		return list;
	}
	
	@GetMapping(path = "/trends/skills")
	@ResponseBody
	public ArrayNode getAllskills() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode list = mapper.createArrayNode();
			try {
				list = dao.getCountSkills();
			} catch (NoRecordFound e) {
				logger.error("Trend Controller Called-- No Skills Found");
			}
		return list;
	}

}
