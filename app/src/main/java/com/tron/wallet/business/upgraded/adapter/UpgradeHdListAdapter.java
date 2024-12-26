package com.tron.wallet.business.upgraded.adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.upgraded.bean.UpgradeHdListBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class UpgradeHdListAdapter extends BaseQuickAdapter<UpgradeHdListBean, BaseViewHolder> {
    public UpgradeHdListAdapter() {
        super((int) R.layout.item_upgrade_hd_list);
    }

    @Override
    public BaseViewHolder createBaseViewHolder(View view) {
        return new UpgradedHdListHolder(view);
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, UpgradeHdListBean upgradeHdListBean) {
        if (baseViewHolder instanceof UpgradedHdListHolder) {
            ((UpgradedHdListHolder) baseViewHolder).onBind(upgradeHdListBean);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        super.onBindViewHolder((UpgradeHdListAdapter) baseViewHolder, i);
        if (baseViewHolder instanceof UpgradedHdListHolder) {
            ((UpgradedHdListHolder) baseViewHolder).showLine(i);
        }
    }

    public class UpgradedHdListHolder extends BaseViewHolder {
        String TRX;
        View line;
        RelativeLayout root;
        TextView tvWalletAddress;
        TextView tvWalletAssets;
        TextView tvWalletName;
        View vTop;

        public UpgradedHdListHolder(View view) {
            super(view);
            this.TRX = " TRX";
            mappingId(view);
        }

        public void onBind(UpgradeHdListBean upgradeHdListBean) {
            this.tvWalletName.setText(upgradeHdListBean.getWalletName());
            TextView textView = this.tvWalletAssets;
            textView.setText(StringTronUtil.formatNumber3Truncate(Double.valueOf(upgradeHdListBean.getBalance())) + this.TRX);
            this.tvWalletAddress.setText(upgradeHdListBean.getWalletAddress());
        }

        public void showLine(int i) {
            List<UpgradeHdListBean> data = getData();
            int size = data == null ? 0 : data.size() - 1;
            this.vTop.setVisibility(i == 0 ? View.GONE : View.VISIBLE);
            this.line.setVisibility(i == size ? 4 : 0);
        }

        private void mappingId(View view) {
            this.tvWalletName = (TextView) view.findViewById(R.id.tv_wallet_name);
            this.tvWalletAssets = (TextView) view.findViewById(R.id.tv_wallet_assets);
            this.tvWalletAddress = (TextView) view.findViewById(R.id.tv_wallet_address);
            this.root = (RelativeLayout) view.findViewById(R.id.root);
            this.vTop = view.findViewById(R.id.v_top);
            this.line = view.findViewById(R.id.line);
        }
    }
}
