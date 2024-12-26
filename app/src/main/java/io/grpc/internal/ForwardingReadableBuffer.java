package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
public abstract class ForwardingReadableBuffer implements ReadableBuffer {
    private final ReadableBuffer buf;

    public ForwardingReadableBuffer(ReadableBuffer readableBuffer) {
        this.buf = (ReadableBuffer) Preconditions.checkNotNull(readableBuffer, "buf");
    }

    @Override
    public int readableBytes() {
        return this.buf.readableBytes();
    }

    @Override
    public int readUnsignedByte() {
        return this.buf.readUnsignedByte();
    }

    @Override
    public int readInt() {
        return this.buf.readInt();
    }

    @Override
    public void skipBytes(int i) {
        this.buf.skipBytes(i);
    }

    @Override
    public void readBytes(byte[] bArr, int i, int i2) {
        this.buf.readBytes(bArr, i, i2);
    }

    @Override
    public void readBytes(ByteBuffer byteBuffer) {
        this.buf.readBytes(byteBuffer);
    }

    @Override
    public void readBytes(OutputStream outputStream, int i) throws IOException {
        this.buf.readBytes(outputStream, i);
    }

    @Override
    public ReadableBuffer readBytes(int i) {
        return this.buf.readBytes(i);
    }

    @Override
    public boolean hasArray() {
        return this.buf.hasArray();
    }

    @Override
    public byte[] array() {
        return this.buf.array();
    }

    @Override
    public int arrayOffset() {
        return this.buf.arrayOffset();
    }

    @Override
    public void close() {
        this.buf.close();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", this.buf).toString();
    }
}
