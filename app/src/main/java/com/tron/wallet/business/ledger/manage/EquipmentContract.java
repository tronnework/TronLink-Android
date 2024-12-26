package com.tron.wallet.business.ledger.manage;

import android.widget.PopupWindow;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.common.interfaces.CloseClickListener;
import io.reactivex.Observable;
import java.util.List;
public interface EquipmentContract {

    public interface Model extends BaseModel {
        Observable<List<EquipmentBean>> getDevices();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        protected abstract void connectEquipment(BleRepoDevice bleRepoDevice, int i);

        protected abstract void disconnectEquipment(BleRepoDevice bleRepoDevice, int i);

        public abstract void getDevices();

        protected abstract void importAddress(BleRepoDevice bleRepoDevice);

        public abstract void refresh();

        protected abstract void removeEquipment(BleRepoDevice bleRepoDevice, int i);
    }

    public interface View extends BaseView {
        void connectedFail(int i, BleRepoDevice bleRepoDevice);

        PopupWindow showLoadingPop(BleRepoDevice bleRepoDevice, LedgerProgressView.STATUS status, CloseClickListener closeClickListener);

        void updateEquipmentList(List<EquipmentBean> list);

        void updateItemStatus(int i, boolean z);
    }
}
