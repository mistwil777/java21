/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.strings;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GroupingOfStringsByInitial {
    public static void main(String[] args) {
        /*
         * Given the following list of strings (U.S. states names):
         */

        List<String> namesList = Arrays.asList(
                "Alabama", "Alaska", "Arizona", "Arkansas", "California",
                "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
                "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
                "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
                "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
                "Nebraska", "Nevada", "New Hampshire", "New Jersey",
                "New Mexico", "New York", "North Carolina", "North Dakota",
                "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah",
                "Vermont", "Virginia", "Washington", "West Virginia",
                "Wisconsin", "Wyoming"
        );

        /*
         * We want to group the names by initial letter, obtaining a map where:
         *   - the key is the initial letter (e.g. "A", "B", "C", etc...)
         *   - the value is a list containing all the names having that initial
         * We also want the map keys (initials) sorted in ascending order.
         *
         * e.g.
         *     A --> [Alabama, Alaska, Arizona, Arkansas]
         *     C --> [California, Colorado, Connecticut]
         *         ...etc...
         *     V --> [Vermont, Virginia]
         *     W --> [Washington, West Virginia, Wisconsin, Wyoming]
         */

        Map<String,List<String>> initialToNamesMap = groupByInitialUsingLambdaExpr(namesList);

        System.out.printf("namesList = %s%n%n", namesList);

        initialToNamesMap.entrySet().forEach(entry -> {
            System.out.printf("%s --> %s%n", entry.getKey(), entry.getValue());
        });
    }


    public static Map<String,List<String>> groupByInitialUsingLambdaExpr(List<String> stringsList) {
        return stringsList.stream()
                .collect(groupingBy(
                        str -> str.substring(0, 1),     // classifier: gets the initial of the string
                        () -> new TreeMap<>(),          // map factory: creates a TreeMap<String,List<String>>
                        toList()                        // downstream: collects strings into a List
                ));
    }

    public static Map<String,List<String>> groupByInitialUsingMethodRef(List<String> stringsList) {
        return stringsList.stream()
                .collect(groupingBy(
                        str -> str.substring(0, 1),     // classifier: gets the initial of the string
                        TreeMap::new,                   // map factory: creates a TreeMap<String,List<String>>
                        toList()                        // downstream: collects strings into a List
                ));
    }
}
