package com.tron.wallet.business.tronpower.unstake;

import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.bean.FreezeUnFreezeParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.stakev2.bean.DelegatedResourceOutput;
import com.tron.wallet.business.tronpower.unstake.UnStakeContract;
import com.tron.wallet.business.tronpower.unstake.adapter.UnStakeResourceBean;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TronPowerUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.HttpAPI;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import j$.util.Collection;
import j$.util.Objects;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.protos.contract.Common;
public class UnStakeModel implements UnStakeContract.Model {
    @Override
    public Observable<List<UnStakeResourceBean>> requestStakeResourceForSelf(final Protocol.Account account) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$requestStakeResourceForSelf$0(account, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main());
    }

    public void lambda$requestStakeResourceForSelf$0(Protocol.Account account, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(build(parseResource(account)));
        observableEmitter.onComplete();
    }

    private Protocol.DelegatedResource parseResource(Protocol.Account account) {
        Collection.-EL.stream(account.getFrozenList()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                UnStakeModel.lambda$parseResource$1(r1, r2, (Protocol.Account.Frozen) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        final long[] jArr = {0, account.getAccountResource().getFrozenBalanceForEnergy().getFrozenBalance()};
        final long[] jArr2 = {0, account.getAccountResource().getFrozenBalanceForEnergy().getExpireTime()};
        Protocol.DelegatedResource.Builder newBuilder = Protocol.DelegatedResource.newBuilder();
        byte[] byteArray = account.getAddress().toByteArray();
        if (byteArray != null && byteArray.length > 0) {
            ByteString copyFrom = ByteString.copyFrom(byteArray);
            newBuilder.setFrom(copyFrom);
            newBuilder.setTo(copyFrom);
        }
        newBuilder.setFrozenBalanceForBandwidth(jArr[0]);
        newBuilder.setExpireTimeForBandwidth(jArr2[0]);
        newBuilder.setExpireTimeForEnergy(jArr2[1]);
        newBuilder.setFrozenBalanceForEnergy(jArr[1]);
        return newBuilder.build();
    }

    public static void lambda$parseResource$1(long[] jArr, long[] jArr2, Protocol.Account.Frozen frozen) {
        jArr[0] = jArr[0] + frozen.getFrozenBalance();
        jArr2[0] = Math.max(jArr2[0], frozen.getExpireTime());
    }

    @Override
    public Observable<List<UnStakeResourceBean>> requestStakeResourceForOthers(String str, int i, int i2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getDelegatedResource(str, 2, 0, i, i2, false).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$requestStakeResourceForOthers$3;
                lambda$requestStakeResourceForOthers$3 = lambda$requestStakeResourceForOthers$3((DelegatedResourceOutput) obj);
                return lambda$requestStakeResourceForOthers$3;
            }
        }).compose(RxSchedulers2.io_main());
    }

    public ObservableSource lambda$requestStakeResourceForOthers$3(DelegatedResourceOutput delegatedResourceOutput) throws Exception {
        final ArrayList arrayList = new ArrayList();
        if (DelegatedResourceOutput.isValid(delegatedResourceOutput)) {
            Collection.-EL.stream(delegatedResourceOutput.getData()).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$requestStakeResourceForOthers$2(arrayList, (DelegatedResourceOutput.DelegatedResource) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        return Observable.just(arrayList);
    }

    public void lambda$requestStakeResourceForOthers$2(ArrayList arrayList, DelegatedResourceOutput.DelegatedResource delegatedResource) {
        arrayList.add(build(delegatedResource));
    }

    @Override
    public BaseConfirmTransParamBuilder buildTransactionParam(Protocol.Account account, final String str, boolean z, GrpcAPI.AccountResourceMessage accountResourceMessage, java.util.Collection<UnStakeResourceBean> collection) {
        final ArrayList arrayList = new ArrayList();
        final HashMap<String, Long> hashMap = new HashMap<>();
        final FreezeUnFreezeParam.TYPE[] typeArr = {FreezeUnFreezeParam.TYPE.FOR_SELF};
        final long[] jArr = new long[2];
        Collection.-EL.stream(collection).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$buildTransactionParam$4(str, arrayList, hashMap, jArr, typeArr, (UnStakeResourceBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        HashMap<String, String> createUnfreezeTargetMap = createUnfreezeTargetMap(hashMap);
        if (createUnfreezeTargetMap.size() > 1) {
            typeArr[0] = FreezeUnFreezeParam.TYPE.BATCH;
        }
        String format = NumberFormat.getNumberInstance(Locale.US).format(jArr[0] + jArr[1]);
        String[] buildParamReleaseResource = buildParamReleaseResource(accountResourceMessage, jArr[0], jArr[1]);
        if (arrayList.size() > 0) {
            return ParamBuildUtils.getUnFreezeTransactionParamBuilder(0, z, typeArr[0], buildParamReleaseResource, format, arrayList, createUnfreezeTargetMap, 0L);
        }
        return null;
    }

    public void lambda$buildTransactionParam$4(String str, List list, HashMap hashMap, long[] jArr, FreezeUnFreezeParam.TYPE[] typeArr, UnStakeResourceBean unStakeResourceBean) {
        GrpcAPI.TransactionExtention createUnfreezeBalanceTransaction;
        DelegatedResourceOutput.DelegatedResource source = unStakeResourceBean.getSource();
        boolean equals = unStakeResourceBean.getType().equals(UnStakeResourceBean.Type.BANDWIDTH);
        byte[] decode58Check = StringTronUtil.decode58Check(source.getReceiverAddress());
        String receiverAddress = source.getReceiverAddress();
        Common.ResourceCode resourceCode = equals ? Common.ResourceCode.BANDWIDTH : Common.ResourceCode.ENERGY;
        if (TextUtils.equals(receiverAddress, str)) {
            createUnfreezeBalanceTransaction = TronAPI.createUnfreezeBalanceTransaction(StringTronUtil.decodeFromBase58Check(str), resourceCode);
        } else {
            createUnfreezeBalanceTransaction = TronAPI.createUnfreezeBalanceTransaction(StringTronUtil.decodeFromBase58Check(str), decode58Check, resourceCode);
        }
        if (createUnfreezeBalanceTransaction == null || !createUnfreezeBalanceTransaction.hasResult()) {
            return;
        }
        list.add(createUnfreezeBalanceTransaction.getTransaction());
        saveUnfreezeMap(hashMap, source);
        if (equals) {
            jArr[0] = jArr[0] + unStakeResourceBean.getTrxCount();
        } else {
            jArr[1] = jArr[1] + unStakeResourceBean.getTrxCount();
        }
        if (TextUtils.equals(str, source.getReceiverAddress())) {
            return;
        }
        typeArr[0] = FreezeUnFreezeParam.TYPE.FOR_OTHER;
    }

    private void saveUnfreezeMap(HashMap<String, Long> hashMap, DelegatedResourceOutput.DelegatedResource delegatedResource) {
        String receiverAddress = delegatedResource.getReceiverAddress();
        Long l = hashMap.get(receiverAddress);
        if (l == null) {
            hashMap.put(receiverAddress, Long.valueOf(delegatedResource.getFrozenBalance()));
        } else {
            hashMap.put(receiverAddress, Long.valueOf(delegatedResource.getFrozenBalance() + l.longValue()));
        }
    }

    private HashMap<String, String> createUnfreezeTargetMap(final HashMap<String, Long> hashMap) {
        final HashMap<String, String> hashMap2 = new HashMap<>();
        Collection.-EL.stream(hashMap.entrySet()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                UnStakeModel.lambda$createUnfreezeTargetMap$5(hashMap, hashMap2, (Map.Entry) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return hashMap2;
    }

    public static void lambda$createUnfreezeTargetMap$5(HashMap hashMap, HashMap hashMap2, Map.Entry entry) {
        BigDecimal div_ = BigDecimalUtils.div_(entry.getValue(), Double.valueOf(Math.pow(10.0d, 6.0d)));
        String str = (String) entry.getKey();
        String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(str);
        if (!TextUtils.isEmpty(nameByAddress)) {
            if (hashMap.size() == 1) {
                str = String.format("%s\n(%s)", nameByAddress, str);
            } else {
                str = String.format("%s(%s)", nameByAddress, str);
            }
        }
        hashMap2.put(str, StringTronUtil.plainScientificNotation(div_) + " TRX");
    }

    private String[] buildParamReleaseResource(GrpcAPI.AccountResourceMessage accountResourceMessage, long j, long j2) {
        String[] strArr = new String[2];
        if (j > 0) {
            BigDecimal scale = TronPowerUtils.expectGetPower(accountResourceMessage.getTotalNetWeight(), accountResourceMessage.getTotalNetLimit(), BigDecimalUtils.toBigDecimal(Long.valueOf(j))).setScale(0, 1);
            if (BigDecimalUtils.MoreThan((Object) scale, (Object) 0)) {
                strArr[1] = StringTronUtil.formatNumber6Truncate(scale) + " " + StringTronUtil.getResString(R.string.bandwidth);
            }
        }
        if (j2 > 0) {
            BigDecimal scale2 = TronPowerUtils.expectGetPower(accountResourceMessage.getTotalEnergyWeight(), accountResourceMessage.getTotalEnergyLimit(), BigDecimalUtils.toBigDecimal(Long.valueOf(j2))).setScale(0, 1);
            if (BigDecimalUtils.MoreThan((Object) scale2, (Object) 0)) {
                strArr[0] = StringTronUtil.formatNumber6Truncate(scale2) + " " + StringTronUtil.getResString(R.string.energy);
            }
        }
        return strArr;
    }

    public UnStakeResourceBean build(DelegatedResourceOutput.DelegatedResource delegatedResource) {
        boolean equals = TextUtils.equals(DelegatedResourceOutput.DelegatedResource.energy, delegatedResource.getResource());
        boolean z = delegatedResource.getExpireTime() <= System.currentTimeMillis();
        return new UnStakeResourceBean(formatTrxCount(delegatedResource.getFrozenBalance()), delegatedResource.getReceiverAddress(), z ? 65536 : 196608, UnStakeResourceBean.Group.OTHER, equals ? UnStakeResourceBean.Type.ENERGY : UnStakeResourceBean.Type.BANDWIDTH, AddressNameUtils.getInstance().getNameByAddress(delegatedResource.getReceiverAddress(), false), delegatedResource, delegatedResource.getExpireTime());
    }

    public List<UnStakeResourceBean> build(Protocol.DelegatedResource delegatedResource) {
        String str;
        String str2;
        boolean z;
        ArrayList arrayList;
        String encode58Check = StringTronUtil.encode58Check(delegatedResource.getTo().toByteArray());
        String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(encode58Check, false);
        ArrayList arrayList2 = new ArrayList();
        long frozenBalanceForBandwidth = delegatedResource.getFrozenBalanceForBandwidth();
        if (frozenBalanceForBandwidth > 0) {
            boolean z2 = delegatedResource.getExpireTimeForBandwidth() <= System.currentTimeMillis();
            str = encode58Check;
            str2 = nameByAddress;
            z = true;
            arrayList = arrayList2;
            arrayList.add(new UnStakeResourceBean(formatTrxCount(frozenBalanceForBandwidth), encode58Check, z2 ? 65536 : 196608, UnStakeResourceBean.Group.SELF, UnStakeResourceBean.Type.BANDWIDTH, nameByAddress, parseFromAccount(delegatedResource, false), delegatedResource.getExpireTimeForBandwidth()));
        } else {
            str = encode58Check;
            str2 = nameByAddress;
            z = true;
            arrayList = arrayList2;
        }
        long frozenBalanceForEnergy = delegatedResource.getFrozenBalanceForEnergy();
        if (frozenBalanceForEnergy > 0) {
            arrayList.add(new UnStakeResourceBean(formatTrxCount(frozenBalanceForEnergy), str, (delegatedResource.getExpireTimeForEnergy() > System.currentTimeMillis() ? 1 : (delegatedResource.getExpireTimeForEnergy() == System.currentTimeMillis() ? 0 : -1)) <= 0 ? 65536 : 196608, UnStakeResourceBean.Group.SELF, UnStakeResourceBean.Type.ENERGY, str2, parseFromAccount(delegatedResource, z), delegatedResource.getExpireTimeForEnergy()));
        }
        Collections.sort(arrayList, new Comparator() {
            @Override
            public final int compare(Object obj, Object obj2) {
                return UnStakeModel.lambda$build$6((UnStakeResourceBean) obj, (UnStakeResourceBean) obj2);
            }
        });
        return arrayList;
    }

    public static int lambda$build$6(UnStakeResourceBean unStakeResourceBean, UnStakeResourceBean unStakeResourceBean2) {
        int i = ((unStakeResourceBean.getExpiredTime() - unStakeResourceBean2.getExpiredTime()) > 0L ? 1 : ((unStakeResourceBean.getExpiredTime() - unStakeResourceBean2.getExpiredTime()) == 0L ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }

    private DelegatedResourceOutput.DelegatedResource parseFromAccount(Protocol.DelegatedResource delegatedResource, boolean z) {
        long expireTimeForBandwidth;
        DelegatedResourceOutput.DelegatedResource delegatedResource2 = new DelegatedResourceOutput.DelegatedResource();
        if (z) {
            expireTimeForBandwidth = delegatedResource.getExpireTimeForEnergy();
        } else {
            expireTimeForBandwidth = delegatedResource.getExpireTimeForBandwidth();
        }
        delegatedResource2.setExpireTime(expireTimeForBandwidth);
        delegatedResource2.setFrozenBalance(z ? delegatedResource.getFrozenBalanceForEnergy() : delegatedResource.getFrozenBalanceForBandwidth());
        delegatedResource2.setOwnerAddress(StringTronUtil.encode58Check(delegatedResource.getFrom().toByteArray()));
        delegatedResource2.setReceiverAddress(StringTronUtil.encode58Check(delegatedResource.getTo().toByteArray()));
        delegatedResource2.setResource(z ? DelegatedResourceOutput.DelegatedResource.energy : "BANDWIDTH");
        return delegatedResource2;
    }

    private long formatTrxCount(long j) {
        return BigDecimalUtils.div_(Long.valueOf(j), Double.valueOf(Math.pow(10.0d, 6.0d))).longValue();
    }
}
