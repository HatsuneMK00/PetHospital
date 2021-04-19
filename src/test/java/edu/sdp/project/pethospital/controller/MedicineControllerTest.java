package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.Medicine;
import edu.sdp.project.pethospital.entity.ResponseMsg;
import edu.sdp.project.pethospital.service.MedicineService;
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
public class MedicineControllerTest {
    private MedicineService medicineService;
    private MockMvc mockMvc;
    private static ObjectMapper mapper;
    @BeforeEach
    void setUp() {
        medicineService = mock(MedicineService.class);
        mockMvc = standaloneSetup(new MedicineController(medicineService)).build();
        mapper = new ObjectMapper();
 
    }

    @Test
    public void happy_path_when_fetch_all_medicine() throws Exception {
        List<Medicine> retMedicine = new ArrayList<>();
        retMedicine.add(new Medicine(100, "Medicine1", "descriptiontest1"));
        retMedicine.add(new Medicine(101, "Medicine2", "descriptiontest2"));

        when(medicineService.getAllMedicines()).thenReturn(retMedicine);
        mockMvc.perform(get("/admin/structure/medicine")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].medId").value(100))
                .andExpect(jsonPath("$.responseMap.result[1].medId").value(101));
        verify(medicineService).getAllMedicines();
        verifyNoMoreInteractions(medicineService);
    }

    @Test
    public void null_path_when_fetch_all_medicine() throws Exception {
        when(medicineService.getAllMedicines()).thenReturn(null);
        mockMvc.perform(get("/admin/structure/medicine")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(medicineService).getAllMedicines();
    }

    @Test
    public void happy_path_when_fetch_medicine() throws Exception {
        int medId = 101;
        Medicine medicine = new Medicine(101, "vaccine1", "descriptiontest1");
        when(medicineService.getMedicine(medId)).thenReturn(medicine);
        mockMvc.perform(put("/admin/structure/medicine/{medId}", 101)).andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMap.result.medId").value(101));
        verify(medicineService).getMedicine(medId);
        verifyNoMoreInteractions(medicineService);
    }

    @Test
    void null_path_when_fetch_vaccine() throws Exception {
        when(medicineService.getMedicine(101)).thenReturn(null);
        mockMvc.perform(put("/admin/structure/medicine/{medId}", 101)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(medicineService).getMedicine(101);
    }

    @Test
    void happy_path_when_add_vaccine() throws Exception {
        Medicine medicine = new Medicine(101, "med1", "descriptiontest1");
        when(medicineService.addMedicine(ArgumentMatchers.any())).thenReturn(1);
        String json = mapper.writeValueAsString(medicine);
        mockMvc.perform(put("/admin/structure/medicine").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
            .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(200));

        // verify(medicineService).addMedicine(medicine);
        // verifyNoMoreInteractions(medicineService);
    }
}