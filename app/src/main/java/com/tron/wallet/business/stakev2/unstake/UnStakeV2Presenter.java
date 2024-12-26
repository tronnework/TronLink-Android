package com.tron.wallet.business.stakev2.unstake;

import android.app.Activity;
import android.util.Pair;
import android.widget.TextView;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.bean.FreezeUnFreezeParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.stakev2.ResState;
import com.tron.wallet.business.stakev2.managementv2.StakeManageDetailActivity;
import com.tron.wallet.business.stakev2.unstake.ResSwitch;
import com.tron.wallet.business.stakev2.unstake.UnStakeV2Activity;
import com.tron.wallet.business.stakev2.unstake.UnStakeV2Contract;
import com.tron.wallet.common.components.StakePercentView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.KeyboardUtil;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.Common;
import org.tron.walletserver.Wallet;
public class UnStakeV2Presenter extends UnStakeV2Contract.Presenter {
    public static final String Key_Account = "Key_Account";
    public static final String Key_AccountResourceMessage = "Key_AccountResourceMessage";
    public static final String Key_ControlAddress = "Key_ControlAddress";
    public static final String Key_ControlName = "Key_ControlName";
    private Protocol.Account account;
    private GrpcAPI.AccountResourceMessage accountResourceMessage;
    private String controlAddress;
    private UnStakeV2Model mModel;
    private UnStakeV2Bean unStakeV2Bean = new UnStakeV2Bean();
    private ResState resState = ResState.Energy;
    private long maximum = 32;
    private long lastInput = 0;

    @Override
    protected void onStart() {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet == null) {
            ((UnStakeV2Contract.View) this.mView).exit();
            return;
        }
        String stringExtra = ((UnStakeV2Contract.View) this.mView).getIIntent().getStringExtra(Key_ControlAddress);
        this.controlAddress = stringExtra;
        if (StringTronUtil.isEmpty(stringExtra)) {
            this.controlAddress = selectedPublicWallet.getAddress();
        }
        this.account = (Protocol.Account) ((UnStakeV2Contract.View) this.mView).getIIntent().getSerializableExtra(Key_Account);
        this.accountResourceMessage = (GrpcAPI.AccountResourceMessage) ((UnStakeV2Contract.View) this.mView).getIIntent().getSerializableExtra(Key_AccountResourceMessage);
        this.mModel = new UnStakeV2Model();
        this.mRxManager.on(Event.BroadcastSuccess2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        ((UnStakeV2Contract.View) this.mView).exit();
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        ((UnStakeV2Contract.View) this.mView).exit();
    }

    @Override
    public void loadData() {
        ((UnStakeV2Contract.View) this.mView).showLoadingDialog();
        Observable.zip(this.mModel.getAccount(this.account, this.controlAddress), this.mModel.getAccountResourceMessage(this.accountResourceMessage, this.controlAddress), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                return UnStakeV2Presenter.lambda$loadData$2((Protocol.Account) obj, (GrpcAPI.AccountResourceMessage) obj2);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<Pair<Protocol.Account, GrpcAPI.AccountResourceMessage>>() {
            @Override
            public void onResponse(String str, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
                ((UnStakeV2Contract.View) mView).dismissLoadingDialog();
                if (pair.first == null || pair.second == null) {
                    return;
                }
                account = (Protocol.Account) pair.first;
                accountResourceMessage = (GrpcAPI.AccountResourceMessage) pair.second;
                UnStakeV2Presenter unStakeV2Presenter = UnStakeV2Presenter.this;
                unStakeV2Presenter.unStakeV2Bean = unStakeV2Presenter.mModel.getViewData((Protocol.Account) pair.first, (GrpcAPI.AccountResourceMessage) pair.second);
                ((UnStakeV2Contract.View) mView).updateUI(unStakeV2Bean, resState);
                resetInput();
            }

            @Override
            public void onFailure(String str, String str2) {
                ((UnStakeV2Contract.View) mView).dismissLoadingDialog();
                ((UnStakeV2Contract.View) mView).toast(((UnStakeV2Contract.View) mView).getIContext().getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "UnStakeV2Presenter_GetRes"));
    }

    public static Pair lambda$loadData$2(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) throws Exception {
        return new Pair(account, accountResourceMessage);
    }

    @Override
    public ResSwitch.OnResSwitchListener OnResSwitchListener() {
        return new ResSwitch.OnResSwitchListener() {
            @Override
            public final void onResSwitch(ResState resState) {
                lambda$OnResSwitchListener$3(resState);
            }
        };
    }

    public void lambda$OnResSwitchListener$3(ResState resState) {
        this.resState = resState;
        AnalyticsHelper.UnStakeV2.logEventUnStakeV2ClickTab(resState);
        resetInput();
        ((UnStakeV2Contract.View) this.mView).resetPercent(resState);
        ((UnStakeV2Contract.View) this.mView).updateUI(this.unStakeV2Bean, resState);
    }

    public void resetInput() {
        ((UnStakeV2Contract.View) this.mView).updateInput(BigDecimalUtils.toString(Long.valueOf(this.resState == ResState.Energy ? this.unStakeV2Bean.getEnergySum() : this.unStakeV2Bean.getBandwidthSum())), BigDecimalUtils.toString(Long.valueOf(this.unStakeV2Bean.getVotesSum())));
        ((UnStakeV2Contract.View) this.mView).showError(UnStakeV2Activity.Error.None);
        ((UnStakeV2Contract.View) this.mView).setInputText("");
    }

    @Override
    public StakePercentView.OnClickPercentListener OnClickPercentListener() {
        return new StakePercentView.OnClickPercentListener() {
            @Override
            public final void onClickPercent(float f, TextView textView, StakePercentView.Percent percent) {
                lambda$OnClickPercentListener$4(f, textView, percent);
            }
        };
    }

    public void lambda$OnClickPercentListener$4(float f, TextView textView, StakePercentView.Percent percent) {
        KeyboardUtil.closeKeybord((Activity) ((UnStakeV2Contract.View) this.mView).getIContext());
        String logEventString = AnalyticsHelper.UnStakeV2.getLogEventString(this.resState);
        AnalyticsHelper.logEvent(logEventString + percent.getCode());
        if (this.resState == ResState.Energy) {
            if (this.unStakeV2Bean.getAvailableUnFreezeEnergy() > 0) {
                setInputText(String.valueOf(BigDecimalUtils.mul_(Long.valueOf(this.unStakeV2Bean.getAvailableUnFreezeEnergy()), Float.valueOf(f)).longValue()));
            } else {
                setInputText("0");
            }
        } else if (this.resState == ResState.Bandwidth) {
            if (this.unStakeV2Bean.getAvailableUnFreezeBandwidth() > 0) {
                setInputText(String.valueOf(BigDecimalUtils.mul_(Long.valueOf(this.unStakeV2Bean.getAvailableUnFreezeBandwidth()), Float.valueOf(f)).longValue()));
            } else {
                setInputText("0");
            }
        }
    }

    @Override
    public void next(final boolean z) {
        try {
            final String inputText = ((UnStakeV2Contract.View) this.mView).getInputText();
            final long longValue = NumberFormat.getNumberInstance(Locale.US).parse(inputText).longValue();
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null && selectedPublicWallet.getCreateType() == 7) {
                ((UnStakeV2Contract.View) this.mView).toast(((UnStakeV2Contract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
            } else if (checkInput(inputText)) {
                String logEventString = AnalyticsHelper.UnStakeV2.getLogEventString(this.resState);
                AnalyticsHelper.logEvent(logEventString + AnalyticsHelper.UnStakeV2.UN_STAKE);
                ((UnStakeV2Contract.View) this.mView).showLoadingDialog();
                ((UnStakeV2Contract.View) this.mView).setButtonEnable(false);
                ((UnStakeV2Contract.View) this.mView).runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$next$7(longValue, z, inputText);
                    }
                });
            }
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
            ((UnStakeV2Contract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$next$8();
                }
            });
        }
    }

    public void lambda$next$7(long j, boolean z, String str) {
        Common.ResourceCode resourceCode;
        if (this.resState == ResState.Bandwidth) {
            resourceCode = Common.ResourceCode.BANDWIDTH;
        } else {
            resourceCode = Common.ResourceCode.ENERGY;
        }
        GrpcAPI.TransactionExtention UnfreezeBalanceV2 = TronAPI.UnfreezeBalanceV2(this.controlAddress, BigDecimalUtils.mul_(Long.valueOf(j), Double.valueOf(Math.pow(10.0d, 6.0d))).longValue(), resourceCode);
        GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage canWithdrawUnfreezeAmount = TronAPI.getCanWithdrawUnfreezeAmount(this.controlAddress);
        long amount = canWithdrawUnfreezeAmount == null ? 0L : canWithdrawUnfreezeAmount.getAmount();
        ((UnStakeV2Contract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$next$5();
            }
        });
        if (UnfreezeBalanceV2 != null) {
            if (TransactionUtils.checkTransactionEmpty(UnfreezeBalanceV2)) {
                ((UnStakeV2Contract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
                return;
            }
            Protocol.Transaction transaction = UnfreezeBalanceV2.getTransaction();
            int i = this.resState == ResState.Energy ? 0 : 1;
            String[] powerNum = getPowerNum(j);
            HashMap<String, String> addressWithUnfreezeNum = getAddressWithUnfreezeNum(j);
            ArrayList arrayList = new ArrayList();
            arrayList.add(transaction);
            ConfirmTransactionNewActivity.startActivity(((UnStakeV2Contract.View) this.mView).getIContext(), ParamBuildUtils.getUnFreezeTransactionParamBuilder(i, z, FreezeUnFreezeParam.TYPE.FOR_SELF, powerNum, str, arrayList, addressWithUnfreezeNum, amount / 1000000));
            return;
        }
        ((UnStakeV2Contract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$next$6();
            }
        });
    }

    public void lambda$next$5() {
        ((UnStakeV2Contract.View) this.mView).setButtonEnable(true);
        ((UnStakeV2Contract.View) this.mView).dismissLoadingDialog();
    }

    public void lambda$next$6() {
        ((UnStakeV2Contract.View) this.mView).toast(StringTronUtil.getResString(R.string.net_error));
    }

    public void lambda$next$8() {
        ((UnStakeV2Contract.View) this.mView).toast(StringTronUtil.getResString(R.string.net_error));
    }

    private String[] getPowerNum(long j) {
        String[] strArr = new String[1];
        if (this.resState == ResState.Energy) {
            long energySum = this.unStakeV2Bean.getEnergySum() - this.mModel.getSurplusRes(this.resState, this.accountResourceMessage, this.unStakeV2Bean.getEnergySum(), j);
            strArr[0] = StringTronUtil.formatNumber6Truncate(Long.valueOf(energySum)) + " " + StringTronUtil.getResString(R.string.energy);
        } else {
            long bandwidthSum = this.unStakeV2Bean.getBandwidthSum() - this.mModel.getSurplusRes(this.resState, this.accountResourceMessage, this.unStakeV2Bean.getBandwidthSum(), j);
            strArr[0] = StringTronUtil.formatNumber6Truncate(Long.valueOf(bandwidthSum)) + " " + StringTronUtil.getResString(R.string.bandwidth);
        }
        return strArr;
    }

    private HashMap<String, String> getAddressWithUnfreezeNum(long j) {
        HashMap<String, String> hashMap = new HashMap<>();
        String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(this.controlAddress);
        String format = StringTronUtil.isEmpty(nameByAddress) ? this.controlAddress : String.format("%s\n(%s)", nameByAddress, this.controlAddress);
        hashMap.put(format, StringTronUtil.plainScientificNotation(Long.valueOf(j)) + " TRX");
        return hashMap;
    }

    @Override
    public void afterTextChanged(android.text.Editable r7) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.stakev2.unstake.UnStakeV2Presenter.afterTextChanged(android.text.Editable):void");
    }

    @Override
    boolean checkInput(String str) {
        long availableUnFreezeEnergy = this.resState == ResState.Energy ? this.unStakeV2Bean.getAvailableUnFreezeEnergy() : this.unStakeV2Bean.getAvailableUnFreezeBandwidth();
        boolean z = false;
        if (!StringTronUtil.isEmpty(str)) {
            try {
                Number parse = NumberFormat.getNumberInstance(Locale.US).parse(str);
                if (this.unStakeV2Bean.getMaximum() <= 0) {
                    ((UnStakeV2Contract.View) this.mView).showError(UnStakeV2Activity.Error.UnStake32);
                    ((UnStakeV2Contract.View) this.mView).setButtonEnable(false);
                } else if (BigDecimalUtils.equalsZero(parse)) {
                    ((UnStakeV2Contract.View) this.mView).showError(UnStakeV2Activity.Error.Min1);
                    ((UnStakeV2Contract.View) this.mView).setButtonEnable(false);
                } else if (BigDecimalUtils.MoreThan(parse, Long.valueOf(availableUnFreezeEnergy))) {
                    ((UnStakeV2Contract.View) this.mView).showError(UnStakeV2Activity.Error.MoreThan);
                    ((UnStakeV2Contract.View) this.mView).setButtonEnable(false);
                } else {
                    ((UnStakeV2Contract.View) this.mView).showError(UnStakeV2Activity.Error.None);
                    ((UnStakeV2Contract.View) this.mView).setButtonEnable(true);
                    z = true;
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } else {
            ((UnStakeV2Contract.View) this.mView).showError(UnStakeV2Activity.Error.None);
            ((UnStakeV2Contract.View) this.mView).setButtonEnable(false);
        }
        updateInputRes(str);
        return z;
    }

    @Override
    public void toManager(boolean z) {
        String logEventString = AnalyticsHelper.UnStakeV2.getLogEventString(this.resState);
        AnalyticsHelper.logEvent(logEventString + AnalyticsHelper.UnStakeV2.MANAGE);
        StakeManageDetailActivity.start(((UnStakeV2Contract.View) this.mView).getIContext(), this.controlAddress, this.resState == ResState.Energy ? 0 : 1, -1L, -1L, this.account, -1L, z, AddressNameUtils.getInstance().getNameByAddress(this.controlAddress), null);
    }

    private void updateInputRes(String str) {
        long energySum = this.resState == ResState.Energy ? this.unStakeV2Bean.getEnergySum() : this.unStakeV2Bean.getBandwidthSum();
        long availableUnFreezeEnergy = this.resState == ResState.Energy ? this.unStakeV2Bean.getAvailableUnFreezeEnergy() : this.unStakeV2Bean.getAvailableUnFreezeBandwidth();
        try {
            long longValue = !StringTronUtil.isEmpty(str) ? NumberFormat.getNumberInstance(Locale.US).parse(str).longValue() : 0L;
            if (longValue <= availableUnFreezeEnergy) {
                availableUnFreezeEnergy = longValue;
            }
        } catch (Exception e) {
            LogUtils.e(e);
            availableUnFreezeEnergy = 0;
        }
        if (availableUnFreezeEnergy == 0) {
            ((UnStakeV2Contract.View) this.mView).updateInput(String.valueOf(energySum), String.valueOf(this.unStakeV2Bean.getVotesSum()));
            return;
        }
        long surplusRes = this.mModel.getSurplusRes(this.resState, this.accountResourceMessage, energySum, availableUnFreezeEnergy);
        long votesSum = this.unStakeV2Bean.getVotesSum() - availableUnFreezeEnergy;
        ((UnStakeV2Contract.View) this.mView).updateInput(String.valueOf(surplusRes), String.valueOf(votesSum >= 0 ? votesSum : 0L));
    }

    public void setInputText(String str) {
        long j = 0;
        try {
            if (!StringTronUtil.isEmpty(str)) {
                j = NumberFormat.getNumberInstance(Locale.US).parse(str).longValue();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.lastInput = j;
        ((UnStakeV2Contract.View) this.mView).setInputText(str);
    }
}
