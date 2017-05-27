package com.example.tomasz.studiesmenager;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by hub on 2017-05-26.
 */

public class Attendence extends SugarRecord<Attendence>{
    public Date Date;
    public int PointsEarned;
    public boolean WasPresent = false;
    public Class Class;

    public Attendence(){}
}
