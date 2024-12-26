package com.tron.wallet.business.stakev2.managementv2.detail.bean;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
import java.util.List;
public class StakeResourceDetailOutput extends BaseOutput {
    private StakeResourceDetailDataBean data;

    public StakeResourceDetailDataBean getData() {
        return this.data;
    }

    public void setData(StakeResourceDetailDataBean stakeResourceDetailDataBean) {
        this.data = stakeResourceDetailDataBean;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof StakeResourceDetailOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StakeResourceDetailOutput) {
            StakeResourceDetailOutput stakeResourceDetailOutput = (StakeResourceDetailOutput) obj;
            if (stakeResourceDetailOutput.canEqual(this)) {
                StakeResourceDetailDataBean data = getData();
                StakeResourceDetailDataBean data2 = stakeResourceDetailOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        StakeResourceDetailDataBean data = getData();
        return 59 + (data == null ? 43 : data.hashCode());
    }

    @Override
    public String toString() {
        return "StakeResourceDetailOutput(data=" + getData() + ")";
    }

    public class StakeResourceDetailDataBean {
        private List<StakeResourceDetailBean> list;
        private int pageIndex;
        private String totalAmount;

        public List<StakeResourceDetailBean> getList() {
            return this.list;
        }

        public int getPageIndex() {
            return this.pageIndex;
        }

        public String getTotalAmount() {
            return this.totalAmount;
        }

        public void setList(List<StakeResourceDetailBean> list) {
            this.list = list;
        }

        public void setPageIndex(int i) {
            this.pageIndex = i;
        }

        public void setTotalAmount(String str) {
            this.totalAmount = str;
        }

        public StakeResourceDetailDataBean() {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof StakeResourceDetailDataBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof StakeResourceDetailDataBean) {
                StakeResourceDetailDataBean stakeResourceDetailDataBean = (StakeResourceDetailDataBean) obj;
                if (stakeResourceDetailDataBean.canEqual(this) && getPageIndex() == stakeResourceDetailDataBean.getPageIndex()) {
                    String totalAmount = getTotalAmount();
                    String totalAmount2 = stakeResourceDetailDataBean.getTotalAmount();
                    if (totalAmount != null ? totalAmount.equals(totalAmount2) : totalAmount2 == null) {
                        List<StakeResourceDetailBean> list = getList();
                        List<StakeResourceDetailBean> list2 = stakeResourceDetailDataBean.getList();
                        return list != null ? list.equals(list2) : list2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            String totalAmount = getTotalAmount();
            int pageIndex = ((getPageIndex() + 59) * 59) + (totalAmount == null ? 43 : totalAmount.hashCode());
            List<StakeResourceDetailBean> list = getList();
            return (pageIndex * 59) + (list != null ? list.hashCode() : 43);
        }

        public String toString() {
            return "StakeResourceDetailOutput.StakeResourceDetailDataBean(totalAmount=" + getTotalAmount() + ", pageIndex=" + getPageIndex() + ", list=" + getList() + ")";
        }
    }
}
