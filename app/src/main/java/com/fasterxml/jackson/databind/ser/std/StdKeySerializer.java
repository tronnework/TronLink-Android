package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import org.tron.common.crypto.datatypes.Utf8String;
@Deprecated
public class StdKeySerializer extends StdSerializer<Object> {
    public StdKeySerializer() {
        super(Object.class);
    }

    @Override
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String obj2;
        Class<?> cls = obj.getClass();
        if (cls == String.class) {
            obj2 = (String) obj;
        } else if (cls.isEnum()) {
            Enum r3 = (Enum) obj;
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
                obj2 = r3.toString();
            } else {
                obj2 = r3.name();
            }
        } else if (obj instanceof Date) {
            serializerProvider.defaultSerializeDateKey((Date) obj, jsonGenerator);
            return;
        } else if (cls == Class.class) {
            obj2 = ((Class) obj).getName();
        } else {
            obj2 = obj.toString();
        }
        jsonGenerator.writeFieldName(obj2);
    }

    @Override
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        return createSchemaNode(Utf8String.TYPE_NAME);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        visitStringFormat(jsonFormatVisitorWrapper, javaType);
    }
}
