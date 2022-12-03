package org.example.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Condition;
import org.example.entity.Doctor;
import org.example.model.DoctorModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void isDoctorById() throws Exception {
        MvcResult mvcResult = commonRequest("/doctors/" + "2");

        Doctor doctorById = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Doctor.class);

        assertThat(doctorById).isNotNull().hasNoNullFieldsOrProperties().has(new Condition<>(d -> d.getFirstName().equals("Ivan"), ""));
    }

    @Test
    void isDoctorByName() throws Exception {

        String firstName = "Ivan";
        DoctorModel doctorModel = DoctorModel.builder().firstName(firstName).build();

        MvcResult mvcResult = requestWithModel("/doctors/firstname", doctorModel);
        List<Doctor> doctorsByFirstName = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(doctorsByFirstName).isNotEmpty().has(new Condition<>(doctors -> doctors.stream().allMatch(d -> d.getFirstName().equals("Ivan")), ""));
    }


    @Test
    void isDoctorBySecondName() throws Exception {

        String secondName = "Ivanov";
        DoctorModel doctorModel = DoctorModel.builder().secondName(secondName).build();

        MvcResult mvcResult = requestWithModel("/doctors/secondname", doctorModel);
        List<Doctor> doctorBySecondName = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(doctorBySecondName).isNotEmpty().has(new Condition<>(doctors -> doctors.stream().allMatch(d -> d.getSecondName().equals("Ivanov")), ""));
    }


    @Test
    void shouldFetchAllDoctors() throws Exception {

        MvcResult mvcResult = commonRequest("/doctors/count");

        var doctors = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);

        assertThat(doctors).isNotEmpty().hasSize(3);
    }


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
