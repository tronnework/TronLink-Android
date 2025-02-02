package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
public final class InnerClassProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected AnnotatedConstructor _annotated;
    protected final transient Constructor<?> _creator;
    protected final SettableBeanProperty _delegate;

    @Override
    public SettableBeanProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
        return withValueDeserializer((JsonDeserializer<?>) jsonDeserializer);
    }

    public InnerClassProperty(SettableBeanProperty settableBeanProperty, Constructor<?> constructor) {
        super(settableBeanProperty);
        this._delegate = settableBeanProperty;
        this._creator = constructor;
    }

    protected InnerClassProperty(InnerClassProperty innerClassProperty, AnnotatedConstructor annotatedConstructor) {
        super(innerClassProperty);
        this._delegate = innerClassProperty._delegate;
        this._annotated = annotatedConstructor;
        Constructor<?> annotated = annotatedConstructor == null ? null : annotatedConstructor.getAnnotated();
        this._creator = annotated;
        if (annotated == null) {
            throw new IllegalArgumentException("Missing constructor (broken JDK (de)serialization?)");
        }
    }

    protected InnerClassProperty(InnerClassProperty innerClassProperty, JsonDeserializer<?> jsonDeserializer) {
        super(innerClassProperty, jsonDeserializer);
        this._delegate = innerClassProperty._delegate.withValueDeserializer(jsonDeserializer);
        this._creator = innerClassProperty._creator;
    }

    protected InnerClassProperty(InnerClassProperty innerClassProperty, PropertyName propertyName) {
        super(innerClassProperty, propertyName);
        this._delegate = innerClassProperty._delegate.withName(propertyName);
        this._creator = innerClassProperty._creator;
    }

    @Override
    public InnerClassProperty withName(PropertyName propertyName) {
        return new InnerClassProperty(this, propertyName);
    }

    @Override
    public InnerClassProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new InnerClassProperty(this, jsonDeserializer);
    }

    @Override
    public void fixAccess(DeserializationConfig deserializationConfig) {
        this._delegate.fixAccess(deserializationConfig);
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this._delegate.getAnnotation(cls);
    }

    @Override
    public AnnotatedMember getMember() {
        return this._delegate.getMember();
    }

    @Override
    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        Object obj2;
        Object obj3;
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            obj3 = this._valueDeserializer.getNullValue(deserializationContext);
        } else if (this._valueTypeDeserializer != null) {
            obj3 = this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer);
        } else {
            try {
                obj2 = this._creator.newInstance(obj);
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e, "Failed to instantiate class " + this._creator.getDeclaringClass().getName() + ", problem: " + e.getMessage());
                obj2 = null;
            }
            this._valueDeserializer.deserialize(jsonParser, deserializationContext, obj2);
            obj3 = obj2;
        }
        set(obj, obj3);
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    @Override
    public final void set(Object obj, Object obj2) throws IOException {
        this._delegate.set(obj, obj2);
    }

    @Override
    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        return this._delegate.setAndReturn(obj, obj2);
    }

    Object readResolve() {
        return new InnerClassProperty(this, this._annotated);
    }

    Object writeReplace() {
        return this._annotated != null ? this : new InnerClassProperty(this, new AnnotatedConstructor(null, this._creator, null, null));
    }
}
