package com.innova.validation.kernel.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidateUtils {

  public static final String IS_NUMBER_REGEX = "^\\d+$";
  public static final String IS_LOWERCASE_LETTER_OR_NUMBER_REGEX = "^[a-z0-9]+$";

  /** 是否為數字 */
  public static boolean isNumber(String text) {
    return text.matches(IS_NUMBER_REGEX);
  }

  /** 是否為英數字 */
  public static boolean isLowercaseLetterOrNumber(String text) {
    return text.matches(IS_LOWERCASE_LETTER_OR_NUMBER_REGEX);
  }

  /** 是否符合長度限制 */
  public static boolean isBetweenLength(String text, int minLen, int maxLen) {
    return text.length() >= minLen && text.length() <= maxLen;
  }
}
