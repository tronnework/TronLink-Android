package com.tron.wallet.business.walletmanager.selectwallet.sort;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.ledger.LedgerWallet;
import j$.util.Collection;
import j$.util.Objects;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class WalletSortHelper {

    static class fun6 {
        static final int[] $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletSortType;

        static {
            int[] iArr = new int[WalletSortType.values().length];
            $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletSortType = iArr;
            try {
                iArr[WalletSortType.SORT_BY_VALUE_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletSortType[WalletSortType.SORT_BY_VALUE_DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletSortType[WalletSortType.SORT_BY_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void sort(List<SelectWalletBean> list, WalletSortType walletSortType) {
        int i = fun6.$SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$WalletSortType[walletSortType.ordinal()];
        int i2 = 1;
        if (i == 1) {
            sortByValueAll(list);
        } else if (i == 2) {
            sortByValueDefault(list);
        } else if (i == 3) {
            sortByType(list);
            for (SelectWalletBean selectWalletBean : list) {
                if (selectWalletBean.isHdGroup()) {
                    selectWalletBean.setHdIndex(i2);
                    i2++;
                }
            }
        } else {
            sortByNone(list);
        }
    }

    private static void sortByNone(List<SelectWalletBean> list) {
        Collections.sort(list, new Comparator<SelectWalletBean>() {
            @Override
            public int compare(SelectWalletBean selectWalletBean, SelectWalletBean selectWalletBean2) {
                if (selectWalletBean.getGroupType() == selectWalletBean2.getGroupType()) {
                    return selectWalletBean.getWallet().getCreateTime() - selectWalletBean2.getWallet().getCreateTime() < 0 ? -1 : 1;
                }
                return selectWalletBean.getGroupType().getValue() - selectWalletBean2.getGroupType().getValue();
            }
        });
    }

    private static void sortByValueDefault(List<SelectWalletBean> list) {
        Collections.sort(list, new Comparator<SelectWalletBean>() {
            @Override
            public int compare(SelectWalletBean selectWalletBean, SelectWalletBean selectWalletBean2) {
                if (selectWalletBean.getGroupType() != WalletGroupType.WATCH || selectWalletBean2.getGroupType() == WalletGroupType.WATCH) {
                    if (selectWalletBean.getGroupType() == WalletGroupType.WATCH || selectWalletBean2.getGroupType() != WalletGroupType.WATCH) {
                        return BigDecimalUtils.Equal(Double.valueOf(selectWalletBean.getBalance()), Double.valueOf(selectWalletBean2.getBalance())) ? selectWalletBean.getWallet().getCreateTime() - selectWalletBean2.getWallet().getCreateTime() < 0 ? -1 : 1 : BigDecimalUtils.MoreThan(Double.valueOf(selectWalletBean.getBalance()), Double.valueOf(selectWalletBean2.getBalance())) ? -1 : 1;
                    }
                    return -1;
                }
                return 1;
            }
        });
    }

    private static void sortByValueAll(List<SelectWalletBean> list) {
        Collections.sort(list, new Comparator<SelectWalletBean>() {
            @Override
            public int compare(SelectWalletBean selectWalletBean, SelectWalletBean selectWalletBean2) {
                return BigDecimalUtils.Equal(Double.valueOf(selectWalletBean.getBalance()), Double.valueOf(selectWalletBean2.getBalance())) ? selectWalletBean.getWallet().getCreateTime() - selectWalletBean2.getWallet().getCreateTime() < 0 ? -1 : 1 : BigDecimalUtils.MoreThan(Double.valueOf(selectWalletBean.getBalance()), Double.valueOf(selectWalletBean2.getBalance())) ? -1 : 1;
            }
        });
    }

    private static void sortByType(List<SelectWalletBean> list) {
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletSortHelper.lambda$sortByType$0((SelectWalletBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        Collections.sort(list, new Comparator<SelectWalletBean>() {
            @Override
            public int compare(SelectWalletBean selectWalletBean, SelectWalletBean selectWalletBean2) {
                int walletBeanWeight;
                if (selectWalletBean.getGroupType() == selectWalletBean2.getGroupType()) {
                    return (selectWalletBean.getGroupType() != WalletGroupType.HEAT || (walletBeanWeight = WalletSortHelper.getWalletBeanWeight(selectWalletBean) - WalletSortHelper.getWalletBeanWeight(selectWalletBean2)) == 0) ? selectWalletBean.getCreateTime() - selectWalletBean2.getCreateTime() < 0 ? -1 : 1 : walletBeanWeight;
                }
                return selectWalletBean.getGroupType().getValue() - selectWalletBean2.getGroupType().getValue();
            }
        });
    }

    public static void lambda$sortByType$0(SelectWalletBean selectWalletBean) {
        if (selectWalletBean.isHdGroup()) {
            Collections.sort(selectWalletBean.getRelationWallets(), new Comparator<SelectWalletBean>() {
                @Override
                public int compare(SelectWalletBean selectWalletBean2, SelectWalletBean selectWalletBean3) {
                    int i;
                    int i2;
                    WalletPath walletPath = selectWalletBean2.getHdTronRelationshipBean().getWalletPath();
                    WalletPath walletPath2 = selectWalletBean3.getHdTronRelationshipBean().getWalletPath();
                    if (walletPath.account != walletPath2.account) {
                        i = walletPath.account;
                        i2 = walletPath2.account;
                    } else if (walletPath.change != walletPath2.change) {
                        i = walletPath.change;
                        i2 = walletPath2.change;
                    } else {
                        i = walletPath.accountIndex;
                        i2 = walletPath2.accountIndex;
                    }
                    return i - i2;
                }
            });
        }
    }

    public static int getWalletBeanWeight(SelectWalletBean selectWalletBean) {
        return selectWalletBean.isHdGroup() ? 0 : 1;
    }

    public static boolean matchKeyWord(String str, SelectWalletBean selectWalletBean, int i) {
        int indexOf;
        try {
        } catch (Throwable th) {
            LogUtils.e(th);
        }
        if (StringTronUtil.isEmpty(str)) {
            selectWalletBean.setPriority(SelectWalletBean.Priority.NONE);
            selectWalletBean.setSearchedTarget(SelectWalletBean.SearchedTarget.NONE);
            return true;
        }
        Wallet wallet = selectWalletBean.getWallet();
        SelectWalletBean.SearchedTarget searchedTarget = SelectWalletBean.SearchedTarget.NONE;
        SelectWalletBean.Priority priority = SelectWalletBean.Priority.NONE;
        String lowerCase = str.toLowerCase();
        String lowerCase2 = wallet.getAddress().toLowerCase();
        int indexOf2 = lowerCase2.indexOf(lowerCase);
        if (indexOf2 == 0 && lowerCase.length() == lowerCase2.length()) {
            searchedTarget = SelectWalletBean.SearchedTarget.Address;
            priority = SelectWalletBean.Priority.ADDRESS_MATCH_ALL;
            indexOf = 0;
        } else {
            String lowerCase3 = wallet.getWalletName().toLowerCase();
            indexOf = lowerCase3.indexOf(lowerCase);
            if (indexOf == 0 && lowerCase.length() == lowerCase3.length()) {
                searchedTarget = SelectWalletBean.SearchedTarget.NAME;
                priority = SelectWalletBean.Priority.NAME_MATCH_ALL;
            } else if (indexOf >= 0 && (!wallet.isWatchOnly() || LedgerWallet.isLedger(wallet))) {
                searchedTarget = SelectWalletBean.SearchedTarget.NAME;
                priority = SelectWalletBean.Priority.NAME_MATCH_PART_AND_NOT_WATCH;
            } else if (lowerCase.length() > 1 && indexOf2 >= 0 && (!wallet.isWatchOnly() || LedgerWallet.isLedger(wallet))) {
                searchedTarget = SelectWalletBean.SearchedTarget.Address;
                priority = SelectWalletBean.Priority.ADDRESS_MATCH_PART_AND_NOT_WATCH;
            } else if (indexOf >= 0) {
                searchedTarget = SelectWalletBean.SearchedTarget.NAME;
                priority = SelectWalletBean.Priority.NAME_MATCH_PART_AND_WATCH;
            } else if (lowerCase.length() > 1 && indexOf2 >= 0) {
                searchedTarget = SelectWalletBean.SearchedTarget.Address;
                priority = SelectWalletBean.Priority.ADDRESS_MATCH_PART_AND_WATCH;
            }
        }
        if (searchedTarget != SelectWalletBean.SearchedTarget.NONE) {
            selectWalletBean.setPriority(priority);
            selectWalletBean.setSearchedTarget(searchedTarget);
            if (searchedTarget == SelectWalletBean.SearchedTarget.NAME) {
                SpannableString spannableString = new SpannableString(wallet.getWalletName());
                spannableString.setSpan(new ForegroundColorSpan(i), indexOf, lowerCase.length() + indexOf, 33);
                selectWalletBean.setSearchedString(spannableString);
            } else {
                SpannableString spannableString2 = new SpannableString(wallet.getAddress());
                spannableString2.setSpan(new ForegroundColorSpan(i), indexOf2, lowerCase.length() + indexOf2, 33);
                selectWalletBean.setSearchedString(spannableString2);
            }
            return true;
        }
        return false;
    }
}
