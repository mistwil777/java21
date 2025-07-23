/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.numbers;

import java.util.Arrays;
import java.util.OptionalInt;

public class ProductOfIntValues {
    public static void main(String[] args) {
        /*
         * Given the following array of int values:
         */

        int[] intValues = { 6, 19, -2, 8, 22, 10, 17, 14 };

        /*
         * We want to obtain the product of all the values.
         *
         * e.g.
         *     product = -95504640   (= 6 * 19 * -2 * 8 * 22 * 10 * 17 * 14)
         */

        OptionalInt productOpt = multiplyIntsUsingLambdaExpr(intValues);

        System.out.printf("intValues = %s%n%n", Arrays.toString(intValues));
        productOpt.ifPresent(value -> System.out.printf("product = %d%n", value));
    }


    public static OptionalInt multiplyIntsUsingLambdaExpr(int[] intValues) {
        return Arrays.stream(intValues).reduce((a, b) -> a * b);
    }
}
