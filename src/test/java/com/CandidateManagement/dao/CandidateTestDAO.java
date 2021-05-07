package com.CandidateManagement.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.RowMapper.candidateRowMapper;
import com.CandidateManagement.models.Candidate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CandidateTestDAO {
    @Mock
    JdbcTemplate jdbcTemplate;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    candidateJdbcDAO CandidateDAO;

    @Test
    public void shouldGetAllCandidates() throws NoRecordFound {
        System.out.println("in dao test Candidate");
        ArrayList<Candidate> list = new ArrayList<>();
        list.add(new Candidate(1,"Harshil Jangid","harshlynn90@gmail.com","Intern","874651368463","wwasdas","2021","NIT J","Bangalore","JAVA","khasdh","harshlynn90@gmail.com","harshlynn90@gmail.com"));
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(candidateRowMapper.class))).thenReturn(list);
        List<Candidate> list2 = CandidateDAO.getCandidates();
        Assert.assertEquals(7, list2.size());
    }
    @Test
    public void shouldAddCandidate() {
        Candidate result = CandidateDAO.create(new Candidate(61,"Harshil Jangid","harshlynn90@gmail.com","Intern","874651368463","wwasdas","2021","NIT J","Bangalore","JAVA","khasdh","harshlynn90@gmail.com","harshlynn90@gmail.com"));
        Assert.assertNotNull(result);
    }

    @Test
    public void shouldUpdateCandidate() {
        int tomatch = 1;
        int index= CandidateDAO.updateCandidate(new Candidate(61,"Harshil Jangid","harshlynn90@gmail.com","Intern","874651368463","wwasdas","2021","NIT J","Bangalore","JAVA","khasdh","harshlynn90@gmail.com","harshlynn90@gmail.com"),61);
        Assert.assertEquals(index, tomatch);
    }

    @Test
    public void shouldDeleteCandidate() {
        int tomatch = 1;
        int index=CandidateDAO.delete(61,"harshlynn90@gmail.com");
        Assert.assertEquals(index, tomatch);
    }

}




