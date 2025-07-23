/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.points.datamodel;

import java.util.EnumMap;

public enum Quadrant {
    I, II, III, IV;

    // Useful factory method for use with method references.
    public static <V> EnumMap<Quadrant,V> newEnumMap() {
        return new EnumMap<>(Quadrant.class);
    }
}
