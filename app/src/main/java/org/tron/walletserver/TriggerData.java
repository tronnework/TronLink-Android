package org.tron.walletserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TriggerData {
    private String method;
    private Map<String, String> parameterMap;

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setParameterMap(Map<String, String> map) {
        this.parameterMap = map;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TriggerData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TriggerData) {
            TriggerData triggerData = (TriggerData) obj;
            if (triggerData.canEqual(this)) {
                String method = getMethod();
                String method2 = triggerData.getMethod();
                if (method != null ? method.equals(method2) : method2 == null) {
                    Map<String, String> parameterMap = getParameterMap();
                    Map<String, String> parameterMap2 = triggerData.getParameterMap();
                    return parameterMap != null ? parameterMap.equals(parameterMap2) : parameterMap2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String method = getMethod();
        int hashCode = method == null ? 43 : method.hashCode();
        Map<String, String> parameterMap = getParameterMap();
        return ((hashCode + 59) * 59) + (parameterMap != null ? parameterMap.hashCode() : 43);
    }

    public String toString() {
        return "TriggerData(method=" + getMethod() + ", parameterMap=" + getParameterMap() + ")";
    }

    public String getMethodNoParams() {
        if (AddressUtil.isEmpty(this.method)) {
            return "";
        }
        if (this.method.contains("(")) {
            String str = this.method;
            return str.substring(0, str.indexOf("("));
        }
        return this.method;
    }

    private List<String> getMethodParamsList() {
        ArrayList arrayList = new ArrayList();
        if (AddressUtil.isEmpty(this.method) || !this.method.contains("(")) {
            return arrayList;
        }
        String str = this.method;
        String substring = str.substring(str.indexOf("(") + 1, this.method.indexOf(")"));
        return (AddressUtil.isEmpty(substring) || substring.contains("(") || substring.contains(")")) ? arrayList : Arrays.asList(substring.split(","));
    }

    public Map<String, String> getParameterMap() {
        Map<String, String> map = this.parameterMap;
        return map == null ? new HashMap() : map;
    }

    public List<TypeValue> parseDataForTypeValueList() {
        ArrayList arrayList = new ArrayList();
        List<String> methodParamsList = getMethodParamsList();
        Map<String, String> parameterMap = getParameterMap();
        if (methodParamsList != null && parameterMap != null && methodParamsList.size() == parameterMap.size()) {
            for (int i = 0; i < methodParamsList.size(); i++) {
                TypeValue typeValue = new TypeValue();
                String valueOf = String.valueOf(methodParamsList.get(i));
                String valueOf2 = String.valueOf(parameterMap.get(String.valueOf(i)));
                typeValue.setType(valueOf);
                typeValue.setValue(valueOf2);
                arrayList.add(typeValue);
            }
        }
        return arrayList;
    }

    public static class TypeValue {
        String type;
        String value;

        public String getType() {
            return this.type;
        }

        public String getValue() {
            return this.value;
        }

        public void setType(String str) {
            this.type = str;
        }

        public void setValue(String str) {
            this.value = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof TypeValue;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof TypeValue) {
                TypeValue typeValue = (TypeValue) obj;
                if (typeValue.canEqual(this)) {
                    String type = getType();
                    String type2 = typeValue.getType();
                    if (type != null ? type.equals(type2) : type2 == null) {
                        String value = getValue();
                        String value2 = typeValue.getValue();
                        return value != null ? value.equals(value2) : value2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            String type = getType();
            int hashCode = type == null ? 43 : type.hashCode();
            String value = getValue();
            return ((hashCode + 59) * 59) + (value != null ? value.hashCode() : 43);
        }

        public String toString() {
            return "TriggerData.TypeValue(type=" + getType() + ", value=" + getValue() + ")";
        }
    }
}
