package com.tron.wallet.business.walletmanager.selectwallet.search;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.business.walletmanager.selectwallet.adapter.SearchWalletListAdapter;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.controller.RecentlyWalletController;
import com.tron.wallet.business.walletmanager.selectwallet.search.SearchWalletContract;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.PopupSelectWalletBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.Iterator;
import java.util.List;
import org.tron.walletserver.Wallet;
public class SelectWalletBottomPopup extends BottomPopupView implements SearchWalletContract.IView {
    private PopupSelectWalletBinding binding;
    EditText etSearch;
    private boolean firstEnter;
    ImageView ivClear;
    RecyclerView listView;
    View llRoot;
    View llSearch;
    private SearchWalletContract.IPresenter mPresenter;
    NoNetView noDataView;
    private OnClickListener onClickListener;
    private OnDismissListener onDismissListener;
    private SearchWalletListAdapter resultAdapter;
    private Wallet selectedWallet;

    public interface OnClickListener {
        void onClick(Wallet wallet);
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_select_wallet;
    }

    @Override
    public void updateRecentWallets(List<SelectWalletBean> list) {
    }

    public SelectWalletBottomPopup(Context context, Wallet wallet, OnClickListener onClickListener, OnDismissListener onDismissListener) {
        super(context);
        this.firstEnter = true;
        this.selectedWallet = wallet;
        this.onClickListener = onClickListener;
        this.onDismissListener = onDismissListener;
        this.mPresenter = new SearchWalletPresenter();
        SearchWalletModel searchWalletModel = new SearchWalletModel();
        searchWalletModel.setSearchedTextColor(context.getResources().getColor(R.color.blue_3c));
        ((BasePresenter) this.mPresenter).setVM(this, searchWalletModel);
        ((SearchWalletPresenter) this.mPresenter).onStart();
    }

    public static SelectWalletBottomPopup showPopup(Context context, Wallet wallet, OnClickListener onClickListener, OnDismissListener onDismissListener) {
        return (SelectWalletBottomPopup) new XPopup.Builder(context).autoOpenSoftInput(false).moveUpToKeyboard(false).enableDrag(false).asCustom(new SelectWalletBottomPopup(context, wallet, onClickListener, onDismissListener)).show();
    }

    @Override
    public void onDismiss() {
        KeyboardUtils.hideSoftInput(getPopupContentView());
        OnDismissListener onDismissListener = this.onDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        super.onDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BasePresenter) this.mPresenter).onDestroy();
        this.binding = null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.binding = PopupSelectWalletBinding.bind(getPopupImplView());
        mappingId();
        this.llRoot.setOnClickListener(getOnClickListener());
        this.binding.ivCommonRight.setOnClickListener(getOnClickListener());
        this.ivClear.setOnClickListener(getOnClickListener());
        this.etSearch.setInputType(1);
        this.etSearch.setImeOptions(6);
        this.listView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        SearchWalletListAdapter searchWalletListAdapter = new SearchWalletListAdapter();
        this.resultAdapter = searchWalletListAdapter;
        searchWalletListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Object item = baseQuickAdapter.getItem(i);
                if (item == null) {
                    return;
                }
                Wallet wallet = ((SelectWalletBean) item).getWallet();
                if (view.getId() == R.id.rl_address) {
                    if (!BackupReminder.isWalletBackup(wallet)) {
                        IToast.getIToast().show(R.string.not_backup_cant_copy_address);
                        return;
                    }
                    UIUtils.copy(wallet.getAddress());
                    IToast.getIToast().show(R.string.already_copy);
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchWalletPage.CLICK_COPY);
                } else if (view.getId() == R.id.item_layout) {
                    RecentlyWalletController.setRecentlyWallet(wallet);
                    WalletUtils.setSelectedWallet(wallet.getWalletName());
                    SpAPI.THIS.setColdWalletSelected(wallet.getWalletName());
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
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.input_wallet_name_address));
        spannableString.setSpan(new StyleSpan(0), 0, spannableString.length(), 33);
        spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString.length(), 33);
        this.etSearch.setHint(spannableString);
        this.etSearch.setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
        this.etSearch.setEnabled(false);
        this.etSearch.addTextChangedListener(new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                if (StringTronUtil.isEmpty(obj)) {
                    etSearch.setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
                } else {
                    etSearch.setTypeface(CustomFontUtils.getTypeface(getContext(), 1));
                }
                if (!TextUtils.isEmpty(obj) && ivClear.getVisibility() == 8) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.TransferSelectTokenPage.CLICK_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_TOKEN);
                }
                ivClear.setVisibility(TextUtils.isEmpty(obj) ? View.GONE : View.VISIBLE);
                firstEnter = false;
                mPresenter.search(editable.toString());
            }
        });
        this.etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    SoftHideKeyBoardUtil.closeKeybord((Activity) getContext());
                } else {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchWalletPage.CLICK_SEARCH);
                }
            }
        });
        this.mPresenter.search(null);
    }

    private void mappingId() {
        this.llRoot = this.binding.root;
        this.llSearch = this.binding.rlSearch;
        this.listView = this.binding.rvList;
        this.etSearch = this.binding.etSearch;
        this.ivClear = this.binding.ivClear;
        this.noDataView = this.binding.noDataView;
    }

    @Override
    public void doAfterShow() {
        super.doAfterShow();
        this.etSearch.setEnabled(true);
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_clear) {
                    etSearch.setTextKeepState("");
                } else if (id == R.id.iv_common_right) {
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
    public void onSearchComplete(List<SelectWalletBean> list, String str) {
        this.resultAdapter.setNewData(list);
        if (list.size() <= 0) {
            this.listView.setVisibility(View.GONE);
            this.noDataView.setVisibility(View.VISIBLE);
        } else {
            this.listView.setVisibility(View.VISIBLE);
            this.noDataView.setVisibility(View.GONE);
            if (this.firstEnter && StringTronUtil.isEmpty(str)) {
                Iterator<SelectWalletBean> it = list.iterator();
                int i = 0;
                while (it.hasNext() && !it.next().isSelected()) {
                    i++;
                }
                this.listView.scrollToPosition(i);
            } else {
                this.listView.scrollToPosition(0);
            }
        }
        if (this.firstEnter) {
            this.mPresenter.getAccountInfoList();
        }
        this.firstEnter = false;
    }

    @Override
    public void updateAccountInfo(List<SelectWalletBean> list) {
        this.resultAdapter.notifyDataSetChanged();
    }
}
