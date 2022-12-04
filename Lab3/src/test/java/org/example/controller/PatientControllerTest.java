package org.example.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Condition;
import org.example.entity.Patient;
import org.example.entity.TimeTable;
import org.example.model.PatientModel;
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
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Test15
    @Test
    void getCountPatient() throws Exception {
        MvcResult mvcResult = commonRequest("/patient/count");
        List<Patient> patients = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
        assertThat(patients).isNotEmpty().hasSize(3);
    }

    //Test16
    @Test
    void isPatientByName() throws Exception {

        String secondName = "Two";
        PatientModel patientModel = PatientModel.builder().secondName(secondName).build();

        MvcResult mvcResult = requestWithModel("/patient/secondname", patientModel);
        List<Patient> patientBySecondName = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(patientBySecondName).isNotEmpty().has(new Condition<>(patients -> patients.stream().allMatch(patient -> patient.getSecondName().equals("Two")), ""));
    }

    //Test17
    @Test
    void havePatientTimetableInHospital() throws Exception {
        long id = 1L;

        MvcResult mvcResult = commonRequest("/patient/" + id + "/timetable");
        List<TimeTable> timeTables = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});


        assertThat(timeTables).isEmpty();
    }


    private MvcResult commonRequest(String path) throws Exception {
        return mockMvc.perform(get(path))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    private MvcResult requestWithModel(String path, PatientModel model) throws Exception {
        return mockMvc.perform(post(path)
                        .content(objectMapper.writeValueAsString(model))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

    }
}
