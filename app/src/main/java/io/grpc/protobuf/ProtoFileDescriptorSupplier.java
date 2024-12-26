package io.grpc.protobuf;

import com.google.protobuf.Descriptors;
public interface ProtoFileDescriptorSupplier {
    Descriptors.FileDescriptor getFileDescriptor();
}
