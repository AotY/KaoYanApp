package com.qtao.kaoyanknowledge.App;

import android.app.Application;
import android.content.Context;

import com.github.johnkil.print.PrintConfig;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by AotY on 2015/7/5.
 */
public class KaoYanApplication extends Application {

    public static Context appContext ;



    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this.getApplicationContext();
        PrintConfig.initDefault(getAssets(), "fonts/material-icon-font.ttf");

    }





}
