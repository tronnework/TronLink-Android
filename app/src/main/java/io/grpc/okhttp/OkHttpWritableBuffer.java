package io.grpc.okhttp;

import io.grpc.internal.WritableBuffer;
import okio.Buffer;
class OkHttpWritableBuffer implements WritableBuffer {
    private final Buffer buffer;
    private int readableBytes;
    private int writableBytes;

    public Buffer buffer() {
        return this.buffer;
    }

    @Override
    public int readableBytes() {
        return this.readableBytes;
    }

    @Override
    public void release() {
    }

    @Override
    public int writableBytes() {
        return this.writableBytes;
    }

    public OkHttpWritableBuffer(Buffer buffer, int i) {
        this.buffer = buffer;
        this.writableBytes = i;
    }

    @Override
    public void write(byte[] bArr, int i, int i2) {
        this.buffer.write(bArr, i, i2);
        this.writableBytes -= i2;
        this.readableBytes += i2;
    }

    @Override
    public void write(byte b) {
        this.buffer.writeByte((int) b);
        this.writableBytes--;
        this.readableBytes++;
    }
}
