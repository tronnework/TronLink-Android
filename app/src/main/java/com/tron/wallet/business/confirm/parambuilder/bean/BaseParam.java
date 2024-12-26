package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.business.confirm.fg.component.InfoTitle;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.bean.ConfirmExtraTitle;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TransactionBean;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.tron.protos.Protocol;
public class BaseParam implements Parcelable {
    public static final Parcelable.Creator<BaseParam> CREATOR = new Parcelable.Creator<BaseParam>() {
        @Override
        public BaseParam createFromParcel(Parcel parcel) {
            return new BaseParam(parcel);
        }

        @Override
        public BaseParam[] newArray(int i) {
            return new BaseParam[i];
        }
    };
    private ConfirmExtraText confirmExtraText;
    private ConfirmExtraTitle confirmExtraTitle;
    private long energyUsed;
    private String errorHint;
    private int errorHintType;
    private boolean hasOwnerPermission;
    private String headerHint;
    private int iconResource;
    private InfoTitle infoTitle;
    private boolean isActives;
    public int isFromDealSign;
    private boolean isOverThreshold;
    private boolean isShield;
    private int layoutHeight;
    private String message;
    private String money;
    private PageFrom pageFrom;
    private int pageIndex;
    private QrBean qrBean;
    private DataStatHelper.StatAction statAction;
    private List<ConfirmExtraText> textLists;
    private TransactionBean transactionBean;
    private List<byte[]> transactions;
    private int type;

    public interface ErrorHintType {
        public static final int ERROR = 1;
        public static final int WARN = 0;
    }

    public enum PageFrom implements Serializable {
        Local,
        DApp,
        DeepLink,
        Financial
    }

    public void addIconResource(int i) {
        this.iconResource = i;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ConfirmExtraTitle getConfirmExtraTitle() {
        return this.confirmExtraTitle;
    }

    public long getEnergyUsed() {
        return this.energyUsed;
    }

    public String getErrorHint() {
        return this.errorHint;
    }

    public int getErrorHintType() {
        return this.errorHintType;
    }

    public String getHeaderHint() {
        return this.headerHint;
    }

    public int getIconResource() {
        return this.iconResource;
    }

    public InfoTitle getInfoTitle() {
        return this.infoTitle;
    }

    public int getIsFromDealSign() {
        return this.isFromDealSign;
    }

    public int getLayoutHeight() {
        return this.layoutHeight;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMoney() {
        return this.money;
    }

    public PageFrom getPageFrom() {
        return this.pageFrom;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public QrBean getQrBean() {
        return this.qrBean;
    }

    public DataStatHelper.StatAction getStatAction() {
        return this.statAction;
    }

    public List<ConfirmExtraText> getTextLists() {
        return this.textLists;
    }

    public TransactionBean getTransactionBean() {
        return this.transactionBean;
    }

    public int getType() {
        return this.type;
    }

    public boolean hasOwnerPermission() {
        return this.hasOwnerPermission;
    }

    public boolean isActives() {
        return this.isActives;
    }

    public boolean isOverThreshold() {
        return this.isOverThreshold;
    }

    public boolean isShield() {
        return this.isShield;
    }

    public void setActives(boolean z) {
        this.isActives = z;
    }

    public void setEnergyUsed(long j) {
        this.energyUsed = j;
    }

    public void setErrorHint(String str) {
        this.errorHint = str;
    }

    public void setHasOwnerPermission(boolean z) {
        this.hasOwnerPermission = z;
    }

    public void setIsFromDealSign(int i) {
        this.isFromDealSign = i;
    }

    public void setLayoutHeight(int i) {
        this.layoutHeight = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setMoney(String str) {
        this.money = str;
    }

    public void setOverThreshold(boolean z) {
        this.isOverThreshold = z;
    }

    public void setPageFrom(PageFrom pageFrom) {
        this.pageFrom = pageFrom;
    }

    public void setPageIndex(int i) {
        this.pageIndex = i;
    }

    public void setQrBean(QrBean qrBean) {
        this.qrBean = qrBean;
    }

    public void setShield(boolean z) {
        this.isShield = z;
    }

    public void setStatAction(DataStatHelper.StatAction statAction) {
        this.statAction = statAction;
    }

    public void setType(int i) {
        this.type = i;
    }

    public BaseParam() {
        this.errorHintType = 1;
        this.hasOwnerPermission = true;
        this.layoutHeight = UIUtils.dip2px(580.0f);
        this.transactionBean = new TransactionBean();
        this.transactions = new ArrayList();
        this.textLists = new ArrayList();
    }

    public BaseParam(Parcel parcel) {
        this.errorHintType = 1;
        this.hasOwnerPermission = true;
        this.layoutHeight = UIUtils.dip2px(580.0f);
        this.type = parcel.readInt();
        this.qrBean = (QrBean) parcel.readParcelable(QrBean.class.getClassLoader());
        TransactionBean transactionBean = (TransactionBean) parcel.readParcelable(TransactionBean.class.getClassLoader());
        this.transactionBean = transactionBean;
        if (transactionBean != null) {
            this.transactions = transactionBean.getBytes();
        }
        this.pageIndex = parcel.readInt();
        this.textLists = parcel.createTypedArrayList(ConfirmExtraText.CREATOR);
        this.confirmExtraText = (ConfirmExtraText) parcel.readParcelable(ConfirmExtraText.class.getClassLoader());
        this.confirmExtraTitle = (ConfirmExtraTitle) parcel.readParcelable(ConfirmExtraTitle.class.getClassLoader());
        this.hasOwnerPermission = parcel.readByte() != 0;
        this.errorHint = parcel.readString();
        this.isActives = parcel.readByte() != 0;
        this.isShield = parcel.readByte() != 0;
        this.money = parcel.readString();
        this.message = parcel.readString();
        this.iconResource = parcel.readInt();
        this.infoTitle = (InfoTitle) parcel.readParcelable(TransactionBean.class.getClassLoader());
        this.energyUsed = parcel.readLong();
        this.layoutHeight = parcel.readInt();
        this.errorHintType = parcel.readInt();
        this.isFromDealSign = parcel.readInt();
        this.isOverThreshold = parcel.readByte() != 0;
        this.pageFrom = (PageFrom) parcel.readSerializable();
        this.statAction = (DataStatHelper.StatAction) parcel.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeParcelable(this.qrBean, i);
        parcel.writeParcelable(this.transactionBean, i);
        parcel.writeInt(this.pageIndex);
        parcel.writeTypedList(this.textLists);
        parcel.writeParcelable(this.confirmExtraText, i);
        parcel.writeParcelable(this.confirmExtraTitle, i);
        parcel.writeByte(this.hasOwnerPermission ? (byte) 1 : (byte) 0);
        parcel.writeString(this.errorHint);
        parcel.writeByte(this.isActives ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShield ? (byte) 1 : (byte) 0);
        parcel.writeString(this.money);
        parcel.writeString(this.message);
        parcel.writeInt(this.iconResource);
        parcel.writeParcelable(this.infoTitle, i);
        parcel.writeLong(this.energyUsed);
        parcel.writeInt(this.layoutHeight);
        parcel.writeInt(this.errorHintType);
        parcel.writeInt(this.isFromDealSign);
        parcel.writeByte(this.isOverThreshold ? (byte) 1 : (byte) 0);
        parcel.writeSerializable(this.pageFrom);
        parcel.writeSerializable(this.statAction);
    }

    public void setHeaderHint(int i) {
        if (i != 0) {
            this.headerHint = StringTronUtil.getResString(i);
        }
    }

    public void setErrorHint(int i, int i2) {
        this.errorHint = StringTronUtil.getResString(i);
        this.errorHintType = i2;
    }

    public void addExtraText(String str, String str2) {
        if (StringTronUtil.isEmpty(str)) {
            return;
        }
        ConfirmExtraText confirmExtraText = new ConfirmExtraText();
        this.confirmExtraText = confirmExtraText;
        confirmExtraText.setLeft(str);
        this.confirmExtraText.setRight(str2);
        this.textLists.add(this.confirmExtraText);
    }

    public void addExtraText(int i, Object obj) {
        if (i == 0) {
            return;
        }
        ConfirmExtraText confirmExtraText = new ConfirmExtraText();
        this.confirmExtraText = confirmExtraText;
        confirmExtraText.setLeft(StringTronUtil.getResString(i));
        this.confirmExtraText.setRight(String.valueOf(obj));
        this.textLists.add(this.confirmExtraText);
    }

    public void addInfoTitle(int i, String str) {
        this.infoTitle = new InfoTitle(i, str);
    }

    public void addInfoTitle(int i, String str, String str2) {
        InfoTitle infoTitle = new InfoTitle(i, str);
        this.infoTitle = infoTitle;
        infoTitle.setSubTitleDetail(str2);
    }

    public void setTitle(int i, int i2) {
        ConfirmExtraTitle confirmExtraTitle = new ConfirmExtraTitle();
        this.confirmExtraTitle = confirmExtraTitle;
        confirmExtraTitle.setDesTitle(StringTronUtil.getResString(i));
        this.confirmExtraTitle.setConfirmTitle(StringTronUtil.getResString(i2));
    }

    public void setTitle(int i, int i2, int i3) {
        ConfirmExtraTitle confirmExtraTitle = new ConfirmExtraTitle();
        this.confirmExtraTitle = confirmExtraTitle;
        if (i != 0) {
            confirmExtraTitle.setDesTitle(StringTronUtil.getResString(i));
        }
        if (i2 != 0) {
            this.confirmExtraTitle.setMutisignTitle(StringTronUtil.getResString(i2));
        }
        if (i3 != 0) {
            this.confirmExtraTitle.setConfirmTitle(StringTronUtil.getResString(i3));
        }
    }

    public void addTransaction(Protocol.Transaction transaction) {
        this.transactions.add(transaction.toByteArray());
        this.transactionBean.setBytes(this.transactions);
    }

    public void addTransaction(byte[] bArr) {
        this.transactions.add(bArr);
        this.transactionBean.setBytes(this.transactions);
    }

    public void clearThenAddTransaction(byte[] bArr) {
        this.transactions.clear();
        this.transactions.add(bArr);
        this.transactionBean.setBytes(this.transactions);
    }

    public void clear() {
        this.transactions.clear();
    }

    public void clearThenAddTransaction(Protocol.Transaction transaction) {
        this.transactions.clear();
        this.transactions.add(transaction.toByteArray());
        this.transactionBean.setBytes(this.transactions);
    }

    public void clearThenAddTransactions(List<byte[]> list) {
        this.transactions.clear();
        this.transactions.addAll(list);
        this.transactionBean.setBytes(this.transactions);
    }
}
