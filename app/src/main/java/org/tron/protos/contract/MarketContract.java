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
public final class MarketContract {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_MarketCancelOrderContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_MarketCancelOrderContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_MarketSellAssetContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_MarketSellAssetContract_fieldAccessorTable;

    public interface MarketCancelOrderContractOrBuilder extends MessageOrBuilder {
        ByteString getOrderId();

        ByteString getOwnerAddress();
    }

    public interface MarketSellAssetContractOrBuilder extends MessageOrBuilder {
        ByteString getBuyTokenId();

        long getBuyTokenQuantity();

        ByteString getOwnerAddress();

        ByteString getSellTokenId();

        long getSellTokenQuantity();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private MarketContract() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class MarketSellAssetContract extends GeneratedMessageV3 implements MarketSellAssetContractOrBuilder {
        public static final int BUY_TOKEN_ID_FIELD_NUMBER = 4;
        public static final int BUY_TOKEN_QUANTITY_FIELD_NUMBER = 5;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int SELL_TOKEN_ID_FIELD_NUMBER = 2;
        public static final int SELL_TOKEN_QUANTITY_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private ByteString buyTokenId_;
        private long buyTokenQuantity_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString sellTokenId_;
        private long sellTokenQuantity_;
        private static final MarketSellAssetContract DEFAULT_INSTANCE = new MarketSellAssetContract();
        private static final Parser<MarketSellAssetContract> PARSER = new AbstractParser<MarketSellAssetContract>() {
            @Override
            public MarketSellAssetContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MarketSellAssetContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static MarketSellAssetContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MarketSellAssetContract> parser() {
            return PARSER;
        }

        @Override
        public ByteString getBuyTokenId() {
            return this.buyTokenId_;
        }

        @Override
        public long getBuyTokenQuantity() {
            return this.buyTokenQuantity_;
        }

        @Override
        public MarketSellAssetContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<MarketSellAssetContract> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getSellTokenId() {
            return this.sellTokenId_;
        }

        @Override
        public long getSellTokenQuantity() {
            return this.sellTokenQuantity_;
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

        private MarketSellAssetContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private MarketSellAssetContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.sellTokenId_ = ByteString.EMPTY;
            this.sellTokenQuantity_ = 0L;
            this.buyTokenId_ = ByteString.EMPTY;
            this.buyTokenQuantity_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MarketSellAssetContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.sellTokenId_ = codedInputStream.readBytes();
                                } else if (readTag == 24) {
                                    this.sellTokenQuantity_ = codedInputStream.readInt64();
                                } else if (readTag == 34) {
                                    this.buyTokenId_ = codedInputStream.readBytes();
                                } else if (readTag != 40) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.buyTokenQuantity_ = codedInputStream.readInt64();
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
            return MarketContract.internal_static_protocol_MarketSellAssetContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MarketContract.internal_static_protocol_MarketSellAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(MarketSellAssetContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.sellTokenId_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.sellTokenId_);
            }
            long j = this.sellTokenQuantity_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            if (!this.buyTokenId_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.buyTokenId_);
            }
            long j2 = this.buyTokenQuantity_;
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
            if (!this.sellTokenId_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.sellTokenId_);
            }
            long j = this.sellTokenQuantity_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(3, j);
            }
            if (!this.buyTokenId_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(4, this.buyTokenId_);
            }
            long j2 = this.buyTokenQuantity_;
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
            if (!(obj instanceof MarketSellAssetContract)) {
                return super.equals(obj);
            }
            MarketSellAssetContract marketSellAssetContract = (MarketSellAssetContract) obj;
            return getOwnerAddress().equals(marketSellAssetContract.getOwnerAddress()) && getSellTokenId().equals(marketSellAssetContract.getSellTokenId()) && getSellTokenQuantity() == marketSellAssetContract.getSellTokenQuantity() && getBuyTokenId().equals(marketSellAssetContract.getBuyTokenId()) && getBuyTokenQuantity() == marketSellAssetContract.getBuyTokenQuantity() && this.unknownFields.equals(marketSellAssetContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getSellTokenId().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getSellTokenQuantity())) * 37) + 4) * 53) + getBuyTokenId().hashCode()) * 37) + 5) * 53) + Internal.hashLong(getBuyTokenQuantity())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static MarketSellAssetContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static MarketSellAssetContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MarketSellAssetContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static MarketSellAssetContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MarketSellAssetContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static MarketSellAssetContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static MarketSellAssetContract parseFrom(InputStream inputStream) throws IOException {
            return (MarketSellAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MarketSellAssetContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MarketSellAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MarketSellAssetContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MarketSellAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MarketSellAssetContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MarketSellAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MarketSellAssetContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MarketSellAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MarketSellAssetContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MarketSellAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MarketSellAssetContract marketSellAssetContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(marketSellAssetContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MarketSellAssetContractOrBuilder {
            private ByteString buyTokenId_;
            private long buyTokenQuantity_;
            private ByteString ownerAddress_;
            private ByteString sellTokenId_;
            private long sellTokenQuantity_;

            @Override
            public ByteString getBuyTokenId() {
                return this.buyTokenId_;
            }

            @Override
            public long getBuyTokenQuantity() {
                return this.buyTokenQuantity_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public ByteString getSellTokenId() {
                return this.sellTokenId_;
            }

            @Override
            public long getSellTokenQuantity() {
                return this.sellTokenQuantity_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MarketContract.internal_static_protocol_MarketSellAssetContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MarketContract.internal_static_protocol_MarketSellAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(MarketSellAssetContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.sellTokenId_ = ByteString.EMPTY;
                this.buyTokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.sellTokenId_ = ByteString.EMPTY;
                this.buyTokenId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MarketSellAssetContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.sellTokenId_ = ByteString.EMPTY;
                this.sellTokenQuantity_ = 0L;
                this.buyTokenId_ = ByteString.EMPTY;
                this.buyTokenQuantity_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return MarketContract.internal_static_protocol_MarketSellAssetContract_descriptor;
            }

            @Override
            public MarketSellAssetContract getDefaultInstanceForType() {
                return MarketSellAssetContract.getDefaultInstance();
            }

            @Override
            public MarketSellAssetContract build() {
                MarketSellAssetContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public MarketSellAssetContract buildPartial() {
                MarketSellAssetContract marketSellAssetContract = new MarketSellAssetContract(this);
                marketSellAssetContract.ownerAddress_ = this.ownerAddress_;
                marketSellAssetContract.sellTokenId_ = this.sellTokenId_;
                marketSellAssetContract.sellTokenQuantity_ = this.sellTokenQuantity_;
                marketSellAssetContract.buyTokenId_ = this.buyTokenId_;
                marketSellAssetContract.buyTokenQuantity_ = this.buyTokenQuantity_;
                onBuilt();
                return marketSellAssetContract;
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
                if (message instanceof MarketSellAssetContract) {
                    return mergeFrom((MarketSellAssetContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(MarketSellAssetContract marketSellAssetContract) {
                if (marketSellAssetContract == MarketSellAssetContract.getDefaultInstance()) {
                    return this;
                }
                if (marketSellAssetContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(marketSellAssetContract.getOwnerAddress());
                }
                if (marketSellAssetContract.getSellTokenId() != ByteString.EMPTY) {
                    setSellTokenId(marketSellAssetContract.getSellTokenId());
                }
                if (marketSellAssetContract.getSellTokenQuantity() != 0) {
                    setSellTokenQuantity(marketSellAssetContract.getSellTokenQuantity());
                }
                if (marketSellAssetContract.getBuyTokenId() != ByteString.EMPTY) {
                    setBuyTokenId(marketSellAssetContract.getBuyTokenId());
                }
                if (marketSellAssetContract.getBuyTokenQuantity() != 0) {
                    setBuyTokenQuantity(marketSellAssetContract.getBuyTokenQuantity());
                }
                mergeUnknownFields(marketSellAssetContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.MarketContract.MarketSellAssetContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.MarketContract.MarketSellAssetContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.MarketContract$MarketSellAssetContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = MarketSellAssetContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setSellTokenId(ByteString byteString) {
                byteString.getClass();
                this.sellTokenId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearSellTokenId() {
                this.sellTokenId_ = MarketSellAssetContract.getDefaultInstance().getSellTokenId();
                onChanged();
                return this;
            }

            public Builder setSellTokenQuantity(long j) {
                this.sellTokenQuantity_ = j;
                onChanged();
                return this;
            }

            public Builder clearSellTokenQuantity() {
                this.sellTokenQuantity_ = 0L;
                onChanged();
                return this;
            }

            public Builder setBuyTokenId(ByteString byteString) {
                byteString.getClass();
                this.buyTokenId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearBuyTokenId() {
                this.buyTokenId_ = MarketSellAssetContract.getDefaultInstance().getBuyTokenId();
                onChanged();
                return this;
            }

            public Builder setBuyTokenQuantity(long j) {
                this.buyTokenQuantity_ = j;
                onChanged();
                return this;
            }

            public Builder clearBuyTokenQuantity() {
                this.buyTokenQuantity_ = 0L;
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

    public static final class MarketCancelOrderContract extends GeneratedMessageV3 implements MarketCancelOrderContractOrBuilder {
        public static final int ORDER_ID_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString orderId_;
        private ByteString ownerAddress_;
        private static final MarketCancelOrderContract DEFAULT_INSTANCE = new MarketCancelOrderContract();
        private static final Parser<MarketCancelOrderContract> PARSER = new AbstractParser<MarketCancelOrderContract>() {
            @Override
            public MarketCancelOrderContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MarketCancelOrderContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static MarketCancelOrderContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MarketCancelOrderContract> parser() {
            return PARSER;
        }

        @Override
        public MarketCancelOrderContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOrderId() {
            return this.orderId_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<MarketCancelOrderContract> getParserForType() {
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

        private MarketCancelOrderContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private MarketCancelOrderContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.orderId_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MarketCancelOrderContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.orderId_ = codedInputStream.readBytes();
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
            return MarketContract.internal_static_protocol_MarketCancelOrderContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MarketContract.internal_static_protocol_MarketCancelOrderContract_fieldAccessorTable.ensureFieldAccessorsInitialized(MarketCancelOrderContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.orderId_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.orderId_);
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
            if (!this.orderId_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.orderId_);
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
            if (!(obj instanceof MarketCancelOrderContract)) {
                return super.equals(obj);
            }
            MarketCancelOrderContract marketCancelOrderContract = (MarketCancelOrderContract) obj;
            return getOwnerAddress().equals(marketCancelOrderContract.getOwnerAddress()) && getOrderId().equals(marketCancelOrderContract.getOrderId()) && this.unknownFields.equals(marketCancelOrderContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getOrderId().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static MarketCancelOrderContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static MarketCancelOrderContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MarketCancelOrderContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static MarketCancelOrderContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MarketCancelOrderContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static MarketCancelOrderContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static MarketCancelOrderContract parseFrom(InputStream inputStream) throws IOException {
            return (MarketCancelOrderContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MarketCancelOrderContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MarketCancelOrderContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MarketCancelOrderContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MarketCancelOrderContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MarketCancelOrderContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MarketCancelOrderContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MarketCancelOrderContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MarketCancelOrderContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MarketCancelOrderContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MarketCancelOrderContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MarketCancelOrderContract marketCancelOrderContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(marketCancelOrderContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MarketCancelOrderContractOrBuilder {
            private ByteString orderId_;
            private ByteString ownerAddress_;

            @Override
            public ByteString getOrderId() {
                return this.orderId_;
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
                return MarketContract.internal_static_protocol_MarketCancelOrderContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MarketContract.internal_static_protocol_MarketCancelOrderContract_fieldAccessorTable.ensureFieldAccessorsInitialized(MarketCancelOrderContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.orderId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.orderId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MarketCancelOrderContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.orderId_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return MarketContract.internal_static_protocol_MarketCancelOrderContract_descriptor;
            }

            @Override
            public MarketCancelOrderContract getDefaultInstanceForType() {
                return MarketCancelOrderContract.getDefaultInstance();
            }

            @Override
            public MarketCancelOrderContract build() {
                MarketCancelOrderContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public MarketCancelOrderContract buildPartial() {
                MarketCancelOrderContract marketCancelOrderContract = new MarketCancelOrderContract(this);
                marketCancelOrderContract.ownerAddress_ = this.ownerAddress_;
                marketCancelOrderContract.orderId_ = this.orderId_;
                onBuilt();
                return marketCancelOrderContract;
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
                if (message instanceof MarketCancelOrderContract) {
                    return mergeFrom((MarketCancelOrderContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(MarketCancelOrderContract marketCancelOrderContract) {
                if (marketCancelOrderContract == MarketCancelOrderContract.getDefaultInstance()) {
                    return this;
                }
                if (marketCancelOrderContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(marketCancelOrderContract.getOwnerAddress());
                }
                if (marketCancelOrderContract.getOrderId() != ByteString.EMPTY) {
                    setOrderId(marketCancelOrderContract.getOrderId());
                }
                mergeUnknownFields(marketCancelOrderContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.MarketContract.MarketCancelOrderContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.MarketContract.MarketCancelOrderContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.MarketContract$MarketCancelOrderContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = MarketCancelOrderContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setOrderId(ByteString byteString) {
                byteString.getClass();
                this.orderId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOrderId() {
                this.orderId_ = MarketCancelOrderContract.getDefaultInstance().getOrderId();
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n#core/contract/market_contract.proto\u0012\bprotocol\"\u0096\u0001\n\u0017MarketSellAssetContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0015\n\rsell_token_id\u0018\u0002 \u0001(\f\u0012\u001b\n\u0013sell_token_quantity\u0018\u0003 \u0001(\u0003\u0012\u0014\n\fbuy_token_id\u0018\u0004 \u0001(\f\u0012\u001a\n\u0012buy_token_quantity\u0018\u0005 \u0001(\u0003\"D\n\u0019MarketCancelOrderContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0010\n\border_id\u0018\u0002 \u0001(\fBE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = MarketContract.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_MarketSellAssetContract_descriptor = descriptor2;
        internal_static_protocol_MarketSellAssetContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"OwnerAddress", "SellTokenId", "SellTokenQuantity", "BuyTokenId", "BuyTokenQuantity"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_MarketCancelOrderContract_descriptor = descriptor3;
        internal_static_protocol_MarketCancelOrderContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"OwnerAddress", "OrderId"});
    }
}
