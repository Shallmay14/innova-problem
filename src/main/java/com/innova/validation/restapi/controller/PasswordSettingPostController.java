package com.innova.validation.restapi.controller;

import com.innova.validation.application.PasswordSettingApplication;
import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.restapi.model.in.PasswordVin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/password-setting/post")
public class PasswordSettingPostController {

  @Autowired private PasswordSettingApplication passwordSettingApplication;

  @PostMapping(value = "/password-validation")
  public PasswordValidResult passwordValidation(@RequestParam("password") String password) {
    return passwordSettingApplication.validPassword(password);
  }
}
