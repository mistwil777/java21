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

public class GroupingOfStringsByLengthTest extends AbstractTestBase {
    private final SUT sut;

    public GroupingOfStringsByLengthTest(String instanceInfo, SUT sut) {
        super(instanceInfo);
        this.sut = sut;
    }

    @Factory
    public static Object[] instances() {
        return new Object[] {
                new GroupingOfStringsByLengthTest("groupByLengthUsingLambdaExpr",
                        GroupingOfStringsByLength::groupByLengthUsingLambdaExpr),
                new GroupingOfStringsByLengthTest("groupByLengthUsingMethodRef",
                        GroupingOfStringsByLength::groupByLengthUsingMethodRef),
        };
    }

    private interface SUT {
        Map<Integer,List<String>> groupByLength(List<String> stringsList);
    }


    //--------------------------------------------------------------------------
    // TESTS
    //--------------------------------------------------------------------------

    private static final List<String> LIST1 = Arrays.asList("one", "two",
            "three", "four", "five", "six");

    @Test
    public void givenEmptyListShouldReturnEmptyMap() {
        Map<Integer,List<String>> lengthToStringsMap = sut.groupByLength(Collections.emptyList());
        assertThat(lengthToStringsMap).isEmpty();
    }

    @Test
    public void givenOneItemListShouldReturnOneEntryMap() {
        Map<Integer,List<String>> lengthToStringsMap = sut.groupByLength(Arrays.asList("one"));
        assertThat(lengthToStringsMap).containsExactly(immutableEntry(3, Arrays.asList("one")));
    }

    @Test
    public void givenList1ShouldReturnThreeEntriesMap() {
        Map<Integer,List<String>> lengthToStringsMap = sut.groupByLength(LIST1);
        assertThat(lengthToStringsMap).isNotEmpty();
        assertThat(lengthToStringsMap.keySet()).containsExactly(3, 4, 5);
        assertThat(lengthToStringsMap.get(3)).containsOnly("one", "two", "six");
        assertThat(lengthToStringsMap.get(4)).containsOnly("four", "five");
        assertThat(lengthToStringsMap.get(5)).containsOnly("three");
    }
}
