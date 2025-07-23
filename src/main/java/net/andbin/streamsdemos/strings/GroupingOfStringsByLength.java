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

public class GroupingOfStringsByLength {
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
         * We want to group the names by length, obtaining a map where:
         *   - the key is the length (e.g. 4, 5, 6, etc...)
         *   - the value is a list containing all the names having that length
         * We also want the map keys (lengths) sorted in ascending order.
         *
         * e.g.
         *     Length  4 --> [Iowa, Ohio, Utah]
         *     Length  5 --> [Idaho, Maine, Texas]
         *         ...etc...
         *     Length 13 --> [Massachusetts, New Hampshire, West Virginia]
         *     Length 14 --> [North Carolina, South Carolina]
         */

        Map<Integer,List<String>> lengthToNamesMap = groupByLengthUsingLambdaExpr(namesList);

        System.out.printf("namesList = %s%n%n", namesList);

        lengthToNamesMap.entrySet().forEach(entry -> {
            System.out.printf("Length %2d --> %s%n", entry.getKey(), entry.getValue());
        });
    }


    public static Map<Integer,List<String>> groupByLengthUsingLambdaExpr(List<String> stringsList) {
        return stringsList.stream()
                .collect(groupingBy(
                        str -> str.length(),        // classifier: gets the length of the string
                        () -> new TreeMap<>(),      // map factory: creates a TreeMap<Integer,List<String>>
                        toList()                    // downstream: collects strings into a List
                ));
    }

    public static Map<Integer,List<String>> groupByLengthUsingMethodRef(List<String> stringsList) {
        return stringsList.stream()
                .collect(groupingBy(
                        String::length,     // classifier: gets the length of the string
                        TreeMap::new,       // map factory: creates a TreeMap<Integer,List<String>>
                        toList()            // downstream: collects strings into a List
                ));
    }
}
