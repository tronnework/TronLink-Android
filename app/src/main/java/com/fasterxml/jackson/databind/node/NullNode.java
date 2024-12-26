package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
public final class NullNode extends ValueNode {
    public static final NullNode instance = new NullNode();

    public static NullNode getInstance() {
        return instance;
    }

    @Override
    public String asText() {
        return "null";
    }

    @Override
    public String asText(String str) {
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    private NullNode() {
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.NULL;
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.VALUE_NULL;
    }

    @Override
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        serializerProvider.defaultSerializeNull(jsonGenerator);
    }

    @Override
    public int hashCode() {
        return JsonNodeType.NULL.ordinal();
    }
}
