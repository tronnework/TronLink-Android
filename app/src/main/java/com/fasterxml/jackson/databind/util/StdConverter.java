package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
public abstract class StdConverter<IN, OUT> implements Converter<IN, OUT> {
    @Override
    public abstract OUT convert(IN in);

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return _findConverterType(typeFactory).containedType(0);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return _findConverterType(typeFactory).containedType(1);
    }

    protected JavaType _findConverterType(TypeFactory typeFactory) {
        JavaType findSuperType = typeFactory.constructType(getClass()).findSuperType(Converter.class);
        if (findSuperType == null || findSuperType.containedTypeCount() < 2) {
            throw new IllegalStateException("Can not find OUT type parameter for Converter of type " + getClass().getName());
        }
        return findSuperType;
    }
}
