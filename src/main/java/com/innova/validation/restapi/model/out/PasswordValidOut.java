package com.innova.validation.restapi.model.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordValidOut {

  /** 驗證結果 */
  private Boolean validResult;

  /** 驗證訊息代號 */
  private String msgCode;

  /** 驗證訊息代號 */
  private String message;
}
