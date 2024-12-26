package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
public class ClassNameIdResolver extends TypeIdResolverBase {
    @Override
    public String getDescForKnownTypeIds() {
        return "class name used as type id";
    }

    public void registerSubtype(Class<?> cls, String str) {
    }

    public ClassNameIdResolver(JavaType javaType, TypeFactory typeFactory) {
        super(javaType, typeFactory);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CLASS;
    }

    @Override
    public String idFromValue(Object obj) {
        return _idFrom(obj, obj.getClass(), this._typeFactory);
    }

    @Override
    public String idFromValueAndType(Object obj, Class<?> cls) {
        return _idFrom(obj, cls, this._typeFactory);
    }

    @Override
    public JavaType typeFromId(DatabindContext databindContext, String str) throws IOException {
        return _typeFromId(str, databindContext);
    }

    public JavaType _typeFromId(String str, DatabindContext databindContext) throws IOException {
        TypeFactory typeFactory = databindContext.getTypeFactory();
        if (str.indexOf(60) > 0) {
            return typeFactory.constructFromCanonical(str);
        }
        try {
            return typeFactory.constructSpecializedType(this._baseType, typeFactory.findClass(str));
        } catch (ClassNotFoundException unused) {
            if (databindContext instanceof DeserializationContext) {
                return ((DeserializationContext) databindContext).handleUnknownTypeId(this._baseType, str, this, "no such class found");
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid type id '" + str + "' (for id type 'Id.class'): " + e.getMessage(), e);
        }
    }

    protected final String _idFrom(Object obj, Class<?> cls, TypeFactory typeFactory) {
        if (Enum.class.isAssignableFrom(cls) && !cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        String name = cls.getName();
        if (!name.startsWith("java.util")) {
            return (name.indexOf(36) < 0 || ClassUtil.getOuterClass(cls) == null || ClassUtil.getOuterClass(this._baseType.getRawClass()) != null) ? name : this._baseType.getRawClass().getName();
        } else if (obj instanceof EnumSet) {
            return typeFactory.constructCollectionType(EnumSet.class, ClassUtil.findEnumType((EnumSet) obj)).toCanonical();
        } else {
            if (obj instanceof EnumMap) {
                return typeFactory.constructMapType(EnumMap.class, ClassUtil.findEnumType((EnumMap) obj), Object.class).toCanonical();
            }
            String substring = name.substring(9);
            return ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) ? "java.util.ArrayList" : name;
        }
    }
}
