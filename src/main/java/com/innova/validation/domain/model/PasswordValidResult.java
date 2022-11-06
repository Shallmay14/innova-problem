package com.innova.validation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordValidResult {

    /** 驗證結果 */
    private Boolean validResult;

    /** 驗證訊息代號 */
    private String msgCode;

    /** 驗證訊息代號 */
    private String message;
}
