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
import org.tron.protos.Protocol;
public final class AccountContract {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_AccountCreateContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AccountCreateContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_AccountPermissionUpdateContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AccountPermissionUpdateContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_AccountUpdateContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_AccountUpdateContract_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_SetAccountIdContract_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_SetAccountIdContract_fieldAccessorTable;

    public interface AccountCreateContractOrBuilder extends MessageOrBuilder {
        ByteString getAccountAddress();

        ByteString getOwnerAddress();

        Protocol.AccountType getType();

        int getTypeValue();
    }

    public interface AccountPermissionUpdateContractOrBuilder extends MessageOrBuilder {
        Protocol.Permission getActives(int i);

        int getActivesCount();

        List<Protocol.Permission> getActivesList();

        Protocol.PermissionOrBuilder getActivesOrBuilder(int i);

        List<? extends Protocol.PermissionOrBuilder> getActivesOrBuilderList();

        Protocol.Permission getOwner();

        ByteString getOwnerAddress();

        Protocol.PermissionOrBuilder getOwnerOrBuilder();

        Protocol.Permission getWitness();

        Protocol.PermissionOrBuilder getWitnessOrBuilder();

        boolean hasOwner();

        boolean hasWitness();
    }

    public interface AccountUpdateContractOrBuilder extends MessageOrBuilder {
        ByteString getAccountName();

        ByteString getOwnerAddress();
    }

    public interface SetAccountIdContractOrBuilder extends MessageOrBuilder {
        ByteString getAccountId();

        ByteString getOwnerAddress();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private AccountContract() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class AccountCreateContract extends GeneratedMessageV3 implements AccountCreateContractOrBuilder {
        public static final int ACCOUNT_ADDRESS_FIELD_NUMBER = 2;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int TYPE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private ByteString accountAddress_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private int type_;
        private static final AccountCreateContract DEFAULT_INSTANCE = new AccountCreateContract();
        private static final Parser<AccountCreateContract> PARSER = new AbstractParser<AccountCreateContract>() {
            @Override
            public AccountCreateContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AccountCreateContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static AccountCreateContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AccountCreateContract> parser() {
            return PARSER;
        }

        @Override
        public ByteString getAccountAddress() {
            return this.accountAddress_;
        }

        @Override
        public AccountCreateContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<AccountCreateContract> getParserForType() {
            return PARSER;
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

        private AccountCreateContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private AccountCreateContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.accountAddress_ = ByteString.EMPTY;
            this.type_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AccountCreateContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.accountAddress_ = codedInputStream.readBytes();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.type_ = codedInputStream.readEnum();
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
            return AccountContract.internal_static_protocol_AccountCreateContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccountContract.internal_static_protocol_AccountCreateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountCreateContract.class, Builder.class);
        }

        @Override
        public Protocol.AccountType getType() {
            Protocol.AccountType valueOf = Protocol.AccountType.valueOf(this.type_);
            return valueOf == null ? Protocol.AccountType.UNRECOGNIZED : valueOf;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (!this.accountAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.accountAddress_);
            }
            if (this.type_ != Protocol.AccountType.Normal.getNumber()) {
                codedOutputStream.writeEnum(3, this.type_);
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
            if (!this.accountAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.accountAddress_);
            }
            if (this.type_ != Protocol.AccountType.Normal.getNumber()) {
                computeBytesSize += CodedOutputStream.computeEnumSize(3, this.type_);
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
            if (!(obj instanceof AccountCreateContract)) {
                return super.equals(obj);
            }
            AccountCreateContract accountCreateContract = (AccountCreateContract) obj;
            return getOwnerAddress().equals(accountCreateContract.getOwnerAddress()) && getAccountAddress().equals(accountCreateContract.getAccountAddress()) && this.type_ == accountCreateContract.type_ && this.unknownFields.equals(accountCreateContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode()) * 37) + 2) * 53) + getAccountAddress().hashCode()) * 37) + 3) * 53) + this.type_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static AccountCreateContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AccountCreateContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AccountCreateContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AccountCreateContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AccountCreateContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AccountCreateContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AccountCreateContract parseFrom(InputStream inputStream) throws IOException {
            return (AccountCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AccountCreateContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountCreateContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AccountCreateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AccountCreateContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountCreateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountCreateContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AccountCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AccountCreateContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountCreateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AccountCreateContract accountCreateContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(accountCreateContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AccountCreateContractOrBuilder {
            private ByteString accountAddress_;
            private ByteString ownerAddress_;
            private int type_;

            @Override
            public ByteString getAccountAddress() {
                return this.accountAddress_;
            }

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
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
                return AccountContract.internal_static_protocol_AccountCreateContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AccountContract.internal_static_protocol_AccountCreateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountCreateContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.accountAddress_ = ByteString.EMPTY;
                this.type_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.accountAddress_ = ByteString.EMPTY;
                this.type_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = AccountCreateContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                this.accountAddress_ = ByteString.EMPTY;
                this.type_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return AccountContract.internal_static_protocol_AccountCreateContract_descriptor;
            }

            @Override
            public AccountCreateContract getDefaultInstanceForType() {
                return AccountCreateContract.getDefaultInstance();
            }

            @Override
            public AccountCreateContract build() {
                AccountCreateContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public AccountCreateContract buildPartial() {
                AccountCreateContract accountCreateContract = new AccountCreateContract(this);
                accountCreateContract.ownerAddress_ = this.ownerAddress_;
                accountCreateContract.accountAddress_ = this.accountAddress_;
                accountCreateContract.type_ = this.type_;
                onBuilt();
                return accountCreateContract;
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
                if (message instanceof AccountCreateContract) {
                    return mergeFrom((AccountCreateContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AccountCreateContract accountCreateContract) {
                if (accountCreateContract == AccountCreateContract.getDefaultInstance()) {
                    return this;
                }
                if (accountCreateContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(accountCreateContract.getOwnerAddress());
                }
                if (accountCreateContract.getAccountAddress() != ByteString.EMPTY) {
                    setAccountAddress(accountCreateContract.getAccountAddress());
                }
                if (accountCreateContract.type_ != 0) {
                    setTypeValue(accountCreateContract.getTypeValue());
                }
                mergeUnknownFields(accountCreateContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.AccountContract.AccountCreateContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AccountContract.AccountCreateContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AccountContract$AccountCreateContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = AccountCreateContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            public Builder setAccountAddress(ByteString byteString) {
                byteString.getClass();
                this.accountAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAccountAddress() {
                this.accountAddress_ = AccountCreateContract.getDefaultInstance().getAccountAddress();
                onChanged();
                return this;
            }

            public Builder setTypeValue(int i) {
                this.type_ = i;
                onChanged();
                return this;
            }

            @Override
            public Protocol.AccountType getType() {
                Protocol.AccountType valueOf = Protocol.AccountType.valueOf(this.type_);
                return valueOf == null ? Protocol.AccountType.UNRECOGNIZED : valueOf;
            }

            public Builder setType(Protocol.AccountType accountType) {
                accountType.getClass();
                this.type_ = accountType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.type_ = 0;
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

    public static final class AccountUpdateContract extends GeneratedMessageV3 implements AccountUpdateContractOrBuilder {
        public static final int ACCOUNT_NAME_FIELD_NUMBER = 1;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private ByteString accountName_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final AccountUpdateContract DEFAULT_INSTANCE = new AccountUpdateContract();
        private static final Parser<AccountUpdateContract> PARSER = new AbstractParser<AccountUpdateContract>() {
            @Override
            public AccountUpdateContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AccountUpdateContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static AccountUpdateContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AccountUpdateContract> parser() {
            return PARSER;
        }

        @Override
        public ByteString getAccountName() {
            return this.accountName_;
        }

        @Override
        public AccountUpdateContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<AccountUpdateContract> getParserForType() {
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

        private AccountUpdateContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private AccountUpdateContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.accountName_ = ByteString.EMPTY;
            this.ownerAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AccountUpdateContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.accountName_ = codedInputStream.readBytes();
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.ownerAddress_ = codedInputStream.readBytes();
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
            return AccountContract.internal_static_protocol_AccountUpdateContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccountContract.internal_static_protocol_AccountUpdateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountUpdateContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.accountName_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.accountName_);
            }
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.ownerAddress_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.accountName_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.accountName_) : 0;
            if (!this.ownerAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.ownerAddress_);
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
            if (!(obj instanceof AccountUpdateContract)) {
                return super.equals(obj);
            }
            AccountUpdateContract accountUpdateContract = (AccountUpdateContract) obj;
            return getAccountName().equals(accountUpdateContract.getAccountName()) && getOwnerAddress().equals(accountUpdateContract.getOwnerAddress()) && this.unknownFields.equals(accountUpdateContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAccountName().hashCode()) * 37) + 2) * 53) + getOwnerAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static AccountUpdateContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AccountUpdateContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AccountUpdateContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AccountUpdateContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AccountUpdateContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AccountUpdateContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AccountUpdateContract parseFrom(InputStream inputStream) throws IOException {
            return (AccountUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AccountUpdateContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountUpdateContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AccountUpdateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AccountUpdateContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountUpdateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountUpdateContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AccountUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AccountUpdateContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AccountUpdateContract accountUpdateContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(accountUpdateContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AccountUpdateContractOrBuilder {
            private ByteString accountName_;
            private ByteString ownerAddress_;

            @Override
            public ByteString getAccountName() {
                return this.accountName_;
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
                return AccountContract.internal_static_protocol_AccountUpdateContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AccountContract.internal_static_protocol_AccountUpdateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountUpdateContract.class, Builder.class);
            }

            private Builder() {
                this.accountName_ = ByteString.EMPTY;
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.accountName_ = ByteString.EMPTY;
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = AccountUpdateContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.accountName_ = ByteString.EMPTY;
                this.ownerAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return AccountContract.internal_static_protocol_AccountUpdateContract_descriptor;
            }

            @Override
            public AccountUpdateContract getDefaultInstanceForType() {
                return AccountUpdateContract.getDefaultInstance();
            }

            @Override
            public AccountUpdateContract build() {
                AccountUpdateContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public AccountUpdateContract buildPartial() {
                AccountUpdateContract accountUpdateContract = new AccountUpdateContract(this);
                accountUpdateContract.accountName_ = this.accountName_;
                accountUpdateContract.ownerAddress_ = this.ownerAddress_;
                onBuilt();
                return accountUpdateContract;
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
                if (message instanceof AccountUpdateContract) {
                    return mergeFrom((AccountUpdateContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AccountUpdateContract accountUpdateContract) {
                if (accountUpdateContract == AccountUpdateContract.getDefaultInstance()) {
                    return this;
                }
                if (accountUpdateContract.getAccountName() != ByteString.EMPTY) {
                    setAccountName(accountUpdateContract.getAccountName());
                }
                if (accountUpdateContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(accountUpdateContract.getOwnerAddress());
                }
                mergeUnknownFields(accountUpdateContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.AccountContract.AccountUpdateContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AccountContract.AccountUpdateContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AccountContract$AccountUpdateContract$Builder");
            }

            public Builder setAccountName(ByteString byteString) {
                byteString.getClass();
                this.accountName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAccountName() {
                this.accountName_ = AccountUpdateContract.getDefaultInstance().getAccountName();
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
                this.ownerAddress_ = AccountUpdateContract.getDefaultInstance().getOwnerAddress();
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

    public static final class SetAccountIdContract extends GeneratedMessageV3 implements SetAccountIdContractOrBuilder {
        public static final int ACCOUNT_ID_FIELD_NUMBER = 1;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private ByteString accountId_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private static final SetAccountIdContract DEFAULT_INSTANCE = new SetAccountIdContract();
        private static final Parser<SetAccountIdContract> PARSER = new AbstractParser<SetAccountIdContract>() {
            @Override
            public SetAccountIdContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new SetAccountIdContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static SetAccountIdContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SetAccountIdContract> parser() {
            return PARSER;
        }

        @Override
        public ByteString getAccountId() {
            return this.accountId_;
        }

        @Override
        public SetAccountIdContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<SetAccountIdContract> getParserForType() {
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

        private SetAccountIdContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private SetAccountIdContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.accountId_ = ByteString.EMPTY;
            this.ownerAddress_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private SetAccountIdContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.accountId_ = codedInputStream.readBytes();
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.ownerAddress_ = codedInputStream.readBytes();
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
            return AccountContract.internal_static_protocol_SetAccountIdContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccountContract.internal_static_protocol_SetAccountIdContract_fieldAccessorTable.ensureFieldAccessorsInitialized(SetAccountIdContract.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.accountId_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.accountId_);
            }
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.ownerAddress_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.accountId_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.accountId_) : 0;
            if (!this.ownerAddress_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.ownerAddress_);
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
            if (!(obj instanceof SetAccountIdContract)) {
                return super.equals(obj);
            }
            SetAccountIdContract setAccountIdContract = (SetAccountIdContract) obj;
            return getAccountId().equals(setAccountIdContract.getAccountId()) && getOwnerAddress().equals(setAccountIdContract.getOwnerAddress()) && this.unknownFields.equals(setAccountIdContract.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAccountId().hashCode()) * 37) + 2) * 53) + getOwnerAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static SetAccountIdContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static SetAccountIdContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static SetAccountIdContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static SetAccountIdContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static SetAccountIdContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static SetAccountIdContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static SetAccountIdContract parseFrom(InputStream inputStream) throws IOException {
            return (SetAccountIdContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static SetAccountIdContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetAccountIdContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SetAccountIdContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SetAccountIdContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static SetAccountIdContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetAccountIdContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static SetAccountIdContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SetAccountIdContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static SetAccountIdContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetAccountIdContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SetAccountIdContract setAccountIdContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(setAccountIdContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SetAccountIdContractOrBuilder {
            private ByteString accountId_;
            private ByteString ownerAddress_;

            @Override
            public ByteString getAccountId() {
                return this.accountId_;
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
                return AccountContract.internal_static_protocol_SetAccountIdContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AccountContract.internal_static_protocol_SetAccountIdContract_fieldAccessorTable.ensureFieldAccessorsInitialized(SetAccountIdContract.class, Builder.class);
            }

            private Builder() {
                this.accountId_ = ByteString.EMPTY;
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.accountId_ = ByteString.EMPTY;
                this.ownerAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = SetAccountIdContract.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.accountId_ = ByteString.EMPTY;
                this.ownerAddress_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return AccountContract.internal_static_protocol_SetAccountIdContract_descriptor;
            }

            @Override
            public SetAccountIdContract getDefaultInstanceForType() {
                return SetAccountIdContract.getDefaultInstance();
            }

            @Override
            public SetAccountIdContract build() {
                SetAccountIdContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public SetAccountIdContract buildPartial() {
                SetAccountIdContract setAccountIdContract = new SetAccountIdContract(this);
                setAccountIdContract.accountId_ = this.accountId_;
                setAccountIdContract.ownerAddress_ = this.ownerAddress_;
                onBuilt();
                return setAccountIdContract;
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
                if (message instanceof SetAccountIdContract) {
                    return mergeFrom((SetAccountIdContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(SetAccountIdContract setAccountIdContract) {
                if (setAccountIdContract == SetAccountIdContract.getDefaultInstance()) {
                    return this;
                }
                if (setAccountIdContract.getAccountId() != ByteString.EMPTY) {
                    setAccountId(setAccountIdContract.getAccountId());
                }
                if (setAccountIdContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(setAccountIdContract.getOwnerAddress());
                }
                mergeUnknownFields(setAccountIdContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.AccountContract.SetAccountIdContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AccountContract.SetAccountIdContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AccountContract$SetAccountIdContract$Builder");
            }

            public Builder setAccountId(ByteString byteString) {
                byteString.getClass();
                this.accountId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAccountId() {
                this.accountId_ = SetAccountIdContract.getDefaultInstance().getAccountId();
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
                this.ownerAddress_ = SetAccountIdContract.getDefaultInstance().getOwnerAddress();
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

    public static final class AccountPermissionUpdateContract extends GeneratedMessageV3 implements AccountPermissionUpdateContractOrBuilder {
        public static final int ACTIVES_FIELD_NUMBER = 4;
        public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
        public static final int OWNER_FIELD_NUMBER = 2;
        public static final int WITNESS_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private List<Protocol.Permission> actives_;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private ByteString ownerAddress_;
        private Protocol.Permission owner_;
        private Protocol.Permission witness_;
        private static final AccountPermissionUpdateContract DEFAULT_INSTANCE = new AccountPermissionUpdateContract();
        private static final Parser<AccountPermissionUpdateContract> PARSER = new AbstractParser<AccountPermissionUpdateContract>() {
            @Override
            public AccountPermissionUpdateContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AccountPermissionUpdateContract(codedInputStream, extensionRegistryLite);
            }
        };

        public static AccountPermissionUpdateContract getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AccountPermissionUpdateContract> parser() {
            return PARSER;
        }

        @Override
        public List<Protocol.Permission> getActivesList() {
            return this.actives_;
        }

        @Override
        public List<? extends Protocol.PermissionOrBuilder> getActivesOrBuilderList() {
            return this.actives_;
        }

        @Override
        public AccountPermissionUpdateContract getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getOwnerAddress() {
            return this.ownerAddress_;
        }

        @Override
        public Parser<AccountPermissionUpdateContract> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasOwner() {
            return this.owner_ != null;
        }

        @Override
        public boolean hasWitness() {
            return this.witness_ != null;
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

        private AccountPermissionUpdateContract(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private AccountPermissionUpdateContract() {
            this.memoizedIsInitialized = (byte) -1;
            this.ownerAddress_ = ByteString.EMPTY;
            this.actives_ = Collections.emptyList();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AccountPermissionUpdateContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            Protocol.Permission.Builder builder;
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
                                if (readTag == 18) {
                                    Protocol.Permission permission = this.owner_;
                                    builder = permission != null ? permission.toBuilder() : null;
                                    Protocol.Permission permission2 = (Protocol.Permission) codedInputStream.readMessage(Protocol.Permission.parser(), extensionRegistryLite);
                                    this.owner_ = permission2;
                                    if (builder != null) {
                                        builder.mergeFrom(permission2);
                                        this.owner_ = builder.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    Protocol.Permission permission3 = this.witness_;
                                    builder = permission3 != null ? permission3.toBuilder() : null;
                                    Protocol.Permission permission4 = (Protocol.Permission) codedInputStream.readMessage(Protocol.Permission.parser(), extensionRegistryLite);
                                    this.witness_ = permission4;
                                    if (builder != null) {
                                        builder.mergeFrom(permission4);
                                        this.witness_ = builder.buildPartial();
                                    }
                                } else if (readTag != 34) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    if (!(z2 & true)) {
                                        this.actives_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.actives_.add((Protocol.Permission) codedInputStream.readMessage(Protocol.Permission.parser(), extensionRegistryLite));
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
                    if (z2 & true) {
                        this.actives_ = Collections.unmodifiableList(this.actives_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AccountContract.internal_static_protocol_AccountPermissionUpdateContract_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccountContract.internal_static_protocol_AccountPermissionUpdateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountPermissionUpdateContract.class, Builder.class);
        }

        @Override
        public Protocol.Permission getOwner() {
            Protocol.Permission permission = this.owner_;
            return permission == null ? Protocol.Permission.getDefaultInstance() : permission;
        }

        @Override
        public Protocol.PermissionOrBuilder getOwnerOrBuilder() {
            return getOwner();
        }

        @Override
        public Protocol.Permission getWitness() {
            Protocol.Permission permission = this.witness_;
            return permission == null ? Protocol.Permission.getDefaultInstance() : permission;
        }

        @Override
        public Protocol.PermissionOrBuilder getWitnessOrBuilder() {
            return getWitness();
        }

        @Override
        public int getActivesCount() {
            return this.actives_.size();
        }

        @Override
        public Protocol.Permission getActives(int i) {
            return this.actives_.get(i);
        }

        @Override
        public Protocol.PermissionOrBuilder getActivesOrBuilder(int i) {
            return this.actives_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.ownerAddress_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.ownerAddress_);
            }
            if (this.owner_ != null) {
                codedOutputStream.writeMessage(2, getOwner());
            }
            if (this.witness_ != null) {
                codedOutputStream.writeMessage(3, getWitness());
            }
            for (int i = 0; i < this.actives_.size(); i++) {
                codedOutputStream.writeMessage(4, this.actives_.get(i));
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
            if (this.owner_ != null) {
                computeBytesSize += CodedOutputStream.computeMessageSize(2, getOwner());
            }
            if (this.witness_ != null) {
                computeBytesSize += CodedOutputStream.computeMessageSize(3, getWitness());
            }
            for (int i2 = 0; i2 < this.actives_.size(); i2++) {
                computeBytesSize += CodedOutputStream.computeMessageSize(4, this.actives_.get(i2));
            }
            int serializedSize = computeBytesSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            boolean z;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AccountPermissionUpdateContract)) {
                return super.equals(obj);
            }
            AccountPermissionUpdateContract accountPermissionUpdateContract = (AccountPermissionUpdateContract) obj;
            boolean z2 = getOwnerAddress().equals(accountPermissionUpdateContract.getOwnerAddress()) && hasOwner() == accountPermissionUpdateContract.hasOwner();
            if (!hasOwner() ? z2 : !(!z2 || !getOwner().equals(accountPermissionUpdateContract.getOwner()))) {
                if (hasWitness() == accountPermissionUpdateContract.hasWitness()) {
                    z = true;
                    if (hasWitness() ? z : !(!z || !getWitness().equals(accountPermissionUpdateContract.getWitness()))) {
                        if (!getActivesList().equals(accountPermissionUpdateContract.getActivesList()) && this.unknownFields.equals(accountPermissionUpdateContract.unknownFields)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            z = false;
            if (hasWitness()) {
                return false;
            }
            if (!getActivesList().equals(accountPermissionUpdateContract.getActivesList())) {
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOwnerAddress().hashCode();
            if (hasOwner()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getOwner().hashCode();
            }
            if (hasWitness()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getWitness().hashCode();
            }
            if (getActivesCount() > 0) {
                hashCode = (((hashCode * 37) + 4) * 53) + getActivesList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static AccountPermissionUpdateContract parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AccountPermissionUpdateContract parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AccountPermissionUpdateContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AccountPermissionUpdateContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AccountPermissionUpdateContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AccountPermissionUpdateContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AccountPermissionUpdateContract parseFrom(InputStream inputStream) throws IOException {
            return (AccountPermissionUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AccountPermissionUpdateContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountPermissionUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountPermissionUpdateContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AccountPermissionUpdateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AccountPermissionUpdateContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountPermissionUpdateContract) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AccountPermissionUpdateContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AccountPermissionUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AccountPermissionUpdateContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AccountPermissionUpdateContract) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AccountPermissionUpdateContract accountPermissionUpdateContract) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(accountPermissionUpdateContract);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AccountPermissionUpdateContractOrBuilder {
            private RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> activesBuilder_;
            private List<Protocol.Permission> actives_;
            private int bitField0_;
            private ByteString ownerAddress_;
            private SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> ownerBuilder_;
            private Protocol.Permission owner_;
            private SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> witnessBuilder_;
            private Protocol.Permission witness_;

            @Override
            public ByteString getOwnerAddress() {
                return this.ownerAddress_;
            }

            @Override
            public boolean hasOwner() {
                return (this.ownerBuilder_ == null && this.owner_ == null) ? false : true;
            }

            @Override
            public boolean hasWitness() {
                return (this.witnessBuilder_ == null && this.witness_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return AccountContract.internal_static_protocol_AccountPermissionUpdateContract_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AccountContract.internal_static_protocol_AccountPermissionUpdateContract_fieldAccessorTable.ensureFieldAccessorsInitialized(AccountPermissionUpdateContract.class, Builder.class);
            }

            private Builder() {
                this.ownerAddress_ = ByteString.EMPTY;
                this.owner_ = null;
                this.witness_ = null;
                this.actives_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ownerAddress_ = ByteString.EMPTY;
                this.owner_ = null;
                this.witness_ = null;
                this.actives_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (AccountPermissionUpdateContract.alwaysUseFieldBuilders) {
                    getActivesFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ownerAddress_ = ByteString.EMPTY;
                if (this.ownerBuilder_ == null) {
                    this.owner_ = null;
                } else {
                    this.owner_ = null;
                    this.ownerBuilder_ = null;
                }
                if (this.witnessBuilder_ == null) {
                    this.witness_ = null;
                } else {
                    this.witness_ = null;
                    this.witnessBuilder_ = null;
                }
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.actives_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return AccountContract.internal_static_protocol_AccountPermissionUpdateContract_descriptor;
            }

            @Override
            public AccountPermissionUpdateContract getDefaultInstanceForType() {
                return AccountPermissionUpdateContract.getDefaultInstance();
            }

            @Override
            public AccountPermissionUpdateContract build() {
                AccountPermissionUpdateContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public AccountPermissionUpdateContract buildPartial() {
                AccountPermissionUpdateContract accountPermissionUpdateContract = new AccountPermissionUpdateContract(this);
                accountPermissionUpdateContract.ownerAddress_ = this.ownerAddress_;
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.ownerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    accountPermissionUpdateContract.owner_ = this.owner_;
                } else {
                    accountPermissionUpdateContract.owner_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV32 = this.witnessBuilder_;
                if (singleFieldBuilderV32 == null) {
                    accountPermissionUpdateContract.witness_ = this.witness_;
                } else {
                    accountPermissionUpdateContract.witness_ = singleFieldBuilderV32.build();
                }
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.actives_ = Collections.unmodifiableList(this.actives_);
                        this.bitField0_ &= -9;
                    }
                    accountPermissionUpdateContract.actives_ = this.actives_;
                } else {
                    accountPermissionUpdateContract.actives_ = repeatedFieldBuilderV3.build();
                }
                accountPermissionUpdateContract.bitField0_ = 0;
                onBuilt();
                return accountPermissionUpdateContract;
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
                if (message instanceof AccountPermissionUpdateContract) {
                    return mergeFrom((AccountPermissionUpdateContract) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AccountPermissionUpdateContract accountPermissionUpdateContract) {
                if (accountPermissionUpdateContract == AccountPermissionUpdateContract.getDefaultInstance()) {
                    return this;
                }
                if (accountPermissionUpdateContract.getOwnerAddress() != ByteString.EMPTY) {
                    setOwnerAddress(accountPermissionUpdateContract.getOwnerAddress());
                }
                if (accountPermissionUpdateContract.hasOwner()) {
                    mergeOwner(accountPermissionUpdateContract.getOwner());
                }
                if (accountPermissionUpdateContract.hasWitness()) {
                    mergeWitness(accountPermissionUpdateContract.getWitness());
                }
                if (this.activesBuilder_ == null) {
                    if (!accountPermissionUpdateContract.actives_.isEmpty()) {
                        if (this.actives_.isEmpty()) {
                            this.actives_ = accountPermissionUpdateContract.actives_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureActivesIsMutable();
                            this.actives_.addAll(accountPermissionUpdateContract.actives_);
                        }
                        onChanged();
                    }
                } else if (!accountPermissionUpdateContract.actives_.isEmpty()) {
                    if (!this.activesBuilder_.isEmpty()) {
                        this.activesBuilder_.addAllMessages(accountPermissionUpdateContract.actives_);
                    } else {
                        this.activesBuilder_.dispose();
                        this.activesBuilder_ = null;
                        this.actives_ = accountPermissionUpdateContract.actives_;
                        this.bitField0_ &= -9;
                        this.activesBuilder_ = AccountPermissionUpdateContract.alwaysUseFieldBuilders ? getActivesFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(accountPermissionUpdateContract.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.contract.AccountContract.AccountPermissionUpdateContract.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.contract.AccountContract.AccountPermissionUpdateContract.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.contract.AccountContract$AccountPermissionUpdateContract$Builder");
            }

            public Builder setOwnerAddress(ByteString byteString) {
                byteString.getClass();
                this.ownerAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearOwnerAddress() {
                this.ownerAddress_ = AccountPermissionUpdateContract.getDefaultInstance().getOwnerAddress();
                onChanged();
                return this;
            }

            @Override
            public Protocol.Permission getOwner() {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.ownerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Protocol.Permission permission = this.owner_;
                    return permission == null ? Protocol.Permission.getDefaultInstance() : permission;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setOwner(Protocol.Permission permission) {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.ownerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    permission.getClass();
                    this.owner_ = permission;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(permission);
                }
                return this;
            }

            public Builder setOwner(Protocol.Permission.Builder builder) {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.ownerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.owner_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeOwner(Protocol.Permission permission) {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.ownerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Protocol.Permission permission2 = this.owner_;
                    if (permission2 != null) {
                        this.owner_ = Protocol.Permission.newBuilder(permission2).mergeFrom(permission).buildPartial();
                    } else {
                        this.owner_ = permission;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(permission);
                }
                return this;
            }

            public Builder clearOwner() {
                if (this.ownerBuilder_ == null) {
                    this.owner_ = null;
                    onChanged();
                } else {
                    this.owner_ = null;
                    this.ownerBuilder_ = null;
                }
                return this;
            }

            public Protocol.Permission.Builder getOwnerBuilder() {
                onChanged();
                return getOwnerFieldBuilder().getBuilder();
            }

            @Override
            public Protocol.PermissionOrBuilder getOwnerOrBuilder() {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.ownerBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Protocol.Permission permission = this.owner_;
                return permission == null ? Protocol.Permission.getDefaultInstance() : permission;
            }

            private SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> getOwnerFieldBuilder() {
                if (this.ownerBuilder_ == null) {
                    this.ownerBuilder_ = new SingleFieldBuilderV3<>(getOwner(), getParentForChildren(), isClean());
                    this.owner_ = null;
                }
                return this.ownerBuilder_;
            }

            @Override
            public Protocol.Permission getWitness() {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.witnessBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Protocol.Permission permission = this.witness_;
                    return permission == null ? Protocol.Permission.getDefaultInstance() : permission;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setWitness(Protocol.Permission permission) {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.witnessBuilder_;
                if (singleFieldBuilderV3 == null) {
                    permission.getClass();
                    this.witness_ = permission;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(permission);
                }
                return this;
            }

            public Builder setWitness(Protocol.Permission.Builder builder) {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.witnessBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.witness_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeWitness(Protocol.Permission permission) {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.witnessBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Protocol.Permission permission2 = this.witness_;
                    if (permission2 != null) {
                        this.witness_ = Protocol.Permission.newBuilder(permission2).mergeFrom(permission).buildPartial();
                    } else {
                        this.witness_ = permission;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(permission);
                }
                return this;
            }

            public Builder clearWitness() {
                if (this.witnessBuilder_ == null) {
                    this.witness_ = null;
                    onChanged();
                } else {
                    this.witness_ = null;
                    this.witnessBuilder_ = null;
                }
                return this;
            }

            public Protocol.Permission.Builder getWitnessBuilder() {
                onChanged();
                return getWitnessFieldBuilder().getBuilder();
            }

            @Override
            public Protocol.PermissionOrBuilder getWitnessOrBuilder() {
                SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> singleFieldBuilderV3 = this.witnessBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Protocol.Permission permission = this.witness_;
                return permission == null ? Protocol.Permission.getDefaultInstance() : permission;
            }

            private SingleFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> getWitnessFieldBuilder() {
                if (this.witnessBuilder_ == null) {
                    this.witnessBuilder_ = new SingleFieldBuilderV3<>(getWitness(), getParentForChildren(), isClean());
                    this.witness_ = null;
                }
                return this.witnessBuilder_;
            }

            private void ensureActivesIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.actives_ = new ArrayList(this.actives_);
                    this.bitField0_ |= 8;
                }
            }

            @Override
            public List<Protocol.Permission> getActivesList() {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.actives_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getActivesCount() {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.actives_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public Protocol.Permission getActives(int i) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.actives_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setActives(int i, Protocol.Permission permission) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    permission.getClass();
                    ensureActivesIsMutable();
                    this.actives_.set(i, permission);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, permission);
                }
                return this;
            }

            public Builder setActives(int i, Protocol.Permission.Builder builder) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureActivesIsMutable();
                    this.actives_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addActives(Protocol.Permission permission) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    permission.getClass();
                    ensureActivesIsMutable();
                    this.actives_.add(permission);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(permission);
                }
                return this;
            }

            public Builder addActives(int i, Protocol.Permission permission) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    permission.getClass();
                    ensureActivesIsMutable();
                    this.actives_.add(i, permission);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, permission);
                }
                return this;
            }

            public Builder addActives(Protocol.Permission.Builder builder) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureActivesIsMutable();
                    this.actives_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addActives(int i, Protocol.Permission.Builder builder) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureActivesIsMutable();
                    this.actives_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllActives(Iterable<? extends Protocol.Permission> iterable) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureActivesIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.actives_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearActives() {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.actives_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeActives(int i) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureActivesIsMutable();
                    this.actives_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Protocol.Permission.Builder getActivesBuilder(int i) {
                return getActivesFieldBuilder().getBuilder(i);
            }

            @Override
            public Protocol.PermissionOrBuilder getActivesOrBuilder(int i) {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.actives_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends Protocol.PermissionOrBuilder> getActivesOrBuilderList() {
                RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> repeatedFieldBuilderV3 = this.activesBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.actives_);
            }

            public Protocol.Permission.Builder addActivesBuilder() {
                return getActivesFieldBuilder().addBuilder(Protocol.Permission.getDefaultInstance());
            }

            public Protocol.Permission.Builder addActivesBuilder(int i) {
                return getActivesFieldBuilder().addBuilder(i, Protocol.Permission.getDefaultInstance());
            }

            public List<Protocol.Permission.Builder> getActivesBuilderList() {
                return getActivesFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Protocol.Permission, Protocol.Permission.Builder, Protocol.PermissionOrBuilder> getActivesFieldBuilder() {
                if (this.activesBuilder_ == null) {
                    this.activesBuilder_ = new RepeatedFieldBuilderV3<>(this.actives_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                    this.actives_ = null;
                }
                return this.activesBuilder_;
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n$core/contract/account_contract.proto\u0012\bprotocol\u001a\u000fcore/Tron.proto\"l\n\u0015AccountCreateContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012\u0017\n\u000faccount_address\u0018\u0002 \u0001(\f\u0012#\n\u0004type\u0018\u0003 \u0001(\u000e2\u0015.protocol.AccountType\"D\n\u0015AccountUpdateContract\u0012\u0014\n\faccount_name\u0018\u0001 \u0001(\f\u0012\u0015\n\rowner_address\u0018\u0002 \u0001(\f\"A\n\u0014SetAccountIdContract\u0012\u0012\n\naccount_id\u0018\u0001 \u0001(\f\u0012\u0015\n\rowner_address\u0018\u0002 \u0001(\f\"«\u0001\n\u001fAccountPermissionUpdateContract\u0012\u0015\n\rowner_address\u0018\u0001 \u0001(\f\u0012#\n\u0005owner\u0018\u0002 \u0001(\u000b2\u0014.protocol.Permission\u0012%\n\u0007witness\u0018\u0003 \u0001(\u000b2\u0014.protocol.Permission\u0012%\n\u0007actives\u0018\u0004 \u0003(\u000b2\u0014.protocol.PermissionBE\n\u0018org.tron.protos.contractZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[]{Protocol.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = AccountContract.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_AccountCreateContract_descriptor = descriptor2;
        internal_static_protocol_AccountCreateContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"OwnerAddress", "AccountAddress", "Type"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_AccountUpdateContract_descriptor = descriptor3;
        internal_static_protocol_AccountUpdateContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"AccountName", "OwnerAddress"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_SetAccountIdContract_descriptor = descriptor4;
        internal_static_protocol_SetAccountIdContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"AccountId", "OwnerAddress"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_protocol_AccountPermissionUpdateContract_descriptor = descriptor5;
        internal_static_protocol_AccountPermissionUpdateContract_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"OwnerAddress", "Owner", "Witness", "Actives"});
        Protocol.getDescriptor();
    }
}
