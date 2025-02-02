package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.lang.reflect.Type;
public class StdDelegatingSerializer extends StdSerializer<Object> implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware {
    protected final Converter<Object, ?> _converter;
    protected final JsonSerializer<Object> _delegateSerializer;
    protected final JavaType _delegateType;

    protected Converter<Object, ?> getConverter() {
        return this._converter;
    }

    @Override
    public JsonSerializer<?> getDelegatee() {
        return this._delegateSerializer;
    }

    public StdDelegatingSerializer(Converter<?, ?> converter) {
        super(Object.class);
        this._converter = converter;
        this._delegateType = null;
        this._delegateSerializer = null;
    }

    public <T> StdDelegatingSerializer(Class<T> cls, Converter<T, ?> converter) {
        super(cls, false);
        this._converter = converter;
        this._delegateType = null;
        this._delegateSerializer = null;
    }

    public StdDelegatingSerializer(Converter<Object, ?> converter, JavaType javaType, JsonSerializer<?> jsonSerializer) {
        super(javaType);
        this._converter = converter;
        this._delegateType = javaType;
        this._delegateSerializer = jsonSerializer;
    }

    protected StdDelegatingSerializer withDelegate(Converter<Object, ?> converter, JavaType javaType, JsonSerializer<?> jsonSerializer) {
        if (getClass() != StdDelegatingSerializer.class) {
            throw new IllegalStateException("Sub-class " + getClass().getName() + " must override 'withDelegate'");
        }
        return new StdDelegatingSerializer(converter, javaType, jsonSerializer);
    }

    @Override
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = this._delegateSerializer;
        if (jsonSerializer == null || !(jsonSerializer instanceof ResolvableSerializer)) {
            return;
        }
        ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer = this._delegateSerializer;
        JavaType javaType = this._delegateType;
        if (jsonSerializer == null) {
            if (javaType == null) {
                javaType = this._converter.getOutputType(serializerProvider.getTypeFactory());
            }
            if (!javaType.isJavaLangObject()) {
                jsonSerializer = serializerProvider.findValueSerializer(javaType);
            }
        }
        if (jsonSerializer instanceof ContextualSerializer) {
            jsonSerializer = serializerProvider.handleSecondaryContextualization(jsonSerializer, beanProperty);
        }
        return (jsonSerializer == this._delegateSerializer && javaType == this._delegateType) ? this : withDelegate(this._converter, javaType, jsonSerializer);
    }

    @Override
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Object convertValue = convertValue(obj);
        if (convertValue == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
            return;
        }
        JsonSerializer<Object> jsonSerializer = this._delegateSerializer;
        if (jsonSerializer == null) {
            jsonSerializer = _findSerializer(convertValue, serializerProvider);
        }
        jsonSerializer.serialize(convertValue, jsonGenerator, serializerProvider);
    }

    @Override
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        Object convertValue = convertValue(obj);
        JsonSerializer<Object> jsonSerializer = this._delegateSerializer;
        if (jsonSerializer == null) {
            jsonSerializer = _findSerializer(obj, serializerProvider);
        }
        jsonSerializer.serializeWithType(convertValue, jsonGenerator, serializerProvider, typeSerializer);
    }

    @Override
    @Deprecated
    public boolean isEmpty(Object obj) {
        return isEmpty(null, obj);
    }

    @Override
    public boolean isEmpty(SerializerProvider serializerProvider, Object obj) {
        Object convertValue = convertValue(obj);
        JsonSerializer<Object> jsonSerializer = this._delegateSerializer;
        if (jsonSerializer == null) {
            return obj == null;
        }
        return jsonSerializer.isEmpty(serializerProvider, convertValue);
    }

    @Override
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = this._delegateSerializer;
        if (jsonSerializer instanceof SchemaAware) {
            return ((SchemaAware) jsonSerializer).getSchema(serializerProvider, type);
        }
        return super.getSchema(serializerProvider, type);
    }

    @Override
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type, boolean z) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = this._delegateSerializer;
        if (jsonSerializer instanceof SchemaAware) {
            return ((SchemaAware) jsonSerializer).getSchema(serializerProvider, type, z);
        }
        return super.getSchema(serializerProvider, type);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = this._delegateSerializer;
        if (jsonSerializer != null) {
            jsonSerializer.acceptJsonFormatVisitor(jsonFormatVisitorWrapper, javaType);
        }
    }

    protected Object convertValue(Object obj) {
        return this._converter.convert(obj);
    }

    protected JsonSerializer<Object> _findSerializer(Object obj, SerializerProvider serializerProvider) throws JsonMappingException {
        return serializerProvider.findValueSerializer(obj.getClass());
    }
}
