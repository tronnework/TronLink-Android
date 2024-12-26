package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public class ExternalTypeHandler {
    private final HashMap<String, Integer> _nameToPropertyIndex;
    private final ExtTypedProperty[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;

    protected ExternalTypeHandler(ExtTypedProperty[] extTypedPropertyArr, HashMap<String, Integer> hashMap, String[] strArr, TokenBuffer[] tokenBufferArr) {
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = hashMap;
        this._typeIds = strArr;
        this._tokens = tokenBufferArr;
    }

    protected ExternalTypeHandler(ExternalTypeHandler externalTypeHandler) {
        ExtTypedProperty[] extTypedPropertyArr = externalTypeHandler._properties;
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = externalTypeHandler._nameToPropertyIndex;
        int length = extTypedPropertyArr.length;
        this._typeIds = new String[length];
        this._tokens = new TokenBuffer[length];
    }

    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }

    public boolean handleTypePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException {
        Integer num = this._nameToPropertyIndex.get(str);
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (this._properties[intValue].hasTypePropertyName(str)) {
            String text = jsonParser.getText();
            if (obj != null && this._tokens[intValue] != null) {
                _deserializeAndSet(jsonParser, deserializationContext, obj, intValue, text);
                this._tokens[intValue] = null;
                return true;
            }
            this._typeIds[intValue] = text;
            return true;
        }
        return false;
    }

    public boolean handlePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException {
        Integer num = this._nameToPropertyIndex.get(str);
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (this._properties[intValue].hasTypePropertyName(str)) {
            this._typeIds[intValue] = jsonParser.getText();
            jsonParser.skipChildren();
            if (obj == null || this._tokens[intValue] == null) {
                return true;
            }
        } else {
            TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
            tokenBuffer.copyCurrentStructure(jsonParser);
            this._tokens[intValue] = tokenBuffer;
            if (obj == null || this._typeIds[intValue] == null) {
                return true;
            }
        }
        String[] strArr = this._typeIds;
        String str2 = strArr[intValue];
        strArr[intValue] = null;
        _deserializeAndSet(jsonParser, deserializationContext, obj, intValue, str2);
        this._tokens[intValue] = null;
        return true;
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        int length = this._properties.length;
        for (int i = 0; i < length; i++) {
            String str = this._typeIds[i];
            if (str == null) {
                TokenBuffer tokenBuffer = this._tokens[i];
                if (tokenBuffer != null) {
                    JsonToken firstToken = tokenBuffer.firstToken();
                    if (firstToken != null && firstToken.isScalarValue()) {
                        JsonParser asParser = tokenBuffer.asParser(jsonParser);
                        asParser.nextToken();
                        SettableBeanProperty property = this._properties[i].getProperty();
                        Object deserializeIfNatural = TypeDeserializer.deserializeIfNatural(asParser, deserializationContext, property.getType());
                        if (deserializeIfNatural != null) {
                            property.set(obj, deserializeIfNatural);
                        } else if (this._properties[i].hasDefaultType()) {
                            str = this._properties[i].getDefaultTypeId();
                        } else {
                            deserializationContext.reportMappingException("Missing external type id property '%s'", this._properties[i].getTypePropertyName());
                        }
                    }
                }
            } else if (this._tokens[i] == null) {
                SettableBeanProperty property2 = this._properties[i].getProperty();
                if (property2.isRequired() || deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                    deserializationContext.reportMappingException("Missing property '%s' for external type id '%s'", property2.getName(), this._properties[i].getTypePropertyName());
                }
                return obj;
            }
            _deserializeAndSet(jsonParser, deserializationContext, obj, i, str);
        }
        return obj;
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, PropertyValueBuffer propertyValueBuffer, PropertyBasedCreator propertyBasedCreator) throws IOException {
        int length = this._properties.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            String str = this._typeIds[i];
            ExtTypedProperty extTypedProperty = this._properties[i];
            if (str == null) {
                if (this._tokens[i] != null) {
                    if (extTypedProperty.hasDefaultType()) {
                        str = extTypedProperty.getDefaultTypeId();
                    } else {
                        deserializationContext.reportMappingException("Missing external type id property '%s'", extTypedProperty.getTypePropertyName());
                    }
                }
            } else if (this._tokens[i] == null) {
                deserializationContext.reportMappingException("Missing property '%s' for external type id '%s'", extTypedProperty.getProperty().getName(), this._properties[i].getTypePropertyName());
            }
            objArr[i] = _deserialize(jsonParser, deserializationContext, i, str);
            SettableBeanProperty property = extTypedProperty.getProperty();
            if (property.getCreatorIndex() >= 0) {
                propertyValueBuffer.assignParameter(property, objArr[i]);
                SettableBeanProperty typeProperty = extTypedProperty.getTypeProperty();
                if (typeProperty != null && typeProperty.getCreatorIndex() >= 0) {
                    propertyValueBuffer.assignParameter(typeProperty, str);
                }
            }
        }
        Object build = propertyBasedCreator.build(deserializationContext, propertyValueBuffer);
        for (int i2 = 0; i2 < length; i2++) {
            SettableBeanProperty property2 = this._properties[i2].getProperty();
            if (property2.getCreatorIndex() < 0) {
                property2.set(build, objArr[i2]);
            }
        }
        return build;
    }

    protected final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, int i, String str) throws IOException {
        JsonParser asParser = this._tokens[i].asParser(jsonParser);
        if (asParser.nextToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
        tokenBuffer.writeStartArray();
        tokenBuffer.writeString(str);
        tokenBuffer.copyCurrentStructure(asParser);
        tokenBuffer.writeEndArray();
        JsonParser asParser2 = tokenBuffer.asParser(jsonParser);
        asParser2.nextToken();
        return this._properties[i].getProperty().deserialize(asParser2, deserializationContext);
    }

    protected final void _deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, int i, String str) throws IOException {
        JsonParser asParser = this._tokens[i].asParser(jsonParser);
        if (asParser.nextToken() == JsonToken.VALUE_NULL) {
            this._properties[i].getProperty().set(obj, null);
            return;
        }
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
        tokenBuffer.writeStartArray();
        tokenBuffer.writeString(str);
        tokenBuffer.copyCurrentStructure(asParser);
        tokenBuffer.writeEndArray();
        JsonParser asParser2 = tokenBuffer.asParser(jsonParser);
        asParser2.nextToken();
        this._properties[i].getProperty().deserializeAndSet(asParser2, deserializationContext, obj);
    }

    public static class Builder {
        private final ArrayList<ExtTypedProperty> _properties = new ArrayList<>();
        private final HashMap<String, Integer> _nameToPropertyIndex = new HashMap<>();

        public void addExternal(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            Integer valueOf = Integer.valueOf(this._properties.size());
            this._properties.add(new ExtTypedProperty(settableBeanProperty, typeDeserializer));
            this._nameToPropertyIndex.put(settableBeanProperty.getName(), valueOf);
            this._nameToPropertyIndex.put(typeDeserializer.getPropertyName(), valueOf);
        }

        public ExternalTypeHandler build(BeanPropertyMap beanPropertyMap) {
            int size = this._properties.size();
            ExtTypedProperty[] extTypedPropertyArr = new ExtTypedProperty[size];
            for (int i = 0; i < size; i++) {
                ExtTypedProperty extTypedProperty = this._properties.get(i);
                SettableBeanProperty find = beanPropertyMap.find(extTypedProperty.getTypePropertyName());
                if (find != null) {
                    extTypedProperty.linkTypeProperty(find);
                }
                extTypedPropertyArr[i] = extTypedProperty;
            }
            return new ExternalTypeHandler(extTypedPropertyArr, this._nameToPropertyIndex, null, null);
        }

        @Deprecated
        public ExternalTypeHandler build() {
            ArrayList<ExtTypedProperty> arrayList = this._properties;
            return new ExternalTypeHandler((ExtTypedProperty[]) arrayList.toArray(new ExtTypedProperty[arrayList.size()]), this._nameToPropertyIndex, null, null);
        }
    }

    public static final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final TypeDeserializer _typeDeserializer;
        private SettableBeanProperty _typeProperty;
        private final String _typePropertyName;

        public SettableBeanProperty getProperty() {
            return this._property;
        }

        public SettableBeanProperty getTypeProperty() {
            return this._typeProperty;
        }

        public String getTypePropertyName() {
            return this._typePropertyName;
        }

        public void linkTypeProperty(SettableBeanProperty settableBeanProperty) {
            this._typeProperty = settableBeanProperty;
        }

        public ExtTypedProperty(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            this._property = settableBeanProperty;
            this._typeDeserializer = typeDeserializer;
            this._typePropertyName = typeDeserializer.getPropertyName();
        }

        public boolean hasTypePropertyName(String str) {
            return str.equals(this._typePropertyName);
        }

        public boolean hasDefaultType() {
            return this._typeDeserializer.getDefaultImpl() != null;
        }

        public String getDefaultTypeId() {
            Class<?> defaultImpl = this._typeDeserializer.getDefaultImpl();
            if (defaultImpl == null) {
                return null;
            }
            return this._typeDeserializer.getTypeIdResolver().idFromValueAndType(null, defaultImpl);
        }
    }
}
