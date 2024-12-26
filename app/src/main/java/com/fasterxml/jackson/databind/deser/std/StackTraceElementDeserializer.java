package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;
public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    private static final long serialVersionUID = 1;

    public StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    @Override
    public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT) {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                StackTraceElement deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                }
                return deserialize;
            }
            return (StackTraceElement) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }
        String str = "";
        String str2 = null;
        String str3 = null;
        String str4 = "";
        String str5 = str4;
        int i = -1;
        while (true) {
            JsonToken nextValue = jsonParser.nextValue();
            if (nextValue != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                if ("className".equals(currentName)) {
                    str = jsonParser.getText();
                } else if ("fileName".equals(currentName)) {
                    str5 = jsonParser.getText();
                } else if ("lineNumber".equals(currentName)) {
                    if (!nextValue.isNumeric()) {
                        return (StackTraceElement) deserializationContext.handleUnexpectedToken(handledType(), nextValue, jsonParser, "Non-numeric token (%s) for property 'lineNumber'", nextValue);
                    }
                    i = jsonParser.getIntValue();
                } else if ("methodName".equals(currentName)) {
                    str4 = jsonParser.getText();
                } else if (!"nativeMethod".equals(currentName)) {
                    if ("moduleName".equals(currentName)) {
                        str2 = jsonParser.getText();
                    } else if ("moduleVersion".equals(currentName)) {
                        str3 = jsonParser.getText();
                    } else {
                        handleUnknownProperty(jsonParser, deserializationContext, this._valueClass, currentName);
                    }
                }
            } else {
                return constructValue(deserializationContext, str, str4, str5, i, str2, str3);
            }
        }
    }

    protected StackTraceElement constructValue(DeserializationContext deserializationContext, String str, String str2, String str3, int i, String str4, String str5) {
        return new StackTraceElement(str, str2, str3, i);
    }
}
