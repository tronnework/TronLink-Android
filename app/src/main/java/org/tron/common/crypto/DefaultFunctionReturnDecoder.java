package org.tron.common.crypto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.tron.common.bip32.Numeric;
import org.tron.common.crypto.TypeReference;
import org.tron.common.crypto.datatypes.Array;
import org.tron.common.crypto.datatypes.Bytes;
import org.tron.common.crypto.datatypes.BytesType;
import org.tron.common.crypto.datatypes.DynamicArray;
import org.tron.common.crypto.datatypes.DynamicBytes;
import org.tron.common.crypto.datatypes.DynamicStruct;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.StaticStruct;
import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.datatypes.Utf8String;
import org.tron.common.crypto.datatypes.generated.Bytes32;
import org.tron.walletserver.AddressUtil;
public class DefaultFunctionReturnDecoder extends FunctionReturnDecoder {
    @Override
    public List<Type> decodeFunctionResult(String str, List<TypeReference<Type>> list) {
        String cleanHexPrefix = Numeric.cleanHexPrefix(str);
        if (AddressUtil.isEmpty(cleanHexPrefix)) {
            return Collections.emptyList();
        }
        return build(cleanHexPrefix, list);
    }

    @Override
    public <T extends Type> Type decodeEventParameter(String str, TypeReference<T> typeReference) {
        String cleanHexPrefix = Numeric.cleanHexPrefix(str);
        try {
            Class<T> classType = typeReference.getClassType();
            if (Bytes.class.isAssignableFrom(classType)) {
                return TypeDecoder.decodeBytes(cleanHexPrefix, Class.forName(classType.getName()));
            }
            if (!Array.class.isAssignableFrom(classType) && !BytesType.class.isAssignableFrom(classType) && !Utf8String.class.isAssignableFrom(classType)) {
                return TypeDecoder.decode(cleanHexPrefix, classType);
            }
            return TypeDecoder.decodeBytes(cleanHexPrefix, Bytes32.class);
        } catch (ClassNotFoundException e) {
            


return null;

//throw new UnsupportedOperationException(
Invalid class reference provided", e);
        }
    }

    private static List<Type> build(String str, List<TypeReference<Type>> list) {
        Type decode;
        ArrayList arrayList = new ArrayList(list.size());
        int i = 0;
        for (TypeReference<Type> typeReference : list) {
            try {
                int dataOffset = getDataOffset(str, i, typeReference);
                Class<Type> classType = typeReference.getClassType();
                if (DynamicStruct.class.isAssignableFrom(classType)) {
                    decode = TypeDecoder.decodeDynamicStruct(str, dataOffset, typeReference);
                } else if (DynamicArray.class.isAssignableFrom(classType)) {
                    decode = TypeDecoder.decodeDynamicArray(str, dataOffset, typeReference);
                } else {
                    if (typeReference instanceof TypeReference.StaticArrayTypeReference) {
                        int size = ((TypeReference.StaticArrayTypeReference) typeReference).getSize();
                        decode = TypeDecoder.decodeStaticArray(str, dataOffset, typeReference, size);
                        i += size * 64;
                    } else if (StaticStruct.class.isAssignableFrom(classType)) {
                        decode = TypeDecoder.decodeStaticStruct(str, dataOffset, typeReference);
                        i += Utils.staticStructNestedPublicFieldsFlatList(classType).size() * 64;
                    } else if (StaticArray.class.isAssignableFrom(classType)) {
                        int parseInt = Integer.parseInt(classType.getSimpleName().substring(11));
                        Type decodeStaticArray = TypeDecoder.decodeStaticArray(str, dataOffset, typeReference, parseInt);
                        if (!DynamicStruct.class.isAssignableFrom(Utils.getParameterizedTypeFromArray(typeReference))) {
                            if (StaticStruct.class.isAssignableFrom(Utils.getParameterizedTypeFromArray(typeReference))) {
                                i += Utils.staticStructNestedPublicFieldsFlatList(Utils.getParameterizedTypeFromArray(typeReference)).size() * parseInt * 64;
                            } else if (!Utf8String.class.isAssignableFrom(Utils.getParameterizedTypeFromArray(typeReference))) {
                                i += parseInt * 64;
                            }
                            decode = decodeStaticArray;
                        }
                        i += 64;
                        decode = decodeStaticArray;
                    } else {
                        decode = TypeDecoder.decode(str, dataOffset, classType);
                    }
                    arrayList.add(decode);
                }
                i += 64;
                arrayList.add(decode);
            } catch (ClassNotFoundException e) {
                


return null;

//throw new UnsupportedOperationException(
Invalid class reference provided", e);
            }
        }
        return arrayList;
    }

    public static <T extends Type> int getDataOffset(String str, int i, TypeReference<?> typeReference) throws ClassNotFoundException {
        Class<?> classType = typeReference.getClassType();
        return (DynamicBytes.class.isAssignableFrom(classType) || Utf8String.class.isAssignableFrom(classType) || DynamicArray.class.isAssignableFrom(classType) || hasDynamicOffsetInStaticArray(typeReference, i)) ? TypeDecoder.decodeUintAsInt(str, i) << 1 : i;
    }

    private static boolean hasDynamicOffsetInStaticArray(TypeReference<?> typeReference, int i) throws ClassNotFoundException {
        try {
            if (StaticArray.class.isAssignableFrom(typeReference.getClassType())) {
                if (!DynamicStruct.class.isAssignableFrom(Utils.getParameterizedTypeFromArray(typeReference))) {
                    if (!TypeDecoder.isDynamic(Utils.getParameterizedTypeFromArray(typeReference))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (ClassCastException unused) {
            return false;
        }
    }
}
