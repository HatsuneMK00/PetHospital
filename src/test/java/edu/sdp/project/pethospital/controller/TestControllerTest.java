package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Fee;
import edu.sdp.project.pethospital.entity.Medicine;
import edu.sdp.project.pethospital.entity.Question;
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

    @Test
    public void happy_path_when_fetch_option_test() throws Exception {
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
        when(testService.getAllTestsByOptionId(testOptionId)).thenReturn(retTest);
        mockMvc.perform(get("/admin/test/exams/option/1")).andExpect(status().isOk());

    }

    @Test
    public void null_path_when_fetch_option_test() throws Exception {
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
        when(testService.getAllTestsByOptionId(testOptionId)).thenReturn(null);
        mockMvc.perform(get("/admin/test/exams/option/1")).andExpect(status().isOk());

    }

    @Test
    public void happy_path_when_fetch_test() throws Exception {
        int testId = 1;
        int userId = 1;
        int testOptionId = 1;
        Date date1 = new Date(10000);
        Date date2 = new Date(10000);
        edu.sdp.project.pethospital.entity.Test test = new edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1,
                date2, 100);
        // List<edu.sdp.project.pethospital.entity.Test> retTest = new ArrayList<>();

        // retTest.add(new edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1,
        // date2, 100));
        // retTest.add(new edu.sdp.project.pethospital.entity.Test(2, 1, 1, date1,
        // date2, 100));
        when(testService.getTestById(userId)).thenReturn(test);
        mockMvc.perform(get("/admin/test/exams/1", "/exam/1")).andExpect(status().isOk());

    }

    @Test
    public void null_path_when_fetch_test() throws Exception {

        when(testService.getTestById(1)).thenReturn(null);
        mockMvc.perform(get("/admin/test/exams/1", "/exam/1")).andExpect(status().isOk());

    }

    @Test
    public void happy_path_when_update_test() throws Exception {
        int id = 1;
        Map param = new HashMap();
        Date date1 = new Date(10000);
        Date date2 = new Date(10000);
        String testId = "1";
        String userId = "1";
        String testOptionId = "1";
        Date beginDate = date1;
        Date endDate = date2;
        String score = "100";
        param.put("testId", testId);
        param.put("userId", userId);
        param.put("testOptionId", testOptionId);
        // param.put("beginDate", beginDate);
        // param.put("endDate", endDate);
        param.put("score", score);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);

        edu.sdp.project.pethospital.entity.Test test = new edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1,
                date2, 100);
        when(testService.getTestById(1)).thenReturn(test);
        mockMvc.perform(post("/admin/test/exams/1").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void null_path_when_update_test() throws Exception {
        int id = 1;
        Map param = new HashMap();
        Date date1 = new Date(10000);
        Date date2 = new Date(10000);
        String testId = "1";
        String userId = "1";
        String testOptionId = "1";
        Date beginDate = date1;
        Date endDate = date2;
        String score = "100";
        param.put("testId", testId);
        param.put("userId", userId);
        param.put("testOptionId", testOptionId);
        // param.put("beginDate", beginDate);
        // param.put("endDate", endDate);
        param.put("score", score);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);

        edu.sdp.project.pethospital.entity.Test test = new edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1,
                date2, 100);
        when(testService.getTestById(1)).thenReturn(null);
        mockMvc.perform(post("/admin/test/exams/1").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void happy_path_when_delete_test() throws Exception {
        when(testService.checkId(1)).thenReturn(true);
        when(testService.deleteTestById(1)).thenReturn(1);
        mockMvc.perform(delete("/admin/test/exams").param("testId", "1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
        verify(testService).deleteTestById(1);
    }

    @Test
    public void null_path_when_delete_test() throws Exception {
        when(testService.checkId(1)).thenReturn(false);
        when(testService.deleteTestById(1)).thenReturn(-1);
        mockMvc.perform(delete("/admin/test/exams").param("testId", "1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));

    }

    @Test
    public void happy_path_when_start_test() throws Exception {
        when(optionUserService.checkId(1, 1)).thenReturn(true);
        when(testService.checkTest(1, 1)).thenReturn(false);
        when(testService.checkDate(1)).thenReturn(true);
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        when(testService.getNewTest(1, 1)).thenReturn(retQues);
        when(testService.addTest(1, 1)).thenReturn(1);

        mockMvc.perform(put("/exam/1/user/1")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(200));

    }

    @Test
    public void _404_path_when_start_test() throws Exception {
        when(optionUserService.checkId(1, 1)).thenReturn(false);
        when(testService.checkTest(1, 1)).thenReturn(false);
        when(testService.checkDate(1)).thenReturn(true);
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        when(testService.getNewTest(1, 1)).thenReturn(retQues);
        when(testService.addTest(1, 1)).thenReturn(1);

        mockMvc.perform(put("/exam/1/user/1")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(404));

    }

    @Test
    public void _400_path_when_start_test() throws Exception {
        when(optionUserService.checkId(1, 1)).thenReturn(true);
        when(testService.checkTest(1, 1)).thenReturn(true);
        when(testService.checkDate(1)).thenReturn(true);
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        when(testService.getNewTest(1, 1)).thenReturn(retQues);
        when(testService.addTest(1, 1)).thenReturn(1);

        mockMvc.perform(put("/exam/1/user/1")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(400));

    }

    @Test
    public void _500_path_when_start_test() throws Exception {
        when(optionUserService.checkId(1, 1)).thenReturn(true);
        when(testService.checkTest(1, 1)).thenReturn(false);
        when(testService.checkDate(1)).thenReturn(false);
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        when(testService.getNewTest(1, 1)).thenReturn(retQues);
        when(testService.addTest(1, 1)).thenReturn(1);

        mockMvc.perform(put("/exam/1/user/1")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(500));

    }

    @Test
    public void _501_path_when_start_test() throws Exception {
        when(optionUserService.checkId(1, 1)).thenReturn(true);
        when(testService.checkTest(1, 1)).thenReturn(false);
        when(testService.checkDate(1)).thenReturn(true);
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        when(testService.getNewTest(1, 1)).thenReturn(null);
        when(testService.addTest(1, 1)).thenReturn(1);

        mockMvc.perform(put("/exam/1/user/1")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(501));

    }

    @Test
    public void _502_path_when_start_test() throws Exception {
        when(optionUserService.checkId(1, 1)).thenReturn(true);
        when(testService.checkTest(1, 1)).thenReturn(false);
        when(testService.checkDate(1)).thenReturn(true);
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        when(testService.getNewTest(1, 1)).thenReturn(retQues);
        when(testService.addTest(1, 1)).thenReturn(-1);

        mockMvc.perform(put("/exam/1/user/1")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(502));

    }
    // @Test
    // public void happy_path_when_end_test() throws Exception{
    // Date date1 = new Date(10000);
    // Date date2 = new Date(10000);
    // edu.sdp.project.pethospital.entity.Test test = new
    // edu.sdp.project.pethospital.entity.Test(1, 1, 1, date1, date2, 100);
    // when(testService.getTestById(1)).thenReturn(test);

    // List<Integer> quesIds = new ArrayList<>();
    // quesIds.add(1);
    // quesIds.add(2);
    // quesIds.add(3);
    // List<String> answers = new ArrayList<>();
    // answers.add("an1");
    // answers.add("an2");
    // answers.add("e");
    // when(testService.getScore(quesIds, answers, 1)).thenReturn(100);
    // when(testService.changeTest(test)).thenReturn(1);

    // LinkedMultiValueMap<String, List> requestParams = new
    // LinkedMultiValueMap<>();

    // requestParams.add("quesIds", quesIds);
    // requestParams.add("answers", answers);
    // mockMvc.perform(post("/exam/1/submit").queryParams(requestParams))
    // .andExpect(status().isOk())
    // .andExpect(jsonPath("$.status").value(200));
    // }

}
