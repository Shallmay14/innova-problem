package com.innova.validation.domain.service.model;

import com.innova.validation.kernel.message.PasswordValidateMessage;

public class PasswordValidResultConstant {

  /** 驗證成功 */
  public static final com.innova.validation.domain.model.PasswordValidResult PASS =
      new com.innova.validation.domain.model.PasswordValidResult(
          true, PasswordValidateMessage.PASS.getCode(), PasswordValidateMessage.PASS.getText());

  /** 驗證失敗，長度不符 */
  public static final com.innova.validation.domain.model.PasswordValidResult ERR_LEN =
      new com.innova.validation.domain.model.PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_LEN.getCode(),
          PasswordValidateMessage.ERR_LEN.getText());

  /** 驗證失敗，字元不符 */
  public static final com.innova.validation.domain.model.PasswordValidResult ERR_CHAR =
      new com.innova.validation.domain.model.PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_CHAR.getCode(),
          PasswordValidateMessage.ERR_CHAR.getText());

  /** 驗證失敗，有重複字串 */
  public static final com.innova.validation.domain.model.PasswordValidResult ERR_SAME =
      new com.innova.validation.domain.model.PasswordValidResult(
          false,
          PasswordValidateMessage.ERR_SAME.getCode(),
          PasswordValidateMessage.ERR_SAME.getText());
}
