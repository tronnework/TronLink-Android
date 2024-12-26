package com.tron.tron_base.frame.net;

import com.tron.tron_base.frame.utils.LogUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
public class IObserver<T> implements Observer<T> {
    public String TAG;
    public ICallback<T> iCallback;

    @Override
    public void onComplete() {
    }

    public IObserver(ICallback<T> iCallback, String str) {
        this.iCallback = iCallback;
        this.TAG = str;
    }

    @Override
    public void onError(Throwable th) {
        LogUtils.e(this.TAG, th);
        this.iCallback.onFailure(this.TAG, th.toString());
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.iCallback.onSubscribe(disposable);
    }

    @Override
    public void onNext(T t) {
        this.iCallback.onResponse(this.TAG, t);
    }
}
