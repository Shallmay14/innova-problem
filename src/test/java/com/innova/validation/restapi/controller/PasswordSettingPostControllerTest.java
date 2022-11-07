package com.innova.validation.restapi.controller;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.kernel.message.PasswordValidateMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

import static org.assertj.core.api.Assertions.assertThat;
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

  @ParameterizedTest
  @ValueSource(strings = {"", "a", "abcd", "abcdefghijklm"})
  void givenValidationURIWithPostAndFormData_whenMockMVC_thenResponseErrLen(String text) throws Exception {
    this.mockMvc
            .perform(
                    MockMvcRequestBuilders.post("/password-setting/post/password-validation")
                            .param("password", text))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msgCode").value(PasswordValidateMessage.ERR_LEN.getCode()));
  }

  @ParameterizedTest
  @ValueSource(strings = {"AAAFVA", "az123#", "#####", "英諾瓦英諾瓦"})
  void givenValidationURIWithPostAndFormData_whenMockMVC_thenResponseErrChar(String text) throws Exception {
    this.mockMvc
            .perform(
                    MockMvcRequestBuilders.post("/password-setting/post/password-validation")
                            .param("password", text))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msgCode").value(PasswordValidateMessage.ERR_CHAR.getCode()));
  }

  @ParameterizedTest
  @ValueSource(strings = {"abcdabcd", "12341234", "123452345", "abcdeff"})
  void givenValidationURIWithPostAndFormData_whenMockMVC_thenResponseErrSame(String text) throws Exception {
    this.mockMvc
            .perform(
                    MockMvcRequestBuilders.post("/password-setting/post/password-validation")
                            .param("password", text))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msgCode").value(PasswordValidateMessage.ERR_SAME.getCode()));
  }

  @ParameterizedTest
  @ValueSource(strings = {"abcdabc", "abcdefghijkl", "az123", "1234123", "12345"})
  void givenValidationURIWithPostAndFormData_whenMockMVC_thenResponsePass(String text) throws Exception {
    this.mockMvc
            .perform(
                    MockMvcRequestBuilders.post("/password-setting/post/password-validation")
                            .param("password", text))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msgCode").value(PasswordValidateMessage.PASS.getCode()));
  }
}
