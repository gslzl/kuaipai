package com.example.myproject;

import android.app.Application;

import com.blankj.utilcode.util.Utils;


public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //blankj:utilcode的初始化
        Utils.init(this);

    }
}
