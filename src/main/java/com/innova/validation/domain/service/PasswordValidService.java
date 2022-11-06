package com.innova.validation.domain.service;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.kernel.message.PasswordValidateMessage;
import com.innova.validation.kernel.utils.ValidateUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidService {

  public PasswordValidResult validPassword(String password) {

    if (!ValidateUtils.isLowercaseLetterOrNumber(password))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_CHAR.getCode(),
          PasswordValidateMessage.ERR_CHAR.getText());

    if (!ValidateUtils.containLength(password, 5, 7))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_LEN.getCode(),
          PasswordValidateMessage.ERR_LEN.getText());

    if (!ValidateUtils.rule(password))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_LEN.getCode(),
          PasswordValidateMessage.ERR_LEN.getText());

    return new PasswordValidResult(
        true, PasswordValidateMessage.PASS.getCode(), PasswordValidateMessage.PASS.getText());
  }
}
