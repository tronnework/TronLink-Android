package com.tron.wallet.business.finance.bean;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
import java.util.List;
public class FinanceAccountBalanceOutput extends BaseOutput {
    private List<DataByAccount> data;

    public List<DataByAccount> getData() {
        return this.data;
    }

    public void setData(List<DataByAccount> list) {
        this.data = list;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof FinanceAccountBalanceOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinanceAccountBalanceOutput) {
            FinanceAccountBalanceOutput financeAccountBalanceOutput = (FinanceAccountBalanceOutput) obj;
            if (financeAccountBalanceOutput.canEqual(this)) {
                List<DataByAccount> data = getData();
                List<DataByAccount> data2 = financeAccountBalanceOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        List<DataByAccount> data = getData();
        return 59 + (data == null ? 43 : data.hashCode());
    }

    @Override
    public String toString() {
        return "FinanceAccountBalanceOutput(data=" + getData() + ")";
    }

    public static class DataByAccount {
        private String accountAddress;
        private String balance;
        private int precision;
        private String tokenIcon;
        private String tokenId;
        private String tokenName;

        public String getAccountAddress() {
            return this.accountAddress;
        }

        public String getBalance() {
            return this.balance;
        }

        public int getPrecision() {
            return this.precision;
        }

        public String getTokenIcon() {
            return this.tokenIcon;
        }

        public String getTokenId() {
            return this.tokenId;
        }

        public String getTokenName() {
            return this.tokenName;
        }

        public void setAccountAddress(String str) {
            this.accountAddress = str;
        }

        public void setBalance(String str) {
            this.balance = str;
        }

        public void setPrecision(int i) {
            this.precision = i;
        }

        public void setTokenIcon(String str) {
            this.tokenIcon = str;
        }

        public void setTokenId(String str) {
            this.tokenId = str;
        }

        public void setTokenName(String str) {
            this.tokenName = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof DataByAccount;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof DataByAccount) {
                DataByAccount dataByAccount = (DataByAccount) obj;
                if (dataByAccount.canEqual(this) && getPrecision() == dataByAccount.getPrecision()) {
                    String balance = getBalance();
                    String balance2 = dataByAccount.getBalance();
                    if (balance != null ? balance.equals(balance2) : balance2 == null) {
                        String tokenId = getTokenId();
                        String tokenId2 = dataByAccount.getTokenId();
                        if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                            String tokenName = getTokenName();
                            String tokenName2 = dataByAccount.getTokenName();
                            if (tokenName != null ? tokenName.equals(tokenName2) : tokenName2 == null) {
                                String tokenIcon = getTokenIcon();
                                String tokenIcon2 = dataByAccount.getTokenIcon();
                                if (tokenIcon != null ? tokenIcon.equals(tokenIcon2) : tokenIcon2 == null) {
                                    String accountAddress = getAccountAddress();
                                    String accountAddress2 = dataByAccount.getAccountAddress();
                                    return accountAddress != null ? accountAddress.equals(accountAddress2) : accountAddress2 == null;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            String balance = getBalance();
            int precision = ((getPrecision() + 59) * 59) + (balance == null ? 43 : balance.hashCode());
            String tokenId = getTokenId();
            int hashCode = (precision * 59) + (tokenId == null ? 43 : tokenId.hashCode());
            String tokenName = getTokenName();
            int hashCode2 = (hashCode * 59) + (tokenName == null ? 43 : tokenName.hashCode());
            String tokenIcon = getTokenIcon();
            int hashCode3 = (hashCode2 * 59) + (tokenIcon == null ? 43 : tokenIcon.hashCode());
            String accountAddress = getAccountAddress();
            return (hashCode3 * 59) + (accountAddress != null ? accountAddress.hashCode() : 43);
        }

        public String toString() {
            return "FinanceAccountBalanceOutput.DataByAccount(balance=" + getBalance() + ", tokenId=" + getTokenId() + ", tokenName=" + getTokenName() + ", tokenIcon=" + getTokenIcon() + ", precision=" + getPrecision() + ", accountAddress=" + getAccountAddress() + ")";
        }
    }
}
