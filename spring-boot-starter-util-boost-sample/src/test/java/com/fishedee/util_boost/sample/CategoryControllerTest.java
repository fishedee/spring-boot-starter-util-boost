package com.fishedee.util_boost.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishedee.util_boost.sample.api.CategoryController;
import com.fishedee.util_boost.sample.api.CategoryDTO;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class CategoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test()throws Exception{
        String add1 = testAdd(new CategoryDTO("fish","a"));
        String add2 = testAdd(new CategoryDTO("cat","b"));
    }

    public String testGet(String id)throws Exception{
        Map<Object,Object> map = new HashMap<>();
        map.put("id",id);
        MockHttpServletRequestBuilder requestBuilder  = MockMvcRequestBuilders.get("/category/get")
                .param("data",objectMapper.writeValueAsString(map))
                .contentType(MediaType.APPLICATION_JSON);
        String str = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        return str;
    }

    public String testMod(String id,CategoryDTO categoryDTO)throws Exception{
        String origin = objectMapper.writeValueAsString(categoryDTO);
        String target = "{\"id\":"+id+","+origin.substring(1,origin.length()-1)+"}";
        MockHttpServletRequestBuilder requestBuilder  = MockMvcRequestBuilders.post("/category/mod")
                .content(target)
                .contentType(MediaType.APPLICATION_JSON);
        String str = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        return str;
    }

    public String testDel(String id)throws  Exception{
        Map<Object,Object> map = new HashMap<>();
        map.put("id",id);
        MockHttpServletRequestBuilder requestBuilder  = MockMvcRequestBuilders.post("/category/del")
                .content(objectMapper.writeValueAsString(map))
                .contentType(MediaType.APPLICATION_JSON);
        String str = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        return str;
    }

    public String testAdd(CategoryDTO categoryDTO)throws  Exception{
        MockHttpServletRequestBuilder requestBuilder  = MockMvcRequestBuilders.post("/category/add")
                .content(objectMapper.writeValueAsString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON);
        String str = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        return str;
    }
}
