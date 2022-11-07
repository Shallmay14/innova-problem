package com.innova.validation.restapi.controller;

import com.innova.validation.kernel.message.PasswordValidateMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class PasswordSettingPostControllerTest {

  private static final String CONTENT_TYPE = "application/json";
  @Autowired private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void givenWac_whenServletContext_thenItProvidesRestController() {
    ServletContext servletContext = webApplicationContext.getServletContext();

    assertNotNull(servletContext);
    assertTrue(servletContext instanceof MockServletContext);
    assertNotNull(webApplicationContext.getBean("passwordSettingPostController"));
  }

  @Test
  void givenActuatorURI_whenMockMVC_thenResponseOK() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/actuator"))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void givenValidationURIWithPostAndFormData_whenMockMVC_thenResponseOK() throws Exception {
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post("/password-setting/post/password-validation")
                .param("password", "abcdabc")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8"))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.msgCode").value(PasswordValidateMessage.PASS.getCode()));
  }
}
