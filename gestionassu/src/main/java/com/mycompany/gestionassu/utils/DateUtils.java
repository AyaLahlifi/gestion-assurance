/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ayala
 */
public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static String formatDate(Date date) {
        if (date == null) return "";
        return DATE_FORMAT.format(date);
    }
    
    public static String formatDateTime(Date date) {
        if (date == null) return "";
        return DATE_TIME_FORMAT.format(date);
    }
    
    public static Date parseDate(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Date parseDateTime(String dateTimeStr) {
        try {
            return DATE_TIME_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Date addDays(Date date, int days) {
        if (date == null) return null;
        return new Date(date.getTime() + ((long) days * 24 * 60 * 60 * 1000));
    } 
}
