package com.tron.wallet.business.transfer;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.customtokens.CustomTokensModel;
import com.tron.wallet.business.customtokens.bean.SyncCustomTokenOutput;
import com.tron.wallet.business.receive.ReceiveActivity;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.tokendetail.TokenDetailHeaderFragment;
import com.tron.wallet.business.tokendetail.mvp.TokenDetailContentFragment;
import com.tron.wallet.business.transfer.TokenDetailContract;
import com.tron.wallet.business.transfer.deposit.DepositActivity;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.business.vote.adapter.VoteContentVpAdapter;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.DappOptions;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.CustomTabEntity;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.TabEntity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ShadowDrawable;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.ExchangeUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class TokenDetailPresenter extends TokenDetailContract.Presenter {
    private List<ChainBean> allChainJson;
    private TokenDetailHeaderFragment headerFragment;
    private boolean isMainChain;
    private boolean isMapping;
    private double mPriceUsdOrCny;
    private String mTitleStr;
    private TokenBean mToken;
    private ChainBean mainChain;
    private FragmentManager manager;
    private Wallet selectedWallet;
    private ChainBean sideChain;
    private String tokenType;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String typeStr = "tokenAddress";
    private List<Fragment> fragments = new ArrayList();
    private List<String> titleLists = new ArrayList();
    private boolean collapse = false;
    private int filterCount = 0;
    private int filterCountAmount = 0;

    public static void lambda$initRxEvent$2(Object obj) throws Exception {
    }

    private void refreshSubTitle() {
    }

    public TokenBean getToken() {
        return this.mToken;
    }

    @Override
    public Wallet getWallet() {
        return this.selectedWallet;
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override
    protected void onStart() {
        this.selectedWallet = WalletUtils.getSelectedWallet();
        List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
        this.allChainJson = allChainJson;
        if (allChainJson != null && allChainJson.size() > 0) {
            for (int i = 0; i < this.allChainJson.size(); i++) {
                if (this.allChainJson.get(i).isMainChain) {
                    this.mainChain = this.allChainJson.get(i);
                } else {
                    this.sideChain = this.allChainJson.get(i);
                }
            }
        }
        getAllAddress();
        this.mRxManager.on(Event.FILTER_SMALL_VALUE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        this.filterCount++;
        int intValue = this.filterCountAmount + ((Integer) obj).intValue();
        this.filterCountAmount = intValue;
        if (this.filterCount >= 3) {
            this.filterCount = 0;
            if (intValue >= 9) {
                if (SpAPI.THIS.getFilterSmallValue()) {
                    ((TokenDetailContract.View) this.mView).toast(((TokenDetailContract.View) this.mView).getIContext().getString(R.string.token_detail_hided_small_assets, TronConfig.filterSmallValue), R.mipmap.toast_icon_positive);
                } else {
                    ((TokenDetailContract.View) this.mView).toast(((TokenDetailContract.View) this.mView).getIContext().getString(R.string.token_detail_showed_small_assets), R.mipmap.toast_icon_positive);
                }
            }
            this.filterCountAmount = 0;
            ((TokenDetailContract.View) this.mView).dismissLoadingDialog();
            ((TokenDetailContract.View) this.mView).setFilterSmallValueEnable(true);
        }
    }

    private void getAllAddress() {
        ((TokenDetailContract.Model) this.mModel).getAllAddress().compose(RxSchedulers.io_main()).subscribe(new Consumer<HashMap<String, NameAddressExtraBean>>() {
            @Override
            public void accept(HashMap<String, NameAddressExtraBean> hashMap) throws Exception {
                ((TokenDetailContract.View) mView).setAddressMap(hashMap);
            }
        });
    }

    private void refreshBottomBar() {
        boolean z = this.isMainChain ? this.mToken.inSideChain : this.mToken.inMainChain;
        if (IRequest.isShasta() || this.selectedWallet.isWatchOnly()) {
            ((TokenDetailContract.View) this.mView).refreshBottomBarForIsMarket(false, z, this.isMainChain);
        } else if (M.M_TRX.equals(this.tokenType)) {
            ((TokenDetailContract.View) this.mView).refreshBottomBarForIsMarket(true, z, this.isMainChain);
        } else if (this.mToken.marketId != 0) {
            ((TokenDetailContract.View) this.mView).refreshBottomBarForIsMarket(true, z, this.isMainChain);
        } else {
            ((TokenDetailContract.View) this.mView).refreshBottomBarForIsMarket(false, z, this.isMainChain);
        }
        if (this.mToken.inActivity) {
            ((TokenDetailContract.View) this.mView).refreshBottomBarForIsMarket(false, z, this.isMainChain);
        }
    }

    private void initFragmentData(Context context) {
        this.fragments.clear();
        this.fragments.add(TokenDetailContentFragment.getInstance(0, this.tokenType, this.mToken, this.mPriceUsdOrCny));
        this.fragments.add(TokenDetailContentFragment.getInstance(1, this.tokenType, this.mToken, this.mPriceUsdOrCny));
        this.fragments.add(TokenDetailContentFragment.getInstance(2, this.tokenType, this.mToken, this.mPriceUsdOrCny));
        this.titleLists.clear();
        this.titleLists.add(context.getResources().getString(R.string.transfer_all));
        this.titleLists.add(context.getResources().getString(R.string.transfer_out));
        this.titleLists.add(context.getResources().getString(R.string.transfer_in));
        this.mTabEntities.clear();
        this.mTabEntities.add(new TabEntity(context.getResources().getString(R.string.transfer_all), 0, 0));
        this.mTabEntities.add(new TabEntity(context.getResources().getString(R.string.transfer_out), 0, 0));
        this.mTabEntities.add(new TabEntity(context.getResources().getString(R.string.transfer_in), 0, 0));
    }

    private void initTabLayout() {
        ((TokenDetailContract.View) this.mView).getViewPager().setAdapter(new VoteContentVpAdapter((FragmentActivity) ((TokenDetailContract.View) this.mView).getIContext(), this.fragments, this.titleLists));
        ((TokenDetailContract.View) this.mView).getXTablayout().setTabEmptyEqual(true, true);
        ((TokenDetailContract.View) this.mView).getXTablayout().setTabMode(1);
        ((TokenDetailContract.View) this.mView).getXTablayout().setupWithViewPager(((TokenDetailContract.View) this.mView).getViewPager());
        ((TokenDetailContract.View) this.mView).getXTablayout().setOnTabSelectedListener(new XTabLayoutV2.ViewPagerOnTabSelectedListener(((TokenDetailContract.View) this.mView).getViewPager()) {
            @Override
            public void onTabSelected(XTabLayoutV2.Tab tab) {
                super.onTabSelected(tab);
                int position = tab.getPosition();
                if (position == 0) {
                    AnalyticsHelper.logEventFormat(AnalyticsHelper.TokenDetails.CLICK_ALL, Integer.valueOf(((TokenDetailContract.View) mView).getTypeIndex()));
                } else if (position == 1) {
                    AnalyticsHelper.logEventFormat(AnalyticsHelper.TokenDetails.CLICK_OUT, Integer.valueOf(((TokenDetailContract.View) mView).getTypeIndex()));
                } else if (position == 2) {
                    AnalyticsHelper.logEventFormat(AnalyticsHelper.TokenDetails.CLICK_IN, Integer.valueOf(((TokenDetailContract.View) mView).getTypeIndex()));
                }
            }
        });
    }

    @Override
    public void refreshData(Intent intent) {
        this.tokenType = intent.getStringExtra(TronConfig.isTRX);
        TokenBean tokenBean = (TokenBean) intent.getParcelableExtra(TronConfig.TRC);
        this.mToken = tokenBean;
        this.isMapping = this.isMainChain ? tokenBean.inSideChain : tokenBean.inMainChain;
        this.mPriceUsdOrCny = intent.getDoubleExtra(TronConfig.PriceUsdOrCny, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        TokenBean tokenBean2 = this.mToken;
        if (tokenBean2 == null) {
            throw new IllegalArgumentException("mToken is null");
        }
        if (tokenBean2.shortName == null) {
            ((TokenDetailActivity) ((TokenDetailContract.View) this.mView).getIContext()).setTitle("TRX");
        } else {
            ((TokenDetailActivity) ((TokenDetailContract.View) this.mView).getIContext()).setTitle(this.mToken.shortName.equals("") ? this.mToken.name : this.mToken.shortName);
        }
        ((TokenDetailActivity) ((TokenDetailContract.View) this.mView).getIContext()).setRightBold();
        initFragmentData(((TokenDetailContract.View) this.mView).getIContext());
        initTabLayout();
        TokenDetailHeaderFragment tokenDetailHeaderFragment = new TokenDetailHeaderFragment();
        this.headerFragment = tokenDetailHeaderFragment;
        tokenDetailHeaderFragment.init(((TokenDetailContract.View) this.mView).getIContext(), this.tokenType, this.mToken, this.mPriceUsdOrCny, this.isMapping, true);
        this.manager.beginTransaction().replace(R.id.token_detail_header, this.headerFragment).commitAllowingStateLoss();
        historyTask();
    }

    private void calLiOptionLength() {
        int i = 0;
        for (int i2 = 0; i2 < this.titleLists.size(); i2++) {
            String str = this.titleLists.get(i2);
            TextView textView = new TextView(((TokenDetailContract.View) this.mView).getIContext());
            textView.setTextSize(2, 14.0f);
            textView.setText(str);
            i += ((int) textView.getPaint().measureText(str)) + UIUtils.dip2px(45.0f);
        }
        ((TokenDetailContract.View) this.mView).setFilterOptionWidth((UIUtils.getScreenWidth(((TokenDetailContract.View) this.mView).getIContext()) - UIUtils.dip2px(40.0f)) - i);
    }

    @Override
    public void syncCustomToken() {
        if (this.selectedWallet == null || this.mToken == null) {
            return;
        }
        new CustomTokensModel().syncCustomToken(this.selectedWallet.getAddress(), this.mToken.getContractAddress()).subscribe(new IObserver(new ICallback<SyncCustomTokenOutput>() {
            @Override
            public void onResponse(String str, SyncCustomTokenOutput syncCustomTokenOutput) {
                if (syncCustomTokenOutput == null || syncCustomTokenOutput.getData() == null) {
                    return;
                }
                mToken = syncCustomTokenOutput.getData();
                mToken.setId("");
                ((TokenDetailContract.View) mView).updateTokenInfo(mToken);
                updateHeader();
            }

            @Override
            public void onFailure(String str, String str2) {
                ((TokenDetailContract.View) mView).updateTokenInfo(null);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "syncCustomToken"));
    }

    @Override
    public void addSome() {
        boolean z;
        netWorkChange();
        addIntent();
        initRxEvent();
        initFragmentData(((TokenDetailContract.View) this.mView).getIContext());
        ((TokenDetailContract.View) this.mView).getViewPager().setOffscreenPageLimit(3);
        ((TokenDetailContract.View) this.mView).getViewPager().setUserInputEnabled(true);
        initTabLayout();
        ((TokenDetailContract.View) this.mView).getFrameLayout().setViewPager(((TokenDetailContract.View) this.mView).getViewPager());
        ((TokenDetailContract.View) this.mView).getFrameLayout().setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                if (collapse) {
                    return false;
                }
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view2);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                onRefresh();
            }
        });
        if (ViewCompat.isLaidOut(((TokenDetailContract.View) this.mView).getAppBarLayout())) {
            ((AppBarLayout.Behavior) ((CoordinatorLayout.LayoutParams) ((TokenDetailContract.View) this.mView).getAppBarLayout().getLayoutParams()).getBehavior()).setDragCallback(new AppBarLayout.Behavior.DragCallback() {
                @Override
                public boolean canDrag(AppBarLayout appBarLayout) {
                    return true;
                }
            });
        }
        ((TokenDetailContract.View) this.mView).getAppBarLayout().addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            }
        });
        ((TokenDetailContract.View) this.mView).getAppBarLayout().addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                lambda$addSome$1(appBarLayout, i);
            }
        });
        if (((TokenDetailContract.View) this.mView).getIIntent() != null) {
            z = 1 == ((TokenDetailContract.View) this.mView).getIIntent().getIntExtra("from", 0);
        } else {
            z = false;
        }
        this.manager = ((TokenDetailActivity) this.mView).getSupportFragmentManager();
        TokenDetailHeaderFragment tokenDetailHeaderFragment = new TokenDetailHeaderFragment();
        this.headerFragment = tokenDetailHeaderFragment;
        tokenDetailHeaderFragment.init(((TokenDetailContract.View) this.mView).getIContext(), this.tokenType, this.mToken, this.mPriceUsdOrCny, this.isMapping, z);
        this.manager.beginTransaction().replace(R.id.token_detail_header, this.headerFragment).commitAllowingStateLoss();
        calLiOptionLength();
    }

    public void lambda$addSome$1(AppBarLayout appBarLayout, int i) {
        appBarLayout.getTotalScrollRange();
        this.collapse = i != 0;
        ((TokenDetailContract.View) this.mView).getFrameLayout().setEnabled(true ^ this.collapse);
        if (this.collapse && ((TokenDetailContract.View) this.mView).getFrameLayout().isRefreshing()) {
            hideRefresh();
        }
        Color.parseColor("#0F4DC9");
        Color.parseColor("#1972D1");
        new ArgbEvaluator();
    }

    @Override
    public void updateHeader() {
        TokenDetailHeaderFragment tokenDetailHeaderFragment = this.headerFragment;
        if (tokenDetailHeaderFragment != null) {
            tokenDetailHeaderFragment.updateTokenInfo(this.mToken);
        }
    }

    private void hideRefresh() {
        ((TokenDetailContract.View) this.mView).getFrameLayout().refreshComplete();
    }

    public void onRefresh() {
        if (!TronConfig.hasNet) {
            IToast.getIToast().show(((TokenDetailContract.View) this.mView).getIContext().getString(R.string.time_out));
            hideRefresh();
            return;
        }
        TokenDetailHeaderFragment tokenDetailHeaderFragment = this.headerFragment;
        if (tokenDetailHeaderFragment != null) {
            tokenDetailHeaderFragment.refresh();
        }
        ((TokenDetailContentFragment) getContentView()).refresh();
    }

    public Fragment getContentView() {
        return this.fragments.get(((TokenDetailContract.View) this.mView).getViewPager().getCurrentItem());
    }

    private void initRxEvent() {
        this.mRxManager.on(Event.TOKEN_DETAIL, new Consumer() {
            @Override
            public final void accept(Object obj) {
                TokenDetailPresenter.lambda$initRxEvent$2(obj);
            }
        });
        this.mRxManager.on(Event.SCAN_SING, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$3(obj);
            }
        });
        this.mRxManager.on(Event.RECEIVABLES, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$4(obj);
            }
        });
        this.mRxManager.on(Event.SEELATER, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$5(obj);
            }
        });
        this.mRxManager.on(Event.TRANSFER_INNER, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$6(obj);
            }
        });
        this.mRxManager.on(Event.BALANCE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$7(obj);
            }
        });
        this.mRxManager.on(Event.TOKEN_DETAIL_REFRESH_FINISHED, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$8(obj);
            }
        });
        this.mRxManager.on(Event.BackToHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$9(obj);
            }
        });
    }

    public void lambda$initRxEvent$3(Object obj) throws Exception {
        ((TokenDetailContract.View) this.mView).scan();
    }

    public void lambda$initRxEvent$4(Object obj) throws Exception {
        receive();
    }

    public void lambda$initRxEvent$5(Object obj) throws Exception {
        showScan();
    }

    public void lambda$initRxEvent$6(Object obj) throws Exception {
        if (obj.toString().equals("1")) {
            ((BaseActivity) ((TokenDetailContract.View) this.mView).getIContext()).finish();
        }
    }

    public void lambda$initRxEvent$7(Object obj) throws Exception {
        this.mToken.balanceStr = (String) obj;
    }

    public void lambda$initRxEvent$8(Object obj) throws Exception {
        hideRefresh();
    }

    public void lambda$initRxEvent$9(Object obj) throws Exception {
        if (this.mView != 0) {
            ((TokenDetailContract.View) this.mView).exit();
        }
    }

    public void addIntent() {
        try {
            Intent iIntent = ((TokenDetailContract.View) this.mView).getIIntent();
            this.tokenType = iIntent.getStringExtra(TronConfig.isTRX);
            TokenBean tokenBean = (TokenBean) iIntent.getParcelableExtra(TronConfig.TRC);
            this.mToken = tokenBean;
            this.isMapping = this.isMainChain ? tokenBean.inSideChain : tokenBean.inMainChain;
            this.mPriceUsdOrCny = iIntent.getDoubleExtra(TronConfig.PriceUsdOrCny, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            TokenBean tokenBean2 = this.mToken;
            if (tokenBean2 == null) {
                throw new IllegalArgumentException("mToken is null");
            }
            if (tokenBean2.shortName == null) {
                ((TokenDetailActivity) ((TokenDetailContract.View) this.mView).getIContext()).setTitle("TRX");
            } else {
                ((TokenDetailActivity) ((TokenDetailContract.View) this.mView).getIContext()).setTitle(this.mToken.shortName.equals("") ? this.mToken.name : this.mToken.shortName);
            }
            ((TokenDetailActivity) ((TokenDetailContract.View) this.mView).getIContext()).setRightBold();
            refreshSubTitle();
            refreshBottomBar();
            this.mRxManager.onSticky(Event.BroadcastSuccess, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$addIntent$10(obj);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$addIntent$10(Object obj) throws Exception {
        if (obj instanceof TokenBean) {
            historyTask();
            refreshSubTitle();
        }
    }

    @Override
    public void historyTask() {
        List<Fragment> list;
        if (((TokenDetailContract.View) this.mView).isIDestroyed() || (list = this.fragments) == null || list.size() < 3) {
            return;
        }
        ((TokenDetailContentFragment) this.fragments.get(0)).addLocalHistoryToListAndShow();
        ((TokenDetailContentFragment) this.fragments.get(1)).addLocalHistoryToListAndShow();
    }

    @Override
    public long getTokenId() {
        TokenBean tokenBean = this.mToken;
        if (tokenBean == null || tokenBean.getId() == null) {
            return 0L;
        }
        return Long.parseLong(this.mToken.getId().equals("") ? "0" : this.mToken.getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mRxManager != null) {
            this.mRxManager.clear();
        }
    }

    @Override
    void netWorkChange() {
        this.isMainChain = SpAPI.THIS.getCurrentChain() == null ? true : SpAPI.THIS.getCurrentChain().isMainChain;
    }

    public void showScan() {
        Intent intent = new Intent(((TokenDetailContract.View) this.mView).getIContext(), ReceiveActivity.class);
        intent.putExtra(AssetsConfig.TOKEN_TYPE, this.tokenType);
        intent.putExtra("address", this.selectedWallet.getAddress());
        if (M.M_TRC10.equals(this.tokenType)) {
            intent.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, this.mToken.getName());
        }
        if (M.M_TRC20.equals(this.tokenType)) {
            intent.putExtra("trc20Output", this.mToken);
        }
        ((TokenDetailContract.View) this.mView).getIContext().startActivity(intent);
    }

    @Override
    public void doReceive() {
        receive();
    }

    private void receive() {
        AnalyticsHelper.logEventFormat(AnalyticsHelper.TokenDetails.CLICK_RECEIVE, Integer.valueOf(((TokenDetailContract.View) this.mView).getTypeIndex()));
        if (!BackupReminder.isWalletBackup(this.selectedWallet)) {
            BackupReminder.showBackupPopup(((TokenDetailContract.View) this.mView).getIContext(), this.selectedWallet, TronConfig.type_receivables);
        } else {
            showScan();
        }
    }

    @Override
    public void doTransfer() {
        Context iContext = ((TokenDetailContract.View) this.mView).getIContext();
        AnalyticsHelper.logEventFormat(AnalyticsHelper.TokenDetails.CLICK_TRANSFER, Integer.valueOf(((TokenDetailContract.View) this.mView).getTypeIndex()));
        if (TronConfig.hasNet) {
            Wallet selectedWallet = WalletUtils.getSelectedWallet();
            Protocol.Account account = WalletUtils.getAccount(iContext, selectedWallet.getWalletName());
            if (selectedWallet.isShieldedWallet() && (account == null || account.getBalance() < 0)) {
                IToast.getIToast().show(iContext.getString(R.string.wait_to_finished));
                return;
            } else {
                SelectSendAddressActivity.startCommon(iContext, this.mToken, account, null);
                return;
            }
        }
        IToast.getIToast().show(iContext.getString(R.string.time_out));
    }

    public void doMarket() {
        if (!TronConfig.hasNet) {
            IToast.getIToast().show(((TokenDetailContract.View) this.mView).getIContext().getString(R.string.no_network));
        } else if (SpAPI.THIS.isFirstMarket()) {
            CustomDialog.Builder builder = new CustomDialog.Builder(((TokenDetailContract.View) this.mView).getIContext());
            final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.market_dialog).build();
            View view = builder.getView();
            View findViewById = view.findViewById(R.id.ll_shadow_cancel);
            View findViewById2 = view.findViewById(R.id.ll_shadow_ok);
            ShadowDrawable.setShadowDrawable(findViewById, DensityUtils.dp2px(((TokenDetailContract.View) this.mView).getIContext(), 10.0f), 856176839, 16, 0, DensityUtils.dp2px(((TokenDetailContract.View) this.mView).getIContext(), 2.0f));
            ShadowDrawable.setShadowDrawable(findViewById2, DensityUtils.dp2px(((TokenDetailContract.View) this.mView).getIContext(), 10.0f), 856176839, 20, 0, DensityUtils.dp2px(((TokenDetailContract.View) this.mView).getIContext(), 2.0f));
            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck);
            ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view2) {
                    CustomDialog.this.dismiss();
                }
            });
            ((TextView) view.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view2) {
                    lambda$doMarket$12(build, checkBox, view2);
                }
            });
            build.show();
        } else {
            goMarket();
        }
    }

    public void lambda$doMarket$12(CustomDialog customDialog, CheckBox checkBox, View view) {
        customDialog.dismiss();
        goMarket();
        if (checkBox.isChecked()) {
            SpAPI.THIS.setIsFirstMarket(false);
        }
    }

    public void goMarket() {
        String str;
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        TronConfig.address = selectedWallet.getAddress();
        TronConfig.walletName = selectedWallet.getWalletName();
        if (this.mToken.getType() == 2 || this.mToken.getType() == 1) {
            int marketId = this.mToken.getMarketId();
            if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
                str = "https://m.poloniex.org/?lang=zh#/marketItem/" + marketId;
            } else {
                str = "https://m.poloniex.org/?lang=en#/marketItem/" + marketId;
            }
            CommonWebActivityV3.start(((TokenDetailContract.View) this.mView).getIContext(), str, ((TokenDetailContract.View) this.mView).getIContext().getString(R.string.poloni_dex), true, new WebOptions.WebOptionsBuild().addCode(1).addDappOptions(new DappOptions.DappOptionsBuild().addIcon(this.mToken.logoUrl).build()).build());
            return;
        }
        CommonWebActivityV3.start(((TokenDetailContract.View) this.mView).getIContext(), LanguageUtils.languageZH(AppContextUtil.getContext()) ? "https://poloniex.org/zh/" : ExchangeUtils.ExchangeUrl, ((TokenDetailContract.View) this.mView).getIContext().getString(R.string.poloni_dex), true, new WebOptions.WebOptionsBuild().addCode(1).addDappOptions(new DappOptions.DappOptionsBuild().addIcon(this.mToken.logoUrl).build()).build());
    }

    public void doDepostit() {
        AnalyticsHelper.logEventFormat(AnalyticsHelper.TokenDetails.CLICK_WITHDRAW_DEPOSIT, Integer.valueOf(((TokenDetailContract.View) this.mView).getTypeIndex()));
        DepositActivity.start(((TokenDetailContract.View) this.mView).getIContext(), this.mToken);
    }

    public void setFilterSmallValue(boolean z) {
        if (this.fragments != null) {
            for (int i = 0; i < this.fragments.size(); i++) {
                ((TokenDetailContentFragment) this.fragments.get(i)).setFilterSmallValue(z);
            }
        }
    }

    @Override
    public boolean isCurrentRefresh() {
        try {
            return ((TokenDetailContentFragment) getContentView()).isCurRefresh();
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }
}
