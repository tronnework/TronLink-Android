package com.tron.wallet.db.Controller;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.common.utils.StringTronUtil;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
public class DappBlackListController extends BaseController<DappBlackBean> {
    private static DappBlackListController instance;

    private DappBlackListController() {
        super(true);
    }

    public static DappBlackListController getInstance() {
        if (instance == null) {
            synchronized (DappBlackListController.class) {
                if (instance == null) {
                    instance = new DappBlackListController();
                }
            }
        }
        return instance;
    }

    public void insertBlackList(List<String> list) {
        final ArrayList arrayList = new ArrayList();
        try {
            Collection.-EL.stream(list).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    DappBlackListController.lambda$insertBlackList$0(arrayList, (String) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            insertMultiObject(arrayList, true);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static void lambda$insertBlackList$0(ArrayList arrayList, String str) {
        DappBlackBean dappBlackBean = new DappBlackBean();
        dappBlackBean.setName(str);
        arrayList.add(dappBlackBean);
    }

    public boolean isBlack(String str) {
        if (StringTronUtil.isEmpty(str)) {
            return false;
        }
        try {
            Iterator it = ((ArrayList) queryAll()).iterator();
            while (it.hasNext()) {
                if (StringTronUtil.equals(StringTronUtil.getHost(((DappBlackBean) it.next()).getName()), str)) {
                    return true;
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return false;
    }
}
