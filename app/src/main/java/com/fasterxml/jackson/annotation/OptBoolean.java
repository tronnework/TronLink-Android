package com.fasterxml.jackson.annotation;
public enum OptBoolean {
    TRUE,
    FALSE,
    DEFAULT;

    public boolean asPrimitive() {
        return this == TRUE;
    }

    public Boolean asBoolean() {
        if (this == DEFAULT) {
            return null;
        }
        return this == TRUE ? Boolean.TRUE : Boolean.FALSE;
    }

    public static OptBoolean fromBoolean(Boolean bool) {
        return bool == null ? DEFAULT : bool.booleanValue() ? TRUE : FALSE;
    }
}
