package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
public class FilteringParserDelegate extends JsonParserDelegate {
    protected boolean _allowMultipleMatches;
    protected JsonToken _currToken;
    protected TokenFilterContext _exposedContext;
    protected TokenFilterContext _headContext;
    @Deprecated
    protected boolean _includeImmediateParent;
    protected boolean _includePath;
    protected TokenFilter _itemFilter;
    protected JsonToken _lastClearedToken;
    protected int _matchCount;
    protected TokenFilter rootFilter;

    protected JsonStreamContext _filterContext() {
        TokenFilterContext tokenFilterContext = this._exposedContext;
        return tokenFilterContext != null ? tokenFilterContext : this._headContext;
    }

    @Override
    public void clearCurrentToken() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            this._lastClearedToken = jsonToken;
            this._currToken = null;
        }
    }

    @Override
    public JsonToken currentToken() {
        return this._currToken;
    }

    @Override
    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public TokenFilter getFilter() {
        return this.rootFilter;
    }

    @Override
    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    public int getMatchCount() {
        return this._matchCount;
    }

    @Override
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    @Override
    public final boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, boolean z, boolean z2) {
        super(jsonParser);
        this.rootFilter = tokenFilter;
        this._itemFilter = tokenFilter;
        this._headContext = TokenFilterContext.createRootContext(tokenFilter);
        this._includePath = z;
        this._allowMultipleMatches = z2;
    }

    @Override
    public final int getCurrentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override
    public final int currentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override
    public boolean hasTokenId(int i) {
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? i == 0 : jsonToken.id() == i;
    }

    @Override
    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    @Override
    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    @Override
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    @Override
    public JsonStreamContext getParsingContext() {
        return _filterContext();
    }

    @Override
    public String getCurrentName() throws IOException {
        JsonStreamContext _filterContext = _filterContext();
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            JsonStreamContext parent = _filterContext.getParent();
            if (parent == null) {
                return null;
            }
            return parent.getCurrentName();
        }
        return _filterContext.getCurrentName();
    }

    @Override
    public void overrideCurrentName(String str) {
        


return;

//throw new UnsupportedOperationException(
Can not currently override name during filtering read");
    }

    @Override
    public JsonToken nextToken() throws IOException {
        JsonToken _nextTokenWithBuffering;
        JsonToken _nextTokenWithBuffering2;
        JsonToken _nextTokenWithBuffering3;
        TokenFilter checkValue;
        JsonToken jsonToken;
        if (!this._allowMultipleMatches && (jsonToken = this._currToken) != null && this._exposedContext == null) {
            if (jsonToken.isStructEnd() && this._headContext.isStartHandled()) {
                this._currToken = null;
                return null;
            } else if (this._currToken.isScalarValue() && !this._headContext.isStartHandled() && !this._includePath && this._itemFilter == TokenFilter.INCLUDE_ALL) {
                this._currToken = null;
                return null;
            }
        }
        TokenFilterContext tokenFilterContext = this._exposedContext;
        if (tokenFilterContext != null) {
            do {
                JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
                if (nextTokenToRead != null) {
                    this._currToken = nextTokenToRead;
                    return nextTokenToRead;
                }
                TokenFilterContext tokenFilterContext2 = this._headContext;
                if (tokenFilterContext == tokenFilterContext2) {
                    this._exposedContext = null;
                    if (tokenFilterContext.inArray()) {
                        JsonToken currentToken = this.delegate.getCurrentToken();
                        this._currToken = currentToken;
                        return currentToken;
                    }
                } else {
                    tokenFilterContext = tokenFilterContext2.findChildOf(tokenFilterContext);
                    this._exposedContext = tokenFilterContext;
                }
            } while (tokenFilterContext != null);
            throw _constructError("Unexpected problem: chain of filtered context broken");
        }
        JsonToken nextToken = this.delegate.nextToken();
        if (nextToken == null) {
            this._currToken = nextToken;
            return nextToken;
        }
        int id = nextToken.id();
        if (id == 1) {
            TokenFilter tokenFilter = this._itemFilter;
            if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                this._currToken = nextToken;
                return nextToken;
            } else if (tokenFilter == null) {
                this.delegate.skipChildren();
            } else {
                TokenFilter checkValue2 = this._headContext.checkValue(tokenFilter);
                if (checkValue2 == null) {
                    this.delegate.skipChildren();
                } else {
                    if (checkValue2 != TokenFilter.INCLUDE_ALL) {
                        checkValue2 = checkValue2.filterStartObject();
                    }
                    this._itemFilter = checkValue2;
                    if (checkValue2 == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildObjectContext(checkValue2, true);
                        this._currToken = nextToken;
                        return nextToken;
                    }
                    TokenFilterContext createChildObjectContext = this._headContext.createChildObjectContext(checkValue2, false);
                    this._headContext = createChildObjectContext;
                    if (this._includePath && (_nextTokenWithBuffering = _nextTokenWithBuffering(createChildObjectContext)) != null) {
                        this._currToken = _nextTokenWithBuffering;
                        return _nextTokenWithBuffering;
                    }
                }
            }
        } else {
            if (id != 2) {
                if (id == 3) {
                    TokenFilter tokenFilter2 = this._itemFilter;
                    if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildArrayContext(tokenFilter2, true);
                        this._currToken = nextToken;
                        return nextToken;
                    } else if (tokenFilter2 == null) {
                        this.delegate.skipChildren();
                    } else {
                        TokenFilter checkValue3 = this._headContext.checkValue(tokenFilter2);
                        if (checkValue3 == null) {
                            this.delegate.skipChildren();
                        } else {
                            if (checkValue3 != TokenFilter.INCLUDE_ALL) {
                                checkValue3 = checkValue3.filterStartArray();
                            }
                            this._itemFilter = checkValue3;
                            if (checkValue3 == TokenFilter.INCLUDE_ALL) {
                                this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                                this._currToken = nextToken;
                                return nextToken;
                            }
                            TokenFilterContext createChildArrayContext = this._headContext.createChildArrayContext(checkValue3, false);
                            this._headContext = createChildArrayContext;
                            if (this._includePath && (_nextTokenWithBuffering2 = _nextTokenWithBuffering(createChildArrayContext)) != null) {
                                this._currToken = _nextTokenWithBuffering2;
                                return _nextTokenWithBuffering2;
                            }
                        }
                    }
                } else if (id != 4) {
                    if (id == 5) {
                        String currentName = this.delegate.getCurrentName();
                        TokenFilter fieldName = this._headContext.setFieldName(currentName);
                        if (fieldName == TokenFilter.INCLUDE_ALL) {
                            this._itemFilter = fieldName;
                            if (!this._includePath && this._includeImmediateParent && !this._headContext.isStartHandled()) {
                                nextToken = this._headContext.nextTokenToRead();
                                this._exposedContext = this._headContext;
                            }
                            this._currToken = nextToken;
                            return nextToken;
                        } else if (fieldName == null) {
                            this.delegate.nextToken();
                            this.delegate.skipChildren();
                        } else {
                            TokenFilter includeProperty = fieldName.includeProperty(currentName);
                            if (includeProperty == null) {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                            } else {
                                this._itemFilter = includeProperty;
                                if (includeProperty == TokenFilter.INCLUDE_ALL && this._includePath) {
                                    this._currToken = nextToken;
                                    return nextToken;
                                } else if (this._includePath && (_nextTokenWithBuffering3 = _nextTokenWithBuffering(this._headContext)) != null) {
                                    this._currToken = _nextTokenWithBuffering3;
                                    return _nextTokenWithBuffering3;
                                }
                            }
                        }
                    } else {
                        TokenFilter tokenFilter3 = this._itemFilter;
                        if (tokenFilter3 == TokenFilter.INCLUDE_ALL) {
                            this._currToken = nextToken;
                            return nextToken;
                        } else if (tokenFilter3 != null && ((checkValue = this._headContext.checkValue(tokenFilter3)) == TokenFilter.INCLUDE_ALL || (checkValue != null && checkValue.includeValue(this.delegate)))) {
                            this._currToken = nextToken;
                            return nextToken;
                        }
                    }
                }
            }
            boolean isStartHandled = this._headContext.isStartHandled();
            TokenFilter filter = this._headContext.getFilter();
            if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                filter.filterFinishArray();
            }
            TokenFilterContext parent = this._headContext.getParent();
            this._headContext = parent;
            this._itemFilter = parent.getFilter();
            if (isStartHandled) {
                this._currToken = nextToken;
                return nextToken;
            }
        }
        return _nextToken2();
    }

    protected final com.fasterxml.jackson.core.JsonToken _nextToken2() throws java.io.IOException {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.fasterxml.jackson.core.filter.FilteringParserDelegate._nextToken2():com.fasterxml.jackson.core.JsonToken");
    }

    protected final com.fasterxml.jackson.core.JsonToken _nextTokenWithBuffering(com.fasterxml.jackson.core.filter.TokenFilterContext r6) throws java.io.IOException {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.fasterxml.jackson.core.filter.FilteringParserDelegate._nextTokenWithBuffering(com.fasterxml.jackson.core.filter.TokenFilterContext):com.fasterxml.jackson.core.JsonToken");
    }

    private JsonToken _nextBuffered(TokenFilterContext tokenFilterContext) throws IOException {
        this._exposedContext = tokenFilterContext;
        JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
        if (nextTokenToRead != null) {
            return nextTokenToRead;
        }
        while (tokenFilterContext != this._headContext) {
            tokenFilterContext = this._exposedContext.findChildOf(tokenFilterContext);
            this._exposedContext = tokenFilterContext;
            if (tokenFilterContext == null) {
                throw _constructError("Unexpected problem: chain of filtered context broken");
            }
            JsonToken nextTokenToRead2 = tokenFilterContext.nextTokenToRead();
            if (nextTokenToRead2 != null) {
                return nextTokenToRead2;
            }
        }
        throw _constructError("Internal error: failed to locate expected buffered tokens");
    }

    @Override
    public JsonToken nextValue() throws IOException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    @Override
    public JsonParser skipChildren() throws IOException {
        if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            JsonToken nextToken = nextToken();
            if (nextToken == null) {
                return this;
            }
            if (nextToken.isStructStart()) {
                i++;
            } else if (nextToken.isStructEnd() && i - 1 == 0) {
                return this;
            }
        }
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
}
