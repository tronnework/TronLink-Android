package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;
public class AsExternalTypeSerializer extends TypeSerializerBase {
    protected final String _typePropertyName;

    protected final void _writeScalarPrefix(Object obj, JsonGenerator jsonGenerator) throws IOException {
    }

    @Override
    public String getPropertyName() {
        return this._typePropertyName;
    }

    public AsExternalTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        super(typeIdResolver, beanProperty);
        this._typePropertyName = str;
    }

    @Override
    public AsExternalTypeSerializer forProperty(BeanProperty beanProperty) {
        return this._property == beanProperty ? this : new AsExternalTypeSerializer(this._idResolver, beanProperty, this._typePropertyName);
    }

    @Override
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.EXTERNAL_PROPERTY;
    }

    @Override
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException {
        _writeObjectPrefix(obj, jsonGenerator);
    }

    @Override
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException {
        _writeObjectPrefix(obj, jsonGenerator);
    }

    @Override
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException {
        _writeArrayPrefix(obj, jsonGenerator);
    }

    @Override
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException {
        _writeArrayPrefix(obj, jsonGenerator);
    }

    @Override
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException {
        _writeScalarPrefix(obj, jsonGenerator);
    }

    @Override
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException {
        _writeScalarPrefix(obj, jsonGenerator);
    }

    @Override
    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException {
        _writeObjectSuffix(obj, jsonGenerator, idFromValue(obj));
    }

    @Override
    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException {
        _writeArraySuffix(obj, jsonGenerator, idFromValue(obj));
    }

    @Override
    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException {
        _writeScalarSuffix(obj, jsonGenerator, idFromValue(obj));
    }

    @Override
    public void writeCustomTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, String str) throws IOException {
        _writeScalarPrefix(obj, jsonGenerator);
    }

    @Override
    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException {
        _writeObjectPrefix(obj, jsonGenerator);
    }

    @Override
    public void writeCustomTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, String str) throws IOException {
        _writeArrayPrefix(obj, jsonGenerator);
    }

    @Override
    public void writeCustomTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator, String str) throws IOException {
        _writeScalarSuffix(obj, jsonGenerator, str);
    }

    @Override
    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException {
        _writeObjectSuffix(obj, jsonGenerator, str);
    }

    @Override
    public void writeCustomTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator, String str) throws IOException {
        _writeArraySuffix(obj, jsonGenerator, str);
    }

    protected final void _writeObjectPrefix(Object obj, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
    }

    protected final void _writeArrayPrefix(Object obj, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
    }

    protected final void _writeScalarSuffix(Object obj, JsonGenerator jsonGenerator, String str) throws IOException {
        if (str != null) {
            jsonGenerator.writeStringField(this._typePropertyName, str);
        }
    }

    protected final void _writeObjectSuffix(Object obj, JsonGenerator jsonGenerator, String str) throws IOException {
        jsonGenerator.writeEndObject();
        if (str != null) {
            jsonGenerator.writeStringField(this._typePropertyName, str);
        }
    }

    protected final void _writeArraySuffix(Object obj, JsonGenerator jsonGenerator, String str) throws IOException {
        jsonGenerator.writeEndArray();
        if (str != null) {
            jsonGenerator.writeStringField(this._typePropertyName, str);
        }
    }
}
