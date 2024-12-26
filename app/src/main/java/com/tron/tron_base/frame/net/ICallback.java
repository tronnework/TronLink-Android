package com.tron.tron_base.frame.net;

import io.reactivex.disposables.Disposable;
public interface ICallback<T> {
    void onFailure(String str, String str2);

    void onResponse(String str, T t);

    void onSubscribe(Disposable disposable);
}
