package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Exam;
import edu.sdp.project.pethospital.entity.Medicine;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.ExamService;
import edu.sdp.project.pethospital.service.MedicineService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.entity.Medicine;
import edu.sdp.project.pethospital.service.UserService;
import edu.sdp.project.pethospital.service.VaccineService;
import edu.sdp.project.pethospital.service.MedicineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

public class ExamControllerTest {
    private ExamService examService;
    private MockMvc mockMvc;
    private static ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        examService = mock(ExamService.class);
        mockMvc = standaloneSetup(new ExamController(examService)).build();
        mapper = new ObjectMapper();

    }

    @Test
    public void happy_path_when_fetch_all_exam() throws Exception {
        List<Exam> retExam = new ArrayList<>();
        retExam.add(new Exam(100, "name1", "descriptiontest1"));
        retExam.add(new Exam(101, "name2", "descriptiontest1"));

        when(examService.getAllExams()).thenReturn(retExam);
        mockMvc.perform(get("/admin/structure/examination")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].examId").value(100))
                .andExpect(jsonPath("$.responseMap.result[1].examId").value(101));
        verify(examService).getAllExams();
        verifyNoMoreInteractions(examService);
    }

    @Test
    public void null_path_when_fetch_all_exam() throws Exception {
        when(examService.getAllExams()).thenReturn(null);
        mockMvc.perform(get("/admin/structure/examination")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(examService).getAllExams();
        verifyNoMoreInteractions(examService);
    }

    @Test
    public void happy_path_when_fetch_exam() throws Exception {
        int examId = 101;
        Exam exam = new Exam(101, "name1", "descriptiontest1");
        when(examService.getExam(examId)).thenReturn(exam);
        mockMvc.perform(put("/admin/structure/examination/{examId}", 101)).andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMap.result.examId").value(101));
        verify(examService).getExam(examId);
        verifyNoMoreInteractions(examService);
    }

    @Test
    void null_path_when_fetch_exam() throws Exception {
        when(examService.getExam(101)).thenReturn(null);
        mockMvc.perform(put("/admin/structure/examination/{examId}", 101)).andExpect(status().isOk())

                .andExpect(jsonPath("$.status").value(404));
        verify(examService).getExam(101);
    }

    @Test
    void happy_path_when_add_exam() throws Exception {
        Exam exam = new Exam(101, "name1", "descriptiontest1");
        when(examService.addExam(exam)).thenReturn(1);

        mockMvc.perform(get("/admin/structure/examination")).andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(200));

        verify(examService).getAllExams();
        verifyNoMoreInteractions(examService);
    }
    @Test
    void null_path_when_add_exam() throws Exception {
        Exam exam = new Exam(101, "name1", "descriptiontest1");
        when(examService.addExam(exam)).thenReturn(-1);

        mockMvc.perform(get("/admin/structure/examination")).andExpect(status().isOk());

        verify(examService).getAllExams();
        verifyNoMoreInteractions(examService);
    }

    @Test
    void happy_path_when_delete_exam() throws Exception {
        int examId = 101;
        when(examService.checkId(examId)).thenReturn(true);
        when(examService.deleteExam(examId)).thenReturn(1);
        mockMvc.perform(delete("/admin/structure/examination").param("examId", "101")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
        verify(examService).deleteExam(examId);
    }

    @Test
    void null_path_when_delete_exam() throws Exception {
        int examId = 101;
        when(examService.checkId(examId)).thenReturn(false);
        when(examService.deleteExam(examId)).thenReturn(-1);
        mockMvc.perform(delete("/admin/structure/examination").param("examId", "101")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void happy_path_when_update_exam() throws Exception {
        int examId = 101;
        Map param = new HashMap();
        String examName = "examName1";
        String examDescrip = "examDescrip";
        param.put("examName", examName);
        param.put("examDescrip", examDescrip);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);

        Exam exam = new Exam(101, "examName1", "examDescrip");
        when(examService.getExam(examId)).thenReturn(exam);
        mockMvc.perform(post("/admin/structure/examination/101").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void null_path_when_update_exam() throws Exception {
        int examId = 101;
        Map param = new HashMap();
        String examName = "examName1";
        String examDescrip = "examDescrip";
        param.put("examName", examName);
        param.put("examDescrip", examDescrip);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);

        Exam exam = new Exam(101, "examName1", "examDescrip");
        when(examService.getExam(examId)).thenReturn(null);
        mockMvc.perform(post("/admin/structure/examination/101").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(404));

    }
}