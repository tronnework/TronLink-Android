package com.tron.wallet.business.stakev2.managementv2.detail.bean;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
import java.util.List;
public class StakeResourceDetailForMeOutput extends BaseOutput {
    private List<StakeResourceDetailForMeBean> data;

    public List<StakeResourceDetailForMeBean> getData() {
        return this.data;
    }

    public void setData(List<StakeResourceDetailForMeBean> list) {
        this.data = list;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof StakeResourceDetailForMeOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StakeResourceDetailForMeOutput) {
            StakeResourceDetailForMeOutput stakeResourceDetailForMeOutput = (StakeResourceDetailForMeOutput) obj;
            if (stakeResourceDetailForMeOutput.canEqual(this)) {
                List<StakeResourceDetailForMeBean> data = getData();
                List<StakeResourceDetailForMeBean> data2 = stakeResourceDetailForMeOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        List<StakeResourceDetailForMeBean> data = getData();
        return 59 + (data == null ? 43 : data.hashCode());
    }

    @Override
    public String toString() {
        return "StakeResourceDetailForMeOutput(data=" + getData() + ")";
    }
}
