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
public final class ExchangeContract {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_ExchangeCreateContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ExchangeCreateContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ExchangeInjectContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ExchangeInjectContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ExchangeTransactionContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ExchangeTransactionContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ExchangeWithdrawContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ExchangeWithdrawContract_fieldAccessorTable;

    public interface ExchangeCreateContractOrBuilder extends MessageOrBuilder {
        long getFirstTokenBalance();

        ByteString getFirstTokenId();

        ByteString getOwnerAddress();

        long getSecondTokenBalance();

        ByteString getSecondTokenId();
    }

    public interface ExchangeInjectContractOrBuilder extends MessageOrBuilder {
        long getExchangeId();

        ByteString getOwnerAddress();

        long getQuant();

        ByteString getTokenId();
    }

    public interface ExchangeTransactionContractOrBuilder extends MessageOrBuilder {
        long getExchangeId();

        long getExpected();

        ByteString getOwnerAddress();

        long getQuant();

        ByteString getTokenId();
    }

    public interface ExchangeWithdrawContractOrBuilder extends MessageOrBuilder {
        long getExchangeId();

        ByteString getOwnerAddress();

        long getQuant();

        ByteString getTokenId();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ExchangeContract() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class ExchangeCreateContract extends GeneratedMessageV3 implements ExchangeCreateContractOrBuilder {
        public static final int FIRST_TOKEN_BALANCE_FIELD_NUMBER = 3;
        public static final int FIRST_TOKEN_ID_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int SECOND_TOKEN_BALANCE_FIELD_NUMBER = 5;
        public static final int SECOND_TOKEN_ID_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private long firstTokenBalance_;
        private ByteString firstTokenId_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private long secondTokenBalance_;
        private ByteString secondTokenId_;
        private static final ExchangeCreateContract DEFAULT_INSTANCE = new ExchangeCreateContract();
        private static final Parser<ExchangeCreateContract> PARSER = new AbstractParser<ExchangeCreateContract>() {
            @Override
            public ExchangeCreateContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ExchangeCreateContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ExchangeCreateContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExchangeCreateContract> parser() {
            return PARSER;
        }

        @Override
        public ExchangeCreateContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public long getFirstTokenBalance() {
            return this.firstTokenBalance_;
        }

        @Override
        public ByteString getFirstTokenId() {
            return this.firstTokenId_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<ExchangeCreateContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getSecondTokenBalance() {
            return this.secondTokenBalance_;
        }

        @Override
        public ByteString getSecondTokenId() {
            return this.secondTokenId_;
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

        private ExchangeCreateContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ExchangeCreateContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.firstTokenId_ = ByteString.EMPTY;
            this.firstTokenBalance_ = 0L;
            this.secondTokenId_ = ByteString.EMPTY;
            this.secondTokenBalance_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ExchangeCreateContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                } else if (readTag == 18) {
                                    this.firstTokenId_ = codedInputStream.readBytes();
                                } else if (readTag == 24) {
                                    this.firstTokenBalance_ = codedInputStream.readInt64();
                                } else if (readTag == 34) {
                                    this.secondTokenId_ = codedInputStream.readBytes();
                                } else if (readTag != 40) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.secondTokenBalance_ = codedInputStream.readInt64();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ExchangeContract.internal_static_protocol_ExchangeCreateContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ExchangeContract.internal_static_protocol_ExchangeCreateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeCreateContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.firstTokenId_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.firstTokenId_);
            }
            long j = this.firstTokenBalance_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            if (!this.secondTokenId_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.secondTokenId_);
            }
            long j2 = this.secondTokenBalance_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(5, j2);
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
            if (!this.firstTokenId_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.firstTokenId_);
            }
            long j = this.firstTokenBalance_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(3, j);
            }
            if (!this.secondTokenId_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(4, this.secondTokenId_);
            }
            long j2 = this.secondTokenBalance_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(5, j2);
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
            if (!(obj instanceof ExchangeCreateContract)) {
                return super.equals(obj);
            }
            ExchangeCreateContract exchangeCreateContract = (ExchangeCreateContract) obj;
            return getOwnerAddress().equals(exchangeCreateContract.getOwnerAddress()) && getFirstTokenId().equals(exchangeCreateContract.getFirstTokenId()) && getFirstTokenBalance() == exchangeCreateContract.getFirstTokenBalance() && getSecondTokenId().equals(exchangeCreateContract.getSecondTokenId()) && getSecondTokenBalance() == exchangeCreateContract.getSecondTokenBalance() && this.unknownFields.equals(exchangeCreateContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getFirstTokenId().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getFirstTokenBalance())) * 37) + 4) * 53) + getSecondTokenId().hashCode()) * 37) + 5) * 53) + Internal.hashLong(getSecondTokenBalance())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ExchangeCreateContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ExchangeCreateContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ExchangeCreateContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ExchangeCreateContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ExchangeCreateContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ExchangeCreateContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ExchangeCreateContract parseFrom(InputStream inputStream) throws IOException {
            return (ExchangeCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ExchangeCreateContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExchangeCreateContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExchangeCreateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ExchangeCreateContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeCreateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExchangeCreateContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExchangeCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ExchangeCreateContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExchangeCreateContract exchangeCreateContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(exchangeCreateContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExchangeCreateContractOrBuilder {
            private long firstTokenBalance_;
            private ByteString firstTokenId_;
            private ByteString ownerAddress_;
            private long secondTokenBalance_;
            private ByteString secondTokenId_;

            @Override
            public long getFirstTokenBalance() {
                return this.firstTokenBalance_;
            }

            @Override
            public ByteString getFirstTokenId() {
                return this.firstTokenId_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getSecondTokenBalance() {
                return this.secondTokenBalance_;
            }

            @Override
            public ByteString getSecondTokenId() {
                return this.secondTokenId_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ExchangeContract.internal_static_protocol_ExchangeCreateContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ExchangeContract.internal_static_protocol_ExchangeCreateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeCreateContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.firstTokenId_ = ByteString.EMPTY;
                this.secondTokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.firstTokenId_ = ByteString.EMPTY;
                this.secondTokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ExchangeCreateContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.firstTokenId_ = ByteString.EMPTY;
                this.firstTokenBalance_ = 0L;
                this.secondTokenId_ = ByteString.EMPTY;
                this.secondTokenBalance_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ExchangeContract.internal_static_protocol_ExchangeCreateContract_descriptor;
            }

            @Override
            public ExchangeCreateContract getDefaultInstanceForType() {
                return ExchangeCreateContract.getDefaultInstance();
            }

            @Override
            public ExchangeCreateContract build() {
                ExchangeCreateContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ExchangeCreateContract buildPartial() {
                ExchangeCreateContract exchangeCreateContract = new ExchangeCreateContract(this);
                exchangeCreateContract.ownerAddress_ = this.ownerAddress_;
                exchangeCreateContract.firstTokenId_ = this.firstTokenId_;
                exchangeCreateContract.firstTokenBalance_ = this.firstTokenBalance_;
                exchangeCreateContract.secondTokenId_ = this.secondTokenId_;
                exchangeCreateContract.secondTokenBalance_ = this.secondTokenBalance_;
                onBuilt();
                return exchangeCreateContract;
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
                if (message instanceof ExchangeCreateContract) {
                    return mergeFrom((ExchangeCreateContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ExchangeCreateContract exchangeCreateContract) {
                if (exchangeCreateContract == ExchangeCreateContract.getDefaultInstance()) {
                    return this;
                }
                if (exchangeCreateContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(exchangeCreateContract.getOwnerAddress());
                }
                if (exchangeCreateContract.getFirstTokenId() != ByteString.EMPTY) {
                    setFirstTokenId(exchangeCreateContract.getFirstTokenId());
                }
                if (exchangeCreateContract.getFirstTokenBalance() != 0) {
                    setFirstTokenBalance(exchangeCreateContract.getFirstTokenBalance());
                }
                if (exchangeCreateContract.getSecondTokenId() != ByteString.EMPTY) {
                    setSecondTokenId(exchangeCreateContract.getSecondTokenId());
                }
                if (exchangeCreateContract.getSecondTokenBalance() != 0) {
                    setSecondTokenBalance(exchangeCreateContract.getSecondTokenBalance());
                }
                mergeUnknownFields(exchangeCreateContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ExchangeContract.ExchangeCreateContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ExchangeContract.ExchangeCreateContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ExchangeContract$ExchangeCreateContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = ExchangeCreateContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setFirstTokenId(ByteString byteString) {
                byteString.getClass();
                this.firstTokenId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearFirstTokenId() {
                this.firstTokenId_ = ExchangeCreateContract.getDefaultInstance().getFirstTokenId();
                onChanged();
                return this;
            }

            public Builder setFirstTokenBalance(long j) {
                this.firstTokenBalance_ = j;
                onChanged();
                return this;
            }

            public Builder clearFirstTokenBalance() {
                this.firstTokenBalance_ = 0L;
                onChanged();
                return this;
            }

            public Builder setSecondTokenId(ByteString byteString) {
                byteString.getClass();
                this.secondTokenId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearSecondTokenId() {
                this.secondTokenId_ = ExchangeCreateContract.getDefaultInstance().getSecondTokenId();
                onChanged();
                return this;
            }

            public Builder setSecondTokenBalance(long j) {
                this.secondTokenBalance_ = j;
                onChanged();
                return this;
            }

            public Builder clearSecondTokenBalance() {
                this.secondTokenBalance_ = 0L;
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

    public static final class ExchangeInjectContract extends GeneratedMessageV3 implements ExchangeInjectContractOrBuilder {
        public static final int EXCHANGE_ID_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int QUANT_FIELD_NUMBER = 4;
        public static final int TOKEN_ID_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private long exchangeId_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private long quant_;
        private ByteString tokenId_;
        private static final ExchangeInjectContract DEFAULT_INSTANCE = new ExchangeInjectContract();
        private static final Parser<ExchangeInjectContract> PARSER = new AbstractParser<ExchangeInjectContract>() {
            @Override
            public ExchangeInjectContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ExchangeInjectContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ExchangeInjectContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExchangeInjectContract> parser() {
            return PARSER;
        }

        @Override
        public ExchangeInjectContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public long getExchangeId() {
            return this.exchangeId_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<ExchangeInjectContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getQuant() {
            return this.quant_;
        }

        @Override
        public ByteString getTokenId() {
            return this.tokenId_;
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

        private ExchangeInjectContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ExchangeInjectContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.exchangeId_ = 0L;
            this.tokenId_ = ByteString.EMPTY;
            this.quant_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ExchangeInjectContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.exchangeId_ = codedInputStream.readInt64();
                            } else if (readTag == 26) {
                                this.tokenId_ = codedInputStream.readBytes();
                            } else if (readTag != 32) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.quant_ = codedInputStream.readInt64();
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
            return ExchangeContract.internal_static_protocol_ExchangeInjectContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ExchangeContract.internal_static_protocol_ExchangeInjectContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeInjectContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.exchangeId_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            if (!this.tokenId_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.tokenId_);
            }
            long j2 = this.quant_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(4, j2);
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
            long j = this.exchangeId_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            if (!this.tokenId_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.tokenId_);
            }
            long j2 = this.quant_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(4, j2);
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
            if (!(obj instanceof ExchangeInjectContract)) {
                return super.equals(obj);
            }
            ExchangeInjectContract exchangeInjectContract = (ExchangeInjectContract) obj;
            return getOwnerAddress().equals(exchangeInjectContract.getOwnerAddress()) && getExchangeId() == exchangeInjectContract.getExchangeId() && getTokenId().equals(exchangeInjectContract.getTokenId()) && getQuant() == exchangeInjectContract.getQuant() && this.unknownFields.equals(exchangeInjectContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getExchangeId())) * 37) + 3) * 53) + getTokenId().hashCode()) * 37) + 4) * 53) + Internal.hashLong(getQuant())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ExchangeInjectContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ExchangeInjectContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ExchangeInjectContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ExchangeInjectContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ExchangeInjectContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ExchangeInjectContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ExchangeInjectContract parseFrom(InputStream inputStream) throws IOException {
            return (ExchangeInjectContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ExchangeInjectContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeInjectContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExchangeInjectContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExchangeInjectContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ExchangeInjectContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeInjectContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExchangeInjectContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExchangeInjectContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ExchangeInjectContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeInjectContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExchangeInjectContract exchangeInjectContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(exchangeInjectContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExchangeInjectContractOrBuilder {
            private long exchangeId_;
            private ByteString ownerAddress_;
            private long quant_;
            private ByteString tokenId_;

            @Override
            public long getExchangeId() {
                return this.exchangeId_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getQuant() {
                return this.quant_;
            }

            @Override
            public ByteString getTokenId() {
                return this.tokenId_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ExchangeContract.internal_static_protocol_ExchangeInjectContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ExchangeContract.internal_static_protocol_ExchangeInjectContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeInjectContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.tokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.tokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ExchangeInjectContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.exchangeId_ = 0L;
                this.tokenId_ = ByteString.EMPTY;
                this.quant_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ExchangeContract.internal_static_protocol_ExchangeInjectContract_descriptor;
            }

            @Override
            public ExchangeInjectContract getDefaultInstanceForType() {
                return ExchangeInjectContract.getDefaultInstance();
            }

            @Override
            public ExchangeInjectContract build() {
                ExchangeInjectContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ExchangeInjectContract buildPartial() {
                ExchangeInjectContract exchangeInjectContract = new ExchangeInjectContract(this);
                exchangeInjectContract.ownerAddress_ = this.ownerAddress_;
                exchangeInjectContract.exchangeId_ = this.exchangeId_;
                exchangeInjectContract.tokenId_ = this.tokenId_;
                exchangeInjectContract.quant_ = this.quant_;
                onBuilt();
                return exchangeInjectContract;
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
                if (message instanceof ExchangeInjectContract) {
                    return mergeFrom((ExchangeInjectContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ExchangeInjectContract exchangeInjectContract) {
                if (exchangeInjectContract == ExchangeInjectContract.getDefaultInstance()) {
                    return this;
                }
                if (exchangeInjectContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(exchangeInjectContract.getOwnerAddress());
                }
                if (exchangeInjectContract.getExchangeId() != 0) {
                    setExchangeId(exchangeInjectContract.getExchangeId());
                }
                if (exchangeInjectContract.getTokenId() != ByteString.EMPTY) {
                    setTokenId(exchangeInjectContract.getTokenId());
                }
                if (exchangeInjectContract.getQuant() != 0) {
                    setQuant(exchangeInjectContract.getQuant());
                }
                mergeUnknownFields(exchangeInjectContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ExchangeContract.ExchangeInjectContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ExchangeContract.ExchangeInjectContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ExchangeContract$ExchangeInjectContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = ExchangeInjectContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setExchangeId(long j) {
                this.exchangeId_ = j;
                onChanged();
                return this;
            }

            public Builder clearExchangeId() {
                this.exchangeId_ = 0L;
                onChanged();
                return this;
            }

            public Builder setTokenId(ByteString byteString) {
                byteString.getClass();
                this.tokenId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearTokenId() {
                this.tokenId_ = ExchangeInjectContract.getDefaultInstance().getTokenId();
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

    public static final class ExchangeWithdrawContract extends GeneratedMessageV3 implements ExchangeWithdrawContractOrBuilder {
        public static final int EXCHANGE_ID_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int QUANT_FIELD_NUMBER = 4;
        public static final int TOKEN_ID_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private long exchangeId_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private long quant_;
        private ByteString tokenId_;
        private static final ExchangeWithdrawContract DEFAULT_INSTANCE = new ExchangeWithdrawContract();
        private static final Parser<ExchangeWithdrawContract> PARSER = new AbstractParser<ExchangeWithdrawContract>() {
            @Override
            public ExchangeWithdrawContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ExchangeWithdrawContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ExchangeWithdrawContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExchangeWithdrawContract> parser() {
            return PARSER;
        }

        @Override
        public ExchangeWithdrawContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public long getExchangeId() {
            return this.exchangeId_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<ExchangeWithdrawContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getQuant() {
            return this.quant_;
        }

        @Override
        public ByteString getTokenId() {
            return this.tokenId_;
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

        private ExchangeWithdrawContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ExchangeWithdrawContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.exchangeId_ = 0L;
            this.tokenId_ = ByteString.EMPTY;
            this.quant_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ExchangeWithdrawContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.exchangeId_ = codedInputStream.readInt64();
                            } else if (readTag == 26) {
                                this.tokenId_ = codedInputStream.readBytes();
                            } else if (readTag != 32) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.quant_ = codedInputStream.readInt64();
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
            return ExchangeContract.internal_static_protocol_ExchangeWithdrawContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ExchangeContract.internal_static_protocol_ExchangeWithdrawContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeWithdrawContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.exchangeId_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            if (!this.tokenId_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.tokenId_);
            }
            long j2 = this.quant_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(4, j2);
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
            long j = this.exchangeId_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            if (!this.tokenId_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.tokenId_);
            }
            long j2 = this.quant_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(4, j2);
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
            if (!(obj instanceof ExchangeWithdrawContract)) {
                return super.equals(obj);
            }
            ExchangeWithdrawContract exchangeWithdrawContract = (ExchangeWithdrawContract) obj;
            return getOwnerAddress().equals(exchangeWithdrawContract.getOwnerAddress()) && getExchangeId() == exchangeWithdrawContract.getExchangeId() && getTokenId().equals(exchangeWithdrawContract.getTokenId()) && getQuant() == exchangeWithdrawContract.getQuant() && this.unknownFields.equals(exchangeWithdrawContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getExchangeId())) * 37) + 3) * 53) + getTokenId().hashCode()) * 37) + 4) * 53) + Internal.hashLong(getQuant())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ExchangeWithdrawContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ExchangeWithdrawContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ExchangeWithdrawContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ExchangeWithdrawContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ExchangeWithdrawContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ExchangeWithdrawContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ExchangeWithdrawContract parseFrom(InputStream inputStream) throws IOException {
            return (ExchangeWithdrawContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ExchangeWithdrawContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeWithdrawContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExchangeWithdrawContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExchangeWithdrawContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ExchangeWithdrawContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeWithdrawContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExchangeWithdrawContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExchangeWithdrawContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ExchangeWithdrawContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeWithdrawContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExchangeWithdrawContract exchangeWithdrawContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(exchangeWithdrawContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExchangeWithdrawContractOrBuilder {
            private long exchangeId_;
            private ByteString ownerAddress_;
            private long quant_;
            private ByteString tokenId_;

            @Override
            public long getExchangeId() {
                return this.exchangeId_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getQuant() {
                return this.quant_;
            }

            @Override
            public ByteString getTokenId() {
                return this.tokenId_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ExchangeContract.internal_static_protocol_ExchangeWithdrawContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ExchangeContract.internal_static_protocol_ExchangeWithdrawContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeWithdrawContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.tokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.tokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ExchangeWithdrawContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.exchangeId_ = 0L;
                this.tokenId_ = ByteString.EMPTY;
                this.quant_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ExchangeContract.internal_static_protocol_ExchangeWithdrawContract_descriptor;
            }

            @Override
            public ExchangeWithdrawContract getDefaultInstanceForType() {
                return ExchangeWithdrawContract.getDefaultInstance();
            }

            @Override
            public ExchangeWithdrawContract build() {
                ExchangeWithdrawContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ExchangeWithdrawContract buildPartial() {
                ExchangeWithdrawContract exchangeWithdrawContract = new ExchangeWithdrawContract(this);
                exchangeWithdrawContract.ownerAddress_ = this.ownerAddress_;
                exchangeWithdrawContract.exchangeId_ = this.exchangeId_;
                exchangeWithdrawContract.tokenId_ = this.tokenId_;
                exchangeWithdrawContract.quant_ = this.quant_;
                onBuilt();
                return exchangeWithdrawContract;
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
                if (message instanceof ExchangeWithdrawContract) {
                    return mergeFrom((ExchangeWithdrawContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ExchangeWithdrawContract exchangeWithdrawContract) {
                if (exchangeWithdrawContract == ExchangeWithdrawContract.getDefaultInstance()) {
                    return this;
                }
                if (exchangeWithdrawContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(exchangeWithdrawContract.getOwnerAddress());
                }
                if (exchangeWithdrawContract.getExchangeId() != 0) {
                    setExchangeId(exchangeWithdrawContract.getExchangeId());
                }
                if (exchangeWithdrawContract.getTokenId() != ByteString.EMPTY) {
                    setTokenId(exchangeWithdrawContract.getTokenId());
                }
                if (exchangeWithdrawContract.getQuant() != 0) {
                    setQuant(exchangeWithdrawContract.getQuant());
                }
                mergeUnknownFields(exchangeWithdrawContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ExchangeContract.ExchangeWithdrawContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ExchangeContract.ExchangeWithdrawContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ExchangeContract$ExchangeWithdrawContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = ExchangeWithdrawContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setExchangeId(long j) {
                this.exchangeId_ = j;
                onChanged();
                return this;
            }

            public Builder clearExchangeId() {
                this.exchangeId_ = 0L;
                onChanged();
                return this;
            }

            public Builder setTokenId(ByteString byteString) {
                byteString.getClass();
                this.tokenId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearTokenId() {
                this.tokenId_ = ExchangeWithdrawContract.getDefaultInstance().getTokenId();
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

    public static final class ExchangeTransactionContract extends GeneratedMessageV3 implements ExchangeTransactionContractOrBuilder {
        public static final int EXCHANGE_ID_FIELD_NUMBER = 2;
        public static final int EXPECTED_FIELD_NUMBER = 5;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int QUANT_FIELD_NUMBER = 4;
        public static final int TOKEN_ID_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private long exchangeId_;
        private long expected_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private long quant_;
        private ByteString tokenId_;
        private static final ExchangeTransactionContract DEFAULT_INSTANCE = new ExchangeTransactionContract();
        private static final Parser<ExchangeTransactionContract> PARSER = new AbstractParser<ExchangeTransactionContract>() {
            @Override
            public ExchangeTransactionContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ExchangeTransactionContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ExchangeTransactionContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExchangeTransactionContract> parser() {
            return PARSER;
        }

        @Override
        public ExchangeTransactionContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public long getExchangeId() {
            return this.exchangeId_;
        }

        @Override
        public long getExpected() {
            return this.expected_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<ExchangeTransactionContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getQuant() {
            return this.quant_;
        }

        @Override
        public ByteString getTokenId() {
            return this.tokenId_;
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

        private ExchangeTransactionContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ExchangeTransactionContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.exchangeId_ = 0L;
            this.tokenId_ = ByteString.EMPTY;
            this.quant_ = 0L;
            this.expected_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ExchangeTransactionContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                } else if (readTag == 16) {
                                    this.exchangeId_ = codedInputStream.readInt64();
                                } else if (readTag == 26) {
                                    this.tokenId_ = codedInputStream.readBytes();
                                } else if (readTag == 32) {
                                    this.quant_ = codedInputStream.readInt64();
                                } else if (readTag != 40) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.expected_ = codedInputStream.readInt64();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ExchangeContract.internal_static_protocol_ExchangeTransactionContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ExchangeContract.internal_static_protocol_ExchangeTransactionContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeTransactionContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.exchangeId_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            if (!this.tokenId_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.tokenId_);
            }
            long j2 = this.quant_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(4, j2);
            }
            long j3 = this.expected_;
            if (j3 != 0) {
                codedOutputStream.writeInt64(5, j3);
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
            long j = this.exchangeId_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            if (!this.tokenId_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.tokenId_);
            }
            long j2 = this.quant_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(4, j2);
            }
            long j3 = this.expected_;
            if (j3 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(5, j3);
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
            if (!(obj instanceof ExchangeTransactionContract)) {
                return super.equals(obj);
            }
            ExchangeTransactionContract exchangeTransactionContract = (ExchangeTransactionContract) obj;
            return getOwnerAddress().equals(exchangeTransactionContract.getOwnerAddress()) && getExchangeId() == exchangeTransactionContract.getExchangeId() && getTokenId().equals(exchangeTransactionContract.getTokenId()) && getQuant() == exchangeTransactionContract.getQuant() && getExpected() == exchangeTransactionContract.getExpected() && this.unknownFields.equals(exchangeTransactionContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getExchangeId())) * 37) + 3) * 53) + getTokenId().hashCode()) * 37) + 4) * 53) + Internal.hashLong(getQuant())) * 37) + 5) * 53) + Internal.hashLong(getExpected())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ExchangeTransactionContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ExchangeTransactionContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ExchangeTransactionContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ExchangeTransactionContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ExchangeTransactionContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ExchangeTransactionContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ExchangeTransactionContract parseFrom(InputStream inputStream) throws IOException {
            return (ExchangeTransactionContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ExchangeTransactionContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeTransactionContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExchangeTransactionContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExchangeTransactionContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ExchangeTransactionContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeTransactionContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ExchangeTransactionContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExchangeTransactionContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ExchangeTransactionContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeTransactionContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExchangeTransactionContract exchangeTransactionContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(exchangeTransactionContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExchangeTransactionContractOrBuilder {
            private long exchangeId_;
            private long expected_;
            private ByteString ownerAddress_;
            private long quant_;
            private ByteString tokenId_;

            @Override
            public long getExchangeId() {
                return this.exchangeId_;
            }

            @Override
            public long getExpected() {
                return this.expected_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getQuant() {
                return this.quant_;
            }

            @Override
            public ByteString getTokenId() {
                return this.tokenId_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ExchangeContract.internal_static_protocol_ExchangeTransactionContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ExchangeContract.internal_static_protocol_ExchangeTransactionContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeTransactionContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.tokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.tokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ExchangeTransactionContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.exchangeId_ = 0L;
                this.tokenId_ = ByteString.EMPTY;
                this.quant_ = 0L;
                this.expected_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ExchangeContract.internal_static_protocol_ExchangeTransactionContract_descriptor;
            }

            @Override
            public ExchangeTransactionContract getDefaultInstanceForType() {
                return ExchangeTransactionContract.getDefaultInstance();
            }

            @Override
            public ExchangeTransactionContract build() {
                ExchangeTransactionContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ExchangeTransactionContract buildPartial() {
                ExchangeTransactionContract exchangeTransactionContract = new ExchangeTransactionContract(this);
                exchangeTransactionContract.ownerAddress_ = this.ownerAddress_;
                exchangeTransactionContract.exchangeId_ = this.exchangeId_;
                exchangeTransactionContract.tokenId_ = this.tokenId_;
                exchangeTransactionContract.quant_ = this.quant_;
                exchangeTransactionContract.expected_ = this.expected_;
                onBuilt();
                return exchangeTransactionContract;
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
                if (message instanceof ExchangeTransactionContract) {
                    return mergeFrom((ExchangeTransactionContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ExchangeTransactionContract exchangeTransactionContract) {
                if (exchangeTransactionContract == ExchangeTransactionContract.getDefaultInstance()) {
                    return this;
                }
                if (exchangeTransactionContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(exchangeTransactionContract.getOwnerAddress());
                }
                if (exchangeTransactionContract.getExchangeId() != 0) {
                    setExchangeId(exchangeTransactionContract.getExchangeId());
                }
                if (exchangeTransactionContract.getTokenId() != ByteString.EMPTY) {
                    setTokenId(exchangeTransactionContract.getTokenId());
                }
                if (exchangeTransactionContract.getQuant() != 0) {
                    setQuant(exchangeTransactionContract.getQuant());
                }
                if (exchangeTransactionContract.getExpected() != 0) {
                    setExpected(exchangeTransactionContract.getExpected());
                }
                mergeUnknownFields(exchangeTransactionContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.ExchangeContract.ExchangeTransactionContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.ExchangeContract.ExchangeTransactionContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.ExchangeContract$ExchangeTransactionContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = ExchangeTransactionContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setExchangeId(long j) {
                this.exchangeId_ = j;
                onChanged();
                return this;
            }

            public Builder clearExchangeId() {
                this.exchangeId_ = 0L;
                onChanged();
                return this;
            }

            public Builder setTokenId(ByteString byteString) {
                byteString.getClass();
                this.tokenId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearTokenId() {
                this.tokenId_ = ExchangeTransactionContract.getDefaultInstance().getTokenId();
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

            public Builder setExpected(long j) {
                this.expected_ = j;
                onChanged();
                return this;
            }

            public Builder clearExpected() {
                this.expected_ = 0L;
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%core/contract/exchange_contract.proto\u0012\bprotocol\"\u009b\u0001\n\u0016ExchangeCreateContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0016\n\u000efirst_token_id\u0018\u0002 \u0001(\f\u0012\u001b\n\u0013first_token_balance\u0018\u0003 \u0001(\u0003\u0012\u0017\n\u000fsecond_token_id\u0018\u0004 \u0001(\f\u0012\u001c\n\u0014second_token_balance\u0018\u0005 \u0001(\u0003\"e\n\u0016ExchangeInjectContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0013\n\u000bexchange_id\u0018\u0002 \u0001(\u0003\u0012\u0010\n\btoken_id\u0018\u0003 \u0001(\f\u0012\r\n\u0005quant\u0018\u0004 \u0001(\u0003\"g\n\u0018ExchangeWithdrawContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0013\n\u000bexchange_id\u0018\u0002 \u0001(\u0003\u0012\u0010\n\btoken_id\u0018\u0003 \u0001(\f\u0012\r\n\u0005quant\u0018\u0004 \u0001(\u0003\"|\n\u001bExchangeTransactionContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0013\n\u000bexchange_id\u0018\u0002 \u0001(\u0003\u0012\u0010\n\btoken_id\u0018\u0003 \u0001(\f\u0012\r\n\u0005quant\u0018\u0004 \u0001(\u0003\u0012\u0010\n\bexpected\u0018\u0005 \u0001(\u0003BE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ExchangeContract.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_ExchangeCreateContract_descriptor = descriptor2;
        internal_static_protocol_ExchangeCreateContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"OwnerAddress", "FirstTokenId", "FirstTokenBalance", "SecondTokenId", "SecondTokenBalance"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_ExchangeInjectContract_descriptor = descriptor3;
        internal_static_protocol_ExchangeInjectContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"OwnerAddress", "ExchangeId", "TokenId", "Quant"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_ExchangeWithdrawContract_descriptor = descriptor4;
        internal_static_protocol_ExchangeWithdrawContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"OwnerAddress", "ExchangeId", "TokenId", "Quant"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_protocol_ExchangeTransactionContract_descriptor = descriptor5;
        internal_static_protocol_ExchangeTransactionContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"OwnerAddress", "ExchangeId", "TokenId", "Quant", "Expected"});
    }
}
