package com.tron.wallet.business.transfer.selectaddress;

import android.content.Context;
import android.util.Pair;
import android.util.SparseArray;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.FragmentActivity;
import com.journeyapps.barcodescanner.ScanOptions;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.bean.DappSearchBean;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.RiskAccountOutput;
import io.reactivex.Observable;
import io.reactivex.SingleSource;
import java.util.List;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public interface SelectSendAddressContract {

    public enum InputError {
        OK,
        INFO,
        WARNING,
        ERROR_NON_BLOCK,
        SCAM,
        ERROR,
        SIMILAR_ADDRESS
    }

    public interface Model extends BaseModel {
        Observable<RiskAccountOutput> checkAccountRisk(String str);

        Observable<DappSearchBean> checkAddressIsScam(SparseArray<List<NameAddressExtraBean>> sparseArray);

        Observable<SparseArray<List<NameAddressExtraBean>>> checkAddressIsScams(SparseArray<List<NameAddressExtraBean>> sparseArray);

        boolean considerNoInput(String str);

        List<NameAddressExtraBean> doSearch(String str, String str2);

        NameAddressExtraBean findFirst(String str, String str2);

        Pair<CharSequence, CharSequence> findSimilarAddress(Context context, String str);

        SparseArray<List<NameAddressExtraBean>> getAllAddresses(String str);

        void initType(PageType pageType);

        NameAddressExtraBean insertNewAddedAddress(String str, String str2, String str3);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void checkInputAddress(String str, int i, NameAddressExtraBean nameAddressExtraBean);

        abstract Pair<Boolean, Integer> checkTransferSupportability(Wallet wallet);

        abstract List<NameAddressExtraBean> doSearch(String str);

        abstract Protocol.Account getReceivedAccount();

        abstract boolean hasOwnerPermission(String str, Protocol.Account account);

        abstract NameAddressExtraBean insertNewAddedAddress(String str, String str2, String str3);

        abstract void loadAddress(String str);

        abstract String parseClipContent();

        abstract Observable<Protocol.Account> queryAccount(String str, String str2);

        abstract void scanQr(FragmentActivity fragmentActivity);

        abstract void setTextWithoutWatcher(EditText editText, CharSequence charSequence);

        abstract SingleSource<Boolean> showAddToAddressBook(NameAddressExtraBean nameAddressExtraBean);

        abstract void subscribeSearchEvent(EditText editText);
    }

    public enum RiskType {
        CONTRACT,
        RISK_ACCOUNT,
        MULTI_SIGN
    }

    public interface View extends BaseView {
        void checkInputAddress(String str);

        PageType getPageType();

        ActivityResultLauncher<ScanOptions> getScannerResultLauncher();

        void setSelectBeanToUI(NameAddressExtraBean nameAddressExtraBean);

        void updateAddressList(SparseArray<List<NameAddressExtraBean>> sparseArray, boolean z);

        void updateInputCheckResult(InputAddressBean inputAddressBean);

        void updateSearchResult(List<NameAddressExtraBean> list, String str);

        void updateWarningSimilarAddress(Pair<CharSequence, CharSequence> pair);
    }

    public static class InputAddressBean {
        NameAddressExtraBean addressBean;
        RiskType riskType;
        InputError error = InputError.OK;
        CharSequence message = "";
        CharSequence subMessage = "";
        boolean isDid = false;

        public NameAddressExtraBean getAddressBean() {
            return this.addressBean;
        }

        public InputError getError() {
            return this.error;
        }

        public CharSequence getMessage() {
            return this.message;
        }

        public RiskType getRiskType() {
            return this.riskType;
        }

        public CharSequence getSubMessage() {
            return this.subMessage;
        }

        public boolean isDid() {
            return this.isDid;
        }

        public void setAddressBean(NameAddressExtraBean nameAddressExtraBean) {
            this.addressBean = nameAddressExtraBean;
        }

        public void setDid(boolean z) {
            this.isDid = z;
        }

        public void setRiskType(RiskType riskType) {
            this.riskType = riskType;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof InputAddressBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof InputAddressBean) {
                InputAddressBean inputAddressBean = (InputAddressBean) obj;
                if (inputAddressBean.canEqual(this) && isDid() == inputAddressBean.isDid()) {
                    NameAddressExtraBean addressBean = getAddressBean();
                    NameAddressExtraBean addressBean2 = inputAddressBean.getAddressBean();
                    if (addressBean != null ? addressBean.equals((Object) addressBean2) : addressBean2 == null) {
                        InputError error = getError();
                        InputError error2 = inputAddressBean.getError();
                        if (error != null ? error.equals(error2) : error2 == null) {
                            CharSequence message = getMessage();
                            CharSequence message2 = inputAddressBean.getMessage();
                            if (message != null ? message.equals(message2) : message2 == null) {
                                CharSequence subMessage = getSubMessage();
                                CharSequence subMessage2 = inputAddressBean.getSubMessage();
                                if (subMessage != null ? subMessage.equals(subMessage2) : subMessage2 == null) {
                                    RiskType riskType = getRiskType();
                                    RiskType riskType2 = inputAddressBean.getRiskType();
                                    return riskType != null ? riskType.equals(riskType2) : riskType2 == null;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            int i = isDid() ? 79 : 97;
            NameAddressExtraBean addressBean = getAddressBean();
            int hashCode = ((i + 59) * 59) + (addressBean == null ? 43 : addressBean.hashCode());
            InputError error = getError();
            int hashCode2 = (hashCode * 59) + (error == null ? 43 : error.hashCode());
            CharSequence message = getMessage();
            int hashCode3 = (hashCode2 * 59) + (message == null ? 43 : message.hashCode());
            CharSequence subMessage = getSubMessage();
            int hashCode4 = (hashCode3 * 59) + (subMessage == null ? 43 : subMessage.hashCode());
            RiskType riskType = getRiskType();
            return (hashCode4 * 59) + (riskType != null ? riskType.hashCode() : 43);
        }

        public void setError(InputError inputError) {
            if (inputError == null) {
                throw new NullPointerException("error is marked non-null but is null");
            }
            this.error = inputError;
        }

        public void setMessage(CharSequence charSequence) {
            if (charSequence == null) {
                throw new NullPointerException("message is marked non-null but is null");
            }
            this.message = charSequence;
        }

        public void setSubMessage(CharSequence charSequence) {
            if (charSequence == null) {
                throw new NullPointerException("subMessage is marked non-null but is null");
            }
            this.subMessage = charSequence;
        }

        public String toString() {
            return "SelectSendAddressContract.InputAddressBean(addressBean=" + getAddressBean() + ", error=" + getError() + ", message=" + ((Object) getMessage()) + ", subMessage=" + ((Object) getSubMessage()) + ", isDid=" + isDid() + ", riskType=" + getRiskType() + ")";
        }
    }
}
