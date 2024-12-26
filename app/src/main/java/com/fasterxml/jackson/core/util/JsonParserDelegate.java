package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
public class JsonParserDelegate extends JsonParser {
    protected JsonParser delegate;

    public JsonParserDelegate(JsonParser jsonParser) {
        this.delegate = jsonParser;
    }

    @Override
    public Object getCurrentValue() {
        return this.delegate.getCurrentValue();
    }

    @Override
    public void setCurrentValue(Object obj) {
        this.delegate.setCurrentValue(obj);
    }

    @Override
    public void setCodec(ObjectCodec objectCodec) {
        this.delegate.setCodec(objectCodec);
    }

    @Override
    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    @Override
    public JsonParser enable(JsonParser.Feature feature) {
        this.delegate.enable(feature);
        return this;
    }

    @Override
    public JsonParser disable(JsonParser.Feature feature) {
        this.delegate.disable(feature);
        return this;
    }

    @Override
    public boolean isEnabled(JsonParser.Feature feature) {
        return this.delegate.isEnabled(feature);
    }

    @Override
    public int getFeatureMask() {
        return this.delegate.getFeatureMask();
    }

    @Override
    @Deprecated
    public JsonParser setFeatureMask(int i) {
        this.delegate.setFeatureMask(i);
        return this;
    }

    @Override
    public JsonParser overrideStdFeatures(int i, int i2) {
        this.delegate.overrideStdFeatures(i, i2);
        return this;
    }

    @Override
    public JsonParser overrideFormatFeatures(int i, int i2) {
        this.delegate.overrideFormatFeatures(i, i2);
        return this;
    }

    @Override
    public FormatSchema getSchema() {
        return this.delegate.getSchema();
    }

    @Override
    public void setSchema(FormatSchema formatSchema) {
        this.delegate.setSchema(formatSchema);
    }

    @Override
    public boolean canUseSchema(FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }

    @Override
    public Version version() {
        return this.delegate.version();
    }

    @Override
    public Object getInputSource() {
        return this.delegate.getInputSource();
    }

    @Override
    public boolean requiresCustomCodec() {
        return this.delegate.requiresCustomCodec();
    }

    @Override
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override
    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    @Override
    public JsonToken currentToken() {
        return this.delegate.currentToken();
    }

    @Override
    public int currentTokenId() {
        return this.delegate.currentTokenId();
    }

    @Override
    public JsonToken getCurrentToken() {
        return this.delegate.getCurrentToken();
    }

    @Override
    public int getCurrentTokenId() {
        return this.delegate.getCurrentTokenId();
    }

    @Override
    public boolean hasCurrentToken() {
        return this.delegate.hasCurrentToken();
    }

    @Override
    public boolean hasTokenId(int i) {
        return this.delegate.hasTokenId(i);
    }

    @Override
    public boolean hasToken(JsonToken jsonToken) {
        return this.delegate.hasToken(jsonToken);
    }

    @Override
    public String getCurrentName() throws IOException {
        return this.delegate.getCurrentName();
    }

    @Override
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    @Override
    public JsonStreamContext getParsingContext() {
        return this.delegate.getParsingContext();
    }

    @Override
    public boolean isExpectedStartArrayToken() {
        return this.delegate.isExpectedStartArrayToken();
    }

    @Override
    public boolean isExpectedStartObjectToken() {
        return this.delegate.isExpectedStartObjectToken();
    }

    @Override
    public void clearCurrentToken() {
        this.delegate.clearCurrentToken();
    }

    @Override
    public JsonToken getLastClearedToken() {
        return this.delegate.getLastClearedToken();
    }

    @Override
    public void overrideCurrentName(String str) {
        this.delegate.overrideCurrentName(str);
    }

    @Override
    public String getText() throws IOException {
        return this.delegate.getText();
    }

    @Override
    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }

    @Override
    public char[] getTextCharacters() throws IOException {
        return this.delegate.getTextCharacters();
    }

    @Override
    public int getTextLength() throws IOException {
        return this.delegate.getTextLength();
    }

    @Override
    public int getTextOffset() throws IOException {
        return this.delegate.getTextOffset();
    }

    @Override
    public int getText(Writer writer) throws IOException, UnsupportedOperationException {
        return this.delegate.getText(writer);
    }

    @Override
    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }

    @Override
    public boolean getBooleanValue() throws IOException {
        return this.delegate.getBooleanValue();
    }

    @Override
    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }

    @Override
    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }

    @Override
    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }

    @Override
    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }

    @Override
    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }

    @Override
    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }

    @Override
    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }

    @Override
    public JsonParser.NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }

    @Override
    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }

    @Override
    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }

    @Override
    public int getValueAsInt(int i) throws IOException {
        return this.delegate.getValueAsInt(i);
    }

    @Override
    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }

    @Override
    public long getValueAsLong(long j) throws IOException {
        return this.delegate.getValueAsLong(j);
    }

    @Override
    public double getValueAsDouble() throws IOException {
        return this.delegate.getValueAsDouble();
    }

    @Override
    public double getValueAsDouble(double d) throws IOException {
        return this.delegate.getValueAsDouble(d);
    }

    @Override
    public boolean getValueAsBoolean() throws IOException {
        return this.delegate.getValueAsBoolean();
    }

    @Override
    public boolean getValueAsBoolean(boolean z) throws IOException {
        return this.delegate.getValueAsBoolean(z);
    }

    @Override
    public String getValueAsString() throws IOException {
        return this.delegate.getValueAsString();
    }

    @Override
    public String getValueAsString(String str) throws IOException {
        return this.delegate.getValueAsString(str);
    }

    @Override
    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }

    @Override
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    @Override
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }

    @Override
    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    @Override
    public JsonToken nextToken() throws IOException {
        return this.delegate.nextToken();
    }

    @Override
    public JsonToken nextValue() throws IOException {
        return this.delegate.nextValue();
    }

    @Override
    public void finishToken() throws IOException {
        this.delegate.finishToken();
    }

    @Override
    public JsonParser skipChildren() throws IOException {
        this.delegate.skipChildren();
        return this;
    }

    @Override
    public boolean canReadObjectId() {
        return this.delegate.canReadObjectId();
    }

    @Override
    public boolean canReadTypeId() {
        return this.delegate.canReadTypeId();
    }

    @Override
    public Object getObjectId() throws IOException {
        return this.delegate.getObjectId();
    }

    @Override
    public Object getTypeId() throws IOException {
        return this.delegate.getTypeId();
    }
}
