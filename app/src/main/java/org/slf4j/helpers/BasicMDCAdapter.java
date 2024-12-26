package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;
public class BasicMDCAdapter implements MDCAdapter {
    private InheritableThreadLocal<Map<String, String>> inheritableThreadLocal = new InheritableThreadLocal<Map<String, String>>() {
        @Override
        public Map<String, String> childValue(Map<String, String> map) {
            if (map == null) {
                return null;
            }
            return new HashMap(map);
        }
    };

    @Override
    public void put(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        Map<String, String> map = this.inheritableThreadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            this.inheritableThreadLocal.set(map);
        }
        map.put(str, str2);
    }

    @Override
    public String get(String str) {
        Map<String, String> map = this.inheritableThreadLocal.get();
        if (map == null || str == null) {
            return null;
        }
        return map.get(str);
    }

    @Override
    public void remove(String str) {
        Map<String, String> map = this.inheritableThreadLocal.get();
        if (map != null) {
            map.remove(str);
        }
    }

    @Override
    public void clear() {
        Map<String, String> map = this.inheritableThreadLocal.get();
        if (map != null) {
            map.clear();
            this.inheritableThreadLocal.remove();
        }
    }

    public Set<String> getKeys() {
        Map<String, String> map = this.inheritableThreadLocal.get();
        if (map != null) {
            return map.keySet();
        }
        return null;
    }

    @Override
    public Map<String, String> getCopyOfContextMap() {
        Map<String, String> map = this.inheritableThreadLocal.get();
        if (map != null) {
            return new HashMap(map);
        }
        return null;
    }

    @Override
    public void setContextMap(Map<String, String> map) {
        this.inheritableThreadLocal.set(new HashMap(map));
    }
}
