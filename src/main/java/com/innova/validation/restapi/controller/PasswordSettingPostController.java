package com.innova.validation.restapi.controller;

import com.innova.validation.application.PasswordSettingApplication;
import com.innova.validation.domain.model.PasswordValidResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 密碼設定 RestController <br>
 * inputValid <br>
 * outputEscape
 */
@RestController
@RequestMapping(value = "/password-setting/post")
public class PasswordSettingPostController {

  @Autowired private PasswordSettingApplication passwordSettingApplication;

  /**
   * 密碼驗證 RESTAPI
   *
   * @param password 輸入密碼
   * @return 驗證結果 {@link PasswordValidResult}
   */
  @PostMapping(value = "/password-validation")
  public PasswordValidResult passwordValidation(@RequestParam("password") String password) {
    return passwordSettingApplication.validPassword(password);
  }
}
