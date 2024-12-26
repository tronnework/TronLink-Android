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
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;
public final class ProposalContract {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_ProposalApproveContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ProposalApproveContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ProposalCreateContract_ParametersEntry_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ProposalCreateContract_ParametersEntry_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ProposalCreateContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ProposalCreateContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ProposalDeleteContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ProposalDeleteContract_fieldAccessorTable;

    public interface ProposalApproveContractOrBuilder extends MessageOrBuilder {
        boolean getIsAddApproval();

        ByteString getOwnerAddress();

        long getProposalId();
    }

    public interface ProposalCreateContractOrBuilder extends MessageOrBuilder {
        boolean containsParameters(long j);

        ByteString getOwnerAddress();

        @Deprecated
        Map<Long, Long> getParameters();

        int getParametersCount();

        Map<Long, Long> getParametersMap();

        long getParametersOrDefault(long j, long j2);

        long getParametersOrThrow(long j);
    }

    public interface ProposalDeleteContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();

        long getProposalId();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ProposalContract() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class ProposalApproveContract extends GeneratedMessageV3 implements ProposalApproveContractOrBuilder {
        public static final int IS_ADD_APPROVAL_FIELD_NUMBER = 3;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int PROPOSAL_ID_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private boolean isAddApproval_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private long proposalId_;
        private static final ProposalApproveContract DEFAULT_INSTANCE = new ProposalApproveContract();
        private static final Parser<ProposalApproveContract> PARSER = new AbstractParser<ProposalApproveContract>() {
            @Override
            public ProposalApproveContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProposalApproveContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProposalApproveContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ProposalApproveContract> parser() {
            return PARSER;
        }

        @Override
        public ProposalApproveContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public boolean getIsAddApproval() {
            return this.isAddApproval_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<ProposalApproveContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getProposalId() {
            return this.proposalId_;
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

        private ProposalApproveContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ProposalApproveContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.proposalId_ = 0L;
            this.isAddApproval_ = false;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ProposalApproveContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.ownerAddress_ = codedInputStream.readBytes();
                            } else if (readTag == 16) {
                                this.proposalId_ = codedInputStream.readInt64();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.isAddApproval_ = codedInputStream.readBool();
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProposalContract.internal_static_protocol_ProposalApproveContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProposalContract.internal_static_protocol_ProposalApproveContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ProposalApproveContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.proposalId_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            boolean z = this.isAddApproval_;
            if (z) {
                codedOutputStream.writeBool(3, z);
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
            long j = this.proposalId_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            boolean z = this.isAddApproval_;
            if (z) {
                computeBytesSize += CodedOutputStream.computeBoolSize(3, z);
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
            if (!(obj instanceof ProposalApproveContract)) {
                return super.equals(obj);
            }
            ProposalApproveContract proposalApproveContract = (ProposalApproveContract) obj;
            return getOwnerAddress().equals(proposalApproveContract.getOwnerAddress()) && getProposalId() == proposalApproveContract.getProposalId() && getIsAddApproval() == proposalApproveContract.getIsAddApproval() && this.unknownFields.equals(proposalApproveContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getProposalId())) * 37) + 3) * 53) + Internal.hashBoolean(getIsAddApproval())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ProposalApproveContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ProposalApproveContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ProposalApproveContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ProposalApproveContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ProposalApproveContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ProposalApproveContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ProposalApproveContract parseFrom(InputStream inputStream) throws IOException {
            return (ProposalApproveContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProposalApproveContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProposalApproveContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProposalApproveContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProposalApproveContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProposalApproveContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProposalApproveContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProposalApproveContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProposalApproveContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProposalApproveContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProposalApproveContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ProposalApproveContract proposalApproveContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(proposalApproveContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProposalApproveContractOrBuilder {
            private boolean isAddApproval_;
            private ByteString ownerAddress_;
            private long proposalId_;

            @Override
            public boolean getIsAddApproval() {
                return this.isAddApproval_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getProposalId() {
                return this.proposalId_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProposalContract.internal_static_protocol_ProposalApproveContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProposalContract.internal_static_protocol_ProposalApproveContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ProposalApproveContract.class, Builder.class);
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
                boolean unused = ProposalApproveContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.proposalId_ = 0L;
                this.isAddApproval_ = false;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ProposalContract.internal_static_protocol_ProposalApproveContract_descriptor;
            }

            @Override
            public ProposalApproveContract getDefaultInstanceForType() {
                return ProposalApproveContract.getDefaultInstance();
            }

            @Override
            public ProposalApproveContract build() {
                ProposalApproveContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ProposalApproveContract buildPartial() {
                ProposalApproveContract proposalApproveContract = new ProposalApproveContract(this);
                proposalApproveContract.ownerAddress_ = this.ownerAddress_;
                proposalApproveContract.proposalId_ = this.proposalId_;
                proposalApproveContract.isAddApproval_ = this.isAddApproval_;
                onBuilt();
                return proposalApproveContract;
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
                if (message instanceof ProposalApproveContract) {
                    return mergeFrom((ProposalApproveContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProposalApproveContract proposalApproveContract) {
                if (proposalApproveContract == ProposalApproveContract.getDefaultInstance()) {
                    return this;
                }
                if (proposalApproveContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(proposalApproveContract.getOwnerAddress());
                }
                if (proposalApproveContract.getProposalId() != 0) {
                    setProposalId(proposalApproveContract.getProposalId());
                }
                if (proposalApproveContract.getIsAddApproval()) {
                    setIsAddApproval(proposalApproveContract.getIsAddApproval());
                }
                mergeUnknownFields(proposalApproveContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ProposalContract.ProposalApproveContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ProposalContract.ProposalApproveContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ProposalContract$ProposalApproveContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = ProposalApproveContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setProposalId(long j) {
                this.proposalId_ = j;
                onChanged();
                return this;
            }

            public Builder clearProposalId() {
                this.proposalId_ = 0L;
                onChanged();
                return this;
            }

            public Builder setIsAddApproval(boolean z) {
                this.isAddApproval_ = z;
                onChanged();
                return this;
            }

            public Builder clearIsAddApproval() {
                this.isAddApproval_ = false;
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

    public static final class ProposalCreateContract extends GeneratedMessageV3 implements ProposalCreateContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int PARAMETERS_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private MapField<Long, Long> parameters_;
        private static final ProposalCreateContract DEFAULT_INSTANCE = new ProposalCreateContract();
        private static final Parser<ProposalCreateContract> PARSER = new AbstractParser<ProposalCreateContract>() {
            @Override
            public ProposalCreateContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProposalCreateContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProposalCreateContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ProposalCreateContract> parser() {
            return PARSER;
        }

        @Override
        public ProposalCreateContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<ProposalCreateContract> getParserForType() {
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

        private ProposalCreateContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ProposalCreateContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ProposalCreateContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.ownerAddress_ = codedInputStream.readBytes();
                            } else if (readTag != 18) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.parameters_ = MapField.newMapField(ParametersDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(ParametersDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.parameters_.getMutableMap().put((Long) mapEntry.getKey(), (Long) mapEntry.getValue());
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProposalContract.internal_static_protocol_ProposalCreateContract_descriptor;
        }

        @Override
        protected MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetParameters();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProposalContract.internal_static_protocol_ProposalCreateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ProposalCreateContract.class, Builder.class);
        }

        public static final class ParametersDefaultEntryHolder {
            static final MapEntry<Long, Long> defaultEntry = MapEntry.newDefaultInstance(ProposalContract.internal_static_protocol_ProposalCreateContract_ParametersEntry_descriptor, WireFormat.FieldType.INT64, 0L, WireFormat.FieldType.INT64, 0L);

            private ParametersDefaultEntryHolder() {
            }
        }

        public MapField<Long, Long> internalGetParameters() {
            MapField<Long, Long> mapField = this.parameters_;
            return mapField == null ? MapField.emptyMapField(ParametersDefaultEntryHolder.defaultEntry) : mapField;
        }

        @Override
        public int getParametersCount() {
            return internalGetParameters().getMap().size();
        }

        @Override
        public boolean containsParameters(long j) {
            return internalGetParameters().getMap().containsKey(Long.valueOf(j));
        }

        @Override
        @Deprecated
        public Map<Long, Long> getParameters() {
            return getParametersMap();
        }

        @Override
        public Map<Long, Long> getParametersMap() {
            return internalGetParameters().getMap();
        }

        @Override
        public long getParametersOrDefault(long j, long j2) {
            Map<Long, Long> map = internalGetParameters().getMap();
            return map.containsKey(Long.valueOf(j)) ? map.get(Long.valueOf(j)).longValue() : j2;
        }

        @Override
        public long getParametersOrThrow(long j) {
            Map<Long, Long> map = internalGetParameters().getMap();
            if (!map.containsKey(Long.valueOf(j))) {
                throw new IllegalArgumentException();
            }
            return map.get(Long.valueOf(j)).longValue();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            GeneratedMessageV3.serializeLongMapTo(codedOutputStream, internalGetParameters(), ParametersDefaultEntryHolder.defaultEntry, 2);
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.ownerAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.ownerAddress_) : 0;
            for (Map.Entry<Long, Long> entry : internalGetParameters().getMap().entrySet()) {
                computeBytesSize += CodedOutputStream.computeMessageSize(2, ParametersDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
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
            if (!(obj instanceof ProposalCreateContract)) {
                return super.equals(obj);
            }
            ProposalCreateContract proposalCreateContract = (ProposalCreateContract) obj;
            return getOwnerAddress().equals(proposalCreateContract.getOwnerAddress()) && internalGetParameters().equals(proposalCreateContract.internalGetParameters()) && this.unknownFields.equals(proposalCreateContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode();
            if (!internalGetParameters().getMap().isEmpty()) {
                hashCode = (((hashCode * 37) + 2) * 53) + internalGetParameters().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static ProposalCreateContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ProposalCreateContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ProposalCreateContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ProposalCreateContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ProposalCreateContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ProposalCreateContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ProposalCreateContract parseFrom(InputStream inputStream) throws IOException {
            return (ProposalCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProposalCreateContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProposalCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProposalCreateContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProposalCreateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProposalCreateContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProposalCreateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProposalCreateContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProposalCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProposalCreateContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProposalCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ProposalCreateContract proposalCreateContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(proposalCreateContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProposalCreateContractOrBuilder {
            private int bitField0_;
            private ByteString ownerAddress_;
            private MapField<Long, Long> parameters_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProposalContract.internal_static_protocol_ProposalCreateContract_descriptor;
            }

            @Override
            protected MapField internalGetMapField(int i) {
                if (i == 2) {
                    return internalGetParameters();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            @Override
            protected MapField internalGetMutableMapField(int i) {
                if (i == 2) {
                    return internalGetMutableParameters();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProposalContract.internal_static_protocol_ProposalCreateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ProposalCreateContract.class, Builder.class);
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
                boolean unused = ProposalCreateContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                internalGetMutableParameters().clear();
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ProposalContract.internal_static_protocol_ProposalCreateContract_descriptor;
            }

            @Override
            public ProposalCreateContract getDefaultInstanceForType() {
                return ProposalCreateContract.getDefaultInstance();
            }

            @Override
            public ProposalCreateContract build() {
                ProposalCreateContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ProposalCreateContract buildPartial() {
                ProposalCreateContract proposalCreateContract = new ProposalCreateContract(this);
                proposalCreateContract.ownerAddress_ = this.ownerAddress_;
                proposalCreateContract.parameters_ = internalGetParameters();
                proposalCreateContract.parameters_.makeImmutable();
                proposalCreateContract.bitField0_ = 0;
                onBuilt();
                return proposalCreateContract;
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
                if (message instanceof ProposalCreateContract) {
                    return mergeFrom((ProposalCreateContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProposalCreateContract proposalCreateContract) {
                if (proposalCreateContract == ProposalCreateContract.getDefaultInstance()) {
                    return this;
                }
                if (proposalCreateContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(proposalCreateContract.getOwnerAddress());
                }
                internalGetMutableParameters().mergeFrom(proposalCreateContract.internalGetParameters());
                mergeUnknownFields(proposalCreateContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ProposalContract.ProposalCreateContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ProposalContract.ProposalCreateContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ProposalContract$ProposalCreateContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = ProposalCreateContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            private MapField<Long, Long> internalGetParameters() {
                MapField<Long, Long> mapField = this.parameters_;
                return mapField == null ? MapField.emptyMapField(ParametersDefaultEntryHolder.defaultEntry) : mapField;
            }

            private MapField<Long, Long> internalGetMutableParameters() {
                onChanged();
                if (this.parameters_ == null) {
                    this.parameters_ = MapField.newMapField(ParametersDefaultEntryHolder.defaultEntry);
                }
                if (!this.parameters_.isMutable()) {
                    this.parameters_ = this.parameters_.copy();
                }
                return this.parameters_;
            }

            @Override
            public int getParametersCount() {
                return internalGetParameters().getMap().size();
            }

            @Override
            public boolean containsParameters(long j) {
                return internalGetParameters().getMap().containsKey(Long.valueOf(j));
            }

            @Override
            @Deprecated
            public Map<Long, Long> getParameters() {
                return getParametersMap();
            }

            @Override
            public Map<Long, Long> getParametersMap() {
                return internalGetParameters().getMap();
            }

            @Override
            public long getParametersOrDefault(long j, long j2) {
                Map<Long, Long> map = internalGetParameters().getMap();
                return map.containsKey(Long.valueOf(j)) ? map.get(Long.valueOf(j)).longValue() : j2;
            }

            @Override
            public long getParametersOrThrow(long j) {
                Map<Long, Long> map = internalGetParameters().getMap();
                if (!map.containsKey(Long.valueOf(j))) {
                    throw new IllegalArgumentException();
                }
                return map.get(Long.valueOf(j)).longValue();
            }

            public Builder clearParameters() {
                internalGetMutableParameters().getMutableMap().clear();
                return this;
            }

            public Builder removeParameters(long j) {
                internalGetMutableParameters().getMutableMap().remove(Long.valueOf(j));
                return this;
            }

            @Deprecated
            public Map<Long, Long> getMutableParameters() {
                return internalGetMutableParameters().getMutableMap();
            }

            public Builder putParameters(long j, long j2) {
                internalGetMutableParameters().getMutableMap().put(Long.valueOf(j), Long.valueOf(j2));
                return this;
            }

            public Builder putAllParameters(Map<Long, Long> map) {
                internalGetMutableParameters().getMutableMap().putAll(map);
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

    public static final class ProposalDeleteContract extends GeneratedMessageV3 implements ProposalDeleteContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int PROPOSAL_ID_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private long proposalId_;
        private static final ProposalDeleteContract DEFAULT_INSTANCE = new ProposalDeleteContract();
        private static final Parser<ProposalDeleteContract> PARSER = new AbstractParser<ProposalDeleteContract>() {
            @Override
            public ProposalDeleteContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProposalDeleteContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProposalDeleteContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ProposalDeleteContract> parser() {
            return PARSER;
        }

        @Override
        public ProposalDeleteContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<ProposalDeleteContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getProposalId() {
            return this.proposalId_;
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

        private ProposalDeleteContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ProposalDeleteContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.proposalId_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ProposalDeleteContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.proposalId_ = codedInputStream.readInt64();
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
            return ProposalContract.internal_static_protocol_ProposalDeleteContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProposalContract.internal_static_protocol_ProposalDeleteContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ProposalDeleteContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.proposalId_;
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
            long j = this.proposalId_;
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
            if (!(obj instanceof ProposalDeleteContract)) {
                return super.equals(obj);
            }
            ProposalDeleteContract proposalDeleteContract = (ProposalDeleteContract) obj;
            return getOwnerAddress().equals(proposalDeleteContract.getOwnerAddress()) && getProposalId() == proposalDeleteContract.getProposalId() && this.unknownFields.equals(proposalDeleteContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getProposalId())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ProposalDeleteContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ProposalDeleteContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ProposalDeleteContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ProposalDeleteContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ProposalDeleteContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ProposalDeleteContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ProposalDeleteContract parseFrom(InputStream inputStream) throws IOException {
            return (ProposalDeleteContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProposalDeleteContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProposalDeleteContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProposalDeleteContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProposalDeleteContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProposalDeleteContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProposalDeleteContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProposalDeleteContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProposalDeleteContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProposalDeleteContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProposalDeleteContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ProposalDeleteContract proposalDeleteContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(proposalDeleteContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProposalDeleteContractOrBuilder {
            private ByteString ownerAddress_;
            private long proposalId_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getProposalId() {
                return this.proposalId_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProposalContract.internal_static_protocol_ProposalDeleteContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProposalContract.internal_static_protocol_ProposalDeleteContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ProposalDeleteContract.class, Builder.class);
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
                boolean unused = ProposalDeleteContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.proposalId_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ProposalContract.internal_static_protocol_ProposalDeleteContract_descriptor;
            }

            @Override
            public ProposalDeleteContract getDefaultInstanceForType() {
                return ProposalDeleteContract.getDefaultInstance();
            }

            @Override
            public ProposalDeleteContract build() {
                ProposalDeleteContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ProposalDeleteContract buildPartial() {
                ProposalDeleteContract proposalDeleteContract = new ProposalDeleteContract(this);
                proposalDeleteContract.ownerAddress_ = this.ownerAddress_;
                proposalDeleteContract.proposalId_ = this.proposalId_;
                onBuilt();
                return proposalDeleteContract;
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
                if (message instanceof ProposalDeleteContract) {
                    return mergeFrom((ProposalDeleteContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProposalDeleteContract proposalDeleteContract) {
                if (proposalDeleteContract == ProposalDeleteContract.getDefaultInstance()) {
                    return this;
                }
                if (proposalDeleteContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(proposalDeleteContract.getOwnerAddress());
                }
                if (proposalDeleteContract.getProposalId() != 0) {
                    setProposalId(proposalDeleteContract.getProposalId());
                }
                mergeUnknownFields(proposalDeleteContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ProposalContract.ProposalDeleteContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ProposalContract.ProposalDeleteContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ProposalContract$ProposalDeleteContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = ProposalDeleteContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setProposalId(long j) {
                this.proposalId_ = j;
                onChanged();
                return this;
            }

            public Builder clearProposalId() {
                this.proposalId_ = 0L;
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%core/contract/proposal_contract.proto\u0012\bprotocol\"^\n\u0017ProposalApproveContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0013\n\u000bproposal_id\u0018\u0002 \u0001(\u0003\u0012\u0017\n\u000fis_add_approval\u0018\u0003 \u0001(\b\"¨\u0001\n\u0016ProposalCreateContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012D\n\nparameters\u0018\u0002 \u0003(\u000b20.protocol.ProposalCreateContract.ParametersEntry\u001a1\n\u000fParametersEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\u0003\u0012\r\n\u0005value\u0018\u0002 \u0001(\u0003:\u00028\u0001\"D\n\u0016ProposalDeleteContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0013\n\u000bproposal_id\u0018\u0002 \u0001(\u0003BE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProposalContract.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_ProposalApproveContract_descriptor = descriptor2;
        internal_static_protocol_ProposalApproveContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"OwnerAddress", "ProposalId", "IsAddApproval"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_ProposalCreateContract_descriptor = descriptor3;
        internal_static_protocol_ProposalCreateContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"OwnerAddress", "Parameters"});
        Descriptors.Descriptor descriptor4 = descriptor3.getNestedTypes().get(0);
        internal_static_protocol_ProposalCreateContract_ParametersEntry_descriptor = descriptor4;
        internal_static_protocol_ProposalCreateContract_ParametersEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_ProposalDeleteContract_descriptor = descriptor5;
        internal_static_protocol_ProposalDeleteContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"OwnerAddress", "ProposalId"});
    }
}
