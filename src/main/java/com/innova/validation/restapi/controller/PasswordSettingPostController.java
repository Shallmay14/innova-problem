package com.innova.validation.restapi.controller;

import com.innova.validation.application.PasswordSettingApplication;
import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.restapi.model.out.PasswordValidOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/password-setting/post")
public class PasswordSettingPostController {

  @Autowired
  private PasswordSettingApplication passwordSettingApplication;

  @PostMapping(value = "/password-validation")
  public PasswordValidResult passwordValidation(@RequestBody String password) {
    return passwordSettingApplication.validPassword(password);
  }
}
