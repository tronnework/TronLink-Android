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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class ShieldContract {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_AuthenticationPath_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AuthenticationPath_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_IncrementalMerkleTree_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_IncrementalMerkleTree_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_IncrementalMerkleVoucherInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_IncrementalMerkleVoucherInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_IncrementalMerkleVoucher_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_IncrementalMerkleVoucher_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_MerklePath_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_MerklePath_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_OutputPointInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_OutputPointInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_OutputPoint_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_OutputPoint_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_PedersenHash_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_PedersenHash_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ReceiveDescription_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ReceiveDescription_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ShieldedTransferContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ShieldedTransferContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_SpendDescription_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_SpendDescription_fieldAccessorTable;

    public interface AuthenticationPathOrBuilder extends MessageOrBuilder {
        boolean getValue(int i);

        int getValueCount();

        List<Boolean> getValueList();
    }

    public interface IncrementalMerkleTreeOrBuilder extends MessageOrBuilder {
        PedersenHash getLeft();

        PedersenHashOrBuilder getLeftOrBuilder();

        PedersenHash getParents(int i);

        int getParentsCount();

        List<PedersenHash> getParentsList();

        PedersenHashOrBuilder getParentsOrBuilder(int i);

        List<? extends PedersenHashOrBuilder> getParentsOrBuilderList();

        PedersenHash getRight();

        PedersenHashOrBuilder getRightOrBuilder();

        boolean hasLeft();

        boolean hasRight();
    }

    public interface IncrementalMerkleVoucherInfoOrBuilder extends MessageOrBuilder {
        ByteString getPaths(int i);

        int getPathsCount();

        List<ByteString> getPathsList();

        IncrementalMerkleVoucher getVouchers(int i);

        int getVouchersCount();

        List<IncrementalMerkleVoucher> getVouchersList();

        IncrementalMerkleVoucherOrBuilder getVouchersOrBuilder(int i);

        List<? extends IncrementalMerkleVoucherOrBuilder> getVouchersOrBuilderList();
    }

    public interface IncrementalMerkleVoucherOrBuilder extends MessageOrBuilder {
        IncrementalMerkleTree getCursor();

        long getCursorDepth();

        IncrementalMerkleTreeOrBuilder getCursorOrBuilder();

        PedersenHash getFilled(int i);

        int getFilledCount();

        List<PedersenHash> getFilledList();

        PedersenHashOrBuilder getFilledOrBuilder(int i);

        List<? extends PedersenHashOrBuilder> getFilledOrBuilderList();

        OutputPoint getOutputPoint();

        OutputPointOrBuilder getOutputPointOrBuilder();

        ByteString getRt();

        IncrementalMerkleTree getTree();

        IncrementalMerkleTreeOrBuilder getTreeOrBuilder();

        boolean hasCursor();

        boolean hasOutputPoint();

        boolean hasTree();
    }

    public interface MerklePathOrBuilder extends MessageOrBuilder {
        AuthenticationPath getAuthenticationPaths(int i);

        int getAuthenticationPathsCount();

        List<AuthenticationPath> getAuthenticationPathsList();

        AuthenticationPathOrBuilder getAuthenticationPathsOrBuilder(int i);

        List<? extends AuthenticationPathOrBuilder> getAuthenticationPathsOrBuilderList();

        boolean getIndex(int i);

        int getIndexCount();

        List<Boolean> getIndexList();

        ByteString getRt();
    }

    public interface OutputPointInfoOrBuilder extends MessageOrBuilder {
        int getBlockNum();

        OutputPoint getOutPoints(int i);

        int getOutPointsCount();

        List<OutputPoint> getOutPointsList();

        OutputPointOrBuilder getOutPointsOrBuilder(int i);

        List<? extends OutputPointOrBuilder> getOutPointsOrBuilderList();
    }

    public interface OutputPointOrBuilder extends MessageOrBuilder {
        ByteString getHash();

        int getIndex();
    }

    public interface PedersenHashOrBuilder extends MessageOrBuilder {
        ByteString getContent();
    }

    public interface ReceiveDescriptionOrBuilder extends MessageOrBuilder {
        ByteString getCEnc();

        ByteString getCOut();

        ByteString getEpk();

        ByteString getNoteCommitment();

        ByteString getValueCommitment();

        ByteString getZkproof();
    }

    public interface ShieldedTransferContractOrBuilder extends MessageOrBuilder {
        ByteString getBindingSignature();

        long getFromAmount();

        ReceiveDescription getReceiveDescription(int i);

        int getReceiveDescriptionCount();

        List<ReceiveDescription> getReceiveDescriptionList();

        ReceiveDescriptionOrBuilder getReceiveDescriptionOrBuilder(int i);

        List<? extends ReceiveDescriptionOrBuilder> getReceiveDescriptionOrBuilderList();

        SpendDescription getSpendDescription(int i);

        int getSpendDescriptionCount();

        List<SpendDescription> getSpendDescriptionList();

        SpendDescriptionOrBuilder getSpendDescriptionOrBuilder(int i);

        List<? extends SpendDescriptionOrBuilder> getSpendDescriptionOrBuilderList();

        long getToAmount();

        ByteString getTransparentFromAddress();

        ByteString getTransparentToAddress();
    }

    public interface SpendDescriptionOrBuilder extends MessageOrBuilder {
        ByteString getAnchor();

        ByteString getNullifier();

        ByteString getRk();

        ByteString getSpendAuthoritySignature();

        ByteString getValueCommitment();

        ByteString getZkproof();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ShieldContract() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class AuthenticationPath extends GeneratedMessageV3 implements AuthenticationPathOrBuilder {
        private static final AuthenticationPath DEFAULT_INSTANCE = new AuthenticationPath();
        private static final Parser<AuthenticationPath> PARSER = new AbstractParser<AuthenticationPath>() {
            @Override
            public AuthenticationPath parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AuthenticationPath(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int VALUE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int valueMemoizedSerializedSize;
        private List<Boolean> value_;

        public static AuthenticationPath getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AuthenticationPath> parser() {
            return PARSER;
        }

        @Override
        public AuthenticationPath getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<AuthenticationPath> getParserForType() {
            return PARSER;
        }

        @Override
        public List<Boolean> getValueList() {
            return this.value_;
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

        private AuthenticationPath(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.valueMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
        }

        private AuthenticationPath() {
            this.valueMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.value_ = Collections.emptyList();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AuthenticationPath(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    if (!(z2 & true)) {
                                        this.value_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.value_.add(Boolean.valueOf(codedInputStream.readBool()));
                                } else if (readTag != 10) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.value_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.value_.add(Boolean.valueOf(codedInputStream.readBool()));
                                    }
                                    codedInputStream.popLimit(pushLimit);
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.value_ = Collections.unmodifiableList(this.value_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShieldContract.internal_static_protocol_AuthenticationPath_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_AuthenticationPath_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationPath.class, Builder.class);
        }

        @Override
        public int getValueCount() {
            return this.value_.size();
        }

        @Override
        public boolean getValue(int i) {
            return this.value_.get(i).booleanValue();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if (getValueList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(10);
                codedOutputStream.writeUInt32NoTag(this.valueMemoizedSerializedSize);
            }
            for (int i = 0; i < this.value_.size(); i++) {
                codedOutputStream.writeBoolNoTag(this.value_.get(i).booleanValue());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int size = getValueList().size();
            int computeInt32SizeNoTag = !getValueList().isEmpty() ? size + 1 + CodedOutputStream.computeInt32SizeNoTag(size) : size;
            this.valueMemoizedSerializedSize = size;
            int serializedSize = computeInt32SizeNoTag + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AuthenticationPath)) {
                return super.equals(obj);
            }
            AuthenticationPath authenticationPath = (AuthenticationPath) obj;
            return getValueList().equals(authenticationPath.getValueList()) && this.unknownFields.equals(authenticationPath.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getValueCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getValueList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static AuthenticationPath parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AuthenticationPath parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AuthenticationPath parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AuthenticationPath parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AuthenticationPath parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AuthenticationPath parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AuthenticationPath parseFrom(InputStream inputStream) throws IOException {
            return (AuthenticationPath) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AuthenticationPath parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthenticationPath) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AuthenticationPath parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthenticationPath) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AuthenticationPath parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthenticationPath) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AuthenticationPath parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AuthenticationPath) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AuthenticationPath parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthenticationPath) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AuthenticationPath authenticationPath) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(authenticationPath);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthenticationPathOrBuilder {
            private int bitField0_;
            private List<Boolean> value_;

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_AuthenticationPath_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_AuthenticationPath_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationPath.class, Builder.class);
            }

            private Builder() {
                this.value_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.value_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = AuthenticationPath.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.value_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_AuthenticationPath_descriptor;
            }

            @Override
            public AuthenticationPath getDefaultInstanceForType() {
                return AuthenticationPath.getDefaultInstance();
            }

            @Override
            public AuthenticationPath build() {
                AuthenticationPath buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public AuthenticationPath buildPartial() {
                AuthenticationPath authenticationPath = new AuthenticationPath(this);
                if ((this.bitField0_ & 1) == 1) {
                    this.value_ = Collections.unmodifiableList(this.value_);
                    this.bitField0_ &= -2;
                }
                authenticationPath.value_ = this.value_;
                onBuilt();
                return authenticationPath;
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
                if (message instanceof AuthenticationPath) {
                    return mergeFrom((AuthenticationPath) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AuthenticationPath authenticationPath) {
                if (authenticationPath == AuthenticationPath.getDefaultInstance()) {
                    return this;
                }
                if (!authenticationPath.value_.isEmpty()) {
                    if (this.value_.isEmpty()) {
                        this.value_ = authenticationPath.value_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureValueIsMutable();
                        this.value_.addAll(authenticationPath.value_);
                    }
                    onChanged();
                }
                mergeUnknownFields(authenticationPath.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.AuthenticationPath.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.AuthenticationPath.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$AuthenticationPath$Builder");
            }

            private void ensureValueIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.value_ = new ArrayList(this.value_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<Boolean> getValueList() {
                return Collections.unmodifiableList(this.value_);
            }

            @Override
            public int getValueCount() {
                return this.value_.size();
            }

            @Override
            public boolean getValue(int i) {
                return this.value_.get(i).booleanValue();
            }

            public Builder setValue(int i, boolean z) {
                ensureValueIsMutable();
                this.value_.set(i, Boolean.valueOf(z));
                onChanged();
                return this;
            }

            public Builder addValue(boolean z) {
                ensureValueIsMutable();
                this.value_.add(Boolean.valueOf(z));
                onChanged();
                return this;
            }

            public Builder addAllValue(Iterable<? extends Boolean> iterable) {
                ensureValueIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.value_);
                onChanged();
                return this;
            }

            public Builder clearValue() {
                this.value_ = Collections.emptyList();
                this.bitField0_ &= -2;
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

    public static final class MerklePath extends GeneratedMessageV3 implements MerklePathOrBuilder {
        public static final int AUTHENTICATION_PATHS_FIELD_NUMBER = 1;
        public static final int INDEX_FIELD_NUMBER = 2;
        public static final int RT_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private List<AuthenticationPath> authenticationPaths_;
        private int bitField0_;
        private int indexMemoizedSerializedSize;
        private List<Boolean> index_;
        private byte memoizedIsInitialized;
        private ByteString rt_;
        private static final MerklePath DEFAULT_INSTANCE = new MerklePath();
        private static final Parser<MerklePath> PARSER = new AbstractParser<MerklePath>() {
            @Override
            public MerklePath parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MerklePath(codedInputStream, extensionRegistryLite);
            }
        };

        public static MerklePath getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MerklePath> parser() {
            return PARSER;
        }

        @Override
        public List<AuthenticationPath> getAuthenticationPathsList() {
            return this.authenticationPaths_;
        }

        @Override
        public List<? extends AuthenticationPathOrBuilder> getAuthenticationPathsOrBuilderList() {
            return this.authenticationPaths_;
        }

        @Override
        public MerklePath getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public List<Boolean> getIndexList() {
            return this.index_;
        }

        @Override
        public Parser<MerklePath> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getRt() {
            return this.rt_;
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

        private MerklePath(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.indexMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
        }

        private MerklePath() {
            this.indexMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.authenticationPaths_ = Collections.emptyList();
            this.index_ = Collections.emptyList();
            this.rt_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MerklePath(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    if (!(z2 & true)) {
                                        this.authenticationPaths_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.authenticationPaths_.add((AuthenticationPath) codedInputStream.readMessage(AuthenticationPath.parser(), extensionRegistryLite));
                                } else if (readTag == 16) {
                                    if (!(z2 & true)) {
                                        this.index_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.index_.add(Boolean.valueOf(codedInputStream.readBool()));
                                } else if (readTag == 18) {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.index_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.index_.add(Boolean.valueOf(codedInputStream.readBool()));
                                    }
                                    codedInputStream.popLimit(pushLimit);
                                } else if (readTag != 26) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.rt_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.authenticationPaths_ = Collections.unmodifiableList(this.authenticationPaths_);
                    }
                    if (z2 & true) {
                        this.index_ = Collections.unmodifiableList(this.index_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShieldContract.internal_static_protocol_MerklePath_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_MerklePath_fieldAccessorTable.ensureFieldAccessorsInitialized(MerklePath.class, Builder.class);
        }

        @Override
        public int getAuthenticationPathsCount() {
            return this.authenticationPaths_.size();
        }

        @Override
        public AuthenticationPath getAuthenticationPaths(int i) {
            return this.authenticationPaths_.get(i);
        }

        @Override
        public AuthenticationPathOrBuilder getAuthenticationPathsOrBuilder(int i) {
            return this.authenticationPaths_.get(i);
        }

        @Override
        public int getIndexCount() {
            return this.index_.size();
        }

        @Override
        public boolean getIndex(int i) {
            return this.index_.get(i).booleanValue();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.authenticationPaths_.size(); i++) {
                codedOutputStream.writeMessage(1, this.authenticationPaths_.get(i));
            }
            if (getIndexList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(18);
                codedOutputStream.writeUInt32NoTag(this.indexMemoizedSerializedSize);
            }
            for (int i2 = 0; i2 < this.index_.size(); i2++) {
                codedOutputStream.writeBoolNoTag(this.index_.get(i2).booleanValue());
            }
            if (!this.rt_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.rt_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.authenticationPaths_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.authenticationPaths_.get(i3));
            }
            int size = getIndexList().size();
            int i4 = i2 + size;
            if (!getIndexList().isEmpty()) {
                i4 = i4 + 1 + CodedOutputStream.computeInt32SizeNoTag(size);
            }
            this.indexMemoizedSerializedSize = size;
            if (!this.rt_.isEmpty()) {
                i4 += CodedOutputStream.computeBytesSize(3, this.rt_);
            }
            int serializedSize = i4 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MerklePath)) {
                return super.equals(obj);
            }
            MerklePath merklePath = (MerklePath) obj;
            return getAuthenticationPathsList().equals(merklePath.getAuthenticationPathsList()) && getIndexList().equals(merklePath.getIndexList()) && getRt().equals(merklePath.getRt()) && this.unknownFields.equals(merklePath.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getAuthenticationPathsCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getAuthenticationPathsList().hashCode();
            }
            if (getIndexCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getIndexList().hashCode();
            }
            int hashCode2 = (((((hashCode * 37) + 3) * 53) + getRt().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static MerklePath parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static MerklePath parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MerklePath parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static MerklePath parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MerklePath parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static MerklePath parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static MerklePath parseFrom(InputStream inputStream) throws IOException {
            return (MerklePath) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MerklePath parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MerklePath) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MerklePath parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MerklePath) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MerklePath parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MerklePath) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MerklePath parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MerklePath) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MerklePath parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MerklePath) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MerklePath merklePath) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(merklePath);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MerklePathOrBuilder {
            private RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> authenticationPathsBuilder_;
            private List<AuthenticationPath> authenticationPaths_;
            private int bitField0_;
            private List<Boolean> index_;
            private ByteString rt_;

            @Override
            public ByteString getRt() {
                return this.rt_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_MerklePath_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_MerklePath_fieldAccessorTable.ensureFieldAccessorsInitialized(MerklePath.class, Builder.class);
            }

            private Builder() {
                this.authenticationPaths_ = Collections.emptyList();
                this.index_ = Collections.emptyList();
                this.rt_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.authenticationPaths_ = Collections.emptyList();
                this.index_ = Collections.emptyList();
                this.rt_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (MerklePath.alwaysUseFieldBuilders) {
                    getAuthenticationPathsFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.authenticationPaths_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.index_ = Collections.emptyList();
                this.bitField0_ &= -3;
                this.rt_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_MerklePath_descriptor;
            }

            @Override
            public MerklePath getDefaultInstanceForType() {
                return MerklePath.getDefaultInstance();
            }

            @Override
            public MerklePath build() {
                MerklePath buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public MerklePath buildPartial() {
                MerklePath merklePath = new MerklePath(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) == 1) {
                        this.authenticationPaths_ = Collections.unmodifiableList(this.authenticationPaths_);
                        this.bitField0_ &= -2;
                    }
                    merklePath.authenticationPaths_ = this.authenticationPaths_;
                } else {
                    merklePath.authenticationPaths_ = repeatedFieldBuilderV3.build();
                }
                if ((this.bitField0_ & 2) == 2) {
                    this.index_ = Collections.unmodifiableList(this.index_);
                    this.bitField0_ &= -3;
                }
                merklePath.index_ = this.index_;
                merklePath.rt_ = this.rt_;
                merklePath.bitField0_ = 0;
                onBuilt();
                return merklePath;
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
                if (message instanceof MerklePath) {
                    return mergeFrom((MerklePath) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(MerklePath merklePath) {
                if (merklePath == MerklePath.getDefaultInstance()) {
                    return this;
                }
                if (this.authenticationPathsBuilder_ == null) {
                    if (!merklePath.authenticationPaths_.isEmpty()) {
                        if (this.authenticationPaths_.isEmpty()) {
                            this.authenticationPaths_ = merklePath.authenticationPaths_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureAuthenticationPathsIsMutable();
                            this.authenticationPaths_.addAll(merklePath.authenticationPaths_);
                        }
                        onChanged();
                    }
                } else if (!merklePath.authenticationPaths_.isEmpty()) {
                    if (!this.authenticationPathsBuilder_.isEmpty()) {
                        this.authenticationPathsBuilder_.addAllMessages(merklePath.authenticationPaths_);
                    } else {
                        this.authenticationPathsBuilder_.dispose();
                        this.authenticationPathsBuilder_ = null;
                        this.authenticationPaths_ = merklePath.authenticationPaths_;
                        this.bitField0_ &= -2;
                        this.authenticationPathsBuilder_ = MerklePath.alwaysUseFieldBuilders ? getAuthenticationPathsFieldBuilder() : null;
                    }
                }
                if (!merklePath.index_.isEmpty()) {
                    if (this.index_.isEmpty()) {
                        this.index_ = merklePath.index_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureIndexIsMutable();
                        this.index_.addAll(merklePath.index_);
                    }
                    onChanged();
                }
                if (merklePath.getRt() != ByteString.EMPTY) {
                    setRt(merklePath.getRt());
                }
                mergeUnknownFields(merklePath.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.MerklePath.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.MerklePath.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$MerklePath$Builder");
            }

            private void ensureAuthenticationPathsIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.authenticationPaths_ = new ArrayList(this.authenticationPaths_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<AuthenticationPath> getAuthenticationPathsList() {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.authenticationPaths_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getAuthenticationPathsCount() {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.authenticationPaths_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public AuthenticationPath getAuthenticationPaths(int i) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.authenticationPaths_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setAuthenticationPaths(int i, AuthenticationPath authenticationPath) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    authenticationPath.getClass();
                    ensureAuthenticationPathsIsMutable();
                    this.authenticationPaths_.set(i, authenticationPath);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, authenticationPath);
                }
                return this;
            }

            public Builder setAuthenticationPaths(int i, AuthenticationPath.Builder builder) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAuthenticationPathsIsMutable();
                    this.authenticationPaths_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAuthenticationPaths(AuthenticationPath authenticationPath) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    authenticationPath.getClass();
                    ensureAuthenticationPathsIsMutable();
                    this.authenticationPaths_.add(authenticationPath);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(authenticationPath);
                }
                return this;
            }

            public Builder addAuthenticationPaths(int i, AuthenticationPath authenticationPath) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    authenticationPath.getClass();
                    ensureAuthenticationPathsIsMutable();
                    this.authenticationPaths_.add(i, authenticationPath);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, authenticationPath);
                }
                return this;
            }

            public Builder addAuthenticationPaths(AuthenticationPath.Builder builder) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAuthenticationPathsIsMutable();
                    this.authenticationPaths_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addAuthenticationPaths(int i, AuthenticationPath.Builder builder) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAuthenticationPathsIsMutable();
                    this.authenticationPaths_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllAuthenticationPaths(Iterable<? extends AuthenticationPath> iterable) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAuthenticationPathsIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.authenticationPaths_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearAuthenticationPaths() {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.authenticationPaths_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeAuthenticationPaths(int i) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAuthenticationPathsIsMutable();
                    this.authenticationPaths_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public AuthenticationPath.Builder getAuthenticationPathsBuilder(int i) {
                return getAuthenticationPathsFieldBuilder().getBuilder(i);
            }

            @Override
            public AuthenticationPathOrBuilder getAuthenticationPathsOrBuilder(int i) {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.authenticationPaths_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends AuthenticationPathOrBuilder> getAuthenticationPathsOrBuilderList() {
                RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> repeatedFieldBuilderV3 = this.authenticationPathsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.authenticationPaths_);
            }

            public AuthenticationPath.Builder addAuthenticationPathsBuilder() {
                return getAuthenticationPathsFieldBuilder().addBuilder(AuthenticationPath.getDefaultInstance());
            }

            public AuthenticationPath.Builder addAuthenticationPathsBuilder(int i) {
                return getAuthenticationPathsFieldBuilder().addBuilder(i, AuthenticationPath.getDefaultInstance());
            }

            public List<AuthenticationPath.Builder> getAuthenticationPathsBuilderList() {
                return getAuthenticationPathsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<AuthenticationPath, AuthenticationPath.Builder, AuthenticationPathOrBuilder> getAuthenticationPathsFieldBuilder() {
                if (this.authenticationPathsBuilder_ == null) {
                    this.authenticationPathsBuilder_ = new RepeatedFieldBuilderV3<>(this.authenticationPaths_, (this.bitField0_ & 1) == 1, getParentForChildren(), isClean());
                    this.authenticationPaths_ = null;
                }
                return this.authenticationPathsBuilder_;
            }

            private void ensureIndexIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.index_ = new ArrayList(this.index_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<Boolean> getIndexList() {
                return Collections.unmodifiableList(this.index_);
            }

            @Override
            public int getIndexCount() {
                return this.index_.size();
            }

            @Override
            public boolean getIndex(int i) {
                return this.index_.get(i).booleanValue();
            }

            public Builder setIndex(int i, boolean z) {
                ensureIndexIsMutable();
                this.index_.set(i, Boolean.valueOf(z));
                onChanged();
                return this;
            }

            public Builder addIndex(boolean z) {
                ensureIndexIsMutable();
                this.index_.add(Boolean.valueOf(z));
                onChanged();
                return this;
            }

            public Builder addAllIndex(Iterable<? extends Boolean> iterable) {
                ensureIndexIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.index_);
                onChanged();
                return this;
            }

            public Builder clearIndex() {
                this.index_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            public Builder setRt(ByteString byteString) {
                byteString.getClass();
                this.rt_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearRt() {
                this.rt_ = MerklePath.getDefaultInstance().getRt();
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

    public static final class OutputPoint extends GeneratedMessageV3 implements OutputPointOrBuilder {
        public static final int HASH_FIELD_NUMBER = 1;
        public static final int INDEX_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private ByteString hash_;
        private int index_;
        private byte memoizedIsInitialized;
        private static final OutputPoint DEFAULT_INSTANCE = new OutputPoint();
        private static final Parser<OutputPoint> PARSER = new AbstractParser<OutputPoint>() {
            @Override
            public OutputPoint parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new OutputPoint(codedInputStream, extensionRegistryLite);
            }
        };

        public static OutputPoint getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<OutputPoint> parser() {
            return PARSER;
        }

        @Override
        public OutputPoint getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getHash() {
            return this.hash_;
        }

        @Override
        public int getIndex() {
            return this.index_;
        }

        @Override
        public Parser<OutputPoint> getParserForType() {
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

        private OutputPoint(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private OutputPoint() {
            this.memoizedIsInitialized = (byte) -1;
            this.hash_ = ByteString.EMPTY;
            this.index_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private OutputPoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.hash_ = codedInputStream.readBytes();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.index_ = codedInputStream.readInt32();
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
            return ShieldContract.internal_static_protocol_OutputPoint_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_OutputPoint_fieldAccessorTable.ensureFieldAccessorsInitialized(OutputPoint.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.hash_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.hash_);
            }
            int i = this.index_;
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
            int computeBytesSize = !this.hash_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.hash_) : 0;
            int i2 = this.index_;
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
            if (!(obj instanceof OutputPoint)) {
                return super.equals(obj);
            }
            OutputPoint outputPoint = (OutputPoint) obj;
            return getHash().equals(outputPoint.getHash()) && getIndex() == outputPoint.getIndex() && this.unknownFields.equals(outputPoint.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getHash().hashCode()) * 37) + 2) * 53) + getIndex()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static OutputPoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static OutputPoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static OutputPoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static OutputPoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static OutputPoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static OutputPoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static OutputPoint parseFrom(InputStream inputStream) throws IOException {
            return (OutputPoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static OutputPoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (OutputPoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static OutputPoint parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (OutputPoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static OutputPoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (OutputPoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static OutputPoint parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (OutputPoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static OutputPoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (OutputPoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(OutputPoint outputPoint) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(outputPoint);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OutputPointOrBuilder {
            private ByteString hash_;
            private int index_;

            @Override
            public ByteString getHash() {
                return this.hash_;
            }

            @Override
            public int getIndex() {
                return this.index_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_OutputPoint_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_OutputPoint_fieldAccessorTable.ensureFieldAccessorsInitialized(OutputPoint.class, Builder.class);
            }

            private Builder() {
                this.hash_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.hash_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = OutputPoint.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.hash_ = ByteString.EMPTY;
                this.index_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_OutputPoint_descriptor;
            }

            @Override
            public OutputPoint getDefaultInstanceForType() {
                return OutputPoint.getDefaultInstance();
            }

            @Override
            public OutputPoint build() {
                OutputPoint buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public OutputPoint buildPartial() {
                OutputPoint outputPoint = new OutputPoint(this);
                outputPoint.hash_ = this.hash_;
                outputPoint.index_ = this.index_;
                onBuilt();
                return outputPoint;
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
                if (message instanceof OutputPoint) {
                    return mergeFrom((OutputPoint) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(OutputPoint outputPoint) {
                if (outputPoint == OutputPoint.getDefaultInstance()) {
                    return this;
                }
                if (outputPoint.getHash() != ByteString.EMPTY) {
                    setHash(outputPoint.getHash());
                }
                if (outputPoint.getIndex() != 0) {
                    setIndex(outputPoint.getIndex());
                }
                mergeUnknownFields(outputPoint.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.OutputPoint.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.OutputPoint.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$OutputPoint$Builder");
            }

            public Builder setHash(ByteString byteString) {
                byteString.getClass();
                this.hash_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearHash() {
                this.hash_ = OutputPoint.getDefaultInstance().getHash();
                onChanged();
                return this;
            }

            public Builder setIndex(int i) {
                this.index_ = i;
                onChanged();
                return this;
            }

            public Builder clearIndex() {
                this.index_ = 0;
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

    public static final class OutputPointInfo extends GeneratedMessageV3 implements OutputPointInfoOrBuilder {
        public static final int BLOCK_NUM_FIELD_NUMBER = 2;
        public static final int OUT_POINTS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int blockNum_;
        private byte memoizedIsInitialized;
        private List<OutputPoint> outPoints_;
        private static final OutputPointInfo DEFAULT_INSTANCE = new OutputPointInfo();
        private static final Parser<OutputPointInfo> PARSER = new AbstractParser<OutputPointInfo>() {
            @Override
            public OutputPointInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new OutputPointInfo(codedInputStream, extensionRegistryLite);
            }
        };

        public static OutputPointInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<OutputPointInfo> parser() {
            return PARSER;
        }

        @Override
        public int getBlockNum() {
            return this.blockNum_;
        }

        @Override
        public OutputPointInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public List<OutputPoint> getOutPointsList() {
            return this.outPoints_;
        }

        @Override
        public List<? extends OutputPointOrBuilder> getOutPointsOrBuilderList() {
            return this.outPoints_;
        }

        @Override
        public Parser<OutputPointInfo> getParserForType() {
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

        private OutputPointInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private OutputPointInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.outPoints_ = Collections.emptyList();
            this.blockNum_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private OutputPointInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (!(z2 & true)) {
                                    this.outPoints_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.outPoints_.add((OutputPoint) codedInputStream.readMessage(OutputPoint.parser(), extensionRegistryLite));
                            } else if (readTag != 16) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.blockNum_ = codedInputStream.readInt32();
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
                        this.outPoints_ = Collections.unmodifiableList(this.outPoints_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShieldContract.internal_static_protocol_OutputPointInfo_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_OutputPointInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(OutputPointInfo.class, Builder.class);
        }

        @Override
        public int getOutPointsCount() {
            return this.outPoints_.size();
        }

        @Override
        public OutputPoint getOutPoints(int i) {
            return this.outPoints_.get(i);
        }

        @Override
        public OutputPointOrBuilder getOutPointsOrBuilder(int i) {
            return this.outPoints_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.outPoints_.size(); i++) {
                codedOutputStream.writeMessage(1, this.outPoints_.get(i));
            }
            int i2 = this.blockNum_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(2, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.outPoints_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.outPoints_.get(i3));
            }
            int i4 = this.blockNum_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeInt32Size(2, i4);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof OutputPointInfo)) {
                return super.equals(obj);
            }
            OutputPointInfo outputPointInfo = (OutputPointInfo) obj;
            return getOutPointsList().equals(outputPointInfo.getOutPointsList()) && getBlockNum() == outputPointInfo.getBlockNum() && this.unknownFields.equals(outputPointInfo.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getOutPointsCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getOutPointsList().hashCode();
            }
            int blockNum = (((((hashCode * 37) + 2) * 53) + getBlockNum()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = blockNum;
            return blockNum;
        }

        public static OutputPointInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static OutputPointInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static OutputPointInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static OutputPointInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static OutputPointInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static OutputPointInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static OutputPointInfo parseFrom(InputStream inputStream) throws IOException {
            return (OutputPointInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static OutputPointInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (OutputPointInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static OutputPointInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (OutputPointInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static OutputPointInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (OutputPointInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static OutputPointInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (OutputPointInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static OutputPointInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (OutputPointInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(OutputPointInfo outputPointInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(outputPointInfo);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OutputPointInfoOrBuilder {
            private int bitField0_;
            private int blockNum_;
            private RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> outPointsBuilder_;
            private List<OutputPoint> outPoints_;

            @Override
            public int getBlockNum() {
                return this.blockNum_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_OutputPointInfo_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_OutputPointInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(OutputPointInfo.class, Builder.class);
            }

            private Builder() {
                this.outPoints_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.outPoints_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (OutputPointInfo.alwaysUseFieldBuilders) {
                    getOutPointsFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.outPoints_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.blockNum_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_OutputPointInfo_descriptor;
            }

            @Override
            public OutputPointInfo getDefaultInstanceForType() {
                return OutputPointInfo.getDefaultInstance();
            }

            @Override
            public OutputPointInfo build() {
                OutputPointInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public OutputPointInfo buildPartial() {
                OutputPointInfo outputPointInfo = new OutputPointInfo(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) == 1) {
                        this.outPoints_ = Collections.unmodifiableList(this.outPoints_);
                        this.bitField0_ &= -2;
                    }
                    outputPointInfo.outPoints_ = this.outPoints_;
                } else {
                    outputPointInfo.outPoints_ = repeatedFieldBuilderV3.build();
                }
                outputPointInfo.blockNum_ = this.blockNum_;
                outputPointInfo.bitField0_ = 0;
                onBuilt();
                return outputPointInfo;
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
                if (message instanceof OutputPointInfo) {
                    return mergeFrom((OutputPointInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(OutputPointInfo outputPointInfo) {
                if (outputPointInfo == OutputPointInfo.getDefaultInstance()) {
                    return this;
                }
                if (this.outPointsBuilder_ == null) {
                    if (!outputPointInfo.outPoints_.isEmpty()) {
                        if (this.outPoints_.isEmpty()) {
                            this.outPoints_ = outputPointInfo.outPoints_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureOutPointsIsMutable();
                            this.outPoints_.addAll(outputPointInfo.outPoints_);
                        }
                        onChanged();
                    }
                } else if (!outputPointInfo.outPoints_.isEmpty()) {
                    if (!this.outPointsBuilder_.isEmpty()) {
                        this.outPointsBuilder_.addAllMessages(outputPointInfo.outPoints_);
                    } else {
                        this.outPointsBuilder_.dispose();
                        this.outPointsBuilder_ = null;
                        this.outPoints_ = outputPointInfo.outPoints_;
                        this.bitField0_ &= -2;
                        this.outPointsBuilder_ = OutputPointInfo.alwaysUseFieldBuilders ? getOutPointsFieldBuilder() : null;
                    }
                }
                if (outputPointInfo.getBlockNum() != 0) {
                    setBlockNum(outputPointInfo.getBlockNum());
                }
                mergeUnknownFields(outputPointInfo.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.OutputPointInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.OutputPointInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$OutputPointInfo$Builder");
            }

            private void ensureOutPointsIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.outPoints_ = new ArrayList(this.outPoints_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<OutputPoint> getOutPointsList() {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.outPoints_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getOutPointsCount() {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.outPoints_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public OutputPoint getOutPoints(int i) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.outPoints_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setOutPoints(int i, OutputPoint outputPoint) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    outputPoint.getClass();
                    ensureOutPointsIsMutable();
                    this.outPoints_.set(i, outputPoint);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, outputPoint);
                }
                return this;
            }

            public Builder setOutPoints(int i, OutputPoint.Builder builder) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutPointsIsMutable();
                    this.outPoints_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addOutPoints(OutputPoint outputPoint) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    outputPoint.getClass();
                    ensureOutPointsIsMutable();
                    this.outPoints_.add(outputPoint);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(outputPoint);
                }
                return this;
            }

            public Builder addOutPoints(int i, OutputPoint outputPoint) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    outputPoint.getClass();
                    ensureOutPointsIsMutable();
                    this.outPoints_.add(i, outputPoint);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, outputPoint);
                }
                return this;
            }

            public Builder addOutPoints(OutputPoint.Builder builder) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutPointsIsMutable();
                    this.outPoints_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addOutPoints(int i, OutputPoint.Builder builder) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutPointsIsMutable();
                    this.outPoints_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllOutPoints(Iterable<? extends OutputPoint> iterable) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutPointsIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.outPoints_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearOutPoints() {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.outPoints_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeOutPoints(int i) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutPointsIsMutable();
                    this.outPoints_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public OutputPoint.Builder getOutPointsBuilder(int i) {
                return getOutPointsFieldBuilder().getBuilder(i);
            }

            @Override
            public OutputPointOrBuilder getOutPointsOrBuilder(int i) {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.outPoints_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends OutputPointOrBuilder> getOutPointsOrBuilderList() {
                RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> repeatedFieldBuilderV3 = this.outPointsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.outPoints_);
            }

            public OutputPoint.Builder addOutPointsBuilder() {
                return getOutPointsFieldBuilder().addBuilder(OutputPoint.getDefaultInstance());
            }

            public OutputPoint.Builder addOutPointsBuilder(int i) {
                return getOutPointsFieldBuilder().addBuilder(i, OutputPoint.getDefaultInstance());
            }

            public List<OutputPoint.Builder> getOutPointsBuilderList() {
                return getOutPointsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> getOutPointsFieldBuilder() {
                if (this.outPointsBuilder_ == null) {
                    this.outPointsBuilder_ = new RepeatedFieldBuilderV3<>(this.outPoints_, (this.bitField0_ & 1) == 1, getParentForChildren(), isClean());
                    this.outPoints_ = null;
                }
                return this.outPointsBuilder_;
            }

            public Builder setBlockNum(int i) {
                this.blockNum_ = i;
                onChanged();
                return this;
            }

            public Builder clearBlockNum() {
                this.blockNum_ = 0;
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

    public static final class PedersenHash extends GeneratedMessageV3 implements PedersenHashOrBuilder {
        public static final int CONTENT_FIELD_NUMBER = 1;
        private static final PedersenHash DEFAULT_INSTANCE = new PedersenHash();
        private static final Parser<PedersenHash> PARSER = new AbstractParser<PedersenHash>() {
            @Override
            public PedersenHash parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PedersenHash(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private ByteString content_;
        private byte memoizedIsInitialized;

        public static PedersenHash getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PedersenHash> parser() {
            return PARSER;
        }

        @Override
        public ByteString getContent() {
            return this.content_;
        }

        @Override
        public PedersenHash getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<PedersenHash> getParserForType() {
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

        private PedersenHash(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private PedersenHash() {
            this.memoizedIsInitialized = (byte) -1;
            this.content_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PedersenHash(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag != 10) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.content_ = codedInputStream.readBytes();
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
            return ShieldContract.internal_static_protocol_PedersenHash_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_PedersenHash_fieldAccessorTable.ensureFieldAccessorsInitialized(PedersenHash.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.content_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.content_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (!this.content_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.content_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeBytesSize;
            return computeBytesSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PedersenHash)) {
                return super.equals(obj);
            }
            PedersenHash pedersenHash = (PedersenHash) obj;
            return getContent().equals(pedersenHash.getContent()) && this.unknownFields.equals(pedersenHash.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getContent().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static PedersenHash parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PedersenHash parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PedersenHash parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PedersenHash parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PedersenHash parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PedersenHash parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PedersenHash parseFrom(InputStream inputStream) throws IOException {
            return (PedersenHash) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PedersenHash parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PedersenHash) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PedersenHash parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PedersenHash) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PedersenHash parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PedersenHash) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PedersenHash parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PedersenHash) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PedersenHash parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PedersenHash) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PedersenHash pedersenHash) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pedersenHash);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PedersenHashOrBuilder {
            private ByteString content_;

            @Override
            public ByteString getContent() {
                return this.content_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_PedersenHash_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_PedersenHash_fieldAccessorTable.ensureFieldAccessorsInitialized(PedersenHash.class, Builder.class);
            }

            private Builder() {
                this.content_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.content_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PedersenHash.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.content_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_PedersenHash_descriptor;
            }

            @Override
            public PedersenHash getDefaultInstanceForType() {
                return PedersenHash.getDefaultInstance();
            }

            @Override
            public PedersenHash build() {
                PedersenHash buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PedersenHash buildPartial() {
                PedersenHash pedersenHash = new PedersenHash(this);
                pedersenHash.content_ = this.content_;
                onBuilt();
                return pedersenHash;
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
                if (message instanceof PedersenHash) {
                    return mergeFrom((PedersenHash) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PedersenHash pedersenHash) {
                if (pedersenHash == PedersenHash.getDefaultInstance()) {
                    return this;
                }
                if (pedersenHash.getContent() != ByteString.EMPTY) {
                    setContent(pedersenHash.getContent());
                }
                mergeUnknownFields(pedersenHash.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.PedersenHash.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.PedersenHash.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$PedersenHash$Builder");
            }

            public Builder setContent(ByteString byteString) {
                byteString.getClass();
                this.content_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearContent() {
                this.content_ = PedersenHash.getDefaultInstance().getContent();
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

    public static final class IncrementalMerkleTree extends GeneratedMessageV3 implements IncrementalMerkleTreeOrBuilder {
        public static final int LEFT_FIELD_NUMBER = 1;
        public static final int PARENTS_FIELD_NUMBER = 3;
        public static final int RIGHT_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private PedersenHash left_;
        private byte memoizedIsInitialized;
        private List<PedersenHash> parents_;
        private PedersenHash right_;
        private static final IncrementalMerkleTree DEFAULT_INSTANCE = new IncrementalMerkleTree();
        private static final Parser<IncrementalMerkleTree> PARSER = new AbstractParser<IncrementalMerkleTree>() {
            @Override
            public IncrementalMerkleTree parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new IncrementalMerkleTree(codedInputStream, extensionRegistryLite);
            }
        };

        public static IncrementalMerkleTree getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<IncrementalMerkleTree> parser() {
            return PARSER;
        }

        @Override
        public IncrementalMerkleTree getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public List<PedersenHash> getParentsList() {
            return this.parents_;
        }

        @Override
        public List<? extends PedersenHashOrBuilder> getParentsOrBuilderList() {
            return this.parents_;
        }

        @Override
        public Parser<IncrementalMerkleTree> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasLeft() {
            return this.left_ != null;
        }

        @Override
        public boolean hasRight() {
            return this.right_ != null;
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

        private IncrementalMerkleTree(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private IncrementalMerkleTree() {
            this.memoizedIsInitialized = (byte) -1;
            this.parents_ = Collections.emptyList();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private IncrementalMerkleTree(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            PedersenHash.Builder builder;
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
                                PedersenHash pedersenHash = this.left_;
                                builder = pedersenHash != null ? pedersenHash.toBuilder() : null;
                                PedersenHash pedersenHash2 = (PedersenHash) codedInputStream.readMessage(PedersenHash.parser(), extensionRegistryLite);
                                this.left_ = pedersenHash2;
                                if (builder != null) {
                                    builder.mergeFrom(pedersenHash2);
                                    this.left_ = builder.buildPartial();
                                }
                            } else if (readTag == 18) {
                                PedersenHash pedersenHash3 = this.right_;
                                builder = pedersenHash3 != null ? pedersenHash3.toBuilder() : null;
                                PedersenHash pedersenHash4 = (PedersenHash) codedInputStream.readMessage(PedersenHash.parser(), extensionRegistryLite);
                                this.right_ = pedersenHash4;
                                if (builder != null) {
                                    builder.mergeFrom(pedersenHash4);
                                    this.right_ = builder.buildPartial();
                                }
                            } else if (readTag != 26) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.parents_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.parents_.add((PedersenHash) codedInputStream.readMessage(PedersenHash.parser(), extensionRegistryLite));
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
                        this.parents_ = Collections.unmodifiableList(this.parents_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShieldContract.internal_static_protocol_IncrementalMerkleTree_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_IncrementalMerkleTree_fieldAccessorTable.ensureFieldAccessorsInitialized(IncrementalMerkleTree.class, Builder.class);
        }

        @Override
        public PedersenHash getLeft() {
            PedersenHash pedersenHash = this.left_;
            return pedersenHash == null ? PedersenHash.getDefaultInstance() : pedersenHash;
        }

        @Override
        public PedersenHashOrBuilder getLeftOrBuilder() {
            return getLeft();
        }

        @Override
        public PedersenHash getRight() {
            PedersenHash pedersenHash = this.right_;
            return pedersenHash == null ? PedersenHash.getDefaultInstance() : pedersenHash;
        }

        @Override
        public PedersenHashOrBuilder getRightOrBuilder() {
            return getRight();
        }

        @Override
        public int getParentsCount() {
            return this.parents_.size();
        }

        @Override
        public PedersenHash getParents(int i) {
            return this.parents_.get(i);
        }

        @Override
        public PedersenHashOrBuilder getParentsOrBuilder(int i) {
            return this.parents_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.left_ != null) {
                codedOutputStream.writeMessage(1, getLeft());
            }
            if (this.right_ != null) {
                codedOutputStream.writeMessage(2, getRight());
            }
            for (int i = 0; i < this.parents_.size(); i++) {
                codedOutputStream.writeMessage(3, this.parents_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.left_ != null ? CodedOutputStream.computeMessageSize(1, getLeft()) : 0;
            if (this.right_ != null) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, getRight());
            }
            for (int i2 = 0; i2 < this.parents_.size(); i2++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, this.parents_.get(i2));
            }
            int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            boolean z;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof IncrementalMerkleTree)) {
                return super.equals(obj);
            }
            IncrementalMerkleTree incrementalMerkleTree = (IncrementalMerkleTree) obj;
            boolean z2 = hasLeft() == incrementalMerkleTree.hasLeft();
            if (!hasLeft() ? z2 : !(!z2 || !getLeft().equals(incrementalMerkleTree.getLeft()))) {
                if (hasRight() == incrementalMerkleTree.hasRight()) {
                    z = true;
                    if (hasRight() ? z : !(!z || !getRight().equals(incrementalMerkleTree.getRight()))) {
                        if (!getParentsList().equals(incrementalMerkleTree.getParentsList()) && this.unknownFields.equals(incrementalMerkleTree.unknownFields)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            z = false;
            if (hasRight()) {
                return false;
            }
            if (!getParentsList().equals(incrementalMerkleTree.getParentsList())) {
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasLeft()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getLeft().hashCode();
            }
            if (hasRight()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getRight().hashCode();
            }
            if (getParentsCount() > 0) {
                hashCode = (((hashCode * 37) + 3) * 53) + getParentsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static IncrementalMerkleTree parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static IncrementalMerkleTree parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static IncrementalMerkleTree parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static IncrementalMerkleTree parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static IncrementalMerkleTree parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static IncrementalMerkleTree parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static IncrementalMerkleTree parseFrom(InputStream inputStream) throws IOException {
            return (IncrementalMerkleTree) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static IncrementalMerkleTree parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncrementalMerkleTree) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static IncrementalMerkleTree parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (IncrementalMerkleTree) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static IncrementalMerkleTree parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncrementalMerkleTree) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static IncrementalMerkleTree parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (IncrementalMerkleTree) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static IncrementalMerkleTree parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncrementalMerkleTree) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(IncrementalMerkleTree incrementalMerkleTree) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(incrementalMerkleTree);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements IncrementalMerkleTreeOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> leftBuilder_;
            private PedersenHash left_;
            private RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> parentsBuilder_;
            private List<PedersenHash> parents_;
            private SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> rightBuilder_;
            private PedersenHash right_;

            @Override
            public boolean hasLeft() {
                return (this.leftBuilder_ == null && this.left_ == null) ? false : true;
            }

            @Override
            public boolean hasRight() {
                return (this.rightBuilder_ == null && this.right_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_IncrementalMerkleTree_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_IncrementalMerkleTree_fieldAccessorTable.ensureFieldAccessorsInitialized(IncrementalMerkleTree.class, Builder.class);
            }

            private Builder() {
                this.left_ = null;
                this.right_ = null;
                this.parents_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.left_ = null;
                this.right_ = null;
                this.parents_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (IncrementalMerkleTree.alwaysUseFieldBuilders) {
                    getParentsFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.leftBuilder_ == null) {
                    this.left_ = null;
                } else {
                    this.left_ = null;
                    this.leftBuilder_ = null;
                }
                if (this.rightBuilder_ == null) {
                    this.right_ = null;
                } else {
                    this.right_ = null;
                    this.rightBuilder_ = null;
                }
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.parents_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_IncrementalMerkleTree_descriptor;
            }

            @Override
            public IncrementalMerkleTree getDefaultInstanceForType() {
                return IncrementalMerkleTree.getDefaultInstance();
            }

            @Override
            public IncrementalMerkleTree build() {
                IncrementalMerkleTree buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public IncrementalMerkleTree buildPartial() {
                IncrementalMerkleTree incrementalMerkleTree = new IncrementalMerkleTree(this);
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.leftBuilder_;
                if (singleFieldBuilderV3 == null) {
                    incrementalMerkleTree.left_ = this.left_;
                } else {
                    incrementalMerkleTree.left_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV32 = this.rightBuilder_;
                if (singleFieldBuilderV32 == null) {
                    incrementalMerkleTree.right_ = this.right_;
                } else {
                    incrementalMerkleTree.right_ = singleFieldBuilderV32.build();
                }
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.parents_ = Collections.unmodifiableList(this.parents_);
                        this.bitField0_ &= -5;
                    }
                    incrementalMerkleTree.parents_ = this.parents_;
                } else {
                    incrementalMerkleTree.parents_ = repeatedFieldBuilderV3.build();
                }
                incrementalMerkleTree.bitField0_ = 0;
                onBuilt();
                return incrementalMerkleTree;
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
                if (message instanceof IncrementalMerkleTree) {
                    return mergeFrom((IncrementalMerkleTree) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(IncrementalMerkleTree incrementalMerkleTree) {
                if (incrementalMerkleTree == IncrementalMerkleTree.getDefaultInstance()) {
                    return this;
                }
                if (incrementalMerkleTree.hasLeft()) {
                    mergeLeft(incrementalMerkleTree.getLeft());
                }
                if (incrementalMerkleTree.hasRight()) {
                    mergeRight(incrementalMerkleTree.getRight());
                }
                if (this.parentsBuilder_ == null) {
                    if (!incrementalMerkleTree.parents_.isEmpty()) {
                        if (this.parents_.isEmpty()) {
                            this.parents_ = incrementalMerkleTree.parents_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureParentsIsMutable();
                            this.parents_.addAll(incrementalMerkleTree.parents_);
                        }
                        onChanged();
                    }
                } else if (!incrementalMerkleTree.parents_.isEmpty()) {
                    if (!this.parentsBuilder_.isEmpty()) {
                        this.parentsBuilder_.addAllMessages(incrementalMerkleTree.parents_);
                    } else {
                        this.parentsBuilder_.dispose();
                        this.parentsBuilder_ = null;
                        this.parents_ = incrementalMerkleTree.parents_;
                        this.bitField0_ &= -5;
                        this.parentsBuilder_ = IncrementalMerkleTree.alwaysUseFieldBuilders ? getParentsFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(incrementalMerkleTree.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.IncrementalMerkleTree.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.IncrementalMerkleTree.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$IncrementalMerkleTree$Builder");
            }

            @Override
            public PedersenHash getLeft() {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.leftBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PedersenHash pedersenHash = this.left_;
                    return pedersenHash == null ? PedersenHash.getDefaultInstance() : pedersenHash;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setLeft(PedersenHash pedersenHash) {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.leftBuilder_;
                if (singleFieldBuilderV3 == null) {
                    pedersenHash.getClass();
                    this.left_ = pedersenHash;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pedersenHash);
                }
                return this;
            }

            public Builder setLeft(PedersenHash.Builder builder) {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.leftBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.left_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeLeft(PedersenHash pedersenHash) {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.leftBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PedersenHash pedersenHash2 = this.left_;
                    if (pedersenHash2 != null) {
                        this.left_ = PedersenHash.newBuilder(pedersenHash2).mergeFrom(pedersenHash).buildPartial();
                    } else {
                        this.left_ = pedersenHash;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pedersenHash);
                }
                return this;
            }

            public Builder clearLeft() {
                if (this.leftBuilder_ == null) {
                    this.left_ = null;
                    onChanged();
                } else {
                    this.left_ = null;
                    this.leftBuilder_ = null;
                }
                return this;
            }

            public PedersenHash.Builder getLeftBuilder() {
                onChanged();
                return getLeftFieldBuilder().getBuilder();
            }

            @Override
            public PedersenHashOrBuilder getLeftOrBuilder() {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.leftBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PedersenHash pedersenHash = this.left_;
                return pedersenHash == null ? PedersenHash.getDefaultInstance() : pedersenHash;
            }

            private SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> getLeftFieldBuilder() {
                if (this.leftBuilder_ == null) {
                    this.leftBuilder_ = new SingleFieldBuilderV3<>(getLeft(), getParentForChildren(), isClean());
                    this.left_ = null;
                }
                return this.leftBuilder_;
            }

            @Override
            public PedersenHash getRight() {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.rightBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PedersenHash pedersenHash = this.right_;
                    return pedersenHash == null ? PedersenHash.getDefaultInstance() : pedersenHash;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setRight(PedersenHash pedersenHash) {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.rightBuilder_;
                if (singleFieldBuilderV3 == null) {
                    pedersenHash.getClass();
                    this.right_ = pedersenHash;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pedersenHash);
                }
                return this;
            }

            public Builder setRight(PedersenHash.Builder builder) {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.rightBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.right_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRight(PedersenHash pedersenHash) {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.rightBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PedersenHash pedersenHash2 = this.right_;
                    if (pedersenHash2 != null) {
                        this.right_ = PedersenHash.newBuilder(pedersenHash2).mergeFrom(pedersenHash).buildPartial();
                    } else {
                        this.right_ = pedersenHash;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pedersenHash);
                }
                return this;
            }

            public Builder clearRight() {
                if (this.rightBuilder_ == null) {
                    this.right_ = null;
                    onChanged();
                } else {
                    this.right_ = null;
                    this.rightBuilder_ = null;
                }
                return this;
            }

            public PedersenHash.Builder getRightBuilder() {
                onChanged();
                return getRightFieldBuilder().getBuilder();
            }

            @Override
            public PedersenHashOrBuilder getRightOrBuilder() {
                SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> singleFieldBuilderV3 = this.rightBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PedersenHash pedersenHash = this.right_;
                return pedersenHash == null ? PedersenHash.getDefaultInstance() : pedersenHash;
            }

            private SingleFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> getRightFieldBuilder() {
                if (this.rightBuilder_ == null) {
                    this.rightBuilder_ = new SingleFieldBuilderV3<>(getRight(), getParentForChildren(), isClean());
                    this.right_ = null;
                }
                return this.rightBuilder_;
            }

            private void ensureParentsIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.parents_ = new ArrayList(this.parents_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public List<PedersenHash> getParentsList() {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.parents_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getParentsCount() {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.parents_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public PedersenHash getParents(int i) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.parents_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setParents(int i, PedersenHash pedersenHash) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    pedersenHash.getClass();
                    ensureParentsIsMutable();
                    this.parents_.set(i, pedersenHash);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, pedersenHash);
                }
                return this;
            }

            public Builder setParents(int i, PedersenHash.Builder builder) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureParentsIsMutable();
                    this.parents_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addParents(PedersenHash pedersenHash) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    pedersenHash.getClass();
                    ensureParentsIsMutable();
                    this.parents_.add(pedersenHash);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(pedersenHash);
                }
                return this;
            }

            public Builder addParents(int i, PedersenHash pedersenHash) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    pedersenHash.getClass();
                    ensureParentsIsMutable();
                    this.parents_.add(i, pedersenHash);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, pedersenHash);
                }
                return this;
            }

            public Builder addParents(PedersenHash.Builder builder) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureParentsIsMutable();
                    this.parents_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addParents(int i, PedersenHash.Builder builder) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureParentsIsMutable();
                    this.parents_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllParents(Iterable<? extends PedersenHash> iterable) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureParentsIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.parents_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearParents() {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.parents_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeParents(int i) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureParentsIsMutable();
                    this.parents_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public PedersenHash.Builder getParentsBuilder(int i) {
                return getParentsFieldBuilder().getBuilder(i);
            }

            @Override
            public PedersenHashOrBuilder getParentsOrBuilder(int i) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.parents_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends PedersenHashOrBuilder> getParentsOrBuilderList() {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.parentsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.parents_);
            }

            public PedersenHash.Builder addParentsBuilder() {
                return getParentsFieldBuilder().addBuilder(PedersenHash.getDefaultInstance());
            }

            public PedersenHash.Builder addParentsBuilder(int i) {
                return getParentsFieldBuilder().addBuilder(i, PedersenHash.getDefaultInstance());
            }

            public List<PedersenHash.Builder> getParentsBuilderList() {
                return getParentsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> getParentsFieldBuilder() {
                if (this.parentsBuilder_ == null) {
                    this.parentsBuilder_ = new RepeatedFieldBuilderV3<>(this.parents_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                    this.parents_ = null;
                }
                return this.parentsBuilder_;
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

    public static final class IncrementalMerkleVoucher extends GeneratedMessageV3 implements IncrementalMerkleVoucherOrBuilder {
        public static final int CURSOR_DEPTH_FIELD_NUMBER = 4;
        public static final int CURSOR_FIELD_NUMBER = 3;
        public static final int FILLED_FIELD_NUMBER = 2;
        public static final int OUTPUT_POINT_FIELD_NUMBER = 10;
        public static final int RT_FIELD_NUMBER = 5;
        public static final int TREE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private long cursorDepth_;
        private IncrementalMerkleTree cursor_;
        private List<PedersenHash> filled_;
        private byte memoizedIsInitialized;
        private OutputPoint outputPoint_;
        private ByteString rt_;
        private IncrementalMerkleTree tree_;
        private static final IncrementalMerkleVoucher DEFAULT_INSTANCE = new IncrementalMerkleVoucher();
        private static final Parser<IncrementalMerkleVoucher> PARSER = new AbstractParser<IncrementalMerkleVoucher>() {
            @Override
            public IncrementalMerkleVoucher parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new IncrementalMerkleVoucher(codedInputStream, extensionRegistryLite);
            }
        };

        public static IncrementalMerkleVoucher getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<IncrementalMerkleVoucher> parser() {
            return PARSER;
        }

        @Override
        public long getCursorDepth() {
            return this.cursorDepth_;
        }

        @Override
        public IncrementalMerkleVoucher getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public List<PedersenHash> getFilledList() {
            return this.filled_;
        }

        @Override
        public List<? extends PedersenHashOrBuilder> getFilledOrBuilderList() {
            return this.filled_;
        }

        @Override
        public Parser<IncrementalMerkleVoucher> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getRt() {
            return this.rt_;
        }

        @Override
        public boolean hasCursor() {
            return this.cursor_ != null;
        }

        @Override
        public boolean hasOutputPoint() {
            return this.outputPoint_ != null;
        }

        @Override
        public boolean hasTree() {
            return this.tree_ != null;
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

        private IncrementalMerkleVoucher(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private IncrementalMerkleVoucher() {
            this.memoizedIsInitialized = (byte) -1;
            this.filled_ = Collections.emptyList();
            this.cursorDepth_ = 0L;
            this.rt_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private IncrementalMerkleVoucher(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                IncrementalMerkleTree incrementalMerkleTree = this.tree_;
                                IncrementalMerkleTree.Builder builder = incrementalMerkleTree != null ? incrementalMerkleTree.toBuilder() : null;
                                IncrementalMerkleTree incrementalMerkleTree2 = (IncrementalMerkleTree) codedInputStream.readMessage(IncrementalMerkleTree.parser(), extensionRegistryLite);
                                this.tree_ = incrementalMerkleTree2;
                                if (builder != null) {
                                    builder.mergeFrom(incrementalMerkleTree2);
                                    this.tree_ = builder.buildPartial();
                                }
                            } else if (readTag == 18) {
                                if (!(z2 & true)) {
                                    this.filled_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.filled_.add((PedersenHash) codedInputStream.readMessage(PedersenHash.parser(), extensionRegistryLite));
                            } else if (readTag == 26) {
                                IncrementalMerkleTree incrementalMerkleTree3 = this.cursor_;
                                IncrementalMerkleTree.Builder builder2 = incrementalMerkleTree3 != null ? incrementalMerkleTree3.toBuilder() : null;
                                IncrementalMerkleTree incrementalMerkleTree4 = (IncrementalMerkleTree) codedInputStream.readMessage(IncrementalMerkleTree.parser(), extensionRegistryLite);
                                this.cursor_ = incrementalMerkleTree4;
                                if (builder2 != null) {
                                    builder2.mergeFrom(incrementalMerkleTree4);
                                    this.cursor_ = builder2.buildPartial();
                                }
                            } else if (readTag == 32) {
                                this.cursorDepth_ = codedInputStream.readInt64();
                            } else if (readTag == 42) {
                                this.rt_ = codedInputStream.readBytes();
                            } else if (readTag != 82) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                OutputPoint outputPoint = this.outputPoint_;
                                OutputPoint.Builder builder3 = outputPoint != null ? outputPoint.toBuilder() : null;
                                OutputPoint outputPoint2 = (OutputPoint) codedInputStream.readMessage(OutputPoint.parser(), extensionRegistryLite);
                                this.outputPoint_ = outputPoint2;
                                if (builder3 != null) {
                                    builder3.mergeFrom(outputPoint2);
                                    this.outputPoint_ = builder3.buildPartial();
                                }
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
                        this.filled_ = Collections.unmodifiableList(this.filled_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShieldContract.internal_static_protocol_IncrementalMerkleVoucher_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_IncrementalMerkleVoucher_fieldAccessorTable.ensureFieldAccessorsInitialized(IncrementalMerkleVoucher.class, Builder.class);
        }

        @Override
        public IncrementalMerkleTree getTree() {
            IncrementalMerkleTree incrementalMerkleTree = this.tree_;
            return incrementalMerkleTree == null ? IncrementalMerkleTree.getDefaultInstance() : incrementalMerkleTree;
        }

        @Override
        public IncrementalMerkleTreeOrBuilder getTreeOrBuilder() {
            return getTree();
        }

        @Override
        public int getFilledCount() {
            return this.filled_.size();
        }

        @Override
        public PedersenHash getFilled(int i) {
            return this.filled_.get(i);
        }

        @Override
        public PedersenHashOrBuilder getFilledOrBuilder(int i) {
            return this.filled_.get(i);
        }

        @Override
        public IncrementalMerkleTree getCursor() {
            IncrementalMerkleTree incrementalMerkleTree = this.cursor_;
            return incrementalMerkleTree == null ? IncrementalMerkleTree.getDefaultInstance() : incrementalMerkleTree;
        }

        @Override
        public IncrementalMerkleTreeOrBuilder getCursorOrBuilder() {
            return getCursor();
        }

        @Override
        public OutputPoint getOutputPoint() {
            OutputPoint outputPoint = this.outputPoint_;
            return outputPoint == null ? OutputPoint.getDefaultInstance() : outputPoint;
        }

        @Override
        public OutputPointOrBuilder getOutputPointOrBuilder() {
            return getOutputPoint();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.tree_ != null) {
                codedOutputStream.writeMessage(1, getTree());
            }
            for (int i = 0; i < this.filled_.size(); i++) {
                codedOutputStream.writeMessage(2, this.filled_.get(i));
            }
            if (this.cursor_ != null) {
                codedOutputStream.writeMessage(3, getCursor());
            }
            long j = this.cursorDepth_;
            if (j != 0) {
                codedOutputStream.writeInt64(4, j);
            }
            if (!this.rt_.isEmpty()) {
                codedOutputStream.writeBytes(5, this.rt_);
            }
            if (this.outputPoint_ != null) {
                codedOutputStream.writeMessage(10, getOutputPoint());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.tree_ != null ? CodedOutputStream.computeMessageSize(1, getTree()) : 0;
            for (int i2 = 0; i2 < this.filled_.size(); i2++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, this.filled_.get(i2));
            }
            if (this.cursor_ != null) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, getCursor());
            }
            long j = this.cursorDepth_;
            if (j != 0) {
                computeMessageSize += CodedOutputStream.computeInt64Size(4, j);
            }
            if (!this.rt_.isEmpty()) {
                computeMessageSize += CodedOutputStream.computeBytesSize(5, this.rt_);
            }
            if (this.outputPoint_ != null) {
                computeMessageSize += CodedOutputStream.computeMessageSize(10, getOutputPoint());
            }
            int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(java.lang.Object r8) {
            


return true;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.IncrementalMerkleVoucher.equals(java.lang.Object):boolean");
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasTree()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getTree().hashCode();
            }
            if (getFilledCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getFilledList().hashCode();
            }
            if (hasCursor()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getCursor().hashCode();
            }
            int hashLong = (((((((hashCode * 37) + 4) * 53) + Internal.hashLong(getCursorDepth())) * 37) + 5) * 53) + getRt().hashCode();
            if (hasOutputPoint()) {
                hashLong = (((hashLong * 37) + 10) * 53) + getOutputPoint().hashCode();
            }
            int hashCode2 = (hashLong * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static IncrementalMerkleVoucher parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static IncrementalMerkleVoucher parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucher parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static IncrementalMerkleVoucher parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucher parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static IncrementalMerkleVoucher parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucher parseFrom(InputStream inputStream) throws IOException {
            return (IncrementalMerkleVoucher) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static IncrementalMerkleVoucher parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncrementalMerkleVoucher) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucher parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (IncrementalMerkleVoucher) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static IncrementalMerkleVoucher parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncrementalMerkleVoucher) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucher parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (IncrementalMerkleVoucher) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static IncrementalMerkleVoucher parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncrementalMerkleVoucher) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(IncrementalMerkleVoucher incrementalMerkleVoucher) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(incrementalMerkleVoucher);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements IncrementalMerkleVoucherOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> cursorBuilder_;
            private long cursorDepth_;
            private IncrementalMerkleTree cursor_;
            private RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> filledBuilder_;
            private List<PedersenHash> filled_;
            private SingleFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> outputPointBuilder_;
            private OutputPoint outputPoint_;
            private ByteString rt_;
            private SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> treeBuilder_;
            private IncrementalMerkleTree tree_;

            @Override
            public long getCursorDepth() {
                return this.cursorDepth_;
            }

            @Override
            public ByteString getRt() {
                return this.rt_;
            }

            @Override
            public boolean hasCursor() {
                return (this.cursorBuilder_ == null && this.cursor_ == null) ? false : true;
            }

            @Override
            public boolean hasOutputPoint() {
                return (this.outputPointBuilder_ == null && this.outputPoint_ == null) ? false : true;
            }

            @Override
            public boolean hasTree() {
                return (this.treeBuilder_ == null && this.tree_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_IncrementalMerkleVoucher_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_IncrementalMerkleVoucher_fieldAccessorTable.ensureFieldAccessorsInitialized(IncrementalMerkleVoucher.class, Builder.class);
            }

            private Builder() {
                this.tree_ = null;
                this.filled_ = Collections.emptyList();
                this.cursor_ = null;
                this.rt_ = ByteString.EMPTY;
                this.outputPoint_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.tree_ = null;
                this.filled_ = Collections.emptyList();
                this.cursor_ = null;
                this.rt_ = ByteString.EMPTY;
                this.outputPoint_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (IncrementalMerkleVoucher.alwaysUseFieldBuilders) {
                    getFilledFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.treeBuilder_ == null) {
                    this.tree_ = null;
                } else {
                    this.tree_ = null;
                    this.treeBuilder_ = null;
                }
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.filled_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                if (this.cursorBuilder_ == null) {
                    this.cursor_ = null;
                } else {
                    this.cursor_ = null;
                    this.cursorBuilder_ = null;
                }
                this.cursorDepth_ = 0L;
                this.rt_ = ByteString.EMPTY;
                if (this.outputPointBuilder_ == null) {
                    this.outputPoint_ = null;
                } else {
                    this.outputPoint_ = null;
                    this.outputPointBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_IncrementalMerkleVoucher_descriptor;
            }

            @Override
            public IncrementalMerkleVoucher getDefaultInstanceForType() {
                return IncrementalMerkleVoucher.getDefaultInstance();
            }

            @Override
            public IncrementalMerkleVoucher build() {
                IncrementalMerkleVoucher buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public IncrementalMerkleVoucher buildPartial() {
                IncrementalMerkleVoucher incrementalMerkleVoucher = new IncrementalMerkleVoucher(this);
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.treeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    incrementalMerkleVoucher.tree_ = this.tree_;
                } else {
                    incrementalMerkleVoucher.tree_ = singleFieldBuilderV3.build();
                }
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.filled_ = Collections.unmodifiableList(this.filled_);
                        this.bitField0_ &= -3;
                    }
                    incrementalMerkleVoucher.filled_ = this.filled_;
                } else {
                    incrementalMerkleVoucher.filled_ = repeatedFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV32 = this.cursorBuilder_;
                if (singleFieldBuilderV32 == null) {
                    incrementalMerkleVoucher.cursor_ = this.cursor_;
                } else {
                    incrementalMerkleVoucher.cursor_ = singleFieldBuilderV32.build();
                }
                incrementalMerkleVoucher.cursorDepth_ = this.cursorDepth_;
                incrementalMerkleVoucher.rt_ = this.rt_;
                SingleFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> singleFieldBuilderV33 = this.outputPointBuilder_;
                if (singleFieldBuilderV33 == null) {
                    incrementalMerkleVoucher.outputPoint_ = this.outputPoint_;
                } else {
                    incrementalMerkleVoucher.outputPoint_ = singleFieldBuilderV33.build();
                }
                incrementalMerkleVoucher.bitField0_ = 0;
                onBuilt();
                return incrementalMerkleVoucher;
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
                if (message instanceof IncrementalMerkleVoucher) {
                    return mergeFrom((IncrementalMerkleVoucher) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(IncrementalMerkleVoucher incrementalMerkleVoucher) {
                if (incrementalMerkleVoucher == IncrementalMerkleVoucher.getDefaultInstance()) {
                    return this;
                }
                if (incrementalMerkleVoucher.hasTree()) {
                    mergeTree(incrementalMerkleVoucher.getTree());
                }
                if (this.filledBuilder_ == null) {
                    if (!incrementalMerkleVoucher.filled_.isEmpty()) {
                        if (this.filled_.isEmpty()) {
                            this.filled_ = incrementalMerkleVoucher.filled_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureFilledIsMutable();
                            this.filled_.addAll(incrementalMerkleVoucher.filled_);
                        }
                        onChanged();
                    }
                } else if (!incrementalMerkleVoucher.filled_.isEmpty()) {
                    if (!this.filledBuilder_.isEmpty()) {
                        this.filledBuilder_.addAllMessages(incrementalMerkleVoucher.filled_);
                    } else {
                        this.filledBuilder_.dispose();
                        this.filledBuilder_ = null;
                        this.filled_ = incrementalMerkleVoucher.filled_;
                        this.bitField0_ &= -3;
                        this.filledBuilder_ = IncrementalMerkleVoucher.alwaysUseFieldBuilders ? getFilledFieldBuilder() : null;
                    }
                }
                if (incrementalMerkleVoucher.hasCursor()) {
                    mergeCursor(incrementalMerkleVoucher.getCursor());
                }
                if (incrementalMerkleVoucher.getCursorDepth() != 0) {
                    setCursorDepth(incrementalMerkleVoucher.getCursorDepth());
                }
                if (incrementalMerkleVoucher.getRt() != ByteString.EMPTY) {
                    setRt(incrementalMerkleVoucher.getRt());
                }
                if (incrementalMerkleVoucher.hasOutputPoint()) {
                    mergeOutputPoint(incrementalMerkleVoucher.getOutputPoint());
                }
                mergeUnknownFields(incrementalMerkleVoucher.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.IncrementalMerkleVoucher.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.IncrementalMerkleVoucher.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$IncrementalMerkleVoucher$Builder");
            }

            @Override
            public IncrementalMerkleTree getTree() {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.treeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    IncrementalMerkleTree incrementalMerkleTree = this.tree_;
                    return incrementalMerkleTree == null ? IncrementalMerkleTree.getDefaultInstance() : incrementalMerkleTree;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setTree(IncrementalMerkleTree incrementalMerkleTree) {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.treeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    incrementalMerkleTree.getClass();
                    this.tree_ = incrementalMerkleTree;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(incrementalMerkleTree);
                }
                return this;
            }

            public Builder setTree(IncrementalMerkleTree.Builder builder) {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.treeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.tree_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTree(IncrementalMerkleTree incrementalMerkleTree) {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.treeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    IncrementalMerkleTree incrementalMerkleTree2 = this.tree_;
                    if (incrementalMerkleTree2 != null) {
                        this.tree_ = IncrementalMerkleTree.newBuilder(incrementalMerkleTree2).mergeFrom(incrementalMerkleTree).buildPartial();
                    } else {
                        this.tree_ = incrementalMerkleTree;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(incrementalMerkleTree);
                }
                return this;
            }

            public Builder clearTree() {
                if (this.treeBuilder_ == null) {
                    this.tree_ = null;
                    onChanged();
                } else {
                    this.tree_ = null;
                    this.treeBuilder_ = null;
                }
                return this;
            }

            public IncrementalMerkleTree.Builder getTreeBuilder() {
                onChanged();
                return getTreeFieldBuilder().getBuilder();
            }

            @Override
            public IncrementalMerkleTreeOrBuilder getTreeOrBuilder() {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.treeBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                IncrementalMerkleTree incrementalMerkleTree = this.tree_;
                return incrementalMerkleTree == null ? IncrementalMerkleTree.getDefaultInstance() : incrementalMerkleTree;
            }

            private SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> getTreeFieldBuilder() {
                if (this.treeBuilder_ == null) {
                    this.treeBuilder_ = new SingleFieldBuilderV3<>(getTree(), getParentForChildren(), isClean());
                    this.tree_ = null;
                }
                return this.treeBuilder_;
            }

            private void ensureFilledIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.filled_ = new ArrayList(this.filled_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<PedersenHash> getFilledList() {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.filled_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getFilledCount() {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.filled_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public PedersenHash getFilled(int i) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.filled_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setFilled(int i, PedersenHash pedersenHash) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    pedersenHash.getClass();
                    ensureFilledIsMutable();
                    this.filled_.set(i, pedersenHash);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, pedersenHash);
                }
                return this;
            }

            public Builder setFilled(int i, PedersenHash.Builder builder) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFilledIsMutable();
                    this.filled_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addFilled(PedersenHash pedersenHash) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    pedersenHash.getClass();
                    ensureFilledIsMutable();
                    this.filled_.add(pedersenHash);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(pedersenHash);
                }
                return this;
            }

            public Builder addFilled(int i, PedersenHash pedersenHash) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    pedersenHash.getClass();
                    ensureFilledIsMutable();
                    this.filled_.add(i, pedersenHash);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, pedersenHash);
                }
                return this;
            }

            public Builder addFilled(PedersenHash.Builder builder) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFilledIsMutable();
                    this.filled_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addFilled(int i, PedersenHash.Builder builder) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFilledIsMutable();
                    this.filled_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllFilled(Iterable<? extends PedersenHash> iterable) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFilledIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.filled_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearFilled() {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.filled_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeFilled(int i) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFilledIsMutable();
                    this.filled_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public PedersenHash.Builder getFilledBuilder(int i) {
                return getFilledFieldBuilder().getBuilder(i);
            }

            @Override
            public PedersenHashOrBuilder getFilledOrBuilder(int i) {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.filled_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends PedersenHashOrBuilder> getFilledOrBuilderList() {
                RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> repeatedFieldBuilderV3 = this.filledBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.filled_);
            }

            public PedersenHash.Builder addFilledBuilder() {
                return getFilledFieldBuilder().addBuilder(PedersenHash.getDefaultInstance());
            }

            public PedersenHash.Builder addFilledBuilder(int i) {
                return getFilledFieldBuilder().addBuilder(i, PedersenHash.getDefaultInstance());
            }

            public List<PedersenHash.Builder> getFilledBuilderList() {
                return getFilledFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<PedersenHash, PedersenHash.Builder, PedersenHashOrBuilder> getFilledFieldBuilder() {
                if (this.filledBuilder_ == null) {
                    this.filledBuilder_ = new RepeatedFieldBuilderV3<>(this.filled_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.filled_ = null;
                }
                return this.filledBuilder_;
            }

            @Override
            public IncrementalMerkleTree getCursor() {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.cursorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    IncrementalMerkleTree incrementalMerkleTree = this.cursor_;
                    return incrementalMerkleTree == null ? IncrementalMerkleTree.getDefaultInstance() : incrementalMerkleTree;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCursor(IncrementalMerkleTree incrementalMerkleTree) {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.cursorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    incrementalMerkleTree.getClass();
                    this.cursor_ = incrementalMerkleTree;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(incrementalMerkleTree);
                }
                return this;
            }

            public Builder setCursor(IncrementalMerkleTree.Builder builder) {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.cursorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.cursor_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCursor(IncrementalMerkleTree incrementalMerkleTree) {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.cursorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    IncrementalMerkleTree incrementalMerkleTree2 = this.cursor_;
                    if (incrementalMerkleTree2 != null) {
                        this.cursor_ = IncrementalMerkleTree.newBuilder(incrementalMerkleTree2).mergeFrom(incrementalMerkleTree).buildPartial();
                    } else {
                        this.cursor_ = incrementalMerkleTree;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(incrementalMerkleTree);
                }
                return this;
            }

            public Builder clearCursor() {
                if (this.cursorBuilder_ == null) {
                    this.cursor_ = null;
                    onChanged();
                } else {
                    this.cursor_ = null;
                    this.cursorBuilder_ = null;
                }
                return this;
            }

            public IncrementalMerkleTree.Builder getCursorBuilder() {
                onChanged();
                return getCursorFieldBuilder().getBuilder();
            }

            @Override
            public IncrementalMerkleTreeOrBuilder getCursorOrBuilder() {
                SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> singleFieldBuilderV3 = this.cursorBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                IncrementalMerkleTree incrementalMerkleTree = this.cursor_;
                return incrementalMerkleTree == null ? IncrementalMerkleTree.getDefaultInstance() : incrementalMerkleTree;
            }

            private SingleFieldBuilderV3<IncrementalMerkleTree, IncrementalMerkleTree.Builder, IncrementalMerkleTreeOrBuilder> getCursorFieldBuilder() {
                if (this.cursorBuilder_ == null) {
                    this.cursorBuilder_ = new SingleFieldBuilderV3<>(getCursor(), getParentForChildren(), isClean());
                    this.cursor_ = null;
                }
                return this.cursorBuilder_;
            }

            public Builder setCursorDepth(long j) {
                this.cursorDepth_ = j;
                onChanged();
                return this;
            }

            public Builder clearCursorDepth() {
                this.cursorDepth_ = 0L;
                onChanged();
                return this;
            }

            public Builder setRt(ByteString byteString) {
                byteString.getClass();
                this.rt_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearRt() {
                this.rt_ = IncrementalMerkleVoucher.getDefaultInstance().getRt();
                onChanged();
                return this;
            }

            @Override
            public OutputPoint getOutputPoint() {
                SingleFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> singleFieldBuilderV3 = this.outputPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    OutputPoint outputPoint = this.outputPoint_;
                    return outputPoint == null ? OutputPoint.getDefaultInstance() : outputPoint;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setOutputPoint(OutputPoint outputPoint) {
                SingleFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> singleFieldBuilderV3 = this.outputPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    outputPoint.getClass();
                    this.outputPoint_ = outputPoint;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(outputPoint);
                }
                return this;
            }

            public Builder setOutputPoint(OutputPoint.Builder builder) {
                SingleFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> singleFieldBuilderV3 = this.outputPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.outputPoint_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeOutputPoint(OutputPoint outputPoint) {
                SingleFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> singleFieldBuilderV3 = this.outputPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    OutputPoint outputPoint2 = this.outputPoint_;
                    if (outputPoint2 != null) {
                        this.outputPoint_ = OutputPoint.newBuilder(outputPoint2).mergeFrom(outputPoint).buildPartial();
                    } else {
                        this.outputPoint_ = outputPoint;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(outputPoint);
                }
                return this;
            }

            public Builder clearOutputPoint() {
                if (this.outputPointBuilder_ == null) {
                    this.outputPoint_ = null;
                    onChanged();
                } else {
                    this.outputPoint_ = null;
                    this.outputPointBuilder_ = null;
                }
                return this;
            }

            public OutputPoint.Builder getOutputPointBuilder() {
                onChanged();
                return getOutputPointFieldBuilder().getBuilder();
            }

            @Override
            public OutputPointOrBuilder getOutputPointOrBuilder() {
                SingleFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> singleFieldBuilderV3 = this.outputPointBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                OutputPoint outputPoint = this.outputPoint_;
                return outputPoint == null ? OutputPoint.getDefaultInstance() : outputPoint;
            }

            private SingleFieldBuilderV3<OutputPoint, OutputPoint.Builder, OutputPointOrBuilder> getOutputPointFieldBuilder() {
                if (this.outputPointBuilder_ == null) {
                    this.outputPointBuilder_ = new SingleFieldBuilderV3<>(getOutputPoint(), getParentForChildren(), isClean());
                    this.outputPoint_ = null;
                }
                return this.outputPointBuilder_;
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

    public static final class IncrementalMerkleVoucherInfo extends GeneratedMessageV3 implements IncrementalMerkleVoucherInfoOrBuilder {
        private static final IncrementalMerkleVoucherInfo DEFAULT_INSTANCE = new IncrementalMerkleVoucherInfo();
        private static final Parser<IncrementalMerkleVoucherInfo> PARSER = new AbstractParser<IncrementalMerkleVoucherInfo>() {
            @Override
            public IncrementalMerkleVoucherInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new IncrementalMerkleVoucherInfo(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PATHS_FIELD_NUMBER = 2;
        public static final int VOUCHERS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private List<ByteString> paths_;
        private List<IncrementalMerkleVoucher> vouchers_;

        public static IncrementalMerkleVoucherInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<IncrementalMerkleVoucherInfo> parser() {
            return PARSER;
        }

        @Override
        public IncrementalMerkleVoucherInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<IncrementalMerkleVoucherInfo> getParserForType() {
            return PARSER;
        }

        @Override
        public List<ByteString> getPathsList() {
            return this.paths_;
        }

        @Override
        public List<IncrementalMerkleVoucher> getVouchersList() {
            return this.vouchers_;
        }

        @Override
        public List<? extends IncrementalMerkleVoucherOrBuilder> getVouchersOrBuilderList() {
            return this.vouchers_;
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

        private IncrementalMerkleVoucherInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private IncrementalMerkleVoucherInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.vouchers_ = Collections.emptyList();
            this.paths_ = Collections.emptyList();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private IncrementalMerkleVoucherInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (!(z2 & true)) {
                                    this.vouchers_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.vouchers_.add((IncrementalMerkleVoucher) codedInputStream.readMessage(IncrementalMerkleVoucher.parser(), extensionRegistryLite));
                            } else if (readTag != 18) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.paths_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.paths_.add(codedInputStream.readBytes());
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
                        this.vouchers_ = Collections.unmodifiableList(this.vouchers_);
                    }
                    if (z2 & true) {
                        this.paths_ = Collections.unmodifiableList(this.paths_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShieldContract.internal_static_protocol_IncrementalMerkleVoucherInfo_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_IncrementalMerkleVoucherInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(IncrementalMerkleVoucherInfo.class, Builder.class);
        }

        @Override
        public int getVouchersCount() {
            return this.vouchers_.size();
        }

        @Override
        public IncrementalMerkleVoucher getVouchers(int i) {
            return this.vouchers_.get(i);
        }

        @Override
        public IncrementalMerkleVoucherOrBuilder getVouchersOrBuilder(int i) {
            return this.vouchers_.get(i);
        }

        @Override
        public int getPathsCount() {
            return this.paths_.size();
        }

        @Override
        public ByteString getPaths(int i) {
            return this.paths_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.vouchers_.size(); i++) {
                codedOutputStream.writeMessage(1, this.vouchers_.get(i));
            }
            for (int i2 = 0; i2 < this.paths_.size(); i2++) {
                codedOutputStream.writeBytes(2, this.paths_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.vouchers_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.vouchers_.get(i3));
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.paths_.size(); i5++) {
                i4 += CodedOutputStream.computeBytesSizeNoTag(this.paths_.get(i5));
            }
            int size = i2 + i4 + getPathsList().size() + this.unknownFields.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof IncrementalMerkleVoucherInfo)) {
                return super.equals(obj);
            }
            IncrementalMerkleVoucherInfo incrementalMerkleVoucherInfo = (IncrementalMerkleVoucherInfo) obj;
            return getVouchersList().equals(incrementalMerkleVoucherInfo.getVouchersList()) && getPathsList().equals(incrementalMerkleVoucherInfo.getPathsList()) && this.unknownFields.equals(incrementalMerkleVoucherInfo.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getVouchersCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getVouchersList().hashCode();
            }
            if (getPathsCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getPathsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static IncrementalMerkleVoucherInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static IncrementalMerkleVoucherInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucherInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static IncrementalMerkleVoucherInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucherInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static IncrementalMerkleVoucherInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucherInfo parseFrom(InputStream inputStream) throws IOException {
            return (IncrementalMerkleVoucherInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static IncrementalMerkleVoucherInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncrementalMerkleVoucherInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucherInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (IncrementalMerkleVoucherInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static IncrementalMerkleVoucherInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncrementalMerkleVoucherInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static IncrementalMerkleVoucherInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (IncrementalMerkleVoucherInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static IncrementalMerkleVoucherInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IncrementalMerkleVoucherInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(IncrementalMerkleVoucherInfo incrementalMerkleVoucherInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(incrementalMerkleVoucherInfo);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements IncrementalMerkleVoucherInfoOrBuilder {
            private int bitField0_;
            private List<ByteString> paths_;
            private RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> vouchersBuilder_;
            private List<IncrementalMerkleVoucher> vouchers_;

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_IncrementalMerkleVoucherInfo_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_IncrementalMerkleVoucherInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(IncrementalMerkleVoucherInfo.class, Builder.class);
            }

            private Builder() {
                this.vouchers_ = Collections.emptyList();
                this.paths_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.vouchers_ = Collections.emptyList();
                this.paths_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (IncrementalMerkleVoucherInfo.alwaysUseFieldBuilders) {
                    getVouchersFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.vouchers_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.paths_ = Collections.emptyList();
                this.bitField0_ &= -3;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_IncrementalMerkleVoucherInfo_descriptor;
            }

            @Override
            public IncrementalMerkleVoucherInfo getDefaultInstanceForType() {
                return IncrementalMerkleVoucherInfo.getDefaultInstance();
            }

            @Override
            public IncrementalMerkleVoucherInfo build() {
                IncrementalMerkleVoucherInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public IncrementalMerkleVoucherInfo buildPartial() {
                IncrementalMerkleVoucherInfo incrementalMerkleVoucherInfo = new IncrementalMerkleVoucherInfo(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) == 1) {
                        this.vouchers_ = Collections.unmodifiableList(this.vouchers_);
                        this.bitField0_ &= -2;
                    }
                    incrementalMerkleVoucherInfo.vouchers_ = this.vouchers_;
                } else {
                    incrementalMerkleVoucherInfo.vouchers_ = repeatedFieldBuilderV3.build();
                }
                if ((this.bitField0_ & 2) == 2) {
                    this.paths_ = Collections.unmodifiableList(this.paths_);
                    this.bitField0_ &= -3;
                }
                incrementalMerkleVoucherInfo.paths_ = this.paths_;
                onBuilt();
                return incrementalMerkleVoucherInfo;
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
                if (message instanceof IncrementalMerkleVoucherInfo) {
                    return mergeFrom((IncrementalMerkleVoucherInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(IncrementalMerkleVoucherInfo incrementalMerkleVoucherInfo) {
                if (incrementalMerkleVoucherInfo == IncrementalMerkleVoucherInfo.getDefaultInstance()) {
                    return this;
                }
                if (this.vouchersBuilder_ == null) {
                    if (!incrementalMerkleVoucherInfo.vouchers_.isEmpty()) {
                        if (this.vouchers_.isEmpty()) {
                            this.vouchers_ = incrementalMerkleVoucherInfo.vouchers_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureVouchersIsMutable();
                            this.vouchers_.addAll(incrementalMerkleVoucherInfo.vouchers_);
                        }
                        onChanged();
                    }
                } else if (!incrementalMerkleVoucherInfo.vouchers_.isEmpty()) {
                    if (!this.vouchersBuilder_.isEmpty()) {
                        this.vouchersBuilder_.addAllMessages(incrementalMerkleVoucherInfo.vouchers_);
                    } else {
                        this.vouchersBuilder_.dispose();
                        this.vouchersBuilder_ = null;
                        this.vouchers_ = incrementalMerkleVoucherInfo.vouchers_;
                        this.bitField0_ &= -2;
                        this.vouchersBuilder_ = IncrementalMerkleVoucherInfo.alwaysUseFieldBuilders ? getVouchersFieldBuilder() : null;
                    }
                }
                if (!incrementalMerkleVoucherInfo.paths_.isEmpty()) {
                    if (this.paths_.isEmpty()) {
                        this.paths_ = incrementalMerkleVoucherInfo.paths_;
                        this.bitField0_ &= -3;
                    } else {
                        ensurePathsIsMutable();
                        this.paths_.addAll(incrementalMerkleVoucherInfo.paths_);
                    }
                    onChanged();
                }
                mergeUnknownFields(incrementalMerkleVoucherInfo.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.IncrementalMerkleVoucherInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.IncrementalMerkleVoucherInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$IncrementalMerkleVoucherInfo$Builder");
            }

            private void ensureVouchersIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.vouchers_ = new ArrayList(this.vouchers_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<IncrementalMerkleVoucher> getVouchersList() {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.vouchers_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getVouchersCount() {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.vouchers_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public IncrementalMerkleVoucher getVouchers(int i) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.vouchers_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setVouchers(int i, IncrementalMerkleVoucher incrementalMerkleVoucher) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    incrementalMerkleVoucher.getClass();
                    ensureVouchersIsMutable();
                    this.vouchers_.set(i, incrementalMerkleVoucher);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, incrementalMerkleVoucher);
                }
                return this;
            }

            public Builder setVouchers(int i, IncrementalMerkleVoucher.Builder builder) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVouchersIsMutable();
                    this.vouchers_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addVouchers(IncrementalMerkleVoucher incrementalMerkleVoucher) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    incrementalMerkleVoucher.getClass();
                    ensureVouchersIsMutable();
                    this.vouchers_.add(incrementalMerkleVoucher);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(incrementalMerkleVoucher);
                }
                return this;
            }

            public Builder addVouchers(int i, IncrementalMerkleVoucher incrementalMerkleVoucher) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    incrementalMerkleVoucher.getClass();
                    ensureVouchersIsMutable();
                    this.vouchers_.add(i, incrementalMerkleVoucher);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, incrementalMerkleVoucher);
                }
                return this;
            }

            public Builder addVouchers(IncrementalMerkleVoucher.Builder builder) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVouchersIsMutable();
                    this.vouchers_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addVouchers(int i, IncrementalMerkleVoucher.Builder builder) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVouchersIsMutable();
                    this.vouchers_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllVouchers(Iterable<? extends IncrementalMerkleVoucher> iterable) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVouchersIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.vouchers_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearVouchers() {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.vouchers_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeVouchers(int i) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVouchersIsMutable();
                    this.vouchers_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public IncrementalMerkleVoucher.Builder getVouchersBuilder(int i) {
                return getVouchersFieldBuilder().getBuilder(i);
            }

            @Override
            public IncrementalMerkleVoucherOrBuilder getVouchersOrBuilder(int i) {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.vouchers_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends IncrementalMerkleVoucherOrBuilder> getVouchersOrBuilderList() {
                RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> repeatedFieldBuilderV3 = this.vouchersBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.vouchers_);
            }

            public IncrementalMerkleVoucher.Builder addVouchersBuilder() {
                return getVouchersFieldBuilder().addBuilder(IncrementalMerkleVoucher.getDefaultInstance());
            }

            public IncrementalMerkleVoucher.Builder addVouchersBuilder(int i) {
                return getVouchersFieldBuilder().addBuilder(i, IncrementalMerkleVoucher.getDefaultInstance());
            }

            public List<IncrementalMerkleVoucher.Builder> getVouchersBuilderList() {
                return getVouchersFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<IncrementalMerkleVoucher, IncrementalMerkleVoucher.Builder, IncrementalMerkleVoucherOrBuilder> getVouchersFieldBuilder() {
                if (this.vouchersBuilder_ == null) {
                    this.vouchersBuilder_ = new RepeatedFieldBuilderV3<>(this.vouchers_, (this.bitField0_ & 1) == 1, getParentForChildren(), isClean());
                    this.vouchers_ = null;
                }
                return this.vouchersBuilder_;
            }

            private void ensurePathsIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.paths_ = new ArrayList(this.paths_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<ByteString> getPathsList() {
                return Collections.unmodifiableList(this.paths_);
            }

            @Override
            public int getPathsCount() {
                return this.paths_.size();
            }

            @Override
            public ByteString getPaths(int i) {
                return this.paths_.get(i);
            }

            public Builder setPaths(int i, ByteString byteString) {
                byteString.getClass();
                ensurePathsIsMutable();
                this.paths_.set(i, byteString);
                onChanged();
                return this;
            }

            public Builder addPaths(ByteString byteString) {
                byteString.getClass();
                ensurePathsIsMutable();
                this.paths_.add(byteString);
                onChanged();
                return this;
            }

            public Builder addAllPaths(Iterable<? extends ByteString> iterable) {
                ensurePathsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.paths_);
                onChanged();
                return this;
            }

            public Builder clearPaths() {
                this.paths_ = Collections.emptyList();
                this.bitField0_ &= -3;
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

    public static final class SpendDescription extends GeneratedMessageV3 implements SpendDescriptionOrBuilder {
        public static final int ANCHOR_FIELD_NUMBER = 2;
        public static final int NULLIFIER_FIELD_NUMBER = 3;
        public static final int RK_FIELD_NUMBER = 4;
        public static final int SPEND_AUTHORITY_SIGNATURE_FIELD_NUMBER = 6;
        public static final int VALUE_COMMITMENT_FIELD_NUMBER = 1;
        public static final int ZKPROOF_FIELD_NUMBER = 5;
        private static final long serialVersionUID = 0;
        private ByteString anchor_;
        private byte memoizedIsInitialized;
        private ByteString nullifier_;
        private ByteString rk_;
        private ByteString spendAuthoritySignature_;
        private ByteString valueCommitment_;
        private ByteString zkproof_;
        private static final SpendDescription DEFAULT_INSTANCE = new SpendDescription();
        private static final Parser<SpendDescription> PARSER = new AbstractParser<SpendDescription>() {
            @Override
            public SpendDescription parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new SpendDescription(codedInputStream, extensionRegistryLite);
            }
        };

        public static SpendDescription getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SpendDescription> parser() {
            return PARSER;
        }

        @Override
        public ByteString getAnchor() {
            return this.anchor_;
        }

        @Override
        public SpendDescription getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getNullifier() {
            return this.nullifier_;
        }

        @Override
        public Parser<SpendDescription> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getRk() {
            return this.rk_;
        }

        @Override
        public ByteString getSpendAuthoritySignature() {
            return this.spendAuthoritySignature_;
        }

        @Override
        public ByteString getValueCommitment() {
            return this.valueCommitment_;
        }

        @Override
        public ByteString getZkproof() {
            return this.zkproof_;
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

        private SpendDescription(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private SpendDescription() {
            this.memoizedIsInitialized = (byte) -1;
            this.valueCommitment_ = ByteString.EMPTY;
            this.anchor_ = ByteString.EMPTY;
            this.nullifier_ = ByteString.EMPTY;
            this.rk_ = ByteString.EMPTY;
            this.zkproof_ = ByteString.EMPTY;
            this.spendAuthoritySignature_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private SpendDescription(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.valueCommitment_ = codedInputStream.readBytes();
                            } else if (readTag == 18) {
                                this.anchor_ = codedInputStream.readBytes();
                            } else if (readTag == 26) {
                                this.nullifier_ = codedInputStream.readBytes();
                            } else if (readTag == 34) {
                                this.rk_ = codedInputStream.readBytes();
                            } else if (readTag == 42) {
                                this.zkproof_ = codedInputStream.readBytes();
                            } else if (readTag != 50) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.spendAuthoritySignature_ = codedInputStream.readBytes();
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
            return ShieldContract.internal_static_protocol_SpendDescription_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_SpendDescription_fieldAccessorTable.ensureFieldAccessorsInitialized(SpendDescription.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.valueCommitment_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.valueCommitment_);
            }
            if (!this.anchor_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.anchor_);
            }
            if (!this.nullifier_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.nullifier_);
            }
            if (!this.rk_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.rk_);
            }
            if (!this.zkproof_.isEmpty()) {
                codedOutputStream.writeBytes(5, this.zkproof_);
            }
            if (!this.spendAuthoritySignature_.isEmpty()) {
                codedOutputStream.writeBytes(6, this.spendAuthoritySignature_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.valueCommitment_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.valueCommitment_) : 0;
            if (!this.anchor_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.anchor_);
            }
            if (!this.nullifier_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.nullifier_);
            }
            if (!this.rk_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(4, this.rk_);
            }
            if (!this.zkproof_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(5, this.zkproof_);
            }
            if (!this.spendAuthoritySignature_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(6, this.spendAuthoritySignature_);
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
            if (!(obj instanceof SpendDescription)) {
                return super.equals(obj);
            }
            SpendDescription spendDescription = (SpendDescription) obj;
            return getValueCommitment().equals(spendDescription.getValueCommitment()) && getAnchor().equals(spendDescription.getAnchor()) && getNullifier().equals(spendDescription.getNullifier()) && getRk().equals(spendDescription.getRk()) && getZkproof().equals(spendDescription.getZkproof()) && getSpendAuthoritySignature().equals(spendDescription.getSpendAuthoritySignature()) && this.unknownFields.equals(spendDescription.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getValueCommitment().hashCode()) * 37) + 2) * 53) + getAnchor().hashCode()) * 37) + 3) * 53) + getNullifier().hashCode()) * 37) + 4) * 53) + getRk().hashCode()) * 37) + 5) * 53) + getZkproof().hashCode()) * 37) + 6) * 53) + getSpendAuthoritySignature().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static SpendDescription parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static SpendDescription parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static SpendDescription parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static SpendDescription parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static SpendDescription parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static SpendDescription parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static SpendDescription parseFrom(InputStream inputStream) throws IOException {
            return (SpendDescription) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static SpendDescription parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SpendDescription) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SpendDescription parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SpendDescription) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static SpendDescription parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SpendDescription) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SpendDescription parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SpendDescription) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static SpendDescription parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SpendDescription) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SpendDescription spendDescription) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(spendDescription);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SpendDescriptionOrBuilder {
            private ByteString anchor_;
            private ByteString nullifier_;
            private ByteString rk_;
            private ByteString spendAuthoritySignature_;
            private ByteString valueCommitment_;
            private ByteString zkproof_;

            @Override
            public ByteString getAnchor() {
                return this.anchor_;
            }

            @Override
            public ByteString getNullifier() {
                return this.nullifier_;
            }

            @Override
            public ByteString getRk() {
                return this.rk_;
            }

            @Override
            public ByteString getSpendAuthoritySignature() {
                return this.spendAuthoritySignature_;
            }

            @Override
            public ByteString getValueCommitment() {
                return this.valueCommitment_;
            }

            @Override
            public ByteString getZkproof() {
                return this.zkproof_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_SpendDescription_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_SpendDescription_fieldAccessorTable.ensureFieldAccessorsInitialized(SpendDescription.class, Builder.class);
            }

            private Builder() {
                this.valueCommitment_ = ByteString.EMPTY;
                this.anchor_ = ByteString.EMPTY;
                this.nullifier_ = ByteString.EMPTY;
                this.rk_ = ByteString.EMPTY;
                this.zkproof_ = ByteString.EMPTY;
                this.spendAuthoritySignature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.valueCommitment_ = ByteString.EMPTY;
                this.anchor_ = ByteString.EMPTY;
                this.nullifier_ = ByteString.EMPTY;
                this.rk_ = ByteString.EMPTY;
                this.zkproof_ = ByteString.EMPTY;
                this.spendAuthoritySignature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = SpendDescription.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.valueCommitment_ = ByteString.EMPTY;
                this.anchor_ = ByteString.EMPTY;
                this.nullifier_ = ByteString.EMPTY;
                this.rk_ = ByteString.EMPTY;
                this.zkproof_ = ByteString.EMPTY;
                this.spendAuthoritySignature_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_SpendDescription_descriptor;
            }

            @Override
            public SpendDescription getDefaultInstanceForType() {
                return SpendDescription.getDefaultInstance();
            }

            @Override
            public SpendDescription build() {
                SpendDescription buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public SpendDescription buildPartial() {
                SpendDescription spendDescription = new SpendDescription(this);
                spendDescription.valueCommitment_ = this.valueCommitment_;
                spendDescription.anchor_ = this.anchor_;
                spendDescription.nullifier_ = this.nullifier_;
                spendDescription.rk_ = this.rk_;
                spendDescription.zkproof_ = this.zkproof_;
                spendDescription.spendAuthoritySignature_ = this.spendAuthoritySignature_;
                onBuilt();
                return spendDescription;
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
                if (message instanceof SpendDescription) {
                    return mergeFrom((SpendDescription) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(SpendDescription spendDescription) {
                if (spendDescription == SpendDescription.getDefaultInstance()) {
                    return this;
                }
                if (spendDescription.getValueCommitment() != ByteString.EMPTY) {
                    setValueCommitment(spendDescription.getValueCommitment());
                }
                if (spendDescription.getAnchor() != ByteString.EMPTY) {
                    setAnchor(spendDescription.getAnchor());
                }
                if (spendDescription.getNullifier() != ByteString.EMPTY) {
                    setNullifier(spendDescription.getNullifier());
                }
                if (spendDescription.getRk() != ByteString.EMPTY) {
                    setRk(spendDescription.getRk());
                }
                if (spendDescription.getZkproof() != ByteString.EMPTY) {
                    setZkproof(spendDescription.getZkproof());
                }
                if (spendDescription.getSpendAuthoritySignature() != ByteString.EMPTY) {
                    setSpendAuthoritySignature(spendDescription.getSpendAuthoritySignature());
                }
                mergeUnknownFields(spendDescription.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.SpendDescription.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.SpendDescription.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$SpendDescription$Builder");
            }

            public Builder setValueCommitment(ByteString byteString) {
                byteString.getClass();
                this.valueCommitment_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearValueCommitment() {
                this.valueCommitment_ = SpendDescription.getDefaultInstance().getValueCommitment();
                onChanged();
                return this;
            }

            public Builder setAnchor(ByteString byteString) {
                byteString.getClass();
                this.anchor_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAnchor() {
                this.anchor_ = SpendDescription.getDefaultInstance().getAnchor();
                onChanged();
                return this;
            }

            public Builder setNullifier(ByteString byteString) {
                byteString.getClass();
                this.nullifier_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearNullifier() {
                this.nullifier_ = SpendDescription.getDefaultInstance().getNullifier();
                onChanged();
                return this;
            }

            public Builder setRk(ByteString byteString) {
                byteString.getClass();
                this.rk_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearRk() {
                this.rk_ = SpendDescription.getDefaultInstance().getRk();
                onChanged();
                return this;
            }

            public Builder setZkproof(ByteString byteString) {
                byteString.getClass();
                this.zkproof_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearZkproof() {
                this.zkproof_ = SpendDescription.getDefaultInstance().getZkproof();
                onChanged();
                return this;
            }

            public Builder setSpendAuthoritySignature(ByteString byteString) {
                byteString.getClass();
                this.spendAuthoritySignature_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearSpendAuthoritySignature() {
                this.spendAuthoritySignature_ = SpendDescription.getDefaultInstance().getSpendAuthoritySignature();
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

    public static final class ReceiveDescription extends GeneratedMessageV3 implements ReceiveDescriptionOrBuilder {
        public static final int C_ENC_FIELD_NUMBER = 4;
        public static final int C_OUT_FIELD_NUMBER = 5;
        public static final int EPK_FIELD_NUMBER = 3;
        public static final int NOTE_COMMITMENT_FIELD_NUMBER = 2;
        public static final int VALUE_COMMITMENT_FIELD_NUMBER = 1;
        public static final int ZKPROOF_FIELD_NUMBER = 6;
        private static final long serialVersionUID = 0;
        private ByteString cEnc_;
        private ByteString cOut_;
        private ByteString epk_;
        private byte memoizedIsInitialized;
        private ByteString noteCommitment_;
        private ByteString valueCommitment_;
        private ByteString zkproof_;
        private static final ReceiveDescription DEFAULT_INSTANCE = new ReceiveDescription();
        private static final Parser<ReceiveDescription> PARSER = new AbstractParser<ReceiveDescription>() {
            @Override
            public ReceiveDescription parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ReceiveDescription(codedInputStream, extensionRegistryLite);
            }
        };

        public static ReceiveDescription getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReceiveDescription> parser() {
            return PARSER;
        }

        @Override
        public ByteString getCEnc() {
            return this.cEnc_;
        }

        @Override
        public ByteString getCOut() {
            return this.cOut_;
        }

        @Override
        public ReceiveDescription getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getEpk() {
            return this.epk_;
        }

        @Override
        public ByteString getNoteCommitment() {
            return this.noteCommitment_;
        }

        @Override
        public Parser<ReceiveDescription> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getValueCommitment() {
            return this.valueCommitment_;
        }

        @Override
        public ByteString getZkproof() {
            return this.zkproof_;
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

        private ReceiveDescription(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ReceiveDescription() {
            this.memoizedIsInitialized = (byte) -1;
            this.valueCommitment_ = ByteString.EMPTY;
            this.noteCommitment_ = ByteString.EMPTY;
            this.epk_ = ByteString.EMPTY;
            this.cEnc_ = ByteString.EMPTY;
            this.cOut_ = ByteString.EMPTY;
            this.zkproof_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ReceiveDescription(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.valueCommitment_ = codedInputStream.readBytes();
                            } else if (readTag == 18) {
                                this.noteCommitment_ = codedInputStream.readBytes();
                            } else if (readTag == 26) {
                                this.epk_ = codedInputStream.readBytes();
                            } else if (readTag == 34) {
                                this.cEnc_ = codedInputStream.readBytes();
                            } else if (readTag == 42) {
                                this.cOut_ = codedInputStream.readBytes();
                            } else if (readTag != 50) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.zkproof_ = codedInputStream.readBytes();
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
            return ShieldContract.internal_static_protocol_ReceiveDescription_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_ReceiveDescription_fieldAccessorTable.ensureFieldAccessorsInitialized(ReceiveDescription.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.valueCommitment_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.valueCommitment_);
            }
            if (!this.noteCommitment_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.noteCommitment_);
            }
            if (!this.epk_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.epk_);
            }
            if (!this.cEnc_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.cEnc_);
            }
            if (!this.cOut_.isEmpty()) {
                codedOutputStream.writeBytes(5, this.cOut_);
            }
            if (!this.zkproof_.isEmpty()) {
                codedOutputStream.writeBytes(6, this.zkproof_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.valueCommitment_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.valueCommitment_) : 0;
            if (!this.noteCommitment_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.noteCommitment_);
            }
            if (!this.epk_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.epk_);
            }
            if (!this.cEnc_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(4, this.cEnc_);
            }
            if (!this.cOut_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(5, this.cOut_);
            }
            if (!this.zkproof_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(6, this.zkproof_);
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
            if (!(obj instanceof ReceiveDescription)) {
                return super.equals(obj);
            }
            ReceiveDescription receiveDescription = (ReceiveDescription) obj;
            return getValueCommitment().equals(receiveDescription.getValueCommitment()) && getNoteCommitment().equals(receiveDescription.getNoteCommitment()) && getEpk().equals(receiveDescription.getEpk()) && getCEnc().equals(receiveDescription.getCEnc()) && getCOut().equals(receiveDescription.getCOut()) && getZkproof().equals(receiveDescription.getZkproof()) && this.unknownFields.equals(receiveDescription.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getValueCommitment().hashCode()) * 37) + 2) * 53) + getNoteCommitment().hashCode()) * 37) + 3) * 53) + getEpk().hashCode()) * 37) + 4) * 53) + getCEnc().hashCode()) * 37) + 5) * 53) + getCOut().hashCode()) * 37) + 6) * 53) + getZkproof().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ReceiveDescription parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ReceiveDescription parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ReceiveDescription parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ReceiveDescription parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ReceiveDescription parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ReceiveDescription parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ReceiveDescription parseFrom(InputStream inputStream) throws IOException {
            return (ReceiveDescription) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ReceiveDescription parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReceiveDescription) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ReceiveDescription parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReceiveDescription) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ReceiveDescription parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReceiveDescription) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ReceiveDescription parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReceiveDescription) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ReceiveDescription parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReceiveDescription) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ReceiveDescription receiveDescription) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(receiveDescription);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ReceiveDescriptionOrBuilder {
            private ByteString cEnc_;
            private ByteString cOut_;
            private ByteString epk_;
            private ByteString noteCommitment_;
            private ByteString valueCommitment_;
            private ByteString zkproof_;

            @Override
            public ByteString getCEnc() {
                return this.cEnc_;
            }

            @Override
            public ByteString getCOut() {
                return this.cOut_;
            }

            @Override
            public ByteString getEpk() {
                return this.epk_;
            }

            @Override
            public ByteString getNoteCommitment() {
                return this.noteCommitment_;
            }

            @Override
            public ByteString getValueCommitment() {
                return this.valueCommitment_;
            }

            @Override
            public ByteString getZkproof() {
                return this.zkproof_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_ReceiveDescription_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_ReceiveDescription_fieldAccessorTable.ensureFieldAccessorsInitialized(ReceiveDescription.class, Builder.class);
            }

            private Builder() {
                this.valueCommitment_ = ByteString.EMPTY;
                this.noteCommitment_ = ByteString.EMPTY;
                this.epk_ = ByteString.EMPTY;
                this.cEnc_ = ByteString.EMPTY;
                this.cOut_ = ByteString.EMPTY;
                this.zkproof_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.valueCommitment_ = ByteString.EMPTY;
                this.noteCommitment_ = ByteString.EMPTY;
                this.epk_ = ByteString.EMPTY;
                this.cEnc_ = ByteString.EMPTY;
                this.cOut_ = ByteString.EMPTY;
                this.zkproof_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ReceiveDescription.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.valueCommitment_ = ByteString.EMPTY;
                this.noteCommitment_ = ByteString.EMPTY;
                this.epk_ = ByteString.EMPTY;
                this.cEnc_ = ByteString.EMPTY;
                this.cOut_ = ByteString.EMPTY;
                this.zkproof_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_ReceiveDescription_descriptor;
            }

            @Override
            public ReceiveDescription getDefaultInstanceForType() {
                return ReceiveDescription.getDefaultInstance();
            }

            @Override
            public ReceiveDescription build() {
                ReceiveDescription buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ReceiveDescription buildPartial() {
                ReceiveDescription receiveDescription = new ReceiveDescription(this);
                receiveDescription.valueCommitment_ = this.valueCommitment_;
                receiveDescription.noteCommitment_ = this.noteCommitment_;
                receiveDescription.epk_ = this.epk_;
                receiveDescription.cEnc_ = this.cEnc_;
                receiveDescription.cOut_ = this.cOut_;
                receiveDescription.zkproof_ = this.zkproof_;
                onBuilt();
                return receiveDescription;
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
                if (message instanceof ReceiveDescription) {
                    return mergeFrom((ReceiveDescription) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ReceiveDescription receiveDescription) {
                if (receiveDescription == ReceiveDescription.getDefaultInstance()) {
                    return this;
                }
                if (receiveDescription.getValueCommitment() != ByteString.EMPTY) {
                    setValueCommitment(receiveDescription.getValueCommitment());
                }
                if (receiveDescription.getNoteCommitment() != ByteString.EMPTY) {
                    setNoteCommitment(receiveDescription.getNoteCommitment());
                }
                if (receiveDescription.getEpk() != ByteString.EMPTY) {
                    setEpk(receiveDescription.getEpk());
                }
                if (receiveDescription.getCEnc() != ByteString.EMPTY) {
                    setCEnc(receiveDescription.getCEnc());
                }
                if (receiveDescription.getCOut() != ByteString.EMPTY) {
                    setCOut(receiveDescription.getCOut());
                }
                if (receiveDescription.getZkproof() != ByteString.EMPTY) {
                    setZkproof(receiveDescription.getZkproof());
                }
                mergeUnknownFields(receiveDescription.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.ReceiveDescription.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.ReceiveDescription.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$ReceiveDescription$Builder");
            }

            public Builder setValueCommitment(ByteString byteString) {
                byteString.getClass();
                this.valueCommitment_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearValueCommitment() {
                this.valueCommitment_ = ReceiveDescription.getDefaultInstance().getValueCommitment();
                onChanged();
                return this;
            }

            public Builder setNoteCommitment(ByteString byteString) {
                byteString.getClass();
                this.noteCommitment_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearNoteCommitment() {
                this.noteCommitment_ = ReceiveDescription.getDefaultInstance().getNoteCommitment();
                onChanged();
                return this;
            }

            public Builder setEpk(ByteString byteString) {
                byteString.getClass();
                this.epk_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearEpk() {
                this.epk_ = ReceiveDescription.getDefaultInstance().getEpk();
                onChanged();
                return this;
            }

            public Builder setCEnc(ByteString byteString) {
                byteString.getClass();
                this.cEnc_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearCEnc() {
                this.cEnc_ = ReceiveDescription.getDefaultInstance().getCEnc();
                onChanged();
                return this;
            }

            public Builder setCOut(ByteString byteString) {
                byteString.getClass();
                this.cOut_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearCOut() {
                this.cOut_ = ReceiveDescription.getDefaultInstance().getCOut();
                onChanged();
                return this;
            }

            public Builder setZkproof(ByteString byteString) {
                byteString.getClass();
                this.zkproof_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearZkproof() {
                this.zkproof_ = ReceiveDescription.getDefaultInstance().getZkproof();
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

    public static final class ShieldedTransferContract extends GeneratedMessageV3 implements ShieldedTransferContractOrBuilder {
        public static final int BINDING_SIGNATURE_FIELD_NUMBER = 5;
        public static final int FROM_AMOUNT_FIELD_NUMBER = 2;
        public static final int RECEIVE_DESCRIPTION_FIELD_NUMBER = 4;
        public static final int SPEND_DESCRIPTION_FIELD_NUMBER = 3;
        public static final int TO_AMOUNT_FIELD_NUMBER = 7;
        public static final int TRANSPARENT_FROM_ADDRESS_FIELD_NUMBER = 1;
        public static final int TRANSPARENT_TO_ADDRESS_FIELD_NUMBER = 6;
        private static final long serialVersionUID = 0;
        private ByteString bindingSignature_;
        private int bitField0_;
        private long fromAmount_;
        private byte memoizedIsInitialized;
        private List<ReceiveDescription> receiveDescription_;
        private List<SpendDescription> spendDescription_;
        private long toAmount_;
        private ByteString transparentFromAddress_;
        private ByteString transparentToAddress_;
        private static final ShieldedTransferContract DEFAULT_INSTANCE = new ShieldedTransferContract();
        private static final Parser<ShieldedTransferContract> PARSER = new AbstractParser<ShieldedTransferContract>() {
            @Override
            public ShieldedTransferContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ShieldedTransferContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ShieldedTransferContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ShieldedTransferContract> parser() {
            return PARSER;
        }

        @Override
        public ByteString getBindingSignature() {
            return this.bindingSignature_;
        }

        @Override
        public ShieldedTransferContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public long getFromAmount() {
            return this.fromAmount_;
        }

        @Override
        public Parser<ShieldedTransferContract> getParserForType() {
            return PARSER;
        }

        @Override
        public List<ReceiveDescription> getReceiveDescriptionList() {
            return this.receiveDescription_;
        }

        @Override
        public List<? extends ReceiveDescriptionOrBuilder> getReceiveDescriptionOrBuilderList() {
            return this.receiveDescription_;
        }

        @Override
        public List<SpendDescription> getSpendDescriptionList() {
            return this.spendDescription_;
        }

        @Override
        public List<? extends SpendDescriptionOrBuilder> getSpendDescriptionOrBuilderList() {
            return this.spendDescription_;
        }

        @Override
        public long getToAmount() {
            return this.toAmount_;
        }

        @Override
        public ByteString getTransparentFromAddress() {
            return this.transparentFromAddress_;
        }

        @Override
        public ByteString getTransparentToAddress() {
            return this.transparentToAddress_;
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

        private ShieldedTransferContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ShieldedTransferContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.transparentFromAddress_ = ByteString.EMPTY;
            this.fromAmount_ = 0L;
            this.spendDescription_ = Collections.emptyList();
            this.receiveDescription_ = Collections.emptyList();
            this.bindingSignature_ = ByteString.EMPTY;
            this.transparentToAddress_ = ByteString.EMPTY;
            this.toAmount_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ShieldedTransferContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.transparentFromAddress_ = codedInputStream.readBytes();
                            } else if (readTag == 16) {
                                this.fromAmount_ = codedInputStream.readInt64();
                            } else if (readTag == 26) {
                                if (!(z2 & true)) {
                                    this.spendDescription_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.spendDescription_.add((SpendDescription) codedInputStream.readMessage(SpendDescription.parser(), extensionRegistryLite));
                            } else if (readTag == 34) {
                                if (!(z2 & true)) {
                                    this.receiveDescription_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.receiveDescription_.add((ReceiveDescription) codedInputStream.readMessage(ReceiveDescription.parser(), extensionRegistryLite));
                            } else if (readTag == 42) {
                                this.bindingSignature_ = codedInputStream.readBytes();
                            } else if (readTag == 50) {
                                this.transparentToAddress_ = codedInputStream.readBytes();
                            } else if (readTag != 56) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.toAmount_ = codedInputStream.readInt64();
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
                        this.spendDescription_ = Collections.unmodifiableList(this.spendDescription_);
                    }
                    if (z2 & true) {
                        this.receiveDescription_ = Collections.unmodifiableList(this.receiveDescription_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShieldContract.internal_static_protocol_ShieldedTransferContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShieldContract.internal_static_protocol_ShieldedTransferContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ShieldedTransferContract.class, Builder.class);
        }

        @Override
        public int getSpendDescriptionCount() {
            return this.spendDescription_.size();
        }

        @Override
        public SpendDescription getSpendDescription(int i) {
            return this.spendDescription_.get(i);
        }

        @Override
        public SpendDescriptionOrBuilder getSpendDescriptionOrBuilder(int i) {
            return this.spendDescription_.get(i);
        }

        @Override
        public int getReceiveDescriptionCount() {
            return this.receiveDescription_.size();
        }

        @Override
        public ReceiveDescription getReceiveDescription(int i) {
            return this.receiveDescription_.get(i);
        }

        @Override
        public ReceiveDescriptionOrBuilder getReceiveDescriptionOrBuilder(int i) {
            return this.receiveDescription_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.transparentFromAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.transparentFromAddress_);
            }
            long j = this.fromAmount_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            for (int i = 0; i < this.spendDescription_.size(); i++) {
                codedOutputStream.writeMessage(3, this.spendDescription_.get(i));
            }
            for (int i2 = 0; i2 < this.receiveDescription_.size(); i2++) {
                codedOutputStream.writeMessage(4, this.receiveDescription_.get(i2));
            }
            if (!this.bindingSignature_.isEmpty()) {
                codedOutputStream.writeBytes(5, this.bindingSignature_);
            }
            if (!this.transparentToAddress_.isEmpty()) {
                codedOutputStream.writeBytes(6, this.transparentToAddress_);
            }
            long j2 = this.toAmount_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(7, j2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.transparentFromAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.transparentFromAddress_) : 0;
            long j = this.fromAmount_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            for (int i2 = 0; i2 < this.spendDescription_.size(); i2++) {
                computeBytesSize += CodedOutputStream.computeMessageSize(3, this.spendDescription_.get(i2));
            }
            for (int i3 = 0; i3 < this.receiveDescription_.size(); i3++) {
                computeBytesSize += CodedOutputStream.computeMessageSize(4, this.receiveDescription_.get(i3));
            }
            if (!this.bindingSignature_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(5, this.bindingSignature_);
            }
            if (!this.transparentToAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(6, this.transparentToAddress_);
            }
            long j2 = this.toAmount_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(7, j2);
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
            if (!(obj instanceof ShieldedTransferContract)) {
                return super.equals(obj);
            }
            ShieldedTransferContract shieldedTransferContract = (ShieldedTransferContract) obj;
            return getTransparentFromAddress().equals(shieldedTransferContract.getTransparentFromAddress()) && getFromAmount() == shieldedTransferContract.getFromAmount() && getSpendDescriptionList().equals(shieldedTransferContract.getSpendDescriptionList()) && getReceiveDescriptionList().equals(shieldedTransferContract.getReceiveDescriptionList()) && getBindingSignature().equals(shieldedTransferContract.getBindingSignature()) && getTransparentToAddress().equals(shieldedTransferContract.getTransparentToAddress()) && getToAmount() == shieldedTransferContract.getToAmount() && this.unknownFields.equals(shieldedTransferContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTransparentFromAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getFromAmount());
            if (getSpendDescriptionCount() > 0) {
                hashCode = (((hashCode * 37) + 3) * 53) + getSpendDescriptionList().hashCode();
            }
            if (getReceiveDescriptionCount() > 0) {
                hashCode = (((hashCode * 37) + 4) * 53) + getReceiveDescriptionList().hashCode();
            }
            int hashCode2 = (((((((((((((hashCode * 37) + 5) * 53) + getBindingSignature().hashCode()) * 37) + 6) * 53) + getTransparentToAddress().hashCode()) * 37) + 7) * 53) + Internal.hashLong(getToAmount())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static ShieldedTransferContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ShieldedTransferContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ShieldedTransferContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ShieldedTransferContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ShieldedTransferContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ShieldedTransferContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ShieldedTransferContract parseFrom(InputStream inputStream) throws IOException {
            return (ShieldedTransferContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ShieldedTransferContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ShieldedTransferContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ShieldedTransferContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ShieldedTransferContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ShieldedTransferContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ShieldedTransferContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ShieldedTransferContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ShieldedTransferContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ShieldedTransferContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ShieldedTransferContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ShieldedTransferContract shieldedTransferContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(shieldedTransferContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ShieldedTransferContractOrBuilder {
            private ByteString bindingSignature_;
            private int bitField0_;
            private long fromAmount_;
            private RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> receiveDescriptionBuilder_;
            private List<ReceiveDescription> receiveDescription_;
            private RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> spendDescriptionBuilder_;
            private List<SpendDescription> spendDescription_;
            private long toAmount_;
            private ByteString transparentFromAddress_;
            private ByteString transparentToAddress_;

            @Override
            public ByteString getBindingSignature() {
                return this.bindingSignature_;
            }

            @Override
            public long getFromAmount() {
                return this.fromAmount_;
            }

            @Override
            public long getToAmount() {
                return this.toAmount_;
            }

            @Override
            public ByteString getTransparentFromAddress() {
                return this.transparentFromAddress_;
            }

            @Override
            public ByteString getTransparentToAddress() {
                return this.transparentToAddress_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShieldContract.internal_static_protocol_ShieldedTransferContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShieldContract.internal_static_protocol_ShieldedTransferContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ShieldedTransferContract.class, Builder.class);
            }

            private Builder() {
                this.transparentFromAddress_ = ByteString.EMPTY;
                this.spendDescription_ = Collections.emptyList();
                this.receiveDescription_ = Collections.emptyList();
                this.bindingSignature_ = ByteString.EMPTY;
                this.transparentToAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.transparentFromAddress_ = ByteString.EMPTY;
                this.spendDescription_ = Collections.emptyList();
                this.receiveDescription_ = Collections.emptyList();
                this.bindingSignature_ = ByteString.EMPTY;
                this.transparentToAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (ShieldedTransferContract.alwaysUseFieldBuilders) {
                    getSpendDescriptionFieldBuilder();
                    getReceiveDescriptionFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                this.transparentFromAddress_ = ByteString.EMPTY;
                this.fromAmount_ = 0L;
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.spendDescription_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV32 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    this.receiveDescription_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                } else {
                    repeatedFieldBuilderV32.clear();
                }
                this.bindingSignature_ = ByteString.EMPTY;
                this.transparentToAddress_ = ByteString.EMPTY;
                this.toAmount_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ShieldContract.internal_static_protocol_ShieldedTransferContract_descriptor;
            }

            @Override
            public ShieldedTransferContract getDefaultInstanceForType() {
                return ShieldedTransferContract.getDefaultInstance();
            }

            @Override
            public ShieldedTransferContract build() {
                ShieldedTransferContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ShieldedTransferContract buildPartial() {
                ShieldedTransferContract shieldedTransferContract = new ShieldedTransferContract(this);
                shieldedTransferContract.transparentFromAddress_ = this.transparentFromAddress_;
                shieldedTransferContract.fromAmount_ = this.fromAmount_;
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.spendDescription_ = Collections.unmodifiableList(this.spendDescription_);
                        this.bitField0_ &= -5;
                    }
                    shieldedTransferContract.spendDescription_ = this.spendDescription_;
                } else {
                    shieldedTransferContract.spendDescription_ = repeatedFieldBuilderV3.build();
                }
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV32 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.receiveDescription_ = Collections.unmodifiableList(this.receiveDescription_);
                        this.bitField0_ &= -9;
                    }
                    shieldedTransferContract.receiveDescription_ = this.receiveDescription_;
                } else {
                    shieldedTransferContract.receiveDescription_ = repeatedFieldBuilderV32.build();
                }
                shieldedTransferContract.bindingSignature_ = this.bindingSignature_;
                shieldedTransferContract.transparentToAddress_ = this.transparentToAddress_;
                shieldedTransferContract.toAmount_ = this.toAmount_;
                shieldedTransferContract.bitField0_ = 0;
                onBuilt();
                return shieldedTransferContract;
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
                if (message instanceof ShieldedTransferContract) {
                    return mergeFrom((ShieldedTransferContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ShieldedTransferContract shieldedTransferContract) {
                if (shieldedTransferContract == ShieldedTransferContract.getDefaultInstance()) {
                    return this;
                }
                if (shieldedTransferContract.getTransparentFromAddress() != ByteString.EMPTY) {
                    setTransparentFromAddress(shieldedTransferContract.getTransparentFromAddress());
                }
                if (shieldedTransferContract.getFromAmount() != 0) {
                    setFromAmount(shieldedTransferContract.getFromAmount());
                }
                if (this.spendDescriptionBuilder_ == null) {
                    if (!shieldedTransferContract.spendDescription_.isEmpty()) {
                        if (this.spendDescription_.isEmpty()) {
                            this.spendDescription_ = shieldedTransferContract.spendDescription_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureSpendDescriptionIsMutable();
                            this.spendDescription_.addAll(shieldedTransferContract.spendDescription_);
                        }
                        onChanged();
                    }
                } else if (!shieldedTransferContract.spendDescription_.isEmpty()) {
                    if (!this.spendDescriptionBuilder_.isEmpty()) {
                        this.spendDescriptionBuilder_.addAllMessages(shieldedTransferContract.spendDescription_);
                    } else {
                        this.spendDescriptionBuilder_.dispose();
                        this.spendDescriptionBuilder_ = null;
                        this.spendDescription_ = shieldedTransferContract.spendDescription_;
                        this.bitField0_ &= -5;
                        this.spendDescriptionBuilder_ = ShieldedTransferContract.alwaysUseFieldBuilders ? getSpendDescriptionFieldBuilder() : null;
                    }
                }
                if (this.receiveDescriptionBuilder_ == null) {
                    if (!shieldedTransferContract.receiveDescription_.isEmpty()) {
                        if (this.receiveDescription_.isEmpty()) {
                            this.receiveDescription_ = shieldedTransferContract.receiveDescription_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureReceiveDescriptionIsMutable();
                            this.receiveDescription_.addAll(shieldedTransferContract.receiveDescription_);
                        }
                        onChanged();
                    }
                } else if (!shieldedTransferContract.receiveDescription_.isEmpty()) {
                    if (!this.receiveDescriptionBuilder_.isEmpty()) {
                        this.receiveDescriptionBuilder_.addAllMessages(shieldedTransferContract.receiveDescription_);
                    } else {
                        this.receiveDescriptionBuilder_.dispose();
                        this.receiveDescriptionBuilder_ = null;
                        this.receiveDescription_ = shieldedTransferContract.receiveDescription_;
                        this.bitField0_ &= -9;
                        this.receiveDescriptionBuilder_ = ShieldedTransferContract.alwaysUseFieldBuilders ? getReceiveDescriptionFieldBuilder() : null;
                    }
                }
                if (shieldedTransferContract.getBindingSignature() != ByteString.EMPTY) {
                    setBindingSignature(shieldedTransferContract.getBindingSignature());
                }
                if (shieldedTransferContract.getTransparentToAddress() != ByteString.EMPTY) {
                    setTransparentToAddress(shieldedTransferContract.getTransparentToAddress());
                }
                if (shieldedTransferContract.getToAmount() != 0) {
                    setToAmount(shieldedTransferContract.getToAmount());
                }
                mergeUnknownFields(shieldedTransferContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ShieldContract.ShieldedTransferContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ShieldContract.ShieldedTransferContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ShieldContract$ShieldedTransferContract$Builder");
            }

            public Builder setTransparentFromAddress(ByteString byteString) {
                byteString.getClass();
                this.transparentFromAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearTransparentFromAddress() {
                this.transparentFromAddress_ = ShieldedTransferContract.getDefaultInstance().getTransparentFromAddress();
                onChanged();
                return this;
            }

            public Builder setFromAmount(long j) {
                this.fromAmount_ = j;
                onChanged();
                return this;
            }

            public Builder clearFromAmount() {
                this.fromAmount_ = 0L;
                onChanged();
                return this;
            }

            private void ensureSpendDescriptionIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.spendDescription_ = new ArrayList(this.spendDescription_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public List<SpendDescription> getSpendDescriptionList() {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.spendDescription_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getSpendDescriptionCount() {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.spendDescription_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public SpendDescription getSpendDescription(int i) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.spendDescription_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setSpendDescription(int i, SpendDescription spendDescription) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    spendDescription.getClass();
                    ensureSpendDescriptionIsMutable();
                    this.spendDescription_.set(i, spendDescription);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, spendDescription);
                }
                return this;
            }

            public Builder setSpendDescription(int i, SpendDescription.Builder builder) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureSpendDescriptionIsMutable();
                    this.spendDescription_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addSpendDescription(SpendDescription spendDescription) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    spendDescription.getClass();
                    ensureSpendDescriptionIsMutable();
                    this.spendDescription_.add(spendDescription);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(spendDescription);
                }
                return this;
            }

            public Builder addSpendDescription(int i, SpendDescription spendDescription) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    spendDescription.getClass();
                    ensureSpendDescriptionIsMutable();
                    this.spendDescription_.add(i, spendDescription);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, spendDescription);
                }
                return this;
            }

            public Builder addSpendDescription(SpendDescription.Builder builder) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureSpendDescriptionIsMutable();
                    this.spendDescription_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addSpendDescription(int i, SpendDescription.Builder builder) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureSpendDescriptionIsMutable();
                    this.spendDescription_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllSpendDescription(Iterable<? extends SpendDescription> iterable) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureSpendDescriptionIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.spendDescription_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearSpendDescription() {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.spendDescription_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeSpendDescription(int i) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureSpendDescriptionIsMutable();
                    this.spendDescription_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public SpendDescription.Builder getSpendDescriptionBuilder(int i) {
                return getSpendDescriptionFieldBuilder().getBuilder(i);
            }

            @Override
            public SpendDescriptionOrBuilder getSpendDescriptionOrBuilder(int i) {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.spendDescription_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends SpendDescriptionOrBuilder> getSpendDescriptionOrBuilderList() {
                RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> repeatedFieldBuilderV3 = this.spendDescriptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.spendDescription_);
            }

            public SpendDescription.Builder addSpendDescriptionBuilder() {
                return getSpendDescriptionFieldBuilder().addBuilder(SpendDescription.getDefaultInstance());
            }

            public SpendDescription.Builder addSpendDescriptionBuilder(int i) {
                return getSpendDescriptionFieldBuilder().addBuilder(i, SpendDescription.getDefaultInstance());
            }

            public List<SpendDescription.Builder> getSpendDescriptionBuilderList() {
                return getSpendDescriptionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<SpendDescription, SpendDescription.Builder, SpendDescriptionOrBuilder> getSpendDescriptionFieldBuilder() {
                if (this.spendDescriptionBuilder_ == null) {
                    this.spendDescriptionBuilder_ = new RepeatedFieldBuilderV3<>(this.spendDescription_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                    this.spendDescription_ = null;
                }
                return this.spendDescriptionBuilder_;
            }

            private void ensureReceiveDescriptionIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.receiveDescription_ = new ArrayList(this.receiveDescription_);
                    this.bitField0_ |= 8;
                }
            }

            @Override
            public List<ReceiveDescription> getReceiveDescriptionList() {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.receiveDescription_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getReceiveDescriptionCount() {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.receiveDescription_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public ReceiveDescription getReceiveDescription(int i) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.receiveDescription_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setReceiveDescription(int i, ReceiveDescription receiveDescription) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    receiveDescription.getClass();
                    ensureReceiveDescriptionIsMutable();
                    this.receiveDescription_.set(i, receiveDescription);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, receiveDescription);
                }
                return this;
            }

            public Builder setReceiveDescription(int i, ReceiveDescription.Builder builder) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveDescriptionIsMutable();
                    this.receiveDescription_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addReceiveDescription(ReceiveDescription receiveDescription) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    receiveDescription.getClass();
                    ensureReceiveDescriptionIsMutable();
                    this.receiveDescription_.add(receiveDescription);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(receiveDescription);
                }
                return this;
            }

            public Builder addReceiveDescription(int i, ReceiveDescription receiveDescription) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    receiveDescription.getClass();
                    ensureReceiveDescriptionIsMutable();
                    this.receiveDescription_.add(i, receiveDescription);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, receiveDescription);
                }
                return this;
            }

            public Builder addReceiveDescription(ReceiveDescription.Builder builder) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveDescriptionIsMutable();
                    this.receiveDescription_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addReceiveDescription(int i, ReceiveDescription.Builder builder) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveDescriptionIsMutable();
                    this.receiveDescription_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllReceiveDescription(Iterable<? extends ReceiveDescription> iterable) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveDescriptionIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.receiveDescription_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearReceiveDescription() {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.receiveDescription_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeReceiveDescription(int i) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveDescriptionIsMutable();
                    this.receiveDescription_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public ReceiveDescription.Builder getReceiveDescriptionBuilder(int i) {
                return getReceiveDescriptionFieldBuilder().getBuilder(i);
            }

            @Override
            public ReceiveDescriptionOrBuilder getReceiveDescriptionOrBuilder(int i) {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.receiveDescription_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends ReceiveDescriptionOrBuilder> getReceiveDescriptionOrBuilderList() {
                RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> repeatedFieldBuilderV3 = this.receiveDescriptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.receiveDescription_);
            }

            public ReceiveDescription.Builder addReceiveDescriptionBuilder() {
                return getReceiveDescriptionFieldBuilder().addBuilder(ReceiveDescription.getDefaultInstance());
            }

            public ReceiveDescription.Builder addReceiveDescriptionBuilder(int i) {
                return getReceiveDescriptionFieldBuilder().addBuilder(i, ReceiveDescription.getDefaultInstance());
            }

            public List<ReceiveDescription.Builder> getReceiveDescriptionBuilderList() {
                return getReceiveDescriptionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<ReceiveDescription, ReceiveDescription.Builder, ReceiveDescriptionOrBuilder> getReceiveDescriptionFieldBuilder() {
                if (this.receiveDescriptionBuilder_ == null) {
                    this.receiveDescriptionBuilder_ = new RepeatedFieldBuilderV3<>(this.receiveDescription_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                    this.receiveDescription_ = null;
                }
                return this.receiveDescriptionBuilder_;
            }

            public Builder setBindingSignature(ByteString byteString) {
                byteString.getClass();
                this.bindingSignature_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearBindingSignature() {
                this.bindingSignature_ = ShieldedTransferContract.getDefaultInstance().getBindingSignature();
                onChanged();
                return this;
            }

            public Builder setTransparentToAddress(ByteString byteString) {
                byteString.getClass();
                this.transparentToAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearTransparentToAddress() {
                this.transparentToAddress_ = ShieldedTransferContract.getDefaultInstance().getTransparentToAddress();
                onChanged();
                return this;
            }

            public Builder setToAmount(long j) {
                this.toAmount_ = j;
                onChanged();
                return this;
            }

            public Builder clearToAmount() {
                this.toAmount_ = 0L;
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n#core/contract/shield_contract.proto\u0012\bprotocol\"#\n\u0012AuthenticationPath\u0012\r\n\u0005value\u0018\u0001 \u0003(\b\"c\n\nMerklePath\u0012:\n\u0014authentication_paths\u0018\u0001 \u0003(\u000b2\u001c.protocol.AuthenticationPath\u0012\r\n\u0005index\u0018\u0002 \u0003(\b\u0012\n\n\u0002rt\u0018\u0003 \u0001(\f\"*\n\u000bOutputPoint\u0012\f\n\u0004hash\u0018\u0001 \u0001(\f\u0012\r\n\u0005index\u0018\u0002 \u0001(\u0005\"O\n\u000fOutputPointInfo\u0012)\n\nout_points\u0018\u0001 \u0003(\u000b2\u0015.protocol.OutputPoint\u0012\u0011\n\tblock_num\u0018\u0002 \u0001(\u0005\"\u001f\n\fPedersenHash\u0012\u000f\n\u0007content\u0018\u0001 \u0001(\f\"\u008d\u0001\n\u0015IncrementalMerkleTree\u0012$\n\u0004left\u0018\u0001 \u0001(\u000b2\u0016.protocol.PedersenHash\u0012%\n\u0005right\u0018\u0002 \u0001(\u000b2\u0016.protocol.PedersenHash\u0012'\n\u0007parents\u0018\u0003 \u0003(\u000b2\u0016.protocol.PedersenHash\"ñ\u0001\n\u0018IncrementalMerkleVoucher\u0012-\n\u0004tree\u0018\u0001 \u0001(\u000b2\u001f.protocol.IncrementalMerkleTree\u0012&\n\u0006filled\u0018\u0002 \u0003(\u000b2\u0016.protocol.PedersenHash\u0012/\n\u0006cursor\u0018\u0003 \u0001(\u000b2\u001f.protocol.IncrementalMerkleTree\u0012\u0014\n\fcursor_depth\u0018\u0004 \u0001(\u0003\u0012\n\n\u0002rt\u0018\u0005 \u0001(\f\u0012+\n\foutput_point\u0018\n \u0001(\u000b2\u0015.protocol.OutputPoint\"c\n\u001cIncrementalMerkleVoucherInfo\u00124\n\bvouchers\u0018\u0001 \u0003(\u000b2\".protocol.IncrementalMerkleVoucher\u0012\r\n\u0005paths\u0018\u0002 \u0003(\f\"\u008f\u0001\n\u0010SpendDescription\u0012\u0018\n\u0010value_commitment\u0018\u0001 \u0001(\f\u0012\u000e\n\u0006anchor\u0018\u0002 \u0001(\f\u0012\u0011\n\tnullifier\u0018\u0003 \u0001(\f\u0012\n\n\u0002rk\u0018\u0004 \u0001(\f\u0012\u000f\n\u0007zkproof\u0018\u0005 \u0001(\f\u0012!\n\u0019spend_authority_signature\u0018\u0006 \u0001(\f\"\u0083\u0001\n\u0012ReceiveDescription\u0012\u0018\n\u0010value_commitment\u0018\u0001 \u0001(\f\u0012\u0017\n\u000fnote_commitment\u0018\u0002 \u0001(\f\u0012\u000b\n\u0003epk\u0018\u0003 \u0001(\f\u0012\r\n\u0005c_enc\u0018\u0004 \u0001(\f\u0012\r\n\u0005c_out\u0018\u0005 \u0001(\f\u0012\u000f\n\u0007zkproof\u0018\u0006 \u0001(\f\"\u0091\u0002\n\u0018ShieldedTransferContract\u0012 \n\u0018transparent_from_address\u0018\u0001 \u0001(\f\u0012\u0013\n\u000bfrom_amount\u0018\u0002 \u0001(\u0003\u00125\n\u0011spend_description\u0018\u0003 \u0003(\u000b2\u001a.protocol.SpendDescription\u00129\n\u0013receive_description\u0018\u0004 \u0003(\u000b2\u001c.protocol.ReceiveDescription\u0012\u0019\n\u0011binding_signature\u0018\u0005 \u0001(\f\u0012\u001e\n\u0016transparent_to_address\u0018\u0006 \u0001(\f\u0012\u0011\n\tto_amount\u0018\u0007 \u0001(\u0003BE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ShieldContract.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_AuthenticationPath_descriptor = descriptor2;
        internal_static_protocol_AuthenticationPath_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Value"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_MerklePath_descriptor = descriptor3;
        internal_static_protocol_MerklePath_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"AuthenticationPaths", "Index", "Rt"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_OutputPoint_descriptor = descriptor4;
        internal_static_protocol_OutputPoint_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Hash", "Index"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_protocol_OutputPointInfo_descriptor = descriptor5;
        internal_static_protocol_OutputPointInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"OutPoints", "BlockNum"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        internal_static_protocol_PedersenHash_descriptor = descriptor6;
        internal_static_protocol_PedersenHash_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Content"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(5);
        internal_static_protocol_IncrementalMerkleTree_descriptor = descriptor7;
        internal_static_protocol_IncrementalMerkleTree_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Left", "Right", "Parents"});
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(6);
        internal_static_protocol_IncrementalMerkleVoucher_descriptor = descriptor8;
        internal_static_protocol_IncrementalMerkleVoucher_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"Tree", "Filled", "Cursor", "CursorDepth", "Rt", "OutputPoint"});
        Descriptors.Descriptor descriptor9 = getDescriptor().getMessageTypes().get(7);
        internal_static_protocol_IncrementalMerkleVoucherInfo_descriptor = descriptor9;
        internal_static_protocol_IncrementalMerkleVoucherInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Vouchers", "Paths"});
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(8);
        internal_static_protocol_SpendDescription_descriptor = descriptor10;
        internal_static_protocol_SpendDescription_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"ValueCommitment", "Anchor", "Nullifier", "Rk", "Zkproof", "SpendAuthoritySignature"});
        Descriptors.Descriptor descriptor11 = getDescriptor().getMessageTypes().get(9);
        internal_static_protocol_ReceiveDescription_descriptor = descriptor11;
        internal_static_protocol_ReceiveDescription_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"ValueCommitment", "NoteCommitment", "Epk", "CEnc", "COut", "Zkproof"});
        Descriptors.Descriptor descriptor12 = getDescriptor().getMessageTypes().get(10);
        internal_static_protocol_ShieldedTransferContract_descriptor = descriptor12;
        internal_static_protocol_ShieldedTransferContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"TransparentFromAddress", "FromAmount", "SpendDescription", "ReceiveDescription", "BindingSignature", "TransparentToAddress", "ToAmount"});
    }
}
