package com.example.tomasz.studiesmenager.Model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by hub on 2017-05-26.
 */

public class Subject extends SugarRecord<Subject> {
    public String Name;
    public Subject(){}

    public Subject(String _name){
        Name = _name;
    }
}
