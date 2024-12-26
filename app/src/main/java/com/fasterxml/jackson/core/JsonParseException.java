package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.RequestPayload;
public class JsonParseException extends JsonProcessingException {
    private static final long serialVersionUID = 2;
    protected transient JsonParser _processor;
    protected RequestPayload _requestPayload;

    @Override
    public JsonParser getProcessor() {
        return this._processor;
    }

    public RequestPayload getRequestPayload() {
        return this._requestPayload;
    }

    public JsonParseException withParser(JsonParser jsonParser) {
        this._processor = jsonParser;
        return this;
    }

    public JsonParseException withRequestPayload(RequestPayload requestPayload) {
        this._requestPayload = requestPayload;
        return this;
    }

    @Deprecated
    public JsonParseException(String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }

    @Deprecated
    public JsonParseException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
    }

    public JsonParseException(JsonParser jsonParser, String str) {
        super(str, jsonParser == null ? null : jsonParser.getCurrentLocation());
        this._processor = jsonParser;
    }

    public JsonParseException(JsonParser jsonParser, String str, Throwable th) {
        super(str, jsonParser == null ? null : jsonParser.getCurrentLocation(), th);
        this._processor = jsonParser;
    }

    public JsonParseException(JsonParser jsonParser, String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
        this._processor = jsonParser;
    }

    public JsonParseException(JsonParser jsonParser, String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
        this._processor = jsonParser;
    }

    public String getRequestPayloadAsString() {
        RequestPayload requestPayload = this._requestPayload;
        if (requestPayload != null) {
            return requestPayload.toString();
        }
        return null;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (this._requestPayload != null) {
            return message + "\nRequest payload : " + this._requestPayload.toString();
        }
        return message;
    }
}
