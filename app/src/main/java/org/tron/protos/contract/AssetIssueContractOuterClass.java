package org.tron.protos.contract;

import com.alibaba.fastjson.asm.Opcodes;
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
public final class AssetIssueContractOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_AssetIssueContract_FrozenSupply_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AssetIssueContract_FrozenSupply_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_AssetIssueContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AssetIssueContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ParticipateAssetIssueContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ParticipateAssetIssueContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_TransferAssetContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_TransferAssetContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_UnfreezeAssetContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_UnfreezeAssetContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_UpdateAssetContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_UpdateAssetContract_fieldAccessorTable;

    public interface AssetIssueContractOrBuilder extends MessageOrBuilder {
        ByteString getAbbr();

        ByteString getDescription();

        long getEndTime();

        long getFreeAssetNetLimit();

        AssetIssueContract.FrozenSupply getFrozenSupply(int i);

        int getFrozenSupplyCount();

        List<AssetIssueContract.FrozenSupply> getFrozenSupplyList();

        AssetIssueContract.FrozenSupplyOrBuilder getFrozenSupplyOrBuilder(int i);

        List<? extends AssetIssueContract.FrozenSupplyOrBuilder> getFrozenSupplyOrBuilderList();

        String getId();

        ByteString getIdBytes();

        ByteString getName();

        int getNum();

        long getOrder();

        ByteString getOwnerAddress();

        int getPrecision();

        long getPublicFreeAssetNetLimit();

        long getPublicFreeAssetNetUsage();

        long getPublicLatestFreeNetTime();

        long getStartTime();

        long getTotalSupply();

        int getTrxNum();

        ByteString getUrl();

        int getVoteScore();
    }

    public interface ParticipateAssetIssueContractOrBuilder extends MessageOrBuilder {
        long getAmount();

        ByteString getAssetName();

        ByteString getOwnerAddress();

        ByteString getToAddress();
    }

    public interface TransferAssetContractOrBuilder extends MessageOrBuilder {
        long getAmount();

        ByteString getAssetName();

        ByteString getOwnerAddress();

        ByteString getToAddress();
    }

    public interface UnfreezeAssetContractOrBuilder extends MessageOrBuilder {
        ByteString getOwnerAddress();
    }

    public interface UpdateAssetContractOrBuilder extends MessageOrBuilder {
        ByteString getDescription();

        long getNewLimit();

        long getNewPublicLimit();

        ByteString getOwnerAddress();

        ByteString getUrl();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private AssetIssueContractOuterClass() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class AssetIssueContract extends GeneratedMessageV3 implements AssetIssueContractOrBuilder {
        public static final int ABBR_FIELD_NUMBER = 3;
        public static final int DESCRIPTION_FIELD_NUMBER = 20;
        public static final int END_TIME_FIELD_NUMBER = 10;
        public static final int FREE_ASSET_NET_LIMIT_FIELD_NUMBER = 22;
        public static final int FROZEN_SUPPLY_FIELD_NUMBER = 5;
        public static final int ID_FIELD_NUMBER = 41;
        public static final int NAME_FIELD_NUMBER = 2;
        public static final int NUM_FIELD_NUMBER = 8;
        public static final int ORDER_FIELD_NUMBER = 11;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int PRECISION_FIELD_NUMBER = 7;
        public static final int PUBLIC_FREE_ASSET_NET_LIMIT_FIELD_NUMBER = 23;
        public static final int PUBLIC_FREE_ASSET_NET_USAGE_FIELD_NUMBER = 24;
        public static final int PUBLIC_LATEST_FREE_NET_TIME_FIELD_NUMBER = 25;
        public static final int START_TIME_FIELD_NUMBER = 9;
        public static final int TOTAL_SUPPLY_FIELD_NUMBER = 4;
        public static final int TRX_NUM_FIELD_NUMBER = 6;
        public static final int URL_FIELD_NUMBER = 21;
        public static final int VOTE_SCORE_FIELD_NUMBER = 16;
        private static final long serialVersionUID = 0;
        private ByteString abbr_;
        private int bitField0_;
        private ByteString description_;
        private long endTime_;
        private long freeAssetNetLimit_;
        private List<FrozenSupply> frozenSupply_;
        private volatile Object id_;
        private byte memoizedIsInitialized;
        private ByteString name_;
        private int num_;
        private long order_;
        private ByteString ownerAddress_;
        private int precision_;
        private long publicFreeAssetNetLimit_;
        private long publicFreeAssetNetUsage_;
        private long publicLatestFreeNetTime_;
        private long startTime_;
        private long totalSupply_;
        private int trxNum_;
        private ByteString url_;
        private int voteScore_;
        private static final AssetIssueContract DEFAULT_INSTANCE = new AssetIssueContract();
        private static final Parser<AssetIssueContract> PARSER = new AbstractParser<AssetIssueContract>() {
            @Override
            public AssetIssueContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AssetIssueContract(codedInputStream, extensionRegistryLite);
            }
        };

        public interface FrozenSupplyOrBuilder extends MessageOrBuilder {
            long getFrozenAmount();

            long getFrozenDays();
        }

        public static AssetIssueContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AssetIssueContract> parser() {
            return PARSER;
        }

        @Override
        public ByteString getAbbr() {
            return this.abbr_;
        }

        @Override
        public AssetIssueContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getDescription() {
            return this.description_;
        }

        @Override
        public long getEndTime() {
            return this.endTime_;
        }

        @Override
        public long getFreeAssetNetLimit() {
            return this.freeAssetNetLimit_;
        }

        @Override
        public List<FrozenSupply> getFrozenSupplyList() {
            return this.frozenSupply_;
        }

        @Override
        public List<? extends FrozenSupplyOrBuilder> getFrozenSupplyOrBuilderList() {
            return this.frozenSupply_;
        }

        @Override
        public ByteString getName() {
            return this.name_;
        }

        @Override
        public int getNum() {
            return this.num_;
        }

        @Override
        public long getOrder() {
            return this.order_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<AssetIssueContract> getParserForType() {
            return PARSER;
        }

        @Override
        public int getPrecision() {
            return this.precision_;
        }

        @Override
        public long getPublicFreeAssetNetLimit() {
            return this.publicFreeAssetNetLimit_;
        }

        @Override
        public long getPublicFreeAssetNetUsage() {
            return this.publicFreeAssetNetUsage_;
        }

        @Override
        public long getPublicLatestFreeNetTime() {
            return this.publicLatestFreeNetTime_;
        }

        @Override
        public long getStartTime() {
            return this.startTime_;
        }

        @Override
        public long getTotalSupply() {
            return this.totalSupply_;
        }

        @Override
        public int getTrxNum() {
            return this.trxNum_;
        }

        @Override
        public ByteString getUrl() {
            return this.url_;
        }

        @Override
        public int getVoteScore() {
            return this.voteScore_;
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

        private AssetIssueContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private AssetIssueContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.id_ = "";
            this.ownerAddress_ = ByteString.EMPTY;
            this.name_ = ByteString.EMPTY;
            this.abbr_ = ByteString.EMPTY;
            this.totalSupply_ = 0L;
            this.frozenSupply_ = Collections.emptyList();
            this.trxNum_ = 0;
            this.precision_ = 0;
            this.num_ = 0;
            this.startTime_ = 0L;
            this.endTime_ = 0L;
            this.order_ = 0L;
            this.voteScore_ = 0;
            this.description_ = ByteString.EMPTY;
            this.url_ = ByteString.EMPTY;
            this.freeAssetNetLimit_ = 0L;
            this.publicFreeAssetNetLimit_ = 0L;
            this.publicFreeAssetNetUsage_ = 0L;
            this.publicLatestFreeNetTime_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AssetIssueContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (true) {
                ?? r3 = 32;
                if (z) {
                    return;
                }
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            switch (readTag) {
                                case 0:
                                    break;
                                case 10:
                                    this.ownerAddress_ = codedInputStream.readBytes();
                                    continue;
                                case 18:
                                    this.name_ = codedInputStream.readBytes();
                                    continue;
                                case 26:
                                    this.abbr_ = codedInputStream.readBytes();
                                    continue;
                                case 32:
                                    this.totalSupply_ = codedInputStream.readInt64();
                                    continue;
                                case 42:
                                    if (!(z2 & true)) {
                                        this.frozenSupply_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.frozenSupply_.add((FrozenSupply) codedInputStream.readMessage(FrozenSupply.parser(), extensionRegistryLite));
                                    continue;
                                case 48:
                                    this.trxNum_ = codedInputStream.readInt32();
                                    continue;
                                case 56:
                                    this.precision_ = codedInputStream.readInt32();
                                    continue;
                                case 64:
                                    this.num_ = codedInputStream.readInt32();
                                    continue;
                                case 72:
                                    this.startTime_ = codedInputStream.readInt64();
                                    continue;
                                case 80:
                                    this.endTime_ = codedInputStream.readInt64();
                                    continue;
                                case 88:
                                    this.order_ = codedInputStream.readInt64();
                                    continue;
                                case 128:
                                    this.voteScore_ = codedInputStream.readInt32();
                                    continue;
                                case Opcodes.IF_ICMPGE:
                                    this.description_ = codedInputStream.readBytes();
                                    continue;
                                case 170:
                                    this.url_ = codedInputStream.readBytes();
                                    continue;
                                case Opcodes.ARETURN:
                                    this.freeAssetNetLimit_ = codedInputStream.readInt64();
                                    continue;
                                case Opcodes.INVOKESTATIC:
                                    this.publicFreeAssetNetLimit_ = codedInputStream.readInt64();
                                    continue;
                                case 192:
                                    this.publicFreeAssetNetUsage_ = codedInputStream.readInt64();
                                    continue;
                                case 200:
                                    this.publicLatestFreeNetTime_ = codedInputStream.readInt64();
                                    continue;
                                case 330:
                                    this.id_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                default:
                                    r3 = parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag);
                                    if (r3 == 0) {
                                        break;
                                    } else {
                                        continue;
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
                    if ((z2 & true) == r3) {
                        this.frozenSupply_ = Collections.unmodifiableList(this.frozenSupply_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_fieldAccessorTable.ensureFieldAccessorsInitialized(AssetIssueContract.class, Builder.class);
        }

        public static final class FrozenSupply extends GeneratedMessageV3 implements FrozenSupplyOrBuilder {
            public static final int FROZEN_AMOUNT_FIELD_NUMBER = 1;
            public static final int FROZEN_DAYS_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private long frozenAmount_;
            private long frozenDays_;
            private byte memoizedIsInitialized;
            private static final FrozenSupply DEFAULT_INSTANCE = new FrozenSupply();
            private static final Parser<FrozenSupply> PARSER = new AbstractParser<FrozenSupply>() {
                @Override
                public FrozenSupply parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new FrozenSupply(codedInputStream, extensionRegistryLite);
                }
            };

            public static FrozenSupply getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<FrozenSupply> parser() {
                return PARSER;
            }

            @Override
            public FrozenSupply getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override
            public long getFrozenAmount() {
                return this.frozenAmount_;
            }

            @Override
            public long getFrozenDays() {
                return this.frozenDays_;
            }

            @Override
            public Parser<FrozenSupply> getParserForType() {
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

            private FrozenSupply(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private FrozenSupply() {
                this.memoizedIsInitialized = (byte) -1;
                this.frozenAmount_ = 0L;
                this.frozenDays_ = 0L;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private FrozenSupply(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.frozenAmount_ = codedInputStream.readInt64();
                                    } else if (readTag != 16) {
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.frozenDays_ = codedInputStream.readInt64();
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
                return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_FrozenSupply_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_FrozenSupply_fieldAccessorTable.ensureFieldAccessorsInitialized(FrozenSupply.class, Builder.class);
            }

            @Override
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                long j = this.frozenAmount_;
                if (j != 0) {
                    codedOutputStream.writeInt64(1, j);
                }
                long j2 = this.frozenDays_;
                if (j2 != 0) {
                    codedOutputStream.writeInt64(2, j2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                long j = this.frozenAmount_;
                int computeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(1, j) : 0;
                long j2 = this.frozenDays_;
                if (j2 != 0) {
                    computeInt64Size += CodedOutputStream.computeInt64Size(2, j2);
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
                if (!(obj instanceof FrozenSupply)) {
                    return super.equals(obj);
                }
                FrozenSupply frozenSupply = (FrozenSupply) obj;
                return getFrozenAmount() == frozenSupply.getFrozenAmount() && getFrozenDays() == frozenSupply.getFrozenDays() && this.unknownFields.equals(frozenSupply.unknownFields);
            }

            @Override
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getFrozenAmount())) * 37) + 2) * 53) + Internal.hashLong(getFrozenDays())) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            public static FrozenSupply parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static FrozenSupply parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static FrozenSupply parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static FrozenSupply parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static FrozenSupply parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static FrozenSupply parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static FrozenSupply parseFrom(InputStream inputStream) throws IOException {
                return (FrozenSupply) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static FrozenSupply parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (FrozenSupply) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static FrozenSupply parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (FrozenSupply) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static FrozenSupply parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (FrozenSupply) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static FrozenSupply parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (FrozenSupply) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static FrozenSupply parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (FrozenSupply) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(FrozenSupply frozenSupply) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(frozenSupply);
            }

            @Override
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            @Override
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FrozenSupplyOrBuilder {
                private long frozenAmount_;
                private long frozenDays_;

                @Override
                public long getFrozenAmount() {
                    return this.frozenAmount_;
                }

                @Override
                public long getFrozenDays() {
                    return this.frozenDays_;
                }

                @Override
                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_FrozenSupply_descriptor;
                }

                @Override
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_FrozenSupply_fieldAccessorTable.ensureFieldAccessorsInitialized(FrozenSupply.class, Builder.class);
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = FrozenSupply.alwaysUseFieldBuilders;
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.frozenAmount_ = 0L;
                    this.frozenDays_ = 0L;
                    return this;
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_FrozenSupply_descriptor;
                }

                @Override
                public FrozenSupply getDefaultInstanceForType() {
                    return FrozenSupply.getDefaultInstance();
                }

                @Override
                public FrozenSupply build() {
                    FrozenSupply buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override
                public FrozenSupply buildPartial() {
                    FrozenSupply frozenSupply = new FrozenSupply(this);
                    frozenSupply.frozenAmount_ = this.frozenAmount_;
                    frozenSupply.frozenDays_ = this.frozenDays_;
                    onBuilt();
                    return frozenSupply;
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
                    if (message instanceof FrozenSupply) {
                        return mergeFrom((FrozenSupply) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(FrozenSupply frozenSupply) {
                    if (frozenSupply == FrozenSupply.getDefaultInstance()) {
                        return this;
                    }
                    if (frozenSupply.getFrozenAmount() != 0) {
                        setFrozenAmount(frozenSupply.getFrozenAmount());
                    }
                    if (frozenSupply.getFrozenDays() != 0) {
                        setFrozenDays(frozenSupply.getFrozenDays());
                    }
                    mergeUnknownFields(frozenSupply.unknownFields);
                    onChanged();
                    return this;
                }

                @Override
                public org.tron.protos.contract.AssetIssueContractOuterClass.AssetIssueContract.FrozenSupply.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AssetIssueContractOuterClass.AssetIssueContract.FrozenSupply.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AssetIssueContractOuterClass$AssetIssueContract$FrozenSupply$Builder");
                }

                public Builder setFrozenAmount(long j) {
                    this.frozenAmount_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearFrozenAmount() {
                    this.frozenAmount_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder setFrozenDays(long j) {
                    this.frozenDays_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearFrozenDays() {
                    this.frozenDays_ = 0L;
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
        public String getId() {
            Object obj = this.id_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.id_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getIdBytes() {
            Object obj = this.id_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.id_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override
        public int getFrozenSupplyCount() {
            return this.frozenSupply_.size();
        }

        @Override
        public FrozenSupply getFrozenSupply(int i) {
            return this.frozenSupply_.get(i);
        }

        @Override
        public FrozenSupplyOrBuilder getFrozenSupplyOrBuilder(int i) {
            return this.frozenSupply_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.name_);
            }
            if (!this.abbr_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.abbr_);
            }
            long j = this.totalSupply_;
            if (j != 0) {
                codedOutputStream.writeInt64(4, j);
            }
            for (int i = 0; i < this.frozenSupply_.size(); i++) {
                codedOutputStream.writeMessage(5, this.frozenSupply_.get(i));
            }
            int i2 = this.trxNum_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(6, i2);
            }
            int i3 = this.precision_;
            if (i3 != 0) {
                codedOutputStream.writeInt32(7, i3);
            }
            int i4 = this.num_;
            if (i4 != 0) {
                codedOutputStream.writeInt32(8, i4);
            }
            long j2 = this.startTime_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(9, j2);
            }
            long j3 = this.endTime_;
            if (j3 != 0) {
                codedOutputStream.writeInt64(10, j3);
            }
            long j4 = this.order_;
            if (j4 != 0) {
                codedOutputStream.writeInt64(11, j4);
            }
            int i5 = this.voteScore_;
            if (i5 != 0) {
                codedOutputStream.writeInt32(16, i5);
            }
            if (!this.description_.isEmpty()) {
                codedOutputStream.writeBytes(20, this.description_);
            }
            if (!this.url_.isEmpty()) {
                codedOutputStream.writeBytes(21, this.url_);
            }
            long j5 = this.freeAssetNetLimit_;
            if (j5 != 0) {
                codedOutputStream.writeInt64(22, j5);
            }
            long j6 = this.publicFreeAssetNetLimit_;
            if (j6 != 0) {
                codedOutputStream.writeInt64(23, j6);
            }
            long j7 = this.publicFreeAssetNetUsage_;
            if (j7 != 0) {
                codedOutputStream.writeInt64(24, j7);
            }
            long j8 = this.publicLatestFreeNetTime_;
            if (j8 != 0) {
                codedOutputStream.writeInt64(25, j8);
            }
            if (!getIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 41, this.id_);
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
            if (!this.name_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.name_);
            }
            if (!this.abbr_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.abbr_);
            }
            long j = this.totalSupply_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(4, j);
            }
            for (int i2 = 0; i2 < this.frozenSupply_.size(); i2++) {
                computeBytesSize += CodedOutputStream.computeMessageSize(5, this.frozenSupply_.get(i2));
            }
            int i3 = this.trxNum_;
            if (i3 != 0) {
                computeBytesSize += CodedOutputStream.computeInt32Size(6, i3);
            }
            int i4 = this.precision_;
            if (i4 != 0) {
                computeBytesSize += CodedOutputStream.computeInt32Size(7, i4);
            }
            int i5 = this.num_;
            if (i5 != 0) {
                computeBytesSize += CodedOutputStream.computeInt32Size(8, i5);
            }
            long j2 = this.startTime_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(9, j2);
            }
            long j3 = this.endTime_;
            if (j3 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(10, j3);
            }
            long j4 = this.order_;
            if (j4 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(11, j4);
            }
            int i6 = this.voteScore_;
            if (i6 != 0) {
                computeBytesSize += CodedOutputStream.computeInt32Size(16, i6);
            }
            if (!this.description_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(20, this.description_);
            }
            if (!this.url_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(21, this.url_);
            }
            long j5 = this.freeAssetNetLimit_;
            if (j5 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(22, j5);
            }
            long j6 = this.publicFreeAssetNetLimit_;
            if (j6 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(23, j6);
            }
            long j7 = this.publicFreeAssetNetUsage_;
            if (j7 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(24, j7);
            }
            long j8 = this.publicLatestFreeNetTime_;
            if (j8 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(25, j8);
            }
            if (!getIdBytes().isEmpty()) {
                computeBytesSize += GeneratedMessageV3.computeStringSize(41, this.id_);
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
            if (!(obj instanceof AssetIssueContract)) {
                return super.equals(obj);
            }
            AssetIssueContract assetIssueContract = (AssetIssueContract) obj;
            return getId().equals(assetIssueContract.getId()) && getOwnerAddress().equals(assetIssueContract.getOwnerAddress()) && getName().equals(assetIssueContract.getName()) && getAbbr().equals(assetIssueContract.getAbbr()) && getTotalSupply() == assetIssueContract.getTotalSupply() && getFrozenSupplyList().equals(assetIssueContract.getFrozenSupplyList()) && getTrxNum() == assetIssueContract.getTrxNum() && getPrecision() == assetIssueContract.getPrecision() && getNum() == assetIssueContract.getNum() && getStartTime() == assetIssueContract.getStartTime() && getEndTime() == assetIssueContract.getEndTime() && getOrder() == assetIssueContract.getOrder() && getVoteScore() == assetIssueContract.getVoteScore() && getDescription().equals(assetIssueContract.getDescription()) && getUrl().equals(assetIssueContract.getUrl()) && getFreeAssetNetLimit() == assetIssueContract.getFreeAssetNetLimit() && getPublicFreeAssetNetLimit() == assetIssueContract.getPublicFreeAssetNetLimit() && getPublicFreeAssetNetUsage() == assetIssueContract.getPublicFreeAssetNetUsage() && getPublicLatestFreeNetTime() == assetIssueContract.getPublicLatestFreeNetTime() && this.unknownFields.equals(assetIssueContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 41) * 53) + getId().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getName().hashCode()) * 37) + 3) * 53) + getAbbr().hashCode()) * 37) + 4) * 53) + Internal.hashLong(getTotalSupply());
            if (getFrozenSupplyCount() > 0) {
                hashCode = (((hashCode * 37) + 5) * 53) + getFrozenSupplyList().hashCode();
            }
            int trxNum = (((((((((((((((((((((((((((((((((((((((((((((((((((((hashCode * 37) + 6) * 53) + getTrxNum()) * 37) + 7) * 53) + getPrecision()) * 37) + 8) * 53) + getNum()) * 37) + 9) * 53) + Internal.hashLong(getStartTime())) * 37) + 10) * 53) + Internal.hashLong(getEndTime())) * 37) + 11) * 53) + Internal.hashLong(getOrder())) * 37) + 16) * 53) + getVoteScore()) * 37) + 20) * 53) + getDescription().hashCode()) * 37) + 21) * 53) + getUrl().hashCode()) * 37) + 22) * 53) + Internal.hashLong(getFreeAssetNetLimit())) * 37) + 23) * 53) + Internal.hashLong(getPublicFreeAssetNetLimit())) * 37) + 24) * 53) + Internal.hashLong(getPublicFreeAssetNetUsage())) * 37) + 25) * 53) + Internal.hashLong(getPublicLatestFreeNetTime())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = trxNum;
            return trxNum;
        }

        public static AssetIssueContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AssetIssueContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AssetIssueContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AssetIssueContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AssetIssueContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AssetIssueContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AssetIssueContract parseFrom(InputStream inputStream) throws IOException {
            return (AssetIssueContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AssetIssueContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AssetIssueContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AssetIssueContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AssetIssueContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AssetIssueContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AssetIssueContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AssetIssueContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AssetIssueContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AssetIssueContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AssetIssueContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AssetIssueContract assetIssueContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(assetIssueContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AssetIssueContractOrBuilder {
            private ByteString abbr_;
            private int bitField0_;
            private ByteString description_;
            private long endTime_;
            private long freeAssetNetLimit_;
            private RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> frozenSupplyBuilder_;
            private List<FrozenSupply> frozenSupply_;
            private Object id_;
            private ByteString name_;
            private int num_;
            private long order_;
            private ByteString ownerAddress_;
            private int precision_;
            private long publicFreeAssetNetLimit_;
            private long publicFreeAssetNetUsage_;
            private long publicLatestFreeNetTime_;
            private long startTime_;
            private long totalSupply_;
            private int trxNum_;
            private ByteString url_;
            private int voteScore_;

            @Override
            public ByteString getAbbr() {
                return this.abbr_;
            }

            @Override
            public ByteString getDescription() {
                return this.description_;
            }

            @Override
            public long getEndTime() {
                return this.endTime_;
            }

            @Override
            public long getFreeAssetNetLimit() {
                return this.freeAssetNetLimit_;
            }

            @Override
            public ByteString getName() {
                return this.name_;
            }

            @Override
            public int getNum() {
                return this.num_;
            }

            @Override
            public long getOrder() {
                return this.order_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public int getPrecision() {
                return this.precision_;
            }

            @Override
            public long getPublicFreeAssetNetLimit() {
                return this.publicFreeAssetNetLimit_;
            }

            @Override
            public long getPublicFreeAssetNetUsage() {
                return this.publicFreeAssetNetUsage_;
            }

            @Override
            public long getPublicLatestFreeNetTime() {
                return this.publicLatestFreeNetTime_;
            }

            @Override
            public long getStartTime() {
                return this.startTime_;
            }

            @Override
            public long getTotalSupply() {
                return this.totalSupply_;
            }

            @Override
            public int getTrxNum() {
                return this.trxNum_;
            }

            @Override
            public ByteString getUrl() {
                return this.url_;
            }

            @Override
            public int getVoteScore() {
                return this.voteScore_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_fieldAccessorTable.ensureFieldAccessorsInitialized(AssetIssueContract.class, Builder.class);
            }

            private Builder() {
                this.id_ = "";
                this.ownerAddress_ = ByteString.EMPTY;
                this.name_ = ByteString.EMPTY;
                this.abbr_ = ByteString.EMPTY;
                this.frozenSupply_ = Collections.emptyList();
                this.description_ = ByteString.EMPTY;
                this.url_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.id_ = "";
                this.ownerAddress_ = ByteString.EMPTY;
                this.name_ = ByteString.EMPTY;
                this.abbr_ = ByteString.EMPTY;
                this.frozenSupply_ = Collections.emptyList();
                this.description_ = ByteString.EMPTY;
                this.url_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (AssetIssueContract.alwaysUseFieldBuilders) {
                    getFrozenSupplyFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                this.id_ = "";
                this.ownerAddress_ = ByteString.EMPTY;
                this.name_ = ByteString.EMPTY;
                this.abbr_ = ByteString.EMPTY;
                this.totalSupply_ = 0L;
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.frozenSupply_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.trxNum_ = 0;
                this.precision_ = 0;
                this.num_ = 0;
                this.startTime_ = 0L;
                this.endTime_ = 0L;
                this.order_ = 0L;
                this.voteScore_ = 0;
                this.description_ = ByteString.EMPTY;
                this.url_ = ByteString.EMPTY;
                this.freeAssetNetLimit_ = 0L;
                this.publicFreeAssetNetLimit_ = 0L;
                this.publicFreeAssetNetUsage_ = 0L;
                this.publicLatestFreeNetTime_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return AssetIssueContractOuterClass.internal_static_protocol_AssetIssueContract_descriptor;
            }

            @Override
            public AssetIssueContract getDefaultInstanceForType() {
                return AssetIssueContract.getDefaultInstance();
            }

            @Override
            public AssetIssueContract build() {
                AssetIssueContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public AssetIssueContract buildPartial() {
                AssetIssueContract assetIssueContract = new AssetIssueContract(this);
                assetIssueContract.id_ = this.id_;
                assetIssueContract.ownerAddress_ = this.ownerAddress_;
                assetIssueContract.name_ = this.name_;
                assetIssueContract.abbr_ = this.abbr_;
                assetIssueContract.totalSupply_ = this.totalSupply_;
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 32) == 32) {
                        this.frozenSupply_ = Collections.unmodifiableList(this.frozenSupply_);
                        this.bitField0_ &= -33;
                    }
                    assetIssueContract.frozenSupply_ = this.frozenSupply_;
                } else {
                    assetIssueContract.frozenSupply_ = repeatedFieldBuilderV3.build();
                }
                assetIssueContract.trxNum_ = this.trxNum_;
                assetIssueContract.precision_ = this.precision_;
                assetIssueContract.num_ = this.num_;
                assetIssueContract.startTime_ = this.startTime_;
                assetIssueContract.endTime_ = this.endTime_;
                assetIssueContract.order_ = this.order_;
                assetIssueContract.voteScore_ = this.voteScore_;
                assetIssueContract.description_ = this.description_;
                assetIssueContract.url_ = this.url_;
                assetIssueContract.freeAssetNetLimit_ = this.freeAssetNetLimit_;
                assetIssueContract.publicFreeAssetNetLimit_ = this.publicFreeAssetNetLimit_;
                assetIssueContract.publicFreeAssetNetUsage_ = this.publicFreeAssetNetUsage_;
                assetIssueContract.publicLatestFreeNetTime_ = this.publicLatestFreeNetTime_;
                assetIssueContract.bitField0_ = 0;
                onBuilt();
                return assetIssueContract;
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
                if (message instanceof AssetIssueContract) {
                    return mergeFrom((AssetIssueContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AssetIssueContract assetIssueContract) {
                if (assetIssueContract == AssetIssueContract.getDefaultInstance()) {
                    return this;
                }
                if (!assetIssueContract.getId().isEmpty()) {
                    this.id_ = assetIssueContract.id_;
                    onChanged();
                }
                if (assetIssueContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(assetIssueContract.getOwnerAddress());
                }
                if (assetIssueContract.getName() != ByteString.EMPTY) {
                    setName(assetIssueContract.getName());
                }
                if (assetIssueContract.getAbbr() != ByteString.EMPTY) {
                    setAbbr(assetIssueContract.getAbbr());
                }
                if (assetIssueContract.getTotalSupply() != 0) {
                    setTotalSupply(assetIssueContract.getTotalSupply());
                }
                if (this.frozenSupplyBuilder_ == null) {
                    if (!assetIssueContract.frozenSupply_.isEmpty()) {
                        if (this.frozenSupply_.isEmpty()) {
                            this.frozenSupply_ = assetIssueContract.frozenSupply_;
                            this.bitField0_ &= -33;
                        } else {
                            ensureFrozenSupplyIsMutable();
                            this.frozenSupply_.addAll(assetIssueContract.frozenSupply_);
                        }
                        onChanged();
                    }
                } else if (!assetIssueContract.frozenSupply_.isEmpty()) {
                    if (!this.frozenSupplyBuilder_.isEmpty()) {
                        this.frozenSupplyBuilder_.addAllMessages(assetIssueContract.frozenSupply_);
                    } else {
                        this.frozenSupplyBuilder_.dispose();
                        this.frozenSupplyBuilder_ = null;
                        this.frozenSupply_ = assetIssueContract.frozenSupply_;
                        this.bitField0_ &= -33;
                        this.frozenSupplyBuilder_ = AssetIssueContract.alwaysUseFieldBuilders ? getFrozenSupplyFieldBuilder() : null;
                    }
                }
                if (assetIssueContract.getTrxNum() != 0) {
                    setTrxNum(assetIssueContract.getTrxNum());
                }
                if (assetIssueContract.getPrecision() != 0) {
                    setPrecision(assetIssueContract.getPrecision());
                }
                if (assetIssueContract.getNum() != 0) {
                    setNum(assetIssueContract.getNum());
                }
                if (assetIssueContract.getStartTime() != 0) {
                    setStartTime(assetIssueContract.getStartTime());
                }
                if (assetIssueContract.getEndTime() != 0) {
                    setEndTime(assetIssueContract.getEndTime());
                }
                if (assetIssueContract.getOrder() != 0) {
                    setOrder(assetIssueContract.getOrder());
                }
                if (assetIssueContract.getVoteScore() != 0) {
                    setVoteScore(assetIssueContract.getVoteScore());
                }
                if (assetIssueContract.getDescription() != ByteString.EMPTY) {
                    setDescription(assetIssueContract.getDescription());
                }
                if (assetIssueContract.getUrl() != ByteString.EMPTY) {
                    setUrl(assetIssueContract.getUrl());
                }
                if (assetIssueContract.getFreeAssetNetLimit() != 0) {
                    setFreeAssetNetLimit(assetIssueContract.getFreeAssetNetLimit());
                }
                if (assetIssueContract.getPublicFreeAssetNetLimit() != 0) {
                    setPublicFreeAssetNetLimit(assetIssueContract.getPublicFreeAssetNetLimit());
                }
                if (assetIssueContract.getPublicFreeAssetNetUsage() != 0) {
                    setPublicFreeAssetNetUsage(assetIssueContract.getPublicFreeAssetNetUsage());
                }
                if (assetIssueContract.getPublicLatestFreeNetTime() != 0) {
                    setPublicLatestFreeNetTime(assetIssueContract.getPublicLatestFreeNetTime());
                }
                mergeUnknownFields(assetIssueContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.AssetIssueContractOuterClass.AssetIssueContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AssetIssueContractOuterClass.AssetIssueContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AssetIssueContractOuterClass$AssetIssueContract$Builder");
            }

            @Override
            public String getId() {
                Object obj = this.id_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.id_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override
            public ByteString getIdBytes() {
                Object obj = this.id_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.id_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setId(String str) {
                str.getClass();
                this.id_ = str;
                onChanged();
                return this;
            }

            public Builder clearId() {
                this.id_ = AssetIssueContract.getDefaultInstance().getId();
                onChanged();
                return this;
            }

            public Builder setIdBytes(ByteString byteString) {
                byteString.getClass();
                AssetIssueContract.checkByteStringIsUtf8(byteString);
                this.id_ = byteString;
                onChanged();
                return this;
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = AssetIssueContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setName(ByteString byteString) {
                byteString.getClass();
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = AssetIssueContract.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder setAbbr(ByteString byteString) {
                byteString.getClass();
                this.abbr_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAbbr() {
                this.abbr_ = AssetIssueContract.getDefaultInstance().getAbbr();
                onChanged();
                return this;
            }

            public Builder setTotalSupply(long j) {
                this.totalSupply_ = j;
                onChanged();
                return this;
            }

            public Builder clearTotalSupply() {
                this.totalSupply_ = 0L;
                onChanged();
                return this;
            }

            private void ensureFrozenSupplyIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.frozenSupply_ = new ArrayList(this.frozenSupply_);
                    this.bitField0_ |= 32;
                }
            }

            @Override
            public List<FrozenSupply> getFrozenSupplyList() {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.frozenSupply_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getFrozenSupplyCount() {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.frozenSupply_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public FrozenSupply getFrozenSupply(int i) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.frozenSupply_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setFrozenSupply(int i, FrozenSupply frozenSupply) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    frozenSupply.getClass();
                    ensureFrozenSupplyIsMutable();
                    this.frozenSupply_.set(i, frozenSupply);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, frozenSupply);
                }
                return this;
            }

            public Builder setFrozenSupply(int i, FrozenSupply.Builder builder) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFrozenSupplyIsMutable();
                    this.frozenSupply_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addFrozenSupply(FrozenSupply frozenSupply) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    frozenSupply.getClass();
                    ensureFrozenSupplyIsMutable();
                    this.frozenSupply_.add(frozenSupply);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(frozenSupply);
                }
                return this;
            }

            public Builder addFrozenSupply(int i, FrozenSupply frozenSupply) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    frozenSupply.getClass();
                    ensureFrozenSupplyIsMutable();
                    this.frozenSupply_.add(i, frozenSupply);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, frozenSupply);
                }
                return this;
            }

            public Builder addFrozenSupply(FrozenSupply.Builder builder) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFrozenSupplyIsMutable();
                    this.frozenSupply_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addFrozenSupply(int i, FrozenSupply.Builder builder) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFrozenSupplyIsMutable();
                    this.frozenSupply_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllFrozenSupply(Iterable<? extends FrozenSupply> iterable) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFrozenSupplyIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.frozenSupply_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearFrozenSupply() {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.frozenSupply_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeFrozenSupply(int i) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFrozenSupplyIsMutable();
                    this.frozenSupply_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public FrozenSupply.Builder getFrozenSupplyBuilder(int i) {
                return getFrozenSupplyFieldBuilder().getBuilder(i);
            }

            @Override
            public FrozenSupplyOrBuilder getFrozenSupplyOrBuilder(int i) {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.frozenSupply_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends FrozenSupplyOrBuilder> getFrozenSupplyOrBuilderList() {
                RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> repeatedFieldBuilderV3 = this.frozenSupplyBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.frozenSupply_);
            }

            public FrozenSupply.Builder addFrozenSupplyBuilder() {
                return getFrozenSupplyFieldBuilder().addBuilder(FrozenSupply.getDefaultInstance());
            }

            public FrozenSupply.Builder addFrozenSupplyBuilder(int i) {
                return getFrozenSupplyFieldBuilder().addBuilder(i, FrozenSupply.getDefaultInstance());
            }

            public List<FrozenSupply.Builder> getFrozenSupplyBuilderList() {
                return getFrozenSupplyFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<FrozenSupply, FrozenSupply.Builder, FrozenSupplyOrBuilder> getFrozenSupplyFieldBuilder() {
                if (this.frozenSupplyBuilder_ == null) {
                    this.frozenSupplyBuilder_ = new RepeatedFieldBuilderV3<>(this.frozenSupply_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
                    this.frozenSupply_ = null;
                }
                return this.frozenSupplyBuilder_;
            }

            public Builder setTrxNum(int i) {
                this.trxNum_ = i;
                onChanged();
                return this;
            }

            public Builder clearTrxNum() {
                this.trxNum_ = 0;
                onChanged();
                return this;
            }

            public Builder setPrecision(int i) {
                this.precision_ = i;
                onChanged();
                return this;
            }

            public Builder clearPrecision() {
                this.precision_ = 0;
                onChanged();
                return this;
            }

            public Builder setNum(int i) {
                this.num_ = i;
                onChanged();
                return this;
            }

            public Builder clearNum() {
                this.num_ = 0;
                onChanged();
                return this;
            }

            public Builder setStartTime(long j) {
                this.startTime_ = j;
                onChanged();
                return this;
            }

            public Builder clearStartTime() {
                this.startTime_ = 0L;
                onChanged();
                return this;
            }

            public Builder setEndTime(long j) {
                this.endTime_ = j;
                onChanged();
                return this;
            }

            public Builder clearEndTime() {
                this.endTime_ = 0L;
                onChanged();
                return this;
            }

            public Builder setOrder(long j) {
                this.order_ = j;
                onChanged();
                return this;
            }

            public Builder clearOrder() {
                this.order_ = 0L;
                onChanged();
                return this;
            }

            public Builder setVoteScore(int i) {
                this.voteScore_ = i;
                onChanged();
                return this;
            }

            public Builder clearVoteScore() {
                this.voteScore_ = 0;
                onChanged();
                return this;
            }

            public Builder setDescription(ByteString byteString) {
                byteString.getClass();
                this.description_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearDescription() {
                this.description_ = AssetIssueContract.getDefaultInstance().getDescription();
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
                this.url_ = AssetIssueContract.getDefaultInstance().getUrl();
                onChanged();
                return this;
            }

            public Builder setFreeAssetNetLimit(long j) {
                this.freeAssetNetLimit_ = j;
                onChanged();
                return this;
            }

            public Builder clearFreeAssetNetLimit() {
                this.freeAssetNetLimit_ = 0L;
                onChanged();
                return this;
            }

            public Builder setPublicFreeAssetNetLimit(long j) {
                this.publicFreeAssetNetLimit_ = j;
                onChanged();
                return this;
            }

            public Builder clearPublicFreeAssetNetLimit() {
                this.publicFreeAssetNetLimit_ = 0L;
                onChanged();
                return this;
            }

            public Builder setPublicFreeAssetNetUsage(long j) {
                this.publicFreeAssetNetUsage_ = j;
                onChanged();
                return this;
            }

            public Builder clearPublicFreeAssetNetUsage() {
                this.publicFreeAssetNetUsage_ = 0L;
                onChanged();
                return this;
            }

            public Builder setPublicLatestFreeNetTime(long j) {
                this.publicLatestFreeNetTime_ = j;
                onChanged();
                return this;
            }

            public Builder clearPublicLatestFreeNetTime() {
                this.publicLatestFreeNetTime_ = 0L;
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

    public static final class TransferAssetContract extends GeneratedMessageV3 implements TransferAssetContractOrBuilder {
        public static final int AMOUNT_FIELD_NUMBER = 4;
        public static final int ASSET_NAME_FIELD_NUMBER = 1;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 2;
        public static final int TO_ADDRESS_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private long amount_;
        private ByteString assetName_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString toAddress_;
        private static final TransferAssetContract DEFAULT_INSTANCE = new TransferAssetContract();
        private static final Parser<TransferAssetContract> PARSER = new AbstractParser<TransferAssetContract>() {
            @Override
            public TransferAssetContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TransferAssetContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static TransferAssetContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TransferAssetContract> parser() {
            return PARSER;
        }

        @Override
        public long getAmount() {
            return this.amount_;
        }

        @Override
        public ByteString getAssetName() {
            return this.assetName_;
        }

        @Override
        public TransferAssetContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<TransferAssetContract> getParserForType() {
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

        private TransferAssetContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private TransferAssetContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.assetName_ = ByteString.EMPTY;
            this.ownerAddress_ = ByteString.EMPTY;
            this.toAddress_ = ByteString.EMPTY;
            this.amount_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private TransferAssetContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.assetName_ = codedInputStream.readBytes();
                            } else if (readTag == 18) {
                                this.ownerAddress_ = codedInputStream.readBytes();
                            } else if (readTag == 26) {
                                this.toAddress_ = codedInputStream.readBytes();
                            } else if (readTag != 32) {
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
            return AssetIssueContractOuterClass.internal_static_protocol_TransferAssetContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AssetIssueContractOuterClass.internal_static_protocol_TransferAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(TransferAssetContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.assetName_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.assetName_);
            }
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.ownerAddress_);
            }
            if (!this.toAddress_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.toAddress_);
            }
            long j = this.amount_;
            if (j != 0) {
                codedOutputStream.writeInt64(4, j);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.assetName_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.assetName_) : 0;
            if (!this.ownerAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.ownerAddress_);
            }
            if (!this.toAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.toAddress_);
            }
            long j = this.amount_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(4, j);
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
            if (!(obj instanceof TransferAssetContract)) {
                return super.equals(obj);
            }
            TransferAssetContract transferAssetContract = (TransferAssetContract) obj;
            return getAssetName().equals(transferAssetContract.getAssetName()) && getOwnerAddress().equals(transferAssetContract.getOwnerAddress()) && getToAddress().equals(transferAssetContract.getToAddress()) && getAmount() == transferAssetContract.getAmount() && this.unknownFields.equals(transferAssetContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAssetName().hashCode()) * 37) + 2) * 53) + getOwnerAddress().hashCode()) * 37) + 3) * 53) + getToAddress().hashCode()) * 37) + 4) * 53) + Internal.hashLong(getAmount())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static TransferAssetContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static TransferAssetContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static TransferAssetContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static TransferAssetContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TransferAssetContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static TransferAssetContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TransferAssetContract parseFrom(InputStream inputStream) throws IOException {
            return (TransferAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static TransferAssetContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransferAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TransferAssetContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TransferAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static TransferAssetContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransferAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TransferAssetContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TransferAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static TransferAssetContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransferAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TransferAssetContract transferAssetContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(transferAssetContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TransferAssetContractOrBuilder {
            private long amount_;
            private ByteString assetName_;
            private ByteString ownerAddress_;
            private ByteString toAddress_;

            @Override
            public long getAmount() {
                return this.amount_;
            }

            @Override
            public ByteString getAssetName() {
                return this.assetName_;
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
                return AssetIssueContractOuterClass.internal_static_protocol_TransferAssetContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AssetIssueContractOuterClass.internal_static_protocol_TransferAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(TransferAssetContract.class, Builder.class);
            }

            private Builder() {
                this.assetName_ = ByteString.EMPTY;
                this.ownerAddress_ = ByteString.EMPTY;
                this.toAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.assetName_ = ByteString.EMPTY;
                this.ownerAddress_ = ByteString.EMPTY;
                this.toAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = TransferAssetContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.assetName_ = ByteString.EMPTY;
                this.ownerAddress_ = ByteString.EMPTY;
                this.toAddress_ = ByteString.EMPTY;
                this.amount_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return AssetIssueContractOuterClass.internal_static_protocol_TransferAssetContract_descriptor;
            }

            @Override
            public TransferAssetContract getDefaultInstanceForType() {
                return TransferAssetContract.getDefaultInstance();
            }

            @Override
            public TransferAssetContract build() {
                TransferAssetContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public TransferAssetContract buildPartial() {
                TransferAssetContract transferAssetContract = new TransferAssetContract(this);
                transferAssetContract.assetName_ = this.assetName_;
                transferAssetContract.ownerAddress_ = this.ownerAddress_;
                transferAssetContract.toAddress_ = this.toAddress_;
                transferAssetContract.amount_ = this.amount_;
                onBuilt();
                return transferAssetContract;
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
                if (message instanceof TransferAssetContract) {
                    return mergeFrom((TransferAssetContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TransferAssetContract transferAssetContract) {
                if (transferAssetContract == TransferAssetContract.getDefaultInstance()) {
                    return this;
                }
                if (transferAssetContract.getAssetName() != ByteString.EMPTY) {
                    setAssetName(transferAssetContract.getAssetName());
                }
                if (transferAssetContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(transferAssetContract.getOwnerAddress());
                }
                if (transferAssetContract.getToAddress() != ByteString.EMPTY) {
                    setToAddress(transferAssetContract.getToAddress());
                }
                if (transferAssetContract.getAmount() != 0) {
                    setAmount(transferAssetContract.getAmount());
                }
                mergeUnknownFields(transferAssetContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.AssetIssueContractOuterClass.TransferAssetContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AssetIssueContractOuterClass.TransferAssetContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AssetIssueContractOuterClass$TransferAssetContract$Builder");
            }

            public Builder setAssetName(ByteString byteString) {
                byteString.getClass();
                this.assetName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAssetName() {
                this.assetName_ = TransferAssetContract.getDefaultInstance().getAssetName();
                onChanged();
                return this;
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = TransferAssetContract.getDefaultInstance().getOwnerAddress();
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
                this.toAddress_ = TransferAssetContract.getDefaultInstance().getToAddress();
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

    public static final class UnfreezeAssetContract extends GeneratedMessageV3 implements UnfreezeAssetContractOrBuilder {
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final UnfreezeAssetContract DEFAULT_INSTANCE = new UnfreezeAssetContract();
        private static final Parser<UnfreezeAssetContract> PARSER = new AbstractParser<UnfreezeAssetContract>() {
            @Override
            public UnfreezeAssetContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UnfreezeAssetContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static UnfreezeAssetContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UnfreezeAssetContract> parser() {
            return PARSER;
        }

        @Override
        public UnfreezeAssetContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<UnfreezeAssetContract> getParserForType() {
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

        private UnfreezeAssetContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UnfreezeAssetContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UnfreezeAssetContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
            return AssetIssueContractOuterClass.internal_static_protocol_UnfreezeAssetContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AssetIssueContractOuterClass.internal_static_protocol_UnfreezeAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UnfreezeAssetContract.class, Builder.class);
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
            if (!(obj instanceof UnfreezeAssetContract)) {
                return super.equals(obj);
            }
            UnfreezeAssetContract unfreezeAssetContract = (UnfreezeAssetContract) obj;
            return getOwnerAddress().equals(unfreezeAssetContract.getOwnerAddress()) && this.unknownFields.equals(unfreezeAssetContract.unknownFields);
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

        public static UnfreezeAssetContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UnfreezeAssetContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UnfreezeAssetContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UnfreezeAssetContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UnfreezeAssetContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UnfreezeAssetContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UnfreezeAssetContract parseFrom(InputStream inputStream) throws IOException {
            return (UnfreezeAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UnfreezeAssetContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnfreezeAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnfreezeAssetContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnfreezeAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UnfreezeAssetContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnfreezeAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnfreezeAssetContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnfreezeAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UnfreezeAssetContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnfreezeAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UnfreezeAssetContract unfreezeAssetContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(unfreezeAssetContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UnfreezeAssetContractOrBuilder {
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
                return AssetIssueContractOuterClass.internal_static_protocol_UnfreezeAssetContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AssetIssueContractOuterClass.internal_static_protocol_UnfreezeAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UnfreezeAssetContract.class, Builder.class);
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
                boolean unused = UnfreezeAssetContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return AssetIssueContractOuterClass.internal_static_protocol_UnfreezeAssetContract_descriptor;
            }

            @Override
            public UnfreezeAssetContract getDefaultInstanceForType() {
                return UnfreezeAssetContract.getDefaultInstance();
            }

            @Override
            public UnfreezeAssetContract build() {
                UnfreezeAssetContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public UnfreezeAssetContract buildPartial() {
                UnfreezeAssetContract unfreezeAssetContract = new UnfreezeAssetContract(this);
                unfreezeAssetContract.ownerAddress_ = this.ownerAddress_;
                onBuilt();
                return unfreezeAssetContract;
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
                if (message instanceof UnfreezeAssetContract) {
                    return mergeFrom((UnfreezeAssetContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UnfreezeAssetContract unfreezeAssetContract) {
                if (unfreezeAssetContract == UnfreezeAssetContract.getDefaultInstance()) {
                    return this;
                }
                if (unfreezeAssetContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(unfreezeAssetContract.getOwnerAddress());
                }
                mergeUnknownFields(unfreezeAssetContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.AssetIssueContractOuterClass.UnfreezeAssetContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AssetIssueContractOuterClass.UnfreezeAssetContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AssetIssueContractOuterClass$UnfreezeAssetContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = UnfreezeAssetContract.getDefaultInstance().getOwnerAddress();
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

    public static final class UpdateAssetContract extends GeneratedMessageV3 implements UpdateAssetContractOrBuilder {
        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        public static final int NEW_LIMIT_FIELD_NUMBER = 4;
        public static final int NEW_PUBLIC_LIMIT_FIELD_NUMBER = 5;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int URL_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private ByteString description_;
        private byte memoizedIsInitialized;
        private long newLimit_;
        private long newPublicLimit_;
        private ByteString ownerAddress_;
        private ByteString url_;
        private static final UpdateAssetContract DEFAULT_INSTANCE = new UpdateAssetContract();
        private static final Parser<UpdateAssetContract> PARSER = new AbstractParser<UpdateAssetContract>() {
            @Override
            public UpdateAssetContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UpdateAssetContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static UpdateAssetContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UpdateAssetContract> parser() {
            return PARSER;
        }

        @Override
        public UpdateAssetContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getDescription() {
            return this.description_;
        }

        @Override
        public long getNewLimit() {
            return this.newLimit_;
        }

        @Override
        public long getNewPublicLimit() {
            return this.newPublicLimit_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<UpdateAssetContract> getParserForType() {
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

        private UpdateAssetContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UpdateAssetContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.description_ = ByteString.EMPTY;
            this.url_ = ByteString.EMPTY;
            this.newLimit_ = 0L;
            this.newPublicLimit_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateAssetContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.description_ = codedInputStream.readBytes();
                                } else if (readTag == 26) {
                                    this.url_ = codedInputStream.readBytes();
                                } else if (readTag == 32) {
                                    this.newLimit_ = codedInputStream.readInt64();
                                } else if (readTag != 40) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.newPublicLimit_ = codedInputStream.readInt64();
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
            return AssetIssueContractOuterClass.internal_static_protocol_UpdateAssetContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AssetIssueContractOuterClass.internal_static_protocol_UpdateAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateAssetContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.description_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.description_);
            }
            if (!this.url_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.url_);
            }
            long j = this.newLimit_;
            if (j != 0) {
                codedOutputStream.writeInt64(4, j);
            }
            long j2 = this.newPublicLimit_;
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
            if (!this.description_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.description_);
            }
            if (!this.url_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.url_);
            }
            long j = this.newLimit_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(4, j);
            }
            long j2 = this.newPublicLimit_;
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
            if (!(obj instanceof UpdateAssetContract)) {
                return super.equals(obj);
            }
            UpdateAssetContract updateAssetContract = (UpdateAssetContract) obj;
            return getOwnerAddress().equals(updateAssetContract.getOwnerAddress()) && getDescription().equals(updateAssetContract.getDescription()) && getUrl().equals(updateAssetContract.getUrl()) && getNewLimit() == updateAssetContract.getNewLimit() && getNewPublicLimit() == updateAssetContract.getNewPublicLimit() && this.unknownFields.equals(updateAssetContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getDescription().hashCode()) * 37) + 3) * 53) + getUrl().hashCode()) * 37) + 4) * 53) + Internal.hashLong(getNewLimit())) * 37) + 5) * 53) + Internal.hashLong(getNewPublicLimit())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static UpdateAssetContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UpdateAssetContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UpdateAssetContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UpdateAssetContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UpdateAssetContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UpdateAssetContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UpdateAssetContract parseFrom(InputStream inputStream) throws IOException {
            return (UpdateAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UpdateAssetContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateAssetContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UpdateAssetContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateAssetContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateAssetContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UpdateAssetContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateAssetContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UpdateAssetContract updateAssetContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateAssetContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateAssetContractOrBuilder {
            private ByteString description_;
            private long newLimit_;
            private long newPublicLimit_;
            private ByteString ownerAddress_;
            private ByteString url_;

            @Override
            public ByteString getDescription() {
                return this.description_;
            }

            @Override
            public long getNewLimit() {
                return this.newLimit_;
            }

            @Override
            public long getNewPublicLimit() {
                return this.newPublicLimit_;
            }

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
                return AssetIssueContractOuterClass.internal_static_protocol_UpdateAssetContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AssetIssueContractOuterClass.internal_static_protocol_UpdateAssetContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateAssetContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.description_ = ByteString.EMPTY;
                this.url_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.description_ = ByteString.EMPTY;
                this.url_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateAssetContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.description_ = ByteString.EMPTY;
                this.url_ = ByteString.EMPTY;
                this.newLimit_ = 0L;
                this.newPublicLimit_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return AssetIssueContractOuterClass.internal_static_protocol_UpdateAssetContract_descriptor;
            }

            @Override
            public UpdateAssetContract getDefaultInstanceForType() {
                return UpdateAssetContract.getDefaultInstance();
            }

            @Override
            public UpdateAssetContract build() {
                UpdateAssetContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public UpdateAssetContract buildPartial() {
                UpdateAssetContract updateAssetContract = new UpdateAssetContract(this);
                updateAssetContract.ownerAddress_ = this.ownerAddress_;
                updateAssetContract.description_ = this.description_;
                updateAssetContract.url_ = this.url_;
                updateAssetContract.newLimit_ = this.newLimit_;
                updateAssetContract.newPublicLimit_ = this.newPublicLimit_;
                onBuilt();
                return updateAssetContract;
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
                if (message instanceof UpdateAssetContract) {
                    return mergeFrom((UpdateAssetContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UpdateAssetContract updateAssetContract) {
                if (updateAssetContract == UpdateAssetContract.getDefaultInstance()) {
                    return this;
                }
                if (updateAssetContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(updateAssetContract.getOwnerAddress());
                }
                if (updateAssetContract.getDescription() != ByteString.EMPTY) {
                    setDescription(updateAssetContract.getDescription());
                }
                if (updateAssetContract.getUrl() != ByteString.EMPTY) {
                    setUrl(updateAssetContract.getUrl());
                }
                if (updateAssetContract.getNewLimit() != 0) {
                    setNewLimit(updateAssetContract.getNewLimit());
                }
                if (updateAssetContract.getNewPublicLimit() != 0) {
                    setNewPublicLimit(updateAssetContract.getNewPublicLimit());
                }
                mergeUnknownFields(updateAssetContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.AssetIssueContractOuterClass.UpdateAssetContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AssetIssueContractOuterClass.UpdateAssetContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AssetIssueContractOuterClass$UpdateAssetContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = UpdateAssetContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setDescription(ByteString byteString) {
                byteString.getClass();
                this.description_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearDescription() {
                this.description_ = UpdateAssetContract.getDefaultInstance().getDescription();
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
                this.url_ = UpdateAssetContract.getDefaultInstance().getUrl();
                onChanged();
                return this;
            }

            public Builder setNewLimit(long j) {
                this.newLimit_ = j;
                onChanged();
                return this;
            }

            public Builder clearNewLimit() {
                this.newLimit_ = 0L;
                onChanged();
                return this;
            }

            public Builder setNewPublicLimit(long j) {
                this.newPublicLimit_ = j;
                onChanged();
                return this;
            }

            public Builder clearNewPublicLimit() {
                this.newPublicLimit_ = 0L;
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

    public static final class ParticipateAssetIssueContract extends GeneratedMessageV3 implements ParticipateAssetIssueContractOrBuilder {
        public static final int AMOUNT_FIELD_NUMBER = 4;
        public static final int ASSET_NAME_FIELD_NUMBER = 3;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int TO_ADDRESS_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private long amount_;
        private ByteString assetName_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private ByteString toAddress_;
        private static final ParticipateAssetIssueContract DEFAULT_INSTANCE = new ParticipateAssetIssueContract();
        private static final Parser<ParticipateAssetIssueContract> PARSER = new AbstractParser<ParticipateAssetIssueContract>() {
            @Override
            public ParticipateAssetIssueContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ParticipateAssetIssueContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ParticipateAssetIssueContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ParticipateAssetIssueContract> parser() {
            return PARSER;
        }

        @Override
        public long getAmount() {
            return this.amount_;
        }

        @Override
        public ByteString getAssetName() {
            return this.assetName_;
        }

        @Override
        public ParticipateAssetIssueContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<ParticipateAssetIssueContract> getParserForType() {
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

        private ParticipateAssetIssueContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ParticipateAssetIssueContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.toAddress_ = ByteString.EMPTY;
            this.assetName_ = ByteString.EMPTY;
            this.amount_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ParticipateAssetIssueContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            } else if (readTag == 26) {
                                this.assetName_ = codedInputStream.readBytes();
                            } else if (readTag != 32) {
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
            return AssetIssueContractOuterClass.internal_static_protocol_ParticipateAssetIssueContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AssetIssueContractOuterClass.internal_static_protocol_ParticipateAssetIssueContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ParticipateAssetIssueContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.toAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.toAddress_);
            }
            if (!this.assetName_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.assetName_);
            }
            long j = this.amount_;
            if (j != 0) {
                codedOutputStream.writeInt64(4, j);
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
            if (!this.assetName_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.assetName_);
            }
            long j = this.amount_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(4, j);
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
            if (!(obj instanceof ParticipateAssetIssueContract)) {
                return super.equals(obj);
            }
            ParticipateAssetIssueContract participateAssetIssueContract = (ParticipateAssetIssueContract) obj;
            return getOwnerAddress().equals(participateAssetIssueContract.getOwnerAddress()) && getToAddress().equals(participateAssetIssueContract.getToAddress()) && getAssetName().equals(participateAssetIssueContract.getAssetName()) && getAmount() == participateAssetIssueContract.getAmount() && this.unknownFields.equals(participateAssetIssueContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getToAddress().hashCode()) * 37) + 3) * 53) + getAssetName().hashCode()) * 37) + 4) * 53) + Internal.hashLong(getAmount())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ParticipateAssetIssueContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ParticipateAssetIssueContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ParticipateAssetIssueContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ParticipateAssetIssueContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ParticipateAssetIssueContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ParticipateAssetIssueContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ParticipateAssetIssueContract parseFrom(InputStream inputStream) throws IOException {
            return (ParticipateAssetIssueContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ParticipateAssetIssueContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ParticipateAssetIssueContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ParticipateAssetIssueContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ParticipateAssetIssueContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ParticipateAssetIssueContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ParticipateAssetIssueContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ParticipateAssetIssueContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ParticipateAssetIssueContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ParticipateAssetIssueContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ParticipateAssetIssueContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ParticipateAssetIssueContract participateAssetIssueContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(participateAssetIssueContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ParticipateAssetIssueContractOrBuilder {
            private long amount_;
            private ByteString assetName_;
            private ByteString ownerAddress_;
            private ByteString toAddress_;

            @Override
            public long getAmount() {
                return this.amount_;
            }

            @Override
            public ByteString getAssetName() {
                return this.assetName_;
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
                return AssetIssueContractOuterClass.internal_static_protocol_ParticipateAssetIssueContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AssetIssueContractOuterClass.internal_static_protocol_ParticipateAssetIssueContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ParticipateAssetIssueContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.toAddress_ = ByteString.EMPTY;
                this.assetName_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.toAddress_ = ByteString.EMPTY;
                this.assetName_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ParticipateAssetIssueContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.toAddress_ = ByteString.EMPTY;
                this.assetName_ = ByteString.EMPTY;
                this.amount_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return AssetIssueContractOuterClass.internal_static_protocol_ParticipateAssetIssueContract_descriptor;
            }

            @Override
            public ParticipateAssetIssueContract getDefaultInstanceForType() {
                return ParticipateAssetIssueContract.getDefaultInstance();
            }

            @Override
            public ParticipateAssetIssueContract build() {
                ParticipateAssetIssueContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ParticipateAssetIssueContract buildPartial() {
                ParticipateAssetIssueContract participateAssetIssueContract = new ParticipateAssetIssueContract(this);
                participateAssetIssueContract.ownerAddress_ = this.ownerAddress_;
                participateAssetIssueContract.toAddress_ = this.toAddress_;
                participateAssetIssueContract.assetName_ = this.assetName_;
                participateAssetIssueContract.amount_ = this.amount_;
                onBuilt();
                return participateAssetIssueContract;
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
                if (message instanceof ParticipateAssetIssueContract) {
                    return mergeFrom((ParticipateAssetIssueContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ParticipateAssetIssueContract participateAssetIssueContract) {
                if (participateAssetIssueContract == ParticipateAssetIssueContract.getDefaultInstance()) {
                    return this;
                }
                if (participateAssetIssueContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(participateAssetIssueContract.getOwnerAddress());
                }
                if (participateAssetIssueContract.getToAddress() != ByteString.EMPTY) {
                    setToAddress(participateAssetIssueContract.getToAddress());
                }
                if (participateAssetIssueContract.getAssetName() != ByteString.EMPTY) {
                    setAssetName(participateAssetIssueContract.getAssetName());
                }
                if (participateAssetIssueContract.getAmount() != 0) {
                    setAmount(participateAssetIssueContract.getAmount());
                }
                mergeUnknownFields(participateAssetIssueContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.AssetIssueContractOuterClass.ParticipateAssetIssueContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AssetIssueContractOuterClass.ParticipateAssetIssueContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AssetIssueContractOuterClass$ParticipateAssetIssueContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = ParticipateAssetIssueContract.getDefaultInstance().getOwnerAddress();
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
                this.toAddress_ = ParticipateAssetIssueContract.getDefaultInstance().getToAddress();
                onChanged();
                return this;
            }

            public Builder setAssetName(ByteString byteString) {
                byteString.getClass();
                this.assetName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAssetName() {
                this.assetName_ = ParticipateAssetIssueContract.getDefaultInstance().getAssetName();
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

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n(core/contract/asset_issue_contract.proto\u0012\bprotocol\"\u0090\u0004\n\u0012AssetIssueContract\u0012\n\n\u0002id\u0018) \u0001(\t\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\f\n\u0004name\u0018\u0002 \u0001(\f\u0012\f\n\u0004abbr\u0018\u0003 \u0001(\f\u0012\u0014\n\ftotal_supply\u0018\u0004 \u0001(\u0003\u0012@\n\rfrozen_supply\u0018\u0005 \u0003(\u000b2).protocol.AssetIssueContract.FrozenSupply\u0012\u000f\n\u0007trx_num\u0018\u0006 \u0001(\u0005\u0012\u0011\n\tprecision\u0018\u0007 \u0001(\u0005\u0012\u000b\n\u0003num\u0018\b \u0001(\u0005\u0012\u0012\n\nstart_time\u0018\t \u0001(\u0003\u0012\u0010\n\bend_time\u0018\n \u0001(\u0003\u0012\r\n\u0005order\u0018\u000b \u0001(\u0003\u0012\u0012\n\nvote_score\u0018\u0010 \u0001(\u0005\u0012\u0013\n\u000bdescription\u0018\u0014 \u0001(\f\u0012\u000b\n\u0003url\u0018\u0015 \u0001(\f\u0012\u001c\n\u0014free_asset_net_limit\u0018\u0016 \u0001(\u0003\u0012#\n\u001bpublic_free_asset_net_limit\u0018\u0017 \u0001(\u0003\u0012#\n\u001bpublic_free_asset_net_usage\u0018\u0018 \u0001(\u0003\u0012#\n\u001bpublic_latest_free_net_time\u0018\u0019 \u0001(\u0003\u001a:\n\fFrozenSupply\u0012\u0015\n\rfrozen_amount\u0018\u0001 \u0001(\u0003\u0012\u0013\n\u000bfrozen_days\u0018\u0002 \u0001(\u0003\"f\n\u0015TransferAssetContract\u0012\u0012\n\nasset_name\u0018\u0001 \u0001(\f\u0012\u0015\n\rowner_address\u0018\u0002 \u0001(\f\u0012\u0012\n\nto_address\u0018\u0003 \u0001(\f\u0012\u000e\n\u0006amount\u0018\u0004 \u0001(\u0003\".\n\u0015UnfreezeAssetContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\"{\n\u0013UpdateAssetContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\f\u0012\u000b\n\u0003url\u0018\u0003 \u0001(\f\u0012\u0011\n\tnew_limit\u0018\u0004 \u0001(\u0003\u0012\u0018\n\u0010new_public_limit\u0018\u0005 \u0001(\u0003\"n\n\u001dParticipateAssetIssueContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0012\n\nto_address\u0018\u0002 \u0001(\f\u0012\u0012\n\nasset_name\u0018\u0003 \u0001(\f\u0012\u000e\n\u0006amount\u0018\u0004 \u0001(\u0003BE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = AssetIssueContractOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_AssetIssueContract_descriptor = descriptor2;
        internal_static_protocol_AssetIssueContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Id", "OwnerAddress", "Name", "Abbr", "TotalSupply", "FrozenSupply", "TrxNum", "Precision", "Num", "StartTime", "EndTime", "Order", "VoteScore", "Description", "Url", "FreeAssetNetLimit", "PublicFreeAssetNetLimit", "PublicFreeAssetNetUsage", "PublicLatestFreeNetTime"});
        Descriptors.Descriptor descriptor3 = descriptor2.getNestedTypes().get(0);
        internal_static_protocol_AssetIssueContract_FrozenSupply_descriptor = descriptor3;
        internal_static_protocol_AssetIssueContract_FrozenSupply_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"FrozenAmount", "FrozenDays"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_TransferAssetContract_descriptor = descriptor4;
        internal_static_protocol_TransferAssetContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"AssetName", "OwnerAddress", "ToAddress", "Amount"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_UnfreezeAssetContract_descriptor = descriptor5;
        internal_static_protocol_UnfreezeAssetContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"OwnerAddress"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(3);
        internal_static_protocol_UpdateAssetContract_descriptor = descriptor6;
        internal_static_protocol_UpdateAssetContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"OwnerAddress", "Description", "Url", "NewLimit", "NewPublicLimit"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(4);
        internal_static_protocol_ParticipateAssetIssueContract_descriptor = descriptor7;
        internal_static_protocol_ParticipateAssetIssueContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"OwnerAddress", "ToAddress", "AssetName", "Amount"});
    }
}
