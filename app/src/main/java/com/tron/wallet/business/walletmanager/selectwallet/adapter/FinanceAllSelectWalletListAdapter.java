package com.tron.wallet.business.walletmanager.selectwallet.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.selectwallet.bean.FinanceSelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.common.components.SearchLoadMoreView;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemWalletFinanceSwitchBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.ledger.LedgerWallet;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class FinanceAllSelectWalletListAdapter extends BaseQuickAdapter<FinanceSelectWalletBean, SearchHolder> {
    public FinanceAllSelectWalletListAdapter() {
        super((int) R.layout.item_wallet_finance_switch);
        setLoadMoreView(new SearchLoadMoreView());
    }

    @Override
    public void convert(SearchHolder searchHolder, FinanceSelectWalletBean financeSelectWalletBean) {
        searchHolder.onBind(financeSelectWalletBean);
    }

    public static class SearchHolder extends BaseViewHolder {
        TextView address;
        TextView balance;
        TextView balance1;
        ImageView ivSelected;
        View llRoot;
        ImageView walletIcon;
        TextView walletName;

        public SearchHolder(View view) {
            super(view);
            ItemWalletFinanceSwitchBinding bind = ItemWalletFinanceSwitchBinding.bind(view);
            this.llRoot = bind.getRoot();
            this.walletName = bind.tvWalletName;
            this.walletIcon = bind.ivIcon;
            this.ivSelected = bind.assetStatus;
            this.balance = bind.tvBalance;
            this.balance1 = bind.tvBalance1;
            this.address = bind.tvAddress;
            addOnClickListener(R.id.item_layout);
        }

        public void onBind(FinanceSelectWalletBean financeSelectWalletBean) {
            int i;
            String str;
            String str2;
            try {
                Wallet wallet = financeSelectWalletBean.getWallet();
                if (LedgerWallet.isLedger(wallet)) {
                    i = R.mipmap.ic_wallet_ledger;
                } else if (wallet.isSamsungWallet()) {
                    i = R.mipmap.icon_dapp_samsung;
                } else if (wallet.isWatchCold()) {
                    i = R.mipmap.icon_wallet_watch_cold;
                } else {
                    i = wallet.isWatchOnly() ? R.mipmap.ic_wallet_observe : R.mipmap.ic_wallet_hot;
                }
                this.walletIcon.setImageResource(i);
                this.ivSelected.setImageResource(financeSelectWalletBean.isSelected() ? R.mipmap.ic_wallet_select : R.mipmap.ic_wallet_unselect);
                this.walletName.setText(wallet.getWalletName());
                this.address.setText("(" + StringTronUtil.indentAddress(wallet.getAddress(), 6) + ")");
                int i2 = fun1.$SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget[financeSelectWalletBean.getSearchedTarget().ordinal()];
                if (i2 == 1) {
                    this.walletName.setText(financeSelectWalletBean.getSearchedString());
                } else if (i2 == 2) {
                    this.address.setText(financeSelectWalletBean.getSearchedString());
                }
                if (financeSelectWalletBean.isUpdateResult()) {
                    String str3 = SpAPI.THIS.isUsdPrice() ? "$" : "¥";
                    if (BigDecimalUtils.between(financeSelectWalletBean.getFinanceAsset(), 0, Double.valueOf(0.001d))) {
                        str = "<" + str3 + "0.001";
                    } else {
                        str = str3 + StringTronUtil.formatNumber3Truncate(BigDecimalUtils.toBigDecimal(financeSelectWalletBean.getFinanceAsset()));
                    }
                    this.balance.setText(this.balance.getContext().getResources().getString(R.string.finance_item_asset) + " " + str);
                    if (BigDecimalUtils.LessThan((Object) financeSelectWalletBean.getAllAsset(), (Object) 0)) {
                        str2 = "<" + str3 + "0.001";
                    } else {
                        str2 = str3 + StringTronUtil.formatNumber3Truncate(BigDecimalUtils.toBigDecimal(financeSelectWalletBean.getAllAsset()));
                    }
                    this.balance1.setText(this.balance1.getContext().getResources().getString(R.string.finance_item_asset_all) + " " + str2);
                    return;
                }
                this.balance.setText(this.balance.getContext().getResources().getString(R.string.finance_item_asset) + " --");
                this.balance1.setText(this.balance1.getContext().getResources().getString(R.string.finance_item_asset_all) + " --");
            } catch (Throwable th) {
                LogUtils.e(th);
            }
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
