package com.tron.wallet.business.walletmanager.selectwallet.controller;

import android.text.TextUtils;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import com.tron.wallet.db.wallet.WalletUtils;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Collectors;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.tron.walletserver.Wallet;
public class RecentlyWalletController {
    public static final int MAX_RECENTLY_WALLETS = 10;
    public static final int MAX_RECENTLY_WALLETS_DISPLAY = 3;
    public static final int MAX_WALLETS_TO_SHOW_RECENTLY = 6;

    public static List<String> getRecentlyWallet() {
        return SpAPI.THIS.getRecentlyWallet();
    }

    public static void setRecentlyWallet(Wallet wallet) {
        if (wallet == null) {
            return;
        }
        List<String> recentlyWallet = SpAPI.THIS.getRecentlyWallet();
        recentlyWallet.remove(wallet.getWalletName());
        recentlyWallet.add(0, wallet.getWalletName());
        if (recentlyWallet.size() > 10) {
            recentlyWallet.remove(10);
        }
        SpAPI.THIS.setRecentlyWallet(recentlyWallet);
    }

    public static List<SelectWalletBean> getRecentlyWalletBeans(final Wallet wallet, final List<TRXAccountBalanceBean> list) {
        return (List) Collection.-EL.stream(getRecentlyWallet()).map(new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                Wallet wallet2;
                wallet2 = WalletUtils.getWallet((String) obj);
                return wallet2;
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
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
                return RecentlyWalletController.lambda$getRecentlyWalletBeans$1((Wallet) obj);
            }
        }).map(new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                return RecentlyWalletController.lambda$getRecentlyWalletBeans$4(Wallet.this, list, (Wallet) obj);
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
            }
        }).limit(3L).collect(Collectors.toList());
    }

    public static boolean lambda$getRecentlyWalletBeans$1(Wallet wallet) {
        return (wallet == null || wallet.isShieldedWallet()) ? false : true;
    }

    public static SelectWalletBean lambda$getRecentlyWalletBeans$4(Wallet wallet, List list, final Wallet wallet2) {
        final SelectWalletBean selectWalletBean = new SelectWalletBean();
        selectWalletBean.setWallet(wallet2);
        if (wallet != null) {
            selectWalletBean.setSelected(wallet2.getWalletName().equals(wallet.getWalletName()));
        }
        selectWalletBean.setGroupType(WalletGroupType.RECENTLY);
        if (list != null) {
            Collection.-EL.stream(list).filter(new Predicate() {
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
                    boolean equals;
                    equals = TextUtils.equals(Wallet.this.getAddress(), ((TRXAccountBalanceBean) obj).getAddress());
                    return equals;
                }
            }).findFirst().ifPresent(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    RecentlyWalletController.lambda$getRecentlyWalletBeans$3(SelectWalletBean.this, (TRXAccountBalanceBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        return selectWalletBean;
    }

    public static void lambda$getRecentlyWalletBeans$3(SelectWalletBean selectWalletBean, TRXAccountBalanceBean tRXAccountBalanceBean) {
        if (tRXAccountBalanceBean.getAccountType() >= 0) {
            selectWalletBean.setAccountType(tRXAccountBalanceBean.getAccountType());
        }
    }
}
