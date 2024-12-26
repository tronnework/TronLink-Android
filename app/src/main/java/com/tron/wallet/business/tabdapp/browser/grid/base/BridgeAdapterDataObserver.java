package com.tron.wallet.business.tabdapp.browser.grid.base;

import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
public class BridgeAdapterDataObserver extends RecyclerView.AdapterDataObserver {
    private final WeakReference<RecyclerView.Adapter> mRefSourceHolder;
    private final WeakReference<Subscriber> mRefSubscriber;
    private final Object mTag;

    public interface Subscriber {
        void onBridgedAdapterChanged(RecyclerView.Adapter adapter, Object obj);

        void onBridgedAdapterItemRangeChanged(RecyclerView.Adapter adapter, Object obj, int i, int i2);

        void onBridgedAdapterItemRangeChanged(RecyclerView.Adapter adapter, Object obj, int i, int i2, Object obj2);

        void onBridgedAdapterItemRangeInserted(RecyclerView.Adapter adapter, Object obj, int i, int i2);

        void onBridgedAdapterItemRangeRemoved(RecyclerView.Adapter adapter, Object obj, int i, int i2);

        void onBridgedAdapterRangeMoved(RecyclerView.Adapter adapter, Object obj, int i, int i2, int i3);
    }

    public Object getTag() {
        return this.mTag;
    }

    public BridgeAdapterDataObserver(Subscriber subscriber, RecyclerView.Adapter adapter, Object obj) {
        this.mRefSubscriber = new WeakReference<>(subscriber);
        this.mRefSourceHolder = new WeakReference<>(adapter);
        this.mTag = obj;
    }

    @Override
    public void onChanged() {
        Subscriber subscriber = this.mRefSubscriber.get();
        RecyclerView.Adapter adapter = this.mRefSourceHolder.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterChanged(adapter, this.mTag);
    }

    @Override
    public void onItemRangeChanged(int i, int i2) {
        Subscriber subscriber = this.mRefSubscriber.get();
        RecyclerView.Adapter adapter = this.mRefSourceHolder.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterItemRangeChanged(adapter, this.mTag, i, i2);
    }

    @Override
    public void onItemRangeChanged(int i, int i2, Object obj) {
        Subscriber subscriber = this.mRefSubscriber.get();
        RecyclerView.Adapter adapter = this.mRefSourceHolder.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterItemRangeChanged(adapter, this.mTag, i, i2, obj);
    }

    @Override
    public void onItemRangeInserted(int i, int i2) {
        Subscriber subscriber = this.mRefSubscriber.get();
        RecyclerView.Adapter adapter = this.mRefSourceHolder.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterItemRangeInserted(adapter, this.mTag, i, i2);
    }

    @Override
    public void onItemRangeRemoved(int i, int i2) {
        Subscriber subscriber = this.mRefSubscriber.get();
        RecyclerView.Adapter adapter = this.mRefSourceHolder.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterItemRangeRemoved(adapter, this.mTag, i, i2);
    }

    @Override
    public void onItemRangeMoved(int i, int i2, int i3) {
        Subscriber subscriber = this.mRefSubscriber.get();
        RecyclerView.Adapter adapter = this.mRefSourceHolder.get();
        if (subscriber == null || adapter == null) {
            return;
        }
        subscriber.onBridgedAdapterRangeMoved(adapter, this.mTag, i, i2, i3);
    }
}
