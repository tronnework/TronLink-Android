package io.grpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
public interface Codec extends Compressor, Decompressor {

    public static final class Gzip implements Codec {
        @Override
        public String getMessageEncoding() {
            return "gzip";
        }

        @Override
        public OutputStream compress(OutputStream outputStream) throws IOException {
            return new GZIPOutputStream(outputStream);
        }

        @Override
        public InputStream decompress(InputStream inputStream) throws IOException {
            return new GZIPInputStream(inputStream);
        }
    }

    public static final class Identity implements Codec {
        public static final Codec NONE = new Identity();

        @Override
        public OutputStream compress(OutputStream outputStream) {
            return outputStream;
        }

        @Override
        public InputStream decompress(InputStream inputStream) {
            return inputStream;
        }

        @Override
        public String getMessageEncoding() {
            return "identity";
        }

        private Identity() {
        }
    }
}
