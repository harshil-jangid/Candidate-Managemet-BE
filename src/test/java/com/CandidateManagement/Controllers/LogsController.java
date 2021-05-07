package com.CandidateManagement.Controllers;

import com.CandidateManagement.dao.candidateJdbcDAO;
import com.CandidateManagement.models.Candidate;
import com.CandidateManagement.models.Logs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LogsController {


    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public LogsController LogsDAO;

    @Autowired
    private ObjectMapper objectMapper;

    public Candidate candidate;

    @Test
    public void shouldGetLogs() throws Exception {
        mockMvc.perform(get("/audits").header("Authorization", "ljdsljflsjlfjlsjdljflsdjlfsjd")).andExpect(status().is(200));
    }

    @Test
    public void shouldAddLogs() throws Exception{
        Logs log = new Logs(1,"asfa","sdfsd",1,"sdfs","sdfs","sdfs");
        String json = objectMapper.writeValueAsString(log);
        mockMvc.perform(post("/addAudit")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .header("Authorization", "ljdsljflsjlfjlsjdljflsdffffffffffffjlfsjd")
        ).andExpect(status().is(201));
    }
}