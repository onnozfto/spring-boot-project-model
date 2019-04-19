package com.self.project.util;

import org.slf4j.helpers.MessageFormatter;

/**
 * @Description
 * @Author will
 * @Date 2019/4/8 0008 上午 10:20
 */
public class Tools {

  public static String format(String pattern, Object... arguments) {
    return MessageFormatter.arrayFormat(pattern, arguments).getMessage();
  }
}
