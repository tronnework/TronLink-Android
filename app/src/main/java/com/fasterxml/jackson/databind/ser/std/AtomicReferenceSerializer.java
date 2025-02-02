package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.util.concurrent.atomic.AtomicReference;
public class AtomicReferenceSerializer extends ReferenceTypeSerializer<AtomicReference<?>> {
    private static final long serialVersionUID = 1;

    @Override
    protected ReferenceTypeSerializer<AtomicReference<?>> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer, NameTransformer nameTransformer, JsonInclude.Include include) {
        return withResolved(beanProperty, typeSerializer, (JsonSerializer<?>) jsonSerializer, nameTransformer, include);
    }

    public AtomicReferenceSerializer(ReferenceType referenceType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super(referenceType, z, typeSerializer, jsonSerializer);
    }

    protected AtomicReferenceSerializer(AtomicReferenceSerializer atomicReferenceSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer, JsonInclude.Include include) {
        super(atomicReferenceSerializer, beanProperty, typeSerializer, jsonSerializer, nameTransformer, include);
    }

    @Override
    protected ReferenceTypeSerializer<AtomicReference<?>> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer, JsonInclude.Include include) {
        return (this._property == beanProperty && include == this._contentInclusion && this._valueTypeSerializer == typeSerializer && this._valueSerializer == jsonSerializer && this._unwrapper == nameTransformer) ? this : new AtomicReferenceSerializer(this, beanProperty, typeSerializer, jsonSerializer, nameTransformer, include);
    }

    @Override
    public boolean _isValueEmpty(AtomicReference<?> atomicReference) {
        return atomicReference.get() == null;
    }

    @Override
    public Object _getReferenced(AtomicReference<?> atomicReference) {
        return atomicReference.get();
    }

    @Override
    public Object _getReferencedIfPresent(AtomicReference<?> atomicReference) {
        return atomicReference.get();
    }
}
