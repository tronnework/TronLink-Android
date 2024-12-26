package com.tron.wallet.business.walletmanager.selectwallet.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.common.components.MultiSignPopupTextView;
import com.tron.wallet.common.components.SearchLoadMoreView;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.ItemWalletListBinding;
import com.tronlinkpro.wallet.R;
public class SearchWalletListAdapter extends BaseQuickAdapter<SelectWalletBean, SearchHolder> {
    private boolean hideAssets;

    public void setHideAssets(boolean z) {
        this.hideAssets = z;
    }

    public SearchWalletListAdapter() {
        super((int) R.layout.item_wallet_list);
        this.hideAssets = false;
        setLoadMoreView(new SearchLoadMoreView());
    }

    @Override
    public void convert(SearchHolder searchHolder, SelectWalletBean selectWalletBean) {
        searchHolder.onBind(selectWalletBean, this.hideAssets);
    }

    public static class SearchHolder extends BaseViewHolder {
        TextView address;
        TextView balance;
        MultiSignPopupTextView flagMultiSign;
        ImageView ivCopy;
        View ivSelected;
        View llRoot;
        View rlAddress;
        ImageView walletIcon;
        TextView walletName;

        public SearchHolder(View view) {
            super(view);
            ItemWalletListBinding bind = ItemWalletListBinding.bind(view);
            this.llRoot = bind.getRoot();
            this.walletName = bind.tvWalletName;
            this.walletIcon = bind.ivIcon;
            this.ivSelected = bind.assetStatus;
            this.balance = bind.tvBalance;
            this.address = bind.tvAddress;
            this.ivCopy = bind.ivCopy;
            this.rlAddress = bind.rlAddress;
            this.flagMultiSign = bind.flagMultiSign;
            TouchDelegateUtils.expandViewTouchDelegate(this.rlAddress, 10);
            addOnClickListener(R.id.rl_address);
            addOnClickListener(R.id.item_layout);
        }

        public void onBind(com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean r8, boolean r9) {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.walletmanager.selectwallet.adapter.SearchWalletListAdapter.SearchHolder.onBind(com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean, boolean):void");
        }
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget;

        static {
            int[] iArr = new int[SelectWalletBean.SearchedTarget.values().length];
            $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget = iArr;
            try {
                iArr[SelectWalletBean.SearchedTarget.NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget[SelectWalletBean.SearchedTarget.Address.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
