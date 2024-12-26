package com.tron.wallet.business.confirm.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.common.bean.RiskAccountOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.addressscam.AddressManager;
import com.tron.wallet.databinding.FgConfirmAssetTransferBinding;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.tron.walletserver.Wallet;
public class ConfirmAssetTransferFragment extends BaseConfirmFragment<EmptyPresenter, EmptyModel> {
    View addressScamTag;
    private FgConfirmAssetTransferBinding binding;
    GlobalConfirmButton btnConfirm;
    GlobalTitleHeaderView headerView;
    SimpleDraweeView ivIcon;
    public LinearLayout llOtherError;
    private NumberFormat numberFormat;
    private int resTitleId;
    GlobalFeeResourceView resourceView;
    RelativeLayout rlAddress;
    RelativeLayout rlAmount;
    RelativeLayout rlContract;
    RelativeLayout rlNote;
    RelativeLayout rlTokenId;
    RelativeLayout rlType;
    TokenBean tokenBean;
    TextView tvAmount;
    TextView tvContractAddress;
    TextView tvName;
    TextView tvNote;
    public TextView tvOtherError;
    TextView tvOutAddress;
    TextView tvOwnerName;
    TextView tvReceivedAddress;
    TextView tvReceivedName;
    TextView tvTokenId;

    public static ConfirmAssetTransferFragment getInstance(BaseParam baseParam) {
        ConfirmAssetTransferFragment confirmAssetTransferFragment = new ConfirmAssetTransferFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        confirmAssetTransferFragment.setArguments(bundle);
        return confirmAssetTransferFragment;
    }

    @Override
    public void processData() {
        super.processData();
        if (this.baseParam == null || this.baseParam.getTransactionBean() == null || !(this.baseParam instanceof TransferParam)) {
            return;
        }
        this.tokenBean = ((TransferParam) this.baseParam).getTokenBean();
        try {
            bindDataToUI();
            addAccountCallback(this.headerView, this.resourceView, this.btnConfirm);
            ensureAccount();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void setTransferInfo() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.confirm.fg.ConfirmAssetTransferFragment.setTransferInfo():void");
    }

    private void bindDataToUI() {
        this.numberFormat = NumberFormat.getNumberInstance(Locale.US);
        setTransferInfo();
        if (this.tokenBean != null) {
            this.tokenBean = ((TransferParam) this.baseParam).getTokenBean();
            int type = ((TransferParam) this.baseParam).getTokenBean().getType();
            if (type == 0) {
                this.baseParam.addIconResource(R.mipmap.trx);
                this.resTitleId = R.string.asset_transfer;
                BaseParam baseParam = this.baseParam;
                int i = this.resTitleId;
                baseParam.addInfoTitle(i, TextDotUtils.getDotText(((TransferParam) this.baseParam).getAmount()) + " TRX");
            } else if (type == 1) {
                this.ivIcon.setImageURI(this.tokenBean.getLogoUrl());
                this.resTitleId = R.string.asset_transfer;
                if (StringTronUtil.isEmpty(this.tokenBean.getShortName()) || "null".equals(this.tokenBean.getShortName())) {
                    BaseParam baseParam2 = this.baseParam;
                    int i2 = this.resTitleId;
                    baseParam2.addInfoTitle(i2, TextDotUtils.getDotText(((TransferParam) this.baseParam).getAmount()) + " ");
                } else {
                    BaseParam baseParam3 = this.baseParam;
                    int i3 = this.resTitleId;
                    baseParam3.addInfoTitle(i3, TextDotUtils.getDotText(((TransferParam) this.baseParam).getAmount()) + " " + this.tokenBean.getShortName());
                }
            } else if (type == 2) {
                this.ivIcon.setImageURI(this.tokenBean.getLogoUrl());
                this.resTitleId = R.string.asset_transfer;
                BaseParam baseParam4 = this.baseParam;
                int i4 = this.resTitleId;
                baseParam4.addInfoTitle(i4, TextDotUtils.getDotText(((TransferParam) this.baseParam).getAmount()) + " " + this.tokenBean.getShortName());
            } else {
                this.ivIcon.setVisibility(View.VISIBLE);
                this.ivIcon.setImageURI(this.tokenBean.getLogoUrl());
            }
        }
        this.baseParam.setTitle(R.string.confirmtransaction, R.string.et_input_password);
        this.headerView.bindData(this.baseParam);
        this.resourceView.bindData(this.baseParam);
        this.btnConfirm.onBind(this.baseParam, true, ((TransferParam) this.baseParam).getToAddress());
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$bindDataToUI$1();
            }
        });
        this.resourceView.setFeeResourceCallback(this.btnConfirm);
    }

    public void lambda$bindDataToUI$1() {
        new ArrayList().add(((TransferParam) this.baseParam).getToAddress());
        AddressManager.checkSingleAddressScam(((TransferParam) this.baseParam).getToAddress()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$bindDataToUI$0((RiskAccountOutput) obj);
            }
        });
    }

    public void lambda$bindDataToUI$0(RiskAccountOutput riskAccountOutput) throws Exception {
        boolean isRisk = riskAccountOutput.getData().isRisk();
        this.btnConfirm.setFinishGetScam(isRisk);
        if (isRisk) {
            this.addressScamTag.setVisibility(View.VISIBLE);
        }
    }

    public void send() {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$send$2();
            }
        });
    }

    public void lambda$send$2() {
        dismissLoadingDialog();
        ConfirmTransactionNewActivity confirmTransactionNewActivity = (ConfirmTransactionNewActivity) getActivity();
        if (this.baseParam.hasOwnerPermission()) {
            confirmTransactionNewActivity.JumpkTo(3);
        } else {
            confirmTransactionNewActivity.JumpkTo(2);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgConfirmAssetTransferBinding inflate = FgConfirmAssetTransferBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    public void mappingId() {
        this.rlAddress = this.binding.rlAddress;
        this.tvOutAddress = this.binding.transferOutAddress;
        this.tvReceivedAddress = this.binding.receivingAddress;
        this.tvOwnerName = this.binding.transferOutName;
        this.tvReceivedName = this.binding.receivingName;
        this.rlAmount = this.binding.rlAmount;
        this.tvAmount = this.binding.tvAmount;
        this.rlContract = this.binding.rlContract;
        this.tvContractAddress = this.binding.tvContractAddress;
        this.tvName = this.binding.tvName;
        this.rlTokenId = this.binding.rlTokenId;
        this.tvTokenId = this.binding.tvTokenId;
        this.rlType = this.binding.rlType;
        this.rlNote = this.binding.rlNote;
        this.tvNote = this.binding.tvNote;
        this.headerView = this.binding.headerView;
        this.ivIcon = this.binding.headerView.ivIcon;
        this.resourceView = this.binding.resourceView;
        this.btnConfirm = this.binding.btnAssetConfirm;
        this.addressScamTag = this.binding.scamTag;
    }

    public Observable<String> getNameByAddress(final String str) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
                if (walletForAddress != null) {
                    observableEmitter.onNext(walletForAddress.getWalletName());
                } else {
                    String nameByAddress = AddressController.getInstance(AppContextUtil.getContext()).getNameByAddress(str);
                    if (!com.tron.tron_base.frame.utils.StringTronUtil.isEmpty(nameByAddress)) {
                        observableEmitter.onNext(nameByAddress);
                    } else {
                        observableEmitter.onNext("");
                    }
                }
                observableEmitter.onComplete();
            }
        }).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread());
    }
}
