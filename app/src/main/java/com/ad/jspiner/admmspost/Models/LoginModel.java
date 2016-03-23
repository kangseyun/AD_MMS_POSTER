package com.ad.jspiner.admmspost.Models;

/**
 * Created by 12kd1004 on 2016. 3. 23..
 */
public class LoginModel {
    private int is_master;
    private int logined;

    public int get_admin(){
        return this.is_master;
    }

    public int get_logined(){
        return this.logined;
    }

    public void set_admin(int num){
        this.is_master = num;
    }

    public void set_logined(int num){
        this.logined = num;
    }
}
