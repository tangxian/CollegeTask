package com.ld.collegetask.beans;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ld.collegetask.BR;


/**
 * Created by heliang on 2017/9/1.
 */

public class User extends BaseObservable{//继承观察可更新
    private String username;// 账号
    private String status = "-100";
    private String userId;
    private String realname;
    private String password;

    public User(){

    }

    public User(String username, String status, String userId, String realname, String password){
        this.username = username;
        this.status = status;
        this.userId = userId;
        this.realname = realname;
        this.password = password;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        //刷新变量（变量id）
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        //刷新变量（变量id）
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        //刷新变量（变量id）
        notifyPropertyChanged(BR.userId);
    }

    @Bindable
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
        //刷新变量（变量id）
        notifyPropertyChanged(BR.realname);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
