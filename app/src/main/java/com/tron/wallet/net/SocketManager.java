package com.tron.wallet.net;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.OkHttpManager;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.SpAPI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
public class SocketManager {
    private static final int SOCKET_DISCONNECT = 1001;
    private static final String TAG = "SocketManager";
    private static OkHttpClient mOkHttpClient;
    private WebSocket mSocket;
    private int mState;
    private String oldAddress = "";
    public static final String HOST = IRequest.getSocketHost();
    public static int reConnetcionSize = 0;
    private static SocketManager socketManager = null;
    private static List<SocketListener> socketListeners = new ArrayList();
    private static List<WebSocket> socketsList = new ArrayList();

    public interface SocketListener {
        void onClosed(WebSocket webSocket, int i, String str);

        void onClosing(WebSocket webSocket, int i, String str);

        void onFailure(WebSocket webSocket, Throwable th, Response response);

        void onMessage(WebSocket webSocket, String str);

        void open(WebSocket webSocket, Response response);
    }

    public static SocketManager getInstance() {
        if (socketManager == null) {
            socketManager = new SocketManager();
        }
        return socketManager;
    }

    public static OkHttpClient getClientInstance() {
        if (mOkHttpClient == null) {
            mOkHttpClient = OkHttpManager.getInstance().build();
        }
        return mOkHttpClient;
    }

    public SocketManager addListenter(SocketListener socketListener) {
        if (socketListeners == null) {
            socketListeners = new ArrayList();
        }
        socketListeners.add(socketListener);
        return socketManager;
    }

    public SocketManager removeListener(SocketListener socketListener) {
        List<SocketListener> list = socketListeners;
        if (list != null && list.size() > 0) {
            socketListeners.remove(socketListener);
        }
        return socketManager;
    }

    private void closeSockets() {
        List<WebSocket> list = socketsList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<WebSocket> it = socketsList.iterator();
        while (it.hasNext()) {
            WebSocket next = it.next();
            if (next != null) {
                next.cancel();
                next.close(1001, "");
            }
            try {
                it.remove();
            } catch (Exception e) {
                SentryUtil.captureException(e);
                LogUtils.e(e);
            }
        }
    }

    public synchronized SocketManager start(String str, int i, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append(HOST);
            sb.append("api/wallet/multi/socket?address=");
            sb.append(str);
            sb.append("&state=");
            sb.append(i);
            sb.append("&netType=");
            sb.append(SpAPI.THIS.isShasta() ? "shasta" : "main_net");
            Request build = new Request.Builder().url(sb.toString()).build();
            EchoWebSocketListener echoWebSocketListener = new EchoWebSocketListener();
            String str2 = this.oldAddress;
            if (str2 == null || !str2.equals(str)) {
                this.oldAddress = str;
                this.mState = i;
                if (socketListeners != null) {
                    socketListeners = new ArrayList();
                }
            }
            closeSockets();
            getClientInstance().dispatcher().cancelAll();
            getClientInstance().newWebSocket(build, echoWebSocketListener);
        }
        return socketManager;
    }

    public void disconnect() {
        try {
            List<SocketListener> list = socketListeners;
            if (list != null) {
                list.clear();
            }
            List<WebSocket> list2 = socketsList;
            if (list2 != null && list2.size() > 0) {
                for (int i = 0; i < socketsList.size(); i++) {
                    if (socketsList.get(i) != null) {
                        socketsList.get(i).close(1001, "");
                    }
                }
            }
            socketsList.clear();
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    public class EchoWebSocketListener extends WebSocketListener {
        private EchoWebSocketListener() {
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            mSocket = webSocket;
            LogUtils.i(SocketManager.TAG, "connection succeeded");
            if (SocketManager.socketListeners.size() > 0) {
                for (int i = 0; i < SocketManager.socketsList.size(); i++) {
                    if (SocketManager.socketsList.get(i) != null) {
                        if (((WebSocket) SocketManager.socketsList.get(i)).close(1001, "")) {
                            SocketManager.socketsList.remove(i);
                        }
                    } else {
                        SocketManager.socketsList.remove(i);
                    }
                }
            }
            SocketManager.socketsList.add(mSocket);
            for (int i2 = 0; i2 < SocketManager.socketListeners.size(); i2++) {
                if (SocketManager.socketListeners.get(i2) != null) {
                    ((SocketListener) SocketManager.socketListeners.get(i2)).open(webSocket, response);
                }
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString byteString) {
            super.onMessage(webSocket, byteString);
            LogUtils.i(SocketManager.TAG, "receive bytes:" + byteString.hex());
        }

        @Override
        public void onMessage(WebSocket webSocket, String str) {
            super.onMessage(webSocket, str);
            LogUtils.i(SocketManager.TAG, "receive text:" + str);
            for (int i = 0; i < SocketManager.socketListeners.size(); i++) {
                if (SocketManager.socketListeners.get(i) != null) {
                    ((SocketListener) SocketManager.socketListeners.get(i)).onMessage(webSocket, str);
                }
            }
        }

        @Override
        public void onClosed(WebSocket webSocket, int i, String str) {
            super.onClosed(webSocket, i, str);
            LogUtils.i(SocketManager.TAG, "closed:" + str);
        }

        @Override
        public void onClosing(WebSocket webSocket, int i, String str) {
            super.onClosing(webSocket, i, str);
            LogUtils.i(SocketManager.TAG, "closing:" + str);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            super.onFailure(webSocket, th, response);
            SocketManager.reConnetcionSize++;
            try {
                if (SocketManager.reConnetcionSize <= 5) {
                    mSocket = null;
                    SocketManager socketManager = SocketManager.this;
                    socketManager.start(socketManager.oldAddress, mState, true);
                } else if (SocketManager.socketsList.contains(webSocket)) {
                    for (int i = 0; i < SocketManager.socketListeners.size(); i++) {
                        if (SocketManager.socketListeners.get(i) != null) {
                            ((SocketListener) SocketManager.socketListeners.get(i)).onFailure(webSocket, th, response);
                        }
                    }
                }
            } catch (Exception e) {
                SentryUtil.captureException(e);
                LogUtils.e(e);
            }
        }
    }
}
