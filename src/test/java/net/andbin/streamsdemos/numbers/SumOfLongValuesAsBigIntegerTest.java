/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.numbers;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.andbin.streamsdemos.AbstractTestBase;

public class SumOfLongValuesAsBigIntegerTest extends AbstractTestBase {
    private final SUT sut;

    public SumOfLongValuesAsBigIntegerTest(String instanceInfo, SUT sut) {
        super(instanceInfo);
        this.sut = sut;
    }

    @Factory
    public static Object[] instances() {
        return new Object[] {
                new SumOfLongValuesAsBigIntegerTest("sumLongsUsingLambdaExpr",
                        SumOfLongValuesAsBigInteger::sumLongsUsingLambdaExpr),
                new SumOfLongValuesAsBigIntegerTest("sumLongsUsingMethodRef",
                        SumOfLongValuesAsBigInteger::sumLongsUsingMethodRef),
        };
    }

    private interface SUT {
        BigInteger sumLongs(long[] longValues);
    }


    //--------------------------------------------------------------------------
    // TESTS
    //--------------------------------------------------------------------------

    private static final long V1 = 9012345678901234567L;
    private static final long V2 = 8901234567890123456L;

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                { new long[] { }       , new BigInteger("0")                    },
                { new long[] { 0 }     , new BigInteger("0")                    },
                { new long[] { V1 }    , new BigInteger("9012345678901234567")  },
                { new long[] { V1, V2 }, new BigInteger("17913580246791358023") },
        };
    }

    @Test(dataProvider = "testData")
    public void givenArrayShouldReturnExpectedResult(long[] inputArray, BigInteger expectedSum) {
        BigInteger actualSum = sut.sumLongs(inputArray);
        assertThat(actualSum).isEqualTo(expectedSum);
    }
}
