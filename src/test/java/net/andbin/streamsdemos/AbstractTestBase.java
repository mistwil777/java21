/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos;

public abstract class AbstractTestBase {
    private final String instanceInfo;

    public AbstractTestBase(String instanceInfo) {
        this.instanceInfo = instanceInfo;
    }

    public String getInstanceInfo() {
        return instanceInfo;
    }
}
