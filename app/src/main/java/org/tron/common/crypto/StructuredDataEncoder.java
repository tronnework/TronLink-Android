package org.tron.common.crypto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tron.wallet.business.pull.PullConstants;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.stream.Collectors;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.tron.common.bip32.Numeric;
import org.tron.common.crypto.StructuredData;
import org.tron.common.crypto.datatypes.AbiTypes;
import org.tron.common.crypto.datatypes.Bool;
import org.tron.common.crypto.datatypes.Int;
import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.datatypes.Uint;
import org.tron.common.crypto.datatypes.Utf8String;
import org.tron.common.utils.LogUtils;
import org.tron.config.Parameter;
import org.tron.walletserver.AddressUtil;
public class StructuredDataEncoder {
    public final StructuredData.EIP712Message jsonMessageObject;
    final String arrayTypeRegex = "^([a-zA-Z_$][a-zA-Z_$0-9]*)((\\[([1-9]\\d*)?\\])+)$";
    final Pattern arrayTypePattern = Pattern.compile("^([a-zA-Z_$][a-zA-Z_$0-9]*)((\\[([1-9]\\d*)?\\])+)$");
    final String bytesTypeRegex = "^bytes[0-9][0-9]?$";
    final Pattern bytesTypePattern = Pattern.compile("^bytes[0-9][0-9]?$");
    final String arrayDimensionRegex = "\\[([1-9]\\d*)?\\]";
    final Pattern arrayDimensionPattern = Pattern.compile("\\[([1-9]\\d*)?\\]");
    final String typeRegex = "^[a-zA-Z_$][a-zA-Z_$0-9]*(\\[([1-9]\\d*)*\\])*$";
    final Pattern typePattern = Pattern.compile("^[a-zA-Z_$][a-zA-Z_$0-9]*(\\[([1-9]\\d*)*\\])*$");
    final String identifierRegex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
    final Pattern identifierPattern = Pattern.compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");
    private ArrayList<String> addressParseList = new ArrayList<>();

    public ArrayList<String> getParseAddressList() {
        return this.addressParseList;
    }

    public StructuredDataEncoder(String str) throws IOException, RuntimeException {
        this.jsonMessageObject = parseJSONMessage(str);
    }

    private static byte[] convertArgToBytes(String str) throws Exception {
        BigInteger bigInteger;
        if (!Numeric.containsHexPrefix(str)) {
            try {
                bigInteger = new BigInteger(str);
            } catch (NumberFormatException unused) {
                bigInteger = new BigInteger(str, 16);
            }
            str = Numeric.toHexStringNoPrefix(bigInteger.toByteArray());
            if (str.length() > 64 && str.startsWith("00")) {
                str = str.substring(2);
            }
        }
        return Numeric.hexStringToByteArray(str);
    }

    public Set<String> getDependencies(String str) {
        HashMap<String, List<StructuredData.Entry>> types = this.jsonMessageObject.getTypes();
        HashSet hashSet = new HashSet();
        if (types.containsKey(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            while (arrayList.size() > 0) {
                String str2 = (String) arrayList.get(arrayList.size() - 1);
                arrayList.remove(arrayList.size() - 1);
                hashSet.add(str2);
                for (StructuredData.Entry entry : types.get(str2)) {
                    String type = entry.getType();
                    if (this.arrayTypePattern.matcher(type).find()) {
                        type = type.substring(0, type.indexOf(91));
                    }
                    if (types.containsKey(type) && !hashSet.contains(type)) {
                        arrayList.add(type);
                    }
                }
            }
            return hashSet;
        }
        return hashSet;
    }

    public String encodeStruct(String str) {
        HashMap<String, List<StructuredData.Entry>> types = this.jsonMessageObject.getTypes();
        StringBuilder sb = new StringBuilder(str + "(");
        for (StructuredData.Entry entry : types.get(str)) {
            sb.append(String.format("%s %s,", entry.getType(), entry.getName()));
        }
        return sb.substring(0, sb.length() - 1) + ")";
    }

    public String encodeType(String str) {
        Set<String> dependencies = getDependencies(str);
        dependencies.remove(str);
        ArrayList<String> arrayList = new ArrayList(dependencies);
        Collections.sort(arrayList);
        arrayList.add(0, str);
        StringBuilder sb = new StringBuilder();
        for (String str2 : arrayList) {
            sb.append(encodeStruct(str2));
        }
        return sb.toString();
    }

    public byte[] typeHash(String str) {
        return Numeric.hexStringToByteArray(Hash.sha3String(encodeType(str)));
    }

    public List<Integer> getArrayDimensionsFromDeclaration(String str) {
        this.arrayTypePattern.matcher(str).find();
        Matcher matcher = this.arrayDimensionPattern.matcher(str);
        ArrayList arrayList = new ArrayList();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (group == null) {
                arrayList.add(Integer.valueOf(Integer.parseInt("-1")));
            } else {
                arrayList.add(Integer.valueOf(Integer.parseInt(group)));
            }
        }
        return arrayList;
    }

    public List<Pair> getDepthsAndDimensions(Object obj, int i) {
        if (!(obj instanceof List)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        List<Object> list = (List) obj;
        arrayList.add(new Pair(Integer.valueOf(i), Integer.valueOf(list.size())));
        for (Object obj2 : list) {
            arrayList.addAll(getDepthsAndDimensions(obj2, i + 1));
        }
        return arrayList;
    }

    public List<Integer> getArrayDimensionsFromData(Object obj) throws RuntimeException {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : ((Map) Collection.-EL.stream(getDepthsAndDimensions(obj, 0)).collect(Collectors.groupingBy(new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj2) {
                return ((Pair) obj2).getFirst();
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
            }
        }))).entrySet()) {
            ArrayList arrayList = new ArrayList();
            for (Pair pair : (List) entry.getValue()) {
                arrayList.add((Integer) pair.getSecond());
            }
            hashMap.put((Integer) entry.getKey(), arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry2 : hashMap.entrySet()) {
            TreeSet treeSet = new TreeSet((java.util.Collection) entry2.getValue());
            if (treeSet.size() != 1) {
                throw new RuntimeException(String.format("Depth %d of array data has more than one dimensions", entry2.getKey()));
            }
            arrayList2.add((Integer) Collection.-EL.stream(treeSet).findFirst().get());
        }
        return arrayList2;
    }

    public List<Object> flattenMultidimensionalArray(Object obj) {
        if (!(obj instanceof List)) {
            return new ArrayList<Object>(obj) {
                final Object val$data;

                {
                    this.val$data = obj;
                    add(obj);
                }
            };
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (List) obj) {
            arrayList.addAll(flattenMultidimensionalArray(obj2));
        }
        return arrayList;
    }

    private byte[] convertToEncodedItem(String str, Object obj) {
        try {
            if (!str.toLowerCase().startsWith(Uint.TYPE_NAME) && !str.toLowerCase().startsWith(Int.TYPE_NAME)) {
                if (str.equals(Utf8String.TYPE_NAME)) {
                    return Numeric.hexStringToByteArray(Hash.sha3String((String) obj));
                }
                if (str.equals("bytes")) {
                    return Hash.sha3(Numeric.hexStringToByteArray((String) obj));
                }
                if (str.equals(Bool.TYPE_NAME)) {
                    return Numeric.toBytesPadded(new BigInteger(((Boolean) obj).booleanValue() ? "1" : "0"), 32);
                } else if (this.bytesTypePattern.matcher(str).find()) {
                    byte[] bArr = new byte[0];
                    Class<? extends Type> type = AbiTypes.getType(str);
                    for (Constructor<?> constructor : type.getConstructors()) {
                        try {
                            return Numeric.hexStringToByteArray(TypeEncoder.encode(type.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(Numeric.hexStringToByteArray((String) obj))));
                        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
                        }
                    }
                    return bArr;
                } else {
                    return Numeric.toBytesPadded(new BigInteger(1, convertArgToBytes((String) obj)), 32);
                }
            }
            if (convertToBigInt(obj).signum() >= 0) {
                return Numeric.toBytesPadded(convertToBigInt(obj), 32);
            }
            byte[] byteArray = convertToBigInt(obj).toByteArray();
            byte[] bArr2 = new byte[32];
            for (int i = 0; i < 32; i++) {
                bArr2[i] = -1;
            }
            System.arraycopy(byteArray, 0, bArr2, 32 - byteArray.length, byteArray.length);
            return bArr2;
        } catch (Exception e) {
            LogUtils.e(e);
            return new byte[0];
        }
    }

    private List<Object> getArrayItems(StructuredData.Entry entry, Object obj) {
        List<Integer> arrayDimensionsFromDeclaration = getArrayDimensionsFromDeclaration(entry.getType());
        List<Integer> arrayDimensionsFromData = getArrayDimensionsFromData(obj);
        String format = String.format("Array Data %s has dimensions %s, but expected dimensions are %s", obj.toString(), arrayDimensionsFromData.toString(), arrayDimensionsFromDeclaration.toString());
        if (arrayDimensionsFromDeclaration.size() != arrayDimensionsFromData.size()) {
            throw new RuntimeException(format);
        }
        for (int i = 0; i < arrayDimensionsFromDeclaration.size(); i++) {
            if (arrayDimensionsFromDeclaration.get(i).intValue() != -1 && !arrayDimensionsFromDeclaration.get(i).equals(arrayDimensionsFromData.get(i))) {
                throw new RuntimeException(format);
            }
        }
        return flattenMultidimensionalArray(obj);
    }

    public byte[] encodeData(java.lang.String r12, java.util.HashMap<java.lang.String, java.lang.Object> r13) throws java.lang.RuntimeException {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.common.crypto.StructuredDataEncoder.encodeData(java.lang.String, java.util.HashMap):byte[]");
    }

    public void lambda$encodeData$0$org-tron-common-crypto-StructuredDataEncoder(ArrayList arrayList, String str) {
        if (str.startsWith(Parameter.CommonConstant.ADD_PRE_FIX_STRING)) {
            this.addressParseList.add(str);
        }
        arrayList.add(AddressUtil.replace41Address(str));
    }

    private BigInteger convertToBigInt(Object obj) throws NumberFormatException, NullPointerException {
        if (obj.toString().startsWith("0x")) {
            return Numeric.toBigInt(obj.toString());
        }
        return new BigInteger(obj.toString());
    }

    public byte[] hashMessage(String str, HashMap<String, Object> hashMap) throws RuntimeException {
        return Hash.sha3(encodeData(str, hashMap));
    }

    public byte[] hashMessage() throws RuntimeException {
        return hashMessage(this.jsonMessageObject.getPrimaryType(), (HashMap) this.jsonMessageObject.getMessage());
    }

    public byte[] hashDomain() throws RuntimeException {
        HashMap<String, Object> hashMap = (HashMap) new ObjectMapper().convertValue(this.jsonMessageObject.getDomain(), HashMap.class);
        if (hashMap.get(PullConstants.CHAIN_ID) != null) {
            hashMap.put(PullConstants.CHAIN_ID, ((HashMap) hashMap.get(PullConstants.CHAIN_ID)).get("value"));
        } else {
            hashMap.remove(PullConstants.CHAIN_ID);
        }
        if (hashMap.get("verifyingContract") != null) {
            hashMap.put("verifyingContract", ((HashMap) hashMap.get("verifyingContract")).get("value"));
        } else {
            hashMap.remove("verifyingContract");
        }
        if (hashMap.get("salt") == null) {
            hashMap.remove("salt");
        }
        return Hash.sha3(encodeData(this.jsonMessageObject.getTypes().containsKey("TIP712Domain") ? "TIP712Domain" : "EIP712Domain", hashMap));
    }

    public void validateStructuredData(StructuredData.EIP712Message eIP712Message) throws RuntimeException {
        for (String str : eIP712Message.getTypes().keySet()) {
            for (StructuredData.Entry entry : eIP712Message.getTypes().get(str)) {
                if (!this.identifierPattern.matcher(entry.getName()).find()) {
                    throw new RuntimeException(String.format("Invalid Identifier %s in %s", entry.getName(), str));
                }
                if (!this.typePattern.matcher(entry.getType()).find()) {
                    throw new RuntimeException(String.format("Invalid Type %s in %s", entry.getType(), str));
                }
            }
        }
    }

    public StructuredData.EIP712Message parseJSONMessage(String str) throws IOException, RuntimeException {
        StructuredData.EIP712Message eIP712Message = (StructuredData.EIP712Message) new ObjectMapper().readValue(str, StructuredData.EIP712Message.class);
        validateStructuredData(eIP712Message);
        return eIP712Message;
    }

    public StructuredData.EIP712Domain getDomain() {
        return this.jsonMessageObject.getDomain();
    }

    public Object getMessage() {
        return this.jsonMessageObject.getMessage();
    }

    public byte[] getStructuredData() throws RuntimeException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = "\u0019\u0001".getBytes();
        byteArrayOutputStream.write(bytes, 0, bytes.length);
        byte[] hashDomain = hashDomain();
        byteArrayOutputStream.write(hashDomain, 0, hashDomain.length);
        byte[] hashMessage = hashMessage(this.jsonMessageObject.getPrimaryType(), (HashMap) this.jsonMessageObject.getMessage());
        byteArrayOutputStream.write(hashMessage, 0, hashMessage.length);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] hashStructuredData() throws RuntimeException {
        return Hash.sha3(getStructuredData());
    }
}
