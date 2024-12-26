package io.grpc.protobuf;

import com.google.protobuf.Descriptors;
import javax.annotation.CheckReturnValue;
public interface ProtoMethodDescriptorSupplier extends ProtoServiceDescriptorSupplier {
    @CheckReturnValue
    Descriptors.MethodDescriptor getMethodDescriptor();
}
