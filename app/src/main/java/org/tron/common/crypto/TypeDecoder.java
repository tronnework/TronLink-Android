package org.tron.common.crypto;

import android.os.Build;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.tron.common.bip32.Numeric;
import org.tron.common.crypto.TypeReference;
import org.tron.common.crypto.datatypes.AbiTypes;
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
import org.tron.common.crypto.datatypes.Int;
import org.tron.common.crypto.datatypes.IntType;
import org.tron.common.crypto.datatypes.NumericType;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.StaticStruct;
import org.tron.common.crypto.datatypes.StructType;
import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.datatypes.Uint;
import org.tron.common.crypto.datatypes.Utf8String;
import org.tron.common.crypto.datatypes.generated.Uint160;
public class TypeDecoder {
    static final int MAX_BYTE_LENGTH_FOR_HEX_STRING = 64;

    public static Type instantiateType(String str, Object obj) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        return instantiateType(TypeReference.makeTypeReference(str), obj);
    }

    public static Type instantiateType(TypeReference typeReference, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Class classType = typeReference.getClassType();
        if (Array.class.isAssignableFrom(classType)) {
            return instantiateArrayType(typeReference, obj);
        }
        return instantiateAtomicType(classType, obj);
    }

    public static <T extends Array> T decode(String str, int i, TypeReference<T> typeReference) {
        Class<?> cls = ((ParameterizedType) typeReference.getType()).getRawType().getClass();
        if (StaticArray.class.isAssignableFrom(cls)) {
            return (T) decodeStaticArray(str, i, typeReference, 1);
        }
        if (DynamicArray.class.isAssignableFrom(cls)) {
            return (T) decodeDynamicArray(str, i, typeReference);
        }
        


return null;

//throw new UnsupportedOperationException(
Unsupported TypeReference: " + cls.getName() + ", only Array types can be passed as TypeReferences");
    }

    public static <T extends Type> T decode(String str, int i, Class<T> cls) {
        if (NumericType.class.isAssignableFrom(cls)) {
            return decodeNumeric(str.substring(i), cls);
        }
        if (Address.class.isAssignableFrom(cls)) {
            return decodeAddress(str.substring(i));
        }
        if (Bool.class.isAssignableFrom(cls)) {
            return decodeBool(str, i);
        }
        if (Bytes.class.isAssignableFrom(cls)) {
            return decodeBytes(str, i, cls);
        }
        if (DynamicBytes.class.isAssignableFrom(cls)) {
            return decodeDynamicBytes(str, i);
        }
        if (Utf8String.class.isAssignableFrom(cls)) {
            return decodeUtf8String(str, i);
        }
        if (Array.class.isAssignableFrom(cls)) {
            


return null;

//throw new UnsupportedOperationException(
Array types must be wrapped in a TypeReference");
        }
        


return null;

//throw new UnsupportedOperationException(
Type cannot be encoded: " + cls.getClass());
    }

    public static <T extends Type> T decode(String str, Class<T> cls) {
        return (T) decode(str, 0, cls);
    }

    static Address decodeAddress(String str) {
        return new Address((Uint) decodeNumeric(str, Uint160.class));
    }

    static <T extends NumericType> T decodeNumeric(String str, Class<T> cls) {
        try {
            byte[] hexStringToByteArray = Numeric.hexStringToByteArray(str);
            int typeLengthInBytes = getTypeLengthInBytes(cls);
            byte[] bArr = new byte[typeLengthInBytes + 1];
            if (Int.class.isAssignableFrom(cls) || Fixed.class.isAssignableFrom(cls)) {
                bArr[0] = hexStringToByteArray[0];
            }
            System.arraycopy(hexStringToByteArray, 32 - typeLengthInBytes, bArr, 1, typeLengthInBytes);
            return cls.getConstructor(BigInteger.class).newInstance(new BigInteger(bArr));
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            


return null;

//throw new UnsupportedOperationException(
Unable to create instance of " + cls.getName(), e);
        }
    }

    static <T extends NumericType> int getTypeLengthInBytes(Class<T> cls) {
        return getTypeLength(cls) >> 3;
    }

    static <T extends NumericType> int getTypeLength(Class<T> cls) {
        if (IntType.class.isAssignableFrom(cls)) {
            String[] split = cls.getSimpleName().split("(Uint|Int)");
            if (split.length == 2) {
                return Integer.parseInt(split[1]);
            }
            return 256;
        } else if (FixedPointType.class.isAssignableFrom(cls)) {
            String[] split2 = cls.getSimpleName().split("(Ufixed|Fixed)");
            if (split2.length == 2) {
                String[] split3 = split2[1].split("x");
                return Integer.parseInt(split3[0]) + Integer.parseInt(split3[1]);
            }
            return 256;
        } else {
            return 256;
        }
    }

    static Type instantiateArrayType(TypeReference typeReference, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        List<Object> arrayToList;
        Constructor<?> constructor;
        if (obj instanceof List) {
            arrayToList = (List) obj;
        } else if (obj.getClass().isArray()) {
            arrayToList = arrayToList(obj);
        } else {
            throw new ClassCastException("Arg of type " + obj.getClass() + " should be a list to instantiate web3j Array");
        }
        int size = typeReference instanceof TypeReference.StaticArrayTypeReference ? ((TypeReference.StaticArrayTypeReference) typeReference).getSize() : -1;
        if (size <= 0) {
            constructor = DynamicArray.class.getConstructor(Class.class, List.class);
        } else {
            constructor = Class.forName("org.web3j.abi.datatypes.generated.StaticArray" + size).getConstructor(Class.class, List.class);
        }
        ArrayList arrayList = new ArrayList(arrayToList.size());
        TypeReference subTypeReference = typeReference.getSubTypeReference();
        for (Object obj2 : arrayToList) {
            arrayList.add(instantiateType(subTypeReference, obj2));
        }
        return (Type) constructor.newInstance(subTypeReference.getClassType(), arrayList);
    }

    static Type instantiateAtomicType(Class<?> cls, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        BigInteger asBigInteger;
        Object valueOf;
        if (NumericType.class.isAssignableFrom(cls)) {
            valueOf = asBigInteger(obj);
        } else if (BytesType.class.isAssignableFrom(cls)) {
            if (!(obj instanceof byte[])) {
                if (obj instanceof BigInteger) {
                    valueOf = ((BigInteger) obj).toByteArray();
                } else {
                    if (obj instanceof String) {
                        valueOf = Numeric.hexStringToByteArray((String) obj);
                    }
                    valueOf = null;
                }
            }
            valueOf = obj;
        } else if (Utf8String.class.isAssignableFrom(cls)) {
            valueOf = obj.toString();
        } else if (Address.class.isAssignableFrom(cls)) {
            if (!(obj instanceof BigInteger) && !(obj instanceof Uint160)) {
                valueOf = obj.toString();
            }
            valueOf = obj;
        } else {
            if (Bool.class.isAssignableFrom(cls)) {
                if (!(obj instanceof Boolean)) {
                    if (asBigInteger(obj) != null) {
                        valueOf = Boolean.valueOf(!asBigInteger.equals(BigInteger.ZERO));
                    }
                }
                valueOf = obj;
            }
            valueOf = null;
        }
        if (valueOf == null) {
            throw new InstantiationException("Could not create type " + cls + " from arg " + obj.toString() + " of type " + obj.getClass());
        }
        return (Type) cls.getConstructor(valueOf.getClass()).newInstance(valueOf);
    }

    static <T extends Type> int getSingleElementLength(String str, int i, Class<T> cls) {
        if (str.length() == i) {
            return 0;
        }
        if (DynamicBytes.class.isAssignableFrom(cls) || Utf8String.class.isAssignableFrom(cls)) {
            return (decodeUintAsInt(str, i) / 32) + 2;
        }
        if (StaticStruct.class.isAssignableFrom(cls)) {
            return Utils.staticStructNestedPublicFieldsFlatList(cls).size();
        }
        return 1;
    }

    public static int decodeUintAsInt(String str, int i) {
        return ((Uint) decode(str.substring(i, i + 64), 0, Uint.class)).getValue().intValue();
    }

    static Bool decodeBool(String str, int i) {
        return new Bool(Numeric.toBigInt(str.substring(i, i + 64)).equals(BigInteger.ONE));
    }

    public static <T extends Bytes> T decodeBytes(String str, Class<T> cls) {
        return (T) decodeBytes(str, 0, cls);
    }

    static <T extends Bytes> T decodeBytes(String str, int i, Class<T> cls) {
        try {
            return cls.getConstructor(byte[].class).newInstance(Numeric.hexStringToByteArray(str.substring(i, (Integer.parseInt(cls.getSimpleName().split("Bytes")[1]) << 1) + i)));
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            


return null;

//throw new UnsupportedOperationException(
Unable to create instance of " + cls.getName(), e);
        }
    }

    static DynamicBytes decodeDynamicBytes(String str, int i) {
        int i2 = i + 64;
        return new DynamicBytes(Numeric.hexStringToByteArray(str.substring(i2, (decodeUintAsInt(str, i) << 1) + i2)));
    }

    static Utf8String decodeUtf8String(String str, int i) {
        return new Utf8String(new String(decodeDynamicBytes(str, i).getValue(), StandardCharsets.UTF_8));
    }

    public static <T extends Type> T decodeStaticArray(String str, int i, TypeReference<T> typeReference, final int i2) {
        return (T) decodeArrayElements(str, i, typeReference, i2, new BiFunction() {
            public BiFunction andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj, Object obj2) {
                return TypeDecoder.lambda$decodeStaticArray$0(i2, (List) obj, (String) obj2);
            }
        });
    }

    public static Type lambda$decodeStaticArray$0(int i, List list, String str) {
        if (list.isEmpty()) {
            


return null;

//throw new UnsupportedOperationException(
Zero length fixed array is invalid type");
        }
        return instantiateStaticArray(list, i);
    }

    public static <T extends Type> T decodeStaticStruct(String str, int i, final TypeReference<T> typeReference) {
        return (T) decodeStaticStructElement(str, i, typeReference, new BiFunction() {
            public BiFunction andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj, Object obj2) {
                return TypeDecoder.lambda$decodeStaticStruct$1(TypeReference.this, (List) obj, (String) obj2);
            }
        });
    }

    public static Type lambda$decodeStaticStruct$1(TypeReference typeReference, List list, String str) {
        if (list.isEmpty()) {
            


return null;

//throw new UnsupportedOperationException(
Zero length fixed array is invalid type");
        }
        return instantiateStruct(typeReference, list);
    }

    private static <T extends Type> T decodeStaticStructElement(String str, int i, TypeReference<T> typeReference, BiFunction<List<T>, String, T> biFunction) {
        int length;
        int i2;
        Type decode;
        int i3;
        Parameter[] parameters;
        try {
            Class<T> classType = typeReference.getClassType();
            Constructor constructor = (Constructor) DesugarArrays.stream(classType.getDeclaredConstructors()).filter(new Predicate() {
                public Predicate and(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                public Predicate negate() {
                    return Predicate$-CC.$default$negate(this);
                }

                public Predicate or(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                @Override
                public final boolean test(Object obj) {
                    boolean allMatch;
                    allMatch = DesugarArrays.stream(((Constructor) obj).getParameterTypes()).allMatch(new TypeDecoderExternalSyntheticLambda8(Type.class));
                    return allMatch;
                }
            }).findAny().orElseThrow(new Supplier() {
                @Override
                public final Object get() {
                    return TypeDecoder.lambda$decodeStaticStructElement$3();
                }
            });
            if (Build.VERSION.SDK_INT >= 26) {
                length = constructor.getParameterCount();
            } else {
                length = constructor.getParameterTypes().length;
            }
            ArrayList arrayList = new ArrayList(length);
            int i4 = 0;
            while (i4 < length) {
                Class<?> cls = constructor.getParameterTypes()[i4];
                if (StaticStruct.class.isAssignableFrom(cls)) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        parameters = classType.getDeclaredFields()[i4].getType().getConstructors()[0].getParameters();
                        i3 = parameters.length * 64;
                    } else {
                        i3 = 0;
                    }
                    i2 = i3 + i;
                    decode = decodeStaticStruct(str.substring(i, i2), 0, TypeReference.create(cls));
                } else {
                    i2 = i + 64;
                    decode = decode(str.substring(i, i2), 0, (Class<Type>) cls);
                }
                arrayList.add(decode);
                i4++;
                i = i2;
            }
            return biFunction.apply(arrayList, Utils.getSimpleTypeName(classType));
        } catch (ClassNotFoundException e) {
            


return null;

//throw new UnsupportedOperationException(
Unable to access parameterized type " + Utils.getTypeName(typeReference.getType()), e);
        }
    }

    public static RuntimeException lambda$decodeStaticStructElement$3() {
        return new RuntimeException("TypeReferenced struct must contain a constructor with types that extend Type");
    }

    private static <T extends Type> T instantiateStruct(TypeReference<T> typeReference, List<T> list) {
        try {
            Constructor constructor = (Constructor) DesugarArrays.stream(typeReference.getClassType().getDeclaredConstructors()).filter(new Predicate() {
                public Predicate and(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                public Predicate negate() {
                    return Predicate$-CC.$default$negate(this);
                }

                public Predicate or(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                @Override
                public final boolean test(Object obj) {
                    boolean allMatch;
                    allMatch = DesugarArrays.stream(((Constructor) obj).getParameterTypes()).allMatch(new TypeDecoderExternalSyntheticLambda8(Type.class));
                    return allMatch;
                }
            }).findAny().orElseThrow(new Supplier() {
                @Override
                public final Object get() {
                    return TypeDecoder.lambda$instantiateStruct$5();
                }
            });
            constructor.setAccessible(true);
            return (T) constructor.newInstance(list.toArray());
        } catch (ReflectiveOperationException e) {
            


return null;

//throw new UnsupportedOperationException(
Constructor cannot accept" + Arrays.toString(list.toArray()), e);
        }
    }

    public static RuntimeException lambda$instantiateStruct$5() {
        return new RuntimeException("TypeReference struct must contain a constructor with types that extend Type");
    }

    public static <T extends Type> T decodeDynamicArray(String str, int i, TypeReference<T> typeReference) {
        return (T) decodeArrayElements(str, i + 64, typeReference, decodeUintAsInt(str, i), new BiFunction() {
            public BiFunction andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj, Object obj2) {
                return TypeDecoder.lambda$decodeDynamicArray$6((List) obj, (String) obj2);
            }
        });
    }

    public static Type lambda$decodeDynamicArray$6(List list, String str) {
        return new DynamicArray(AbiTypes.getType(str), list);
    }

    public static <T extends Type> T decodeDynamicStruct(String str, int i, final TypeReference<T> typeReference) {
        return (T) decodeDynamicStructElements(str, i, typeReference, new BiFunction() {
            public BiFunction andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj, Object obj2) {
                return TypeDecoder.lambda$decodeDynamicStruct$7(TypeReference.this, (List) obj, (String) obj2);
            }
        });
    }

    public static Type lambda$decodeDynamicStruct$7(TypeReference typeReference, List list, String str) {
        if (list.isEmpty()) {
            


return null;

//throw new UnsupportedOperationException(
Zero length fixed array is invalid type");
        }
        return instantiateStruct(typeReference, list);
    }

    private static <T extends Type> T decodeDynamicStructElements(String str, int i, TypeReference<T> typeReference, BiFunction<List<T>, String, T> biFunction) {
        int length;
        int intValue;
        int intValue2;
        Type decode;
        int bytes32PaddedLength;
        try {
            Class<T> classType = typeReference.getClassType();
            Constructor constructor = (Constructor) DesugarArrays.stream(classType.getDeclaredConstructors()).filter(new Predicate() {
                public Predicate and(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                public Predicate negate() {
                    return Predicate$-CC.$default$negate(this);
                }

                public Predicate or(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                @Override
                public final boolean test(Object obj) {
                    boolean allMatch;
                    allMatch = DesugarArrays.stream(((Constructor) obj).getParameterTypes()).allMatch(new TypeDecoderExternalSyntheticLambda8(Type.class));
                    return allMatch;
                }
            }).findAny().orElseThrow(new Supplier() {
                @Override
                public final Object get() {
                    return TypeDecoder.lambda$decodeDynamicStructElements$9();
                }
            });
            if (Build.VERSION.SDK_INT >= 26) {
                length = constructor.getParameterCount();
            } else {
                length = constructor.getParameterTypes().length;
            }
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                Class<?> cls = constructor.getParameterTypes()[i3];
                int i4 = i + i2;
                if (isDynamic(cls)) {
                    arrayList.add(Integer.valueOf(length == 1 ? i : decodeDynamicStructDynamicParameterOffset(str.substring(i4, i4 + 64)) + i));
                    i2 += 64;
                } else {
                    if (StaticStruct.class.isAssignableFrom(cls)) {
                        decode = decodeStaticStruct(str.substring(i4), 0, TypeReference.create(cls));
                        bytes32PaddedLength = Utils.staticStructNestedPublicFieldsFlatList(classType).size() * 64;
                    } else {
                        decode = decode(str.substring(i4), 0, (Class<Type>) cls);
                        bytes32PaddedLength = decode.bytes32PaddedLength() * 2;
                    }
                    i2 += bytes32PaddedLength;
                    hashMap.put(Integer.valueOf(i3), decode);
                }
            }
            int dynamicStructDynamicParametersCount = getDynamicStructDynamicParametersCount(constructor.getParameterTypes());
            int i5 = 0;
            for (int i6 = 0; i6 < length; i6++) {
                Class<?> cls2 = constructor.getParameterTypes()[i6];
                if (isDynamic(cls2)) {
                    if (i5 == dynamicStructDynamicParametersCount - 1) {
                        intValue = str.length();
                        intValue2 = ((Integer) arrayList.get(i5)).intValue();
                    } else {
                        intValue = ((Integer) arrayList.get(i5 + 1)).intValue();
                        intValue2 = ((Integer) arrayList.get(i5)).intValue();
                    }
                    hashMap.put(Integer.valueOf(i6), decodeDynamicParameterFromStruct(str, ((Integer) arrayList.get(i5)).intValue(), intValue - intValue2, cls2));
                    i5++;
                }
            }
            String simpleTypeName = Utils.getSimpleTypeName(classType);
            ArrayList arrayList2 = new ArrayList();
            for (int i7 = 0; i7 < length; i7++) {
                arrayList2.add((Type) hashMap.get(Integer.valueOf(i7)));
            }
            return biFunction.apply(arrayList2, simpleTypeName);
        } catch (ClassNotFoundException e) {
            


return null;

//throw new UnsupportedOperationException(
Unable to access parameterized type " + Utils.getTypeName(typeReference.getType()), e);
        }
    }

    public static RuntimeException lambda$decodeDynamicStructElements$9() {
        return new RuntimeException("TypeReferenced struct must contain a constructor with types that extend Type");
    }

    private static <T extends Type> int getDynamicStructDynamicParametersCount(Class<?>[] clsArr) {
        return (int) DesugarArrays.stream(clsArr).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                boolean isDynamic;
                isDynamic = TypeDecoder.isDynamic((Class) obj);
                return isDynamic;
            }
        }).count();
    }

    private static <T extends Type> T decodeDynamicParameterFromStruct(String str, int i, int i2, Class<T> cls) {
        String substring = str.substring(i, i2 + i);
        if (DynamicStruct.class.isAssignableFrom(cls)) {
            return (T) decodeDynamicStruct(substring, 64, TypeReference.create(cls));
        }
        return (T) decode(substring, cls);
    }

    private static int decodeDynamicStructDynamicParameterOffset(String str) {
        return decodeUintAsInt(str, 0) * 2;
    }

    public static <T extends Type> boolean isDynamic(Class<T> cls) {
        return DynamicBytes.class.isAssignableFrom(cls) || Utf8String.class.isAssignableFrom(cls) || DynamicArray.class.isAssignableFrom(cls);
    }

    static BigInteger asBigInteger(Object obj) {
        boolean z;
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).toBigInteger();
        }
        if (obj instanceof String) {
            return Numeric.toBigInt((String) obj);
        }
        if (obj instanceof byte[]) {
            return Numeric.toBigInt((byte[]) obj);
        }
        boolean z2 = obj instanceof Double;
        if (z2 || ((z = obj instanceof Float)) || z2 || z) {
            return BigDecimal.valueOf(((Number) obj).doubleValue()).toBigInteger();
        }
        if (obj instanceof Number) {
            return BigInteger.valueOf(((Number) obj).longValue());
        }
        return null;
    }

    static List arrayToList(Object obj) {
        int length = java.lang.reflect.Array.getLength(obj);
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(java.lang.reflect.Array.get(obj, i));
        }
        return arrayList;
    }

    private static <T extends Type> T instantiateStaticArray(List<T> list, int i) {
        try {
            return (T) Class.forName("org.web3j.abi.datatypes.generated.StaticArray" + i).getConstructor(List.class).newInstance(list);
        } catch (ReflectiveOperationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private static <T extends Type> T decodeArrayElements(String str, int i, TypeReference<T> typeReference, int i2, BiFunction<List<T>, String, T> biFunction) {
        Type decode;
        Type decodeStaticStruct;
        try {
            Class parameterizedTypeFromArray = Utils.getParameterizedTypeFromArray(typeReference);
            int i3 = 0;
            if (StructType.class.isAssignableFrom(parameterizedTypeFromArray)) {
                ArrayList arrayList = new ArrayList(i2);
                int i4 = i;
                while (i3 < i2) {
                    if (DynamicStruct.class.isAssignableFrom(parameterizedTypeFromArray)) {
                        decodeStaticStruct = decodeDynamicStruct(str, DefaultFunctionReturnDecoder.getDataOffset(str, i4, typeReference) + i, TypeReference.create(parameterizedTypeFromArray));
                    } else {
                        decodeStaticStruct = decodeStaticStruct(str, i4, TypeReference.create(parameterizedTypeFromArray));
                    }
                    arrayList.add(decodeStaticStruct);
                    i3++;
                    i4 += getSingleElementLength(str, i4, parameterizedTypeFromArray) * 64;
                }
                return biFunction.apply(arrayList, Utils.getSimpleTypeName(parameterizedTypeFromArray));
            } else if (Array.class.isAssignableFrom(parameterizedTypeFromArray)) {
                


return null;

//throw new UnsupportedOperationException(
Arrays of arrays are not currently supported for external functions, seehttp://solidity.readthedocs.io/en/develop/types.html#members");
            } else {
                ArrayList arrayList2 = new ArrayList(i2);
                int i5 = i;
                while (i3 < i2) {
                    if (isDynamic(parameterizedTypeFromArray)) {
                        decode = decode(str, DefaultFunctionReturnDecoder.getDataOffset(str, i5, typeReference) + i, parameterizedTypeFromArray);
                        i5 += 64;
                    } else {
                        decode = decode(str, i5, parameterizedTypeFromArray);
                        i5 += getSingleElementLength(str, i5, parameterizedTypeFromArray) * 64;
                    }
                    arrayList2.add(decode);
                    i3++;
                }
                return biFunction.apply(arrayList2, Utils.getSimpleTypeName(parameterizedTypeFromArray));
            }
        } catch (ClassNotFoundException e) {
            


return null;

//throw new UnsupportedOperationException(
Unable to access parameterized type " + Utils.getTypeName(typeReference.getType()), e);
        }
    }
}
