package com.pikachu.purple.application.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    public static int getPercentage(int part, int whole) {
        return (int) Math.round(((double) part / (double) whole) * 100);
    }

    public static int getPercentage(double part, double whole) {
        return (int) Math.round((part/whole) * 100);
    }

}
