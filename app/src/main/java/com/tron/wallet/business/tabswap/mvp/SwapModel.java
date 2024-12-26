package com.tron.wallet.business.tabswap.mvp;

import android.content.Context;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.business.tabswap.mvp.Contract;
import com.tron.wallet.business.tabswap.utils.SwapCacheUtils;
import com.tron.wallet.business.tabswap.utils.SwapV3ABIBuilder;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import okhttp3.RequestBody;
import org.tron.api.GrpcAPI;
import org.tron.common.crypto.FunctionEncoder;
import org.tron.common.utils.abi.EncodingException;
import org.tron.net.input.TriggerContractRequest;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SwapModel implements Contract.Model {
    @Override
    public Observable<BigDecimal> getBalanceByAddress(String str, String[] strArr) {
        return null;
    }

    public static String getSwapContractAddress() {
        return (IRequest.isRelease() || IRequest.isPrerelease()) ? "TJ4NNy8xZEqsowCBhLvZ45LCqPdGjkET5j" : "TWfXrhLJBYLVmUrmDnrtBZK1BGCWvtEjp6";
    }

    @Override
    public Observable<SwapWhiteListOutput> getWhiteListTokens(boolean z, final Context context) {
        final Observable<SwapWhiteListOutput> create = Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                SwapModel.lambda$getWhiteListTokens$0(context, observableEmitter);
            }
        });
        return !z ? create : ((HttpAPI) IRequest.getAPI(HttpAPI.class)).querySwapV3TokenList().onErrorResumeNext(new Function() {
            @Override
            public final Object apply(Object obj) {
                Observable observable = Observable.this;
                return LogUtils.e((Throwable) obj);
            }
        });
    }

    public static void lambda$getWhiteListTokens$0(Context context, ObservableEmitter observableEmitter) throws Exception {
        SwapWhiteListOutput read = SwapCacheUtils.getInstance().read(context);
        if (read == null) {
            read = new SwapWhiteListOutput();
        }
        observableEmitter.onNext(read);
        observableEmitter.onComplete();
    }

    @Override
    public Observable<SwapInfoOutput> querySwapInfo(final SwapTokenBean swapTokenBean, final String str, final String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestGetAsset(WalletUtils.getSelectedWallet().getAddress(), swapTokenBean.isTrx() ? 0 : 2, swapTokenBean.getToken()).onErrorResumeNext(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LogUtils.e((Throwable) obj);
            }
        }).compose(RxSchedulers.io_main()).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return SwapModel.lambda$querySwapInfo$3(SwapTokenBean.this, str, str2, (AssetsQueryOutput) obj);
            }
        });
    }

    public static ObservableSource lambda$querySwapInfo$3(SwapTokenBean swapTokenBean, String str, String str2, AssetsQueryOutput assetsQueryOutput) throws Exception {
        BigDecimal bigDecimal;
        if (assetsQueryOutput == null || assetsQueryOutput.data == null) {
            bigDecimal = new BigDecimal(0);
        } else {
            bigDecimal = BigDecimalUtils.mul_(swapTokenBean.getInputAmount(), assetsQueryOutput.data.getUsdPrice());
        }
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).querySwapV3Info(swapTokenBean.getToken(), str, str2, BigDecimalUtils.moreThanOrEqual(bigDecimal, 5000) ? "" : "PSM,CURVE,CURVE_COMBINATION,WTRX,SUNSWAP_V1,SUNSWAP_V2", "tronlink").compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<Object> recordTransaction(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).addDappRecord(requestBody).compose(RxSchedulers.io_main());
    }

    @Override
    public List<Protocol.Transaction> submitSwap(SwapInfoOutput swapInfoOutput, int i, SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, Wallet wallet) {
        ArrayList arrayList = new ArrayList();
        Protocol.Transaction triggerSwap = triggerSwap(swapInfoOutput, i, swapTokenBean, swapTokenBean2, wallet);
        if (triggerSwap == null) {
            arrayList.clear();
        } else {
            arrayList.add(triggerSwap);
        }
        return arrayList;
    }

    public Protocol.Transaction approve(String str, String str2, String str3, String str4) {
        try {
            GrpcAPI.TransactionExtention approve = TronAPI.approve(str, str2, str3, str4);
            if (!approve.hasResult() || !approve.hasTransaction() || approve.getTransaction() == null || "".equals(approve.getTransaction())) {
                return null;
            }
            LogUtils.w("energy-approve:" + approve.getEnergyUsed());
            return approve.getTransaction();
        } catch (EncodingException e) {
            LogUtils.e(e);
            return null;
        }
    }

    private Protocol.Transaction triggerSwap(SwapInfoOutput swapInfoOutput, int i, SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, Wallet wallet) {
        try {
            String buildArgs = SwapV3ABIBuilder.buildArgs(swapInfoOutput, i, swapTokenBean, swapTokenBean2, wallet.getAddress());
            TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
            triggerContractRequest.setAbiPro(true);
            triggerContractRequest.setMethodABI(FunctionEncoder.buildMethodId("swapExactInput(address[],string[],uint256[],uint24[],(uint256,uint256,address,uint256))"));
            triggerContractRequest.setArgsStr(buildArgs);
            triggerContractRequest.setMethodStr("swapExactInput(address[],string[],uint256[],uint24[],tuple(uint256,uint256,address,uint256))");
            triggerContractRequest.setFeeLimit(TronConfig.feeLimit_swap_v3);
            triggerContractRequest.setContractAddrStr(getSwapContractAddress());
            triggerContractRequest.setHex(false);
            long j = 0;
            triggerContractRequest.setTokenCallValue(0L);
            if (swapTokenBean.isTrx()) {
                j = new BigDecimal(swapTokenBean.getInputAmount()).multiply(new BigDecimal(Math.pow(10.0d, 6.0d))).longValue();
            }
            triggerContractRequest.setCallValue(j);
            triggerContractRequest.setOwer(wallet.getDecode58CheckAddress());
            GrpcAPI.TransactionExtention triggerContract2 = TronAPI.triggerContract2(triggerContractRequest);
            LogUtils.w("energy-swap:" + triggerContract2.getEnergyUsed());
            LogUtils.w("triggerSwap", "callValue = " + j);
            if (triggerContract2 == null || !triggerContract2.hasResult()) {
                return null;
            }
            return triggerContract2.getTransaction();
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }
}
