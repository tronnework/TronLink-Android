package com.tron.wallet.common.bean;

import java.util.List;
public class AccountBalanceOutput {
    private int code;
    private DataBean data;
    private String message;

    public static class DataBean {
        private List<BalanceListBean> balanceList;
        private double totalBalance;

        public static class BalanceListBean {
            public static final int ACCOUNT_TYPE_MULTI_SIGN = 1;
            public static final int ACCOUNT_TYPE_NORMAL = 0;
            private int accountType;
            private String address;
            private int addressType;
            private double balance;
            private long txNum;

            public int getAccountType() {
                return this.accountType;
            }

            public String getAddress() {
                return this.address;
            }

            public int getAddressType() {
                return this.addressType;
            }

            public double getBalance() {
                return this.balance;
            }

            public long getTxNum() {
                return this.txNum;
            }

            public void setAccountType(int i) {
                this.accountType = i;
            }

            public void setAddress(String str) {
                this.address = str;
            }

            public void setAddressType(int i) {
                this.addressType = i;
            }

            public void setBalance(double d) {
                this.balance = d;
            }

            public void setTxNum(long j) {
                this.txNum = j;
            }
        }

        public List<BalanceListBean> getBalanceList() {
            return this.balanceList;
        }

        public double getTotalBalance() {
            return this.totalBalance;
        }

        public void setBalanceList(List<BalanceListBean> list) {
            this.balanceList = list;
        }

        public void setTotalBalance(double d) {
            this.totalBalance = d;
        }
    }

    public int getCode() {
        return this.code;
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
