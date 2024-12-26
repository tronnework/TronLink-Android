package com.tron.wallet.common.utils.addressscam;

import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.bean.RiskAccountOutput;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.observers.DefaultObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
public class AddressManager {
    private static final String TAG = "AddressManager";
    private static final int countOneTime = 30;
    private static AddressManager instance;
    private RxManager rxManager = new RxManager();

    public interface AddressScamCall {
        void callback(HashMap<String, Map<String, Boolean>> hashMap);

        void error();
    }

    private AddressManager() {
    }

    public static void checkAddressIsScam(List<String> list, final AddressScamCall addressScamCall) {
        final int size = list.size();
        final HashMap hashMap = new HashMap();
        checkAddressIsScam(list).subscribe(new DefaultObserver<AddressScamResponseBean>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable th) {
            }

            @Override
            public void onNext(AddressScamResponseBean addressScamResponseBean) {
                if (addressScamResponseBean.getData() == null || addressScamResponseBean.getData().isEmpty()) {
                    return;
                }
                hashMap.putAll(addressScamResponseBean.getData());
                if (hashMap.size() == size) {
                    addressScamCall.callback(hashMap);
                }
            }
        });
    }

    public static Observable<AddressScamResponseBean> checkAddressIsScam(List<String> list) {
        int ceil = (int) Math.ceil(list.size() / 30);
        ArrayList arrayList = new ArrayList(ceil);
        for (int i = 0; i < ceil; i++) {
            int i2 = i * 30;
            arrayList.add(list.subList(i2, Math.min(i2 + 30, list.size())));
        }
        return Observable.fromIterable(arrayList).concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return AddressManager.lambda$checkAddressIsScam$0((List) obj);
            }
        });
    }

    public static ObservableSource lambda$checkAddressIsScam$0(List list) throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("accountList", list);
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).checkAddressIsScam(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(hashMap))).compose(RxSchedulers.io_main());
    }

    public static Observable<RiskAccountOutput> checkSingleAddressScam(String str) {
        return checkAccountRisk(str).onErrorReturn(new Function() {
            @Override
            public final Object apply(Object obj) {
                RiskAccountOutput createDefault;
                Throwable th = (Throwable) obj;
                createDefault = RiskAccountOutput.createDefault();
                return createDefault;
            }
        });
    }

    public static Observable<RiskAccountOutput> checkAccountRisk(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestAccountRisk(str).compose(RxSchedulers2.io_main());
    }
}
