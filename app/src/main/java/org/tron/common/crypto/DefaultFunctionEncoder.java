package org.tron.common.crypto;

import java.math.BigInteger;
import java.util.List;
import org.tron.common.crypto.datatypes.DynamicStruct;
import org.tron.common.crypto.datatypes.Function;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.StaticStruct;
import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.datatypes.Uint;
public class DefaultFunctionEncoder extends FunctionEncoder {
    @Override
    public String encodeFunction(Function function) {
        List<Type> inputParameters = function.getInputParameters();
        return encodeParameters(inputParameters, new StringBuilder(buildMethodId(buildMethodSignature(function.getName(), inputParameters))));
    }

    @Override
    public String encodeParameters(List<Type> list) {
        return encodeParameters(list, new StringBuilder());
    }

    @Override
    public String encodeWithSelector(String str, List<Type> list) {
        return encodeParameters(list, new StringBuilder(str));
    }

    @Override
    protected String encodePackedParameters(List<Type> list) {
        StringBuilder sb = new StringBuilder();
        for (Type type : list) {
            sb.append(TypeEncoder.encodePacked(type));
        }
        return sb.toString();
    }

    private static String encodeParameters(List<Type> list, StringBuilder sb) {
        int length = getLength(list) * 32;
        StringBuilder sb2 = new StringBuilder();
        for (Type type : list) {
            String encode = TypeEncoder.encode(type);
            if (TypeEncoder.isDynamic(type)) {
                sb.append(TypeEncoder.encodeNumeric(new Uint(BigInteger.valueOf(length))));
                sb2.append(encode);
                length += encode.length() >> 1;
            } else {
                sb.append(encode);
            }
        }
        sb.append((CharSequence) sb2);
        return sb.toString();
    }

    private static int getLength(List<Type> list) {
        int size;
        int i = 0;
        for (Type type : list) {
            boolean z = type instanceof StaticArray;
            if (z) {
                StaticArray staticArray = (StaticArray) type;
                if (StaticStruct.class.isAssignableFrom(staticArray.getComponentType())) {
                    size = Utils.staticStructNestedPublicFieldsFlatList(staticArray.getComponentType()).size() * staticArray.getValue().size();
                    i += size;
                }
            }
            if (!(z && DynamicStruct.class.isAssignableFrom(((StaticArray) type).getComponentType())) && z) {
                size = ((StaticArray) type).getValue().size();
                i += size;
            } else {
                i++;
            }
        }
        return i;
    }
}
