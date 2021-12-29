package me.song.transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PracticeApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SampleRepository sampleRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Sample 객체 create 테스트")
    public void createSampleTest() throws Exception{
        Sample me = Sample.builder()
                .id(null)
                .name("Belle")
                .age(29)
                .build();

        String json = objectMapper.writeValueAsString(me); // Object -> Json
        System.out.println(json);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/sample")
                                                                    .contentType(MediaType.APPLICATION_JSON)
                                                                    .accept(MediaType.APPLICATION_JSON)
                                                                    .content(json))
                                     .andExpect(status().isOk())
//                                     .andDo(print())
                                     .andReturn();

        System.out.println("==============================");
        System.out.println(mvcResult.getResponse().getContentAsString()); // 호출한 api(/sample)의 리턴값. create 된 cnt 수를 리턴

        assertThat("1").isEqualTo(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Select Test")
    public void selectTest() throws Exception {
        String me = "Song";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/sample/Son"))
                        .andExpect(status().is5xxServerError())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(model().size(1))
//                        .andExpect(model().attribute("age", "29")
//                        .andDo(print())
                        .andReturn();




    }
}
