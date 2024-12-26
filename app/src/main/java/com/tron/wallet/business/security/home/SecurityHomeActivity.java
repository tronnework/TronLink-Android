package com.tron.wallet.business.security.home;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.security.ExecuteStatusEnum;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.business.security.SecurityResult;
import com.tron.wallet.business.security.approvecheck.ApproveCheckActivity;
import com.tron.wallet.business.security.approvecheck.ApproveCheckModel;
import com.tron.wallet.business.security.check.ApproveCheck;
import com.tron.wallet.business.security.check.EnvironmentCheck;
import com.tron.wallet.business.security.check.TokenRiskCheck;
import com.tron.wallet.business.security.components.HomeResource;
import com.tron.wallet.business.security.components.SecurityItemView;
import com.tron.wallet.business.security.environment.SecurityEnvironmentActivity;
import com.tron.wallet.business.security.interfaces.SecurityResultInterface;
import com.tron.wallet.business.security.tokencheck.TokenCheckActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcSecurityHomeBinding;
import com.tron.wallet.databinding.ItemSecurityPage1Binding;
import com.tron.wallet.databinding.ItemSecurityPage2Binding;
import com.tron.wallet.databinding.ItemSecurityPage3Binding;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
public class SecurityHomeActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private RotateAnimation animation;
    SecurityItemView approveCheckClick;
    View approveCheckLoading;
    private SecurityResult approveCheckResult;
    SecurityItemView approveCheckShow;
    private AcSecurityHomeBinding binding;
    View btnRestart;
    View btnStart;
    private int detectDoneNum;
    SecurityItemView environmentCheckClick;
    View environmentCheckLoading;
    private SecurityResult environmentCheckResult;
    SecurityItemView environmentCheckShow;
    private Handler handler;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;
    View ivCommonRight;
    ImageView ivLoading1;
    ImageView ivLoading2;
    ImageView ivLoading3;
    ImageView ivLoading4;
    ImageView ivLoading5;
    ImageView ivTopResultIcon;
    View llCommonLeft;
    private int riskNum;
    View rootPage1;
    View rootPage2;
    View rootPage3;
    SecurityItemView tokenCheckClick;
    View tokenCheckLoading;
    private SecurityResult tokenCheckResult;
    SecurityItemView tokenCheckShow;
    TextView tvRiskNumPage2;
    TextView tvRiskNumPage3;
    TextView tvRiskTopNumPage3;
    TextView tvRiskTopTextPage3;
    TextView tvUpdateTime;
    TextView tvWaringNumPage2;
    TextView tvWaringNumPage3;
    private int waringNum;
    private final String Tag = "SecurityHome-Tag";
    private EnvironmentCheck environmentCheck = new EnvironmentCheck();
    private TokenRiskCheck tokenRiskCheck = new TokenRiskCheck();
    private ApproveCheck approveCheck = new ApproveCheck();
    private HomeResource homeResource = new HomeResource();
    NoDoubleClickListener2 noDoubleClickListener = new NoDoubleClickListener2() {
        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.approve_check_click:
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityHomePage.SECURITY_HOME_CLICK_ITEM_3);
                    if (approveCheckResult == null || approveCheckResult.getExecuteStatusEnum() == ExecuteStatusEnum.Error) {
                        return;
                    }
                    intentActivityResultLauncher.launch(new Intent(mContext, ApproveCheckActivity.class));
                    return;
                case R.id.btn_restart:
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityHomePage.SECURITY_HOME_CLICK_CHECK_AGAIN);
                    start();
                    return;
                case R.id.btn_start:
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityHomePage.SECURITY_HOME_CLICK_CHECK);
                    start();
                    return;
                case R.id.environment_check_click:
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityHomePage.SECURITY_HOME_CLICK_ITEM_1);
                    if (environmentCheckResult == null || environmentCheckResult.getExecuteStatusEnum() == ExecuteStatusEnum.Error) {
                        return;
                    }
                    SecurityEnvironmentActivity.start(mContext, environmentCheckResult);
                    return;
                case R.id.iv_common_right:
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityHomePage.SECURITY_HOME_CLICK_ASK);
                    CommonWebActivityV3.start(mContext, IRequest.getSecurityCheckUrl(), "", -2, false);
                    return;
                case R.id.ll_common_left:
                    exit();
                    return;
                case R.id.token_check_click:
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityHomePage.SECURITY_HOME_CLICK_ITEM_2);
                    if (tokenCheckResult == null || tokenCheckResult.getExecuteStatusEnum() == ExecuteStatusEnum.Error) {
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setClass(mContext, TokenCheckActivity.class);
                    intent.putExtra(TokenCheckActivity.TOKEN_CHECK_RESULT, tokenCheckResult);
                    intentActivityResultLauncher.launch(intent);
                    return;
                default:
                    return;
            }
        }
    };

    public enum Page {
        Page1,
        Page2,
        Page3
    }

    private void resetNum() {
        this.detectDoneNum = 0;
        this.riskNum = 0;
        this.waringNum = 0;
    }

    public void start() {
        resetNum();
        startAnimation();
        showPage(Page.Page2);
        Handler handler = this.handler;
        if (handler != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    lambda$start$0();
                }
            }, 2000L);
        }
    }

    public void lambda$start$0() {
        this.environmentCheck.securityStart();
    }

    @Override
    protected void setLayout() {
        AcSecurityHomeBinding inflate = AcSecurityHomeBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
        setBtnClick();
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.SecurityHomePage.SECURITY_HOME_SHOW);
        this.handler = new Handler();
        this.detectDoneNum = 0;
        showPage(Page.Page1);
        this.environmentCheck.setSecurityResultInterface(new SecurityResultInterface() {
            @Override
            public final void onThreadReturnResult(SecurityResult securityResult) {
                lambda$processData$1(securityResult);
            }
        });
        this.tokenRiskCheck.setSecurityResultInterface(new SecurityResultInterface() {
            @Override
            public final void onThreadReturnResult(SecurityResult securityResult) {
                lambda$processData$2(securityResult);
            }
        });
        this.approveCheck.setSecurityResultInterface(new SecurityResultInterface() {
            @Override
            public final void onThreadReturnResult(SecurityResult securityResult) {
                lambda$processData$3(securityResult);
            }
        });
        this.intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {
            @Override
            public final void onActivityResult(Object obj) {
                lambda$processData$4((ActivityResult) obj);
            }
        });
        getAllAddress();
    }

    public void lambda$processData$1(SecurityResult securityResult) {
        this.environmentCheckResult = securityResult;
        resultProcessing(securityResult, SecurityItemView.HomeItem.Environment);
        this.tokenRiskCheck.securityStart();
    }

    public void lambda$processData$2(SecurityResult securityResult) {
        this.tokenCheckResult = securityResult;
        resultProcessing(securityResult, SecurityItemView.HomeItem.Token);
        this.approveCheck.securityStart();
    }

    public void lambda$processData$3(SecurityResult securityResult) {
        this.approveCheckResult = securityResult;
        resultProcessing(securityResult, SecurityItemView.HomeItem.Approve);
    }

    public void lambda$processData$4(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            try {
                Serializable serializableExtra = activityResult.getData().getSerializableExtra(TokenCheckActivity.TOKEN_CHECK_RESULT);
                if (serializableExtra != null) {
                    SecurityResult securityResult = (SecurityResult) serializableExtra;
                    this.tokenCheckResult = securityResult;
                    LogUtils.i("registerForActivityResult", securityResult.toString());
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    private void getAllAddress() {
        new ApproveCheckModel().getAllAddress().compose(RxSchedulers.io_main()).subscribe(new Consumer<HashMap<String, NameAddressExtraBean>>() {
            @Override
            public void accept(HashMap<String, NameAddressExtraBean> hashMap) throws Exception {
                setAddressMap(hashMap);
            }
        });
    }

    public void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap) {
        if (hashMap != null) {
            AddressMapUtils.getInstance().setAddressMap(hashMap);
        }
    }

    private synchronized void resultProcessing(final SecurityResult securityResult, final SecurityItemView.HomeItem homeItem) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$resultProcessing$5(securityResult, homeItem);
            }
        });
    }

    public void lambda$resultProcessing$5(SecurityResult securityResult, SecurityItemView.HomeItem homeItem) {
        if (securityResult == null) {
            return;
        }
        LogUtils.i("SecurityHome-Tag", "resultProcessing:" + homeItem);
        ExecuteStatusEnum executeStatusEnum = securityResult.getExecuteStatusEnum();
        this.detectDoneNum = this.detectDoneNum + 1;
        if (executeStatusEnum == ExecuteStatusEnum.Error) {
            updateHomeText(ResultStatusEnum.Error, homeItem, this.homeResource, securityResult.getRiskNum());
        } else {
            this.riskNum += securityResult.getRiskNum();
            this.waringNum += securityResult.getWaringNum();
            if (securityResult.getRiskNum() != 0) {
                updateHomeText(ResultStatusEnum.Risk, homeItem, this.homeResource, securityResult.getRiskNum());
            } else if (securityResult.getWaringNum() != 0) {
                updateHomeText(ResultStatusEnum.Waring, homeItem, this.homeResource, securityResult.getWaringNum());
            } else {
                updateHomeText(ResultStatusEnum.Safe, homeItem, this.homeResource, 0);
            }
        }
        if (this.detectDoneNum >= 3) {
            updateTopViewNum();
            this.detectDoneNum = 0;
            showPage(Page.Page3);
            stopAnimation();
        }
    }

    private void updateHomeText(ResultStatusEnum resultStatusEnum, SecurityItemView.HomeItem homeItem, HomeResource homeResource, int i) {
        int i2 = fun3.$SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem[homeItem.ordinal()];
        if (i2 == 1) {
            this.environmentCheckShow.updateHomeText(resultStatusEnum, homeItem, homeResource, i);
            this.environmentCheckClick.updateHomeText(resultStatusEnum, homeItem, homeResource, i);
            UIUtils.showOrHidePage(this.environmentCheckShow, this.environmentCheckLoading);
        } else if (i2 == 2) {
            this.tokenCheckShow.updateHomeText(resultStatusEnum, homeItem, homeResource, i);
            this.tokenCheckClick.updateHomeText(resultStatusEnum, homeItem, homeResource, i);
            UIUtils.showOrHidePage(this.tokenCheckShow, this.tokenCheckLoading);
        } else if (i2 != 3) {
        } else {
            this.approveCheckShow.updateHomeText(resultStatusEnum, homeItem, homeResource, i);
            this.approveCheckClick.updateHomeText(resultStatusEnum, homeItem, homeResource, i);
            UIUtils.showOrHidePage(this.approveCheckShow, this.approveCheckLoading);
        }
    }

    public void updateTopViewNum() {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateTopViewNum$6();
            }
        });
    }

    public void lambda$updateTopViewNum$6() {
        int i = this.riskNum;
        if (i == 0 && this.waringNum == 0) {
            this.ivTopResultIcon.setImageResource(R.mipmap.icon_security_safe_big);
            this.tvRiskTopNumPage3.setTextColor(getResources().getColor(R.color.green_57));
            this.tvRiskTopTextPage3.setTextColor(getResources().getColor(R.color.green_57));
            this.tvRiskTopNumPage3.setText("0");
            this.tvRiskTopTextPage3.setText(getResources().getString(R.string.security_home_risky_items));
        } else if (i != 0) {
            this.ivTopResultIcon.setImageResource(R.mipmap.icon_security_risk_big);
            this.tvRiskTopNumPage3.setTextColor(getResources().getColor(R.color.red_EC));
            this.tvRiskTopTextPage3.setTextColor(getResources().getColor(R.color.red_EC));
            this.tvRiskTopNumPage3.setText(String.valueOf(this.riskNum));
            this.tvRiskTopTextPage3.setText(getResources().getString(R.string.security_home_risky_items));
        } else if (this.waringNum != 0) {
            this.ivTopResultIcon.setImageResource(R.mipmap.icon_security_waring_big);
            this.tvRiskTopNumPage3.setTextColor(getResources().getColor(R.color.yellow_FFA928));
            this.tvRiskTopTextPage3.setTextColor(getResources().getColor(R.color.yellow_FFA928));
            this.tvRiskTopNumPage3.setText(String.valueOf(this.waringNum));
            this.tvRiskTopTextPage3.setText(getResources().getString(R.string.security_home_attention_items));
        }
        this.tvRiskNumPage2.setText(String.valueOf(this.riskNum));
        this.tvWaringNumPage2.setText(String.valueOf(this.waringNum));
        this.tvRiskNumPage3.setText(String.valueOf(this.riskNum));
        this.tvWaringNumPage3.setText(String.valueOf(this.waringNum));
    }

    public static class fun3 {
        static final int[] $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem;
        static final int[] $SwitchMap$com$tron$wallet$business$security$home$SecurityHomeActivity$Page;

        static {
            int[] iArr = new int[Page.values().length];
            $SwitchMap$com$tron$wallet$business$security$home$SecurityHomeActivity$Page = iArr;
            try {
                iArr[Page.Page1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$home$SecurityHomeActivity$Page[Page.Page2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$home$SecurityHomeActivity$Page[Page.Page3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SecurityItemView.HomeItem.values().length];
            $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem = iArr2;
            try {
                iArr2[SecurityItemView.HomeItem.Environment.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem[SecurityItemView.HomeItem.Token.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem[SecurityItemView.HomeItem.Approve.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public void showPage(final Page page) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$showPage$7(page);
            }
        });
    }

    public void lambda$showPage$7(Page page) {
        int i = fun3.$SwitchMap$com$tron$wallet$business$security$home$SecurityHomeActivity$Page[page.ordinal()];
        if (i == 1) {
            UIUtils.showOrHidePage(this.rootPage1, this.rootPage2, this.rootPage3);
        } else if (i == 2) {
            UIUtils.showOrHidePage(this.rootPage2, this.rootPage1, this.rootPage3);
        } else if (i != 3) {
        } else {
            setTime();
            UIUtils.showOrHidePage(this.rootPage3, this.rootPage1, this.rootPage2);
        }
    }

    public void setTime() {
        String format = new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
        TextView textView = this.tvUpdateTime;
        textView.setText(getString(R.string.security_home_update_time) + " " + format);
    }

    public void startAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.animation = rotateAnimation;
        rotateAnimation.setFillAfter(true);
        this.animation.setInterpolator(new LinearInterpolator());
        this.animation.setDuration(1000L);
        this.animation.setRepeatCount(-1);
        UIUtils.showOrHidePage(this.environmentCheckLoading, this.environmentCheckShow);
        UIUtils.showOrHidePage(this.tokenCheckLoading, this.tokenCheckShow);
        UIUtils.showOrHidePage(this.approveCheckLoading, this.approveCheckShow);
        _startAnimation(this.ivLoading1, this.ivLoading2, this.ivLoading3, this.ivLoading4, this.ivLoading5);
    }

    public void stopAnimation() {
        _stopAnimation(this.ivLoading1, this.ivLoading2, this.ivLoading3, this.ivLoading4, this.ivLoading5);
    }

    private void _startAnimation(final ImageView... imageViewArr) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$_startAnimation$8(imageViewArr);
            }
        });
    }

    public void lambda$_startAnimation$8(ImageView[] imageViewArr) {
        for (ImageView imageView : imageViewArr) {
            imageView.startAnimation(this.animation);
        }
    }

    private void _stopAnimation(final ImageView... imageViewArr) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$_stopAnimation$9(imageViewArr);
            }
        });
    }

    public void lambda$_stopAnimation$9(ImageView[] imageViewArr) {
        for (ImageView imageView : imageViewArr) {
            this.animation.cancel();
            imageView.clearAnimation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
        this.environmentCheck.securityOnDestroy();
        this.tokenRiskCheck.securityOnDestroy();
        this.approveCheck.securityOnDestroy();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void mappingId() {
        this.rootPage1 = this.binding.getRoot().findViewById(R.id.root_page_1);
        this.rootPage2 = this.binding.getRoot().findViewById(R.id.root_page_2);
        this.rootPage3 = this.binding.getRoot().findViewById(R.id.root_page_3);
        ItemSecurityPage1Binding bind = ItemSecurityPage1Binding.bind(this.rootPage1);
        ItemSecurityPage2Binding bind2 = ItemSecurityPage2Binding.bind(this.rootPage2);
        ItemSecurityPage3Binding bind3 = ItemSecurityPage3Binding.bind(this.rootPage3);
        this.btnStart = bind.btnStart;
        this.btnRestart = bind3.btnRestart;
        this.llCommonLeft = this.binding.llCommonLeft;
        this.ivCommonRight = this.binding.ivCommonRight;
        this.ivLoading1 = bind2.ivLoading1;
        this.ivLoading2 = bind2.ivLoading2;
        this.ivLoading3 = bind2.ivLoading3;
        this.ivLoading4 = bind2.ivLoading4;
        this.ivLoading5 = bind2.ivLoading5;
        this.tvRiskNumPage2 = bind2.tvRiskNumPage2;
        this.tvWaringNumPage2 = bind2.tvWaringNumPage2;
        this.environmentCheckShow = bind2.environmentCheckShow;
        this.tokenCheckShow = bind2.tokenCheckShow;
        this.approveCheckShow = bind2.approveCheckShow;
        this.environmentCheckLoading = bind2.environmentCheckLoading;
        this.tokenCheckLoading = bind2.tokenCheckLoading;
        this.approveCheckLoading = bind2.approveCheckLoading;
        this.ivTopResultIcon = bind3.ivTopResultIcon;
        this.tvRiskNumPage3 = bind3.tvRiskNumPage3;
        this.tvWaringNumPage3 = bind3.tvWaringNumPage3;
        this.tvRiskTopNumPage3 = bind3.tvRiskTopNumPage3;
        this.tvRiskTopTextPage3 = bind3.tvRiskTopTextPage3;
        this.tvUpdateTime = bind3.tvUpdateTime;
        this.environmentCheckClick = bind3.environmentCheckClick;
        this.tokenCheckClick = bind3.tokenCheckClick;
        this.approveCheckClick = bind3.approveCheckClick;
    }

    public void setBtnClick() {
        this.llCommonLeft.setOnClickListener(this.noDoubleClickListener);
        this.ivCommonRight.setOnClickListener(this.noDoubleClickListener);
        this.btnStart.setOnClickListener(this.noDoubleClickListener);
        this.btnRestart.setOnClickListener(this.noDoubleClickListener);
        this.environmentCheckClick.setOnClickListener(this.noDoubleClickListener);
        this.tokenCheckClick.setOnClickListener(this.noDoubleClickListener);
        this.approveCheckClick.setOnClickListener(this.noDoubleClickListener);
    }
}
