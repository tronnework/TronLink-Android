package com.tron.wallet.common.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.common.components.CircularImageDraweeView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemTokenSelectBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class SelectTokenAdapter extends RecyclerView.Adapter {
    SwapWhiteListOutput.Data currentData;
    private List<SwapWhiteListOutput.Data> datas;
    private OnItemClickListener mOnItemClickLitener;
    private int selected = -1;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public void setOnItemClickLitener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickLitener = onItemClickListener;
    }

    public SelectTokenAdapter(List<SwapWhiteListOutput.Data> list, SwapWhiteListOutput.Data data) {
        this.datas = list;
        this.currentData = data;
    }

    public void setSelection(int i) {
        this.selected = i;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SingleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_token_select, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        SwapWhiteListOutput.Data data;
        if (viewHolder instanceof SingleViewHolder) {
            final SingleViewHolder singleViewHolder = (SingleViewHolder) viewHolder;
            SwapWhiteListOutput.Data data2 = this.datas.get(singleViewHolder.getBindingAdapterPosition());
            singleViewHolder.mTvName.setText(data2.getSymbol());
            if (TextUtils.equals(data2.getSymbol(), "TRX")) {
                singleViewHolder.mSimpleDraweeView.setImageURI("res://mipmap/2131624909");
                singleViewHolder.mTvAddress.setVisibility(View.GONE);
            } else {
                if (!StringTronUtil.isEmpty(data2.getLogoURI())) {
                    singleViewHolder.mSimpleDraweeView.setCircularImage(data2.getLogoURI());
                }
                singleViewHolder.mTvAddress.setText(data2.getAddress());
                singleViewHolder.mTvAddress.setVisibility(View.VISIBLE);
            }
            if (this.selected < 0 && (data = this.currentData) != null && data.getSymbol() != null && this.currentData.getSymbol().equals(this.datas.get(singleViewHolder.getBindingAdapterPosition()).getSymbol())) {
                this.selected = singleViewHolder.getBindingAdapterPosition();
            }
            if (this.selected == singleViewHolder.getBindingAdapterPosition()) {
                singleViewHolder.mCheckBox.setChecked(true);
                singleViewHolder.itemView.setSelected(true);
                singleViewHolder.layout.setBackgroundResource(R.color.blue_13_6);
            } else {
                singleViewHolder.mCheckBox.setChecked(false);
                singleViewHolder.itemView.setSelected(false);
                singleViewHolder.layout.setBackgroundResource(R.drawable.selector_item_select_token);
            }
            if (this.mOnItemClickLitener != null) {
                singleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (singleViewHolder.getAdapterPosition() <= -1 || datas == null || singleViewHolder.getAdapterPosition() >= datas.size()) {
                            return;
                        }
                        mOnItemClickLitener.onItemClick(singleViewHolder.itemView, singleViewHolder.getAdapterPosition());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.datas.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {
        ItemTokenSelectBinding binding;
        RelativeLayout layout;
        CheckBox mCheckBox;
        CircularImageDraweeView mSimpleDraweeView;
        TextView mTvAddress;
        TextView mTvName;

        public SingleViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        private void mappingId(View view) {
            ItemTokenSelectBinding bind = ItemTokenSelectBinding.bind(view);
            this.binding = bind;
            this.layout = bind.itemLayout;
            this.mSimpleDraweeView = this.binding.tokenIcon;
            this.mTvName = this.binding.tvTokenName;
            this.mTvAddress = this.binding.tvTokenAddress;
            this.mCheckBox = this.binding.checkbox;
        }
    }
}
