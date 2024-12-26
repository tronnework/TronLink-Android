package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.one;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.business.confirm.fg.BaseApproveFragment;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappMetadataParam;
import com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.DappApproveConfirmFragment;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.one.DappApproveConfirmOneContract;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FgDappConfirmApproveOneBinding;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
public class DappApproveConfirmFragmentOne extends BaseApproveFragment<DappApproveConfirmOnePresenter, DappApproveConfirmOneModel> implements DappApproveConfirmOneContract.View {
    public static int TYPE_CUSTOM_APPROVE_VALUE = 0;
    public static int TYPE_DEFAULT_APPROVE_VALUE = 1;
    private String approveAmount;
    private FgDappConfirmApproveOneBinding binding;
    View customAmountLayout;
    private String customApproveAmount;
    View customBalanceLayout;
    View customLayout;
    ImageView customSelectView;
    View customTips;
    private DappDetailParam dappDetailParam;
    private String defaultApproveAmount;
    TextView defaultApproveAmountView;
    View defaultApproveLayout;
    ImageView defaultApproveSelect;
    View defaultTips;
    CurrencyEditText etAmount;
    private DappMetadataParam metaParam;
    private NumberFormat numberFormat;
    public int selectApproveModeType;
    private TokenBean tokenBean;
    TextView tvBalance;
    TextView tvTrustApproveTitle;

    public String getApproveAmount() {
        return this.approveAmount;
    }

    public static DappApproveConfirmFragmentOne getInstance(BaseParam baseParam, BaseParam baseParam2) {
        DappApproveConfirmFragmentOne dappApproveConfirmFragmentOne = new DappApproveConfirmFragmentOne();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_DETAIL, baseParam);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_META, baseParam2);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam2);
        dappApproveConfirmFragmentOne.setArguments(bundle);
        return dappApproveConfirmFragmentOne;
    }

    @Override
    public void processData() {
        super.processData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.dappDetailParam = (DappDetailParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM_DETAIL);
            this.metaParam = (DappMetadataParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM_META);
        }
        initEt();
        this.tvTrustApproveTitle.setText(String.format(getResources().getString(R.string.trust_approve_title), getString(this.dappDetailParam.isAccount() ? R.string.address : R.string.contract_string)));
        setSelectApproveMode(TYPE_CUSTOM_APPROVE_VALUE);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgDappConfirmApproveOneBinding inflate = FgDappConfirmApproveOneBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        onViewClicked();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.customLayout = this.binding.customApproveAmountLayout;
        this.customTips = this.binding.tips;
        this.customSelectView = this.binding.approveAmountSelect;
        this.customAmountLayout = this.binding.llAmount;
        this.customBalanceLayout = this.binding.balanceLayout;
        this.tvBalance = this.binding.tvBalance;
        this.defaultApproveLayout = this.binding.defaultApproveAmountLayout;
        this.defaultTips = this.binding.defaultTips;
        this.defaultApproveSelect = this.binding.trustApproveSelect;
        this.defaultApproveAmountView = this.binding.tvDefaultBalance;
        this.tvTrustApproveTitle = this.binding.trustApproveTitle;
        this.etAmount = this.binding.etCount;
    }

    public void setSelectApproveMode(int i) {
        this.selectApproveModeType = i;
        if (i != TYPE_CUSTOM_APPROVE_VALUE) {
            if (i == TYPE_DEFAULT_APPROVE_VALUE) {
                this.customLayout.setBackgroundResource(R.drawable.bg_shape_cd_8);
                this.customSelectView.setImageResource(R.mipmap.dapp_approve_amount_unselected);
                this.customAmountLayout.setVisibility(View.GONE);
                this.customBalanceLayout.setVisibility(View.GONE);
                this.defaultApproveLayout.setBackgroundResource(R.drawable.bg_shape_6d_8);
                this.defaultApproveSelect.setImageResource(R.mipmap.dapp_approve_amount_selected);
                this.defaultApproveAmountView.setVisibility(View.VISIBLE);
                this.approveAmount = this.defaultApproveAmount;
                updateBtn(true);
                ((DappApproveConfirmFragment) getParentFragment()).updateApproveAmount(this.approveAmount);
                return;
            }
            return;
        }
        this.customLayout.setBackgroundResource(R.drawable.bg_shape_6d_8);
        this.customSelectView.setImageResource(R.mipmap.dapp_approve_amount_selected);
        this.customAmountLayout.setVisibility(View.VISIBLE);
        this.customBalanceLayout.setVisibility(View.VISIBLE);
        this.defaultApproveLayout.setBackgroundResource(R.drawable.bg_shape_cd_8);
        this.defaultApproveSelect.setImageResource(R.mipmap.dapp_approve_amount_unselected);
        this.defaultApproveAmountView.setVisibility(View.GONE);
        String text_EditText_Dot = TextDotUtils.getText_EditText_Dot(this.etAmount);
        this.customApproveAmount = text_EditText_Dot;
        if (StringTronUtil.isEmpty(text_EditText_Dot)) {
            this.approveAmount = this.customApproveAmount;
        } else {
            BigDecimal bigDecimal = new BigDecimal(1);
            if (this.tokenBean != null) {
                bigDecimal = new BigDecimal(Math.pow(10.0d, this.tokenBean.getPrecision()));
            }
            this.approveAmount = BigDecimalUtils.mul_(new BigDecimal(this.customApproveAmount), bigDecimal).toPlainString();
        }
        updateBtn(!StringTronUtil.isNullOrEmpty(this.approveAmount));
        ((DappApproveConfirmFragment) getParentFragment()).updateApproveAmount(this.approveAmount);
    }

    public void onViewClicked() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                DappApproveConfirmFragmentOne dappApproveConfirmFragmentOne;
                int i;
                BigDecimal bigDecimal;
                switch (view.getId()) {
                    case R.id.approve_amount_select:
                        setSelectApproveMode(DappApproveConfirmFragmentOne.TYPE_CUSTOM_APPROVE_VALUE);
                        return;
                    case R.id.default_tips:
                        String string = getResources().getString(R.string.dapp_approve_default_tips);
                        Object[] objArr = new Object[1];
                        if (dappDetailParam.isAccount()) {
                            dappApproveConfirmFragmentOne = DappApproveConfirmFragmentOne.this;
                            i = R.string.address;
                        } else {
                            dappApproveConfirmFragmentOne = DappApproveConfirmFragmentOne.this;
                            i = R.string.contract_string;
                        }
                        objArr[0] = dappApproveConfirmFragmentOne.getString(i);
                        PopupWindowUtil.showPopupText(mContext, String.format(string, objArr), defaultTips, false);
                        return;
                    case R.id.tips:
                        PopupWindowUtil.showPopupText(mContext, String.format(getResources().getString(R.string.dapp_approve_custom_tips), new Object[0]), customTips, false);
                        return;
                    case R.id.trust_approve_select:
                        setSelectApproveMode(DappApproveConfirmFragmentOne.TYPE_DEFAULT_APPROVE_VALUE);
                        return;
                    case R.id.tv_max:
                        if (tokenBean != null) {
                            bigDecimal = tokenBean.getBalanceBigDecimal();
                        } else {
                            bigDecimal = new BigDecimal(0);
                        }
                        TextDotUtils.setText_Dot(etAmount, numberFormat.format(bigDecimal));
                        etAmount.setSelection(etAmount.getText().length());
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.approveAmountSelect.setOnClickListener(noDoubleClickListener2);
        this.binding.trustApproveSelect.setOnClickListener(noDoubleClickListener2);
        this.binding.tvMax.setOnClickListener(noDoubleClickListener2);
        this.binding.tips.setOnClickListener(noDoubleClickListener2);
        this.binding.defaultTips.setOnClickListener(noDoubleClickListener2);
    }

    public void initEt() {
        updateBtn(false);
        TextDotUtils.setTextChangedListener_Dot(this.etAmount);
        this.tokenBean = this.dappDetailParam.getTokenBean();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        TokenBean tokenBean = this.tokenBean;
        if (tokenBean != null) {
            numberInstance.setMaximumFractionDigits(tokenBean.getPrecision());
        }
        setAmountNumberFormat();
        setAssetsCount(this.tokenBean);
        String str = this.metaParam.getTriggerData().getParameterMap().get("1");
        this.customApproveAmount = TextDotUtils.getText_EditText_Dot(this.etAmount);
        this.approveAmount = this.metaParam.getTriggerData().getParameterMap().get("1");
        this.defaultApproveAmount = this.metaParam.getTriggerData().getParameterMap().get("1");
        if (this.tokenBean != null) {
            BigDecimal bigDecimal = new BigDecimal(Math.pow(10.0d, this.tokenBean.getPrecision()));
            String format = this.numberFormat.format(BigDecimalUtils.div(new BigDecimal(str), bigDecimal));
            TextView textView = this.defaultApproveAmountView;
            textView.setText(format + " " + this.tokenBean.getShortName());
        } else {
            this.defaultApproveAmountView.setText(str);
        }
        this.etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                DappApproveConfirmFragmentOne dappApproveConfirmFragmentOne = DappApproveConfirmFragmentOne.this;
                dappApproveConfirmFragmentOne.customApproveAmount = TextDotUtils.getText_EditText_Dot(dappApproveConfirmFragmentOne.etAmount);
                if (!StringTronUtil.isEmpty(customApproveAmount)) {
                    approveAmount = BigDecimalUtils.mul_(new BigDecimal(customApproveAmount), new BigDecimal(Math.pow(10.0d, tokenBean != null ? tokenBean.getPrecision() : 0))).toPlainString();
                } else {
                    DappApproveConfirmFragmentOne dappApproveConfirmFragmentOne2 = DappApproveConfirmFragmentOne.this;
                    dappApproveConfirmFragmentOne2.approveAmount = dappApproveConfirmFragmentOne2.customApproveAmount;
                }
                DappApproveConfirmFragmentOne dappApproveConfirmFragmentOne3 = DappApproveConfirmFragmentOne.this;
                dappApproveConfirmFragmentOne3.updateBtn(!StringTronUtil.isNullOrEmpty(dappApproveConfirmFragmentOne3.approveAmount));
            }
        });
        updateBtn(false);
    }

    private void setAssetsCount(TokenBean tokenBean) {
        String str;
        if (tokenBean != null) {
            StringBuilder sb = new StringBuilder("\t");
            sb.append(StringTronUtil.isEmpty(tokenBean.getShortName()) ? tokenBean.getName() : tokenBean.getShortName());
            String sb2 = sb.toString();
            TextView textView = this.tvBalance;
            StringBuilder sb3 = new StringBuilder();
            if (tokenBean.getType() == 0) {
                str = tokenBean.totalBalance + "";
            } else {
                str = tokenBean.balanceStr;
            }
            sb3.append(str);
            sb3.append(sb2);
            textView.setText(sb3.toString());
        }
    }

    private boolean isNotEllipsized(TextView textView, String str) {
        return StringTronUtil.equals(str, getEllipsizedText(textView, str));
    }

    private String getEllipsizedText(TextView textView, String str) {
        return TextUtils.ellipsize(str, textView.getPaint(), UIUtils.dip2px(180.0f), textView.getEllipsize()).toString();
    }

    public void updateBtn(boolean z) {
        if (getActivity() instanceof DappConfirmNewActivity) {
            ((DappConfirmNewActivity) getActivity()).getConfirmBtn().setApproveAmount(this.approveAmount);
            ((DappConfirmNewActivity) getActivity()).getConfirmBtn().setEnabled(z);
        } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
            ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setApproveAmount(this.approveAmount);
            ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setEnabled(z);
        }
    }

    private void setAmountNumberFormat() {
        TokenBean tokenBean = this.tokenBean;
        if (tokenBean != null && tokenBean.getPrecision() != 0) {
            this.numberFormat.setMaximumFractionDigits(this.tokenBean.getPrecision());
            this.etAmount.setInputType(8194);
            this.etAmount.setDOTValue(this.tokenBean.getPrecision());
            return;
        }
        this.numberFormat.setMaximumFractionDigits(6);
        TokenBean tokenBean2 = this.tokenBean;
        if (tokenBean2 != null && tokenBean2.getType() == 0) {
            this.etAmount.setInputType(8194);
            this.etAmount.setDOTValue(6);
            return;
        }
        this.etAmount.setInputType(2);
    }

    public void updateSelectApproveMode() {
        if (this.dappDetailParam.getDappProjectInfoBean().isWhite()) {
            setSelectApproveMode(TYPE_DEFAULT_APPROVE_VALUE);
        } else if (this.selectApproveModeType != TYPE_CUSTOM_APPROVE_VALUE || StringTronUtil.isEmpty(this.approveAmount)) {
        } else {
            BigDecimal bigDecimal = new BigDecimal(1);
            if (this.tokenBean != null) {
                bigDecimal = new BigDecimal(Math.pow(10.0d, this.tokenBean.getPrecision()));
            }
            this.etAmount.setText(this.numberFormat.format(BigDecimalUtils.div(new BigDecimal(this.approveAmount), bigDecimal)));
        }
    }
}
