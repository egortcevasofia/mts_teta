package com.example.demo.controller;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void userTable() throws Exception {
        ArrayList<UserDto> userList = new ArrayList<>();
        userList.add(new UserDto());
        userList.add(new UserDto());
        when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(get("/user/findUser"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attribute("users", userList));
    }


    @Test
    void userForm() throws Exception {
        User actualUser = new User(1L, "Vasia", "123", new HashSet<>());
        when(userService.findUserByUsername(any())).thenReturn(actualUser);
        mockMvc.perform(
                get("/user"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", actualUser));
    }

    @Test
    public void userFormException() throws Exception {
        when(userService.findUserByUsername(any())).thenThrow(NotFoundException.class);
        mockMvc.perform(
                get("/user"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NotFoundException.class));
    }

    @Test
    void userFormAdmin() throws Exception {
        User actualUser = new User(1L, "Vasia", "123", new HashSet<>());
        when(userService.findUserById(any())).thenReturn(actualUser);
        mockMvc.perform(
                get("/user/admin/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("user_form"))
                .andExpect(model().attribute("user", actualUser));
    }


    @Test
    void updateUserForm() throws Exception {
        UserDto actualUser = new UserDto(1L, "Vasia", "123");

        mockMvc.perform(post("/user")
                .with(csrf())
                .flashAttr("user", actualUser))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/user/findUser"));
    }

    @Test
    void updateUserFormHasErrors() throws Exception {
        UserDto actualUser = new UserDto(1L, "Vasia", "123", new HashSet<>());

        mockMvc.perform(post("/user")
                .with(csrf())
                .flashAttr("user", actualUser))
                .andExpect(model().attributeHasFieldErrors("user"))
                .andExpect(view().name("user_form"));
    }


    @Test
    void registrationForm() throws Exception {

        mockMvc.perform(get("/user/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration_form"));
    }


    @Test
    void submitUserForm() throws Exception {
        UserDto actualUser = new UserDto(1L, "Vasia", "123");
        mockMvc.perform(post("/user/registration")
                .with(csrf())
                .flashAttr("user", actualUser))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("requestFormUserRegistration"))
                .andExpect(status().isOk());
    }


    @Test
    void deleteUser() throws Exception {
        doNothing().when(userService).deleteById(anyInt());
        mockMvc.perform(delete("/user/{id}", 1L)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/findUser"));
        Mockito.verify(userService, Mockito.times(1)).deleteById(1L);

    }
}
