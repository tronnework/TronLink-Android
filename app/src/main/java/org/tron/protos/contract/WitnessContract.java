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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class WitnessContract {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_VoteWitnessContract_Vote_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_VoteWitnessContract_Vote_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_VoteWitnessContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_VoteWitnessContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_WitnessCreateContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_WitnessCreateContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_WitnessUpdateContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_WitnessUpdateContract_fieldAccessorTable;

    public interface VoteWitnessContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();

        boolean getSupport();

        VoteWitnessContract.Vote getVotes(int i);

        int getVotesCount();

        List<VoteWitnessContract.Vote> getVotesList();

        VoteWitnessContract.VoteOrBuilder getVotesOrBuilder(int i);

        List<? extends VoteWitnessContract.VoteOrBuilder> getVotesOrBuilderList();
    }

    public interface WitnessCreateContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();

        ByteString getUrl();
    }

    public interface WitnessUpdateContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();

        ByteString getUpdateUrl();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private WitnessContract() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class WitnessCreateContract extends GeneratedMessageV3 implements WitnessCreateContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int URL_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString url_;
        private static final WitnessCreateContract DEFAULT_INSTANCE = new WitnessCreateContract();
        private static final Parser<WitnessCreateContract> PARSER = new AbstractParser<WitnessCreateContract>() {
            @Override
            public WitnessCreateContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new WitnessCreateContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static WitnessCreateContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WitnessCreateContract> parser() {
            return PARSER;
        }

        @Override
        public WitnessCreateContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<WitnessCreateContract> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getUrl() {
            return this.url_;
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

        private WitnessCreateContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private WitnessCreateContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.url_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private WitnessCreateContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.url_ = codedInputStream.readBytes();
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
            return WitnessContract.internal_static_protocol_WitnessCreateContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return WitnessContract.internal_static_protocol_WitnessCreateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(WitnessCreateContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.url_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.url_);
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
            if (!this.url_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.url_);
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
            if (!(obj instanceof WitnessCreateContract)) {
                return super.equals(obj);
            }
            WitnessCreateContract witnessCreateContract = (WitnessCreateContract) obj;
            return getOwnerAddress().equals(witnessCreateContract.getOwnerAddress()) && getUrl().equals(witnessCreateContract.getUrl()) && this.unknownFields.equals(witnessCreateContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getUrl().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static WitnessCreateContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static WitnessCreateContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static WitnessCreateContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static WitnessCreateContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static WitnessCreateContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static WitnessCreateContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static WitnessCreateContract parseFrom(InputStream inputStream) throws IOException {
            return (WitnessCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static WitnessCreateContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WitnessCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static WitnessCreateContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (WitnessCreateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static WitnessCreateContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WitnessCreateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static WitnessCreateContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (WitnessCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static WitnessCreateContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WitnessCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WitnessCreateContract witnessCreateContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(witnessCreateContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WitnessCreateContractOrBuilder {
            private ByteString ownerAddress_;
            private ByteString url_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public ByteString getUrl() {
                return this.url_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return WitnessContract.internal_static_protocol_WitnessCreateContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return WitnessContract.internal_static_protocol_WitnessCreateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(WitnessCreateContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.url_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.url_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = WitnessCreateContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.url_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return WitnessContract.internal_static_protocol_WitnessCreateContract_descriptor;
            }

            @Override
            public WitnessCreateContract getDefaultInstanceForType() {
                return WitnessCreateContract.getDefaultInstance();
            }

            @Override
            public WitnessCreateContract build() {
                WitnessCreateContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public WitnessCreateContract buildPartial() {
                WitnessCreateContract witnessCreateContract = new WitnessCreateContract(this);
                witnessCreateContract.ownerAddress_ = this.ownerAddress_;
                witnessCreateContract.url_ = this.url_;
                onBuilt();
                return witnessCreateContract;
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
                if (message instanceof WitnessCreateContract) {
                    return mergeFrom((WitnessCreateContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(WitnessCreateContract witnessCreateContract) {
                if (witnessCreateContract == WitnessCreateContract.getDefaultInstance()) {
                    return this;
                }
                if (witnessCreateContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(witnessCreateContract.getOwnerAddress());
                }
                if (witnessCreateContract.getUrl() != ByteString.EMPTY) {
                    setUrl(witnessCreateContract.getUrl());
                }
                mergeUnknownFields(witnessCreateContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.WitnessContract.WitnessCreateContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.WitnessContract.WitnessCreateContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.WitnessContract$WitnessCreateContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = WitnessCreateContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setUrl(ByteString byteString) {
                byteString.getClass();
                this.url_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearUrl() {
                this.url_ = WitnessCreateContract.getDefaultInstance().getUrl();
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

    public static final class WitnessUpdateContract extends GeneratedMessageV3 implements WitnessUpdateContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int UPDATE_URL_FIELD_NUMBER = 12;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString updateUrl_;
        private static final WitnessUpdateContract DEFAULT_INSTANCE = new WitnessUpdateContract();
        private static final Parser<WitnessUpdateContract> PARSER = new AbstractParser<WitnessUpdateContract>() {
            @Override
            public WitnessUpdateContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new WitnessUpdateContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static WitnessUpdateContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WitnessUpdateContract> parser() {
            return PARSER;
        }

        @Override
        public WitnessUpdateContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<WitnessUpdateContract> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getUpdateUrl() {
            return this.updateUrl_;
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

        private WitnessUpdateContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private WitnessUpdateContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.updateUrl_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private WitnessUpdateContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                } else if (readTag != 98) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.updateUrl_ = codedInputStream.readBytes();
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
            return WitnessContract.internal_static_protocol_WitnessUpdateContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return WitnessContract.internal_static_protocol_WitnessUpdateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(WitnessUpdateContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.updateUrl_.isEmpty()) {
                codedOutputStream.writeBytes(12, this.updateUrl_);
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
            if (!this.updateUrl_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(12, this.updateUrl_);
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
            if (!(obj instanceof WitnessUpdateContract)) {
                return super.equals(obj);
            }
            WitnessUpdateContract witnessUpdateContract = (WitnessUpdateContract) obj;
            return getOwnerAddress().equals(witnessUpdateContract.getOwnerAddress()) && getUpdateUrl().equals(witnessUpdateContract.getUpdateUrl()) && this.unknownFields.equals(witnessUpdateContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 12) * 53) + getUpdateUrl().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static WitnessUpdateContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static WitnessUpdateContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static WitnessUpdateContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static WitnessUpdateContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static WitnessUpdateContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static WitnessUpdateContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static WitnessUpdateContract parseFrom(InputStream inputStream) throws IOException {
            return (WitnessUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static WitnessUpdateContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WitnessUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static WitnessUpdateContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (WitnessUpdateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static WitnessUpdateContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WitnessUpdateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static WitnessUpdateContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (WitnessUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static WitnessUpdateContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WitnessUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WitnessUpdateContract witnessUpdateContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(witnessUpdateContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WitnessUpdateContractOrBuilder {
            private ByteString ownerAddress_;
            private ByteString updateUrl_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public ByteString getUpdateUrl() {
                return this.updateUrl_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return WitnessContract.internal_static_protocol_WitnessUpdateContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return WitnessContract.internal_static_protocol_WitnessUpdateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(WitnessUpdateContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.updateUrl_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.updateUrl_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = WitnessUpdateContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.updateUrl_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return WitnessContract.internal_static_protocol_WitnessUpdateContract_descriptor;
            }

            @Override
            public WitnessUpdateContract getDefaultInstanceForType() {
                return WitnessUpdateContract.getDefaultInstance();
            }

            @Override
            public WitnessUpdateContract build() {
                WitnessUpdateContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public WitnessUpdateContract buildPartial() {
                WitnessUpdateContract witnessUpdateContract = new WitnessUpdateContract(this);
                witnessUpdateContract.ownerAddress_ = this.ownerAddress_;
                witnessUpdateContract.updateUrl_ = this.updateUrl_;
                onBuilt();
                return witnessUpdateContract;
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
                if (message instanceof WitnessUpdateContract) {
                    return mergeFrom((WitnessUpdateContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(WitnessUpdateContract witnessUpdateContract) {
                if (witnessUpdateContract == WitnessUpdateContract.getDefaultInstance()) {
                    return this;
                }
                if (witnessUpdateContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(witnessUpdateContract.getOwnerAddress());
                }
                if (witnessUpdateContract.getUpdateUrl() != ByteString.EMPTY) {
                    setUpdateUrl(witnessUpdateContract.getUpdateUrl());
                }
                mergeUnknownFields(witnessUpdateContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.WitnessContract.WitnessUpdateContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.WitnessContract.WitnessUpdateContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.WitnessContract$WitnessUpdateContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = WitnessUpdateContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setUpdateUrl(ByteString byteString) {
                byteString.getClass();
                this.updateUrl_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearUpdateUrl() {
                this.updateUrl_ = WitnessUpdateContract.getDefaultInstance().getUpdateUrl();
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

    public static final class VoteWitnessContract extends GeneratedMessageV3 implements VoteWitnessContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int SUPPORT_FIELD_NUMBER = 3;
        public static final int VOTES_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private boolean support_;
        private List<Vote> votes_;
        private static final VoteWitnessContract DEFAULT_INSTANCE = new VoteWitnessContract();
        private static final Parser<VoteWitnessContract> PARSER = new AbstractParser<VoteWitnessContract>() {
            @Override
            public VoteWitnessContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new VoteWitnessContract(codedInputStream, extensionRegistryLite);
            }
        };

        public interface VoteOrBuilder extends MessageOrBuilder {
            ByteString getVoteAddress();

            long getVoteCount();
        }

        public static VoteWitnessContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<VoteWitnessContract> parser() {
            return PARSER;
        }

        @Override
        public VoteWitnessContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<VoteWitnessContract> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean getSupport() {
            return this.support_;
        }

        @Override
        public List<Vote> getVotesList() {
            return this.votes_;
        }

        @Override
        public List<? extends VoteOrBuilder> getVotesOrBuilderList() {
            return this.votes_;
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

        private VoteWitnessContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private VoteWitnessContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.votes_ = Collections.emptyList();
            this.support_ = false;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private VoteWitnessContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.votes_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.votes_.add((Vote) codedInputStream.readMessage(Vote.parser(), extensionRegistryLite));
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.support_ = codedInputStream.readBool();
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
                        this.votes_ = Collections.unmodifiableList(this.votes_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return WitnessContract.internal_static_protocol_VoteWitnessContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return WitnessContract.internal_static_protocol_VoteWitnessContract_fieldAccessorTable.ensureFieldAccessorsInitialized(VoteWitnessContract.class, Builder.class);
        }

        public static final class Vote extends GeneratedMessageV3 implements VoteOrBuilder {
            private static final Vote DEFAULT_INSTANCE = new Vote();
            private static final Parser<Vote> PARSER = new AbstractParser<Vote>() {
                @Override
                public Vote parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Vote(codedInputStream, extensionRegistryLite);
                }
            };
            public static final int VOTE_ADDRESS_FIELD_NUMBER = 1;
            public static final int VOTE_COUNT_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private byte memoizedIsInitialized;
            private ByteString voteAddress_;
            private long voteCount_;

            public static Vote getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Vote> parser() {
                return PARSER;
            }

            @Override
            public Vote getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override
            public Parser<Vote> getParserForType() {
                return PARSER;
            }

            @Override
            public ByteString getVoteAddress() {
                return this.voteAddress_;
            }

            @Override
            public long getVoteCount() {
                return this.voteCount_;
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

            private Vote(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Vote() {
                this.memoizedIsInitialized = (byte) -1;
                this.voteAddress_ = ByteString.EMPTY;
                this.voteCount_ = 0L;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Vote(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.voteAddress_ = codedInputStream.readBytes();
                                    } else if (readTag != 16) {
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.voteCount_ = codedInputStream.readInt64();
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
                return WitnessContract.internal_static_protocol_VoteWitnessContract_Vote_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return WitnessContract.internal_static_protocol_VoteWitnessContract_Vote_fieldAccessorTable.ensureFieldAccessorsInitialized(Vote.class, Builder.class);
            }

            @Override
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (!this.voteAddress_.isEmpty()) {
                    codedOutputStream.writeBytes(1, this.voteAddress_);
                }
                long j = this.voteCount_;
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
                int computeBytesSize = !this.voteAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.voteAddress_) : 0;
                long j = this.voteCount_;
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
                if (!(obj instanceof Vote)) {
                    return super.equals(obj);
                }
                Vote vote = (Vote) obj;
                return getVoteAddress().equals(vote.getVoteAddress()) && getVoteCount() == vote.getVoteCount() && this.unknownFields.equals(vote.unknownFields);
            }

            @Override
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getVoteAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getVoteCount())) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            public static Vote parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static Vote parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Vote parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static Vote parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Vote parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static Vote parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Vote parseFrom(InputStream inputStream) throws IOException {
                return (Vote) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Vote parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Vote) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Vote parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Vote) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Vote parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Vote) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Vote parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Vote) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Vote parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Vote) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Vote vote) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(vote);
            }

            @Override
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            @Override
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VoteOrBuilder {
                private ByteString voteAddress_;
                private long voteCount_;

                @Override
                public ByteString getVoteAddress() {
                    return this.voteAddress_;
                }

                @Override
                public long getVoteCount() {
                    return this.voteCount_;
                }

                @Override
                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return WitnessContract.internal_static_protocol_VoteWitnessContract_Vote_descriptor;
                }

                @Override
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return WitnessContract.internal_static_protocol_VoteWitnessContract_Vote_fieldAccessorTable.ensureFieldAccessorsInitialized(Vote.class, Builder.class);
                }

                private Builder() {
                    this.voteAddress_ = ByteString.EMPTY;
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.voteAddress_ = ByteString.EMPTY;
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Vote.alwaysUseFieldBuilders;
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.voteAddress_ = ByteString.EMPTY;
                    this.voteCount_ = 0L;
                    return this;
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return WitnessContract.internal_static_protocol_VoteWitnessContract_Vote_descriptor;
                }

                @Override
                public Vote getDefaultInstanceForType() {
                    return Vote.getDefaultInstance();
                }

                @Override
                public Vote build() {
                    Vote buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override
                public Vote buildPartial() {
                    Vote vote = new Vote(this);
                    vote.voteAddress_ = this.voteAddress_;
                    vote.voteCount_ = this.voteCount_;
                    onBuilt();
                    return vote;
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
                    if (message instanceof Vote) {
                        return mergeFrom((Vote) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Vote vote) {
                    if (vote == Vote.getDefaultInstance()) {
                        return this;
                    }
                    if (vote.getVoteAddress() != ByteString.EMPTY) {
                        setVoteAddress(vote.getVoteAddress());
                    }
                    if (vote.getVoteCount() != 0) {
                        setVoteCount(vote.getVoteCount());
                    }
                    mergeUnknownFields(vote.unknownFields);
                    onChanged();
                    return this;
                }

                @Override
                public org.tron.protos.contract.WitnessContract.VoteWitnessContract.Vote.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.WitnessContract.VoteWitnessContract.Vote.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.WitnessContract$VoteWitnessContract$Vote$Builder");
                }

                public Builder setVoteAddress(ByteString byteString) {
                    byteString.getClass();
                    this.voteAddress_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearVoteAddress() {
                    this.voteAddress_ = Vote.getDefaultInstance().getVoteAddress();
                    onChanged();
                    return this;
                }

                public Builder setVoteCount(long j) {
                    this.voteCount_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearVoteCount() {
                    this.voteCount_ = 0L;
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

        @Override
        public int getVotesCount() {
            return this.votes_.size();
        }

        @Override
        public Vote getVotes(int i) {
            return this.votes_.get(i);
        }

        @Override
        public VoteOrBuilder getVotesOrBuilder(int i) {
            return this.votes_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            for (int i = 0; i < this.votes_.size(); i++) {
                codedOutputStream.writeMessage(2, this.votes_.get(i));
            }
            boolean z = this.support_;
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
            for (int i2 = 0; i2 < this.votes_.size(); i2++) {
                computeBytesSize += CodedOutputStream.computeMessageSize(2, this.votes_.get(i2));
            }
            boolean z = this.support_;
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
            if (!(obj instanceof VoteWitnessContract)) {
                return super.equals(obj);
            }
            VoteWitnessContract voteWitnessContract = (VoteWitnessContract) obj;
            return getOwnerAddress().equals(voteWitnessContract.getOwnerAddress()) && getVotesList().equals(voteWitnessContract.getVotesList()) && getSupport() == voteWitnessContract.getSupport() && this.unknownFields.equals(voteWitnessContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode();
            if (getVotesCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getVotesList().hashCode();
            }
            int hashBoolean = (((((hashCode * 37) + 3) * 53) + Internal.hashBoolean(getSupport())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashBoolean;
            return hashBoolean;
        }

        public static VoteWitnessContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static VoteWitnessContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static VoteWitnessContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static VoteWitnessContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static VoteWitnessContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static VoteWitnessContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static VoteWitnessContract parseFrom(InputStream inputStream) throws IOException {
            return (VoteWitnessContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static VoteWitnessContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VoteWitnessContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static VoteWitnessContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (VoteWitnessContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static VoteWitnessContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VoteWitnessContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static VoteWitnessContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (VoteWitnessContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static VoteWitnessContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VoteWitnessContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(VoteWitnessContract voteWitnessContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(voteWitnessContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VoteWitnessContractOrBuilder {
            private int bitField0_;
            private ByteString ownerAddress_;
            private boolean support_;
            private RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> votesBuilder_;
            private List<Vote> votes_;

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
                return WitnessContract.internal_static_protocol_VoteWitnessContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return WitnessContract.internal_static_protocol_VoteWitnessContract_fieldAccessorTable.ensureFieldAccessorsInitialized(VoteWitnessContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.votes_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.votes_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (VoteWitnessContract.alwaysUseFieldBuilders) {
                    getVotesFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.votes_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.support_ = false;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return WitnessContract.internal_static_protocol_VoteWitnessContract_descriptor;
            }

            @Override
            public VoteWitnessContract getDefaultInstanceForType() {
                return VoteWitnessContract.getDefaultInstance();
            }

            @Override
            public VoteWitnessContract build() {
                VoteWitnessContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public VoteWitnessContract buildPartial() {
                VoteWitnessContract voteWitnessContract = new VoteWitnessContract(this);
                voteWitnessContract.ownerAddress_ = this.ownerAddress_;
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.votes_ = Collections.unmodifiableList(this.votes_);
                        this.bitField0_ &= -3;
                    }
                    voteWitnessContract.votes_ = this.votes_;
                } else {
                    voteWitnessContract.votes_ = repeatedFieldBuilderV3.build();
                }
                voteWitnessContract.support_ = this.support_;
                voteWitnessContract.bitField0_ = 0;
                onBuilt();
                return voteWitnessContract;
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
                if (message instanceof VoteWitnessContract) {
                    return mergeFrom((VoteWitnessContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(VoteWitnessContract voteWitnessContract) {
                if (voteWitnessContract == VoteWitnessContract.getDefaultInstance()) {
                    return this;
                }
                if (voteWitnessContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(voteWitnessContract.getOwnerAddress());
                }
                if (this.votesBuilder_ == null) {
                    if (!voteWitnessContract.votes_.isEmpty()) {
                        if (this.votes_.isEmpty()) {
                            this.votes_ = voteWitnessContract.votes_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureVotesIsMutable();
                            this.votes_.addAll(voteWitnessContract.votes_);
                        }
                        onChanged();
                    }
                } else if (!voteWitnessContract.votes_.isEmpty()) {
                    if (!this.votesBuilder_.isEmpty()) {
                        this.votesBuilder_.addAllMessages(voteWitnessContract.votes_);
                    } else {
                        this.votesBuilder_.dispose();
                        this.votesBuilder_ = null;
                        this.votes_ = voteWitnessContract.votes_;
                        this.bitField0_ &= -3;
                        this.votesBuilder_ = VoteWitnessContract.alwaysUseFieldBuilders ? getVotesFieldBuilder() : null;
                    }
                }
                if (voteWitnessContract.getSupport()) {
                    setSupport(voteWitnessContract.getSupport());
                }
                mergeUnknownFields(voteWitnessContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.WitnessContract.VoteWitnessContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.WitnessContract.VoteWitnessContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.WitnessContract$VoteWitnessContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = VoteWitnessContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            private void ensureVotesIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.votes_ = new ArrayList(this.votes_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<Vote> getVotesList() {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.votes_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getVotesCount() {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.votes_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public Vote getVotes(int i) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.votes_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setVotes(int i, Vote vote) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    vote.getClass();
                    ensureVotesIsMutable();
                    this.votes_.set(i, vote);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, vote);
                }
                return this;
            }

            public Builder setVotes(int i, Vote.Builder builder) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVotesIsMutable();
                    this.votes_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addVotes(Vote vote) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    vote.getClass();
                    ensureVotesIsMutable();
                    this.votes_.add(vote);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(vote);
                }
                return this;
            }

            public Builder addVotes(int i, Vote vote) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    vote.getClass();
                    ensureVotesIsMutable();
                    this.votes_.add(i, vote);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, vote);
                }
                return this;
            }

            public Builder addVotes(Vote.Builder builder) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVotesIsMutable();
                    this.votes_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addVotes(int i, Vote.Builder builder) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVotesIsMutable();
                    this.votes_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllVotes(Iterable<? extends Vote> iterable) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVotesIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.votes_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearVotes() {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.votes_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeVotes(int i) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureVotesIsMutable();
                    this.votes_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Vote.Builder getVotesBuilder(int i) {
                return getVotesFieldBuilder().getBuilder(i);
            }

            @Override
            public VoteOrBuilder getVotesOrBuilder(int i) {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.votes_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends VoteOrBuilder> getVotesOrBuilderList() {
                RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> repeatedFieldBuilderV3 = this.votesBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.votes_);
            }

            public Vote.Builder addVotesBuilder() {
                return getVotesFieldBuilder().addBuilder(Vote.getDefaultInstance());
            }

            public Vote.Builder addVotesBuilder(int i) {
                return getVotesFieldBuilder().addBuilder(i, Vote.getDefaultInstance());
            }

            public List<Vote.Builder> getVotesBuilderList() {
                return getVotesFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Vote, Vote.Builder, VoteOrBuilder> getVotesFieldBuilder() {
                if (this.votesBuilder_ == null) {
                    this.votesBuilder_ = new RepeatedFieldBuilderV3<>(this.votes_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.votes_ = null;
                }
                return this.votesBuilder_;
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n$core/contract/witness_contract.proto\u0012\bprotocol\";\n\u0015WitnessCreateContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u000b\n\u0003url\u0018\u0002 \u0001(\f\"B\n\u0015WitnessUpdateContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0012\n\nupdate_url\u0018\f \u0001(\f\"¢\u0001\n\u0013VoteWitnessContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u00121\n\u0005votes\u0018\u0002 \u0003(\u000b2\".protocol.VoteWitnessContract.Vote\u0012\u000f\n\u0007support\u0018\u0003 \u0001(\b\u001a0\n\u0004Vote\u0012\u0014\n\fvote_address\u0018\u0001 \u0001(\f\u0012\u0012\n\nvote_count\u0018\u0002 \u0001(\u0003BE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = WitnessContract.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_WitnessCreateContract_descriptor = descriptor2;
        internal_static_protocol_WitnessCreateContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"OwnerAddress", "Url"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_WitnessUpdateContract_descriptor = descriptor3;
        internal_static_protocol_WitnessUpdateContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"OwnerAddress", "UpdateUrl"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_VoteWitnessContract_descriptor = descriptor4;
        internal_static_protocol_VoteWitnessContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"OwnerAddress", "Votes", "Support"});
        Descriptors.Descriptor descriptor5 = descriptor4.getNestedTypes().get(0);
        internal_static_protocol_VoteWitnessContract_Vote_descriptor = descriptor5;
        internal_static_protocol_VoteWitnessContract_Vote_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"VoteAddress", "VoteCount"});
    }
}
