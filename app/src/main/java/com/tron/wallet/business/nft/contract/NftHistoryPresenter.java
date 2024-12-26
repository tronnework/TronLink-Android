package com.tron.wallet.business.nft.contract;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.nft.NftHistoryActivity;
import com.tron.wallet.business.nft.NftHistoryFragment;
import com.tron.wallet.business.nft.contract.NftHistoryContract;
import com.tron.wallet.business.vote.adapter.VoteContentVpAdapter;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.CustomTabEntity;
import com.tron.wallet.common.bean.TabEntity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.components.tablayout.OnTabSelectListener;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.tron.walletserver.Wallet;
public class NftHistoryPresenter extends NftHistoryContract.Presenter {
    private List<ChainBean> allChainJson;
    private String contractAddress;
    private boolean isMainChain;
    private boolean isMapping;
    private double mPriceUsdOrCny;
    private String mTitleStr;
    private TokenBean mTokenBean;
    private ChainBean mainChain;
    private FragmentManager manager;
    private Wallet selectedWallet;
    private ChainBean sideChain;
    private String tokenType;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String typeStr = "tokenAddress";
    private List<Fragment> fragments = new ArrayList();
    private List<String> titleLists = new ArrayList();
    private boolean collapse = false;

    public static void lambda$initRxEvent$0(Object obj) throws Exception {
    }

    public TokenBean getToken() {
        return this.mTokenBean;
    }

    @Override
    public void setCanRefresh(boolean z) {
        this.collapse = z;
    }

    @Override
    protected void onStart() {
        this.selectedWallet = WalletUtils.getSelectedWallet();
        this.allChainJson = SpAPI.THIS.getAllChainJson();
        this.contractAddress = ((NftHistoryContract.View) this.mView).getIIntent().getStringExtra(TronConfig.CONTRACT_ADDRESS);
        this.mTokenBean = (TokenBean) ((NftHistoryContract.View) this.mView).getIIntent().getParcelableExtra(TronConfig.TOKEN_DATA);
        List<ChainBean> list = this.allChainJson;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.allChainJson.size(); i++) {
            if (this.allChainJson.get(i).isMainChain) {
                this.mainChain = this.allChainJson.get(i);
            } else {
                this.sideChain = this.allChainJson.get(i);
            }
        }
    }

    private void initFragmentData(Context context) {
        this.fragments.clear();
        this.fragments.add(NftHistoryFragment.getInstance(0, this.tokenType, this.mTokenBean, this.mPriceUsdOrCny, this.contractAddress));
        this.fragments.add(NftHistoryFragment.getInstance(1, this.tokenType, this.mTokenBean, this.mPriceUsdOrCny, this.contractAddress));
        this.fragments.add(NftHistoryFragment.getInstance(2, this.tokenType, this.mTokenBean, this.mPriceUsdOrCny, this.contractAddress));
        this.titleLists.clear();
        this.titleLists.add(context.getResources().getString(R.string.transfer_all));
        this.titleLists.add(context.getResources().getString(R.string.transfer_sent1));
        this.titleLists.add(context.getResources().getString(R.string.transfer_receive));
        this.mTabEntities.clear();
        this.mTabEntities.add(new TabEntity(context.getResources().getString(R.string.transfer_all), 0, 0));
        this.mTabEntities.add(new TabEntity(context.getResources().getString(R.string.nft_send), 0, 0));
        this.mTabEntities.add(new TabEntity(context.getResources().getString(R.string.nft_received), 0, 0));
    }

    private void initTabLayout() {
        ((NftHistoryContract.View) this.mView).getXTablayout().setTabData(this.mTabEntities);
        ((NftHistoryContract.View) this.mView).getXTablayout().setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabReselect(int i) {
            }

            @Override
            public void onTabSelect(int i) {
                ((NftHistoryContract.View) mView).getViewPager().setCurrentItem(i);
                if (i == 0) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_HISTORY_PAGE_TAB_ALL);
                } else if (i == 1) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_HISTORY_PAGE_TAB_SEND);
                } else if (i != 2) {
                } else {
                    AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_HISTORY_PAGE_TAB_RECEIVE);
                }
            }
        });
        ((NftHistoryContract.View) this.mView).getViewPager().registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                ((NftHistoryContract.View) mView).getXTablayout().setCurrentTab(i);
            }
        });
    }

    @Override
    public void addSome() {
        netWorkChange();
        initRxEvent();
        initFragmentData(((NftHistoryContract.View) this.mView).getIContext());
        ((NftHistoryContract.View) this.mView).getViewPager().setOffscreenPageLimit(3);
        ((NftHistoryContract.View) this.mView).getViewPager().setUserInputEnabled(true);
        ((NftHistoryContract.View) this.mView).getViewPager().setAdapter(new VoteContentVpAdapter((FragmentActivity) ((NftHistoryContract.View) this.mView).getIContext(), this.fragments, this.titleLists));
        initTabLayout();
        ((NftHistoryContract.View) this.mView).getFrameLayout().setViewPager(((NftHistoryContract.View) this.mView).getViewPager());
        ((NftHistoryContract.View) this.mView).getFrameLayout().setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                if (collapse) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view2);
                }
                return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                onRefresh();
            }
        });
        this.manager = ((NftHistoryActivity) this.mView).getSupportFragmentManager();
    }

    private void hideRefresh() {
        ((NftHistoryContract.View) this.mView).getFrameLayout().refreshComplete();
    }

    public void onRefresh() {
        if (!TronConfig.hasNet) {
            IToast.getIToast().show(((NftHistoryContract.View) this.mView).getIContext().getString(R.string.time_out));
            hideRefresh();
            return;
        }
        ((NftHistoryFragment) getContentView()).refresh();
    }

    public Fragment getContentView() {
        return this.fragments.get(((NftHistoryContract.View) this.mView).getViewPager().getCurrentItem());
    }

    private void initRxEvent() {
        this.mRxManager.on(Event.TOKEN_DETAIL, new Consumer() {
            @Override
            public final void accept(Object obj) {
                NftHistoryPresenter.lambda$initRxEvent$0(obj);
            }
        });
        this.mRxManager.on(Event.TOKEN_DETAIL_REFRESH_FINISHED, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$1(obj);
            }
        });
        this.mRxManager.on(Event.BackToHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$2(obj);
            }
        });
    }

    public void lambda$initRxEvent$1(Object obj) throws Exception {
        hideRefresh();
    }

    public void lambda$initRxEvent$2(Object obj) throws Exception {
        ((NftHistoryContract.View) this.mView).exit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mRxManager != null) {
            this.mRxManager.clear();
        }
    }

    @Override
    void netWorkChange() {
        this.isMainChain = SpAPI.THIS.getCurrentChain() == null ? true : SpAPI.THIS.getCurrentChain().isMainChain;
    }
}
