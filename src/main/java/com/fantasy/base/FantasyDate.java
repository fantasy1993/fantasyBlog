package com.fantasy.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fantasy on 2017/7/26.
 */
public class FantasyDate {

    /*获取当前时间格式yyyy-mm-dd*/
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
