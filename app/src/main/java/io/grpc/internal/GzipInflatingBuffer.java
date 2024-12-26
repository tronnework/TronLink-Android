package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
public class GzipInflatingBuffer implements Closeable {
    private static final int GZIP_HEADER_MIN_SIZE = 10;
    private static final int GZIP_MAGIC = 35615;
    private static final int GZIP_TRAILER_SIZE = 8;
    private static final int HEADER_COMMENT_FLAG = 16;
    private static final int HEADER_CRC_FLAG = 2;
    private static final int HEADER_EXTRA_FLAG = 4;
    private static final int HEADER_NAME_FLAG = 8;
    private static final int INFLATE_BUFFER_SIZE = 512;
    private static final int UNSIGNED_SHORT_SIZE = 2;
    private long expectedGzipTrailerIsize;
    private int gzipHeaderFlag;
    private int headerExtraToRead;
    private Inflater inflater;
    private int inflaterInputEnd;
    private int inflaterInputStart;
    private final CompositeReadableBuffer gzippedData = new CompositeReadableBuffer();
    private final CRC32 crc = new CRC32();
    private final GzipMetadataReader gzipMetadataReader = new GzipMetadataReader(this, null);
    private final byte[] inflaterInput = new byte[512];
    private State state = State.HEADER;
    private boolean closed = false;
    private int bytesConsumed = 0;
    private int deflatedBytesConsumed = 0;
    private boolean isStalled = true;

    public enum State {
        HEADER,
        HEADER_EXTRA_LEN,
        HEADER_EXTRA,
        HEADER_NAME,
        HEADER_COMMENT,
        HEADER_CRC,
        INITIALIZE_INFLATER,
        INFLATING,
        INFLATER_NEEDS_INPUT,
        TRAILER
    }

    public int getAndResetBytesConsumed() {
        int i = this.bytesConsumed;
        this.bytesConsumed = 0;
        return i;
    }

    public int getAndResetDeflatedBytesConsumed() {
        int i = this.deflatedBytesConsumed;
        this.deflatedBytesConsumed = 0;
        return i;
    }

    static int Fun$112(GzipInflatingBuffer gzipInflatingBuffer, int i) {
        int i2 = gzipInflatingBuffer.inflaterInputStart + i;
        gzipInflatingBuffer.inflaterInputStart = i2;
        return i2;
    }

    static int Fun$512(GzipInflatingBuffer gzipInflatingBuffer, int i) {
        int i2 = gzipInflatingBuffer.bytesConsumed + i;
        gzipInflatingBuffer.bytesConsumed = i2;
        return i2;
    }

    public class GzipMetadataReader {
        private GzipMetadataReader() {
        }

        GzipMetadataReader(GzipInflatingBuffer gzipInflatingBuffer, 1 r2) {
            this();
        }

        public int readUnsignedByte() {
            int readUnsignedByte;
            if (inflaterInputEnd - inflaterInputStart > 0) {
                readUnsignedByte = inflaterInput[inflaterInputStart] & 255;
                GzipInflatingBuffer.Fun$112(GzipInflatingBuffer.this, 1);
            } else {
                readUnsignedByte = gzippedData.readUnsignedByte();
            }
            crc.update(readUnsignedByte);
            GzipInflatingBuffer.Fun$512(GzipInflatingBuffer.this, 1);
            return readUnsignedByte;
        }

        public void skipBytes(int i) {
            int i2;
            int i3 = inflaterInputEnd - inflaterInputStart;
            if (i3 > 0) {
                int min = Math.min(i3, i);
                crc.update(inflaterInput, inflaterInputStart, min);
                GzipInflatingBuffer.Fun$112(GzipInflatingBuffer.this, min);
                i2 = i - min;
            } else {
                i2 = i;
            }
            if (i2 > 0) {
                byte[] bArr = new byte[512];
                int i4 = 0;
                while (i4 < i2) {
                    int min2 = Math.min(i2 - i4, 512);
                    gzippedData.readBytes(bArr, 0, min2);
                    crc.update(bArr, 0, min2);
                    i4 += min2;
                }
            }
            GzipInflatingBuffer.Fun$512(GzipInflatingBuffer.this, i);
        }

        public int readableBytes() {
            return (inflaterInputEnd - inflaterInputStart) + gzippedData.readableBytes();
        }

        public boolean readBytesUntilZero() {
            while (readableBytes() > 0) {
                if (readUnsignedByte() == 0) {
                    return true;
                }
            }
            return false;
        }

        public int readUnsignedShort() {
            return readUnsignedByte() | (readUnsignedByte() << 8);
        }

        public long readUnsignedInt() {
            return readUnsignedShort() | (readUnsignedShort() << 16);
        }
    }

    public boolean isStalled() {
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        return this.isStalled;
    }

    public boolean hasPartialData() {
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        return (this.gzipMetadataReader.readableBytes() == 0 && this.state == State.HEADER) ? false : true;
    }

    public void addGzippedBytes(ReadableBuffer readableBuffer) {
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        this.gzippedData.addBuffer(readableBuffer);
        this.isStalled = false;
    }

    @Override
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.gzippedData.close();
        Inflater inflater = this.inflater;
        if (inflater != null) {
            inflater.end();
            this.inflater = null;
        }
    }

    public int inflateBytes(byte[] bArr, int i, int i2) throws DataFormatException, ZipException {
        boolean z = true;
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        boolean z2 = true;
        int i3 = 0;
        while (z2) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                switch (fun1.$SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[this.state.ordinal()]) {
                    case 1:
                        z2 = processHeader();
                        break;
                    case 2:
                        z2 = processHeaderExtraLen();
                        break;
                    case 3:
                        z2 = processHeaderExtra();
                        break;
                    case 4:
                        z2 = processHeaderName();
                        break;
                    case 5:
                        z2 = processHeaderComment();
                        break;
                    case 6:
                        z2 = processHeaderCrc();
                        break;
                    case 7:
                        z2 = initializeInflater();
                        break;
                    case 8:
                        i3 += inflate(bArr, i + i3, i4);
                        if (this.state != State.TRAILER) {
                            z2 = true;
                            break;
                        } else {
                            z2 = processTrailer();
                            break;
                        }
                    case 9:
                        z2 = fill();
                        break;
                    case 10:
                        z2 = processTrailer();
                        break;
                    default:
                        throw new AssertionError("Invalid state: " + this.state);
                }
            } else {
                if (z2 && (this.state != State.HEADER || this.gzipMetadataReader.readableBytes() >= 10)) {
                    z = false;
                }
                this.isStalled = z;
                return i3;
            }
        }
        if (z2) {
            z = false;
        }
        this.isStalled = z;
        return i3;
    }

    static class fun1 {
        static final int[] $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State = iArr;
            try {
                iArr[State.HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_EXTRA_LEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_EXTRA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_COMMENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_CRC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.INITIALIZE_INFLATER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.INFLATING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.INFLATER_NEEDS_INPUT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.TRAILER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private boolean processHeader() throws ZipException {
        if (this.gzipMetadataReader.readableBytes() < 10) {
            return false;
        }
        if (this.gzipMetadataReader.readUnsignedShort() == GZIP_MAGIC) {
            if (this.gzipMetadataReader.readUnsignedByte() == 8) {
                this.gzipHeaderFlag = this.gzipMetadataReader.readUnsignedByte();
                this.gzipMetadataReader.skipBytes(6);
                this.state = State.HEADER_EXTRA_LEN;
                return true;
            }
            throw new ZipException("Unsupported compression method");
        }
        throw new ZipException("Not in GZIP format");
    }

    private boolean processHeaderExtraLen() {
        if ((this.gzipHeaderFlag & 4) == 4) {
            if (this.gzipMetadataReader.readableBytes() < 2) {
                return false;
            }
            this.headerExtraToRead = this.gzipMetadataReader.readUnsignedShort();
            this.state = State.HEADER_EXTRA;
            return true;
        }
        this.state = State.HEADER_NAME;
        return true;
    }

    private boolean processHeaderExtra() {
        int readableBytes = this.gzipMetadataReader.readableBytes();
        int i = this.headerExtraToRead;
        if (readableBytes < i) {
            return false;
        }
        this.gzipMetadataReader.skipBytes(i);
        this.state = State.HEADER_NAME;
        return true;
    }

    private boolean processHeaderName() {
        if ((this.gzipHeaderFlag & 8) == 8) {
            if (this.gzipMetadataReader.readBytesUntilZero()) {
                this.state = State.HEADER_COMMENT;
                return true;
            }
            return false;
        }
        this.state = State.HEADER_COMMENT;
        return true;
    }

    private boolean processHeaderComment() {
        if ((this.gzipHeaderFlag & 16) == 16) {
            if (this.gzipMetadataReader.readBytesUntilZero()) {
                this.state = State.HEADER_CRC;
                return true;
            }
            return false;
        }
        this.state = State.HEADER_CRC;
        return true;
    }

    private boolean processHeaderCrc() throws ZipException {
        if ((this.gzipHeaderFlag & 2) == 2) {
            if (this.gzipMetadataReader.readableBytes() < 2) {
                return false;
            }
            if ((65535 & ((int) this.crc.getValue())) != this.gzipMetadataReader.readUnsignedShort()) {
                throw new ZipException("Corrupt GZIP header");
            }
            this.state = State.INITIALIZE_INFLATER;
            return true;
        }
        this.state = State.INITIALIZE_INFLATER;
        return true;
    }

    private boolean initializeInflater() {
        Inflater inflater = this.inflater;
        if (inflater == null) {
            this.inflater = new Inflater(true);
        } else {
            inflater.reset();
        }
        this.crc.reset();
        int i = this.inflaterInputEnd;
        int i2 = this.inflaterInputStart;
        int i3 = i - i2;
        if (i3 > 0) {
            this.inflater.setInput(this.inflaterInput, i2, i3);
            this.state = State.INFLATING;
        } else {
            this.state = State.INFLATER_NEEDS_INPUT;
        }
        return true;
    }

    private int inflate(byte[] bArr, int i, int i2) throws DataFormatException, ZipException {
        Preconditions.checkState(this.inflater != null, "inflater is null");
        try {
            int totalIn = this.inflater.getTotalIn();
            int inflate = this.inflater.inflate(bArr, i, i2);
            int totalIn2 = this.inflater.getTotalIn() - totalIn;
            this.bytesConsumed += totalIn2;
            this.deflatedBytesConsumed += totalIn2;
            this.inflaterInputStart += totalIn2;
            this.crc.update(bArr, i, inflate);
            if (this.inflater.finished()) {
                this.expectedGzipTrailerIsize = this.inflater.getBytesWritten() & 4294967295L;
                this.state = State.TRAILER;
            } else if (this.inflater.needsInput()) {
                this.state = State.INFLATER_NEEDS_INPUT;
            }
            return inflate;
        } catch (DataFormatException e) {
            throw new DataFormatException("Inflater data format exception: " + e.getMessage());
        }
    }

    private boolean fill() {
        Preconditions.checkState(this.inflater != null, "inflater is null");
        Preconditions.checkState(this.inflaterInputStart == this.inflaterInputEnd, "inflaterInput has unconsumed bytes");
        int min = Math.min(this.gzippedData.readableBytes(), 512);
        if (min == 0) {
            return false;
        }
        this.inflaterInputStart = 0;
        this.inflaterInputEnd = min;
        this.gzippedData.readBytes(this.inflaterInput, 0, min);
        this.inflater.setInput(this.inflaterInput, this.inflaterInputStart, min);
        this.state = State.INFLATING;
        return true;
    }

    private boolean processTrailer() throws ZipException {
        if (this.inflater != null && this.gzipMetadataReader.readableBytes() <= 18) {
            this.inflater.end();
            this.inflater = null;
        }
        if (this.gzipMetadataReader.readableBytes() < 8) {
            return false;
        }
        if (this.crc.getValue() != this.gzipMetadataReader.readUnsignedInt() || this.expectedGzipTrailerIsize != this.gzipMetadataReader.readUnsignedInt()) {
            throw new ZipException("Corrupt GZIP trailer");
        }
        this.crc.reset();
        this.state = State.HEADER;
        return true;
    }
}
