/*
 * Copyright (C) 2017 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.points.datamodel;

import java.util.Arrays;
import java.util.List;

public class PointsData {
    private PointsData() {}

    /*
     * This Point2D data samples contains:
     *   - 13 points in Quadrant I
     *   - 9  points in Quadrant II
     *   - 10 points in Quadrant III
     *   - 7  points in Quadrant IV
     *   - 1  point in the origin
     *   - 2  points on X axis (y=0)
     *   - 2  points on Y axis (x=0)
     */
    public static List<Point2D> getSamples() {
        return Arrays.asList(
                new Point2D( +7   , +3.25),     // Quadrant I
                new Point2D(-10   , +1   ),     // Quadrant II
                new Point2D( +8   , -4   ),     // Quadrant IV
                new Point2D(  0   ,  0   ),     // origin
                new Point2D( -3.75, +3   ),     // Quadrant II
                new Point2D( -9.25, +4.5 ),     // Quadrant II
                new Point2D( +4.5 , +3.5 ),     // Quadrant I
                new Point2D( +0.75, +2.5 ),     // Quadrant I
                new Point2D(  0   , -6   ),     // Y axis
                new Point2D( -1.5 , +1.75),     // Quadrant II
                new Point2D( +6.5 , +5.5 ),     // Quadrant I
                new Point2D(+10   , +2   ),     // Quadrant I
                new Point2D( -6.5 ,  0   ),     // X axis
                new Point2D( +9.25, +4.75),     // Quadrant I
                new Point2D( +3.5 , -2   ),     // Quadrant IV
                new Point2D( -1.75, -3   ),     // Quadrant III
                new Point2D(+10.75, +6   ),     // Quadrant I
                new Point2D( +5.75, -3.25),     // Quadrant IV
                new Point2D( -7   , -3   ),     // Quadrant III
                new Point2D( +4   ,  0   ),     // X axis
                new Point2D( -2.25, +6.75),     // Quadrant II
                new Point2D( -9   , -1.5 ),     // Quadrant III
                new Point2D( -5   , +5.5 ),     // Quadrant II
                new Point2D( -7   , +2.5 ),     // Quadrant II
                new Point2D( -8   , -6.5 ),     // Quadrant III
                new Point2D( +2.25, +1.25),     // Quadrant I
                new Point2D(-12   , +7.25),     // Quadrant II
                new Point2D( +4.5 , -5.75),     // Quadrant IV
                new Point2D( +5.5 , +1.5 ),     // Quadrant I
                new Point2D( +1   , -1   ),     // Quadrant IV
                new Point2D( +7   , -1.5 ),     // Quadrant IV
                new Point2D(  0   , +5.25),     // Y axis
                new Point2D( -2.5 , -5   ),     // Quadrant III
                new Point2D( -4   , -7.5 ),     // Quadrant III
                new Point2D( -7.25, +7.5 ),     // Quadrant II
                new Point2D( +3   , +4.5 ),     // Quadrant I
                new Point2D( +8.5 , +8   ),     // Quadrant I
                new Point2D(-10.75, -4.25),     // Quadrant III
                new Point2D( +8   , +0.75),     // Quadrant I
                new Point2D( -3   , -1.75),     // Quadrant III
                new Point2D( -4.5 , -3.5 ),     // Quadrant III
                new Point2D( +3.5 , +6.75),     // Quadrant I
                new Point2D( +2   , -4   ),     // Quadrant IV
                new Point2D( -5.5 , -5.5 )      // Quadrant III
        );
    }
}
