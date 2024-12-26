package com.tron.wallet.business.message.db;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.net.NetMessageType;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import j$.util.Collection;
import j$.util.DesugarArrays;
import j$.util.Objects;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
public class TransactionMessageManager {
    private static final long DEFAULT_BUFFER_TIME = 3000;
    private static final String TAG = "TransactionMessageManager";
    private int MSG_WHAT;
    private final CopyOnWriteArrayList<TransactionMessage> cachePool;
    private final TransactionMessageDbController controller;
    private Handler handler;
    private volatile boolean initialized;
    private final List<WeakReference<Observer>> observers;
    private final RxManager rxManager;

    public interface Observer {
        void update(List<TransactionMessage> list);
    }

    private TransactionMessageManager() {
        this.cachePool = new CopyOnWriteArrayList<>();
        this.controller = TransactionMessageDbController.get();
        this.rxManager = new RxManager();
        this.observers = new ArrayList();
        this.MSG_WHAT = getClass().hashCode();
    }

    public static TransactionMessageManager getInstance() {
        return Nested.inst;
    }

    public void init() {
        if (this.initialized) {
            return;
        }
        registerEvent();
        this.initialized = true;
        this.handler = new Handler(Looper.getMainLooper());
    }

    public boolean insertOrReplace(TransactionMessage transactionMessage) {
        boolean insertOrReplace = this.controller.insertOrReplace(transactionMessage);
        postMessageChange();
        return insertOrReplace;
    }

    public boolean insertMulti(List<TransactionMessage> list, boolean z) {
        boolean insertMultiObject = this.controller.insertMultiObject(list);
        if (z) {
            postMessageChange();
        }
        return insertMultiObject;
    }

    public List<TransactionMessage> queryAll() {
        return this.controller.queryAll();
    }

    public List<TransactionMessage> query(int i, int i2) {
        return this.controller.query(i, i2);
    }

    public void remove(List<TransactionMessage> list) {
        this.controller.remove(list);
        postMessageChange();
    }

    public long queryUnread() {
        return this.controller.queryUnread();
    }

    public void addObserver(Observer observer) {
        this.observers.add(new WeakReference<>(observer));
    }

    public void removeObserver(Observer observer) {
        Iterator<WeakReference<Observer>> it = this.observers.iterator();
        while (it.hasNext()) {
            WeakReference<Observer> next = it.next();
            if (next.get() != null && observer == next.get()) {
                it.remove();
                return;
            }
        }
    }

    private void notifyObservers(final List<TransactionMessage> list) {
        Collection.-EL.stream(this.observers).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                TransactionMessageManager.lambda$notifyObservers$0(list, (WeakReference) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public static void lambda$notifyObservers$0(List list, WeakReference weakReference) {
        Observer observer = (Observer) weakReference.get();
        if (observer != null) {
            observer.update(list);
        }
    }

    private void registerEvent() {
        DesugarArrays.stream(new int[]{3, 2}).forEach(new IntConsumer() {
            @Override
            public final void accept(int i) {
                lambda$registerEvent$2(i);
            }

            public IntConsumer andThen(IntConsumer intConsumer) {
                return Objects.requireNonNull(intConsumer);
            }
        });
    }

    public void lambda$registerEvent$2(final int i) {
        this.rxManager.onIO(NetMessageType.getEvent(i), new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$registerEvent$1(i, obj);
            }
        });
    }

    public void lambda$registerEvent$1(final int i, Object obj) {
        if (obj instanceof String) {
            final String str = (String) obj;
            Observable.create(new ObservableOnSubscribe() {
                @Override
                public final void subscribe(ObservableEmitter observableEmitter) {
                    lambda$onNewTransferMessage$4(str, i, observableEmitter);
                }
            }).subscribeOn(Schedulers.io()).subscribe(new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj2) {
                    lambda$onNewTransferMessage$6((List) obj2);
                }
            }, new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj2) {
                    TransactionMessageManager.lambda$onNewTransferMessage$7((Throwable) obj2);
                }
            });
        }
    }

    public void lambda$onNewTransferMessage$4(String str, final int i, ObservableEmitter observableEmitter) throws Exception {
        try {
            List parseArray = JSON.parseArray(str, TransactionMessage.class);
            if (parseArray == null) {
                parseArray = new ArrayList();
            } else {
                Collection.-EL.stream(parseArray).forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        TransactionMessageManager.lambda$onNewTransferMessage$3(i, (TransactionMessage) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            }
            if (!parseArray.isEmpty()) {
                observableEmitter.onNext(parseArray);
                this.cachePool.addAll(0, parseArray);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        observableEmitter.onComplete();
    }

    public static void lambda$onNewTransferMessage$3(int i, TransactionMessage transactionMessage) {
        transactionMessage.setType(NetMessageType.getTransactionTypeByMsg(i));
        transactionMessage.setContract_ret(TransactionMessage.TYPE_SUCCESS);
    }

    public void lambda$onNewTransferMessage$6(List list) throws Exception {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        if (handler.hasMessages(this.MSG_WHAT)) {
            LogUtils.w(TAG, "add new messages to cache pool");
            return;
        }
        Message obtain = Message.obtain(this.handler, new Runnable() {
            @Override
            public final void run() {
                lambda$onNewTransferMessage$5();
            }
        });
        obtain.what = this.MSG_WHAT;
        this.handler.sendMessageDelayed(obtain, DEFAULT_BUFFER_TIME);
    }

    public void lambda$onNewTransferMessage$5() {
        insertRx(true);
        this.handler.removeMessages(this.MSG_WHAT);
        LogUtils.w(TAG, "time up, post cached messages");
    }

    public static void lambda$onNewTransferMessage$7(Throwable th) throws Exception {
        LogUtils.w(TAG, "insert on new message fail");
        LogUtils.e(th);
    }

    public void insertRx(final boolean z) {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$insertRx$8(z, observableEmitter);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$insertRx$9((List) obj);
            }
        }, new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                TransactionMessageManager.lambda$insertRx$10((Throwable) obj);
            }
        });
    }

    public void lambda$insertRx$8(boolean z, ObservableEmitter observableEmitter) throws Exception {
        ArrayList arrayList = new ArrayList(this.cachePool);
        this.cachePool.removeAll(arrayList);
        this.controller.insertMultiObject(arrayList);
        if (z) {
            postMessageChange();
        }
        observableEmitter.onNext(arrayList);
        observableEmitter.onComplete();
    }

    public void lambda$insertRx$9(List list) throws Exception {
        notifyObservers(list);
        LogUtils.w(TAG, "insert on new message successful");
    }

    public static void lambda$insertRx$10(Throwable th) throws Exception {
        LogUtils.w(TAG, "insert on new message fail");
        LogUtils.e(th);
    }

    private void postMessageChange() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.post(Event.MSG_CENTER_UPDATE, Long.valueOf(queryUnread()));
        }
    }

    public void updateAllUnread() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.post(Event.MSG_CENTER_UPDATE, 0L);
        }
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public void run() {
                controller.setAllRead();
            }
        });
    }

    public static class Nested {
        static TransactionMessageManager inst = new TransactionMessageManager();

        private Nested() {
        }
    }
}
