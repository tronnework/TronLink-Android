package org.tron.protos.contract;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
public final class Common {
    private static Descriptors.FileDescriptor descriptor;

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Common() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public enum ResourceCode implements ProtocolMessageEnum {
        BANDWIDTH(0),
        ENERGY(1),
        TRON_POWER(2),
        UNRECOGNIZED(-1);
        
        public static final int BANDWIDTH_VALUE = 0;
        public static final int ENERGY_VALUE = 1;
        public static final int TRON_POWER_VALUE = 2;
        private final int value;
        private static final Internal.EnumLiteMap<ResourceCode> internalValueMap = new Internal.EnumLiteMap<ResourceCode>() {
            @Override
            public ResourceCode findValueByNumber(int i) {
                return ResourceCode.forNumber(i);
            }
        };
        private static final ResourceCode[] VALUES = values();

        public static ResourceCode forNumber(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        return null;
                    }
                    return TRON_POWER;
                }
                return ENERGY;
            }
            return BANDWIDTH;
        }

        public static Internal.EnumLiteMap<ResourceCode> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static ResourceCode valueOf(int i) {
            return forNumber(i);
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Common.getDescriptor().getEnumTypes().get(0);
        }

        public static ResourceCode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        ResourceCode(int i) {
            this.value = i;
        }
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001acore/contract/common.proto\u0012\bprotocol*9\n\fResourceCode\u0012\r\n\tBANDWIDTH\u0010\u0000\u0012\n\n\u0006ENERGY\u0010\u0001\u0012\u000e\n\nTRON_POWER\u0010\u0002BE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = Common.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
