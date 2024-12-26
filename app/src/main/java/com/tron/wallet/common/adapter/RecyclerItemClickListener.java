package com.tron.wallet.common.adapter;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    protected GestureDetector mGestureDetector;
    protected OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);

        void onLongItemClick(View view, int i);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (findChildViewUnder == null || mListener == null) {
                    return;
                }
                mListener.onLongItemClick(findChildViewUnder, recyclerView.getChildAdapterPosition(findChildViewUnder));
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        GestureDetector gestureDetector;
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder == null || this.mListener == null || (gestureDetector = this.mGestureDetector) == null || !gestureDetector.onTouchEvent(motionEvent)) {
            return false;
        }
        this.mListener.onItemClick(findChildViewUnder, recyclerView.getChildAdapterPosition(findChildViewUnder));
        return true;
    }
}
