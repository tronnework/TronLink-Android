package com.tron.wallet.business.confirm.sign;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.confirm.sign.SignTransactionNewContract;
import com.tron.wallet.business.confirm.signed.SignedTransactionNewActivity;
import com.tron.wallet.common.adapter.RecyclerItemClickListener;
import com.tron.wallet.common.adapter.SignTransactionBottomAdapter;
import com.tron.wallet.common.adapter.SignTransactionPagerAdapter;
import com.tron.wallet.common.adapter.SignTransactionTopAdapter;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.utils.ByteArray;
import org.tron.protos.Protocol;
public class SignTransactionNewPresenter extends SignTransactionNewContract.Presenter {
    private String FROM_W;
    private int lastIndex;
    private int mCurIndex;
    private QrBean qrBean;
    private SignTransactionBottomAdapter signBottomAdapter;
    private List<SignContentFragment> signFgs;
    private SignTransactionTopAdapter signTopAdapter;
    private int size;
    private TokenBean tokenBean;
    private int transactionType;

    @Override
    protected void onStart() {
    }

    @Override
    public void init() {
        Bundle extras = ((SignTransactionNewContract.View) this.mView).getIIntent().getExtras();
        if (extras != null) {
            try {
                this.qrBean = (QrBean) extras.getParcelable(TronConfig.QR_CODE_DATA);
                this.tokenBean = (TokenBean) extras.getParcelable(TronConfig.TOKEN_DATA);
                this.FROM_W = extras.getString(TronConfig.FROM_W);
                QrBean qrBean = this.qrBean;
                if (qrBean != null) {
                    this.transactionType = qrBean.getType();
                    List<SignContentFragment> fg = getFg(((SignTransactionNewContract.Model) this.mModel).subSign(this.qrBean));
                    this.signFgs = fg;
                    this.size = fg.size();
                    ((SignTransactionNewContract.View) this.mView).updateUI(this.size, this.FROM_W, ((SignTransactionNewContract.Model) this.mModel).isBiggerThanQrLimitSize());
                    if (this.size <= 2) {
                        ((SignTransactionNewContract.View) this.mView).getVP().setOffscreenPageLimit(1);
                    } else {
                        ((SignTransactionNewContract.View) this.mView).getVP().setOffscreenPageLimit(this.size / 2);
                    }
                    CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                    compositePageTransformer.addTransformer(new MarginPageTransformer(DensityUtils.dp2px(((SignTransactionNewContract.View) this.mView).getIContext(), 36.0f)));
                    compositePageTransformer.addTransformer(new ScaleInTransformer(((SignTransactionNewContract.View) this.mView).getIContext()));
                    ((SignTransactionNewContract.View) this.mView).getVP().setPageTransformer(compositePageTransformer);
                    ((SignTransactionNewContract.View) this.mView).getVP().setAdapter(new SignTransactionPagerAdapter((FragmentActivity) ((SignTransactionNewContract.View) this.mView).getIContext(), this.signFgs));
                    this.signTopAdapter = new SignTransactionTopAdapter(this.signFgs.size());
                    this.signBottomAdapter = new SignTransactionBottomAdapter(this.signFgs.size());
                    ((SignTransactionNewContract.View) this.mView).setTopAdapter(this.signTopAdapter);
                    ((SignTransactionNewContract.View) this.mView).setBottomAdapter(this.signBottomAdapter);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public final void run() {
                            lambda$init$0();
                        }
                    }, 50L);
                    return;
                }
                ((SignTransactionNewContract.View) this.mView).cancle();
                ((SignTransactionNewContract.View) this.mView).ToastAsBottom(R.string.parsererror);
            } catch (Exception e) {
                LogUtils.e(e);
                ((SignTransactionNewContract.View) this.mView).cancle();
                ((SignTransactionNewContract.View) this.mView).ToastAsBottom(R.string.parsererror);
            }
        }
    }

    public void lambda$init$0() {
        updateAlpha(this.mCurIndex);
    }

    @Override
    public void buttonNext() {
        int i = this.size;
        if (i == 1 || this.lastIndex >= i - 1) {
            if (TronConfig.COLD_W.equals(this.FROM_W)) {
                this.mRxManager.post(RB.RB_SIGNED_FINISH, "111");
                ((SignTransactionNewContract.View) this.mView).exit();
                return;
            } else if (TronConfig.OB_W.equals(this.FROM_W)) {
                ((SignTransactionNewContract.View) this.mView).requestPermissionToScan();
            }
        }
        this.lastIndex++;
        this.mCurIndex++;
        ((SignTransactionNewContract.View) this.mView).getVP().setCurrentItem(this.mCurIndex);
    }

    public void updateButtonText() {
        if (this.lastIndex >= this.size - 1) {
            if (TronConfig.COLD_W.equals(this.FROM_W)) {
                ((SignTransactionNewContract.View) this.mView).updateButtonText(R.string.internet_connected);
            } else {
                ((SignTransactionNewContract.View) this.mView).updateButtonText(R.string.qr_detail_15);
            }
        } else if (TronConfig.COLD_W.equals(this.FROM_W)) {
            ((SignTransactionNewContract.View) this.mView).updateButtonText(R.string.scan_wallet_sign_next);
        } else {
            ((SignTransactionNewContract.View) this.mView).updateButtonText(R.string.qr_detail_2);
        }
    }

    public void updateAdapter(int i) {
        SignTransactionTopAdapter signTransactionTopAdapter = this.signTopAdapter;
        if (signTransactionTopAdapter != null) {
            signTransactionTopAdapter.updateIndex(i);
        }
        SignTransactionBottomAdapter signTransactionBottomAdapter = this.signBottomAdapter;
        if (signTransactionBottomAdapter != null) {
            signTransactionBottomAdapter.updateIndex(i, this.lastIndex);
        }
    }

    public void updateAlpha(int i) {
        List<SignContentFragment> list = this.signFgs;
        if (list == null || list.size() <= 1) {
            return;
        }
        for (int i2 = 0; i2 < this.signFgs.size(); i2++) {
            if (i2 == i) {
                this.signFgs.get(i2).updateBg(1.0f);
            } else {
                this.signFgs.get(i2).updateBg(0.3f);
            }
        }
    }

    @Override
    public void addListener(RecyclerView recyclerView) {
        ((SignTransactionNewContract.View) this.mView).getVP().registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                mCurIndex = i;
                lastIndex = i;
                updateAdapter(i);
                updateAlpha(i);
                updateButtonText();
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(((SignTransactionNewContract.View) this.mView).getIContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onLongItemClick(View view, int i) {
            }

            @Override
            public void onItemClick(View view, int i) {
                if (lastIndex < i) {
                    return;
                }
                mCurIndex = i;
                ((SignTransactionNewContract.View) mView).getVP().setCurrentItem(i);
            }
        }));
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("SCAN_RESULT");
            String stringExtra2 = intent.getStringExtra(TronConfig.QR_CODE_DATA);
            QrBean qrBean = (QrBean) new Gson().fromJson(!StringTronUtil.isEmpty(stringExtra) ? stringExtra : stringExtra2,  QrBean.class);
            this.qrBean = qrBean;
            if (qrBean.getcN() != this.qrBean.gettN() && this.qrBean.gettN() != 0 && this.qrBean.gettN() != 1) {
                SignedTransactionNewActivity.start((BaseActivity) ((SignTransactionNewContract.View) this.mView).getIContext(), this.qrBean, this.tokenBean, TronConfig.OB_W);
                return;
            }
            for (String str : this.qrBean.getHexList()) {
                if (this.qrBean.getType() != 98 && this.qrBean.getType() != 15 && this.qrBean.getType() != 102 && this.qrBean.getType() != 103 && this.qrBean.getType() != 104 && this.qrBean.getType() != 101) {
                    Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(Hex.decode(str));
                    if (parseFrom.getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.ShieldedTransferContract) {
                        break;
                    } else if (parseFrom != null && parseFrom.getSignatureCount() == 0) {
                        IToast.getIToast().showLongBottom(R.string.signfirst);
                        return;
                    }
                }
            }
            if (StringTronUtil.isEmpty(stringExtra)) {
                stringExtra = stringExtra2;
            }
            if (this.qrBean.getType() == 99 || this.transactionType == 99) {
                this.mRxManager.post(Event.WATCH_WALLET_VERIFY, stringExtra);
            }
            ((SignTransactionNewContract.View) this.mView).setResult(this.tokenBean, stringExtra);
        } catch (InvalidProtocolBufferException e) {
            ((SignTransactionNewContract.View) this.mView).ToastError(R.string.qr_detail_7);
            LogUtils.e(e);
        }
    }

    @Override
    public boolean isSignMessageV2() {
        QrBean qrBean = this.qrBean;
        if (qrBean != null) {
            int type = qrBean.getType();
            return type == 102 || type == 104 || type == 103;
        }
        return false;
    }

    @Override
    public boolean checkStakeV2() {
        List<String> hexList;
        QrBean qrBean = this.qrBean;
        if (qrBean != null && (hexList = qrBean.getHexList()) != null && hexList.size() != 0) {
            try {
                Protocol.Transaction.Contract.ContractType type = Protocol.Transaction.parseFrom(ByteArray.fromHexString(hexList.get(0))).getRawData().getContract(0).getType();
                if (type == Protocol.Transaction.Contract.ContractType.FreezeBalanceV2Contract || type == Protocol.Transaction.Contract.ContractType.UnfreezeBalanceV2Contract || type == Protocol.Transaction.Contract.ContractType.WithdrawExpireUnfreezeContract || type == Protocol.Transaction.Contract.ContractType.DelegateResourceContract || type == Protocol.Transaction.Contract.ContractType.UnDelegateResourceContract) {
                    return true;
                }
                if (type == Protocol.Transaction.Contract.ContractType.CancelAllUnfreezeV2Contract) {
                    return true;
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return false;
    }

    private List<SignContentFragment> getFg(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            SignContentFragment signContentFragment = new SignContentFragment();
            signContentFragment.setParam(str);
            arrayList.add(signContentFragment);
        }
        return arrayList;
    }
}
