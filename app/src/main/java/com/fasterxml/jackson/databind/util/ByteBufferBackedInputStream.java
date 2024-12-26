package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
public class ByteBufferBackedInputStream extends InputStream {
    protected final ByteBuffer _b;

    public ByteBufferBackedInputStream(ByteBuffer byteBuffer) {
        this._b = byteBuffer;
    }

    @Override
    public int available() {
        return this._b.remaining();
    }

    @Override
    public int read() throws IOException {
        if (this._b.hasRemaining()) {
            return this._b.get() & 255;
        }
        return -1;
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this._b.hasRemaining()) {
            int min = Math.min(i2, this._b.remaining());
            this._b.get(bArr, i, min);
            return min;
        }
        return -1;
    }
}
