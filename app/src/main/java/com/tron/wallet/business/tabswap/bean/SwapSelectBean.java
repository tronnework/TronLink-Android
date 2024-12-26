package com.tron.wallet.business.tabswap.bean;

import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import java.io.Serializable;
public class SwapSelectBean implements Serializable {
    SwapWhiteListOutput.Data data;
    int position;

    public int getPosition() {
        return this.position;
    }

    public SwapWhiteListOutput.Data getSwapWhiteListOutput() {
        return this.data;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setSwapWhiteListOutput(SwapWhiteListOutput.Data data) {
        this.data = data;
    }

    public SwapSelectBean(SwapWhiteListOutput.Data data, int i) {
        this.data = data;
        this.position = i;
    }

    public String toString() {
        return "SwapSelectBean{swapWhiteListOutput.Data=" + this.data + ", position=" + this.position + '}';
    }
}
