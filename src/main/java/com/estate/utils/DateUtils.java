package com.estate.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String convertDateToString(Date date) {
        if (date == null) return null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        return dateFormat.format(date);
    }

    public static String convertFullDateToString(Date date) {
        if (date == null) return null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
        return dateFormat.format(date);
    }
}
