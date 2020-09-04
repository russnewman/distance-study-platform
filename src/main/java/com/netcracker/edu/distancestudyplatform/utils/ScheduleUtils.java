package com.netcracker.edu.distancestudyplatform.utils;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.mappers.ScheduleMapper;
import com.netcracker.edu.distancestudyplatform.model.Schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class ScheduleUtils {
    private static final int curYear = 2020;

    public static List<ScheduleDto> castSchedulesToDTO(List<Schedule> schedules){
        return schedules
                .stream()
                .map(ScheduleMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public static String getTodayName(){
        return new SimpleDateFormat("EEEE", Locale.ENGLISH)
                .format(
                        Calendar.getInstance()
                                .getTime()
                                .getTime()
                );
    }

    public static String getNextDayName(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        return new SimpleDateFormat("EEEE", Locale.ENGLISH)
                .format(
                        c.getTime().getTime()
                );
    }


    public static String getTomorrowName(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return new SimpleDateFormat("EEEE", Locale.ENGLISH)
                .format(
                        cal
                        .getTime()
                        .getTime()
                );
    }



    public static Boolean getWeekIsOdd(){
        LocalDate startDate = LocalDate.of(curYear, 9, 1)
                .with(
                        TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)
                );
        LocalDate curDate = LocalDate.now();
        if(curDate.isBefore(startDate)) return true;
        long diff = ChronoUnit.DAYS.between(startDate, curDate);
        if((diff/7 + 1) % 2 == 0){
            return diff % 7 == 0;
        }
        else return diff % 7 != 0;
    }
}
