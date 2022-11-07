package com.innova.validation.domain.service;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.kernel.message.PasswordValidateMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PasswordValidServiceTest {

  private static final PasswordValidResult pass =
      new PasswordValidResult(
          true, PasswordValidateMessage.PASS.getCode(), PasswordValidateMessage.PASS.getText());
  private static final PasswordValidResult errLen =
      new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_LEN.getCode(),
          PasswordValidateMessage.ERR_LEN.getText());
  private static final PasswordValidResult errChar =
      new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_CHAR.getCode(),
          PasswordValidateMessage.ERR_CHAR.getText());
  private static final PasswordValidResult errSame =
      new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_SAME.getCode(),
          PasswordValidateMessage.ERR_SAME.getText());
  @Autowired PasswordValidService passwordValidService;

  @ParameterizedTest
  @ValueSource(strings = {"", "a", "abcd", "abcdefghijklm"})
  void errLenTest(String text) {
    PasswordValidResult validRes = passwordValidService.validPassword(text);
    assertThat(validRes).usingRecursiveComparison().isEqualTo(errLen);
  }

  @ParameterizedTest
  @ValueSource(strings = {"AAAFVA", "az123#", "#####", "英諾瓦英諾瓦"})
  void errCharTest(String text) {
    PasswordValidResult validRes = passwordValidService.validPassword(text);
    assertThat(validRes).usingRecursiveComparison().isEqualTo(errChar);
  }

  @ParameterizedTest
  @ValueSource(strings = {"abcdabcd", "12341234", "123452345", "abcdeff"})
  void errSameTest(String text) {
    PasswordValidResult validRes = passwordValidService.validPassword(text);
    assertThat(validRes).usingRecursiveComparison().isEqualTo(errSame);
  }

  @ParameterizedTest
  @ValueSource(strings = {"abcdabc", "abcdefghijkl", "az123", "1234123", "12345"})
  void passTest(String text) {
    PasswordValidResult validRes = passwordValidService.validPassword(text);
    assertThat(validRes).usingRecursiveComparison().isEqualTo(pass);
  }
}
