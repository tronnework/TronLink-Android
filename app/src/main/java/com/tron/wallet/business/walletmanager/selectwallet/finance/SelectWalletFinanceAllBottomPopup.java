package com.tron.wallet.business.walletmanager.selectwallet.finance;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.wallet.business.walletmanager.selectwallet.adapter.FinanceAllSelectWalletListAdapter;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.controller.RecentlyWalletController;
import com.tron.wallet.business.walletmanager.selectwallet.finance.SelectWalletFinanceContract;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.databinding.PopupSelectWalletFinanceSummaryBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class SelectWalletFinanceAllBottomPopup extends BottomPopupView implements SelectWalletFinanceContract.IView {
    private PopupSelectWalletFinanceSummaryBinding binding;
    private boolean firstEnter;
    private boolean hasSelected;
    private boolean isUsd;
    ImageView ivAllAssetSelect;
    View ivAsk;
    ImageView ivPlaceHolder;
    ImageView ivPlaceHolderBody;
    RecyclerView listView;
    View llAllAsset;
    View llContent;
    View llRoot;
    private SelectWalletFinanceContract.Presenter mPresenter;
    private OnClickListener onClickListener;
    private OnDismissListener onDismissListener;
    private FinanceAllSelectWalletListAdapter resultAdapter;
    private Wallet selectedWallet;
    TextView tvFinanceAsset;
    TextView tvFinanceAssetAll;
    TextView tvFinancePercent;
    TextView tvWalletList;

    public interface OnClickListener {
        void onClick(Wallet wallet);
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_select_wallet_finance_summary;
    }

    public SelectWalletFinanceAllBottomPopup(Context context, Wallet wallet, OnClickListener onClickListener, OnDismissListener onDismissListener) {
        super(context);
        this.firstEnter = true;
        this.isUsd = true;
        this.selectedWallet = wallet;
        this.onClickListener = onClickListener;
        this.onDismissListener = onDismissListener;
        this.mPresenter = new SelectWalletFinancePresenter();
        SelectWalletFinanceModel selectWalletFinanceModel = new SelectWalletFinanceModel();
        selectWalletFinanceModel.setSearchedTextColor(context.getResources().getColor(R.color.blue_3c));
        this.mPresenter.setVM(this, selectWalletFinanceModel);
        ((SelectWalletFinancePresenter) this.mPresenter).onStart();
        this.mPresenter.setSelectedWallet(this.selectedWallet);
    }

    public static SelectWalletFinanceAllBottomPopup showPopup(Context context, Wallet wallet, OnClickListener onClickListener, OnDismissListener onDismissListener) {
        return (SelectWalletFinanceAllBottomPopup) new XPopup.Builder(context).autoOpenSoftInput(false).moveUpToKeyboard(false).enableDrag(false).asCustom(new SelectWalletFinanceAllBottomPopup(context, wallet, onClickListener, onDismissListener)).show();
    }

    @Override
    public void onDismiss() {
        OnDismissListener onDismissListener;
        KeyboardUtils.hideSoftInput(getPopupContentView());
        if (!this.hasSelected && (onDismissListener = this.onDismissListener) != null) {
            onDismissListener.onDismiss();
        }
        super.onDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.onDestroy();
        this.binding = null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.binding = PopupSelectWalletFinanceSummaryBinding.bind(getPopupImplView());
        mappingId();
        this.binding.ivCommonRight.setOnClickListener(getOnClickListener());
        this.llRoot.setOnClickListener(getOnClickListener());
        this.isUsd = SpAPI.THIS.isUsdPrice();
        if (this.selectedWallet != null) {
            this.ivAllAssetSelect.setImageResource(R.mipmap.ic_wallet_unselect);
        } else {
            this.ivAllAssetSelect.setImageResource(R.mipmap.ic_wallet_select);
        }
        this.llAllAsset.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                hasSelected = true;
                if (onClickListener != null) {
                    onClickListener.onClick(null);
                }
                dismiss();
            }
        });
        this.ivAsk.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                PopupWindowUtil.showPopupText(getContext(), getResources().getString(R.string.finance_wallet_summary_desc), ivAsk, false);
            }
        });
        this.listView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        FinanceAllSelectWalletListAdapter financeAllSelectWalletListAdapter = new FinanceAllSelectWalletListAdapter();
        this.resultAdapter = financeAllSelectWalletListAdapter;
        financeAllSelectWalletListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Object item = baseQuickAdapter.getItem(i);
                if (item == null) {
                    return;
                }
                Wallet wallet = ((SelectWalletBean) item).getWallet();
                if (view.getId() == R.id.item_layout) {
                    RecentlyWalletController.setRecentlyWallet(wallet);
                    WalletUtils.setSelectedWallet(wallet.getWalletName());
                    SpAPI.THIS.setColdWalletSelected(wallet.getWalletName());
                    hasSelected = true;
                    if (onClickListener != null) {
                        onClickListener.onClick(wallet);
                    }
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchWalletPage.CLICK_WALLET);
                    dismiss();
                }
            }
        });
        this.listView.setAdapter(this.resultAdapter);
        ((SimpleItemAnimator) this.listView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mPresenter.search(null);
        AnalyticsHelper.logEvent(AnalyticsHelper.FinancePage.FINANCE_PAGE_SWITCH_ACCOUNT_POPUP_SHOW);
    }

    private void mappingId() {
        this.llRoot = this.binding.root;
        this.llContent = this.binding.llContent;
        this.llAllAsset = this.binding.llAllAsset;
        this.listView = this.binding.rvList;
        this.tvFinanceAsset = this.binding.tvFinanceAsset;
        this.tvFinanceAssetAll = this.binding.tvFinanceAssetAll;
        this.tvFinancePercent = this.binding.tvFinanceAssetPercent;
        this.tvWalletList = this.binding.tvWalletList;
        this.ivAllAssetSelect = this.binding.ivAllAssetSelect;
        this.ivPlaceHolder = this.binding.ivPlaceHolder;
        this.ivPlaceHolderBody = this.binding.ivPlaceHolderBody;
        this.ivAsk = this.binding.ivAsk;
    }

    @Override
    public void doAfterShow() {
        super.doAfterShow();
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_common_right) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchWalletPage.CLICK_CLOSE);
                    dismiss();
                } else if (id != R.id.root) {
                } else {
                    dismiss();
                }
            }
        };
    }

    @Override
    public Context getIContext() {
        return getContext();
    }

    @Override
    public void onSearchComplete(java.util.List<com.tron.wallet.business.walletmanager.selectwallet.bean.FinanceSelectWalletBean> r10, java.lang.String r11) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.walletmanager.selectwallet.finance.SelectWalletFinanceAllBottomPopup.onSearchComplete(java.util.List, java.lang.String):void");
    }
}
