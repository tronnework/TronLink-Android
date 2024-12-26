package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import java.io.IOException;
import java.lang.reflect.Type;
import org.tron.common.crypto.datatypes.Utf8String;
@JacksonStdImpl
public final class StringSerializer extends NonTypedScalarSerializerBase<Object> {
    private static final long serialVersionUID = 1;

    public StringSerializer() {
        super(String.class, false);
    }

    @Override
    @Deprecated
    public boolean isEmpty(Object obj) {
        String str = (String) obj;
        return str == null || str.length() == 0;
    }

    @Override
    public boolean isEmpty(SerializerProvider serializerProvider, Object obj) {
        String str = (String) obj;
        return str == null || str.length() == 0;
    }

    @Override
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString((String) obj);
    }

    @Override
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode(Utf8String.TYPE_NAME, true);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        visitStringFormat(jsonFormatVisitorWrapper, javaType);
    }
}
