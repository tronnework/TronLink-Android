package com.tron.wallet.business.tronpower.stake;

import android.util.SparseArray;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.FragmentActivity;
import com.journeyapps.barcodescanner.ScanOptions;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.utils.DataStatHelper;
import io.reactivex.SingleSource;
import java.math.BigDecimal;
import java.util.List;
public interface SelectStakeReceiveAccountContract {

    public enum InputError {
        OK,
        INFO,
        WARNING,
        UNCHECKED,
        ERROR
    }

    public interface Model extends BaseModel {
        List<NameAddressExtraBean> doSearch(String str, String str2);

        NameAddressExtraBean findFirst(String str, String str2);

        SparseArray<List<NameAddressExtraBean>> getAllAddresses(String str);

        NameAddressExtraBean insertNewAddedAddress(String str, String str2, String str3);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void checkInputAddress(String str, NameAddressExtraBean nameAddressExtraBean);

        public abstract boolean considerNoInput(String str);

        public abstract List<NameAddressExtraBean> doSearch(String str);

        public abstract void getData();

        public abstract void init();

        public abstract NameAddressExtraBean insertNewAddedAddress(String str, String str2, String str3);

        abstract void loadAddress(String str);

        abstract String parseClipContent();

        public abstract void scanQr(FragmentActivity fragmentActivity);

        public abstract void setTextWithoutWatcher(EditText editText, CharSequence charSequence);

        public abstract SingleSource<Boolean> showAddToAddressBook(NameAddressExtraBean nameAddressExtraBean);

        public abstract void stake();

        public abstract void subscribeSearchEvent(EditText editText);
    }

    public interface View extends BaseView {
        void checkInputAddress(String str);

        String getAountEnergy();

        double getCanUseTrxCount();

        Button getNextButton();

        ActivityResultLauncher<ScanOptions> getScannerResultLauncher();

        String getSelectedAddress();

        String getStakeAddress();

        BigDecimal getStakeAmount();

        DataStatHelper.StatAction getStatAction();

        boolean isCheckConfirmAmount();

        boolean isFreezeBandwidth();

        boolean isMultiSign();

        void setErrorCountStatus(boolean z);

        void setErrorCountStatus(boolean z, int i);

        void setErrorCountStatus(boolean z, String str);

        void updateAddressList(SparseArray<List<NameAddressExtraBean>> sparseArray);

        void updateInputCheckResult(InputAddressBean inputAddressBean);

        void updateSearchResult(List<NameAddressExtraBean> list, String str);
    }

    public static class InputAddressBean {
        NameAddressExtraBean addressBean;
        InputError error = InputError.OK;
        String message = "";

        public NameAddressExtraBean getAddressBean() {
            return this.addressBean;
        }

        public InputError getError() {
            return this.error;
        }

        public String getMessage() {
            return this.message;
        }

        public void setAddressBean(NameAddressExtraBean nameAddressExtraBean) {
            this.addressBean = nameAddressExtraBean;
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
                if (inputAddressBean.canEqual(this)) {
                    NameAddressExtraBean addressBean = getAddressBean();
                    NameAddressExtraBean addressBean2 = inputAddressBean.getAddressBean();
                    if (addressBean != null ? addressBean.equals((Object) addressBean2) : addressBean2 == null) {
                        InputError error = getError();
                        InputError error2 = inputAddressBean.getError();
                        if (error != null ? error.equals(error2) : error2 == null) {
                            String message = getMessage();
                            String message2 = inputAddressBean.getMessage();
                            return message != null ? message.equals(message2) : message2 == null;
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
            NameAddressExtraBean addressBean = getAddressBean();
            int hashCode = addressBean == null ? 43 : addressBean.hashCode();
            InputError error = getError();
            int hashCode2 = ((hashCode + 59) * 59) + (error == null ? 43 : error.hashCode());
            String message = getMessage();
            return (hashCode2 * 59) + (message != null ? message.hashCode() : 43);
        }

        public void setError(InputError inputError) {
            if (inputError == null) {
                throw new NullPointerException("error is marked non-null but is null");
            }
            this.error = inputError;
        }

        public void setMessage(String str) {
            if (str == null) {
                throw new NullPointerException("message is marked non-null but is null");
            }
            this.message = str;
        }

        public String toString() {
            return "SelectStakeReceiveAccountContract.InputAddressBean(addressBean=" + getAddressBean() + ", error=" + getError() + ", message=" + getMessage() + ")";
        }
    }
}
