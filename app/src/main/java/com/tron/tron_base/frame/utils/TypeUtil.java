package com.tron.tron_base.frame.utils;

import java.lang.reflect.ParameterizedType;
public class TypeUtil {
    public static <T> T getT(Object obj, int i) {
        try {
            return (T) ((Class) ((ParameterizedType) obj.getClass().getGenericSuperclass()).getActualTypeArguments()[i]).newInstance();
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }
}
