/*
 * Copyright (C) 2016-2017 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.numbers;

import static java.util.stream.Collectors.partitioningBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.andbin.streamsdemos.numbers.util.NumberUtils;

public class PartitionOfEvenOddIntValues {
    public static void main(String[] args) {
        /*
         * Given the following array of int values:
         */

        int[] intValues = {
                -20, -11, 8, -13, 4, -14, -18, 7, -20, 2,
                18, 11, -18, -3, -19, -7, -12, -9, 22, 15
        };

        /*
         * We want to partition even/odd values and obtain a map where:
         *   - key is the parity as boolean (FALSE=odd / TRUE=even)
         *   - values are lists containing only even or only odd values
         *
         * e.g.
         *     Odd  --> [-11, -13, 7, 11, -3, -19, -7, -9, 15]
         *     Even --> [-20, 8, 4, -14, -18, -20, 2, 18, -18, -12, 22]
         */

        Map<Boolean,List<Integer>> parityToValuesMap = partitionEvenOddUsingLambdaExpr(intValues);

        System.out.printf("intValues = %s%n%n", Arrays.toString(intValues));

        System.out.printf("Odd  --> %s%n", parityToValuesMap.get(false));
        System.out.printf("Even --> %s%n", parityToValuesMap.get(true));
    }


    public static Map<Boolean,List<Integer>> partitionEvenOddUsingLambdaExpr(int[] intValues) {
        return Arrays.stream(intValues)
                .boxed()                    // boxes int to Integer
                .collect(partitioningBy(
                        value -> NumberUtils.isEven(value)      // predicate: true if value is even
                ));
    }

    public static Map<Boolean,List<Integer>> partitionEvenOddUsingMethodRef(int[] intValues) {
        return Arrays.stream(intValues)
                .boxed()                    // boxes int to Integer
                .collect(partitioningBy(
                        NumberUtils::isEven         // predicate: true if value is even
                ));
    }
}
