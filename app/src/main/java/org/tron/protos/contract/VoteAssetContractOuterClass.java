package org.tron.protos.contract;

import com.google.protobuf.AbstractMessageLite;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class VoteAssetContractOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_VoteAssetContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_VoteAssetContract_fieldAccessorTable;

    public interface VoteAssetContractOrBuilder extends MessageOrBuilder {
        int getCount();

        ByteString getOwnerAddress();

        boolean getSupport();

        ByteString getVoteAddress(int i);

        int getVoteAddressCount();

        List<ByteString> getVoteAddressList();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private VoteAssetContractOuterClass() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class VoteAssetContract extends GeneratedMessageV3 implements VoteAssetContractOrBuilder {
        public static final int COUNT_FIELD_NUMBER = 5;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int SUPPORT_FIELD_NUMBER = 3;
        public static final int VOTE_ADDRESS_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int count_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private boolean support_;
        private List<ByteString> voteAddress_;
        private static final VoteAssetContract DEFAULT_INSTANCE = new VoteAssetContract();
        private static final Parser<VoteAssetContract> PARSER = new AbstractParser<VoteAssetContract>() {
            @Override
            public VoteAssetContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new VoteAssetContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static VoteAssetContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<VoteAssetContract> parser() {
            return PARSER;
        }

        @Override
        public int getCount() {
            return this.count_;
        }

        @Override
        public VoteAssetContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<VoteAssetContract> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean getSupport() {
            return this.support_;
        }

        @Override
        public List<ByteString> getVoteAddressList() {
            return this.voteAddress_;
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

        private VoteAssetContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private VoteAssetContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.voteAddress_ = Collections.emptyList();
            this.support_ = false;
            this.count_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private VoteAssetContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (true) {
                if (z) {
                    break;
                }
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.ownerAddress_ = codedInputStream.readBytes();
                            } else if (readTag == 18) {
                                if (!(z2 & true)) {
                                    this.voteAddress_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.voteAddress_.add(codedInputStream.readBytes());
                            } else if (readTag == 24) {
                                this.support_ = codedInputStream.readBool();
                            } else if (readTag != 40) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.count_ = codedInputStream.readInt32();
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.voteAddress_ = Collections.unmodifiableList(this.voteAddress_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return VoteAssetContractOuterClass.internal_static_protocol_VoteAssetContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return VoteAssetContractOuterClass.internal_static_protocol_VoteAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(VoteAssetContract.class, Builder.class);
        }

        @Override
        public int getVoteAddressCount() {
            return this.voteAddress_.size();
        }

        @Override
        public ByteString getVoteAddress(int i) {
            return this.voteAddress_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            for (int i = 0; i < this.voteAddress_.size(); i++) {
                codedOutputStream.writeBytes(2, this.voteAddress_.get(i));
            }
            boolean z = this.support_;
            if (z) {
                codedOutputStream.writeBool(3, z);
            }
            int i2 = this.count_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(5, i2);
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
            int i2 = 0;
            for (int i3 = 0; i3 < this.voteAddress_.size(); i3++) {
                i2 += CodedOutputStream.computeBytesSizeNoTag(this.voteAddress_.get(i3));
            }
            int size = computeBytesSize + i2 + getVoteAddressList().size();
            boolean z = this.support_;
            if (z) {
                size += CodedOutputStream.computeBoolSize(3, z);
            }
            int i4 = this.count_;
            if (i4 != 0) {
                size += CodedOutputStream.computeInt32Size(5, i4);
            }
            int serializedSize = size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof VoteAssetContract)) {
                return super.equals(obj);
            }
            VoteAssetContract voteAssetContract = (VoteAssetContract) obj;
            return getOwnerAddress().equals(voteAssetContract.getOwnerAddress()) && getVoteAddressList().equals(voteAssetContract.getVoteAddressList()) && getSupport() == voteAssetContract.getSupport() && getCount() == voteAssetContract.getCount() && this.unknownFields.equals(voteAssetContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode();
            if (getVoteAddressCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getVoteAddressList().hashCode();
            }
            int hashBoolean = (((((((((hashCode * 37) + 3) * 53) + Internal.hashBoolean(getSupport())) * 37) + 5) * 53) + getCount()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashBoolean;
            return hashBoolean;
        }

        public static VoteAssetContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static VoteAssetContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static VoteAssetContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static VoteAssetContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static VoteAssetContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static VoteAssetContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static VoteAssetContract parseFrom(InputStream inputStream) throws IOException {
            return (VoteAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static VoteAssetContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VoteAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static VoteAssetContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (VoteAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static VoteAssetContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VoteAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static VoteAssetContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (VoteAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static VoteAssetContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VoteAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(VoteAssetContract voteAssetContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(voteAssetContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VoteAssetContractOrBuilder {
            private int bitField0_;
            private int count_;
            private ByteString ownerAddress_;
            private boolean support_;
            private List<ByteString> voteAddress_;

            @Override
            public int getCount() {
                return this.count_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public boolean getSupport() {
                return this.support_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return VoteAssetContractOuterClass.internal_static_protocol_VoteAssetContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return VoteAssetContractOuterClass.internal_static_protocol_VoteAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(VoteAssetContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.voteAddress_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.voteAddress_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = VoteAssetContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.voteAddress_ = Collections.emptyList();
                this.bitField0_ &= -3;
                this.support_ = false;
                this.count_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return VoteAssetContractOuterClass.internal_static_protocol_VoteAssetContract_descriptor;
            }

            @Override
            public VoteAssetContract getDefaultInstanceForType() {
                return VoteAssetContract.getDefaultInstance();
            }

            @Override
            public VoteAssetContract build() {
                VoteAssetContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public VoteAssetContract buildPartial() {
                VoteAssetContract voteAssetContract = new VoteAssetContract(this);
                voteAssetContract.ownerAddress_ = this.ownerAddress_;
                if ((this.bitField0_ & 2) == 2) {
                    this.voteAddress_ = Collections.unmodifiableList(this.voteAddress_);
                    this.bitField0_ &= -3;
                }
                voteAssetContract.voteAddress_ = this.voteAddress_;
                voteAssetContract.support_ = this.support_;
                voteAssetContract.count_ = this.count_;
                voteAssetContract.bitField0_ = 0;
                onBuilt();
                return voteAssetContract;
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
                if (message instanceof VoteAssetContract) {
                    return mergeFrom((VoteAssetContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(VoteAssetContract voteAssetContract) {
                if (voteAssetContract == VoteAssetContract.getDefaultInstance()) {
                    return this;
                }
                if (voteAssetContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(voteAssetContract.getOwnerAddress());
                }
                if (!voteAssetContract.voteAddress_.isEmpty()) {
                    if (this.voteAddress_.isEmpty()) {
                        this.voteAddress_ = voteAssetContract.voteAddress_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureVoteAddressIsMutable();
                        this.voteAddress_.addAll(voteAssetContract.voteAddress_);
                    }
                    onChanged();
                }
                if (voteAssetContract.getSupport()) {
                    setSupport(voteAssetContract.getSupport());
                }
                if (voteAssetContract.getCount() != 0) {
                    setCount(voteAssetContract.getCount());
                }
                mergeUnknownFields(voteAssetContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.VoteAssetContractOuterClass.VoteAssetContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.VoteAssetContractOuterClass.VoteAssetContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.VoteAssetContractOuterClass$VoteAssetContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = VoteAssetContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            private void ensureVoteAddressIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.voteAddress_ = new ArrayList(this.voteAddress_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<ByteString> getVoteAddressList() {
                return Collections.unmodifiableList(this.voteAddress_);
            }

            @Override
            public int getVoteAddressCount() {
                return this.voteAddress_.size();
            }

            @Override
            public ByteString getVoteAddress(int i) {
                return this.voteAddress_.get(i);
            }

            public Builder setVoteAddress(int i, ByteString byteString) {
                byteString.getClass();
                ensureVoteAddressIsMutable();
                this.voteAddress_.set(i, byteString);
                onChanged();
                return this;
            }

            public Builder addVoteAddress(ByteString byteString) {
                byteString.getClass();
                ensureVoteAddressIsMutable();
                this.voteAddress_.add(byteString);
                onChanged();
                return this;
            }

            public Builder addAllVoteAddress(Iterable<? extends ByteString> iterable) {
                ensureVoteAddressIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.voteAddress_);
                onChanged();
                return this;
            }

            public Builder clearVoteAddress() {
                this.voteAddress_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            public Builder setSupport(boolean z) {
                this.support_ = z;
                onChanged();
                return this;
            }

            public Builder clearSupport() {
                this.support_ = false;
                onChanged();
                return this;
            }

            public Builder setCount(int i) {
                this.count_ = i;
                onChanged();
                return this;
            }

            public Builder clearCount() {
                this.count_ = 0;
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n'core/contract/vote_asset_contract.proto\u0012\bprotocol\"`\n\u0011VoteAssetContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0014\n\fvote_address\u0018\u0002 \u0003(\f\u0012\u000f\n\u0007support\u0018\u0003 \u0001(\b\u0012\r\n\u0005count\u0018\u0005 \u0001(\u0005BE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = VoteAssetContractOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_VoteAssetContract_descriptor = descriptor2;
        internal_static_protocol_VoteAssetContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"OwnerAddress", "VoteAddress", "Support", "Count"});
    }
}
