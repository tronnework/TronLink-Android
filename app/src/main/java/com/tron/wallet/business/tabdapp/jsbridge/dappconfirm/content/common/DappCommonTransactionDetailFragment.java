package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.transfer.DappTransferTransactionDetailContract;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.transfer.DappTransferTransactionDetailPresenter;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgDappConfirmTransactionDetailBinding;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class DappCommonTransactionDetailFragment extends BaseFragment<DappTransferTransactionDetailPresenter, EmptyModel> implements DappTransferTransactionDetailContract.View, BaseConfirmFragment.AccountCallback {
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

    public void setParam(DappDetailParam dappDetailParam) {
        this.param = dappDetailParam;
    }

    public static DappCommonTransactionDetailFragment getInstance(BaseParam baseParam) {
        DappCommonTransactionDetailFragment dappCommonTransactionDetailFragment = new DappCommonTransactionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        dappCommonTransactionDetailFragment.setArguments(bundle);
        return dappCommonTransactionDetailFragment;
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
            ((DappConfirmNewActivity) getActivity()).getConfirmBtn().onBind(this.param);
            this.resourceView.setFeeResourceCallback(((DappConfirmNewActivity) getActivity()).getConfirmBtn());
        }
    }

    private void bindDataToUI() {
        this.numberFormat = NumberFormat.getNumberInstance(Locale.US);
        DappDetailParam dappDetailParam = this.param;
        if (dappDetailParam == null) {
            return;
        }
        this.resourceView.bindData(dappDetailParam);
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
                    DappCommonTransactionDetailFragment dappCommonTransactionDetailFragment = DappCommonTransactionDetailFragment.this;
                    CommonWebActivityV3.start((Context) activity, dappCommonTransactionDetailFragment.getTrc10Url(dappCommonTransactionDetailFragment.param.getAssetName()), mContext.getResources().getString(R.string.tronscan), -2, true);
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
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$bindDataToResourceUI$0();
            }
        });
    }

    public void lambda$bindDataToResourceUI$0() {
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
}
