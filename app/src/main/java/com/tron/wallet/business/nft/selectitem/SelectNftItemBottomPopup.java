package com.tron.wallet.business.nft.selectitem;

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
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.nft.selectitem.SelectNftItemBottomPopup;
import com.tron.wallet.business.nft.selectitem.SelectNftItemContract;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.PopupSelectNftItemBinding;
import com.tronlinkpro.wallet.R;
import java.util.Collection;
import java.util.List;
public class SelectNftItemBottomPopup extends BottomPopupView implements SelectNftItemContract.View {
    PopupSelectNftItemBinding binding;
    private String controllerAddress;
    private OnDismissListener dismissListener;
    EditText etSearch;
    private boolean isMultiSign;
    ImageView ivClear;
    View ivPlaceHolder;
    View llRoot;
    private SelectNftItemContract.Presenter mPresenter;
    NoNetView noDataView;
    NoNetView noNetView;
    private OnClickListener onClickListener;
    private SelectNftItemAdapter resultAdapter;
    RecyclerView rvTokenList;
    private NftItemInfo selectedNftItemInfo;
    private TokenBean selectedTokenBean;

    public interface OnClickListener {
        void onClick(NftItemInfo nftItemInfo);
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_select_nft_item;
    }

    public SelectNftItemBottomPopup setOnDismissListener(OnDismissListener onDismissListener) {
        this.dismissListener = onDismissListener;
        return this;
    }

    public SelectNftItemBottomPopup setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public SelectNftItemBottomPopup(Context context, TokenBean tokenBean, NftItemInfo nftItemInfo, String str, boolean z) {
        super(context);
        this.selectedTokenBean = tokenBean;
        this.selectedNftItemInfo = nftItemInfo;
        this.controllerAddress = str;
        this.isMultiSign = z;
        this.mPresenter = new SelectNftItemPresenter();
    }

    public static SelectNftItemBottomPopup showPopup(Context context, TokenBean tokenBean, NftItemInfo nftItemInfo, String str, boolean z) {
        return (SelectNftItemBottomPopup) new XPopup.Builder(context).autoOpenSoftInput(false).moveUpToKeyboard(false).enableDrag(false).asCustom(new SelectNftItemBottomPopup(context, tokenBean, nftItemInfo, str, z)).show();
    }

    @Override
    public void onDismiss() {
        KeyboardUtils.hideSoftInput(getPopupContentView());
        OnDismissListener onDismissListener = this.dismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        super.onDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.destroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mappingId(getPopupImplView());
        setClick();
        this.etSearch.setInputType(1);
        this.etSearch.setImeOptions(6);
        this.rvTokenList.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        SelectNftItemAdapter selectNftItemAdapter = new SelectNftItemAdapter();
        this.resultAdapter = selectNftItemAdapter;
        selectNftItemAdapter.setSelectedItem(this.selectedNftItemInfo);
        this.resultAdapter.setOnItemChildClickListener(new fun1());
        this.resultAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$onCreate$0();
            }
        }, this.rvTokenList);
        this.rvTokenList.setAdapter(this.resultAdapter);
        ((SimpleItemAnimator) this.rvTokenList.getItemAnimator()).setSupportsChangeAnimations(false);
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.choose_collection_hint));
        spannableString.setSpan(new StyleSpan(0), 0, spannableString.length(), 33);
        spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString.length(), 33);
        this.etSearch.setHint(spannableString);
        this.etSearch.setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
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
                    AnalyticsHelper.logEvent(isMultiSign ? AnalyticsHelper.TransferSelectTokenPage.CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_NFT : AnalyticsHelper.TransferSelectTokenPage.CLICK_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_NFT);
                }
                ivClear.setVisibility(TextUtils.isEmpty(obj) ? View.GONE : View.VISIBLE);
                showPlaceHolder(true);
                if (TextUtils.isEmpty(editable.toString())) {
                    mPresenter.searchAssets(null, 1, 20);
                } else {
                    mPresenter.searchAssets(editable.toString(), 1, 20);
                }
            }
        });
        this.etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    return;
                }
                SoftHideKeyBoardUtil.closeKeybord((Activity) getContext());
            }
        });
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onCreate$1(view);
            }
        });
        showPlaceHolder(true);
        this.mPresenter.start(this, this.selectedTokenBean, this.selectedNftItemInfo, this.controllerAddress);
    }

    public class fun1 implements BaseQuickAdapter.OnItemChildClickListener {
        fun1() {
        }

        @Override
        public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            if (view.getId() == R.id.ll_nft_item) {
                NftItemInfo nftItemInfo = (NftItemInfo) baseQuickAdapter.getItem(i);
                if (onClickListener != null && nftItemInfo.getAssetId() != selectedNftItemInfo.getAssetId()) {
                    onClickListener.onClick(nftItemInfo);
                }
                postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        SelectNftItemBottomPopup.1.this.lambda$onItemChildClick$0();
                    }
                }, 300L);
            }
        }

        public void lambda$onItemChildClick$0() {
            dismiss();
        }
    }

    public void lambda$onCreate$0() {
        SelectNftItemContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.loadMoreSearchResult(this.etSearch.getText().toString());
        }
    }

    public void lambda$onCreate$1(View view) {
        this.mPresenter.searchAssets(this.etSearch.getText().toString(), 0, 20);
    }

    private void mappingId(View view) {
        PopupSelectNftItemBinding bind = PopupSelectNftItemBinding.bind(view);
        this.binding = bind;
        this.llRoot = bind.getRoot().findViewById(R.id.root);
        this.rvTokenList = this.binding.rvTokenList;
        this.etSearch = this.binding.etSearch;
        this.ivClear = this.binding.ivClear;
        this.noDataView = this.binding.noDataView;
        this.noNetView = this.binding.noNetView;
        this.ivPlaceHolder = this.binding.placeHolder;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_clear) {
                    etSearch.setTextKeepState("");
                } else if (id == R.id.iv_common_right || id == R.id.root) {
                    dismiss();
                }
            }
        };
        this.binding.getRoot().findViewById(R.id.iv_common_right).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.iv_clear).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.root).setOnClickListener(noDoubleClickListener);
    }

    public void showPlaceHolder(boolean z) {
        this.ivPlaceHolder.setVisibility(z ? View.VISIBLE : View.GONE);
        this.rvTokenList.setVisibility(z ? View.GONE : View.VISIBLE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    private void showListOrEmpty(int i) {
        if (i <= 0) {
            this.rvTokenList.setVisibility(View.GONE);
            this.noDataView.setVisibility(View.VISIBLE);
            return;
        }
        this.rvTokenList.setVisibility(View.VISIBLE);
        this.noDataView.setVisibility(View.GONE);
    }

    @Override
    public void updateSearchedAssets(List<NftItemInfo> list) {
        showPlaceHolder(false);
        this.resultAdapter.setNewData(list);
        this.rvTokenList.scrollToPosition(0);
        if (this.resultAdapter.getData() != null && this.resultAdapter.getData().size() == 20 && !StringTronUtil.isEmpty(this.etSearch.getText().toString())) {
            this.resultAdapter.setLoadMoreText(R.string.search_nft_tips_20);
        } else {
            this.resultAdapter.setLoadMoreText(R.string.loading_fail_no_more);
        }
        showListOrEmpty(list.size());
    }

    @Override
    public void showNoNetView() {
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.rvTokenList.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadMoreComplete(List<NftItemInfo> list) {
        this.resultAdapter.loadMoreComplete();
        this.resultAdapter.addData((Collection) list);
    }

    @Override
    public void loadMoreFail() {
        this.resultAdapter.loadMoreFail();
    }

    @Override
    public void loadMoreDone() {
        if (this.resultAdapter.getData() != null && this.resultAdapter.getData().size() <= 5) {
            this.resultAdapter.loadMoreEnd(true);
        } else {
            this.resultAdapter.loadMoreEnd();
        }
    }
}
