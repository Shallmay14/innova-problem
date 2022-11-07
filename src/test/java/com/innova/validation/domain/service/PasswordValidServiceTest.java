package com.innova.validation.domain.service;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.domain.service.model.PasswordValidResultConstant;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PasswordValidServiceTest {
  @Autowired PasswordValidService passwordValidService;

  /**
   * 預期驗證失敗，長度不符
   *
   * @param text 驗證密碼
   */
  @ParameterizedTest
  @ValueSource(strings = {"", "a", "abcd", "abcdefghijklm"})
  void errLenTest(String text) {
    PasswordValidResult validRes = passwordValidService.validPassword(text);
    assertThat(validRes).usingRecursiveComparison().isEqualTo(PasswordValidResultConstant.ERR_LEN);
  }

  /**
   * 預期驗證失敗，字元不符
   *
   * @param text 驗證密碼
   */
  @ParameterizedTest
  @ValueSource(strings = {"AAAFVA", "az123#", "#####", "英諾瓦英諾瓦"})
  void errCharTest(String text) {
    PasswordValidResult validRes = passwordValidService.validPassword(text);
    assertThat(validRes).usingRecursiveComparison().isEqualTo(PasswordValidResultConstant.ERR_CHAR);
  }

  /**
   * 預期驗證失敗，有重複字串
   *
   * @param text 驗證密碼
   */
  @ParameterizedTest
  @ValueSource(strings = {"abcdabcd", "12341234", "123452345", "abcdeff"})
  void errSameTest(String text) {
    PasswordValidResult validRes = passwordValidService.validPassword(text);
    assertThat(validRes).usingRecursiveComparison().isEqualTo(PasswordValidResultConstant.ERR_SAME);
  }

  /**
   * 預期驗證成功
   *
   * @param text 驗證密碼
   */
  @ParameterizedTest
  @ValueSource(strings = {"abcdabc", "abcdefghijkl", "az123", "1234123", "12345"})
  void passTest(String text) {
    PasswordValidResult validRes = passwordValidService.validPassword(text);
    assertThat(validRes).usingRecursiveComparison().isEqualTo(PasswordValidResultConstant.PASS);
  }
}
