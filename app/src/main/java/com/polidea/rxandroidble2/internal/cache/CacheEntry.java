package com.polidea.rxandroidble2.internal.cache;

import com.polidea.rxandroidble2.internal.DeviceComponent;
import java.util.Map;
class CacheEntry implements Map.Entry<String, DeviceComponent> {
    private final DeviceComponentWeakReference deviceComponentWeakReference;
    private final String string;

    @Override
    public String getKey() {
        return this.string;
    }

    public CacheEntry(String str, DeviceComponentWeakReference deviceComponentWeakReference) {
        this.string = str;
        this.deviceComponentWeakReference = deviceComponentWeakReference;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CacheEntry) {
            CacheEntry cacheEntry = (CacheEntry) obj;
            return this.string.equals(cacheEntry.getKey()) && this.deviceComponentWeakReference.equals(cacheEntry.deviceComponentWeakReference);
        }
        return false;
    }

    @Override
    public DeviceComponent getValue() {
        return (DeviceComponent) this.deviceComponentWeakReference.get();
    }

    @Override
    public int hashCode() {
        return (this.string.hashCode() * 31) + this.deviceComponentWeakReference.hashCode();
    }

    @Override
    public DeviceComponent setValue(DeviceComponent deviceComponent) {
        


return null;

//throw new UnsupportedOperationException(
Not implemented");
    }
}
