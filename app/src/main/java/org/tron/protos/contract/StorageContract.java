package org.tron.protos.contract;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
public final class StorageContract {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_BuyStorageBytesContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_BuyStorageBytesContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_BuyStorageContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_BuyStorageContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_SellStorageContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_SellStorageContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_UpdateBrokerageContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_UpdateBrokerageContract_fieldAccessorTable;

    public interface BuyStorageBytesContractOrBuilder extends MessageOrBuilder {
        long getBytes();

        ByteString getOwnerAddress();
    }

    public interface BuyStorageContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();

        long getQuant();
    }

    public interface SellStorageContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();

        long getStorageBytes();
    }

    public interface UpdateBrokerageContractOrBuilder extends MessageOrBuilder {
        int getBrokerage();

        ByteString getOwnerAddress();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private StorageContract() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class BuyStorageBytesContract extends GeneratedMessageV3 implements BuyStorageBytesContractOrBuilder {
        public static final int BYTES_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private long bytes_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final BuyStorageBytesContract DEFAULT_INSTANCE = new BuyStorageBytesContract();
        private static final Parser<BuyStorageBytesContract> PARSER = new AbstractParser<BuyStorageBytesContract>() {
            @Override
            public BuyStorageBytesContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BuyStorageBytesContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static BuyStorageBytesContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BuyStorageBytesContract> parser() {
            return PARSER;
        }

        @Override
        public long getBytes() {
            return this.bytes_;
        }

        @Override
        public BuyStorageBytesContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<BuyStorageBytesContract> getParserForType() {
            return PARSER;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        private BuyStorageBytesContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private BuyStorageBytesContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.bytes_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BuyStorageBytesContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.ownerAddress_ = codedInputStream.readBytes();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bytes_ = codedInputStream.readInt64();
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StorageContract.internal_static_protocol_BuyStorageBytesContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StorageContract.internal_static_protocol_BuyStorageBytesContract_fieldAccessorTable.ensureFieldAccessorsInitialized(BuyStorageBytesContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.bytes_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.ownerAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.ownerAddress_) : 0;
            long j = this.bytes_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            int serializedSize = computeBytesSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BuyStorageBytesContract)) {
                return super.equals(obj);
            }
            BuyStorageBytesContract buyStorageBytesContract = (BuyStorageBytesContract) obj;
            return getOwnerAddress().equals(buyStorageBytesContract.getOwnerAddress()) && getBytes() == buyStorageBytesContract.getBytes() && this.unknownFields.equals(buyStorageBytesContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getBytes())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static BuyStorageBytesContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static BuyStorageBytesContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BuyStorageBytesContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static BuyStorageBytesContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BuyStorageBytesContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static BuyStorageBytesContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BuyStorageBytesContract parseFrom(InputStream inputStream) throws IOException {
            return (BuyStorageBytesContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BuyStorageBytesContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BuyStorageBytesContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BuyStorageBytesContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BuyStorageBytesContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BuyStorageBytesContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BuyStorageBytesContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BuyStorageBytesContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BuyStorageBytesContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BuyStorageBytesContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BuyStorageBytesContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BuyStorageBytesContract buyStorageBytesContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(buyStorageBytesContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BuyStorageBytesContractOrBuilder {
            private long bytes_;
            private ByteString ownerAddress_;

            @Override
            public long getBytes() {
                return this.bytes_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return StorageContract.internal_static_protocol_BuyStorageBytesContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return StorageContract.internal_static_protocol_BuyStorageBytesContract_fieldAccessorTable.ensureFieldAccessorsInitialized(BuyStorageBytesContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BuyStorageBytesContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.bytes_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return StorageContract.internal_static_protocol_BuyStorageBytesContract_descriptor;
            }

            @Override
            public BuyStorageBytesContract getDefaultInstanceForType() {
                return BuyStorageBytesContract.getDefaultInstance();
            }

            @Override
            public BuyStorageBytesContract build() {
                BuyStorageBytesContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public BuyStorageBytesContract buildPartial() {
                BuyStorageBytesContract buyStorageBytesContract = new BuyStorageBytesContract(this);
                buyStorageBytesContract.ownerAddress_ = this.ownerAddress_;
                buyStorageBytesContract.bytes_ = this.bytes_;
                onBuilt();
                return buyStorageBytesContract;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof BuyStorageBytesContract) {
                    return mergeFrom((BuyStorageBytesContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BuyStorageBytesContract buyStorageBytesContract) {
                if (buyStorageBytesContract == BuyStorageBytesContract.getDefaultInstance()) {
                    return this;
                }
                if (buyStorageBytesContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(buyStorageBytesContract.getOwnerAddress());
                }
                if (buyStorageBytesContract.getBytes() != 0) {
                    setBytes(buyStorageBytesContract.getBytes());
                }
                mergeUnknownFields(buyStorageBytesContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.StorageContract.BuyStorageBytesContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.StorageContract.BuyStorageBytesContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.StorageContract$BuyStorageBytesContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = BuyStorageBytesContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setBytes(long j) {
                this.bytes_ = j;
                onChanged();
                return this;
            }

            public Builder clearBytes() {
                this.bytes_ = 0L;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class BuyStorageContract extends GeneratedMessageV3 implements BuyStorageContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int QUANT_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private long quant_;
        private static final BuyStorageContract DEFAULT_INSTANCE = new BuyStorageContract();
        private static final Parser<BuyStorageContract> PARSER = new AbstractParser<BuyStorageContract>() {
            @Override
            public BuyStorageContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BuyStorageContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static BuyStorageContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BuyStorageContract> parser() {
            return PARSER;
        }

        @Override
        public BuyStorageContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<BuyStorageContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getQuant() {
            return this.quant_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        private BuyStorageContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private BuyStorageContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.quant_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BuyStorageContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.ownerAddress_ = codedInputStream.readBytes();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.quant_ = codedInputStream.readInt64();
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StorageContract.internal_static_protocol_BuyStorageContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StorageContract.internal_static_protocol_BuyStorageContract_fieldAccessorTable.ensureFieldAccessorsInitialized(BuyStorageContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.quant_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.ownerAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.ownerAddress_) : 0;
            long j = this.quant_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            int serializedSize = computeBytesSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BuyStorageContract)) {
                return super.equals(obj);
            }
            BuyStorageContract buyStorageContract = (BuyStorageContract) obj;
            return getOwnerAddress().equals(buyStorageContract.getOwnerAddress()) && getQuant() == buyStorageContract.getQuant() && this.unknownFields.equals(buyStorageContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getQuant())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static BuyStorageContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static BuyStorageContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BuyStorageContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static BuyStorageContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BuyStorageContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static BuyStorageContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BuyStorageContract parseFrom(InputStream inputStream) throws IOException {
            return (BuyStorageContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BuyStorageContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BuyStorageContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BuyStorageContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BuyStorageContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BuyStorageContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BuyStorageContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BuyStorageContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BuyStorageContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BuyStorageContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BuyStorageContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BuyStorageContract buyStorageContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(buyStorageContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BuyStorageContractOrBuilder {
            private ByteString ownerAddress_;
            private long quant_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getQuant() {
                return this.quant_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return StorageContract.internal_static_protocol_BuyStorageContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return StorageContract.internal_static_protocol_BuyStorageContract_fieldAccessorTable.ensureFieldAccessorsInitialized(BuyStorageContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BuyStorageContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.quant_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return StorageContract.internal_static_protocol_BuyStorageContract_descriptor;
            }

            @Override
            public BuyStorageContract getDefaultInstanceForType() {
                return BuyStorageContract.getDefaultInstance();
            }

            @Override
            public BuyStorageContract build() {
                BuyStorageContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public BuyStorageContract buildPartial() {
                BuyStorageContract buyStorageContract = new BuyStorageContract(this);
                buyStorageContract.ownerAddress_ = this.ownerAddress_;
                buyStorageContract.quant_ = this.quant_;
                onBuilt();
                return buyStorageContract;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof BuyStorageContract) {
                    return mergeFrom((BuyStorageContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BuyStorageContract buyStorageContract) {
                if (buyStorageContract == BuyStorageContract.getDefaultInstance()) {
                    return this;
                }
                if (buyStorageContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(buyStorageContract.getOwnerAddress());
                }
                if (buyStorageContract.getQuant() != 0) {
                    setQuant(buyStorageContract.getQuant());
                }
                mergeUnknownFields(buyStorageContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.StorageContract.BuyStorageContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.StorageContract.BuyStorageContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.StorageContract$BuyStorageContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = BuyStorageContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setQuant(long j) {
                this.quant_ = j;
                onChanged();
                return this;
            }

            public Builder clearQuant() {
                this.quant_ = 0L;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class SellStorageContract extends GeneratedMessageV3 implements SellStorageContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int STORAGE_BYTES_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private long storageBytes_;
        private static final SellStorageContract DEFAULT_INSTANCE = new SellStorageContract();
        private static final Parser<SellStorageContract> PARSER = new AbstractParser<SellStorageContract>() {
            @Override
            public SellStorageContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new SellStorageContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static SellStorageContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SellStorageContract> parser() {
            return PARSER;
        }

        @Override
        public SellStorageContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<SellStorageContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getStorageBytes() {
            return this.storageBytes_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        private SellStorageContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private SellStorageContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.storageBytes_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private SellStorageContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.ownerAddress_ = codedInputStream.readBytes();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.storageBytes_ = codedInputStream.readInt64();
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StorageContract.internal_static_protocol_SellStorageContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StorageContract.internal_static_protocol_SellStorageContract_fieldAccessorTable.ensureFieldAccessorsInitialized(SellStorageContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.storageBytes_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.ownerAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.ownerAddress_) : 0;
            long j = this.storageBytes_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            int serializedSize = computeBytesSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SellStorageContract)) {
                return super.equals(obj);
            }
            SellStorageContract sellStorageContract = (SellStorageContract) obj;
            return getOwnerAddress().equals(sellStorageContract.getOwnerAddress()) && getStorageBytes() == sellStorageContract.getStorageBytes() && this.unknownFields.equals(sellStorageContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getStorageBytes())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static SellStorageContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static SellStorageContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static SellStorageContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static SellStorageContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static SellStorageContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static SellStorageContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static SellStorageContract parseFrom(InputStream inputStream) throws IOException {
            return (SellStorageContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static SellStorageContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SellStorageContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SellStorageContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SellStorageContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static SellStorageContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SellStorageContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SellStorageContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SellStorageContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static SellStorageContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SellStorageContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SellStorageContract sellStorageContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(sellStorageContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SellStorageContractOrBuilder {
            private ByteString ownerAddress_;
            private long storageBytes_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getStorageBytes() {
                return this.storageBytes_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return StorageContract.internal_static_protocol_SellStorageContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return StorageContract.internal_static_protocol_SellStorageContract_fieldAccessorTable.ensureFieldAccessorsInitialized(SellStorageContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = SellStorageContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.storageBytes_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return StorageContract.internal_static_protocol_SellStorageContract_descriptor;
            }

            @Override
            public SellStorageContract getDefaultInstanceForType() {
                return SellStorageContract.getDefaultInstance();
            }

            @Override
            public SellStorageContract build() {
                SellStorageContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public SellStorageContract buildPartial() {
                SellStorageContract sellStorageContract = new SellStorageContract(this);
                sellStorageContract.ownerAddress_ = this.ownerAddress_;
                sellStorageContract.storageBytes_ = this.storageBytes_;
                onBuilt();
                return sellStorageContract;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof SellStorageContract) {
                    return mergeFrom((SellStorageContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(SellStorageContract sellStorageContract) {
                if (sellStorageContract == SellStorageContract.getDefaultInstance()) {
                    return this;
                }
                if (sellStorageContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(sellStorageContract.getOwnerAddress());
                }
                if (sellStorageContract.getStorageBytes() != 0) {
                    setStorageBytes(sellStorageContract.getStorageBytes());
                }
                mergeUnknownFields(sellStorageContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.StorageContract.SellStorageContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.StorageContract.SellStorageContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.StorageContract$SellStorageContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = SellStorageContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setStorageBytes(long j) {
                this.storageBytes_ = j;
                onChanged();
                return this;
            }

            public Builder clearStorageBytes() {
                this.storageBytes_ = 0L;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class UpdateBrokerageContract extends GeneratedMessageV3 implements UpdateBrokerageContractOrBuilder {
        public static final int BROKERAGE_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int brokerage_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final UpdateBrokerageContract DEFAULT_INSTANCE = new UpdateBrokerageContract();
        private static final Parser<UpdateBrokerageContract> PARSER = new AbstractParser<UpdateBrokerageContract>() {
            @Override
            public UpdateBrokerageContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UpdateBrokerageContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static UpdateBrokerageContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UpdateBrokerageContract> parser() {
            return PARSER;
        }

        @Override
        public int getBrokerage() {
            return this.brokerage_;
        }

        @Override
        public UpdateBrokerageContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<UpdateBrokerageContract> getParserForType() {
            return PARSER;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        private UpdateBrokerageContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UpdateBrokerageContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.brokerage_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateBrokerageContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.ownerAddress_ = codedInputStream.readBytes();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.brokerage_ = codedInputStream.readInt32();
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StorageContract.internal_static_protocol_UpdateBrokerageContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StorageContract.internal_static_protocol_UpdateBrokerageContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateBrokerageContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            int i = this.brokerage_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.ownerAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.ownerAddress_) : 0;
            int i2 = this.brokerage_;
            if (i2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt32Size(2, i2);
            }
            int serializedSize = computeBytesSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UpdateBrokerageContract)) {
                return super.equals(obj);
            }
            UpdateBrokerageContract updateBrokerageContract = (UpdateBrokerageContract) obj;
            return getOwnerAddress().equals(updateBrokerageContract.getOwnerAddress()) && getBrokerage() == updateBrokerageContract.getBrokerage() && this.unknownFields.equals(updateBrokerageContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getBrokerage()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static UpdateBrokerageContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UpdateBrokerageContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UpdateBrokerageContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UpdateBrokerageContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UpdateBrokerageContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UpdateBrokerageContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UpdateBrokerageContract parseFrom(InputStream inputStream) throws IOException {
            return (UpdateBrokerageContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UpdateBrokerageContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateBrokerageContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateBrokerageContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateBrokerageContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UpdateBrokerageContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateBrokerageContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateBrokerageContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateBrokerageContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UpdateBrokerageContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateBrokerageContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UpdateBrokerageContract updateBrokerageContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateBrokerageContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateBrokerageContractOrBuilder {
            private int brokerage_;
            private ByteString ownerAddress_;

            @Override
            public int getBrokerage() {
                return this.brokerage_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return StorageContract.internal_static_protocol_UpdateBrokerageContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return StorageContract.internal_static_protocol_UpdateBrokerageContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateBrokerageContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateBrokerageContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.brokerage_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return StorageContract.internal_static_protocol_UpdateBrokerageContract_descriptor;
            }

            @Override
            public UpdateBrokerageContract getDefaultInstanceForType() {
                return UpdateBrokerageContract.getDefaultInstance();
            }

            @Override
            public UpdateBrokerageContract build() {
                UpdateBrokerageContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public UpdateBrokerageContract buildPartial() {
                UpdateBrokerageContract updateBrokerageContract = new UpdateBrokerageContract(this);
                updateBrokerageContract.ownerAddress_ = this.ownerAddress_;
                updateBrokerageContract.brokerage_ = this.brokerage_;
                onBuilt();
                return updateBrokerageContract;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof UpdateBrokerageContract) {
                    return mergeFrom((UpdateBrokerageContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UpdateBrokerageContract updateBrokerageContract) {
                if (updateBrokerageContract == UpdateBrokerageContract.getDefaultInstance()) {
                    return this;
                }
                if (updateBrokerageContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(updateBrokerageContract.getOwnerAddress());
                }
                if (updateBrokerageContract.getBrokerage() != 0) {
                    setBrokerage(updateBrokerageContract.getBrokerage());
                }
                mergeUnknownFields(updateBrokerageContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.StorageContract.UpdateBrokerageContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.StorageContract.UpdateBrokerageContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.StorageContract$UpdateBrokerageContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = UpdateBrokerageContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setBrokerage(int i) {
                this.brokerage_ = i;
                onChanged();
                return this;
            }

            public Builder clearBrokerage() {
                this.brokerage_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n$core/contract/storage_contract.proto\u0012\bprotocol\"?\n\u0017BuyStorageBytesContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\r\n\u0005bytes\u0018\u0002 \u0001(\u0003\":\n\u0012BuyStorageContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\r\n\u0005quant\u0018\u0002 \u0001(\u0003\"C\n\u0013SellStorageContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0015\n\rstorage_bytes\u0018\u0002 \u0001(\u0003\"C\n\u0017UpdateBrokerageContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0011\n\tbrokerage\u0018\u0002 \u0001(\u0005BE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = StorageContract.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_BuyStorageBytesContract_descriptor = descriptor2;
        internal_static_protocol_BuyStorageBytesContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"OwnerAddress", "Bytes"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_BuyStorageContract_descriptor = descriptor3;
        internal_static_protocol_BuyStorageContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"OwnerAddress", "Quant"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_SellStorageContract_descriptor = descriptor4;
        internal_static_protocol_SellStorageContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"OwnerAddress", "StorageBytes"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_protocol_UpdateBrokerageContract_descriptor = descriptor5;
        internal_static_protocol_UpdateBrokerageContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"OwnerAddress", "Brokerage"});
    }
}
