package com.tron.wallet.business.tabmy.dealsign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.tabmy.dealsign.DealSignContract;
import com.tron.wallet.common.components.BadgeButton;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.AcDealSignBinding;
import com.tronlinkpro.wallet.R;
public class DealSignActivity extends BaseActivity<DealSignPresenter, EmptyModel> implements DealSignContract.View {
    public static final String IS_FROM_DETAIL = "is_fromdetail";
    private AcDealSignBinding binding;
    FrameLayout content;
    private String currentWalletName;
    LinearLayout llTabCount;
    BadgeButton redView;
    RelativeLayout signAlready;
    RelativeLayout signFailure;
    RelativeLayout signSuccess;
    RelativeLayout signWait;
    TextView tvSignAlready;
    TextView tvSignFailure;
    TextView tvSignSuccess;
    TextView tvSignWait;
    private boolean hasSocketConnect = false;
    private boolean isFromDetail = false;
    private int mIndex = 0;

    @Override
    public boolean getSocketConnect() {
        return this.hasSocketConnect;
    }

    @Override
    public String getWalletName() {
        return this.currentWalletName;
    }

    @Override
    public void setSocketConnect(boolean z) {
        this.hasSocketConnect = z;
    }

    @Override
    protected void setLayout() {
        AcDealSignBinding inflate = AcDealSignBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.deal_sign));
        if (getIntent() != null) {
            this.hasSocketConnect = getIntent().getBooleanExtra(TronConfig.socket_state, false);
            this.currentWalletName = getIntent().getStringExtra(TronConfig.deal_wallet_name);
            this.isFromDetail = getIntent().getBooleanExtra(IS_FROM_DETAIL, false);
            this.mIndex = getIntent().getIntExtra(TronConfig.deal_wallet_index, 0);
        }
    }

    public void mappingId() {
        this.tvSignWait = this.binding.tvSignWait;
        this.redView = this.binding.redView;
        this.signWait = this.binding.signWait;
        this.tvSignAlready = this.binding.tvSignAlready;
        this.signAlready = this.binding.signAlready;
        this.tvSignSuccess = this.binding.tvSignSuccess;
        this.signSuccess = this.binding.signSuccess;
        this.tvSignFailure = this.binding.tvSignFailure;
        this.signFailure = this.binding.signFailure;
        this.content = this.binding.content;
        this.llTabCount = this.binding.llTabCount;
    }

    @Override
    protected void processData() {
        showOrHideWaitSignCount(0);
        ((DealSignPresenter) this.mPresenter).addSome(getSupportFragmentManager());
        resetTabState();
        initTabClick(this.mIndex);
        ((DealSignPresenter) this.mPresenter).onTabClick(this.mIndex);
        AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.ENTER_WALLET_MANAGER_MULTI_SIGN_PAGE);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        openMain();
        finish();
    }

    private void openMain() {
        if (this.isFromDetail) {
            return;
        }
        Intent intent = new Intent(this, MainTabActivity.class);
        intent.addFlags(67108864);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            openMain();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        ((DealSignPresenter) this.mPresenter).onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle, persistableBundle);
    }

    @Override
    public void onCreate2(Bundle bundle) {
        super.onCreate2(bundle);
        ((DealSignPresenter) this.mPresenter).onCreate2(bundle, getSupportFragmentManager());
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_already:
                        resetTabState();
                        tvSignAlready.setTextColor(getResources().getColor(R.color.white));
                        tvSignAlready.setBackgroundResource(R.drawable.selector_bg_black);
                        ((DealSignPresenter) mPresenter).onTabClick(1);
                        return;
                    case R.id.sign_failure:
                        resetTabState();
                        tvSignFailure.setTextColor(getResources().getColor(R.color.white));
                        tvSignFailure.setBackgroundResource(R.drawable.selector_bg_black);
                        ((DealSignPresenter) mPresenter).onTabClick(3);
                        return;
                    case R.id.sign_ok:
                    case R.id.sign_state:
                    default:
                        return;
                    case R.id.sign_success:
                        resetTabState();
                        tvSignSuccess.setTextColor(getResources().getColor(R.color.white));
                        tvSignSuccess.setBackgroundResource(R.drawable.selector_bg_black);
                        ((DealSignPresenter) mPresenter).onTabClick(2);
                        return;
                    case R.id.sign_wait:
                        resetTabState();
                        tvSignWait.setTextColor(getResources().getColor(R.color.white));
                        tvSignWait.setBackgroundResource(R.drawable.selector_bg_black);
                        ((DealSignPresenter) mPresenter).onTabClick(0);
                        return;
                }
            }
        };
        this.binding.signWait.setOnClickListener(noDoubleClickListener2);
        this.binding.signAlready.setOnClickListener(noDoubleClickListener2);
        this.binding.signSuccess.setOnClickListener(noDoubleClickListener2);
        this.binding.signFailure.setOnClickListener(noDoubleClickListener2);
    }

    public void resetTabState() {
        this.tvSignWait.setTextColor(getResources().getColor(R.color.black_02_30));
        this.tvSignAlready.setTextColor(getResources().getColor(R.color.black_02_30));
        this.tvSignSuccess.setTextColor(getResources().getColor(R.color.black_02_30));
        this.tvSignFailure.setTextColor(getResources().getColor(R.color.black_02_30));
        this.tvSignWait.setBackgroundResource(R.drawable.roundborder_transparent);
        this.tvSignAlready.setBackgroundResource(R.drawable.roundborder_transparent);
        this.tvSignSuccess.setBackgroundResource(R.drawable.roundborder_transparent);
        this.tvSignFailure.setBackgroundResource(R.drawable.roundborder_transparent);
    }

    public void initTabClick(int i) {
        if (i == 0) {
            this.tvSignWait.setTextColor(getResources().getColor(R.color.white));
            this.tvSignWait.setBackgroundResource(R.drawable.selector_bg_black);
        } else if (i == 1) {
            this.tvSignAlready.setTextColor(getResources().getColor(R.color.white));
            this.tvSignAlready.setBackgroundResource(R.drawable.selector_bg_black);
        } else if (i == 2) {
            this.tvSignSuccess.setTextColor(getResources().getColor(R.color.white));
            this.tvSignSuccess.setBackgroundResource(R.drawable.selector_bg_black);
        } else if (i != 3) {
        } else {
            this.tvSignFailure.setTextColor(getResources().getColor(R.color.white));
            this.tvSignFailure.setBackgroundResource(R.drawable.selector_bg_black);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            this.hasSocketConnect = intent.getBooleanExtra(TronConfig.socket_state, false);
            this.currentWalletName = intent.getStringExtra(TronConfig.deal_wallet_name);
            this.isFromDetail = intent.getBooleanExtra(IS_FROM_DETAIL, false);
            int intExtra = intent.getIntExtra(TronConfig.deal_wallet_index, -1);
            this.mIndex = intExtra;
            if (intExtra >= 0) {
                resetTabState();
                initTabClick(this.mIndex);
                ((DealSignPresenter) this.mPresenter).onTabClick(this.mIndex);
                return;
            }
            resetTabState();
            initTabClick(1);
            ((DealSignPresenter) this.mPresenter).onTabClick(1);
        }
    }

    public static void start(Context context, String str, boolean z, boolean z2) {
        Intent intent = new Intent(context, DealSignActivity.class);
        intent.putExtra(TronConfig.socket_state, z);
        intent.putExtra(TronConfig.deal_wallet_name, str);
        intent.putExtra(IS_FROM_DETAIL, z2);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, boolean z) {
        start(context, str, z, false);
    }

    public static void start(Context context, String str, boolean z, int i) {
        Intent intent = new Intent(context, DealSignActivity.class);
        intent.putExtra(TronConfig.socket_state, z);
        intent.putExtra(TronConfig.deal_wallet_name, str);
        intent.putExtra(TronConfig.deal_wallet_index, i);
        context.startActivity(intent);
    }

    @Override
    public void showOrHideWaitSignCount(int i) {
        if (i > 0) {
            this.redView.setVisibility(View.VISIBLE);
            this.llTabCount.setVisibility(View.VISIBLE);
            BadgeButton badgeButton = this.redView;
            badgeButton.setBadgeTextDef(i + "");
            return;
        }
        this.redView.setVisibility(View.GONE);
        this.llTabCount.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            TronConfig.openList.clear();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.binding = null;
    }
}
