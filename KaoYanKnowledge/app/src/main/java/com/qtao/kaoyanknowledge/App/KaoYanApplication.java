package com.qtao.kaoyanknowledge.App;

import android.app.Application;
import android.content.Context;

/**
 * Created by AotY on 2015/7/5.
 */
public class KaoYanApplication extends Application {

    public static Context appContext ;

    @Override
    public void onCreate() {
        appContext = this.getApplicationContext();
        super.onCreate();
    }


}
