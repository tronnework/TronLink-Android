package org.slf4j.helpers;

import java.util.Map;
import org.slf4j.spi.MDCAdapter;
public class NOPMDCAdapter implements MDCAdapter {
    @Override
    public void clear() {
    }

    @Override
    public String get(String str) {
        return null;
    }

    @Override
    public Map<String, String> getCopyOfContextMap() {
        return null;
    }

    @Override
    public void put(String str, String str2) {
    }

    @Override
    public void remove(String str) {
    }

    @Override
    public void setContextMap(Map<String, String> map) {
    }
}
