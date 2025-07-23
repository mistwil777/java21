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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.andbin.streamsdemos.AbstractTestBase;
import net.andbin.streamsdemos.points.datamodel.Point2D;
import net.andbin.streamsdemos.points.datamodel.Quadrant;

public class CountOfPointsByQuadrantTest extends AbstractTestBase {
    private final SUT sut;

    public CountOfPointsByQuadrantTest(String instanceInfo, SUT sut) {
        super(instanceInfo);
        this.sut = sut;
    }

    @Factory
    public static Object[] instances() {
        return new Object[] {
                new CountOfPointsByQuadrantTest("countPointsByQuadrantUsingLambdaExpr",
                        CountOfPointsByQuadrant::countPointsByQuadrantUsingLambdaExpr),
                new CountOfPointsByQuadrantTest("countPointsByQuadrantUsingMethodRef",
                        CountOfPointsByQuadrant::countPointsByQuadrantUsingMethodRef),
        };
    }

    private interface SUT {
        Map<Quadrant,Long> countPointsByQuadrant(List<Point2D> pointsList);
    }


    //--------------------------------------------------------------------------
    // TESTS
    //--------------------------------------------------------------------------

    private static final Point2D P1 = new Point2D(1, 1);        // Quadrant I
    private static final Point2D P2 = new Point2D(-1, 1);       // Quadrant II
    private static final Point2D P3 = new Point2D(-1, -1);      // Quadrant III
    private static final Point2D P4 = new Point2D(1, -1);       // Quadrant IV

    private static final Point2D P5 = new Point2D(0, 0);        // origin
    private static final Point2D P6 = new Point2D(3, 0);        // on X axis
    private static final Point2D P7 = new Point2D(0, 3);        // on Y axis

    private static final Point2D P10 = new Point2D(3, 3);       // Quadrant I
    private static final Point2D P11 = new Point2D(6, 6);       // Quadrant I

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                { Arrays.<Point2D>asList()     , new long[] { 0, 0, 0, 0 } },
                { Arrays.asList(P5, P6, P7)    , new long[] { 0, 0, 0, 0 } },
                { Arrays.asList(P1, P5, P6, P7), new long[] { 1, 0, 0, 0 } },
                { Arrays.asList(P2, P5, P6, P7), new long[] { 0, 1, 0, 0 } },
                { Arrays.asList(P3, P5, P6, P7), new long[] { 0, 0, 1, 0 } },
                { Arrays.asList(P4, P5, P6, P7), new long[] { 0, 0, 0, 1 } },
                { Arrays.asList(P1, P1, P1)    , new long[] { 3, 0, 0, 0 } },
                { Arrays.asList(P1, P10, P11)  , new long[] { 3, 0, 0, 0 } },
        };
    }

    @Test(dataProvider = "testData")
    public void givenPointsShouldReturnExpectedResult(List<Point2D> pointsList, long[] expectedCounts) {
        Map<Quadrant,Long> quadrantToCountMap = sut.countPointsByQuadrant(pointsList);
        assertThat(quadrantToCountMap).isNotNull();

        assertPointsCount(quadrantToCountMap, Quadrant.I, expectedCounts[0]);
        assertPointsCount(quadrantToCountMap, Quadrant.II, expectedCounts[1]);
        assertPointsCount(quadrantToCountMap, Quadrant.III, expectedCounts[2]);
        assertPointsCount(quadrantToCountMap, Quadrant.IV, expectedCounts[3]);
    }


    private static void assertPointsCount(Map<Quadrant,Long> quadrantToCountMap,
            Quadrant quadrant, long expectedCount) {
        if (expectedCount == 0) {
            assertThat(quadrantToCountMap).doesNotContainKey(quadrant);
        } else {
            assertThat(quadrantToCountMap).containsEntry(quadrant, expectedCount);
        }
    }
}
