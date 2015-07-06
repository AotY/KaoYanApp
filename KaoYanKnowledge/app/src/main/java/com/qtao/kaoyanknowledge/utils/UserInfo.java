package com.qtao.kaoyanknowledge.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AotY on 2015/7/6.
 */
public class UserInfo {

    public static final String USERINFO = "userInfo";

    public static final String USERMAJOR = "major";


    /**
     * 保存用户的专业
     *
     * @param context
     * @param major
     */
    public static void setUserMajor(Context context, String major) {
        SharedPreferences preferences = context.getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERMAJOR, major);
        editor.commit();
    }

    /**
     * 获取用户的专业
     *
     * @param context
     */
    public static String getUserMajor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        String major = preferences.getString(USERMAJOR, null);
        return major;
    }


}
