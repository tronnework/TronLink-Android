package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonPointer;
public class JsonPointerBasedFilter extends TokenFilter {
    protected final JsonPointer _pathToMatch;

    @Override
    public TokenFilter filterStartArray() {
        return this;
    }

    @Override
    public TokenFilter filterStartObject() {
        return this;
    }

    public JsonPointerBasedFilter(String str) {
        this(JsonPointer.compile(str));
    }

    public JsonPointerBasedFilter(JsonPointer jsonPointer) {
        this._pathToMatch = jsonPointer;
    }

    @Override
    public TokenFilter includeElement(int i) {
        JsonPointer matchElement = this._pathToMatch.matchElement(i);
        if (matchElement == null) {
            return null;
        }
        if (matchElement.matches()) {
            return TokenFilter.INCLUDE_ALL;
        }
        return new JsonPointerBasedFilter(matchElement);
    }

    @Override
    public TokenFilter includeProperty(String str) {
        JsonPointer matchProperty = this._pathToMatch.matchProperty(str);
        if (matchProperty == null) {
            return null;
        }
        if (matchProperty.matches()) {
            return TokenFilter.INCLUDE_ALL;
        }
        return new JsonPointerBasedFilter(matchProperty);
    }

    @Override
    protected boolean _includeScalar() {
        return this._pathToMatch.matches();
    }

    @Override
    public String toString() {
        return "[JsonPointerFilter at: " + this._pathToMatch + "]";
    }
}
