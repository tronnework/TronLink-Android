package org.tron.common.crypto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import org.tron.common.crypto.datatypes.Address;
import org.tron.common.crypto.datatypes.DynamicBytes;
import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.spi.FunctionReturnDecoderProvider;
public abstract class FunctionReturnDecoder {
    private static final FunctionReturnDecoder decoder;

    protected abstract <T extends Type> Type decodeEventParameter(String str, TypeReference<T> typeReference);

    protected abstract List<Type> decodeFunctionResult(String str, List<TypeReference<Type>> list);

    static {
        Iterator it = ServiceLoader.load(FunctionReturnDecoderProvider.class).iterator();
        decoder = it.hasNext() ? ((FunctionReturnDecoderProvider) it.next()).get() : new DefaultFunctionReturnDecoder();
    }

    public static List<Type> decode(String str, List<TypeReference<Type>> list) {
        return decoder.decodeFunctionResult(str, list);
    }

    public static byte[] decodeDynamicBytes(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TypeReference<DynamicBytes>() {
        });
        List<Type> decodeFunctionResult = decoder.decodeFunctionResult(str, arrayList);
        if (decodeFunctionResult.isEmpty()) {
            return null;
        }
        return ((DynamicBytes) decodeFunctionResult.get(0)).getValue();
    }

    public static String decodeAddress(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TypeReference<Address>() {
        });
        List<Type> decodeFunctionResult = decoder.decodeFunctionResult(str, arrayList);
        if (decodeFunctionResult.isEmpty()) {
            return null;
        }
        return ((Address) decodeFunctionResult.get(0)).getValue();
    }

    public static <T extends Type> Type decodeIndexedValue(String str, TypeReference<T> typeReference) {
        return decoder.decodeEventParameter(str, typeReference);
    }
}
