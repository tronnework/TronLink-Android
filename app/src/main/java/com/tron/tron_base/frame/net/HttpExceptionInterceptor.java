package com.tron.tron_base.frame.net;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.common.config.Event;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.apache.http.conn.ConnectTimeoutException;
public class HttpExceptionInterceptor implements Interceptor {
    private static final int INIT_TRIGGER_COUNT = 3;
    private boolean hasNoticed;
    private int count = 0;
    private long triggerCount = 3;

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        try {
            Response proceed = chain.proceed(chain.request());
            this.count = 0;
            this.triggerCount = 3L;
            if (this.hasNoticed) {
                this.hasNoticed = false;
                RxBus.getInstance().post(Event.SWITCH_SERVER, 1);
            }
            return proceed;
        } catch (Exception e) {
            LogUtils.d(Event.SWITCH_SERVER + e + " count:" + this.count);
            if ((e instanceof SocketTimeoutException) || (e instanceof ConnectTimeoutException)) {
                int i = this.count + 1;
                this.count = i;
                if (i > this.triggerCount) {
                    RxBus.getInstance().post(Event.SWITCH_SERVER, 2);
                    this.hasNoticed = true;
                    this.count = 0;
                    this.triggerCount += 3;
                }
            }
            throw e;
        }
    }
}
