package com.tron.wallet.business.tabswap.record;

import com.tron.wallet.business.tabswap.record.SwapRecordContract;
import com.tron.wallet.db.Controller.JustSwapTransactionController;
import com.tron.wallet.db.bean.JustSwapBean;
import java.util.List;
public class SwapRecordModel implements SwapRecordContract.Model {
    @Override
    public List<JustSwapBean> requestRecords(String str, int i, int i2) {
        return JustSwapTransactionController.getInstance().getNotesByAddress(str, i, i2);
    }
}
