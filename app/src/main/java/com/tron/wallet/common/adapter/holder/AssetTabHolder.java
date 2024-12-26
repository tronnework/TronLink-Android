package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.addassets2.MyAllAssetsActivity;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.TokenType;
import com.tron.wallet.business.assetshome.SortHelper;
import com.tron.wallet.common.components.BadgeButton;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.tron.walletserver.Wallet;
public class AssetTabHolder extends BaseHolder implements XTabLayout.OnTabSelectedListener {
    View btnContainer;
    private int currentTokenType;
    @Nullable
    TextView divider;
    private Consumer<Pair<Boolean, Boolean>> hideFiltersListener;
    ImageView ivSort;
    View mAddAssetsView;
    protected Context mContext;
    View newAssetsCountLayout;
    BadgeButton newAssetsCountTextView;
    private SortHelper.OnSortChangedListener outerListener;
    @Nullable
    TextView tvAsset;
    @Nullable
    TextView tvCollection;
    TextView tvSort;
    View viewSort;

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
    }

    public AssetTabHolder(Context context) {
        super(View.inflate(context, R.layout.asset_tab, null));
        this.currentTokenType = 0;
        this.mContext = context;
    }

    public AssetTabHolder(View view, SortHelper.OnSortChangedListener onSortChangedListener, Consumer<Pair<Boolean, Boolean>> consumer) {
        super(view);
        this.currentTokenType = 0;
        this.mContext = view.getContext();
        this.outerListener = onSortChangedListener;
        this.hideFiltersListener = consumer;
        this.mAddAssetsView = view.findViewById(R.id.iv_add_assets);
        this.tvAsset = (TextView) view.findViewById(R.id.tv_asset);
        this.tvCollection = (TextView) view.findViewById(R.id.tv_collection);
        this.divider = (TextView) view.findViewById(R.id.divider);
        this.newAssetsCountLayout = view.findViewById(R.id.ll_new_assets_count);
        this.newAssetsCountTextView = (BadgeButton) view.findViewById(R.id.text_new_assets_count);
        this.btnContainer = view.findViewById(R.id.rl_btn_container);
        this.viewSort = view.findViewById(R.id.rl_sort);
        this.tvSort = (TextView) view.findViewById(R.id.tv_sort_by);
        this.ivSort = (ImageView) view.findViewById(R.id.iv_sort);
    }

    public void onBind(int i) {
        boolean z = false;
        this.viewSort.setVisibility(View.VISIBLE);
        initListener();
        TokenSortType initSortType = SortHelper.get().getInitSortType(this.currentTokenType);
        if (initSortType != null) {
            updateSortText(initSortType);
        }
        if (i > 0) {
            this.newAssetsCountLayout.setVisibility(View.VISIBLE);
        } else {
            this.newAssetsCountLayout.setVisibility(View.GONE);
        }
        setSortHightLightStatus((SortHelper.get().getHideLittleAssets() || SortHelper.get().isHideScamToken()) ? true : true);
        checkWatchWallet();
    }

    public void checkWatchWallet() {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        this.itemView.setVisibility(selectedPublicWallet != null && selectedPublicWallet.isWatchNotPaired() ? View.GONE : View.VISIBLE);
    }

    private void initListener() {
        this.mAddAssetsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initListener$0(view);
            }
        });
        this.viewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initListener$1(view);
            }
        });
    }

    public void lambda$initListener$0(View view) {
        clickAddAssetsLogic();
    }

    public void lambda$initListener$1(View view) {
        this.viewSort.setSelected(true);
        clickSortAssetsLogic(this.outerListener);
    }

    private void setSortHightLightStatus(boolean z) {
        Resources resources;
        int i;
        this.ivSort.setImageResource(z ? R.mipmap.ic_assets_sort_selected : R.mipmap.ic_sort_asset_new);
        TextView textView = this.tvSort;
        if (z) {
            resources = this.mContext.getResources();
            i = R.color.blue_3c;
        } else {
            resources = this.mContext.getResources();
            i = R.color.black_02;
        }
        textView.setTextColor(resources.getColor(i));
    }

    private void clickAddAssetsLogic() {
        MyAllAssetsActivity.startActivity(this.mContext);
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_ADD_ASSET);
    }

    private void clickSortAssetsLogic(final SortHelper.OnSortChangedListener onSortChangedListener) {
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_SORT);
        SortHelper.get().showSortDialog(this.itemView.getContext(), this.itemView, TokenType.toAssetDataType(this.currentTokenType), new SortHelper.OnSortChangedListener() {
            @Override
            public final void onSortChanged(PopupWindow popupWindow, TokenSortType tokenSortType, int i, boolean z, boolean z2, boolean z3) {
                lambda$clickSortAssetsLogic$2(onSortChangedListener, popupWindow, tokenSortType, i, z, z2, z3);
            }
        }, new PopupWindow.OnDismissListener() {
            @Override
            public final void onDismiss() {
                lambda$clickSortAssetsLogic$3();
            }
        });
    }

    public void lambda$clickSortAssetsLogic$2(SortHelper.OnSortChangedListener onSortChangedListener, PopupWindow popupWindow, TokenSortType tokenSortType, int i, boolean z, boolean z2, boolean z3) {
        if (onSortChangedListener != null) {
            onSortChangedListener.onSortChanged(popupWindow, tokenSortType, i, z, z2, z3);
        }
        onSortChanged(tokenSortType, i);
        int i2 = fun1.$SwitchMap$com$tron$wallet$business$addassets2$bean$TokenSortType[tokenSortType.ordinal()];
        if (i2 == 1) {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_SORT_VALUE);
        } else if (i2 == 2) {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_SORT_PREFERENCE);
        } else if (i2 == 3) {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_SORT_NAME);
        } else if (i2 == 4) {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_SORT_MANUAL);
        }
        this.hideFiltersListener.accept(new Pair<>(Boolean.valueOf(z), Boolean.valueOf(z2)));
    }

    public void lambda$clickSortAssetsLogic$3() {
        this.viewSort.setSelected(false);
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.EXIT_ASSET_PAGE_SORT);
    }

    private void onSortChanged(TokenSortType tokenSortType, int i) {
        if (tokenSortType != TokenSortType.SORT_BY_USER_MANUAL) {
            updateSortText(tokenSortType);
        }
    }

    public void updateHideLittleAssetsStatus(boolean z) {
        setSortHightLightStatus(z);
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$addassets2$bean$TokenSortType;

        static {
            int[] iArr = new int[TokenSortType.values().length];
            $SwitchMap$com$tron$wallet$business$addassets2$bean$TokenSortType = iArr;
            try {
                iArr[TokenSortType.SORT_BY_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$addassets2$bean$TokenSortType[TokenSortType.SORT_BY_USER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$addassets2$bean$TokenSortType[TokenSortType.SORT_BY_NAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$addassets2$bean$TokenSortType[TokenSortType.SORT_BY_USER_MANUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void updateSortText(TokenSortType tokenSortType) {
        int i = fun1.$SwitchMap$com$tron$wallet$business$addassets2$bean$TokenSortType[tokenSortType.ordinal()];
        this.tvSort.setText(this.itemView.getContext().getString(i != 1 ? i != 2 ? R.string.sort_by_name : R.string.sort_by_preference : R.string.sort_by_value));
    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        this.currentTokenType = tab.getPosition() > 0 ? 5 : 0;
        updateSortText(SortHelper.get().getInitSortType(this.currentTokenType));
        AnalyticsHelper.AssetPage.logEvent(this.currentTokenType == 0 ? AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_TAB_ASSET : AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_TAB_COLLECTION);
    }
}
