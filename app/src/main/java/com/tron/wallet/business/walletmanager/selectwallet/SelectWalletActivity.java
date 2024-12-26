package com.tron.wallet.business.walletmanager.selectwallet;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addwallet.AddWalletActivityV2;
import com.tron.wallet.business.assetshome.AssetsPresenter;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.business.walletmanager.selectwallet.SelectSortTypeBottomPopup;
import com.tron.wallet.business.walletmanager.selectwallet.SelectWalletContract;
import com.tron.wallet.business.walletmanager.selectwallet.adapter.SelectWalletAdapter;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.business.walletmanager.selectwallet.controller.RecentlyWalletController;
import com.tron.wallet.business.walletmanager.selectwallet.search.SearchWalletActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.UpgradeParamSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcSelectwalletBinding;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import j$.util.Collection;
import j$.util.Map;
import j$.util.Objects;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.tron.walletserver.Wallet;
public class SelectWalletActivity extends BaseActivity<SelectWalletPresenter, SelectWalletModel> implements SelectWalletContract.View {
    private AnimatorSet animatorSet;
    View btnAdd;
    View btnBack;
    View btnSort;
    private Map<WalletGroupType, Integer> groupInfo;
    private boolean isFromDetail;
    ImageView ivColdHard;
    ImageView ivNormal;
    ImageView ivRecently;
    ImageView ivWatch;
    ImageView ivWatchCold;
    View llAction;
    private BasePopupView popupView;
    RecyclerView rvList;
    private SelectWalletAdapter selectWalletAdapter;
    private AcSelectwalletBinding selectWalletBinding;
    private boolean showShieldWallet;
    View tvAdd;
    TextView tvTitle;

    public static void lambda$initView$3(View view) {
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SelectWalletActivity.class);
        context.startActivity(intent);
    }

    public static void startActivityFromDetail(Context context, boolean z) {
        Intent intent = new Intent();
        intent.setClass(context, SelectWalletActivity.class);
        intent.putExtra(TronConfig.IS_FROM_DETAIL, z);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, boolean z) {
        Intent intent = new Intent();
        intent.setClass(context, SelectWalletActivity.class);
        intent.putExtra(TronConfig.SHIELD_IS_TRC20, z);
        context.startActivity(intent);
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.showShieldWallet = intent.getBooleanExtra(TronConfig.SHIELD_IS_TRC20, false);
            this.isFromDetail = intent.getBooleanExtra(TronConfig.IS_FROM_DETAIL, false);
        }
    }

    @Override
    protected void setLayout() {
        AcSelectwalletBinding inflate = AcSelectwalletBinding.inflate(getLayoutInflater());
        this.selectWalletBinding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.selectWalletBinding = null;
    }

    public void mappingId() {
        this.btnBack = this.selectWalletBinding.ivBack;
        this.tvTitle = this.selectWalletBinding.tvMainTitle;
        this.btnAdd = this.selectWalletBinding.ivAdd;
        this.tvAdd = this.selectWalletBinding.tvAdd;
        this.btnSort = this.selectWalletBinding.ivSort;
        this.rvList = this.selectWalletBinding.rcList;
        this.llAction = this.selectWalletBinding.llAction;
        this.ivRecently = this.selectWalletBinding.ivRecently;
        this.ivNormal = this.selectWalletBinding.ivNormal;
        this.ivColdHard = this.selectWalletBinding.ivColdHard;
        this.ivWatch = this.selectWalletBinding.ivWatch;
        this.ivWatchCold = this.selectWalletBinding.ivWatchCold;
    }

    @Override
    protected void processData() {
        this.groupInfo = new HashMap();
        initView();
        ((SelectWalletPresenter) this.mPresenter).setShowShieldWallet(this.showShieldWallet);
        ((SelectWalletPresenter) this.mPresenter).getWallets();
        AnalyticsHelper.logEvent(this.showShieldWallet ? AnalyticsHelper.SwitchAccountPage.ENTER_SWITCH_ACCOUNT_PAGE_SHIELD : AnalyticsHelper.SwitchAccountPage.ENTER_SWITCH_ACCOUNT_PAGE);
        handTheHDPopWindow();
    }

    private void handTheHDPopWindow() {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                SelectWalletActivity.lambda$handTheHDPopWindow$0(observableEmitter);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$handTheHDPopWindow$1((Boolean) obj);
            }
        });
    }

    public static void lambda$handTheHDPopWindow$0(ObservableEmitter observableEmitter) throws Exception {
        List<HdTronRelationshipBean> queryAllRelationBeans = HDTronWalletController.queryAllRelationBeans();
        HashSet hashSet = new HashSet();
        boolean z = false;
        boolean z2 = false;
        for (HdTronRelationshipBean hdTronRelationshipBean : queryAllRelationBeans) {
            if (!hdTronRelationshipBean.getNonHd()) {
                z2 = true;
            }
            if (!hashSet.contains(hdTronRelationshipBean.getRelationshipHDAddress())) {
                hashSet.add(hdTronRelationshipBean.getRelationshipHDAddress());
            }
        }
        if (z2 && hashSet.size() > 1) {
            z = true;
        }
        observableEmitter.onNext(Boolean.valueOf(z));
        observableEmitter.onComplete();
    }

    public void lambda$handTheHDPopWindow$1(Boolean bool) throws Exception {
        if (SpAPI.THIS.isCold() || SpAPI.THIS.hasHdPopShowed()) {
            return;
        }
        if (UpgradeParamSetting.notNeedUpgrade() && bool.booleanValue()) {
            showWalletStructureUpdatePopWindow(false);
        } else if (UpgradeParamSetting.showChangedHdEdit()) {
            showWalletStructureUpdatePopWindow(true);
        }
    }

    public void showOrHideActionBar(boolean z) {
        ObjectAnimator ofFloat;
        ObjectAnimator ofFloat2;
        if (this.llAction.getVisibility() != 0) {
            return;
        }
        if (z) {
            setSelectedGroup();
        }
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.animatorSet = new AnimatorSet();
        if (z) {
            View view = this.llAction;
            ofFloat = ObjectAnimator.ofFloat(view, "translationX", view.getWidth(), 0.0f);
            ofFloat2 = ObjectAnimator.ofFloat(this.llAction, "alpha", 0.0f, 1.0f);
        } else {
            View view2 = this.llAction;
            ofFloat = ObjectAnimator.ofFloat(view2, "translationX", 0.0f, view2.getWidth());
            ofFloat2 = ObjectAnimator.ofFloat(this.llAction, "alpha", 1.0f, 0.0f);
        }
        this.animatorSet.playTogether(ofFloat, ofFloat2);
        this.animatorSet.setDuration(300L);
        this.animatorSet.start();
    }

    private void initView() {
        if (SpAPI.THIS.isCold()) {
            this.btnSort.setVisibility(View.GONE);
        }
        if (this.showShieldWallet) {
            this.tvTitle.setText(getResources().getString(R.string.select_shield_wallet));
            this.btnAdd.setVisibility(View.GONE);
            this.tvAdd.setVisibility(View.GONE);
            this.btnSort.setVisibility(View.GONE);
        }
        this.selectWalletAdapter = new SelectWalletAdapter(this);
        this.selectWalletAdapter.setHideAssets(getSharedPreferences(AssetsPresenter.KEY_SP, 0).getBoolean(AssetsPresenter.KEY_ASSET_STATUS, false));
        this.selectWalletAdapter.setFromDetail(this.isFromDetail);
        this.rvList.setLayoutManager(new WrapContentLinearLayoutManager(this, 1, false));
        this.rvList.addItemDecoration(new TagDividerItemDecoration());
        this.rvList.setAdapter(this.selectWalletAdapter);
        this.rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int preState = 0;
            private boolean isShowing = true;

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (this.preState == 1 && i2 != 0 && this.isShowing) {
                    showOrHideActionBar(false);
                    this.isShowing = false;
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0 && !this.isShowing) {
                    showOrHideActionBar(true);
                    this.isShowing = true;
                }
                this.preState = i;
            }
        });
        this.selectWalletAdapter.setOnItemViewClickListener(new SelectWalletAdapter.OnItemViewClickListener() {
            @Override
            public final void onViewClick(BaseQuickAdapter baseQuickAdapter, View view, int i, SelectWalletBean selectWalletBean) {
                lambda$initView$2(baseQuickAdapter, view, i, selectWalletBean);
            }
        });
        if (this.showShieldWallet) {
            this.llAction.setVisibility(View.GONE);
        } else if (SpAPI.THIS.isCold()) {
            this.ivNormal.setVisibility(View.GONE);
            this.ivWatchCold.setVisibility(View.GONE);
            this.ivWatch.setVisibility(View.GONE);
        }
        this.llAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                SelectWalletActivity.lambda$initView$3(view);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.ivRecently, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        TouchDelegateUtils.expandViewTouchDelegate(this.ivNormal, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        TouchDelegateUtils.expandViewTouchDelegate(this.ivColdHard, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        TouchDelegateUtils.expandViewTouchDelegate(this.ivWatchCold, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        TouchDelegateUtils.expandViewTouchDelegate(this.ivWatch, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        this.btnBack.setOnClickListener(getOnClickListener());
        this.tvTitle.setOnClickListener(getOnClickListener());
        this.selectWalletBinding.ivAdd.setOnClickListener(getOnClickListener());
        this.tvAdd.setOnClickListener(getOnClickListener());
        this.btnSort.setOnClickListener(getOnClickListener());
        this.ivRecently.setOnClickListener(getOnClickListener());
        this.ivNormal.setOnClickListener(getOnClickListener());
        this.ivColdHard.setOnClickListener(getOnClickListener());
        this.ivWatchCold.setOnClickListener(getOnClickListener());
        this.ivWatch.setOnClickListener(getOnClickListener());
    }

    public void lambda$initView$2(BaseQuickAdapter baseQuickAdapter, View view, int i, SelectWalletBean selectWalletBean) {
        try {
            switch (view.getId()) {
                case R.id.iv_copy:
                case R.id.tv_address:
                    if (!BackupReminder.isWalletBackup(selectWalletBean.getWallet()) && !this.showShieldWallet) {
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
                    break;
                case R.id.iv_jump:
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_WALLET_DETAIL);
                    setSelectedWallet(selectWalletBean.getWallet());
                    WalletDetailActivity.startActivity(this, selectWalletBean.getWallet().getWalletName(), this.showShieldWallet);
                    break;
                case R.id.top_card:
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_SELECT);
                    setSelectedWallet(selectWalletBean.getWallet());
                    exit();
                    break;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void setSelectedWallet(Wallet wallet) {
        if (!this.showShieldWallet) {
            RecentlyWalletController.setRecentlyWallet(wallet);
            WalletUtils.setSelectedWallet(wallet.getWalletName());
        }
        SpAPI.THIS.setColdWalletSelected(wallet.getWalletName());
        if (this.mPresenter == 0 || ((SelectWalletPresenter) this.mPresenter).mRxManager == null) {
            return;
        }
        ((SelectWalletPresenter) this.mPresenter).mRxManager.post(Event.RESET_TAB, "");
    }

    public void scrollItemToTop(int i) {
        int headerLayoutCount = i + this.selectWalletAdapter.getHeaderLayoutCount();
        LinearTopSmoothScroller linearTopSmoothScroller = new LinearTopSmoothScroller(this);
        linearTopSmoothScroller.setTargetPosition(headerLayoutCount);
        this.rvList.getLayoutManager().startSmoothScroll(linearTopSmoothScroller);
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_add:
                    case R.id.tv_add:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_CREATE_WALLET);
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SELECT_ACCOUNT_PAGE_ADD);
                        Intent intent = new Intent(mContext, AddWalletActivityV2.class);
                        if (showShieldWallet) {
                            intent.putExtra(AddWalletType.INTENT_KEY_WALLET_TYPE, 1);
                        }
                        startActivity(intent);
                        return;
                    case R.id.iv_back:
                    case R.id.tv_main_title:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SELECT_ACCOUNT_PAGE_BACK);
                        finish();
                        return;
                    case R.id.iv_cold_hard:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_FLOAT_BAR);
                        if (((Integer) Map.-EL.getOrDefault(groupInfo, WalletGroupType.COLD_HARD, 0)).intValue() > 0) {
                            setSelectedGroup(WalletGroupType.COLD_HARD);
                            SelectWalletActivity selectWalletActivity = SelectWalletActivity.this;
                            selectWalletActivity.scrollItemToTop(selectWalletActivity.getGroupPositionCount(WalletGroupType.HEAT));
                            return;
                        }
                        return;
                    case R.id.iv_normal:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_FLOAT_BAR);
                        if (((Integer) Map.-EL.getOrDefault(groupInfo, WalletGroupType.HEAT, 0)).intValue() > 0) {
                            setSelectedGroup(WalletGroupType.HEAT);
                            SelectWalletActivity selectWalletActivity2 = SelectWalletActivity.this;
                            selectWalletActivity2.scrollItemToTop(((Integer) Map.-EL.getOrDefault(selectWalletActivity2.groupInfo, WalletGroupType.RECENTLY, 0)).intValue());
                            return;
                        }
                        return;
                    case R.id.iv_recently:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_FLOAT_BAR);
                        if (((Integer) Map.-EL.getOrDefault(groupInfo, WalletGroupType.RECENTLY, 0)).intValue() > 0) {
                            setSelectedGroup(WalletGroupType.RECENTLY);
                            scrollItemToTop(0);
                            return;
                        }
                        return;
                    case R.id.iv_sort:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SELECT_ACCOUNT_PAGE_SORT);
                        SelectWalletActivity selectWalletActivity3 = SelectWalletActivity.this;
                        SelectSortTypeBottomPopup.showPopup(selectWalletActivity3, ((SelectWalletPresenter) selectWalletActivity3.mPresenter).getWalletSortType()).setOnItemClickListener(new SelectSortTypeBottomPopup.OnClickListener() {
                            @Override
                            public void onClick(WalletSortType walletSortType) {
                                if (mPresenter == 0 || walletSortType == ((SelectWalletPresenter) mPresenter).getWalletSortType()) {
                                    return;
                                }
                                ((SelectWalletPresenter) mPresenter).setWalletSortType(walletSortType);
                            }
                        });
                        return;
                    case R.id.iv_watch:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_FLOAT_BAR);
                        if (((Integer) Map.-EL.getOrDefault(groupInfo, WalletGroupType.WATCH, 0)).intValue() > 0) {
                            setSelectedGroup(WalletGroupType.WATCH);
                            SelectWalletActivity selectWalletActivity4 = SelectWalletActivity.this;
                            selectWalletActivity4.scrollItemToTop(selectWalletActivity4.getGroupPositionCount(WalletGroupType.WATCH_COLD));
                            return;
                        }
                        return;
                    case R.id.iv_watch_cold:
                        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_FLOAT_BAR);
                        if (((Integer) Map.-EL.getOrDefault(groupInfo, WalletGroupType.WATCH_COLD, 0)).intValue() > 0) {
                            setSelectedGroup(WalletGroupType.WATCH_COLD);
                            SelectWalletActivity selectWalletActivity5 = SelectWalletActivity.this;
                            selectWalletActivity5.scrollItemToTop(selectWalletActivity5.getGroupPositionCount(WalletGroupType.COLD_HARD));
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public int getGroupPositionCount(WalletGroupType walletGroupType) {
        int intValue;
        int intValue2;
        if (walletGroupType == WalletGroupType.RECENTLY) {
            return ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.RECENTLY, 0)).intValue();
        }
        if (walletGroupType == WalletGroupType.HEAT) {
            intValue = ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.RECENTLY, 0)).intValue();
            intValue2 = ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.HEAT, 0)).intValue();
        } else if (walletGroupType == WalletGroupType.COLD_HARD) {
            intValue = ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.RECENTLY, 0)).intValue() + ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.HEAT, 0)).intValue();
            intValue2 = ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.COLD_HARD, 0)).intValue();
        } else if (walletGroupType == WalletGroupType.WATCH_COLD) {
            intValue = ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.RECENTLY, 0)).intValue() + ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.HEAT, 0)).intValue() + ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.COLD_HARD, 0)).intValue();
            intValue2 = ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.WATCH_COLD, 0)).intValue();
        } else {
            intValue = ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.RECENTLY, 0)).intValue() + ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.HEAT, 0)).intValue() + ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.COLD_HARD, 0)).intValue() + ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.WATCH_COLD, 0)).intValue();
            intValue2 = ((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.WATCH, 0)).intValue();
        }
        return intValue + intValue2;
    }

    public void setSelectedGroup(WalletGroupType walletGroupType) {
        this.ivRecently.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.RECENTLY, 0)).intValue() > 0 ? R.mipmap.ic_wallet_recently : R.mipmap.ic_wallet_recently_unable);
        this.ivNormal.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.HEAT, 0)).intValue() > 0 ? R.mipmap.ic_wallet_normal : R.mipmap.ic_wallet_normal_unable);
        this.ivColdHard.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.COLD_HARD, 0)).intValue() > 0 ? R.mipmap.ic_wallet_cold_hard : R.mipmap.ic_wallet_cold_hard_unable);
        this.ivWatchCold.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.WATCH_COLD, 0)).intValue() > 0 ? R.mipmap.ic_wallet_watch_cold : R.mipmap.ic_wallet_watch_cold_unable);
        this.ivWatch.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.WATCH, 0)).intValue() > 0 ? R.mipmap.ic_wallet_watch : R.mipmap.ic_wallet_watch_unable);
        if (walletGroupType == WalletGroupType.RECENTLY) {
            this.ivRecently.setImageResource(R.mipmap.ic_wallet_recently_selected);
        } else if (walletGroupType == WalletGroupType.HEAT) {
            this.ivNormal.setImageResource(R.mipmap.ic_wallet_normal_selected);
        } else if (walletGroupType == WalletGroupType.COLD_HARD) {
            this.ivColdHard.setImageResource(R.mipmap.ic_wallet_cold_hard_selected);
        } else if (walletGroupType == WalletGroupType.WATCH_COLD) {
            this.ivWatchCold.setImageResource(R.mipmap.ic_wallet_watch_cold_selected);
        } else if (walletGroupType == WalletGroupType.WATCH) {
            this.ivWatch.setImageResource(R.mipmap.ic_wallet_watch_selected);
        }
    }

    private void setSelectedGroup() {
        this.ivRecently.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.RECENTLY, 0)).intValue() > 0 ? R.mipmap.ic_wallet_recently : R.mipmap.ic_wallet_recently_unable);
        this.ivNormal.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.HEAT, 0)).intValue() > 0 ? R.mipmap.ic_wallet_normal : R.mipmap.ic_wallet_normal_unable);
        this.ivColdHard.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.COLD_HARD, 0)).intValue() > 0 ? R.mipmap.ic_wallet_cold_hard : R.mipmap.ic_wallet_cold_hard_unable);
        this.ivWatchCold.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.WATCH_COLD, 0)).intValue() > 0 ? R.mipmap.ic_wallet_watch_cold : R.mipmap.ic_wallet_watch_cold_unable);
        this.ivWatch.setImageResource(((Integer) Map.-EL.getOrDefault(this.groupInfo, WalletGroupType.WATCH, 0)).intValue() > 0 ? R.mipmap.ic_wallet_watch : R.mipmap.ic_wallet_watch_unable);
        int findFirstVisibleItemPosition = ((LinearLayoutManager) this.rvList.getLayoutManager()).findFirstVisibleItemPosition();
        if (findFirstVisibleItemPosition < getGroupPositionCount(WalletGroupType.RECENTLY)) {
            this.ivRecently.setImageResource(R.mipmap.ic_wallet_recently_selected);
        } else if (findFirstVisibleItemPosition < getGroupPositionCount(WalletGroupType.HEAT)) {
            this.ivNormal.setImageResource(R.mipmap.ic_wallet_normal_selected);
        } else if (findFirstVisibleItemPosition < getGroupPositionCount(WalletGroupType.COLD_HARD)) {
            this.ivColdHard.setImageResource(R.mipmap.ic_wallet_cold_hard_selected);
        } else if (findFirstVisibleItemPosition < getGroupPositionCount(WalletGroupType.WATCH_COLD)) {
            this.ivWatchCold.setImageResource(R.mipmap.ic_wallet_watch_cold_selected);
        } else if (findFirstVisibleItemPosition < getGroupPositionCount(WalletGroupType.WATCH)) {
            this.ivWatch.setImageResource(R.mipmap.ic_wallet_watch_selected);
        }
    }

    @Override
    public void updateData(List<SelectWalletBean> list) {
        if (!this.showShieldWallet && this.selectWalletAdapter.getHeaderLayoutCount() <= 0 && ((SelectWalletPresenter) this.mPresenter).getWalletsSize() >= 6) {
            this.selectWalletAdapter.addHeaderView(View.inflate(this, R.layout.item_select_wallet_search, null), new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$updateData$4(view);
                }
            });
            this.rvList.scrollToPosition(1);
        } else if (this.selectWalletAdapter.getHeaderLayoutCount() > 0 && ((SelectWalletPresenter) this.mPresenter).getWalletsSize() < 6) {
            this.selectWalletAdapter.removeAllHeaderView();
        }
        this.selectWalletAdapter.updateData(list);
        this.groupInfo.clear();
        Collection.-EL.stream(list).map(new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                WalletGroupType groupType;
                groupType = ((SelectWalletBean) obj).getGroupType();
                return groupType;
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
            }
        }).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$updateData$6((WalletGroupType) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        setSelectedGroup();
    }

    public void lambda$updateData$4(View view) {
        AnalyticsHelper.logEvent(AnalyticsHelper.SwitchAccountPage.CLICK_SWITCH_ACCOUNT_PAGE_SEARCH);
        SearchWalletActivity.startActivity(this);
    }

    public void lambda$updateData$6(WalletGroupType walletGroupType) {
        if (walletGroupType == WalletGroupType.RECENTLY || (((SelectWalletPresenter) this.mPresenter).getWalletSortType() != WalletSortType.SORT_BY_VALUE_ALL && ((SelectWalletPresenter) this.mPresenter).getWalletSortType() != WalletSortType.SORT_BY_VALUE_DEFAULT)) {
            java.util.Map<WalletGroupType, Integer> map = this.groupInfo;
            map.put(walletGroupType, Integer.valueOf(((Integer) Map.-EL.getOrDefault(map, walletGroupType, 0)).intValue() + 1));
            return;
        }
        this.groupInfo.put(WalletGroupType.ALL, Integer.valueOf(((Integer) Map.-EL.getOrDefault(this.groupInfo, walletGroupType, 0)).intValue() + 1));
    }

    @Override
    public void refreshData() {
        this.selectWalletAdapter.notifyDataSetChanged();
    }

    public WalletGroupType getWalletGroupType(SelectWalletBean selectWalletBean) {
        if (selectWalletBean.getGroupType() != WalletGroupType.RECENTLY && (((SelectWalletPresenter) this.mPresenter).getWalletSortType() == WalletSortType.SORT_BY_VALUE_ALL || ((SelectWalletPresenter) this.mPresenter).getWalletSortType() == WalletSortType.SORT_BY_VALUE_DEFAULT)) {
            return WalletGroupType.ALL;
        }
        return selectWalletBean.getGroupType();
    }

    public static class LinearTopSmoothScroller extends LinearSmoothScroller {
        @Override
        protected int getVerticalSnapPreference() {
            return -1;
        }

        public LinearTopSmoothScroller(Context context) {
            super(context);
        }
    }

    public class TagDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Paint mPaint;
        private int mGroupHeight = UIUtils.dip2px(50.0f);
        private int bottomHeight = UIUtils.dip2px(250.0f);

        public TagDividerItemDecoration() {
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setAntiAlias(true);
        }

        private boolean isNewGroup(int i) {
            if (!showShieldWallet) {
                if (i == 0) {
                    return true;
                }
                SelectWalletActivity selectWalletActivity = SelectWalletActivity.this;
                WalletGroupType walletGroupType = selectWalletActivity.getWalletGroupType(selectWalletActivity.selectWalletAdapter.getData(i));
                SelectWalletActivity selectWalletActivity2 = SelectWalletActivity.this;
                if (walletGroupType != selectWalletActivity2.getWalletGroupType(selectWalletActivity2.selectWalletAdapter.getData(i - 1))) {
                    return true;
                }
            }
            return false;
        }

        private WalletGroupType getNewGroupType(int i) {
            SelectWalletActivity selectWalletActivity = SelectWalletActivity.this;
            return selectWalletActivity.getWalletGroupType(selectWalletActivity.selectWalletAdapter.getData(i));
        }

        @Override
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            super.getItemOffsets(rect, view, recyclerView, state);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (childAdapterPosition < selectWalletAdapter.getHeaderLayoutCount()) {
                return;
            }
            if (isNewGroup(childAdapterPosition - selectWalletAdapter.getHeaderLayoutCount())) {
                rect.top = this.mGroupHeight;
            }
            if (childAdapterPosition == recyclerView.getAdapter().getItemCount() - 1) {
                rect.bottom = this.bottomHeight;
            }
        }

        @Override
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            int i;
            int i2;
            super.onDrawOver(canvas, recyclerView, state);
            int left = recyclerView.getLeft() + recyclerView.getPaddingLeft();
            int right = recyclerView.getRight() - recyclerView.getPaddingRight();
            int childCount = recyclerView.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = recyclerView.getChildAt(i3);
                int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
                if (childAdapterPosition >= selectWalletAdapter.getHeaderLayoutCount()) {
                    int headerLayoutCount = childAdapterPosition - selectWalletAdapter.getHeaderLayoutCount();
                    if (isNewGroup(headerLayoutCount)) {
                        int top = childAt.getTop();
                        View inflate = View.inflate(getIContext(), R.layout.item_select_wallet_decoration, null);
                        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_tag);
                        TextView textView = (TextView) inflate.findViewById(R.id.tv_title);
                        int i4 = fun5.$SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletGroupType[getNewGroupType(headerLayoutCount).ordinal()];
                        if (i4 != 1) {
                            i2 = R.string.cold_wallet;
                            if (i4 == 2) {
                                if (!SpAPI.THIS.isCold()) {
                                    i2 = R.string.wallet_hard;
                                }
                                i = R.mipmap.ic_wallet_cold_hard;
                            } else if (i4 == 3) {
                                i = R.mipmap.ic_wallet_watch_cold;
                            } else if (i4 == 4) {
                                i = R.mipmap.ic_wallet_watch;
                                i2 = R.string.wallet_watch;
                            } else if (i4 != 5) {
                                i = R.mipmap.ic_wallet_normal;
                                i2 = R.string.wallet_normal;
                            } else {
                                i = R.mipmap.ic_wallet_all;
                                i2 = R.string.all_wallet;
                            }
                        } else {
                            i = R.mipmap.ic_wallet_recently;
                            i2 = R.string.wallet_recently;
                        }
                        imageView.setImageDrawable(getResources().getDrawable(i));
                        textView.setText(getResources().getString(i2));
                        inflate.setLayoutParams(new ViewGroup.LayoutParams(-2, this.mGroupHeight));
                        inflate.setDrawingCacheEnabled(true);
                        inflate.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                        inflate.layout(0, 0, right, this.mGroupHeight);
                        inflate.buildDrawingCache();
                        canvas.drawBitmap(inflate.getDrawingCache(), left, top - this.mGroupHeight, (Paint) null);
                    }
                }
            }
        }
    }

    static class fun5 {
        static final int[] $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletGroupType;

        static {
            int[] iArr = new int[WalletGroupType.values().length];
            $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletGroupType = iArr;
            try {
                iArr[WalletGroupType.RECENTLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletGroupType[WalletGroupType.COLD_HARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletGroupType[WalletGroupType.WATCH_COLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletGroupType[WalletGroupType.WATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletGroupType[WalletGroupType.ALL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletGroupType[WalletGroupType.HEAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void showWalletStructureUpdatePopWindow(final Boolean bool) {
        BasePopupView asCustom = new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(this) {
            @Override
            public int getImplLayoutId() {
                return R.layout.wallet_structure_dialog;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                Button button = (Button) findViewById(R.id.btn_know);
                TextView textView = (TextView) findViewById(R.id.tv_learm_hd);
                TextView textView2 = (TextView) findViewById(R.id.tv_content4);
                if (bool.booleanValue()) {
                    textView2.setVisibility(View.VISIBLE);
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                textView.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        CommonWebActivityV3.start(getIContext(), IRequest.getLearnHDUrl(), "", -2, false);
                    }
                });
                SpAPI.THIS.setHdPopShowed(true);
            }
        });
        this.popupView = asCustom;
        asCustom.show();
    }
}
