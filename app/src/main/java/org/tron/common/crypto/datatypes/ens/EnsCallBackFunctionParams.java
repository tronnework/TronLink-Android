package org.tron.common.crypto.datatypes.ens;

import java.util.Arrays;
import java.util.List;
import org.tron.common.crypto.datatypes.DynamicBytes;
import org.tron.common.crypto.datatypes.Type;
public class EnsCallBackFunctionParams {
    private DynamicBytes extraData;
    private DynamicBytes gatewayResponse;

    public DynamicBytes getExtraData() {
        return this.extraData;
    }

    public DynamicBytes getGatewayResponse() {
        return this.gatewayResponse;
    }

    public void setExtraData(DynamicBytes dynamicBytes) {
        this.extraData = dynamicBytes;
    }

    public void setGatewayResponse(DynamicBytes dynamicBytes) {
        this.gatewayResponse = dynamicBytes;
    }

    public EnsCallBackFunctionParams(DynamicBytes dynamicBytes, DynamicBytes dynamicBytes2) {
        this.gatewayResponse = dynamicBytes;
        this.extraData = dynamicBytes2;
    }

    public EnsCallBackFunctionParams(byte[] bArr, byte[] bArr2) {
        this.gatewayResponse = new DynamicBytes(bArr);
        this.extraData = new DynamicBytes(bArr2);
    }

    public List<Type> getParams() {
        return Arrays.asList(this.gatewayResponse, this.extraData);
    }
}
