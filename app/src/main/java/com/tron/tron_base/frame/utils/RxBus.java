package com.tron.tron_base.frame.utils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
public class RxBus {
    private ConcurrentHashMap<Object, Object> mStickyEventList;
    private ConcurrentHashMap<Object, CopyOnWriteArrayList<Subject>> subjectMapper;

    public static RxBus getInstance() {
        return RxBusNested.rxBus;
    }

    public static class RxBusNested {
        private static RxBus rxBus = new RxBus();

        private RxBusNested() {
        }
    }

    private RxBus() {
        this.subjectMapper = new ConcurrentHashMap<>();
        this.mStickyEventList = new ConcurrentHashMap<>();
    }

    public RxBus OnEvent(Observable<?> observable, Consumer<Object> consumer) {
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable th) throws Exception {
            }
        });
        return getInstance();
    }

    public <T> Observable<T> register(Object obj) {
        CopyOnWriteArrayList<Subject> copyOnWriteArrayList = this.subjectMapper.get(obj);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.subjectMapper.put(obj, copyOnWriteArrayList);
        }
        PublishSubject create = PublishSubject.create();
        copyOnWriteArrayList.add(create);
        LogUtils.d("register", obj + "  size:" + copyOnWriteArrayList.size());
        return create;
    }

    public <T> Disposable registerSticky(Object obj, Consumer<Object> consumer, Subject subject) {
        if (this.mStickyEventList.get(obj) != null) {
            Disposable subscribe = subject.observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable th) {
                    LogUtils.e(th);
                }
            });
            subject.onNext(this.mStickyEventList.get(obj));
            return subscribe;
        }
        return null;
    }

    public void unregister(Object obj) {
        if (this.subjectMapper.get(obj) != null) {
            this.subjectMapper.remove(obj);
        }
    }

    public RxBus unregister(Object obj, Observable<?> observable) {
        if (observable == null) {
            return getInstance();
        }
        try {
            CopyOnWriteArrayList<Subject> copyOnWriteArrayList = this.subjectMapper.get(obj);
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.remove((Subject) observable);
                if (isEmpty(copyOnWriteArrayList)) {
                    this.subjectMapper.remove(obj);
                    LogUtils.d("unregister", obj + "  size:" + copyOnWriteArrayList.size());
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return getInstance();
    }

    public void postSingleObject(Object obj) {
        post(obj.getClass().getName(), obj);
    }

    public void post(Object obj, Object obj2) {
        try {
            CopyOnWriteArrayList<Subject> copyOnWriteArrayList = this.subjectMapper.get(obj);
            if (isEmpty(copyOnWriteArrayList)) {
                return;
            }
            Iterator<Subject> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().onNext(obj2);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void postSticky(Object obj, Object obj2) {
        this.mStickyEventList.put(obj, obj2);
        post(obj, obj2);
    }

    public static boolean isEmpty(Collection<Subject> collection) {
        return collection == null || collection.isEmpty();
    }
}
