package io.grpc;

import java.io.IOException;
import java.io.OutputStream;
public interface Compressor {
    OutputStream compress(OutputStream outputStream) throws IOException;

    String getMessageEncoding();
}
