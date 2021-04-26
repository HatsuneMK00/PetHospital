package edu.sdp.project.pethospital.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import edu.sdp.project.pethospital.entity.Question;
import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.entity.Vaccine;
import edu.sdp.project.pethospital.service.QuestionService;
import edu.sdp.project.pethospital.service.UserService;
import edu.sdp.project.pethospital.service.VaccineService;
import jdk.jshell.spi.ExecutionControl.ExecutionControlException;
import edu.sdp.project.pethospital.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

public class QuestionControllerTest {
    private QuestionService questionService;
    private ImageService imageService;
    private MockMvc mockMvc;
    private static ObjectMapper mapper;
    private MultipartFile mockFile;

    @BeforeEach
    void setUp() {
        mockFile = mock(MultipartFile.class);
        questionService = mock(QuestionService.class);
        imageService = mock(ImageService.class);
        mockMvc = standaloneSetup(new QuestionController(questionService, imageService)).build();
    }

    @Test
    public void happy_path_when_fetch_all_question() throws Exception {
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));

        when(questionService.getAllQues()).thenReturn(retQues);
        mockMvc.perform(get("/admin/test/question")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].quesId").value(100))
                .andExpect(jsonPath("$.responseMap.result[1].quesId").value(101));
        verify(questionService).getAllQues();
        verifyNoMoreInteractions(questionService);
    }

    @Test
    public void null_path_when_fetch_all_question() throws Exception {
        when(questionService.getAllQues()).thenReturn(null);
        mockMvc.perform(get("/admin/test/question")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(questionService).getAllQues();
        verifyNoMoreInteractions(questionService);
    }

    @Test
    public void happy_path_when_fetch_question() throws Exception {
        int quesId = 101;
        Question question = new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1");
        when(questionService.getQuestionById(quesId)).thenReturn(question);
        mockMvc.perform(put("/admin/test/question/{quesId}", 101)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
        verify(questionService).getQuestionById(quesId);
        verifyNoMoreInteractions(questionService);
    }

    @Test
    void null_path_when_fetch_question() throws Exception {
        when(questionService.getQuestionById(101)).thenReturn(null);
        mockMvc.perform(put("/admin/test/question/{quesId}", 101)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    public void happy_path_when_fetch_question_by_tag() throws Exception {
        String tag = "tag1";
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        when(questionService.getAllQuesByTag(tag)).thenReturn(retQues);
        mockMvc.perform(put("/admin/test/question/tag/{tag}", tag)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
        verify(questionService).getAllQuesByTag(tag);
        verifyNoMoreInteractions(questionService);
    }

    @Test
    void null_path_when_fetch_question_by_tag() throws Exception {
        when(questionService.getAllQuesByTag("tag1")).thenReturn(null);
        mockMvc.perform(put("/admin/test/question/tag/{tag}", "tag1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));

    }

    @Test
    public void happy_path_when_fetch_question_by_search() throws Exception {
        String search = "qid:{101}";
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        when(questionService.getQuesBySearch(search)).thenReturn(retQues);
        mockMvc.perform(get("/admin/test/question/search").param("searchParam", search)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
        verify(questionService).getQuesBySearch(search);
        verifyNoMoreInteractions(questionService);
    }

    @Test
    void null_path_when_fetch_question_by_search() throws Exception {
        String search = "qid:{101}";
        List<Question> retQues = new ArrayList<>();
        retQues.add(new Question(100, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        retQues.add(new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1"));
        when(questionService.getQuesBySearch(search)).thenReturn(null);
        mockMvc.perform(get("/admin/test/question/search").param("searchParam", search)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));

    }

    @Test
    void happy_path_when_add_question() throws Exception {
        Question question = new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1");
        Map param = new HashMap();
        String quesId = "101";
        String type = "type1";
        String descrip = "descrip1";
        String answer = "answer1";
        String score = "5";
        String image = "image1";
        String tag = "tag1";

        param.put("quesId", quesId);
        param.put("type", type);
        param.put("descrip", descrip);
        param.put("answer", answer);
        param.put("score", score);
        param.put("image", image);
        param.put("tag", tag);

        when(questionService.addQuestion(question)).thenReturn(1);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);
        mockMvc.perform(put("/admin/test/question").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

    @Test
    void null_path_when_add_question() throws Exception {
        Question question = new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1");
        Map param = new HashMap();
        String quesId = "101";
        String type = "type1";
        String descrip = "descrip1";
        String answer = "answer1";
        String score = "5";
        String image = "image1";
        String tag = "tag1";

        param.put("quesId", quesId);
        // param.put("type",type);
        // param.put("descrip",descrip);
        param.put("answer", answer);
        param.put("score", score);
        param.put("image", image);
        param.put("tag", tag);

        when(questionService.addQuestion(question)).thenReturn(-1);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);
        mockMvc.perform(put("/admin/test/question").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void error_path_when_add_question() throws Exception {
        Question question = new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1");
        Map param = new HashMap();
        String quesId = "101";
        String type = "type1";
        String descrip = "descrip1";
        String answer = "answer1";
        String score = "5";
        String image = "image1";
        String tag = "tag1";

        param.put("quesId", quesId);
        param.put("type", type);
        param.put("descrip", descrip);
        param.put("answer", answer);
        param.put("score", score);
        param.put("image", image);
        param.put("tag", tag);

        when(questionService.addQuestion(question)).thenReturn(-1);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);
        mockMvc.perform(put("/admin/test/question").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(500));
    }

    @Test
    public void happy_path_when_upload_image() throws Exception {
        int quesId = 101;
        String imageUrl = "url";
        when(questionService.checkId(quesId)).thenReturn(false);
        when(imageService.storeFile(mockFile)).thenReturn("cool");
        when(questionService.setImageUrl(quesId, imageUrl)).thenReturn("url");
        MockMultipartFile firstFile = new MockMultipartFile("image", "filename.txt", "text/plain",
                "some xml".getBytes());

        mockMvc.perform(multipart("/admin/test/question/101/image").file(firstFile)).andExpect(status().isOk());
    }

    @Test
    public void null_path_when_upload_image() throws Exception {
        int quesId = 101;
        String imageUrl = "url";
        when(questionService.checkId(quesId)).thenReturn(false);
        when(imageService.storeFile(mockFile)).thenReturn("cool");
        when(questionService.setImageUrl(quesId, imageUrl)).thenReturn("url");
        MockMultipartFile firstFile = new MockMultipartFile("image", "filename.txt", "text/plain",
                "some xml".getBytes());

        mockMvc.perform(multipart("/admin/test/question/101/image").file(firstFile)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void happy_path_when_change_question() throws Exception {
        Question question = new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1");
        Map param = new HashMap();
        String quesId = "101";
        String type = "type1";
        String descrip = "descrip1";
        String answer = "answer1";
        String score = "5";
        String image = "image1";
        String tag = "tag1";

        param.put("quesId", quesId);
        param.put("type", type);
        param.put("descrip", descrip);
        param.put("answer", answer);
        param.put("score", score);
        param.put("image", image);
        param.put("tag", tag);

        when(questionService.getQuestionById(101)).thenReturn(question);
        when(questionService.updateQuestion(question)).thenReturn(1);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);
        mockMvc.perform(post("/admin/test/question/101").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

    @Test
    void null_path_when_change_question() throws Exception {
        Question question = new Question(101, "type1", "descrip1", "answer1", 5, "image1", "tag1");
        Map param = new HashMap();
        String quesId = "101";
        String type = "type1";
        String descrip = "descrip1";
        String answer = "answer1";
        String score = "5";
        String image = "image1";
        String tag = "tag1";

        param.put("quesId", quesId);
        param.put("type", type);
        param.put("descrip", descrip);
        param.put("answer", answer);
        param.put("score", score);
        param.put("image", image);
        param.put("tag", tag);

        when(questionService.getQuestionById(101)).thenReturn(null);
        when(questionService.updateQuestion(question)).thenReturn(-1);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);
        mockMvc.perform(post("/admin/test/question/101").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(404));

    }

    @Test
    void happy_path_when_delete_question() throws Exception {
        int quesId = 101;
        when(questionService.checkId(quesId)).thenReturn(true);
        when(questionService.deleteQuestionById(quesId)).thenReturn(1);
        mockMvc.perform(delete("/admin/test/question").param("quesId", "101")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
        verify(questionService).deleteQuestionById(quesId);
    }

    @Test
    void null_path_when_delete_section() throws Exception {
        int quesId = 101;
        when(questionService.checkId(quesId)).thenReturn(false);
        when(questionService.deleteQuestionById(quesId)).thenReturn(-1);
        mockMvc.perform(delete("/admin/test/question").param("quesId", "101")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
    }

}
