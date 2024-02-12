package com.famly;

import com.famly.controllers.UserController;
import com.famly.entity.User;
import com.famly.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
/**
 * @author harish.kumar-mbp
 * createdOn 05/02/24
 */

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    MockMvc mockMvc;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserController controller;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }
    User user1 = new User(1L, "","", "", "", 1L);
    User user2 = new User(2L, "","", "", "", 1L);
    User user3 = new User(3L, "","", "", "", 1L);

    @Test
    public void verifyUsersGet() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3));
        Mockito.when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/")
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.jsonPath("$").value(3));

    }

    @Test
    public void verifyUsersCreate() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3));
        User user1 = user2;
        ObjectMapper mapper = new ObjectMapper();
        String ob = mapper.writeValueAsString(user1);
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/users/").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ob);

        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(3));

    }
}
