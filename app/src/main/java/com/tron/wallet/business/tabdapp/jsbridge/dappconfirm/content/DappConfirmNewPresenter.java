package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content;

import android.util.Pair;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewContract;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
public class DappConfirmNewPresenter extends DappConfirmNewContract.Presenter {
    @Override
    protected void onStart() {
    }

    public void getCollectionInfo(String str, String str2, String str3) {
        ((DappConfirmNewContract.Model) this.mModel).getCollectionInfo(str, str2, str3).subscribe(new IObserver(new ICallback<NftItemInfoOutput>() {
            @Override
            public void onFailure(String str4, String str5) {
            }

            @Override
            public void onResponse(String str4, NftItemInfoOutput nftItemInfoOutput) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }
        }, "getCollectionInfo"));
    }

    @Override
    public void getApproveDate(String str, final DappDetailParam dappDetailParam) {
        Observable.zip(((DappConfirmNewContract.Model) this.mModel).getProjectInfo(str, WalletUtils.getSelectedPublicWallet().getAddress()), AssetsManager.getInstance().requestGetAsset(WalletUtils.getSelectedPublicWallet().getAddress(), 2, dappDetailParam.getContractAddress()), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                return DappConfirmNewPresenter.lambda$getApproveDate$0((DappProjectIOutput) obj, (AssetsQueryOutput) obj2);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<Pair<DappProjectIOutput, AssetsQueryOutput>>() {
            @Override
            public void onResponse(String str2, Pair<DappProjectIOutput, AssetsQueryOutput> pair) {
                checkIsContract(dappDetailParam.getTriggerData().getParameterMap().get("0"), pair);
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((DappConfirmNewContract.View) mView).toast(((DappConfirmNewContract.View) mView).getIContext().getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "getApproveDate"));
    }

    public static Pair lambda$getApproveDate$0(DappProjectIOutput dappProjectIOutput, AssetsQueryOutput assetsQueryOutput) throws Exception {
        return new Pair(dappProjectIOutput, assetsQueryOutput);
    }

    public void checkIsContract(final String str, final Pair<DappProjectIOutput, AssetsQueryOutput> pair) {
        AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
            @Override
            public final void doOnBackground() {
                lambda$checkIsContract$1(str, pair);
            }
        });
    }

    public void lambda$checkIsContract$1(String str, Pair pair) {
        try {
            Protocol.Account account = AccountUtils.getInstance().getAccount(StringTronUtil.decode58Check(str));
            if (account != null && account.getTypeValue() != 2) {
                ((DappConfirmNewContract.View) this.mView).updateApproveConfirmFragment(((DappProjectIOutput) pair.first).getData(), true, ((AssetsQueryOutput) pair.second).getData());
            }
            ((DappConfirmNewContract.View) this.mView).updateApproveConfirmFragment(((DappProjectIOutput) pair.first).getData(), false, ((AssetsQueryOutput) pair.second).getData());
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
        }
    }
}
