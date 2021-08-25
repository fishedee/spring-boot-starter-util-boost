package com.fishedee.util_boost.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishedee.util_boost.sample.api.User;
import com.fishedee.util_boost.sample.api.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

//RANDOM_PORT才能打开Security进行测试
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*
    //FIXME AuthenticationCredentialsNotFoundException，总是报这个错误
    @Test
    public void testCheckAuthority()throws  Exception{
        MockHttpServletRequestBuilder requestBuilder  = MockMvcRequestBuilders.get("/user/checkAuthority")
                .contentType(MediaType.APPLICATION_JSON);
        String str = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("{}",str);
        assertTrue(str.contains("没有权限访问"));
    }
     */

    @Test
    public void testCheckJsonDecimal()throws  Exception{
        MockHttpServletRequestBuilder requestBuilder  = MockMvcRequestBuilders.get("/user/checkJsonDecimal")
                .contentType(MediaType.APPLICATION_JSON);
        String str = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(str,"{\"code\":0,\"msg\":\"\",\"data\":\"123\"}");
    }

    @Test
    public void testCheckValidation()throws  Exception{
        MockHttpServletRequestBuilder requestBuilder  = MockMvcRequestBuilders.get("/user/checkValidation")
                .contentType(MediaType.APPLICATION_JSON);
        String str = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(str,"{\"code\":1,\"msg\":\"[name:must not be blank]\",\"data\":null}");
    }

    @Test
    public void testCheckUser()throws  Exception{
        UserDTO user = new UserDTO();
        user.setAge(123);
        user.setName("fish");
        MockHttpServletRequestBuilder requestBuilder  = MockMvcRequestBuilders.get("/user/checkUser")
                .param("data",objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON);
        String str = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(str,"{\"code\":0,\"msg\":\"\",\"data\":{\"name\":\"fish\",\"age\":123}}");
    }
}
