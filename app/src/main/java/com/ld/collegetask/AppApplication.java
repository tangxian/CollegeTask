package com.ld.collegetask;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.ld.collegetask.beans.User;

/**
 * Created by heliang on 2017/9/41.
 */

public class AppApplication extends Application {
    public static String sTag = "Android";
    public static String server, serverWithoutHttp ;
    //    public static String server2 = "http://test.kpb.whkpb.gov.cn";
    private int pixelWidth, pixelHeight;
    private  float density;

    private User user;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            server = "http://www.whce.gov.cn";
            serverWithoutHttp = "www.whce.gov.cn";
        } else {
            server = "http://www.whce.gov.cn";
            serverWithoutHttp = "www.whce.gov.cn";
        }
        Log.e("AppApplication", server);
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void getPhoneInfo(){
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        setDensity(dm.density);
        setPixelWidth(dm.widthPixels);
        setPixelHeight(dm.heightPixels);
    }

    public int getPixelWidth() {
        return pixelWidth;
    }

    public void setPixelWidth(int pixelWidth) {
        this.pixelWidth = pixelWidth;
    }

    public int getPixelHeight() {
        return pixelHeight;
    }

    public void setPixelHeight(int pixelHeight) {
        this.pixelHeight = pixelHeight;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

}
