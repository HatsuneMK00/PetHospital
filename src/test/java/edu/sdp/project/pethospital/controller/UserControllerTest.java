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
    @Test
    public void null_path_when_get_all_users() throws Exception {
        when(userService.traverseAllUsers()).thenReturn(null);
        mockMvc.perform(get("/admin/user/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(userService).traverseAllUsers();
    }
    @Test
    public void happy_path_when_get_all_admins() throws Exception {
        List<User> retAdmins= new ArrayList<>();
        retAdmins.add(new User(
                1,
                "account1",
                "name1",
                "123456",
                "admin"
        ));
        retAdmins.add(new User(
                2,
                "account2",
                "name2",
                "123456",
                "admin"
        ));
        when(userService.traverseAllAdmins()).thenReturn(retAdmins);
        mockMvc.perform(get("/admin/user/getAdmins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMap.result[0].role").value("admin"))
                .andExpect(jsonPath("$.responseMap.result[1].role").value("admin"));
        verify(userService).traverseAllAdmins();
        verifyNoMoreInteractions(userService);
    }
    @Test
    public void null_path_when_get_all_admins() throws Exception {
        when(userService.traverseAllAdmins()).thenReturn(null);
        mockMvc.perform(get("/admin/user/getAdmins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(userService).traverseAllAdmins();
    }
    @Test
    public void happy_path_when_get_normal_users() throws Exception {
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
                "user"
        ));
        when(userService.traverseNormalUsers()).thenReturn(retUsers);
        mockMvc.perform(get("/admin/user/getNormal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.responseMap.result[0].role").value("user"))
                .andExpect(jsonPath("$.responseMap.result[1].role").value("user"));
        verify(userService).traverseNormalUsers();
        verifyNoMoreInteractions(userService);
    }
    @Test
    public void null_path_when_get_normal_users() throws Exception {
        when(userService.traverseNormalUsers()).thenReturn(null);
        mockMvc.perform(get("/admin/user/getNormal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(404));
        verify(userService).traverseNormalUsers();
    }
    @Test
    public void happy_path_when_get_user() throws Exception {
        int userId=1;
        User user = new User(1,"account","name","123456","user");
        when(userService.getUserById(userId)).thenReturn(user);
        mockMvc.perform(get("/admin/user/getById").param("userId","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMap.result.userId").value(1));
        verify(userService).getUserById(userId);
        verifyNoMoreInteractions(userService);
    }
}
