package edu.sdp.project.pethospital.controller;

import edu.sdp.project.pethospital.entity.User;
import edu.sdp.project.pethospital.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

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

public class UserControllerTest {
    private UserService userService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        userService = mock(UserService.class);
        mockMvc =standaloneSetup(new UserController(userService)).build();
    }

    @Test
    public void happy_path_when_get_all_users() throws Exception {
        List<User> retUsers = new ArrayList<>();
        retUsers.add(new User(
                1,
                "account1",
                "name1",
                "123456",
                "user"
        ));
        retUsers.add(new User(
                2,
                "account2",
                "name2",
                "123456",
                "admin"
        ));
        when(userService.traverseAllUsers()).thenReturn(retUsers);
        mockMvc.perform(get("/admin/user/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].userId").value(1))
                .andExpect(jsonPath("$.responseMap.result[1].userId").value(2));
        verify(userService).traverseAllUsers();
        verifyNoMoreInteractions(userService);
    }
}
