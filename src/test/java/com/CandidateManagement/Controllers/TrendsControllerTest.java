package com.CandidateManagement.Controllers;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.dao.candidateJdbcDAO;
import com.CandidateManagement.dao.trendsJdbcDAO;
import com.CandidateManagement.models.Candidate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = {CandidateController.class})
@SpringBootTest
@AutoConfigureMockMvc
public class TrendsControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public trendsJdbcDAO trendsdao;

    @Autowired
    private ObjectMapper objectMapper;

    public Candidate candidate;


    @Test
    public void shouldGetTrendsLocation() throws Exception {
//        Mockito.when(this.opportunityDaoImp.getOpportunities()).thenReturn(new ArrayList<Opportunity>());
        mockMvc.perform(get("/trends/location").header("Authorization", "ljdsljflsjlfjlsjdljflsdjlfsjd")).andExpect(status().is(200));
    }
    @Test
    public void shouldGetTrendsSkills() throws Exception {
//        Mockito.when(this.opportunityDaoImp.getOpportunities()).thenReturn(new ArrayList<Opportunity>());
        mockMvc.perform(get("/trends/skills").header("Authorization", "ljdsljflsjlfjlsjdljflsdjlfsjd")).andExpect(status().is(200));
    }
}
