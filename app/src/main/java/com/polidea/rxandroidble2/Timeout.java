package com.polidea.rxandroidble2;

import java.util.concurrent.TimeUnit;
public class Timeout {
    public final TimeUnit timeUnit;
    public final long timeout;

    public Timeout(long j, TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        this.timeout = j;
    }
}
