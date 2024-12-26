package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
public class SimpleDeserializers implements Deserializers, Serializable {
    private static final long serialVersionUID = 1;
    protected HashMap<ClassKey, JsonDeserializer<?>> _classMappings = null;
    protected boolean _hasEnumDeserializer = false;

    public SimpleDeserializers() {
    }

    public SimpleDeserializers(Map<Class<?>, JsonDeserializer<?>> map) {
        addDeserializers(map);
    }

    public <T> void addDeserializer(Class<T> cls, JsonDeserializer<? extends T> jsonDeserializer) {
        ClassKey classKey = new ClassKey(cls);
        if (this._classMappings == null) {
            this._classMappings = new HashMap<>();
        }
        this._classMappings.put(classKey, jsonDeserializer);
        if (cls == Enum.class) {
            this._hasEnumDeserializer = true;
        }
    }

    public void addDeserializers(Map<Class<?>, JsonDeserializer<?>> map) {
        for (Map.Entry<Class<?>, JsonDeserializer<?>> entry : map.entrySet()) {
            addDeserializer(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public JsonDeserializer<?> findArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        HashMap<ClassKey, JsonDeserializer<?>> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(arrayType.getRawClass()));
    }

    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        HashMap<ClassKey, JsonDeserializer<?>> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(javaType.getRawClass()));
    }

    @Override
    public JsonDeserializer<?> findCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        HashMap<ClassKey, JsonDeserializer<?>> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(collectionType.getRawClass()));
    }

    @Override
    public JsonDeserializer<?> findCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        HashMap<ClassKey, JsonDeserializer<?>> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(collectionLikeType.getRawClass()));
    }

    @Override
    public JsonDeserializer<?> findEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        HashMap<ClassKey, JsonDeserializer<?>> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        JsonDeserializer<?> jsonDeserializer = hashMap.get(new ClassKey(cls));
        return (jsonDeserializer == null && this._hasEnumDeserializer && cls.isEnum()) ? this._classMappings.get(new ClassKey(Enum.class)) : jsonDeserializer;
    }

    @Override
    public JsonDeserializer<?> findTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        HashMap<ClassKey, JsonDeserializer<?>> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(cls));
    }

    @Override
    public JsonDeserializer<?> findReferenceDeserializer(ReferenceType referenceType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        HashMap<ClassKey, JsonDeserializer<?>> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(referenceType.getRawClass()));
    }

    @Override
    public JsonDeserializer<?> findMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        HashMap<ClassKey, JsonDeserializer<?>> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(mapType.getRawClass()));
    }

    @Override
    public JsonDeserializer<?> findMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        HashMap<ClassKey, JsonDeserializer<?>> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(mapLikeType.getRawClass()));
    }
}
