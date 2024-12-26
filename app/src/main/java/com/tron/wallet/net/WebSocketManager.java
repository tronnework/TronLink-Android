package com.tron.wallet.net;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.OkHttpManager;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.addassets2.repository.KVController;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletType;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.net.WebSocketManager;
import io.reactivex.functions.Consumer;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.function.IntPredicate$-CC;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.bouncycastle.util.encoders.Hex;
import org.tron.net.bean.MessagePermission;
import org.tron.walletserver.Wallet;
public class WebSocketManager {
    private static final int POST_DELAYED_MS = 1000;
    private static final int SOCKET_DISCONNECT = 1001;
    private static final String TAG = "WebSocketManager";
    private String address;
    private MsgIdCache cache;
    private Handler handler;
    private HandlerThread handlerThread;
    private long lastMsgId;
    private MessageCallback messageCallback;
    private LinkedBlockingQueue<Message> messageQueue;
    private OkHttpClient okHttpClient;
    private RxManager rxManager;
    private SocketHandler socketHandler;
    private Timer timer;
    private String url = HOST;
    private static final String HOST = IRequest.getWebSocketHost() + "api/message";
    private static WebSocketManager instance = null;

    public enum State {
        INIT,
        CONNECTING,
        CONNECTED
    }

    private WebSocketManager() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null) {
            this.address = selectedWallet.getAddress();
        }
        this.rxManager = new RxManager();
        this.cache = new MsgIdCache();
        this.messageQueue = new LinkedBlockingQueue<>(100);
        this.lastMsgId = SpAPI.THIS.getLastMSGid();
        initListener();
    }

    public static WebSocketManager getInstance() {
        if (instance == null) {
            synchronized (WebSocketManager.class) {
                if (instance == null) {
                    instance = new WebSocketManager();
                }
            }
        }
        return instance;
    }

    private OkHttpClient getClientInstance() {
        if (this.okHttpClient == null) {
            this.okHttpClient = OkHttpManager.getInstance().build().newBuilder().readTimeout(60L, TimeUnit.SECONDS).pingInterval(30L, TimeUnit.SECONDS).build();
        }
        return this.okHttpClient;
    }

    private void initListener() {
        HandlerThread handlerThread = new HandlerThread("msgQ");
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
        MessageCallback messageCallback = new MessageCallback();
        this.messageCallback = messageCallback;
        this.handler.postDelayed(messageCallback, 1000L);
        this.rxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initListener$0(obj);
            }
        });
        this.rxManager.on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initListener$1(obj);
            }
        });
        this.rxManager.on(Event.MESSAGING_FIREBASE_TOKEN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initListener$2(obj);
            }
        });
        this.rxManager.on(Event.DELETE_WALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initListener$3(obj);
            }
        });
        Timer timer = new Timer();
        this.timer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (socketHandler == null || socketHandler.getState() == State.INIT) {
                    LogUtils.d(WebSocketManager.TAG, "socket disconnected, reconnect");
                    start();
                }
            }
        }, 5000L, 5000L);
    }

    public void lambda$initListener$0(Object obj) throws Exception {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null) {
            String address = selectedWallet.getAddress();
            String str = this.address;
            if (str == null || !str.equals(address)) {
                this.address = address;
                sendBasicParam();
            }
        }
    }

    public void lambda$initListener$1(Object obj) throws Exception {
        disconnect();
        this.cache.reInit();
        start();
    }

    public void lambda$initListener$2(Object obj) throws Exception {
        sendBasicParam();
    }

    public void lambda$initListener$3(Object obj) throws Exception {
        sendBasicParam();
    }

    public synchronized void disconnect() {
        SocketHandler socketHandler = this.socketHandler;
        if (socketHandler != null) {
            socketHandler.close();
            this.socketHandler = null;
        }
    }

    public synchronized void setUrl(String str) {
        this.url = str;
        disconnect();
    }

    public synchronized void start() {
        Wallet selectedWallet;
        if (!IRequest.isShasta() && !SpAPI.THIS.isCold() && !IRequest.isNile()) {
            SocketHandler socketHandler = this.socketHandler;
            if (socketHandler == null || socketHandler.getState() == State.INIT) {
                disconnect();
                String str = IRequest.getWebSocketHost() + "api/message";
                if (WalletUtils.getSelectedWallet() != null) {
                    str = str + "?address=" + getHexAddress(selectedWallet.getAddress());
                }
                Request build = new Request.Builder().url(str).addHeader("Signature", "true").build();
                SocketHandler socketHandler2 = new SocketHandler();
                this.socketHandler = socketHandler2;
                socketHandler2.setState(State.CONNECTING);
                getClientInstance().dispatcher().cancelAll();
                getClientInstance().newWebSocket(build, this.socketHandler);
            }
        }
    }

    public void send(NetMessage netMessage) {
        try {
            send(new Gson().toJson(netMessage));
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.d(TAG, e.toString());
        }
    }

    public synchronized void send(String str) {
        SocketHandler socketHandler = this.socketHandler;
        if (socketHandler != null && socketHandler.getState() == State.CONNECTED) {
            LogUtils.d(TAG, "send: " + str);
            boolean send = this.socketHandler.getWebSocket().send(str);
            LogUtils.d(TAG, "send result: " + send);
        } else {
            start();
        }
    }

    public void sendAck(NetMessage netMessage) {
        NetMessageAck netMessageAck = new NetMessageAck();
        if (netMessage != null && netMessage.getData() != null) {
            ArrayList arrayList = new ArrayList();
            for (NetMessageData netMessageData : netMessage.getData()) {
                this.cache.putId(netMessageData.getMsgId());
                arrayList.add(netMessageData.getMsgId());
            }
            netMessageAck.setMsgIdList(arrayList);
            this.cache.commit();
        }
        netMessageAck.setMsgType(10000);
        send(netMessageAck);
    }

    private String getHexAddress(String str) {
        byte[] decode58Check = StringTronUtil.decode58Check(str);
        if (decode58Check == null) {
            return null;
        }
        return Hex.toHexString(decode58Check);
    }

    public void sendBasicParam() {
        if (SpAPI.THIS.getMessagePermission().getAccountActivityOpenStatus()) {
            sendBasicParam(true);
        } else {
            sendBasicParam(false);
        }
    }

    public void sendBasicParam(boolean z) {
        BasicParamMessage basicParamMessage = new BasicParamMessage();
        basicParamMessage.setMsgType(0);
        basicParamMessage.setDeviceToken(SpAPI.THIS.getFireBaseToken());
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null) {
            AddressInfo addressInfo = new AddressInfo();
            addressInfo.setAddress(getHexAddress(selectedWallet.getAddress()));
            addressInfo.setAddressType(LedgerWallet.isLedger(selectedWallet) ? 1 : WalletType.getType(selectedWallet));
            basicParamMessage.setAddressInfo(addressInfo);
        }
        ArrayList arrayList = new ArrayList();
        for (String str : WalletUtils.getPublicWalletNames()) {
            Wallet wallet = WalletUtils.getWallet(str);
            if (wallet != null) {
                AddressInfo addressInfo2 = new AddressInfo();
                addressInfo2.setAddress(getHexAddress(wallet.getAddress()));
                addressInfo2.setAddressType(LedgerWallet.isLedger(wallet) ? 1 : WalletType.getType(wallet));
                arrayList.add(addressInfo2);
            }
        }
        basicParamMessage.setAddressInfoList(arrayList);
        final MessagePermission messagePermission = SpAPI.THIS.getMessagePermission();
        final ArrayList arrayList2 = new ArrayList();
        if (z) {
            DesugarArrays.stream(WebSocketConfig.supportedMsgTypes).filter(new IntPredicate() {
                public IntPredicate and(IntPredicate intPredicate) {
                    return Objects.requireNonNull(intPredicate);
                }

                public IntPredicate negate() {
                    return IntPredicate$-CC.$default$negate(this);
                }

                public IntPredicate or(IntPredicate intPredicate) {
                    return Objects.requireNonNull(intPredicate);
                }

                @Override
                public final boolean test(int i) {
                    return WebSocketManager.lambda$sendBasicParam$4(MessagePermission.this, i);
                }
            }).forEach(new IntConsumer() {
                @Override
                public final void accept(int i) {
                    arrayList2.add(Integer.valueOf(i));
                }

                public IntConsumer andThen(IntConsumer intConsumer) {
                    return Objects.requireNonNull(intConsumer);
                }
            });
        } else {
            DesugarArrays.stream(WebSocketConfig.supportedMsgTypes).filter(new IntPredicate() {
                public IntPredicate and(IntPredicate intPredicate) {
                    return Objects.requireNonNull(intPredicate);
                }

                public IntPredicate negate() {
                    return IntPredicate$-CC.$default$negate(this);
                }

                public IntPredicate or(IntPredicate intPredicate) {
                    return Objects.requireNonNull(intPredicate);
                }

                @Override
                public final boolean test(int i) {
                    return WebSocketManager.lambda$sendBasicParam$6(MessagePermission.this, i);
                }
            }).forEach(new IntConsumer() {
                @Override
                public final void accept(int i) {
                    arrayList2.add(Integer.valueOf(i));
                }

                public IntConsumer andThen(IntConsumer intConsumer) {
                    return Objects.requireNonNull(intConsumer);
                }
            });
        }
        basicParamMessage.setMsgTypes(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < WebSocketConfig.supportedWalletTypesForSocketPush.length; i++) {
            arrayList3.add(Integer.valueOf(WebSocketConfig.supportedWalletTypesForSocketPush[i]));
        }
        basicParamMessage.setWssAddrTypes(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        for (int i2 = 0; i2 < WebSocketConfig.supportedWalletTypesForSystemPush.length; i2++) {
            arrayList4.add(Integer.valueOf(WebSocketConfig.supportedWalletTypesForSystemPush[i2]));
        }
        basicParamMessage.setFirebaseAddrTypes(arrayList4);
        basicParamMessage.setSeqId(this.lastMsgId);
        send(basicParamMessage);
    }

    public static boolean lambda$sendBasicParam$4(MessagePermission messagePermission, int i) {
        return !(i == 2 || i == 3) || messagePermission.getAccountActivityOpenStatus();
    }

    public static boolean lambda$sendBasicParam$6(MessagePermission messagePermission, int i) {
        return i == 1 && messagePermission.getAccountActivityOpenStatus();
    }

    public class SocketHandler extends WebSocketListener {
        private WebSocket webSocket = null;
        private State state = State.INIT;

        public State getState() {
            return this.state;
        }

        public WebSocket getWebSocket() {
            return this.webSocket;
        }

        public void setState(State state) {
            this.state = state;
        }

        public void setWebSocket(WebSocket webSocket) {
            this.webSocket = webSocket;
        }

        public SocketHandler() {
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            LogUtils.d(WebSocketManager.TAG, "connection succeeded");
            setState(State.CONNECTED);
            setWebSocket(webSocket);
            sendBasicParam();
            cache.init();
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    WebSocketManager.SocketHandler.this.lambda$onOpen$0();
                }
            });
        }

        public void lambda$onOpen$0() {
            rxManager.post(Event.WS_MSG_SOCKET_STATE, "onOpen");
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString byteString) {
            super.onMessage(webSocket, byteString);
            LogUtils.d(WebSocketManager.TAG, "receive bytes:" + byteString.hex());
        }

        private NetMessage parse(String str) {
            JsonElement jsonElement;
            JsonArray asJsonArray;
            Gson gson = new Gson();
            NetMessage netMessage = null;
            try {
                NetMessage netMessage2 = (NetMessage) gson.fromJson(str,  NetMessage.class);
                if (netMessage2 != null) {
                    try {
                        if (netMessage2.getData() != null && (jsonElement = ((JsonElement) gson.fromJson(str,  JsonElement.class)).getAsJsonObject().get("data")) != null && (asJsonArray = jsonElement.getAsJsonArray()) != null) {
                            Iterator<JsonElement> it = asJsonArray.iterator();
                            while (it.hasNext()) {
                                JsonElement next = it.next();
                                if (next != null) {
                                    try {
                                        if (cache.hasId(next.getAsJsonObject().get("msgId").getAsString())) {
                                            it.remove();
                                        }
                                        long asLong = next.getAsJsonObject().get("accountMsgId").getAsLong();
                                        if (asLong > lastMsgId) {
                                            lastMsgId = asLong;
                                        }
                                    } catch (Exception e) {
                                        SentryUtil.captureException(e);
                                        LogUtils.d(WebSocketManager.TAG, e.toString());
                                    }
                                }
                            }
                            SpAPI.THIS.setLastMSGid(lastMsgId);
                            netMessage2.setDataString(asJsonArray.toString());
                        }
                    } catch (Exception e2) {
                        e = e2;
                        netMessage = netMessage2;
                        SentryUtil.captureException(e);
                        LogUtils.d(WebSocketManager.TAG, e.toString());
                        return netMessage;
                    }
                }
                netMessage2.setSeqId(lastMsgId);
                return netMessage2;
            } catch (Exception e3) {
                e = e3;
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, String str) {
            super.onMessage(webSocket, str);
            LogUtils.d(WebSocketManager.TAG, "receive text:" + str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                NetMessage parse = parse(str);
                if (parse != null && parse.getDataString() != null) {
                    boolean offer = messageQueue.offer(new Message(NetMessageType.getEvent(parse.getMsgType()), parse.getDataString()));
                    LogUtils.d(WebSocketManager.TAG, "offerResult:" + offer);
                }
                sendAck(parse);
            } catch (Exception e) {
                SentryUtil.captureException(e);
                LogUtils.d(WebSocketManager.TAG, e.toString());
            }
        }

        @Override
        public void onClosed(WebSocket webSocket, int i, String str) {
            super.onClosed(webSocket, i, str);
            LogUtils.d(WebSocketManager.TAG, "onClosed: code:" + i + " reason:" + str);
            setState(State.INIT);
            setWebSocket(null);
        }

        @Override
        public void onClosing(WebSocket webSocket, int i, String str) {
            super.onClosing(webSocket, i, str);
            LogUtils.d(WebSocketManager.TAG, "closing: code:" + i + " reason:" + str);
            setState(State.INIT);
            setWebSocket(null);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            super.onFailure(webSocket, th, response);
            LogUtils.d(WebSocketManager.TAG, "failure:" + th + " response:" + response);
            setState(State.INIT);
            setWebSocket(null);
        }

        public void close() {
            WebSocket webSocket = this.webSocket;
            if (webSocket != null) {
                try {
                    webSocket.cancel();
                    this.webSocket.close(1001, "");
                } catch (Exception e) {
                    SentryUtil.captureException(e);
                    LogUtils.d(WebSocketManager.TAG, e.toString());
                }
            }
        }
    }

    private class Message {
        private String event;
        private String message;

        public String getEvent() {
            return this.event;
        }

        public String getMessage() {
            return this.message;
        }

        public void setEvent(String str) {
            this.event = str;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof Message;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Message) {
                Message message = (Message) obj;
                if (message.canEqual(this)) {
                    String event = getEvent();
                    String event2 = message.getEvent();
                    if (event != null ? event.equals(event2) : event2 == null) {
                        String message2 = getMessage();
                        String message3 = message.getMessage();
                        return message2 != null ? message2.equals(message3) : message3 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            String event = getEvent();
            int hashCode = event == null ? 43 : event.hashCode();
            String message = getMessage();
            return ((hashCode + 59) * 59) + (message != null ? message.hashCode() : 43);
        }

        public String toString() {
            return "WebSocketManager.Message(event=" + getEvent() + ", message=" + getMessage() + ")";
        }

        public Message(String str, String str2) {
            this.event = str;
            this.message = str2;
        }
    }

    public class MessageCallback implements Runnable {
        private MessageCallback() {
        }

        @Override
        public void run() {
            try {
                LogUtils.d(WebSocketManager.TAG, "MessageCallback:");
                Message message = (Message) messageQueue.take();
                if (message != null) {
                    LogUtils.d(WebSocketManager.TAG, "post:" + message.getMessage());
                    rxManager.post(message.getEvent(), message.getMessage());
                }
                handler.removeCallbacks(messageCallback);
            } catch (InterruptedException e) {
                SentryUtil.captureException(e);
            }
            handler.postDelayed(messageCallback, 1000L);
        }
    }

    public class MsgIdCache {
        private final String ID_KEY;
        private final int SIZE;
        private LinkedList<String> idsList;
        private boolean initialized;

        private MsgIdCache() {
            this.ID_KEY = "NetMsgIds";
            this.SIZE = 20;
            this.idsList = new LinkedList<>();
        }

        public synchronized void init() {
            if (!this.initialized) {
                read();
                this.initialized = true;
            }
        }

        public synchronized void reInit() {
            this.initialized = false;
            init();
        }

        public synchronized void putId(String str) {
            if (this.idsList.size() >= 20) {
                this.idsList.poll();
            }
            this.idsList.offer(str);
        }

        public synchronized void commit() {
            write();
        }

        public boolean hasId(String str) {
            return this.idsList.contains(str);
        }

        private void write() {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = this.idsList.iterator();
            while (it.hasNext()) {
                sb.append(it.next() + ",");
            }
            KVController.getInstance().setValue("NetMsgIds", sb.toString());
        }

        private void read() {
            String[] split;
            this.idsList.clear();
            String value = KVController.getInstance().getValue("NetMsgIds");
            if (value == null || (split = value.split(",")) == null) {
                return;
            }
            for (String str : split) {
                if (str != null && str.length() > 0) {
                    this.idsList.offer(str);
                }
            }
        }
    }
}
