package com.tron.wallet.common.bean;

import java.util.List;
public class ChainparametersOutPut {
    public List<TronParametersBean> tronParameters;

    public static class TronParametersBean {
        public String key;
        public long value;
    }
}
