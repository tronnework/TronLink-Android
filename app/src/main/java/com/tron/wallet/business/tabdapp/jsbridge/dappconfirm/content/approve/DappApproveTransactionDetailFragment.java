package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.DappApproveTransactionDetailContract;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.two.DappApproveConfirmFragmentTwo;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.FgDappConfirmTransactionDetailBinding;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class DappApproveTransactionDetailFragment extends BaseFragment<DappApproveTransactionDetailPresenter, EmptyModel> implements DappApproveTransactionDetailContract.View, BaseConfirmFragment.AccountCallback {
    private BasePopupView amountEditPopWindow;
    View amountEditView;
    private Parcelable baseParam;
    private FgDappConfirmTransactionDetailBinding binding;
    private boolean isEdited;
    private NumberFormat numberFormat;
    private DappDetailParam param;
    GlobalFeeResourceView resourceView;
    RelativeLayout rlAddress;
    RelativeLayout rlAmount;
    RelativeLayout rlApproveAmount;
    RelativeLayout rlContract;
    RelativeLayout rlNote;
    RelativeLayout rlTokenId;
    TextView tvAmount;
    TextView tvApproveAmount;
    TextView tvContractAddress;
    TextView tvName;
    TextView tvNoLimit;
    TextView tvNote;
    TextView tvOutAddress;
    TextView tvOwnerName;
    TextView tvReceivedAddress;
    TextView tvReceivedName;
    TextView tvTokenId;
    TextView tvUnit;

    public void setParam(DappDetailParam dappDetailParam) {
        this.param = dappDetailParam;
    }

    public static DappApproveTransactionDetailFragment getInstance(BaseParam baseParam) {
        DappApproveTransactionDetailFragment dappApproveTransactionDetailFragment = new DappApproveTransactionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        dappApproveTransactionDetailFragment.setArguments(bundle);
        return dappApproveTransactionDetailFragment;
    }

    @Override
    protected void processData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.baseParam = arguments.getParcelable(ArgumentKeys.KEY_PARAM);
        }
        Parcelable parcelable = this.baseParam;
        if (parcelable instanceof DappDetailParam) {
            this.param = (DappDetailParam) parcelable;
            try {
                bindDataToUI();
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (getActivity() instanceof DappConfirmNewActivity) {
                ((DappConfirmNewActivity) getActivity()).getConfirmBtn().onBind(this.param);
                this.resourceView.setFeeResourceCallback(((DappConfirmNewActivity) getActivity()).getConfirmBtn());
            } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
                ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().onBind(this.param);
                this.resourceView.setFeeResourceCallback(((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn());
            }
        }
    }

    private void bindDataToUI() {
        this.numberFormat = NumberFormat.getNumberInstance(Locale.US);
        setChildrenVisibility();
        DappDetailParam dappDetailParam = this.param;
        if (dappDetailParam == null) {
            return;
        }
        this.resourceView.bindData(dappDetailParam);
        TouchDelegateUtils.expandViewTouchDelegate(this.amountEditView, 5, 10, 10, 10);
    }

    private void setChildrenVisibility() {
        if (this.param.getTokenBean() != null && this.param.getTokenBean().getType() == 2) {
            this.param.setTokenActionType(1);
        } else if (this.param.getTokenBean() == null) {
            this.param.setTokenActionType(1);
        }
        int tokenActionType = this.param.getTokenActionType();
        if (tokenActionType != 1) {
            if (tokenActionType != 3) {
                if (tokenActionType != 990) {
                    return;
                }
                this.tvContractAddress.setText(this.param.getContractAddress());
                this.rlContract.setVisibility(View.VISIBLE);
                if (this.param.getTokenBean() != null && !StringTronUtil.isEmpty(this.param.getTokenBean().getShortName())) {
                    this.tvName.setText(this.param.getTokenBean().getName());
                    return;
                } else {
                    this.tvName.setVisibility(View.GONE);
                    return;
                }
            }
            this.tvContractAddress.setText(this.param.getContractAddress());
            final String str = this.param.getTriggerData().getParameterMap().get("1");
            TextView textView = this.tvApproveAmount;
            textView.setText("#" + str);
            this.rlContract.setVisibility(View.VISIBLE);
            this.rlApproveAmount.setVisibility(View.VISIBLE);
            if (this.param.getTokenBean() != null && !StringTronUtil.isEmpty(this.param.getTokenBean().getShortName())) {
                this.tvName.setText(this.param.getTokenBean().getName());
            } else {
                this.tvName.setVisibility(View.GONE);
            }
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$setChildrenVisibility$0(str);
                }
            });
            return;
        }
        this.tvContractAddress.setText(this.param.getContractAddress());
        String str2 = this.param.getTriggerData().getParameterMap().get("1");
        if (isUnlimited(str2, this.param.getTokenBean())) {
            this.tvNoLimit.setVisibility(View.VISIBLE);
            this.tvApproveAmount.setVisibility(View.GONE);
            this.tvNoLimit.setText(getIContext().getResources().getString(R.string.unlimited));
            this.tvUnit.setText("");
        } else {
            TokenBean tokenBean = this.param.getTokenBean();
            if (BigDecimalUtils.equalsZero(str2)) {
                this.tvNoLimit.setVisibility(View.VISIBLE);
                this.tvApproveAmount.setVisibility(View.GONE);
                TextView textView2 = this.tvNoLimit;
                StringBuilder sb = new StringBuilder("0 ");
                sb.append(tokenBean != null ? tokenBean.getShortName() : "");
                textView2.setText(sb.toString());
                this.tvUnit.setText("");
            } else {
                this.tvNoLimit.setVisibility(View.GONE);
                if (tokenBean != null) {
                    this.numberFormat.setMaximumFractionDigits(tokenBean.getPrecision());
                    str2 = this.numberFormat.format(BigDecimalUtils.div(new BigDecimal(str2), new BigDecimal(Math.pow(10.0d, tokenBean.getPrecision()))));
                    TextView textView3 = this.tvUnit;
                    textView3.setText(" " + tokenBean.getShortName());
                }
                this.tvApproveAmount.setVisibility(View.VISIBLE);
                this.tvApproveAmount.setText(str2);
            }
        }
        this.rlContract.setVisibility(View.VISIBLE);
        this.rlApproveAmount.setVisibility(View.VISIBLE);
        this.amountEditView.setVisibility(View.VISIBLE);
        if (this.param.getTokenBean() != null && !StringTronUtil.isEmpty(this.param.getTokenBean().getShortName())) {
            this.tvName.setText(this.param.getTokenBean().getName());
        } else {
            this.tvName.setVisibility(View.GONE);
        }
        this.amountEditView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((DappApproveConfirmFragmentTwo) getParentFragment()).switchToOne();
            }
        });
    }

    public void lambda$setChildrenVisibility$0(String str) {
        try {
            getCollectionInfo(this.param.getOwnerAddress(), this.param.getContractAddress(), str);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgDappConfirmTransactionDetailBinding inflate = FgDappConfirmTransactionDetailBinding.inflate(layoutInflater, viewGroup, false);
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
        this.rlAddress = this.binding.rlAddress;
        this.tvOutAddress = this.binding.transferOutAddress;
        this.tvReceivedAddress = this.binding.receivingAddress;
        this.tvOwnerName = this.binding.transferOutName;
        this.tvReceivedName = this.binding.receivingName;
        this.rlAmount = this.binding.rlAmount;
        this.tvAmount = this.binding.tvAmount;
        this.rlApproveAmount = this.binding.rlApproveAmount;
        this.tvApproveAmount = this.binding.tvApproveAmount;
        this.tvUnit = this.binding.unit;
        this.amountEditView = this.binding.ivAmountEdit;
        this.tvNoLimit = this.binding.tvApproveNoLimitAmount;
        this.rlContract = this.binding.rlContract;
        this.tvContractAddress = this.binding.tvContractAddress;
        this.tvName = this.binding.tvName;
        this.rlTokenId = this.binding.rlTokenId;
        this.tvTokenId = this.binding.tvTokenId;
        this.rlNote = this.binding.rlNote;
        this.tvNote = this.binding.tvNote;
        this.resourceView = this.binding.resourceView;
    }

    public String getUrl() {
        String contractUrl = IRequest.getContractUrl(this.param.getContractAddress());
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return contractUrl + "?lang=zh";
        }
        return contractUrl + "?lang=en";
    }

    public String getTrc10Url(String str) {
        String tRC10Url = IRequest.getTRC10Url(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return tRC10Url + "?lang=zh";
        }
        return tRC10Url + "?lang=en";
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                int i = 2;
                if (id == R.id.rl_contract_address) {
                    if (StringTronUtil.isEmpty(param.getContractAddress())) {
                        return;
                    }
                    CommonWebActivityV3.start((Context) mContext, getUrl(), mContext.getResources().getString(R.string.tronscan), -2, true);
                    try {
                        if (!Protocol.Transaction.parseFrom(param.getTransactionBean().getBytes().get(0)).getRawData().getContract(0).getType().equals(Protocol.Transaction.Contract.ContractType.TriggerSmartContract)) {
                            i = 1;
                        }
                        AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_SMART_CONTRACT, Integer.valueOf(i));
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                } else if (id == R.id.tv_approve_no_limit_amount) {
                    String str = param.getTriggerData().getParameterMap().get("1");
                    DappApproveTransactionDetailFragment dappApproveTransactionDetailFragment = DappApproveTransactionDetailFragment.this;
                    if (dappApproveTransactionDetailFragment.isUnlimited(str, dappApproveTransactionDetailFragment.param.getTokenBean())) {
                        PopupWindowUtil.showPopupText(mContext, getResources().getString(R.string.dapp_approve_account_unlimit), tvNoLimit, true);
                    } else if (BigDecimalUtils.equalsZero(str)) {
                        PopupWindowUtil.showPopupText(mContext, getResources().getString(R.string.confirm_approve_zero_tip), tvNoLimit, true);
                    }
                } else if (id != R.id.tv_token_id) {
                } else {
                    Activity activity = mContext;
                    DappApproveTransactionDetailFragment dappApproveTransactionDetailFragment2 = DappApproveTransactionDetailFragment.this;
                    CommonWebActivityV3.start((Context) activity, dappApproveTransactionDetailFragment2.getTrc10Url(dappApproveTransactionDetailFragment2.param.getAssetName()), mContext.getResources().getString(R.string.tronscan), -2, true);
                    try {
                        if (!Protocol.Transaction.parseFrom(param.getTransactionBean().getBytes().get(0)).getRawData().getContract(0).getType().equals(Protocol.Transaction.Contract.ContractType.TriggerSmartContract)) {
                            i = 1;
                        }
                        AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_TOKEN_ID, Integer.valueOf(i));
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                    }
                }
            }
        };
        this.binding.rlContractAddress.setOnClickListener(noDoubleClickListener2);
        this.binding.getRoot().findViewById(R.id.tv_token_id).setOnClickListener(noDoubleClickListener2);
        this.binding.getRoot().findViewById(R.id.tv_approve_no_limit_amount).setOnClickListener(noDoubleClickListener2);
    }

    public void updateDetail(DappDetailParam dappDetailParam) {
        this.param = dappDetailParam;
        bindDataToUI();
    }

    @Override
    public void bindDataToResourceUI(int i) {
        this.param.setIsActive(i);
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$bindDataToResourceUI$1();
            }
        });
    }

    public void lambda$bindDataToResourceUI$1() {
        try {
            this.resourceView.bindData(this.param);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public boolean isUnlimited(String str, TokenBean tokenBean) {
        return BigDecimalUtils.MoreThan(new BigDecimal(str), new BigDecimal(Math.pow(10.0d, (tokenBean != null ? tokenBean.getPrecision() : 0) + 18)));
    }

    public void getCollectionInfo(final String str, final String str2, final String str3) {
        runOnThreeThread(new OnBackground() {
            @Override
            public void doOnBackground() {
                ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollectionInfo(str, str2, str3).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<NftItemInfoOutput>() {
                    @Override
                    public void onFailure(String str4, String str5) {
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onResponse(String str4, NftItemInfoOutput nftItemInfoOutput) {
                        updateNftInfoUI(nftItemInfoOutput.getData());
                    }
                }, "getCollectionInfo"));
            }
        });
    }

    public void updateNftInfoUI(NftItemInfo nftItemInfo) {
        if (nftItemInfo == null || StringTronUtil.isNullOrEmpty(nftItemInfo.getName())) {
            return;
        }
        String str = this.param.getTriggerData().getParameterMap().get("1");
        String name = !StringTronUtil.isNullOrEmpty(nftItemInfo.getName()) ? nftItemInfo.getName() : "";
        TextView textView = this.tvApproveAmount;
        textView.setText("#" + str + " " + name);
    }

    @Override
    public void onRefreshAccountComplete(boolean z, Protocol.Transaction transaction, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
        this.resourceView.onRefreshAccountComplete(z, transaction, pair);
    }

    public void updateTokenInfo(TokenBean tokenBean) {
        this.param.setTokenBean(tokenBean);
        if (tokenBean.getType() == 2) {
            this.param.setTokenActionType(1);
        } else if (tokenBean.getType() == 5) {
            this.param.setTokenActionType(3);
        }
        setChildrenVisibility();
    }

    void showAmountEditPopWindow(final String str, final TokenBean tokenBean) {
        BasePopupView asCustom = new XPopup.Builder(getIContext()).maxWidth(XPopupUtils.getScreenWidth(getIContext())).dismissOnTouchOutside(false).dismissOnBackPressed(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(getIContext()) {
            @Override
            public int getImplLayoutId() {
                return R.layout.amount_edit_popup_view;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                final EditText editText = (EditText) findViewById(R.id.et_amount);
                final Button button = (Button) findViewById(R.id.btn_confirm);
                Button button2 = (Button) findViewById(R.id.btn_cancel);
                if (tokenBean != null) {
                    editText.setInputType(8194);
                } else {
                    editText.setInputType(2);
                }
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (StringTronUtil.isNullOrEmpty(editable.toString())) {
                            button.setEnabled(false);
                        } else {
                            button.setEnabled(true);
                        }
                    }
                });
                TextDotUtils.setTextChangedListener_Dot(editText);
                if (isEdited) {
                    editText.setText(str);
                } else {
                    button.setEnabled(false);
                }
                editText.requestFocus();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_EDIT_APPROVE_AMOUNT_CONFIRM);
                        dismiss();
                        String obj = editText.getText().toString();
                        if (StringTronUtil.isNullOrEmpty(obj)) {
                            return;
                        }
                        isEdited = true;
                        TextView textView = tvUnit;
                        textView.setText(" " + tokenBean.getShortName());
                        if (tokenBean != null) {
                            if (obj.contains(",")) {
                                obj = obj.replaceAll(",", "");
                            }
                            BigDecimal bigDecimal = new BigDecimal(Math.pow(10.0d, tokenBean.getPrecision()));
                            BigDecimal bigDecimal2 = new BigDecimal(obj);
                            numberFormat.setMaximumFractionDigits(tokenBean.getPrecision());
                            numberFormat.setRoundingMode(RoundingMode.DOWN);
                            TextDotUtils.setText_Dot(tvApproveAmount, numberFormat.format(bigDecimal2));
                            obj = numberFormat.format(BigDecimalUtils.mul_(numberFormat.format(bigDecimal2), bigDecimal));
                        } else {
                            tvApproveAmount.setText(obj);
                        }
                        String str2 = param.getTriggerData().getParameterMap().get("0");
                        try {
                            String obj2 = NumberFormat.getNumberInstance(Locale.US).parse(obj).toString();
                            ((DappConfirmNewActivity) getActivity()).setEditApproveAmountParam(str2, obj2);
                            ((DappApproveConfirmFragmentTwo) getParentFragment()).updateMetaData(str2, obj2);
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_EDIT_APPROVE_AMOUNT_CANCEL);
                        dismiss();
                    }
                });
            }
        });
        this.amountEditPopWindow = asCustom;
        asCustom.show();
    }
}
