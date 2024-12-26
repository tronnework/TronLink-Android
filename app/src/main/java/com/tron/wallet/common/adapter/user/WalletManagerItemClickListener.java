package com.tron.wallet.common.adapter.user;

import android.content.Context;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.common.adapter.RecyclerItemClickListener;
import com.tron.wallet.common.components.LoadingView;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
public class WalletManagerItemClickListener extends RecyclerItemClickListener {
    public void onInnerViewClicked(View view, int i) {
    }

    public WalletManagerItemClickListener(Context context, RecyclerView recyclerView, RecyclerItemClickListener.OnItemClickListener onItemClickListener) {
        super(context, recyclerView, onItemClickListener);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder == null) {
            return false;
        }
        int childAdapterPosition = recyclerView.getChildAdapterPosition(findChildViewUnder);
        View findViewById = findChildViewUnder.findViewById(R.id.asset_status);
        View findViewById2 = findChildViewUnder.findViewById(R.id.iv_copy);
        LoadingView loadingView = (LoadingView) findChildViewUnder.findViewById(R.id.rl_loading);
        if (containsViewIn(findViewById, motionEvent, 0)) {
            if (findViewById.getVisibility() == 0 && this.mGestureDetector.onTouchEvent(motionEvent)) {
                onInnerViewClicked(findViewById, childAdapterPosition);
            }
            return false;
        } else if (containsViewIn(findViewById2, motionEvent, UIUtils.dip2px(5.0f)) && this.mGestureDetector.onTouchEvent(motionEvent)) {
            onInnerViewClicked(findViewById2, childAdapterPosition);
            return false;
        } else if (containsViewIn(loadingView, motionEvent, UIUtils.dip2px(5.0f)) && this.mGestureDetector.onTouchEvent(motionEvent) && loadingView.getState() == LoadingView.State.FAIL) {
            loadingView.updateState(LoadingView.State.LOADING);
            onInnerViewClicked(loadingView, childAdapterPosition);
            return false;
        } else {
            return super.onInterceptTouchEvent(recyclerView, motionEvent);
        }
    }

    private boolean containsViewIn(View view, MotionEvent motionEvent, int i) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        return new RectF(i2 - i, iArr[1] - i, i2 + view.getMeasuredWidth() + i, iArr[1] + view.getMeasuredHeight() + i).contains(motionEvent.getRawX(), motionEvent.getRawY());
    }
}
