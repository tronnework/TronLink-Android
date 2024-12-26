package com.tron.wallet.common.bean;

import java.util.List;
public class AllTransferOutput {
    public List<DataBean> data;
    public int total;

    public static class DataBean {
        public boolean confirmed;
        public ContractDataBean contractData;
        public int contractType;
        public String hash;
        public OtherContractDataBean otherContractData;
        public String ownerAddress;
        public long timestamp;
        public String toAddress;

        public static class ContractDataBean {
            public String account_address;
            public String account_name;
            public long amount;
            public String asset_name;
            public long balance;
            public long count;
            public long from_amount;
            public long frozen_balance;
            public long frozen_duration;
            public boolean is_add_approval;
            public String name;
            public String owner_address;
            public long proposal_id;
            public String resource;
            public String to_address;
            public long total_supply;
            public int type;
            public long unfreeze_balance;
            public String update_url;
            public String url;
            public long vote_count;
            public List<VotesBean> votes;

            public static class VotesBean {
                public String vote_address;
                public long vote_count;
            }
        }

        public static class OtherContractDataBean {
            public int amount;
            public String asset_name;
            public String owner_address;
            public String to_address;
        }
    }
}
