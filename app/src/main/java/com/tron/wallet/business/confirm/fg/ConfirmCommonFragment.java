package com.tron.wallet.business.confirm.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.FreezeUnFreezeParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ManagePermissionGroupParam;
import com.tron.wallet.common.adapter.ConfirmExtraTextAdapter;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BandwidthUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgConfirmCommonBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.tron.protos.Protocol;
public class ConfirmCommonFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private BaseParam baseParam;
    private FgConfirmCommonBinding binding;
    Button btSend;
    LinearLayout llErrorContent;
    RelativeLayout rlType;
    RecyclerView rvText;
    TextView tvFee;
    TextView tvFeeAmountBw;
    TextView tvHint;
    TextView tvRealMoney;
    TextView tvTitle;
    View vMiddle;
    private int walletType;

    public void setParam(BaseParam baseParam) {
        this.baseParam = baseParam;
    }

    @Override
    protected void processData() {
        updateUI();
        this.btSend.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                next();
            }
        });
    }

    private void updateUI() {
        if (this.baseParam == null) {
            return;
        }
        try {
            int createType = WalletUtils.getSelectedPublicWallet().getCreateType();
            this.walletType = createType;
            if (createType == 8 && this.baseParam.hasOwnerPermission()) {
                this.btSend.setText(R.string.request_from_ledger);
            } else {
                this.btSend.setText(R.string.confirm);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (!StringTronUtil.isEmpty(this.baseParam.getErrorHint())) {
            this.tvHint.setText(this.baseParam.getErrorHint());
        }
        if (this.baseParam.getConfirmExtraTitle() != null) {
            this.tvTitle.setText(this.baseParam.getConfirmExtraTitle().getDesTitle());
        }
        if (this.baseParam.isActives()) {
            this.rlType.setVisibility(View.VISIBLE);
        } else {
            this.rlType.setVisibility(View.GONE);
        }
        if (this.baseParam.getTextLists() != null && this.baseParam.getTextLists().size() != 0) {
            List<ConfirmExtraText> textLists = this.baseParam.getTextLists();
            if (textLists.size() != 0) {
                this.rvText.setVisibility(View.VISIBLE);
                this.vMiddle.setVisibility(View.VISIBLE);
                ConfirmExtraTextAdapter confirmExtraTextAdapter = new ConfirmExtraTextAdapter(textLists);
                this.rvText.setLayoutManager(new LinearLayoutManager(this.mContext));
                this.rvText.setAdapter(confirmExtraTextAdapter);
            }
        } else {
            this.rvText.setVisibility(View.GONE);
            this.vMiddle.setVisibility(View.GONE);
        }
        if (StringTronUtil.isEmpty(this.baseParam.getMoney())) {
            this.tvRealMoney.setVisibility(View.GONE);
        } else {
            this.tvRealMoney.setVisibility(View.VISIBLE);
            this.tvRealMoney.setText(this.baseParam.getMoney());
        }
        if (StringTronUtil.isEmpty(this.baseParam.getErrorHint())) {
            this.llErrorContent.setVisibility(View.GONE);
        } else {
            this.llErrorContent.setVisibility(View.VISIBLE);
            this.tvHint.setText(this.baseParam.getErrorHint());
        }
        try {
            setBandWidth(this.tvFee, this.tvFeeAmountBw, Protocol.Transaction.parseFrom(this.baseParam.getTransactionBean().getBytes().get(0)));
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e(e2);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgConfirmCommonBinding inflate = FgConfirmCommonBinding.inflate(layoutInflater, viewGroup, false);
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
        this.rvText = this.binding.rvText;
        this.tvTitle = this.binding.tvTitle;
        this.tvRealMoney = this.binding.tvRealMoney;
        this.tvHint = this.binding.tvHint;
        this.rlType = this.binding.rlType;
        this.vMiddle = this.binding.vMiddle;
        this.tvFee = this.binding.tvFee;
        this.tvFeeAmountBw = this.binding.tvFeeAmountBw;
        this.llErrorContent = this.binding.llErrorContent;
        this.btSend = this.binding.btSend;
    }

    public void next() {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$next$0();
            }
        });
    }

    public void lambda$next$0() {
        this.btSend.setEnabled(true);
        dismissLoadingDialog();
        ConfirmTransactionNewActivity confirmTransactionNewActivity = (ConfirmTransactionNewActivity) getActivity();
        if (this.baseParam.hasOwnerPermission()) {
            confirmTransactionNewActivity.JumpkTo(3);
        } else {
            confirmTransactionNewActivity.JumpkTo(2);
        }
    }

    public void setClick() {
        this.binding.ivCloseTwo.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() != R.id.iv_close_two) {
                    return;
                }
                mContext.finish();
            }
        });
    }

    public void setBandWidth(TextView textView, TextView textView2, Protocol.Transaction transaction) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        numberInstance.setMaximumFractionDigits(6);
        BaseParam baseParam = this.baseParam;
        if (baseParam instanceof ManagePermissionGroupParam) {
            textView.setText(baseParam.hasOwnerPermission() ? "100 TRX" : "101 TRX");
            textView2.setText(numberInstance.format(BandwidthUtils.getBandwidthCost(transaction)) + "\t" + getString(R.string.bandwidth));
        } else if (!BandwidthUtils.isEnoughBandwidth(getIContext(), BandwidthUtils.getBandwidthCost(transaction))) {
            long bandwidthCost = BandwidthUtils.getBandwidthCost(transaction);
            double d = bandwidthCost * TronConfig.feeBandWidth;
            if (this.baseParam.hasOwnerPermission()) {
                getResources().getString(R.string.no_band_width);
                textView.setText(numberInstance.format(d) + "\tTRX");
                if ((this.baseParam instanceof FreezeUnFreezeParam) && fun3.$SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[transaction.getRawData().getContract(0).getType().ordinal()] == 1) {
                    FreezeUnFreezeParam freezeUnFreezeParam = (FreezeUnFreezeParam) this.baseParam;
                    if ((freezeUnFreezeParam.getCanUseTrxCount() - freezeUnFreezeParam.getRealFreeze().doubleValue()) - d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        this.tvHint.setText(String.format(getString(R.string.no_enough_bandwidth, Integer.valueOf((int) bandwidthCost), numberInstance.format(d)), new Object[0]));
                        this.llErrorContent.setVisibility(View.VISIBLE);
                        this.btSend.setEnabled(false);
                    } else if (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        this.tvHint.setText(String.format(getString(R.string.no_enough_bandwidth_trx, numberInstance.format(d)), new Object[0]));
                        this.llErrorContent.setVisibility(View.VISIBLE);
                        this.btSend.setEnabled(true);
                    } else {
                        this.llErrorContent.setVisibility(View.GONE);
                    }
                }
            } else {
                textView.setText("");
            }
            textView.setText(numberInstance.format(d) + "\tTRX");
        } else {
            textView2.setText(numberInstance.format(BandwidthUtils.getBandwidthCost(transaction)) + "\t" + getString(R.string.bandwidth));
        }
    }

    public static class fun3 {
        static final int[] $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType;

        static {
            int[] iArr = new int[Protocol.Transaction.Contract.ContractType.values().length];
            $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType = iArr;
            try {
                iArr[Protocol.Transaction.Contract.ContractType.FreezeBalanceContract.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
