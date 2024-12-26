package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.io.CharTypes;
import kotlin.text.Typography;
public final class JsonReadContext extends JsonStreamContext {
    protected JsonReadContext _child;
    protected int _columnNr;
    protected String _currentName;
    protected Object _currentValue;
    protected DupDetector _dups;
    protected int _lineNr;
    protected final JsonReadContext _parent;

    public JsonReadContext clearAndGetParent() {
        this._currentValue = null;
        return this._parent;
    }

    @Override
    public String getCurrentName() {
        return this._currentName;
    }

    @Override
    public Object getCurrentValue() {
        return this._currentValue;
    }

    public DupDetector getDupDetector() {
        return this._dups;
    }

    @Override
    public JsonReadContext getParent() {
        return this._parent;
    }

    @Override
    public void setCurrentValue(Object obj) {
        this._currentValue = obj;
    }

    public JsonReadContext withDupDetector(DupDetector dupDetector) {
        this._dups = dupDetector;
        return this;
    }

    public JsonReadContext(JsonReadContext jsonReadContext, DupDetector dupDetector, int i, int i2, int i3) {
        this._parent = jsonReadContext;
        this._dups = dupDetector;
        this._type = i;
        this._lineNr = i2;
        this._columnNr = i3;
        this._index = -1;
    }

    protected void reset(int i, int i2, int i3) {
        this._type = i;
        this._index = -1;
        this._lineNr = i2;
        this._columnNr = i3;
        this._currentName = null;
        this._currentValue = null;
        DupDetector dupDetector = this._dups;
        if (dupDetector != null) {
            dupDetector.reset();
        }
    }

    public static JsonReadContext createRootContext(int i, int i2, DupDetector dupDetector) {
        return new JsonReadContext(null, dupDetector, 0, i, i2);
    }

    public static JsonReadContext createRootContext(DupDetector dupDetector) {
        return new JsonReadContext(null, dupDetector, 0, 1, 0);
    }

    public JsonReadContext createChildArrayContext(int i, int i2) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            DupDetector dupDetector = this._dups;
            jsonReadContext = new JsonReadContext(this, dupDetector == null ? null : dupDetector.child(), 1, i, i2);
            this._child = jsonReadContext;
        } else {
            jsonReadContext.reset(1, i, i2);
        }
        return jsonReadContext;
    }

    public JsonReadContext createChildObjectContext(int i, int i2) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            DupDetector dupDetector = this._dups;
            JsonReadContext jsonReadContext2 = new JsonReadContext(this, dupDetector == null ? null : dupDetector.child(), 2, i, i2);
            this._child = jsonReadContext2;
            return jsonReadContext2;
        }
        jsonReadContext.reset(2, i, i2);
        return jsonReadContext;
    }

    public JsonLocation getStartLocation(Object obj) {
        return new JsonLocation(obj, -1L, this._lineNr, this._columnNr);
    }

    public boolean expectComma() {
        int i = this._index + 1;
        this._index = i;
        return this._type != 0 && i > 0;
    }

    public void setCurrentName(String str) throws JsonProcessingException {
        this._currentName = str;
        DupDetector dupDetector = this._dups;
        if (dupDetector != null) {
            _checkDup(dupDetector, str);
        }
    }

    private void _checkDup(DupDetector dupDetector, String str) throws JsonProcessingException {
        if (dupDetector.isDup(str)) {
            Object source = dupDetector.getSource();
            JsonParser jsonParser = source instanceof JsonParser ? (JsonParser) source : null;
            throw new JsonParseException(jsonParser, "Duplicate field '" + str + "'");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        int i = this._type;
        if (i == 0) {
            sb.append("/");
        } else if (i == 1) {
            sb.append('[');
            sb.append(getCurrentIndex());
            sb.append(']');
        } else {
            sb.append('{');
            if (this._currentName != null) {
                sb.append(Typography.quote);
                CharTypes.appendQuoted(sb, this._currentName);
                sb.append(Typography.quote);
            } else {
                sb.append('?');
            }
            sb.append('}');
        }
        return sb.toString();
    }
}
