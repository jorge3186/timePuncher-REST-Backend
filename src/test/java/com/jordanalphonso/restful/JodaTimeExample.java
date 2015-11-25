package com.jordanalphonso.restful;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;


public class JodaTimeExample {

	@Test
    public void ready() {
        DateTimeFormatter dateFormat = DateTimeFormat
                .forPattern("G,C,Y,x,w,e,E,Y,D,M,d,a,K,h,H,k,m,s,S,z,Z");

        String dob = "2002-01-15";
        LocalTime localTime = new LocalTime();
        LocalDate localDate = new LocalDate();
        DateTime dateTime = new DateTime();
        LocalDateTime localDateTime = new LocalDateTime();
        DateTimeZone dateTimeZone = DateTimeZone.getDefault();

        System.out
                .println("dateFormatr : " + dateFormat.print(localDateTime));
        System.out.println("LocalTime : " + localTime.toString());
        System.out.println("localDate : " + localDate.toString());
        System.out.println("dateTime : " + dateTime.toString());
        System.out.println("localDateTime : " + localDateTime.toString());
        System.out.println("DateTimeZone : " + dateTimeZone.toString());
        System.out.println("Year Difference : "
                + Years.yearsBetween(DateTime.parse(dob), dateTime).getYears());
        System.out.println("Month Difference : "
                + Months.monthsBetween(DateTime.parse(dob), dateTime)
                        .getMonths());
        
        System.out.println("Formatted DATE: " + localDate.toString() + " " + localTime.toString().substring(0, 8));
    }
}