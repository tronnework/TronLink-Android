package com.tron.wallet.ledger.blemodule.utils;

import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class DisposableMap {
    private final Map<String, Disposable> Disposables = new HashMap();

    public synchronized void replaceDisposable(String str, Disposable disposable) {
        Disposable put = this.Disposables.put(str, disposable);
        if (put != null && !put.isDisposed()) {
            put.dispose();
        }
    }

    public synchronized boolean removeDisposable(String str) {
        Disposable remove = this.Disposables.remove(str);
        if (remove == null) {
            return false;
        }
        if (!remove.isDisposed()) {
            remove.dispose();
        }
        return true;
    }

    public synchronized void removeAllDisposables() {
        Iterator<Map.Entry<String, Disposable>> it = this.Disposables.entrySet().iterator();
        while (it.hasNext()) {
            Disposable value = it.next().getValue();
            it.remove();
            if (!value.isDisposed()) {
                value.dispose();
            }
        }
    }
}
