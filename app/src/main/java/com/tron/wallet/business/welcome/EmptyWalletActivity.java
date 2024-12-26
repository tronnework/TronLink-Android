package com.tron.wallet.business.welcome;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.create.creatimport.UserPrivacyAgreementActivity;
import com.tron.wallet.business.ledger.search.SearchLedgerActivity;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.migrate.MigrateActivity;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import com.tron.wallet.business.migrate.component.MigrateProgressDialog;
import com.tron.wallet.business.pull.PullActivity;
import com.tron.wallet.business.samsung.SamsungSDKWrapper;
import com.tron.wallet.business.walletmanager.createwallet.CreateWalletActivity;
import com.tron.wallet.business.walletmanager.importwallet.AddWatchWalletActivity;
import com.tron.wallet.business.walletmanager.importwallet.ImportSamsungActivity;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletActivity;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletActivity;
import com.tron.wallet.common.adapter.user.GuileViewPagerAdapter;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.components.bannerview.transform.GuidePageTransformer;
import com.tron.wallet.common.components.dialog.Common2Dialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.NodeUtils;
import com.tron.wallet.databinding.AcEmptytronwalletBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.tron.walletserver.Wallet;
public class EmptyWalletActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private static final int REQUESTCODE_SamsungKeystoreWallet = 16;
    private static final String TAG = "EmptyWalletActivity";
    LinearLayout OtherLayout;
    private AcEmptytronwalletBinding binding;
    View coldView;
    private List<SimpleGuideFragment> dataList;
    ImageView ivHandBg;
    View ivIndicator;
    private LinearLayout llCold;
    TextView mImportSamsungView;
    View migrateView;
    private BasePopupView popupView;
    private int[] res;
    private int[] resInnerTitle;
    private int[] resTitle;
    RelativeLayout rlBottom;
    RelativeLayout rlContent;
    RelativeLayout rlCreate;
    RelativeLayout rlImport;
    View rlSamsung;
    RelativeLayout rlSwitch;
    RelativeLayout root;
    private RxManager rxManager;
    private BasePopupView safetyPopView;
    TextView tvColdWallet;
    TextView tvCreate;
    TextView tvImport;
    TextView tvIndicator1;
    TextView tvIndicator2;
    TextView tvIndicator3;
    TextView tvIndicator4;
    private Button tvKnow;
    TextView tvLedger;
    private TextView tvNote;
    TextView tvObservation;
    TextView tvSwitch;
    private GuileViewPagerAdapter viewPagerAdapter;
    ViewPager2 vpContent;
    private boolean isJumpToSamSungWallet = false;
    private int GUIDE_PAGE_SIZE = 4;
    private boolean isCold = false;

    public interface OnSafeChannelPopClickListener {
        void confirmClick();
    }

    public static void lambda$processData$0(View view) {
    }

    public boolean isCold() {
        return this.isCold;
    }

    @Override
    protected void setLayout() {
        AcEmptytronwalletBinding inflate = AcEmptytronwalletBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    public void mappingId() {
        this.rlSwitch = this.binding.rlSwitch;
        this.tvSwitch = this.binding.tvSwitch;
        this.vpContent = this.binding.vpContent;
        this.tvCreate = this.binding.tvCreate;
        this.tvImport = this.binding.tvImport;
        this.root = this.binding.root;
        this.rlContent = this.binding.rlContent;
        this.ivHandBg = this.binding.handBg;
        this.ivIndicator = this.binding.ivIndicator;
        this.tvIndicator1 = this.binding.indicator1;
        this.tvIndicator2 = this.binding.indicator2;
        this.tvIndicator3 = this.binding.indicator3;
        this.tvIndicator4 = this.binding.indicator4;
        this.rlCreate = this.binding.rlCreate;
        this.rlImport = this.binding.rlImport;
        this.rlBottom = this.binding.rlBottom;
        this.mImportSamsungView = this.binding.tvImportSamsung;
        this.OtherLayout = this.binding.llOthers;
        this.migrateView = this.binding.tvImportMigrate;
        this.tvObservation = this.binding.tvObservation;
        this.tvLedger = this.binding.tvLedger;
        this.tvColdWallet = this.binding.tvCold;
        this.rlSamsung = this.binding.rlSamsungImport;
        this.coldView = this.binding.rlCold;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        for (int i = 0; i < this.GUIDE_PAGE_SIZE; i++) {
            if (this.dataList.get(i) != null && (this.dataList.get(i).isAdded() || this.dataList.get(i).getTag() != null)) {
                supportFragmentManager.putFragment(bundle, "SimpleGuideFragment" + i, this.dataList.get(i));
            }
        }
    }

    @Override
    public void onCreate2(Bundle bundle) {
        super.onCreate2(bundle);
        this.dataList = new ArrayList();
        for (int i = 0; i < this.GUIDE_PAGE_SIZE; i++) {
            this.dataList.add(null);
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (bundle != null) {
            for (int i2 = 0; i2 < this.GUIDE_PAGE_SIZE; i2++) {
                List<SimpleGuideFragment> list = this.dataList;
                list.set(i2, (SimpleGuideFragment) supportFragmentManager.getFragment(bundle, "SimpleGuideFragment" + i2));
            }
        }
        for (int i3 = 0; i3 < this.GUIDE_PAGE_SIZE; i3++) {
            if (this.dataList.get(i3) == null) {
                this.dataList.set(i3, SimpleGuideFragment.getInstance());
            }
        }
    }

    @Override
    protected void processData() {
        try {
            this.tvObservation.getPaint().setFlags(9);
            this.tvLedger.getPaint().setFlags(9);
            this.tvColdWallet.getPaint().setFlags(9);
            this.mImportSamsungView.getPaint().setFlags(9);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        Set<String> publicWalletNames = WalletUtils.getPublicWalletNames();
        if (publicWalletNames != null && publicWalletNames.size() != 0) {
            go(MainTabActivity.class);
            finish();
            return;
        }
        SpAPI.THIS.setIsFirst(false);
        this.isCold = SpAPI.THIS.isCold();
        this.res = new int[]{R.mipmap.guide_pic_abundant, R.mipmap.guide_pic_safe, R.mipmap.guide_pic_easy, R.mipmap.guide_pic_smooth};
        this.resTitle = new int[]{R.string.guide1, R.string.guide3, R.string.guide5, R.string.most_fluent};
        this.resInnerTitle = new int[]{R.string.guide2, R.string.guide_safety, R.string.guide4, R.string.guide6};
        int[] iArr = {R.string.guide7, R.string.guide_safety_title, R.string.guide_easy_asset_title, R.string.guide_smooth_title};
        for (int i = 0; i < this.dataList.size(); i++) {
            this.dataList.get(i).setRes(this.res[i], this.resTitle[i], this.resInnerTitle[i], iArr[i]);
        }
        GuileViewPagerAdapter guileViewPagerAdapter = new GuileViewPagerAdapter(this, this.dataList);
        this.viewPagerAdapter = guileViewPagerAdapter;
        this.vpContent.setAdapter(guileViewPagerAdapter);
        this.vpContent.setPageTransformer(new GuidePageTransformer());
        switchWalletType(this.isCold);
        this.vpContent.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int i2) {
            }

            @Override
            public void onPageScrolled(int i2, float f, int i3) {
            }

            @Override
            public void onPageSelected(int i2) {
                change(i2 % dataList.size());
            }
        });
        if (this.rxManager == null) {
            this.rxManager = new RxManager();
        }
        getNode();
        checkSamsungSDKEnable();
        checkMigrateEnabled();
        AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.ENTER_START_PAGE);
        if (TextUtils.equals(getIntent().getAction(), PullActivity.ACTION)) {
            PullActivity.showDataErrorPopup(this, new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    EmptyWalletActivity.lambda$processData$0(view);
                }
            });
        } else if (getIntent().getClipData() != null && getIntent().getData() != null) {
            String dataString = getIntent().getDataString();
            String uri = getIntent().getClipData().getItemAt(0).getUri().toString();
            if (!TextUtils.isEmpty(dataString) && !TextUtils.isEmpty(uri)) {
                MigrateProgressDialog.showUp(this, Uri.parse(dataString), Uri.parse(uri), new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        lambda$processData$1((Boolean) obj);
                    }
                });
            }
        }
        Collection.-EL.stream(getClickableViews()).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$2((View) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        this.coldView.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(final View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.CLICK_START_PAGE_COLD_PAIR_WALLET);
                OnSafeChannelPopClickListener onSafeChannelPopClickListener = new OnSafeChannelPopClickListener() {
                    @Override
                    public void confirmClick() {
                        if (SpAPI.THIS.hasReadUserAgreement()) {
                            PairColdWalletActivity.start(view.getContext(), 0);
                            return;
                        }
                        Intent intent = new Intent(view.getContext(), UserPrivacyAgreementActivity.class);
                        intent.putExtra("go", 9);
                        startActivity(intent);
                    }
                };
                if (SpAPI.THIS.hasShowTheSafeChannelPop()) {
                    onSafeChannelPopClickListener.confirmClick();
                } else {
                    showSafetyPopWindow(onSafeChannelPopClickListener);
                }
            }
        });
        this.rxManager.on(Event.SDK_FINISH_CREATE_WALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$3(obj);
            }
        });
        this.rxManager.on(Event.JumpToMain, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$4(obj);
            }
        });
    }

    public void lambda$processData$1(Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            Intent intent = new Intent(this, MainTabActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(32768);
            startActivity(intent);
            finish();
        }
    }

    public void lambda$processData$2(View view) {
        view.setOnClickListener(getOnClickListener());
    }

    public void lambda$processData$3(Object obj) throws Exception {
        finish();
    }

    public void lambda$processData$4(Object obj) throws Exception {
        if (isFinishing() && isDestroyed()) {
            return;
        }
        finish();
    }

    private void checkMigrateEnabled() {
        if (TextUtils.equals("com.tronlinkpro.wallet", MigrateConfig.APP_ID_GLOBAL)) {
            this.migrateView.setVisibility(View.VISIBLE);
            this.migrateView.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    go(MigrateActivity.class);
                    AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.CLICK_START_PAGE_MIGRATE_WALLET);
                }
            });
        }
    }

    private void checkSamsungSDKEnable() {
        if (SamsungSDKWrapper.isSupportSamsungSdk()) {
            this.rlSamsung.setVisibility(View.VISIBLE);
        } else {
            this.rlSamsung.setVisibility(View.GONE);
        }
    }

    public boolean getNet() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                StatusBarUtils.setLightStatusBar(EmptyWalletActivity.this, true);
            }
        });
    }

    public void change(int i) {
        if (i == 0) {
            this.ivIndicator.setBackgroundResource(this.isCold ? R.mipmap.guide_indicator_cold_1 : R.mipmap.guide_indicator_hd_1);
            this.tvIndicator1.setTextColor(this.isCold ? getResources().getColor(R.color.white) : getResources().getColor(R.color.black_23));
            TextView textView = this.tvIndicator2;
            boolean z = this.isCold;
            textView.setTextColor(getResources().getColor(R.color.gray_9B));
            TextView textView2 = this.tvIndicator3;
            boolean z2 = this.isCold;
            textView2.setTextColor(getResources().getColor(R.color.gray_9B));
            this.tvIndicator4.setTextColor(getResources().getColor(R.color.gray_9B));
        } else if (i == 1) {
            this.ivIndicator.setBackgroundResource(this.isCold ? R.mipmap.guide_indicator_cold_2 : R.mipmap.guide_indicator_hd_2);
            TextView textView3 = this.tvIndicator1;
            boolean z3 = this.isCold;
            textView3.setTextColor(getResources().getColor(R.color.gray_9B));
            this.tvIndicator2.setTextColor(this.isCold ? getResources().getColor(R.color.white) : getResources().getColor(R.color.black_23));
            TextView textView4 = this.tvIndicator3;
            boolean z4 = this.isCold;
            textView4.setTextColor(getResources().getColor(R.color.gray_9B));
            this.tvIndicator4.setTextColor(getResources().getColor(R.color.gray_9B));
        } else if (i == 2) {
            this.ivIndicator.setBackgroundResource(this.isCold ? R.mipmap.guide_indicator_cold_3 : R.mipmap.guide_indicator_hd_3);
            TextView textView5 = this.tvIndicator1;
            boolean z5 = this.isCold;
            textView5.setTextColor(getResources().getColor(R.color.gray_9B));
            TextView textView6 = this.tvIndicator2;
            boolean z6 = this.isCold;
            textView6.setTextColor(getResources().getColor(R.color.gray_9B));
            this.tvIndicator3.setTextColor(this.isCold ? getResources().getColor(R.color.white) : getResources().getColor(R.color.black_23));
            this.tvIndicator4.setTextColor(getResources().getColor(R.color.gray_9B));
        } else if (i != 3) {
        } else {
            this.ivIndicator.setBackgroundResource(this.isCold ? R.mipmap.guide_indicator_cold_4 : R.mipmap.guide_indicator_hd_4);
            TextView textView7 = this.tvIndicator1;
            boolean z7 = this.isCold;
            textView7.setTextColor(getResources().getColor(R.color.gray_9B));
            TextView textView8 = this.tvIndicator2;
            boolean z8 = this.isCold;
            textView8.setTextColor(getResources().getColor(R.color.gray_9B));
            this.tvIndicator3.setTextColor(getResources().getColor(R.color.gray_9B));
            this.tvIndicator4.setTextColor(this.isCold ? getResources().getColor(R.color.white) : getResources().getColor(R.color.black_23));
        }
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    private List<View> getClickableViews() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.rlSwitch);
        arrayList.add(this.tvCreate);
        arrayList.add(this.tvImport);
        arrayList.add(this.mImportSamsungView);
        arrayList.add(this.binding.tvObservation);
        arrayList.add(this.tvLedger);
        return arrayList;
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.rl_switch:
                        if (isCold) {
                            EmptyWalletActivity emptyWalletActivity = EmptyWalletActivity.this;
                            emptyWalletActivity.switchWalletType(!emptyWalletActivity.isCold);
                            return;
                        }
                        showAlertPopWindow();
                        return;
                    case R.id.tv_create:
                        AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.CLICK_START_PAGE_CREATE_WALLET);
                        OnSafeChannelPopClickListener onSafeChannelPopClickListener = new OnSafeChannelPopClickListener() {
                            @Override
                            public void confirmClick() {
                                if (!SpAPI.THIS.hasReadUserAgreement()) {
                                    Intent intent = new Intent(EmptyWalletActivity.this, UserPrivacyAgreementActivity.class);
                                    intent.putExtra("go", 1);
                                    intent.putExtra(TronConfig.EMPTY_CREATE_WALLET, true);
                                    go(intent);
                                    return;
                                }
                                go(CreateWalletActivity.class);
                            }
                        };
                        if (SpAPI.THIS.hasShowTheSafeChannelPop()) {
                            onSafeChannelPopClickListener.confirmClick();
                            return;
                        } else {
                            showSafetyPopWindow(onSafeChannelPopClickListener);
                            return;
                        }
                    case R.id.tv_import:
                        AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.CLICK_START_PAGE_IMPORT_WALLET);
                        OnSafeChannelPopClickListener onSafeChannelPopClickListener2 = new OnSafeChannelPopClickListener() {
                            @Override
                            public void confirmClick() {
                                if (!SpAPI.THIS.hasReadUserAgreement()) {
                                    Intent intent = new Intent(EmptyWalletActivity.this, UserPrivacyAgreementActivity.class);
                                    intent.putExtra("go", 2);
                                    go(intent);
                                    return;
                                }
                                ImportWalletActivity.start(EmptyWalletActivity.this, false);
                            }
                        };
                        if (SpAPI.THIS.hasShowTheSafeChannelPop()) {
                            onSafeChannelPopClickListener2.confirmClick();
                            return;
                        } else {
                            showSafetyPopWindow(onSafeChannelPopClickListener2);
                            return;
                        }
                    case R.id.tv_import_samsung:
                        AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.CLICK_START_PAGE_SAMSUNG_WALLET);
                        SpAPI.THIS.setCold(false);
                        OnSafeChannelPopClickListener onSafeChannelPopClickListener3 = new OnSafeChannelPopClickListener() {
                            @Override
                            public void confirmClick() {
                                if (!SpAPI.THIS.hasReadUserAgreement()) {
                                    Intent intent = new Intent(EmptyWalletActivity.this, UserPrivacyAgreementActivity.class);
                                    intent.putExtra("go", 7);
                                    go(intent);
                                    return;
                                }
                                mImportSamsungView.setEnabled(false);
                                if (TextUtils.isEmpty(SamsungSDKWrapper.checkSeedHashEmpty(EmptyWalletActivity.this, false))) {
                                    showGotoSamSungWallet();
                                } else {
                                    importSamsungWallet();
                                }
                            }
                        };
                        if (SpAPI.THIS.hasShowTheSafeChannelPop()) {
                            onSafeChannelPopClickListener3.confirmClick();
                            return;
                        } else {
                            showSafetyPopWindow(onSafeChannelPopClickListener3);
                            return;
                        }
                    case R.id.tv_ledger:
                        AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.CLICK_START_PAGE_LEDGER_WALLET);
                        OnSafeChannelPopClickListener onSafeChannelPopClickListener4 = new OnSafeChannelPopClickListener() {
                            @Override
                            public void confirmClick() {
                                if (!SpAPI.THIS.hasReadUserAgreement()) {
                                    Intent intent = new Intent(EmptyWalletActivity.this, UserPrivacyAgreementActivity.class);
                                    intent.putExtra("go", 8);
                                    go(intent);
                                    return;
                                }
                                SearchLedgerActivity.start(EmptyWalletActivity.this);
                            }
                        };
                        if (SpAPI.THIS.hasShowTheSafeChannelPop()) {
                            onSafeChannelPopClickListener4.confirmClick();
                            return;
                        } else {
                            showSafetyPopWindow(onSafeChannelPopClickListener4);
                            return;
                        }
                    case R.id.tv_observation:
                        AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.CLICK_START_PAGE_WATCH_WALLET);
                        OnSafeChannelPopClickListener onSafeChannelPopClickListener5 = new OnSafeChannelPopClickListener() {
                            @Override
                            public void confirmClick() {
                                if (!SpAPI.THIS.hasReadUserAgreement()) {
                                    Intent intent = new Intent(EmptyWalletActivity.this, UserPrivacyAgreementActivity.class);
                                    intent.putExtra("go", 4);
                                    go(intent);
                                    return;
                                }
                                go(AddWatchWalletActivity.class);
                            }
                        };
                        if (SpAPI.THIS.hasShowTheSafeChannelPop()) {
                            onSafeChannelPopClickListener5.confirmClick();
                            return;
                        } else {
                            showSafetyPopWindow(onSafeChannelPopClickListener5);
                            return;
                        }
                    default:
                        return;
                }
            }
        };
    }

    public void importSamsungWallet() {
        SamsungSDKWrapper.importSamsungWallet(this, new SamsungSDKWrapper.getAddressCallBack() {
            @Override
            public void onSuccess(String str, String str2) {
                Wallet wallet;
                setSamsungViewEnable(true);
                for (String str3 : WalletUtils.getWalletNames()) {
                    if (!TextUtils.isEmpty(str3) && (wallet = WalletUtils.getWallet(str3)) != null && wallet.getAddress().equals(str2)) {
                        RxBus.getInstance().post(Event.SELECTEDWALLET, str3);
                        WalletUtils.setSelectedWallet(str3);
                        runOnUIThread(new OnMainThread() {
                            @Override
                            public void doInUIThread() {
                                toast(getString(R.string.samsung_wallet_existed));
                            }
                        });
                        return;
                    }
                }
                ImportSamsungActivity.start(EmptyWalletActivity.this, str2);
            }

            @Override
            public void onFailure(String str) {
                setSamsungViewEnable(true);
                IToast.getIToast().setImage(R.mipmap.broadcast_fail).show(getString(R.string.sx_import_fail));
            }
        });
    }

    public void setSamsungViewEnable(final boolean z) {
        runOnUIThread(new OnMainThread() {
            @Override
            public void doInUIThread() {
                mImportSamsungView.setEnabled(z);
            }
        });
    }

    public void showGotoSamSungWallet() {
        final Common2Dialog common2Dialog = new Common2Dialog(this.mContext);
        common2Dialog.setTitle(getString(R.string.create_sumsung_keystore)).setInnerTitle(getString(R.string.keystore_not_exist_to_create)).setBtListener(getString(R.string.ok2), new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showGotoSamSungWallet$5(common2Dialog, view);
            }
        }).setCancleBt(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showGotoSamSungWallet$6(common2Dialog, view);
            }
        });
        common2Dialog.show();
    }

    public void lambda$showGotoSamSungWallet$5(Common2Dialog common2Dialog, View view) {
        common2Dialog.dismiss();
        setSamsungViewEnable(true);
        SamsungSDKWrapper.getoSamsungKeystoreWallet(this, 16);
    }

    public void lambda$showGotoSamSungWallet$6(Common2Dialog common2Dialog, View view) {
        common2Dialog.dismiss();
        setSamsungViewEnable(true);
    }

    private void getNode() {
        try {
            List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
            if (allChainJson == null || allChainJson.size() == 0) {
                NodeUtils.nodeRequest();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
        this.binding = null;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (this.isJumpToSamSungWallet) {
            if (!TextUtils.isEmpty(SamsungSDKWrapper.checkSeedHashEmpty(this, false))) {
                importSamsungWallet();
            }
            this.isJumpToSamSungWallet = false;
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 16) {
            this.isJumpToSamSungWallet = true;
        }
    }

    public void switchWalletType(final boolean z) {
        LogUtils.e("switchWalletType", "" + this.vpContent.getCurrentItem());
        SpAPI.THIS.setCold(z);
        this.isCold = z;
        Collection.-EL.stream(this.dataList).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                ((SimpleGuideFragment) obj).setCold(z);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        if (this.isCold) {
            StatusBarUtils.setLightStatusBar(this, false);
            this.tvSwitch.setTextColor(getResources().getColor(R.color.white));
            this.tvSwitch.setText(getResources().getString(R.string.switch_to_hd_wallet));
            Drawable drawable = getResources().getDrawable(R.mipmap.guide_wallet_switch_cold);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.tvSwitch.setCompoundDrawables(null, null, drawable, null);
            this.root.setBackgroundResource(R.color.black_23);
            this.rlContent.setBackgroundResource(R.mipmap.guide_bg_cold);
            this.ivHandBg.setBackgroundResource(R.mipmap.guide_hand_cold);
            change(this.vpContent.getCurrentItem());
            this.rlCreate.setBackgroundResource(R.drawable.bg_guide_create_cold);
            this.rlImport.setBackgroundResource(R.drawable.bg_guide_import_cold);
            this.tvCreate.setTextColor(getResources().getColor(R.color.white));
            Drawable drawable2 = getResources().getDrawable(R.mipmap.guide_create_cold);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            this.tvCreate.setCompoundDrawables(drawable2, null, null, null);
            this.tvImport.setTextColor(getResources().getColor(R.color.black_23));
            Drawable drawable3 = getResources().getDrawable(R.mipmap.guide_import_cold);
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
            this.tvImport.setCompoundDrawables(drawable3, null, null, null);
            this.rlBottom.setBackgroundResource(R.mipmap.guide_bg_bottom_cold);
            this.OtherLayout.setVisibility(View.GONE);
            this.viewPagerAdapter.notifyDataSetChanged();
            return;
        }
        StatusBarUtils.setLightStatusBar(this, true);
        this.tvSwitch.setTextColor(getResources().getColor(R.color.black_23));
        this.tvSwitch.setText(getResources().getString(R.string.switch_to_cold_wallet));
        Drawable drawable4 = getResources().getDrawable(R.mipmap.guide_wallet_switch_hd);
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
        this.tvSwitch.setCompoundDrawables(null, null, drawable4, null);
        this.root.setBackgroundResource(R.color.gray_F7F);
        this.rlContent.setBackgroundResource(R.mipmap.guide_bg);
        this.ivHandBg.setBackgroundResource(R.mipmap.guide_hand_hd);
        change(this.vpContent.getCurrentItem());
        this.rlCreate.setBackgroundResource(R.drawable.bg_guide_create);
        this.rlImport.setBackgroundResource(R.drawable.bg_guide_import);
        this.tvCreate.setTextColor(getResources().getColor(R.color.black_23));
        Drawable drawable5 = getResources().getDrawable(R.mipmap.guide_create_hd);
        drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable5.getMinimumHeight());
        this.tvCreate.setCompoundDrawables(drawable5, null, null, null);
        this.tvImport.setTextColor(getResources().getColor(R.color.white));
        Drawable drawable6 = getResources().getDrawable(R.mipmap.guide_import_hd);
        drawable6.setBounds(0, 0, drawable6.getMinimumWidth(), drawable6.getMinimumHeight());
        this.tvImport.setCompoundDrawables(drawable6, null, null, null);
        this.rlBottom.setBackgroundResource(R.mipmap.guide_bg_bottom);
        this.OtherLayout.setVisibility(View.VISIBLE);
        this.viewPagerAdapter.notifyDataSetChanged();
    }

    public void showAlertPopWindow() {
        BasePopupView asCustom = new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(this) {
            @Override
            public int getImplLayoutId() {
                return R.layout.cold_wallet_dialog;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                TextView textView = (TextView) findViewById(R.id.tv_cancle);
                Button button = (Button) findViewById(R.id.btn_confirm);
                tvKnow = (Button) findViewById(R.id.btn_know);
                tvNote = (TextView) findViewById(R.id.tv_note_notice);
                llCold = (LinearLayout) findViewById(R.id.ll_cold);
                if (!getNet()) {
                    tvNote.setVisibility(View.GONE);
                    llCold.setVisibility(View.VISIBLE);
                    tvKnow.setVisibility(View.GONE);
                } else {
                    tvNote.setVisibility(View.VISIBLE);
                    llCold.setVisibility(View.GONE);
                    tvKnow.setVisibility(View.VISIBLE);
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.CLICK_START_PAGE_COLD_WALLET);
                        SpAPI.THIS.setCold(true);
                        switchWalletType(true);
                    }
                });
                tvKnow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
            }
        });
        this.popupView = asCustom;
        asCustom.show();
    }

    void showSafetyPopWindow(final OnSafeChannelPopClickListener onSafeChannelPopClickListener) {
        BasePopupView asCustom = new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(false).dismissOnBackPressed(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(this) {
            @Override
            public int getImplLayoutId() {
                return R.layout.safety_install_channel_popup_view;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                ((Button) findViewById(R.id.btn_confirm)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SpAPI.THIS.setShowTheSafeChannelPop();
                        onSafeChannelPopClickListener.confirmClick();
                        dismiss();
                    }
                });
            }
        });
        this.safetyPopView = asCustom;
        asCustom.show();
    }
}
