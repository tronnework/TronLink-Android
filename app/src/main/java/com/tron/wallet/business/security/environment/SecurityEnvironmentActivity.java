package com.tron.wallet.business.security.environment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.business.security.SecurityResult;
import com.tron.wallet.business.security.components.EnvironmentResource;
import com.tron.wallet.business.security.components.SecurityItemView;
import com.tron.wallet.business.security.environment.SecurityEnvironmentActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.interfaces.PopWindowSimpleButtonListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.databinding.AcSecurityEnvironmentBinding;
import com.tronlinkpro.wallet.R;
public class SecurityEnvironmentActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String DataKey = "SecurityEnvironmentKey";
    private AcSecurityEnvironmentBinding binding;
    SecurityItemView clipboardCheck;
    SecurityItemView netCheck;
    SecurityItemView officialCheck;
    private PopupWindow popupWindow;
    SecurityItemView rootCheck;
    SecurityItemView simulatorCheck;
    TextView tvRiskNum;
    TextView tvTopNum;
    TextView tvTopText;
    TextView tvWaringNum;
    private View viewOfficialCheckLine;

    public static void start(Context context, SecurityResult securityResult) {
        Intent intent = new Intent(context, SecurityEnvironmentActivity.class);
        intent.putExtra(DataKey, securityResult);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcSecurityEnvironmentBinding inflate = AcSecurityEnvironmentBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public void mappingId() {
        this.tvTopNum = this.binding.tvTopNum;
        this.tvTopText = this.binding.tvTopText;
        this.tvRiskNum = this.binding.tvRiskNum;
        this.tvWaringNum = this.binding.tvWaringNum;
        this.rootCheck = this.binding.rootCheck;
        this.simulatorCheck = this.binding.simulatorCheck;
        this.netCheck = this.binding.netCheck;
        this.clipboardCheck = this.binding.clipboardCheck;
        this.officialCheck = this.binding.officialCheck;
        this.viewOfficialCheckLine = this.binding.viewOfficialCheckLine;
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.SecurityEnvironmentPage.SECURITY_ENVIRONMENT_SHOW);
        SecurityResult securityResult = (SecurityResult) getIntent().getSerializableExtra(DataKey);
        checkNull(securityResult);
        SecurityResult.EnvironmentCheckData environmentCheckData = securityResult.getEnvironmentCheckData();
        checkNull(environmentCheckData);
        EnvironmentResource environmentResource = new EnvironmentResource();
        if (securityResult.getRiskNum() == 0 && securityResult.getWaringNum() == 0) {
            this.tvTopNum.setTextColor(getResources().getColor(R.color.green_57));
            this.tvTopNum.setText("0");
            this.tvTopText.setText(R.string.security_home_risky_items);
        } else if (securityResult.getRiskNum() != 0) {
            this.tvTopNum.setTextColor(getResources().getColor(R.color.red_EC));
            this.tvTopNum.setText(String.valueOf(securityResult.getRiskNum()));
            this.tvTopText.setText(R.string.security_environment_high_risk_items);
        } else if (securityResult.getWaringNum() != 0) {
            this.tvTopNum.setTextColor(getResources().getColor(R.color.yellow_FFA928));
            this.tvTopNum.setText(String.valueOf(securityResult.getWaringNum()));
            this.tvTopText.setText(R.string.security_environment_moderate_risk_items);
        }
        this.tvRiskNum.setText(String.valueOf(securityResult.getRiskNum()));
        this.tvWaringNum.setText(String.valueOf(securityResult.getWaringNum()));
        this.rootCheck.updateEnvironmentText(environmentCheckData.getRootCheck(), SecurityItemView.EnvironmentItem.Root, environmentResource);
        this.simulatorCheck.updateEnvironmentText(environmentCheckData.getSimulatorCheck(), SecurityItemView.EnvironmentItem.Simulator, environmentResource);
        this.netCheck.updateEnvironmentText(environmentCheckData.getNetCheck(), SecurityItemView.EnvironmentItem.Net, environmentResource);
        this.clipboardCheck.updateEnvironmentText(environmentCheckData.getClipboardCheck(), SecurityItemView.EnvironmentItem.Clipboard, environmentResource);
        if (environmentCheckData.getOfficialCheck() == ResultStatusEnum.Safe) {
            this.officialCheck.setVisibility(View.GONE);
            this.viewOfficialCheckLine.setVisibility(View.GONE);
        } else {
            this.officialCheck.updateEnvironmentText(environmentCheckData.getOfficialCheck(), SecurityItemView.EnvironmentItem.Official, environmentResource);
        }
        this.binding.llCommonLeft.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                exit();
            }
        });
        this.binding.ivCommonRight.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.SecurityEnvironmentPage.SECURITY_ENVIRONMENT_CLICK_ASK);
                CommonWebActivityV3.start(mContext, IRequest.getSecurityCheckUrl(), "", -2, false);
            }
        });
        this.binding.rootCheck.setOnClickListener(new fun3(environmentCheckData));
    }

    public class fun3 extends NoDoubleClickListener2 {
        final SecurityResult.EnvironmentCheckData val$environmentCheckData;

        fun3(SecurityResult.EnvironmentCheckData environmentCheckData) {
            this.val$environmentCheckData = environmentCheckData;
        }

        @Override
        protected void onNoDoubleClick(View view) {
            if (this.val$environmentCheckData.getRootCheck() == ResultStatusEnum.Risk || this.val$environmentCheckData.getRootCheck() == ResultStatusEnum.Waring) {
                AnalyticsHelper.logEvent(AnalyticsHelper.SecurityEnvironmentPage.SECURITY_ENVIRONMENT_CLICK_ROOT);
                SecurityEnvironmentActivity securityEnvironmentActivity = SecurityEnvironmentActivity.this;
                securityEnvironmentActivity.popupWindow = PopupWindowUtil.showRootRisk(securityEnvironmentActivity.mContext, new PopWindowSimpleButtonListener() {
                    @Override
                    public final void dismiss() {
                        SecurityEnvironmentActivity.3.this.lambda$onNoDoubleClick$0();
                    }
                });
                binding.getRoot().post(new Runnable() {
                    @Override
                    public final void run() {
                        SecurityEnvironmentActivity.3.this.lambda$onNoDoubleClick$1();
                    }
                });
            }
        }

        public void lambda$onNoDoubleClick$0() {
            popupWindow.dismiss();
        }

        public void lambda$onNoDoubleClick$1() {
            popupWindow.showAtLocation(binding.getRoot(), 17, 0, 0);
        }
    }

    public void checkNull(Object obj) {
        if (obj == null) {
            ToastError();
            exit();
        }
    }

    @Override
    public void onBackPressed() {
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.popupWindow.dismiss();
        } else {
            super.onBackPressed();
        }
    }
}
