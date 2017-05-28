package com.example.tomasz.studiesmenager.Model;

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
   /* public void generatePush(int count) {
        for(int i = 0;i < 10;i++) {
            pastAttendences = Attendence.findWithQuery(Attendence.class,
                    "select * from attendence join class on class.id = class join subject on subject.id = class.subject where subject.id = ?",
                    String.valueOf(subjectID));
        }
    }*/
}


