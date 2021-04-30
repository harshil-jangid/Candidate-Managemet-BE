package com.CandidateManagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;
import com.CandidateManagement.models.Candidate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class trendsJdbcDAO implements DAOTrends<Candidate> {
	
	private JdbcTemplate jdbcTemplate;
    public trendsJdbcDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
    @Override
	public ArrayNode getCountLocation() {
		String Query="select joiningLocation, joiningDate, count(*) from candidate group by joiningDate, joiningLocation order by joiningLocation; ";
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode outerList = mapper.createArrayNode();

		
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				ArrayNode innerList = mapper.createArrayNode();
			    ObjectNode innerNode = mapper.createObjectNode();
				ObjectNode outerNode = mapper.createObjectNode();

			    innerNode.put("name", resultSet.getString(2));
			    innerNode.put("value", resultSet.getString(3));
				innerList.add(innerNode);
			    innerNode=mapper.createObjectNode();
			    String currentString= resultSet.getString(1);
				 while(resultSet.next()) {
					 if(currentString.equals(resultSet.getString(1))) {
						    innerNode.put("name", resultSet.getString(2));
						    innerNode.put("value", resultSet.getString(3));
						    innerList.add(innerNode);
						    System.out.println(currentString);
						    innerNode = mapper.createObjectNode();
					 }else {
						 outerNode.put("name", currentString);
						 outerNode.set("series", innerList);
						 outerList.add(outerNode);
						 outerNode=mapper.createObjectNode();
						 innerList=mapper.createArrayNode();
						 currentString = resultSet.getString(1);
						 innerNode.put("name", resultSet.getString(2));
						 innerNode.put("value", resultSet.getString(3));
						 innerList.add(innerNode);
						 innerNode=mapper.createObjectNode();
					 }
				 }
				 outerNode=mapper.createObjectNode();
				 outerNode.put("name", currentString);
				 outerNode.set("series", innerList);
				 outerList.add(outerNode);
				 System.out.println(outerList);
			}
		});
		
		return outerList;
	}
    
    @Override
	public ArrayNode getCountSkills() {
    	String Query="select skill, joiningDate, count(*) from candidate group by joiningDate, skill order by skill;";
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode outerList = mapper.createArrayNode();

		
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				ArrayNode innerList = mapper.createArrayNode();
			    ObjectNode innerNode = mapper.createObjectNode();
				ObjectNode outerNode = mapper.createObjectNode();

			    innerNode.put("name", resultSet.getString(2));
			    innerNode.put("value", resultSet.getString(3));
				innerList.add(innerNode);
			    innerNode=mapper.createObjectNode();
			    String currentString= resultSet.getString(1);
				 while(resultSet.next()) {
					 if(currentString.equals(resultSet.getString(1))) {
						    innerNode.put("name", resultSet.getString(2));
						    innerNode.put("value", resultSet.getString(3));
						    innerList.add(innerNode);
						    System.out.println(currentString);
						    innerNode = mapper.createObjectNode();
					 }else {
						 outerNode.put("name", currentString);
						 outerNode.set("series", innerList);
						 outerList.add(outerNode);
						 outerNode=mapper.createObjectNode();
						 innerList=mapper.createArrayNode();
						 currentString = resultSet.getString(1);
						 innerNode.put("name", resultSet.getString(2));
						 innerNode.put("value", resultSet.getString(3));
						 innerList.add(innerNode);
						 innerNode=mapper.createObjectNode();
					 } 
				 }
				 outerNode=mapper.createObjectNode();
				 outerNode.put("name", currentString);
				 outerNode.set("series", innerList);
				 outerList.add(outerNode);
			}
		});
		
		return outerList;
    }
}
