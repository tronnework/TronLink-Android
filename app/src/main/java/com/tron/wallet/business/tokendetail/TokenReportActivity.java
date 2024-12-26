package com.tron.wallet.business.tokendetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.tokendetail.mvp.TokenReportContract;
import com.tron.wallet.business.tokendetail.mvp.TokenReportModel;
import com.tron.wallet.business.tokendetail.mvp.TokenReportPresenter;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcTokenReportBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
public class TokenReportActivity extends BaseActivity<TokenReportPresenter, TokenReportModel> implements TokenReportContract.View {
    public static final String TOKEN_LOGO = "TOKEN_LOGO";
    private AcTokenReportBinding binding;
    Button btnSubmit;
    Button btnToTronscan;
    RelativeLayout ckAirdrop;
    RelativeLayout ckApproveScam;
    RelativeLayout ckOther;
    RelativeLayout ckScam;
    RelativeLayout ckScamToken;
    RelativeLayout ckUnableTrade;
    EditText etContact;
    EditText etReportDesc;
    ImageView ivAirdrop;
    ImageView ivApproveScam;
    ImageView ivOther;
    ImageView ivScam;
    ImageView ivScamToken;
    SimpleDraweeView ivTokenTeportIcon;
    ImageView ivUnableTrade;
    private TokenBean mToken;
    private String tokenAddress;
    private String tokenSymbol;
    private int tokenType;
    TextView tvTokenReportName;
    boolean isAirdrop = false;
    boolean isScam = false;
    boolean isApproveScam = false;
    boolean isUnableTrade = false;
    boolean isScamToken = false;
    boolean isOther = false;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            finish();
        }
    };

    public static void start(Context context, TokenBean tokenBean, String str, int i, String str2) {
        Intent intent = new Intent(context, TokenReportActivity.class);
        intent.putExtra(TronConfig.TRC, tokenBean);
        intent.putExtra("address", str);
        intent.putExtra(AssetsConfig.TOKEN_TYPE, i);
        intent.putExtra(AssetsConfig.TOKEN_SYMBOL, str2);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    protected void setLayout() {
        AcTokenReportBinding inflate = AcTokenReportBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setHeaderBar(getString(R.string.token_report));
        mappingId();
        setClick();
    }

    @Override
    public void onLeftButtonClick() {
        AnalyticsHelper.logEvent(AnalyticsHelper.TokenProjectDetailPage.TOKEN_REPORT_CLICK_BACK);
        exit();
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private void mappingId() {
        this.ivTokenTeportIcon = this.binding.ivTokenReportIcon;
        this.tvTokenReportName = this.binding.tvTokenReportName;
        this.ckAirdrop = this.binding.ckAirdrop;
        this.ckScam = this.binding.ckScam;
        this.ckApproveScam = this.binding.ckApproveScam;
        this.ckUnableTrade = this.binding.ckUnableTrade;
        this.ckScamToken = this.binding.ckScamToken;
        this.ckOther = this.binding.ckOther;
        this.ivAirdrop = this.binding.ivAirdrop;
        this.ivScam = this.binding.ivScam;
        this.ivApproveScam = this.binding.ivApproveScam;
        this.ivUnableTrade = this.binding.ivUnableTrade;
        this.ivScamToken = this.binding.ivScamToken;
        this.ivOther = this.binding.ivOther;
        this.etReportDesc = this.binding.etReportDesc;
        this.etContact = this.binding.etContact;
        this.btnSubmit = this.binding.btnSubmit;
        this.btnToTronscan = this.binding.btnToTronscan;
    }

    private void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_submit) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.TokenProjectDetailPage.TOKEN_REPORT_CLICK_SUBMIT);
                    ArrayList arrayList = new ArrayList();
                    if (isAirdrop) {
                        arrayList.add(1);
                    }
                    if (isScam) {
                        arrayList.add(2);
                    }
                    if (isApproveScam) {
                        arrayList.add(3);
                    }
                    if (isUnableTrade) {
                        arrayList.add(4);
                    }
                    if (isScamToken) {
                        arrayList.add(5);
                    }
                    if (isOther) {
                        arrayList.add(6);
                    }
                    ((TokenReportPresenter) mPresenter).report(tokenAddress, tokenType, tokenSymbol, arrayList.toString().substring(1, arrayList.toString().length() - 1).replaceAll(" ", ""), etContact.getText().toString(), etReportDesc.getText().toString());
                } else if (id == R.id.btn_to_tronscan) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.TokenProjectDetailPage.TOKEN_REPORT_CLICK_TOTRONSCAN);
                    UIUtils.toTronScanTokenReport(TokenReportActivity.this);
                } else if (id == R.id.ck_airdrop) {
                    if (isAirdrop) {
                        isAirdrop = false;
                        ivAirdrop.setImageDrawable(getDrawable(R.mipmap.ic_address_check_unselect));
                    } else {
                        isAirdrop = true;
                        ivAirdrop.setImageDrawable(getDrawable(R.mipmap.ic_address_check_selected));
                    }
                    checkSubmitButton();
                } else {
                    switch (id) {
                        case R.id.ck_approve_scam:
                            if (isApproveScam) {
                                isApproveScam = false;
                                ivApproveScam.setImageDrawable(getDrawable(R.mipmap.ic_address_check_unselect));
                            } else {
                                isApproveScam = true;
                                ivApproveScam.setImageDrawable(getDrawable(R.mipmap.ic_address_check_selected));
                            }
                            checkSubmitButton();
                            return;
                        case R.id.ck_other:
                            if (isOther) {
                                isOther = false;
                                ivOther.setImageDrawable(getDrawable(R.mipmap.ic_address_check_unselect));
                            } else {
                                isOther = true;
                                ivOther.setImageDrawable(getDrawable(R.mipmap.ic_address_check_selected));
                            }
                            checkSubmitButton();
                            return;
                        case R.id.ck_scam:
                            if (isScam) {
                                isScam = false;
                                ivScam.setImageDrawable(getDrawable(R.mipmap.ic_address_check_unselect));
                            } else {
                                isScam = true;
                                ivScam.setImageDrawable(getDrawable(R.mipmap.ic_address_check_selected));
                            }
                            checkSubmitButton();
                            return;
                        case R.id.ck_scam_token:
                            if (isScamToken) {
                                isScamToken = false;
                                ivScamToken.setImageDrawable(getDrawable(R.mipmap.ic_address_check_unselect));
                            } else {
                                isScamToken = true;
                                ivScamToken.setImageDrawable(getDrawable(R.mipmap.ic_address_check_selected));
                            }
                            checkSubmitButton();
                            return;
                        case R.id.ck_unable_trade:
                            if (isUnableTrade) {
                                isUnableTrade = false;
                                ivUnableTrade.setImageDrawable(getDrawable(R.mipmap.ic_address_check_unselect));
                            } else {
                                isUnableTrade = true;
                                ivUnableTrade.setImageDrawable(getDrawable(R.mipmap.ic_address_check_selected));
                            }
                            checkSubmitButton();
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.ckOther.setOnClickListener(noDoubleClickListener2);
        this.ckScam.setOnClickListener(noDoubleClickListener2);
        this.ckApproveScam.setOnClickListener(noDoubleClickListener2);
        this.ckUnableTrade.setOnClickListener(noDoubleClickListener2);
        this.ckScamToken.setOnClickListener(noDoubleClickListener2);
        this.ckAirdrop.setOnClickListener(noDoubleClickListener2);
        this.btnToTronscan.setOnClickListener(noDoubleClickListener2);
        this.btnSubmit.setOnClickListener(noDoubleClickListener2);
    }

    public void checkSubmitButton() {
        if (this.isAirdrop || this.isScamToken || this.isScam || this.isApproveScam || this.isUnableTrade || this.isOther) {
            this.btnSubmit.setEnabled(true);
        } else {
            this.btnSubmit.setEnabled(false);
        }
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.TokenProjectDetailPage.TOKEN_REPORT_SHOW);
        this.tokenAddress = getIntent().getStringExtra("address");
        this.tokenType = getIntent().getIntExtra(AssetsConfig.TOKEN_TYPE, 2);
        this.tokenSymbol = getIntent().getStringExtra(AssetsConfig.TOKEN_SYMBOL);
        TokenBean tokenBean = (TokenBean) getIntent().getParcelableExtra(TronConfig.TRC);
        this.mToken = tokenBean;
        if (tokenBean != null) {
            this.ivTokenTeportIcon.setImageURI(tokenBean.getLogoUrl());
            if (!StringTronUtil.isEmpty(this.mToken.getShortName()) && !StringTronUtil.isEmpty(this.mToken.getShortName())) {
                String str = this.mToken.getShortName() + " (" + this.mToken.getName() + ")";
                SpannableString spannableString = new SpannableString(str);
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gray_9B)), this.mToken.getShortName().length(), str.length(), 17);
                this.tvTokenReportName.setText(spannableString);
                return;
            } else if (StringTronUtil.isEmpty(this.mToken.getShortName())) {
                this.tvTokenReportName.setText(this.mToken.getName());
                return;
            } else {
                return;
            }
        }
        this.tvTokenReportName.setText(this.tokenSymbol);
    }

    @Override
    public void reportSuccess() {
        hideLoadingPageDialog();
        toast(getString(R.string.token_report_submitted));
        this.handler.sendEmptyMessage(0);
    }
}
