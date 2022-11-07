package com.innova.validation.restapi.model.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordVin {

  /** 輸入密碼 */
  private String password;
}
