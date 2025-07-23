/*
 * Copyright (C) 2017 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.points;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.andbin.streamsdemos.AbstractTestBase;
import net.andbin.streamsdemos.points.datamodel.Point2D;
import net.andbin.streamsdemos.points.datamodel.Quadrant;

public class MaxOriginDistanceByQuadrantTest extends AbstractTestBase {
    private final SUT sut;

    public MaxOriginDistanceByQuadrantTest(String instanceInfo, SUT sut) {
        super(instanceInfo);
        this.sut = sut;
    }

    @Factory
    public static Object[] instances() {
        return new Object[] {
                new MaxOriginDistanceByQuadrantTest("maxOriginDistanceByQuadrantUsingLambdaExpr",
                        MaxOriginDistanceByQuadrant::maxOriginDistanceByQuadrantUsingLambdaExpr),
                new MaxOriginDistanceByQuadrantTest("maxOriginDistanceByQuadrantUsingMethodRef",
                        MaxOriginDistanceByQuadrant::maxOriginDistanceByQuadrantUsingMethodRef),
        };
    }

    private interface SUT {
        Map<Quadrant,Optional<Double>> maxOriginDistanceByQuadrant(List<Point2D> pointsList);
    }


    //--------------------------------------------------------------------------
    // TESTS
    //--------------------------------------------------------------------------

    private static final Point2D PO = new Point2D(0, 0);        // origin
    private static final Point2D PX = new Point2D(3, 0);        // on X axis
    private static final Point2D PY = new Point2D(0, 3);        // on Y axis

    private static final Point2D P1 = new Point2D(1.5, 2);      // Quadrant I
    private static final Point2D P2 = new Point2D(-3, 4);       // Quadrant II
    private static final Point2D P3 = new Point2D(-4.5, -6);    // Quadrant III
    private static final Point2D P4 = new Point2D(6, -8);       // Quadrant IV

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                { Arrays.<Point2D>asList()     , new Double[] { null, null, null, null } },
                { Arrays.asList(PO, PX, PY)    , new Double[] { null, null, null, null } },
                { Arrays.asList(P1, P2, P3, P4), new Double[] { 2.5 , 5.0 , 7.5 , 10.0 } },
        };
    }

    @Test(dataProvider = "testData")
    public void givenPointsShouldReturnExpectedResult(List<Point2D> pointsList, Double[] expectedDistances) {
        Map<Quadrant,Optional<Double>> quadrantToOriginDistanceMap = sut.maxOriginDistanceByQuadrant(pointsList);
        assertThat(quadrantToOriginDistanceMap).isNotNull();

        assertOriginDistance(quadrantToOriginDistanceMap, Quadrant.I, expectedDistances[0]);
        assertOriginDistance(quadrantToOriginDistanceMap, Quadrant.II, expectedDistances[1]);
        assertOriginDistance(quadrantToOriginDistanceMap, Quadrant.III, expectedDistances[2]);
        assertOriginDistance(quadrantToOriginDistanceMap, Quadrant.IV, expectedDistances[3]);
    }


    private static void assertOriginDistance(Map<Quadrant,Optional<Double>> quadrantToOriginDistanceMap,
            Quadrant quadrant, Double expectedDistance) {
        if (expectedDistance == null) {
            assertThat(quadrantToOriginDistanceMap).doesNotContainKey(quadrant);
        } else {
            assertThat(quadrantToOriginDistanceMap).containsKey(quadrant);
            assertThat(quadrantToOriginDistanceMap.get(quadrant)).hasValue(expectedDistance);
        }
    }
}
