package com.tron.wallet.common.utils.addressscam;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
import java.util.HashMap;
import java.util.Map;
public class AddressScamResponseBean extends BaseOutput {
    private HashMap<String, Map<String, Boolean>> data;

    public HashMap<String, Map<String, Boolean>> getData() {
        return this.data;
    }

    public void setData(HashMap<String, Map<String, Boolean>> hashMap) {
        this.data = hashMap;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof AddressScamResponseBean;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddressScamResponseBean) {
            AddressScamResponseBean addressScamResponseBean = (AddressScamResponseBean) obj;
            if (addressScamResponseBean.canEqual(this)) {
                HashMap<String, Map<String, Boolean>> data = getData();
                HashMap<String, Map<String, Boolean>> data2 = addressScamResponseBean.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        HashMap<String, Map<String, Boolean>> data = getData();
        return 59 + (data == null ? 43 : data.hashCode());
    }

    @Override
    public String toString() {
        return "AddressScamResponseBean(data=" + getData() + ")";
    }
}
