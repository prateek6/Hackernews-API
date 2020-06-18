package com.demo.hackernews.commons.utils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static final String ddmmmyyyyhhmm = "dd-MMM-yyyy HH:mm";
    public static final String DATE_EMPTY_STRING = "";

    public static LocalDateTime getLocalDateTimeByEpoch(Long epochValue){
        return Instant.ofEpochSecond(epochValue).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate getLocalDateByEpoch(Long epochValue){
        return Instant.ofEpochSecond(epochValue)
                .atZone(ZoneId.systemDefault()).toLocalDate();
        //return  new Timestamp(epochValue).toLocalDateTime().toLocalDate();
    }

    public static long diffInYears(long startTime){
        return Period.between( getLocalDateByEpoch(startTime), LocalDate.now()).getYears();
    }


    public static String getHumanReadableDate(LocalDateTime date) {
        if(date==null)return DATE_EMPTY_STRING;
        return date.format(DateTimeFormatter.ofPattern(ddmmmyyyyhhmm));
    }

    public static String getHumanReadableDateByEpoch(Long epochValue){
        return getHumanReadableDate(getLocalDateTimeByEpoch(epochValue));
    }
}
