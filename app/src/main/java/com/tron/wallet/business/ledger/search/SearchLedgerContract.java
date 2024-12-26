package com.tron.wallet.business.ledger.search;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.ledger.manage.EquipmentBean;
import java.util.List;
public interface SearchLedgerContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void connect(EquipmentBean equipmentBean, int i);

        abstract void startScan();

        abstract void stopScan();
    }

    public interface View extends BaseView {
        void showErrorView();

        void showNoDataTips();

        void updateData(List<EquipmentBean> list);
    }
}
