package io.proj3ct.telegramBot;

import java.util.Calendar;

public class Helper {
    static public Boolean isCurrentWeekOdd(){
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        System.out.println(week);
        Boolean isOddWeek = week % 2 != 0;
        System.out.println("Is current week odd? " + isOddWeek);
        return isOddWeek;
    }
}
