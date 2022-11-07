package com.innova.validation.restapi.controller;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.domain.service.model.PasswordValidResultConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class PasswordSettingPostControllerTest {

  public static final String PASSWORD_VALIDATION_URI = "/password-setting/post/password-validation";
  private static final String CONTENT_TYPE = "application/json";
  @Autowired private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  /**
   * 統一建立 mockMvc
   *
   * @throws Exception
   */
  @BeforeEach
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  /**
   * 服務啟動測試
   *
   * @throws Exception
   */
  @Test
  void givenActuatorURI_whenMockMVC_thenResponseOK() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/actuator"))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  /**
   * 預期驗證失敗，長度不符
   *
   * @param text 驗證密碼
   * @throws Exception
   */
  @ParameterizedTest
  @ValueSource(strings = {"", "a", "abcd", "abcdefghijklm"})
  void givenValidationURIWithPostAndFormData_whenMockMVC_thenResponseErrLen(String text)
      throws Exception {
    passwordValidMockMvc(text, PasswordValidResultConstant.ERR_LEN);
  }

  /**
   * 預期驗證失敗，字元不符
   *
   * @param text 驗證密碼
   * @throws Exception
   */
  @ParameterizedTest
  @ValueSource(strings = {"AAAFVA", "az123#", "#####", "英諾瓦英諾瓦"})
  void givenValidationURIWithPostAndFormData_whenMockMVC_thenResponseErrChar(String text)
      throws Exception {
    passwordValidMockMvc(text, PasswordValidResultConstant.ERR_CHAR);
  }

  /**
   * 預期驗證失敗，有重複字串
   *
   * @param text 驗證密碼
   * @throws Exception
   */
  @ParameterizedTest
  @ValueSource(strings = {"abcdabcd", "12341234", "123452345", "abcdeff"})
  void givenValidationURIWithPostAndFormData_whenMockMVC_thenResponseErrSame(String text)
      throws Exception {
    passwordValidMockMvc(text, PasswordValidResultConstant.ERR_SAME);
  }

  /**
   * 預期驗證成功
   *
   * @param text 驗證密碼
   * @throws Exception
   */
  @ParameterizedTest
  @ValueSource(strings = {"abcdabc", "abcdefghijkl", "az123", "1234123", "12345"})
  void givenValidationURIWithPostAndFormData_whenMockMVC_thenResponsePass(String text)
      throws Exception {
    passwordValidMockMvc(text, PasswordValidResultConstant.PASS);
  }

  /**
   * 共用密碼驗證 mockMvc
   *
   * @param text 驗證密碼
   * @param expectRes 預期結果 {@link PasswordValidResult}
   * @throws Exception
   */
  private void passwordValidMockMvc(String text, PasswordValidResult expectRes) throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATION_URI).param("password", text))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
        .andExpect(MockMvcResultMatchers.jsonPath("$").value(expectRes));
  }
}
