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

public class SectionControllerTest {
    private SectionService sectionService;
    private ImageService imageService;
    private MockMvc mockMvc;
    private static ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        sectionService = mock(SectionService.class);
        imageService = mock(ImageService.class);
        mockMvc = standaloneSetup(new SectionController(sectionService, imageService)).build();
        mapper = new ObjectMapper();

    }

    @Test
    public void happy_path_when_fetch_all_section() throws Exception {
        List<Section> retSection = new ArrayList<>();
        retSection.add(new Section(100, "Sec1", "rec1", "doc1", "assis1", "url1"));
        retSection.add(new Section(101, "Sec1", "rec1", "doc1", "assis1", "url1"));

        when(sectionService.getAllSections()).thenReturn(retSection);
        mockMvc.perform(get("/admin/structure/section")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].sectionId").value(100))
                .andExpect(jsonPath("$.responseMap.result[1].sectionId").value(101));
        verify(sectionService).getAllSections();
        verifyNoMoreInteractions(sectionService);
    }

    @Test
    public void null_path_when_fetch_all_section() throws Exception {
        when(sectionService.getAllSections()).thenReturn(null);
        mockMvc.perform(get("/admin/structure/section")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(sectionService).getAllSections();
        verifyNoMoreInteractions(sectionService);
    }

    @Test
    public void happy_path_when_fetch_section() throws Exception {
        int sectionId = 101;
        Section section = new Section(101, "Sec1", "rec1", "doc1", "assis1", "url1");
        when(sectionService.getSection(sectionId)).thenReturn(section);
        mockMvc.perform(put("/admin/structure/section/{sectionId}", 101)).andExpect(status().isOk());
        verify(sectionService).getSection(sectionId);
        verifyNoMoreInteractions(sectionService);
    }

    @Test
    void null_path_when_fetch_section() throws Exception {
        int sectionId = 101;

        when(sectionService.getSection(sectionId)).thenReturn(null);
        mockMvc.perform(put("/admin/structure/section/{sectionId}", 101)).andExpect(status().isOk());

    }

    @Test
    void happy_path_when_add_section() throws Exception {
        Section section = new Section(100, "Sec1", "rec1", "doc1", "assis1", "url1");

        when(sectionService.addSection(section)).thenReturn(1);
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("sectionName", "Sec1");
        requestParams.add("recDescrip", "rec1");
        requestParams.add("docDescrip", "doc1");
        requestParams.add("assisDescrip", "assis1");
        requestParams.add("sectionImageUrl", "url1");

        mockMvc.perform(put("/admin/structure/section").params(requestParams)).andExpect(status().isOk());

    }

    @Test
    void null_path_when_add_section() throws Exception {
        Section section = new Section(100, "Sec1", "rec1", "doc1", "assis1", "url1");

        when(sectionService.addSection(section)).thenReturn(-1);
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();

        requestParams.add("sectionName", "Sec1");
        requestParams.add("recDescrip", "rec1");
        requestParams.add("docDescrip", "doc1");
        requestParams.add("assisDescrip", "assis1");
        requestParams.add("sectionImageUrl", "url1");

        mockMvc.perform(put("/admin/structure/section").params(requestParams)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));

    }

    @Test
    void happy_path_when_delete_section() throws Exception {
        int sectionId = 101;
        when(sectionService.checkId(sectionId)).thenReturn(true);
        when(sectionService.deleteSection(sectionId)).thenReturn(1);
        mockMvc.perform(delete("/admin/structure/section").param("sectionId", "101")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
        verify(sectionService).deleteSection(sectionId);
    }

    @Test
    void null_path_when_delete_section() throws Exception {
        int sectionId = 101;
        when(sectionService.checkId(sectionId)).thenReturn(false);
        when(sectionService.deleteSection(sectionId)).thenReturn(-1);
        mockMvc.perform(delete("/admin/structure/section").param("sectionId", "101")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void happy_path_when_update_section() throws Exception {
        int sectionId = 101;
        Map param = new HashMap();
        String sectionName = "sec1";
        String recDescrip = "rec1";
        String docDescrip = "doc1";
        String assisDescrip = "assis1";
        String sectionImageUrl = "url1";
        param.put("sectionName", sectionName);
        param.put("recDescrip", recDescrip);
        param.put("docDescrip", docDescrip);
        param.put("assisDescrip", assisDescrip);
        param.put("sectionImageUrl", sectionImageUrl);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);

        Section section = new Section(100, "Sec1", "rec1", "doc1", "assis1", "url1");
        when(sectionService.getSection(sectionId)).thenReturn(section);
        mockMvc.perform(post("/admin/structure/section/101").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void null_path_when_update_section() throws Exception {
        int sectionId = 101;
        Map param = new HashMap();
        String sectionName = "sec1";
        String recDescrip = "rec1";
        String docDescrip = "doc1";
        String assisDescrip = "assis1";
        String sectionImageUrl = "url1";
        param.put("sectionName", sectionName);
        param.put("recDescrip", recDescrip);
        param.put("docDescrip", docDescrip);
        param.put("assisDescrip", assisDescrip);
        param.put("sectionImageUrl", sectionImageUrl);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);

        Section section = new Section(100, "Sec1", "rec1", "doc1", "assis1", "url1");
        when(sectionService.getSection(sectionId)).thenReturn(null);
        mockMvc.perform(post("/admin/structure/section/101").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(404));
    }

}
