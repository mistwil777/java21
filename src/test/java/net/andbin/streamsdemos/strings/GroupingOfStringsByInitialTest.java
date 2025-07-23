/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.strings;

import static com.google.common.collect.Maps.immutableEntry;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.andbin.streamsdemos.AbstractTestBase;

public class GroupingOfStringsByInitialTest extends AbstractTestBase {
    private final SUT sut;

    public GroupingOfStringsByInitialTest(String instanceInfo, SUT sut) {
        super(instanceInfo);
        this.sut = sut;
    }

    @Factory
    public static Object[] instances() {
        return new Object[] {
                new GroupingOfStringsByInitialTest("groupByInitialUsingLambdaExpr",
                        GroupingOfStringsByInitial::groupByInitialUsingLambdaExpr),
                new GroupingOfStringsByInitialTest("groupByInitialUsingMethodRef",
                        GroupingOfStringsByInitial::groupByInitialUsingMethodRef),
        };
    }

    private interface SUT {
        Map<String,List<String>> groupByInitial(List<String> stringsList);
    }


    //--------------------------------------------------------------------------
    // TESTS
    //--------------------------------------------------------------------------

    private static final List<String> LIST1 = Arrays.asList("one", "two",
            "three", "four", "five", "six");

    @Test
    public void givenEmptyListShouldReturnEmptyMap() {
        Map<String,List<String>> initialToStringsMap = sut.groupByInitial(Collections.emptyList());
        assertThat(initialToStringsMap).isEmpty();
    }

    @Test
    public void givenOneItemListShouldReturnOneEntryMap() {
        Map<String,List<String>> initialToStringsMap = sut.groupByInitial(Arrays.asList("one"));
        assertThat(initialToStringsMap).containsExactly(immutableEntry("o", Arrays.asList("one")));
    }

    @Test
    public void givenList1ShouldReturnFourEntriesMap() {
        Map<String,List<String>> initialToStringsMap = sut.groupByInitial(LIST1);
        assertThat(initialToStringsMap).isNotEmpty();
        assertThat(initialToStringsMap.keySet()).containsExactly("f", "o", "s", "t");
        assertThat(initialToStringsMap.get("f")).containsOnly("four", "five");
        assertThat(initialToStringsMap.get("o")).containsOnly("one");
        assertThat(initialToStringsMap.get("s")).containsOnly("six");
        assertThat(initialToStringsMap.get("t")).containsOnly("two", "three");
    }
}
