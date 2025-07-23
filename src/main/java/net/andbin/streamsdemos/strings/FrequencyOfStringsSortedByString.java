/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.strings;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FrequencyOfStringsSortedByString {
    public static void main(String[] args) {
        /*
         * Given the following list of strings (Castle characters):
         */

        List<String> namesList = Arrays.asList(
                "Richard", "Kate", "Alexis", "Martha", "Jenny", "Richard",
                "Martha", "Tory", "Richard", "Roy", "Alexis", "Martha",
                "Javier", "Victoria", "Kevin", "Kate", "Richard", "Kate",
                "Richard", "Alexis", "Richard", "Vikram", "Sidney", "Kate",
                "Javier", "Richard", "Javier", "Lanie", "Martha", "Lanie",
                "Kate", "Martha", "Kevin", "Hayley", "Javier", "Kate",
                "Jim", "Kevin", "Lanie", "Kevin", "Roy", "Victoria"
        );

        /*
         * We want to calculate the frequency (number of occurrencies) of
         * each name, obtaining a map where:
         *   - the key is the name
         *   - the value is the frequency
         * We also want the map keys (names) sorted in ascending order.
         *
         * e.g.
         *     Alexis   has frequency 3
         *     Hayley   has frequency 1
         *         ...etc...
         *     Victoria has frequency 2
         *     Vikram   has frequency 1
         */

        Map<String,Long> nameToFrequencyMap = mapStringsFrequencyUsingLambdaExpr(namesList);

        System.out.printf("namesList = %s%n%n", namesList);

        nameToFrequencyMap.entrySet().forEach(entry -> {
            System.out.printf("%-8s has frequency %d%n", entry.getKey(), entry.getValue());
        });
    }


    public static Map<String,Long> mapStringsFrequencyUsingLambdaExpr(List<String> stringsList) {
        return stringsList.stream()
                .collect(groupingBy(
                        str -> str,                 // classifier: the string itself
                        () -> new TreeMap<>(),      // map factory: creates a TreeMap<String,Long>
                        counting()                  // downstream: counts occurrencies
                ));
    }

    public static Map<String,Long> mapStringsFrequencyUsingMethodRef(List<String> stringsList) {
        return stringsList.stream()
                .collect(groupingBy(
                        str -> str,         // classifier: the string itself
                        TreeMap::new,       // map factory: creates a TreeMap<String,Long>
                        counting()          // downstream: counts occurrencies
                ));
    }
}
