package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.databinding.ItemCustomtokenErrorBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class CustomTokenNoFunctionView extends RecyclerView {
    private ErrorTipAdapter errorTipAdapter;

    public CustomTokenNoFunctionView(Context context) {
        super(context);
    }

    public CustomTokenNoFunctionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomTokenNoFunctionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setData(List<String> list) {
        setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        ErrorTipAdapter errorTipAdapter = new ErrorTipAdapter();
        this.errorTipAdapter = errorTipAdapter;
        setAdapter(errorTipAdapter);
        ((SimpleItemAnimator) getItemAnimator()).setSupportsChangeAnimations(false);
        this.errorTipAdapter.setNewData(list);
    }

    static class ErrorTipAdapter extends BaseQuickAdapter<String, ViewHolder> {
        public ErrorTipAdapter() {
            super((int) R.layout.item_customtoken_error);
        }

        @Override
        public void convert(ViewHolder viewHolder, String str) {
            viewHolder.onBind(str);
        }

        public static class ViewHolder extends BaseViewHolder {
            ItemCustomtokenErrorBinding binding;
            TextView textView;

            public ViewHolder(View view) {
                super(view);
                if (view != null) {
                    this.binding = ItemCustomtokenErrorBinding.bind(view);
                }
                mappingId();
            }

            private void mappingId() {
                this.textView = this.binding.tvTip;
            }

            public void onBind(String str) {
                this.textView.setText(str);
            }
        }
    }
}
