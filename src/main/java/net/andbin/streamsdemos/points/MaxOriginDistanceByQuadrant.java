/*
 * Copyright (C) 2017 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.points;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import net.andbin.streamsdemos.points.datamodel.Point2D;
import net.andbin.streamsdemos.points.datamodel.PointsData;
import net.andbin.streamsdemos.points.datamodel.Quadrant;

public class MaxOriginDistanceByQuadrant {
    public static void main(String[] args) {
        /*
         * Given the following list of points (see PointsData):
         */

        List<Point2D> pointsList = PointsData.getSamples();

        /*
         * We want to calculate the maximum distance of a point from the origin
         * for each quadrant, obtaining a map where:
         *   - the key is the Quadrant
         *   - the value is the (optional) maximum distance from the origin
         * Points not in a quadrant must be excluded.
         * We also want the map keys (quadrants) sorted according to Quadrant
         * enum ordinals.
         *
         * e.g.
         *     Quadrant I   --> max origin distance: 12.311072
         *     Quadrant II  --> max origin distance: 14.020075
         *     Quadrant III --> max origin distance: 11.559628
         *     Quadrant IV  --> max origin distance: 8.944272
         */

        Map<Quadrant,Optional<Double>> quadrantToOriginDistanceMap =
                maxOriginDistanceByQuadrantUsingLambdaExpr(pointsList);

        System.out.printf("pointsList = %s%n%n", pointsList);

        quadrantToOriginDistanceMap.entrySet().forEach(entry -> {
            Quadrant quadrant = entry.getKey();
            Optional<Double> originDistanceOpt = entry.getValue();

            System.out.printf(Locale.ENGLISH, "Quadrant %-3s --> max origin distance: %f%n",
                    quadrant, originDistanceOpt.orElseGet(() -> null));
        });
    }


    public static Map<Quadrant,Optional<Double>> maxOriginDistanceByQuadrantUsingLambdaExpr(List<Point2D> pointsList) {
        return pointsList.stream()
                .filter(point -> point.isInAQuadrant())     // filter: accepts only points in a quadrant
                .collect(groupingBy(
                        point -> point.getQuadrant(),       // classifier: gets the Quadrant
                        () -> Quadrant.newEnumMap(),        // map factory: creates an EnumMap<Quadrant,Optional<Double>>
                        mapping(                            // downstream: a "mapping" Collector
                                (Point2D point) -> point.getOriginDistance(),   // mapper: maps from point to origin distance
                                maxBy(naturalOrder())                           // downstream: max by origin distance value
                        )
                ));
    }

    public static Map<Quadrant,Optional<Double>> maxOriginDistanceByQuadrantUsingMethodRef(List<Point2D> pointsList) {
        return pointsList.stream()
                .filter(Point2D::isInAQuadrant)         // filter: accepts only points in a quadrant
                .collect(groupingBy(
                        Point2D::getQuadrant,           // classifier: gets the Quadrant
                        Quadrant::newEnumMap,           // map factory: creates an EnumMap<Quadrant,Optional<Double>>
                        mapping(                        // downstream: a "mapping" Collector
                                Point2D::getOriginDistance,     // mapper: maps from point to origin distance
                                maxBy(naturalOrder())           // downstream: max by origin distance value
                        )
                ));
    }
}
