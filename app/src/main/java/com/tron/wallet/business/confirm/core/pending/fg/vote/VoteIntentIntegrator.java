package com.tron.wallet.business.confirm.core.pending.fg.vote;

import android.app.Activity;
import android.os.Handler;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ProfitParam;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.vote.component.VoteMainActivity;
import com.tron.wallet.common.bean.token.TransactionBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.StringTronUtil;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.WitnessContract;
public class VoteIntentIntegrator {
    public static void openVoteMainActivity(Activity activity, BaseParam baseParam) {
        TransactionBean transactionBean;
        MultiSignPermissionData multiSignPermissionData;
        if (baseParam == null || (transactionBean = baseParam.getTransactionBean()) == null || transactionBean.getBytes() == null || transactionBean.getBytes().size() == 0) {
            return;
        }
        try {
            String str = "";
            Protocol.Transaction.Contract contract = Protocol.Transaction.parseFrom(transactionBean.getBytes().get(0)).getRawData().getContract(0);
            if (contract.getType() == Protocol.Transaction.Contract.ContractType.VoteWitnessContract) {
                str = StringTronUtil.encode58Check(((WitnessContract.VoteWitnessContract) TransactionUtils.unpackContract(contract, WitnessContract.VoteWitnessContract.class)).getOwnerAddress().toByteArray());
                multiSignPermissionData = ((VoteParam) baseParam).getPermissionData();
            } else if (contract.getType() == Protocol.Transaction.Contract.ContractType.WithdrawBalanceContract) {
                str = StringTronUtil.encode58Check(((BalanceContract.WithdrawBalanceContract) TransactionUtils.unpackContract(contract, BalanceContract.WithdrawBalanceContract.class)).getOwnerAddress().toByteArray());
                multiSignPermissionData = ((ProfitParam) baseParam).getPermissionData();
            } else {
                multiSignPermissionData = null;
            }
            VoteMainActivity.start(activity, null, baseParam.isActives(), str, null, multiSignPermissionData);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RxBus.getInstance().post(Event.BackToVoteHome, Event.BackToVoteHome);
                }
            }, 800L);
            activity.finish();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
