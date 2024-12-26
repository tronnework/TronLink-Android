package org.tron.protos;

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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class TronInventoryItems {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_InventoryItems_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_InventoryItems_fieldAccessorTable;

    public interface InventoryItemsOrBuilder extends MessageOrBuilder {
        ByteString getItems(int i);

        int getItemsCount();

        List<ByteString> getItemsList();

        int getType();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private TronInventoryItems() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class InventoryItems extends GeneratedMessageV3 implements InventoryItemsOrBuilder {
        public static final int ITEMS_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private List<ByteString> items_;
        private byte memoizedIsInitialized;
        private int type_;
        private static final InventoryItems DEFAULT_INSTANCE = new InventoryItems();
        private static final Parser<InventoryItems> PARSER = new AbstractParser<InventoryItems>() {
            @Override
            public InventoryItems parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new InventoryItems(codedInputStream, extensionRegistryLite);
            }
        };

        public static InventoryItems getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<InventoryItems> parser() {
            return PARSER;
        }

        @Override
        public InventoryItems getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public List<ByteString> getItemsList() {
            return this.items_;
        }

        @Override
        public Parser<InventoryItems> getParserForType() {
            return PARSER;
        }

        @Override
        public int getType() {
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

        private InventoryItems(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private InventoryItems() {
            this.memoizedIsInitialized = (byte) -1;
            this.type_ = 0;
            this.items_ = Collections.emptyList();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private InventoryItems(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.type_ = codedInputStream.readInt32();
                            } else if (readTag != 18) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.items_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.items_.add(codedInputStream.readBytes());
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
                        this.items_ = Collections.unmodifiableList(this.items_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TronInventoryItems.internal_static_protocol_InventoryItems_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TronInventoryItems.internal_static_protocol_InventoryItems_fieldAccessorTable.ensureFieldAccessorsInitialized(InventoryItems.class, Builder.class);
        }

        @Override
        public int getItemsCount() {
            return this.items_.size();
        }

        @Override
        public ByteString getItems(int i) {
            return this.items_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.type_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            for (int i2 = 0; i2 < this.items_.size(); i2++) {
                codedOutputStream.writeBytes(2, this.items_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.type_;
            int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
            int i3 = 0;
            for (int i4 = 0; i4 < this.items_.size(); i4++) {
                i3 += CodedOutputStream.computeBytesSizeNoTag(this.items_.get(i4));
            }
            int size = computeInt32Size + i3 + getItemsList().size() + this.unknownFields.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof InventoryItems)) {
                return super.equals(obj);
            }
            InventoryItems inventoryItems = (InventoryItems) obj;
            return getType() == inventoryItems.getType() && getItemsList().equals(inventoryItems.getItemsList()) && this.unknownFields.equals(inventoryItems.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getType();
            if (getItemsCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getItemsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static InventoryItems parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static InventoryItems parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static InventoryItems parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static InventoryItems parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static InventoryItems parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static InventoryItems parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static InventoryItems parseFrom(InputStream inputStream) throws IOException {
            return (InventoryItems) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static InventoryItems parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InventoryItems) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static InventoryItems parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (InventoryItems) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static InventoryItems parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InventoryItems) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static InventoryItems parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (InventoryItems) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static InventoryItems parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InventoryItems) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(InventoryItems inventoryItems) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(inventoryItems);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements InventoryItemsOrBuilder {
            private int bitField0_;
            private List<ByteString> items_;
            private int type_;

            @Override
            public int getType() {
                return this.type_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return TronInventoryItems.internal_static_protocol_InventoryItems_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return TronInventoryItems.internal_static_protocol_InventoryItems_fieldAccessorTable.ensureFieldAccessorsInitialized(InventoryItems.class, Builder.class);
            }

            private Builder() {
                this.items_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.items_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = InventoryItems.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.type_ = 0;
                this.items_ = Collections.emptyList();
                this.bitField0_ &= -3;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return TronInventoryItems.internal_static_protocol_InventoryItems_descriptor;
            }

            @Override
            public InventoryItems getDefaultInstanceForType() {
                return InventoryItems.getDefaultInstance();
            }

            @Override
            public InventoryItems build() {
                InventoryItems buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public InventoryItems buildPartial() {
                InventoryItems inventoryItems = new InventoryItems(this);
                inventoryItems.type_ = this.type_;
                if ((this.bitField0_ & 2) == 2) {
                    this.items_ = Collections.unmodifiableList(this.items_);
                    this.bitField0_ &= -3;
                }
                inventoryItems.items_ = this.items_;
                inventoryItems.bitField0_ = 0;
                onBuilt();
                return inventoryItems;
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
                if (message instanceof InventoryItems) {
                    return mergeFrom((InventoryItems) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(InventoryItems inventoryItems) {
                if (inventoryItems == InventoryItems.getDefaultInstance()) {
                    return this;
                }
                if (inventoryItems.getType() != 0) {
                    setType(inventoryItems.getType());
                }
                if (!inventoryItems.items_.isEmpty()) {
                    if (this.items_.isEmpty()) {
                        this.items_ = inventoryItems.items_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureItemsIsMutable();
                        this.items_.addAll(inventoryItems.items_);
                    }
                    onChanged();
                }
                mergeUnknownFields(inventoryItems.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.TronInventoryItems.InventoryItems.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.TronInventoryItems.InventoryItems.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.TronInventoryItems$InventoryItems$Builder");
            }

            public Builder setType(int i) {
                this.type_ = i;
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.type_ = 0;
                onChanged();
                return this;
            }

            private void ensureItemsIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.items_ = new ArrayList(this.items_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<ByteString> getItemsList() {
                return Collections.unmodifiableList(this.items_);
            }

            @Override
            public int getItemsCount() {
                return this.items_.size();
            }

            @Override
            public ByteString getItems(int i) {
                return this.items_.get(i);
            }

            public Builder setItems(int i, ByteString byteString) {
                byteString.getClass();
                ensureItemsIsMutable();
                this.items_.set(i, byteString);
                onChanged();
                return this;
            }

            public Builder addItems(ByteString byteString) {
                byteString.getClass();
                ensureItemsIsMutable();
                this.items_.add(byteString);
                onChanged();
                return this;
            }

            public Builder addAllItems(Iterable<? extends ByteString> iterable) {
                ensureItemsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.items_);
                onChanged();
                return this;
            }

            public Builder clearItems() {
                this.items_ = Collections.emptyList();
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

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001dcore/TronInventoryItems.proto\u0012\bprotocol\"-\n\u000eInventoryItems\u0012\f\n\u0004type\u0018\u0001 \u0001(\u0005\u0012\r\n\u0005items\u0018\u0002 \u0003(\fBP\n\u000forg.tron.protosB\u0012TronInventoryItemsZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = TronInventoryItems.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_InventoryItems_descriptor = descriptor2;
        internal_static_protocol_InventoryItems_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Type", "Items"});
    }
}
