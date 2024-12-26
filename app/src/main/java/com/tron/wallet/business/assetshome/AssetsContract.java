package com.tron.wallet.business.assetshome;

import android.content.Context;
import android.content.Intent;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftAssetOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordBean;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
import javax.annotation.Nonnull;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public interface AssetsContract {

    public interface Model extends BaseModel {
        Observable<AssetsHomeOutput> getAssetsHomeData(String str);

        boolean getHideLittleAssets(String str);

        boolean getHideScamTokenAssets(String str);

        Observable<AssetsHomeData> getLocalData(Wallet wallet, Protocol.Account account);

        Observable<NftAssetOutput> getNftTokens(Context context, String str);

        Observable<NftAssetOutput> requestNftTokens(String str);

        void saveNftTokens(Context context, String str, List<NftTokenBean> list);

        Observable<Boolean> setHideLittleAssets(String str, boolean z);

        Observable<Boolean> setHideScamTokenAssets(String str, boolean z);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void backup();

        abstract void checkAndShowMultiSignView();

        abstract boolean getAssetStatus();

        abstract void getCollectionData();

        abstract void getDBData();

        abstract boolean getHideLittleAssetsFlag();

        abstract void getReleaseData(boolean z);

        abstract boolean getSocketState();

        abstract void initData();

        abstract void netWorkChange();

        abstract void onActivityResult(int i, int i2, Intent intent);

        abstract void recordDealAct();

        abstract void refreshData();

        abstract void removeListener();

        abstract void setSortFiltersFlag(boolean z, boolean z2);

        abstract void startSocket(boolean z);

        abstract void stopSocket();

        protected abstract void switchAssetStatus(boolean z);

        abstract void updateSelectedWallet();
    }

    public interface View extends BaseView {
        int getCurrentType();

        @Nonnull
        List<TokenBean> getData(int i);

        boolean getStart();

        void hideMultiPermissionTipView();

        void onChainChanged();

        void onWalletChanged(Wallet wallet);

        void pullToRefreshComplete();

        void scan();

        void setNetNotice(boolean z);

        void setShowSwitchNode(boolean z);

        void setStart(boolean z);

        void showBlockSync(Wallet wallet);

        void showMultiPermissionTipView(boolean z, String str, String str2);

        void showOrHidenSafeTipView(boolean z);

        void showSwitchServerNotice(boolean z);

        void switchFragment(int i);

        void updateBackuptipsView(BackupRecordBean backupRecordBean);

        void updateDealSignView(int i);

        void updateFail(int i);

        void updateHeaderData(Wallet wallet, AssetsHomeData assetsHomeData, Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage, boolean z);

        void updateHeaderLoading(boolean z);

        void updateHideFiltersFlag(boolean z, boolean z2);

        void updateHidePrivacyState(boolean z);

        void updateNewAssetCount(int i);

        void updateNewData(int i, List<TokenBean> list, boolean z);

        void updateStart(int i);

        void updateSyncBlock(long j, long j2);

        void updateWalletName(Wallet wallet);
    }
}
