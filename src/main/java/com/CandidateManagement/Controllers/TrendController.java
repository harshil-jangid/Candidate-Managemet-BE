package com.CandidateManagement.Controllers;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.dao.DAOTrends;
import com.CandidateManagement.models.Candidate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@RestController
public class TrendController {
    @Autowired
    DAOTrends<Candidate> dao ;
    private static final Logger logger = LoggerFactory.getLogger(TrendController.class);


    @GetMapping(path = "/trends/location")
    @ResponseBody
    public ArrayNode getAlllocation(@RequestHeader(value="Authorization", required=true)String token) throws NoRecordFound {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode list = mapper.createArrayNode();

        list = dao.getCountLocation();

        return list;
    }

    @GetMapping(path = "/trends/skills")
    @ResponseBody
    public ArrayNode getAllskills() throws NoRecordFound {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode list = mapper.createArrayNode();

        list = dao.getCountSkills();
        return list;
    }

}
