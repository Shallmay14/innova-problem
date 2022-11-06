package com.innova.validation.application;

import com.innova.validation.domain.model.PasswordValidResult;
import com.innova.validation.domain.service.PasswordValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// @Transactional
public class PasswordSettingApplication {
  @Autowired PasswordValidService passwordValidService;

  public PasswordValidResult validPassword(String password) {
    return passwordValidService.validPassword(password);
  }
}
