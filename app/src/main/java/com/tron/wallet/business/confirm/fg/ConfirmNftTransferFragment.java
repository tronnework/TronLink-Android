package com.tron.wallet.business.confirm.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.confirm.fg.component.Utils;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.NFTParam;
import com.tron.wallet.common.bean.RiskAccountOutput;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.addressscam.AddressManager;
import com.tron.wallet.databinding.FgConfirmNftTransferBinding;
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
import org.tron.walletserver.Wallet;
public class ConfirmNftTransferFragment extends BaseConfirmFragment<EmptyPresenter, EmptyModel> {
    View addressScamTag;
    private FgConfirmNftTransferBinding binding;
    GlobalConfirmButton btnConfirm;
    GlobalTitleHeaderView headerView;
    public LinearLayout llOtherError;
    private NumberFormat numberFormat;
    private BaseParam param;
    GlobalFeeResourceView resourceView;
    RelativeLayout rlAddress;
    RelativeLayout rlAmount;
    RelativeLayout rlContract;
    RelativeLayout rlNote;
    RelativeLayout rlTokenId;
    RelativeLayout rlType;
    View rootLayout;
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

    public static ConfirmNftTransferFragment getInstance(BaseParam baseParam) {
        ConfirmNftTransferFragment confirmNftTransferFragment = new ConfirmNftTransferFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        confirmNftTransferFragment.setArguments(bundle);
        return confirmNftTransferFragment;
    }

    @Override
    public void processData() {
        super.processData();
        BaseParam baseParam = this.baseParam;
        this.param = baseParam;
        if (baseParam == null) {
            return;
        }
        try {
            bindDataToUI();
            addAccountCallback(this.headerView, this.resourceView, this.btnConfirm);
            ensureAccount();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void bindDataToUI() {
        String shortName;
        BaseParam baseParam = this.param;
        if (baseParam != null && (baseParam instanceof NFTParam)) {
            NFTParam nFTParam = (NFTParam) baseParam;
            ViewGroup.LayoutParams layoutParams = this.rootLayout.getLayoutParams();
            layoutParams.height = this.param.getLayoutHeight();
            this.rootLayout.setLayoutParams(layoutParams);
            if (StringTronUtil.isEmpty(nFTParam.getShortName())) {
                shortName = nFTParam.getTokenId();
            } else {
                shortName = nFTParam.getShortName();
            }
            this.tvAmount.setText(shortName);
            this.rlAmount.setVisibility(View.GONE);
            this.tvAmount.setText("0 TRX");
            if (this.param.isActives()) {
                this.rlType.setVisibility(View.VISIBLE);
            } else {
                this.rlType.setVisibility(View.GONE);
            }
            this.rlContract.setVisibility(View.VISIBLE);
            this.tvContractAddress.setText(nFTParam.getContractAddress());
            this.tvContractAddress.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    UIUtils.toContractDetailProtocol(getIContext(), tvContractAddress.getText().toString());
                }
            });
            this.tvReceivedAddress.setText(nFTParam.getToAddress());
            this.tvOutAddress.setText(nFTParam.getFromAddress());
            this.tvOwnerName.setText(Utils.getNameByAddress(nFTParam.getFromAddress()));
            this.tvReceivedName.setText(Utils.getNameByAddress(nFTParam.getToAddress()));
            this.tvName.setText(nFTParam.getName());
            if (!StringTronUtil.isEmpty(nFTParam.getNote())) {
                this.rlNote.setVisibility(View.VISIBLE);
                this.tvNote.setText(nFTParam.getNote());
            } else {
                this.rlNote.setVisibility(View.GONE);
            }
            BaseParam baseParam2 = this.param;
            baseParam2.addInfoTitle(R.string.asset_transfer, "#" + nFTParam.getTokenId() + " " + nFTParam.getShortName());
            this.param.setTitle(R.string.confirmtransaction, R.string.et_input_password);
            this.resourceView.bindData(this.param);
            this.headerView.bindData(this.param);
            this.btnConfirm.onBind(this.param, true, ((NFTParam) this.baseParam).getToAddress());
            checkScamAddress();
            this.resourceView.setFeeResourceCallback(this.btnConfirm);
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
        }
    }

    private void checkScamAddress() {
        this.btnConfirm.onBind(this.baseParam, true, ((NFTParam) this.baseParam).getToAddress());
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$checkScamAddress$1();
            }
        });
    }

    public void lambda$checkScamAddress$1() {
        new ArrayList().add(((NFTParam) this.baseParam).getToAddress());
        AddressManager.checkSingleAddressScam(((NFTParam) this.baseParam).getToAddress()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$checkScamAddress$0((RiskAccountOutput) obj);
            }
        });
    }

    public void lambda$checkScamAddress$0(RiskAccountOutput riskAccountOutput) throws Exception {
        boolean isRisk = riskAccountOutput.getData().isRisk();
        this.btnConfirm.setFinishGetScam(isRisk);
        if (isRisk) {
            this.addressScamTag.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgConfirmNftTransferBinding inflate = FgConfirmNftTransferBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.headerView = this.binding.headerView;
        this.rootLayout = this.binding.root;
        this.rlAddress = this.binding.rlAddress;
        this.tvOutAddress = this.binding.transferOutAddress;
        this.tvReceivedAddress = this.binding.receivingAddress;
        this.tvOwnerName = this.binding.transferOutName;
        this.tvReceivedName = this.binding.receivingName;
        this.rlAmount = this.binding.rlAmount;
        this.tvAmount = this.binding.tvAmount;
        this.rlContract = this.binding.rlContract;
        this.tvContractAddress = this.binding.tvContractAddress;
        this.tvName = this.binding.tvContractName;
        this.rlTokenId = this.binding.rlTokenId;
        this.tvTokenId = this.binding.tvTokenId;
        this.rlType = this.binding.rlType;
        this.rlNote = this.binding.rlNote;
        this.tvNote = this.binding.tvNote;
        this.resourceView = this.binding.resourceView;
        this.btnConfirm = this.binding.btnConfirm;
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
