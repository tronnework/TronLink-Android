package org.tron.common.crypto;

import android.os.Build;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.stream.Collectors;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import kotlin.UByte$ExternalSyntheticBackport0;
import org.tron.common.bip32.Numeric;
import org.tron.common.crypto.datatypes.Address;
import org.tron.common.crypto.datatypes.Array;
import org.tron.common.crypto.datatypes.Bool;
import org.tron.common.crypto.datatypes.Bytes;
import org.tron.common.crypto.datatypes.BytesType;
import org.tron.common.crypto.datatypes.DynamicArray;
import org.tron.common.crypto.datatypes.DynamicBytes;
import org.tron.common.crypto.datatypes.DynamicStruct;
import org.tron.common.crypto.datatypes.Fixed;
import org.tron.common.crypto.datatypes.FixedPointType;
import org.tron.common.crypto.datatypes.NumericType;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.StaticStruct;
import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.datatypes.Ufixed;
import org.tron.common.crypto.datatypes.Uint;
import org.tron.common.crypto.datatypes.Utf8String;
import org.tron.common.crypto.datatypes.primitive.PrimitiveType;
public class TypeEncoder {
    private TypeEncoder() {
    }

    public static boolean isDynamic(Type type) {
        return (type instanceof DynamicBytes) || (type instanceof Utf8String) || (type instanceof DynamicArray) || ((type instanceof StaticArray) && DynamicStruct.class.isAssignableFrom(((StaticArray) type).getComponentType()));
    }

    public static String encode(Type type) {
        if (type instanceof NumericType) {
            return encodeNumeric((NumericType) type);
        }
        if (type instanceof Address) {
            return encodeAddress((Address) type);
        }
        if (type instanceof Bool) {
            return encodeBool((Bool) type);
        }
        if (type instanceof Bytes) {
            return encodeBytes((Bytes) type);
        }
        if (type instanceof DynamicBytes) {
            return encodeDynamicBytes((DynamicBytes) type);
        }
        if (type instanceof Utf8String) {
            return encodeString((Utf8String) type);
        }
        if (type instanceof StaticArray) {
            StaticArray staticArray = (StaticArray) type;
            if (DynamicStruct.class.isAssignableFrom(staticArray.getComponentType())) {
                return encodeStaticArrayWithDynamicStruct(staticArray);
            }
            return encodeArrayValues(staticArray);
        } else if (type instanceof DynamicStruct) {
            return encodeDynamicStruct((DynamicStruct) type);
        } else {
            if (type instanceof DynamicArray) {
                return encodeDynamicArray((DynamicArray) type);
            }
            if (type instanceof PrimitiveType) {
                return encode(((PrimitiveType) type).toSolidityType());
            }
            


return null;

//throw new UnsupportedOperationException(
Type cannot be encoded: " + type.getClass());
        }
    }

    public static String encodePacked(Type type) {
        if (type instanceof Utf8String) {
            return removePadding(encode(type), type);
        }
        if (type instanceof DynamicBytes) {
            return encode(type).substring(64);
        }
        if (type instanceof DynamicArray) {
            return arrayEncodePacked((DynamicArray) type);
        }
        if (type instanceof StaticArray) {
            return arrayEncodePacked((StaticArray) type);
        }
        if (type instanceof PrimitiveType) {
            return encodePacked(((PrimitiveType) type).toSolidityType());
        }
        return removePadding(encode(type), type);
    }

    static String removePadding(String str, Type type) {
        if (type instanceof NumericType) {
            return ((type instanceof Ufixed) || (type instanceof Fixed)) ? str : str.substring(64 - (((NumericType) type).getBitSize() / 4), 64);
        } else if (type instanceof Address) {
            return str.substring(64 - (((Address) type).toUint().getBitSize() / 4), 64);
        } else {
            if (type instanceof Bool) {
                return str.substring(62, 64);
            }
            if (type instanceof Bytes) {
                return str.substring(0, ((BytesType) type).getValue().length * 2);
            }
            if (type instanceof Utf8String) {
                return str.substring(64, (((Utf8String) type).getValue().getBytes(StandardCharsets.UTF_8).length * 2) + 64);
            }
            


return null;

//throw new UnsupportedOperationException(
Type cannot be encoded: " + type.getClass());
        }
    }

    private static <T extends Type> String encodeStaticArrayWithDynamicStruct(Array<T> array) {
        String encodeStructsArraysOffsets = encodeStructsArraysOffsets(array);
        String encodeArrayValues = encodeArrayValues(array);
        return encodeStructsArraysOffsets + encodeArrayValues;
    }

    static String encodeAddress(Address address) {
        return encodeNumeric(address.toUint());
    }

    public static String encodeNumeric(NumericType numericType) {
        byte[] byteArray = toByteArray(numericType);
        byte paddingValue = getPaddingValue(numericType);
        byte[] bArr = new byte[32];
        if (paddingValue != 0) {
            for (int i = 0; i < 32; i++) {
                bArr[i] = paddingValue;
            }
        }
        System.arraycopy(byteArray, 0, bArr, 32 - byteArray.length, byteArray.length);
        return Numeric.toHexStringNoPrefix(bArr);
    }

    private static byte getPaddingValue(NumericType numericType) {
        return numericType.getValue().signum() == -1 ? (byte) -1 : (byte) 0;
    }

    private static byte[] toByteArray(NumericType numericType) {
        BigInteger value = numericType.getValue();
        if (((numericType instanceof Ufixed) || (numericType instanceof Uint)) && value.bitLength() == 256) {
            byte[] bArr = new byte[32];
            System.arraycopy(value.toByteArray(), 1, bArr, 0, 32);
            return bArr;
        }
        return value.toByteArray();
    }

    static String encodeBool(Bool bool) {
        byte[] bArr = new byte[32];
        if (bool.getValue().booleanValue()) {
            bArr[31] = 1;
        }
        return Numeric.toHexStringNoPrefix(bArr);
    }

    static String encodeBytes(BytesType bytesType) {
        byte[] value = bytesType.getValue();
        int length = value.length;
        int i = length % 32;
        if (i != 0) {
            byte[] bArr = new byte[(32 - i) + length];
            System.arraycopy(value, 0, bArr, 0, length);
            value = bArr;
        }
        return Numeric.toHexStringNoPrefix(value);
    }

    static String encodeDynamicBytes(DynamicBytes dynamicBytes) {
        String encode = encode(new Uint(BigInteger.valueOf(dynamicBytes.getValue().length)));
        String encodeBytes = encodeBytes(dynamicBytes);
        return encode + encodeBytes;
    }

    static String encodeString(Utf8String utf8String) {
        return encodeDynamicBytes(new DynamicBytes(utf8String.getValue().getBytes(StandardCharsets.UTF_8)));
    }

    static <T extends Type> String encodeArrayValues(Array<T> array) {
        StringBuilder sb = new StringBuilder();
        for (T t : array.getValue()) {
            sb.append(encode(t));
        }
        return sb.toString();
    }

    static String encodeDynamicStruct(DynamicStruct dynamicStruct) {
        String encodeDynamicStructValues = encodeDynamicStructValues(dynamicStruct);
        return encodeDynamicStructValues;
    }

    private static String encodeDynamicStructValues(DynamicStruct dynamicStruct) {
        int i = 0;
        for (int i2 = 0; i2 < dynamicStruct.getValue().size(); i2++) {
            Type type = (Type) dynamicStruct.getValue().get(i2);
            i = isDynamic(type) ? i + 32 : i + type.bytes32PaddedLength();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < dynamicStruct.getValue().size(); i3++) {
            Type type2 = (Type) dynamicStruct.getValue().get(i3);
            if (isDynamic(type2)) {
                arrayList.add(Numeric.toHexStringNoPrefix(Numeric.toBytesPadded(new BigInteger(Long.toString(i)), 32)));
                String encode = encode(type2);
                arrayList2.add(encode);
                i += encode.length() >> 1;
            } else {
                arrayList.add(encode((Type) dynamicStruct.getValue().get(i3)));
            }
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(arrayList);
        arrayList3.addAll(arrayList2);
        if (Build.VERSION.SDK_INT >= 26) {
            return UByte$ExternalSyntheticBackport0.m("", arrayList3);
        }
        final StringBuffer stringBuffer = new StringBuffer();
        Collection.-EL.stream(arrayList3).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                stringBuffer.append((String) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return stringBuffer.toString();
    }

    static <T extends Type> String encodeDynamicArray(DynamicArray<T> dynamicArray) {
        String encode = encode(new Uint(BigInteger.valueOf(dynamicArray.getValue().size())));
        String encodeArrayValuesOffsets = encodeArrayValuesOffsets(dynamicArray);
        String encodeArrayValues = encodeArrayValues(dynamicArray);
        return encode + encodeArrayValuesOffsets + encodeArrayValues;
    }

    private static <T extends Type> String encodeArrayValuesOffsets(DynamicArray<T> dynamicArray) {
        int length;
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        boolean z2 = !dynamicArray.getValue().isEmpty() && (dynamicArray.getValue().get(0) instanceof DynamicBytes);
        boolean z3 = !dynamicArray.getValue().isEmpty() && (dynamicArray.getValue().get(0) instanceof Utf8String);
        z = (dynamicArray.getValue().isEmpty() || !(dynamicArray.getValue().get(0) instanceof DynamicStruct)) ? false : false;
        if (z2 || z3) {
            long j = 0;
            for (int i = 0; i < dynamicArray.getValue().size(); i++) {
                if (i == 0) {
                    j = dynamicArray.getValue().size() * 32;
                } else {
                    if (z2) {
                        length = ((byte[]) ((Type) dynamicArray.getValue().get(i - 1)).getValue()).length;
                    } else {
                        length = ((String) ((Type) dynamicArray.getValue().get(i - 1)).getValue()).length();
                    }
                    j += (((length + 31) / 32) * 32) + 32;
                }
                sb.append(Numeric.toHexStringNoPrefix(Numeric.toBytesPadded(new BigInteger(Long.toString(j)), 32)));
            }
        } else if (z) {
            sb.append(encodeStructsArraysOffsets(dynamicArray));
        }
        return sb.toString();
    }

    private static <T extends Type> String encodeStructsArraysOffsets(Array<T> array) {
        StringBuilder sb = new StringBuilder();
        long size = array.getValue().size();
        List list = (List) Collection.-EL.stream(array.getValue()).map(new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                return TypeEncoder.encode((Type) obj);
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
            }
        }).collect(Collectors.toList());
        int i = 0;
        while (i < array.getValue().size()) {
            size = i == 0 ? size * 32 : size + (((String) list.get(i - 1)).length() / 2);
            sb.append(Numeric.toHexStringNoPrefix(Numeric.toBytesPadded(new BigInteger(Long.toString(size)), 32)));
            i++;
        }
        return sb.toString();
    }

    private static <T extends Type> boolean isSupportingEncodedPacked(Array<T> array) {
        return (Utf8String.class.isAssignableFrom(array.getComponentType()) || DynamicStruct.class.isAssignableFrom(array.getComponentType()) || DynamicArray.class.isAssignableFrom(array.getComponentType()) || StaticStruct.class.isAssignableFrom(array.getComponentType()) || FixedPointType.class.isAssignableFrom(array.getComponentType()) || DynamicBytes.class.isAssignableFrom(array.getComponentType())) ? false : true;
    }

    private static <T extends Type> String arrayEncodePacked(Array<T> array) {
        if (isSupportingEncodedPacked(array)) {
            if (array.getValue().isEmpty()) {
                return "";
            }
            if (array instanceof DynamicArray) {
                return encode(array).substring(64);
            }
            if (array instanceof StaticArray) {
                return encode(array);
            }
        }
        


return null;

//throw new UnsupportedOperationException(
Type cannot be packed encoded: " + array.getClass());
    }
}
