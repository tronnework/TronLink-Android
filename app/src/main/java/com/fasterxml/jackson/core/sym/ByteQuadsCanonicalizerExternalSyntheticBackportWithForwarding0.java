package com.fasterxml.jackson.core.sym;

import java.util.concurrent.atomic.AtomicReference;
public final class ByteQuadsCanonicalizerExternalSyntheticBackportWithForwarding0 {
    public static boolean m(AtomicReference atomicReference, Object obj, Object obj2) {
        while (!atomicReference.compareAndSet(obj, obj2)) {
            if (atomicReference.get() != obj) {
                return false;
            }
        }
        return true;
    }
}
