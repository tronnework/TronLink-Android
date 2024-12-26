package com.tron.wallet.business.walletmanager.relation;

import android.os.Handler;
import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.walletmanager.relation.RelationContract;
import com.tron.wallet.business.walletmanager.relation.RelationPresenter;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
public class RelationPresenter extends RelationContract.Presenter {
    private static final int DEFAULT_RETRY_COUNT = 3;
    private Map<String, AccountBalanceOutput.DataBean.BalanceListBean> balanceList = new HashMap();
    private RequestBody body;
    private Gson gson;
    private Handler handler;
    private AtomicInteger retry;
    private double total;
    private List<Wallet> walletLists;
    private ArrayList<Map<String, Integer>> walletListsAddress;

    @Override
    protected void onStart() {
        this.gson = new Gson();
        this.handler = new Handler();
    }

    @Override
    public void getRelationWallet(String str) {
        this.walletLists = HDTronWalletController.queryWalletRelationship2(str, HDTronWalletController.Order.CreateTime);
        this.walletListsAddress = new ArrayList<>();
        for (Wallet wallet : this.walletLists) {
            HashMap hashMap = new HashMap();
            hashMap.put(wallet.getAddress(), Integer.valueOf(wallet.getCreateType()));
            if (hashMap.size() != 0) {
                this.walletListsAddress.add(hashMap);
            }
        }
    }

    @Override
    protected void addData() {
        Wallet wallet;
        this.walletLists = new ArrayList();
        this.walletListsAddress = new ArrayList<>();
        for (String str : WalletUtils.getPublicWalletNames()) {
            if (!TextUtils.isEmpty(str) && (wallet = WalletUtils.getWallet(str)) != null) {
                if (((RelationContract.View) this.mView).isShieldManage()) {
                    if (wallet.isShieldedWallet()) {
                        this.walletLists.add(wallet);
                    }
                } else {
                    if (!WalletUtils.isNonHDWallet(wallet)) {
                        this.walletLists.add(wallet);
                    }
                    if (!wallet.isShieldedWallet()) {
                        int createType = wallet.getCreateType();
                        int i = createType != -1 ? createType != 0 ? (createType == 1 || createType == 2 || createType == 3) ? 2 : 0 : 1 : 3;
                        HashMap hashMap = new HashMap();
                        hashMap.put(wallet.getAddress(), Integer.valueOf(i));
                        if (hashMap.size() != 0) {
                            this.walletListsAddress.add(hashMap);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void getData() {
        if (this.walletLists.size() != 0) {
            getLocalWalletBalance();
            String json = this.gson.toJson(this.walletListsAddress);
            this.retry = new AtomicInteger(3);
            this.body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
            ((RelationContract.Model) this.mModel).getAccountInfosList(WalletUtils.getSelectedPublicWallet().getAddress(), this.body).subscribe(new IObserver(new fun1(), "getAccountInfosList"));
        }
    }

    public class fun1 implements ICallback<AccountBalanceOutput> {
        @Override
        public void onSubscribe(Disposable disposable) {
        }

        fun1() {
        }

        @Override
        public void onResponse(String str, AccountBalanceOutput accountBalanceOutput) {
            if (accountBalanceOutput == null || accountBalanceOutput.getCode() != 0 || accountBalanceOutput.getData() == null || accountBalanceOutput.getData().getBalanceList() == null || accountBalanceOutput.getData().getBalanceList().size() == 0) {
                return;
            }
            double totalBalance = accountBalanceOutput.getData().getTotalBalance();
            if (totalBalance != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                total = totalBalance;
            }
            for (AccountBalanceOutput.DataBean.BalanceListBean balanceListBean : accountBalanceOutput.getData().getBalanceList()) {
                balanceList.put(balanceListBean.getAddress(), balanceListBean);
            }
            TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).insertMultObject(accountBalanceOutput.getData().getBalanceList());
            ((RelationContract.View) mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    RelationPresenter.1.this.lambda$onResponse$0();
                }
            });
        }

        public void lambda$onResponse$0() {
            ((RelationContract.View) mView).updateUi(walletLists, balanceList);
        }

        @Override
        public void onFailure(String str, String str2) {
            ((RelationContract.View) mView).updateUi(walletLists, balanceList);
        }
    }

    public void getLocalWalletBalance() {
        ((RelationContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getLocalWalletBalance$1();
            }
        });
    }

    public void lambda$getLocalWalletBalance$1() {
        Map<String, AccountBalanceOutput.DataBean.BalanceListBean> localData = ((RelationContract.Model) this.mModel).getLocalData(this.walletListsAddress);
        Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map = this.balanceList;
        if ((map != null && map.size() != 0) || localData == null || localData.size() == 0) {
            return;
        }
        this.balanceList = localData;
        this.total = ((RelationContract.Model) this.mModel).getTotalTRX();
        ((RelationContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$getLocalWalletBalance$0();
            }
        });
    }

    public void lambda$getLocalWalletBalance$0() {
        ((RelationContract.View) this.mView).updateUi(this.walletLists, this.balanceList);
    }
}
