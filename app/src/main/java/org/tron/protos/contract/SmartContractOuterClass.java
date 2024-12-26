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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.tron.tron_base.frame.net.SignatureManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.tron.protos.Protocol;
public final class SmartContractOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_ClearABIContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ClearABIContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_ContractState_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_ContractState_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_CreateSmartContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_CreateSmartContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_SmartContractDataWrapper_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_SmartContractDataWrapper_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_SmartContract_ABI_Entry_Param_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_SmartContract_ABI_Entry_Param_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_SmartContract_ABI_Entry_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_SmartContract_ABI_Entry_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_SmartContract_ABI_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_SmartContract_ABI_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_SmartContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_SmartContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_TriggerSmartContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_TriggerSmartContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_UpdateEnergyLimitContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_UpdateEnergyLimitContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_UpdateSettingContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_UpdateSettingContract_fieldAccessorTable;

    public interface ClearABIContractOrBuilder extends MessageOrBuilder {
        ByteString getContractAddress();

        ByteString getOwnerAddress();
    }

    public interface ContractStateOrBuilder extends MessageOrBuilder {
        long getEnergyFactor();

        long getEnergyUsage();

        long getUpdateCycle();
    }

    public interface CreateSmartContractOrBuilder extends MessageOrBuilder {
        long getCallTokenValue();

        SmartContract getNewContract();

        SmartContractOrBuilder getNewContractOrBuilder();

        ByteString getOwnerAddress();

        long getTokenId();

        boolean hasNewContract();
    }

    public interface SmartContractDataWrapperOrBuilder extends MessageOrBuilder {
        ContractState getContractState();

        ContractStateOrBuilder getContractStateOrBuilder();

        ByteString getRuntimecode();

        SmartContract getSmartContract();

        SmartContractOrBuilder getSmartContractOrBuilder();

        boolean hasContractState();

        boolean hasSmartContract();
    }

    public interface SmartContractOrBuilder extends MessageOrBuilder {
        SmartContract.ABI getAbi();

        SmartContract.ABIOrBuilder getAbiOrBuilder();

        ByteString getBytecode();

        long getCallValue();

        ByteString getCodeHash();

        long getConsumeUserResourcePercent();

        ByteString getContractAddress();

        String getName();

        ByteString getNameBytes();

        ByteString getOriginAddress();

        long getOriginEnergyLimit();

        ByteString getTrxHash();

        int getVersion();

        boolean hasAbi();
    }

    public interface TriggerSmartContractOrBuilder extends MessageOrBuilder {
        long getCallTokenValue();

        long getCallValue();

        ByteString getContractAddress();

        ByteString getData();

        ByteString getOwnerAddress();

        long getTokenId();
    }

    public interface UpdateEnergyLimitContractOrBuilder extends MessageOrBuilder {
        ByteString getContractAddress();

        long getOriginEnergyLimit();

        ByteString getOwnerAddress();
    }

    public interface UpdateSettingContractOrBuilder extends MessageOrBuilder {
        long getConsumeUserResourcePercent();

        ByteString getContractAddress();

        ByteString getOwnerAddress();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private SmartContractOuterClass() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class SmartContract extends GeneratedMessageV3 implements SmartContractOrBuilder {
        public static final int ABI_FIELD_NUMBER = 3;
        public static final int BYTECODE_FIELD_NUMBER = 4;
        public static final int CALL_VALUE_FIELD_NUMBER = 5;
        public static final int CODE_HASH_FIELD_NUMBER = 9;
        public static final int CONSUME_USER_RESOURCE_PERCENT_FIELD_NUMBER = 6;
        public static final int CONTRACT_ADDRESS_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 7;
        public static final int ORIGIN_ADDRESS_FIELD_NUMBER = 1;
        public static final int ORIGIN_ENERGY_LIMIT_FIELD_NUMBER = 8;
        public static final int TRX_HASH_FIELD_NUMBER = 10;
        public static final int VERSION_FIELD_NUMBER = 11;
        private static final long serialVersionUID = 0;
        private ABI abi_;
        private ByteString bytecode_;
        private long callValue_;
        private ByteString codeHash_;
        private long consumeUserResourcePercent_;
        private ByteString contractAddress_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private ByteString originAddress_;
        private long originEnergyLimit_;
        private ByteString trxHash_;
        private int version_;
        private static final SmartContract DEFAULT_INSTANCE = new SmartContract();
        private static final Parser<SmartContract> PARSER = new AbstractParser<SmartContract>() {
            @Override
            public SmartContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new SmartContract(codedInputStream, extensionRegistryLite);
            }
        };

        public interface ABIOrBuilder extends MessageOrBuilder {
            ABI.Entry getEntrys(int i);

            int getEntrysCount();

            List<ABI.Entry> getEntrysList();

            ABI.EntryOrBuilder getEntrysOrBuilder(int i);

            List<? extends ABI.EntryOrBuilder> getEntrysOrBuilderList();
        }

        public static SmartContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SmartContract> parser() {
            return PARSER;
        }

        @Override
        public ByteString getBytecode() {
            return this.bytecode_;
        }

        @Override
        public long getCallValue() {
            return this.callValue_;
        }

        @Override
        public ByteString getCodeHash() {
            return this.codeHash_;
        }

        @Override
        public long getConsumeUserResourcePercent() {
            return this.consumeUserResourcePercent_;
        }

        @Override
        public ByteString getContractAddress() {
            return this.contractAddress_;
        }

        @Override
        public SmartContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOriginAddress() {
            return this.originAddress_;
        }

        @Override
        public long getOriginEnergyLimit() {
            return this.originEnergyLimit_;
        }

        @Override
        public Parser<SmartContract> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getTrxHash() {
            return this.trxHash_;
        }

        @Override
        public int getVersion() {
            return this.version_;
        }

        @Override
        public boolean hasAbi() {
            return this.abi_ != null;
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

        private SmartContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private SmartContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.originAddress_ = ByteString.EMPTY;
            this.contractAddress_ = ByteString.EMPTY;
            this.bytecode_ = ByteString.EMPTY;
            this.callValue_ = 0L;
            this.consumeUserResourcePercent_ = 0L;
            this.name_ = "";
            this.originEnergyLimit_ = 0L;
            this.codeHash_ = ByteString.EMPTY;
            this.trxHash_ = ByteString.EMPTY;
            this.version_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private SmartContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                break;
                            case 10:
                                this.originAddress_ = codedInputStream.readBytes();
                                continue;
                            case 18:
                                this.contractAddress_ = codedInputStream.readBytes();
                                continue;
                            case 26:
                                ABI abi = this.abi_;
                                ABI.Builder builder = abi != null ? abi.toBuilder() : null;
                                ABI abi2 = (ABI) codedInputStream.readMessage(ABI.parser(), extensionRegistryLite);
                                this.abi_ = abi2;
                                if (builder != null) {
                                    builder.mergeFrom(abi2);
                                    this.abi_ = builder.buildPartial();
                                } else {
                                    continue;
                                }
                            case 34:
                                this.bytecode_ = codedInputStream.readBytes();
                                continue;
                            case 40:
                                this.callValue_ = codedInputStream.readInt64();
                                continue;
                            case 48:
                                this.consumeUserResourcePercent_ = codedInputStream.readInt64();
                                continue;
                            case 58:
                                this.name_ = codedInputStream.readStringRequireUtf8();
                                continue;
                            case 64:
                                this.originEnergyLimit_ = codedInputStream.readInt64();
                                continue;
                            case 74:
                                this.codeHash_ = codedInputStream.readBytes();
                                continue;
                            case 82:
                                this.trxHash_ = codedInputStream.readBytes();
                                continue;
                            case 88:
                                this.version_ = codedInputStream.readInt32();
                                continue;
                            default:
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    break;
                                } else {
                                    continue;
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
            return SmartContractOuterClass.internal_static_protocol_SmartContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SmartContractOuterClass.internal_static_protocol_SmartContract_fieldAccessorTable.ensureFieldAccessorsInitialized(SmartContract.class, Builder.class);
        }

        public static final class ABI extends GeneratedMessageV3 implements ABIOrBuilder {
            public static final int ENTRYS_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private List<Entry> entrys_;
            private byte memoizedIsInitialized;
            private static final ABI DEFAULT_INSTANCE = new ABI();
            private static final Parser<ABI> PARSER = new AbstractParser<ABI>() {
                @Override
                public ABI parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ABI(codedInputStream, extensionRegistryLite);
                }
            };

            public interface EntryOrBuilder extends MessageOrBuilder {
                boolean getAnonymous();

                boolean getConstant();

                Entry.Param getInputs(int i);

                int getInputsCount();

                List<Entry.Param> getInputsList();

                Entry.ParamOrBuilder getInputsOrBuilder(int i);

                List<? extends Entry.ParamOrBuilder> getInputsOrBuilderList();

                String getName();

                ByteString getNameBytes();

                Entry.Param getOutputs(int i);

                int getOutputsCount();

                List<Entry.Param> getOutputsList();

                Entry.ParamOrBuilder getOutputsOrBuilder(int i);

                List<? extends Entry.ParamOrBuilder> getOutputsOrBuilderList();

                boolean getPayable();

                Entry.StateMutabilityType getStateMutability();

                int getStateMutabilityValue();

                Entry.EntryType getType();

                int getTypeValue();
            }

            public static ABI getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ABI> parser() {
                return PARSER;
            }

            @Override
            public ABI getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override
            public List<Entry> getEntrysList() {
                return this.entrys_;
            }

            @Override
            public List<? extends EntryOrBuilder> getEntrysOrBuilderList() {
                return this.entrys_;
            }

            @Override
            public Parser<ABI> getParserForType() {
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

            private ABI(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private ABI() {
                this.memoizedIsInitialized = (byte) -1;
                this.entrys_ = Collections.emptyList();
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private ABI(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag != 10) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    if (!(z2 & true)) {
                                        this.entrys_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.entrys_.add((Entry) codedInputStream.readMessage(Entry.parser(), extensionRegistryLite));
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
                            this.entrys_ = Collections.unmodifiableList(this.entrys_);
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_fieldAccessorTable.ensureFieldAccessorsInitialized(ABI.class, Builder.class);
            }

            public static final class Entry extends GeneratedMessageV3 implements EntryOrBuilder {
                public static final int ANONYMOUS_FIELD_NUMBER = 1;
                public static final int CONSTANT_FIELD_NUMBER = 2;
                public static final int INPUTS_FIELD_NUMBER = 4;
                public static final int NAME_FIELD_NUMBER = 3;
                public static final int OUTPUTS_FIELD_NUMBER = 5;
                public static final int PAYABLE_FIELD_NUMBER = 7;
                public static final int STATEMUTABILITY_FIELD_NUMBER = 8;
                public static final int TYPE_FIELD_NUMBER = 6;
                private static final long serialVersionUID = 0;
                private boolean anonymous_;
                private int bitField0_;
                private boolean constant_;
                private List<Param> inputs_;
                private byte memoizedIsInitialized;
                private volatile Object name_;
                private List<Param> outputs_;
                private boolean payable_;
                private int stateMutability_;
                private int type_;
                private static final Entry DEFAULT_INSTANCE = new Entry();
                private static final Parser<Entry> PARSER = new AbstractParser<Entry>() {
                    @Override
                    public Entry parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return new Entry(codedInputStream, extensionRegistryLite);
                    }
                };

                public interface ParamOrBuilder extends MessageOrBuilder {
                    boolean getIndexed();

                    String getName();

                    ByteString getNameBytes();

                    String getType();

                    ByteString getTypeBytes();
                }

                public static Entry getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<Entry> parser() {
                    return PARSER;
                }

                @Override
                public boolean getAnonymous() {
                    return this.anonymous_;
                }

                @Override
                public boolean getConstant() {
                    return this.constant_;
                }

                @Override
                public Entry getDefaultInstanceForType() {
                    return DEFAULT_INSTANCE;
                }

                @Override
                public List<Param> getInputsList() {
                    return this.inputs_;
                }

                @Override
                public List<? extends ParamOrBuilder> getInputsOrBuilderList() {
                    return this.inputs_;
                }

                @Override
                public List<Param> getOutputsList() {
                    return this.outputs_;
                }

                @Override
                public List<? extends ParamOrBuilder> getOutputsOrBuilderList() {
                    return this.outputs_;
                }

                @Override
                public Parser<Entry> getParserForType() {
                    return PARSER;
                }

                @Override
                public boolean getPayable() {
                    return this.payable_;
                }

                @Override
                public int getStateMutabilityValue() {
                    return this.stateMutability_;
                }

                @Override
                public int getTypeValue() {
                    return this.type_;
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

                private Entry(GeneratedMessageV3.Builder<?> builder) {
                    super(builder);
                    this.memoizedIsInitialized = (byte) -1;
                }

                private Entry() {
                    this.memoizedIsInitialized = (byte) -1;
                    this.anonymous_ = false;
                    this.constant_ = false;
                    this.name_ = "";
                    this.inputs_ = Collections.emptyList();
                    this.outputs_ = Collections.emptyList();
                    this.type_ = 0;
                    this.payable_ = false;
                    this.stateMutability_ = 0;
                }

                @Override
                public final UnknownFieldSet getUnknownFields() {
                    return this.unknownFields;
                }

                private Entry(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    if (readTag == 8) {
                                        this.anonymous_ = codedInputStream.readBool();
                                    } else if (readTag == 16) {
                                        this.constant_ = codedInputStream.readBool();
                                    } else if (readTag == 26) {
                                        this.name_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag == 34) {
                                        if (!(z2 & true)) {
                                            this.inputs_ = new ArrayList();
                                            z2 |= true;
                                        }
                                        this.inputs_.add((Param) codedInputStream.readMessage(Param.parser(), extensionRegistryLite));
                                    } else if (readTag == 42) {
                                        if (!(z2 & true)) {
                                            this.outputs_ = new ArrayList();
                                            z2 |= true;
                                        }
                                        this.outputs_.add((Param) codedInputStream.readMessage(Param.parser(), extensionRegistryLite));
                                    } else if (readTag == 48) {
                                        this.type_ = codedInputStream.readEnum();
                                    } else if (readTag == 56) {
                                        this.payable_ = codedInputStream.readBool();
                                    } else if (readTag != 64) {
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.stateMutability_ = codedInputStream.readEnum();
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
                                this.inputs_ = Collections.unmodifiableList(this.inputs_);
                            }
                            if (z2 & true) {
                                this.outputs_ = Collections.unmodifiableList(this.outputs_);
                            }
                            this.unknownFields = newBuilder.build();
                            makeExtensionsImmutable();
                        }
                    }
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_descriptor;
                }

                @Override
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_fieldAccessorTable.ensureFieldAccessorsInitialized(Entry.class, Builder.class);
                }

                public enum EntryType implements ProtocolMessageEnum {
                    UnknownEntryType(0),
                    Constructor(1),
                    Function(2),
                    Event(3),
                    Fallback(4),
                    Receive(5),
                    Error(6),
                    UNRECOGNIZED(-1);
                    
                    public static final int Constructor_VALUE = 1;
                    public static final int Error_VALUE = 6;
                    public static final int Event_VALUE = 3;
                    public static final int Fallback_VALUE = 4;
                    public static final int Function_VALUE = 2;
                    public static final int Receive_VALUE = 5;
                    public static final int UnknownEntryType_VALUE = 0;
                    private final int value;
                    private static final Internal.EnumLiteMap<EntryType> internalValueMap = new Internal.EnumLiteMap<EntryType>() {
                        @Override
                        public EntryType findValueByNumber(int i) {
                            return EntryType.forNumber(i);
                        }
                    };
                    private static final EntryType[] VALUES = values();

                    public static EntryType forNumber(int i) {
                        switch (i) {
                            case 0:
                                return UnknownEntryType;
                            case 1:
                                return Constructor;
                            case 2:
                                return Function;
                            case 3:
                                return Event;
                            case 4:
                                return Fallback;
                            case 5:
                                return Receive;
                            case 6:
                                return Error;
                            default:
                                return null;
                        }
                    }

                    public static Internal.EnumLiteMap<EntryType> internalGetValueMap() {
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
                    public static EntryType valueOf(int i) {
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
                        return Entry.getDescriptor().getEnumTypes().get(0);
                    }

                    public static EntryType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                        if (enumValueDescriptor.getType() == getDescriptor()) {
                            return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
                        }
                        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                    }

                    EntryType(int i) {
                        this.value = i;
                    }
                }

                public enum StateMutabilityType implements ProtocolMessageEnum {
                    UnknownMutabilityType(0),
                    Pure(1),
                    View(2),
                    Nonpayable(3),
                    Payable(4),
                    UNRECOGNIZED(-1);
                    
                    public static final int Nonpayable_VALUE = 3;
                    public static final int Payable_VALUE = 4;
                    public static final int Pure_VALUE = 1;
                    public static final int UnknownMutabilityType_VALUE = 0;
                    public static final int View_VALUE = 2;
                    private final int value;
                    private static final Internal.EnumLiteMap<StateMutabilityType> internalValueMap = new Internal.EnumLiteMap<StateMutabilityType>() {
                        @Override
                        public StateMutabilityType findValueByNumber(int i) {
                            return StateMutabilityType.forNumber(i);
                        }
                    };
                    private static final StateMutabilityType[] VALUES = values();

                    public static StateMutabilityType forNumber(int i) {
                        if (i != 0) {
                            if (i != 1) {
                                if (i != 2) {
                                    if (i != 3) {
                                        if (i != 4) {
                                            return null;
                                        }
                                        return Payable;
                                    }
                                    return Nonpayable;
                                }
                                return View;
                            }
                            return Pure;
                        }
                        return UnknownMutabilityType;
                    }

                    public static Internal.EnumLiteMap<StateMutabilityType> internalGetValueMap() {
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
                    public static StateMutabilityType valueOf(int i) {
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
                        return Entry.getDescriptor().getEnumTypes().get(1);
                    }

                    public static StateMutabilityType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                        if (enumValueDescriptor.getType() == getDescriptor()) {
                            return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
                        }
                        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                    }

                    StateMutabilityType(int i) {
                        this.value = i;
                    }
                }

                public static final class Param extends GeneratedMessageV3 implements ParamOrBuilder {
                    public static final int INDEXED_FIELD_NUMBER = 1;
                    public static final int NAME_FIELD_NUMBER = 2;
                    public static final int TYPE_FIELD_NUMBER = 3;
                    private static final long serialVersionUID = 0;
                    private boolean indexed_;
                    private byte memoizedIsInitialized;
                    private volatile Object name_;
                    private volatile Object type_;
                    private static final Param DEFAULT_INSTANCE = new Param();
                    private static final Parser<Param> PARSER = new AbstractParser<Param>() {
                        @Override
                        public Param parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                            return new Param(codedInputStream, extensionRegistryLite);
                        }
                    };

                    public static Param getDefaultInstance() {
                        return DEFAULT_INSTANCE;
                    }

                    public static Parser<Param> parser() {
                        return PARSER;
                    }

                    @Override
                    public Param getDefaultInstanceForType() {
                        return DEFAULT_INSTANCE;
                    }

                    @Override
                    public boolean getIndexed() {
                        return this.indexed_;
                    }

                    @Override
                    public Parser<Param> getParserForType() {
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

                    private Param(GeneratedMessageV3.Builder<?> builder) {
                        super(builder);
                        this.memoizedIsInitialized = (byte) -1;
                    }

                    private Param() {
                        this.memoizedIsInitialized = (byte) -1;
                        this.indexed_ = false;
                        this.name_ = "";
                        this.type_ = "";
                    }

                    @Override
                    public final UnknownFieldSet getUnknownFields() {
                        return this.unknownFields;
                    }

                    private Param(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                            this.indexed_ = codedInputStream.readBool();
                                        } else if (readTag == 18) {
                                            this.name_ = codedInputStream.readStringRequireUtf8();
                                        } else if (readTag != 26) {
                                            if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                            }
                                        } else {
                                            this.type_ = codedInputStream.readStringRequireUtf8();
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
                        return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_Param_descriptor;
                    }

                    @Override
                    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                        return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_Param_fieldAccessorTable.ensureFieldAccessorsInitialized(Param.class, Builder.class);
                    }

                    @Override
                    public String getName() {
                        Object obj = this.name_;
                        if (obj instanceof String) {
                            return (String) obj;
                        }
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.name_ = stringUtf8;
                        return stringUtf8;
                    }

                    @Override
                    public ByteString getNameBytes() {
                        Object obj = this.name_;
                        if (obj instanceof String) {
                            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.name_ = copyFromUtf8;
                            return copyFromUtf8;
                        }
                        return (ByteString) obj;
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
                    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                        boolean z = this.indexed_;
                        if (z) {
                            codedOutputStream.writeBool(1, z);
                        }
                        if (!getNameBytes().isEmpty()) {
                            GeneratedMessageV3.writeString(codedOutputStream, 2, this.name_);
                        }
                        if (!getTypeBytes().isEmpty()) {
                            GeneratedMessageV3.writeString(codedOutputStream, 3, this.type_);
                        }
                        this.unknownFields.writeTo(codedOutputStream);
                    }

                    @Override
                    public int getSerializedSize() {
                        int i = this.memoizedSize;
                        if (i != -1) {
                            return i;
                        }
                        boolean z = this.indexed_;
                        int computeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
                        if (!getNameBytes().isEmpty()) {
                            computeBoolSize += GeneratedMessageV3.computeStringSize(2, this.name_);
                        }
                        if (!getTypeBytes().isEmpty()) {
                            computeBoolSize += GeneratedMessageV3.computeStringSize(3, this.type_);
                        }
                        int serializedSize = computeBoolSize + this.unknownFields.getSerializedSize();
                        this.memoizedSize = serializedSize;
                        return serializedSize;
                    }

                    @Override
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Param)) {
                            return super.equals(obj);
                        }
                        Param param = (Param) obj;
                        return getIndexed() == param.getIndexed() && getName().equals(param.getName()) && getType().equals(param.getType()) && this.unknownFields.equals(param.unknownFields);
                    }

                    @Override
                    public int hashCode() {
                        if (this.memoizedHashCode != 0) {
                            return this.memoizedHashCode;
                        }
                        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getIndexed())) * 37) + 2) * 53) + getName().hashCode()) * 37) + 3) * 53) + getType().hashCode()) * 29) + this.unknownFields.hashCode();
                        this.memoizedHashCode = hashCode;
                        return hashCode;
                    }

                    public static Param parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                        return PARSER.parseFrom(byteBuffer);
                    }

                    public static Param parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
                    }

                    public static Param parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                        return PARSER.parseFrom(byteString);
                    }

                    public static Param parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return PARSER.parseFrom(byteString, extensionRegistryLite);
                    }

                    public static Param parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                        return PARSER.parseFrom(bArr);
                    }

                    public static Param parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return PARSER.parseFrom(bArr, extensionRegistryLite);
                    }

                    public static Param parseFrom(InputStream inputStream) throws IOException {
                        return (Param) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
                    }

                    public static Param parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                        return (Param) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
                    }

                    public static Param parseDelimitedFrom(InputStream inputStream) throws IOException {
                        return (Param) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
                    }

                    public static Param parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                        return (Param) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
                    }

                    public static Param parseFrom(CodedInputStream codedInputStream) throws IOException {
                        return (Param) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
                    }

                    public static Param parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                        return (Param) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
                    }

                    @Override
                    public Builder newBuilderForType() {
                        return newBuilder();
                    }

                    public static Builder newBuilder() {
                        return DEFAULT_INSTANCE.toBuilder();
                    }

                    public static Builder newBuilder(Param param) {
                        return DEFAULT_INSTANCE.toBuilder().mergeFrom(param);
                    }

                    @Override
                    public Builder toBuilder() {
                        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
                    }

                    @Override
                    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                        return new Builder(builderParent);
                    }

                    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ParamOrBuilder {
                        private boolean indexed_;
                        private Object name_;
                        private Object type_;

                        @Override
                        public boolean getIndexed() {
                            return this.indexed_;
                        }

                        @Override
                        public final boolean isInitialized() {
                            return true;
                        }

                        public static final Descriptors.Descriptor getDescriptor() {
                            return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_Param_descriptor;
                        }

                        @Override
                        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                            return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_Param_fieldAccessorTable.ensureFieldAccessorsInitialized(Param.class, Builder.class);
                        }

                        private Builder() {
                            this.name_ = "";
                            this.type_ = "";
                            maybeForceBuilderInitialization();
                        }

                        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                            super(builderParent);
                            this.name_ = "";
                            this.type_ = "";
                            maybeForceBuilderInitialization();
                        }

                        private void maybeForceBuilderInitialization() {
                            boolean unused = Param.alwaysUseFieldBuilders;
                        }

                        @Override
                        public Builder clear() {
                            super.clear();
                            this.indexed_ = false;
                            this.name_ = "";
                            this.type_ = "";
                            return this;
                        }

                        @Override
                        public Descriptors.Descriptor getDescriptorForType() {
                            return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_Param_descriptor;
                        }

                        @Override
                        public Param getDefaultInstanceForType() {
                            return Param.getDefaultInstance();
                        }

                        @Override
                        public Param build() {
                            Param buildPartial = buildPartial();
                            if (buildPartial.isInitialized()) {
                                return buildPartial;
                            }
                            throw newUninitializedMessageException((Message) buildPartial);
                        }

                        @Override
                        public Param buildPartial() {
                            Param param = new Param(this);
                            param.indexed_ = this.indexed_;
                            param.name_ = this.name_;
                            param.type_ = this.type_;
                            onBuilt();
                            return param;
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
                            if (message instanceof Param) {
                                return mergeFrom((Param) message);
                            }
                            super.mergeFrom(message);
                            return this;
                        }

                        public Builder mergeFrom(Param param) {
                            if (param == Param.getDefaultInstance()) {
                                return this;
                            }
                            if (param.getIndexed()) {
                                setIndexed(param.getIndexed());
                            }
                            if (!param.getName().isEmpty()) {
                                this.name_ = param.name_;
                                onChanged();
                            }
                            if (!param.getType().isEmpty()) {
                                this.type_ = param.type_;
                                onChanged();
                            }
                            mergeUnknownFields(param.unknownFields);
                            onChanged();
                            return this;
                        }

                        @Override
                        public org.tron.protos.contract.SmartContractOuterClass.SmartContract.ABI.Entry.Param.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                            


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.SmartContract.ABI.Entry.Param.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$SmartContract$ABI$Entry$Param$Builder");
                        }

                        public Builder setIndexed(boolean z) {
                            this.indexed_ = z;
                            onChanged();
                            return this;
                        }

                        public Builder clearIndexed() {
                            this.indexed_ = false;
                            onChanged();
                            return this;
                        }

                        @Override
                        public String getName() {
                            Object obj = this.name_;
                            if (!(obj instanceof String)) {
                                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                                this.name_ = stringUtf8;
                                return stringUtf8;
                            }
                            return (String) obj;
                        }

                        @Override
                        public ByteString getNameBytes() {
                            Object obj = this.name_;
                            if (obj instanceof String) {
                                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                                this.name_ = copyFromUtf8;
                                return copyFromUtf8;
                            }
                            return (ByteString) obj;
                        }

                        public Builder setName(String str) {
                            str.getClass();
                            this.name_ = str;
                            onChanged();
                            return this;
                        }

                        public Builder clearName() {
                            this.name_ = Param.getDefaultInstance().getName();
                            onChanged();
                            return this;
                        }

                        public Builder setNameBytes(ByteString byteString) {
                            byteString.getClass();
                            Param.checkByteStringIsUtf8(byteString);
                            this.name_ = byteString;
                            onChanged();
                            return this;
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
                            this.type_ = Param.getDefaultInstance().getType();
                            onChanged();
                            return this;
                        }

                        public Builder setTypeBytes(ByteString byteString) {
                            byteString.getClass();
                            Param.checkByteStringIsUtf8(byteString);
                            this.type_ = byteString;
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
                public String getName() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                public ByteString getNameBytes() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.name_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                public int getInputsCount() {
                    return this.inputs_.size();
                }

                @Override
                public Param getInputs(int i) {
                    return this.inputs_.get(i);
                }

                @Override
                public ParamOrBuilder getInputsOrBuilder(int i) {
                    return this.inputs_.get(i);
                }

                @Override
                public int getOutputsCount() {
                    return this.outputs_.size();
                }

                @Override
                public Param getOutputs(int i) {
                    return this.outputs_.get(i);
                }

                @Override
                public ParamOrBuilder getOutputsOrBuilder(int i) {
                    return this.outputs_.get(i);
                }

                @Override
                public EntryType getType() {
                    EntryType valueOf = EntryType.valueOf(this.type_);
                    return valueOf == null ? EntryType.UNRECOGNIZED : valueOf;
                }

                @Override
                public StateMutabilityType getStateMutability() {
                    StateMutabilityType valueOf = StateMutabilityType.valueOf(this.stateMutability_);
                    return valueOf == null ? StateMutabilityType.UNRECOGNIZED : valueOf;
                }

                @Override
                public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                    boolean z = this.anonymous_;
                    if (z) {
                        codedOutputStream.writeBool(1, z);
                    }
                    boolean z2 = this.constant_;
                    if (z2) {
                        codedOutputStream.writeBool(2, z2);
                    }
                    if (!getNameBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 3, this.name_);
                    }
                    for (int i = 0; i < this.inputs_.size(); i++) {
                        codedOutputStream.writeMessage(4, this.inputs_.get(i));
                    }
                    for (int i2 = 0; i2 < this.outputs_.size(); i2++) {
                        codedOutputStream.writeMessage(5, this.outputs_.get(i2));
                    }
                    if (this.type_ != EntryType.UnknownEntryType.getNumber()) {
                        codedOutputStream.writeEnum(6, this.type_);
                    }
                    boolean z3 = this.payable_;
                    if (z3) {
                        codedOutputStream.writeBool(7, z3);
                    }
                    if (this.stateMutability_ != StateMutabilityType.UnknownMutabilityType.getNumber()) {
                        codedOutputStream.writeEnum(8, this.stateMutability_);
                    }
                    this.unknownFields.writeTo(codedOutputStream);
                }

                @Override
                public int getSerializedSize() {
                    int i = this.memoizedSize;
                    if (i != -1) {
                        return i;
                    }
                    boolean z = this.anonymous_;
                    int computeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
                    boolean z2 = this.constant_;
                    if (z2) {
                        computeBoolSize += CodedOutputStream.computeBoolSize(2, z2);
                    }
                    if (!getNameBytes().isEmpty()) {
                        computeBoolSize += GeneratedMessageV3.computeStringSize(3, this.name_);
                    }
                    for (int i2 = 0; i2 < this.inputs_.size(); i2++) {
                        computeBoolSize += CodedOutputStream.computeMessageSize(4, this.inputs_.get(i2));
                    }
                    for (int i3 = 0; i3 < this.outputs_.size(); i3++) {
                        computeBoolSize += CodedOutputStream.computeMessageSize(5, this.outputs_.get(i3));
                    }
                    if (this.type_ != EntryType.UnknownEntryType.getNumber()) {
                        computeBoolSize += CodedOutputStream.computeEnumSize(6, this.type_);
                    }
                    boolean z3 = this.payable_;
                    if (z3) {
                        computeBoolSize += CodedOutputStream.computeBoolSize(7, z3);
                    }
                    if (this.stateMutability_ != StateMutabilityType.UnknownMutabilityType.getNumber()) {
                        computeBoolSize += CodedOutputStream.computeEnumSize(8, this.stateMutability_);
                    }
                    int serializedSize = computeBoolSize + this.unknownFields.getSerializedSize();
                    this.memoizedSize = serializedSize;
                    return serializedSize;
                }

                @Override
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Entry)) {
                        return super.equals(obj);
                    }
                    Entry entry = (Entry) obj;
                    return getAnonymous() == entry.getAnonymous() && getConstant() == entry.getConstant() && getName().equals(entry.getName()) && getInputsList().equals(entry.getInputsList()) && getOutputsList().equals(entry.getOutputsList()) && this.type_ == entry.type_ && getPayable() == entry.getPayable() && this.stateMutability_ == entry.stateMutability_ && this.unknownFields.equals(entry.unknownFields);
                }

                @Override
                public int hashCode() {
                    if (this.memoizedHashCode != 0) {
                        return this.memoizedHashCode;
                    }
                    int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getAnonymous())) * 37) + 2) * 53) + Internal.hashBoolean(getConstant())) * 37) + 3) * 53) + getName().hashCode();
                    if (getInputsCount() > 0) {
                        hashCode = (((hashCode * 37) + 4) * 53) + getInputsList().hashCode();
                    }
                    if (getOutputsCount() > 0) {
                        hashCode = (((hashCode * 37) + 5) * 53) + getOutputsList().hashCode();
                    }
                    int hashBoolean = (((((((((((((hashCode * 37) + 6) * 53) + this.type_) * 37) + 7) * 53) + Internal.hashBoolean(getPayable())) * 37) + 8) * 53) + this.stateMutability_) * 29) + this.unknownFields.hashCode();
                    this.memoizedHashCode = hashBoolean;
                    return hashBoolean;
                }

                public static Entry parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                    return PARSER.parseFrom(byteBuffer);
                }

                public static Entry parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
                }

                public static Entry parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                    return PARSER.parseFrom(byteString);
                }

                public static Entry parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return PARSER.parseFrom(byteString, extensionRegistryLite);
                }

                public static Entry parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                    return PARSER.parseFrom(bArr);
                }

                public static Entry parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return PARSER.parseFrom(bArr, extensionRegistryLite);
                }

                public static Entry parseFrom(InputStream inputStream) throws IOException {
                    return (Entry) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
                }

                public static Entry parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return (Entry) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static Entry parseDelimitedFrom(InputStream inputStream) throws IOException {
                    return (Entry) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
                }

                public static Entry parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return (Entry) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static Entry parseFrom(CodedInputStream codedInputStream) throws IOException {
                    return (Entry) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
                }

                public static Entry parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return (Entry) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
                }

                @Override
                public Builder newBuilderForType() {
                    return newBuilder();
                }

                public static Builder newBuilder() {
                    return DEFAULT_INSTANCE.toBuilder();
                }

                public static Builder newBuilder(Entry entry) {
                    return DEFAULT_INSTANCE.toBuilder().mergeFrom(entry);
                }

                @Override
                public Builder toBuilder() {
                    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
                }

                @Override
                public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                    return new Builder(builderParent);
                }

                public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EntryOrBuilder {
                    private boolean anonymous_;
                    private int bitField0_;
                    private boolean constant_;
                    private RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> inputsBuilder_;
                    private List<Param> inputs_;
                    private Object name_;
                    private RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> outputsBuilder_;
                    private List<Param> outputs_;
                    private boolean payable_;
                    private int stateMutability_;
                    private int type_;

                    @Override
                    public boolean getAnonymous() {
                        return this.anonymous_;
                    }

                    @Override
                    public boolean getConstant() {
                        return this.constant_;
                    }

                    @Override
                    public boolean getPayable() {
                        return this.payable_;
                    }

                    @Override
                    public int getStateMutabilityValue() {
                        return this.stateMutability_;
                    }

                    @Override
                    public int getTypeValue() {
                        return this.type_;
                    }

                    @Override
                    public final boolean isInitialized() {
                        return true;
                    }

                    public static final Descriptors.Descriptor getDescriptor() {
                        return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_descriptor;
                    }

                    @Override
                    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                        return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_fieldAccessorTable.ensureFieldAccessorsInitialized(Entry.class, Builder.class);
                    }

                    private Builder() {
                        this.name_ = "";
                        this.inputs_ = Collections.emptyList();
                        this.outputs_ = Collections.emptyList();
                        this.type_ = 0;
                        this.stateMutability_ = 0;
                        maybeForceBuilderInitialization();
                    }

                    private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                        super(builderParent);
                        this.name_ = "";
                        this.inputs_ = Collections.emptyList();
                        this.outputs_ = Collections.emptyList();
                        this.type_ = 0;
                        this.stateMutability_ = 0;
                        maybeForceBuilderInitialization();
                    }

                    private void maybeForceBuilderInitialization() {
                        if (Entry.alwaysUseFieldBuilders) {
                            getInputsFieldBuilder();
                            getOutputsFieldBuilder();
                        }
                    }

                    @Override
                    public Builder clear() {
                        super.clear();
                        this.anonymous_ = false;
                        this.constant_ = false;
                        this.name_ = "";
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            this.inputs_ = Collections.emptyList();
                            this.bitField0_ &= -9;
                        } else {
                            repeatedFieldBuilderV3.clear();
                        }
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV32 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV32 == null) {
                            this.outputs_ = Collections.emptyList();
                            this.bitField0_ &= -17;
                        } else {
                            repeatedFieldBuilderV32.clear();
                        }
                        this.type_ = 0;
                        this.payable_ = false;
                        this.stateMutability_ = 0;
                        return this;
                    }

                    @Override
                    public Descriptors.Descriptor getDescriptorForType() {
                        return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_Entry_descriptor;
                    }

                    @Override
                    public Entry getDefaultInstanceForType() {
                        return Entry.getDefaultInstance();
                    }

                    @Override
                    public Entry build() {
                        Entry buildPartial = buildPartial();
                        if (buildPartial.isInitialized()) {
                            return buildPartial;
                        }
                        throw newUninitializedMessageException((Message) buildPartial);
                    }

                    @Override
                    public Entry buildPartial() {
                        Entry entry = new Entry(this);
                        entry.anonymous_ = this.anonymous_;
                        entry.constant_ = this.constant_;
                        entry.name_ = this.name_;
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            if ((this.bitField0_ & 8) == 8) {
                                this.inputs_ = Collections.unmodifiableList(this.inputs_);
                                this.bitField0_ &= -9;
                            }
                            entry.inputs_ = this.inputs_;
                        } else {
                            entry.inputs_ = repeatedFieldBuilderV3.build();
                        }
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV32 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV32 == null) {
                            if ((this.bitField0_ & 16) == 16) {
                                this.outputs_ = Collections.unmodifiableList(this.outputs_);
                                this.bitField0_ &= -17;
                            }
                            entry.outputs_ = this.outputs_;
                        } else {
                            entry.outputs_ = repeatedFieldBuilderV32.build();
                        }
                        entry.type_ = this.type_;
                        entry.payable_ = this.payable_;
                        entry.stateMutability_ = this.stateMutability_;
                        entry.bitField0_ = 0;
                        onBuilt();
                        return entry;
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
                        if (message instanceof Entry) {
                            return mergeFrom((Entry) message);
                        }
                        super.mergeFrom(message);
                        return this;
                    }

                    public Builder mergeFrom(Entry entry) {
                        if (entry == Entry.getDefaultInstance()) {
                            return this;
                        }
                        if (entry.getAnonymous()) {
                            setAnonymous(entry.getAnonymous());
                        }
                        if (entry.getConstant()) {
                            setConstant(entry.getConstant());
                        }
                        if (!entry.getName().isEmpty()) {
                            this.name_ = entry.name_;
                            onChanged();
                        }
                        if (this.inputsBuilder_ == null) {
                            if (!entry.inputs_.isEmpty()) {
                                if (this.inputs_.isEmpty()) {
                                    this.inputs_ = entry.inputs_;
                                    this.bitField0_ &= -9;
                                } else {
                                    ensureInputsIsMutable();
                                    this.inputs_.addAll(entry.inputs_);
                                }
                                onChanged();
                            }
                        } else if (!entry.inputs_.isEmpty()) {
                            if (!this.inputsBuilder_.isEmpty()) {
                                this.inputsBuilder_.addAllMessages(entry.inputs_);
                            } else {
                                this.inputsBuilder_.dispose();
                                this.inputsBuilder_ = null;
                                this.inputs_ = entry.inputs_;
                                this.bitField0_ &= -9;
                                this.inputsBuilder_ = Entry.alwaysUseFieldBuilders ? getInputsFieldBuilder() : null;
                            }
                        }
                        if (this.outputsBuilder_ == null) {
                            if (!entry.outputs_.isEmpty()) {
                                if (this.outputs_.isEmpty()) {
                                    this.outputs_ = entry.outputs_;
                                    this.bitField0_ &= -17;
                                } else {
                                    ensureOutputsIsMutable();
                                    this.outputs_.addAll(entry.outputs_);
                                }
                                onChanged();
                            }
                        } else if (!entry.outputs_.isEmpty()) {
                            if (!this.outputsBuilder_.isEmpty()) {
                                this.outputsBuilder_.addAllMessages(entry.outputs_);
                            } else {
                                this.outputsBuilder_.dispose();
                                this.outputsBuilder_ = null;
                                this.outputs_ = entry.outputs_;
                                this.bitField0_ &= -17;
                                this.outputsBuilder_ = Entry.alwaysUseFieldBuilders ? getOutputsFieldBuilder() : null;
                            }
                        }
                        if (entry.type_ != 0) {
                            setTypeValue(entry.getTypeValue());
                        }
                        if (entry.getPayable()) {
                            setPayable(entry.getPayable());
                        }
                        if (entry.stateMutability_ != 0) {
                            setStateMutabilityValue(entry.getStateMutabilityValue());
                        }
                        mergeUnknownFields(entry.unknownFields);
                        onChanged();
                        return this;
                    }

                    @Override
                    public org.tron.protos.contract.SmartContractOuterClass.SmartContract.ABI.Entry.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.SmartContract.ABI.Entry.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$SmartContract$ABI$Entry$Builder");
                    }

                    public Builder setAnonymous(boolean z) {
                        this.anonymous_ = z;
                        onChanged();
                        return this;
                    }

                    public Builder clearAnonymous() {
                        this.anonymous_ = false;
                        onChanged();
                        return this;
                    }

                    public Builder setConstant(boolean z) {
                        this.constant_ = z;
                        onChanged();
                        return this;
                    }

                    public Builder clearConstant() {
                        this.constant_ = false;
                        onChanged();
                        return this;
                    }

                    @Override
                    public String getName() {
                        Object obj = this.name_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.name_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    @Override
                    public ByteString getNameBytes() {
                        Object obj = this.name_;
                        if (obj instanceof String) {
                            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.name_ = copyFromUtf8;
                            return copyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setName(String str) {
                        str.getClass();
                        this.name_ = str;
                        onChanged();
                        return this;
                    }

                    public Builder clearName() {
                        this.name_ = Entry.getDefaultInstance().getName();
                        onChanged();
                        return this;
                    }

                    public Builder setNameBytes(ByteString byteString) {
                        byteString.getClass();
                        Entry.checkByteStringIsUtf8(byteString);
                        this.name_ = byteString;
                        onChanged();
                        return this;
                    }

                    private void ensureInputsIsMutable() {
                        if ((this.bitField0_ & 8) != 8) {
                            this.inputs_ = new ArrayList(this.inputs_);
                            this.bitField0_ |= 8;
                        }
                    }

                    @Override
                    public List<Param> getInputsList() {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            return Collections.unmodifiableList(this.inputs_);
                        }
                        return repeatedFieldBuilderV3.getMessageList();
                    }

                    @Override
                    public int getInputsCount() {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            return this.inputs_.size();
                        }
                        return repeatedFieldBuilderV3.getCount();
                    }

                    @Override
                    public Param getInputs(int i) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            return this.inputs_.get(i);
                        }
                        return repeatedFieldBuilderV3.getMessage(i);
                    }

                    public Builder setInputs(int i, Param param) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            param.getClass();
                            ensureInputsIsMutable();
                            this.inputs_.set(i, param);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.setMessage(i, param);
                        }
                        return this;
                    }

                    public Builder setInputs(int i, Param.Builder builder) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureInputsIsMutable();
                            this.inputs_.set(i, builder.build());
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.setMessage(i, builder.build());
                        }
                        return this;
                    }

                    public Builder addInputs(Param param) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            param.getClass();
                            ensureInputsIsMutable();
                            this.inputs_.add(param);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addMessage(param);
                        }
                        return this;
                    }

                    public Builder addInputs(int i, Param param) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            param.getClass();
                            ensureInputsIsMutable();
                            this.inputs_.add(i, param);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addMessage(i, param);
                        }
                        return this;
                    }

                    public Builder addInputs(Param.Builder builder) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureInputsIsMutable();
                            this.inputs_.add(builder.build());
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addMessage(builder.build());
                        }
                        return this;
                    }

                    public Builder addInputs(int i, Param.Builder builder) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureInputsIsMutable();
                            this.inputs_.add(i, builder.build());
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addMessage(i, builder.build());
                        }
                        return this;
                    }

                    public Builder addAllInputs(Iterable<? extends Param> iterable) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureInputsIsMutable();
                            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.inputs_);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addAllMessages(iterable);
                        }
                        return this;
                    }

                    public Builder clearInputs() {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            this.inputs_ = Collections.emptyList();
                            this.bitField0_ &= -9;
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.clear();
                        }
                        return this;
                    }

                    public Builder removeInputs(int i) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureInputsIsMutable();
                            this.inputs_.remove(i);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.remove(i);
                        }
                        return this;
                    }

                    public Param.Builder getInputsBuilder(int i) {
                        return getInputsFieldBuilder().getBuilder(i);
                    }

                    @Override
                    public ParamOrBuilder getInputsOrBuilder(int i) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            return this.inputs_.get(i);
                        }
                        return repeatedFieldBuilderV3.getMessageOrBuilder(i);
                    }

                    @Override
                    public List<? extends ParamOrBuilder> getInputsOrBuilderList() {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.inputsBuilder_;
                        if (repeatedFieldBuilderV3 != null) {
                            return repeatedFieldBuilderV3.getMessageOrBuilderList();
                        }
                        return Collections.unmodifiableList(this.inputs_);
                    }

                    public Param.Builder addInputsBuilder() {
                        return getInputsFieldBuilder().addBuilder(Param.getDefaultInstance());
                    }

                    public Param.Builder addInputsBuilder(int i) {
                        return getInputsFieldBuilder().addBuilder(i, Param.getDefaultInstance());
                    }

                    public List<Param.Builder> getInputsBuilderList() {
                        return getInputsFieldBuilder().getBuilderList();
                    }

                    private RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> getInputsFieldBuilder() {
                        if (this.inputsBuilder_ == null) {
                            this.inputsBuilder_ = new RepeatedFieldBuilderV3<>(this.inputs_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                            this.inputs_ = null;
                        }
                        return this.inputsBuilder_;
                    }

                    private void ensureOutputsIsMutable() {
                        if ((this.bitField0_ & 16) != 16) {
                            this.outputs_ = new ArrayList(this.outputs_);
                            this.bitField0_ |= 16;
                        }
                    }

                    @Override
                    public List<Param> getOutputsList() {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            return Collections.unmodifiableList(this.outputs_);
                        }
                        return repeatedFieldBuilderV3.getMessageList();
                    }

                    @Override
                    public int getOutputsCount() {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            return this.outputs_.size();
                        }
                        return repeatedFieldBuilderV3.getCount();
                    }

                    @Override
                    public Param getOutputs(int i) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            return this.outputs_.get(i);
                        }
                        return repeatedFieldBuilderV3.getMessage(i);
                    }

                    public Builder setOutputs(int i, Param param) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            param.getClass();
                            ensureOutputsIsMutable();
                            this.outputs_.set(i, param);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.setMessage(i, param);
                        }
                        return this;
                    }

                    public Builder setOutputs(int i, Param.Builder builder) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureOutputsIsMutable();
                            this.outputs_.set(i, builder.build());
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.setMessage(i, builder.build());
                        }
                        return this;
                    }

                    public Builder addOutputs(Param param) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            param.getClass();
                            ensureOutputsIsMutable();
                            this.outputs_.add(param);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addMessage(param);
                        }
                        return this;
                    }

                    public Builder addOutputs(int i, Param param) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            param.getClass();
                            ensureOutputsIsMutable();
                            this.outputs_.add(i, param);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addMessage(i, param);
                        }
                        return this;
                    }

                    public Builder addOutputs(Param.Builder builder) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureOutputsIsMutable();
                            this.outputs_.add(builder.build());
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addMessage(builder.build());
                        }
                        return this;
                    }

                    public Builder addOutputs(int i, Param.Builder builder) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureOutputsIsMutable();
                            this.outputs_.add(i, builder.build());
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addMessage(i, builder.build());
                        }
                        return this;
                    }

                    public Builder addAllOutputs(Iterable<? extends Param> iterable) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureOutputsIsMutable();
                            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.outputs_);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.addAllMessages(iterable);
                        }
                        return this;
                    }

                    public Builder clearOutputs() {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            this.outputs_ = Collections.emptyList();
                            this.bitField0_ &= -17;
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.clear();
                        }
                        return this;
                    }

                    public Builder removeOutputs(int i) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            ensureOutputsIsMutable();
                            this.outputs_.remove(i);
                            onChanged();
                        } else {
                            repeatedFieldBuilderV3.remove(i);
                        }
                        return this;
                    }

                    public Param.Builder getOutputsBuilder(int i) {
                        return getOutputsFieldBuilder().getBuilder(i);
                    }

                    @Override
                    public ParamOrBuilder getOutputsOrBuilder(int i) {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 == null) {
                            return this.outputs_.get(i);
                        }
                        return repeatedFieldBuilderV3.getMessageOrBuilder(i);
                    }

                    @Override
                    public List<? extends ParamOrBuilder> getOutputsOrBuilderList() {
                        RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> repeatedFieldBuilderV3 = this.outputsBuilder_;
                        if (repeatedFieldBuilderV3 != null) {
                            return repeatedFieldBuilderV3.getMessageOrBuilderList();
                        }
                        return Collections.unmodifiableList(this.outputs_);
                    }

                    public Param.Builder addOutputsBuilder() {
                        return getOutputsFieldBuilder().addBuilder(Param.getDefaultInstance());
                    }

                    public Param.Builder addOutputsBuilder(int i) {
                        return getOutputsFieldBuilder().addBuilder(i, Param.getDefaultInstance());
                    }

                    public List<Param.Builder> getOutputsBuilderList() {
                        return getOutputsFieldBuilder().getBuilderList();
                    }

                    private RepeatedFieldBuilderV3<Param, Param.Builder, ParamOrBuilder> getOutputsFieldBuilder() {
                        if (this.outputsBuilder_ == null) {
                            this.outputsBuilder_ = new RepeatedFieldBuilderV3<>(this.outputs_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                            this.outputs_ = null;
                        }
                        return this.outputsBuilder_;
                    }

                    public Builder setTypeValue(int i) {
                        this.type_ = i;
                        onChanged();
                        return this;
                    }

                    @Override
                    public EntryType getType() {
                        EntryType valueOf = EntryType.valueOf(this.type_);
                        return valueOf == null ? EntryType.UNRECOGNIZED : valueOf;
                    }

                    public Builder setType(EntryType entryType) {
                        entryType.getClass();
                        this.type_ = entryType.getNumber();
                        onChanged();
                        return this;
                    }

                    public Builder clearType() {
                        this.type_ = 0;
                        onChanged();
                        return this;
                    }

                    public Builder setPayable(boolean z) {
                        this.payable_ = z;
                        onChanged();
                        return this;
                    }

                    public Builder clearPayable() {
                        this.payable_ = false;
                        onChanged();
                        return this;
                    }

                    public Builder setStateMutabilityValue(int i) {
                        this.stateMutability_ = i;
                        onChanged();
                        return this;
                    }

                    @Override
                    public StateMutabilityType getStateMutability() {
                        StateMutabilityType valueOf = StateMutabilityType.valueOf(this.stateMutability_);
                        return valueOf == null ? StateMutabilityType.UNRECOGNIZED : valueOf;
                    }

                    public Builder setStateMutability(StateMutabilityType stateMutabilityType) {
                        stateMutabilityType.getClass();
                        this.stateMutability_ = stateMutabilityType.getNumber();
                        onChanged();
                        return this;
                    }

                    public Builder clearStateMutability() {
                        this.stateMutability_ = 0;
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
            public int getEntrysCount() {
                return this.entrys_.size();
            }

            @Override
            public Entry getEntrys(int i) {
                return this.entrys_.get(i);
            }

            @Override
            public EntryOrBuilder getEntrysOrBuilder(int i) {
                return this.entrys_.get(i);
            }

            @Override
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                for (int i = 0; i < this.entrys_.size(); i++) {
                    codedOutputStream.writeMessage(1, this.entrys_.get(i));
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
                for (int i3 = 0; i3 < this.entrys_.size(); i3++) {
                    i2 += CodedOutputStream.computeMessageSize(1, this.entrys_.get(i3));
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
                if (!(obj instanceof ABI)) {
                    return super.equals(obj);
                }
                ABI abi = (ABI) obj;
                return getEntrysList().equals(abi.getEntrysList()) && this.unknownFields.equals(abi.unknownFields);
            }

            @Override
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + getDescriptor().hashCode();
                if (getEntrysCount() > 0) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getEntrysList().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static ABI parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static ABI parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static ABI parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static ABI parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ABI parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static ABI parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static ABI parseFrom(InputStream inputStream) throws IOException {
                return (ABI) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static ABI parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ABI) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ABI parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (ABI) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static ABI parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ABI) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ABI parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (ABI) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static ABI parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ABI) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ABI abi) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(abi);
            }

            @Override
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            @Override
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ABIOrBuilder {
                private int bitField0_;
                private RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> entrysBuilder_;
                private List<Entry> entrys_;

                @Override
                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_descriptor;
                }

                @Override
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_fieldAccessorTable.ensureFieldAccessorsInitialized(ABI.class, Builder.class);
                }

                private Builder() {
                    this.entrys_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.entrys_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (ABI.alwaysUseFieldBuilders) {
                        getEntrysFieldBuilder();
                    }
                }

                @Override
                public Builder clear() {
                    super.clear();
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.entrys_ = Collections.emptyList();
                        this.bitField0_ &= -2;
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return SmartContractOuterClass.internal_static_protocol_SmartContract_ABI_descriptor;
                }

                @Override
                public ABI getDefaultInstanceForType() {
                    return ABI.getDefaultInstance();
                }

                @Override
                public ABI build() {
                    ABI buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override
                public ABI buildPartial() {
                    ABI abi = new ABI(this);
                    int i = this.bitField0_;
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        if ((i & 1) == 1) {
                            this.entrys_ = Collections.unmodifiableList(this.entrys_);
                            this.bitField0_ &= -2;
                        }
                        abi.entrys_ = this.entrys_;
                    } else {
                        abi.entrys_ = repeatedFieldBuilderV3.build();
                    }
                    onBuilt();
                    return abi;
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
                    if (message instanceof ABI) {
                        return mergeFrom((ABI) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ABI abi) {
                    if (abi == ABI.getDefaultInstance()) {
                        return this;
                    }
                    if (this.entrysBuilder_ == null) {
                        if (!abi.entrys_.isEmpty()) {
                            if (this.entrys_.isEmpty()) {
                                this.entrys_ = abi.entrys_;
                                this.bitField0_ &= -2;
                            } else {
                                ensureEntrysIsMutable();
                                this.entrys_.addAll(abi.entrys_);
                            }
                            onChanged();
                        }
                    } else if (!abi.entrys_.isEmpty()) {
                        if (!this.entrysBuilder_.isEmpty()) {
                            this.entrysBuilder_.addAllMessages(abi.entrys_);
                        } else {
                            this.entrysBuilder_.dispose();
                            this.entrysBuilder_ = null;
                            this.entrys_ = abi.entrys_;
                            this.bitField0_ &= -2;
                            this.entrysBuilder_ = ABI.alwaysUseFieldBuilders ? getEntrysFieldBuilder() : null;
                        }
                    }
                    mergeUnknownFields(abi.unknownFields);
                    onChanged();
                    return this;
                }

                @Override
                public org.tron.protos.contract.SmartContractOuterClass.SmartContract.ABI.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.SmartContract.ABI.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$SmartContract$ABI$Builder");
                }

                private void ensureEntrysIsMutable() {
                    if ((this.bitField0_ & 1) != 1) {
                        this.entrys_ = new ArrayList(this.entrys_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override
                public List<Entry> getEntrysList() {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return Collections.unmodifiableList(this.entrys_);
                    }
                    return repeatedFieldBuilderV3.getMessageList();
                }

                @Override
                public int getEntrysCount() {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.entrys_.size();
                    }
                    return repeatedFieldBuilderV3.getCount();
                }

                @Override
                public Entry getEntrys(int i) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.entrys_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessage(i);
                }

                public Builder setEntrys(int i, Entry entry) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        entry.getClass();
                        ensureEntrysIsMutable();
                        this.entrys_.set(i, entry);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, entry);
                    }
                    return this;
                }

                public Builder setEntrys(int i, Entry.Builder builder) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureEntrysIsMutable();
                        this.entrys_.set(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addEntrys(Entry entry) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        entry.getClass();
                        ensureEntrysIsMutable();
                        this.entrys_.add(entry);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(entry);
                    }
                    return this;
                }

                public Builder addEntrys(int i, Entry entry) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        entry.getClass();
                        ensureEntrysIsMutable();
                        this.entrys_.add(i, entry);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, entry);
                    }
                    return this;
                }

                public Builder addEntrys(Entry.Builder builder) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureEntrysIsMutable();
                        this.entrys_.add(builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addEntrys(int i, Entry.Builder builder) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureEntrysIsMutable();
                        this.entrys_.add(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addAllEntrys(Iterable<? extends Entry> iterable) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureEntrysIsMutable();
                        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.entrys_);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder clearEntrys() {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.entrys_ = Collections.emptyList();
                        this.bitField0_ &= -2;
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                public Builder removeEntrys(int i) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureEntrysIsMutable();
                        this.entrys_.remove(i);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.remove(i);
                    }
                    return this;
                }

                public Entry.Builder getEntrysBuilder(int i) {
                    return getEntrysFieldBuilder().getBuilder(i);
                }

                @Override
                public EntryOrBuilder getEntrysOrBuilder(int i) {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.entrys_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessageOrBuilder(i);
                }

                @Override
                public List<? extends EntryOrBuilder> getEntrysOrBuilderList() {
                    RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entrysBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        return repeatedFieldBuilderV3.getMessageOrBuilderList();
                    }
                    return Collections.unmodifiableList(this.entrys_);
                }

                public Entry.Builder addEntrysBuilder() {
                    return getEntrysFieldBuilder().addBuilder(Entry.getDefaultInstance());
                }

                public Entry.Builder addEntrysBuilder(int i) {
                    return getEntrysFieldBuilder().addBuilder(i, Entry.getDefaultInstance());
                }

                public List<Entry.Builder> getEntrysBuilderList() {
                    return getEntrysFieldBuilder().getBuilderList();
                }

                private RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> getEntrysFieldBuilder() {
                    if (this.entrysBuilder_ == null) {
                        this.entrysBuilder_ = new RepeatedFieldBuilderV3<>(this.entrys_, (this.bitField0_ & 1) == 1, getParentForChildren(), isClean());
                        this.entrys_ = null;
                    }
                    return this.entrysBuilder_;
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
        public ABI getAbi() {
            ABI abi = this.abi_;
            return abi == null ? ABI.getDefaultInstance() : abi;
        }

        @Override
        public ABIOrBuilder getAbiOrBuilder() {
            return getAbi();
        }

        @Override
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.originAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.originAddress_);
            }
            if (!this.contractAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.contractAddress_);
            }
            if (this.abi_ != null) {
                codedOutputStream.writeMessage(3, getAbi());
            }
            if (!this.bytecode_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.bytecode_);
            }
            long j = this.callValue_;
            if (j != 0) {
                codedOutputStream.writeInt64(5, j);
            }
            long j2 = this.consumeUserResourcePercent_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(6, j2);
            }
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 7, this.name_);
            }
            long j3 = this.originEnergyLimit_;
            if (j3 != 0) {
                codedOutputStream.writeInt64(8, j3);
            }
            if (!this.codeHash_.isEmpty()) {
                codedOutputStream.writeBytes(9, this.codeHash_);
            }
            if (!this.trxHash_.isEmpty()) {
                codedOutputStream.writeBytes(10, this.trxHash_);
            }
            int i = this.version_;
            if (i != 0) {
                codedOutputStream.writeInt32(11, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.originAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.originAddress_) : 0;
            if (!this.contractAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.contractAddress_);
            }
            if (this.abi_ != null) {
                computeBytesSize += CodedOutputStream.computeMessageSize(3, getAbi());
            }
            if (!this.bytecode_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(4, this.bytecode_);
            }
            long j = this.callValue_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(5, j);
            }
            long j2 = this.consumeUserResourcePercent_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(6, j2);
            }
            if (!getNameBytes().isEmpty()) {
                computeBytesSize += GeneratedMessageV3.computeStringSize(7, this.name_);
            }
            long j3 = this.originEnergyLimit_;
            if (j3 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(8, j3);
            }
            if (!this.codeHash_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(9, this.codeHash_);
            }
            if (!this.trxHash_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(10, this.trxHash_);
            }
            int i2 = this.version_;
            if (i2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt32Size(11, i2);
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
            if (!(obj instanceof SmartContract)) {
                return super.equals(obj);
            }
            SmartContract smartContract = (SmartContract) obj;
            boolean z = getOriginAddress().equals(smartContract.getOriginAddress()) && getContractAddress().equals(smartContract.getContractAddress()) && hasAbi() == smartContract.hasAbi();
            if (!hasAbi() ? z : !(!z || !getAbi().equals(smartContract.getAbi()))) {
                if (getBytecode().equals(smartContract.getBytecode()) && getCallValue() == smartContract.getCallValue() && getConsumeUserResourcePercent() == smartContract.getConsumeUserResourcePercent() && getName().equals(smartContract.getName()) && getOriginEnergyLimit() == smartContract.getOriginEnergyLimit() && getCodeHash().equals(smartContract.getCodeHash()) && getTrxHash().equals(smartContract.getTrxHash()) && getVersion() == smartContract.getVersion() && this.unknownFields.equals(smartContract.unknownFields)) {
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
            int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOriginAddress().hashCode()) * 37) + 2) * 53) + getContractAddress().hashCode();
            if (hasAbi()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getAbi().hashCode();
            }
            int hashCode2 = (((((((((((((((((((((((((((((((((hashCode * 37) + 4) * 53) + getBytecode().hashCode()) * 37) + 5) * 53) + Internal.hashLong(getCallValue())) * 37) + 6) * 53) + Internal.hashLong(getConsumeUserResourcePercent())) * 37) + 7) * 53) + getName().hashCode()) * 37) + 8) * 53) + Internal.hashLong(getOriginEnergyLimit())) * 37) + 9) * 53) + getCodeHash().hashCode()) * 37) + 10) * 53) + getTrxHash().hashCode()) * 37) + 11) * 53) + getVersion()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static SmartContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static SmartContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static SmartContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static SmartContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static SmartContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static SmartContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static SmartContract parseFrom(InputStream inputStream) throws IOException {
            return (SmartContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static SmartContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmartContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SmartContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SmartContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static SmartContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmartContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SmartContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SmartContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static SmartContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmartContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SmartContract smartContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(smartContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SmartContractOrBuilder {
            private SingleFieldBuilderV3<ABI, ABI.Builder, ABIOrBuilder> abiBuilder_;
            private ABI abi_;
            private ByteString bytecode_;
            private long callValue_;
            private ByteString codeHash_;
            private long consumeUserResourcePercent_;
            private ByteString contractAddress_;
            private Object name_;
            private ByteString originAddress_;
            private long originEnergyLimit_;
            private ByteString trxHash_;
            private int version_;

            @Override
            public ByteString getBytecode() {
                return this.bytecode_;
            }

            @Override
            public long getCallValue() {
                return this.callValue_;
            }

            @Override
            public ByteString getCodeHash() {
                return this.codeHash_;
            }

            @Override
            public long getConsumeUserResourcePercent() {
                return this.consumeUserResourcePercent_;
            }

            @Override
            public ByteString getContractAddress() {
                return this.contractAddress_;
            }

            @Override
            public ByteString getOriginAddress() {
                return this.originAddress_;
            }

            @Override
            public long getOriginEnergyLimit() {
                return this.originEnergyLimit_;
            }

            @Override
            public ByteString getTrxHash() {
                return this.trxHash_;
            }

            @Override
            public int getVersion() {
                return this.version_;
            }

            @Override
            public boolean hasAbi() {
                return (this.abiBuilder_ == null && this.abi_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SmartContractOuterClass.internal_static_protocol_SmartContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SmartContractOuterClass.internal_static_protocol_SmartContract_fieldAccessorTable.ensureFieldAccessorsInitialized(SmartContract.class, Builder.class);
            }

            private Builder() {
                this.originAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                this.abi_ = null;
                this.bytecode_ = ByteString.EMPTY;
                this.name_ = "";
                this.codeHash_ = ByteString.EMPTY;
                this.trxHash_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.originAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                this.abi_ = null;
                this.bytecode_ = ByteString.EMPTY;
                this.name_ = "";
                this.codeHash_ = ByteString.EMPTY;
                this.trxHash_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = SmartContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.originAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                if (this.abiBuilder_ == null) {
                    this.abi_ = null;
                } else {
                    this.abi_ = null;
                    this.abiBuilder_ = null;
                }
                this.bytecode_ = ByteString.EMPTY;
                this.callValue_ = 0L;
                this.consumeUserResourcePercent_ = 0L;
                this.name_ = "";
                this.originEnergyLimit_ = 0L;
                this.codeHash_ = ByteString.EMPTY;
                this.trxHash_ = ByteString.EMPTY;
                this.version_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return SmartContractOuterClass.internal_static_protocol_SmartContract_descriptor;
            }

            @Override
            public SmartContract getDefaultInstanceForType() {
                return SmartContract.getDefaultInstance();
            }

            @Override
            public SmartContract build() {
                SmartContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public SmartContract buildPartial() {
                SmartContract smartContract = new SmartContract(this);
                smartContract.originAddress_ = this.originAddress_;
                smartContract.contractAddress_ = this.contractAddress_;
                SingleFieldBuilderV3<ABI, ABI.Builder, ABIOrBuilder> singleFieldBuilderV3 = this.abiBuilder_;
                if (singleFieldBuilderV3 == null) {
                    smartContract.abi_ = this.abi_;
                } else {
                    smartContract.abi_ = singleFieldBuilderV3.build();
                }
                smartContract.bytecode_ = this.bytecode_;
                smartContract.callValue_ = this.callValue_;
                smartContract.consumeUserResourcePercent_ = this.consumeUserResourcePercent_;
                smartContract.name_ = this.name_;
                smartContract.originEnergyLimit_ = this.originEnergyLimit_;
                smartContract.codeHash_ = this.codeHash_;
                smartContract.trxHash_ = this.trxHash_;
                smartContract.version_ = this.version_;
                onBuilt();
                return smartContract;
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
                if (message instanceof SmartContract) {
                    return mergeFrom((SmartContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(SmartContract smartContract) {
                if (smartContract == SmartContract.getDefaultInstance()) {
                    return this;
                }
                if (smartContract.getOriginAddress() != ByteString.EMPTY) {
                    setOriginAddress(smartContract.getOriginAddress());
                }
                if (smartContract.getContractAddress() != ByteString.EMPTY) {
                    setContractAddress(smartContract.getContractAddress());
                }
                if (smartContract.hasAbi()) {
                    mergeAbi(smartContract.getAbi());
                }
                if (smartContract.getBytecode() != ByteString.EMPTY) {
                    setBytecode(smartContract.getBytecode());
                }
                if (smartContract.getCallValue() != 0) {
                    setCallValue(smartContract.getCallValue());
                }
                if (smartContract.getConsumeUserResourcePercent() != 0) {
                    setConsumeUserResourcePercent(smartContract.getConsumeUserResourcePercent());
                }
                if (!smartContract.getName().isEmpty()) {
                    this.name_ = smartContract.name_;
                    onChanged();
                }
                if (smartContract.getOriginEnergyLimit() != 0) {
                    setOriginEnergyLimit(smartContract.getOriginEnergyLimit());
                }
                if (smartContract.getCodeHash() != ByteString.EMPTY) {
                    setCodeHash(smartContract.getCodeHash());
                }
                if (smartContract.getTrxHash() != ByteString.EMPTY) {
                    setTrxHash(smartContract.getTrxHash());
                }
                if (smartContract.getVersion() != 0) {
                    setVersion(smartContract.getVersion());
                }
                mergeUnknownFields(smartContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.SmartContractOuterClass.SmartContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.SmartContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$SmartContract$Builder");
            }

            public Builder setOriginAddress(ByteString byteString) {
                byteString.getClass();
                this.originAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOriginAddress() {
                this.originAddress_ = SmartContract.getDefaultInstance().getOriginAddress();
                onChanged();
                return this;
            }

            public Builder setContractAddress(ByteString byteString) {
                byteString.getClass();
                this.contractAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearContractAddress() {
                this.contractAddress_ = SmartContract.getDefaultInstance().getContractAddress();
                onChanged();
                return this;
            }

            @Override
            public ABI getAbi() {
                SingleFieldBuilderV3<ABI, ABI.Builder, ABIOrBuilder> singleFieldBuilderV3 = this.abiBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ABI abi = this.abi_;
                    return abi == null ? ABI.getDefaultInstance() : abi;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setAbi(ABI abi) {
                SingleFieldBuilderV3<ABI, ABI.Builder, ABIOrBuilder> singleFieldBuilderV3 = this.abiBuilder_;
                if (singleFieldBuilderV3 == null) {
                    abi.getClass();
                    this.abi_ = abi;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(abi);
                }
                return this;
            }

            public Builder setAbi(ABI.Builder builder) {
                SingleFieldBuilderV3<ABI, ABI.Builder, ABIOrBuilder> singleFieldBuilderV3 = this.abiBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.abi_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeAbi(ABI abi) {
                SingleFieldBuilderV3<ABI, ABI.Builder, ABIOrBuilder> singleFieldBuilderV3 = this.abiBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ABI abi2 = this.abi_;
                    if (abi2 != null) {
                        this.abi_ = ABI.newBuilder(abi2).mergeFrom(abi).buildPartial();
                    } else {
                        this.abi_ = abi;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(abi);
                }
                return this;
            }

            public Builder clearAbi() {
                if (this.abiBuilder_ == null) {
                    this.abi_ = null;
                    onChanged();
                } else {
                    this.abi_ = null;
                    this.abiBuilder_ = null;
                }
                return this;
            }

            public ABI.Builder getAbiBuilder() {
                onChanged();
                return getAbiFieldBuilder().getBuilder();
            }

            @Override
            public ABIOrBuilder getAbiOrBuilder() {
                SingleFieldBuilderV3<ABI, ABI.Builder, ABIOrBuilder> singleFieldBuilderV3 = this.abiBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ABI abi = this.abi_;
                return abi == null ? ABI.getDefaultInstance() : abi;
            }

            private SingleFieldBuilderV3<ABI, ABI.Builder, ABIOrBuilder> getAbiFieldBuilder() {
                if (this.abiBuilder_ == null) {
                    this.abiBuilder_ = new SingleFieldBuilderV3<>(getAbi(), getParentForChildren(), isClean());
                    this.abi_ = null;
                }
                return this.abiBuilder_;
            }

            public Builder setBytecode(ByteString byteString) {
                byteString.getClass();
                this.bytecode_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearBytecode() {
                this.bytecode_ = SmartContract.getDefaultInstance().getBytecode();
                onChanged();
                return this;
            }

            public Builder setCallValue(long j) {
                this.callValue_ = j;
                onChanged();
                return this;
            }

            public Builder clearCallValue() {
                this.callValue_ = 0L;
                onChanged();
                return this;
            }

            public Builder setConsumeUserResourcePercent(long j) {
                this.consumeUserResourcePercent_ = j;
                onChanged();
                return this;
            }

            public Builder clearConsumeUserResourcePercent() {
                this.consumeUserResourcePercent_ = 0L;
                onChanged();
                return this;
            }

            @Override
            public String getName() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setName(String str) {
                str.getClass();
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = SmartContract.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                byteString.getClass();
                SmartContract.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder setOriginEnergyLimit(long j) {
                this.originEnergyLimit_ = j;
                onChanged();
                return this;
            }

            public Builder clearOriginEnergyLimit() {
                this.originEnergyLimit_ = 0L;
                onChanged();
                return this;
            }

            public Builder setCodeHash(ByteString byteString) {
                byteString.getClass();
                this.codeHash_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearCodeHash() {
                this.codeHash_ = SmartContract.getDefaultInstance().getCodeHash();
                onChanged();
                return this;
            }

            public Builder setTrxHash(ByteString byteString) {
                byteString.getClass();
                this.trxHash_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearTrxHash() {
                this.trxHash_ = SmartContract.getDefaultInstance().getTrxHash();
                onChanged();
                return this;
            }

            public Builder setVersion(int i) {
                this.version_ = i;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.version_ = 0;
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

    public static final class ContractState extends GeneratedMessageV3 implements ContractStateOrBuilder {
        public static final int ENERGY_FACTOR_FIELD_NUMBER = 2;
        public static final int ENERGY_USAGE_FIELD_NUMBER = 1;
        public static final int UPDATE_CYCLE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private long energyFactor_;
        private long energyUsage_;
        private byte memoizedIsInitialized;
        private long updateCycle_;
        private static final ContractState DEFAULT_INSTANCE = new ContractState();
        private static final Parser<ContractState> PARSER = new AbstractParser<ContractState>() {
            @Override
            public ContractState parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ContractState(codedInputStream, extensionRegistryLite);
            }
        };

        public static ContractState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ContractState> parser() {
            return PARSER;
        }

        @Override
        public ContractState getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public long getEnergyFactor() {
            return this.energyFactor_;
        }

        @Override
        public long getEnergyUsage() {
            return this.energyUsage_;
        }

        @Override
        public Parser<ContractState> getParserForType() {
            return PARSER;
        }

        @Override
        public long getUpdateCycle() {
            return this.updateCycle_;
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

        private ContractState(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ContractState() {
            this.memoizedIsInitialized = (byte) -1;
            this.energyUsage_ = 0L;
            this.energyFactor_ = 0L;
            this.updateCycle_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ContractState(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.energyUsage_ = codedInputStream.readInt64();
                            } else if (readTag == 16) {
                                this.energyFactor_ = codedInputStream.readInt64();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.updateCycle_ = codedInputStream.readInt64();
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
            return SmartContractOuterClass.internal_static_protocol_ContractState_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SmartContractOuterClass.internal_static_protocol_ContractState_fieldAccessorTable.ensureFieldAccessorsInitialized(ContractState.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            long j = this.energyUsage_;
            if (j != 0) {
                codedOutputStream.writeInt64(1, j);
            }
            long j2 = this.energyFactor_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(2, j2);
            }
            long j3 = this.updateCycle_;
            if (j3 != 0) {
                codedOutputStream.writeInt64(3, j3);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            long j = this.energyUsage_;
            int computeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(1, j) : 0;
            long j2 = this.energyFactor_;
            if (j2 != 0) {
                computeInt64Size += CodedOutputStream.computeInt64Size(2, j2);
            }
            long j3 = this.updateCycle_;
            if (j3 != 0) {
                computeInt64Size += CodedOutputStream.computeInt64Size(3, j3);
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
            if (!(obj instanceof ContractState)) {
                return super.equals(obj);
            }
            ContractState contractState = (ContractState) obj;
            return getEnergyUsage() == contractState.getEnergyUsage() && getEnergyFactor() == contractState.getEnergyFactor() && getUpdateCycle() == contractState.getUpdateCycle() && this.unknownFields.equals(contractState.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getEnergyUsage())) * 37) + 2) * 53) + Internal.hashLong(getEnergyFactor())) * 37) + 3) * 53) + Internal.hashLong(getUpdateCycle())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ContractState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ContractState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ContractState parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ContractState parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ContractState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ContractState parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ContractState parseFrom(InputStream inputStream) throws IOException {
            return (ContractState) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ContractState parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ContractState) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ContractState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ContractState) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ContractState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ContractState) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ContractState parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ContractState) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ContractState parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ContractState) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ContractState contractState) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(contractState);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ContractStateOrBuilder {
            private long energyFactor_;
            private long energyUsage_;
            private long updateCycle_;

            @Override
            public long getEnergyFactor() {
                return this.energyFactor_;
            }

            @Override
            public long getEnergyUsage() {
                return this.energyUsage_;
            }

            @Override
            public long getUpdateCycle() {
                return this.updateCycle_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SmartContractOuterClass.internal_static_protocol_ContractState_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SmartContractOuterClass.internal_static_protocol_ContractState_fieldAccessorTable.ensureFieldAccessorsInitialized(ContractState.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ContractState.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.energyUsage_ = 0L;
                this.energyFactor_ = 0L;
                this.updateCycle_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return SmartContractOuterClass.internal_static_protocol_ContractState_descriptor;
            }

            @Override
            public ContractState getDefaultInstanceForType() {
                return ContractState.getDefaultInstance();
            }

            @Override
            public ContractState build() {
                ContractState buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ContractState buildPartial() {
                ContractState contractState = new ContractState(this);
                contractState.energyUsage_ = this.energyUsage_;
                contractState.energyFactor_ = this.energyFactor_;
                contractState.updateCycle_ = this.updateCycle_;
                onBuilt();
                return contractState;
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
                if (message instanceof ContractState) {
                    return mergeFrom((ContractState) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ContractState contractState) {
                if (contractState == ContractState.getDefaultInstance()) {
                    return this;
                }
                if (contractState.getEnergyUsage() != 0) {
                    setEnergyUsage(contractState.getEnergyUsage());
                }
                if (contractState.getEnergyFactor() != 0) {
                    setEnergyFactor(contractState.getEnergyFactor());
                }
                if (contractState.getUpdateCycle() != 0) {
                    setUpdateCycle(contractState.getUpdateCycle());
                }
                mergeUnknownFields(contractState.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.SmartContractOuterClass.ContractState.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.ContractState.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$ContractState$Builder");
            }

            public Builder setEnergyUsage(long j) {
                this.energyUsage_ = j;
                onChanged();
                return this;
            }

            public Builder clearEnergyUsage() {
                this.energyUsage_ = 0L;
                onChanged();
                return this;
            }

            public Builder setEnergyFactor(long j) {
                this.energyFactor_ = j;
                onChanged();
                return this;
            }

            public Builder clearEnergyFactor() {
                this.energyFactor_ = 0L;
                onChanged();
                return this;
            }

            public Builder setUpdateCycle(long j) {
                this.updateCycle_ = j;
                onChanged();
                return this;
            }

            public Builder clearUpdateCycle() {
                this.updateCycle_ = 0L;
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

    public static final class CreateSmartContract extends GeneratedMessageV3 implements CreateSmartContractOrBuilder {
        public static final int CALL_TOKEN_VALUE_FIELD_NUMBER = 3;
        public static final int NEW_CONTRACT_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int TOKEN_ID_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private long callTokenValue_;
        private byte memoizedIsInitialized;
        private SmartContract newContract_;
        private ByteString ownerAddress_;
        private long tokenId_;
        private static final CreateSmartContract DEFAULT_INSTANCE = new CreateSmartContract();
        private static final Parser<CreateSmartContract> PARSER = new AbstractParser<CreateSmartContract>() {
            @Override
            public CreateSmartContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CreateSmartContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static CreateSmartContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CreateSmartContract> parser() {
            return PARSER;
        }

        @Override
        public long getCallTokenValue() {
            return this.callTokenValue_;
        }

        @Override
        public CreateSmartContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<CreateSmartContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getTokenId() {
            return this.tokenId_;
        }

        @Override
        public boolean hasNewContract() {
            return this.newContract_ != null;
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

        private CreateSmartContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CreateSmartContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.callTokenValue_ = 0L;
            this.tokenId_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CreateSmartContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    SmartContract smartContract = this.newContract_;
                                    SmartContract.Builder builder = smartContract != null ? smartContract.toBuilder() : null;
                                    SmartContract smartContract2 = (SmartContract) codedInputStream.readMessage(SmartContract.parser(), extensionRegistryLite);
                                    this.newContract_ = smartContract2;
                                    if (builder != null) {
                                        builder.mergeFrom(smartContract2);
                                        this.newContract_ = builder.buildPartial();
                                    }
                                } else if (readTag == 24) {
                                    this.callTokenValue_ = codedInputStream.readInt64();
                                } else if (readTag != 32) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.tokenId_ = codedInputStream.readInt64();
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
            return SmartContractOuterClass.internal_static_protocol_CreateSmartContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SmartContractOuterClass.internal_static_protocol_CreateSmartContract_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateSmartContract.class, Builder.class);
        }

        @Override
        public SmartContract getNewContract() {
            SmartContract smartContract = this.newContract_;
            return smartContract == null ? SmartContract.getDefaultInstance() : smartContract;
        }

        @Override
        public SmartContractOrBuilder getNewContractOrBuilder() {
            return getNewContract();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (this.newContract_ != null) {
                codedOutputStream.writeMessage(2, getNewContract());
            }
            long j = this.callTokenValue_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            long j2 = this.tokenId_;
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
            if (this.newContract_ != null) {
                computeBytesSize += CodedOutputStream.computeMessageSize(2, getNewContract());
            }
            long j = this.callTokenValue_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(3, j);
            }
            long j2 = this.tokenId_;
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
            if (!(obj instanceof CreateSmartContract)) {
                return super.equals(obj);
            }
            CreateSmartContract createSmartContract = (CreateSmartContract) obj;
            boolean z = getOwnerAddress().equals(createSmartContract.getOwnerAddress()) && hasNewContract() == createSmartContract.hasNewContract();
            if (!hasNewContract() ? z : !(!z || !getNewContract().equals(createSmartContract.getNewContract()))) {
                if (getCallTokenValue() == createSmartContract.getCallTokenValue() && getTokenId() == createSmartContract.getTokenId() && this.unknownFields.equals(createSmartContract.unknownFields)) {
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
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode();
            if (hasNewContract()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getNewContract().hashCode();
            }
            int hashLong = (((((((((hashCode * 37) + 3) * 53) + Internal.hashLong(getCallTokenValue())) * 37) + 4) * 53) + Internal.hashLong(getTokenId())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashLong;
            return hashLong;
        }

        public static CreateSmartContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CreateSmartContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CreateSmartContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CreateSmartContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CreateSmartContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CreateSmartContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CreateSmartContract parseFrom(InputStream inputStream) throws IOException {
            return (CreateSmartContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CreateSmartContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateSmartContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CreateSmartContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CreateSmartContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CreateSmartContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateSmartContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CreateSmartContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CreateSmartContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CreateSmartContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateSmartContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CreateSmartContract createSmartContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(createSmartContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CreateSmartContractOrBuilder {
            private long callTokenValue_;
            private SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> newContractBuilder_;
            private SmartContract newContract_;
            private ByteString ownerAddress_;
            private long tokenId_;

            @Override
            public long getCallTokenValue() {
                return this.callTokenValue_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getTokenId() {
                return this.tokenId_;
            }

            @Override
            public boolean hasNewContract() {
                return (this.newContractBuilder_ == null && this.newContract_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SmartContractOuterClass.internal_static_protocol_CreateSmartContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SmartContractOuterClass.internal_static_protocol_CreateSmartContract_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateSmartContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.newContract_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.newContract_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CreateSmartContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                if (this.newContractBuilder_ == null) {
                    this.newContract_ = null;
                } else {
                    this.newContract_ = null;
                    this.newContractBuilder_ = null;
                }
                this.callTokenValue_ = 0L;
                this.tokenId_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return SmartContractOuterClass.internal_static_protocol_CreateSmartContract_descriptor;
            }

            @Override
            public CreateSmartContract getDefaultInstanceForType() {
                return CreateSmartContract.getDefaultInstance();
            }

            @Override
            public CreateSmartContract build() {
                CreateSmartContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public CreateSmartContract buildPartial() {
                CreateSmartContract createSmartContract = new CreateSmartContract(this);
                createSmartContract.ownerAddress_ = this.ownerAddress_;
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.newContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    createSmartContract.newContract_ = this.newContract_;
                } else {
                    createSmartContract.newContract_ = singleFieldBuilderV3.build();
                }
                createSmartContract.callTokenValue_ = this.callTokenValue_;
                createSmartContract.tokenId_ = this.tokenId_;
                onBuilt();
                return createSmartContract;
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
                if (message instanceof CreateSmartContract) {
                    return mergeFrom((CreateSmartContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CreateSmartContract createSmartContract) {
                if (createSmartContract == CreateSmartContract.getDefaultInstance()) {
                    return this;
                }
                if (createSmartContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(createSmartContract.getOwnerAddress());
                }
                if (createSmartContract.hasNewContract()) {
                    mergeNewContract(createSmartContract.getNewContract());
                }
                if (createSmartContract.getCallTokenValue() != 0) {
                    setCallTokenValue(createSmartContract.getCallTokenValue());
                }
                if (createSmartContract.getTokenId() != 0) {
                    setTokenId(createSmartContract.getTokenId());
                }
                mergeUnknownFields(createSmartContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.SmartContractOuterClass.CreateSmartContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.CreateSmartContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$CreateSmartContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = CreateSmartContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            @Override
            public SmartContract getNewContract() {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.newContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    SmartContract smartContract = this.newContract_;
                    return smartContract == null ? SmartContract.getDefaultInstance() : smartContract;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setNewContract(SmartContract smartContract) {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.newContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    smartContract.getClass();
                    this.newContract_ = smartContract;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(smartContract);
                }
                return this;
            }

            public Builder setNewContract(SmartContract.Builder builder) {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.newContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.newContract_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeNewContract(SmartContract smartContract) {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.newContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    SmartContract smartContract2 = this.newContract_;
                    if (smartContract2 != null) {
                        this.newContract_ = SmartContract.newBuilder(smartContract2).mergeFrom(smartContract).buildPartial();
                    } else {
                        this.newContract_ = smartContract;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(smartContract);
                }
                return this;
            }

            public Builder clearNewContract() {
                if (this.newContractBuilder_ == null) {
                    this.newContract_ = null;
                    onChanged();
                } else {
                    this.newContract_ = null;
                    this.newContractBuilder_ = null;
                }
                return this;
            }

            public SmartContract.Builder getNewContractBuilder() {
                onChanged();
                return getNewContractFieldBuilder().getBuilder();
            }

            @Override
            public SmartContractOrBuilder getNewContractOrBuilder() {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.newContractBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                SmartContract smartContract = this.newContract_;
                return smartContract == null ? SmartContract.getDefaultInstance() : smartContract;
            }

            private SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> getNewContractFieldBuilder() {
                if (this.newContractBuilder_ == null) {
                    this.newContractBuilder_ = new SingleFieldBuilderV3<>(getNewContract(), getParentForChildren(), isClean());
                    this.newContract_ = null;
                }
                return this.newContractBuilder_;
            }

            public Builder setCallTokenValue(long j) {
                this.callTokenValue_ = j;
                onChanged();
                return this;
            }

            public Builder clearCallTokenValue() {
                this.callTokenValue_ = 0L;
                onChanged();
                return this;
            }

            public Builder setTokenId(long j) {
                this.tokenId_ = j;
                onChanged();
                return this;
            }

            public Builder clearTokenId() {
                this.tokenId_ = 0L;
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

    public static final class TriggerSmartContract extends GeneratedMessageV3 implements TriggerSmartContractOrBuilder {
        public static final int CALL_TOKEN_VALUE_FIELD_NUMBER = 5;
        public static final int CALL_VALUE_FIELD_NUMBER = 3;
        public static final int CONTRACT_ADDRESS_FIELD_NUMBER = 2;
        public static final int DATA_FIELD_NUMBER = 4;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int TOKEN_ID_FIELD_NUMBER = 6;
        private static final long serialVersionUID = 0;
        private long callTokenValue_;
        private long callValue_;
        private ByteString contractAddress_;
        private ByteString data_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private long tokenId_;
        private static final TriggerSmartContract DEFAULT_INSTANCE = new TriggerSmartContract();
        private static final Parser<TriggerSmartContract> PARSER = new AbstractParser<TriggerSmartContract>() {
            @Override
            public TriggerSmartContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TriggerSmartContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static TriggerSmartContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TriggerSmartContract> parser() {
            return PARSER;
        }

        @Override
        public long getCallTokenValue() {
            return this.callTokenValue_;
        }

        @Override
        public long getCallValue() {
            return this.callValue_;
        }

        @Override
        public ByteString getContractAddress() {
            return this.contractAddress_;
        }

        @Override
        public ByteString getData() {
            return this.data_;
        }

        @Override
        public TriggerSmartContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<TriggerSmartContract> getParserForType() {
            return PARSER;
        }

        @Override
        public long getTokenId() {
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

        private TriggerSmartContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private TriggerSmartContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.contractAddress_ = ByteString.EMPTY;
            this.callValue_ = 0L;
            this.data_ = ByteString.EMPTY;
            this.callTokenValue_ = 0L;
            this.tokenId_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private TriggerSmartContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.contractAddress_ = codedInputStream.readBytes();
                            } else if (readTag == 24) {
                                this.callValue_ = codedInputStream.readInt64();
                            } else if (readTag == 34) {
                                this.data_ = codedInputStream.readBytes();
                            } else if (readTag == 40) {
                                this.callTokenValue_ = codedInputStream.readInt64();
                            } else if (readTag != 48) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.tokenId_ = codedInputStream.readInt64();
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
            return SmartContractOuterClass.internal_static_protocol_TriggerSmartContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SmartContractOuterClass.internal_static_protocol_TriggerSmartContract_fieldAccessorTable.ensureFieldAccessorsInitialized(TriggerSmartContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.contractAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.contractAddress_);
            }
            long j = this.callValue_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            if (!this.data_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.data_);
            }
            long j2 = this.callTokenValue_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(5, j2);
            }
            long j3 = this.tokenId_;
            if (j3 != 0) {
                codedOutputStream.writeInt64(6, j3);
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
            if (!this.contractAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.contractAddress_);
            }
            long j = this.callValue_;
            if (j != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(3, j);
            }
            if (!this.data_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(4, this.data_);
            }
            long j2 = this.callTokenValue_;
            if (j2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(5, j2);
            }
            long j3 = this.tokenId_;
            if (j3 != 0) {
                computeBytesSize += CodedOutputStream.computeInt64Size(6, j3);
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
            if (!(obj instanceof TriggerSmartContract)) {
                return super.equals(obj);
            }
            TriggerSmartContract triggerSmartContract = (TriggerSmartContract) obj;
            return getOwnerAddress().equals(triggerSmartContract.getOwnerAddress()) && getContractAddress().equals(triggerSmartContract.getContractAddress()) && getCallValue() == triggerSmartContract.getCallValue() && getData().equals(triggerSmartContract.getData()) && getCallTokenValue() == triggerSmartContract.getCallTokenValue() && getTokenId() == triggerSmartContract.getTokenId() && this.unknownFields.equals(triggerSmartContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getContractAddress().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getCallValue())) * 37) + 4) * 53) + getData().hashCode()) * 37) + 5) * 53) + Internal.hashLong(getCallTokenValue())) * 37) + 6) * 53) + Internal.hashLong(getTokenId())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static TriggerSmartContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static TriggerSmartContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static TriggerSmartContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static TriggerSmartContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TriggerSmartContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static TriggerSmartContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TriggerSmartContract parseFrom(InputStream inputStream) throws IOException {
            return (TriggerSmartContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static TriggerSmartContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TriggerSmartContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TriggerSmartContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TriggerSmartContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static TriggerSmartContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TriggerSmartContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TriggerSmartContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TriggerSmartContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static TriggerSmartContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TriggerSmartContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TriggerSmartContract triggerSmartContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(triggerSmartContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TriggerSmartContractOrBuilder {
            private long callTokenValue_;
            private long callValue_;
            private ByteString contractAddress_;
            private ByteString data_;
            private ByteString ownerAddress_;
            private long tokenId_;

            @Override
            public long getCallTokenValue() {
                return this.callTokenValue_;
            }

            @Override
            public long getCallValue() {
                return this.callValue_;
            }

            @Override
            public ByteString getContractAddress() {
                return this.contractAddress_;
            }

            @Override
            public ByteString getData() {
                return this.data_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public long getTokenId() {
                return this.tokenId_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SmartContractOuterClass.internal_static_protocol_TriggerSmartContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SmartContractOuterClass.internal_static_protocol_TriggerSmartContract_fieldAccessorTable.ensureFieldAccessorsInitialized(TriggerSmartContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = TriggerSmartContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                this.callValue_ = 0L;
                this.data_ = ByteString.EMPTY;
                this.callTokenValue_ = 0L;
                this.tokenId_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return SmartContractOuterClass.internal_static_protocol_TriggerSmartContract_descriptor;
            }

            @Override
            public TriggerSmartContract getDefaultInstanceForType() {
                return TriggerSmartContract.getDefaultInstance();
            }

            @Override
            public TriggerSmartContract build() {
                TriggerSmartContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public TriggerSmartContract buildPartial() {
                TriggerSmartContract triggerSmartContract = new TriggerSmartContract(this);
                triggerSmartContract.ownerAddress_ = this.ownerAddress_;
                triggerSmartContract.contractAddress_ = this.contractAddress_;
                triggerSmartContract.callValue_ = this.callValue_;
                triggerSmartContract.data_ = this.data_;
                triggerSmartContract.callTokenValue_ = this.callTokenValue_;
                triggerSmartContract.tokenId_ = this.tokenId_;
                onBuilt();
                return triggerSmartContract;
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
                if (message instanceof TriggerSmartContract) {
                    return mergeFrom((TriggerSmartContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TriggerSmartContract triggerSmartContract) {
                if (triggerSmartContract == TriggerSmartContract.getDefaultInstance()) {
                    return this;
                }
                if (triggerSmartContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(triggerSmartContract.getOwnerAddress());
                }
                if (triggerSmartContract.getContractAddress() != ByteString.EMPTY) {
                    setContractAddress(triggerSmartContract.getContractAddress());
                }
                if (triggerSmartContract.getCallValue() != 0) {
                    setCallValue(triggerSmartContract.getCallValue());
                }
                if (triggerSmartContract.getData() != ByteString.EMPTY) {
                    setData(triggerSmartContract.getData());
                }
                if (triggerSmartContract.getCallTokenValue() != 0) {
                    setCallTokenValue(triggerSmartContract.getCallTokenValue());
                }
                if (triggerSmartContract.getTokenId() != 0) {
                    setTokenId(triggerSmartContract.getTokenId());
                }
                mergeUnknownFields(triggerSmartContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.SmartContractOuterClass.TriggerSmartContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.TriggerSmartContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$TriggerSmartContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = TriggerSmartContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setContractAddress(ByteString byteString) {
                byteString.getClass();
                this.contractAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearContractAddress() {
                this.contractAddress_ = TriggerSmartContract.getDefaultInstance().getContractAddress();
                onChanged();
                return this;
            }

            public Builder setCallValue(long j) {
                this.callValue_ = j;
                onChanged();
                return this;
            }

            public Builder clearCallValue() {
                this.callValue_ = 0L;
                onChanged();
                return this;
            }

            public Builder setData(ByteString byteString) {
                byteString.getClass();
                this.data_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearData() {
                this.data_ = TriggerSmartContract.getDefaultInstance().getData();
                onChanged();
                return this;
            }

            public Builder setCallTokenValue(long j) {
                this.callTokenValue_ = j;
                onChanged();
                return this;
            }

            public Builder clearCallTokenValue() {
                this.callTokenValue_ = 0L;
                onChanged();
                return this;
            }

            public Builder setTokenId(long j) {
                this.tokenId_ = j;
                onChanged();
                return this;
            }

            public Builder clearTokenId() {
                this.tokenId_ = 0L;
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

    public static final class ClearABIContract extends GeneratedMessageV3 implements ClearABIContractOrBuilder {
        public static final int CONTRACT_ADDRESS_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private ByteString contractAddress_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final ClearABIContract DEFAULT_INSTANCE = new ClearABIContract();
        private static final Parser<ClearABIContract> PARSER = new AbstractParser<ClearABIContract>() {
            @Override
            public ClearABIContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ClearABIContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static ClearABIContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ClearABIContract> parser() {
            return PARSER;
        }

        @Override
        public ByteString getContractAddress() {
            return this.contractAddress_;
        }

        @Override
        public ClearABIContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<ClearABIContract> getParserForType() {
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

        private ClearABIContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ClearABIContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.contractAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ClearABIContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.contractAddress_ = codedInputStream.readBytes();
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
            return SmartContractOuterClass.internal_static_protocol_ClearABIContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SmartContractOuterClass.internal_static_protocol_ClearABIContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ClearABIContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.contractAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.contractAddress_);
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
            if (!this.contractAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.contractAddress_);
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
            if (!(obj instanceof ClearABIContract)) {
                return super.equals(obj);
            }
            ClearABIContract clearABIContract = (ClearABIContract) obj;
            return getOwnerAddress().equals(clearABIContract.getOwnerAddress()) && getContractAddress().equals(clearABIContract.getContractAddress()) && this.unknownFields.equals(clearABIContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getContractAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ClearABIContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ClearABIContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ClearABIContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ClearABIContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ClearABIContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ClearABIContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ClearABIContract parseFrom(InputStream inputStream) throws IOException {
            return (ClearABIContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ClearABIContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ClearABIContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ClearABIContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ClearABIContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ClearABIContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ClearABIContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ClearABIContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ClearABIContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ClearABIContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ClearABIContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ClearABIContract clearABIContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(clearABIContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ClearABIContractOrBuilder {
            private ByteString contractAddress_;
            private ByteString ownerAddress_;

            @Override
            public ByteString getContractAddress() {
                return this.contractAddress_;
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
                return SmartContractOuterClass.internal_static_protocol_ClearABIContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SmartContractOuterClass.internal_static_protocol_ClearABIContract_fieldAccessorTable.ensureFieldAccessorsInitialized(ClearABIContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ClearABIContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return SmartContractOuterClass.internal_static_protocol_ClearABIContract_descriptor;
            }

            @Override
            public ClearABIContract getDefaultInstanceForType() {
                return ClearABIContract.getDefaultInstance();
            }

            @Override
            public ClearABIContract build() {
                ClearABIContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public ClearABIContract buildPartial() {
                ClearABIContract clearABIContract = new ClearABIContract(this);
                clearABIContract.ownerAddress_ = this.ownerAddress_;
                clearABIContract.contractAddress_ = this.contractAddress_;
                onBuilt();
                return clearABIContract;
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
                if (message instanceof ClearABIContract) {
                    return mergeFrom((ClearABIContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ClearABIContract clearABIContract) {
                if (clearABIContract == ClearABIContract.getDefaultInstance()) {
                    return this;
                }
                if (clearABIContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(clearABIContract.getOwnerAddress());
                }
                if (clearABIContract.getContractAddress() != ByteString.EMPTY) {
                    setContractAddress(clearABIContract.getContractAddress());
                }
                mergeUnknownFields(clearABIContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.SmartContractOuterClass.ClearABIContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.ClearABIContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$ClearABIContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = ClearABIContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setContractAddress(ByteString byteString) {
                byteString.getClass();
                this.contractAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearContractAddress() {
                this.contractAddress_ = ClearABIContract.getDefaultInstance().getContractAddress();
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

    public static final class UpdateSettingContract extends GeneratedMessageV3 implements UpdateSettingContractOrBuilder {
        public static final int CONSUME_USER_RESOURCE_PERCENT_FIELD_NUMBER = 3;
        public static final int CONTRACT_ADDRESS_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private long consumeUserResourcePercent_;
        private ByteString contractAddress_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final UpdateSettingContract DEFAULT_INSTANCE = new UpdateSettingContract();
        private static final Parser<UpdateSettingContract> PARSER = new AbstractParser<UpdateSettingContract>() {
            @Override
            public UpdateSettingContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UpdateSettingContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static UpdateSettingContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UpdateSettingContract> parser() {
            return PARSER;
        }

        @Override
        public long getConsumeUserResourcePercent() {
            return this.consumeUserResourcePercent_;
        }

        @Override
        public ByteString getContractAddress() {
            return this.contractAddress_;
        }

        @Override
        public UpdateSettingContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<UpdateSettingContract> getParserForType() {
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

        private UpdateSettingContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UpdateSettingContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.contractAddress_ = ByteString.EMPTY;
            this.consumeUserResourcePercent_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateSettingContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.contractAddress_ = codedInputStream.readBytes();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.consumeUserResourcePercent_ = codedInputStream.readInt64();
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
            return SmartContractOuterClass.internal_static_protocol_UpdateSettingContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SmartContractOuterClass.internal_static_protocol_UpdateSettingContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateSettingContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.contractAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.contractAddress_);
            }
            long j = this.consumeUserResourcePercent_;
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
            if (!this.contractAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.contractAddress_);
            }
            long j = this.consumeUserResourcePercent_;
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
            if (!(obj instanceof UpdateSettingContract)) {
                return super.equals(obj);
            }
            UpdateSettingContract updateSettingContract = (UpdateSettingContract) obj;
            return getOwnerAddress().equals(updateSettingContract.getOwnerAddress()) && getContractAddress().equals(updateSettingContract.getContractAddress()) && getConsumeUserResourcePercent() == updateSettingContract.getConsumeUserResourcePercent() && this.unknownFields.equals(updateSettingContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getContractAddress().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getConsumeUserResourcePercent())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static UpdateSettingContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UpdateSettingContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UpdateSettingContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UpdateSettingContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UpdateSettingContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UpdateSettingContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UpdateSettingContract parseFrom(InputStream inputStream) throws IOException {
            return (UpdateSettingContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UpdateSettingContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateSettingContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateSettingContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateSettingContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UpdateSettingContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateSettingContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateSettingContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateSettingContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UpdateSettingContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateSettingContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UpdateSettingContract updateSettingContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateSettingContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateSettingContractOrBuilder {
            private long consumeUserResourcePercent_;
            private ByteString contractAddress_;
            private ByteString ownerAddress_;

            @Override
            public long getConsumeUserResourcePercent() {
                return this.consumeUserResourcePercent_;
            }

            @Override
            public ByteString getContractAddress() {
                return this.contractAddress_;
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
                return SmartContractOuterClass.internal_static_protocol_UpdateSettingContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SmartContractOuterClass.internal_static_protocol_UpdateSettingContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateSettingContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateSettingContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                this.consumeUserResourcePercent_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return SmartContractOuterClass.internal_static_protocol_UpdateSettingContract_descriptor;
            }

            @Override
            public UpdateSettingContract getDefaultInstanceForType() {
                return UpdateSettingContract.getDefaultInstance();
            }

            @Override
            public UpdateSettingContract build() {
                UpdateSettingContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public UpdateSettingContract buildPartial() {
                UpdateSettingContract updateSettingContract = new UpdateSettingContract(this);
                updateSettingContract.ownerAddress_ = this.ownerAddress_;
                updateSettingContract.contractAddress_ = this.contractAddress_;
                updateSettingContract.consumeUserResourcePercent_ = this.consumeUserResourcePercent_;
                onBuilt();
                return updateSettingContract;
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
                if (message instanceof UpdateSettingContract) {
                    return mergeFrom((UpdateSettingContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UpdateSettingContract updateSettingContract) {
                if (updateSettingContract == UpdateSettingContract.getDefaultInstance()) {
                    return this;
                }
                if (updateSettingContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(updateSettingContract.getOwnerAddress());
                }
                if (updateSettingContract.getContractAddress() != ByteString.EMPTY) {
                    setContractAddress(updateSettingContract.getContractAddress());
                }
                if (updateSettingContract.getConsumeUserResourcePercent() != 0) {
                    setConsumeUserResourcePercent(updateSettingContract.getConsumeUserResourcePercent());
                }
                mergeUnknownFields(updateSettingContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.SmartContractOuterClass.UpdateSettingContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.UpdateSettingContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$UpdateSettingContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = UpdateSettingContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setContractAddress(ByteString byteString) {
                byteString.getClass();
                this.contractAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearContractAddress() {
                this.contractAddress_ = UpdateSettingContract.getDefaultInstance().getContractAddress();
                onChanged();
                return this;
            }

            public Builder setConsumeUserResourcePercent(long j) {
                this.consumeUserResourcePercent_ = j;
                onChanged();
                return this;
            }

            public Builder clearConsumeUserResourcePercent() {
                this.consumeUserResourcePercent_ = 0L;
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

    public static final class UpdateEnergyLimitContract extends GeneratedMessageV3 implements UpdateEnergyLimitContractOrBuilder {
        public static final int CONTRACT_ADDRESS_FIELD_NUMBER = 2;
        public static final int ORIGIN_ENERGY_LIMIT_FIELD_NUMBER = 3;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private ByteString contractAddress_;
        private byte memoizedIsInitialized;
        private long originEnergyLimit_;
        private ByteString ownerAddress_;
        private static final UpdateEnergyLimitContract DEFAULT_INSTANCE = new UpdateEnergyLimitContract();
        private static final Parser<UpdateEnergyLimitContract> PARSER = new AbstractParser<UpdateEnergyLimitContract>() {
            @Override
            public UpdateEnergyLimitContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UpdateEnergyLimitContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static UpdateEnergyLimitContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UpdateEnergyLimitContract> parser() {
            return PARSER;
        }

        @Override
        public ByteString getContractAddress() {
            return this.contractAddress_;
        }

        @Override
        public UpdateEnergyLimitContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public long getOriginEnergyLimit() {
            return this.originEnergyLimit_;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<UpdateEnergyLimitContract> getParserForType() {
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

        private UpdateEnergyLimitContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UpdateEnergyLimitContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.contractAddress_ = ByteString.EMPTY;
            this.originEnergyLimit_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateEnergyLimitContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.contractAddress_ = codedInputStream.readBytes();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.originEnergyLimit_ = codedInputStream.readInt64();
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
            return SmartContractOuterClass.internal_static_protocol_UpdateEnergyLimitContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SmartContractOuterClass.internal_static_protocol_UpdateEnergyLimitContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateEnergyLimitContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.contractAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.contractAddress_);
            }
            long j = this.originEnergyLimit_;
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
            if (!this.contractAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.contractAddress_);
            }
            long j = this.originEnergyLimit_;
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
            if (!(obj instanceof UpdateEnergyLimitContract)) {
                return super.equals(obj);
            }
            UpdateEnergyLimitContract updateEnergyLimitContract = (UpdateEnergyLimitContract) obj;
            return getOwnerAddress().equals(updateEnergyLimitContract.getOwnerAddress()) && getContractAddress().equals(updateEnergyLimitContract.getContractAddress()) && getOriginEnergyLimit() == updateEnergyLimitContract.getOriginEnergyLimit() && this.unknownFields.equals(updateEnergyLimitContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getContractAddress().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getOriginEnergyLimit())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static UpdateEnergyLimitContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UpdateEnergyLimitContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UpdateEnergyLimitContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UpdateEnergyLimitContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UpdateEnergyLimitContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UpdateEnergyLimitContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UpdateEnergyLimitContract parseFrom(InputStream inputStream) throws IOException {
            return (UpdateEnergyLimitContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UpdateEnergyLimitContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateEnergyLimitContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateEnergyLimitContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateEnergyLimitContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UpdateEnergyLimitContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateEnergyLimitContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateEnergyLimitContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateEnergyLimitContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UpdateEnergyLimitContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateEnergyLimitContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UpdateEnergyLimitContract updateEnergyLimitContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateEnergyLimitContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateEnergyLimitContractOrBuilder {
            private ByteString contractAddress_;
            private long originEnergyLimit_;
            private ByteString ownerAddress_;

            @Override
            public ByteString getContractAddress() {
                return this.contractAddress_;
            }

            @Override
            public long getOriginEnergyLimit() {
                return this.originEnergyLimit_;
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
                return SmartContractOuterClass.internal_static_protocol_UpdateEnergyLimitContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SmartContractOuterClass.internal_static_protocol_UpdateEnergyLimitContract_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateEnergyLimitContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateEnergyLimitContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.contractAddress_ = ByteString.EMPTY;
                this.originEnergyLimit_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return SmartContractOuterClass.internal_static_protocol_UpdateEnergyLimitContract_descriptor;
            }

            @Override
            public UpdateEnergyLimitContract getDefaultInstanceForType() {
                return UpdateEnergyLimitContract.getDefaultInstance();
            }

            @Override
            public UpdateEnergyLimitContract build() {
                UpdateEnergyLimitContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public UpdateEnergyLimitContract buildPartial() {
                UpdateEnergyLimitContract updateEnergyLimitContract = new UpdateEnergyLimitContract(this);
                updateEnergyLimitContract.ownerAddress_ = this.ownerAddress_;
                updateEnergyLimitContract.contractAddress_ = this.contractAddress_;
                updateEnergyLimitContract.originEnergyLimit_ = this.originEnergyLimit_;
                onBuilt();
                return updateEnergyLimitContract;
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
                if (message instanceof UpdateEnergyLimitContract) {
                    return mergeFrom((UpdateEnergyLimitContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UpdateEnergyLimitContract updateEnergyLimitContract) {
                if (updateEnergyLimitContract == UpdateEnergyLimitContract.getDefaultInstance()) {
                    return this;
                }
                if (updateEnergyLimitContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(updateEnergyLimitContract.getOwnerAddress());
                }
                if (updateEnergyLimitContract.getContractAddress() != ByteString.EMPTY) {
                    setContractAddress(updateEnergyLimitContract.getContractAddress());
                }
                if (updateEnergyLimitContract.getOriginEnergyLimit() != 0) {
                    setOriginEnergyLimit(updateEnergyLimitContract.getOriginEnergyLimit());
                }
                mergeUnknownFields(updateEnergyLimitContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.SmartContractOuterClass.UpdateEnergyLimitContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.UpdateEnergyLimitContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$UpdateEnergyLimitContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = UpdateEnergyLimitContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setContractAddress(ByteString byteString) {
                byteString.getClass();
                this.contractAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearContractAddress() {
                this.contractAddress_ = UpdateEnergyLimitContract.getDefaultInstance().getContractAddress();
                onChanged();
                return this;
            }

            public Builder setOriginEnergyLimit(long j) {
                this.originEnergyLimit_ = j;
                onChanged();
                return this;
            }

            public Builder clearOriginEnergyLimit() {
                this.originEnergyLimit_ = 0L;
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

    public static final class SmartContractDataWrapper extends GeneratedMessageV3 implements SmartContractDataWrapperOrBuilder {
        public static final int CONTRACT_STATE_FIELD_NUMBER = 3;
        private static final SmartContractDataWrapper DEFAULT_INSTANCE = new SmartContractDataWrapper();
        private static final Parser<SmartContractDataWrapper> PARSER = new AbstractParser<SmartContractDataWrapper>() {
            @Override
            public SmartContractDataWrapper parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new SmartContractDataWrapper(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int RUNTIMECODE_FIELD_NUMBER = 2;
        public static final int SMART_CONTRACT_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private ContractState contractState_;
        private byte memoizedIsInitialized;
        private ByteString runtimecode_;
        private SmartContract smartContract_;

        public static SmartContractDataWrapper getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SmartContractDataWrapper> parser() {
            return PARSER;
        }

        @Override
        public SmartContractDataWrapper getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<SmartContractDataWrapper> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getRuntimecode() {
            return this.runtimecode_;
        }

        @Override
        public boolean hasContractState() {
            return this.contractState_ != null;
        }

        @Override
        public boolean hasSmartContract() {
            return this.smartContract_ != null;
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

        private SmartContractDataWrapper(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private SmartContractDataWrapper() {
            this.memoizedIsInitialized = (byte) -1;
            this.runtimecode_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private SmartContractDataWrapper(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    SmartContract smartContract = this.smartContract_;
                                    SmartContract.Builder builder = smartContract != null ? smartContract.toBuilder() : null;
                                    SmartContract smartContract2 = (SmartContract) codedInputStream.readMessage(SmartContract.parser(), extensionRegistryLite);
                                    this.smartContract_ = smartContract2;
                                    if (builder != null) {
                                        builder.mergeFrom(smartContract2);
                                        this.smartContract_ = builder.buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    this.runtimecode_ = codedInputStream.readBytes();
                                } else if (readTag != 26) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    ContractState contractState = this.contractState_;
                                    ContractState.Builder builder2 = contractState != null ? contractState.toBuilder() : null;
                                    ContractState contractState2 = (ContractState) codedInputStream.readMessage(ContractState.parser(), extensionRegistryLite);
                                    this.contractState_ = contractState2;
                                    if (builder2 != null) {
                                        builder2.mergeFrom(contractState2);
                                        this.contractState_ = builder2.buildPartial();
                                    }
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
            return SmartContractOuterClass.internal_static_protocol_SmartContractDataWrapper_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SmartContractOuterClass.internal_static_protocol_SmartContractDataWrapper_fieldAccessorTable.ensureFieldAccessorsInitialized(SmartContractDataWrapper.class, Builder.class);
        }

        @Override
        public SmartContract getSmartContract() {
            SmartContract smartContract = this.smartContract_;
            return smartContract == null ? SmartContract.getDefaultInstance() : smartContract;
        }

        @Override
        public SmartContractOrBuilder getSmartContractOrBuilder() {
            return getSmartContract();
        }

        @Override
        public ContractState getContractState() {
            ContractState contractState = this.contractState_;
            return contractState == null ? ContractState.getDefaultInstance() : contractState;
        }

        @Override
        public ContractStateOrBuilder getContractStateOrBuilder() {
            return getContractState();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.smartContract_ != null) {
                codedOutputStream.writeMessage(1, getSmartContract());
            }
            if (!this.runtimecode_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.runtimecode_);
            }
            if (this.contractState_ != null) {
                codedOutputStream.writeMessage(3, getContractState());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.smartContract_ != null ? CodedOutputStream.computeMessageSize(1, getSmartContract()) : 0;
            if (!this.runtimecode_.isEmpty()) {
                computeMessageSize += CodedOutputStream.computeBytesSize(2, this.runtimecode_);
            }
            if (this.contractState_ != null) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, getContractState());
            }
            int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(java.lang.Object r5) {
            


return true;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.SmartContractDataWrapper.equals(java.lang.Object):boolean");
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasSmartContract()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getSmartContract().hashCode();
            }
            int hashCode2 = (((hashCode * 37) + 2) * 53) + getRuntimecode().hashCode();
            if (hasContractState()) {
                hashCode2 = (((hashCode2 * 37) + 3) * 53) + getContractState().hashCode();
            }
            int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }

        public static SmartContractDataWrapper parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static SmartContractDataWrapper parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static SmartContractDataWrapper parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static SmartContractDataWrapper parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static SmartContractDataWrapper parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static SmartContractDataWrapper parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static SmartContractDataWrapper parseFrom(InputStream inputStream) throws IOException {
            return (SmartContractDataWrapper) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static SmartContractDataWrapper parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmartContractDataWrapper) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SmartContractDataWrapper parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SmartContractDataWrapper) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static SmartContractDataWrapper parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmartContractDataWrapper) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SmartContractDataWrapper parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SmartContractDataWrapper) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static SmartContractDataWrapper parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SmartContractDataWrapper) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SmartContractDataWrapper smartContractDataWrapper) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(smartContractDataWrapper);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SmartContractDataWrapperOrBuilder {
            private SingleFieldBuilderV3<ContractState, ContractState.Builder, ContractStateOrBuilder> contractStateBuilder_;
            private ContractState contractState_;
            private ByteString runtimecode_;
            private SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> smartContractBuilder_;
            private SmartContract smartContract_;

            @Override
            public ByteString getRuntimecode() {
                return this.runtimecode_;
            }

            @Override
            public boolean hasContractState() {
                return (this.contractStateBuilder_ == null && this.contractState_ == null) ? false : true;
            }

            @Override
            public boolean hasSmartContract() {
                return (this.smartContractBuilder_ == null && this.smartContract_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SmartContractOuterClass.internal_static_protocol_SmartContractDataWrapper_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SmartContractOuterClass.internal_static_protocol_SmartContractDataWrapper_fieldAccessorTable.ensureFieldAccessorsInitialized(SmartContractDataWrapper.class, Builder.class);
            }

            private Builder() {
                this.smartContract_ = null;
                this.runtimecode_ = ByteString.EMPTY;
                this.contractState_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.smartContract_ = null;
                this.runtimecode_ = ByteString.EMPTY;
                this.contractState_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = SmartContractDataWrapper.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.smartContractBuilder_ == null) {
                    this.smartContract_ = null;
                } else {
                    this.smartContract_ = null;
                    this.smartContractBuilder_ = null;
                }
                this.runtimecode_ = ByteString.EMPTY;
                if (this.contractStateBuilder_ == null) {
                    this.contractState_ = null;
                } else {
                    this.contractState_ = null;
                    this.contractStateBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return SmartContractOuterClass.internal_static_protocol_SmartContractDataWrapper_descriptor;
            }

            @Override
            public SmartContractDataWrapper getDefaultInstanceForType() {
                return SmartContractDataWrapper.getDefaultInstance();
            }

            @Override
            public SmartContractDataWrapper build() {
                SmartContractDataWrapper buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public SmartContractDataWrapper buildPartial() {
                SmartContractDataWrapper smartContractDataWrapper = new SmartContractDataWrapper(this);
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.smartContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    smartContractDataWrapper.smartContract_ = this.smartContract_;
                } else {
                    smartContractDataWrapper.smartContract_ = singleFieldBuilderV3.build();
                }
                smartContractDataWrapper.runtimecode_ = this.runtimecode_;
                SingleFieldBuilderV3<ContractState, ContractState.Builder, ContractStateOrBuilder> singleFieldBuilderV32 = this.contractStateBuilder_;
                if (singleFieldBuilderV32 == null) {
                    smartContractDataWrapper.contractState_ = this.contractState_;
                } else {
                    smartContractDataWrapper.contractState_ = singleFieldBuilderV32.build();
                }
                onBuilt();
                return smartContractDataWrapper;
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
                if (message instanceof SmartContractDataWrapper) {
                    return mergeFrom((SmartContractDataWrapper) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(SmartContractDataWrapper smartContractDataWrapper) {
                if (smartContractDataWrapper == SmartContractDataWrapper.getDefaultInstance()) {
                    return this;
                }
                if (smartContractDataWrapper.hasSmartContract()) {
                    mergeSmartContract(smartContractDataWrapper.getSmartContract());
                }
                if (smartContractDataWrapper.getRuntimecode() != ByteString.EMPTY) {
                    setRuntimecode(smartContractDataWrapper.getRuntimecode());
                }
                if (smartContractDataWrapper.hasContractState()) {
                    mergeContractState(smartContractDataWrapper.getContractState());
                }
                mergeUnknownFields(smartContractDataWrapper.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.SmartContractOuterClass.SmartContractDataWrapper.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.SmartContractOuterClass.SmartContractDataWrapper.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.SmartContractOuterClass$SmartContractDataWrapper$Builder");
            }

            @Override
            public SmartContract getSmartContract() {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.smartContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    SmartContract smartContract = this.smartContract_;
                    return smartContract == null ? SmartContract.getDefaultInstance() : smartContract;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setSmartContract(SmartContract smartContract) {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.smartContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    smartContract.getClass();
                    this.smartContract_ = smartContract;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(smartContract);
                }
                return this;
            }

            public Builder setSmartContract(SmartContract.Builder builder) {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.smartContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.smartContract_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeSmartContract(SmartContract smartContract) {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.smartContractBuilder_;
                if (singleFieldBuilderV3 == null) {
                    SmartContract smartContract2 = this.smartContract_;
                    if (smartContract2 != null) {
                        this.smartContract_ = SmartContract.newBuilder(smartContract2).mergeFrom(smartContract).buildPartial();
                    } else {
                        this.smartContract_ = smartContract;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(smartContract);
                }
                return this;
            }

            public Builder clearSmartContract() {
                if (this.smartContractBuilder_ == null) {
                    this.smartContract_ = null;
                    onChanged();
                } else {
                    this.smartContract_ = null;
                    this.smartContractBuilder_ = null;
                }
                return this;
            }

            public SmartContract.Builder getSmartContractBuilder() {
                onChanged();
                return getSmartContractFieldBuilder().getBuilder();
            }

            @Override
            public SmartContractOrBuilder getSmartContractOrBuilder() {
                SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> singleFieldBuilderV3 = this.smartContractBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                SmartContract smartContract = this.smartContract_;
                return smartContract == null ? SmartContract.getDefaultInstance() : smartContract;
            }

            private SingleFieldBuilderV3<SmartContract, SmartContract.Builder, SmartContractOrBuilder> getSmartContractFieldBuilder() {
                if (this.smartContractBuilder_ == null) {
                    this.smartContractBuilder_ = new SingleFieldBuilderV3<>(getSmartContract(), getParentForChildren(), isClean());
                    this.smartContract_ = null;
                }
                return this.smartContractBuilder_;
            }

            public Builder setRuntimecode(ByteString byteString) {
                byteString.getClass();
                this.runtimecode_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearRuntimecode() {
                this.runtimecode_ = SmartContractDataWrapper.getDefaultInstance().getRuntimecode();
                onChanged();
                return this;
            }

            @Override
            public ContractState getContractState() {
                SingleFieldBuilderV3<ContractState, ContractState.Builder, ContractStateOrBuilder> singleFieldBuilderV3 = this.contractStateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ContractState contractState = this.contractState_;
                    return contractState == null ? ContractState.getDefaultInstance() : contractState;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setContractState(ContractState contractState) {
                SingleFieldBuilderV3<ContractState, ContractState.Builder, ContractStateOrBuilder> singleFieldBuilderV3 = this.contractStateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    contractState.getClass();
                    this.contractState_ = contractState;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(contractState);
                }
                return this;
            }

            public Builder setContractState(ContractState.Builder builder) {
                SingleFieldBuilderV3<ContractState, ContractState.Builder, ContractStateOrBuilder> singleFieldBuilderV3 = this.contractStateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.contractState_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeContractState(ContractState contractState) {
                SingleFieldBuilderV3<ContractState, ContractState.Builder, ContractStateOrBuilder> singleFieldBuilderV3 = this.contractStateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ContractState contractState2 = this.contractState_;
                    if (contractState2 != null) {
                        this.contractState_ = ContractState.newBuilder(contractState2).mergeFrom(contractState).buildPartial();
                    } else {
                        this.contractState_ = contractState;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(contractState);
                }
                return this;
            }

            public Builder clearContractState() {
                if (this.contractStateBuilder_ == null) {
                    this.contractState_ = null;
                    onChanged();
                } else {
                    this.contractState_ = null;
                    this.contractStateBuilder_ = null;
                }
                return this;
            }

            public ContractState.Builder getContractStateBuilder() {
                onChanged();
                return getContractStateFieldBuilder().getBuilder();
            }

            @Override
            public ContractStateOrBuilder getContractStateOrBuilder() {
                SingleFieldBuilderV3<ContractState, ContractState.Builder, ContractStateOrBuilder> singleFieldBuilderV3 = this.contractStateBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ContractState contractState = this.contractState_;
                return contractState == null ? ContractState.getDefaultInstance() : contractState;
            }

            private SingleFieldBuilderV3<ContractState, ContractState.Builder, ContractStateOrBuilder> getContractStateFieldBuilder() {
                if (this.contractStateBuilder_ == null) {
                    this.contractStateBuilder_ = new SingleFieldBuilderV3<>(getContractState(), getParentForChildren(), isClean());
                    this.contractState_ = null;
                }
                return this.contractStateBuilder_;
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\"core/contract/smart_contract.proto\u0012\bprotocol\u001a\u000fcore/Tron.proto\"¬\u0007\n\rSmartContract\u0012\u0016\n\u000eorigin_address\u0018\u0001 \u0001(\f\u0012\u0018\n\u0010contract_address\u0018\u0002 \u0001(\f\u0012(\n\u0003abi\u0018\u0003 \u0001(\u000b2\u001b.protocol.SmartContract.ABI\u0012\u0010\n\bbytecode\u0018\u0004 \u0001(\f\u0012\u0012\n\ncall_value\u0018\u0005 \u0001(\u0003\u0012%\n\u001dconsume_user_resource_percent\u0018\u0006 \u0001(\u0003\u0012\f\n\u0004name\u0018\u0007 \u0001(\t\u0012\u001b\n\u0013origin_energy_limit\u0018\b \u0001(\u0003\u0012\u0011\n\tcode_hash\u0018\t \u0001(\f\u0012\u0010\n\btrx_hash\u0018\n \u0001(\f\u0012\u000f\n\u0007version\u0018\u000b \u0001(\u0005\u001a\u0090\u0005\n\u0003ABI\u00121\n\u0006entrys\u0018\u0001 \u0003(\u000b2!.protocol.SmartContract.ABI.Entry\u001aÕ\u0004\n\u0005Entry\u0012\u0011\n\tanonymous\u0018\u0001 \u0001(\b\u0012\u0010\n\bconstant\u0018\u0002 \u0001(\b\u0012\f\n\u0004name\u0018\u0003 \u0001(\t\u00127\n\u0006inputs\u0018\u0004 \u0003(\u000b2'.protocol.SmartContract.ABI.Entry.Param\u00128\n\u0007outputs\u0018\u0005 \u0003(\u000b2'.protocol.SmartContract.ABI.Entry.Param\u00129\n\u0004type\u0018\u0006 \u0001(\u000e2+.protocol.SmartContract.ABI.Entry.EntryType\u0012\u000f\n\u0007payable\u0018\u0007 \u0001(\b\u0012N\n\u000fstateMutability\u0018\b \u0001(\u000e25.protocol.SmartContract.ABI.Entry.StateMutabilityType\u001a4\n\u0005Param\u0012\u000f\n\u0007indexed\u0018\u0001 \u0001(\b\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012\f\n\u0004type\u0018\u0003 \u0001(\t\"q\n\tEntryType\u0012\u0014\n\u0010UnknownEntryType\u0010\u0000\u0012\u000f\n\u000bConstructor\u0010\u0001\u0012\f\n\bFunction\u0010\u0002\u0012\t\n\u0005Event\u0010\u0003\u0012\f\n\bFallback\u0010\u0004\u0012\u000b\n\u0007Receive\u0010\u0005\u0012\t\n\u0005Error\u0010\u0006\"a\n\u0013StateMutabilityType\u0012\u0019\n\u0015UnknownMutabilityType\u0010\u0000\u0012\b\n\u0004Pure\u0010\u0001\u0012\b\n\u0004View\u0010\u0002\u0012\u000e\n\nNonpayable\u0010\u0003\u0012\u000b\n\u0007Payable\u0010\u0004\"R\n\rContractState\u0012\u0014\n\fenergy_usage\u0018\u0001 \u0001(\u0003\u0012\u0015\n\renergy_factor\u0018\u0002 \u0001(\u0003\u0012\u0014\n\fupdate_cycle\u0018\u0003 \u0001(\u0003\"\u0087\u0001\n\u0013CreateSmartContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012-\n\fnew_contract\u0018\u0002 \u0001(\u000b2\u0017.protocol.SmartContract\u0012\u0018\n\u0010call_token_value\u0018\u0003 \u0001(\u0003\u0012\u0010\n\btoken_id\u0018\u0004 \u0001(\u0003\"\u0095\u0001\n\u0014TriggerSmartContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0018\n\u0010contract_address\u0018\u0002 \u0001(\f\u0012\u0012\n\ncall_value\u0018\u0003 \u0001(\u0003\u0012\f\n\u0004data\u0018\u0004 \u0001(\f\u0012\u0018\n\u0010call_token_value\u0018\u0005 \u0001(\u0003\u0012\u0010\n\btoken_id\u0018\u0006 \u0001(\u0003\"C\n\u0010ClearABIContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0018\n\u0010contract_address\u0018\u0002 \u0001(\f\"o\n\u0015UpdateSettingContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0018\n\u0010contract_address\u0018\u0002 \u0001(\f\u0012%\n\u001dconsume_user_resource_percent\u0018\u0003 \u0001(\u0003\"i\n\u0019UpdateEnergyLimitContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0018\n\u0010contract_address\u0018\u0002 \u0001(\f\u0012\u001b\n\u0013origin_energy_limit\u0018\u0003 \u0001(\u0003\"\u0091\u0001\n\u0018SmartContractDataWrapper\u0012/\n\u000esmart_contract\u0018\u0001 \u0001(\u000b2\u0017.protocol.SmartContract\u0012\u0013\n\u000bruntimecode\u0018\u0002 \u0001(\f\u0012/\n\u000econtract_state\u0018\u0003 \u0001(\u000b2\u0017.protocol.ContractStateBE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[]{Protocol.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = SmartContractOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_SmartContract_descriptor = descriptor2;
        internal_static_protocol_SmartContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"OriginAddress", "ContractAddress", "Abi", "Bytecode", "CallValue", "ConsumeUserResourcePercent", "Name", "OriginEnergyLimit", "CodeHash", "TrxHash", SignatureManager.Constants.Version});
        Descriptors.Descriptor descriptor3 = descriptor2.getNestedTypes().get(0);
        internal_static_protocol_SmartContract_ABI_descriptor = descriptor3;
        internal_static_protocol_SmartContract_ABI_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Entrys"});
        Descriptors.Descriptor descriptor4 = descriptor3.getNestedTypes().get(0);
        internal_static_protocol_SmartContract_ABI_Entry_descriptor = descriptor4;
        internal_static_protocol_SmartContract_ABI_Entry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Anonymous", "Constant", "Name", "Inputs", "Outputs", "Type", "Payable", "StateMutability"});
        Descriptors.Descriptor descriptor5 = descriptor4.getNestedTypes().get(0);
        internal_static_protocol_SmartContract_ABI_Entry_Param_descriptor = descriptor5;
        internal_static_protocol_SmartContract_ABI_Entry_Param_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Indexed", "Name", "Type"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_ContractState_descriptor = descriptor6;
        internal_static_protocol_ContractState_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"EnergyUsage", "EnergyFactor", "UpdateCycle"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_CreateSmartContract_descriptor = descriptor7;
        internal_static_protocol_CreateSmartContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"OwnerAddress", "NewContract", "CallTokenValue", "TokenId"});
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(3);
        internal_static_protocol_TriggerSmartContract_descriptor = descriptor8;
        internal_static_protocol_TriggerSmartContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"OwnerAddress", "ContractAddress", "CallValue", "Data", "CallTokenValue", "TokenId"});
        Descriptors.Descriptor descriptor9 = getDescriptor().getMessageTypes().get(4);
        internal_static_protocol_ClearABIContract_descriptor = descriptor9;
        internal_static_protocol_ClearABIContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"OwnerAddress", "ContractAddress"});
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(5);
        internal_static_protocol_UpdateSettingContract_descriptor = descriptor10;
        internal_static_protocol_UpdateSettingContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"OwnerAddress", "ContractAddress", "ConsumeUserResourcePercent"});
        Descriptors.Descriptor descriptor11 = getDescriptor().getMessageTypes().get(6);
        internal_static_protocol_UpdateEnergyLimitContract_descriptor = descriptor11;
        internal_static_protocol_UpdateEnergyLimitContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"OwnerAddress", "ContractAddress", "OriginEnergyLimit"});
        Descriptors.Descriptor descriptor12 = getDescriptor().getMessageTypes().get(7);
        internal_static_protocol_SmartContractDataWrapper_descriptor = descriptor12;
        internal_static_protocol_SmartContractDataWrapper_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"SmartContract", "Runtimecode", "ContractState"});
        Protocol.getDescriptor();
    }
}
