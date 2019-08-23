package com.github.wxiaoqi.security.common.web;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AceDateFormat extends DateFormat {

  private DateFormat dateFormat;
  private SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd");
  private SimpleDateFormat format2 = new SimpleDateFormat("yyy-MM-dd HH:mm");
  private SimpleDateFormat format3 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

  public AceDateFormat(DateFormat dateFormat) {
    this.dateFormat = dateFormat;
    calendar = Calendar.getInstance();
  }


  @Override
  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
    return dateFormat.format(date, toAppendTo, fieldPosition);
  }


  // 主要还是装饰这个方法
  @Override
  public Date parse(String source) throws ParseException {

    Date date = null;

    try {
      // 先按我的规则来
      date = format1.parse(source);
    } catch (Exception e) {
      try {
        // 先按我的规则来
        date = format2.parse(source);
      } catch (Exception e1) {
        try {
          // 先按我的规则来
          date = format3.parse(source);
        } catch (Exception e2) {
          // 不行，那就按原先的规则吧
          date = dateFormat.parse(source);
        }
      }
    }

    return date;
  }


  @Override
  public Date parse(String source, ParsePosition pos) {

    Date date = null;

    try {
      // 先按我的规则来
      date = format1.parse(source, pos);
    } catch (Exception e) {
      try {
        // 先按我的规则来
        date = format2.parse(source, pos);
      } catch (Exception e1) {
        try {
          // 先按我的规则来
          date = format3.parse(source, pos);
        } catch (Exception e2) {
          // 不行，那就按原先的规则吧
          date = dateFormat.parse(source, pos);
        }
      }
    }

    return date;
  }


  // 这里装饰clone方法的原因是因为clone方法在jackson中也有用到
  @Override
  public Object clone() {
    Object format = dateFormat.clone();
    return new AceDateFormat((DateFormat) format);
  }


}
