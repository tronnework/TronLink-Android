package com.tron.wallet.business.upgraded.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.upgraded.bean.UpgradeHdBean;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
public class UpgradeHdAdapter extends BaseQuickAdapter<UpgradeHdBean, BaseViewHolder> {
    private OnItemViewClickListener onItemViewClickListener;

    public interface OnItemViewClickListener {
        void onViewClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public UpgradeHdAdapter() {
        super(R.layout.item_upgrade_hd, new ArrayList());
    }

    @Override
    public BaseViewHolder createBaseViewHolder(View view) {
        return new UpgradedViewHolder(view);
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, UpgradeHdBean upgradeHdBean) {
        if (baseViewHolder instanceof UpgradedViewHolder) {
            ((UpgradedViewHolder) baseViewHolder).onBind(upgradeHdBean, this.onItemViewClickListener, this);
        }
    }

    public static class UpgradedViewHolder extends BaseViewHolder {
        ImageView ivArrow;
        ImageView ivSelectStatus;
        View mask;
        RecyclerView rcInnerContent;
        View rlArrow;
        View root;
        TextView tvAssets;
        TextView tvName;
        TextView tvRelated;
        private UpgradeHdListAdapter upgradeHdListAdapter;

        public UpgradedViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void onBind(UpgradeHdBean upgradeHdBean, final OnItemViewClickListener onItemViewClickListener, final BaseQuickAdapter baseQuickAdapter) {
            this.tvName.setText(upgradeHdBean.getTitle());
            TextView textView = this.tvAssets;
            textView.setText(StringTronUtil.formatNumber3Truncate(upgradeHdBean.getBalance()) + " TRX");
            this.tvRelated.setText(String.valueOf(upgradeHdBean.getUpgradeHdLists() == null ? "---" : Integer.valueOf(upgradeHdBean.getUpgradeHdLists().size())));
            if (this.upgradeHdListAdapter == null) {
                this.upgradeHdListAdapter = new UpgradeHdListAdapter();
                this.rcInnerContent.setLayoutManager(new LinearLayoutManager(this.root.getContext(), 1, false));
                this.rcInnerContent.setAdapter(this.upgradeHdListAdapter);
            }
            this.upgradeHdListAdapter.setNewData(upgradeHdBean.getUpgradeHdLists());
            if (upgradeHdBean.isExpand()) {
                this.mask.setVisibility(View.VISIBLE);
                this.ivArrow.setImageResource(R.mipmap.top_arrow);
                this.rcInnerContent.setVisibility(View.VISIBLE);
            } else {
                this.mask.setVisibility(View.GONE);
                this.ivArrow.setImageResource(R.mipmap.bottom_arrow);
                this.rcInnerContent.setVisibility(View.GONE);
            }
            this.ivSelectStatus.setImageResource(upgradeHdBean.isSelected() ? R.mipmap.ic_wallet_select : R.mipmap.icon_hd_unselected);
            this.rlArrow.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    OnItemViewClickListener onItemViewClickListener2 = onItemViewClickListener;
                    if (onItemViewClickListener2 != null) {
                        onItemViewClickListener2.onViewClick(baseQuickAdapter, view, UpgradedViewHolder.this.getAbsoluteAdapterPosition());
                    }
                }
            });
            this.root.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    OnItemViewClickListener onItemViewClickListener2 = onItemViewClickListener;
                    if (onItemViewClickListener2 != null) {
                        onItemViewClickListener2.onViewClick(baseQuickAdapter, view, UpgradedViewHolder.this.getAbsoluteAdapterPosition());
                    }
                }
            });
        }

        private void mappingId(View view) {
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.tvAssets = (TextView) view.findViewById(R.id.tv_assets);
            this.tvRelated = (TextView) view.findViewById(R.id.tv_related);
            this.rcInnerContent = (RecyclerView) view.findViewById(R.id.rc_inner_content);
            this.ivSelectStatus = (ImageView) view.findViewById(R.id.iv_select_status);
            this.rlArrow = view.findViewById(R.id.rl_arrow);
            this.ivArrow = (ImageView) view.findViewById(R.id.iv_arrow);
            this.mask = view.findViewById(R.id.mask);
            this.root = view.findViewById(R.id.root);
        }
    }
}
