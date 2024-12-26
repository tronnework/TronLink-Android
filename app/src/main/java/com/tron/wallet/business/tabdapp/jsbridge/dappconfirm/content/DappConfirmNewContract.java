package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content;

import android.content.Intent;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public interface DappConfirmNewContract {

    public interface Model extends BaseModel {
        Observable<Object> addDappRecord(RequestBody requestBody);

        Observable<NftItemInfoOutput> getCollectionInfo(String str, String str2, String str3);

        Observable<DappProjectIOutput> getProjectInfo(String str, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getApproveDate(String str, DappDetailParam dappDetailParam);
    }

    public interface View extends BaseView {
        Intent getIItent();

        void updateApproveConfirmFragment(DappProjectInfoBean dappProjectInfoBean, boolean z, TokenBean tokenBean);
    }
}
