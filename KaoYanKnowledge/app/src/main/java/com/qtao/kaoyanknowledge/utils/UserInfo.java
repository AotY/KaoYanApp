package com.qtao.kaoyanknowledge.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AotY on 2015/7/6.
 */
public class UserInfo {

    public static final String USERINFO = "userInfo";

    public static final String USERMAJOR = "major";

    public static final String ISALWAYSCHOOSE = "isalwayschoose";


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
     * 清除用户的专业
     *
     * @param context
     */
    public static void clearUserMajor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERMAJOR, null);
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

    /**
     * 获取用户选择
     *
     * @param context
     */
    public static boolean getUserChooseEnterMajor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        boolean b = preferences.getBoolean(ISALWAYSCHOOSE, false);
        return b;
    }
    /**
     * 保存用户选择
     *
     * @param context
     */
    public static void setUserChooseEnterMajor(Context context , boolean b) {
        SharedPreferences preferences = context.getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(ISALWAYSCHOOSE, b);
        editor.commit();
    }



}
