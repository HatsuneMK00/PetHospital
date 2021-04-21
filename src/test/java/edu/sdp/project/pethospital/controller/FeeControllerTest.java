package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Fee;
import edu.sdp.project.pethospital.entity.Medicine;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.FeeService;
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

public class FeeControllerTest {
    private FeeService feeService;
    private MockMvc mockMvc;
    private static ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        feeService = mock(FeeService.class);
        mockMvc = standaloneSetup(new FeeController(feeService)).build();
        mapper = new ObjectMapper();

    }

    @Test
    public void happy_path_when_fetch_all_fee() throws Exception {
        List<Fee> retFee = new ArrayList<>();
        retFee.add(new Fee(100, "Medicine1", 100.00, "descriptiontest1"));
        retFee.add(new Fee(101, "Medicine2", 200.00, "descriptiontest2"));

        when(feeService.getAllFees()).thenReturn(retFee);
        mockMvc.perform(get("/admin/structure/fee")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].feeId").value(100))
                .andExpect(jsonPath("$.responseMap.result[1].feeId").value(101));
        verify(feeService).getAllFees();
        verifyNoMoreInteractions(feeService);
    }

    @Test
    public void null_path_when_fetch_all_fee() throws Exception {
        when(feeService.getAllFees()).thenReturn(null);
        mockMvc.perform(get("/admin/structure/fee")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(feeService).getAllFees();
    }

    @Test
    public void happy_path_when_fetch_fee() throws Exception {
        int feeId = 101;
        Fee fee = new Fee(101, "feename", 100.00, "descriptiontest1");
        when(feeService.getFee(feeId)).thenReturn(fee);
        mockMvc.perform(put("/admin/structure/fee/{feeId}", 101)).andExpect(status().isOk());
        verify(feeService).getFee(feeId);
        verifyNoMoreInteractions(feeService);
    }

    @Test
    void null_path_when_fetch_fee() throws Exception {
        when(feeService.getFee(101)).thenReturn(null);
        mockMvc.perform(put("/admin/structure/fee/{feeId}", 101)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));

    }

    @Test
    void happy_path_when_add_fee() throws Exception {
        Fee fee = new Fee(101, "fee1", 100.00, "des1");

        when(feeService.addFee(fee)).thenReturn(1);
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("feeName", "fee1");
        requestParams.add("feeDescrip", "des1");
        requestParams.add("feePrice", "100.00");
        mockMvc.perform(put("/admin/structure/fee").params(requestParams)).andExpect(status().isOk());

    }

    @Test
    void null_path_when_add_fee() throws Exception {
        Fee fee = new Fee(101, "fee", 100.00, "des1");

        when(feeService.addFee(fee)).thenReturn(-1);
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();

        requestParams.add("feeName", "fee1");
        requestParams.add("feeDescrip", "des1");
        requestParams.add("feePrice", "100.00");
        mockMvc.perform(put("/admin/structure/fee").params(requestParams)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void happy_path_when_delete_fee() throws Exception {
        int feeId = 101;
        when(feeService.checkId(feeId)).thenReturn(true);
        when(feeService.deleteFee(feeId)).thenReturn(1);
        mockMvc.perform(delete("/admin/structure/fee").param("feeId", "101")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
        verify(feeService).deleteFee(feeId);
    }

    @Test
    void null_path_when_delete_fee() throws Exception {
        int feeId = 101;
        when(feeService.checkId(feeId)).thenReturn(false);
        when(feeService.deleteFee(feeId)).thenReturn(-1);
        mockMvc.perform(delete("/admin/structure/fee").param("feeId", "101")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void happy_path_when_update_fee() throws Exception {
        int feeId = 101;
        Map param = new HashMap();
        String feeName = "fee1";
        String feeDescrip = "des1";
        double feePrice = 100.00;
        param.put("feeName", feeName);
        param.put("feeDescrip", feeDescrip);
        param.put("feePrice", feePrice);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);

        Fee fee = new Fee(101, "fee1", 100.00, "descriptiontest1");
        when(feeService.getFee(feeId)).thenReturn(fee);
        mockMvc.perform(post("/admin/structure/fee/101").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void null_path_when_update_medicine() throws Exception {
        int feeId = 101;
        Map param = new HashMap();
        String feeName = "fee1";
        String feeDescrip = "des1";
        double feePrice = 100.00;
        param.put("feeName", feeName);
        param.put("feeDescrip", feeDescrip);
        param.put("feePrice", feePrice);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);

        Fee fee = new Fee(101, "fee1", 100.00, "descriptiontest1");
        when(feeService.getFee(feeId)).thenReturn(null);
        mockMvc.perform(post("/admin/structure/fee/101").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value(404));

    }

}