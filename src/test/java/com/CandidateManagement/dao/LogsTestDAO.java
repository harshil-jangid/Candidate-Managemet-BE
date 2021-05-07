package com.CandidateManagement.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.CandidateManagement.Exceptions.NoRecordFound;
import com.CandidateManagement.RowMapper.candidateRowMapper;
import com.CandidateManagement.RowMapper.logsRowMapper;
import com.CandidateManagement.models.Candidate;
import com.CandidateManagement.models.Logs;
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
public class LogsTestDAO {
    @Mock
    JdbcTemplate jdbcTemplate;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    logsJdbcDAO LogsDAO;

    @Test
    public void shouldGetAllLogs() throws NoRecordFound {
        List<Logs> list2 = LogsDAO.getLogs();
        Assert.assertEquals(28, list2.size());
    }

    @Test
    public void shouldAddCandidate() {
        Logs result = LogsDAO.addLog(new Logs(86, "Deleted this Candidate", "harshlynn90@gmail.com", 61, "07/05/2021 00:44:26", "{\"id\":61,\"name\":\"Harshil Jangid\",\"email\":\"harshlynn90@gmail.com\",\"jobTitle\":\"Intern\",\"phone\":\"874651368463\",\"imageUrl\":\"wwasdas\",\"joiningDate\":\"2021\",\"collegeName\":\"NIT J\",\"joiningLocation\":\"Bangalore\",\"skill\":\"JAVA\",\"description\":\"khasdh\",\"createdBy\":\"harshlynn90@gmail.com\",\"lastUpdatedBy\":\"harshlynn90@gmail.com\"}", "{\"id\":0,\"name\":null,\"email\":null,\"jobTitle\":null,\"phone\":null,\"imageUrl\":null,\"joiningDate\":null,\"collegeName\":null,\"joiningLocation\":null,\"skill\":null,\"description\":null,\"createdBy\":null,\"lastUpdatedBy\":null}"));
        Assert.assertNotNull(result);
    }
}
