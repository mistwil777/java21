/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.strings;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class ConversionOfStringsToUppercase {
    public static void main(String[] args) {
        /*
         * Given the following list of strings (fruit names):
         */

        List<String> stringsList = Arrays.asList(
                "apple", "apricot", "banana", "blackberry", "blueberry",
                "cherry", "lemon", "lime", "mandarine", "mango", "melon",
                "orange", "peach", "pear", "raspberry", "strawberry",
                "watermelon"
        );

        /*
         * We want to obtain a new list containing all the strings
         * converted to uppercase.
         *
         * e.g.
         *     result = [APPLE, APRICOT, BANANA, ...etc..., WATERMELON]
         */

        List<String> result = convertToUppercaseUsingLambdaExpr(stringsList);

        System.out.printf("stringsList = %s%n%n", stringsList);
        System.out.printf("result = %s%n", result);
    }


    public static List<String> convertToUppercaseUsingLambdaExpr(List<String> stringsList) {
        return stringsList.stream()
                .map(str -> str.toUpperCase())
                .collect(toList());
    }

    public static List<String> convertToUppercaseUsingMethodRef(List<String> stringsList) {
        return stringsList.stream()
                .map(String::toUpperCase)
                .collect(toList());
    }
}
