package com.tron.wallet.business.confirm.fg.approve;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.approve.ConfirmSwapApproveContract;
import com.tron.wallet.business.confirm.fg.bean.CancelApproveParam;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FgCancelApproveConfirmBinding;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class ConfirmCancelApproveFragment extends BaseConfirmFragment<ConfirmSwapApprovePresenter, ConfirmSwapApproveModel> implements ConfirmSwapApproveContract.View {
    RelativeLayout approveLayout;
    private FgCancelApproveConfirmBinding binding;
    private CancelApproveParam cancelApproveParam;
    GlobalConfirmButton confirmCancelApprove;
    GlobalTitleHeaderView headerView;
    ImageView ivBack;
    SimpleDraweeView ivCoinIcon;
    LinearLayout liApproveCap;
    RelativeLayout llRoot;
    private NumberFormat numberFormat;
    GlobalFeeResourceView resourceView;
    RelativeLayout rlTopTitleLayout;
    TextView tokenContractTag;
    TextView tokenTag;
    TextView tvApproveCap;
    TextView tvAuthorizationTarget;
    TextView tvTokenAddress;
    TextView tvTokenName;

    public void setParam(CancelApproveParam cancelApproveParam) {
        this.cancelApproveParam = cancelApproveParam;
    }

    @Override
    public void updateHeaderInfoForProject(DappProjectInfoBean dappProjectInfoBean, boolean z) {
    }

    public static ConfirmCancelApproveFragment getInstance(CancelApproveParam cancelApproveParam) {
        ConfirmCancelApproveFragment confirmCancelApproveFragment = new ConfirmCancelApproveFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_DETAIL, cancelApproveParam);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, cancelApproveParam);
        confirmCancelApproveFragment.setArguments(bundle);
        return confirmCancelApproveFragment;
    }

    @Override
    public void processData() {
        super.processData();
        AnalyticsHelper.logEvent(AnalyticsHelper.SecurityCancelApprovePage.SECURITY_CANCEL_APPROVE_SHOW);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.cancelApproveParam = (CancelApproveParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM);
        }
        try {
            addAccountCallback(this.headerView, this.resourceView, this.confirmCancelApprove);
            bindDataToUI();
            ensureAccount();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.ivBack, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
    }

    public void lambda$processData$0(View view) {
        this.mContext.finish();
    }

    private void bindDataToUI() {
        CancelApproveParam cancelApproveParam = this.cancelApproveParam;
        if (cancelApproveParam == null) {
            return;
        }
        cancelApproveParam.setTitle(R.string.confirmtransaction, R.string.confirmtransaction);
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
        this.resourceView.bindData(this.cancelApproveParam);
        this.headerView.bindData(this.cancelApproveParam);
        this.confirmCancelApprove.onBind(this.cancelApproveParam);
        this.confirmCancelApprove.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.SecurityCancelApprovePage.SECURITY_CANCEL_APPROVE_CLICK_CONFIRM);
            }
        });
        this.resourceView.setFeeResourceCallback(this.confirmCancelApprove);
        this.approveLayout.setVisibility(View.GONE);
        this.rlTopTitleLayout.setVisibility(View.GONE);
        this.ivCoinIcon.setImageURI(this.cancelApproveParam.getTokenLogo());
        setTokenTag(this.tokenTag, Integer.parseInt(this.cancelApproveParam.getTokenType()));
        this.tvAuthorizationTarget.setText(this.cancelApproveParam.getApproveAddress());
        if (StringTronUtil.isEmpty(this.cancelApproveParam.getTokenName())) {
            this.tvTokenName.setText(R.string.approve_check_unknown_token);
        } else {
            this.tvTokenName.setText(this.cancelApproveParam.getTokenName());
        }
        this.tvTokenAddress.setText(this.cancelApproveParam.getTokenAddress());
        if ("2".equals(this.cancelApproveParam.getTokenType())) {
            this.tvApproveCap.setText(this.cancelApproveParam.getNum());
        } else if (AnalyticsHelper.SelectSendAddress.CLICK_ADDRESS_BOOK.equals(this.cancelApproveParam.getTokenType())) {
            if (StringTronUtil.isEmpty(this.cancelApproveParam.getTrc721Id())) {
                TextView textView = this.tvApproveCap;
                textView.setText("#" + this.cancelApproveParam.getNum() + " " + this.cancelApproveParam.getTokenSymbol());
            } else {
                TextView textView2 = this.tvApproveCap;
                textView2.setText("#" + this.cancelApproveParam.getTrc721Id().substring(this.cancelApproveParam.getTrc721Id().indexOf("_") + 1) + " " + this.cancelApproveParam.getTokenSymbol());
            }
            this.tvAuthorizationTarget.setText(this.cancelApproveParam.getProjectAddress());
        } else {
            this.tvApproveCap.setText(this.cancelApproveParam.getNum());
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgCancelApproveConfirmBinding inflate = FgCancelApproveConfirmBinding.inflate(getLayoutInflater());
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
        this.llRoot = this.binding.root;
        this.ivBack = this.binding.ivBack;
        this.headerView = this.binding.headerView;
        this.confirmCancelApprove = this.binding.confirmCancelApprove;
        this.approveLayout = this.binding.headerView.approveLayout;
        this.resourceView = this.binding.resourceView;
        this.tvTokenName = this.binding.tvTokenName;
        this.tokenTag = this.binding.tokenTag;
        this.tokenContractTag = this.binding.tokenContractTag;
        this.tvTokenAddress = this.binding.tvTokenAddress;
        this.tvAuthorizationTarget = this.binding.tvAuthorizationTarget;
        this.rlTopTitleLayout = (RelativeLayout) this.binding.getRoot().findViewById(R.id.rl_top_two);
        this.ivCoinIcon = this.binding.ivCoinIcon;
        this.tvApproveCap = this.binding.tvApproveCap;
        this.liApproveCap = this.binding.liApproveCap;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_close) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityCancelApprovePage.SECURITY_CANCEL_APPROVE_CLICK_CANCEL);
                    mContext.finish();
                } else if (id != R.id.tv_authorization_target) {
                    if (id != R.id.tv_token_address || cancelApproveParam == null || cancelApproveParam.getTokenAddress() == null || StringTronUtil.isEmpty(cancelApproveParam.getTokenAddress())) {
                        return;
                    }
                    Activity activity = mContext;
                    ConfirmCancelApproveFragment confirmCancelApproveFragment = ConfirmCancelApproveFragment.this;
                    CommonWebActivityV3.start((Context) activity, confirmCancelApproveFragment.getTokenUrl(confirmCancelApproveFragment.cancelApproveParam.getTokenAddress()), mContext.getResources().getString(R.string.tronscan), -2, true);
                } else if (cancelApproveParam == null || StringTronUtil.isEmpty(cancelApproveParam.getApproveAddress())) {
                } else {
                    Activity activity2 = mContext;
                    ConfirmCancelApproveFragment confirmCancelApproveFragment2 = ConfirmCancelApproveFragment.this;
                    CommonWebActivityV3.start((Context) activity2, confirmCancelApproveFragment2.getContractUrl(confirmCancelApproveFragment2.cancelApproveParam.getApproveAddress()), mContext.getResources().getString(R.string.tronscan), -2, true);
                }
            }
        };
        this.binding.tvTokenAddress.setOnClickListener(noDoubleClickListener);
        this.binding.tvAuthorizationTarget.setOnClickListener(noDoubleClickListener);
    }

    private void setTokenTag(TextView textView, int i) {
        textView.setVisibility(View.VISIBLE);
        if (i == 5) {
            textView.setBackground(this.mContext.getResources().getDrawable(R.drawable.roundborder_15ffa9_2));
            textView.setText("TRC721");
            textView.setTextColor(this.mContext.getResources().getColor(R.color.orange_FFA9));
            return;
        }
        textView.setBackground(this.mContext.getResources().getDrawable(R.drawable.roundborder_1557_2));
        textView.setText(TronConfig.TRC20);
        textView.setTextColor(this.mContext.getResources().getColor(R.color.green_57));
    }

    public String getContractUrl(String str) {
        String contractUrl = IRequest.getContractUrl(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return contractUrl + "?lang=zh";
        }
        return contractUrl + "?lang=en";
    }

    public String getTokenUrl(String str) {
        String format = String.format("%s%s", IRequest.getTronscan20TokenIntroduceUrl(), str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return format + "?lang=zh";
        }
        return format + "?lang=en";
    }

    private String getAccountUrl(String str) {
        String accountUrl = IRequest.getAccountUrl(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return accountUrl + "?lang=zh";
        }
        return accountUrl + "?lang=en";
    }

    public boolean isUnlimited(String str, TokenBean tokenBean) {
        return BigDecimalUtils.MoreThan(new BigDecimal(str), new BigDecimal(Math.pow(10.0d, (tokenBean != null ? tokenBean.getPrecision() : 0) + 18)));
    }

    public void onRefreshAccountComplete(boolean z, Protocol.Transaction transaction, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
        this.resourceView.onRefreshAccountComplete(z, transaction, pair);
    }

    private String getTrc10Url(String str) {
        String tRC10Url = IRequest.getTRC10Url(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return tRC10Url + "?lang=zh";
        }
        return tRC10Url + "?lang=en";
    }
}
