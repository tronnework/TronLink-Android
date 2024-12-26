package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.db.SpAPI;
import io.reactivex.functions.Consumer;
public class AutoSelectServer {
    public static int SERVER_SA = 1;
    public static int SERVER_USA;
    private static AutoSelectServer instance;
    private RxManager rxManager;
    private int state;

    public int getServerState() {
        return this.state;
    }

    private AutoSelectServer() {
        RxManager rxManager = new RxManager();
        this.rxManager = rxManager;
        this.state = 1;
        rxManager.on(Event.SWITCH_SERVER, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$0(obj);
            }
        });
    }

    public void lambda$new$0(Object obj) throws Exception {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            this.state = intValue;
            if (intValue != 2) {
                if (intValue != 3) {
                    return;
                }
                IRequest.onServerChanged();
            } else if (SpAPI.THIS.isServerAuto() && TronConfig.hasNet) {
                int serverUserPrefer = SpAPI.THIS.getServerUserPrefer();
                int i = SERVER_USA;
                if (serverUserPrefer == i) {
                    i = SERVER_SA;
                }
                LogUtils.d("SwitchServer:" + i);
                SpAPI.THIS.setServerUserPrefer(i);
                IRequest.onServerChanged();
            }
        }
    }

    public static AutoSelectServer getInstance() {
        if (instance == null) {
            synchronized (AutoSelectServer.class) {
                if (instance == null) {
                    instance = new AutoSelectServer();
                }
            }
        }
        return instance;
    }
}
