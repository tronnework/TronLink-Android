package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappMetadataParam;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.LayoutTransactionMetadataFragmentBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.crypto.Hash;
import org.tron.common.utils.ByteArray;
import org.tron.protos.Protocol;
import org.tron.walletserver.TriggerData;
public class TransactionMetadataFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private LayoutTransactionMetadataFragmentBinding binding;
    RelativeLayout functionParamLayout;
    private DappMetadataParam param;
    RecyclerView paramsRecyclerView;
    View tvCopy;
    TextView tvFunctionName;
    TextView tvFunctionParam;
    TextView tvHexadecimalData;

    private void initRecyclerView() {
    }

    public static TransactionMetadataFragment getInstance(BaseParam baseParam) {
        TransactionMetadataFragment transactionMetadataFragment = new TransactionMetadataFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        transactionMetadataFragment.setArguments(bundle);
        return transactionMetadataFragment;
    }

    @Override
    protected void processData() {
        initRecyclerView();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.param = (DappMetadataParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM);
        }
        if (this.param == null) {
            return;
        }
        try {
            bindDataToUI();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void bindDataToUI() {
        if (this.param == null) {
            return;
        }
        this.paramsRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getIContext()));
        ContractParamsAdapter contractParamsAdapter = new ContractParamsAdapter(getIContext());
        this.paramsRecyclerView.setAdapter(contractParamsAdapter);
        if (this.param.getTriggerData() != null) {
            if (StringTronUtil.isEmpty(this.param.getTriggerData().getMethodNoParams())) {
                this.tvFunctionName.setText(getIContext().getResources().getString(R.string.unknown));
            } else {
                TextView textView = this.tvFunctionName;
                textView.setText(this.param.getTriggerData().getMethodNoParams() + calulateMethodId(this.param.getTriggerData().getMethod()));
            }
            if (this.param.getTriggerData().parseDataForTypeValueList() != null && this.param.getTriggerData().parseDataForTypeValueList().size() > 0) {
                contractParamsAdapter.notifyData(this.param.getTriggerData().parseDataForTypeValueList());
            } else {
                this.paramsRecyclerView.setVisibility(View.GONE);
                this.functionParamLayout.setVisibility(View.GONE);
            }
        } else {
            try {
                String printContract = WalletUtils.printContract(Protocol.Transaction.parseFrom(this.param.getTransactionBean().getBytes().get(0)));
                this.tvFunctionName.setText(this.param.getContractMethodName());
                ArrayList arrayList = new ArrayList();
                for (Map.Entry entry : ((HashMap) JSON.parseObject(printContract, HashMap.class)).entrySet()) {
                    TriggerData.TypeValue typeValue = new TriggerData.TypeValue();
                    String obj = entry.getValue().toString();
                    typeValue.setType((String) entry.getKey());
                    typeValue.setValue(obj);
                    arrayList.add(typeValue);
                }
                if (arrayList.size() > 0) {
                    contractParamsAdapter.notifyData(arrayList);
                } else {
                    this.paramsRecyclerView.setVisibility(View.GONE);
                    this.functionParamLayout.setVisibility(View.GONE);
                }
            } catch (InvalidProtocolBufferException e) {
                LogUtils.e(e);
                this.paramsRecyclerView.setVisibility(View.GONE);
                this.functionParamLayout.setVisibility(View.GONE);
            }
        }
        this.tvHexadecimalData.setText(Hex.toHexString(this.param.getTransactionBean().getBytes().get(0)));
        this.tvCopy.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                UIUtils.copy(tvHexadecimalData.getText().toString());
                TransactionMetadataFragment transactionMetadataFragment = TransactionMetadataFragment.this;
                transactionMetadataFragment.toast(transactionMetadataFragment.getString(R.string.copy_susscess));
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LayoutTransactionMetadataFragmentBinding inflate = LayoutTransactionMetadataFragmentBinding.inflate(layoutInflater, viewGroup, false);
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
        this.tvFunctionName = this.binding.tvFunctionContractName;
        this.tvFunctionParam = this.binding.tvFunctionParam;
        this.tvHexadecimalData = this.binding.hexadecimalDataContent;
        this.tvCopy = this.binding.tvCopyHex;
        this.functionParamLayout = this.binding.rlFunctionParam;
        this.paramsRecyclerView = this.binding.rlParams;
    }

    public void setParam(DappMetadataParam dappMetadataParam) {
        this.param = dappMetadataParam;
        bindDataToUI();
    }

    public String calulateMethodId(String str) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return "";
        }
        byte[] bArr = new byte[4];
        System.arraycopy(Hash.sha3(str.getBytes()), 0, bArr, 0, 4);
        return " (" + ByteArray.toHexString(bArr) + ")";
    }
}
