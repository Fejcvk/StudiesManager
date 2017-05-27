package com.example.tomasz.studiesmenager;

import com.orm.SugarRecord;

/**
 * Created by hub on 2017-05-26.
 */

public class Subject extends SugarRecord<Subject> {
    private String Name;

    public Subject(){}
    public Subject(String _name){
        Name = _name;
    }

    public  void setName(String _name){
        Name = _name;
    }

    public String getName(){
        return Name;
    }
}
