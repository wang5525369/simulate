package com.itrus.contract.tools.date;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DateExtUtils extends LocalDateTimeUtil {

    static Map<String,Integer> mapHoliday = Maps.newHashMap();
    static HashSet<String> mapWorkDay = Sets.newHashSet();
    static List<String> defaultHoliday = Lists.newArrayList(String.valueOf(DayOfWeek.SATURDAY.getValue()),String.valueOf(DayOfWeek.SUNDAY.getValue()));
    static{
        mapHoliday.put(String.valueOf(DayOfWeek.SATURDAY.getValue()),2);
        mapHoliday.put(String.valueOf(DayOfWeek.SUNDAY.getValue()),1);
    }

    /** 添加节假日
    @param holiday 年月日 例如二零二一年九月一日 20210901
    @param workDay 距离上班时间 例如10.1距离上班是7天，10.2就是6
     */
    static public void addHoliday(String holiday,int workDay){
        if (defaultHoliday.contains(holiday) == false) {
            mapHoliday.put(holiday, workDay);
        }
    }

    /** 删除节假日
     @param holiday 年月日 例如二零二一年九月一日 20210901
     */
    static public void removeHoliday(String holiday){
        if (defaultHoliday.contains(holiday) == false) {
            mapHoliday.remove(holiday);
        }
    }

    /** 添加工作日
     @param workDay 年月日 例如二零二一年九月一日 20210901
     */
    static public void addWorkDay(String workDay){
        mapWorkDay.add(workDay);
    }

    /** 删除工作日
     @param workDay 年月日 例如二零二一年九月一日 20210901
     */
    static public void removeWorkDay(String workDay){
        mapWorkDay.remove(workDay);
    }

    static public int getHolidayWorkDays(LocalDateTime localDateTime){
        int days = 0;

        String strDay = LocalDateTimeUtil.format(localDateTime,"yyyyMMdd");

        if (mapWorkDay.contains(strDay)){
            return days;
        }
        if (mapHoliday.containsKey(strDay)){
            return days = mapHoliday.get(strDay);
        }
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        strDay = String.valueOf(dayOfWeek.getValue());
        if (mapHoliday.containsKey(strDay)){
            return days = mapHoliday.get(strDay);
        }
        return days;
    }
}
