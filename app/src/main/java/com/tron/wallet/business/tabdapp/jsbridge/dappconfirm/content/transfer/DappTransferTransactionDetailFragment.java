package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.transfer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.transfer.DappTransferTransactionDetailContract;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.RiskAccountOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.addressscam.AddressManager;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.databinding.FgDappConfirmTransactionDetailBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.LogUtils;
import org.tron.protos.Protocol;
public class DappTransferTransactionDetailFragment extends BaseFragment<DappTransferTransactionDetailPresenter, EmptyModel> implements DappTransferTransactionDetailContract.View, BaseConfirmFragment.AccountCallback {
    View addressScamTag;
    private Parcelable baseParam;
    private FgDappConfirmTransactionDetailBinding binding;
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
    TextView tvNote;
    TextView tvOutAddress;
    TextView tvOwnerName;
    TextView tvReceivedAddress;
    TextView tvReceivedName;
    TextView tvTokenId;
    String outAddress = "";
    String toAddress = "";

    public void setParam(DappDetailParam dappDetailParam) {
        this.param = dappDetailParam;
    }

    public static DappTransferTransactionDetailFragment getInstance(BaseParam baseParam) {
        DappTransferTransactionDetailFragment dappTransferTransactionDetailFragment = new DappTransferTransactionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        dappTransferTransactionDetailFragment.setArguments(bundle);
        return dappTransferTransactionDetailFragment;
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
            if (!StringTronUtil.isNullOrEmpty(this.param.getToAddress())) {
                this.toAddress = this.param.getToAddress();
            }
            ((DappConfirmNewActivity) getActivity()).getConfirmBtn().onBind(this.param, true, this.toAddress);
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$processData$1();
                }
            });
            this.resourceView.setFeeResourceCallback(((DappConfirmNewActivity) getActivity()).getConfirmBtn());
        }
    }

    public void lambda$processData$1() {
        new ArrayList().add(this.toAddress);
        AddressManager.checkSingleAddressScam(this.toAddress).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0((RiskAccountOutput) obj);
            }
        });
    }

    public void lambda$processData$0(RiskAccountOutput riskAccountOutput) throws Exception {
        boolean isRisk = riskAccountOutput.getData().isRisk();
        ((DappConfirmNewActivity) getActivity()).getConfirmBtn().setFinishGetScam(isRisk);
        if (isRisk) {
            showScamView();
        }
    }

    private void bindDataToUI() {
        this.numberFormat = NumberFormat.getNumberInstance(Locale.US);
        setChildrenVisibility();
        this.rlAddress.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (tvReceivedAddress.getLineCount() > tvOutAddress.getLineCount()) {
                    tvOutAddress.setLines(tvReceivedAddress.getLineCount());
                } else if (tvReceivedAddress.getLineCount() < tvOutAddress.getLineCount()) {
                    tvReceivedAddress.setLines(tvOutAddress.getLineCount());
                }
            }
        });
        DappDetailParam dappDetailParam = this.param;
        if (dappDetailParam == null) {
            return;
        }
        if (dappDetailParam.getContractType() == 1 || this.param.getContractType() == 2) {
            ((DappTransferTransactionDetailPresenter) this.mPresenter).getAccount(this.param.getToAddress());
        }
        this.resourceView.bindData(this.param);
    }

    private void setChildrenVisibility() {
        int contractType = this.param.getContractType();
        if (contractType != 1) {
            if (contractType != 2) {
                if (contractType != 31) {
                    return;
                }
                int tokenActionType = this.param.getTokenActionType();
                if (tokenActionType == 0 || tokenActionType == 2) {
                    this.rlAddress.setVisibility(View.VISIBLE);
                    if (this.param.getTokenActionType() == 0) {
                        if (TriggerUtils.isTransferFrom(this.param.getTriggerSmartContract())) {
                            this.toAddress = this.param.getTriggerData().getParameterMap().get("1");
                            this.outAddress = this.param.getTriggerData().getParameterMap().get("0");
                        } else {
                            this.toAddress = this.param.getTriggerData().getParameterMap().get("0");
                            this.outAddress = this.param.getOwnerAddress();
                        }
                    } else if (this.param.getTokenActionType() == 2) {
                        this.toAddress = this.param.getTriggerData().getParameterMap().get("1");
                        this.outAddress = this.param.getOwnerAddress();
                    }
                    if (!StringTronUtil.isEmpty(this.param.getOwnerAddress(), this.toAddress)) {
                        this.rlAddress.setVisibility(View.VISIBLE);
                        this.tvOutAddress.setText(this.outAddress);
                        this.tvReceivedAddress.setText(this.toAddress);
                        if (WalletUtils.getWalletForAddress(this.param.getOwnerAddress()) != null) {
                            String walletName = WalletUtils.getWalletForAddress(this.param.getOwnerAddress()).getWalletName();
                            TextView textView = this.tvOwnerName;
                            if (StringTronUtil.isNullOrEmpty(walletName)) {
                                walletName = "";
                            }
                            textView.setText(walletName);
                        }
                        if (WalletUtils.getWalletForAddress(this.toAddress) != null) {
                            String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(this.toAddress);
                            this.tvReceivedName.setText(StringTronUtil.isNullOrEmpty(nameByAddress) ? "" : nameByAddress);
                        }
                    }
                    if (!StringTronUtil.isEmpty(this.param.getContractAddress())) {
                        this.rlContract.setVisibility(View.VISIBLE);
                        this.tvContractAddress.setText(this.param.getContractAddress());
                        if (this.param.getTokenBean() != null && !StringTronUtil.isEmpty(this.param.getTokenBean().getShortName())) {
                            this.tvName.setText(this.param.getTokenBean().getName());
                        } else {
                            this.tvName.setVisibility(View.GONE);
                        }
                    }
                    if (StringTronUtil.isEmpty(this.param.getNote())) {
                        return;
                    }
                    this.rlNote.setVisibility(View.VISIBLE);
                    this.tvNote.setText(this.param.getNote());
                    return;
                }
                return;
            } else if (!StringTronUtil.isEmpty(this.param.getAssetName())) {
                this.rlTokenId.setVisibility(View.VISIBLE);
                this.tvTokenId.setText(this.param.getAssetName());
            }
        }
        this.rlAddress.setVisibility(View.VISIBLE);
        if (!StringTronUtil.isEmpty(this.param.getOwnerAddress(), this.param.getToAddress())) {
            this.rlAddress.setVisibility(View.VISIBLE);
            this.tvOutAddress.setText(this.param.getOwnerAddress());
            this.tvReceivedAddress.setText(this.param.getToAddress());
            if (WalletUtils.getWalletForAddress(this.param.getOwnerAddress()) != null) {
                String walletName2 = WalletUtils.getWalletForAddress(this.param.getOwnerAddress()).getWalletName();
                TextView textView2 = this.tvOwnerName;
                if (StringTronUtil.isNullOrEmpty(walletName2)) {
                    walletName2 = "";
                }
                textView2.setText(walletName2);
            }
            if (WalletUtils.getWalletForAddress(this.param.getToAddress()) != null) {
                String nameByAddress2 = AddressNameUtils.getInstance().getNameByAddress(this.param.getToAddress());
                this.tvReceivedName.setText(StringTronUtil.isNullOrEmpty(nameByAddress2) ? "" : nameByAddress2);
            }
        }
        if (StringTronUtil.isEmpty(this.param.getNote())) {
            return;
        }
        this.rlNote.setVisibility(View.VISIBLE);
        this.tvNote.setText(this.param.getNote());
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
        this.rlContract = this.binding.rlContract;
        this.tvContractAddress = this.binding.tvContractAddress;
        this.tvName = this.binding.tvName;
        this.rlTokenId = this.binding.rlTokenId;
        this.tvTokenId = this.binding.tvTokenId;
        this.rlNote = this.binding.rlNote;
        this.tvNote = this.binding.tvNote;
        this.resourceView = this.binding.resourceView;
        this.addressScamTag = this.binding.scamTag;
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
                } else if (id != R.id.tv_token_id) {
                } else {
                    Activity activity = mContext;
                    DappTransferTransactionDetailFragment dappTransferTransactionDetailFragment = DappTransferTransactionDetailFragment.this;
                    CommonWebActivityV3.start((Context) activity, dappTransferTransactionDetailFragment.getTrc10Url(dappTransferTransactionDetailFragment.param.getAssetName()), mContext.getResources().getString(R.string.tronscan), -2, true);
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
        this.binding.tvTokenId.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void bindDataToResourceUI(int i) {
        this.param.setIsActive(i);
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

    public void showScamView() {
        this.addressScamTag.setVisibility(View.VISIBLE);
    }
}
