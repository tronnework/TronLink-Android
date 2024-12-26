package com.tron.wallet.common.components.bannerview;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public abstract void bindData(T t, int i, int i2);

    public BaseViewHolder(View view) {
        super(view);
        this.mViews = new SparseArray<>();
    }

    protected <V extends View> V findView(int i) {
        V v = (V) this.mViews.get(i);
        if (v == null) {
            V v2 = (V) this.itemView.findViewById(i);
            this.mViews.put(i, v2);
            return v2;
        }
        return v;
    }

    protected void setText(int i, String str) {
        View findView = findView(i);
        if (findView instanceof TextView) {
            ((TextView) findView).setText(str);
        }
    }

    protected void setText(int i, int i2) {
        View findView = findView(i);
        if (findView instanceof TextView) {
            ((TextView) findView).setText(i2);
        }
    }

    protected void setTextColor(int i, int i2) {
        View findView = findView(i);
        if (findView instanceof TextView) {
            ((TextView) findView).setTextColor(i2);
        }
    }

    protected void setOnClickListener(int i, View.OnClickListener onClickListener) {
        findView(i).setOnClickListener(onClickListener);
    }

    protected void setBackgroundResource(int i, int i2) {
        findView(i).setBackgroundResource(i2);
    }

    protected void setBackgroundColor(int i, int i2) {
        findView(i).setBackgroundColor(i2);
    }

    protected void setImageResource(int i, int i2) {
        View findView = findView(i);
        if (findView instanceof ImageView) {
            ((ImageView) findView).setImageResource(i2);
        }
    }

    protected void setImageUrl(int i, String str) {
        View findView = findView(i);
        if (findView instanceof SimpleDraweeView) {
            ((SimpleDraweeView) findView).setImageURI(str);
        }
    }
}
