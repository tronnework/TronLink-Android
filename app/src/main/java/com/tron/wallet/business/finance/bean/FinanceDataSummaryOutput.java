package com.tron.wallet.business.finance.bean;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
import java.util.List;
public class FinanceDataSummaryOutput extends BaseOutput {
    private FinanceData data;

    public FinanceData getData() {
        return this.data;
    }

    public void setData(FinanceData financeData) {
        this.data = financeData;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof FinanceDataSummaryOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinanceDataSummaryOutput) {
            FinanceDataSummaryOutput financeDataSummaryOutput = (FinanceDataSummaryOutput) obj;
            if (financeDataSummaryOutput.canEqual(this)) {
                FinanceData data = getData();
                FinanceData data2 = financeDataSummaryOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        FinanceData data = getData();
        return 59 + (data == null ? 43 : data.hashCode());
    }

    @Override
    public String toString() {
        return "FinanceDataSummaryOutput(data=" + getData() + ")";
    }

    public static class FinanceData {
        private DataSummary total;
        private List<DataByAccount> users;

        public DataSummary getTotal() {
            return this.total;
        }

        public List<DataByAccount> getUsers() {
            return this.users;
        }

        public void setTotal(DataSummary dataSummary) {
            this.total = dataSummary;
        }

        public void setUsers(List<DataByAccount> list) {
            this.users = list;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof FinanceData;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof FinanceData) {
                FinanceData financeData = (FinanceData) obj;
                if (financeData.canEqual(this)) {
                    DataSummary total = getTotal();
                    DataSummary total2 = financeData.getTotal();
                    if (total != null ? total.equals(total2) : total2 == null) {
                        List<DataByAccount> users = getUsers();
                        List<DataByAccount> users2 = financeData.getUsers();
                        return users != null ? users.equals(users2) : users2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            DataSummary total = getTotal();
            int hashCode = total == null ? 43 : total.hashCode();
            List<DataByAccount> users = getUsers();
            return ((hashCode + 59) * 59) + (users != null ? users.hashCode() : 43);
        }

        public String toString() {
            return "FinanceDataSummaryOutput.FinanceData(total=" + getTotal() + ", users=" + getUsers() + ")";
        }
    }

    public static class DataSummary {
        private String financialAssetsCny;
        private String financialAssetsUsd;
        private String financialIncomeCny;
        private String financialIncomeUsd;
        private String financialPercent;
        private String totalAssetsCny;
        private String totalAssetsUsd;

        public String getFinancialAssetsCny() {
            return this.financialAssetsCny;
        }

        public String getFinancialAssetsUsd() {
            return this.financialAssetsUsd;
        }

        public String getFinancialIncomeCny() {
            return this.financialIncomeCny;
        }

        public String getFinancialIncomeUsd() {
            return this.financialIncomeUsd;
        }

        public String getFinancialPercent() {
            return this.financialPercent;
        }

        public String getTotalAssetsCny() {
            return this.totalAssetsCny;
        }

        public String getTotalAssetsUsd() {
            return this.totalAssetsUsd;
        }

        public void setFinancialAssetsCny(String str) {
            this.financialAssetsCny = str;
        }

        public void setFinancialAssetsUsd(String str) {
            this.financialAssetsUsd = str;
        }

        public void setFinancialIncomeCny(String str) {
            this.financialIncomeCny = str;
        }

        public void setFinancialIncomeUsd(String str) {
            this.financialIncomeUsd = str;
        }

        public void setFinancialPercent(String str) {
            this.financialPercent = str;
        }

        public void setTotalAssetsCny(String str) {
            this.totalAssetsCny = str;
        }

        public void setTotalAssetsUsd(String str) {
            this.totalAssetsUsd = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof DataSummary;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof DataSummary) {
                DataSummary dataSummary = (DataSummary) obj;
                if (dataSummary.canEqual(this)) {
                    String financialPercent = getFinancialPercent();
                    String financialPercent2 = dataSummary.getFinancialPercent();
                    if (financialPercent != null ? financialPercent.equals(financialPercent2) : financialPercent2 == null) {
                        String financialAssetsCny = getFinancialAssetsCny();
                        String financialAssetsCny2 = dataSummary.getFinancialAssetsCny();
                        if (financialAssetsCny != null ? financialAssetsCny.equals(financialAssetsCny2) : financialAssetsCny2 == null) {
                            String financialAssetsUsd = getFinancialAssetsUsd();
                            String financialAssetsUsd2 = dataSummary.getFinancialAssetsUsd();
                            if (financialAssetsUsd != null ? financialAssetsUsd.equals(financialAssetsUsd2) : financialAssetsUsd2 == null) {
                                String financialIncomeCny = getFinancialIncomeCny();
                                String financialIncomeCny2 = dataSummary.getFinancialIncomeCny();
                                if (financialIncomeCny != null ? financialIncomeCny.equals(financialIncomeCny2) : financialIncomeCny2 == null) {
                                    String financialIncomeUsd = getFinancialIncomeUsd();
                                    String financialIncomeUsd2 = dataSummary.getFinancialIncomeUsd();
                                    if (financialIncomeUsd != null ? financialIncomeUsd.equals(financialIncomeUsd2) : financialIncomeUsd2 == null) {
                                        String totalAssetsCny = getTotalAssetsCny();
                                        String totalAssetsCny2 = dataSummary.getTotalAssetsCny();
                                        if (totalAssetsCny != null ? totalAssetsCny.equals(totalAssetsCny2) : totalAssetsCny2 == null) {
                                            String totalAssetsUsd = getTotalAssetsUsd();
                                            String totalAssetsUsd2 = dataSummary.getTotalAssetsUsd();
                                            return totalAssetsUsd != null ? totalAssetsUsd.equals(totalAssetsUsd2) : totalAssetsUsd2 == null;
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
                return false;
            }
            return false;
        }

        public int hashCode() {
            String financialPercent = getFinancialPercent();
            int hashCode = financialPercent == null ? 43 : financialPercent.hashCode();
            String financialAssetsCny = getFinancialAssetsCny();
            int hashCode2 = ((hashCode + 59) * 59) + (financialAssetsCny == null ? 43 : financialAssetsCny.hashCode());
            String financialAssetsUsd = getFinancialAssetsUsd();
            int hashCode3 = (hashCode2 * 59) + (financialAssetsUsd == null ? 43 : financialAssetsUsd.hashCode());
            String financialIncomeCny = getFinancialIncomeCny();
            int hashCode4 = (hashCode3 * 59) + (financialIncomeCny == null ? 43 : financialIncomeCny.hashCode());
            String financialIncomeUsd = getFinancialIncomeUsd();
            int hashCode5 = (hashCode4 * 59) + (financialIncomeUsd == null ? 43 : financialIncomeUsd.hashCode());
            String totalAssetsCny = getTotalAssetsCny();
            int hashCode6 = (hashCode5 * 59) + (totalAssetsCny == null ? 43 : totalAssetsCny.hashCode());
            String totalAssetsUsd = getTotalAssetsUsd();
            return (hashCode6 * 59) + (totalAssetsUsd != null ? totalAssetsUsd.hashCode() : 43);
        }

        public String toString() {
            return "FinanceDataSummaryOutput.DataSummary(financialPercent=" + getFinancialPercent() + ", financialAssetsCny=" + getFinancialAssetsCny() + ", financialAssetsUsd=" + getFinancialAssetsUsd() + ", financialIncomeCny=" + getFinancialIncomeCny() + ", financialIncomeUsd=" + getFinancialIncomeUsd() + ", totalAssetsCny=" + getTotalAssetsCny() + ", totalAssetsUsd=" + getTotalAssetsUsd() + ")";
        }
    }

    public static class DataByAccount {
        private String accountAddress;
        private String financialAssetsCny;
        private String financialAssetsUsd;
        private String financialIncomeCny;
        private String financialIncomeUsd;
        private String financialPercent;
        private String totalAssetsCny;
        private String totalAssetsUsd;

        public String getAccountAddress() {
            return this.accountAddress;
        }

        public String getFinancialAssetsCny() {
            return this.financialAssetsCny;
        }

        public String getFinancialAssetsUsd() {
            return this.financialAssetsUsd;
        }

        public String getFinancialIncomeCny() {
            return this.financialIncomeCny;
        }

        public String getFinancialIncomeUsd() {
            return this.financialIncomeUsd;
        }

        public String getFinancialPercent() {
            return this.financialPercent;
        }

        public String getTotalAssetsCny() {
            return this.totalAssetsCny;
        }

        public String getTotalAssetsUsd() {
            return this.totalAssetsUsd;
        }

        public void setAccountAddress(String str) {
            this.accountAddress = str;
        }

        public void setFinancialAssetsCny(String str) {
            this.financialAssetsCny = str;
        }

        public void setFinancialAssetsUsd(String str) {
            this.financialAssetsUsd = str;
        }

        public void setFinancialIncomeCny(String str) {
            this.financialIncomeCny = str;
        }

        public void setFinancialIncomeUsd(String str) {
            this.financialIncomeUsd = str;
        }

        public void setFinancialPercent(String str) {
            this.financialPercent = str;
        }

        public void setTotalAssetsCny(String str) {
            this.totalAssetsCny = str;
        }

        public void setTotalAssetsUsd(String str) {
            this.totalAssetsUsd = str;
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
                if (dataByAccount.canEqual(this)) {
                    String financialPercent = getFinancialPercent();
                    String financialPercent2 = dataByAccount.getFinancialPercent();
                    if (financialPercent != null ? financialPercent.equals(financialPercent2) : financialPercent2 == null) {
                        String financialAssetsCny = getFinancialAssetsCny();
                        String financialAssetsCny2 = dataByAccount.getFinancialAssetsCny();
                        if (financialAssetsCny != null ? financialAssetsCny.equals(financialAssetsCny2) : financialAssetsCny2 == null) {
                            String financialAssetsUsd = getFinancialAssetsUsd();
                            String financialAssetsUsd2 = dataByAccount.getFinancialAssetsUsd();
                            if (financialAssetsUsd != null ? financialAssetsUsd.equals(financialAssetsUsd2) : financialAssetsUsd2 == null) {
                                String financialIncomeCny = getFinancialIncomeCny();
                                String financialIncomeCny2 = dataByAccount.getFinancialIncomeCny();
                                if (financialIncomeCny != null ? financialIncomeCny.equals(financialIncomeCny2) : financialIncomeCny2 == null) {
                                    String financialIncomeUsd = getFinancialIncomeUsd();
                                    String financialIncomeUsd2 = dataByAccount.getFinancialIncomeUsd();
                                    if (financialIncomeUsd != null ? financialIncomeUsd.equals(financialIncomeUsd2) : financialIncomeUsd2 == null) {
                                        String totalAssetsCny = getTotalAssetsCny();
                                        String totalAssetsCny2 = dataByAccount.getTotalAssetsCny();
                                        if (totalAssetsCny != null ? totalAssetsCny.equals(totalAssetsCny2) : totalAssetsCny2 == null) {
                                            String totalAssetsUsd = getTotalAssetsUsd();
                                            String totalAssetsUsd2 = dataByAccount.getTotalAssetsUsd();
                                            if (totalAssetsUsd != null ? totalAssetsUsd.equals(totalAssetsUsd2) : totalAssetsUsd2 == null) {
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
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            String financialPercent = getFinancialPercent();
            int hashCode = financialPercent == null ? 43 : financialPercent.hashCode();
            String financialAssetsCny = getFinancialAssetsCny();
            int hashCode2 = ((hashCode + 59) * 59) + (financialAssetsCny == null ? 43 : financialAssetsCny.hashCode());
            String financialAssetsUsd = getFinancialAssetsUsd();
            int hashCode3 = (hashCode2 * 59) + (financialAssetsUsd == null ? 43 : financialAssetsUsd.hashCode());
            String financialIncomeCny = getFinancialIncomeCny();
            int hashCode4 = (hashCode3 * 59) + (financialIncomeCny == null ? 43 : financialIncomeCny.hashCode());
            String financialIncomeUsd = getFinancialIncomeUsd();
            int hashCode5 = (hashCode4 * 59) + (financialIncomeUsd == null ? 43 : financialIncomeUsd.hashCode());
            String totalAssetsCny = getTotalAssetsCny();
            int hashCode6 = (hashCode5 * 59) + (totalAssetsCny == null ? 43 : totalAssetsCny.hashCode());
            String totalAssetsUsd = getTotalAssetsUsd();
            int hashCode7 = (hashCode6 * 59) + (totalAssetsUsd == null ? 43 : totalAssetsUsd.hashCode());
            String accountAddress = getAccountAddress();
            return (hashCode7 * 59) + (accountAddress != null ? accountAddress.hashCode() : 43);
        }

        public String toString() {
            return "FinanceDataSummaryOutput.DataByAccount(financialPercent=" + getFinancialPercent() + ", financialAssetsCny=" + getFinancialAssetsCny() + ", financialAssetsUsd=" + getFinancialAssetsUsd() + ", financialIncomeCny=" + getFinancialIncomeCny() + ", financialIncomeUsd=" + getFinancialIncomeUsd() + ", totalAssetsCny=" + getTotalAssetsCny() + ", totalAssetsUsd=" + getTotalAssetsUsd() + ", accountAddress=" + getAccountAddress() + ")";
        }
    }
}
