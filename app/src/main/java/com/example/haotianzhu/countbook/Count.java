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

    public String getName(){
        return this.name;
    }
    public String getDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(date);
        return dateString;
    }
    public Integer getInitialValue(){
        return  initial_value;
    }
    public Integer getCurrentValue(){
        return  current_value;
    }
    public String getText(){
        return comment;
    }

    public void increaseValue(){
        this.current_value += 1;
        return;
    }

    public void decreaseValue(){
        this.current_value -= 1;
        if (this.current_value >=0){
            return;
        }else{this.current_value = 0; return;}
    }

    public void resetValue(){
        this.current_value = initial_value;
        return;
    }

    public void updateName(String name){
        this.date = new Date();
        this.name = name;
        return;
    }
    public void updateValue(Integer initial_value){
        this.date = new Date();
        this.initial_value = initial_value;
        return;
    }
    public void updateCurrent(Integer value){
        this.date = new Date();
        this.current_value = value;
        return;
    }

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

