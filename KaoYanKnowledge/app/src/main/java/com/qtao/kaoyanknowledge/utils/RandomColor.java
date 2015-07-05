package com.qtao.kaoyanknowledge.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by AotY on 2015/7/5.
 */
public class RandomColor {
    public static int randomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}
