package com.tron.wallet.business.walletmanager.selectwallet.search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.assetshome.AssetsPresenter;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.business.walletmanager.selectwallet.adapter.SelectWalletAdapter;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.controller.RecentlyWalletController;
import com.tron.wallet.business.walletmanager.selectwallet.search.SearchWalletContract;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivitySearchWalletBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.List;
import org.tron.walletserver.Wallet;
public class SearchWalletActivity extends BaseActivity<SearchWalletPresenter, SearchWalletModel> implements SearchWalletContract.View {
    private ActivitySearchWalletBinding binding;
    EditText etSearch;
    ImageView ivClear;
    View llRecent;
    View llResult;
    NoNetView noResultView;
    private SelectWalletAdapter recentAdapter;
    RecyclerView rvRecent;
    RecyclerView rvSearchResult;
    private SelectWalletAdapter searchAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SearchWalletActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivitySearchWalletBinding inflate = ActivitySearchWalletBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.llRecent = this.binding.llRecent;
        this.llResult = this.binding.llResult;
        this.ivClear = this.binding.ivClear;
        this.rvSearchResult = this.binding.rvSearchResult;
        this.rvRecent = this.binding.rvRecent;
        this.noResultView = this.binding.noResultView;
        this.etSearch = this.binding.etSearch;
    }

    @Override
    protected void processData() {
        initView();
        ((SearchWalletPresenter) this.mPresenter).getRecentWallets();
    }

    private void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences(AssetsPresenter.KEY_SP, 0);
        SelectWalletAdapter selectWalletAdapter = new SelectWalletAdapter(this);
        this.searchAdapter = selectWalletAdapter;
        selectWalletAdapter.setHideAssets(sharedPreferences.getBoolean(AssetsPresenter.KEY_ASSET_STATUS, false));
        this.rvSearchResult.setLayoutManager(new WrapContentLinearLayoutManager(this, 1, false));
        this.rvSearchResult.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (recyclerView.getChildAdapterPosition(view) == recyclerView.getAdapter().getItemCount() - 1) {
                    rect.bottom = UIUtils.dip2px(40.0f);
                }
            }
        });
        this.rvSearchResult.setAdapter(this.searchAdapter);
        ((ImageView) this.llResult.findViewById(R.id.iv_tag)).setImageResource(R.mipmap.ic_search_item);
        ((TextView) this.llResult.findViewById(R.id.tv_title)).setText(getResources().getString(R.string.the_search_result));
        SelectWalletAdapter.OnItemViewClickListener onItemViewClickListener = new SelectWalletAdapter.OnItemViewClickListener() {
            @Override
            public final void onViewClick(BaseQuickAdapter baseQuickAdapter, View view, int i, SelectWalletBean selectWalletBean) {
                lambda$initView$0(baseQuickAdapter, view, i, selectWalletBean);
            }
        };
        this.searchAdapter.setOnItemViewClickListener(onItemViewClickListener);
        SelectWalletAdapter selectWalletAdapter2 = new SelectWalletAdapter(this);
        this.recentAdapter = selectWalletAdapter2;
        selectWalletAdapter2.setHideAssets(sharedPreferences.getBoolean(AssetsPresenter.KEY_ASSET_STATUS, false));
        this.rvRecent.setLayoutManager(new WrapContentLinearLayoutManager(this, 1, false));
        this.rvRecent.setAdapter(this.recentAdapter);
        this.recentAdapter.setOnItemViewClickListener(onItemViewClickListener);
        this.etSearch.setImeOptions(3);
        this.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 3) {
                    onSearchTextChanged(textView.getText().toString());
                    textView.clearFocus();
                    SoftHideKeyBoardUtil.closeKeybord(SearchWalletActivity.this);
                    return false;
                }
                return true;
            }
        });
        this.etSearch.addTextChangedListener(new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                onSearchTextChanged(editable.toString().trim());
            }
        });
        this.ivClear.setVisibility(View.GONE);
        this.ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initView$1(view);
            }
        });
        this.binding.tvCancel.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                SoftHideKeyBoardUtil.closeKeybord(SearchWalletActivity.this);
                exit();
            }
        });
    }

    public void lambda$initView$0(BaseQuickAdapter baseQuickAdapter, View view, int i, SelectWalletBean selectWalletBean) {
        try {
            switch (view.getId()) {
                case R.id.iv_copy:
                case R.id.tv_address:
                    if (!BackupReminder.isWalletBackup(selectWalletBean.getWallet())) {
                        BackupReminder.showBackupPopup(this, selectWalletBean.getWallet(), "", new BackupReminder.onActionListener() {
                            @Override
                            public void onClickCancel(Wallet wallet) {
                            }

                            @Override
                            public void onClickConfirm(Wallet wallet) {
                                setSelectedWallet(wallet);
                            }
                        });
                        break;
                    } else {
                        UIUtils.copy(selectWalletBean.getWallet().getAddress());
                        toast(getString(R.string.already_copy));
                        break;
                    }
                case R.id.iv_jump:
                    setSelectedWallet(selectWalletBean.getWallet());
                    WalletDetailActivity.startActivity(this, selectWalletBean.getWallet().getWalletName(), false);
                    break;
                case R.id.top_card:
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_SELECT);
                    setSelectedWallet(selectWalletBean.getWallet());
                    goToMainActivity();
                    break;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$initView$1(View view) {
        this.etSearch.setText("");
    }

    public void setSelectedWallet(Wallet wallet) {
        RecentlyWalletController.setRecentlyWallet(wallet);
        WalletUtils.setSelectedWallet(wallet.getWalletName());
        SpAPI.THIS.setColdWalletSelected(wallet.getWalletName());
        if (this.mPresenter == 0 || ((SearchWalletPresenter) this.mPresenter).mRxManager == null) {
            return;
        }
        ((SearchWalletPresenter) this.mPresenter).mRxManager.post(Event.RESET_TAB, "");
    }

    public void onSearchTextChanged(String str) {
        if (TextUtils.isEmpty(str)) {
            this.ivClear.setVisibility(View.GONE);
            this.noResultView.setVisibility(View.GONE);
            this.llResult.setVisibility(View.GONE);
            this.llRecent.setVisibility(View.VISIBLE);
            return;
        }
        this.ivClear.setVisibility(View.VISIBLE);
        this.llRecent.setVisibility(View.GONE);
        if (this.mPresenter != 0) {
            ((SearchWalletPresenter) this.mPresenter).search(str);
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this.mContext, MainTabActivity.class);
        intent.addFlags(67108864);
        this.mContext.startActivity(intent);
    }

    @Override
    public void updateRecentWallets(List<SelectWalletBean> list) {
        this.recentAdapter.updateData(list);
    }

    @Override
    public void updateAccountInfo(List<SelectWalletBean> list) {
        this.searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSearchComplete(List<SelectWalletBean> list, String str) {
        if (list.isEmpty()) {
            this.noResultView.setVisibility(View.VISIBLE);
            this.llResult.setVisibility(View.GONE);
            return;
        }
        this.noResultView.setVisibility(View.GONE);
        this.llResult.setVisibility(View.VISIBLE);
        this.searchAdapter.updateData(list);
    }
}
