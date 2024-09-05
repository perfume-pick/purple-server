package com.pikachu.purple.application.util;

public class MathUtil {

    public static int getPercentage(int part, int whole) {
        return (int) Math.round(((double) part / (double) whole) * 100);
    }

}
