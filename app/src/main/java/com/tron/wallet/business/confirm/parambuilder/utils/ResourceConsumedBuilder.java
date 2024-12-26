package com.tron.wallet.business.confirm.parambuilder.utils;

import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.net.IRequestExtend;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.bean.ConstContractEnergyBean;
import com.tron.wallet.business.confirm.fg.bean.HotInfoBean;
import com.tron.wallet.business.confirm.fg.bean.ResourceConsumedBean;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpApiExtend;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;
public class ResourceConsumedBuilder {
    private BaseParam param;
    private ResourceConsumedBean resourceConsumedBean = new ResourceConsumedBean();

    public static ConstContractEnergyBean lambda$asyncGetContactEnergy$11(ConstContractEnergyBean constContractEnergyBean, Throwable th) throws Exception {
        return constContractEnergyBean;
    }

    public static ConstContractEnergyBean lambda$asyncGetContactEnergy$8(ConstContractEnergyBean constContractEnergyBean, Throwable th) throws Exception {
        return constContractEnergyBean;
    }

    public static ConstContractEnergyBean lambda$asyncGetContactEnergy$9(ConstContractEnergyBean constContractEnergyBean, ConstContractEnergyBean constContractEnergyBean2) throws Exception {
        return constContractEnergyBean;
    }

    public ResourceConsumedBean build() {
        return this.resourceConsumedBean;
    }

    public ResourceConsumedBuilder(BaseParam baseParam) {
        this.param = baseParam;
    }

    public com.tron.wallet.business.confirm.fg.bean.ResourceConsumedBean buildStationaryFee(org.tron.protos.Protocol.Transaction r9) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.confirm.parambuilder.utils.ResourceConsumedBuilder.buildStationaryFee(org.tron.protos.Protocol$Transaction):com.tron.wallet.business.confirm.fg.bean.ResourceConsumedBean");
    }

    public ResourceConsumedBean buildEnergy(ResourceConsumedBean resourceConsumedBean, ConstContractEnergyBean constContractEnergyBean) {
        if (constContractEnergyBean == null || constContractEnergyBean.getEnergyUsed() == 0) {
            return resourceConsumedBean;
        }
        resourceConsumedBean.setEnergyConsumed(constContractEnergyBean.getEnergyUsed());
        resourceConsumedBean.setEnergyPenalty(constContractEnergyBean.getEnergyPenalty());
        resourceConsumedBean.setEnergyUserConsumedPercent(constContractEnergyBean.getUserPercent());
        if (constContractEnergyBean.getUserPercent() == 100 || constContractEnergyBean.getCreatorEnergyUsable() == 0 || constContractEnergyBean.getCreatorEnergyLimit() == 0) {
            resourceConsumedBean.setEnergyUserConsumed(constContractEnergyBean.getEnergyUsed());
            resourceConsumedBean.setEnergyUserPenalty(constContractEnergyBean.getEnergyPenalty());
        } else {
            double userPercent = 1.0d - (constContractEnergyBean.getUserPercent() / 100.0d);
            if (constContractEnergyBean.getCreatorEnergyUsable() > constContractEnergyBean.getEnergyUsed() * userPercent * (constContractEnergyBean.isHotContract() ? 20 : 3)) {
                long energyUsed = (long) (constContractEnergyBean.getEnergyUsed() * userPercent);
                if (energyUsed <= constContractEnergyBean.getCreatorEnergyLimit()) {
                    resourceConsumedBean.setEnergyUserConsumed(constContractEnergyBean.getEnergyUsed() - energyUsed);
                    resourceConsumedBean.setEnergyUserPenalty(constContractEnergyBean.getEnergyPenalty() - ((long) (constContractEnergyBean.getEnergyPenalty() * userPercent)));
                } else {
                    resourceConsumedBean.setEnergyUserConsumed(constContractEnergyBean.getEnergyUsed() - constContractEnergyBean.getCreatorEnergyLimit());
                    resourceConsumedBean.setEnergyUserPenalty(constContractEnergyBean.getEnergyPenalty() - ((long) (constContractEnergyBean.getEnergyPenalty() * (constContractEnergyBean.getCreatorEnergyLimit() / constContractEnergyBean.getEnergyUsed()))));
                }
            } else {
                resourceConsumedBean.setEnergyUserConsumed(constContractEnergyBean.getEnergyUsed());
                resourceConsumedBean.setEnergyUserPenalty(constContractEnergyBean.getEnergyPenalty());
            }
        }
        return resourceConsumedBean;
    }

    public Observable<ResourceConsumedBean> buildTransactionEnergy(List<Protocol.Transaction> list) {
        ArrayList arrayList = new ArrayList();
        for (Protocol.Transaction transaction : list) {
            arrayList.add(asyncGetContactEnergy(transaction).map(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ResourceConsumedBean lambda$buildTransactionEnergy$0;
                    lambda$buildTransactionEnergy$0 = lambda$buildTransactionEnergy$0((ConstContractEnergyBean) obj);
                    return lambda$buildTransactionEnergy$0;
                }
            }));
        }
        return Observable.zip(arrayList, new Function() {
            @Override
            public final Object apply(Object obj) {
                ResourceConsumedBean lambda$buildTransactionEnergy$1;
                lambda$buildTransactionEnergy$1 = lambda$buildTransactionEnergy$1((Object[]) obj);
                return lambda$buildTransactionEnergy$1;
            }
        });
    }

    public ResourceConsumedBean lambda$buildTransactionEnergy$0(ConstContractEnergyBean constContractEnergyBean) throws Exception {
        return buildEnergy(new ResourceConsumedBean(), constContractEnergyBean);
    }

    public ResourceConsumedBean lambda$buildTransactionEnergy$1(Object[] objArr) throws Exception {
        long j = 0;
        for (Object obj : objArr) {
            if (obj instanceof ResourceConsumedBean) {
                ResourceConsumedBean resourceConsumedBean = (ResourceConsumedBean) obj;
                j = Math.max(j, resourceConsumedBean.getEnergyUserConsumedPercent());
                ResourceConsumedBean resourceConsumedBean2 = this.resourceConsumedBean;
                resourceConsumedBean2.setEnergyConsumed(resourceConsumedBean2.getEnergyConsumed() + resourceConsumedBean.getEnergyConsumed());
                ResourceConsumedBean resourceConsumedBean3 = this.resourceConsumedBean;
                resourceConsumedBean3.setEnergyUserConsumed(resourceConsumedBean3.getEnergyUserConsumed() + resourceConsumedBean.getEnergyUserConsumed());
                ResourceConsumedBean resourceConsumedBean4 = this.resourceConsumedBean;
                resourceConsumedBean4.setEnergyPenalty(resourceConsumedBean4.getEnergyPenalty() + resourceConsumedBean.getEnergyPenalty());
                ResourceConsumedBean resourceConsumedBean5 = this.resourceConsumedBean;
                resourceConsumedBean5.setEnergyUserPenalty(resourceConsumedBean5.getEnergyUserPenalty() + resourceConsumedBean.getEnergyUserPenalty());
                ResourceConsumedBean resourceConsumedBean6 = this.resourceConsumedBean;
                resourceConsumedBean6.setDeductedEnergy(resourceConsumedBean6.getDeductedEnergy() + resourceConsumedBean.getDeductedEnergy());
            }
        }
        this.resourceConsumedBean.setEnergyUserConsumedPercent(j);
        return this.resourceConsumedBean;
    }

    public io.reactivex.Observable<com.tron.wallet.business.confirm.fg.bean.ResourceConsumedBean> asyncBuildTransactionResources(org.tron.protos.Protocol.Transaction r8, org.tron.protos.Protocol.Account r9, org.tron.api.GrpcAPI.AccountResourceMessage r10) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.confirm.parambuilder.utils.ResourceConsumedBuilder.asyncBuildTransactionResources(org.tron.protos.Protocol$Transaction, org.tron.protos.Protocol$Account, org.tron.api.GrpcAPI$AccountResourceMessage):io.reactivex.Observable");
    }

    public static void lambda$asyncBuildTransactionResources$2(List list, byte[] bArr) {
        try {
            list.add(Protocol.Transaction.parseFrom(bArr));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public ResourceConsumedBean lambda$asyncBuildTransactionResources$3(ConstContractEnergyBean constContractEnergyBean) throws Exception {
        return buildEnergy(this.resourceConsumedBean, constContractEnergyBean);
    }

    public static Observable<ConstContractEnergyBean> asyncGetContactEnergy(Protocol.Transaction transaction) {
        final ConstContractEnergyBean constContractEnergyBean = new ConstContractEnergyBean();
        Observable<ConstContractEnergyBean> just = Observable.just(constContractEnergyBean);
        if (transaction == null || transaction.toString().length() <= 0 || transaction.getRawData().getContract(0).getType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
            return just;
        }
        try {
            final SmartContractOuterClass.TriggerSmartContract triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(transaction.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class);
            final byte[] byteArray = triggerSmartContract.getContractAddress().toByteArray();
            constContractEnergyBean.setContactAddress(StringTronUtil.encode58Check(byteArray));
            return Observable.zip(Observable.fromCallable(new Callable() {
                @Override
                public final Object call() {
                    return ResourceConsumedBuilder.lambda$asyncGetContactEnergy$4(SmartContractOuterClass.TriggerSmartContract.this, constContractEnergyBean);
                }
            }), Observable.fromCallable(new Callable() {
                @Override
                public final Object call() {
                    SmartContractOuterClass.SmartContract contract;
                    contract = TronAPI.getContract(byteArray);
                    return contract;
                }
            }).flatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return ResourceConsumedBuilder.lambda$asyncGetContactEnergy$7(ConstContractEnergyBean.this, (SmartContractOuterClass.SmartContract) obj);
                }
            }).onErrorReturn(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return ResourceConsumedBuilder.lambda$asyncGetContactEnergy$8(ConstContractEnergyBean.this, (Throwable) obj);
                }
            }), new BiFunction() {
                @Override
                public final Object apply(Object obj, Object obj2) {
                    return ResourceConsumedBuilder.lambda$asyncGetContactEnergy$9((ConstContractEnergyBean) obj, (ConstContractEnergyBean) obj2);
                }
            }).flatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return ResourceConsumedBuilder.lambda$asyncGetContactEnergy$12((ConstContractEnergyBean) obj);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
            return just;
        }
    }

    public static ConstContractEnergyBean lambda$asyncGetContactEnergy$4(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, ConstContractEnergyBean constContractEnergyBean) throws Exception {
        GrpcAPI.TransactionExtention triggerConstantContractOnline = TronAPI.triggerConstantContractOnline(triggerSmartContract);
        constContractEnergyBean.setEnergyUsed(triggerConstantContractOnline.getEnergyUsed());
        constContractEnergyBean.setEnergyPenalty(triggerConstantContractOnline.getEnergyPenalty());
        return constContractEnergyBean;
    }

    public static ObservableSource lambda$asyncGetContactEnergy$7(final ConstContractEnergyBean constContractEnergyBean, SmartContractOuterClass.SmartContract smartContract) throws Exception {
        if (smartContract == null || smartContract.toString().length() == 0) {
            return Observable.just(constContractEnergyBean);
        }
        constContractEnergyBean.setUserPercent(smartContract.getConsumeUserResourcePercent());
        if (constContractEnergyBean.getUserPercent() >= 100) {
            return Observable.just(constContractEnergyBean);
        }
        constContractEnergyBean.setCreatorEnergyLimit(smartContract.getOriginEnergyLimit());
        return Observable.just(smartContract.getOriginAddress()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return ResourceConsumedBuilder.lambda$asyncGetContactEnergy$6(ConstContractEnergyBean.this, (ByteString) obj);
            }
        });
    }

    public static ConstContractEnergyBean lambda$asyncGetContactEnergy$6(ConstContractEnergyBean constContractEnergyBean, ByteString byteString) throws Exception {
        GrpcAPI.AccountResourceMessage accountRes = TronAPI.getAccountRes(byteString.toByteArray());
        if (accountRes != null && accountRes.toString().length() > 0) {
            constContractEnergyBean.setCreatorEnergyUsable(accountRes.getEnergyLimit() - accountRes.getEnergyUsed());
        }
        return constContractEnergyBean;
    }

    public static ObservableSource lambda$asyncGetContactEnergy$12(final ConstContractEnergyBean constContractEnergyBean) throws Exception {
        if (constContractEnergyBean.getUserPercent() < 100 && BigDecimalUtils.MoreThan(Long.valueOf(constContractEnergyBean.getCreatorEnergyUsable()), Double.valueOf(constContractEnergyBean.getEnergyUsed() * (1.0d - (constContractEnergyBean.getUserPercent() / 100.0d)) * 3.0d))) {
            return ((HttpApiExtend) IRequestExtend.getAPI(HttpApiExtend.class)).getHotContract().map(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return ResourceConsumedBuilder.lambda$asyncGetContactEnergy$10(ConstContractEnergyBean.this, (HotInfoBean) obj);
                }
            }).onErrorReturn(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return ResourceConsumedBuilder.lambda$asyncGetContactEnergy$11(ConstContractEnergyBean.this, (Throwable) obj);
                }
            });
        }
        constContractEnergyBean.setHotContract(false);
        return Observable.just(constContractEnergyBean);
    }

    public static ConstContractEnergyBean lambda$asyncGetContactEnergy$10(ConstContractEnergyBean constContractEnergyBean, HotInfoBean hotInfoBean) throws Exception {
        boolean z;
        if (hotInfoBean != null && hotInfoBean.getHotContacts() != null) {
            Iterator<HotInfoBean.HotContact> it = hotInfoBean.getHotContacts().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (constContractEnergyBean.getContactAddress().equals(it.next().getContactAddress())) {
                    z = true;
                    break;
                }
            }
            constContractEnergyBean.setHotContract(z);
        }
        return constContractEnergyBean;
    }

    public boolean enoughBandWidth(Protocol.Account account, Protocol.Transaction transaction) {
        long bandwidthCost = TransactionUtils.bandwidthCost(transaction);
        GrpcAPI.AccountResourceMessage accountRes = WalletUtils.getAccountRes(AppContextUtil.getContext(), WalletUtils.getSelectedWallet().getWalletName());
        long netLimit = accountRes.getNetLimit() - accountRes.getNetUsed();
        long freeNetLimit = accountRes.getFreeNetLimit() - accountRes.getFreeNetUsed();
        accountRes.getNetLimit();
        accountRes.getFreeNetLimit();
        accountRes.getNetUsed();
        accountRes.getFreeNetUsed();
        return netLimit >= bandwidthCost || freeNetLimit >= bandwidthCost || ((double) account.getBalance()) * TronConfig.feeBandWidth >= ((double) bandwidthCost);
    }
}
