package io.grpc.protobuf;

import com.google.protobuf.Descriptors;
public interface ProtoServiceDescriptorSupplier extends ProtoFileDescriptorSupplier {
    Descriptors.ServiceDescriptor getServiceDescriptor();
}
