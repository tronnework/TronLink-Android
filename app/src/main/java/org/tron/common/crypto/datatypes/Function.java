package org.tron.common.crypto.datatypes;

import java.util.List;
import org.tron.common.crypto.TypeReference;
import org.tron.common.crypto.Utils;
public class Function {
    private List<Type> inputParameters;
    private String name;
    private List<TypeReference<Type>> outputParameters;

    public List<Type> getInputParameters() {
        return this.inputParameters;
    }

    public String getName() {
        return this.name;
    }

    public List<TypeReference<Type>> getOutputParameters() {
        return this.outputParameters;
    }

    public Function(String str, List<Type> list, List<TypeReference<?>> list2) {
        this.name = str;
        this.inputParameters = list;
        this.outputParameters = Utils.convert(list2);
    }
}
