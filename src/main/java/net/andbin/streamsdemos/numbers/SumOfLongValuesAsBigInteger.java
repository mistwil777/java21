/*
 * Copyright (C) 2016-2017 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.numbers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;

public class SumOfLongValuesAsBigInteger {
    public static void main(String[] args) {
        /*
         * Given the following array of large long values:
         * (note Java7's underscores in numeric literals!)
         */

        long[] longValues = {
                5_688_532_055_701_194_210L,
                6_268_490_417_976_816_750L,
                  612_339_080_707_480_992L,
                2_318_751_640_889_052_753L,
                7_966_514_575_380_920_986L,
                8_632_476_475_037_157_219L,
                1_260_013_854_635_404_300L,
                9_141_849_402_704_233_556L,
                  543_001_030_510_006_966L,
                9_185_263_974_716_956_513L,
        };

        /*
         * We want to calculate the sum of all the values as BigInteger,
         * without any loss of magnitude or precision.
         *
         * e.g.
         *     sum = 51,617,232,508,259,224,245
         */

        BigInteger sum = sumLongsUsingLambdaExpr(longValues);

        System.out.println("longValues = [");

        for (long value : longValues) {
            System.out.printf(Locale.ENGLISH, "      %,26d%n", value);
        }

        System.out.printf("]%n%n");

        System.out.printf(Locale.ENGLISH, "sum = %,26d%n", sum);
    }


    public static BigInteger sumLongsUsingLambdaExpr(long[] longValues) {
        return Arrays.stream(longValues)
                .mapToObj(value -> BigInteger.valueOf(value))           // map: long to BigInteger
                .reduce(BigInteger.ZERO, (bi1, bi2) -> bi1.add(bi2));   // reduction: addition of two BigInteger
    }

    public static BigInteger sumLongsUsingMethodRef(long[] longValues) {
        return Arrays.stream(longValues)
                .mapToObj(BigInteger::valueOf)                  // map: long to BigInteger
                .reduce(BigInteger.ZERO, BigInteger::add);      // reduction: addition of two BigInteger
    }
}
