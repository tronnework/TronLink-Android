package com.tron.wallet.business.confirm.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentTransaction;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ledger.ConfirmLedgerFragment;
import com.tron.wallet.business.confirm.core.samsung.SamsungConfirmFragment;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ManagePermissionGroupParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ParticipateMultisignParam;
import com.tron.wallet.business.pull.IntentResult;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.AnimationUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcConfirmNewBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TransactionCapsule;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class ConfirmTransactionNewActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private static final String SAMSUNG = "samsung";
    private int WalletType;
    private FragmentTransaction beginTransaction;
    private AcConfirmNewBinding binding;
    FrameLayout confirm;
    private ConfirmTransactionPresenter confirmTransactionPresenter;
    FrameLayout containerBottom;
    FrameLayout containerTop;
    private View currentVisibleFrame;
    private BaseFragment fragment1;
    private ConfirmMultisignFragment fragment2;
    private BaseFragment fragment3;
    private boolean isSamsung;
    private boolean isSmartContract;
    private int pageIndex;
    private BaseParam param;
    RelativeLayout root;

    public void updateParam(BaseParam baseParam) {
        this.param = baseParam;
    }

    public static void startActivity(Context context, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) {
        Intent intent = new Intent();
        intent.setClass(context, ConfirmTransactionNewActivity.class);
        baseConfirmTransParamBuilder.build(intent);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder, boolean z) {
        Intent intent = new Intent();
        intent.setClass(context, ConfirmTransactionNewActivity.class);
        baseConfirmTransParamBuilder.build(intent);
        intent.putExtra(SAMSUNG, z);
        context.startActivity(intent);
    }

    public static void startForResult(Activity activity, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder, int i) {
        Intent intent = new Intent();
        intent.setClass(activity, ConfirmTransactionNewActivity.class);
        baseConfirmTransParamBuilder.build(intent);
        activity.startActivityForResult(intent, i);
    }

    public static void startForResult(Activity activity, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder, int i, boolean z) {
        Intent intent = new Intent();
        intent.setClass(activity, ConfirmTransactionNewActivity.class);
        baseConfirmTransParamBuilder.build(intent);
        intent.putExtra(SAMSUNG, z);
        activity.startActivityForResult(intent, i);
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(1);
        }
    }

    @Override
    protected void setLayout() {
        AcConfirmNewBinding inflate = AcConfirmNewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.containerTop = this.binding.containerTop;
        this.containerBottom = this.binding.containerBottom;
        this.confirm = this.binding.confirm;
        this.root = this.binding.root;
    }

    @Override
    protected void processData() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity.processData():void");
    }

    static class fun1 {
        static final int[] $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType;

        static {
            int[] iArr = new int[Protocol.Transaction.Contract.ContractType.values().length];
            $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType = iArr;
            try {
                iArr[Protocol.Transaction.Contract.ContractType.AccountCreateContract.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferContract.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferAssetContract.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ShieldedTransferContract.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.VoteAssetContract.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.VoteWitnessContract.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WitnessCreateContract.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WitnessUpdateContract.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeBalanceContract.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeBalanceV2Contract.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WithdrawBalanceContract.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.FreezeBalanceContract.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.FreezeBalanceV2Contract.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.AccountPermissionUpdateContract.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WithdrawExpireUnfreezeContract.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.DelegateResourceContract.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnDelegateResourceContract.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.CancelAllUnfreezeV2Contract.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeAssetContract.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UpdateAssetContract.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.CustomContract.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UNRECOGNIZED.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TriggerSmartContract.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ProposalCreateContract.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ProposalApproveContract.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ProposalDeleteContract.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
        }
    }

    public void lambda$processData$0() {
        int measuredHeight = this.containerTop.getMeasuredHeight();
        LogUtils.w("Confirm", "height = " + measuredHeight);
    }

    public void JumpkTo(int i) {
        if (i == 1) {
            setV(this.containerTop, this.containerBottom, this.confirm);
        } else if (i != 2) {
            if (i == 3) {
                int i2 = this.pageIndex;
                if (i2 == 1 || i2 == 2 || !this.param.hasOwnerPermission()) {
                    this.confirm.setVisibility(View.VISIBLE);
                    initConfirmFragment(this.param);
                } else {
                    this.confirm.setVisibility(View.VISIBLE);
                }
            }
        } else if (this.pageIndex == 1) {
            initMutisignFragment();
        } else {
            setV(this.containerBottom, this.containerTop, this.confirm);
        }
        logEvent(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_CONFIRM);
    }

    public void backUp(int i) {
        if (i == 1) {
            backEvent();
            exit();
        } else if (i != 2) {
            if (i == 3) {
                if (!this.param.hasOwnerPermission()) {
                    setV(this.containerBottom, this.containerTop, this.confirm);
                } else if (this.param.getPageIndex() == 1) {
                    setV(this.containerTop, this.containerBottom, this.confirm);
                } else {
                    exit();
                }
            }
        } else if (this.param.getPageIndex() == 1) {
            setV(this.containerTop, this.containerBottom, this.confirm);
        } else {
            exit();
        }
        logEvent(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_BACK);
    }

    public void show(FrameLayout frameLayout, FrameLayout frameLayout2) {
        frameLayout.setVisibility(View.GONE);
        frameLayout2.setVisibility(View.VISIBLE);
        frameLayout.setAnimation(AnimationUtil.moveToViewLeft());
        frameLayout2.setAnimation(AnimationUtil.moveToViewRightLocation());
    }

    public void hide(RelativeLayout relativeLayout, RelativeLayout relativeLayout2) {
        relativeLayout.setVisibility(View.VISIBLE);
        relativeLayout2.setVisibility(View.GONE);
        relativeLayout.setAnimation(AnimationUtil.moveToViewLeftLocation());
        relativeLayout2.setAnimation(AnimationUtil.moveToViewRight());
    }

    public void setV(View view, View view2, View view3) {
        view.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
        this.currentVisibleFrame = view;
    }

    private void initConfirmFragment(BaseParam baseParam) {
        BaseFragment baseFragment = this.fragment3;
        if (baseFragment != null) {
            if (this.WalletType == 8) {
                ((ConfirmLedgerFragment) baseFragment).setParam(baseParam);
                ((ConfirmLedgerFragment) this.fragment3).update();
                return;
            } else if (this.isSamsung) {
                ((SamsungConfirmFragment) baseFragment).setParam(baseParam);
                ((SamsungConfirmFragment) this.fragment3).update();
                return;
            } else {
                ((ConfirmTransactionFragment) baseFragment).setParam(baseParam);
                ((ConfirmTransactionFragment) this.fragment3).update();
                return;
            }
        }
        if (this.WalletType == 8) {
            ConfirmLedgerFragment confirmLedgerFragment = new ConfirmLedgerFragment();
            this.fragment3 = confirmLedgerFragment;
            confirmLedgerFragment.setParam(baseParam);
        } else if (this.isSamsung) {
            SamsungConfirmFragment samsungConfirmFragment = new SamsungConfirmFragment();
            this.fragment3 = samsungConfirmFragment;
            samsungConfirmFragment.setParam(baseParam);
        } else {
            ConfirmTransactionFragment confirmTransactionFragment = new ConfirmTransactionFragment();
            this.fragment3 = confirmTransactionFragment;
            confirmTransactionFragment.setParam(baseParam);
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.beginTransaction = beginTransaction;
        beginTransaction.add(R.id.confirm, this.fragment3);
        this.beginTransaction.disallowAddToBackStack();
        this.beginTransaction.commitAllowingStateLoss();
    }

    private void initMutisignFragment() {
        if (this.fragment2 == null) {
            this.fragment2 = new ConfirmMultisignFragment();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            this.beginTransaction = beginTransaction;
            beginTransaction.replace(R.id.container_bottom, this.fragment2);
            this.beginTransaction.disallowAddToBackStack();
            this.beginTransaction.commitAllowingStateLoss();
        }
        setV(this.containerBottom, this.containerTop, this.confirm);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        BaseFragment baseFragment = this.fragment3;
        if (baseFragment != null) {
            baseFragment.onActivityResult(i, i2, intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        BaseFragment baseFragment = this.fragment3;
        if (baseFragment == null || !(baseFragment instanceof ConfirmLedgerFragment)) {
            return;
        }
        ((ConfirmLedgerFragment) baseFragment).onRequestPermissionResult(i, strArr, iArr);
    }

    public void exit2() {
        Toast(R.string.could_not_parse_transaction);
        super.exit();
    }

    public Wallet getCurrentWallet() {
        BaseParam baseParam = this.param;
        if (baseParam instanceof ParticipateMultisignParam) {
            return WalletUtils.getWallet(((ParticipateMultisignParam) baseParam).getWalletName());
        }
        if (baseParam instanceof ManagePermissionGroupParam) {
            return WalletUtils.getWallet(((ManagePermissionGroupParam) baseParam).getWalletName());
        }
        if (baseParam != null && baseParam.getQrBean() != null && !StringTronUtil.isNullOrEmpty(this.param.getQrBean().getAddress())) {
            return WalletUtils.getWalletForAddress(this.param.getQrBean().getAddress());
        }
        return WalletUtils.getSelectedWallet();
    }

    private void logEvent(String str) {
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(this.isSmartContract ? 2 : 1);
        AnalyticsHelper.logEvent(String.format(str, objArr));
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            BaseFragment baseFragment = this.fragment3;
            if (baseFragment != null) {
                baseFragment.onKeyDownChild(i, keyEvent);
            }
            backEvent();
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void backEvent() {
        if (this.param.getPageFrom() == BaseParam.PageFrom.DeepLink) {
            try {
                Intent intent = new Intent();
                intent.putExtra(IntentResult.ResultKey, IntentResult.Canceled);
                setResult(-1, intent);
                finish();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } else if (this.param.getPageFrom() == BaseParam.PageFrom.Financial) {
            ((EmptyPresenter) this.mPresenter).mRxManager.post(Event.EVENT_STAKE_AND_VOTE_DONE, "cancel");
        }
    }

    public void updateLedgerParam(BaseParam baseParam) {
        if (this.WalletType == 8 && !baseParam.isActives() && baseParam.getPageFrom() == BaseParam.PageFrom.Local) {
            List<byte[]> bytes = baseParam.getTransactionBean().getBytes();
            ArrayList arrayList = new ArrayList();
            for (byte[] bArr : bytes) {
                try {
                    Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(bArr);
                    arrayList.add(TransactionUtils.setExpiration(parseFrom, parseFrom.getRawData().getTimestamp() + TransactionCapsule.TRANSACTION_DEFAULT_EXPIRATION_TIME_120).toByteArray());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (arrayList.size() == 0 || arrayList.size() != bytes.size()) {
                return;
            }
            baseParam.clearThenAddTransactions(arrayList);
        }
    }
}
