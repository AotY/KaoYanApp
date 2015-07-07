package com.amulyakhare.textdrawable.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author amulya
 * @datetime 14 Oct 2014, 5:20 PM
 */
public class ColorGenerator {

    public static ColorGenerator DEFAULT;

    public static ColorGenerator MATERIAL;

    static {
        DEFAULT = create(Arrays.asList(
                0xfff16364,
                0xfff58559,
                0xfff9a43e,
                0xffe4c62e,
                0xff67bf74,
                0xff59a2be,
                0xff2093cd,
                0xffad62a7,
                0xff805781
        ));
        MATERIAL = create(Arrays.asList(
                0xffe57373,
                0xfff06292,
                0xffD32F2F,
                0xff3F51B5,
                0xff64b5f6,
                0xff4fc3f7,
                0xff4dd0e1,
                0xff4db6ac,
                0xfff16364,
                0xfff58559,
                0xff00C853,
                0xffba68c8,
                0xff9575cd,
                0xffF44336,
                0xff7986cb,
                0xfff9a43e,
                0xffe4c62e,
                0xff67bf74,
                0xff59a2be,
                0xff7B1FA2,
                0x7C2185B,
                0xffE53935,
                0xff00E676,
                0xff424242,
                0xffE040FB,
                0xff2093cd,
                0xffad62a7,
                0xff805781,
                0xffFF5722,
                0xff81c784,
                0xff388E3C,
                0xffCDDC39,
                0xff00796B,
                0xff536DFE,
                0xff009688,
                0xff00BCD4,
                0xffFFC107,
                0xff03A9F4,
                0xff4CAF50,
                0xffaed581,
                0xff004D40,
                0xff827717,
                0xffFF9800,
                0xffFBC02D,
                0xff795548,
                0xff607D8B,
                0xffff8a65,
                0xff4A148C,
                0xff18FFFF,
                0xffFF1744,
                0xff3F51B5,
                0xff388E3C,
                0xffd4e157,
                0xffffd54f,
                0xffffb74d,
                0xffa1887f,
                0xff90a4ae,
                0xff26C6DA,
                0xff673AB7,
                0xff2196F3,
                0xffF44336,
                0xff26A69A,
                0xff00796B,
                0xffFF4081
        ));
    }

    private final List<Integer> mColors;
    private final Random mRandom;

    public static ColorGenerator create(List<Integer> colorList) {
        return new ColorGenerator(colorList);
    }

    private ColorGenerator(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public int getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getColor(Object key) {
        return mColors.get(Math.abs(key.hashCode()) % mColors.size());
    }
}
