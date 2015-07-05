package com.qtao.kaoyanknowledge.App;

import android.app.Application;
import android.content.Context;

<<<<<<< HEAD
import com.github.johnkil.print.PrintConfig;
import com.readystatesoftware.systembartint.SystemBarTintManager;

=======
>>>>>>> 2f174ea5baccc84928f2c0a75f318b9ef517bf0c
/**
 * Created by AotY on 2015/7/5.
 */
public class KaoYanApplication extends Application {

    public static Context appContext ;

<<<<<<< HEAD


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this.getApplicationContext();
        PrintConfig.initDefault(getAssets(), "fonts/material-icon-font.ttf");

    }





=======
    @Override
    public void onCreate() {
        appContext = this.getApplicationContext();
        super.onCreate();
    }


>>>>>>> 2f174ea5baccc84928f2c0a75f318b9ef517bf0c
}
