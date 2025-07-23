/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.numbers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.andbin.streamsdemos.AbstractTestBase;

public class PartitionOfEvenOddIntValuesTest extends AbstractTestBase {
    private final SUT sut;

    public PartitionOfEvenOddIntValuesTest(String instanceInfo, SUT sut) {
        super(instanceInfo);
        this.sut = sut;
    }

    @Factory
    public static Object[] instances() {
        return new Object[] {
                new PartitionOfEvenOddIntValuesTest("partitionEvenOddUsingLambdaExpr",
                        PartitionOfEvenOddIntValues::partitionEvenOddUsingLambdaExpr),
                new PartitionOfEvenOddIntValuesTest("partitionEvenOddUsingMethodRef",
                        PartitionOfEvenOddIntValues::partitionEvenOddUsingMethodRef),
        };
    }

    private interface SUT {
        Map<Boolean,List<Integer>> partitionEvenOdd(int[] intValues);
    }


    //--------------------------------------------------------------------------
    // TESTS
    //--------------------------------------------------------------------------

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                { new int[] {}              , new Integer[] {}       ,  new Integer[] {}        },
                { new int[] { 3 }           , new Integer[] { 3 }    ,  new Integer[] { }       },
                { new int[] { 6 }           , new Integer[] {}       ,  new Integer[] { 6 }     },
                { new int[] { -7, 0, 3, -6 }, new Integer[] { -7, 3 },  new Integer[] { 0, -6 } },
        };
    }

    @Test(dataProvider = "testData")
    public void givenArrayShouldReturnExpectedResult(int[] inputArray,
            Integer[] expectedOddValues, Integer[] expectedEvenValues) {
        Map<Boolean,List<Integer>> parityToValuesMap = sut.partitionEvenOdd(inputArray);
        assertThat(parityToValuesMap).isNotNull();
        assertThat(parityToValuesMap.get(false)).containsOnly(expectedOddValues);
        assertThat(parityToValuesMap.get(true)).containsOnly(expectedEvenValues);
    }
}
