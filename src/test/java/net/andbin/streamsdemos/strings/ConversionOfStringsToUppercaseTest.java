/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.strings;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.andbin.streamsdemos.AbstractTestBase;

public class ConversionOfStringsToUppercaseTest extends AbstractTestBase {
    private final SUT sut;

    public ConversionOfStringsToUppercaseTest(String instanceInfo, SUT sut) {
        super(instanceInfo);
        this.sut = sut;
    }

    @Factory
    public static Object[] instances() {
        return new Object[] {
                new ConversionOfStringsToUppercaseTest("convertToUppercaseUsingLambdaExpr",
                        ConversionOfStringsToUppercase::convertToUppercaseUsingLambdaExpr),
                new ConversionOfStringsToUppercaseTest("convertToUppercaseUsingMethodRef",
                        ConversionOfStringsToUppercase::convertToUppercaseUsingMethodRef),
        };
    }

    private interface SUT {
        List<String> convertToUppercase(List<String> stringsList);
    }


    //--------------------------------------------------------------------------
    // TESTS
    //--------------------------------------------------------------------------

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                { Arrays.asList()            ,  Arrays.asList()             },
                { Arrays.asList("one")       ,  Arrays.asList("ONE")        },
                { Arrays.asList("one", "two"),  Arrays.asList("ONE", "TWO") },
        };
    }

    @Test(dataProvider = "testData")
    public void givenListShouldReturnExpectedResult(List<String> inputList, List<String> expectedList) {
        List<String> actualList = sut.convertToUppercase(inputList);
        assertThat(actualList).isEqualTo(expectedList);
    }
}
