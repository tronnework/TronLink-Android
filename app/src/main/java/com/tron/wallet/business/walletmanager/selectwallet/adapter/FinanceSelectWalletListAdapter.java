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
import com.tron.wallet.databinding.ItemWalletFinanceListBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.ledger.LedgerWallet;
import com.tronlinkpro.wallet.R;
import org.apache.commons.cli.HelpFormatter;
import org.tron.walletserver.Wallet;
public class FinanceSelectWalletListAdapter extends BaseQuickAdapter<FinanceSelectWalletBean, SearchHolder> {
    private boolean hideAssets;
    private SearchLoadMoreView loadMore;
    private String projectId;

    public void setHideAssets(boolean z) {
        this.hideAssets = z;
    }

    public void setProjectId(String str) {
        this.projectId = str;
    }

    public FinanceSelectWalletListAdapter() {
        super((int) R.layout.item_wallet_finance_list);
        this.hideAssets = false;
        SearchLoadMoreView searchLoadMoreView = new SearchLoadMoreView();
        this.loadMore = searchLoadMoreView;
        setLoadMoreView(searchLoadMoreView);
    }

    @Override
    public void convert(SearchHolder searchHolder, FinanceSelectWalletBean financeSelectWalletBean) {
        searchHolder.onBind(financeSelectWalletBean, this.hideAssets, this.projectId);
    }

    public void setLoadMoreText(int i) {
        this.loadMore.setNoMoreText(i, new Object[0]);
    }

    public static class SearchHolder extends BaseViewHolder {
        TextView address;
        TextView balance;
        ImageView ivSelected;
        View llRoot;
        ImageView walletIcon;
        TextView walletName;

        public SearchHolder(View view) {
            super(view);
            ItemWalletFinanceListBinding bind = ItemWalletFinanceListBinding.bind(view);
            this.llRoot = bind.getRoot();
            this.walletName = bind.tvWalletName;
            this.walletIcon = bind.ivIcon;
            this.ivSelected = bind.assetStatus;
            this.balance = bind.tvBalance;
            this.address = bind.tvAddress;
            addOnClickListener(R.id.item_layout);
        }

        public void onBind(FinanceSelectWalletBean financeSelectWalletBean, boolean z, String str) {
            int i;
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
                this.address.setText(wallet.getAddress());
                int i2 = fun1.$SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget[financeSelectWalletBean.getSearchedTarget().ordinal()];
                if (i2 == 1) {
                    this.walletName.setText(financeSelectWalletBean.getSearchedString());
                } else if (i2 == 2) {
                    this.address.setText(financeSelectWalletBean.getSearchedString());
                }
                if (!SpAPI.THIS.isCold() && !wallet.isShieldedWallet()) {
                    if (SpAPI.THIS.isShasta()) {
                        this.balance.setText("Shasta");
                        return;
                    } else if (z) {
                        this.balance.setText(this.itemView.getResources().getString(R.string.asset_hidden_string));
                        return;
                    } else {
                        int i3 = StringTronUtil.isEmpty(str) ? R.string.finance_avaliable : R.string.finance_used;
                        if (financeSelectWalletBean.isUpdateResult()) {
                            if (BigDecimalUtils.between(financeSelectWalletBean.getFinanceBalance(), 0, Double.valueOf(1.0E-6d))) {
                                TextView textView = this.balance;
                                textView.setText(this.balance.getContext().getResources().getString(i3) + " <0.000001 " + financeSelectWalletBean.getTokenName());
                                return;
                            }
                            String formatNumber6Truncate = StringTronUtil.formatNumber6Truncate(BigDecimalUtils.toBigDecimal(financeSelectWalletBean.getFinanceBalance()));
                            TextView textView2 = this.balance;
                            textView2.setText(this.balance.getContext().getResources().getString(i3) + " " + formatNumber6Truncate + " " + financeSelectWalletBean.getTokenName());
                            return;
                        }
                        this.balance.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                        return;
                    }
                }
                this.balance.setText("-- TRX");
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
