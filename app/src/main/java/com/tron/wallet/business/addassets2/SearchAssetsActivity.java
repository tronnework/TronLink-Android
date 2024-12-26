package com.tron.wallet.business.addassets2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.addassets2.SearchAssetsContract;
import com.tron.wallet.business.addassets2.adapter.AssetsListAdapter;
import com.tron.wallet.business.addassets2.adapter.SearchResultAdapter;
import com.tron.wallet.business.customtokens.CustomTokensActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityAddassetSearchresult2Binding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.util.Collection;
import java.util.List;
public class SearchAssetsActivity extends BaseActivity<SearchAssetsPresenter, SearchAssetsModel> implements SearchAssetsContract.View {
    private static final int MSG_SEARCH = 10001;
    private ActivityAddassetSearchresult2Binding binding;
    private AssetsListAdapter hotAssetsAdapter;
    ImageView ivClear;
    View llSearch;
    RelativeLayout mFilterMenu;
    RadioGroup mFilterRadioGroup;
    LinearLayout mHotTokenLayout;
    NoNetView mNoDataView;
    LinearLayout mNoResultLayout;
    EditText mSearchEt;
    RecyclerView mSearchResultRecyclerView;
    LinearLayout mTopAssetLayout;
    LinearLayout mTopResultLayout;
    RecyclerView mTopTokensRecyclerView;
    NoNetView netErrorView;
    View placeHolder;
    private SearchResultAdapter searchAssetsAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SearchAssetsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityAddassetSearchresult2Binding inflate = ActivityAddassetSearchresult2Binding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
        setClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.mTopAssetLayout = this.binding.llAssetResult;
        this.mFilterMenu = this.binding.rlAssetsResultMenu;
        this.mFilterRadioGroup = this.binding.radioGroupId;
        this.mSearchResultRecyclerView = this.binding.rvAssetsResult;
        this.mTopTokensRecyclerView = this.binding.rvTopTokens;
        this.mSearchEt = this.binding.searchHeader.etSearch;
        this.mNoResultLayout = this.binding.llNoresult;
        this.mTopResultLayout = this.binding.llTopResult;
        this.mHotTokenLayout = this.binding.llHotToken;
        this.mNoDataView = this.binding.noDataView;
        this.ivClear = this.binding.searchHeader.ivClear;
        this.netErrorView = this.binding.netErrorView;
        this.llSearch = this.binding.searchHeader.llSearch;
        this.placeHolder = this.binding.placeHolder;
    }

    @Override
    protected void processData() {
        ViewCompat.setTransitionName(this.llSearch, "llSearch");
        if (!SpAPI.THIS.getCurIsMainChain()) {
            this.mFilterMenu.setVisibility(View.GONE);
        }
        this.mSearchEt.setInputType(1);
        this.mSearchEt.setImeOptions(6);
        this.mSearchResultRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(new AssetsListAdapter.ItemCallback() {
            @Override
            public void onItemLongClicked(View view, TokenBean tokenBean, int[] iArr, int i) {
                AssetsListAdapter.ItemCallback.-CC.$default$onItemLongClicked(this, view, tokenBean, iArr, i);
            }

            @Override
            public void onItemClicked(TokenBean tokenBean, int i) {
                if (!WatchWalletVerifier.getInstance().getWatchWalletHasOwnerPermission()) {
                    WatchWalletVerifier.getInstance().confirmVerify(getIContext());
                } else {
                    ((SearchAssetsPresenter) mPresenter).followAssets(tokenBean, i, false);
                }
            }

            @Override
            public void onItemSelected(TokenBean tokenBean, int i) {
                ((SearchAssetsPresenter) mPresenter).showAssetsDetail(tokenBean);
            }
        }, false);
        this.searchAssetsAdapter = searchResultAdapter;
        searchResultAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$processData$0();
            }
        }, this.mSearchResultRecyclerView);
        this.mSearchResultRecyclerView.setAdapter(this.searchAssetsAdapter);
        ((SimpleItemAnimator) this.mSearchResultRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mTopTokensRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        AssetsListAdapter assetsListAdapter = new AssetsListAdapter(this, AssetsListAdapter.TagType.SHOW_ALL, new AssetsListAdapter.ItemCallback() {
            @Override
            public void onItemLongClicked(View view, TokenBean tokenBean, int[] iArr, int i) {
                AssetsListAdapter.ItemCallback.-CC.$default$onItemLongClicked(this, view, tokenBean, iArr, i);
            }

            @Override
            public void onItemClicked(TokenBean tokenBean, int i) {
                ((SearchAssetsPresenter) mPresenter).showAssetsDetail(tokenBean);
            }

            @Override
            public void onItemSelected(TokenBean tokenBean, int i) {
                if (!WatchWalletVerifier.getInstance().getWatchWalletHasOwnerPermission()) {
                    WatchWalletVerifier.getInstance().confirmVerify(getIContext());
                } else {
                    ((SearchAssetsPresenter) mPresenter).followAssets(tokenBean, i, true);
                }
            }
        });
        this.hotAssetsAdapter = assetsListAdapter;
        assetsListAdapter.setShowNoMoreItem(false);
        this.mTopTokensRecyclerView.setAdapter(this.hotAssetsAdapter);
        ((SimpleItemAnimator) this.mTopTokensRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        setListener();
        ((SearchAssetsPresenter) this.mPresenter).getHotAssets();
        SoftHideKeyBoardUtil.showSoftInput(this, this.mSearchEt);
    }

    public void lambda$processData$0() {
        if (this.mPresenter != 0) {
            ((SearchAssetsPresenter) this.mPresenter).loadMoreSearchResult(this.mSearchEt.getText().toString());
        }
    }

    @Override
    public void updateAssetsPrice() {
        this.hotAssetsAdapter.notifyPriceChanged();
    }

    private void setListener() {
        this.mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable.toString())) {
                    ivClear.setVisibility(View.GONE);
                    ((SearchAssetsPresenter) mPresenter).searchAssets(null, 1, 20);
                    hideSearchResultView();
                    return;
                }
                ivClear.setVisibility(View.VISIBLE);
                showPlaceHolder();
                ((SearchAssetsPresenter) mPresenter).searchAssets(editable.toString(), 1, 20);
            }
        });
        this.mNoDataView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$setListener$1(view);
            }
        });
        this.netErrorView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$setListener$2(view);
            }
        });
        this.mFilterRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.button_10:
                        ((SearchAssetsPresenter) mPresenter).setFilterType(1);
                        break;
                    case R.id.button_20:
                        ((SearchAssetsPresenter) mPresenter).setFilterType(2);
                        break;
                    case R.id.button_721:
                        ((SearchAssetsPresenter) mPresenter).setFilterType(5);
                        break;
                    default:
                        ((SearchAssetsPresenter) mPresenter).setFilterType(0);
                        break;
                }
                ((SearchAssetsPresenter) mPresenter).searchAssets(mSearchEt.getText().toString(), 1, 20);
            }
        });
    }

    public void lambda$setListener$1(View view) {
        CustomTokensActivity.startActivity(this, ((SearchAssetsPresenter) this.mPresenter).getSearchKeyWord());
        FirebaseAnalytics.getInstance(getIContext()).logEvent("Click_search_asset_token_record", null);
    }

    public void lambda$setListener$2(View view) {
        if (TextUtils.isEmpty(this.mSearchEt.getText())) {
            ((SearchAssetsPresenter) this.mPresenter).getHotAssets();
        } else {
            ((SearchAssetsPresenter) this.mPresenter).reloadSearch();
        }
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_clear) {
                    mSearchEt.setText("");
                } else if (id != R.id.tv_cancel) {
                } else {
                    SoftHideKeyBoardUtil.closeKeybord(SearchAssetsActivity.this);
                    finishAfterTransition();
                }
            }
        };
        this.binding.searchHeader.ivClear.setOnClickListener(noDoubleClickListener);
        this.binding.searchHeader.tvCancel.setOnClickListener(noDoubleClickListener);
    }

    @Override
    public void showHotAssets(List<TokenBean> list) {
        this.placeHolder.setVisibility(View.GONE);
        this.mSearchResultRecyclerView.setVisibility(View.GONE);
        this.mTopAssetLayout.setVisibility(View.GONE);
        this.netErrorView.setVisibility(View.GONE);
        this.mTopResultLayout.setVisibility(View.VISIBLE);
        if (list != null && list.size() > 0) {
            this.mHotTokenLayout.setVisibility(View.VISIBLE);
            this.mTopTokensRecyclerView.setVisibility(View.VISIBLE);
            this.hotAssetsAdapter.notifyDataChanged(list);
            return;
        }
        this.mHotTokenLayout.setVisibility(View.GONE);
        this.mTopTokensRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideHotAssets() {
        this.netErrorView.setVisibility(View.GONE);
        this.placeHolder.setVisibility(View.GONE);
        this.mSearchResultRecyclerView.setVisibility(View.VISIBLE);
        this.mTopAssetLayout.setVisibility(View.VISIBLE);
        this.mHotTokenLayout.setVisibility(View.GONE);
        this.mNoResultLayout.setVisibility(View.GONE);
        this.mTopTokensRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void updateAssetsFollowState(TokenBean tokenBean, int i, boolean z) {
        if (z) {
            this.hotAssetsAdapter.notifyDataChanged(tokenBean, i);
        } else {
            this.searchAssetsAdapter.notifyAssetAddState(tokenBean, i);
        }
    }

    @Override
    public void showAssetsAddedView() {
        toast(getResources().getString(R.string.assets_added_to_home));
        FirebaseAnalytics.getInstance(getIContext()).logEvent("Click_search_asset_add", null);
    }

    void hideSearchResultView() {
        this.netErrorView.setVisibility(View.GONE);
        this.placeHolder.setVisibility(View.GONE);
        this.mFilterMenu.setVisibility(View.GONE);
        this.mSearchResultRecyclerView.setVisibility(View.GONE);
        if (this.hotAssetsAdapter.getItemCount() > 0) {
            this.mHotTokenLayout.setVisibility(View.VISIBLE);
        } else {
            this.mHotTokenLayout.setVisibility(View.GONE);
        }
        this.mNoResultLayout.setVisibility(View.GONE);
        this.mTopTokensRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoDatasPage() {
        if (((SearchAssetsPresenter) this.mPresenter).getSearchKeyWord().startsWith(ExifInterface.GPS_DIRECTION_TRUE) && ((SearchAssetsPresenter) this.mPresenter).getSearchKeyWord().length() == 34) {
            if (StringTronUtil.isAddressValid(((SearchAssetsPresenter) this.mPresenter).getSearchKeyWord())) {
                this.mNoDataView.setReloadText(R.string.add_a_custom_token);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mNoDataView.rlReload.getLayoutParams();
                layoutParams.height = UIUtils.dip2px(40.0f);
                layoutParams.topMargin = UIUtils.dip2px(20.0f);
                this.mNoDataView.rlReload.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mNoDataView.tvReload.getLayoutParams();
                layoutParams2.height = UIUtils.dip2px(40.0f);
                this.mNoDataView.tvReload.setLayoutParams(layoutParams2);
                this.mNoDataView.setReloadable(true);
                this.mNoDataView.setReloadDescription(R.string.no_token_is_found_has_been_recorded);
            } else {
                this.mNoDataView.setReloadDescription(R.string.address_is_invalid);
                this.mNoDataView.setReloadable(false);
            }
        } else {
            this.mNoDataView.setReloadable(false);
            this.mNoDataView.setReloadDescription(R.string.no_token_is_found);
        }
        this.netErrorView.setVisibility(View.GONE);
        this.mNoResultLayout.setVisibility(View.VISIBLE);
        this.mSearchResultRecyclerView.setVisibility(View.GONE);
        this.mHotTokenLayout.setVisibility(View.GONE);
        this.mTopTokensRecyclerView.setVisibility(View.GONE);
        this.placeHolder.setVisibility(View.GONE);
    }

    @Override
    public void showNoNetView() {
        this.mNoResultLayout.setVisibility(View.GONE);
        this.mSearchResultRecyclerView.setVisibility(View.GONE);
        this.mHotTokenLayout.setVisibility(View.GONE);
        this.mTopTokensRecyclerView.setVisibility(View.GONE);
        this.placeHolder.setVisibility(View.GONE);
        this.netErrorView.setVisibility(View.VISIBLE);
        this.netErrorView.hideLoading();
    }

    @Override
    public void updateSearchedAssets(List<TokenBean> list) {
        if (TextUtils.isEmpty(this.mSearchEt.getText())) {
            hideSearchResultView();
            return;
        }
        hideHotAssets();
        this.searchAssetsAdapter.setNewData(list);
        this.mSearchResultRecyclerView.scrollToPosition(0);
        Bundle bundle = new Bundle();
        bundle.putString("Search_asset_key_word", this.mSearchEt.getText().toString());
        FirebaseAnalytics.getInstance(getIContext()).logEvent("Search_asset", bundle);
        if (this.searchAssetsAdapter.getData() != null && this.searchAssetsAdapter.getData().size() == 100) {
            this.searchAssetsAdapter.setLoadMoreText(R.string.search_asset_tips_100);
        } else {
            this.searchAssetsAdapter.setLoadMoreText(R.string.loading_fail_no_more);
        }
    }

    void showPlaceHolder() {
        this.netErrorView.setVisibility(View.GONE);
        this.placeHolder.setVisibility(View.VISIBLE);
        if (SpAPI.THIS.getCurIsMainChain()) {
            this.mFilterMenu.setVisibility(View.VISIBLE);
        }
        this.mTopAssetLayout.setVisibility(View.VISIBLE);
        this.mNoResultLayout.setVisibility(View.GONE);
        this.mSearchResultRecyclerView.setVisibility(View.GONE);
        this.mHotTokenLayout.setVisibility(View.GONE);
        this.mTopTokensRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void loadMoreComplete(List<TokenBean> list) {
        this.searchAssetsAdapter.loadMoreComplete();
        this.searchAssetsAdapter.addData((Collection) list);
    }

    @Override
    public void loadMoreFail() {
        this.searchAssetsAdapter.loadMoreFail();
    }

    @Override
    public void loadMoreDone() {
        if (this.searchAssetsAdapter.getData() != null && this.searchAssetsAdapter.getData().size() <= 5) {
            this.searchAssetsAdapter.loadMoreEnd(true);
        } else {
            this.searchAssetsAdapter.loadMoreEnd();
        }
    }
}
