

/*
* ASSIGNMENT1
* DATE: 9.28.2017
* VERSION: V1.0
*
*/
/*
 * Copyright (c): HAOTIAN ZHU, CMPUT301, UNIVERSITY OF ALBERTA, ALL RIGHTS RESERVED.
 *                       YOU MAY USE, DISTRIBUTE, OR MODIFY THIS CODE UNDER  TERM AND
 *                       CONDITION OF CODE OF STUDENTS BEHAVIOR AT UNIVERSITY OF ALBERTA.
 */



package com.example.haotianzhu.countbook;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by haotianzhu on 2017-09-23.
 *
 *
 * count class with following attributes: name,current value, initial value, and text, and date
 *
 * functions: get attributes(each attribute), update each attribute, and increase or decrease current vlaue.
 *
 * other function: resetValue function: to set current value as initial value
 *
 * override to string to return a string contains name/value/date information
 *
 */

public class Count implements Serializable {
    private String name; //user neeed specify this
    private Date date;
    private Integer current_value;
    private Integer  initial_value; //user neeed specify this
    private String comment;


    public Count(String name,Integer initial_value,String comment){
        this.name = name;
        this.initial_value = initial_value;
        this.current_value = 0;
        this.comment = comment;
        this.date = new Date();
    }
    public Count(String name, Integer initial_value){
        this(name,initial_value,"");
    }


    // get count's name
    public String getName(){
        return this.name;
    }
    //get count's date
    public String getDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(date);
        return dateString;
    }
    //get count's initial value
    public Integer getInitialValue(){
        return  initial_value;
    }
    //get count's current value
    public Integer getCurrentValue(){
        return  current_value;
    }

    //get count's text
    public String getText(){
        return comment;
    }

    // increase value
    public void increaseValue(){
        this.current_value += 1;
        return;
    }

    //decrease value
    public void decreaseValue(){
        this.current_value -= 1;
        if (this.current_value >=0){
            return;
        }else{this.current_value = 0; return;}
    }

    // reset value
    public void resetValue(){
        this.current_value = initial_value;
        return;
    }

    //change name and date()
    public void updateName(String name){
        this.date = new Date();
        this.name = name;
        return;
    }

    //change update initial value and date()
    public void updateValue(Integer initial_value){
        this.date = new Date();
        this.initial_value = initial_value;
        return;
    }
    //change current value and date()
    public void updateCurrent(Integer value){
        this.date = new Date();
        this.current_value = value;
        return;
    }

    //change comment and date()
    public void updateComment(String comment){
        this.date = new Date();
        this.comment = comment;
        return;
    }
    @Override
    public String toString(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(date);
        return "Name: "+name +" | Value: "+ Integer.toString(current_value) +" | Date: "+ dateString;
    }
}

