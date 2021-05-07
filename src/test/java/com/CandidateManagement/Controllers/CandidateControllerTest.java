package com.CandidateManagement.Controllers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.CandidateManagement.dao.candidateJdbcDAO;
import com.CandidateManagement.models.Candidate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CandidateControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public candidateJdbcDAO CandidateDAO;

    @Autowired
    private ObjectMapper objectMapper;

    public Candidate candidate;


    @Test
    public void shouldGetAllCandidate() throws Exception {
        mockMvc.perform(get("/all").header("Authorization", "Bearer ljdsljflsjlfjlsjdljflsdjlfsjd")).andExpect(status().is(200));
    }


    @Test
    public void shouldAddCandidates() throws Exception{
        Candidate candidate = new Candidate();
        String json = objectMapper.writeValueAsString(candidate);
        mockMvc.perform(post("/add/{currentUser}","harshlynn90@gmail.com")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .header("Authorization", "ljdsljflsjlfjlsjdljflsdffffffffffffjlfsjd")
        ).andExpect(status().is(201));
    }

    @Test
    public void shouldDelOpportunity() throws Exception {
        mockMvc.perform(delete("/delete/{id}/{deletedById}",(int)1,"sjdhfjhb")
                .header("Authorization", "ljdsljflsjlfjlsjdljflsdffffffffffffjlfsjd")
        ).andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateOpportunity() throws Exception{
        Candidate candidate = new Candidate();
        String json = objectMapper.writeValueAsString(candidate);
        mockMvc.perform(put("/update/{currentUser}","harshlynn90@gmail.com")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .header("Authorization", "ljdsljflsjlfjlsjdljflsdffffffffffffjlfsjd")
        ).andExpect(status().is(200));
    }

}
