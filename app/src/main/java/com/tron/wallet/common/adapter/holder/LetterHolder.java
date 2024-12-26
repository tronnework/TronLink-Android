package com.tron.wallet.common.adapter.holder;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
public class LetterHolder extends RecyclerView.ViewHolder {
    public final TextView textView;

    public LetterHolder(View view) {
        super(view);
        this.textView = (TextView) view;
    }
}
