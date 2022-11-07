package com.innova.validation.domain.service;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.kernel.message.PasswordValidateMessage;
import com.innova.validation.kernel.utils.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidService {

  public PasswordValidResult validPassword(String password) {

    if (!ValidateUtils.isLowercaseLetterOrNumber(password))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_CHAR.getCode(),
          PasswordValidateMessage.ERR_CHAR.getText());

    if (!ValidateUtils.isBetweenLength(password, 5, 7))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_LEN.getCode(),
          PasswordValidateMessage.ERR_LEN.getText());

    if (!isContainSameSequence(password))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_SAME.getCode(),
          PasswordValidateMessage.ERR_SAME.getText());

    return new PasswordValidResult(
        true, PasswordValidateMessage.PASS.getCode(), PasswordValidateMessage.PASS.getText());
  }

  private boolean isContainSameSequence(String text) {

    for (int index = 1; index < text.length(); index++) {
      int lastLength = text.length()-index;
      int maxLength = index <= lastLength ? index : lastLength;

      for (int range = index; range < index + maxLength; range++) {
        int startIdx = index - range;
        int endIdx = index + range;

        if (StringUtils.equals(
            getRangeText(text, startIdx, index), getRangeText(text, index, endIdx))) return true;
      }
    }

    return false;
  }

  private String getRangeText(String text, int startIdx, int endIdx) {
    return text.substring(startIdx,endIdx);
  }
}
