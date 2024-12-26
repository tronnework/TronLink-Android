package com.tron.wallet.business.finance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.web.WebConstant;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.components.AndroidBug5497Workaround;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcWebBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tronlinkpro.wallet.R;
public class FinanceWebActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String INJECTTRONWEB_KEY = "INJECTTRONWEB_KEY";
    private static final String TAG = "FinanceWebActivity";
    public static final String TITLE_KEY = "TITLE_KEY";
    public static final String URL_KEY = "URL_KEY";
    public static final String WEBOPTIONS_KEY = "WEBOPTIONS_KEY";
    private AcWebBinding binding;
    private FinanceFragment financeFragment;
    private boolean injectTronweb;
    private String outSideTitle;
    RelativeLayout root;
    private String title;
    private WebOptions webOptions;
    private String weburl;

    public static void start(Context context, String str, String str2, boolean z, WebOptions webOptions) {
        Intent intent = new Intent(context, FinanceWebActivity.class);
        intent.putExtra("URL_KEY", str);
        intent.putExtra("TITLE_KEY", str2);
        intent.putExtra("INJECTTRONWEB_KEY", z);
        if (webOptions != null) {
            intent.putExtra("WEBOPTIONS_KEY", webOptions);
        }
        context.startActivity(intent);
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        WebOptions webOptions = (WebOptions) getIntent().getSerializableExtra("WEBOPTIONS_KEY");
        this.webOptions = webOptions;
        String screenModel = webOptions != null ? webOptions.getScreenModel() : "";
        screenModel.hashCode();
        if (screenModel.equals(WebConstant.SENSOR)) {
            setRequestedOrientation(4);
        } else if (screenModel.equals(WebConstant.LANDSCAPE)) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
    }

    @Override
    protected void setLayout() {
        AcWebBinding inflate = AcWebBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
        this.outSideTitle = getIntent().getStringExtra("TITLE_KEY");
        this.injectTronweb = getIntent().getBooleanExtra("INJECTTRONWEB_KEY", false);
        this.weburl = getIntent().getStringExtra("URL_KEY");
        if (StringTronUtil.isEmpty(this.outSideTitle)) {
            this.title = getString(R.string.loading_dapp);
        } else if (StringTronUtil.isEmpty(this.outSideTitle)) {
            this.title = "";
        } else {
            this.title = this.outSideTitle;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.root = this.binding.root;
    }

    @Override
    protected void processData() {
        AndroidBug5497Workaround.assistActivity(this);
        LogUtils.i(TAG, "url: " + this.weburl);
        new FinanceFragment();
        this.financeFragment = FinanceFragment.getInstance(this.title, this.weburl, true, this.webOptions);
        getSupportFragmentManager().beginTransaction().replace(R.id.web_view, this.financeFragment).commitAllowingStateLoss();
        if (LedgerWallet.isLedger(WalletUtils.getSelectedPublicWallet()) && this.webOptions.getDappOptions().isInjectZTron()) {
            ConfirmCustomPopupView.getBuilder(this).setTitle(getResources().getString(R.string.ledger_not_support_ztron_title)).setInfo(getResources().getString(R.string.ledger_not_support_ztron_des)).setConfirmText(getResources().getString(R.string.i_know_exit)).setShowCancelBtn(false).setShowTip(false).setAutoDismissOutSide(false).setPopupCallback(new XPopupCallback() {
                @Override
                public void beforeDismiss(BasePopupView basePopupView) {
                }

                @Override
                public void beforeShow(BasePopupView basePopupView) {
                }

                @Override
                public boolean onBackPressed(BasePopupView basePopupView) {
                    return false;
                }

                @Override
                public void onClickOutside(BasePopupView basePopupView) {
                }

                @Override
                public void onCreated(BasePopupView basePopupView) {
                }

                @Override
                public void onDismiss(BasePopupView basePopupView) {
                }

                @Override
                public void onDrag(BasePopupView basePopupView, int i, float f, boolean z) {
                }

                @Override
                public void onKeyBoardStateChanged(BasePopupView basePopupView, int i) {
                }

                @Override
                public void onShow(final BasePopupView basePopupView) {
                    basePopupView.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View view, int i, KeyEvent keyEvent) {
                            if (i == 4 && keyEvent.getAction() == 0) {
                                finish();
                                basePopupView.dismiss();
                                return true;
                            }
                            return false;
                        }
                    });
                }
            }).setConfirmListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$processData$0(view);
                }
            }).build().show();
        }
    }

    public void lambda$processData$0(View view) {
        finish();
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        FinanceFragment financeFragment;
        if (i == 4 && (financeFragment = this.financeFragment) != null && financeFragment.isPageFinished()) {
            this.financeFragment.handleBackKeyPressed();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        FinanceFragment financeFragment = this.financeFragment;
        if (financeFragment != null) {
            financeFragment.onActivityResult(i, i2, intent);
        }
    }
}
