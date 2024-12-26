package com.tron.wallet.business.assetshome;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.repository.KVController;
import com.tron.wallet.business.addassets2.sort.TokenSortByName;
import com.tron.wallet.business.addassets2.sort.TokenSortByValue;
import com.tron.wallet.business.assetshome.SortHelper;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.tron.walletserver.Wallet;
public class SortHelper {
    private TokenSortType currentType;
    private boolean hideLittleAssets;
    private boolean hideScamToken;
    private TokenSortType previousType;
    private int sortIndex;

    public interface OnSortChangedListener {
        void onSortChanged(PopupWindow popupWindow, TokenSortType tokenSortType, int i, boolean z, boolean z2, boolean z3);
    }

    public TokenSortType getCurrentType() {
        return this.currentType;
    }

    public boolean getHideLittleAssets() {
        return this.hideLittleAssets;
    }

    public boolean isHideScamToken() {
        return this.hideScamToken;
    }

    public void setHideFiltersFlag(boolean z, boolean z2) {
        this.hideLittleAssets = z;
        this.hideScamToken = z2;
    }

    private SortHelper() {
        this.sortIndex = 0;
    }

    public static SortHelper get() {
        return Nested.instance;
    }

    public void showSortDialog(Context context, View view, int i, @Nonnull OnSortChangedListener onSortChangedListener, @Nullable PopupWindow.OnDismissListener onDismissListener) {
        PopupWindow showAssetSortPop = PopupWindowUtil.showAssetSortPop(context, this.sortIndex, this.hideLittleAssets, this.hideScamToken, i, new fun1(onSortChangedListener, view));
        showAssetSortPop.showAtLocation(view, 80, 0, 0);
        if (onDismissListener != null) {
            showAssetSortPop.setOnDismissListener(onDismissListener);
        }
    }

    class fun1 implements OnSortChangedListener {
        final OnSortChangedListener val$listener;
        final View val$root;

        fun1(OnSortChangedListener onSortChangedListener, View view) {
            this.val$listener = onSortChangedListener;
            this.val$root = view;
        }

        @Override
        public void onSortChanged(final PopupWindow popupWindow, TokenSortType tokenSortType, int i, boolean z, boolean z2, boolean z3) {
            boolean z4 = tokenSortType == TokenSortType.SORT_BY_USER_MANUAL;
            if (tokenSortType == TokenSortType.SORT_BY_USER || tokenSortType == TokenSortType.SORT_BY_USER_MANUAL || (tokenSortType != TokenSortType.SORT_BY_VALUE && tokenSortType != TokenSortType.SORT_BY_NAME)) {
            }
            int unused = sortIndex;
            updateSortType(tokenSortType, i, !z4);
            this.val$listener.onSortChanged(popupWindow, tokenSortType, i, z, z2, z3);
            this.val$root.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    SortHelper.1.lambda$onSortChanged$0(popupWindow);
                }
            }, z4 ? 500L : 0L);
        }

        public static void lambda$onSortChanged$0(PopupWindow popupWindow) {
            try {
                popupWindow.dismiss();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public void showMyAssetsSortDialog(Context context, View view, int i, @Nonnull OnSortChangedListener onSortChangedListener, @Nullable PopupWindow.OnDismissListener onDismissListener) {
        PopupWindow showMyAssetSortPop = PopupWindowUtil.showMyAssetSortPop(context, this.hideLittleAssets, this.hideScamToken, i, new fun2(onSortChangedListener, view));
        showMyAssetSortPop.showAtLocation(view, 80, 0, 0);
        if (onDismissListener != null) {
            showMyAssetSortPop.setOnDismissListener(onDismissListener);
        }
    }

    class fun2 implements OnSortChangedListener {
        final OnSortChangedListener val$listener;
        final View val$root;

        fun2(OnSortChangedListener onSortChangedListener, View view) {
            this.val$listener = onSortChangedListener;
            this.val$root = view;
        }

        @Override
        public void onSortChanged(final PopupWindow popupWindow, TokenSortType tokenSortType, int i, boolean z, boolean z2, boolean z3) {
            this.val$listener.onSortChanged(popupWindow, tokenSortType, i, z, z2, z3);
            this.val$root.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    SortHelper.2.lambda$onSortChanged$0(popupWindow);
                }
            }, 0L);
        }

        public static void lambda$onSortChanged$0(PopupWindow popupWindow) {
            try {
                popupWindow.dismiss();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public void updateSortType(TokenSortType tokenSortType, int i, boolean z) {
        this.sortIndex = tokenSortType.getValue();
        if (z) {
            if (tokenSortType != TokenSortType.SORT_BY_USER_MANUAL) {
                this.currentType = tokenSortType;
                saveSortType(tokenSortType, i);
                return;
            }
            this.previousType = getInitSortType(i);
            saveSortType(TokenSortType.SORT_BY_USER, i);
        }
    }

    public void onCustomSortCancel(int i) {
        TokenSortType tokenSortType = this.previousType;
        if (tokenSortType != null) {
            saveSortType(tokenSortType, i);
        }
    }

    public Observable<List<TokenBean>> sortByName(List<TokenBean> list) {
        if (list != null) {
            new TokenSortByName().sort(list);
            return Observable.just(list);
        }
        return Observable.just(new ArrayList());
    }

    public Observable<List<TokenBean>> sortByValue(List<TokenBean> list) {
        if (list != null) {
            new TokenSortByValue(PriceUpdater.getTRX_price()).sort(list);
            return Observable.just(list);
        }
        return Observable.just(new ArrayList());
    }

    public Observable<List<TokenBean>> sortByUser(int i) {
        if (i == 1) {
            return Observable.just(AssetsManager.getInstance().getSortedFollowCollections()).flatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return SortHelper.lambda$sortByUser$0((List) obj);
                }
            });
        }
        if (i == 0) {
            AssetsHomeData sortedFollowAssets = AssetsManager.getInstance().getSortedFollowAssets();
            if (sortedFollowAssets != null && sortedFollowAssets.token != null) {
                return Observable.just(sortedFollowAssets.token);
            }
            return Observable.just(new ArrayList());
        }
        return Observable.just(new ArrayList());
    }

    public static Observable lambda$sortByUser$0(List list) throws Exception {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((NftTokenBean) it.next()).convertToTokenBean());
        }
        return Observable.just(arrayList);
    }

    public TokenSortType getInitSortType(int i) {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet == null) {
            return null;
        }
        TokenSortType tokenSortType = KVController.getInstance().getTokenSortType(selectedPublicWallet.getAddress());
        this.currentType = tokenSortType;
        updateSortType(tokenSortType, i, false);
        return tokenSortType;
    }

    public void saveSortType(TokenSortType tokenSortType, int i) {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet == null) {
            return;
        }
        KVController.getInstance().setTokenSortType(selectedPublicWallet.getAddress(), tokenSortType);
    }

    private static class Nested {
        static SortHelper instance = new SortHelper();

        private Nested() {
        }
    }
}
