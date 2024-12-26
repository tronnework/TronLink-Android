package com.tron.wallet.business.ledger.manage;

import com.tron.wallet.business.ledger.manage.EquipmentContract;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.dao.BleDeviceDaoManager;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.ledger.bleclient.BleClientManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class EquipmentModel implements EquipmentContract.Model {
    @Override
    public Observable<List<EquipmentBean>> getDevices() {
        return BleDeviceDaoManager.getInstance().queryAll().flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return EquipmentModel.lambda$getDevices$0((List) obj);
            }
        });
    }

    public static ObservableSource lambda$getDevices$0(List list) throws Exception {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            BleRepoDevice bleRepoDevice = (BleRepoDevice) it.next();
            EquipmentBean equipmentBean = new EquipmentBean(bleRepoDevice);
            arrayList.add(equipmentBean);
            equipmentBean.setConnected(BleClientManager.getInstance().getTransport(bleRepoDevice.getMac()).isConnected());
        }
        return Observable.just(arrayList);
    }
}
