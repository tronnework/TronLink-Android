package com.tron.wallet.business.tabdapp.browser.grid.baseadapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.tron.wallet.business.tabdapp.browser.grid.base.AdapterPathSegment;
import com.tron.wallet.business.tabdapp.browser.grid.base.UnwrapPositionResult;
import java.util.List;
public interface WrapperAdapter<VH extends RecyclerView.ViewHolder> extends WrappedAdapter<VH> {
    void getWrappedAdapters(List<RecyclerView.Adapter> list);

    void release();

    void unwrapPosition(UnwrapPositionResult unwrapPositionResult, int i);

    int wrapPosition(AdapterPathSegment adapterPathSegment, int i);
}
