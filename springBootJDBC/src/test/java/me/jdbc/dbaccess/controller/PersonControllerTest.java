package me.jdbc.dbaccess.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jdbc.dbaccess.dto.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    
    @Test
    @DisplayName("create person 테스트")
    public void createPersonControllerTest() throws Exception {
    	Person person = new Person();
    	
    	person.setName("Ryu");
    	person.setWorkAt("GS SHOP");
    	person.setSpeciality("SW");
    	person.setAge(29);
    	person.setPhoneNumber("01000000000");
    	
    	String personJson = objectMapper.writeValueAsString(person); // Java Object -> Json String
    	
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/person/insert_person")
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON)
    			.content(personJson))
    							.andExpect(status().isOk())
    							.andDo(print())
    							.andReturn();
    			
    	System.out.println("=================== Result ======================");
    	
    	System.out.println(mvcResult.getResponse().getContentAsString());
    	
    	assertThat(personJson).isEqualTo(mvcResult.getResponse().getContentAsString());
    }


    
    
//    @Test
//    @DisplayName("Select one person 테스트")
//    public void readOnePersonControllerTest

}