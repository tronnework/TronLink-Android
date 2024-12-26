package com.tron.tron_base.frame.base;

import com.tron.tron_base.frame.utils.RxManager;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
public abstract class BasePresenter<M, V> {
    public M mModel;
    public V mView;
    public RxManager mRxManager = new RxManager();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void onBackPressed() {
    }

    protected abstract void onStart();

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        onStart();
    }

    public void addDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }

    public void onDestroy() {
        this.mRxManager.clear();
        if (this.compositeDisposable.isDisposed()) {
            return;
        }
        this.compositeDisposable.dispose();
    }
}
