package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;
public class BinaryNode extends ValueNode {
    static final BinaryNode EMPTY_BINARY_NODE = new BinaryNode(new byte[0]);
    protected final byte[] _data;

    @Override
    public byte[] binaryValue() {
        return this._data;
    }

    public BinaryNode(byte[] bArr) {
        this._data = bArr;
    }

    public BinaryNode(byte[] bArr, int i, int i2) {
        if (i == 0 && i2 == bArr.length) {
            this._data = bArr;
            return;
        }
        byte[] bArr2 = new byte[i2];
        this._data = bArr2;
        System.arraycopy(bArr, i, bArr2, 0, i2);
    }

    public static BinaryNode valueOf(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return bArr.length == 0 ? EMPTY_BINARY_NODE : new BinaryNode(bArr);
    }

    public static BinaryNode valueOf(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return null;
        }
        return i2 == 0 ? EMPTY_BINARY_NODE : new BinaryNode(bArr, i, i2);
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.BINARY;
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    @Override
    public String asText() {
        return Base64Variants.getDefaultVariant().encode(this._data, false);
    }

    @Override
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        Base64Variant base64Variant = serializerProvider.getConfig().getBase64Variant();
        byte[] bArr = this._data;
        jsonGenerator.writeBinary(base64Variant, bArr, 0, bArr.length);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof BinaryNode)) {
            return Arrays.equals(((BinaryNode) obj)._data, this._data);
        }
        return false;
    }

    @Override
    public int hashCode() {
        byte[] bArr = this._data;
        if (bArr == null) {
            return -1;
        }
        return bArr.length;
    }

    @Override
    public String toString() {
        return Base64Variants.getDefaultVariant().encode(this._data, true);
    }
}
