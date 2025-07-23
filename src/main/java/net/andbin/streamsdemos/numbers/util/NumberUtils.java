/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.numbers.util;

public class NumberUtils {
    private NumberUtils() {}

    public static boolean isEven(int value) {
        return value % 2 == 0;
    }
}
