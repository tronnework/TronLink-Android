package com.tron.wallet.business.addassets2.selecttoken;

import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.NftDataOutput;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftAssetOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.repository.FollowAssetsSortListController;
import com.tron.wallet.business.addassets2.repository.KVController;
import com.tron.wallet.business.addassets2.selecttoken.LocalTokenBeansHelper;
import com.tron.wallet.business.addassets2.sort.TokenSortByName;
import com.tron.wallet.business.addassets2.sort.TokenSortByUser;
import com.tron.wallet.business.addassets2.sort.TokenSortByValue;
import com.tron.wallet.business.nft.dao.NftTokenBeanController;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.db.Controller.AssetsHomeDataDaoManager;
import com.tron.wallet.db.SpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
public class LocalTokenBeansHelper {
    public static Single<List<TokenBean>> getSortedTokens(final String str, final boolean z) {
        return Observable.just(1, 2, 5, 6).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getSortedTokens$0(str, z, (Integer) obj);
            }
        }).onErrorReturn(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getSortedTokens$1((Throwable) obj);
            }
        }).collect(new Callable() {
            @Override
            public final Object call() {
                return LocalTokenBeansHelper.lambda$getSortedTokens$2();
            }
        }, new BiConsumer() {
            @Override
            public final void accept(Object obj, Object obj2) {
                LocalTokenBeansHelper.lambda$getSortedTokens$3((HashMap) obj, (LocalTokenBeansHelper.TokensMap) obj2);
            }
        }).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getSortedTokens$4((HashMap) obj);
            }
        });
    }

    public static ObservableSource lambda$getSortedTokens$0(String str, boolean z, Integer num) throws Exception {
        if (num.intValue() == 1) {
            return getHomeAssetsTokens(str, z);
        }
        if (num.intValue() == 2 && SpAPI.THIS.getCurIsMainChain()) {
            return getHomeCollections(str, z);
        }
        if (num.intValue() == 5) {
            return getMyAllAssetsTokens(str);
        }
        if (num.intValue() == 6 && SpAPI.THIS.getCurIsMainChain()) {
            return getMyAllCollections(str);
        }
        return Observable.just(new TokensMap());
    }

    public static TokensMap lambda$getSortedTokens$1(Throwable th) throws Exception {
        return new TokensMap();
    }

    public static HashMap lambda$getSortedTokens$2() throws Exception {
        return new HashMap();
    }

    public static void lambda$getSortedTokens$3(HashMap hashMap, TokensMap tokensMap) throws Exception {
        hashMap.put(Integer.valueOf(tokensMap.index1), tokensMap.tokens1);
        hashMap.put(Integer.valueOf(tokensMap.index2), tokensMap.tokens2);
    }

    public static List lambda$getSortedTokens$4(HashMap hashMap) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= 4; i++) {
            List list = (List) hashMap.get(Integer.valueOf(i));
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        for (int i2 = 5; i2 <= 8; i2++) {
            List list2 = (List) hashMap.get(Integer.valueOf(i2));
            if (list2 != null) {
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    TokenBean tokenBean = (TokenBean) it.next();
                    Iterator it2 = arrayList.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (AssetsManager.sameTokenBean(tokenBean, (TokenBean) it2.next())) {
                                it.remove();
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                arrayList.addAll(list2);
            }
        }
        return arrayList;
    }

    private static Observable<TokensMap> getHomeAssetsTokens(final String str, final boolean z) {
        return Observable.just(str).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getHomeAssetsTokens$6(z, str, (String) obj);
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getHomeAssetsTokens$7(str, (AssetsHomeData) obj);
            }
        });
    }

    public static ObservableSource lambda$getHomeAssetsTokens$6(boolean z, String str, String str2) throws Exception {
        AssetsHomeData addAssetsHomeData;
        if (!z && (addAssetsHomeData = AssetsHomeDataDaoManager.getAddAssetsHomeData(AppContextUtil.getmApplication(), str)) != null && addAssetsHomeData.token != null && addAssetsHomeData.token.size() > 0) {
            return Observable.just(addAssetsHomeData);
        }
        return AssetsManager.getInstance().requestFollowAssets(str).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getHomeAssetsTokens$5((AssetsHomeOutput) obj);
            }
        });
    }

    public static AssetsHomeData lambda$getHomeAssetsTokens$5(AssetsHomeOutput assetsHomeOutput) throws Exception {
        if (assetsHomeOutput != null) {
            return assetsHomeOutput.data;
        }
        return null;
    }

    public static ObservableSource lambda$getHomeAssetsTokens$7(String str, AssetsHomeData assetsHomeData) throws Exception {
        TokensMap tokensMap = new TokensMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        TokenSortType tokenSortType = KVController.getInstance().getTokenSortType(str);
        if (assetsHomeData != null) {
            if (TokenSortType.SORT_BY_USER.equals(tokenSortType)) {
                new TokenSortByUser(FollowAssetsSortListController.getInstance().getTokenSortList(str)).sort(assetsHomeData.token);
            } else if (TokenSortType.SORT_BY_VALUE.equals(tokenSortType)) {
                new TokenSortByValue(PriceUpdater.getTRX_price()).sort(assetsHomeData.token);
            } else {
                new TokenSortByName().sort(assetsHomeData.token);
            }
        }
        if (assetsHomeData != null && assetsHomeData.token != null) {
            Iterator<TokenBean> it = assetsHomeData.token.iterator();
            while (it.hasNext()) {
                TokenBean next = it.next();
                if (next.getType() == 0 || next.getTop() == 2 || BigDecimalUtils.MoreThan((Object) next.getBalanceBigDecimal(), (Object) 0)) {
                    arrayList.add(next);
                } else {
                    arrayList2.add(next);
                }
            }
        }
        tokensMap.index1 = 1;
        tokensMap.tokens1 = arrayList;
        tokensMap.index2 = 3;
        tokensMap.tokens2 = arrayList2;
        return Observable.just(tokensMap);
    }

    private static Observable<TokensMap> getHomeCollections(final String str, final boolean z) {
        return Observable.just(str).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getHomeCollections$9(z, str, (String) obj);
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getHomeCollections$10(str, (List) obj);
            }
        });
    }

    public static ObservableSource lambda$getHomeCollections$9(boolean z, String str, String str2) throws Exception {
        List<NftTokenBean> allAddedTokenList;
        if (!z && (allAddedTokenList = NftTokenBeanController.getInstance().getAllAddedTokenList(str2)) != null && allAddedTokenList.size() > 0) {
            return Observable.just(allAddedTokenList);
        }
        return AssetsManager.getInstance().requestUserCollections(str).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getHomeCollections$8((NftAssetOutput) obj);
            }
        });
    }

    public static List lambda$getHomeCollections$8(NftAssetOutput nftAssetOutput) throws Exception {
        if (nftAssetOutput != null) {
            return nftAssetOutput.getData();
        }
        return null;
    }

    public static ObservableSource lambda$getHomeCollections$10(String str, List list) throws Exception {
        List<TokenBean> list2;
        if (list != null) {
            list2 = AssetsManager.transferNftTokenBeanToTokenBean(list);
            TokenSortType tokenSortType = KVController.getInstance().getTokenSortType(str);
            if (TokenSortType.SORT_BY_USER.equals(tokenSortType)) {
                new TokenSortByUser(FollowAssetsSortListController.getInstance().getTokenSortList(str, 5)).sort(list2);
            } else if (TokenSortType.SORT_BY_VALUE.equals(tokenSortType)) {
                new TokenSortByValue(PriceUpdater.getTRX_price()).sort(list2);
            } else {
                new TokenSortByName().sort(list2);
            }
        } else {
            list2 = null;
        }
        TokensMap tokensMap = new TokensMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (list2 != null) {
            for (TokenBean tokenBean : list2) {
                if (tokenBean.getTop() == 2 || tokenBean.getTop() == 2 || BigDecimalUtils.MoreThan((Object) tokenBean.getBalanceBigDecimal(), (Object) 0)) {
                    arrayList.add(tokenBean);
                } else {
                    arrayList2.add(tokenBean);
                }
            }
        }
        tokensMap.index1 = 2;
        tokensMap.tokens1 = arrayList;
        tokensMap.index2 = 4;
        tokensMap.tokens2 = arrayList2;
        return Observable.just(tokensMap);
    }

    private static Observable<TokensMap> getMyAllAssetsTokens(String str) {
        return Observable.just(str).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource map;
                map = AssetsManager.MyAssets.requestMyAssets((String) obj).map(new Function() {
                    @Override
                    public final Object apply(Object obj2) {
                        return LocalTokenBeansHelper.lambda$getMyAllAssetsTokens$11((AssetsDataOutput) obj2);
                    }
                });
                return map;
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getMyAllAssetsTokens$13((List) obj);
            }
        });
    }

    public static List lambda$getMyAllAssetsTokens$11(AssetsDataOutput assetsDataOutput) throws Exception {
        if (assetsDataOutput == null || assetsDataOutput.getData() == null) {
            return null;
        }
        return assetsDataOutput.getData().getTokens();
    }

    public static ObservableSource lambda$getMyAllAssetsTokens$13(List list) throws Exception {
        TokensMap tokensMap = new TokensMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                TokenBean tokenBean = (TokenBean) it.next();
                if (tokenBean.getType() != 0 && !tokenBean.getIsInAssets()) {
                    if (BigDecimalUtils.MoreThan((Object) tokenBean.getBalanceBigDecimal(), (Object) 0)) {
                        arrayList.add(tokenBean);
                    } else {
                        arrayList2.add(tokenBean);
                    }
                }
            }
        }
        tokensMap.index1 = 5;
        tokensMap.tokens1 = arrayList;
        tokensMap.index2 = 7;
        tokensMap.tokens2 = arrayList2;
        return Observable.just(tokensMap);
    }

    private static Observable<TokensMap> getMyAllCollections(String str) {
        return Observable.just(str).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource map;
                map = AssetsManager.MyCollections.requestMyCollections((String) obj).map(new Function() {
                    @Override
                    public final Object apply(Object obj2) {
                        return LocalTokenBeansHelper.lambda$getMyAllCollections$14((NftDataOutput) obj2);
                    }
                });
                return map;
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LocalTokenBeansHelper.lambda$getMyAllCollections$16((List) obj);
            }
        });
    }

    public static List lambda$getMyAllCollections$14(NftDataOutput nftDataOutput) throws Exception {
        if (nftDataOutput == null || nftDataOutput.getData() == null) {
            return null;
        }
        return nftDataOutput.getData().getToken();
    }

    public static ObservableSource lambda$getMyAllCollections$16(List list) throws Exception {
        TokensMap tokensMap = new TokensMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (list != null) {
            ArrayList<TokenBean> arrayList3 = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList3.add(((NftTokenBean) it.next()).convertToTokenBean());
            }
            for (TokenBean tokenBean : arrayList3) {
                if (tokenBean.getTop() != 2 && !tokenBean.getIsInAssets()) {
                    if (BigDecimalUtils.MoreThan((Object) tokenBean.getBalanceBigDecimal(), (Object) 0)) {
                        arrayList.add(tokenBean);
                    } else {
                        arrayList2.add(tokenBean);
                    }
                }
            }
        }
        tokensMap.index1 = 6;
        tokensMap.tokens1 = arrayList;
        tokensMap.index2 = 8;
        tokensMap.tokens2 = arrayList2;
        return Observable.just(tokensMap);
    }

    public static class TokensMap {
        public int index1;
        public int index2;
        public List<TokenBean> tokens1;
        public List<TokenBean> tokens2;

        private TokensMap() {
        }
    }
}
