package com.example.tomasz.studiesmenager.Model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by hub on 2017-05-26.
 */

public class Class  extends SugarRecord<Class>{
    public enum ClassType{
        Lab,
        Lecture,
        Class
    }
    public ClassType Type;
    public int MaxScore;
    public int MinPassScore;
    public Date StartHour;
    public Date EndHour;
    public int FreqInWeeks; // 1 - every week, 2 - every two weeks...
    public boolean ShowNotifications = true;
    public Subject Subject;

    public Class(){}

}
