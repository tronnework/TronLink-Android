package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.triggersmartcontract;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.triggersmartcontract.DappTriggerSmartContractConfirmContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class DappTriggerSmartContractConfirmModel implements DappTriggerSmartContractConfirmContract.Model {
    @Override
    public Observable<DappProjectIOutput> getProjectInfo(String str, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getProjectInfo(str, str2).compose(RxSchedulers.io_main());
    }
}
