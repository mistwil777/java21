/*
 * Copyright (C) 2016-2017 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.points.datamodel;

import java.text.NumberFormat;
import java.util.Locale;

public class Point2D {
    private final double x;
    private final double y;
    private final Quadrant quadrant;
    private final double originDistance;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
        quadrant = calculateQuadrant(x, y);
        originDistance = calculateOriginDistance(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Quadrant getQuadrant() {
        return quadrant;
    }

    public double getOriginDistance() {
        return originDistance;
    }

    public boolean isInAQuadrant() {
        return quadrant != null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }

        Point2D otherPoint = (Point2D) otherObject;

        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(otherPoint.x)) {
            return false;
        }

        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(otherPoint.y)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        nf.setMinimumFractionDigits(0);

        return "(" + nf.format(x) + "," + nf.format(y) + ")";
    }


    // Utility methods

    public static Quadrant calculateQuadrant(double x, double y) {
        if (y > 0) {
            if (x > 0) {
                return Quadrant.I;
            } else if (x < 0) {
                return Quadrant.II;
            }
        } else if (y < 0) {
            if (x < 0) {
                return Quadrant.III;
            } else if (x > 0) {
                return Quadrant.IV;
            }
        }

        return null;  // in the origin or exactly on X or Y axis
    }

    public static double calculateOriginDistance(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }
}
