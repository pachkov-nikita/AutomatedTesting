package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.DoctorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TimetableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;




    private MvcResult commonRequest(String path) throws Exception {
        return mockMvc.perform(get(path))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    private MvcResult requestWithModel(String path, DoctorModel model) throws Exception {
        return mockMvc.perform(post(path)
                        .content(objectMapper.writeValueAsString(model))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

    }
}
