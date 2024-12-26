package com.tron.wallet.business.tronpower.stake;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.SparseArray;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tronpower.stake.SelectStakeReceiveAccountContract;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.tron.walletserver.Wallet;
public class SelectStakeReceiveAccountModel implements SelectStakeReceiveAccountContract.Model {
    private final SparseArray<List<NameAddressExtraBean>> allAddress = new SparseArray<>();

    @Override
    public synchronized SparseArray<List<NameAddressExtraBean>> getAllAddresses(final String str) {
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
                    return SelectStakeReceiveAccountModel.lambda$getAllAddresses$0(str, (AddressDao) obj);
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
        this.allAddress.put(0, arrayList);
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
                    return SelectStakeReceiveAccountModel.lambda$getAllAddresses$2((Wallet) obj);
                }
            }).sorted(new Comparator() {
                @Override
                public final int compare(Object obj, Object obj2) {
                    return SelectStakeReceiveAccountModel.lambda$getAllAddresses$3((Wallet) obj, (Wallet) obj2);
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
        this.allAddress.put(1, arrayList2);
        return this.allAddress;
    }

    public static boolean lambda$getAllAddresses$0(String str, AddressDao addressDao) {
        return (addressDao == null || TextUtils.equals(addressDao.getAddress(), str)) ? false : true;
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

    @Override
    public NameAddressExtraBean findFirst(String str, final String str2) {
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
                    boolean equals;
                    equals = TextUtils.equals(((NameAddressExtraBean) obj).getAddress(), str2);
                    return equals;
                }
            }).findFirst().ifPresent(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SelectStakeReceiveAccountModel.lambda$findFirst$6(nameAddressExtraBeanArr, (NameAddressExtraBean) obj);
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

    public static void lambda$findFirst$6(NameAddressExtraBean[] nameAddressExtraBeanArr, NameAddressExtraBean nameAddressExtraBean) {
        nameAddressExtraBeanArr[0] = nameAddressExtraBean.copy();
    }

    @Override
    public List<NameAddressExtraBean> doSearch(String str, final String str2) {
        if (considerNoInput(str2)) {
            return new ArrayList();
        }
        if (this.allAddress.size() == 0) {
            getAllAddresses(str);
        }
        final ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.allAddress.size(); i++) {
            Collection.-EL.stream(this.allAddress.valueAt(i)).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$doSearch$7(str2, arrayList, (NameAddressExtraBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        Collections.sort(arrayList, new Comparator() {
            @Override
            public final int compare(Object obj, Object obj2) {
                return SelectStakeReceiveAccountModel.lambda$doSearch$8((NameAddressExtraBean) obj, (NameAddressExtraBean) obj2);
            }
        });
        return arrayList;
    }

    public void lambda$doSearch$7(String str, List list, NameAddressExtraBean nameAddressExtraBean) {
        NameAddressExtraBean copy = nameAddressExtraBean.copy();
        appendMatchedResult(str, list, copy, copy.getName().toString(), copy.getAddress().toString());
    }

    public static int lambda$doSearch$8(NameAddressExtraBean nameAddressExtraBean, NameAddressExtraBean nameAddressExtraBean2) {
        return nameAddressExtraBean2.getPriority().ordinal() - nameAddressExtraBean.getPriority().ordinal();
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

    @Override
    public NameAddressExtraBean insertNewAddedAddress(String str, String str2, String str3) {
        List<NameAddressExtraBean> valueAt = this.allAddress.valueAt(0);
        if (valueAt.isEmpty()) {
            valueAt = new ArrayList<>();
        }
        NameAddressExtraBean nameAddressExtraBean = NameAddressExtraBean.getDefault();
        nameAddressExtraBean.setName(str2);
        nameAddressExtraBean.setAddress(str);
        nameAddressExtraBean.setStorage(NameAddressExtraBean.Storage.ADDRESS_BOOK);
        nameAddressExtraBean.setIndentAddress(StringTronUtil.indentAddress(str, 6));
        nameAddressExtraBean.setExtra(str3);
        valueAt.add(nameAddressExtraBean);
        this.allAddress.put(0, valueAt);
        return nameAddressExtraBean;
    }

    public boolean considerNoInput(String str) {
        return str.getBytes().length <= 0;
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
