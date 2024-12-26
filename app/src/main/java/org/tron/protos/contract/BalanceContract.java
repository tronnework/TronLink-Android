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
import com.tron.wallet.common.utils.AnalyticsHelper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.tron.protos.contract.Common;
public final class BalanceContract {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_AccountBalanceRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AccountBalanceRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_AccountBalanceResponse_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AccountBalanceResponse_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_AccountIdentifier_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AccountIdentifier_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_AccountTrace_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AccountTrace_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_BlockBalanceTrace_BlockIdentifier_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_BlockBalanceTrace_BlockIdentifier_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_BlockBalanceTrace_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_BlockBalanceTrace_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_CancelAllUnfreezeV2Contract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_CancelAllUnfreezeV2Contract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_DelegateResourceContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_DelegateResourceContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_FreezeBalanceContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_FreezeBalanceContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_FreezeBalanceV2Contract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_FreezeBalanceV2Contract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_TransactionBalanceTrace_Operation_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_TransactionBalanceTrace_Operation_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_TransactionBalanceTrace_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_TransactionBalanceTrace_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_TransferContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_TransferContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_UnDelegateResourceContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_UnDelegateResourceContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_UnfreezeBalanceContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_UnfreezeBalanceContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_UnfreezeBalanceV2Contract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_UnfreezeBalanceV2Contract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_WithdrawBalanceContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_WithdrawBalanceContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_WithdrawExpireUnfreezeContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_WithdrawExpireUnfreezeContract_fieldAccessorTable;

    public interface AccountBalanceRequestOrBuilder extends MessageOrBuilder {
        AccountIdentifier getAccountIdentifier();

        AccountIdentifierOrBuilder getAccountIdentifierOrBuilder();

        BlockBalanceTrace.BlockIdentifier getBlockIdentifier();

        BlockBalanceTrace.BlockIdentifierOrBuilder getBlockIdentifierOrBuilder();

        boolean hasAccountIdentifier();

        boolean hasBlockIdentifier();
    }

    public interface AccountBalanceResponseOrBuilder extends MessageOrBuilder {
        long getBalance();

        BlockBalanceTrace.BlockIdentifier getBlockIdentifier();

        BlockBalanceTrace.BlockIdentifierOrBuilder getBlockIdentifierOrBuilder();

        boolean hasBlockIdentifier();
    }

    public interface AccountIdentifierOrBuilder extends MessageOrBuilder {
        ByteString getAddress();
    }

    public interface AccountTraceOrBuilder extends MessageOrBuilder {
        long getBalance();

        long getPlaceholder();
    }

    public interface BlockBalanceTraceOrBuilder extends MessageOrBuilder {
        BlockBalanceTrace.BlockIdentifier getBlockIdentifier();

        BlockBalanceTrace.BlockIdentifierOrBuilder getBlockIdentifierOrBuilder();

        long getTimestamp();

        TransactionBalanceTrace getTransactionBalanceTrace(int i);

        int getTransactionBalanceTraceCount();

        List<TransactionBalanceTrace> getTransactionBalanceTraceList();

        TransactionBalanceTraceOrBuilder getTransactionBalanceTraceOrBuilder(int i);

        List<? extends TransactionBalanceTraceOrBuilder> getTransactionBalanceTraceOrBuilderList();

        boolean hasBlockIdentifier();
    }

    public interface CancelAllUnfreezeV2ContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();
    }

    public interface DelegateResourceContractOrBuilder extends MessageOrBuilder {
        long getBalance();

        boolean getLock();

        long getLockPeriod();

        ByteString getOwnerAddress();

        ByteString getReceiverAddress();

        Common.ResourceCode getResource();

        int getResourceValue();
    }

    public interface FreezeBalanceContractOrBuilder extends MessageOrBuilder {
        long getFrozenBalance();

        long getFrozenDuration();

        ByteString getOwnerAddress();

        ByteString getReceiverAddress();

        Common.ResourceCode getResource();

        int getResourceValue();
    }

    public interface FreezeBalanceV2ContractOrBuilder extends MessageOrBuilder {
        long getFrozenBalance();

        ByteString getOwnerAddress();

        Common.ResourceCode getResource();

        int getResourceValue();
    }

    public interface TransactionBalanceTraceOrBuilder extends MessageOrBuilder {
        TransactionBalanceTrace.Operation getOperation(int i);

        int getOperationCount();

        List<TransactionBalanceTrace.Operation> getOperationList();

        TransactionBalanceTrace.OperationOrBuilder getOperationOrBuilder(int i);

        List<? extends TransactionBalanceTrace.OperationOrBuilder> getOperationOrBuilderList();

        String getStatus();

        ByteString getStatusBytes();

        ByteString getTransactionIdentifier();

        String getType();

        ByteString getTypeBytes();
    }

    public interface TransferContractOrBuilder extends MessageOrBuilder {
        long getAmount();

        ByteString getOwnerAddress();

        ByteString getToAddress();
    }

    public interface UnDelegateResourceContractOrBuilder extends MessageOrBuilder {
        long getBalance();

        ByteString getOwnerAddress();

        ByteString getReceiverAddress();

        Common.ResourceCode getResource();

        int getResourceValue();
    }

    public interface UnfreezeBalanceContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();

        ByteString getReceiverAddress();

        Common.ResourceCode getResource();

        int getResourceValue();
    }

    public interface UnfreezeBalanceV2ContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();

        Common.ResourceCode getResource();

        int getResourceValue();

        long getUnfreezeBalance();
    }

    public interface WithdrawBalanceContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();
    }

    public interface WithdrawExpireUnfreezeContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private BalanceContract() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class FreezeBalanceContract extends GeneratedMessageV3 implements FreezeBalanceContractOrBuilder {
        public static final int FROZEN_BALANCE_FIELD_NUMBER = 2;
        public static final int FROZEN_DURATION_FIELD_NUMBER = 3;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int RECEIVER_ADDRESS_FIELD_NUMBER = 15;
        public static final int RESOURCE_FIELD_NUMBER = 10;
        private static final long serialVersionUID = 0;
        private long frozenBalance_;
        private long frozenDuration_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString receiverAddress_;
        private int resource_;
        private static final FreezeBalanceContract DEFAULT_INSTANCE = new FreezeBalanceContract();
        private static final Parser<FreezeBalanceContract> PARSER = new AbstractParser<FreezeBalanceContract>() {
            @Override
            public FreezeBalanceContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new FreezeBalanceContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static FreezeBalanceContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FreezeBalanceContract> parser() {
            return PARSER;
        }

        @Override
        public FreezeBalanceContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public long getFrozenBalance() {
            return this.frozenBalance_;
        }

        @Override
        public long getFrozenDuration() {
            return this.frozenDuration_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<FreezeBalanceContract> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getReceiverAddress() {
            return this.receiverAddress_;
        }

        @Override
        public int getResourceValue() {
            return this.resource_;
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

        private FreezeBalanceContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private FreezeBalanceContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.frozenBalance_ = 0L;
            this.frozenDuration_ = 0L;
            this.resource_ = 0;
            this.receiverAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FreezeBalanceContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.frozenBalance_ = codedInputStream.readInt64();
                                } else if (readTag == 24) {
                                    this.frozenDuration_ = codedInputStream.readInt64();
                                } else if (readTag == 80) {
                                    this.resource_ = codedInputStream.readEnum();
                                } else if (readTag != 122) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.receiverAddress_ = codedInputStream.readBytes();
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
            return BalanceContract.internal_static_protocol_FreezeBalanceContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_FreezeBalanceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(FreezeBalanceContract.class, Builder.class);
        }

        @Override
        public Common.ResourceCode getResource() {
            Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
            return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.frozenBalance_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            long j2 = this.frozenDuration_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(3, j2);
            }
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                codedOutputStream.writeEnum(10, this.resource_);
            }
            if (!this.receiverAddress_.isEmpty()) {
                codedOutputStream.writeBytes(15, this.receiverAddress_);
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
            long j = this.frozenBalance_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            long j2 = this.frozenDuration_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(3, j2);
            }
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                computeBytesSize += CodedOutputStream.computeEnumSize(10, this.resource_);
            }
            if (!this.receiverAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(15, this.receiverAddress_);
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
            if (!(obj instanceof FreezeBalanceContract)) {
                return super.equals(obj);
            }
            FreezeBalanceContract freezeBalanceContract = (FreezeBalanceContract) obj;
            return getOwnerAddress().equals(freezeBalanceContract.getOwnerAddress()) && getFrozenBalance() == freezeBalanceContract.getFrozenBalance() && getFrozenDuration() == freezeBalanceContract.getFrozenDuration() && this.resource_ == freezeBalanceContract.resource_ && getReceiverAddress().equals(freezeBalanceContract.getReceiverAddress()) && this.unknownFields.equals(freezeBalanceContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getFrozenBalance())) * 37) + 3) * 53) + Internal.hashLong(getFrozenDuration())) * 37) + 10) * 53) + this.resource_) * 37) + 15) * 53) + getReceiverAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static FreezeBalanceContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static FreezeBalanceContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static FreezeBalanceContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static FreezeBalanceContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FreezeBalanceContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static FreezeBalanceContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static FreezeBalanceContract parseFrom(InputStream inputStream) throws IOException {
            return (FreezeBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static FreezeBalanceContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FreezeBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FreezeBalanceContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FreezeBalanceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static FreezeBalanceContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FreezeBalanceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FreezeBalanceContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FreezeBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static FreezeBalanceContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FreezeBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FreezeBalanceContract freezeBalanceContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(freezeBalanceContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FreezeBalanceContractOrBuilder {
            private long frozenBalance_;
            private long frozenDuration_;
            private ByteString ownerAddress_;
            private ByteString receiverAddress_;
            private int resource_;

            @Override
            public long getFrozenBalance() {
                return this.frozenBalance_;
            }

            @Override
            public long getFrozenDuration() {
                return this.frozenDuration_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public ByteString getReceiverAddress() {
                return this.receiverAddress_;
            }

            @Override
            public int getResourceValue() {
                return this.resource_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_FreezeBalanceContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_FreezeBalanceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(FreezeBalanceContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = FreezeBalanceContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.frozenBalance_ = 0L;
                this.frozenDuration_ = 0L;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_FreezeBalanceContract_descriptor;
            }

            @Override
            public FreezeBalanceContract getDefaultInstanceForType() {
                return FreezeBalanceContract.getDefaultInstance();
            }

            @Override
            public FreezeBalanceContract build() {
                FreezeBalanceContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public FreezeBalanceContract buildPartial() {
                FreezeBalanceContract freezeBalanceContract = new FreezeBalanceContract(this);
                freezeBalanceContract.ownerAddress_ = this.ownerAddress_;
                freezeBalanceContract.frozenBalance_ = this.frozenBalance_;
                freezeBalanceContract.frozenDuration_ = this.frozenDuration_;
                freezeBalanceContract.resource_ = this.resource_;
                freezeBalanceContract.receiverAddress_ = this.receiverAddress_;
                onBuilt();
                return freezeBalanceContract;
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
                if (message instanceof FreezeBalanceContract) {
                    return mergeFrom((FreezeBalanceContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(FreezeBalanceContract freezeBalanceContract) {
                if (freezeBalanceContract == FreezeBalanceContract.getDefaultInstance()) {
                    return this;
                }
                if (freezeBalanceContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(freezeBalanceContract.getOwnerAddress());
                }
                if (freezeBalanceContract.getFrozenBalance() != 0) {
                    setFrozenBalance(freezeBalanceContract.getFrozenBalance());
                }
                if (freezeBalanceContract.getFrozenDuration() != 0) {
                    setFrozenDuration(freezeBalanceContract.getFrozenDuration());
                }
                if (freezeBalanceContract.resource_ != 0) {
                    setResourceValue(freezeBalanceContract.getResourceValue());
                }
                if (freezeBalanceContract.getReceiverAddress() != ByteString.EMPTY) {
                    setReceiverAddress(freezeBalanceContract.getReceiverAddress());
                }
                mergeUnknownFields(freezeBalanceContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.FreezeBalanceContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.FreezeBalanceContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$FreezeBalanceContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = FreezeBalanceContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setFrozenBalance(long j) {
                this.frozenBalance_ = j;
                onChanged();
                return this;
            }

            public Builder clearFrozenBalance() {
                this.frozenBalance_ = 0L;
                onChanged();
                return this;
            }

            public Builder setFrozenDuration(long j) {
                this.frozenDuration_ = j;
                onChanged();
                return this;
            }

            public Builder clearFrozenDuration() {
                this.frozenDuration_ = 0L;
                onChanged();
                return this;
            }

            public Builder setResourceValue(int i) {
                this.resource_ = i;
                onChanged();
                return this;
            }

            @Override
            public Common.ResourceCode getResource() {
                Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
                return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
            }

            public Builder setResource(Common.ResourceCode resourceCode) {
                resourceCode.getClass();
                this.resource_ = resourceCode.getNumber();
                onChanged();
                return this;
            }

            public Builder clearResource() {
                this.resource_ = 0;
                onChanged();
                return this;
            }

            public Builder setReceiverAddress(ByteString byteString) {
                byteString.getClass();
                this.receiverAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearReceiverAddress() {
                this.receiverAddress_ = FreezeBalanceContract.getDefaultInstance().getReceiverAddress();
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

    public static final class UnfreezeBalanceContract extends GeneratedMessageV3 implements UnfreezeBalanceContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int RECEIVER_ADDRESS_FIELD_NUMBER = 15;
        public static final int RESOURCE_FIELD_NUMBER = 10;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString receiverAddress_;
        private int resource_;
        private static final UnfreezeBalanceContract DEFAULT_INSTANCE = new UnfreezeBalanceContract();
        private static final Parser<UnfreezeBalanceContract> PARSER = new AbstractParser<UnfreezeBalanceContract>() {
            @Override
            public UnfreezeBalanceContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UnfreezeBalanceContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static UnfreezeBalanceContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UnfreezeBalanceContract> parser() {
            return PARSER;
        }

        @Override
        public UnfreezeBalanceContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<UnfreezeBalanceContract> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getReceiverAddress() {
            return this.receiverAddress_;
        }

        @Override
        public int getResourceValue() {
            return this.resource_;
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

        private UnfreezeBalanceContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UnfreezeBalanceContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.resource_ = 0;
            this.receiverAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UnfreezeBalanceContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            } else if (readTag == 80) {
                                this.resource_ = codedInputStream.readEnum();
                            } else if (readTag != 122) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.receiverAddress_ = codedInputStream.readBytes();
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
            return BalanceContract.internal_static_protocol_UnfreezeBalanceContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_UnfreezeBalanceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UnfreezeBalanceContract.class, Builder.class);
        }

        @Override
        public Common.ResourceCode getResource() {
            Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
            return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                codedOutputStream.writeEnum(10, this.resource_);
            }
            if (!this.receiverAddress_.isEmpty()) {
                codedOutputStream.writeBytes(15, this.receiverAddress_);
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
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                computeBytesSize += CodedOutputStream.computeEnumSize(10, this.resource_);
            }
            if (!this.receiverAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(15, this.receiverAddress_);
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
            if (!(obj instanceof UnfreezeBalanceContract)) {
                return super.equals(obj);
            }
            UnfreezeBalanceContract unfreezeBalanceContract = (UnfreezeBalanceContract) obj;
            return getOwnerAddress().equals(unfreezeBalanceContract.getOwnerAddress()) && this.resource_ == unfreezeBalanceContract.resource_ && getReceiverAddress().equals(unfreezeBalanceContract.getReceiverAddress()) && this.unknownFields.equals(unfreezeBalanceContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 10) * 53) + this.resource_) * 37) + 15) * 53) + getReceiverAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static UnfreezeBalanceContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UnfreezeBalanceContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UnfreezeBalanceContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UnfreezeBalanceContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UnfreezeBalanceContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UnfreezeBalanceContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UnfreezeBalanceContract parseFrom(InputStream inputStream) throws IOException {
            return (UnfreezeBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UnfreezeBalanceContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnfreezeBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnfreezeBalanceContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnfreezeBalanceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UnfreezeBalanceContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnfreezeBalanceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnfreezeBalanceContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnfreezeBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UnfreezeBalanceContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnfreezeBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UnfreezeBalanceContract unfreezeBalanceContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(unfreezeBalanceContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UnfreezeBalanceContractOrBuilder {
            private ByteString ownerAddress_;
            private ByteString receiverAddress_;
            private int resource_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public ByteString getReceiverAddress() {
                return this.receiverAddress_;
            }

            @Override
            public int getResourceValue() {
                return this.resource_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_UnfreezeBalanceContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_UnfreezeBalanceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UnfreezeBalanceContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UnfreezeBalanceContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_UnfreezeBalanceContract_descriptor;
            }

            @Override
            public UnfreezeBalanceContract getDefaultInstanceForType() {
                return UnfreezeBalanceContract.getDefaultInstance();
            }

            @Override
            public UnfreezeBalanceContract build() {
                UnfreezeBalanceContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public UnfreezeBalanceContract buildPartial() {
                UnfreezeBalanceContract unfreezeBalanceContract = new UnfreezeBalanceContract(this);
                unfreezeBalanceContract.ownerAddress_ = this.ownerAddress_;
                unfreezeBalanceContract.resource_ = this.resource_;
                unfreezeBalanceContract.receiverAddress_ = this.receiverAddress_;
                onBuilt();
                return unfreezeBalanceContract;
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
                if (message instanceof UnfreezeBalanceContract) {
                    return mergeFrom((UnfreezeBalanceContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UnfreezeBalanceContract unfreezeBalanceContract) {
                if (unfreezeBalanceContract == UnfreezeBalanceContract.getDefaultInstance()) {
                    return this;
                }
                if (unfreezeBalanceContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(unfreezeBalanceContract.getOwnerAddress());
                }
                if (unfreezeBalanceContract.resource_ != 0) {
                    setResourceValue(unfreezeBalanceContract.getResourceValue());
                }
                if (unfreezeBalanceContract.getReceiverAddress() != ByteString.EMPTY) {
                    setReceiverAddress(unfreezeBalanceContract.getReceiverAddress());
                }
                mergeUnknownFields(unfreezeBalanceContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.UnfreezeBalanceContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.UnfreezeBalanceContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$UnfreezeBalanceContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = UnfreezeBalanceContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setResourceValue(int i) {
                this.resource_ = i;
                onChanged();
                return this;
            }

            @Override
            public Common.ResourceCode getResource() {
                Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
                return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
            }

            public Builder setResource(Common.ResourceCode resourceCode) {
                resourceCode.getClass();
                this.resource_ = resourceCode.getNumber();
                onChanged();
                return this;
            }

            public Builder clearResource() {
                this.resource_ = 0;
                onChanged();
                return this;
            }

            public Builder setReceiverAddress(ByteString byteString) {
                byteString.getClass();
                this.receiverAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearReceiverAddress() {
                this.receiverAddress_ = UnfreezeBalanceContract.getDefaultInstance().getReceiverAddress();
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

    public static final class WithdrawBalanceContract extends GeneratedMessageV3 implements WithdrawBalanceContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final WithdrawBalanceContract DEFAULT_INSTANCE = new WithdrawBalanceContract();
        private static final Parser<WithdrawBalanceContract> PARSER = new AbstractParser<WithdrawBalanceContract>() {
            @Override
            public WithdrawBalanceContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new WithdrawBalanceContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static WithdrawBalanceContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WithdrawBalanceContract> parser() {
            return PARSER;
        }

        @Override
        public WithdrawBalanceContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<WithdrawBalanceContract> getParserForType() {
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

        private WithdrawBalanceContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private WithdrawBalanceContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private WithdrawBalanceContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.ownerAddress_ = codedInputStream.readBytes();
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
            return BalanceContract.internal_static_protocol_WithdrawBalanceContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_WithdrawBalanceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(WithdrawBalanceContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (!this.ownerAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.ownerAddress_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeBytesSize;
            return computeBytesSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof WithdrawBalanceContract)) {
                return super.equals(obj);
            }
            WithdrawBalanceContract withdrawBalanceContract = (WithdrawBalanceContract) obj;
            return getOwnerAddress().equals(withdrawBalanceContract.getOwnerAddress()) && this.unknownFields.equals(withdrawBalanceContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static WithdrawBalanceContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static WithdrawBalanceContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static WithdrawBalanceContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static WithdrawBalanceContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static WithdrawBalanceContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static WithdrawBalanceContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static WithdrawBalanceContract parseFrom(InputStream inputStream) throws IOException {
            return (WithdrawBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static WithdrawBalanceContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WithdrawBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static WithdrawBalanceContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (WithdrawBalanceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static WithdrawBalanceContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WithdrawBalanceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static WithdrawBalanceContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (WithdrawBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static WithdrawBalanceContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WithdrawBalanceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WithdrawBalanceContract withdrawBalanceContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(withdrawBalanceContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WithdrawBalanceContractOrBuilder {
            private ByteString ownerAddress_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_WithdrawBalanceContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_WithdrawBalanceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(WithdrawBalanceContract.class, Builder.class);
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
                boolean unused = WithdrawBalanceContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_WithdrawBalanceContract_descriptor;
            }

            @Override
            public WithdrawBalanceContract getDefaultInstanceForType() {
                return WithdrawBalanceContract.getDefaultInstance();
            }

            @Override
            public WithdrawBalanceContract build() {
                WithdrawBalanceContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public WithdrawBalanceContract buildPartial() {
                WithdrawBalanceContract withdrawBalanceContract = new WithdrawBalanceContract(this);
                withdrawBalanceContract.ownerAddress_ = this.ownerAddress_;
                onBuilt();
                return withdrawBalanceContract;
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
                if (message instanceof WithdrawBalanceContract) {
                    return mergeFrom((WithdrawBalanceContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(WithdrawBalanceContract withdrawBalanceContract) {
                if (withdrawBalanceContract == WithdrawBalanceContract.getDefaultInstance()) {
                    return this;
                }
                if (withdrawBalanceContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(withdrawBalanceContract.getOwnerAddress());
                }
                mergeUnknownFields(withdrawBalanceContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.WithdrawBalanceContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.WithdrawBalanceContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$WithdrawBalanceContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = WithdrawBalanceContract.getDefaultInstance().getOwnerAddress();
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

    public static final class TransferContract extends GeneratedMessageV3 implements TransferContractOrBuilder {
        public static final int AMOUNT_FIELD_NUMBER = 3;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int TO_ADDRESS_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private long amount_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString toAddress_;
        private static final TransferContract DEFAULT_INSTANCE = new TransferContract();
        private static final Parser<TransferContract> PARSER = new AbstractParser<TransferContract>() {
            @Override
            public TransferContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TransferContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static TransferContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TransferContract> parser() {
            return PARSER;
        }

        @Override
        public long getAmount() {
            return this.amount_;
        }

        @Override
        public TransferContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<TransferContract> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getToAddress() {
            return this.toAddress_;
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

        private TransferContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private TransferContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.toAddress_ = ByteString.EMPTY;
            this.amount_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private TransferContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            } else if (readTag == 18) {
                                this.toAddress_ = codedInputStream.readBytes();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.amount_ = codedInputStream.readInt64();
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
            return BalanceContract.internal_static_protocol_TransferContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_TransferContract_fieldAccessorTable.ensureFieldAccessorsInitialized(TransferContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.toAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.toAddress_);
            }
            long j = this.amount_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
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
            if (!this.toAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.toAddress_);
            }
            long j = this.amount_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(3, j);
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
            if (!(obj instanceof TransferContract)) {
                return super.equals(obj);
            }
            TransferContract transferContract = (TransferContract) obj;
            return getOwnerAddress().equals(transferContract.getOwnerAddress()) && getToAddress().equals(transferContract.getToAddress()) && getAmount() == transferContract.getAmount() && this.unknownFields.equals(transferContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getToAddress().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getAmount())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static TransferContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static TransferContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static TransferContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static TransferContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TransferContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static TransferContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TransferContract parseFrom(InputStream inputStream) throws IOException {
            return (TransferContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static TransferContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransferContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TransferContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TransferContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static TransferContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransferContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TransferContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TransferContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static TransferContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransferContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TransferContract transferContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(transferContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TransferContractOrBuilder {
            private long amount_;
            private ByteString ownerAddress_;
            private ByteString toAddress_;

            @Override
            public long getAmount() {
                return this.amount_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public ByteString getToAddress() {
                return this.toAddress_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_TransferContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_TransferContract_fieldAccessorTable.ensureFieldAccessorsInitialized(TransferContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.toAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.toAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = TransferContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.toAddress_ = ByteString.EMPTY;
                this.amount_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_TransferContract_descriptor;
            }

            @Override
            public TransferContract getDefaultInstanceForType() {
                return TransferContract.getDefaultInstance();
            }

            @Override
            public TransferContract build() {
                TransferContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public TransferContract buildPartial() {
                TransferContract transferContract = new TransferContract(this);
                transferContract.ownerAddress_ = this.ownerAddress_;
                transferContract.toAddress_ = this.toAddress_;
                transferContract.amount_ = this.amount_;
                onBuilt();
                return transferContract;
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
                if (message instanceof TransferContract) {
                    return mergeFrom((TransferContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TransferContract transferContract) {
                if (transferContract == TransferContract.getDefaultInstance()) {
                    return this;
                }
                if (transferContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(transferContract.getOwnerAddress());
                }
                if (transferContract.getToAddress() != ByteString.EMPTY) {
                    setToAddress(transferContract.getToAddress());
                }
                if (transferContract.getAmount() != 0) {
                    setAmount(transferContract.getAmount());
                }
                mergeUnknownFields(transferContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.TransferContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.TransferContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$TransferContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = TransferContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setToAddress(ByteString byteString) {
                byteString.getClass();
                this.toAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearToAddress() {
                this.toAddress_ = TransferContract.getDefaultInstance().getToAddress();
                onChanged();
                return this;
            }

            public Builder setAmount(long j) {
                this.amount_ = j;
                onChanged();
                return this;
            }

            public Builder clearAmount() {
                this.amount_ = 0L;
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

    public static final class TransactionBalanceTrace extends GeneratedMessageV3 implements TransactionBalanceTraceOrBuilder {
        public static final int OPERATION_FIELD_NUMBER = 2;
        public static final int STATUS_FIELD_NUMBER = 4;
        public static final int TRANSACTION_IDENTIFIER_FIELD_NUMBER = 1;
        public static final int TYPE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private List<Operation> operation_;
        private volatile Object status_;
        private ByteString transactionIdentifier_;
        private volatile Object type_;
        private static final TransactionBalanceTrace DEFAULT_INSTANCE = new TransactionBalanceTrace();
        private static final Parser<TransactionBalanceTrace> PARSER = new AbstractParser<TransactionBalanceTrace>() {
            @Override
            public TransactionBalanceTrace parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TransactionBalanceTrace(codedInputStream, extensionRegistryLite);
            }
        };

        public interface OperationOrBuilder extends MessageOrBuilder {
            ByteString getAddress();

            long getAmount();

            long getOperationIdentifier();
        }

        public static TransactionBalanceTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TransactionBalanceTrace> parser() {
            return PARSER;
        }

        @Override
        public TransactionBalanceTrace getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public List<Operation> getOperationList() {
            return this.operation_;
        }

        @Override
        public List<? extends OperationOrBuilder> getOperationOrBuilderList() {
            return this.operation_;
        }

        @Override
        public Parser<TransactionBalanceTrace> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getTransactionIdentifier() {
            return this.transactionIdentifier_;
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

        private TransactionBalanceTrace(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private TransactionBalanceTrace() {
            this.memoizedIsInitialized = (byte) -1;
            this.transactionIdentifier_ = ByteString.EMPTY;
            this.operation_ = Collections.emptyList();
            this.type_ = "";
            this.status_ = "";
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private TransactionBalanceTrace(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.transactionIdentifier_ = codedInputStream.readBytes();
                                } else if (readTag == 18) {
                                    if (!(z2 & true)) {
                                        this.operation_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.operation_.add((Operation) codedInputStream.readMessage(Operation.parser(), extensionRegistryLite));
                                } else if (readTag == 26) {
                                    this.type_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 34) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.status_ = codedInputStream.readStringRequireUtf8();
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
                    if (z2 & true) {
                        this.operation_ = Collections.unmodifiableList(this.operation_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BalanceContract.internal_static_protocol_TransactionBalanceTrace_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_TransactionBalanceTrace_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionBalanceTrace.class, Builder.class);
        }

        public static final class Operation extends GeneratedMessageV3 implements OperationOrBuilder {
            public static final int ADDRESS_FIELD_NUMBER = 2;
            public static final int AMOUNT_FIELD_NUMBER = 3;
            public static final int OPERATION_IDENTIFIER_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private ByteString address_;
            private long amount_;
            private byte memoizedIsInitialized;
            private long operationIdentifier_;
            private static final Operation DEFAULT_INSTANCE = new Operation();
            private static final Parser<Operation> PARSER = new AbstractParser<Operation>() {
                @Override
                public Operation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Operation(codedInputStream, extensionRegistryLite);
                }
            };

            public static Operation getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Operation> parser() {
                return PARSER;
            }

            @Override
            public ByteString getAddress() {
                return this.address_;
            }

            @Override
            public long getAmount() {
                return this.amount_;
            }

            @Override
            public Operation getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override
            public long getOperationIdentifier() {
                return this.operationIdentifier_;
            }

            @Override
            public Parser<Operation> getParserForType() {
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

            private Operation(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Operation() {
                this.memoizedIsInitialized = (byte) -1;
                this.operationIdentifier_ = 0L;
                this.address_ = ByteString.EMPTY;
                this.amount_ = 0L;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Operation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.operationIdentifier_ = codedInputStream.readInt64();
                                } else if (readTag == 18) {
                                    this.address_ = codedInputStream.readBytes();
                                } else if (readTag != 24) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.amount_ = codedInputStream.readInt64();
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
                return BalanceContract.internal_static_protocol_TransactionBalanceTrace_Operation_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_TransactionBalanceTrace_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
            }

            @Override
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                long j = this.operationIdentifier_;
                if (j != 0) {
                    codedOutputStream.writeInt64(1, j);
                }
                if (!this.address_.isEmpty()) {
                    codedOutputStream.writeBytes(2, this.address_);
                }
                long j2 = this.amount_;
                if (j2 != 0) {
                    codedOutputStream.writeInt64(3, j2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                long j = this.operationIdentifier_;
                int computeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(1, j) : 0;
                if (!this.address_.isEmpty()) {
                    computeInt64Size += CodedOutputStream.computeBytesSize(2, this.address_);
                }
                long j2 = this.amount_;
                if (j2 != 0) {
                    computeInt64Size += CodedOutputStream.computeInt64Size(3, j2);
                }
                int serializedSize = computeInt64Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Operation)) {
                    return super.equals(obj);
                }
                Operation operation = (Operation) obj;
                return getOperationIdentifier() == operation.getOperationIdentifier() && getAddress().equals(operation.getAddress()) && getAmount() == operation.getAmount() && this.unknownFields.equals(operation.unknownFields);
            }

            @Override
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getOperationIdentifier())) * 37) + 2) * 53) + getAddress().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getAmount())) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            public static Operation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static Operation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Operation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static Operation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Operation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static Operation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Operation parseFrom(InputStream inputStream) throws IOException {
                return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Operation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Operation parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Operation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Operation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Operation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Operation parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Operation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Operation operation) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(operation);
            }

            @Override
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            @Override
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OperationOrBuilder {
                private ByteString address_;
                private long amount_;
                private long operationIdentifier_;

                @Override
                public ByteString getAddress() {
                    return this.address_;
                }

                @Override
                public long getAmount() {
                    return this.amount_;
                }

                @Override
                public long getOperationIdentifier() {
                    return this.operationIdentifier_;
                }

                @Override
                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return BalanceContract.internal_static_protocol_TransactionBalanceTrace_Operation_descriptor;
                }

                @Override
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return BalanceContract.internal_static_protocol_TransactionBalanceTrace_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
                }

                private Builder() {
                    this.address_ = ByteString.EMPTY;
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.address_ = ByteString.EMPTY;
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Operation.alwaysUseFieldBuilders;
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.operationIdentifier_ = 0L;
                    this.address_ = ByteString.EMPTY;
                    this.amount_ = 0L;
                    return this;
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return BalanceContract.internal_static_protocol_TransactionBalanceTrace_Operation_descriptor;
                }

                @Override
                public Operation getDefaultInstanceForType() {
                    return Operation.getDefaultInstance();
                }

                @Override
                public Operation build() {
                    Operation buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override
                public Operation buildPartial() {
                    Operation operation = new Operation(this);
                    operation.operationIdentifier_ = this.operationIdentifier_;
                    operation.address_ = this.address_;
                    operation.amount_ = this.amount_;
                    onBuilt();
                    return operation;
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
                    if (message instanceof Operation) {
                        return mergeFrom((Operation) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Operation operation) {
                    if (operation == Operation.getDefaultInstance()) {
                        return this;
                    }
                    if (operation.getOperationIdentifier() != 0) {
                        setOperationIdentifier(operation.getOperationIdentifier());
                    }
                    if (operation.getAddress() != ByteString.EMPTY) {
                        setAddress(operation.getAddress());
                    }
                    if (operation.getAmount() != 0) {
                        setAmount(operation.getAmount());
                    }
                    mergeUnknownFields(operation.unknownFields);
                    onChanged();
                    return this;
                }

                @Override
                public org.tron.protos.contract.BalanceContract.TransactionBalanceTrace.Operation.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.TransactionBalanceTrace.Operation.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$TransactionBalanceTrace$Operation$Builder");
                }

                public Builder setOperationIdentifier(long j) {
                    this.operationIdentifier_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearOperationIdentifier() {
                    this.operationIdentifier_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder setAddress(ByteString byteString) {
                    byteString.getClass();
                    this.address_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearAddress() {
                    this.address_ = Operation.getDefaultInstance().getAddress();
                    onChanged();
                    return this;
                }

                public Builder setAmount(long j) {
                    this.amount_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearAmount() {
                    this.amount_ = 0L;
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
        public int getOperationCount() {
            return this.operation_.size();
        }

        @Override
        public Operation getOperation(int i) {
            return this.operation_.get(i);
        }

        @Override
        public OperationOrBuilder getOperationOrBuilder(int i) {
            return this.operation_.get(i);
        }

        @Override
        public String getType() {
            Object obj = this.type_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.type_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getTypeBytes() {
            Object obj = this.type_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.type_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override
        public String getStatus() {
            Object obj = this.status_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.status_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getStatusBytes() {
            Object obj = this.status_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.status_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.transactionIdentifier_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.transactionIdentifier_);
            }
            for (int i = 0; i < this.operation_.size(); i++) {
                codedOutputStream.writeMessage(2, this.operation_.get(i));
            }
            if (!getTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.type_);
            }
            if (!getStatusBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.status_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.transactionIdentifier_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.transactionIdentifier_) : 0;
            for (int i2 = 0; i2 < this.operation_.size(); i2++) {
                computeBytesSize += CodedOutputStream.computeMessageSize(2, this.operation_.get(i2));
            }
            if (!getTypeBytes().isEmpty()) {
                computeBytesSize += GeneratedMessageV3.computeStringSize(3, this.type_);
            }
            if (!getStatusBytes().isEmpty()) {
                computeBytesSize += GeneratedMessageV3.computeStringSize(4, this.status_);
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
            if (!(obj instanceof TransactionBalanceTrace)) {
                return super.equals(obj);
            }
            TransactionBalanceTrace transactionBalanceTrace = (TransactionBalanceTrace) obj;
            return getTransactionIdentifier().equals(transactionBalanceTrace.getTransactionIdentifier()) && getOperationList().equals(transactionBalanceTrace.getOperationList()) && getType().equals(transactionBalanceTrace.getType()) && getStatus().equals(transactionBalanceTrace.getStatus()) && this.unknownFields.equals(transactionBalanceTrace.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTransactionIdentifier().hashCode();
            if (getOperationCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getOperationList().hashCode();
            }
            int hashCode2 = (((((((((hashCode * 37) + 3) * 53) + getType().hashCode()) * 37) + 4) * 53) + getStatus().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static TransactionBalanceTrace parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static TransactionBalanceTrace parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static TransactionBalanceTrace parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static TransactionBalanceTrace parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TransactionBalanceTrace parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static TransactionBalanceTrace parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TransactionBalanceTrace parseFrom(InputStream inputStream) throws IOException {
            return (TransactionBalanceTrace) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static TransactionBalanceTrace parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionBalanceTrace) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TransactionBalanceTrace parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TransactionBalanceTrace) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static TransactionBalanceTrace parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionBalanceTrace) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TransactionBalanceTrace parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TransactionBalanceTrace) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static TransactionBalanceTrace parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionBalanceTrace) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TransactionBalanceTrace transactionBalanceTrace) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(transactionBalanceTrace);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TransactionBalanceTraceOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> operationBuilder_;
            private List<Operation> operation_;
            private Object status_;
            private ByteString transactionIdentifier_;
            private Object type_;

            @Override
            public ByteString getTransactionIdentifier() {
                return this.transactionIdentifier_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_TransactionBalanceTrace_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_TransactionBalanceTrace_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionBalanceTrace.class, Builder.class);
            }

            private Builder() {
                this.transactionIdentifier_ = ByteString.EMPTY;
                this.operation_ = Collections.emptyList();
                this.type_ = "";
                this.status_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.transactionIdentifier_ = ByteString.EMPTY;
                this.operation_ = Collections.emptyList();
                this.type_ = "";
                this.status_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (TransactionBalanceTrace.alwaysUseFieldBuilders) {
                    getOperationFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                this.transactionIdentifier_ = ByteString.EMPTY;
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.operation_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.type_ = "";
                this.status_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_TransactionBalanceTrace_descriptor;
            }

            @Override
            public TransactionBalanceTrace getDefaultInstanceForType() {
                return TransactionBalanceTrace.getDefaultInstance();
            }

            @Override
            public TransactionBalanceTrace build() {
                TransactionBalanceTrace buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public TransactionBalanceTrace buildPartial() {
                TransactionBalanceTrace transactionBalanceTrace = new TransactionBalanceTrace(this);
                transactionBalanceTrace.transactionIdentifier_ = this.transactionIdentifier_;
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.operation_ = Collections.unmodifiableList(this.operation_);
                        this.bitField0_ &= -3;
                    }
                    transactionBalanceTrace.operation_ = this.operation_;
                } else {
                    transactionBalanceTrace.operation_ = repeatedFieldBuilderV3.build();
                }
                transactionBalanceTrace.type_ = this.type_;
                transactionBalanceTrace.status_ = this.status_;
                transactionBalanceTrace.bitField0_ = 0;
                onBuilt();
                return transactionBalanceTrace;
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
                if (message instanceof TransactionBalanceTrace) {
                    return mergeFrom((TransactionBalanceTrace) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TransactionBalanceTrace transactionBalanceTrace) {
                if (transactionBalanceTrace == TransactionBalanceTrace.getDefaultInstance()) {
                    return this;
                }
                if (transactionBalanceTrace.getTransactionIdentifier() != ByteString.EMPTY) {
                    setTransactionIdentifier(transactionBalanceTrace.getTransactionIdentifier());
                }
                if (this.operationBuilder_ == null) {
                    if (!transactionBalanceTrace.operation_.isEmpty()) {
                        if (this.operation_.isEmpty()) {
                            this.operation_ = transactionBalanceTrace.operation_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureOperationIsMutable();
                            this.operation_.addAll(transactionBalanceTrace.operation_);
                        }
                        onChanged();
                    }
                } else if (!transactionBalanceTrace.operation_.isEmpty()) {
                    if (!this.operationBuilder_.isEmpty()) {
                        this.operationBuilder_.addAllMessages(transactionBalanceTrace.operation_);
                    } else {
                        this.operationBuilder_.dispose();
                        this.operationBuilder_ = null;
                        this.operation_ = transactionBalanceTrace.operation_;
                        this.bitField0_ &= -3;
                        this.operationBuilder_ = TransactionBalanceTrace.alwaysUseFieldBuilders ? getOperationFieldBuilder() : null;
                    }
                }
                if (!transactionBalanceTrace.getType().isEmpty()) {
                    this.type_ = transactionBalanceTrace.type_;
                    onChanged();
                }
                if (!transactionBalanceTrace.getStatus().isEmpty()) {
                    this.status_ = transactionBalanceTrace.status_;
                    onChanged();
                }
                mergeUnknownFields(transactionBalanceTrace.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.TransactionBalanceTrace.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.TransactionBalanceTrace.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$TransactionBalanceTrace$Builder");
            }

            public Builder setTransactionIdentifier(ByteString byteString) {
                byteString.getClass();
                this.transactionIdentifier_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearTransactionIdentifier() {
                this.transactionIdentifier_ = TransactionBalanceTrace.getDefaultInstance().getTransactionIdentifier();
                onChanged();
                return this;
            }

            private void ensureOperationIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.operation_ = new ArrayList(this.operation_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<Operation> getOperationList() {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.operation_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getOperationCount() {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.operation_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public Operation getOperation(int i) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.operation_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setOperation(int i, Operation operation) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    operation.getClass();
                    ensureOperationIsMutable();
                    this.operation_.set(i, operation);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, operation);
                }
                return this;
            }

            public Builder setOperation(int i, Operation.Builder builder) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOperationIsMutable();
                    this.operation_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addOperation(Operation operation) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    operation.getClass();
                    ensureOperationIsMutable();
                    this.operation_.add(operation);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(operation);
                }
                return this;
            }

            public Builder addOperation(int i, Operation operation) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    operation.getClass();
                    ensureOperationIsMutable();
                    this.operation_.add(i, operation);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, operation);
                }
                return this;
            }

            public Builder addOperation(Operation.Builder builder) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOperationIsMutable();
                    this.operation_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addOperation(int i, Operation.Builder builder) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOperationIsMutable();
                    this.operation_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllOperation(Iterable<? extends Operation> iterable) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOperationIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.operation_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearOperation() {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.operation_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeOperation(int i) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOperationIsMutable();
                    this.operation_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Operation.Builder getOperationBuilder(int i) {
                return getOperationFieldBuilder().getBuilder(i);
            }

            @Override
            public OperationOrBuilder getOperationOrBuilder(int i) {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.operation_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends OperationOrBuilder> getOperationOrBuilderList() {
                RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.operation_);
            }

            public Operation.Builder addOperationBuilder() {
                return getOperationFieldBuilder().addBuilder(Operation.getDefaultInstance());
            }

            public Operation.Builder addOperationBuilder(int i) {
                return getOperationFieldBuilder().addBuilder(i, Operation.getDefaultInstance());
            }

            public List<Operation.Builder> getOperationBuilderList() {
                return getOperationFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> getOperationFieldBuilder() {
                if (this.operationBuilder_ == null) {
                    this.operationBuilder_ = new RepeatedFieldBuilderV3<>(this.operation_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.operation_ = null;
                }
                return this.operationBuilder_;
            }

            @Override
            public String getType() {
                Object obj = this.type_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.type_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override
            public ByteString getTypeBytes() {
                Object obj = this.type_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.type_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setType(String str) {
                str.getClass();
                this.type_ = str;
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.type_ = TransactionBalanceTrace.getDefaultInstance().getType();
                onChanged();
                return this;
            }

            public Builder setTypeBytes(ByteString byteString) {
                byteString.getClass();
                TransactionBalanceTrace.checkByteStringIsUtf8(byteString);
                this.type_ = byteString;
                onChanged();
                return this;
            }

            @Override
            public String getStatus() {
                Object obj = this.status_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.status_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override
            public ByteString getStatusBytes() {
                Object obj = this.status_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.status_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setStatus(String str) {
                str.getClass();
                this.status_ = str;
                onChanged();
                return this;
            }

            public Builder clearStatus() {
                this.status_ = TransactionBalanceTrace.getDefaultInstance().getStatus();
                onChanged();
                return this;
            }

            public Builder setStatusBytes(ByteString byteString) {
                byteString.getClass();
                TransactionBalanceTrace.checkByteStringIsUtf8(byteString);
                this.status_ = byteString;
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

    public static final class BlockBalanceTrace extends GeneratedMessageV3 implements BlockBalanceTraceOrBuilder {
        public static final int BLOCK_IDENTIFIER_FIELD_NUMBER = 1;
        private static final BlockBalanceTrace DEFAULT_INSTANCE = new BlockBalanceTrace();
        private static final Parser<BlockBalanceTrace> PARSER = new AbstractParser<BlockBalanceTrace>() {
            @Override
            public BlockBalanceTrace parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BlockBalanceTrace(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TIMESTAMP_FIELD_NUMBER = 2;
        public static final int TRANSACTION_BALANCE_TRACE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private BlockIdentifier blockIdentifier_;
        private byte memoizedIsInitialized;
        private long timestamp_;
        private List<TransactionBalanceTrace> transactionBalanceTrace_;

        public interface BlockIdentifierOrBuilder extends MessageOrBuilder {
            ByteString getHash();

            long getNumber();
        }

        public static BlockBalanceTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BlockBalanceTrace> parser() {
            return PARSER;
        }

        @Override
        public BlockBalanceTrace getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<BlockBalanceTrace> getParserForType() {
            return PARSER;
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }

        @Override
        public List<TransactionBalanceTrace> getTransactionBalanceTraceList() {
            return this.transactionBalanceTrace_;
        }

        @Override
        public List<? extends TransactionBalanceTraceOrBuilder> getTransactionBalanceTraceOrBuilderList() {
            return this.transactionBalanceTrace_;
        }

        @Override
        public boolean hasBlockIdentifier() {
            return this.blockIdentifier_ != null;
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

        private BlockBalanceTrace(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private BlockBalanceTrace() {
            this.memoizedIsInitialized = (byte) -1;
            this.timestamp_ = 0L;
            this.transactionBalanceTrace_ = Collections.emptyList();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BlockBalanceTrace(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    BlockIdentifier blockIdentifier = this.blockIdentifier_;
                                    BlockIdentifier.Builder builder = blockIdentifier != null ? blockIdentifier.toBuilder() : null;
                                    BlockIdentifier blockIdentifier2 = (BlockIdentifier) codedInputStream.readMessage(BlockIdentifier.parser(), extensionRegistryLite);
                                    this.blockIdentifier_ = blockIdentifier2;
                                    if (builder != null) {
                                        builder.mergeFrom(blockIdentifier2);
                                        this.blockIdentifier_ = builder.buildPartial();
                                    }
                                } else if (readTag == 16) {
                                    this.timestamp_ = codedInputStream.readInt64();
                                } else if (readTag != 26) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    if (!(z2 & true)) {
                                        this.transactionBalanceTrace_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.transactionBalanceTrace_.add((TransactionBalanceTrace) codedInputStream.readMessage(TransactionBalanceTrace.parser(), extensionRegistryLite));
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
                    if (z2 & true) {
                        this.transactionBalanceTrace_ = Collections.unmodifiableList(this.transactionBalanceTrace_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BalanceContract.internal_static_protocol_BlockBalanceTrace_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_BlockBalanceTrace_fieldAccessorTable.ensureFieldAccessorsInitialized(BlockBalanceTrace.class, Builder.class);
        }

        public static final class BlockIdentifier extends GeneratedMessageV3 implements BlockIdentifierOrBuilder {
            public static final int HASH_FIELD_NUMBER = 1;
            public static final int NUMBER_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private ByteString hash_;
            private byte memoizedIsInitialized;
            private long number_;
            private static final BlockIdentifier DEFAULT_INSTANCE = new BlockIdentifier();
            private static final Parser<BlockIdentifier> PARSER = new AbstractParser<BlockIdentifier>() {
                @Override
                public BlockIdentifier parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new BlockIdentifier(codedInputStream, extensionRegistryLite);
                }
            };

            public static BlockIdentifier getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<BlockIdentifier> parser() {
                return PARSER;
            }

            @Override
            public BlockIdentifier getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override
            public ByteString getHash() {
                return this.hash_;
            }

            @Override
            public long getNumber() {
                return this.number_;
            }

            @Override
            public Parser<BlockIdentifier> getParserForType() {
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

            private BlockIdentifier(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private BlockIdentifier() {
                this.memoizedIsInitialized = (byte) -1;
                this.hash_ = ByteString.EMPTY;
                this.number_ = 0L;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private BlockIdentifier(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.number_ = codedInputStream.readInt64();
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
                return BalanceContract.internal_static_protocol_BlockBalanceTrace_BlockIdentifier_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_BlockBalanceTrace_BlockIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(BlockIdentifier.class, Builder.class);
            }

            @Override
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (!this.hash_.isEmpty()) {
                    codedOutputStream.writeBytes(1, this.hash_);
                }
                long j = this.number_;
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
                int computeBytesSize = !this.hash_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.hash_) : 0;
                long j = this.number_;
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
                if (!(obj instanceof BlockIdentifier)) {
                    return super.equals(obj);
                }
                BlockIdentifier blockIdentifier = (BlockIdentifier) obj;
                return getHash().equals(blockIdentifier.getHash()) && getNumber() == blockIdentifier.getNumber() && this.unknownFields.equals(blockIdentifier.unknownFields);
            }

            @Override
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getHash().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getNumber())) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            public static BlockIdentifier parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static BlockIdentifier parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static BlockIdentifier parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static BlockIdentifier parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static BlockIdentifier parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static BlockIdentifier parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static BlockIdentifier parseFrom(InputStream inputStream) throws IOException {
                return (BlockIdentifier) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static BlockIdentifier parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (BlockIdentifier) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static BlockIdentifier parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (BlockIdentifier) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static BlockIdentifier parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (BlockIdentifier) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static BlockIdentifier parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (BlockIdentifier) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static BlockIdentifier parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (BlockIdentifier) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(BlockIdentifier blockIdentifier) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(blockIdentifier);
            }

            @Override
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            @Override
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BlockIdentifierOrBuilder {
                private ByteString hash_;
                private long number_;

                @Override
                public ByteString getHash() {
                    return this.hash_;
                }

                @Override
                public long getNumber() {
                    return this.number_;
                }

                @Override
                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return BalanceContract.internal_static_protocol_BlockBalanceTrace_BlockIdentifier_descriptor;
                }

                @Override
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return BalanceContract.internal_static_protocol_BlockBalanceTrace_BlockIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(BlockIdentifier.class, Builder.class);
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
                    boolean unused = BlockIdentifier.alwaysUseFieldBuilders;
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.hash_ = ByteString.EMPTY;
                    this.number_ = 0L;
                    return this;
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return BalanceContract.internal_static_protocol_BlockBalanceTrace_BlockIdentifier_descriptor;
                }

                @Override
                public BlockIdentifier getDefaultInstanceForType() {
                    return BlockIdentifier.getDefaultInstance();
                }

                @Override
                public BlockIdentifier build() {
                    BlockIdentifier buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override
                public BlockIdentifier buildPartial() {
                    BlockIdentifier blockIdentifier = new BlockIdentifier(this);
                    blockIdentifier.hash_ = this.hash_;
                    blockIdentifier.number_ = this.number_;
                    onBuilt();
                    return blockIdentifier;
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
                    if (message instanceof BlockIdentifier) {
                        return mergeFrom((BlockIdentifier) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(BlockIdentifier blockIdentifier) {
                    if (blockIdentifier == BlockIdentifier.getDefaultInstance()) {
                        return this;
                    }
                    if (blockIdentifier.getHash() != ByteString.EMPTY) {
                        setHash(blockIdentifier.getHash());
                    }
                    if (blockIdentifier.getNumber() != 0) {
                        setNumber(blockIdentifier.getNumber());
                    }
                    mergeUnknownFields(blockIdentifier.unknownFields);
                    onChanged();
                    return this;
                }

                @Override
                public org.tron.protos.contract.BalanceContract.BlockBalanceTrace.BlockIdentifier.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.BlockBalanceTrace.BlockIdentifier.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$BlockBalanceTrace$BlockIdentifier$Builder");
                }

                public Builder setHash(ByteString byteString) {
                    byteString.getClass();
                    this.hash_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearHash() {
                    this.hash_ = BlockIdentifier.getDefaultInstance().getHash();
                    onChanged();
                    return this;
                }

                public Builder setNumber(long j) {
                    this.number_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearNumber() {
                    this.number_ = 0L;
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
        public BlockIdentifier getBlockIdentifier() {
            BlockIdentifier blockIdentifier = this.blockIdentifier_;
            return blockIdentifier == null ? BlockIdentifier.getDefaultInstance() : blockIdentifier;
        }

        @Override
        public BlockIdentifierOrBuilder getBlockIdentifierOrBuilder() {
            return getBlockIdentifier();
        }

        @Override
        public int getTransactionBalanceTraceCount() {
            return this.transactionBalanceTrace_.size();
        }

        @Override
        public TransactionBalanceTrace getTransactionBalanceTrace(int i) {
            return this.transactionBalanceTrace_.get(i);
        }

        @Override
        public TransactionBalanceTraceOrBuilder getTransactionBalanceTraceOrBuilder(int i) {
            return this.transactionBalanceTrace_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.blockIdentifier_ != null) {
                codedOutputStream.writeMessage(1, getBlockIdentifier());
            }
            long j = this.timestamp_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            for (int i = 0; i < this.transactionBalanceTrace_.size(); i++) {
                codedOutputStream.writeMessage(3, this.transactionBalanceTrace_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.blockIdentifier_ != null ? CodedOutputStream.computeMessageSize(1, getBlockIdentifier()) : 0;
            long j = this.timestamp_;
            if (j != 0) {
                computeMessageSize += CodedOutputStream.computeInt64Size(2, j);
            }
            for (int i2 = 0; i2 < this.transactionBalanceTrace_.size(); i2++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, this.transactionBalanceTrace_.get(i2));
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
            if (!(obj instanceof BlockBalanceTrace)) {
                return super.equals(obj);
            }
            BlockBalanceTrace blockBalanceTrace = (BlockBalanceTrace) obj;
            boolean z = hasBlockIdentifier() == blockBalanceTrace.hasBlockIdentifier();
            if (!hasBlockIdentifier() ? z : !(!z || !getBlockIdentifier().equals(blockBalanceTrace.getBlockIdentifier()))) {
                if (getTimestamp() == blockBalanceTrace.getTimestamp() && getTransactionBalanceTraceList().equals(blockBalanceTrace.getTransactionBalanceTraceList()) && this.unknownFields.equals(blockBalanceTrace.unknownFields)) {
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
            if (hasBlockIdentifier()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getBlockIdentifier().hashCode();
            }
            int hashLong = (((hashCode * 37) + 2) * 53) + Internal.hashLong(getTimestamp());
            if (getTransactionBalanceTraceCount() > 0) {
                hashLong = (((hashLong * 37) + 3) * 53) + getTransactionBalanceTraceList().hashCode();
            }
            int hashCode2 = (hashLong * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static BlockBalanceTrace parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static BlockBalanceTrace parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BlockBalanceTrace parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static BlockBalanceTrace parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BlockBalanceTrace parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static BlockBalanceTrace parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BlockBalanceTrace parseFrom(InputStream inputStream) throws IOException {
            return (BlockBalanceTrace) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BlockBalanceTrace parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BlockBalanceTrace) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BlockBalanceTrace parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BlockBalanceTrace) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BlockBalanceTrace parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BlockBalanceTrace) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BlockBalanceTrace parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BlockBalanceTrace) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BlockBalanceTrace parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BlockBalanceTrace) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BlockBalanceTrace blockBalanceTrace) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(blockBalanceTrace);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BlockBalanceTraceOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<BlockIdentifier, BlockIdentifier.Builder, BlockIdentifierOrBuilder> blockIdentifierBuilder_;
            private BlockIdentifier blockIdentifier_;
            private long timestamp_;
            private RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> transactionBalanceTraceBuilder_;
            private List<TransactionBalanceTrace> transactionBalanceTrace_;

            @Override
            public long getTimestamp() {
                return this.timestamp_;
            }

            @Override
            public boolean hasBlockIdentifier() {
                return (this.blockIdentifierBuilder_ == null && this.blockIdentifier_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_BlockBalanceTrace_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_BlockBalanceTrace_fieldAccessorTable.ensureFieldAccessorsInitialized(BlockBalanceTrace.class, Builder.class);
            }

            private Builder() {
                this.blockIdentifier_ = null;
                this.transactionBalanceTrace_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.blockIdentifier_ = null;
                this.transactionBalanceTrace_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (BlockBalanceTrace.alwaysUseFieldBuilders) {
                    getTransactionBalanceTraceFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.blockIdentifierBuilder_ == null) {
                    this.blockIdentifier_ = null;
                } else {
                    this.blockIdentifier_ = null;
                    this.blockIdentifierBuilder_ = null;
                }
                this.timestamp_ = 0L;
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.transactionBalanceTrace_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_BlockBalanceTrace_descriptor;
            }

            @Override
            public BlockBalanceTrace getDefaultInstanceForType() {
                return BlockBalanceTrace.getDefaultInstance();
            }

            @Override
            public BlockBalanceTrace build() {
                BlockBalanceTrace buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public BlockBalanceTrace buildPartial() {
                BlockBalanceTrace blockBalanceTrace = new BlockBalanceTrace(this);
                SingleFieldBuilderV3<BlockIdentifier, BlockIdentifier.Builder, BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    blockBalanceTrace.blockIdentifier_ = this.blockIdentifier_;
                } else {
                    blockBalanceTrace.blockIdentifier_ = singleFieldBuilderV3.build();
                }
                blockBalanceTrace.timestamp_ = this.timestamp_;
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.transactionBalanceTrace_ = Collections.unmodifiableList(this.transactionBalanceTrace_);
                        this.bitField0_ &= -5;
                    }
                    blockBalanceTrace.transactionBalanceTrace_ = this.transactionBalanceTrace_;
                } else {
                    blockBalanceTrace.transactionBalanceTrace_ = repeatedFieldBuilderV3.build();
                }
                blockBalanceTrace.bitField0_ = 0;
                onBuilt();
                return blockBalanceTrace;
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
                if (message instanceof BlockBalanceTrace) {
                    return mergeFrom((BlockBalanceTrace) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BlockBalanceTrace blockBalanceTrace) {
                if (blockBalanceTrace == BlockBalanceTrace.getDefaultInstance()) {
                    return this;
                }
                if (blockBalanceTrace.hasBlockIdentifier()) {
                    mergeBlockIdentifier(blockBalanceTrace.getBlockIdentifier());
                }
                if (blockBalanceTrace.getTimestamp() != 0) {
                    setTimestamp(blockBalanceTrace.getTimestamp());
                }
                if (this.transactionBalanceTraceBuilder_ == null) {
                    if (!blockBalanceTrace.transactionBalanceTrace_.isEmpty()) {
                        if (this.transactionBalanceTrace_.isEmpty()) {
                            this.transactionBalanceTrace_ = blockBalanceTrace.transactionBalanceTrace_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureTransactionBalanceTraceIsMutable();
                            this.transactionBalanceTrace_.addAll(blockBalanceTrace.transactionBalanceTrace_);
                        }
                        onChanged();
                    }
                } else if (!blockBalanceTrace.transactionBalanceTrace_.isEmpty()) {
                    if (!this.transactionBalanceTraceBuilder_.isEmpty()) {
                        this.transactionBalanceTraceBuilder_.addAllMessages(blockBalanceTrace.transactionBalanceTrace_);
                    } else {
                        this.transactionBalanceTraceBuilder_.dispose();
                        this.transactionBalanceTraceBuilder_ = null;
                        this.transactionBalanceTrace_ = blockBalanceTrace.transactionBalanceTrace_;
                        this.bitField0_ &= -5;
                        this.transactionBalanceTraceBuilder_ = BlockBalanceTrace.alwaysUseFieldBuilders ? getTransactionBalanceTraceFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(blockBalanceTrace.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.BlockBalanceTrace.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.BlockBalanceTrace.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$BlockBalanceTrace$Builder");
            }

            @Override
            public BlockIdentifier getBlockIdentifier() {
                SingleFieldBuilderV3<BlockIdentifier, BlockIdentifier.Builder, BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BlockIdentifier blockIdentifier = this.blockIdentifier_;
                    return blockIdentifier == null ? BlockIdentifier.getDefaultInstance() : blockIdentifier;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setBlockIdentifier(BlockIdentifier blockIdentifier) {
                SingleFieldBuilderV3<BlockIdentifier, BlockIdentifier.Builder, BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    blockIdentifier.getClass();
                    this.blockIdentifier_ = blockIdentifier;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(blockIdentifier);
                }
                return this;
            }

            public Builder setBlockIdentifier(BlockIdentifier.Builder builder) {
                SingleFieldBuilderV3<BlockIdentifier, BlockIdentifier.Builder, BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.blockIdentifier_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeBlockIdentifier(BlockIdentifier blockIdentifier) {
                SingleFieldBuilderV3<BlockIdentifier, BlockIdentifier.Builder, BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BlockIdentifier blockIdentifier2 = this.blockIdentifier_;
                    if (blockIdentifier2 != null) {
                        this.blockIdentifier_ = BlockIdentifier.newBuilder(blockIdentifier2).mergeFrom(blockIdentifier).buildPartial();
                    } else {
                        this.blockIdentifier_ = blockIdentifier;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(blockIdentifier);
                }
                return this;
            }

            public Builder clearBlockIdentifier() {
                if (this.blockIdentifierBuilder_ == null) {
                    this.blockIdentifier_ = null;
                    onChanged();
                } else {
                    this.blockIdentifier_ = null;
                    this.blockIdentifierBuilder_ = null;
                }
                return this;
            }

            public BlockIdentifier.Builder getBlockIdentifierBuilder() {
                onChanged();
                return getBlockIdentifierFieldBuilder().getBuilder();
            }

            @Override
            public BlockIdentifierOrBuilder getBlockIdentifierOrBuilder() {
                SingleFieldBuilderV3<BlockIdentifier, BlockIdentifier.Builder, BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                BlockIdentifier blockIdentifier = this.blockIdentifier_;
                return blockIdentifier == null ? BlockIdentifier.getDefaultInstance() : blockIdentifier;
            }

            private SingleFieldBuilderV3<BlockIdentifier, BlockIdentifier.Builder, BlockIdentifierOrBuilder> getBlockIdentifierFieldBuilder() {
                if (this.blockIdentifierBuilder_ == null) {
                    this.blockIdentifierBuilder_ = new SingleFieldBuilderV3<>(getBlockIdentifier(), getParentForChildren(), isClean());
                    this.blockIdentifier_ = null;
                }
                return this.blockIdentifierBuilder_;
            }

            public Builder setTimestamp(long j) {
                this.timestamp_ = j;
                onChanged();
                return this;
            }

            public Builder clearTimestamp() {
                this.timestamp_ = 0L;
                onChanged();
                return this;
            }

            private void ensureTransactionBalanceTraceIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.transactionBalanceTrace_ = new ArrayList(this.transactionBalanceTrace_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public List<TransactionBalanceTrace> getTransactionBalanceTraceList() {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.transactionBalanceTrace_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getTransactionBalanceTraceCount() {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.transactionBalanceTrace_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public TransactionBalanceTrace getTransactionBalanceTrace(int i) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.transactionBalanceTrace_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setTransactionBalanceTrace(int i, TransactionBalanceTrace transactionBalanceTrace) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    transactionBalanceTrace.getClass();
                    ensureTransactionBalanceTraceIsMutable();
                    this.transactionBalanceTrace_.set(i, transactionBalanceTrace);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, transactionBalanceTrace);
                }
                return this;
            }

            public Builder setTransactionBalanceTrace(int i, TransactionBalanceTrace.Builder builder) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTransactionBalanceTraceIsMutable();
                    this.transactionBalanceTrace_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addTransactionBalanceTrace(TransactionBalanceTrace transactionBalanceTrace) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    transactionBalanceTrace.getClass();
                    ensureTransactionBalanceTraceIsMutable();
                    this.transactionBalanceTrace_.add(transactionBalanceTrace);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(transactionBalanceTrace);
                }
                return this;
            }

            public Builder addTransactionBalanceTrace(int i, TransactionBalanceTrace transactionBalanceTrace) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    transactionBalanceTrace.getClass();
                    ensureTransactionBalanceTraceIsMutable();
                    this.transactionBalanceTrace_.add(i, transactionBalanceTrace);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, transactionBalanceTrace);
                }
                return this;
            }

            public Builder addTransactionBalanceTrace(TransactionBalanceTrace.Builder builder) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTransactionBalanceTraceIsMutable();
                    this.transactionBalanceTrace_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addTransactionBalanceTrace(int i, TransactionBalanceTrace.Builder builder) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTransactionBalanceTraceIsMutable();
                    this.transactionBalanceTrace_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllTransactionBalanceTrace(Iterable<? extends TransactionBalanceTrace> iterable) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTransactionBalanceTraceIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.transactionBalanceTrace_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearTransactionBalanceTrace() {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.transactionBalanceTrace_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeTransactionBalanceTrace(int i) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTransactionBalanceTraceIsMutable();
                    this.transactionBalanceTrace_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public TransactionBalanceTrace.Builder getTransactionBalanceTraceBuilder(int i) {
                return getTransactionBalanceTraceFieldBuilder().getBuilder(i);
            }

            @Override
            public TransactionBalanceTraceOrBuilder getTransactionBalanceTraceOrBuilder(int i) {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.transactionBalanceTrace_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends TransactionBalanceTraceOrBuilder> getTransactionBalanceTraceOrBuilderList() {
                RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> repeatedFieldBuilderV3 = this.transactionBalanceTraceBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.transactionBalanceTrace_);
            }

            public TransactionBalanceTrace.Builder addTransactionBalanceTraceBuilder() {
                return getTransactionBalanceTraceFieldBuilder().addBuilder(TransactionBalanceTrace.getDefaultInstance());
            }

            public TransactionBalanceTrace.Builder addTransactionBalanceTraceBuilder(int i) {
                return getTransactionBalanceTraceFieldBuilder().addBuilder(i, TransactionBalanceTrace.getDefaultInstance());
            }

            public List<TransactionBalanceTrace.Builder> getTransactionBalanceTraceBuilderList() {
                return getTransactionBalanceTraceFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<TransactionBalanceTrace, TransactionBalanceTrace.Builder, TransactionBalanceTraceOrBuilder> getTransactionBalanceTraceFieldBuilder() {
                if (this.transactionBalanceTraceBuilder_ == null) {
                    this.transactionBalanceTraceBuilder_ = new RepeatedFieldBuilderV3<>(this.transactionBalanceTrace_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                    this.transactionBalanceTrace_ = null;
                }
                return this.transactionBalanceTraceBuilder_;
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

    public static final class AccountTrace extends GeneratedMessageV3 implements AccountTraceOrBuilder {
        public static final int BALANCE_FIELD_NUMBER = 1;
        private static final AccountTrace DEFAULT_INSTANCE = new AccountTrace();
        private static final Parser<AccountTrace> PARSER = new AbstractParser<AccountTrace>() {
            @Override
            public AccountTrace parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AccountTrace(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PLACEHOLDER_FIELD_NUMBER = 99;
        private static final long serialVersionUID = 0;
        private long balance_;
        private byte memoizedIsInitialized;
        private long placeholder_;

        public static AccountTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AccountTrace> parser() {
            return PARSER;
        }

        @Override
        public long getBalance() {
            return this.balance_;
        }

        @Override
        public AccountTrace getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<AccountTrace> getParserForType() {
            return PARSER;
        }

        @Override
        public long getPlaceholder() {
            return this.placeholder_;
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

        private AccountTrace(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private AccountTrace() {
            this.memoizedIsInitialized = (byte) -1;
            this.balance_ = 0L;
            this.placeholder_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AccountTrace(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 8) {
                                    this.balance_ = codedInputStream.readInt64();
                                } else if (readTag != 792) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.placeholder_ = codedInputStream.readInt64();
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
            return BalanceContract.internal_static_protocol_AccountTrace_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_AccountTrace_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountTrace.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            long j = this.balance_;
            if (j != 0) {
                codedOutputStream.writeInt64(1, j);
            }
            long j2 = this.placeholder_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(99, j2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            long j = this.balance_;
            int computeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(1, j) : 0;
            long j2 = this.placeholder_;
            if (j2 != 0) {
                computeInt64Size += CodedOutputStream.computeInt64Size(99, j2);
            }
            int serializedSize = computeInt64Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AccountTrace)) {
                return super.equals(obj);
            }
            AccountTrace accountTrace = (AccountTrace) obj;
            return getBalance() == accountTrace.getBalance() && getPlaceholder() == accountTrace.getPlaceholder() && this.unknownFields.equals(accountTrace.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getBalance())) * 37) + 99) * 53) + Internal.hashLong(getPlaceholder())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static AccountTrace parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AccountTrace parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AccountTrace parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AccountTrace parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AccountTrace parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AccountTrace parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AccountTrace parseFrom(InputStream inputStream) throws IOException {
            return (AccountTrace) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AccountTrace parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountTrace) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountTrace parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AccountTrace) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AccountTrace parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountTrace) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountTrace parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AccountTrace) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AccountTrace parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountTrace) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AccountTrace accountTrace) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(accountTrace);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AccountTraceOrBuilder {
            private long balance_;
            private long placeholder_;

            @Override
            public long getBalance() {
                return this.balance_;
            }

            @Override
            public long getPlaceholder() {
                return this.placeholder_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_AccountTrace_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_AccountTrace_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountTrace.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = AccountTrace.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.balance_ = 0L;
                this.placeholder_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_AccountTrace_descriptor;
            }

            @Override
            public AccountTrace getDefaultInstanceForType() {
                return AccountTrace.getDefaultInstance();
            }

            @Override
            public AccountTrace build() {
                AccountTrace buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public AccountTrace buildPartial() {
                AccountTrace accountTrace = new AccountTrace(this);
                accountTrace.balance_ = this.balance_;
                accountTrace.placeholder_ = this.placeholder_;
                onBuilt();
                return accountTrace;
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
                if (message instanceof AccountTrace) {
                    return mergeFrom((AccountTrace) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AccountTrace accountTrace) {
                if (accountTrace == AccountTrace.getDefaultInstance()) {
                    return this;
                }
                if (accountTrace.getBalance() != 0) {
                    setBalance(accountTrace.getBalance());
                }
                if (accountTrace.getPlaceholder() != 0) {
                    setPlaceholder(accountTrace.getPlaceholder());
                }
                mergeUnknownFields(accountTrace.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.AccountTrace.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.AccountTrace.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$AccountTrace$Builder");
            }

            public Builder setBalance(long j) {
                this.balance_ = j;
                onChanged();
                return this;
            }

            public Builder clearBalance() {
                this.balance_ = 0L;
                onChanged();
                return this;
            }

            public Builder setPlaceholder(long j) {
                this.placeholder_ = j;
                onChanged();
                return this;
            }

            public Builder clearPlaceholder() {
                this.placeholder_ = 0L;
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

    public static final class AccountIdentifier extends GeneratedMessageV3 implements AccountIdentifierOrBuilder {
        public static final int ADDRESS_FIELD_NUMBER = 1;
        private static final AccountIdentifier DEFAULT_INSTANCE = new AccountIdentifier();
        private static final Parser<AccountIdentifier> PARSER = new AbstractParser<AccountIdentifier>() {
            @Override
            public AccountIdentifier parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AccountIdentifier(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private ByteString address_;
        private byte memoizedIsInitialized;

        public static AccountIdentifier getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AccountIdentifier> parser() {
            return PARSER;
        }

        @Override
        public ByteString getAddress() {
            return this.address_;
        }

        @Override
        public AccountIdentifier getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<AccountIdentifier> getParserForType() {
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

        private AccountIdentifier(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private AccountIdentifier() {
            this.memoizedIsInitialized = (byte) -1;
            this.address_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AccountIdentifier(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.address_ = codedInputStream.readBytes();
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
            return BalanceContract.internal_static_protocol_AccountIdentifier_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_AccountIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountIdentifier.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.address_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.address_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (!this.address_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.address_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeBytesSize;
            return computeBytesSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AccountIdentifier)) {
                return super.equals(obj);
            }
            AccountIdentifier accountIdentifier = (AccountIdentifier) obj;
            return getAddress().equals(accountIdentifier.getAddress()) && this.unknownFields.equals(accountIdentifier.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static AccountIdentifier parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AccountIdentifier parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AccountIdentifier parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AccountIdentifier parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AccountIdentifier parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AccountIdentifier parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AccountIdentifier parseFrom(InputStream inputStream) throws IOException {
            return (AccountIdentifier) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AccountIdentifier parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountIdentifier) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountIdentifier parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AccountIdentifier) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AccountIdentifier parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountIdentifier) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountIdentifier parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AccountIdentifier) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AccountIdentifier parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountIdentifier) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AccountIdentifier accountIdentifier) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(accountIdentifier);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AccountIdentifierOrBuilder {
            private ByteString address_;

            @Override
            public ByteString getAddress() {
                return this.address_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_AccountIdentifier_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_AccountIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountIdentifier.class, Builder.class);
            }

            private Builder() {
                this.address_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.address_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = AccountIdentifier.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.address_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_AccountIdentifier_descriptor;
            }

            @Override
            public AccountIdentifier getDefaultInstanceForType() {
                return AccountIdentifier.getDefaultInstance();
            }

            @Override
            public AccountIdentifier build() {
                AccountIdentifier buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public AccountIdentifier buildPartial() {
                AccountIdentifier accountIdentifier = new AccountIdentifier(this);
                accountIdentifier.address_ = this.address_;
                onBuilt();
                return accountIdentifier;
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
                if (message instanceof AccountIdentifier) {
                    return mergeFrom((AccountIdentifier) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AccountIdentifier accountIdentifier) {
                if (accountIdentifier == AccountIdentifier.getDefaultInstance()) {
                    return this;
                }
                if (accountIdentifier.getAddress() != ByteString.EMPTY) {
                    setAddress(accountIdentifier.getAddress());
                }
                mergeUnknownFields(accountIdentifier.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.AccountIdentifier.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.AccountIdentifier.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$AccountIdentifier$Builder");
            }

            public Builder setAddress(ByteString byteString) {
                byteString.getClass();
                this.address_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAddress() {
                this.address_ = AccountIdentifier.getDefaultInstance().getAddress();
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

    public static final class AccountBalanceRequest extends GeneratedMessageV3 implements AccountBalanceRequestOrBuilder {
        public static final int ACCOUNT_IDENTIFIER_FIELD_NUMBER = 1;
        public static final int BLOCK_IDENTIFIER_FIELD_NUMBER = 2;
        private static final AccountBalanceRequest DEFAULT_INSTANCE = new AccountBalanceRequest();
        private static final Parser<AccountBalanceRequest> PARSER = new AbstractParser<AccountBalanceRequest>() {
            @Override
            public AccountBalanceRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AccountBalanceRequest(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private AccountIdentifier accountIdentifier_;
        private BlockBalanceTrace.BlockIdentifier blockIdentifier_;
        private byte memoizedIsInitialized;

        public static AccountBalanceRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AccountBalanceRequest> parser() {
            return PARSER;
        }

        @Override
        public AccountBalanceRequest getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<AccountBalanceRequest> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasAccountIdentifier() {
            return this.accountIdentifier_ != null;
        }

        @Override
        public boolean hasBlockIdentifier() {
            return this.blockIdentifier_ != null;
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

        private AccountBalanceRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private AccountBalanceRequest() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AccountBalanceRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                AccountIdentifier accountIdentifier = this.accountIdentifier_;
                                AccountIdentifier.Builder builder = accountIdentifier != null ? accountIdentifier.toBuilder() : null;
                                AccountIdentifier accountIdentifier2 = (AccountIdentifier) codedInputStream.readMessage(AccountIdentifier.parser(), extensionRegistryLite);
                                this.accountIdentifier_ = accountIdentifier2;
                                if (builder != null) {
                                    builder.mergeFrom(accountIdentifier2);
                                    this.accountIdentifier_ = builder.buildPartial();
                                }
                            } else if (readTag != 18) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                BlockBalanceTrace.BlockIdentifier blockIdentifier = this.blockIdentifier_;
                                BlockBalanceTrace.BlockIdentifier.Builder builder2 = blockIdentifier != null ? blockIdentifier.toBuilder() : null;
                                BlockBalanceTrace.BlockIdentifier blockIdentifier2 = (BlockBalanceTrace.BlockIdentifier) codedInputStream.readMessage(BlockBalanceTrace.BlockIdentifier.parser(), extensionRegistryLite);
                                this.blockIdentifier_ = blockIdentifier2;
                                if (builder2 != null) {
                                    builder2.mergeFrom(blockIdentifier2);
                                    this.blockIdentifier_ = builder2.buildPartial();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BalanceContract.internal_static_protocol_AccountBalanceRequest_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_AccountBalanceRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountBalanceRequest.class, Builder.class);
        }

        @Override
        public AccountIdentifier getAccountIdentifier() {
            AccountIdentifier accountIdentifier = this.accountIdentifier_;
            return accountIdentifier == null ? AccountIdentifier.getDefaultInstance() : accountIdentifier;
        }

        @Override
        public AccountIdentifierOrBuilder getAccountIdentifierOrBuilder() {
            return getAccountIdentifier();
        }

        @Override
        public BlockBalanceTrace.BlockIdentifier getBlockIdentifier() {
            BlockBalanceTrace.BlockIdentifier blockIdentifier = this.blockIdentifier_;
            return blockIdentifier == null ? BlockBalanceTrace.BlockIdentifier.getDefaultInstance() : blockIdentifier;
        }

        @Override
        public BlockBalanceTrace.BlockIdentifierOrBuilder getBlockIdentifierOrBuilder() {
            return getBlockIdentifier();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.accountIdentifier_ != null) {
                codedOutputStream.writeMessage(1, getAccountIdentifier());
            }
            if (this.blockIdentifier_ != null) {
                codedOutputStream.writeMessage(2, getBlockIdentifier());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.accountIdentifier_ != null ? CodedOutputStream.computeMessageSize(1, getAccountIdentifier()) : 0;
            if (this.blockIdentifier_ != null) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, getBlockIdentifier());
            }
            int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(java.lang.Object r5) {
            


return true;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.AccountBalanceRequest.equals(java.lang.Object):boolean");
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasAccountIdentifier()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getAccountIdentifier().hashCode();
            }
            if (hasBlockIdentifier()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getBlockIdentifier().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static AccountBalanceRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AccountBalanceRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AccountBalanceRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AccountBalanceRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AccountBalanceRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AccountBalanceRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AccountBalanceRequest parseFrom(InputStream inputStream) throws IOException {
            return (AccountBalanceRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AccountBalanceRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountBalanceRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountBalanceRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AccountBalanceRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AccountBalanceRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountBalanceRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountBalanceRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AccountBalanceRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AccountBalanceRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountBalanceRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AccountBalanceRequest accountBalanceRequest) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(accountBalanceRequest);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AccountBalanceRequestOrBuilder {
            private SingleFieldBuilderV3<AccountIdentifier, AccountIdentifier.Builder, AccountIdentifierOrBuilder> accountIdentifierBuilder_;
            private AccountIdentifier accountIdentifier_;
            private SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> blockIdentifierBuilder_;
            private BlockBalanceTrace.BlockIdentifier blockIdentifier_;

            @Override
            public boolean hasAccountIdentifier() {
                return (this.accountIdentifierBuilder_ == null && this.accountIdentifier_ == null) ? false : true;
            }

            @Override
            public boolean hasBlockIdentifier() {
                return (this.blockIdentifierBuilder_ == null && this.blockIdentifier_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_AccountBalanceRequest_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_AccountBalanceRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountBalanceRequest.class, Builder.class);
            }

            private Builder() {
                this.accountIdentifier_ = null;
                this.blockIdentifier_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.accountIdentifier_ = null;
                this.blockIdentifier_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = AccountBalanceRequest.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.accountIdentifierBuilder_ == null) {
                    this.accountIdentifier_ = null;
                } else {
                    this.accountIdentifier_ = null;
                    this.accountIdentifierBuilder_ = null;
                }
                if (this.blockIdentifierBuilder_ == null) {
                    this.blockIdentifier_ = null;
                } else {
                    this.blockIdentifier_ = null;
                    this.blockIdentifierBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_AccountBalanceRequest_descriptor;
            }

            @Override
            public AccountBalanceRequest getDefaultInstanceForType() {
                return AccountBalanceRequest.getDefaultInstance();
            }

            @Override
            public AccountBalanceRequest build() {
                AccountBalanceRequest buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public AccountBalanceRequest buildPartial() {
                AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest(this);
                SingleFieldBuilderV3<AccountIdentifier, AccountIdentifier.Builder, AccountIdentifierOrBuilder> singleFieldBuilderV3 = this.accountIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    accountBalanceRequest.accountIdentifier_ = this.accountIdentifier_;
                } else {
                    accountBalanceRequest.accountIdentifier_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV32 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV32 == null) {
                    accountBalanceRequest.blockIdentifier_ = this.blockIdentifier_;
                } else {
                    accountBalanceRequest.blockIdentifier_ = singleFieldBuilderV32.build();
                }
                onBuilt();
                return accountBalanceRequest;
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
                if (message instanceof AccountBalanceRequest) {
                    return mergeFrom((AccountBalanceRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AccountBalanceRequest accountBalanceRequest) {
                if (accountBalanceRequest == AccountBalanceRequest.getDefaultInstance()) {
                    return this;
                }
                if (accountBalanceRequest.hasAccountIdentifier()) {
                    mergeAccountIdentifier(accountBalanceRequest.getAccountIdentifier());
                }
                if (accountBalanceRequest.hasBlockIdentifier()) {
                    mergeBlockIdentifier(accountBalanceRequest.getBlockIdentifier());
                }
                mergeUnknownFields(accountBalanceRequest.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.AccountBalanceRequest.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.AccountBalanceRequest.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$AccountBalanceRequest$Builder");
            }

            @Override
            public AccountIdentifier getAccountIdentifier() {
                SingleFieldBuilderV3<AccountIdentifier, AccountIdentifier.Builder, AccountIdentifierOrBuilder> singleFieldBuilderV3 = this.accountIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AccountIdentifier accountIdentifier = this.accountIdentifier_;
                    return accountIdentifier == null ? AccountIdentifier.getDefaultInstance() : accountIdentifier;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setAccountIdentifier(AccountIdentifier accountIdentifier) {
                SingleFieldBuilderV3<AccountIdentifier, AccountIdentifier.Builder, AccountIdentifierOrBuilder> singleFieldBuilderV3 = this.accountIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    accountIdentifier.getClass();
                    this.accountIdentifier_ = accountIdentifier;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(accountIdentifier);
                }
                return this;
            }

            public Builder setAccountIdentifier(AccountIdentifier.Builder builder) {
                SingleFieldBuilderV3<AccountIdentifier, AccountIdentifier.Builder, AccountIdentifierOrBuilder> singleFieldBuilderV3 = this.accountIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.accountIdentifier_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeAccountIdentifier(AccountIdentifier accountIdentifier) {
                SingleFieldBuilderV3<AccountIdentifier, AccountIdentifier.Builder, AccountIdentifierOrBuilder> singleFieldBuilderV3 = this.accountIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AccountIdentifier accountIdentifier2 = this.accountIdentifier_;
                    if (accountIdentifier2 != null) {
                        this.accountIdentifier_ = AccountIdentifier.newBuilder(accountIdentifier2).mergeFrom(accountIdentifier).buildPartial();
                    } else {
                        this.accountIdentifier_ = accountIdentifier;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(accountIdentifier);
                }
                return this;
            }

            public Builder clearAccountIdentifier() {
                if (this.accountIdentifierBuilder_ == null) {
                    this.accountIdentifier_ = null;
                    onChanged();
                } else {
                    this.accountIdentifier_ = null;
                    this.accountIdentifierBuilder_ = null;
                }
                return this;
            }

            public AccountIdentifier.Builder getAccountIdentifierBuilder() {
                onChanged();
                return getAccountIdentifierFieldBuilder().getBuilder();
            }

            @Override
            public AccountIdentifierOrBuilder getAccountIdentifierOrBuilder() {
                SingleFieldBuilderV3<AccountIdentifier, AccountIdentifier.Builder, AccountIdentifierOrBuilder> singleFieldBuilderV3 = this.accountIdentifierBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AccountIdentifier accountIdentifier = this.accountIdentifier_;
                return accountIdentifier == null ? AccountIdentifier.getDefaultInstance() : accountIdentifier;
            }

            private SingleFieldBuilderV3<AccountIdentifier, AccountIdentifier.Builder, AccountIdentifierOrBuilder> getAccountIdentifierFieldBuilder() {
                if (this.accountIdentifierBuilder_ == null) {
                    this.accountIdentifierBuilder_ = new SingleFieldBuilderV3<>(getAccountIdentifier(), getParentForChildren(), isClean());
                    this.accountIdentifier_ = null;
                }
                return this.accountIdentifierBuilder_;
            }

            @Override
            public BlockBalanceTrace.BlockIdentifier getBlockIdentifier() {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BlockBalanceTrace.BlockIdentifier blockIdentifier = this.blockIdentifier_;
                    return blockIdentifier == null ? BlockBalanceTrace.BlockIdentifier.getDefaultInstance() : blockIdentifier;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setBlockIdentifier(BlockBalanceTrace.BlockIdentifier blockIdentifier) {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    blockIdentifier.getClass();
                    this.blockIdentifier_ = blockIdentifier;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(blockIdentifier);
                }
                return this;
            }

            public Builder setBlockIdentifier(BlockBalanceTrace.BlockIdentifier.Builder builder) {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.blockIdentifier_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeBlockIdentifier(BlockBalanceTrace.BlockIdentifier blockIdentifier) {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BlockBalanceTrace.BlockIdentifier blockIdentifier2 = this.blockIdentifier_;
                    if (blockIdentifier2 != null) {
                        this.blockIdentifier_ = BlockBalanceTrace.BlockIdentifier.newBuilder(blockIdentifier2).mergeFrom(blockIdentifier).buildPartial();
                    } else {
                        this.blockIdentifier_ = blockIdentifier;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(blockIdentifier);
                }
                return this;
            }

            public Builder clearBlockIdentifier() {
                if (this.blockIdentifierBuilder_ == null) {
                    this.blockIdentifier_ = null;
                    onChanged();
                } else {
                    this.blockIdentifier_ = null;
                    this.blockIdentifierBuilder_ = null;
                }
                return this;
            }

            public BlockBalanceTrace.BlockIdentifier.Builder getBlockIdentifierBuilder() {
                onChanged();
                return getBlockIdentifierFieldBuilder().getBuilder();
            }

            @Override
            public BlockBalanceTrace.BlockIdentifierOrBuilder getBlockIdentifierOrBuilder() {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                BlockBalanceTrace.BlockIdentifier blockIdentifier = this.blockIdentifier_;
                return blockIdentifier == null ? BlockBalanceTrace.BlockIdentifier.getDefaultInstance() : blockIdentifier;
            }

            private SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> getBlockIdentifierFieldBuilder() {
                if (this.blockIdentifierBuilder_ == null) {
                    this.blockIdentifierBuilder_ = new SingleFieldBuilderV3<>(getBlockIdentifier(), getParentForChildren(), isClean());
                    this.blockIdentifier_ = null;
                }
                return this.blockIdentifierBuilder_;
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

    public static final class AccountBalanceResponse extends GeneratedMessageV3 implements AccountBalanceResponseOrBuilder {
        public static final int BALANCE_FIELD_NUMBER = 1;
        public static final int BLOCK_IDENTIFIER_FIELD_NUMBER = 2;
        private static final AccountBalanceResponse DEFAULT_INSTANCE = new AccountBalanceResponse();
        private static final Parser<AccountBalanceResponse> PARSER = new AbstractParser<AccountBalanceResponse>() {
            @Override
            public AccountBalanceResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AccountBalanceResponse(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private long balance_;
        private BlockBalanceTrace.BlockIdentifier blockIdentifier_;
        private byte memoizedIsInitialized;

        public static AccountBalanceResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AccountBalanceResponse> parser() {
            return PARSER;
        }

        @Override
        public long getBalance() {
            return this.balance_;
        }

        @Override
        public AccountBalanceResponse getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<AccountBalanceResponse> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasBlockIdentifier() {
            return this.blockIdentifier_ != null;
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

        private AccountBalanceResponse(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private AccountBalanceResponse() {
            this.memoizedIsInitialized = (byte) -1;
            this.balance_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AccountBalanceResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.balance_ = codedInputStream.readInt64();
                            } else if (readTag != 18) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                BlockBalanceTrace.BlockIdentifier blockIdentifier = this.blockIdentifier_;
                                BlockBalanceTrace.BlockIdentifier.Builder builder = blockIdentifier != null ? blockIdentifier.toBuilder() : null;
                                BlockBalanceTrace.BlockIdentifier blockIdentifier2 = (BlockBalanceTrace.BlockIdentifier) codedInputStream.readMessage(BlockBalanceTrace.BlockIdentifier.parser(), extensionRegistryLite);
                                this.blockIdentifier_ = blockIdentifier2;
                                if (builder != null) {
                                    builder.mergeFrom(blockIdentifier2);
                                    this.blockIdentifier_ = builder.buildPartial();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BalanceContract.internal_static_protocol_AccountBalanceResponse_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_AccountBalanceResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountBalanceResponse.class, Builder.class);
        }

        @Override
        public BlockBalanceTrace.BlockIdentifier getBlockIdentifier() {
            BlockBalanceTrace.BlockIdentifier blockIdentifier = this.blockIdentifier_;
            return blockIdentifier == null ? BlockBalanceTrace.BlockIdentifier.getDefaultInstance() : blockIdentifier;
        }

        @Override
        public BlockBalanceTrace.BlockIdentifierOrBuilder getBlockIdentifierOrBuilder() {
            return getBlockIdentifier();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            long j = this.balance_;
            if (j != 0) {
                codedOutputStream.writeInt64(1, j);
            }
            if (this.blockIdentifier_ != null) {
                codedOutputStream.writeMessage(2, getBlockIdentifier());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            long j = this.balance_;
            int computeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(1, j) : 0;
            if (this.blockIdentifier_ != null) {
                computeInt64Size += CodedOutputStream.computeMessageSize(2, getBlockIdentifier());
            }
            int serializedSize = computeInt64Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AccountBalanceResponse)) {
                return super.equals(obj);
            }
            AccountBalanceResponse accountBalanceResponse = (AccountBalanceResponse) obj;
            boolean z = getBalance() == accountBalanceResponse.getBalance() && hasBlockIdentifier() == accountBalanceResponse.hasBlockIdentifier();
            if (!hasBlockIdentifier() ? z : !(!z || !getBlockIdentifier().equals(accountBalanceResponse.getBlockIdentifier()))) {
                if (this.unknownFields.equals(accountBalanceResponse.unknownFields)) {
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
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getBalance());
            if (hasBlockIdentifier()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getBlockIdentifier().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static AccountBalanceResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AccountBalanceResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AccountBalanceResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AccountBalanceResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AccountBalanceResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AccountBalanceResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AccountBalanceResponse parseFrom(InputStream inputStream) throws IOException {
            return (AccountBalanceResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AccountBalanceResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountBalanceResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountBalanceResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AccountBalanceResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AccountBalanceResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountBalanceResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountBalanceResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AccountBalanceResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AccountBalanceResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountBalanceResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AccountBalanceResponse accountBalanceResponse) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(accountBalanceResponse);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AccountBalanceResponseOrBuilder {
            private long balance_;
            private SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> blockIdentifierBuilder_;
            private BlockBalanceTrace.BlockIdentifier blockIdentifier_;

            @Override
            public long getBalance() {
                return this.balance_;
            }

            @Override
            public boolean hasBlockIdentifier() {
                return (this.blockIdentifierBuilder_ == null && this.blockIdentifier_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_AccountBalanceResponse_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_AccountBalanceResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountBalanceResponse.class, Builder.class);
            }

            private Builder() {
                this.blockIdentifier_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.blockIdentifier_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = AccountBalanceResponse.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.balance_ = 0L;
                if (this.blockIdentifierBuilder_ == null) {
                    this.blockIdentifier_ = null;
                } else {
                    this.blockIdentifier_ = null;
                    this.blockIdentifierBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_AccountBalanceResponse_descriptor;
            }

            @Override
            public AccountBalanceResponse getDefaultInstanceForType() {
                return AccountBalanceResponse.getDefaultInstance();
            }

            @Override
            public AccountBalanceResponse build() {
                AccountBalanceResponse buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public AccountBalanceResponse buildPartial() {
                AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse(this);
                accountBalanceResponse.balance_ = this.balance_;
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    accountBalanceResponse.blockIdentifier_ = this.blockIdentifier_;
                } else {
                    accountBalanceResponse.blockIdentifier_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return accountBalanceResponse;
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
                if (message instanceof AccountBalanceResponse) {
                    return mergeFrom((AccountBalanceResponse) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AccountBalanceResponse accountBalanceResponse) {
                if (accountBalanceResponse == AccountBalanceResponse.getDefaultInstance()) {
                    return this;
                }
                if (accountBalanceResponse.getBalance() != 0) {
                    setBalance(accountBalanceResponse.getBalance());
                }
                if (accountBalanceResponse.hasBlockIdentifier()) {
                    mergeBlockIdentifier(accountBalanceResponse.getBlockIdentifier());
                }
                mergeUnknownFields(accountBalanceResponse.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.AccountBalanceResponse.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.AccountBalanceResponse.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$AccountBalanceResponse$Builder");
            }

            public Builder setBalance(long j) {
                this.balance_ = j;
                onChanged();
                return this;
            }

            public Builder clearBalance() {
                this.balance_ = 0L;
                onChanged();
                return this;
            }

            @Override
            public BlockBalanceTrace.BlockIdentifier getBlockIdentifier() {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BlockBalanceTrace.BlockIdentifier blockIdentifier = this.blockIdentifier_;
                    return blockIdentifier == null ? BlockBalanceTrace.BlockIdentifier.getDefaultInstance() : blockIdentifier;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setBlockIdentifier(BlockBalanceTrace.BlockIdentifier blockIdentifier) {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    blockIdentifier.getClass();
                    this.blockIdentifier_ = blockIdentifier;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(blockIdentifier);
                }
                return this;
            }

            public Builder setBlockIdentifier(BlockBalanceTrace.BlockIdentifier.Builder builder) {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.blockIdentifier_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeBlockIdentifier(BlockBalanceTrace.BlockIdentifier blockIdentifier) {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BlockBalanceTrace.BlockIdentifier blockIdentifier2 = this.blockIdentifier_;
                    if (blockIdentifier2 != null) {
                        this.blockIdentifier_ = BlockBalanceTrace.BlockIdentifier.newBuilder(blockIdentifier2).mergeFrom(blockIdentifier).buildPartial();
                    } else {
                        this.blockIdentifier_ = blockIdentifier;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(blockIdentifier);
                }
                return this;
            }

            public Builder clearBlockIdentifier() {
                if (this.blockIdentifierBuilder_ == null) {
                    this.blockIdentifier_ = null;
                    onChanged();
                } else {
                    this.blockIdentifier_ = null;
                    this.blockIdentifierBuilder_ = null;
                }
                return this;
            }

            public BlockBalanceTrace.BlockIdentifier.Builder getBlockIdentifierBuilder() {
                onChanged();
                return getBlockIdentifierFieldBuilder().getBuilder();
            }

            @Override
            public BlockBalanceTrace.BlockIdentifierOrBuilder getBlockIdentifierOrBuilder() {
                SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> singleFieldBuilderV3 = this.blockIdentifierBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                BlockBalanceTrace.BlockIdentifier blockIdentifier = this.blockIdentifier_;
                return blockIdentifier == null ? BlockBalanceTrace.BlockIdentifier.getDefaultInstance() : blockIdentifier;
            }

            private SingleFieldBuilderV3<BlockBalanceTrace.BlockIdentifier, BlockBalanceTrace.BlockIdentifier.Builder, BlockBalanceTrace.BlockIdentifierOrBuilder> getBlockIdentifierFieldBuilder() {
                if (this.blockIdentifierBuilder_ == null) {
                    this.blockIdentifierBuilder_ = new SingleFieldBuilderV3<>(getBlockIdentifier(), getParentForChildren(), isClean());
                    this.blockIdentifier_ = null;
                }
                return this.blockIdentifierBuilder_;
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

    public static final class FreezeBalanceV2Contract extends GeneratedMessageV3 implements FreezeBalanceV2ContractOrBuilder {
        public static final int FROZEN_BALANCE_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int RESOURCE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private long frozenBalance_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private int resource_;
        private static final FreezeBalanceV2Contract DEFAULT_INSTANCE = new FreezeBalanceV2Contract();
        private static final Parser<FreezeBalanceV2Contract> PARSER = new AbstractParser<FreezeBalanceV2Contract>() {
            @Override
            public FreezeBalanceV2Contract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new FreezeBalanceV2Contract(codedInputStream, extensionRegistryLite);
            }
        };

        public static FreezeBalanceV2Contract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FreezeBalanceV2Contract> parser() {
            return PARSER;
        }

        @Override
        public FreezeBalanceV2Contract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public long getFrozenBalance() {
            return this.frozenBalance_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<FreezeBalanceV2Contract> getParserForType() {
            return PARSER;
        }

        @Override
        public int getResourceValue() {
            return this.resource_;
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

        private FreezeBalanceV2Contract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private FreezeBalanceV2Contract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.frozenBalance_ = 0L;
            this.resource_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FreezeBalanceV2Contract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.frozenBalance_ = codedInputStream.readInt64();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.resource_ = codedInputStream.readEnum();
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
            return BalanceContract.internal_static_protocol_FreezeBalanceV2Contract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_FreezeBalanceV2Contract_fieldAccessorTable.ensureFieldAccessorsInitialized(FreezeBalanceV2Contract.class, Builder.class);
        }

        @Override
        public Common.ResourceCode getResource() {
            Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
            return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.frozenBalance_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                codedOutputStream.writeEnum(3, this.resource_);
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
            long j = this.frozenBalance_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                computeBytesSize += CodedOutputStream.computeEnumSize(3, this.resource_);
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
            if (!(obj instanceof FreezeBalanceV2Contract)) {
                return super.equals(obj);
            }
            FreezeBalanceV2Contract freezeBalanceV2Contract = (FreezeBalanceV2Contract) obj;
            return getOwnerAddress().equals(freezeBalanceV2Contract.getOwnerAddress()) && getFrozenBalance() == freezeBalanceV2Contract.getFrozenBalance() && this.resource_ == freezeBalanceV2Contract.resource_ && this.unknownFields.equals(freezeBalanceV2Contract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getFrozenBalance())) * 37) + 3) * 53) + this.resource_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static FreezeBalanceV2Contract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static FreezeBalanceV2Contract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static FreezeBalanceV2Contract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static FreezeBalanceV2Contract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FreezeBalanceV2Contract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static FreezeBalanceV2Contract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static FreezeBalanceV2Contract parseFrom(InputStream inputStream) throws IOException {
            return (FreezeBalanceV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static FreezeBalanceV2Contract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FreezeBalanceV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FreezeBalanceV2Contract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FreezeBalanceV2Contract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static FreezeBalanceV2Contract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FreezeBalanceV2Contract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FreezeBalanceV2Contract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FreezeBalanceV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static FreezeBalanceV2Contract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FreezeBalanceV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FreezeBalanceV2Contract freezeBalanceV2Contract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(freezeBalanceV2Contract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FreezeBalanceV2ContractOrBuilder {
            private long frozenBalance_;
            private ByteString ownerAddress_;
            private int resource_;

            @Override
            public long getFrozenBalance() {
                return this.frozenBalance_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public int getResourceValue() {
                return this.resource_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_FreezeBalanceV2Contract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_FreezeBalanceV2Contract_fieldAccessorTable.ensureFieldAccessorsInitialized(FreezeBalanceV2Contract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = FreezeBalanceV2Contract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.frozenBalance_ = 0L;
                this.resource_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_FreezeBalanceV2Contract_descriptor;
            }

            @Override
            public FreezeBalanceV2Contract getDefaultInstanceForType() {
                return FreezeBalanceV2Contract.getDefaultInstance();
            }

            @Override
            public FreezeBalanceV2Contract build() {
                FreezeBalanceV2Contract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public FreezeBalanceV2Contract buildPartial() {
                FreezeBalanceV2Contract freezeBalanceV2Contract = new FreezeBalanceV2Contract(this);
                freezeBalanceV2Contract.ownerAddress_ = this.ownerAddress_;
                freezeBalanceV2Contract.frozenBalance_ = this.frozenBalance_;
                freezeBalanceV2Contract.resource_ = this.resource_;
                onBuilt();
                return freezeBalanceV2Contract;
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
                if (message instanceof FreezeBalanceV2Contract) {
                    return mergeFrom((FreezeBalanceV2Contract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(FreezeBalanceV2Contract freezeBalanceV2Contract) {
                if (freezeBalanceV2Contract == FreezeBalanceV2Contract.getDefaultInstance()) {
                    return this;
                }
                if (freezeBalanceV2Contract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(freezeBalanceV2Contract.getOwnerAddress());
                }
                if (freezeBalanceV2Contract.getFrozenBalance() != 0) {
                    setFrozenBalance(freezeBalanceV2Contract.getFrozenBalance());
                }
                if (freezeBalanceV2Contract.resource_ != 0) {
                    setResourceValue(freezeBalanceV2Contract.getResourceValue());
                }
                mergeUnknownFields(freezeBalanceV2Contract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.FreezeBalanceV2Contract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.FreezeBalanceV2Contract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$FreezeBalanceV2Contract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = FreezeBalanceV2Contract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setFrozenBalance(long j) {
                this.frozenBalance_ = j;
                onChanged();
                return this;
            }

            public Builder clearFrozenBalance() {
                this.frozenBalance_ = 0L;
                onChanged();
                return this;
            }

            public Builder setResourceValue(int i) {
                this.resource_ = i;
                onChanged();
                return this;
            }

            @Override
            public Common.ResourceCode getResource() {
                Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
                return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
            }

            public Builder setResource(Common.ResourceCode resourceCode) {
                resourceCode.getClass();
                this.resource_ = resourceCode.getNumber();
                onChanged();
                return this;
            }

            public Builder clearResource() {
                this.resource_ = 0;
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

    public static final class UnfreezeBalanceV2Contract extends GeneratedMessageV3 implements UnfreezeBalanceV2ContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int RESOURCE_FIELD_NUMBER = 3;
        public static final int UNFREEZE_BALANCE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private int resource_;
        private long unfreezeBalance_;
        private static final UnfreezeBalanceV2Contract DEFAULT_INSTANCE = new UnfreezeBalanceV2Contract();
        private static final Parser<UnfreezeBalanceV2Contract> PARSER = new AbstractParser<UnfreezeBalanceV2Contract>() {
            @Override
            public UnfreezeBalanceV2Contract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UnfreezeBalanceV2Contract(codedInputStream, extensionRegistryLite);
            }
        };

        public static UnfreezeBalanceV2Contract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UnfreezeBalanceV2Contract> parser() {
            return PARSER;
        }

        @Override
        public UnfreezeBalanceV2Contract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<UnfreezeBalanceV2Contract> getParserForType() {
            return PARSER;
        }

        @Override
        public int getResourceValue() {
            return this.resource_;
        }

        @Override
        public long getUnfreezeBalance() {
            return this.unfreezeBalance_;
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

        private UnfreezeBalanceV2Contract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UnfreezeBalanceV2Contract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.unfreezeBalance_ = 0L;
            this.resource_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UnfreezeBalanceV2Contract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.unfreezeBalance_ = codedInputStream.readInt64();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.resource_ = codedInputStream.readEnum();
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
            return BalanceContract.internal_static_protocol_UnfreezeBalanceV2Contract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_UnfreezeBalanceV2Contract_fieldAccessorTable.ensureFieldAccessorsInitialized(UnfreezeBalanceV2Contract.class, Builder.class);
        }

        @Override
        public Common.ResourceCode getResource() {
            Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
            return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            long j = this.unfreezeBalance_;
            if (j != 0) {
                codedOutputStream.writeInt64(2, j);
            }
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                codedOutputStream.writeEnum(3, this.resource_);
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
            long j = this.unfreezeBalance_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(2, j);
            }
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                computeBytesSize += CodedOutputStream.computeEnumSize(3, this.resource_);
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
            if (!(obj instanceof UnfreezeBalanceV2Contract)) {
                return super.equals(obj);
            }
            UnfreezeBalanceV2Contract unfreezeBalanceV2Contract = (UnfreezeBalanceV2Contract) obj;
            return getOwnerAddress().equals(unfreezeBalanceV2Contract.getOwnerAddress()) && getUnfreezeBalance() == unfreezeBalanceV2Contract.getUnfreezeBalance() && this.resource_ == unfreezeBalanceV2Contract.resource_ && this.unknownFields.equals(unfreezeBalanceV2Contract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getUnfreezeBalance())) * 37) + 3) * 53) + this.resource_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static UnfreezeBalanceV2Contract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UnfreezeBalanceV2Contract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UnfreezeBalanceV2Contract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UnfreezeBalanceV2Contract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UnfreezeBalanceV2Contract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UnfreezeBalanceV2Contract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UnfreezeBalanceV2Contract parseFrom(InputStream inputStream) throws IOException {
            return (UnfreezeBalanceV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UnfreezeBalanceV2Contract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnfreezeBalanceV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnfreezeBalanceV2Contract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnfreezeBalanceV2Contract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UnfreezeBalanceV2Contract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnfreezeBalanceV2Contract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnfreezeBalanceV2Contract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnfreezeBalanceV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UnfreezeBalanceV2Contract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnfreezeBalanceV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UnfreezeBalanceV2Contract unfreezeBalanceV2Contract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(unfreezeBalanceV2Contract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UnfreezeBalanceV2ContractOrBuilder {
            private ByteString ownerAddress_;
            private int resource_;
            private long unfreezeBalance_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public int getResourceValue() {
                return this.resource_;
            }

            @Override
            public long getUnfreezeBalance() {
                return this.unfreezeBalance_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_UnfreezeBalanceV2Contract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_UnfreezeBalanceV2Contract_fieldAccessorTable.ensureFieldAccessorsInitialized(UnfreezeBalanceV2Contract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UnfreezeBalanceV2Contract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.unfreezeBalance_ = 0L;
                this.resource_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_UnfreezeBalanceV2Contract_descriptor;
            }

            @Override
            public UnfreezeBalanceV2Contract getDefaultInstanceForType() {
                return UnfreezeBalanceV2Contract.getDefaultInstance();
            }

            @Override
            public UnfreezeBalanceV2Contract build() {
                UnfreezeBalanceV2Contract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public UnfreezeBalanceV2Contract buildPartial() {
                UnfreezeBalanceV2Contract unfreezeBalanceV2Contract = new UnfreezeBalanceV2Contract(this);
                unfreezeBalanceV2Contract.ownerAddress_ = this.ownerAddress_;
                unfreezeBalanceV2Contract.unfreezeBalance_ = this.unfreezeBalance_;
                unfreezeBalanceV2Contract.resource_ = this.resource_;
                onBuilt();
                return unfreezeBalanceV2Contract;
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
                if (message instanceof UnfreezeBalanceV2Contract) {
                    return mergeFrom((UnfreezeBalanceV2Contract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UnfreezeBalanceV2Contract unfreezeBalanceV2Contract) {
                if (unfreezeBalanceV2Contract == UnfreezeBalanceV2Contract.getDefaultInstance()) {
                    return this;
                }
                if (unfreezeBalanceV2Contract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(unfreezeBalanceV2Contract.getOwnerAddress());
                }
                if (unfreezeBalanceV2Contract.getUnfreezeBalance() != 0) {
                    setUnfreezeBalance(unfreezeBalanceV2Contract.getUnfreezeBalance());
                }
                if (unfreezeBalanceV2Contract.resource_ != 0) {
                    setResourceValue(unfreezeBalanceV2Contract.getResourceValue());
                }
                mergeUnknownFields(unfreezeBalanceV2Contract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.UnfreezeBalanceV2Contract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.UnfreezeBalanceV2Contract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$UnfreezeBalanceV2Contract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = UnfreezeBalanceV2Contract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setUnfreezeBalance(long j) {
                this.unfreezeBalance_ = j;
                onChanged();
                return this;
            }

            public Builder clearUnfreezeBalance() {
                this.unfreezeBalance_ = 0L;
                onChanged();
                return this;
            }

            public Builder setResourceValue(int i) {
                this.resource_ = i;
                onChanged();
                return this;
            }

            @Override
            public Common.ResourceCode getResource() {
                Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
                return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
            }

            public Builder setResource(Common.ResourceCode resourceCode) {
                resourceCode.getClass();
                this.resource_ = resourceCode.getNumber();
                onChanged();
                return this;
            }

            public Builder clearResource() {
                this.resource_ = 0;
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

    public static final class WithdrawExpireUnfreezeContract extends GeneratedMessageV3 implements WithdrawExpireUnfreezeContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final WithdrawExpireUnfreezeContract DEFAULT_INSTANCE = new WithdrawExpireUnfreezeContract();
        private static final Parser<WithdrawExpireUnfreezeContract> PARSER = new AbstractParser<WithdrawExpireUnfreezeContract>() {
            @Override
            public WithdrawExpireUnfreezeContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new WithdrawExpireUnfreezeContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static WithdrawExpireUnfreezeContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WithdrawExpireUnfreezeContract> parser() {
            return PARSER;
        }

        @Override
        public WithdrawExpireUnfreezeContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<WithdrawExpireUnfreezeContract> getParserForType() {
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

        private WithdrawExpireUnfreezeContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private WithdrawExpireUnfreezeContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private WithdrawExpireUnfreezeContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.ownerAddress_ = codedInputStream.readBytes();
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
            return BalanceContract.internal_static_protocol_WithdrawExpireUnfreezeContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_WithdrawExpireUnfreezeContract_fieldAccessorTable.ensureFieldAccessorsInitialized(WithdrawExpireUnfreezeContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (!this.ownerAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.ownerAddress_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeBytesSize;
            return computeBytesSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof WithdrawExpireUnfreezeContract)) {
                return super.equals(obj);
            }
            WithdrawExpireUnfreezeContract withdrawExpireUnfreezeContract = (WithdrawExpireUnfreezeContract) obj;
            return getOwnerAddress().equals(withdrawExpireUnfreezeContract.getOwnerAddress()) && this.unknownFields.equals(withdrawExpireUnfreezeContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static WithdrawExpireUnfreezeContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static WithdrawExpireUnfreezeContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static WithdrawExpireUnfreezeContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static WithdrawExpireUnfreezeContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static WithdrawExpireUnfreezeContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static WithdrawExpireUnfreezeContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static WithdrawExpireUnfreezeContract parseFrom(InputStream inputStream) throws IOException {
            return (WithdrawExpireUnfreezeContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static WithdrawExpireUnfreezeContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WithdrawExpireUnfreezeContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static WithdrawExpireUnfreezeContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (WithdrawExpireUnfreezeContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static WithdrawExpireUnfreezeContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WithdrawExpireUnfreezeContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static WithdrawExpireUnfreezeContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (WithdrawExpireUnfreezeContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static WithdrawExpireUnfreezeContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WithdrawExpireUnfreezeContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WithdrawExpireUnfreezeContract withdrawExpireUnfreezeContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(withdrawExpireUnfreezeContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WithdrawExpireUnfreezeContractOrBuilder {
            private ByteString ownerAddress_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_WithdrawExpireUnfreezeContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_WithdrawExpireUnfreezeContract_fieldAccessorTable.ensureFieldAccessorsInitialized(WithdrawExpireUnfreezeContract.class, Builder.class);
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
                boolean unused = WithdrawExpireUnfreezeContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_WithdrawExpireUnfreezeContract_descriptor;
            }

            @Override
            public WithdrawExpireUnfreezeContract getDefaultInstanceForType() {
                return WithdrawExpireUnfreezeContract.getDefaultInstance();
            }

            @Override
            public WithdrawExpireUnfreezeContract build() {
                WithdrawExpireUnfreezeContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public WithdrawExpireUnfreezeContract buildPartial() {
                WithdrawExpireUnfreezeContract withdrawExpireUnfreezeContract = new WithdrawExpireUnfreezeContract(this);
                withdrawExpireUnfreezeContract.ownerAddress_ = this.ownerAddress_;
                onBuilt();
                return withdrawExpireUnfreezeContract;
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
                if (message instanceof WithdrawExpireUnfreezeContract) {
                    return mergeFrom((WithdrawExpireUnfreezeContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(WithdrawExpireUnfreezeContract withdrawExpireUnfreezeContract) {
                if (withdrawExpireUnfreezeContract == WithdrawExpireUnfreezeContract.getDefaultInstance()) {
                    return this;
                }
                if (withdrawExpireUnfreezeContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(withdrawExpireUnfreezeContract.getOwnerAddress());
                }
                mergeUnknownFields(withdrawExpireUnfreezeContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.WithdrawExpireUnfreezeContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.WithdrawExpireUnfreezeContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$WithdrawExpireUnfreezeContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = WithdrawExpireUnfreezeContract.getDefaultInstance().getOwnerAddress();
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

    public static final class DelegateResourceContract extends GeneratedMessageV3 implements DelegateResourceContractOrBuilder {
        public static final int BALANCE_FIELD_NUMBER = 3;
        public static final int LOCK_FIELD_NUMBER = 5;
        public static final int LOCK_PERIOD_FIELD_NUMBER = 6;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int RECEIVER_ADDRESS_FIELD_NUMBER = 4;
        public static final int RESOURCE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private long balance_;
        private long lockPeriod_;
        private boolean lock_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString receiverAddress_;
        private int resource_;
        private static final DelegateResourceContract DEFAULT_INSTANCE = new DelegateResourceContract();
        private static final Parser<DelegateResourceContract> PARSER = new AbstractParser<DelegateResourceContract>() {
            @Override
            public DelegateResourceContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DelegateResourceContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static DelegateResourceContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DelegateResourceContract> parser() {
            return PARSER;
        }

        @Override
        public long getBalance() {
            return this.balance_;
        }

        @Override
        public DelegateResourceContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public boolean getLock() {
            return this.lock_;
        }

        @Override
        public long getLockPeriod() {
            return this.lockPeriod_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<DelegateResourceContract> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getReceiverAddress() {
            return this.receiverAddress_;
        }

        @Override
        public int getResourceValue() {
            return this.resource_;
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

        private DelegateResourceContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private DelegateResourceContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.resource_ = 0;
            this.balance_ = 0L;
            this.receiverAddress_ = ByteString.EMPTY;
            this.lock_ = false;
            this.lockPeriod_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DelegateResourceContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.resource_ = codedInputStream.readEnum();
                            } else if (readTag == 24) {
                                this.balance_ = codedInputStream.readInt64();
                            } else if (readTag == 34) {
                                this.receiverAddress_ = codedInputStream.readBytes();
                            } else if (readTag == 40) {
                                this.lock_ = codedInputStream.readBool();
                            } else if (readTag != 48) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.lockPeriod_ = codedInputStream.readInt64();
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
            return BalanceContract.internal_static_protocol_DelegateResourceContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_DelegateResourceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(DelegateResourceContract.class, Builder.class);
        }

        @Override
        public Common.ResourceCode getResource() {
            Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
            return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                codedOutputStream.writeEnum(2, this.resource_);
            }
            long j = this.balance_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            if (!this.receiverAddress_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.receiverAddress_);
            }
            boolean z = this.lock_;
            if (z) {
                codedOutputStream.writeBool(5, z);
            }
            long j2 = this.lockPeriod_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(6, j2);
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
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                computeBytesSize += CodedOutputStream.computeEnumSize(2, this.resource_);
            }
            long j = this.balance_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(3, j);
            }
            if (!this.receiverAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(4, this.receiverAddress_);
            }
            boolean z = this.lock_;
            if (z) {
                computeBytesSize += CodedOutputStream.computeBoolSize(5, z);
            }
            long j2 = this.lockPeriod_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(6, j2);
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
            if (!(obj instanceof DelegateResourceContract)) {
                return super.equals(obj);
            }
            DelegateResourceContract delegateResourceContract = (DelegateResourceContract) obj;
            return getOwnerAddress().equals(delegateResourceContract.getOwnerAddress()) && this.resource_ == delegateResourceContract.resource_ && getBalance() == delegateResourceContract.getBalance() && getReceiverAddress().equals(delegateResourceContract.getReceiverAddress()) && getLock() == delegateResourceContract.getLock() && getLockPeriod() == delegateResourceContract.getLockPeriod() && this.unknownFields.equals(delegateResourceContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + this.resource_) * 37) + 3) * 53) + Internal.hashLong(getBalance())) * 37) + 4) * 53) + getReceiverAddress().hashCode()) * 37) + 5) * 53) + Internal.hashBoolean(getLock())) * 37) + 6) * 53) + Internal.hashLong(getLockPeriod())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static DelegateResourceContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static DelegateResourceContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static DelegateResourceContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static DelegateResourceContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static DelegateResourceContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static DelegateResourceContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static DelegateResourceContract parseFrom(InputStream inputStream) throws IOException {
            return (DelegateResourceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static DelegateResourceContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DelegateResourceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DelegateResourceContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DelegateResourceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static DelegateResourceContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DelegateResourceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DelegateResourceContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DelegateResourceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static DelegateResourceContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DelegateResourceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DelegateResourceContract delegateResourceContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(delegateResourceContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DelegateResourceContractOrBuilder {
            private long balance_;
            private long lockPeriod_;
            private boolean lock_;
            private ByteString ownerAddress_;
            private ByteString receiverAddress_;
            private int resource_;

            @Override
            public long getBalance() {
                return this.balance_;
            }

            @Override
            public boolean getLock() {
                return this.lock_;
            }

            @Override
            public long getLockPeriod() {
                return this.lockPeriod_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public ByteString getReceiverAddress() {
                return this.receiverAddress_;
            }

            @Override
            public int getResourceValue() {
                return this.resource_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_DelegateResourceContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_DelegateResourceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(DelegateResourceContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = DelegateResourceContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.balance_ = 0L;
                this.receiverAddress_ = ByteString.EMPTY;
                this.lock_ = false;
                this.lockPeriod_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_DelegateResourceContract_descriptor;
            }

            @Override
            public DelegateResourceContract getDefaultInstanceForType() {
                return DelegateResourceContract.getDefaultInstance();
            }

            @Override
            public DelegateResourceContract build() {
                DelegateResourceContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public DelegateResourceContract buildPartial() {
                DelegateResourceContract delegateResourceContract = new DelegateResourceContract(this);
                delegateResourceContract.ownerAddress_ = this.ownerAddress_;
                delegateResourceContract.resource_ = this.resource_;
                delegateResourceContract.balance_ = this.balance_;
                delegateResourceContract.receiverAddress_ = this.receiverAddress_;
                delegateResourceContract.lock_ = this.lock_;
                delegateResourceContract.lockPeriod_ = this.lockPeriod_;
                onBuilt();
                return delegateResourceContract;
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
                if (message instanceof DelegateResourceContract) {
                    return mergeFrom((DelegateResourceContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(DelegateResourceContract delegateResourceContract) {
                if (delegateResourceContract == DelegateResourceContract.getDefaultInstance()) {
                    return this;
                }
                if (delegateResourceContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(delegateResourceContract.getOwnerAddress());
                }
                if (delegateResourceContract.resource_ != 0) {
                    setResourceValue(delegateResourceContract.getResourceValue());
                }
                if (delegateResourceContract.getBalance() != 0) {
                    setBalance(delegateResourceContract.getBalance());
                }
                if (delegateResourceContract.getReceiverAddress() != ByteString.EMPTY) {
                    setReceiverAddress(delegateResourceContract.getReceiverAddress());
                }
                if (delegateResourceContract.getLock()) {
                    setLock(delegateResourceContract.getLock());
                }
                if (delegateResourceContract.getLockPeriod() != 0) {
                    setLockPeriod(delegateResourceContract.getLockPeriod());
                }
                mergeUnknownFields(delegateResourceContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.DelegateResourceContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.DelegateResourceContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$DelegateResourceContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = DelegateResourceContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setResourceValue(int i) {
                this.resource_ = i;
                onChanged();
                return this;
            }

            @Override
            public Common.ResourceCode getResource() {
                Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
                return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
            }

            public Builder setResource(Common.ResourceCode resourceCode) {
                resourceCode.getClass();
                this.resource_ = resourceCode.getNumber();
                onChanged();
                return this;
            }

            public Builder clearResource() {
                this.resource_ = 0;
                onChanged();
                return this;
            }

            public Builder setBalance(long j) {
                this.balance_ = j;
                onChanged();
                return this;
            }

            public Builder clearBalance() {
                this.balance_ = 0L;
                onChanged();
                return this;
            }

            public Builder setReceiverAddress(ByteString byteString) {
                byteString.getClass();
                this.receiverAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearReceiverAddress() {
                this.receiverAddress_ = DelegateResourceContract.getDefaultInstance().getReceiverAddress();
                onChanged();
                return this;
            }

            public Builder setLock(boolean z) {
                this.lock_ = z;
                onChanged();
                return this;
            }

            public Builder clearLock() {
                this.lock_ = false;
                onChanged();
                return this;
            }

            public Builder setLockPeriod(long j) {
                this.lockPeriod_ = j;
                onChanged();
                return this;
            }

            public Builder clearLockPeriod() {
                this.lockPeriod_ = 0L;
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

    public static final class UnDelegateResourceContract extends GeneratedMessageV3 implements UnDelegateResourceContractOrBuilder {
        public static final int BALANCE_FIELD_NUMBER = 3;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int RECEIVER_ADDRESS_FIELD_NUMBER = 4;
        public static final int RESOURCE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private long balance_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString receiverAddress_;
        private int resource_;
        private static final UnDelegateResourceContract DEFAULT_INSTANCE = new UnDelegateResourceContract();
        private static final Parser<UnDelegateResourceContract> PARSER = new AbstractParser<UnDelegateResourceContract>() {
            @Override
            public UnDelegateResourceContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UnDelegateResourceContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static UnDelegateResourceContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UnDelegateResourceContract> parser() {
            return PARSER;
        }

        @Override
        public long getBalance() {
            return this.balance_;
        }

        @Override
        public UnDelegateResourceContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<UnDelegateResourceContract> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getReceiverAddress() {
            return this.receiverAddress_;
        }

        @Override
        public int getResourceValue() {
            return this.resource_;
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

        private UnDelegateResourceContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UnDelegateResourceContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.resource_ = 0;
            this.balance_ = 0L;
            this.receiverAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UnDelegateResourceContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.resource_ = codedInputStream.readEnum();
                            } else if (readTag == 24) {
                                this.balance_ = codedInputStream.readInt64();
                            } else if (readTag != 34) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.receiverAddress_ = codedInputStream.readBytes();
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
            return BalanceContract.internal_static_protocol_UnDelegateResourceContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_UnDelegateResourceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UnDelegateResourceContract.class, Builder.class);
        }

        @Override
        public Common.ResourceCode getResource() {
            Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
            return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                codedOutputStream.writeEnum(2, this.resource_);
            }
            long j = this.balance_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            if (!this.receiverAddress_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.receiverAddress_);
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
            if (this.resource_ != Common.ResourceCode.BANDWIDTH.getNumber()) {
                computeBytesSize += CodedOutputStream.computeEnumSize(2, this.resource_);
            }
            long j = this.balance_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(3, j);
            }
            if (!this.receiverAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(4, this.receiverAddress_);
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
            if (!(obj instanceof UnDelegateResourceContract)) {
                return super.equals(obj);
            }
            UnDelegateResourceContract unDelegateResourceContract = (UnDelegateResourceContract) obj;
            return getOwnerAddress().equals(unDelegateResourceContract.getOwnerAddress()) && this.resource_ == unDelegateResourceContract.resource_ && getBalance() == unDelegateResourceContract.getBalance() && getReceiverAddress().equals(unDelegateResourceContract.getReceiverAddress()) && this.unknownFields.equals(unDelegateResourceContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + this.resource_) * 37) + 3) * 53) + Internal.hashLong(getBalance())) * 37) + 4) * 53) + getReceiverAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static UnDelegateResourceContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UnDelegateResourceContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UnDelegateResourceContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UnDelegateResourceContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UnDelegateResourceContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UnDelegateResourceContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UnDelegateResourceContract parseFrom(InputStream inputStream) throws IOException {
            return (UnDelegateResourceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UnDelegateResourceContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnDelegateResourceContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnDelegateResourceContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnDelegateResourceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UnDelegateResourceContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnDelegateResourceContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnDelegateResourceContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnDelegateResourceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UnDelegateResourceContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnDelegateResourceContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UnDelegateResourceContract unDelegateResourceContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(unDelegateResourceContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UnDelegateResourceContractOrBuilder {
            private long balance_;
            private ByteString ownerAddress_;
            private ByteString receiverAddress_;
            private int resource_;

            @Override
            public long getBalance() {
                return this.balance_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public ByteString getReceiverAddress() {
                return this.receiverAddress_;
            }

            @Override
            public int getResourceValue() {
                return this.resource_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_UnDelegateResourceContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_UnDelegateResourceContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UnDelegateResourceContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.receiverAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UnDelegateResourceContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.resource_ = 0;
                this.balance_ = 0L;
                this.receiverAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_UnDelegateResourceContract_descriptor;
            }

            @Override
            public UnDelegateResourceContract getDefaultInstanceForType() {
                return UnDelegateResourceContract.getDefaultInstance();
            }

            @Override
            public UnDelegateResourceContract build() {
                UnDelegateResourceContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public UnDelegateResourceContract buildPartial() {
                UnDelegateResourceContract unDelegateResourceContract = new UnDelegateResourceContract(this);
                unDelegateResourceContract.ownerAddress_ = this.ownerAddress_;
                unDelegateResourceContract.resource_ = this.resource_;
                unDelegateResourceContract.balance_ = this.balance_;
                unDelegateResourceContract.receiverAddress_ = this.receiverAddress_;
                onBuilt();
                return unDelegateResourceContract;
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
                if (message instanceof UnDelegateResourceContract) {
                    return mergeFrom((UnDelegateResourceContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UnDelegateResourceContract unDelegateResourceContract) {
                if (unDelegateResourceContract == UnDelegateResourceContract.getDefaultInstance()) {
                    return this;
                }
                if (unDelegateResourceContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(unDelegateResourceContract.getOwnerAddress());
                }
                if (unDelegateResourceContract.resource_ != 0) {
                    setResourceValue(unDelegateResourceContract.getResourceValue());
                }
                if (unDelegateResourceContract.getBalance() != 0) {
                    setBalance(unDelegateResourceContract.getBalance());
                }
                if (unDelegateResourceContract.getReceiverAddress() != ByteString.EMPTY) {
                    setReceiverAddress(unDelegateResourceContract.getReceiverAddress());
                }
                mergeUnknownFields(unDelegateResourceContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.UnDelegateResourceContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.UnDelegateResourceContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$UnDelegateResourceContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = UnDelegateResourceContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setResourceValue(int i) {
                this.resource_ = i;
                onChanged();
                return this;
            }

            @Override
            public Common.ResourceCode getResource() {
                Common.ResourceCode valueOf = Common.ResourceCode.valueOf(this.resource_);
                return valueOf == null ? Common.ResourceCode.UNRECOGNIZED : valueOf;
            }

            public Builder setResource(Common.ResourceCode resourceCode) {
                resourceCode.getClass();
                this.resource_ = resourceCode.getNumber();
                onChanged();
                return this;
            }

            public Builder clearResource() {
                this.resource_ = 0;
                onChanged();
                return this;
            }

            public Builder setBalance(long j) {
                this.balance_ = j;
                onChanged();
                return this;
            }

            public Builder clearBalance() {
                this.balance_ = 0L;
                onChanged();
                return this;
            }

            public Builder setReceiverAddress(ByteString byteString) {
                byteString.getClass();
                this.receiverAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearReceiverAddress() {
                this.receiverAddress_ = UnDelegateResourceContract.getDefaultInstance().getReceiverAddress();
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

    public static final class CancelAllUnfreezeV2Contract extends GeneratedMessageV3 implements CancelAllUnfreezeV2ContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final CancelAllUnfreezeV2Contract DEFAULT_INSTANCE = new CancelAllUnfreezeV2Contract();
        private static final Parser<CancelAllUnfreezeV2Contract> PARSER = new AbstractParser<CancelAllUnfreezeV2Contract>() {
            @Override
            public CancelAllUnfreezeV2Contract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CancelAllUnfreezeV2Contract(codedInputStream, extensionRegistryLite);
            }
        };

        public static CancelAllUnfreezeV2Contract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CancelAllUnfreezeV2Contract> parser() {
            return PARSER;
        }

        @Override
        public CancelAllUnfreezeV2Contract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<CancelAllUnfreezeV2Contract> getParserForType() {
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

        private CancelAllUnfreezeV2Contract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CancelAllUnfreezeV2Contract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CancelAllUnfreezeV2Contract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.ownerAddress_ = codedInputStream.readBytes();
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
            return BalanceContract.internal_static_protocol_CancelAllUnfreezeV2Contract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BalanceContract.internal_static_protocol_CancelAllUnfreezeV2Contract_fieldAccessorTable.ensureFieldAccessorsInitialized(CancelAllUnfreezeV2Contract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (!this.ownerAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.ownerAddress_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeBytesSize;
            return computeBytesSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CancelAllUnfreezeV2Contract)) {
                return super.equals(obj);
            }
            CancelAllUnfreezeV2Contract cancelAllUnfreezeV2Contract = (CancelAllUnfreezeV2Contract) obj;
            return getOwnerAddress().equals(cancelAllUnfreezeV2Contract.getOwnerAddress()) && this.unknownFields.equals(cancelAllUnfreezeV2Contract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static CancelAllUnfreezeV2Contract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CancelAllUnfreezeV2Contract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CancelAllUnfreezeV2Contract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CancelAllUnfreezeV2Contract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CancelAllUnfreezeV2Contract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CancelAllUnfreezeV2Contract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CancelAllUnfreezeV2Contract parseFrom(InputStream inputStream) throws IOException {
            return (CancelAllUnfreezeV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CancelAllUnfreezeV2Contract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelAllUnfreezeV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelAllUnfreezeV2Contract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CancelAllUnfreezeV2Contract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CancelAllUnfreezeV2Contract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelAllUnfreezeV2Contract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelAllUnfreezeV2Contract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CancelAllUnfreezeV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CancelAllUnfreezeV2Contract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelAllUnfreezeV2Contract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CancelAllUnfreezeV2Contract cancelAllUnfreezeV2Contract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(cancelAllUnfreezeV2Contract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CancelAllUnfreezeV2ContractOrBuilder {
            private ByteString ownerAddress_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return BalanceContract.internal_static_protocol_CancelAllUnfreezeV2Contract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return BalanceContract.internal_static_protocol_CancelAllUnfreezeV2Contract_fieldAccessorTable.ensureFieldAccessorsInitialized(CancelAllUnfreezeV2Contract.class, Builder.class);
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
                boolean unused = CancelAllUnfreezeV2Contract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return BalanceContract.internal_static_protocol_CancelAllUnfreezeV2Contract_descriptor;
            }

            @Override
            public CancelAllUnfreezeV2Contract getDefaultInstanceForType() {
                return CancelAllUnfreezeV2Contract.getDefaultInstance();
            }

            @Override
            public CancelAllUnfreezeV2Contract build() {
                CancelAllUnfreezeV2Contract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public CancelAllUnfreezeV2Contract buildPartial() {
                CancelAllUnfreezeV2Contract cancelAllUnfreezeV2Contract = new CancelAllUnfreezeV2Contract(this);
                cancelAllUnfreezeV2Contract.ownerAddress_ = this.ownerAddress_;
                onBuilt();
                return cancelAllUnfreezeV2Contract;
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
                if (message instanceof CancelAllUnfreezeV2Contract) {
                    return mergeFrom((CancelAllUnfreezeV2Contract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CancelAllUnfreezeV2Contract cancelAllUnfreezeV2Contract) {
                if (cancelAllUnfreezeV2Contract == CancelAllUnfreezeV2Contract.getDefaultInstance()) {
                    return this;
                }
                if (cancelAllUnfreezeV2Contract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(cancelAllUnfreezeV2Contract.getOwnerAddress());
                }
                mergeUnknownFields(cancelAllUnfreezeV2Contract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.BalanceContract.CancelAllUnfreezeV2Contract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.BalanceContract.CancelAllUnfreezeV2Contract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.BalanceContract$CancelAllUnfreezeV2Contract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = CancelAllUnfreezeV2Contract.getDefaultInstance().getOwnerAddress();
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n$core/contract/balance_contract.proto\u0012\bprotocol\u001a\u001acore/contract/common.proto\"£\u0001\n\u0015FreezeBalanceContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0016\n\u000efrozen_balance\u0018\u0002 \u0001(\u0003\u0012\u0017\n\u000ffrozen_duration\u0018\u0003 \u0001(\u0003\u0012(\n\bresource\u0018\n \u0001(\u000e2\u0016.protocol.ResourceCode\u0012\u0018\n\u0010receiver_address\u0018\u000f \u0001(\f\"t\n\u0017UnfreezeBalanceContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012(\n\bresource\u0018\n \u0001(\u000e2\u0016.protocol.ResourceCode\u0012\u0018\n\u0010receiver_address\u0018\u000f \u0001(\f\"0\n\u0017WithdrawBalanceContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\"M\n\u0010TransferContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0012\n\nto_address\u0018\u0002 \u0001(\f\u0012\u000e\n\u0006amount\u0018\u0003 \u0001(\u0003\"ã\u0001\n\u0017TransactionBalanceTrace\u0012\u001e\n\u0016transaction_identifier\u0018\u0001 \u0001(\f\u0012>\n\toperation\u0018\u0002 \u0003(\u000b2+.protocol.TransactionBalanceTrace.Operation\u0012\f\n\u0004type\u0018\u0003 \u0001(\t\u0012\u000e\n\u0006status\u0018\u0004 \u0001(\t\u001aJ\n\tOperation\u0012\u001c\n\u0014operation_identifier\u0018\u0001 \u0001(\u0003\u0012\u000f\n\u0007address\u0018\u0002 \u0001(\f\u0012\u000e\n\u0006amount\u0018\u0003 \u0001(\u0003\"ä\u0001\n\u0011BlockBalanceTrace\u0012E\n\u0010block_identifier\u0018\u0001 \u0001(\u000b2+.protocol.BlockBalanceTrace.BlockIdentifier\u0012\u0011\n\ttimestamp\u0018\u0002 \u0001(\u0003\u0012D\n\u0019transaction_balance_trace\u0018\u0003 \u0003(\u000b2!.protocol.TransactionBalanceTrace\u001a/\n\u000fBlockIdentifier\u0012\f\n\u0004hash\u0018\u0001 \u0001(\f\u0012\u000e\n\u0006number\u0018\u0002 \u0001(\u0003\"4\n\fAccountTrace\u0012\u000f\n\u0007balance\u0018\u0001 \u0001(\u0003\u0012\u0013\n\u000bplaceholder\u0018c \u0001(\u0003\"$\n\u0011AccountIdentifier\u0012\u000f\n\u0007address\u0018\u0001 \u0001(\f\"\u0097\u0001\n\u0015AccountBalanceRequest\u00127\n\u0012account_identifier\u0018\u0001 \u0001(\u000b2\u001b.protocol.AccountIdentifier\u0012E\n\u0010block_identifier\u0018\u0002 \u0001(\u000b2+.protocol.BlockBalanceTrace.BlockIdentifier\"p\n\u0016AccountBalanceResponse\u0012\u000f\n\u0007balance\u0018\u0001 \u0001(\u0003\u0012E\n\u0010block_identifier\u0018\u0002 \u0001(\u000b2+.protocol.BlockBalanceTrace.BlockIdentifier\"r\n\u0017FreezeBalanceV2Contract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0016\n\u000efrozen_balance\u0018\u0002 \u0001(\u0003\u0012(\n\bresource\u0018\u0003 \u0001(\u000e2\u0016.protocol.ResourceCode\"v\n\u0019UnfreezeBalanceV2Contract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0018\n\u0010unfreeze_balance\u0018\u0002 \u0001(\u0003\u0012(\n\bresource\u0018\u0003 \u0001(\u000e2\u0016.protocol.ResourceCode\"7\n\u001eWithdrawExpireUnfreezeContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\"©\u0001\n\u0018DelegateResourceContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012(\n\bresource\u0018\u0002 \u0001(\u000e2\u0016.protocol.ResourceCode\u0012\u000f\n\u0007balance\u0018\u0003 \u0001(\u0003\u0012\u0018\n\u0010receiver_address\u0018\u0004 \u0001(\f\u0012\f\n\u0004lock\u0018\u0005 \u0001(\b\u0012\u0013\n\u000block_period\u0018\u0006 \u0001(\u0003\"\u0088\u0001\n\u001aUnDelegateResourceContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012(\n\bresource\u0018\u0002 \u0001(\u000e2\u0016.protocol.ResourceCode\u0012\u000f\n\u0007balance\u0018\u0003 \u0001(\u0003\u0012\u0018\n\u0010receiver_address\u0018\u0004 \u0001(\f\"4\n\u001bCancelAllUnfreezeV2Contract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\fBE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[]{Common.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = BalanceContract.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_FreezeBalanceContract_descriptor = descriptor2;
        internal_static_protocol_FreezeBalanceContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"OwnerAddress", "FrozenBalance", "FrozenDuration", AnalyticsHelper.ResourcePage.ENTER_RESOURCE_PAGE, "ReceiverAddress"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_UnfreezeBalanceContract_descriptor = descriptor3;
        internal_static_protocol_UnfreezeBalanceContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"OwnerAddress", AnalyticsHelper.ResourcePage.ENTER_RESOURCE_PAGE, "ReceiverAddress"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_WithdrawBalanceContract_descriptor = descriptor4;
        internal_static_protocol_WithdrawBalanceContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"OwnerAddress"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_protocol_TransferContract_descriptor = descriptor5;
        internal_static_protocol_TransferContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"OwnerAddress", "ToAddress", "Amount"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        internal_static_protocol_TransactionBalanceTrace_descriptor = descriptor6;
        internal_static_protocol_TransactionBalanceTrace_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"TransactionIdentifier", "Operation", "Type", "Status"});
        Descriptors.Descriptor descriptor7 = descriptor6.getNestedTypes().get(0);
        internal_static_protocol_TransactionBalanceTrace_Operation_descriptor = descriptor7;
        internal_static_protocol_TransactionBalanceTrace_Operation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"OperationIdentifier", "Address", "Amount"});
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(5);
        internal_static_protocol_BlockBalanceTrace_descriptor = descriptor8;
        internal_static_protocol_BlockBalanceTrace_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"BlockIdentifier", "Timestamp", "TransactionBalanceTrace"});
        Descriptors.Descriptor descriptor9 = descriptor8.getNestedTypes().get(0);
        internal_static_protocol_BlockBalanceTrace_BlockIdentifier_descriptor = descriptor9;
        internal_static_protocol_BlockBalanceTrace_BlockIdentifier_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Hash", "Number"});
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(6);
        internal_static_protocol_AccountTrace_descriptor = descriptor10;
        internal_static_protocol_AccountTrace_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"Balance", "Placeholder"});
        Descriptors.Descriptor descriptor11 = getDescriptor().getMessageTypes().get(7);
        internal_static_protocol_AccountIdentifier_descriptor = descriptor11;
        internal_static_protocol_AccountIdentifier_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"Address"});
        Descriptors.Descriptor descriptor12 = getDescriptor().getMessageTypes().get(8);
        internal_static_protocol_AccountBalanceRequest_descriptor = descriptor12;
        internal_static_protocol_AccountBalanceRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"AccountIdentifier", "BlockIdentifier"});
        Descriptors.Descriptor descriptor13 = getDescriptor().getMessageTypes().get(9);
        internal_static_protocol_AccountBalanceResponse_descriptor = descriptor13;
        internal_static_protocol_AccountBalanceResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"Balance", "BlockIdentifier"});
        Descriptors.Descriptor descriptor14 = getDescriptor().getMessageTypes().get(10);
        internal_static_protocol_FreezeBalanceV2Contract_descriptor = descriptor14;
        internal_static_protocol_FreezeBalanceV2Contract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"OwnerAddress", "FrozenBalance", AnalyticsHelper.ResourcePage.ENTER_RESOURCE_PAGE});
        Descriptors.Descriptor descriptor15 = getDescriptor().getMessageTypes().get(11);
        internal_static_protocol_UnfreezeBalanceV2Contract_descriptor = descriptor15;
        internal_static_protocol_UnfreezeBalanceV2Contract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor15, new String[]{"OwnerAddress", "UnfreezeBalance", AnalyticsHelper.ResourcePage.ENTER_RESOURCE_PAGE});
        Descriptors.Descriptor descriptor16 = getDescriptor().getMessageTypes().get(12);
        internal_static_protocol_WithdrawExpireUnfreezeContract_descriptor = descriptor16;
        internal_static_protocol_WithdrawExpireUnfreezeContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor16, new String[]{"OwnerAddress"});
        Descriptors.Descriptor descriptor17 = getDescriptor().getMessageTypes().get(13);
        internal_static_protocol_DelegateResourceContract_descriptor = descriptor17;
        internal_static_protocol_DelegateResourceContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor17, new String[]{"OwnerAddress", AnalyticsHelper.ResourcePage.ENTER_RESOURCE_PAGE, "Balance", "ReceiverAddress", "Lock", "LockPeriod"});
        Descriptors.Descriptor descriptor18 = getDescriptor().getMessageTypes().get(14);
        internal_static_protocol_UnDelegateResourceContract_descriptor = descriptor18;
        internal_static_protocol_UnDelegateResourceContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor18, new String[]{"OwnerAddress", AnalyticsHelper.ResourcePage.ENTER_RESOURCE_PAGE, "Balance", "ReceiverAddress"});
        Descriptors.Descriptor descriptor19 = getDescriptor().getMessageTypes().get(15);
        internal_static_protocol_CancelAllUnfreezeV2Contract_descriptor = descriptor19;
        internal_static_protocol_CancelAllUnfreezeV2Contract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor19, new String[]{"OwnerAddress"});
        Common.getDescriptor();
    }
}
