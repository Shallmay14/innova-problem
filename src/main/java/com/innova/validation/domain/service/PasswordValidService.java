package com.innova.validation.domain.service;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.kernel.message.PasswordValidateMessage;
import com.innova.validation.kernel.utils.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/** 密碼驗證 DomainService */
@Service
public class PasswordValidService {

  /**
   * 密碼驗證
   *
   * @param password 輸入密碼
   * @return 驗證結果 {@link PasswordValidResult}
   */
  public PasswordValidResult validPassword(String password) {

    // 1. 密碼長度驗證
    if (!ValidateUtils.isBetweenLength(password, 5, 12))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_LEN.getCode(),
          PasswordValidateMessage.ERR_LEN.getText());

    // 2. 密碼字元驗證
    if (!ValidateUtils.isLowercaseLetterOrNumber(password))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_CHAR.getCode(),
          PasswordValidateMessage.ERR_CHAR.getText());

    // 3. 密碼相似性驗證
    if (isContainSameSequence(password))
      return new PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_SAME.getCode(),
          PasswordValidateMessage.ERR_SAME.getText());

    // 4. 密碼驗證完成
    return new PasswordValidResult(
        true, PasswordValidateMessage.PASS.getCode(), PasswordValidateMessage.PASS.getText());
  }

  /**
   * 密碼相似性驗證
   * @param text 輸入密碼
   * @return 是否有相同字串相連
   */
  private boolean isContainSameSequence(String text) {

    // 不足兩字不比較
    if(StringUtils.isEmpty(text) || text.length() <= 1)
      return false;

    // 從第二字開始判斷，e.g. abcd 從 b 開始判斷
    for (int index = 1; index < text.length(); index++) {

      // 取得後續文字長度，e.g. abcd -> bcd = 3
      int lastLength = text.length() - index;
      // 取得需要比較的最大範圍，e.g. {a,bcd} -> 1, {ab,cd} -> 2
      int maxRange = index <= lastLength ? index : lastLength;

      // 從最大範圍逐一比較，{ab,cd} > {b,c}
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

  /**
   * 取得段落文字
   * @param text 輸入字串
   * @param startIdx 段落起點
   * @param endIdx 段落終點
   * @return 字串擷取段落文字
   */
  private String getRangeText(String text, int startIdx, int endIdx) {
    // 留做 Cache 空間
    return text.substring(startIdx, endIdx);
  }
}
