package org.slf4j.event;

import com.google.firebase.perf.FirebasePerformance;
public enum Level {
    ERROR(40, "ERROR"),
    WARN(30, "WARN"),
    INFO(20, "INFO"),
    DEBUG(10, "DEBUG"),
    TRACE(0, FirebasePerformance.HttpMethod.TRACE);
    
    private int levelInt;
    private String levelStr;

    public int toInt() {
        return this.levelInt;
    }

    @Override
    public String toString() {
        return this.levelStr;
    }

    Level(int i, String str) {
        this.levelInt = i;
        this.levelStr = str;
    }
}
