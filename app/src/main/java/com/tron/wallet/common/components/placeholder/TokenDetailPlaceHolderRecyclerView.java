package com.tron.wallet.common.components.placeholder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.databinding.ItemTokendetailPlaceholderBinding;
import com.tronlinkpro.wallet.R;
public class TokenDetailPlaceHolderRecyclerView extends RecyclerView {
    public TokenDetailPlaceHolderRecyclerView(Context context) {
        this(context, null);
    }

    public TokenDetailPlaceHolderRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TokenDetailPlaceHolderRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutManager(new WrapContentLinearLayoutManager(getContext(), 1, false));
        setAdapter(new PHAdapter());
    }

    private static class PHAdapter extends RecyclerView.Adapter {
        @Override
        public int getItemCount() {
            return 8;
        }

        private PHAdapter() {
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new PlaceHolderVH(View.inflate(viewGroup.getContext(), R.layout.item_tokendetail_placeholder, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ((PlaceHolderVH) viewHolder).onBind(getItemCount());
        }
    }

    public static class PlaceHolderVH extends BaseHolder {
        private static final int DEFAULT_HOLDER_COUNT = 8;
        ItemTokendetailPlaceholderBinding binding;
        ImageView ivPlaceHolder;

        private void startAnimal() {
        }

        public PlaceHolderVH(View view) {
            super(view);
            ItemTokendetailPlaceholderBinding bind = ItemTokendetailPlaceholderBinding.bind(view);
            this.binding = bind;
            this.ivPlaceHolder = bind.ivTokenDetailPlaceholder;
        }

        public void onBind(int i) {
            setBackground(i);
            startAnimal();
        }

        public void setBackground(int i) {
            if (getAdapterPosition() == 0) {
                this.ivPlaceHolder.setImageResource(R.mipmap.iv_token_detail_placeholder_up);
            } else if (getAdapterPosition() == i - 1) {
                this.ivPlaceHolder.setImageResource(R.mipmap.iv_token_detail_placeholder_bottom);
            } else {
                this.ivPlaceHolder.setImageResource(R.mipmap.iv_token_detail_placeholder_mid);
            }
        }
    }
}
