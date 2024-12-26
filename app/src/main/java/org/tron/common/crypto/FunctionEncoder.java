package org.tron.common.crypto;

import j$.util.Collection;
import j$.util.Objects;
import j$.util.stream.Collectors;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import org.tron.common.bip32.Numeric;
import org.tron.common.crypto.datatypes.Function;
import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.spi.FunctionEncoderProvider;
public abstract class FunctionEncoder {
    private static final FunctionEncoder FUNCTION_ENCODER;

    protected abstract String encodeFunction(Function function);

    protected abstract String encodePackedParameters(List<Type> list);

    protected abstract String encodeParameters(List<Type> list);

    protected abstract String encodeWithSelector(String str, List<Type> list);

    static {
        Iterator it = ServiceLoader.load(FunctionEncoderProvider.class).iterator();
        FUNCTION_ENCODER = it.hasNext() ? ((FunctionEncoderProvider) it.next()).get() : new DefaultFunctionEncoder();
    }

    public static String encode(Function function) {
        return FUNCTION_ENCODER.encodeFunction(function);
    }

    public static String encode(String str, List<Type> list) {
        return FUNCTION_ENCODER.encodeWithSelector(str, list);
    }

    public static String encodeConstructor(List<Type> list) {
        return FUNCTION_ENCODER.encodeParameters(list);
    }

    public static String encodeConstructorPacked(List<Type> list) {
        return FUNCTION_ENCODER.encodePackedParameters(list);
    }

    public static Function makeFunction(String str, List<String> list, List<Object> list2, List<String> list3) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it = list2.iterator();
        for (String str2 : list) {
            arrayList.add(TypeDecoder.instantiateType(str2, it.next()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str3 : list3) {
            arrayList2.add(TypeReference.makeTypeReference(str3));
        }
        return new Function(str, arrayList, arrayList2);
    }

    public static String buildMethodSignature(String str, List<Type> list) {
        return str + "(" + ((String) Collection.-EL.stream(list).map(new java.util.function.Function() {
            public java.util.function.Function andThen(java.util.function.Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                return ((Type) obj).getTypeAsString();
            }

            public java.util.function.Function compose(java.util.function.Function function) {
                return Objects.requireNonNull(function);
            }
        }).collect(Collectors.joining(","))) + ")";
    }

    public static String buildMethodId(String str) {
        return Numeric.toHexString(Hash.sha3(str.getBytes())).substring(0, 10);
    }
}
