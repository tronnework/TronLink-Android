package com.tron.wallet.common.bean.token;

import java.util.List;
public class TransactionBean2 {
    private String address;
    private String functionSelector;
    private String netType;
    private TransactionBean transaction;

    public static class TransactionBean {
        private RawDataBean raw_data;
        private List<String> signature;

        public static class RawDataBean {
            private List<ContractBean> contract;
            private String data;
            private long expiration;
            private long fee_limit;
            private String ref_block_bytes;
            private String ref_block_hash;
            private long timestamp;

            public static class ContractBean {
                private int Permission_id;
                private ParameterBean parameter;
                private String type;

                public static class ParameterBean {
                    private String type_url;
                    private String value;

                    public String getType_url() {
                        return this.type_url;
                    }

                    public String getValue() {
                        return this.value;
                    }

                    public void setType_url(String str) {
                        this.type_url = str;
                    }

                    public void setValue(String str) {
                        this.value = str;
                    }
                }

                public ParameterBean getParameter() {
                    return this.parameter;
                }

                public int getPermission_id() {
                    return this.Permission_id;
                }

                public String getType() {
                    return this.type;
                }

                public void setParameter(ParameterBean parameterBean) {
                    this.parameter = parameterBean;
                }

                public void setPermission_id(int i) {
                    this.Permission_id = i;
                }

                public void setType(String str) {
                    this.type = str;
                }
            }

            public List<ContractBean> getContract() {
                return this.contract;
            }

            public String getData() {
                return this.data;
            }

            public long getExpiration() {
                return this.expiration;
            }

            public long getFee_limit() {
                return this.fee_limit;
            }

            public String getRef_block_bytes() {
                return this.ref_block_bytes;
            }

            public String getRef_block_hash() {
                return this.ref_block_hash;
            }

            public long getTimestamp() {
                return this.timestamp;
            }

            public void setContract(List<ContractBean> list) {
                this.contract = list;
            }

            public void setData(String str) {
                this.data = str;
            }

            public void setExpiration(long j) {
                this.expiration = j;
            }

            public void setFee_limit(long j) {
                this.fee_limit = j;
            }

            public void setRef_block_bytes(String str) {
                this.ref_block_bytes = str;
            }

            public void setRef_block_hash(String str) {
                this.ref_block_hash = str;
            }

            public void setTimestamp(long j) {
                this.timestamp = j;
            }
        }

        public RawDataBean getRaw_data() {
            return this.raw_data;
        }

        public List<String> getSignature() {
            return this.signature;
        }

        public void setRaw_data(RawDataBean rawDataBean) {
            this.raw_data = rawDataBean;
        }

        public void setSignature(List<String> list) {
            this.signature = list;
        }
    }

    public String getAddress() {
        return this.address;
    }

    public String getFunctionSelector() {
        return this.functionSelector;
    }

    public String getNetType() {
        return this.netType;
    }

    public TransactionBean getTransaction() {
        return this.transaction;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setFunctionSelector(String str) {
        this.functionSelector = str;
    }

    public void setNetType(String str) {
        this.netType = str;
    }

    public void setTransaction(TransactionBean transactionBean) {
        this.transaction = transactionBean;
    }
}
