package com.innova.validation.domain.service;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.kernel.message.PasswordValidateMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PasswordValidServiceTest {

  private static final PasswordValidResult pass =
      new PasswordValidResult(
          true, PasswordValidateMessage.PASS.getCode(), PasswordValidateMessage.PASS.getText());
  @Autowired PasswordValidService passwordValidService;

  @Test
  public void passTest1() {
    PasswordValidResult validRes = passwordValidService.validPassword("abcdefghij");
      assertThat(validRes).usingRecursiveComparison().isEqualTo(pass);
  }
}
