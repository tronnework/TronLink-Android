package com.tron.wallet.business.confirm.parambuilder;

import android.content.Intent;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
public class BaseConfirmTransParamBuilder {
    public static final String INTENT_PARAM = "intent_param";
    public BaseParam param;

    public BaseParam get() {
        return this.param;
    }

    public void setParam(BaseParam baseParam) {
        this.param = baseParam;
    }

    public void build(Intent intent) {
        BaseParam baseParam = this.param;
        if (baseParam != null) {
            intent.putExtra(INTENT_PARAM, baseParam);
        }
    }
}
