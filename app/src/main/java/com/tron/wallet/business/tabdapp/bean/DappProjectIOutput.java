package com.tron.wallet.business.tabdapp.bean;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
public class DappProjectIOutput extends BaseOutput {
    private DappProjectInfoBean data;

    public DappProjectInfoBean getData() {
        return this.data;
    }

    public void setData(DappProjectInfoBean dappProjectInfoBean) {
        this.data = dappProjectInfoBean;
    }

    @Override
    public String toString() {
        return "DappProjectIOutput(super=" + super.toString() + ", data=" + getData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof DappProjectIOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DappProjectIOutput) {
            DappProjectIOutput dappProjectIOutput = (DappProjectIOutput) obj;
            if (dappProjectIOutput.canEqual(this) && super.equals(obj)) {
                DappProjectInfoBean data = getData();
                DappProjectInfoBean data2 = dappProjectIOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        DappProjectInfoBean data = getData();
        return (hashCode * 59) + (data == null ? 43 : data.hashCode());
    }
}
