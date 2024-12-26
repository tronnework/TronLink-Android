package com.tron.wallet.business.tabmy.myhome.settings.bean;

import java.util.List;
public class NodeOutput {
    public String code;
    public DataBean data;

    public static class DataBean {
        public List<String> fullnode;
        public List<String> solidity_node;
    }
}
