package com.innova.validation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 密碼驗證結果 <br/>
 * {@link #validResult} <br/>
 * {@link #msgCode} <br/>
 * {@link #message}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordValidResult {

    /** 驗證結果 */
    private Boolean validResult;

    /** 驗證訊息代號 */
    private String msgCode;

    /** 驗證訊息 */
    private String message;
}
