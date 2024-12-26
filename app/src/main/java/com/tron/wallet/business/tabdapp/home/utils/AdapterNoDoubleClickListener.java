package com.tron.wallet.business.tabdapp.home.utils;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import io.reactivex.functions.Consumer;
import j$.util.DesugarArrays;
import j$.util.Objects;
public class AdapterNoDoubleClickListener<T> extends NoDoubleClickListener implements BaseQuickAdapter.OnItemClickListener {
    private T clickedItem;
    private final Consumer<T>[] onItemClicked;

    public AdapterNoDoubleClickListener(Consumer<T>... consumerArr) {
        this.onItemClicked = consumerArr;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        try {
            this.clickedItem = baseQuickAdapter.getData().get(i);
            onClick(view);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    protected void onNoDoubleClick(View view) {
        Consumer<T>[] consumerArr;
        if (this.clickedItem == null || (consumerArr = this.onItemClicked) == null) {
            return;
        }
        DesugarArrays.stream(consumerArr).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onNoDoubleClick$0((Consumer) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public void lambda$onNoDoubleClick$0(Consumer consumer) {
        try {
            consumer.accept(this.clickedItem);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
