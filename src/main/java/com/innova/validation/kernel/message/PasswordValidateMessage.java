package com.innova.validation.kernel.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PasswordValidateMessage {

    /** 全部 */
    PASS("SPV0000","Pass"),
    /** 新增 */
    ERR_CHAR("EPV0001", "Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each."),
    /** 新增 */
    ERR_LEN("EPV0002", "Must be between 5 and 12 characters in length."),
    /** 新增 */
    ERR_RULE("EPV0003", "Must not contain any sequence of characters immediately followed by the same sequence.");

    /** 訊息代碼. */
    private final String code;
    /** 訊息文字 */
    private final String text;

}
