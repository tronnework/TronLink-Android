package com.tron.wallet.business.confirm.sign;

import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.adapter.SignTransactionBottomAdapter;
import com.tron.wallet.common.adapter.SignTransactionTopAdapter;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import java.util.List;
public interface SignTransactionNewContract {

    public interface Model extends BaseModel {
        boolean isBiggerThanQrLimitSize();

        List<String> subSign(QrBean qrBean);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addListener(RecyclerView recyclerView);

        public abstract void buttonNext();

        public abstract boolean checkStakeV2();

        public abstract void init();

        public abstract boolean isSignMessageV2();

        public abstract void onActivityResult(int i, int i2, Intent intent);
    }

    public interface View extends BaseView {
        void cancle();

        Intent getIIntent();

        FragmentManager getSupportFragmentManager();

        ViewPager2 getVP();

        void requestPermissionToScan();

        void scanedFinish();

        void setBottomAdapter(SignTransactionBottomAdapter signTransactionBottomAdapter);

        void setResult(TokenBean tokenBean, String str);

        void setTopAdapter(SignTransactionTopAdapter signTransactionTopAdapter);

        void updateButtonText(int i);

        void updateUI(int i, String str, boolean z);
    }
}
