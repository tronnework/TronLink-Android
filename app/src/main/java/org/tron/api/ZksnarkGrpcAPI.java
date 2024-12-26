package org.tron.api;

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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.tron.protos.Protocol;
public final class ZksnarkGrpcAPI {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_ZksnarkRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ZksnarkRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ZksnarkResponse_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ZksnarkResponse_fieldAccessorTable;

    public interface ZksnarkRequestOrBuilder extends MessageOrBuilder {
        ByteString getSighash();

        Protocol.Transaction getTransaction();

        Protocol.TransactionOrBuilder getTransactionOrBuilder();

        String getTxId();

        ByteString getTxIdBytes();

        long getValueBalance();

        boolean hasTransaction();
    }

    public interface ZksnarkResponseOrBuilder extends MessageOrBuilder {
        ZksnarkResponse.Code getCode();

        int getCodeValue();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ZksnarkGrpcAPI() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class ZksnarkRequest extends GeneratedMessageV3 implements ZksnarkRequestOrBuilder {
        private static final ZksnarkRequest DEFAULT_INSTANCE = new ZksnarkRequest();
        private static final Parser<ZksnarkRequest> PARSER = new AbstractParser<ZksnarkRequest>() {
            @Override
            public ZksnarkRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ZksnarkRequest(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SIGHASH_FIELD_NUMBER = 2;
        public static final int TRANSACTION_FIELD_NUMBER = 1;
        public static final int TXID_FIELD_NUMBER = 4;
        public static final int VALUEBALANCE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString sighash_;
        private Protocol.Transaction transaction_;
        private volatile Object txId_;
        private long valueBalance_;

        public static ZksnarkRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ZksnarkRequest> parser() {
            return PARSER;
        }

        @Override
        public ZksnarkRequest getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<ZksnarkRequest> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getSighash() {
            return this.sighash_;
        }

        @Override
        public long getValueBalance() {
            return this.valueBalance_;
        }

        @Override
        public boolean hasTransaction() {
            return this.transaction_ != null;
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

        private ZksnarkRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ZksnarkRequest() {
            this.memoizedIsInitialized = (byte) -1;
            this.sighash_ = ByteString.EMPTY;
            this.valueBalance_ = 0L;
            this.txId_ = "";
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ZksnarkRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    Protocol.Transaction transaction = this.transaction_;
                                    Protocol.Transaction.Builder builder = transaction != null ? transaction.toBuilder() : null;
                                    Protocol.Transaction transaction2 = (Protocol.Transaction) codedInputStream.readMessage(Protocol.Transaction.parser(), extensionRegistryLite);
                                    this.transaction_ = transaction2;
                                    if (builder != null) {
                                        builder.mergeFrom(transaction2);
                                        this.transaction_ = builder.buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    this.sighash_ = codedInputStream.readBytes();
                                } else if (readTag == 24) {
                                    this.valueBalance_ = codedInputStream.readInt64();
                                } else if (readTag != 34) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.txId_ = codedInputStream.readStringRequireUtf8();
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
            return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ZksnarkRequest.class, Builder.class);
        }

        @Override
        public Protocol.Transaction getTransaction() {
            Protocol.Transaction transaction = this.transaction_;
            return transaction == null ? Protocol.Transaction.getDefaultInstance() : transaction;
        }

        @Override
        public Protocol.TransactionOrBuilder getTransactionOrBuilder() {
            return getTransaction();
        }

        @Override
        public String getTxId() {
            Object obj = this.txId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.txId_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getTxIdBytes() {
            Object obj = this.txId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.txId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.transaction_ != null) {
                codedOutputStream.writeMessage(1, getTransaction());
            }
            if (!this.sighash_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.sighash_);
            }
            long j = this.valueBalance_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            if (!getTxIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.txId_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.transaction_ != null ? CodedOutputStream.computeMessageSize(1, getTransaction()) : 0;
            if (!this.sighash_.isEmpty()) {
                computeMessageSize += CodedOutputStream.computeBytesSize(2, this.sighash_);
            }
            long j = this.valueBalance_;
            if (j != 0) {
                computeMessageSize += CodedOutputStream.computeInt64Size(3, j);
            }
            if (!getTxIdBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(4, this.txId_);
            }
            int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ZksnarkRequest)) {
                return super.equals(obj);
            }
            ZksnarkRequest zksnarkRequest = (ZksnarkRequest) obj;
            boolean z = hasTransaction() == zksnarkRequest.hasTransaction();
            if (!hasTransaction() ? z : !(!z || !getTransaction().equals(zksnarkRequest.getTransaction()))) {
                if (getSighash().equals(zksnarkRequest.getSighash()) && getValueBalance() == zksnarkRequest.getValueBalance() && getTxId().equals(zksnarkRequest.getTxId()) && this.unknownFields.equals(zksnarkRequest.unknownFields)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasTransaction()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getTransaction().hashCode();
            }
            int hashCode2 = (((((((((((((hashCode * 37) + 2) * 53) + getSighash().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getValueBalance())) * 37) + 4) * 53) + getTxId().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static ZksnarkRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ZksnarkRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ZksnarkRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ZksnarkRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ZksnarkRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ZksnarkRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ZksnarkRequest parseFrom(InputStream inputStream) throws IOException {
            return (ZksnarkRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ZksnarkRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ZksnarkRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ZksnarkRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ZksnarkRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ZksnarkRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ZksnarkRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ZksnarkRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ZksnarkRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ZksnarkRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ZksnarkRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ZksnarkRequest zksnarkRequest) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(zksnarkRequest);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ZksnarkRequestOrBuilder {
            private ByteString sighash_;
            private SingleFieldBuilderV3<Protocol.Transaction, Protocol.Transaction.Builder, Protocol.TransactionOrBuilder> transactionBuilder_;
            private Protocol.Transaction transaction_;
            private Object txId_;
            private long valueBalance_;

            @Override
            public ByteString getSighash() {
                return this.sighash_;
            }

            @Override
            public long getValueBalance() {
                return this.valueBalance_;
            }

            @Override
            public boolean hasTransaction() {
                return (this.transactionBuilder_ == null && this.transaction_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkRequest_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ZksnarkRequest.class, Builder.class);
            }

            private Builder() {
                this.transaction_ = null;
                this.sighash_ = ByteString.EMPTY;
                this.txId_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.transaction_ = null;
                this.sighash_ = ByteString.EMPTY;
                this.txId_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ZksnarkRequest.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.transactionBuilder_ == null) {
                    this.transaction_ = null;
                } else {
                    this.transaction_ = null;
                    this.transactionBuilder_ = null;
                }
                this.sighash_ = ByteString.EMPTY;
                this.valueBalance_ = 0L;
                this.txId_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkRequest_descriptor;
            }

            @Override
            public ZksnarkRequest getDefaultInstanceForType() {
                return ZksnarkRequest.getDefaultInstance();
            }

            @Override
            public ZksnarkRequest build() {
                ZksnarkRequest buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ZksnarkRequest buildPartial() {
                ZksnarkRequest zksnarkRequest = new ZksnarkRequest(this);
                SingleFieldBuilderV3<Protocol.Transaction, Protocol.Transaction.Builder, Protocol.TransactionOrBuilder> singleFieldBuilderV3 = this.transactionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    zksnarkRequest.transaction_ = this.transaction_;
                } else {
                    zksnarkRequest.transaction_ = singleFieldBuilderV3.build();
                }
                zksnarkRequest.sighash_ = this.sighash_;
                zksnarkRequest.valueBalance_ = this.valueBalance_;
                zksnarkRequest.txId_ = this.txId_;
                onBuilt();
                return zksnarkRequest;
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
                if (message instanceof ZksnarkRequest) {
                    return mergeFrom((ZksnarkRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ZksnarkRequest zksnarkRequest) {
                if (zksnarkRequest == ZksnarkRequest.getDefaultInstance()) {
                    return this;
                }
                if (zksnarkRequest.hasTransaction()) {
                    mergeTransaction(zksnarkRequest.getTransaction());
                }
                if (zksnarkRequest.getSighash() != ByteString.EMPTY) {
                    setSighash(zksnarkRequest.getSighash());
                }
                if (zksnarkRequest.getValueBalance() != 0) {
                    setValueBalance(zksnarkRequest.getValueBalance());
                }
                if (!zksnarkRequest.getTxId().isEmpty()) {
                    this.txId_ = zksnarkRequest.txId_;
                    onChanged();
                }
                mergeUnknownFields(zksnarkRequest.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.api.ZksnarkGrpcAPI.ZksnarkRequest.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.api.ZksnarkGrpcAPI.ZksnarkRequest.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.api.ZksnarkGrpcAPI$ZksnarkRequest$Builder");
            }

            @Override
            public Protocol.Transaction getTransaction() {
                SingleFieldBuilderV3<Protocol.Transaction, Protocol.Transaction.Builder, Protocol.TransactionOrBuilder> singleFieldBuilderV3 = this.transactionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Protocol.Transaction transaction = this.transaction_;
                    return transaction == null ? Protocol.Transaction.getDefaultInstance() : transaction;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setTransaction(Protocol.Transaction transaction) {
                SingleFieldBuilderV3<Protocol.Transaction, Protocol.Transaction.Builder, Protocol.TransactionOrBuilder> singleFieldBuilderV3 = this.transactionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    transaction.getClass();
                    this.transaction_ = transaction;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(transaction);
                }
                return this;
            }

            public Builder setTransaction(Protocol.Transaction.Builder builder) {
                SingleFieldBuilderV3<Protocol.Transaction, Protocol.Transaction.Builder, Protocol.TransactionOrBuilder> singleFieldBuilderV3 = this.transactionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.transaction_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTransaction(Protocol.Transaction transaction) {
                SingleFieldBuilderV3<Protocol.Transaction, Protocol.Transaction.Builder, Protocol.TransactionOrBuilder> singleFieldBuilderV3 = this.transactionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Protocol.Transaction transaction2 = this.transaction_;
                    if (transaction2 != null) {
                        this.transaction_ = Protocol.Transaction.newBuilder(transaction2).mergeFrom(transaction).buildPartial();
                    } else {
                        this.transaction_ = transaction;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(transaction);
                }
                return this;
            }

            public Builder clearTransaction() {
                if (this.transactionBuilder_ == null) {
                    this.transaction_ = null;
                    onChanged();
                } else {
                    this.transaction_ = null;
                    this.transactionBuilder_ = null;
                }
                return this;
            }

            public Protocol.Transaction.Builder getTransactionBuilder() {
                onChanged();
                return getTransactionFieldBuilder().getBuilder();
            }

            @Override
            public Protocol.TransactionOrBuilder getTransactionOrBuilder() {
                SingleFieldBuilderV3<Protocol.Transaction, Protocol.Transaction.Builder, Protocol.TransactionOrBuilder> singleFieldBuilderV3 = this.transactionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Protocol.Transaction transaction = this.transaction_;
                return transaction == null ? Protocol.Transaction.getDefaultInstance() : transaction;
            }

            private SingleFieldBuilderV3<Protocol.Transaction, Protocol.Transaction.Builder, Protocol.TransactionOrBuilder> getTransactionFieldBuilder() {
                if (this.transactionBuilder_ == null) {
                    this.transactionBuilder_ = new SingleFieldBuilderV3<>(getTransaction(), getParentForChildren(), isClean());
                    this.transaction_ = null;
                }
                return this.transactionBuilder_;
            }

            public Builder setSighash(ByteString byteString) {
                byteString.getClass();
                this.sighash_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearSighash() {
                this.sighash_ = ZksnarkRequest.getDefaultInstance().getSighash();
                onChanged();
                return this;
            }

            public Builder setValueBalance(long j) {
                this.valueBalance_ = j;
                onChanged();
                return this;
            }

            public Builder clearValueBalance() {
                this.valueBalance_ = 0L;
                onChanged();
                return this;
            }

            @Override
            public String getTxId() {
                Object obj = this.txId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.txId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override
            public ByteString getTxIdBytes() {
                Object obj = this.txId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.txId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTxId(String str) {
                str.getClass();
                this.txId_ = str;
                onChanged();
                return this;
            }

            public Builder clearTxId() {
                this.txId_ = ZksnarkRequest.getDefaultInstance().getTxId();
                onChanged();
                return this;
            }

            public Builder setTxIdBytes(ByteString byteString) {
                byteString.getClass();
                ZksnarkRequest.checkByteStringIsUtf8(byteString);
                this.txId_ = byteString;
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

    public static final class ZksnarkResponse extends GeneratedMessageV3 implements ZksnarkResponseOrBuilder {
        public static final int CODE_FIELD_NUMBER = 1;
        private static final ZksnarkResponse DEFAULT_INSTANCE = new ZksnarkResponse();
        private static final Parser<ZksnarkResponse> PARSER = new AbstractParser<ZksnarkResponse>() {
            @Override
            public ZksnarkResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ZksnarkResponse(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int code_;
        private byte memoizedIsInitialized;

        public static ZksnarkResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ZksnarkResponse> parser() {
            return PARSER;
        }

        @Override
        public int getCodeValue() {
            return this.code_;
        }

        @Override
        public ZksnarkResponse getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<ZksnarkResponse> getParserForType() {
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

        private ZksnarkResponse(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ZksnarkResponse() {
            this.memoizedIsInitialized = (byte) -1;
            this.code_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ZksnarkResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag != 8) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.code_ = codedInputStream.readEnum();
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
            return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkResponse_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ZksnarkResponse.class, Builder.class);
        }

        public enum Code implements ProtocolMessageEnum {
            SUCCESS(0),
            FAILED(1),
            UNRECOGNIZED(-1);
            
            public static final int FAILED_VALUE = 1;
            public static final int SUCCESS_VALUE = 0;
            private final int value;
            private static final Internal.EnumLiteMap<Code> internalValueMap = new Internal.EnumLiteMap<Code>() {
                @Override
                public Code findValueByNumber(int i) {
                    return Code.forNumber(i);
                }
            };
            private static final Code[] VALUES = values();

            public static Code forNumber(int i) {
                if (i != 0) {
                    if (i != 1) {
                        return null;
                    }
                    return FAILED;
                }
                return SUCCESS;
            }

            public static Internal.EnumLiteMap<Code> internalGetValueMap() {
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
            public static Code valueOf(int i) {
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
                return ZksnarkResponse.getDescriptor().getEnumTypes().get(0);
            }

            public static Code valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            Code(int i) {
                this.value = i;
            }
        }

        @Override
        public Code getCode() {
            Code valueOf = Code.valueOf(this.code_);
            return valueOf == null ? Code.UNRECOGNIZED : valueOf;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.code_ != Code.SUCCESS.getNumber()) {
                codedOutputStream.writeEnum(1, this.code_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = (this.code_ != Code.SUCCESS.getNumber() ? CodedOutputStream.computeEnumSize(1, this.code_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeEnumSize;
            return computeEnumSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ZksnarkResponse)) {
                return super.equals(obj);
            }
            ZksnarkResponse zksnarkResponse = (ZksnarkResponse) obj;
            return this.code_ == zksnarkResponse.code_ && this.unknownFields.equals(zksnarkResponse.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.code_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ZksnarkResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ZksnarkResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ZksnarkResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ZksnarkResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ZksnarkResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ZksnarkResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ZksnarkResponse parseFrom(InputStream inputStream) throws IOException {
            return (ZksnarkResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ZksnarkResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ZksnarkResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ZksnarkResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ZksnarkResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ZksnarkResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ZksnarkResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ZksnarkResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ZksnarkResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ZksnarkResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ZksnarkResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ZksnarkResponse zksnarkResponse) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(zksnarkResponse);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ZksnarkResponseOrBuilder {
            private int code_;

            @Override
            public int getCodeValue() {
                return this.code_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkResponse_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ZksnarkResponse.class, Builder.class);
            }

            private Builder() {
                this.code_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.code_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ZksnarkResponse.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.code_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return ZksnarkGrpcAPI.internal_static_protocol_ZksnarkResponse_descriptor;
            }

            @Override
            public ZksnarkResponse getDefaultInstanceForType() {
                return ZksnarkResponse.getDefaultInstance();
            }

            @Override
            public ZksnarkResponse build() {
                ZksnarkResponse buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ZksnarkResponse buildPartial() {
                ZksnarkResponse zksnarkResponse = new ZksnarkResponse(this);
                zksnarkResponse.code_ = this.code_;
                onBuilt();
                return zksnarkResponse;
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
                if (message instanceof ZksnarkResponse) {
                    return mergeFrom((ZksnarkResponse) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ZksnarkResponse zksnarkResponse) {
                if (zksnarkResponse == ZksnarkResponse.getDefaultInstance()) {
                    return this;
                }
                if (zksnarkResponse.code_ != 0) {
                    setCodeValue(zksnarkResponse.getCodeValue());
                }
                mergeUnknownFields(zksnarkResponse.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.api.ZksnarkGrpcAPI.ZksnarkResponse.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.api.ZksnarkGrpcAPI.ZksnarkResponse.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.api.ZksnarkGrpcAPI$ZksnarkResponse$Builder");
            }

            public Builder setCodeValue(int i) {
                this.code_ = i;
                onChanged();
                return this;
            }

            @Override
            public Code getCode() {
                Code valueOf = Code.valueOf(this.code_);
                return valueOf == null ? Code.UNRECOGNIZED : valueOf;
            }

            public Builder setCode(Code code) {
                code.getClass();
                this.code_ = code.getNumber();
                onChanged();
                return this;
            }

            public Builder clearCode() {
                this.code_ = 0;
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0011api/zksnark.proto\u0012\bprotocol\u001a\u000fcore/Tron.proto\"q\n\u000eZksnarkRequest\u0012*\n\u000btransaction\u0018\u0001 \u0001(\u000b2\u0015.protocol.Transaction\u0012\u000f\n\u0007sighash\u0018\u0002 \u0001(\f\u0012\u0014\n\fvalueBalance\u0018\u0003 \u0001(\u0003\u0012\f\n\u0004txId\u0018\u0004 \u0001(\t\"`\n\u000fZksnarkResponse\u0012,\n\u0004code\u0018\u0001 \u0001(\u000e2\u001e.protocol.ZksnarkResponse.Code\"\u001f\n\u0004Code\u0012\u000b\n\u0007SUCCESS\u0010\u0000\u0012\n\n\u0006FAILED\u0010\u00012Y\n\u000bTronZksnark\u0012J\n\u0011CheckZksnarkProof\u0012\u0018.protocol.ZksnarkRequest\u001a\u0019.protocol.ZksnarkResponse\"\u0000BH\n\forg.tron.apiB\u000eZksnarkGrpcAPIZ(github.com/tronprotocol/grpc-gateway/apib\u0006proto3"}, new Descriptors.FileDescriptor[]{Protocol.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ZksnarkGrpcAPI.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_ZksnarkRequest_descriptor = descriptor2;
        internal_static_protocol_ZksnarkRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Transaction", "Sighash", "ValueBalance", "TxId"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_ZksnarkResponse_descriptor = descriptor3;
        internal_static_protocol_ZksnarkResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Code"});
        Protocol.getDescriptor();
    }
}
