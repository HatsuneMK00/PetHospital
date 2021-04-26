package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Fee;
import edu.sdp.project.pethospital.entity.Medicine;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.entity.Section;
import edu.sdp.project.pethospital.service.FeeService;
import edu.sdp.project.pethospital.service.ImageService;
import edu.sdp.project.pethospital.service.MedicineService;
import edu.sdp.project.pethospital.service.SectionService;
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

import edu.sdp.project.pethospital.service.OptionUserService;
import edu.sdp.project.pethospital.service.TestService;

import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

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

public class TestControllerTest {
    private TestService testService;
    private OptionUserService optionUserService;
    private MockMvc mockMvc;
    private static ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        testService = mock(TestService.class);
        optionUserService = mock(OptionUserService.class);
        mockMvc = standaloneSetup(new TestController(testService, optionUserService)).build();
        mapper = new ObjectMapper();

    }

    @Test
    public void happy_path_when_fetch_all_test() throws Exception {
        List<edu.sdp.project.pethospital.entity.Test> retTest = new ArrayList<>();
        Date date1 = new Date(10000);
        Date date2 = new Date(10000);
        retTest.add(new edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1, date2, 100));
        retTest.add(new edu.sdp.project.pethospital.entity.Test(2, 1, 1, date1, date2, 100));

        when(testService.getAllTests()).thenReturn(retTest);
        mockMvc.perform(get("/admin/test/exams")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].testId").value(1))
                .andExpect(jsonPath("$.responseMap.result[1].testId").value(2));
        verify(testService).getAllTests();
        verifyNoMoreInteractions(testService);
    }

    @Test
    public void null_path_when_fetch_all_test() throws Exception {
        List<edu.sdp.project.pethospital.entity.Test> retTest = new ArrayList<>();
        Date date1 = new Date(10000);
        Date date2 = new Date(10000);
        retTest.add(new edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1, date2, 100));
        retTest.add(new edu.sdp.project.pethospital.entity.Test(2, 1, 1, date1, date2, 100));

        when(testService.getAllTests()).thenReturn(null);
        mockMvc.perform(get("/admin/test/exams")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(404));
        verify(testService).getAllTests();
        verifyNoMoreInteractions(testService);
    }

    @Test
    public void happy_path_when_fetch_user_test() throws Exception {
        int testId = 1;
        int userId = 1;
        int testOptionId = 1;

        // edu.sdp.project.pethospital.entity.Test test = new
        // edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1, date2, 100);
        List<edu.sdp.project.pethospital.entity.Test> retTest = new ArrayList<>();
        Date date1 = new Date(10000);
        Date date2 = new Date(10000);
        retTest.add(new edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1, date2, 100));
        retTest.add(new edu.sdp.project.pethospital.entity.Test(2, 1, 1, date1, date2, 100));
        when(testService.getAllTestsByUser(userId)).thenReturn(retTest);
        mockMvc.perform(put("/admin/test/exams/user/1", "/exam/user/1")).andExpect(status().isOk());
        verify(testService).getAllTestsByUser(testId);
        verifyNoMoreInteractions(testService);
    }

    @Test
    public void null_path_when_fetch_user_test() throws Exception {
        int testId = 1;
        int userId = 1;
        int testOptionId = 1;

        // edu.sdp.project.pethospital.entity.Test test = new
        // edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1, date2, 100);
        List<edu.sdp.project.pethospital.entity.Test> retTest = new ArrayList<>();
        Date date1 = new Date(10000);
        Date date2 = new Date(10000);
        retTest.add(new edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1, date2, 100));
        retTest.add(new edu.sdp.project.pethospital.entity.Test(2, 1, 1, date1, date2, 100));
        when(testService.getAllTestsByUser(userId)).thenReturn(null);
        mockMvc.perform(put("/admin/test/exams/user/1", "/exam/user/1")).andExpect(status().isOk());
        verify(testService).getAllTestsByUser(testId);
        verifyNoMoreInteractions(testService);
    }

}
