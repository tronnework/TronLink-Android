package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
public class FailingDeserializer extends StdDeserializer<Object> {
    private static final long serialVersionUID = 1;
    protected final String _message;

    public FailingDeserializer(String str) {
        super(Object.class);
        this._message = str;
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        deserializationContext.reportMappingException(this._message, new Object[0]);
        return null;
    }
}
