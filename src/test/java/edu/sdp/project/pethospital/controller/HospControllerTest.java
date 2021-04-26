package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Fee;
import edu.sdp.project.pethospital.entity.HospRecord;
import edu.sdp.project.pethospital.entity.Medicine;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.FeeService;
import edu.sdp.project.pethospital.service.HospService;
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

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

public class HospControllerTest {
    private HospService hospService;
    private MockMvc mockMvc;
    private static ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        hospService = mock(HospService.class);
        mockMvc = standaloneSetup(new HospController(hospService)).build();
        mapper = new ObjectMapper();

    }

    @Test
    public void happy_path_when_fetch_all_hosp_record() throws Exception {
        List<HospRecord> rethosp = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        java.sql.Timestamp date1 = new java.sql.Timestamp(10000);
        java.sql.Timestamp date2 = new java.sql.Timestamp(10001);
        rethosp.add(new HospRecord(100, "name1", "disease1", date1, date2));
        rethosp.add(new HospRecord(101, "name2", "disease2", date1, date2));

        when(hospService.getAllHospRecord()).thenReturn(rethosp);
        mockMvc.perform(get("/admin/structure/hospitalize")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].hosId").value(100))
                .andExpect(jsonPath("$.responseMap.result[1].hosId").value(101));
        verify(hospService).getAllHospRecord();
        verifyNoMoreInteractions(hospService);
    }

    @Test
    public void null_path_when_fetch_all_hosp_record() throws Exception {
        when(hospService.getAllHospRecord()).thenReturn(null);
        mockMvc.perform(get("/admin/structure/hospitalize")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(hospService).getAllHospRecord();
        verifyNoMoreInteractions(hospService);
    }

    @Test
    public void happy_path_when_fetch_hosp_record() throws Exception {
        int hosId = 101;
        java.sql.Timestamp date1 = new java.sql.Timestamp(10000);
        HospRecord hospRecord = new HospRecord(101, "name1", "disease1", date1, date1);
        when(hospService.getHospRecord(hosId)).thenReturn(hospRecord);
        mockMvc.perform(put("/admin/structure/hospitalize/{hosId}", 101)).andExpect(status().isOk());
        verify(hospService).getHospRecord(hosId);
        verifyNoMoreInteractions(hospService);
    }

    @Test
    public void null_path_when_fetch_hosp_record() throws Exception {
        when(hospService.getHospRecord(101)).thenReturn(null);
        mockMvc.perform(put("/admin/structure/hospitalize/{hosId}", 101)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(hospService).getHospRecord(101);
        verifyNoMoreInteractions(hospService);
    }

    @Test
    void happy_path_when_add_hosp_record() throws Exception {
        java.sql.Timestamp date1 = new java.sql.Timestamp(10000);
        HospRecord hospRecord = new HospRecord(101, "name1", "disease1", date1, date1);
        when(hospService.addHospRecord("name1", "disease1", date1)).thenReturn(1);

        mockMvc.perform(get("/admin/structure/hospitalize")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));

        verify(hospService).getAllHospRecord();
        verifyNoMoreInteractions(hospService);
    }

    @Test
    void null_path_when_add_hosp_record() throws Exception {
        java.sql.Timestamp date1 = new java.sql.Timestamp(10000);
        when(hospService.addHospRecord("name1", "disease1", date1)).thenReturn(-1);

        mockMvc.perform(get("/admin/structure/hospitalize")).andExpect(status().isOk());

        verify(hospService).getAllHospRecord();
        verifyNoMoreInteractions(hospService);

    }

    @Test
    void happy_path_when_delete_hosp_record() throws Exception{

        int hosId = 101;
        when(hospService.checkId(hosId)).thenReturn(true);
        when(hospService.deleteHospRecord(hosId)).thenReturn(1);
        mockMvc.perform(delete("/admin/structure/hospitalize").param("hosId", "101")).andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(200));
        verify(hospService).deleteHospRecord(hosId);

    }
    @Test
    void null_path_when_delete_hosp_record() throws Exception{
        int hosId = 101;
        when(hospService.checkId(hosId)).thenReturn(false);
        when(hospService.deleteHospRecord(hosId)).thenReturn(-1);
        mockMvc.perform(delete("/admin/structure/hospitalize").param("hosId", "101")).andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(404));

        verify(hospService).checkId(hosId);
    }

    @Test
    void happy_path_when_update_hosp() throws Exception {
        int hosId = 101;
        Map param = new HashMap();
        String hosAnimalName = "name1";
        String disease = "disease1";
        java.sql.Timestamp date1 = new java.sql.Timestamp(10000);
        java.sql.Timestamp date2 = new java.sql.Timestamp(10001);
        String inDate = "2012-08-29 10:02:45";
        String outDate = "2012-08-29 10:02:46";
        param.put("hosAnimalName", hosAnimalName);
        param.put("disease", disease);
        param.put("inDate", inDate);
        param.put("outDate", outDate);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);

        HospRecord hospRecord = new HospRecord(101, "name1", "disease1", date1, date2);
        when(hospService.getHospRecord(hosId)).thenReturn(hospRecord);
        mockMvc.perform(post("/admin/structure/hospitalize/101").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void null_path_when_update_medicine() throws Exception {
        int hosId = 101;
        Map param = new HashMap();
        String hosAnimalName = "name1";
        String disease = "disease1";
        java.sql.Timestamp date1 = new java.sql.Timestamp(10000);
        java.sql.Timestamp date2 = new java.sql.Timestamp(10001);
        String inDate = "2012-08-29 10:02:45";
        String outDate = "2012-08-29 10:02:46";
        param.put("hosAnimalName", hosAnimalName);
        param.put("disease", disease);
        param.put("inDate", inDate);
        param.put("outDate", outDate);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);


        HospRecord hospRecord = new HospRecord(101, "name1", "disease1", date1, date2);
        when(hospService.getHospRecord(hosId)).thenReturn(null);
        mockMvc.perform(post("/admin/structure/hospitalize/101").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(404));

    }


}
