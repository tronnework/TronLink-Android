package org.tron.protos;

import com.google.common.net.HttpHeaders;
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
import com.tron.tron_base.frame.net.SignatureManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class Discover {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protocol_BackupMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_BackupMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_Endpoint_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_Endpoint_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_FindNeighbours_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_FindNeighbours_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_Neighbours_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_Neighbours_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_PingMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_PingMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protocol_PongMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protocol_PongMessage_fieldAccessorTable;

    public interface BackupMessageOrBuilder extends MessageOrBuilder {
        boolean getFlag();

        int getPriority();
    }

    public interface EndpointOrBuilder extends MessageOrBuilder {
        ByteString getAddress();

        ByteString getAddressIpv6();

        ByteString getNodeId();

        int getPort();
    }

    public interface FindNeighboursOrBuilder extends MessageOrBuilder {
        Endpoint getFrom();

        EndpointOrBuilder getFromOrBuilder();

        ByteString getTargetId();

        long getTimestamp();

        boolean hasFrom();
    }

    public interface NeighboursOrBuilder extends MessageOrBuilder {
        Endpoint getFrom();

        EndpointOrBuilder getFromOrBuilder();

        Endpoint getNeighbours(int i);

        int getNeighboursCount();

        List<Endpoint> getNeighboursList();

        EndpointOrBuilder getNeighboursOrBuilder(int i);

        List<? extends EndpointOrBuilder> getNeighboursOrBuilderList();

        long getTimestamp();

        boolean hasFrom();
    }

    public interface PingMessageOrBuilder extends MessageOrBuilder {
        Endpoint getFrom();

        EndpointOrBuilder getFromOrBuilder();

        long getTimestamp();

        Endpoint getTo();

        EndpointOrBuilder getToOrBuilder();

        int getVersion();

        boolean hasFrom();

        boolean hasTo();
    }

    public interface PongMessageOrBuilder extends MessageOrBuilder {
        int getEcho();

        Endpoint getFrom();

        EndpointOrBuilder getFromOrBuilder();

        long getTimestamp();

        boolean hasFrom();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Discover() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class Endpoint extends GeneratedMessageV3 implements EndpointOrBuilder {
        public static final int ADDRESSIPV6_FIELD_NUMBER = 4;
        public static final int ADDRESS_FIELD_NUMBER = 1;
        public static final int NODEID_FIELD_NUMBER = 3;
        public static final int PORT_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private ByteString addressIpv6_;
        private ByteString address_;
        private byte memoizedIsInitialized;
        private ByteString nodeId_;
        private int port_;
        private static final Endpoint DEFAULT_INSTANCE = new Endpoint();
        private static final Parser<Endpoint> PARSER = new AbstractParser<Endpoint>() {
            @Override
            public Endpoint parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Endpoint(codedInputStream, extensionRegistryLite);
            }
        };

        public static Endpoint getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Endpoint> parser() {
            return PARSER;
        }

        @Override
        public ByteString getAddress() {
            return this.address_;
        }

        @Override
        public ByteString getAddressIpv6() {
            return this.addressIpv6_;
        }

        @Override
        public Endpoint getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public ByteString getNodeId() {
            return this.nodeId_;
        }

        @Override
        public Parser<Endpoint> getParserForType() {
            return PARSER;
        }

        @Override
        public int getPort() {
            return this.port_;
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

        private Endpoint(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Endpoint() {
            this.memoizedIsInitialized = (byte) -1;
            this.address_ = ByteString.EMPTY;
            this.port_ = 0;
            this.nodeId_ = ByteString.EMPTY;
            this.addressIpv6_ = ByteString.EMPTY;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Endpoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.address_ = codedInputStream.readBytes();
                            } else if (readTag == 16) {
                                this.port_ = codedInputStream.readInt32();
                            } else if (readTag == 26) {
                                this.nodeId_ = codedInputStream.readBytes();
                            } else if (readTag != 34) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.addressIpv6_ = codedInputStream.readBytes();
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
            return Discover.internal_static_protocol_Endpoint_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Discover.internal_static_protocol_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.address_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.address_);
            }
            int i = this.port_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            if (!this.nodeId_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.nodeId_);
            }
            if (!this.addressIpv6_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.addressIpv6_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = !this.address_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.address_) : 0;
            int i2 = this.port_;
            if (i2 != 0) {
                computeBytesSize += CodedOutputStream.computeInt32Size(2, i2);
            }
            if (!this.nodeId_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(3, this.nodeId_);
            }
            if (!this.addressIpv6_.isEmpty()) {
                computeBytesSize += CodedOutputStream.computeBytesSize(4, this.addressIpv6_);
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
            if (!(obj instanceof Endpoint)) {
                return super.equals(obj);
            }
            Endpoint endpoint = (Endpoint) obj;
            return getAddress().equals(endpoint.getAddress()) && getPort() == endpoint.getPort() && getNodeId().equals(endpoint.getNodeId()) && getAddressIpv6().equals(endpoint.getAddressIpv6()) && this.unknownFields.equals(endpoint.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAddress().hashCode()) * 37) + 2) * 53) + getPort()) * 37) + 3) * 53) + getNodeId().hashCode()) * 37) + 4) * 53) + getAddressIpv6().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Endpoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Endpoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Endpoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Endpoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Endpoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Endpoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Endpoint parseFrom(InputStream inputStream) throws IOException {
            return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Endpoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Endpoint parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Endpoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Endpoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Endpoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Endpoint parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Endpoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Endpoint endpoint) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(endpoint);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EndpointOrBuilder {
            private ByteString addressIpv6_;
            private ByteString address_;
            private ByteString nodeId_;
            private int port_;

            @Override
            public ByteString getAddress() {
                return this.address_;
            }

            @Override
            public ByteString getAddressIpv6() {
                return this.addressIpv6_;
            }

            @Override
            public ByteString getNodeId() {
                return this.nodeId_;
            }

            @Override
            public int getPort() {
                return this.port_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Discover.internal_static_protocol_Endpoint_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Discover.internal_static_protocol_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
            }

            private Builder() {
                this.address_ = ByteString.EMPTY;
                this.nodeId_ = ByteString.EMPTY;
                this.addressIpv6_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.address_ = ByteString.EMPTY;
                this.nodeId_ = ByteString.EMPTY;
                this.addressIpv6_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Endpoint.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.address_ = ByteString.EMPTY;
                this.port_ = 0;
                this.nodeId_ = ByteString.EMPTY;
                this.addressIpv6_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Discover.internal_static_protocol_Endpoint_descriptor;
            }

            @Override
            public Endpoint getDefaultInstanceForType() {
                return Endpoint.getDefaultInstance();
            }

            @Override
            public Endpoint build() {
                Endpoint buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public Endpoint buildPartial() {
                Endpoint endpoint = new Endpoint(this);
                endpoint.address_ = this.address_;
                endpoint.port_ = this.port_;
                endpoint.nodeId_ = this.nodeId_;
                endpoint.addressIpv6_ = this.addressIpv6_;
                onBuilt();
                return endpoint;
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
                if (message instanceof Endpoint) {
                    return mergeFrom((Endpoint) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Endpoint endpoint) {
                if (endpoint == Endpoint.getDefaultInstance()) {
                    return this;
                }
                if (endpoint.getAddress() != ByteString.EMPTY) {
                    setAddress(endpoint.getAddress());
                }
                if (endpoint.getPort() != 0) {
                    setPort(endpoint.getPort());
                }
                if (endpoint.getNodeId() != ByteString.EMPTY) {
                    setNodeId(endpoint.getNodeId());
                }
                if (endpoint.getAddressIpv6() != ByteString.EMPTY) {
                    setAddressIpv6(endpoint.getAddressIpv6());
                }
                mergeUnknownFields(endpoint.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.Discover.Endpoint.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.Discover.Endpoint.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.Discover$Endpoint$Builder");
            }

            public Builder setAddress(ByteString byteString) {
                byteString.getClass();
                this.address_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAddress() {
                this.address_ = Endpoint.getDefaultInstance().getAddress();
                onChanged();
                return this;
            }

            public Builder setPort(int i) {
                this.port_ = i;
                onChanged();
                return this;
            }

            public Builder clearPort() {
                this.port_ = 0;
                onChanged();
                return this;
            }

            public Builder setNodeId(ByteString byteString) {
                byteString.getClass();
                this.nodeId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearNodeId() {
                this.nodeId_ = Endpoint.getDefaultInstance().getNodeId();
                onChanged();
                return this;
            }

            public Builder setAddressIpv6(ByteString byteString) {
                byteString.getClass();
                this.addressIpv6_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAddressIpv6() {
                this.addressIpv6_ = Endpoint.getDefaultInstance().getAddressIpv6();
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

    public static final class PingMessage extends GeneratedMessageV3 implements PingMessageOrBuilder {
        public static final int FROM_FIELD_NUMBER = 1;
        public static final int TIMESTAMP_FIELD_NUMBER = 4;
        public static final int TO_FIELD_NUMBER = 2;
        public static final int VERSION_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private Endpoint from_;
        private byte memoizedIsInitialized;
        private long timestamp_;
        private Endpoint to_;
        private int version_;
        private static final PingMessage DEFAULT_INSTANCE = new PingMessage();
        private static final Parser<PingMessage> PARSER = new AbstractParser<PingMessage>() {
            @Override
            public PingMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PingMessage(codedInputStream, extensionRegistryLite);
            }
        };

        public static PingMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PingMessage> parser() {
            return PARSER;
        }

        @Override
        public PingMessage getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<PingMessage> getParserForType() {
            return PARSER;
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }

        @Override
        public int getVersion() {
            return this.version_;
        }

        @Override
        public boolean hasFrom() {
            return this.from_ != null;
        }

        @Override
        public boolean hasTo() {
            return this.to_ != null;
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

        private PingMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private PingMessage() {
            this.memoizedIsInitialized = (byte) -1;
            this.version_ = 0;
            this.timestamp_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PingMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            Endpoint.Builder builder;
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
                                    Endpoint endpoint = this.from_;
                                    builder = endpoint != null ? endpoint.toBuilder() : null;
                                    Endpoint endpoint2 = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                                    this.from_ = endpoint2;
                                    if (builder != null) {
                                        builder.mergeFrom(endpoint2);
                                        this.from_ = builder.buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    Endpoint endpoint3 = this.to_;
                                    builder = endpoint3 != null ? endpoint3.toBuilder() : null;
                                    Endpoint endpoint4 = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                                    this.to_ = endpoint4;
                                    if (builder != null) {
                                        builder.mergeFrom(endpoint4);
                                        this.to_ = builder.buildPartial();
                                    }
                                } else if (readTag == 24) {
                                    this.version_ = codedInputStream.readInt32();
                                } else if (readTag != 32) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.timestamp_ = codedInputStream.readInt64();
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
            return Discover.internal_static_protocol_PingMessage_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Discover.internal_static_protocol_PingMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(PingMessage.class, Builder.class);
        }

        @Override
        public Endpoint getFrom() {
            Endpoint endpoint = this.from_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        @Override
        public EndpointOrBuilder getFromOrBuilder() {
            return getFrom();
        }

        @Override
        public Endpoint getTo() {
            Endpoint endpoint = this.to_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        @Override
        public EndpointOrBuilder getToOrBuilder() {
            return getTo();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.from_ != null) {
                codedOutputStream.writeMessage(1, getFrom());
            }
            if (this.to_ != null) {
                codedOutputStream.writeMessage(2, getTo());
            }
            int i = this.version_;
            if (i != 0) {
                codedOutputStream.writeInt32(3, i);
            }
            long j = this.timestamp_;
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
            int computeMessageSize = this.from_ != null ? CodedOutputStream.computeMessageSize(1, getFrom()) : 0;
            if (this.to_ != null) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, getTo());
            }
            int i2 = this.version_;
            if (i2 != 0) {
                computeMessageSize += CodedOutputStream.computeInt32Size(3, i2);
            }
            long j = this.timestamp_;
            if (j != 0) {
                computeMessageSize += CodedOutputStream.computeInt64Size(4, j);
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
            if (!(obj instanceof PingMessage)) {
                return super.equals(obj);
            }
            PingMessage pingMessage = (PingMessage) obj;
            boolean z2 = hasFrom() == pingMessage.hasFrom();
            if (!hasFrom() ? z2 : !(!z2 || !getFrom().equals(pingMessage.getFrom()))) {
                if (hasTo() == pingMessage.hasTo()) {
                    z = true;
                    if (hasTo() ? z : !(!z || !getTo().equals(pingMessage.getTo()))) {
                        if (getVersion() != pingMessage.getVersion() && getTimestamp() == pingMessage.getTimestamp() && this.unknownFields.equals(pingMessage.unknownFields)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            z = false;
            if (hasTo()) {
                return false;
            }
            if (getVersion() != pingMessage.getVersion()) {
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasFrom()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getFrom().hashCode();
            }
            if (hasTo()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getTo().hashCode();
            }
            int version = (((((((((hashCode * 37) + 3) * 53) + getVersion()) * 37) + 4) * 53) + Internal.hashLong(getTimestamp())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = version;
            return version;
        }

        public static PingMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PingMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PingMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PingMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PingMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PingMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PingMessage parseFrom(InputStream inputStream) throws IOException {
            return (PingMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PingMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PingMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PingMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PingMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PingMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PingMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PingMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PingMessage pingMessage) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pingMessage);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PingMessageOrBuilder {
            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> fromBuilder_;
            private Endpoint from_;
            private long timestamp_;
            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> toBuilder_;
            private Endpoint to_;
            private int version_;

            @Override
            public long getTimestamp() {
                return this.timestamp_;
            }

            @Override
            public int getVersion() {
                return this.version_;
            }

            @Override
            public boolean hasFrom() {
                return (this.fromBuilder_ == null && this.from_ == null) ? false : true;
            }

            @Override
            public boolean hasTo() {
                return (this.toBuilder_ == null && this.to_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Discover.internal_static_protocol_PingMessage_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Discover.internal_static_protocol_PingMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(PingMessage.class, Builder.class);
            }

            private Builder() {
                this.from_ = null;
                this.to_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.from_ = null;
                this.to_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PingMessage.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.fromBuilder_ == null) {
                    this.from_ = null;
                } else {
                    this.from_ = null;
                    this.fromBuilder_ = null;
                }
                if (this.toBuilder_ == null) {
                    this.to_ = null;
                } else {
                    this.to_ = null;
                    this.toBuilder_ = null;
                }
                this.version_ = 0;
                this.timestamp_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Discover.internal_static_protocol_PingMessage_descriptor;
            }

            @Override
            public PingMessage getDefaultInstanceForType() {
                return PingMessage.getDefaultInstance();
            }

            @Override
            public PingMessage build() {
                PingMessage buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PingMessage buildPartial() {
                PingMessage pingMessage = new PingMessage(this);
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    pingMessage.from_ = this.from_;
                } else {
                    pingMessage.from_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV32 = this.toBuilder_;
                if (singleFieldBuilderV32 == null) {
                    pingMessage.to_ = this.to_;
                } else {
                    pingMessage.to_ = singleFieldBuilderV32.build();
                }
                pingMessage.version_ = this.version_;
                pingMessage.timestamp_ = this.timestamp_;
                onBuilt();
                return pingMessage;
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
                if (message instanceof PingMessage) {
                    return mergeFrom((PingMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PingMessage pingMessage) {
                if (pingMessage == PingMessage.getDefaultInstance()) {
                    return this;
                }
                if (pingMessage.hasFrom()) {
                    mergeFrom(pingMessage.getFrom());
                }
                if (pingMessage.hasTo()) {
                    mergeTo(pingMessage.getTo());
                }
                if (pingMessage.getVersion() != 0) {
                    setVersion(pingMessage.getVersion());
                }
                if (pingMessage.getTimestamp() != 0) {
                    setTimestamp(pingMessage.getTimestamp());
                }
                mergeUnknownFields(pingMessage.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.Discover.PingMessage.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.Discover.PingMessage.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.Discover$PingMessage$Builder");
            }

            @Override
            public Endpoint getFrom() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint = this.from_;
                    return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setFrom(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    endpoint.getClass();
                    this.from_ = endpoint;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(endpoint);
                }
                return this;
            }

            public Builder setFrom(Endpoint.Builder builder) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.from_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeFrom(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint2 = this.from_;
                    if (endpoint2 != null) {
                        this.from_ = Endpoint.newBuilder(endpoint2).mergeFrom(endpoint).buildPartial();
                    } else {
                        this.from_ = endpoint;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(endpoint);
                }
                return this;
            }

            public Builder clearFrom() {
                if (this.fromBuilder_ == null) {
                    this.from_ = null;
                    onChanged();
                } else {
                    this.from_ = null;
                    this.fromBuilder_ = null;
                }
                return this;
            }

            public Endpoint.Builder getFromBuilder() {
                onChanged();
                return getFromFieldBuilder().getBuilder();
            }

            @Override
            public EndpointOrBuilder getFromOrBuilder() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Endpoint endpoint = this.from_;
                return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
            }

            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getFromFieldBuilder() {
                if (this.fromBuilder_ == null) {
                    this.fromBuilder_ = new SingleFieldBuilderV3<>(getFrom(), getParentForChildren(), isClean());
                    this.from_ = null;
                }
                return this.fromBuilder_;
            }

            @Override
            public Endpoint getTo() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.toBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint = this.to_;
                    return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setTo(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.toBuilder_;
                if (singleFieldBuilderV3 == null) {
                    endpoint.getClass();
                    this.to_ = endpoint;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(endpoint);
                }
                return this;
            }

            public Builder setTo(Endpoint.Builder builder) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.toBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.to_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTo(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.toBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint2 = this.to_;
                    if (endpoint2 != null) {
                        this.to_ = Endpoint.newBuilder(endpoint2).mergeFrom(endpoint).buildPartial();
                    } else {
                        this.to_ = endpoint;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(endpoint);
                }
                return this;
            }

            public Builder clearTo() {
                if (this.toBuilder_ == null) {
                    this.to_ = null;
                    onChanged();
                } else {
                    this.to_ = null;
                    this.toBuilder_ = null;
                }
                return this;
            }

            public Endpoint.Builder getToBuilder() {
                onChanged();
                return getToFieldBuilder().getBuilder();
            }

            @Override
            public EndpointOrBuilder getToOrBuilder() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.toBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Endpoint endpoint = this.to_;
                return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
            }

            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getToFieldBuilder() {
                if (this.toBuilder_ == null) {
                    this.toBuilder_ = new SingleFieldBuilderV3<>(getTo(), getParentForChildren(), isClean());
                    this.to_ = null;
                }
                return this.toBuilder_;
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

    public static final class PongMessage extends GeneratedMessageV3 implements PongMessageOrBuilder {
        public static final int ECHO_FIELD_NUMBER = 2;
        public static final int FROM_FIELD_NUMBER = 1;
        public static final int TIMESTAMP_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private int echo_;
        private Endpoint from_;
        private byte memoizedIsInitialized;
        private long timestamp_;
        private static final PongMessage DEFAULT_INSTANCE = new PongMessage();
        private static final Parser<PongMessage> PARSER = new AbstractParser<PongMessage>() {
            @Override
            public PongMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PongMessage(codedInputStream, extensionRegistryLite);
            }
        };

        public static PongMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PongMessage> parser() {
            return PARSER;
        }

        @Override
        public PongMessage getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public int getEcho() {
            return this.echo_;
        }

        @Override
        public Parser<PongMessage> getParserForType() {
            return PARSER;
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }

        @Override
        public boolean hasFrom() {
            return this.from_ != null;
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

        private PongMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private PongMessage() {
            this.memoizedIsInitialized = (byte) -1;
            this.echo_ = 0;
            this.timestamp_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PongMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Endpoint endpoint = this.from_;
                                Endpoint.Builder builder = endpoint != null ? endpoint.toBuilder() : null;
                                Endpoint endpoint2 = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                                this.from_ = endpoint2;
                                if (builder != null) {
                                    builder.mergeFrom(endpoint2);
                                    this.from_ = builder.buildPartial();
                                }
                            } else if (readTag == 16) {
                                this.echo_ = codedInputStream.readInt32();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.timestamp_ = codedInputStream.readInt64();
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
            return Discover.internal_static_protocol_PongMessage_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Discover.internal_static_protocol_PongMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(PongMessage.class, Builder.class);
        }

        @Override
        public Endpoint getFrom() {
            Endpoint endpoint = this.from_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        @Override
        public EndpointOrBuilder getFromOrBuilder() {
            return getFrom();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.from_ != null) {
                codedOutputStream.writeMessage(1, getFrom());
            }
            int i = this.echo_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            long j = this.timestamp_;
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
            int computeMessageSize = this.from_ != null ? CodedOutputStream.computeMessageSize(1, getFrom()) : 0;
            int i2 = this.echo_;
            if (i2 != 0) {
                computeMessageSize += CodedOutputStream.computeInt32Size(2, i2);
            }
            long j = this.timestamp_;
            if (j != 0) {
                computeMessageSize += CodedOutputStream.computeInt64Size(3, j);
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
            if (!(obj instanceof PongMessage)) {
                return super.equals(obj);
            }
            PongMessage pongMessage = (PongMessage) obj;
            boolean z = hasFrom() == pongMessage.hasFrom();
            if (!hasFrom() ? z : !(!z || !getFrom().equals(pongMessage.getFrom()))) {
                if (getEcho() == pongMessage.getEcho() && getTimestamp() == pongMessage.getTimestamp() && this.unknownFields.equals(pongMessage.unknownFields)) {
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
            if (hasFrom()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getFrom().hashCode();
            }
            int echo = (((((((((hashCode * 37) + 2) * 53) + getEcho()) * 37) + 3) * 53) + Internal.hashLong(getTimestamp())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = echo;
            return echo;
        }

        public static PongMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PongMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PongMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PongMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PongMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PongMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PongMessage parseFrom(InputStream inputStream) throws IOException {
            return (PongMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PongMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PongMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PongMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PongMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PongMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PongMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PongMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PongMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PongMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PongMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PongMessage pongMessage) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pongMessage);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PongMessageOrBuilder {
            private int echo_;
            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> fromBuilder_;
            private Endpoint from_;
            private long timestamp_;

            @Override
            public int getEcho() {
                return this.echo_;
            }

            @Override
            public long getTimestamp() {
                return this.timestamp_;
            }

            @Override
            public boolean hasFrom() {
                return (this.fromBuilder_ == null && this.from_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Discover.internal_static_protocol_PongMessage_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Discover.internal_static_protocol_PongMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(PongMessage.class, Builder.class);
            }

            private Builder() {
                this.from_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.from_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PongMessage.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.fromBuilder_ == null) {
                    this.from_ = null;
                } else {
                    this.from_ = null;
                    this.fromBuilder_ = null;
                }
                this.echo_ = 0;
                this.timestamp_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Discover.internal_static_protocol_PongMessage_descriptor;
            }

            @Override
            public PongMessage getDefaultInstanceForType() {
                return PongMessage.getDefaultInstance();
            }

            @Override
            public PongMessage build() {
                PongMessage buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PongMessage buildPartial() {
                PongMessage pongMessage = new PongMessage(this);
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    pongMessage.from_ = this.from_;
                } else {
                    pongMessage.from_ = singleFieldBuilderV3.build();
                }
                pongMessage.echo_ = this.echo_;
                pongMessage.timestamp_ = this.timestamp_;
                onBuilt();
                return pongMessage;
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
                if (message instanceof PongMessage) {
                    return mergeFrom((PongMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PongMessage pongMessage) {
                if (pongMessage == PongMessage.getDefaultInstance()) {
                    return this;
                }
                if (pongMessage.hasFrom()) {
                    mergeFrom(pongMessage.getFrom());
                }
                if (pongMessage.getEcho() != 0) {
                    setEcho(pongMessage.getEcho());
                }
                if (pongMessage.getTimestamp() != 0) {
                    setTimestamp(pongMessage.getTimestamp());
                }
                mergeUnknownFields(pongMessage.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.Discover.PongMessage.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.Discover.PongMessage.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.Discover$PongMessage$Builder");
            }

            @Override
            public Endpoint getFrom() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint = this.from_;
                    return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setFrom(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    endpoint.getClass();
                    this.from_ = endpoint;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(endpoint);
                }
                return this;
            }

            public Builder setFrom(Endpoint.Builder builder) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.from_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeFrom(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint2 = this.from_;
                    if (endpoint2 != null) {
                        this.from_ = Endpoint.newBuilder(endpoint2).mergeFrom(endpoint).buildPartial();
                    } else {
                        this.from_ = endpoint;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(endpoint);
                }
                return this;
            }

            public Builder clearFrom() {
                if (this.fromBuilder_ == null) {
                    this.from_ = null;
                    onChanged();
                } else {
                    this.from_ = null;
                    this.fromBuilder_ = null;
                }
                return this;
            }

            public Endpoint.Builder getFromBuilder() {
                onChanged();
                return getFromFieldBuilder().getBuilder();
            }

            @Override
            public EndpointOrBuilder getFromOrBuilder() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Endpoint endpoint = this.from_;
                return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
            }

            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getFromFieldBuilder() {
                if (this.fromBuilder_ == null) {
                    this.fromBuilder_ = new SingleFieldBuilderV3<>(getFrom(), getParentForChildren(), isClean());
                    this.from_ = null;
                }
                return this.fromBuilder_;
            }

            public Builder setEcho(int i) {
                this.echo_ = i;
                onChanged();
                return this;
            }

            public Builder clearEcho() {
                this.echo_ = 0;
                onChanged();
                return this;
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

    public static final class FindNeighbours extends GeneratedMessageV3 implements FindNeighboursOrBuilder {
        public static final int FROM_FIELD_NUMBER = 1;
        public static final int TARGETID_FIELD_NUMBER = 2;
        public static final int TIMESTAMP_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private Endpoint from_;
        private byte memoizedIsInitialized;
        private ByteString targetId_;
        private long timestamp_;
        private static final FindNeighbours DEFAULT_INSTANCE = new FindNeighbours();
        private static final Parser<FindNeighbours> PARSER = new AbstractParser<FindNeighbours>() {
            @Override
            public FindNeighbours parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new FindNeighbours(codedInputStream, extensionRegistryLite);
            }
        };

        public static FindNeighbours getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FindNeighbours> parser() {
            return PARSER;
        }

        @Override
        public FindNeighbours getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public Parser<FindNeighbours> getParserForType() {
            return PARSER;
        }

        @Override
        public ByteString getTargetId() {
            return this.targetId_;
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }

        @Override
        public boolean hasFrom() {
            return this.from_ != null;
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

        private FindNeighbours(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private FindNeighbours() {
            this.memoizedIsInitialized = (byte) -1;
            this.targetId_ = ByteString.EMPTY;
            this.timestamp_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FindNeighbours(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Endpoint endpoint = this.from_;
                                Endpoint.Builder builder = endpoint != null ? endpoint.toBuilder() : null;
                                Endpoint endpoint2 = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                                this.from_ = endpoint2;
                                if (builder != null) {
                                    builder.mergeFrom(endpoint2);
                                    this.from_ = builder.buildPartial();
                                }
                            } else if (readTag == 18) {
                                this.targetId_ = codedInputStream.readBytes();
                            } else if (readTag != 24) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.timestamp_ = codedInputStream.readInt64();
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
            return Discover.internal_static_protocol_FindNeighbours_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Discover.internal_static_protocol_FindNeighbours_fieldAccessorTable.ensureFieldAccessorsInitialized(FindNeighbours.class, Builder.class);
        }

        @Override
        public Endpoint getFrom() {
            Endpoint endpoint = this.from_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        @Override
        public EndpointOrBuilder getFromOrBuilder() {
            return getFrom();
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.from_ != null) {
                codedOutputStream.writeMessage(1, getFrom());
            }
            if (!this.targetId_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.targetId_);
            }
            long j = this.timestamp_;
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
            int computeMessageSize = this.from_ != null ? CodedOutputStream.computeMessageSize(1, getFrom()) : 0;
            if (!this.targetId_.isEmpty()) {
                computeMessageSize += CodedOutputStream.computeBytesSize(2, this.targetId_);
            }
            long j = this.timestamp_;
            if (j != 0) {
                computeMessageSize += CodedOutputStream.computeInt64Size(3, j);
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
            if (!(obj instanceof FindNeighbours)) {
                return super.equals(obj);
            }
            FindNeighbours findNeighbours = (FindNeighbours) obj;
            boolean z = hasFrom() == findNeighbours.hasFrom();
            if (!hasFrom() ? z : !(!z || !getFrom().equals(findNeighbours.getFrom()))) {
                if (getTargetId().equals(findNeighbours.getTargetId()) && getTimestamp() == findNeighbours.getTimestamp() && this.unknownFields.equals(findNeighbours.unknownFields)) {
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
            if (hasFrom()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getFrom().hashCode();
            }
            int hashCode2 = (((((((((hashCode * 37) + 2) * 53) + getTargetId().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getTimestamp())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static FindNeighbours parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static FindNeighbours parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static FindNeighbours parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static FindNeighbours parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FindNeighbours parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static FindNeighbours parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static FindNeighbours parseFrom(InputStream inputStream) throws IOException {
            return (FindNeighbours) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static FindNeighbours parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FindNeighbours) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FindNeighbours parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FindNeighbours) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static FindNeighbours parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FindNeighbours) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FindNeighbours parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FindNeighbours) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static FindNeighbours parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FindNeighbours) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FindNeighbours findNeighbours) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(findNeighbours);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FindNeighboursOrBuilder {
            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> fromBuilder_;
            private Endpoint from_;
            private ByteString targetId_;
            private long timestamp_;

            @Override
            public ByteString getTargetId() {
                return this.targetId_;
            }

            @Override
            public long getTimestamp() {
                return this.timestamp_;
            }

            @Override
            public boolean hasFrom() {
                return (this.fromBuilder_ == null && this.from_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Discover.internal_static_protocol_FindNeighbours_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Discover.internal_static_protocol_FindNeighbours_fieldAccessorTable.ensureFieldAccessorsInitialized(FindNeighbours.class, Builder.class);
            }

            private Builder() {
                this.from_ = null;
                this.targetId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.from_ = null;
                this.targetId_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = FindNeighbours.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.fromBuilder_ == null) {
                    this.from_ = null;
                } else {
                    this.from_ = null;
                    this.fromBuilder_ = null;
                }
                this.targetId_ = ByteString.EMPTY;
                this.timestamp_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Discover.internal_static_protocol_FindNeighbours_descriptor;
            }

            @Override
            public FindNeighbours getDefaultInstanceForType() {
                return FindNeighbours.getDefaultInstance();
            }

            @Override
            public FindNeighbours build() {
                FindNeighbours buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public FindNeighbours buildPartial() {
                FindNeighbours findNeighbours = new FindNeighbours(this);
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    findNeighbours.from_ = this.from_;
                } else {
                    findNeighbours.from_ = singleFieldBuilderV3.build();
                }
                findNeighbours.targetId_ = this.targetId_;
                findNeighbours.timestamp_ = this.timestamp_;
                onBuilt();
                return findNeighbours;
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
                if (message instanceof FindNeighbours) {
                    return mergeFrom((FindNeighbours) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(FindNeighbours findNeighbours) {
                if (findNeighbours == FindNeighbours.getDefaultInstance()) {
                    return this;
                }
                if (findNeighbours.hasFrom()) {
                    mergeFrom(findNeighbours.getFrom());
                }
                if (findNeighbours.getTargetId() != ByteString.EMPTY) {
                    setTargetId(findNeighbours.getTargetId());
                }
                if (findNeighbours.getTimestamp() != 0) {
                    setTimestamp(findNeighbours.getTimestamp());
                }
                mergeUnknownFields(findNeighbours.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.Discover.FindNeighbours.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.Discover.FindNeighbours.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.Discover$FindNeighbours$Builder");
            }

            @Override
            public Endpoint getFrom() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint = this.from_;
                    return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setFrom(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    endpoint.getClass();
                    this.from_ = endpoint;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(endpoint);
                }
                return this;
            }

            public Builder setFrom(Endpoint.Builder builder) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.from_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeFrom(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint2 = this.from_;
                    if (endpoint2 != null) {
                        this.from_ = Endpoint.newBuilder(endpoint2).mergeFrom(endpoint).buildPartial();
                    } else {
                        this.from_ = endpoint;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(endpoint);
                }
                return this;
            }

            public Builder clearFrom() {
                if (this.fromBuilder_ == null) {
                    this.from_ = null;
                    onChanged();
                } else {
                    this.from_ = null;
                    this.fromBuilder_ = null;
                }
                return this;
            }

            public Endpoint.Builder getFromBuilder() {
                onChanged();
                return getFromFieldBuilder().getBuilder();
            }

            @Override
            public EndpointOrBuilder getFromOrBuilder() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Endpoint endpoint = this.from_;
                return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
            }

            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getFromFieldBuilder() {
                if (this.fromBuilder_ == null) {
                    this.fromBuilder_ = new SingleFieldBuilderV3<>(getFrom(), getParentForChildren(), isClean());
                    this.from_ = null;
                }
                return this.fromBuilder_;
            }

            public Builder setTargetId(ByteString byteString) {
                byteString.getClass();
                this.targetId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearTargetId() {
                this.targetId_ = FindNeighbours.getDefaultInstance().getTargetId();
                onChanged();
                return this;
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

    public static final class Neighbours extends GeneratedMessageV3 implements NeighboursOrBuilder {
        public static final int FROM_FIELD_NUMBER = 1;
        public static final int NEIGHBOURS_FIELD_NUMBER = 2;
        public static final int TIMESTAMP_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private Endpoint from_;
        private byte memoizedIsInitialized;
        private List<Endpoint> neighbours_;
        private long timestamp_;
        private static final Neighbours DEFAULT_INSTANCE = new Neighbours();
        private static final Parser<Neighbours> PARSER = new AbstractParser<Neighbours>() {
            @Override
            public Neighbours parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Neighbours(codedInputStream, extensionRegistryLite);
            }
        };

        public static Neighbours getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Neighbours> parser() {
            return PARSER;
        }

        @Override
        public Neighbours getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public List<Endpoint> getNeighboursList() {
            return this.neighbours_;
        }

        @Override
        public List<? extends EndpointOrBuilder> getNeighboursOrBuilderList() {
            return this.neighbours_;
        }

        @Override
        public Parser<Neighbours> getParserForType() {
            return PARSER;
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }

        @Override
        public boolean hasFrom() {
            return this.from_ != null;
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

        private Neighbours(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Neighbours() {
            this.memoizedIsInitialized = (byte) -1;
            this.neighbours_ = Collections.emptyList();
            this.timestamp_ = 0L;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Neighbours(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    Endpoint endpoint = this.from_;
                                    Endpoint.Builder builder = endpoint != null ? endpoint.toBuilder() : null;
                                    Endpoint endpoint2 = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                                    this.from_ = endpoint2;
                                    if (builder != null) {
                                        builder.mergeFrom(endpoint2);
                                        this.from_ = builder.buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    if (!(z2 & true)) {
                                        this.neighbours_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.neighbours_.add((Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite));
                                } else if (readTag != 24) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.timestamp_ = codedInputStream.readInt64();
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
                        this.neighbours_ = Collections.unmodifiableList(this.neighbours_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Discover.internal_static_protocol_Neighbours_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Discover.internal_static_protocol_Neighbours_fieldAccessorTable.ensureFieldAccessorsInitialized(Neighbours.class, Builder.class);
        }

        @Override
        public Endpoint getFrom() {
            Endpoint endpoint = this.from_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        @Override
        public EndpointOrBuilder getFromOrBuilder() {
            return getFrom();
        }

        @Override
        public int getNeighboursCount() {
            return this.neighbours_.size();
        }

        @Override
        public Endpoint getNeighbours(int i) {
            return this.neighbours_.get(i);
        }

        @Override
        public EndpointOrBuilder getNeighboursOrBuilder(int i) {
            return this.neighbours_.get(i);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.from_ != null) {
                codedOutputStream.writeMessage(1, getFrom());
            }
            for (int i = 0; i < this.neighbours_.size(); i++) {
                codedOutputStream.writeMessage(2, this.neighbours_.get(i));
            }
            long j = this.timestamp_;
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
            int computeMessageSize = this.from_ != null ? CodedOutputStream.computeMessageSize(1, getFrom()) : 0;
            for (int i2 = 0; i2 < this.neighbours_.size(); i2++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, this.neighbours_.get(i2));
            }
            long j = this.timestamp_;
            if (j != 0) {
                computeMessageSize += CodedOutputStream.computeInt64Size(3, j);
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
            if (!(obj instanceof Neighbours)) {
                return super.equals(obj);
            }
            Neighbours neighbours = (Neighbours) obj;
            boolean z = hasFrom() == neighbours.hasFrom();
            if (!hasFrom() ? z : !(!z || !getFrom().equals(neighbours.getFrom()))) {
                if (getNeighboursList().equals(neighbours.getNeighboursList()) && getTimestamp() == neighbours.getTimestamp() && this.unknownFields.equals(neighbours.unknownFields)) {
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
            if (hasFrom()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getFrom().hashCode();
            }
            if (getNeighboursCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getNeighboursList().hashCode();
            }
            int hashLong = (((((hashCode * 37) + 3) * 53) + Internal.hashLong(getTimestamp())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashLong;
            return hashLong;
        }

        public static Neighbours parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Neighbours parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Neighbours parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Neighbours parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Neighbours parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Neighbours parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Neighbours parseFrom(InputStream inputStream) throws IOException {
            return (Neighbours) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Neighbours parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Neighbours) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Neighbours parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Neighbours) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Neighbours parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Neighbours) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Neighbours parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Neighbours) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Neighbours parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Neighbours) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Neighbours neighbours) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(neighbours);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements NeighboursOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> fromBuilder_;
            private Endpoint from_;
            private RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> neighboursBuilder_;
            private List<Endpoint> neighbours_;
            private long timestamp_;

            @Override
            public long getTimestamp() {
                return this.timestamp_;
            }

            @Override
            public boolean hasFrom() {
                return (this.fromBuilder_ == null && this.from_ == null) ? false : true;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Discover.internal_static_protocol_Neighbours_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Discover.internal_static_protocol_Neighbours_fieldAccessorTable.ensureFieldAccessorsInitialized(Neighbours.class, Builder.class);
            }

            private Builder() {
                this.from_ = null;
                this.neighbours_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.from_ = null;
                this.neighbours_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (Neighbours.alwaysUseFieldBuilders) {
                    getNeighboursFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.fromBuilder_ == null) {
                    this.from_ = null;
                } else {
                    this.from_ = null;
                    this.fromBuilder_ = null;
                }
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.neighbours_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.timestamp_ = 0L;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Discover.internal_static_protocol_Neighbours_descriptor;
            }

            @Override
            public Neighbours getDefaultInstanceForType() {
                return Neighbours.getDefaultInstance();
            }

            @Override
            public Neighbours build() {
                Neighbours buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public Neighbours buildPartial() {
                Neighbours neighbours = new Neighbours(this);
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    neighbours.from_ = this.from_;
                } else {
                    neighbours.from_ = singleFieldBuilderV3.build();
                }
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.neighbours_ = Collections.unmodifiableList(this.neighbours_);
                        this.bitField0_ &= -3;
                    }
                    neighbours.neighbours_ = this.neighbours_;
                } else {
                    neighbours.neighbours_ = repeatedFieldBuilderV3.build();
                }
                neighbours.timestamp_ = this.timestamp_;
                neighbours.bitField0_ = 0;
                onBuilt();
                return neighbours;
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
                if (message instanceof Neighbours) {
                    return mergeFrom((Neighbours) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Neighbours neighbours) {
                if (neighbours == Neighbours.getDefaultInstance()) {
                    return this;
                }
                if (neighbours.hasFrom()) {
                    mergeFrom(neighbours.getFrom());
                }
                if (this.neighboursBuilder_ == null) {
                    if (!neighbours.neighbours_.isEmpty()) {
                        if (this.neighbours_.isEmpty()) {
                            this.neighbours_ = neighbours.neighbours_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureNeighboursIsMutable();
                            this.neighbours_.addAll(neighbours.neighbours_);
                        }
                        onChanged();
                    }
                } else if (!neighbours.neighbours_.isEmpty()) {
                    if (!this.neighboursBuilder_.isEmpty()) {
                        this.neighboursBuilder_.addAllMessages(neighbours.neighbours_);
                    } else {
                        this.neighboursBuilder_.dispose();
                        this.neighboursBuilder_ = null;
                        this.neighbours_ = neighbours.neighbours_;
                        this.bitField0_ &= -3;
                        this.neighboursBuilder_ = Neighbours.alwaysUseFieldBuilders ? getNeighboursFieldBuilder() : null;
                    }
                }
                if (neighbours.getTimestamp() != 0) {
                    setTimestamp(neighbours.getTimestamp());
                }
                mergeUnknownFields(neighbours.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.Discover.Neighbours.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.Discover.Neighbours.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.Discover$Neighbours$Builder");
            }

            @Override
            public Endpoint getFrom() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint = this.from_;
                    return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setFrom(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    endpoint.getClass();
                    this.from_ = endpoint;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(endpoint);
                }
                return this;
            }

            public Builder setFrom(Endpoint.Builder builder) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.from_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeFrom(Endpoint endpoint) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Endpoint endpoint2 = this.from_;
                    if (endpoint2 != null) {
                        this.from_ = Endpoint.newBuilder(endpoint2).mergeFrom(endpoint).buildPartial();
                    } else {
                        this.from_ = endpoint;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(endpoint);
                }
                return this;
            }

            public Builder clearFrom() {
                if (this.fromBuilder_ == null) {
                    this.from_ = null;
                    onChanged();
                } else {
                    this.from_ = null;
                    this.fromBuilder_ = null;
                }
                return this;
            }

            public Endpoint.Builder getFromBuilder() {
                onChanged();
                return getFromFieldBuilder().getBuilder();
            }

            @Override
            public EndpointOrBuilder getFromOrBuilder() {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.fromBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Endpoint endpoint = this.from_;
                return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
            }

            private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getFromFieldBuilder() {
                if (this.fromBuilder_ == null) {
                    this.fromBuilder_ = new SingleFieldBuilderV3<>(getFrom(), getParentForChildren(), isClean());
                    this.from_ = null;
                }
                return this.fromBuilder_;
            }

            private void ensureNeighboursIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.neighbours_ = new ArrayList(this.neighbours_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<Endpoint> getNeighboursList() {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.neighbours_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getNeighboursCount() {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.neighbours_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public Endpoint getNeighbours(int i) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.neighbours_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setNeighbours(int i, Endpoint endpoint) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    endpoint.getClass();
                    ensureNeighboursIsMutable();
                    this.neighbours_.set(i, endpoint);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, endpoint);
                }
                return this;
            }

            public Builder setNeighbours(int i, Endpoint.Builder builder) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNeighboursIsMutable();
                    this.neighbours_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addNeighbours(Endpoint endpoint) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    endpoint.getClass();
                    ensureNeighboursIsMutable();
                    this.neighbours_.add(endpoint);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(endpoint);
                }
                return this;
            }

            public Builder addNeighbours(int i, Endpoint endpoint) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    endpoint.getClass();
                    ensureNeighboursIsMutable();
                    this.neighbours_.add(i, endpoint);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, endpoint);
                }
                return this;
            }

            public Builder addNeighbours(Endpoint.Builder builder) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNeighboursIsMutable();
                    this.neighbours_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addNeighbours(int i, Endpoint.Builder builder) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNeighboursIsMutable();
                    this.neighbours_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllNeighbours(Iterable<? extends Endpoint> iterable) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNeighboursIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.neighbours_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearNeighbours() {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.neighbours_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeNeighbours(int i) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNeighboursIsMutable();
                    this.neighbours_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Endpoint.Builder getNeighboursBuilder(int i) {
                return getNeighboursFieldBuilder().getBuilder(i);
            }

            @Override
            public EndpointOrBuilder getNeighboursOrBuilder(int i) {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.neighbours_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends EndpointOrBuilder> getNeighboursOrBuilderList() {
                RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.neighboursBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.neighbours_);
            }

            public Endpoint.Builder addNeighboursBuilder() {
                return getNeighboursFieldBuilder().addBuilder(Endpoint.getDefaultInstance());
            }

            public Endpoint.Builder addNeighboursBuilder(int i) {
                return getNeighboursFieldBuilder().addBuilder(i, Endpoint.getDefaultInstance());
            }

            public List<Endpoint.Builder> getNeighboursBuilderList() {
                return getNeighboursFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getNeighboursFieldBuilder() {
                if (this.neighboursBuilder_ == null) {
                    this.neighboursBuilder_ = new RepeatedFieldBuilderV3<>(this.neighbours_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.neighbours_ = null;
                }
                return this.neighboursBuilder_;
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

    public static final class BackupMessage extends GeneratedMessageV3 implements BackupMessageOrBuilder {
        public static final int FLAG_FIELD_NUMBER = 1;
        public static final int PRIORITY_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private boolean flag_;
        private byte memoizedIsInitialized;
        private int priority_;
        private static final BackupMessage DEFAULT_INSTANCE = new BackupMessage();
        private static final Parser<BackupMessage> PARSER = new AbstractParser<BackupMessage>() {
            @Override
            public BackupMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BackupMessage(codedInputStream, extensionRegistryLite);
            }
        };

        public static BackupMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BackupMessage> parser() {
            return PARSER;
        }

        @Override
        public BackupMessage getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public boolean getFlag() {
            return this.flag_;
        }

        @Override
        public Parser<BackupMessage> getParserForType() {
            return PARSER;
        }

        @Override
        public int getPriority() {
            return this.priority_;
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

        private BackupMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private BackupMessage() {
            this.memoizedIsInitialized = (byte) -1;
            this.flag_ = false;
            this.priority_ = 0;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BackupMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.flag_ = codedInputStream.readBool();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.priority_ = codedInputStream.readInt32();
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
            return Discover.internal_static_protocol_BackupMessage_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Discover.internal_static_protocol_BackupMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(BackupMessage.class, Builder.class);
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.flag_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            int i = this.priority_;
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
            boolean z = this.flag_;
            int computeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
            int i2 = this.priority_;
            if (i2 != 0) {
                computeBoolSize += CodedOutputStream.computeInt32Size(2, i2);
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
            if (!(obj instanceof BackupMessage)) {
                return super.equals(obj);
            }
            BackupMessage backupMessage = (BackupMessage) obj;
            return getFlag() == backupMessage.getFlag() && getPriority() == backupMessage.getPriority() && this.unknownFields.equals(backupMessage.unknownFields);
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getFlag())) * 37) + 2) * 53) + getPriority()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static BackupMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static BackupMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BackupMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static BackupMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BackupMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static BackupMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BackupMessage parseFrom(InputStream inputStream) throws IOException {
            return (BackupMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BackupMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BackupMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BackupMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BackupMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BackupMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BackupMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BackupMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BackupMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BackupMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BackupMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BackupMessage backupMessage) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(backupMessage);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BackupMessageOrBuilder {
            private boolean flag_;
            private int priority_;

            @Override
            public boolean getFlag() {
                return this.flag_;
            }

            @Override
            public int getPriority() {
                return this.priority_;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Discover.internal_static_protocol_BackupMessage_descriptor;
            }

            @Override
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Discover.internal_static_protocol_BackupMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(BackupMessage.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BackupMessage.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.flag_ = false;
                this.priority_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Discover.internal_static_protocol_BackupMessage_descriptor;
            }

            @Override
            public BackupMessage getDefaultInstanceForType() {
                return BackupMessage.getDefaultInstance();
            }

            @Override
            public BackupMessage build() {
                BackupMessage buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public BackupMessage buildPartial() {
                BackupMessage backupMessage = new BackupMessage(this);
                backupMessage.flag_ = this.flag_;
                backupMessage.priority_ = this.priority_;
                onBuilt();
                return backupMessage;
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
                if (message instanceof BackupMessage) {
                    return mergeFrom((BackupMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BackupMessage backupMessage) {
                if (backupMessage == BackupMessage.getDefaultInstance()) {
                    return this;
                }
                if (backupMessage.getFlag()) {
                    setFlag(backupMessage.getFlag());
                }
                if (backupMessage.getPriority() != 0) {
                    setPriority(backupMessage.getPriority());
                }
                mergeUnknownFields(backupMessage.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public org.tron.protos.Discover.BackupMessage.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.protos.Discover.BackupMessage.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):org.tron.protos.Discover$BackupMessage$Builder");
            }

            public Builder setFlag(boolean z) {
                this.flag_ = z;
                onChanged();
                return this;
            }

            public Builder clearFlag() {
                this.flag_ = false;
                onChanged();
                return this;
            }

            public Builder setPriority(int i) {
                this.priority_ = i;
                onChanged();
                return this;
            }

            public Builder clearPriority() {
                this.priority_ = 0;
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
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0013core/Discover.proto\u0012\bprotocol\"N\n\bEndpoint\u0012\u000f\n\u0007address\u0018\u0001 \u0001(\f\u0012\f\n\u0004port\u0018\u0002 \u0001(\u0005\u0012\u000e\n\u0006nodeId\u0018\u0003 \u0001(\f\u0012\u0013\n\u000baddressIpv6\u0018\u0004 \u0001(\f\"s\n\u000bPingMessage\u0012 \n\u0004from\u0018\u0001 \u0001(\u000b2\u0012.protocol.Endpoint\u0012\u001e\n\u0002to\u0018\u0002 \u0001(\u000b2\u0012.protocol.Endpoint\u0012\u000f\n\u0007version\u0018\u0003 \u0001(\u0005\u0012\u0011\n\ttimestamp\u0018\u0004 \u0001(\u0003\"P\n\u000bPongMessage\u0012 \n\u0004from\u0018\u0001 \u0001(\u000b2\u0012.protocol.Endpoint\u0012\f\n\u0004echo\u0018\u0002 \u0001(\u0005\u0012\u0011\n\ttimestamp\u0018\u0003 \u0001(\u0003\"W\n\u000eFindNeighbours\u0012 \n\u0004from\u0018\u0001 \u0001(\u000b2\u0012.protocol.Endpoint\u0012\u0010\n\btargetId\u0018\u0002 \u0001(\f\u0012\u0011\n\ttimestamp\u0018\u0003 \u0001(\u0003\"i\n\nNeighbours\u0012 \n\u0004from\u0018\u0001 \u0001(\u000b2\u0012.protocol.Endpoint\u0012&\n\nneighbours\u0018\u0002 \u0003(\u000b2\u0012.protocol.Endpoint\u0012\u0011\n\ttimestamp\u0018\u0003 \u0001(\u0003\"/\n\rBackupMessage\u0012\f\n\u0004flag\u0018\u0001 \u0001(\b\u0012\u0010\n\bpriority\u0018\u0002 \u0001(\u0005BF\n\u000forg.tron.protosB\bDiscoverZ)github.com/tronprotocol/grpc-gateway/coreb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = Discover.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_Endpoint_descriptor = descriptor2;
        internal_static_protocol_Endpoint_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Address", "Port", "NodeId", "AddressIpv6"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_protocol_PingMessage_descriptor = descriptor3;
        internal_static_protocol_PingMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{HttpHeaders.FROM, "To", SignatureManager.Constants.Version, "Timestamp"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_protocol_PongMessage_descriptor = descriptor4;
        internal_static_protocol_PongMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{HttpHeaders.FROM, "Echo", "Timestamp"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_protocol_FindNeighbours_descriptor = descriptor5;
        internal_static_protocol_FindNeighbours_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{HttpHeaders.FROM, "TargetId", "Timestamp"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        internal_static_protocol_Neighbours_descriptor = descriptor6;
        internal_static_protocol_Neighbours_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{HttpHeaders.FROM, "Neighbours", "Timestamp"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(5);
        internal_static_protocol_BackupMessage_descriptor = descriptor7;
        internal_static_protocol_BackupMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Flag", "Priority"});
    }
}
