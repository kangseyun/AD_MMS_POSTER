package com.ad.jspiner.admmspost.Models;

/**
 * Created by 12kd1004 on 2016. 3. 23..
 */
public class MenuModel {
    public String number;
    public String name;
    public String date;
    public String phone;
    public String is_active;

    public MenuModel(String number,String name,String date,String phone, String is_active){
        this.number = number;
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.is_active = is_active;
    }
}
