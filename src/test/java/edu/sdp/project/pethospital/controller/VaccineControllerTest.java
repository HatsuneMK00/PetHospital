package edu.sdp.project.pethospital.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.entity.Vaccine;
import edu.sdp.project.pethospital.service.UserService;
import edu.sdp.project.pethospital.service.VaccineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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

public class VaccineControllerTest {
    private VaccineService vaccineService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vaccineService = mock(VaccineService.class);
        mockMvc = standaloneSetup(new VaccineController(vaccineService)).build();
    }

    @Test
    public void happy_path_when_fetch_all_vaccine() throws Exception {
        List<Vaccine> retVaccine = new ArrayList<>();
        retVaccine.add(new Vaccine(100, "vaccine1", "descriptiontest1"));
        retVaccine.add(new Vaccine(101, "vaccine2", "descriptiontest2"));

        when(vaccineService.getAllVaccines()).thenReturn(retVaccine);
        mockMvc.perform(get("/admin/structure/vaccine")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].vacId").value(100))
                .andExpect(jsonPath("$.responseMap.result[1].vacId").value(101));
        verify(vaccineService).getAllVaccines();
        verifyNoMoreInteractions(vaccineService);

    }

    @Test
    public void null_path_when_fatch_all_vaccine() throws Exception {
        when(vaccineService.getAllVaccines()).thenReturn(null);
        mockMvc.perform(get("/admin/structure/vaccine")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(vaccineService).getAllVaccines();
    }

    @Test
    public void happy_path_when_fetch_vaccine() throws Exception {
        int vacId = 101;
        Vaccine vaccine = new Vaccine(101, "vaccine1", "descriptiontest1");
        when(vaccineService.getVaccine(vacId)).thenReturn(vaccine);
        mockMvc.perform(put("/admin/structure/vaccine/{vacId}", 101)).andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMap.result.vacId").value(101));
        verify(vaccineService).getVaccine(vacId);
        verifyNoMoreInteractions(vaccineService);
    }

    @Test
    void null_path_when_fetch_vaccine() throws Exception {
        when(vaccineService.getVaccine(101)).thenReturn(null);
        mockMvc.perform(put("/admin/structure/vaccine/{vacId}", 101)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(vaccineService).getVaccine(101);
    }
    @Test
    void happy_path_when_add_vaccine() throws Exception {
        Vaccine vaccine = new Vaccine(101, "vaccine1", "descriptiontest1");
        when(vaccineService.addVaccine(vaccine)).thenReturn(1);

        mockMvc.perform(get("/admin/structure/vaccine")).andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(200));

        verify(vaccineService).getAllVaccines();
        verifyNoMoreInteractions(vaccineService);
    }
    @Test
    void null_path_when_add_vaccine() throws Exception{
        Vaccine vaccine = new Vaccine();
        when(vaccineService.addVaccine(vaccine)).thenReturn(-1);

        mockMvc.perform(get("/admin/structure/vaccine")).andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(200));

        verify(vaccineService).getAllVaccines();
        verifyNoMoreInteractions(vaccineService);

    }

    @Test
    void happy_path_when_delete_vaccine() throws Exception{

        int vacId = 101;
        when(vaccineService.checkId(vacId)).thenReturn(true);
        when(vaccineService.deleteVaccine(vacId)).thenReturn(1);
        mockMvc.perform(delete("/admin/structure/vaccine").param("vacId", "101")).andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(200));
        verify(vaccineService).deleteVaccine(vacId);

    }
    @Test
    void null_path_when_delete_vaccine() throws Exception{
        int vacId = 1;
        when(vaccineService.checkId(vacId)).thenReturn(false);
        when(vaccineService.deleteVaccine(vacId)).thenReturn(-1);
        mockMvc.perform(delete("/admin/structure/vaccine").param("vacId","1")).andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(404));

        verify(vaccineService).checkId(vacId);
    }

}
