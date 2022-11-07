package com.innova.validation.domain.service;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.kernel.message.PasswordValidateMessage;
import com.innova.validation.kernel.utils.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidService {

  public PasswordValidResult validPassword(String password) {

    if (!ValidateUtils.isBetweenLength(password, 5, 12))
      return new PasswordValidResult(
              false,
              PasswordValidateMessage.ERR_LEN.getCode(),
              PasswordValidateMessage.ERR_LEN.getText());

    if (!ValidateUtils.isLowercaseLetterOrNumber(password))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_CHAR.getCode(),
          PasswordValidateMessage.ERR_CHAR.getText());

    if (isContainSameSequence(password))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_SAME.getCode(),
          PasswordValidateMessage.ERR_SAME.getText());

    return new PasswordValidResult(
        true, PasswordValidateMessage.PASS.getCode(), PasswordValidateMessage.PASS.getText());
  }

  private boolean isContainSameSequence(String text) {

    for (int index = 1; index < text.length(); index++) {
      int lastLength = text.length() - index;
      int maxRange = index <= lastLength ? index : lastLength;

      for (int range = maxRange; range > 0; range--) {
        int startIdx = index - range;
        int endIdx = index + range;

        String subText1 = getRangeText(text, startIdx, index);
        String subText2 = getRangeText(text, index, endIdx);

        if (StringUtils.equals(subText1, subText2)) return true;
      }
    }

    return false;
  }

  private String getRangeText(String text, int startIdx, int endIdx) {
    return text.substring(startIdx, endIdx);
  }
}
