package com.tron.wallet.business.assetshome;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.wallet.TronApplication;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addwallet.AddWalletActivityV2;
import com.tron.wallet.business.assetshome.AssetsContract;
import com.tron.wallet.business.assetshome.AssetsFragment;
import com.tron.wallet.business.assetshome.SortHelper;
import com.tron.wallet.business.assetshome.adapter.AssetsHeaderAdapter;
import com.tron.wallet.business.assetshome.listener.HiddenSwitchListener;
import com.tron.wallet.business.assetshome.nested.AssetListFragment;
import com.tron.wallet.business.assetshome.tipview.AssetsTipViewHelper;
import com.tron.wallet.business.assetshome.tipview.MnemonicBackupTipView;
import com.tron.wallet.business.assetshome.tipview.MultiPermissionTipView;
import com.tron.wallet.business.assetshome.tipview.MultiSignTipView;
import com.tron.wallet.business.assetshome.tipview.SecAskTipView;
import com.tron.wallet.business.tabmy.dealsign.DealSignActivity;
import com.tron.wallet.business.tabmy.myhome.settings.ServerSelectActivity;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordBean;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.business.walletmanager.selectwallet.SelectWalletActivity;
import com.tron.wallet.common.adapter.holder.AssetTabHolder;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.MultiSignPopupTextView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.task.AccountUpdater;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.SdUtils;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FgAssetsBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.NetUtil;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class AssetsFragment extends BaseFragment<AssetsPresenter, AssetsModel> implements AssetsContract.View, PermissionInterface {
    public static final int TYPE_ASSETS = 0;
    public static final int TYPE_NFT = 1;
    private Protocol.Account account;
    View appBarDivider;
    AppBarLayout appBarLayout;
    private AssetTabHolder assetTabHolder;
    private AssetsTipViewHelper assetsTipViewHelper;
    private FgAssetsBinding binding;
    private boolean collapse;
    CoordinatorLayout coordinatorLayout;
    private AssetListFragment[] fragments;
    private boolean goDealAct;
    private boolean isShowSwitchNode;
    ImageView ivScan;
    View llBackupTipView;
    View llDealSignTipView;
    View llMultiPermissionTipView;
    View llSecAskTipView;
    LinearLayout llSignGo;
    LinearLayout llWalletName;
    TextView mBlockAmount;
    TextView mCurrentBlock;
    private AssetsHeaderAdapter mHeaderAdapter;
    private PermissionHelper mPermissionHelper;
    RecyclerView mRecyclerView;
    ImageView mSignClose;
    TextView mSignDesc;
    TextView mWalletNameTv;
    private MnemonicBackupTipView mnemonicBackupTipView;
    private MultiPermissionTipView multiPermissionTipView;
    private MultiSignTipView multiSignTipView;
    private AssetsFragmentPagerAdapter pagerAdapter;
    PtrHTFrameLayout rcFrame;
    RelativeLayout rlBlockSync;
    RelativeLayout rlNetNotice;
    RelativeLayout rlNodeNotice;
    RelativeLayout rlServerNotice;
    private SecAskTipView secAskTipView;
    XTabLayout tabLayout;
    View tabRightContainer;
    private String[] tabTitles;
    TextView tvChainName;
    TextView tvSwitchNode;
    TextView tvSwitchServer;
    ViewPager viewPager;
    private final AtomicInteger socketCounter = new AtomicInteger(0);
    private final AtomicBoolean flagCanDisplayNft = new AtomicBoolean(AssetsConfig.canDisplayCollections());
    private boolean start = true;

    @Override
    public int getPermissionsRequestCode() {
        return 2009;
    }

    @Override
    public boolean getStart() {
        return this.start;
    }

    @Override
    protected void processData() {
    }

    @Override
    public void setStart(boolean z) {
        this.start = z;
    }

    @Override
    protected void processData(Bundle bundle) {
        initViews(bundle);
        this.mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                StatusBarUtils.setLightStatusBar(getActivity(), true);
            }
        });
        this.tvChainName.setText(NetUtil.getNetName());
        int dip2px = UIUtils.dip2px(5.0f);
        TouchDelegateUtils.expandViewTouchDelegate(this.llWalletName, dip2px, dip2px, dip2px, dip2px * 3);
        ((AssetsPresenter) this.mPresenter).checkAndShowMultiSignView();
        checkAndShowSecAskTipView();
    }

    @Override
    public void updateDealSignView(int i) {
        initTipViews();
        if (i > 0) {
            this.assetsTipViewHelper.showTipView(this.multiSignTipView, this.mContext.getResources().getString(R.string.main_ms, Integer.valueOf(i)));
        } else {
            this.assetsTipViewHelper.hideTipView(this.multiSignTipView);
        }
    }

    @Override
    public void showOrHidenSafeTipView(boolean z) {
        initTipViews();
        if (z) {
            this.assetsTipViewHelper.showTipView(this.mnemonicBackupTipView, new String[0]);
        } else {
            this.assetsTipViewHelper.hideTipView(this.mnemonicBackupTipView);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AssetsPresenter) this.mPresenter).removeListener();
        if (!this.goDealAct) {
            ((AssetsPresenter) this.mPresenter).stopSocket();
        }
        this.start = false;
        if (Build.VERSION.SDK_INT >= 24) {
            AccountUpdater.setInterval(60000L, false);
        }
        MultiSignPopupTextView.hideCurrentPopup();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.flagCanDisplayNft.get() != AssetsConfig.canDisplayCollections()) {
            this.flagCanDisplayNft.set(AssetsConfig.canDisplayCollections());
            this.pagerAdapter.notifyDataSetChanged();
        }
        this.goDealAct = false;
        this.start = true;
        ((AssetsPresenter) this.mPresenter).updateSelectedWallet();
        ((AssetsPresenter) this.mPresenter).showAssetsLoading(true);
        ((AssetsPresenter) this.mPresenter).initData();
        ((AssetsPresenter) this.mPresenter).startSocket(true);
        this.assetTabHolder.updateSortText(SortHelper.get().getInitSortType(getCurrentType() != 0 ? 5 : 0));
        if (Build.VERSION.SDK_INT >= 24) {
            AccountUpdater.setInterval(TronConfig.ACCOUNT_UPDATE_FOREGROUND_INTERVAL, true);
        }
        this.assetTabHolder.checkWatchWallet();
        if (!TronApplication.sdStorageChecked) {
            autoDiskCheck();
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.HomePage.ENTER_HOME_PAGE_TAB_ASSET);
    }

    private void autoDiskCheck() {
        TronApplication.sdStorageChecked = true;
        if (SdUtils.checkFreeSpace()) {
            return;
        }
        ToastUtil.getInstance().show(getActivity(), getResources().getString(R.string.sd_storage_warning));
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgAssetsBinding inflate = FgAssetsBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.rcFrame = this.binding.rcFrame;
        this.mRecyclerView = this.binding.rcList;
        this.llBackupTipView = this.binding.getRoot().findViewById(R.id.ll_mne_backup);
        this.llDealSignTipView = this.binding.getRoot().findViewById(R.id.ll_multi_sign);
        this.llMultiPermissionTipView = this.binding.getRoot().findViewById(R.id.ll_multi_permission);
        this.llSecAskTipView = this.binding.getRoot().findViewById(R.id.ll_sec_ask);
        this.mSignClose = this.binding.llMneBackup.ivSignClose;
        this.mSignDesc = this.binding.llMneBackup.tvSignDesc;
        this.llSignGo = this.binding.llMneBackup.llSignArrow;
        this.llWalletName = this.binding.walletNameLayout.llWalletname;
        this.mWalletNameTv = this.binding.walletNameLayout.tvWalletname;
        this.rlNetNotice = this.binding.walletNameLayout.rlNetNotice;
        this.rlServerNotice = this.binding.walletNameLayout.rlServerNotice;
        this.tvSwitchServer = this.binding.walletNameLayout.tvSwitchServer;
        this.rlNodeNotice = this.binding.walletNameLayout.rlNodeNotice;
        this.tvSwitchNode = this.binding.walletNameLayout.tvSwitchNode;
        this.rlBlockSync = this.binding.walletNameLayout.rlBlockSync;
        this.mCurrentBlock = this.binding.walletNameLayout.tvCurrentBlock;
        this.mBlockAmount = this.binding.walletNameLayout.tvBlockAmount;
        this.ivScan = this.binding.walletNameLayout.ivWalletScan;
        this.viewPager = this.binding.viewPagerContent;
        this.tabLayout = this.binding.llTab;
        this.coordinatorLayout = this.binding.coordinatorLayout;
        this.appBarLayout = this.binding.appbarLayout;
        this.tabRightContainer = this.binding.rlBtnContainer;
        this.tvChainName = this.binding.walletNameLayout.tvChainName;
        this.appBarDivider = this.binding.divider;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_wallet_manager:
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_ADD_WALLET);
                        mContext.startActivity(new Intent(mContext, AddWalletActivityV2.class));
                        return;
                    case R.id.iv_wallet_scan:
                        scan();
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_SCAN);
                        return;
                    case R.id.ll_walletname:
                        SelectWalletActivity.startActivity(mContext);
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_CHANGE_WALLET);
                        return;
                    case R.id.tv_switch_node:
                        ((AssetsPresenter) mPresenter).onClickSwitchNode();
                        return;
                    case R.id.tv_switch_server:
                        go(ServerSelectActivity.class);
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.walletNameLayout.llWalletname.setOnClickListener(noDoubleClickListener);
        this.binding.walletNameLayout.ivWalletManager.setOnClickListener(noDoubleClickListener);
        this.binding.walletNameLayout.ivWalletScan.setOnClickListener(noDoubleClickListener);
        this.binding.walletNameLayout.tvSwitchNode.setOnClickListener(noDoubleClickListener);
        this.binding.walletNameLayout.tvSwitchServer.setOnClickListener(noDoubleClickListener);
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        StatusBarUtils.setLightStatusBar(getActivity(), true);
    }

    @Override
    public void scan() {
        PermissionHelper permissionHelper = new PermissionHelper(this.mContext, this);
        this.mPermissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionHelper permissionHelper = this.mPermissionHelper;
        if (permissionHelper == null || !permissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    @Override
    public void requestPermissionsSuccess() {
        toScan();
    }

    public void toScan() {
        ScannerActivity.startFromFragment(this);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        ((AssetsPresenter) this.mPresenter).onActivityResult(i, i2, intent);
    }

    @Override
    public void updateWalletName(Wallet wallet) {
        if (wallet != null) {
            this.mWalletNameTv.setText(wallet.getWalletName());
            this.account = null;
        }
    }

    @Override
    public void showSwitchServerNotice(boolean z) {
        if (TronConfig.hasNet) {
            this.rlNetNotice.setVisibility(View.GONE);
            this.rlServerNotice.setVisibility(z ? View.VISIBLE : View.GONE);
            return;
        }
        this.rlNetNotice.setVisibility(View.VISIBLE);
        this.rlServerNotice.setVisibility(View.GONE);
    }

    private void initTipViews() {
        if (this.assetsTipViewHelper == null) {
            this.assetsTipViewHelper = new AssetsTipViewHelper();
            if (this.multiSignTipView == null && this.llDealSignTipView != null) {
                MultiSignTipView multiSignTipView = new MultiSignTipView(this.mContext, this.llDealSignTipView, new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        lambda$initTipViews$0(view);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        lambda$initTipViews$1(view);
                    }
                });
                this.multiSignTipView = multiSignTipView;
                this.assetsTipViewHelper.setTipView(multiSignTipView);
            }
            if (this.multiPermissionTipView == null && this.llMultiPermissionTipView != null) {
                MultiPermissionTipView multiPermissionTipView = new MultiPermissionTipView(this.mContext, this.llMultiPermissionTipView, null, null);
                this.multiPermissionTipView = multiPermissionTipView;
                this.assetsTipViewHelper.setTipView(multiPermissionTipView);
            }
            if (this.mnemonicBackupTipView == null && this.llBackupTipView != null) {
                MnemonicBackupTipView mnemonicBackupTipView = new MnemonicBackupTipView(this.mContext, this.llBackupTipView, null, new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        lambda$initTipViews$2(view);
                    }
                });
                this.mnemonicBackupTipView = mnemonicBackupTipView;
                this.assetsTipViewHelper.setTipView(mnemonicBackupTipView);
            }
            if (this.secAskTipView == null && this.llSecAskTipView != null && SecAskTipView.isVisibility()) {
                SecAskTipView secAskTipView = new SecAskTipView(this.mContext, this.llSecAskTipView, null, null);
                this.secAskTipView = secAskTipView;
                this.assetsTipViewHelper.setTipView(secAskTipView);
            }
        }
    }

    public void lambda$initTipViews$0(View view) {
        this.goDealAct = false;
        ((AssetsPresenter) this.mPresenter).recordDealAct();
        ((AssetsPresenter) this.mPresenter).stopSocket();
    }

    public void lambda$initTipViews$1(View view) {
        ((AssetsPresenter) this.mPresenter).recordDealAct();
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null && selectedWallet.isWatchNotPaired()) {
            PairColdWalletDialog.showUp(getIContext(), null);
            this.goDealAct = false;
            return;
        }
        DealSignActivity.start(this.mContext, selectedWallet != null ? selectedWallet.getWalletName() : "", ((AssetsPresenter) this.mPresenter).getSocketState());
        this.goDealAct = true;
    }

    public void lambda$initTipViews$2(View view) {
        if (this.mPresenter != 0) {
            ((AssetsPresenter) this.mPresenter).backup();
        }
    }

    @Override
    public void onWalletChanged(Wallet wallet) {
        initTipViews();
        this.assetsTipViewHelper.onWalletChanged(wallet);
    }

    @Override
    public void showMultiPermissionTipView(boolean z, String str, String str2) {
        initTipViews();
        if (z) {
            this.assetsTipViewHelper.showTipView(this.multiPermissionTipView, str, str2);
        } else {
            this.assetsTipViewHelper.hideTipView(this.multiPermissionTipView);
        }
    }

    @Override
    public void hideMultiPermissionTipView() {
        AssetsTipViewHelper assetsTipViewHelper;
        MultiPermissionTipView multiPermissionTipView = this.multiPermissionTipView;
        if (multiPermissionTipView == null || (assetsTipViewHelper = this.assetsTipViewHelper) == null) {
            return;
        }
        assetsTipViewHelper.hideTipView(multiPermissionTipView);
    }

    private void checkAndShowSecAskTipView() {
        if (SecAskTipView.isVisibility()) {
            initTipViews();
            SecAskTipView secAskTipView = this.secAskTipView;
            if (secAskTipView != null) {
                this.assetsTipViewHelper.showTipView(secAskTipView, new String[0]);
            }
        }
    }

    private boolean noNetLogic() {
        if (!TronConfig.hasNet) {
            this.rlNetNotice.setVisibility(View.VISIBLE);
            this.rlServerNotice.setVisibility(View.GONE);
            RelativeLayout relativeLayout = this.rlNodeNotice;
            if (relativeLayout == null || relativeLayout.getVisibility() != 0) {
                return true;
            }
            this.rlNodeNotice.setVisibility(View.GONE);
            return true;
        }
        this.rlNetNotice.setVisibility(View.GONE);
        return false;
    }

    @Override
    public void setNetNotice(boolean z) {
        RelativeLayout relativeLayout;
        noNetLogic();
        if (z || (relativeLayout = this.rlNodeNotice) == null || relativeLayout.getVisibility() != 0) {
            return;
        }
        this.rlNodeNotice.setVisibility(View.GONE);
    }

    @Override
    public void setShowSwitchNode(boolean z) {
        if (z) {
            if (!TronConfig.hasNet) {
                RelativeLayout relativeLayout = this.rlNodeNotice;
                if (relativeLayout != null && relativeLayout.getVisibility() == 0) {
                    this.rlNodeNotice.setVisibility(View.GONE);
                }
                noNetLogic();
                return;
            }
            noNetLogic();
        }
        if (this.isShowSwitchNode == z) {
            return;
        }
        this.isShowSwitchNode = z;
        if (this.rlNodeNotice != null) {
            if (z && TronConfig.hasNet) {
                this.rlNodeNotice.setVisibility(View.VISIBLE);
            } else {
                this.rlNodeNotice.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void updateSyncBlock(long j, long j2) {
        if (this.rlBlockSync != null) {
            TextView textView = this.mCurrentBlock;
            textView.setText(j + "");
            TextView textView2 = this.mBlockAmount;
            textView2.setText(j2 + "");
        }
    }

    @Override
    public void showBlockSync(Wallet wallet) {
        if (wallet != null && wallet.isShieldedWallet()) {
            this.rlBlockSync.setVisibility(SpAPI.THIS.getCurrentChainName().equals("MainChain") ^ true ? View.GONE : View.VISIBLE);
            return;
        }
        this.rlBlockSync.setVisibility(View.GONE);
    }

    @Override
    public void updateStart(int i) {
        AssetListFragment[] assetListFragmentArr = this.fragments;
        if (assetListFragmentArr == null || i >= assetListFragmentArr.length) {
            return;
        }
        assetListFragmentArr[i].updateStart();
    }

    @Override
    public void updateFail(int i) {
        AssetListFragment[] assetListFragmentArr = this.fragments;
        if (assetListFragmentArr == null || i >= assetListFragmentArr.length) {
            return;
        }
        assetListFragmentArr[i].updateFail();
    }

    @Override
    public void updateNewData(int i, List<TokenBean> list, boolean z) {
        AssetListFragment[] assetListFragmentArr = this.fragments;
        if (assetListFragmentArr == null || i >= assetListFragmentArr.length) {
            return;
        }
        assetListFragmentArr[i].updateData(list, z, this.account);
    }

    @Override
    public void updateHidePrivacyState(boolean z) {
        if (this.fragments == null) {
            return;
        }
        AssetsHeaderAdapter assetsHeaderAdapter = this.mHeaderAdapter;
        if (assetsHeaderAdapter != null) {
            assetsHeaderAdapter.updateHiddenState(z);
        }
        for (AssetListFragment assetListFragment : this.fragments) {
            assetListFragment.updateHidePrivacy(z);
        }
    }

    @Override
    public void updateNewAssetCount(int i) {
        AssetTabHolder assetTabHolder = this.assetTabHolder;
        if (assetTabHolder != null) {
            assetTabHolder.onBind(i);
        }
    }

    @Override
    public int getCurrentType() {
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            return viewPager.getCurrentItem();
        }
        return 0;
    }

    @Override
    public void switchFragment(int i) {
        if (i > 1 || i < 0) {
            return;
        }
        this.viewPager.setCurrentItem(i);
    }

    @Override
    @Nonnull
    public List<TokenBean> getData(int i) {
        AssetListFragment[] assetListFragmentArr;
        if (this.viewPager != null && (assetListFragmentArr = this.fragments) != null) {
            int i2 = i == 0 ? 0 : 1;
            if (assetListFragmentArr.length > i2) {
                return assetListFragmentArr[i2].getCurrentData();
            }
        }
        return new ArrayList();
    }

    @Override
    public void onChainChanged() {
        this.tvChainName.setText(SpAPI.THIS.getCurrentChain().chainName);
        if (this.pagerAdapter != null) {
            this.flagCanDisplayNft.set(AssetsConfig.canDisplayCollections());
            this.pagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateHeaderLoading(boolean z) {
        AssetsHeaderAdapter assetsHeaderAdapter = this.mHeaderAdapter;
        if (assetsHeaderAdapter != null) {
            assetsHeaderAdapter.updateLoadingPrice(z);
        }
    }

    @Override
    public void updateHeaderData(Wallet wallet, AssetsHomeData assetsHomeData, Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage, boolean z) {
        AssetsHeaderAdapter assetsHeaderAdapter = this.mHeaderAdapter;
        if (assetsHeaderAdapter != null) {
            this.account = account;
            assetsHeaderAdapter.updateUI(wallet, assetsHomeData, account, accountResourceMessage, z);
        }
    }

    @Override
    public void updateBackuptipsView(BackupRecordBean backupRecordBean) {
        AssetsHeaderAdapter assetsHeaderAdapter = this.mHeaderAdapter;
        if (assetsHeaderAdapter != null) {
            assetsHeaderAdapter.updateBackupTips(backupRecordBean);
        }
    }

    @Override
    public void pullToRefreshComplete() {
        this.rcFrame.refreshComplete();
    }

    @Override
    public void updateHideFiltersFlag(boolean z, boolean z2) {
        SortHelper.get().setHideFiltersFlag(z, z2);
        AssetTabHolder assetTabHolder = this.assetTabHolder;
        if (assetTabHolder != null) {
            assetTabHolder.updateHideLittleAssetsStatus(z | z2);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        AssetListFragment assetListFragment;
        super.onSaveInstanceState(bundle);
        FragmentManager childFragmentManager = getChildFragmentManager();
        AssetListFragment assetListFragment2 = this.fragments[0];
        if (assetListFragment2 != null && assetListFragment2.isAdded()) {
            childFragmentManager.putFragment(bundle, "AssetListFragment:0", this.fragments[0]);
        }
        if (this.flagCanDisplayNft.get() && (assetListFragment = this.fragments[1]) != null && assetListFragment.isAdded()) {
            childFragmentManager.putFragment(bundle, "AssetListFragment:1", this.fragments[1]);
        }
    }

    private void initViews(Bundle bundle) {
        this.tabTitles = getResources().getStringArray(R.array.asset_titles);
        this.fragments = new AssetListFragment[this.flagCanDisplayNft.get() ? 2 : 1];
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (bundle != null) {
            this.fragments[0] = (AssetListFragment) childFragmentManager.getFragment(bundle, "AssetListFragment:0");
            if (this.flagCanDisplayNft.get()) {
                this.fragments[1] = (AssetListFragment) childFragmentManager.getFragment(bundle, "AssetListFragment:1");
            }
        }
        AssetListFragment[] assetListFragmentArr = this.fragments;
        if (assetListFragmentArr[0] == null) {
            assetListFragmentArr[0] = AssetListFragment.create(0);
        }
        if (this.flagCanDisplayNft.get()) {
            AssetListFragment[] assetListFragmentArr2 = this.fragments;
            if (assetListFragmentArr2[1] == null) {
                assetListFragmentArr2[1] = AssetListFragment.create(1);
            }
        }
        AssetsFragmentPagerAdapter assetsFragmentPagerAdapter = new AssetsFragmentPagerAdapter(childFragmentManager);
        this.pagerAdapter = assetsFragmentPagerAdapter;
        this.viewPager.setAdapter(assetsFragmentPagerAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.rcFrame.setViewPager(this.viewPager);
        this.rcFrame.setPtrHandler(new fun3());
        this.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                lambda$initViews$3(appBarLayout, i);
            }
        });
        AssetTabHolder assetTabHolder = new AssetTabHolder(this.tabRightContainer, new SortHelper.OnSortChangedListener() {
            @Override
            public final void onSortChanged(PopupWindow popupWindow, TokenSortType tokenSortType, int i, boolean z, boolean z2, boolean z3) {
                lambda$initViews$4(popupWindow, tokenSortType, i, z, z2, z3);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initViews$5((Pair) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        this.assetTabHolder = assetTabHolder;
        assetTabHolder.onBind(0);
        this.tabLayout.addOnTabSelectedListener(this.assetTabHolder);
        boolean assetStatus = this.mPresenter != 0 ? ((AssetsPresenter) this.mPresenter).getAssetStatus() : false;
        AssetsHeaderAdapter assetsHeaderAdapter = new AssetsHeaderAdapter();
        this.mHeaderAdapter = assetsHeaderAdapter;
        assetsHeaderAdapter.setSwapClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initViews$6(view);
            }
        }).setHiddenChangedListener(assetStatus, new HiddenSwitchListener() {
            @Override
            public final void onSwitch(boolean z) {
                lambda$initViews$7(z);
            }
        });
        this.mRecyclerView.setAdapter(this.mHeaderAdapter);
        this.mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getIContext(), 1, false) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        });
    }

    public class fun3 implements PtrHandler {
        fun3() {
        }

        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
            if (collapse || fragments[viewPager.getCurrentItem()].canPullDown()) {
                return false;
            }
            return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, coordinatorLayout, view2);
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            ptrFrameLayout.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    AssetsFragment.3.this.lambda$onRefreshBegin$0();
                }
            }, 1000L);
        }

        public void lambda$onRefreshBegin$0() {
            ((AssetsPresenter) mPresenter).onRefresh();
        }
    }

    public void lambda$initViews$3(AppBarLayout appBarLayout, int i) {
        this.collapse = i != 0;
        boolean z = Math.abs(i) >= appBarLayout.getTotalScrollRange();
        if (z && this.appBarDivider.getVisibility() != 0) {
            this.appBarDivider.setVisibility(View.VISIBLE);
        } else if (z || this.appBarDivider.getVisibility() == 8) {
        } else {
            this.appBarDivider.setVisibility(View.GONE);
        }
    }

    public void lambda$initViews$4(PopupWindow popupWindow, TokenSortType tokenSortType, int i, boolean z, boolean z2, boolean z3) {
        if (this.mPresenter != 0) {
            ((AssetsPresenter) this.mPresenter).onSortChanged(tokenSortType, i);
        }
    }

    public void lambda$initViews$5(Pair pair) {
        if (pair != null) {
            boolean booleanValue = ((Boolean) pair.first).booleanValue();
            boolean booleanValue2 = ((Boolean) pair.second).booleanValue();
            if (this.mPresenter != 0) {
                ((AssetsPresenter) this.mPresenter).setSortFiltersFlag(booleanValue, booleanValue2);
            }
            AnalyticsHelper.AssetPage.logEvent(booleanValue ? AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_HIDE_SMALL_BALANCE : AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_DISPLAY_SMALL_BALANCE);
        }
    }

    public void lambda$initViews$6(View view) {
        if (this.mPresenter == 0 || ((AssetsPresenter) this.mPresenter).mRxManager == null) {
            return;
        }
        ((AssetsPresenter) this.mPresenter).mRxManager.post(Event.ENTER_SWAP, 1001);
    }

    public void lambda$initViews$7(boolean z) {
        if (this.mPresenter != 0) {
            ((AssetsPresenter) this.mPresenter).switchAssetStatus(z);
        }
    }

    public class AssetsFragmentPagerAdapter extends FragmentStatePagerAdapter {
        @Override
        public int getItemPosition(Object obj) {
            return -2;
        }

        public AssetsFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments[i];
        }

        @Override
        public int getCount() {
            if (flagCanDisplayNft.get()) {
                return tabTitles.length;
            }
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return tabTitles[i];
        }
    }
}
