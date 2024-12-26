package com.tron.tron_base.frame.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
public class NumberTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        if (type == Double.class || type == Double.TYPE) {
            return new DoubleAdapter();
        }
        if (type == Integer.class || type == Integer.TYPE) {
            return new IntegerAdapter();
        }
        if (type == Long.class || type == Long.TYPE) {
            return new LongAdapter();
        }
        if (type == Float.class || type == Float.TYPE) {
            return new FloatAdapter();
        }
        return null;
    }
}
