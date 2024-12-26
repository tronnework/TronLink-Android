package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Iterator;
@JacksonStdImpl
public class IterableSerializer extends AsArraySerializerBase<Iterable<?>> {
    @Override
    public AsArraySerializerBase<Iterable<?>> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer, Boolean bool) {
        return withResolved(beanProperty, typeSerializer, (JsonSerializer<?>) jsonSerializer, bool);
    }

    public IterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        super(Iterable.class, javaType, z, typeSerializer, (JsonSerializer<Object>) null);
    }

    public IterableSerializer(IterableSerializer iterableSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super(iterableSerializer, beanProperty, typeSerializer, jsonSerializer, bool);
    }

    @Override
    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new IterableSerializer(this, this._property, typeSerializer, this._elementSerializer, this._unwrapSingle);
    }

    @Override
    public AsArraySerializerBase<Iterable<?>> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        return new IterableSerializer(this, beanProperty, typeSerializer, jsonSerializer, bool);
    }

    @Override
    public boolean isEmpty(SerializerProvider serializerProvider, Iterable<?> iterable) {
        return iterable == null || !iterable.iterator().hasNext();
    }

    @Override
    public boolean hasSingleElement(Iterable<?> iterable) {
        if (iterable != null) {
            Iterator<?> it = iterable.iterator();
            if (it.hasNext()) {
                it.next();
                return !it.hasNext();
            }
            return false;
        }
        return false;
    }

    @Override
    public final void serialize(Iterable<?> iterable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (((this._unwrapSingle == null && serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this._unwrapSingle == Boolean.TRUE) && hasSingleElement(iterable)) {
            serializeContents(iterable, jsonGenerator, serializerProvider);
            return;
        }
        jsonGenerator.writeStartArray();
        serializeContents(iterable, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndArray();
    }

    @Override
    public void serializeContents(Iterable<?> iterable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JsonSerializer<Object> jsonSerializer;
        Iterator<?> it = iterable.iterator();
        if (it.hasNext()) {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            Class<?> cls = null;
            JsonSerializer<Object> jsonSerializer2 = null;
            do {
                Object next = it.next();
                if (next == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    JsonSerializer<Object> jsonSerializer3 = this._elementSerializer;
                    if (jsonSerializer3 == null) {
                        Class<?> cls2 = next.getClass();
                        if (cls2 != cls) {
                            jsonSerializer2 = serializerProvider.findValueSerializer(cls2, this._property);
                            cls = cls2;
                        }
                        jsonSerializer = jsonSerializer2;
                    } else {
                        jsonSerializer = jsonSerializer2;
                        jsonSerializer2 = jsonSerializer3;
                    }
                    if (typeSerializer == null) {
                        jsonSerializer2.serialize(next, jsonGenerator, serializerProvider);
                    } else {
                        jsonSerializer2.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                    }
                    jsonSerializer2 = jsonSerializer;
                }
            } while (it.hasNext());
        }
    }
}
