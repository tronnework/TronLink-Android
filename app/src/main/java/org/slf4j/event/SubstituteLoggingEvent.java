package org.slf4j.event;

import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;
public class SubstituteLoggingEvent implements LoggingEvent {
    Object[] argArray;
    Level level;
    SubstituteLogger logger;
    String loggerName;
    Marker marker;
    String message;
    String threadName;
    Throwable throwable;
    long timeStamp;

    @Override
    public Object[] getArgumentArray() {
        return this.argArray;
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    public SubstituteLogger getLogger() {
        return this.logger;
    }

    @Override
    public String getLoggerName() {
        return this.loggerName;
    }

    @Override
    public Marker getMarker() {
        return this.marker;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getThreadName() {
        return this.threadName;
    }

    @Override
    public Throwable getThrowable() {
        return this.throwable;
    }

    @Override
    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setArgumentArray(Object[] objArr) {
        this.argArray = objArr;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setLogger(SubstituteLogger substituteLogger) {
        this.logger = substituteLogger;
    }

    public void setLoggerName(String str) {
        this.loggerName = str;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setThreadName(String str) {
        this.threadName = str;
    }

    public void setThrowable(Throwable th) {
        this.throwable = th;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }
}
