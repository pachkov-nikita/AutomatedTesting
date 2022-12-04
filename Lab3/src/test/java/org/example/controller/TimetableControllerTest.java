package org.example.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Condition;
import org.example.entity.TimeTable;
import org.example.model.TimetableModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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


    //Test6
    @Test
    void getAllTimetable() throws Exception {

        MvcResult mvcResult = commonRequest("/timetable/count");

        var timetable = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);

        assertThat(timetable).isNotEmpty().hasSize(8);
    }

    //Test7
    @Test
    void getTimetableForDoctorId() throws Exception {

        MvcResult mvcResult = commonRequest("/timetable/doctor/2");

        List<TimeTable> timetable = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(timetable).isNotEmpty().has(new Condition<>(timeTables -> timeTables.stream().allMatch(table -> table.getDoctorId() == 2), ""));
    }

    //Test8
    @Test
    void getTimetableForPatientId() throws Exception {

        MvcResult mvcResult = commonRequest("/timetable/patient/4");

        List<TimeTable> timetable = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(timetable).isNotEmpty().has(new Condition<>(timeTables -> timeTables.stream().allMatch(table -> table.getPatientId() == 4), ""));
    }

    //Test9
    @Test
    void createRecord() throws Exception {
        TimetableModel timetableModel = TimetableModel.builder().doctorId(3L).patientId(5L).ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 11, 0)).build();

        MvcResult mvcResult = requestWithModel("/timetable/timetable", timetableModel);
        TimeTable timeTable = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(timeTable).isNotNull().hasNoNullFieldsOrProperties().has(new Condition<>(
                table -> !table.isVacant() && table.getDoctorId() == 3L  && table.getPatientId() == 5L &&
                        table.getLdt().equals(LocalDateTime.of(2022, Month.DECEMBER, 11, 11, 0)), ""));
    }


    private MvcResult commonRequest(String path) throws Exception {
        return mockMvc.perform(get(path))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    private MvcResult requestWithModel(String path, TimetableModel model) throws Exception {
        return mockMvc.perform(post(path)
                        .content(objectMapper.writeValueAsString(model))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

    }
}
