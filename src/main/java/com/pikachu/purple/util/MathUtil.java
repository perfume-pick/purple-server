package com.pikachu.purple.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    public static double round(double number, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        return Math.round(number * scale) / scale;
    }

}
