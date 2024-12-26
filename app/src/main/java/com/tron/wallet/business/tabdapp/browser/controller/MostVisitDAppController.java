package com.tron.wallet.business.tabdapp.browser.controller;

import android.net.Uri;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.tabdapp.browser.bean.MostVisitDAppBean;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.Controller.DappAuthorizedController;
import com.tron.wallet.db.bean.DAppNonOfficialAuthorizedProjectBean;
import com.tron.wallet.db.bean.DappAuthorizedProjectBean;
import com.tron.wallet.db.greendao.MostVisitDAppBeanDao;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import org.apache.commons.cli.HelpFormatter;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
public class MostVisitDAppController extends BaseController<MostVisitDAppBean> {
    private static final long COOL_DOWN_DURATION = 86400000;
    private static final int FLAG_WALLET_CONNECTED = 1;
    private static final int MAX_COUNT = 100;
    private static MostVisitDAppController instance;

    public enum Action {
        VISIT,
        CONNECT,
        SIGN
    }

    public static void lambda$dAppVisitDataUpdate$11(Boolean bool) throws Exception {
    }

    public void dAppConnected(String str) {
    }

    private MostVisitDAppController() {
        super(true);
    }

    public static MostVisitDAppController getInstance() {
        if (instance == null) {
            synchronized (MostVisitDAppController.class) {
                if (instance == null) {
                    instance = new MostVisitDAppController();
                }
            }
        }
        return instance;
    }

    public void initCapacity() {
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                Integer lambda$initCapacity$0;
                lambda$initCapacity$0 = lambda$initCapacity$0();
                return lambda$initCapacity$0;
            }
        }).doOnNext(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initCapacity$2((Integer) obj);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                LogUtils.d("MostVisitDAppController", "size:" + ((Integer) obj));
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SentryUtil.captureException((Throwable) obj);
            }
        });
    }

    public Integer lambda$initCapacity$0() throws Exception {
        int count = (int) this.beanDao.queryBuilder().count();
        if (count > 100) {
            deleteMultiObject(this.beanDao.queryBuilder().orderAsc(MostVisitDAppBeanDao.Properties.Score, MostVisitDAppBeanDao.Properties.VisitTimestamp).limit(count - 100).list());
        }
        return Integer.valueOf(count);
    }

    public void lambda$initCapacity$2(Integer num) throws Exception {
        if (num.intValue() > 0) {
            final long currentTimeMillis = System.currentTimeMillis();
            List list = this.beanDao.queryBuilder().where(MostVisitDAppBeanDao.Properties.Score.gt(0), MostVisitDAppBeanDao.Properties.UpdateTimestamp.lt(Long.valueOf(currentTimeMillis - 86400000))).list();
            Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    MostVisitDAppController.lambda$initCapacity$1(currentTimeMillis, (MostVisitDAppBean) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            insertMultiObject(list);
        }
    }

    public static void lambda$initCapacity$1(long j, MostVisitDAppBean mostVisitDAppBean) {
        int score = mostVisitDAppBean.getScore() - (AssetsConfig.getDappScore().getWane() * ((int) ((j - mostVisitDAppBean.getUpdateTimestamp()) / 86400000)));
        if (score < 0) {
            score = 0;
        }
        mostVisitDAppBean.setScore(score);
        mostVisitDAppBean.setUpdateTimestamp(j);
    }

    public Observable<List<MostVisitDAppBean>> getMostVisitDApps(int i) {
        return Observable.just(Integer.valueOf(i)).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$getMostVisitDApps$9;
                lambda$getMostVisitDApps$9 = lambda$getMostVisitDApps$9((Integer) obj);
                return lambda$getMostVisitDApps$9;
            }
        });
    }

    public ObservableSource lambda$getMostVisitDApps$9(Integer num) throws Exception {
        List<DappAuthorizedProjectBean> queryAllDistinctAuthorizedProjectList = DappAuthorizedController.queryAllDistinctAuthorizedProjectList();
        List<DAppNonOfficialAuthorizedProjectBean> queryAllDistinctAuthorizedProjectFromNonOfficialList = DappAuthorizedController.queryAllDistinctAuthorizedProjectFromNonOfficialList();
        final List list = queryAllDistinctAuthorizedProjectList != null ? (List) Collection.-EL.stream(queryAllDistinctAuthorizedProjectList).map(new java.util.function.Function() {
            public java.util.function.Function andThen(java.util.function.Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                String url;
                url = ((DappAuthorizedProjectBean) obj).getUrl();
                return url;
            }

            public java.util.function.Function compose(java.util.function.Function function) {
                return Objects.requireNonNull(function);
            }
        }).collect(Collectors.toList()) : null;
        List list2 = queryAllDistinctAuthorizedProjectFromNonOfficialList != null ? (List) Collection.-EL.stream(queryAllDistinctAuthorizedProjectFromNonOfficialList).map(new java.util.function.Function() {
            public java.util.function.Function andThen(java.util.function.Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                String url;
                url = ((DAppNonOfficialAuthorizedProjectBean) obj).getUrl();
                return url;
            }

            public java.util.function.Function compose(java.util.function.Function function) {
                return Objects.requireNonNull(function);
            }
        }).collect(Collectors.toList()) : null;
        if (list == null) {
            list = new ArrayList();
        }
        if (list2 == null) {
            list2 = new ArrayList();
        }
        list.addAll(list2);
        List list3 = this.beanDao.queryBuilder().where(MostVisitDAppBeanDao.Properties.Score.gt(0), new WhereCondition[0]).orderDesc(MostVisitDAppBeanDao.Properties.Score, MostVisitDAppBeanDao.Properties.VisitTimestamp).list();
        Object arrayList = new ArrayList();
        if (list3 != null) {
            arrayList = (List) Collection.-EL.stream(list3).filter(new Predicate() {
                public Predicate and(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                public Predicate negate() {
                    return Predicate$-CC.$default$negate(this);
                }

                public Predicate or(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                @Override
                public final boolean test(Object obj) {
                    return MostVisitDAppController.lambda$getMostVisitDApps$7((MostVisitDAppBean) obj);
                }
            }).filter(new Predicate() {
                public Predicate and(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                public Predicate negate() {
                    return Predicate$-CC.$default$negate(this);
                }

                public Predicate or(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                @Override
                public final boolean test(Object obj) {
                    return MostVisitDAppController.lambda$getMostVisitDApps$8(list, (MostVisitDAppBean) obj);
                }
            }).limit(num.intValue()).collect(Collectors.toList());
        }
        return Observable.just(arrayList);
    }

    public static boolean lambda$getMostVisitDApps$7(MostVisitDAppBean mostVisitDAppBean) {
        Uri parse = Uri.parse(IRequest.getDappReportUrl());
        return (mostVisitDAppBean.getUrl() != null && mostVisitDAppBean.getUrl().contains(parse.getHost()) && mostVisitDAppBean.getUrl().contains(parse.getFragment())) ? false : true;
    }

    public static boolean lambda$getMostVisitDApps$8(List list, MostVisitDAppBean mostVisitDAppBean) {
        return list != null && list.contains(Uri.parse(mostVisitDAppBean.getUrl()).getHost());
    }

    private MostVisitDAppBean getVisitDApp(String str) {
        try {
            Uri parse = Uri.parse(str);
            QueryBuilder queryBuilder = this.beanDao.queryBuilder();
            Property property = MostVisitDAppBeanDao.Properties.Url;
            return (MostVisitDAppBean) queryBuilder.where(property.like("%" + parse.getHost() + "%"), new WhereCondition[0]).unique();
        } catch (Throwable th) {
            LogUtils.e(th);
            this.beanDao.queryBuilder().where(MostVisitDAppBeanDao.Properties.Url.eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
            return null;
        }
    }

    public void dAppVisited(String str, String str2, String str3, boolean z) {
        dAppVisitDataUpdate(str, Action.VISIT, str2 == null ? "" : str2, str3 == null ? "" : str3, z);
    }

    public void dAppSigned(String str) {
        dAppVisitDataUpdate(str, Action.SIGN, null, null, false);
    }

    private void dAppVisitDataUpdate(String str, final Action action, final String str2, final String str3, final boolean z) {
        if (IRequest.isNile()) {
            return;
        }
        final String urlTrim = urlTrim(str);
        if (StringTronUtil.isEmpty(urlTrim)) {
            return;
        }
        LogUtils.d("MostVisitDApp", "action:" + action.toString());
        LogUtils.d("MostVisitDApp", urlTrim + HelpFormatter.DEFAULT_OPT_PREFIX + str2 + HelpFormatter.DEFAULT_OPT_PREFIX + str3);
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                Boolean lambda$dAppVisitDataUpdate$10;
                lambda$dAppVisitDataUpdate$10 = lambda$dAppVisitDataUpdate$10(urlTrim, z, action, str2, str3);
                return lambda$dAppVisitDataUpdate$10;
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                MostVisitDAppController.lambda$dAppVisitDataUpdate$11((Boolean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SentryUtil.captureException((Throwable) obj);
            }
        });
    }

    public Boolean lambda$dAppVisitDataUpdate$10(String str, boolean z, Action action, String str2, String str3) throws Exception {
        int dappSign;
        boolean insertOrReplace;
        synchronized (this) {
            MostVisitDAppBean visitDApp = getVisitDApp(str);
            if (visitDApp == null) {
                visitDApp = new MostVisitDAppBean();
            }
            visitDApp.setAnonymous(z);
            int i = fun1.$SwitchMap$com$tron$wallet$business$tabdapp$browser$controller$MostVisitDAppController$Action[action.ordinal()];
            if (i == 1) {
                dappSign = AssetsConfig.getDappScore().getDappSign();
            } else if (i == 2) {
                dappSign = AssetsConfig.getDappScore().getDappAccess();
            } else {
                if (i == 3 && (visitDApp.getFlag() & 1) != 1) {
                    dappSign = AssetsConfig.getDappScore().getWalletConnect();
                    visitDApp.setFlag(1 | visitDApp.getFlag());
                }
                dappSign = 0;
            }
            visitDApp.setUrl(str);
            if (str2 != null) {
                visitDApp.setTitle(str2);
            }
            if (str3 != null) {
                visitDApp.setIcon(str3);
            }
            long currentTimeMillis = System.currentTimeMillis();
            visitDApp.setVisitTimestamp(currentTimeMillis);
            visitDApp.setUpdateTimestamp(currentTimeMillis);
            visitDApp.setScore(visitDApp.getScore() + dappSign);
            insertOrReplace = insertOrReplace(visitDApp);
        }
        return Boolean.valueOf(insertOrReplace);
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$tabdapp$browser$controller$MostVisitDAppController$Action;

        static {
            int[] iArr = new int[Action.values().length];
            $SwitchMap$com$tron$wallet$business$tabdapp$browser$controller$MostVisitDAppController$Action = iArr;
            try {
                iArr[Action.SIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$tabdapp$browser$controller$MostVisitDAppController$Action[Action.VISIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$tabdapp$browser$controller$MostVisitDAppController$Action[Action.CONNECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private String urlTrim(String str) {
        if (StringTronUtil.isEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        return trim.endsWith("/") ? trim.substring(0, trim.length() - 1) : trim;
    }
}
