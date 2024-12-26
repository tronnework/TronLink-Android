package com.tron.wallet.business.addassets2.selecttoken;

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
import android.widget.RadioGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.wallet.business.addassets2.adapter.AssetsListAdapter;
import com.tron.wallet.business.addassets2.adapter.SearchResultAdapter;
import com.tron.wallet.business.addassets2.selecttoken.SelectTokenBottomPopup;
import com.tron.wallet.business.addassets2.selecttoken.SelectTokenContract;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.PopupSelectTokenBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
public class SelectTokenBottomPopup extends BottomPopupView implements SelectTokenContract.View {
    private PopupSelectTokenBinding binding;
    View bt721;
    private String controllerAddress;
    private OnDismissListener dismissListener;
    EditText etSearch;
    private boolean isMultiSign;
    ImageView ivClear;
    View ivPlaceHolder;
    View llRoot;
    View llSearch;
    RadioGroup mFilterRadioGroup;
    private SelectTokenContract.Presenter mPresenter;
    private MultiSignPermissionData multiSignPermissionData;
    NoNetView noDataView;
    NoNetView noNetView;
    private OnClickListener onClickListener;
    private SearchResultAdapter resultAdapter;
    RecyclerView rvTokenList;
    private TokenBean selectedTokenBean;
    ErrorView tvMultiSignTip;

    public interface OnClickListener {
        void onClick(TokenBean tokenBean);
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_select_token;
    }

    public SelectTokenBottomPopup setOnDismissListener(OnDismissListener onDismissListener) {
        this.dismissListener = onDismissListener;
        return this;
    }

    public SelectTokenBottomPopup setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public SelectTokenBottomPopup(Context context, TokenBean tokenBean, boolean z, MultiSignPermissionData multiSignPermissionData, String str) {
        super(context);
        this.selectedTokenBean = tokenBean;
        this.controllerAddress = str;
        this.mPresenter = new SelectTokenPresenter();
        this.isMultiSign = z;
        this.multiSignPermissionData = multiSignPermissionData;
    }

    public static SelectTokenBottomPopup showPopup(Context context, TokenBean tokenBean, boolean z, MultiSignPermissionData multiSignPermissionData, String str) {
        return (SelectTokenBottomPopup) new XPopup.Builder(context).autoOpenSoftInput(false).moveUpToKeyboard(false).enableDrag(false).asCustom(new SelectTokenBottomPopup(context, tokenBean, z, multiSignPermissionData, str)).show();
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
        MultiSignPermissionData multiSignPermissionData;
        super.onCreate();
        mappingId();
        setClick();
        this.llSearch.setVisibility(SpAPI.THIS.getCurIsMainChain() ? View.VISIBLE : View.GONE);
        this.etSearch.setInputType(1);
        this.etSearch.setImeOptions(6);
        this.rvTokenList.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(new fun1(), true);
        this.resultAdapter = searchResultAdapter;
        searchResultAdapter.setSwitchIcon(R.mipmap.iv_selected, 0);
        if (this.isMultiSign && (multiSignPermissionData = this.multiSignPermissionData) != null && (!multiSignPermissionData.isTransferTRXPermission() || !this.multiSignPermissionData.isTransferTRC10Permission() || !this.multiSignPermissionData.isTransferTRC20Permission())) {
            String[] permissionTip = MultiSignPermissionData.getPermissionTip(this.multiSignPermissionData);
            this.tvMultiSignTip.setText((CharSequence) String.format(getContext().getResources().getString(R.string.transfer_multi_permission_tip), permissionTip[1], permissionTip[0]), true);
            this.tvMultiSignTip.updateWarning(ErrorView.Level.WARNING);
            this.tvMultiSignTip.setVisibility(View.VISIBLE);
            HashSet hashSet = new HashSet();
            if (!this.multiSignPermissionData.isTransferTRXPermission()) {
                hashSet.add(0);
            }
            if (!this.multiSignPermissionData.isTransferTRC10Permission()) {
                hashSet.add(1);
            }
            if (!this.multiSignPermissionData.isTransferTRC20Permission()) {
                hashSet.add(2);
                hashSet.add(5);
            }
            this.resultAdapter.setDisableTokenType(hashSet);
        }
        this.resultAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$onCreate$0();
            }
        }, this.rvTokenList);
        this.rvTokenList.setAdapter(this.resultAdapter);
        ((SimpleItemAnimator) this.rvTokenList.getItemAnimator()).setSupportsChangeAnimations(false);
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.select_token_hint));
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
                    AnalyticsHelper.logEvent(isMultiSign ? AnalyticsHelper.TransferSelectTokenPage.CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_TOKEN : AnalyticsHelper.TransferSelectTokenPage.CLICK_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_TOKEN);
                }
                ivClear.setVisibility(TextUtils.isEmpty(obj) ? View.GONE : View.VISIBLE);
                if (TextUtils.isEmpty(editable.toString())) {
                    mPresenter.searchAssets(null, 1, 20);
                    return;
                }
                showPlaceHolder(true);
                mPresenter.searchAssets(editable.toString(), 1, 20);
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
        this.bt721.setVisibility(SpAPI.THIS.getCurIsMainChain() ? View.VISIBLE : View.GONE);
        this.mFilterRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.button_10:
                        mPresenter.setFilterType(1);
                        break;
                    case R.id.button_20:
                        mPresenter.setFilterType(2);
                        break;
                    case R.id.button_721:
                        mPresenter.setFilterType(5);
                        break;
                    default:
                        mPresenter.setFilterType(0);
                        break;
                }
                SelectTokenBottomPopup selectTokenBottomPopup = SelectTokenBottomPopup.this;
                selectTokenBottomPopup.showPlaceHolder(!StringTronUtil.isEmpty(selectTokenBottomPopup.etSearch.getText().toString()));
                mPresenter.searchAssets(etSearch.getText().toString(), 1, 20);
            }
        });
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onCreate$1(view);
            }
        });
        showPlaceHolder(true);
        this.mPresenter.start(this, this.selectedTokenBean, this.controllerAddress, this.isMultiSign);
    }

    public class fun1 implements AssetsListAdapter.ItemCallback {
        @Override
        public void onItemLongClicked(View view, TokenBean tokenBean, int[] iArr, int i) {
            AssetsListAdapter.ItemCallback.-CC.$default$onItemLongClicked(this, view, tokenBean, iArr, i);
        }

        fun1() {
        }

        @Override
        public void onItemClicked(TokenBean tokenBean, int i) {
            if (onClickListener != null && !tokenBean.isSelected) {
                onClickListener.onClick(tokenBean);
            }
            postDelayed(new Runnable() {
                @Override
                public final void run() {
                    SelectTokenBottomPopup.1.this.lambda$onItemClicked$0();
                }
            }, 300L);
        }

        public void lambda$onItemClicked$0() {
            dismiss();
        }

        @Override
        public void onItemSelected(TokenBean tokenBean, int i) {
            if (onClickListener != null && !tokenBean.isSelected) {
                onClickListener.onClick(tokenBean);
            }
            postDelayed(new Runnable() {
                @Override
                public final void run() {
                    SelectTokenBottomPopup.1.this.lambda$onItemSelected$1();
                }
            }, 300L);
        }

        public void lambda$onItemSelected$1() {
            dismiss();
        }
    }

    public void lambda$onCreate$0() {
        SelectTokenContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.loadMoreSearchResult(this.etSearch.getText().toString());
        }
    }

    public void lambda$onCreate$1(View view) {
        this.mPresenter.searchAssets(this.etSearch.getText().toString(), 1, 20);
    }

    private void mappingId() {
        PopupSelectTokenBinding bind = PopupSelectTokenBinding.bind(getPopupImplView());
        this.binding = bind;
        this.llRoot = bind.rlRoot;
        this.mFilterRadioGroup = this.binding.radioGroupId;
        this.llSearch = this.binding.rlSearch;
        this.rvTokenList = this.binding.rvTokenList;
        this.etSearch = this.binding.etSearch;
        this.ivClear = this.binding.ivClear;
        this.noNetView = this.binding.noNetView;
        this.noDataView = this.binding.noDataView;
        this.ivPlaceHolder = this.binding.placeHolder;
        this.tvMultiSignTip = this.binding.tvMultiSignTip;
        this.bt721 = this.binding.button721;
    }

    @Override
    public void doAfterShow() {
        super.doAfterShow();
        this.etSearch.setEnabled(true);
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
        this.binding.ivCommonRight.setOnClickListener(noDoubleClickListener);
        this.binding.ivClear.setOnClickListener(noDoubleClickListener);
        this.binding.rlRoot.setOnClickListener(noDoubleClickListener);
    }

    public void showPlaceHolder(boolean z) {
        this.ivPlaceHolder.setVisibility(z ? View.VISIBLE : View.GONE);
        this.rvTokenList.setVisibility(z ? View.GONE : View.VISIBLE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    private void showListOrEmpty(int i) {
        showPlaceHolder(false);
        if (i <= 0) {
            this.rvTokenList.setVisibility(View.GONE);
            this.noDataView.setVisibility(View.VISIBLE);
            return;
        }
        this.rvTokenList.setVisibility(View.VISIBLE);
        this.noDataView.setVisibility(View.GONE);
    }

    @Override
    public void updateSearchedAssets(List<TokenBean> list) {
        showPlaceHolder(false);
        this.resultAdapter.setNewData(list);
        this.rvTokenList.scrollToPosition(0);
        if (this.resultAdapter.getData() != null && this.resultAdapter.getData().size() == 100) {
            this.resultAdapter.setLoadMoreText(R.string.search_asset_tips_100);
        } else {
            this.resultAdapter.setLoadMoreText(R.string.loading_fail_no_more);
        }
        showListOrEmpty(list.size());
    }

    @Override
    public void showNoNetView() {
        this.noNetView.setVisibility(View.VISIBLE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.rvTokenList.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    @Override
    public void loadMoreComplete(List<TokenBean> list) {
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
