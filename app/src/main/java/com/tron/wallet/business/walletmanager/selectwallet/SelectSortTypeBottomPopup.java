package com.tron.wallet.business.walletmanager.selectwallet;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.PopupSelectWalletSortTypeBinding;
import com.tronlinkpro.wallet.R;
public class SelectSortTypeBottomPopup extends BottomPopupView {
    private PopupSelectWalletSortTypeBinding binding;
    TextView btnSortClass;
    TextView btnSortValue;
    View llRoot;
    private OnClickListener onClickListener;
    View tipView;
    private WalletSortType walletSortType;

    public interface OnClickListener {
        void onClick(WalletSortType walletSortType);
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_select_wallet_sort_type;
    }

    public SelectSortTypeBottomPopup setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public SelectSortTypeBottomPopup(Context context, WalletSortType walletSortType) {
        super(context);
        this.walletSortType = walletSortType;
    }

    public static SelectSortTypeBottomPopup showPopup(Context context, WalletSortType walletSortType) {
        return (SelectSortTypeBottomPopup) new XPopup.Builder(context).autoOpenSoftInput(false).moveUpToKeyboard(false).enableDrag(false).asCustom(new SelectSortTypeBottomPopup(context, walletSortType)).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.binding = PopupSelectWalletSortTypeBinding.bind(getPopupImplView());
        mappingId();
        setSelectedWalletSortType();
        this.llRoot.setOnClickListener(getOnClickListener());
        this.binding.ivBack.setOnClickListener(getOnClickListener());
        this.btnSortClass.setOnClickListener(getOnClickListener());
        this.btnSortValue.setOnClickListener(getOnClickListener());
        this.binding.btConfirm.setOnClickListener(getOnClickListener());
    }

    private void mappingId() {
        this.llRoot = this.binding.root;
        this.btnSortClass = this.binding.btnSortClass;
        this.btnSortValue = this.binding.btnSortValue;
        this.tipView = this.binding.sortTips;
    }

    public void setSelectedWalletSortType(WalletSortType walletSortType) {
        if (this.walletSortType == walletSortType) {
            return;
        }
        this.walletSortType = walletSortType;
        setSelectedWalletSortType();
    }

    private void setSelectedWalletSortType() {
        if (this.walletSortType == WalletSortType.SORT_BY_TYPE) {
            this.btnSortClass.setTextColor(getContext().getResources().getColor(R.color.blue_3c));
            this.btnSortClass.setSelected(true);
            this.tipView.setVisibility(View.GONE);
            this.btnSortValue.setTextColor(getContext().getResources().getColor(R.color.black_23));
            this.btnSortValue.setSelected(false);
            return;
        }
        this.btnSortClass.setTextColor(getContext().getResources().getColor(R.color.black_23));
        this.btnSortClass.setSelected(false);
        this.tipView.setVisibility(View.VISIBLE);
        this.btnSortValue.setTextColor(getContext().getResources().getColor(R.color.blue_3c));
        this.btnSortValue.setSelected(true);
        if (this.walletSortType != WalletSortType.SORT_BY_VALUE_DEFAULT) {
            setSelectedWalletSortType(WalletSortType.SORT_BY_VALUE_DEFAULT);
        }
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.bt_confirm:
                        if (onClickListener != null) {
                            onClickListener.onClick(walletSortType);
                        }
                        dismiss();
                        return;
                    case R.id.btn_sort_class:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SELECT_ACCOUNT_PAGE_SORT_BY_TYPE);
                        setSelectedWalletSortType(WalletSortType.SORT_BY_TYPE);
                        return;
                    case R.id.btn_sort_value:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SELECT_ACCOUNT_PAGE_SORT_BY_VALUE);
                        if (walletSortType != WalletSortType.SORT_BY_VALUE_DEFAULT) {
                            setSelectedWalletSortType(WalletSortType.SORT_BY_VALUE_DEFAULT);
                            return;
                        }
                        return;
                    case R.id.iv_back:
                    case R.id.root:
                        dismiss();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        this.binding = null;
        super.onDestroy();
    }
}
