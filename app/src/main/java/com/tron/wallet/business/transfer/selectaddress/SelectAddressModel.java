package com.tron.wallet.business.transfer.selectaddress;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Pair;
import android.util.SparseArray;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.bean.DappSearchBean;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressContract;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.RecentSendBean;
import com.tron.wallet.common.bean.RiskAccountOutput;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.Controller.RecentSendController;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.tron.walletserver.Wallet;
public class SelectAddressModel implements SelectSendAddressContract.Model {
    private final SparseArray<List<NameAddressExtraBean>> allAddress = new SparseArray<>();
    private PageType pageType = PageType.TRANSFER;

    public static boolean lambda$getAllAddresses$0(AddressDao addressDao) {
        return addressDao != null;
    }

    public static boolean lambda$getAllAddresses$5(RecentSendBean recentSendBean) {
        return recentSendBean != null;
    }

    @Override
    public Observable<SparseArray<List<NameAddressExtraBean>>> checkAddressIsScams(SparseArray<List<NameAddressExtraBean>> sparseArray) {
        return null;
    }

    @Override
    public void initType(PageType pageType) {
        this.pageType = pageType;
    }

    @Override
    public SparseArray<List<NameAddressExtraBean>> getAllAddresses(String str) {
        final ArrayList arrayList = new ArrayList();
        try {
            Collection.-EL.stream(AddressController.getInstance().searchAll()).filter(new Predicate() {
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
                    return SelectAddressModel.lambda$getAllAddresses$0((AddressDao) obj);
                }
            }).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    arrayList.add(NameAddressExtraBean.fromAddressBook((AddressDao) obj));
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.allAddress.put(1, arrayList);
        final ArrayList arrayList2 = new ArrayList();
        try {
            Collection.-EL.stream(WalletUtils.getAllWallets()).filter(new Predicate() {
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
                    return SelectAddressModel.lambda$getAllAddresses$2((Wallet) obj);
                }
            }).sorted(new Comparator() {
                @Override
                public final int compare(Object obj, Object obj2) {
                    return SelectAddressModel.lambda$getAllAddresses$3((Wallet) obj, (Wallet) obj2);
                }
            }).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    arrayList2.add(NameAddressExtraBean.fromWallet((Wallet) obj));
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
        this.allAddress.put(2, arrayList2);
        final ArrayList arrayList3 = new ArrayList();
        try {
            Collection.-EL.stream(RecentSendController.getInstance().queryByAddress(this.pageType == PageType.TRANSFER ? 0 : 1, str)).filter(new Predicate() {
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
                    return SelectAddressModel.lambda$getAllAddresses$5((RecentSendBean) obj);
                }
            }).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$getAllAddresses$6(arrayList, arrayList2, arrayList3, (RecentSendBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        } catch (Exception e3) {
            LogUtils.e(e3);
        }
        this.allAddress.put(0, arrayList3);
        return this.allAddress;
    }

    public static boolean lambda$getAllAddresses$2(Wallet wallet) {
        return (wallet == null || wallet.isShieldedWallet()) ? false : true;
    }

    public static int lambda$getAllAddresses$3(Wallet wallet, Wallet wallet2) {
        int value = WalletGroupType.getGroupType(wallet).getValue() - WalletGroupType.getGroupType(wallet2).getValue();
        if (value != 0) {
            return value;
        }
        int i = ((wallet.getCreateTime() - wallet2.getCreateTime()) > 0L ? 1 : ((wallet.getCreateTime() - wallet2.getCreateTime()) == 0L ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        return i < 0 ? -1 : 1;
    }

    public void lambda$getAllAddresses$6(List list, List list2, List list3, RecentSendBean recentSendBean) {
        NameAddressExtraBean fromRecentRecord = NameAddressExtraBean.fromRecentRecord(recentSendBean);
        findInList(list, fromRecentRecord);
        if (TextUtils.isEmpty(fromRecentRecord.getName())) {
            findInList(list2, fromRecentRecord);
        }
        list3.add(fromRecentRecord);
    }

    @Override
    public List<NameAddressExtraBean> doSearch(final String str, final String str2) {
        if (considerNoInput(str2)) {
            return new ArrayList();
        }
        if (this.allAddress.size() == 0) {
            getAllAddresses(str);
        }
        final ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.allAddress.size(); i++) {
            SparseArray<List<NameAddressExtraBean>> sparseArray = this.allAddress;
            if (sparseArray != null && sparseArray.valueAt(i) != null) {
                Collection.-EL.stream(this.allAddress.valueAt(i)).filter(new Predicate() {
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
                        return SelectAddressModel.lambda$doSearch$7(str, (NameAddressExtraBean) obj);
                    }
                }).forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        lambda$doSearch$8(str2, arrayList, (NameAddressExtraBean) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            }
        }
        Collections.sort(arrayList, new Comparator() {
            @Override
            public final int compare(Object obj, Object obj2) {
                return SelectAddressModel.lambda$doSearch$9((NameAddressExtraBean) obj, (NameAddressExtraBean) obj2);
            }
        });
        return arrayList;
    }

    public static boolean lambda$doSearch$7(String str, NameAddressExtraBean nameAddressExtraBean) {
        return (TextUtils.equals(str, nameAddressExtraBean.getAddress()) || TextUtils.equals(nameAddressExtraBean.getAddress(), str)) ? false : true;
    }

    public void lambda$doSearch$8(String str, List list, NameAddressExtraBean nameAddressExtraBean) {
        NameAddressExtraBean copy = nameAddressExtraBean.copy();
        appendMatchedResult(str, list, copy, copy.getName().toString(), copy.getAddress().toString());
    }

    public static int lambda$doSearch$9(NameAddressExtraBean nameAddressExtraBean, NameAddressExtraBean nameAddressExtraBean2) {
        return nameAddressExtraBean2.getPriority().ordinal() - nameAddressExtraBean.getPriority().ordinal();
    }

    @Override
    public NameAddressExtraBean findFirst(final String str, final String str2) {
        if (considerNoInput(str2)) {
            return null;
        }
        if (this.allAddress.size() == 0) {
            getAllAddresses(str);
        }
        final NameAddressExtraBean[] nameAddressExtraBeanArr = new NameAddressExtraBean[1];
        for (int i = 0; i < this.allAddress.size(); i++) {
            Collection.-EL.stream(this.allAddress.valueAt(i)).filter(new Predicate() {
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
                    return SelectAddressModel.lambda$findFirst$10(str, str2, (NameAddressExtraBean) obj);
                }
            }).findFirst().ifPresent(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SelectAddressModel.lambda$findFirst$11(nameAddressExtraBeanArr, (NameAddressExtraBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            NameAddressExtraBean nameAddressExtraBean = nameAddressExtraBeanArr[0];
            if (nameAddressExtraBean != null) {
                return nameAddressExtraBean;
            }
        }
        return null;
    }

    public static boolean lambda$findFirst$10(String str, String str2, NameAddressExtraBean nameAddressExtraBean) {
        return !TextUtils.equals(nameAddressExtraBean.getAddress(), str) && TextUtils.equals(nameAddressExtraBean.getAddress(), str2);
    }

    public static void lambda$findFirst$11(NameAddressExtraBean[] nameAddressExtraBeanArr, NameAddressExtraBean nameAddressExtraBean) {
        nameAddressExtraBeanArr[0] = nameAddressExtraBean.copy();
    }

    @Override
    public boolean considerNoInput(String str) {
        return str.getBytes().length <= 0;
    }

    @Override
    public NameAddressExtraBean insertNewAddedAddress(String str, String str2, String str3) {
        List<NameAddressExtraBean> valueAt = this.allAddress.valueAt(1);
        if (valueAt.isEmpty()) {
            valueAt = new ArrayList<>();
        }
        NameAddressExtraBean nameAddressExtraBean = NameAddressExtraBean.getDefault();
        nameAddressExtraBean.setName(str2);
        nameAddressExtraBean.setAddress(str);
        nameAddressExtraBean.setStorage(NameAddressExtraBean.Storage.ADDRESS_BOOK);
        nameAddressExtraBean.setIndentAddress(str);
        nameAddressExtraBean.setExtra(str3);
        valueAt.add(nameAddressExtraBean);
        this.allAddress.put(1, valueAt);
        return nameAddressExtraBean;
    }

    @Override
    public Pair<CharSequence, CharSequence> findSimilarAddress(Context context, final String str) {
        int i;
        AtomicReference atomicReference = new AtomicReference();
        boolean z = false;
        int[] iArr = {1, 2, 0};
        final AtomicInteger atomicInteger = new AtomicInteger(-1);
        final AtomicReference atomicReference2 = new AtomicReference();
        final AtomicInteger atomicInteger2 = new AtomicInteger(-1);
        int i2 = 0;
        while (i2 < 3) {
            final int i3 = iArr[i2];
            final AtomicBoolean atomicBoolean = new AtomicBoolean(z);
            int i4 = i2;
            Collection.-EL.stream(this.allAddress.valueAt(i3)).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SelectAddressModel.lambda$findSimilarAddress$12(str, i3, atomicBoolean, atomicInteger, atomicReference2, atomicInteger2, (NameAddressExtraBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            if (atomicBoolean.get()) {
                return null;
            }
            i2 = i4 + 1;
            z = false;
        }
        if (atomicInteger.get() <= 0 || atomicReference2.get() == null || atomicInteger2.get() < 0) {
            return null;
        }
        try {
            if (atomicInteger2.get() == 1) {
                i = R.string.address_book;
            } else {
                i = atomicInteger2.get() == 2 ? R.string.my_account : R.string.recently_send;
            }
            atomicReference.set(new Pair(TextUtils.isEmpty(((NameAddressExtraBean) atomicReference2.get()).getName()) ? String.format("%s: %s", context.getString(i), ((NameAddressExtraBean) atomicReference2.get()).getAddress()) : String.format("%s: %s (%s)", context.getString(i), ((NameAddressExtraBean) atomicReference2.get()).getName(), ((NameAddressExtraBean) atomicReference2.get()).getAddress()), ((NameAddressExtraBean) atomicReference2.get()).getAddress().toString().substring(atomicInteger.get())));
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return (Pair) atomicReference.get();
    }

    public static void lambda$findSimilarAddress$12(String str, int i, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, AtomicReference atomicReference, AtomicInteger atomicInteger2, NameAddressExtraBean nameAddressExtraBean) {
        try {
            int i2 = -1;
            for (int length = str.length() - 3; length >= 0 && str.substring(length).equalsIgnoreCase(nameAddressExtraBean.getAddress().toString().substring(length)); length--) {
                i2 = length;
            }
            if (i2 == 0 && i > 0) {
                atomicBoolean.set(true);
            } else if (i2 > 0) {
                if (atomicInteger.get() <= 0 || atomicInteger.get() > i2) {
                    atomicInteger.set(i2);
                    atomicReference.set(nameAddressExtraBean);
                    atomicInteger2.set(i);
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public Observable<RiskAccountOutput> checkAccountRisk(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestAccountRisk(str).compose(RxSchedulers2.io_main());
    }

    @Override
    public Observable<DappSearchBean> checkAddressIsScam(SparseArray<List<NameAddressExtraBean>> sparseArray) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).doSearchDapp("a").compose(RxSchedulers2.io_main());
    }

    public void appendMatchedResult(String str, List<NameAddressExtraBean> list, NameAddressExtraBean nameAddressExtraBean, String str2, String str3) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(AppContextUtil.getContext().getResources().getColor(R.color.blue_3c));
        StyleSpan styleSpan = new StyleSpan(1);
        int indexOf = str2.toLowerCase().indexOf(str.toLowerCase());
        if (indexOf >= 0) {
            list.add(nameAddressExtraBean);
            SpannableString spannableString = new SpannableString(str2);
            spannableString.setSpan(foregroundColorSpan, indexOf, str.length() + indexOf, 18);
            spannableString.setSpan(styleSpan, indexOf, str.length() + indexOf, 18);
            nameAddressExtraBean.setName(spannableString);
            nameAddressExtraBean.setPriority(indexOf == 0 ? SelectWalletBean.Priority.HIGH : SelectWalletBean.Priority.MIDDLE);
            return;
        }
        int indexOf2 = str3.toLowerCase().indexOf(str.toLowerCase());
        if (indexOf2 >= 0) {
            list.add(list.size(), nameAddressExtraBean);
            int length = str.length() + indexOf2;
            SpannableString spannableString2 = new SpannableString(nameAddressExtraBean.getAddress());
            spannableString2.setSpan(foregroundColorSpan, indexOf2, length, 17);
            spannableString2.setSpan(styleSpan, indexOf2, length, 17);
            nameAddressExtraBean.setAddress(spannableString2);
            nameAddressExtraBean.setPriority(indexOf2 == 0 ? SelectWalletBean.Priority.LOW : SelectWalletBean.Priority.NONE);
        }
    }

    private void findInList(List<NameAddressExtraBean> list, final NameAddressExtraBean nameAddressExtraBean) {
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
                equals = TextUtils.equals(((NameAddressExtraBean) obj).getAddress(), NameAddressExtraBean.this.getAddress());
                return equals;
            }
        }).findFirst().ifPresent(new Consumer() {
            @Override
            public final void accept(Object obj) {
                NameAddressExtraBean.this.setName(((NameAddressExtraBean) obj).getName());
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }
}
