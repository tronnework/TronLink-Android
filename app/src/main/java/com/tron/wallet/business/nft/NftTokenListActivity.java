package com.tron.wallet.business.nft;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.bean.nft.NftInfoOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.nft.NftTokenListActivity;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.business.nft.contract.NftModel;
import com.tron.wallet.business.nft.contract.NftPresenter;
import com.tron.wallet.business.receive.ReceiveActivity;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.adapter.nft.NftListAdapter;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.CheckAccountActivatedCallback;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.ComponentUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.TokenItemUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityNftTokenListBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class NftTokenListActivity extends BaseActivity<NftPresenter, NftModel> implements Contract.View {
    private ActivityNftTokenListBinding binding;
    View bottomView;
    private List<String> customTokenNoFunctions;
    private boolean customTokenSyncing;
    private boolean fromNetwork;
    private boolean hasShowWarningTag;
    ImageView ivLoading;
    ImageView ivTransfer;
    private String mHomePage;
    private String mTokenAddress;
    private String mWalletAddress;
    private NftListAdapter nftListAdapter;
    NoNetView noNetView;
    PtrFrameLayout ptrFrameLayout;
    LinearLayout receiveLayout;
    ItemWarningTagView rlScam;
    RecyclerView rootRv;
    private String shortName;
    private TokenBean tokenBean;
    LinearLayout transferLayout;
    TextView tvTransfer;

    public static void start(Context context, String str, TokenBean tokenBean) {
        ComponentUtils.startActivity(context, NftTokenListActivity.class, new Pair(Contract.View.KEY_WALLET_ADDRESS, str), new Pair(AssetsConfig.TOKEN_BEAN, tokenBean));
    }

    @Override
    protected void setLayout() {
        ActivityNftTokenListBinding inflate = ActivityNftTokenListBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        getHeaderHolder().itemView.setBackgroundColor(getIContext().getResources().getColor(R.color.white));
        setCommonRight2(getString(R.string.nft_records));
        getHeaderHolder().tvCommonRight2.setBackground(null);
        getHeaderHolder().tvCommonRight2.setTextColor(getResources().getColor(R.color.blue_3c));
        getHeaderHolder().tvCommonRight2.setTextSize(14.0f);
        mappingId();
    }

    public void mappingId() {
        this.rootRv = this.binding.rootRv;
        this.noNetView = this.binding.noNetView;
        this.ptrFrameLayout = this.binding.rcFrame;
        this.ivLoading = this.binding.ivLoading;
        this.rlScam = this.binding.rlScam;
        this.bottomView = this.binding.llBottomBar;
        this.transferLayout = this.binding.llTransfer;
        this.receiveLayout = this.binding.llReceive;
        this.ivTransfer = this.binding.ivTransfer;
        this.tvTransfer = this.binding.tvTransfer;
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.ll_receive) {
                    receivables();
                } else if (id != R.id.ll_transfer) {
                } else {
                    if (!BackupReminder.isWalletBackup(WalletUtils.getSelectedWallet())) {
                        BackupReminder.showBackupPopup(mContext, WalletUtils.getSelectedWallet(), "", mContext.getResources().getString(R.string.backup_tip_for_send));
                    } else if (!TronConfig.hasNet) {
                        NftTokenListActivity nftTokenListActivity = NftTokenListActivity.this;
                        nftTokenListActivity.toast(nftTokenListActivity.getString(R.string.time_out));
                    } else if (nftListAdapter.getData() == null || nftListAdapter.getData().size() == 0) {
                    } else {
                        if (!AssetsConfig.isCustomTokenAndNotSupportTransfer(tokenBean)) {
                            AccountUtils.getInstance().checkAccountIsActivatedFromLocalWithTokenBean(mContext, new CheckAccountActivatedCallback() {
                                @Override
                                public void isActivated() {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("token_address", nftListAdapter.getData().get(0).getTokenAddress());
                                    FirebaseAnalytics.getInstance(mContext).logEvent("Click_nft_transfer", bundle);
                                    SelectSendAddressActivity.startForNft(NftTokenListActivity.this, tokenBean, null, nftListAdapter.getData().get(0));
                                }
                            }, null, tokenBean);
                            return;
                        }
                        NftTokenListActivity nftTokenListActivity2 = NftTokenListActivity.this;
                        nftTokenListActivity2.toast(nftTokenListActivity2.getResources().getString(R.string.token_not_support_transfer));
                    }
                }
            }
        };
        this.binding.llReceive.setOnClickListener(noDoubleClickListener2);
        this.binding.llTransfer.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    protected void processData() {
        try {
            this.mWalletAddress = getIntent().getStringExtra(Contract.View.KEY_WALLET_ADDRESS);
            TokenBean tokenBean = (TokenBean) getIntent().getParcelableExtra(AssetsConfig.TOKEN_BEAN);
            this.tokenBean = tokenBean;
            this.mTokenAddress = tokenBean.getContractAddress();
            this.shortName = this.tokenBean.getShortName();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        setClick();
        setHeaderBar(this.shortName);
        getHeaderHolder().tvCommonTitle.setMaxWidth(UIUtils.dip2px(190.0f));
        getHeaderHolder().tvCommonTitle.setEllipsize(TextUtils.TruncateAt.END);
        this.mHomePage = getUrl();
        this.rootRv.setLayoutManager(new WrapContentLinearLayoutManager(getIContext(), 1, false));
        NftListAdapter nftListAdapter = new NftListAdapter(getIContext(), this.mWalletAddress, this.mHomePage, this.shortName, new NftListAdapter.CopyClickListener() {
            @Override
            public void copy(String str) {
                copyAndToast(str);
                AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_DETAIL_PAGE_COPY_ADDRESS);
            }

            @Override
            public void arrowClick() {
                AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_DETAIL_PAGE_PRODUCTION_PAGE);
                CommonWebActivityV3.start(getIContext(), mHomePage, getResources().getString(R.string.nft_project_detail), -2, false);
            }
        });
        this.nftListAdapter = nftListAdapter;
        nftListAdapter.setTokenBean(this.tokenBean);
        getHeaderHolder().tvCommonRight2.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                NftHistoryActivity.start(getIContext(), mTokenAddress, tokenBean);
                AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_DETAIL_PAGE_TRANSACTION_HISTORY);
            }
        });
        this.rootRv.setAdapter(this.nftListAdapter);
        this.nftListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$processData$0();
            }
        }, this.rootRv);
        ((NftPresenter) this.mPresenter).getNftTokenInfo(this.mWalletAddress, this.mTokenAddress);
        TokenBean tokenBean2 = this.tokenBean;
        if (tokenBean2 != null && tokenBean2.getTokenStatus() != 0) {
            ((NftPresenter) this.mPresenter).getCustomTokenInfo(this.mWalletAddress, this.tokenBean);
        }
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$1(view);
            }
        });
        this.ptrFrameLayout.setPtrHandler(new fun4());
        if (!this.tokenBean.getTransferStatus()) {
            this.bottomView.setVisibility(View.GONE);
        }
        if (WalletUtils.getSelectedPublicWallet().isWatchNotPaired()) {
            this.bottomView.setVisibility(View.GONE);
        }
        setWarningTagView(this.tokenBean);
        AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.ENTER_NFT_DETAIL_PAGE);
    }

    public void lambda$processData$0() {
        if (!this.fromNetwork) {
            this.nftListAdapter.loadMoreEnd(true);
        } else if (this.mPresenter != 0) {
            ((NftPresenter) this.mPresenter).getNftTokenInfo(this.mWalletAddress, this.mTokenAddress);
        }
    }

    public void lambda$processData$1(View view) {
        ((NftPresenter) this.mPresenter).getNftTokenInfo(this.mWalletAddress, this.mTokenAddress);
    }

    public class fun4 implements PtrHandler {
        fun4() {
        }

        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
            return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, null) && noNetView.getVisibility() != 0;
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            if (mPresenter == 0) {
                return;
            }
            ((NftPresenter) mPresenter).refresh(mWalletAddress, mTokenAddress);
            if (tokenBean != null && tokenBean.getTokenStatus() != 0 && customTokenNoFunctions == null) {
                ((NftPresenter) mPresenter).getCustomTokenInfo(mWalletAddress, tokenBean);
            }
            ptrFrameLayout.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    NftTokenListActivity.4.this.lambda$onRefreshBegin$0();
                }
            }, 2000L);
        }

        public void lambda$onRefreshBegin$0() {
            ptrFrameLayout.refreshComplete();
        }
    }

    private String getUrl() {
        String format = String.format("%s%s", IRequest.getTronscan20TokenIntroduceUrl(), this.mTokenAddress);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return format + "?lang=zh";
        }
        return format + "?lang=en";
    }

    @Override
    public void onGetTokenInfo(NftInfoOutput nftInfoOutput, boolean z, boolean z2) {
        this.noNetView.setVisibility(View.GONE);
        this.ivLoading.setVisibility(View.GONE);
        this.rootRv.setVisibility(View.VISIBLE);
        this.fromNetwork = z2;
        if (z && (nftInfoOutput.getData().getCollectionInfoList() == null || nftInfoOutput.getData().getCollectionInfoList().size() == 0)) {
            this.transferLayout.setEnabled(false);
        } else {
            this.transferLayout.setEnabled(true);
        }
        this.nftListAdapter.updateData(nftInfoOutput, z);
        setWarningTagView(this.tokenBean);
    }

    @Override
    public void loadMoreFailed() {
        this.nftListAdapter.loadMoreFail();
    }

    @Override
    public void loadMoreEnd() {
        this.nftListAdapter.loadMoreEnd(this.nftListAdapter.getData().size() <= 5);
    }

    @Override
    public void loadMoreComplete() {
        this.nftListAdapter.loadMoreComplete();
    }

    @Override
    public List<NftItemInfo> getCurrentData() {
        NftListAdapter nftListAdapter = this.nftListAdapter;
        if (nftListAdapter == null) {
            return new ArrayList();
        }
        return nftListAdapter.getData();
    }

    @Override
    public void onBroadcastSuccess(Object obj) {
        if (this.mPresenter == 0 || TextUtils.isEmpty(this.mWalletAddress) || TextUtils.isEmpty(this.mTokenAddress)) {
            return;
        }
        this.ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$onBroadcastSuccess$2();
            }
        }, 3000L);
    }

    public void lambda$onBroadcastSuccess$2() {
        this.ptrFrameLayout.autoRefresh(true);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        exit();
    }

    @Override
    public void showNoDatasPage() {
        this.noNetView.setVisibility(View.VISIBLE);
        this.ivLoading.setVisibility(View.GONE);
        this.rootRv.setVisibility(View.GONE);
    }

    public void copyAndToast(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UIUtils.copy(str);
        toast(getString(R.string.already_copy));
    }

    @Override
    public void updateTokenInfo(TokenBean tokenBean) {
        this.customTokenSyncing = false;
        if (tokenBean == null) {
            ToastError(getResources().getString(R.string.custom_token_sync_fail));
            return;
        }
        this.rlScam.setVisibility(View.GONE);
        this.tokenBean = tokenBean;
        this.nftListAdapter.setTokenBean(tokenBean);
        this.nftListAdapter.notifyDataSetChanged();
        setHeaderBar(tokenBean.getShortName());
        ((NftPresenter) this.mPresenter).getNftTokenInfo(this.mWalletAddress, this.mTokenAddress);
    }

    @Override
    public void updateTokenNoFunctions(List<String> list) {
        this.customTokenNoFunctions = list;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.nftListAdapter.setCustomTokenNoFunctions(list);
    }

    private void syncCustomTokenInfo() {
        if (this.customTokenSyncing) {
            return;
        }
        ConfirmCustomPopupView.getBuilder(getIContext()).setTitle(getResources().getString(R.string.custom_token_sync_title)).setInfo(getResources().getString(R.string.custom_token_sync_info)).setConfirmText(getResources().getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$syncCustomTokenInfo$3(view);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_UPDATE_CANCEL);
            }
        }).build().show();
    }

    public void lambda$syncCustomTokenInfo$3(View view) {
        this.customTokenSyncing = true;
        AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_UPDATE_CONFIRM);
        ((NftPresenter) this.mPresenter).syncCustomToken(this.mWalletAddress, this.tokenBean);
    }

    private void setWarningTagView(TokenBean tokenBean) {
        if (this.hasShowWarningTag) {
            return;
        }
        this.hasShowWarningTag = true;
        TokenItemUtil.initScamToDetailView(getIContext(), this.rlScam, tokenBean);
    }

    public void receivables() {
        if (!BackupReminder.isWalletBackup(WalletUtils.getSelectedWallet())) {
            BackupReminder.showBackupPopup(this.mContext, WalletUtils.getSelectedWallet(), "");
        } else {
            goReceive();
        }
    }

    private void goReceive() {
        this.mContext.startActivity(new Intent(this.mContext, ReceiveActivity.class));
    }
}
