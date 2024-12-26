package com.tron.wallet.common.bean;

import android.text.TextUtils;
import android.util.Pair;
import com.tron.wallet.business.mutil.bean.MultiAddressOutput;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.bean.AddressDao;
import j$.util.Collection;
import j$.util.Objects;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import org.tron.walletserver.Wallet;
public class NameAddressExtraBean {
    public static final int INDENT_COUNT = 6;
    String extra = "";
    CharSequence name = "";
    CharSequence address = "";
    CharSequence indentAddress = "";
    Storage storage = Storage.NONE;
    SelectWalletBean.Priority priority = SelectWalletBean.Priority.NONE;
    boolean isScam = false;

    public enum Storage {
        RECENT,
        ADDRESS_BOOK,
        MY_ACCOUNT,
        NONE
    }

    public CharSequence getAddress() {
        return this.address;
    }

    public String getExtra() {
        return this.extra;
    }

    public CharSequence getIndentAddress() {
        return this.indentAddress;
    }

    public CharSequence getName() {
        return this.name;
    }

    public SelectWalletBean.Priority getPriority() {
        return this.priority;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public boolean isScam() {
        return this.isScam;
    }

    public void setAddress(CharSequence charSequence) {
        this.address = charSequence;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public void setIndentAddress(CharSequence charSequence) {
        this.indentAddress = charSequence;
    }

    public void setName(CharSequence charSequence) {
        this.name = charSequence;
    }

    public void setPriority(SelectWalletBean.Priority priority) {
        this.priority = priority;
    }

    public void setScam(boolean z) {
        this.isScam = z;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String toString() {
        return "NameAddressExtraBean(extra=" + getExtra() + ", name=" + ((Object) getName()) + ", address=" + ((Object) getAddress()) + ", indentAddress=" + ((Object) getIndentAddress()) + ", storage=" + getStorage() + ", priority=" + getPriority() + ", isScam=" + isScam() + ")";
    }

    public boolean equals(NameAddressExtraBean nameAddressExtraBean) {
        if (nameAddressExtraBean == null) {
            return false;
        }
        return TextUtils.equals(this.address, nameAddressExtraBean.address);
    }

    public static NameAddressExtraBean fromAddressBook(AddressDao addressDao) {
        NameAddressExtraBean nameAddressExtraBean = new NameAddressExtraBean();
        nameAddressExtraBean.setName(addressDao.getName());
        nameAddressExtraBean.setAddress(addressDao.getAddress());
        nameAddressExtraBean.setExtra(addressDao.getDescription());
        nameAddressExtraBean.setIndentAddress(StringTronUtil.indentAddress(nameAddressExtraBean.address.toString(), 6));
        nameAddressExtraBean.setStorage(Storage.ADDRESS_BOOK);
        return nameAddressExtraBean;
    }

    public static NameAddressExtraBean fromWallet(Wallet wallet) {
        NameAddressExtraBean nameAddressExtraBean = new NameAddressExtraBean();
        nameAddressExtraBean.setName(wallet.getWalletName());
        nameAddressExtraBean.setAddress(wallet.getAddress());
        nameAddressExtraBean.setIndentAddress(StringTronUtil.indentAddress(nameAddressExtraBean.address.toString(), 6));
        nameAddressExtraBean.setStorage(Storage.MY_ACCOUNT);
        return nameAddressExtraBean;
    }

    public static NameAddressExtraBean fromRecentRecord(RecentSendBean recentSendBean) {
        NameAddressExtraBean nameAddressExtraBean = new NameAddressExtraBean();
        nameAddressExtraBean.setAddress(recentSendBean.getReceiverAddress());
        nameAddressExtraBean.setIndentAddress(StringTronUtil.indentAddress(nameAddressExtraBean.address.toString(), 6));
        nameAddressExtraBean.setStorage(Storage.RECENT);
        nameAddressExtraBean.setExtra(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(recentSendBean.getTimestamp()));
        return nameAddressExtraBean;
    }

    public static Pair<List<NameAddressExtraBean>, Map<String, MultiAddressOutput>> fromMultiAddressOutput(List<MultiAddressOutput> list, final Map<String, String> map) {
        final ArrayList arrayList = new ArrayList();
        final HashMap hashMap = new HashMap();
        if (list != null && !list.isEmpty()) {
            Collection.-EL.stream(list).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    NameAddressExtraBean.lambda$fromMultiAddressOutput$0(map, arrayList, hashMap, (MultiAddressOutput) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        return new Pair<>(arrayList, hashMap);
    }

    public static void lambda$fromMultiAddressOutput$0(Map map, List list, Map map2, MultiAddressOutput multiAddressOutput) {
        NameAddressExtraBean nameAddressExtraBean = new NameAddressExtraBean();
        String ownerAddress = multiAddressOutput.getOwnerAddress();
        nameAddressExtraBean.setAddress(ownerAddress);
        nameAddressExtraBean.setName((CharSequence) map.get(ownerAddress));
        nameAddressExtraBean.setIndentAddress(StringTronUtil.indentAddress(nameAddressExtraBean.address.toString(), 6));
        nameAddressExtraBean.setStorage(Storage.NONE);
        list.add(nameAddressExtraBean);
        map2.put(ownerAddress, multiAddressOutput);
    }

    public NameAddressExtraBean copy() {
        NameAddressExtraBean nameAddressExtraBean = new NameAddressExtraBean();
        nameAddressExtraBean.setAddress(this.address);
        nameAddressExtraBean.setName(this.name);
        nameAddressExtraBean.setIndentAddress(this.indentAddress);
        nameAddressExtraBean.setExtra(this.extra);
        nameAddressExtraBean.setStorage(this.storage);
        return nameAddressExtraBean;
    }

    public static NameAddressExtraBean getDefault() {
        return new NameAddressExtraBean();
    }
}
