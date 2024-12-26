package org.tron.common.utils;

import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import com.tencent.bugly.Bugly;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.text.StringCharacterIterator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
public class JsonFormat {
    private static final int BUFFER_SIZE = 4096;
    private static final Pattern DIGITS = Pattern.compile("[0-9]", 2);

    private static int digitValue(char c) {
        return ('0' > c || c > '9') ? ('a' > c || c > 'z') ? c - '7' : c - 'W' : c - '0';
    }

    private static boolean isHex(char c) {
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F');
    }

    private static boolean isOctal(char c) {
        return '0' <= c && c <= '7';
    }

    public static void print(Message message, Appendable appendable) throws IOException {
        JsonGenerator jsonGenerator = new JsonGenerator(appendable);
        jsonGenerator.print("{");
        print(message, jsonGenerator);
        jsonGenerator.print("}");
    }

    public static void print(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        JsonGenerator jsonGenerator = new JsonGenerator(appendable);
        jsonGenerator.print("{");
        printUnknownFields(unknownFieldSet, jsonGenerator);
        jsonGenerator.print("}");
    }

    protected static void print(Message message, JsonGenerator jsonGenerator) throws IOException {
        if (message == null || message.getAllFields() == null || message.getAllFields().size() == 0) {
            return;
        }
        Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> it = message.getAllFields().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Descriptors.FieldDescriptor, Object> next = it.next();
            printField(next.getKey(), next.getValue(), jsonGenerator);
            if (it.hasNext()) {
                jsonGenerator.print(",");
            }
        }
        if (message.getUnknownFields().asMap().size() > 0) {
            jsonGenerator.print(", ");
        }
        printUnknownFields(message.getUnknownFields(), jsonGenerator);
    }

    public static String printToString(Message message) {
        try {
            StringBuilder sb = new StringBuilder();
            print(message, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Writing to a StringBuilder threw an IOException (should never happen).", e);
        }
    }

    public static String printToString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            print(unknownFieldSet, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Writing to a StringBuilder threw an IOException (should never happen).", e);
        }
    }

    public static String printErrorMsg(Exception exc) {
        return "{\"Error\":\"" + exc.getMessage() + "\"}";
    }

    public static void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, JsonGenerator jsonGenerator) throws IOException {
        printSingleField(fieldDescriptor, obj, jsonGenerator);
    }

    private static void printSingleField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, JsonGenerator jsonGenerator) throws IOException {
        if (fieldDescriptor.isExtension()) {
            jsonGenerator.print("\"");
            if (fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) {
                jsonGenerator.print(fieldDescriptor.getMessageType().getFullName());
            } else {
                jsonGenerator.print(fieldDescriptor.getFullName());
            }
            jsonGenerator.print("\"");
        } else {
            jsonGenerator.print("\"");
            if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                jsonGenerator.print(fieldDescriptor.getMessageType().getName());
            } else {
                jsonGenerator.print(fieldDescriptor.getName());
            }
            jsonGenerator.print("\"");
        }
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            jsonGenerator.print(": ");
            jsonGenerator.indent();
        } else {
            jsonGenerator.print(": ");
        }
        if (fieldDescriptor.isRepeated()) {
            jsonGenerator.print("[");
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                printFieldValue(fieldDescriptor, it.next(), jsonGenerator);
                if (it.hasNext()) {
                    jsonGenerator.print(",");
                }
            }
            jsonGenerator.print("]");
            return;
        }
        printFieldValue(fieldDescriptor, obj, jsonGenerator);
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            jsonGenerator.outdent();
        }
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    private static void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, JsonGenerator jsonGenerator) throws IOException {
        switch (fun1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fieldDescriptor.getType().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                jsonGenerator.print(obj.toString());
                return;
            case 10:
            case 11:
                jsonGenerator.print(unsignedToString(((Integer) obj).intValue()));
                return;
            case 12:
            case 13:
                jsonGenerator.print(unsignedToString(((Long) obj).longValue()));
                return;
            case 14:
                jsonGenerator.print("\"");
                jsonGenerator.print(escapeText((String) obj));
                jsonGenerator.print("\"");
                return;
            case 15:
                jsonGenerator.print("\"");
                jsonGenerator.print(escapeBytes((ByteString) obj));
                jsonGenerator.print("\"");
                return;
            case 16:
                jsonGenerator.print("\"");
                jsonGenerator.print(((Descriptors.EnumValueDescriptor) obj).getName());
                jsonGenerator.print("\"");
                return;
            case 17:
            case 18:
                jsonGenerator.print("{");
                print((Message) obj, jsonGenerator);
                jsonGenerator.print("}");
                return;
            default:
                return;
        }
    }

    protected static void printUnknownFields(UnknownFieldSet unknownFieldSet, JsonGenerator jsonGenerator) throws IOException {
        boolean z = true;
        for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFieldSet.asMap().entrySet()) {
            UnknownFieldSet.Field value = entry.getValue();
            if (z) {
                z = false;
            } else {
                jsonGenerator.print(", ");
            }
            jsonGenerator.print("\"");
            jsonGenerator.print(entry.getKey().toString());
            jsonGenerator.print("\"");
            jsonGenerator.print(": [");
            boolean z2 = true;
            for (Long l : value.getVarintList()) {
                long longValue = l.longValue();
                if (z2) {
                    z2 = false;
                } else {
                    jsonGenerator.print(", ");
                }
                jsonGenerator.print(unsignedToString(longValue));
            }
            for (Integer num : value.getFixed32List()) {
                int intValue = num.intValue();
                if (z2) {
                    z2 = false;
                } else {
                    jsonGenerator.print(", ");
                }
                jsonGenerator.print(String.format(null, "0x%08x", Integer.valueOf(intValue)));
            }
            for (Long l2 : value.getFixed64List()) {
                long longValue2 = l2.longValue();
                if (z2) {
                    z2 = false;
                } else {
                    jsonGenerator.print(", ");
                }
                jsonGenerator.print(String.format(null, "0x%016x", Long.valueOf(longValue2)));
            }
            for (ByteString byteString : value.getLengthDelimitedList()) {
                if (z2) {
                    z2 = false;
                } else {
                    jsonGenerator.print(", ");
                }
                jsonGenerator.print("\"");
                jsonGenerator.print(escapeBytes(byteString));
                jsonGenerator.print("\"");
            }
            for (UnknownFieldSet unknownFieldSet2 : value.getGroupList()) {
                if (z2) {
                    z2 = false;
                } else {
                    jsonGenerator.print(", ");
                }
                jsonGenerator.print("{");
                printUnknownFields(unknownFieldSet2, jsonGenerator);
                jsonGenerator.print("}");
            }
            jsonGenerator.print("]");
        }
    }

    private static String unsignedToString(int i) {
        if (i >= 0) {
            return Integer.toString(i);
        }
        return Long.toString(i & 4294967295L);
    }

    private static String unsignedToString(long j) {
        if (j >= 0) {
            return Long.toString(j);
        }
        return BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }

    public static void merge(Readable readable, Message.Builder builder) throws IOException {
        merge(readable, ExtensionRegistry.getEmptyRegistry(), builder);
    }

    public static void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
        merge(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
    }

    public static void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        merge(toStringBuilder(readable), extensionRegistry, builder);
    }

    public static void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        Tokenizer tokenizer = new Tokenizer(charSequence);
        tokenizer.consume("{");
        while (!tokenizer.tryConsume("}")) {
            mergeField(tokenizer, extensionRegistry, builder);
        }
        if (!tokenizer.atEnd()) {
            throw tokenizer.parseException("Expecting the end of the stream, but there seems to be more data!  Check the input for a valid JSON format.");
        }
    }

    protected static StringBuilder toStringBuilder(Readable readable) throws IOException {
        StringBuilder sb = new StringBuilder();
        CharBuffer allocate = CharBuffer.allocate(4096);
        while (true) {
            int read = readable.read(allocate);
            if (read == -1) {
                return sb;
            }
            allocate.flip();
            sb.append((CharSequence) allocate, 0, read);
        }
    }

    protected static void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        boolean z;
        Descriptors.FieldDescriptor fieldDescriptor;
        Descriptors.Descriptor descriptorForType = builder.getDescriptorForType();
        String consumeIdentifier = tokenizer.consumeIdentifier();
        Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName(consumeIdentifier);
        Descriptors.FieldDescriptor fieldDescriptor2 = null;
        if (findFieldByName == null && (findFieldByName = descriptorForType.findFieldByName(consumeIdentifier.toLowerCase(Locale.US))) != null && findFieldByName.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
            findFieldByName = null;
        }
        if (findFieldByName == null || findFieldByName.getType() != Descriptors.FieldDescriptor.Type.GROUP || findFieldByName.getMessageType().getName().equals(consumeIdentifier)) {
            fieldDescriptor2 = findFieldByName;
        }
        if (fieldDescriptor2 == null && DIGITS.matcher(consumeIdentifier).matches()) {
            fieldDescriptor2 = descriptorForType.findFieldByNumber(Integer.parseInt(consumeIdentifier));
            z = true;
        } else {
            z = false;
        }
        ExtensionRegistry.ExtensionInfo findExtensionByName = extensionRegistry.findExtensionByName(consumeIdentifier);
        if (findExtensionByName == null) {
            fieldDescriptor = fieldDescriptor2;
        } else if (findExtensionByName.descriptor.getContainingType() != descriptorForType) {
            throw tokenizer.parseExceptionPreviousToken("Extension \"" + consumeIdentifier + "\" does not extend message type \"" + descriptorForType.getFullName() + "\".");
        } else {
            fieldDescriptor = findExtensionByName.descriptor;
        }
        if (fieldDescriptor == null) {
            handleMissingField(tokenizer, extensionRegistry, builder);
        }
        if (fieldDescriptor != null) {
            tokenizer.consume(":");
            if (tokenizer.tryConsume("[")) {
                while (!tokenizer.tryConsume("]")) {
                    handleValue(tokenizer, extensionRegistry, builder, fieldDescriptor, findExtensionByName, z);
                    tokenizer.tryConsume(",");
                }
            } else {
                handleValue(tokenizer, extensionRegistry, builder, fieldDescriptor, findExtensionByName, z);
            }
        }
        if (tokenizer.tryConsume(",")) {
            mergeField(tokenizer, extensionRegistry, builder);
        }
    }

    private static void handleMissingField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        tokenizer.tryConsume(":");
        if ("{".equals(tokenizer.currentToken())) {
            tokenizer.consume("{");
            do {
                tokenizer.consumeIdentifier();
                handleMissingField(tokenizer, extensionRegistry, builder);
            } while (tokenizer.tryConsume(","));
            tokenizer.consume("}");
        } else if ("[".equals(tokenizer.currentToken())) {
            tokenizer.consume("[");
            do {
                handleMissingField(tokenizer, extensionRegistry, builder);
            } while (tokenizer.tryConsume(","));
            tokenizer.consume("]");
        } else if ("null".equals(tokenizer.currentToken())) {
            tokenizer.consume("null");
        } else if (tokenizer.lookingAtInteger()) {
            tokenizer.consumeInt64();
        } else if (tokenizer.lookingAtBoolean()) {
            tokenizer.consumeBoolean();
        } else {
            tokenizer.consumeString();
        }
    }

    private static void handleValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, Message.Builder builder, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, boolean z) throws ParseException {
        Object handlePrimitive;
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            handlePrimitive = handleObject(tokenizer, extensionRegistry, builder, fieldDescriptor, extensionInfo, z);
        } else {
            handlePrimitive = handlePrimitive(tokenizer, fieldDescriptor);
        }
        if (handlePrimitive != null) {
            if (fieldDescriptor.isRepeated()) {
                builder.addRepeatedField(fieldDescriptor, handlePrimitive);
            } else {
                builder.setField(fieldDescriptor, handlePrimitive);
            }
        }
    }

    private static Object handlePrimitive(Tokenizer tokenizer, Descriptors.FieldDescriptor fieldDescriptor) throws ParseException {
        if ("null".equals(tokenizer.currentToken())) {
            tokenizer.consume("null");
            return null;
        }
        switch (fun1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fieldDescriptor.getType().ordinal()]) {
            case 1:
            case 3:
            case 5:
                return Integer.valueOf(tokenizer.consumeInt32());
            case 2:
            case 4:
            case 6:
                return Long.valueOf(tokenizer.consumeInt64());
            case 7:
                return Float.valueOf(tokenizer.consumeFloat());
            case 8:
                return Double.valueOf(tokenizer.consumeDouble());
            case 9:
                return Boolean.valueOf(tokenizer.consumeBoolean());
            case 10:
            case 11:
                return Integer.valueOf(tokenizer.consumeUInt32());
            case 12:
            case 13:
                return Long.valueOf(tokenizer.consumeUInt64());
            case 14:
                return tokenizer.consumeString();
            case 15:
                return tokenizer.consumeByteString();
            case 16:
                Descriptors.EnumDescriptor enumType = fieldDescriptor.getEnumType();
                if (tokenizer.lookingAtInteger()) {
                    int consumeInt32 = tokenizer.consumeInt32();
                    Descriptors.EnumValueDescriptor findValueByNumber = enumType.findValueByNumber(consumeInt32);
                    if (findValueByNumber != null) {
                        return findValueByNumber;
                    }
                    throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value with number " + consumeInt32 + ThreadPoolManager.DOT);
                }
                String consumeIdentifier = tokenizer.consumeIdentifier();
                if (StringUtils.isAllLowerCase(consumeIdentifier)) {
                    consumeIdentifier = ((char) (consumeIdentifier.charAt(0) - ' ')) + consumeIdentifier.substring(1);
                }
                Descriptors.EnumValueDescriptor findValueByName = enumType.findValueByName(consumeIdentifier);
                if (findValueByName != null) {
                    return findValueByName;
                }
                throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value named \"" + consumeIdentifier + "\".");
            case 17:
            case 18:
                throw new RuntimeException("Can't get here.");
            default:
                return null;
        }
    }

    private static Object handleObject(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, Message.Builder builder, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, boolean z) throws ParseException {
        Message.Builder newBuilderForType;
        if (extensionInfo == null) {
            newBuilderForType = builder.newBuilderForField(fieldDescriptor);
        } else {
            newBuilderForType = extensionInfo.defaultInstance.newBuilderForType();
        }
        if (z) {
            ByteString consumeByteString = tokenizer.consumeByteString();
            try {
                newBuilderForType.mergeFrom(consumeByteString);
                return newBuilderForType.build();
            } catch (InvalidProtocolBufferException unused) {
                throw tokenizer.parseException("Failed to build " + fieldDescriptor.getFullName() + " from " + consumeByteString);
            }
        }
        tokenizer.consume("{");
        while (!tokenizer.tryConsume("}")) {
            if (tokenizer.atEnd()) {
                throw tokenizer.parseException("Expected \"}\".");
            }
            mergeField(tokenizer, extensionRegistry, newBuilderForType);
            tokenizer.tryConsume(",");
        }
        return newBuilderForType.build();
    }

    static String escapeBytes(ByteString byteString) {
        return ByteArray.toHexString(byteString.toByteArray());
    }

    static String unicodeEscaped(char c) {
        if (c < 16) {
            return "\\u000" + Integer.toHexString(c);
        } else if (c < 256) {
            return "\\u00" + Integer.toHexString(c);
        } else if (c < 4096) {
            return "\\u0" + Integer.toHexString(c);
        } else {
            return "\\u" + Integer.toHexString(c);
        }
    }

    static ByteString unescapeBytes(CharSequence charSequence) throws InvalidEscapeSequence {
        try {
            return ByteString.copyFrom(ByteArray.fromHexString(charSequence.toString()));
        } catch (Exception unused) {
            throw new InvalidEscapeSequence("invalidate hex String");
        }
    }

    static String escapeText(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(str);
        for (char first = stringCharacterIterator.first(); first != 65535; first = stringCharacterIterator.next()) {
            if (first == '\f') {
                sb.append("\\f");
            } else if (first == '\r') {
                sb.append("\\r");
            } else if (first == '\"') {
                sb.append("\\\"");
            } else if (first != '\\') {
                switch (first) {
                    case '\b':
                        sb.append("\\b");
                        continue;
                    case '\t':
                        sb.append("\\t");
                        continue;
                    case '\n':
                        sb.append("\\n");
                        continue;
                    default:
                        if (first >= 0 && first <= 31) {
                            appendEscapedUnicode(sb, first);
                            continue;
                        } else if (Character.isHighSurrogate(first)) {
                            appendEscapedUnicode(sb, first);
                            char next = stringCharacterIterator.next();
                            if (next == 65535) {
                                throw new IllegalArgumentException("invalid unicode string: unexpected high surrogate pair value without corresponding low value.");
                            }
                            appendEscapedUnicode(sb, next);
                            break;
                        } else {
                            sb.append(first);
                            break;
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    static void appendEscapedUnicode(StringBuilder sb, char c) {
        sb.append(c < 16 ? "\\u000" : c < 256 ? "\\u00" : c < 4096 ? "\\u0" : "\\u");
        sb.append(Integer.toHexString(c));
    }

    static String unescapeText(String str) throws InvalidEscapeSequence {
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            char c = charArray[i];
            if (c == '\\') {
                int i2 = i + 1;
                if (i2 < charArray.length) {
                    char c2 = charArray[i2];
                    if (c2 == '\"') {
                        sb.append(Typography.quote);
                    } else if (c2 == '\'') {
                        sb.append('\'');
                    } else if (c2 == '\\') {
                        sb.append('\\');
                    } else if (c2 == 'b') {
                        sb.append('\b');
                    } else if (c2 == 'f') {
                        sb.append('\f');
                    } else if (c2 == 'n') {
                        sb.append('\n');
                    } else if (c2 == 'r') {
                        sb.append(CharUtils.CR);
                    } else if (c2 == 't') {
                        sb.append('\t');
                    } else if (c2 == 'u') {
                        if (i + 5 < charArray.length) {
                            sb.append((char) Integer.parseInt(new String(charArray, i + 2, 4), 16));
                            i += 5;
                        } else {
                            throw new InvalidEscapeSequence("Invalid escape sequence: '\\u' at end of string.");
                        }
                    } else {
                        throw new InvalidEscapeSequence("Invalid escape sequence: '\\" + c2 + "'");
                    }
                    i = i2;
                } else {
                    throw new InvalidEscapeSequence("Invalid escape sequence: '\\' at end of string.");
                }
            } else {
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }

    static int parseInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, true, false);
    }

    static int parseUInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, false, false);
    }

    static long parseInt64(String str) throws NumberFormatException {
        return parseInteger(str, true, true);
    }

    static long parseUInt64(String str) throws NumberFormatException {
        return parseInteger(str, false, true);
    }

    private static long parseInteger(String str, boolean z, boolean z2) throws NumberFormatException {
        boolean z3;
        int i;
        int i2 = 0;
        if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX, 0)) {
            z3 = false;
        } else if (!z) {
            throw new NumberFormatException("Number must be positive: " + str);
        } else {
            i2 = 1;
            z3 = true;
        }
        if (str.startsWith("0x", i2)) {
            i2 += 2;
            i = 16;
        } else {
            i = str.startsWith("0", i2) ? 8 : 10;
        }
        String substring = str.substring(i2);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i);
            if (z3) {
                parseLong = -parseLong;
            }
            if (z2) {
                return parseLong;
            }
            if (z) {
                if (parseLong > 2147483647L || parseLong < -2147483648L) {
                    throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
                }
                return parseLong;
            } else if (parseLong >= 4294967296L || parseLong < 0) {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
            } else {
                return parseLong;
            }
        }
        BigInteger bigInteger = new BigInteger(substring, i);
        if (z3) {
            bigInteger = bigInteger.negate();
        }
        if (z2) {
            if (z) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: " + str);
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + str);
            }
        } else if (z) {
            if (bigInteger.bitLength() > 31) {
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
            }
        } else if (bigInteger.bitLength() > 32) {
            throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
        }
        return bigInteger.longValue();
    }

    public static class JsonGenerator {
        private boolean atStartOfLine = true;
        private StringBuilder indent = new StringBuilder();
        private Appendable output;

        public JsonGenerator(Appendable appendable) {
            this.output = appendable;
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.indent.delete(length - 2, length);
        }

        public void print(CharSequence charSequence) throws IOException {
            int length = charSequence.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (charSequence.charAt(i2) == '\n') {
                    write(charSequence.subSequence(i, length), (i2 - i) + 1);
                    i = i2 + 1;
                    this.atStartOfLine = true;
                }
            }
            write(charSequence.subSequence(i, length), length - i);
        }

        private void write(CharSequence charSequence, int i) throws IOException {
            if (i == 0) {
                return;
            }
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.indent);
            }
            this.output.append(charSequence);
        }
    }

    public static class Tokenizer {
        private String currentToken;
        private final Matcher matcher;
        private final CharSequence text;
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);
        private int pos = 0;
        private int line = 0;
        private int column = 0;
        private int previousLine = 0;
        private int previousColumn = 0;

        public String currentToken() {
            return this.currentToken;
        }

        public Tokenizer(CharSequence charSequence) {
            this.text = charSequence;
            this.matcher = WHITESPACE.matcher(charSequence);
            skipWhitespace();
            nextToken();
        }

        static ByteString unescapeBytes(CharSequence charSequence) throws InvalidEscapeSequence {
            try {
                return ByteString.copyFrom(ByteArray.fromHexString(charSequence.toString()));
            } catch (Exception unused) {
                throw new InvalidEscapeSequence("INVALID hex String");
            }
        }

        public boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    this.line++;
                    this.column = 0;
                } else {
                    this.column++;
                }
                this.pos++;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
                return;
            }
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
                this.currentToken = this.matcher.group();
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            } else {
                this.currentToken = String.valueOf(this.text.charAt(this.pos));
                Matcher matcher2 = this.matcher;
                matcher2.region(this.pos + 1, matcher2.regionEnd());
            }
            skipWhitespace();
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            }
        }

        public boolean tryConsume(String str) {
            if (this.currentToken.equals(str)) {
                nextToken();
                return true;
            }
            return false;
        }

        public void consume(String str) throws ParseException {
            if (tryConsume(str)) {
                return;
            }
            throw parseException("Expected \"" + str + "\".");
        }

        public boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char charAt = this.currentToken.charAt(0);
            return ('0' <= charAt && charAt <= '9') || charAt == '-' || charAt == '+';
        }

        public boolean lookingAtBoolean() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            return "true".equals(this.currentToken) || Bugly.SDK_IS_DEV.equals(this.currentToken);
        }

        public String consumeIdentifier() throws ParseException {
            for (int i = 0; i < this.currentToken.length(); i++) {
                char charAt = this.currentToken.charAt(i);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && !(('0' <= charAt && charAt <= '9') || charAt == '_' || charAt == '.' || charAt == '\"'))) {
                    throw parseException("Expected identifier. -" + charAt);
                }
            }
            String replaceAll = this.currentToken.replaceAll("\"|'", "");
            nextToken();
            return replaceAll;
        }

        public int consumeInt32() throws ParseException {
            try {
                int parseInt32 = JsonFormat.parseInt32(this.currentToken);
                nextToken();
                return parseInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public int consumeUInt32() throws ParseException {
            try {
                int parseUInt32 = JsonFormat.parseUInt32(this.currentToken);
                nextToken();
                return parseUInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeInt64() throws ParseException {
            try {
                long parseInt64 = JsonFormat.parseInt64(this.currentToken);
                nextToken();
                return parseInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeUInt64() throws ParseException {
            try {
                long parseUInt64 = JsonFormat.parseUInt64(this.currentToken);
                nextToken();
                return parseUInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public double consumeDouble() throws ParseException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX);
                nextToken();
                return startsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else if (this.currentToken.equalsIgnoreCase("nan")) {
                nextToken();
                return Double.NaN;
            } else {
                try {
                    double parseDouble = Double.parseDouble(this.currentToken);
                    nextToken();
                    return parseDouble;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public float consumeFloat() throws ParseException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX);
                nextToken();
                return startsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            } else if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                nextToken();
                return Float.NaN;
            } else {
                try {
                    float parseFloat = Float.parseFloat(this.currentToken);
                    nextToken();
                    return parseFloat;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true")) {
                nextToken();
                return true;
            } else if (this.currentToken.equals(Bugly.SDK_IS_DEV)) {
                nextToken();
                return false;
            } else {
                throw parseException("Expected \"true\" or \"false\".");
            }
        }

        public String consumeString() throws ParseException {
            char charAt = this.currentToken.length() > 0 ? this.currentToken.charAt(0) : (char) 0;
            if (charAt != '\"' && charAt != '\'') {
                throw parseException("Expected string.");
            }
            if (this.currentToken.length() >= 2) {
                String str = this.currentToken;
                if (str.charAt(str.length() - 1) == charAt) {
                    try {
                        String str2 = this.currentToken;
                        String unescapeText = JsonFormat.unescapeText(str2.substring(1, str2.length() - 1));
                        nextToken();
                        return unescapeText;
                    } catch (InvalidEscapeSequence e) {
                        throw parseException(e.getMessage());
                    }
                }
            }
            throw parseException("String missing ending quote.");
        }

        public ByteString consumeByteString() throws ParseException {
            char charAt = this.currentToken.length() > 0 ? this.currentToken.charAt(0) : (char) 0;
            if (charAt != '\"' && charAt != '\'') {
                throw parseException("Expected string.");
            }
            if (this.currentToken.length() >= 2) {
                String str = this.currentToken;
                if (str.charAt(str.length() - 1) == charAt) {
                    try {
                        String str2 = this.currentToken;
                        ByteString unescapeBytes = unescapeBytes(str2.substring(1, str2.length() - 1));
                        nextToken();
                        return unescapeBytes;
                    } catch (InvalidEscapeSequence e) {
                        throw parseException(e.getMessage());
                    }
                }
            }
            throw parseException("String missing ending quote.");
        }

        public ParseException parseException(String str) {
            return new ParseException((this.line + 1) + ":" + (this.column + 1) + ": " + str);
        }

        public ParseException parseExceptionPreviousToken(String str) {
            return new ParseException((this.previousLine + 1) + ":" + (this.previousColumn + 1) + ": " + str);
        }

        private ParseException integerParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse integer: " + numberFormatException.getMessage());
        }

        private ParseException floatParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse number: " + numberFormatException.getMessage());
        }
    }

    public static class ParseException extends IOException {
        private static final long serialVersionUID = 1;

        public ParseException(String str) {
            super(str);
        }
    }

    public static class InvalidEscapeSequence extends IOException {
        private static final long serialVersionUID = 1;

        public InvalidEscapeSequence(String str) {
            super(str);
        }
    }
}
