/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.numbers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.OptionalInt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.andbin.streamsdemos.AbstractTestBase;

public class ProductOfIntValuesTest extends AbstractTestBase {
    private final SUT sut;

    public ProductOfIntValuesTest(String instanceInfo, SUT sut) {
        super(instanceInfo);
        this.sut = sut;
    }

    @Factory
    public static Object[] instances() {
        return new Object[] {
                new ProductOfIntValuesTest("multiplyIntsUsingLambdaExpr",
                        ProductOfIntValues::multiplyIntsUsingLambdaExpr)
        };
    }

    private interface SUT {
        OptionalInt multiplyInts(int[] intValues);
    }


    //--------------------------------------------------------------------------
    // TESTS
    //--------------------------------------------------------------------------

    @Test
    public void givenEmptyArrayShouldReturnNoValue() {
        OptionalInt productOpt = sut.multiplyInts(new int[0]);
        assertThat(productOpt).isEmpty();
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                { new int[] { 0 }           , 0      },
                { new int[] { 123 }         , 123    },
                { new int[] { 123, -456 }   , -56088 },
                { new int[] { 0, 123, -456 }, 0      },
                { new int[] { 123, 0, -456 }, 0      },
                { new int[] { 123, -456, 0 }, 0      },
        };
    }

    @Test(dataProvider = "testData")
    public void givenNonEmptyArrayShouldReturnExpectedResult(int[] inputArray, int expectedProduct) {
        OptionalInt productOpt = sut.multiplyInts(inputArray);
        assertThat(productOpt).hasValue(expectedProduct);
    }
}
