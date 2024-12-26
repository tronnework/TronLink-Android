package com.tron.wallet.business.walletmanager.selectwallet.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.lxj.xpopup.core.BasePopupView;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.LoadingView;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.MultiSignPopupTextView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemSelectWalletBinding;
import com.tron.wallet.databinding.ItemSelectWalletGroupBinding;
import com.tron.wallet.databinding.ItemSelectWalletNormalBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.ledger.LedgerWallet;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class SelectWalletAdapter extends BaseQuickAdapter<SelectWalletBean, BaseViewHolder> {
    private boolean hideAssets;
    private boolean isFromDetail;
    private View.OnClickListener onHeaderClickListener;
    private OnItemViewClickListener onItemViewClickListener;

    public interface OnItemViewClickListener {
        void onViewClick(BaseQuickAdapter baseQuickAdapter, View view, int i, SelectWalletBean selectWalletBean);
    }

    public OnItemViewClickListener getOnItemViewClickListener() {
        return this.onItemViewClickListener;
    }

    public void setFromDetail(boolean z) {
        this.isFromDetail = z;
    }

    public void setHideAssets(boolean z) {
        this.hideAssets = z;
    }

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public SelectWalletAdapter(Context context) {
        super(new ArrayList());
    }

    public void addHeaderView(View view, View.OnClickListener onClickListener) {
        addHeaderView(view);
        this.onHeaderClickListener = onClickListener;
    }

    public void updateData(List<SelectWalletBean> list) {
        setNewData(list);
    }

    public SelectWalletBean getData(int i) {
        if (getData() != null) {
            return getData().get(i);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        super.onBindViewHolder((SelectWalletAdapter) baseViewHolder, i);
        if (baseViewHolder.getItemViewType() == 273) {
            baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$onBindViewHolder$0(view);
                }
            });
        }
    }

    public void lambda$onBindViewHolder$0(View view) {
        this.onHeaderClickListener.onClick(view);
    }

    @Override
    protected int getDefItemViewType(int i) {
        SelectWalletBean data = getData(i);
        if (data.getGroupType() == WalletGroupType.RECENTLY) {
            return ItemType.ITEM_TYPE_RECENTLY;
        }
        if (data.isHdGroup()) {
            return ItemType.ITEM_TYPE_GROUP;
        }
        return ItemType.ITEM_TYPE_NORMAL;
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        if (i == ItemType.ITEM_TYPE_RECENTLY) {
            return new RecentlyWalletViewHolder(getItemView(R.layout.item_select_wallet, viewGroup));
        }
        if (i == ItemType.ITEM_TYPE_GROUP) {
            return new WalletGroupViewHolder(getItemView(R.layout.item_select_wallet_group, viewGroup));
        }
        return new WalletViewHolder(getItemView(R.layout.item_select_wallet_normal, viewGroup));
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, SelectWalletBean selectWalletBean) {
        try {
            if (baseViewHolder instanceof WalletViewHolder) {
                final WalletViewHolder walletViewHolder = (WalletViewHolder) baseViewHolder;
                walletViewHolder.onBind(selectWalletBean, this.hideAssets, this.isFromDetail, this);
                walletViewHolder.ivLoading.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewDetachedFromWindow(View view) {
                    }

                    @Override
                    public void onViewAttachedToWindow(View view) {
                        if (view.getVisibility() == 0) {
                            walletViewHolder.ivLoading.updateState(walletViewHolder.ivLoading.getState());
                        }
                    }
                });
            } else if (baseViewHolder instanceof RecentlyWalletViewHolder) {
                ((RecentlyWalletViewHolder) baseViewHolder).onBind(selectWalletBean, this, this.isFromDetail);
            } else if (baseViewHolder instanceof WalletGroupViewHolder) {
                ((WalletGroupViewHolder) baseViewHolder).onBind(selectWalletBean, this.hideAssets, this, this.isFromDetail);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    static class ItemType {
        static int ITEM_TYPE_GROUP = 2;
        static int ITEM_TYPE_NORMAL = 1;
        static int ITEM_TYPE_RECENTLY;

        ItemType() {
        }
    }

    public static class RecentlyWalletViewHolder extends BaseViewHolder {
        MultiSignPopupTextView flagMultiSign;
        ImageView ivCopy;
        View ivJump;
        View ivSelected;
        SimpleDraweeView ivType;
        View llCard;
        TextView tvAddress;
        TextView tvName;

        public RecentlyWalletViewHolder(View view) {
            super(view);
            ItemSelectWalletBinding bind = ItemSelectWalletBinding.bind(view);
            this.llCard = bind.topCard;
            this.ivSelected = bind.assetStatus;
            this.tvName = bind.tvName;
            this.tvAddress = bind.tvAddress;
            this.ivCopy = bind.ivCopy;
            this.ivJump = bind.ivJump;
            this.flagMultiSign = bind.flagMultiSign;
            this.ivType = bind.ivType;
            TouchDelegateUtils.expandViewTouchDelegate(this.ivCopy, 10);
            TouchDelegateUtils.expandViewTouchDelegate(this.ivJump, 10);
        }

        private void setTypeImage(int i) {
            this.ivType.setImageURI(new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(i)).build());
        }

        void onBind(SelectWalletBean selectWalletBean, SelectWalletAdapter selectWalletAdapter, boolean z) {
            Wallet wallet = selectWalletBean.getWallet();
            this.flagMultiSign.setVisibility(selectWalletBean.getAccountType() == 1 ? View.VISIBLE : View.GONE);
            this.flagMultiSign.setWallet(wallet);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(UIUtils.dip2px(8.0f));
            gradientDrawable.setGradientType(0);
            gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            if (SpAPI.THIS.isCold()) {
                gradientDrawable.setColors(new int[]{-12486928, -9194507});
                setTypeImage(R.mipmap.ic_wallet_card_type_cold);
            } else if (wallet.isSamsungWallet()) {
                gradientDrawable.setColors(new int[]{-15719808, -9729057});
                setTypeImage(R.mipmap.ic_wallet_card_type_sansung);
            } else if (LedgerWallet.isLedger(wallet)) {
                gradientDrawable.setColors(new int[]{-15719808, -9729057});
                setTypeImage(R.mipmap.ic_wallet_card_type_ledger);
            } else if (wallet.isWatchCold()) {
                gradientDrawable.setColors(new int[]{-13551260, -8287036});
                setTypeImage(R.mipmap.ic_wallet_card_type_watch_cold);
            } else if (wallet.isWatchOnly()) {
                gradientDrawable.setColors(new int[]{-8099841, -4613137});
                setTypeImage(R.mipmap.ic_wallet_card_type_watch);
            } else {
                gradientDrawable.setColors(new int[]{-12813069, -7885064});
                setTypeImage(R.mipmap.ic_wallet_card_type_hot);
            }
            this.llCard.setBackground(gradientDrawable);
            this.ivSelected.setVisibility(selectWalletBean.isSelected() ? View.VISIBLE : View.GONE);
            this.tvName.setText(wallet.getWalletName());
            this.tvAddress.setText(wallet.getAddress());
            int i = fun2.$SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget[selectWalletBean.getSearchedTarget().ordinal()];
            if (i == 1) {
                this.tvName.setText(selectWalletBean.getSearchedString());
            } else if (i == 2) {
                this.tvAddress.setText(selectWalletBean.getSearchedString());
            }
            addOnClickListener(this.llCard, selectWalletBean, selectWalletAdapter);
            addOnClickListener(this.ivCopy, selectWalletBean, selectWalletAdapter);
            addOnClickListener(this.tvAddress, selectWalletBean, selectWalletAdapter);
            if (!z) {
                this.ivJump.setVisibility(selectWalletBean.getWallet().isShieldedWallet() ? View.GONE : View.VISIBLE);
                addOnClickListener(this.ivJump, selectWalletBean, selectWalletAdapter);
                return;
            }
            this.ivJump.setVisibility(View.GONE);
        }

        private void addOnClickListener(View view, final SelectWalletBean selectWalletBean, final SelectWalletAdapter selectWalletAdapter) {
            if (view != null) {
                if (!view.isClickable()) {
                    view.setClickable(true);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        if (selectWalletAdapter.getOnItemViewClickListener() != null) {
                            selectWalletAdapter.getOnItemViewClickListener().onViewClick(selectWalletAdapter, view2, RecentlyWalletViewHolder.this.getAbsoluteAdapterPosition(), selectWalletBean);
                        }
                    }
                });
            }
        }
    }

    public static class fun2 {
        static final int[] $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget;

        static {
            int[] iArr = new int[SelectWalletBean.SearchedTarget.values().length];
            $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget = iArr;
            try {
                iArr[SelectWalletBean.SearchedTarget.NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget[SelectWalletBean.SearchedTarget.Address.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class WalletViewHolder extends BaseViewHolder {
        MultiSignPopupTextView flagMultiSign;
        ImageView ivCopy;
        View ivJump;
        LoadingView ivLoading;
        View ivSelected;
        SimpleDraweeView ivType;
        View llCard;
        TextView tvAddress;
        TextView tvName;
        TextView tvPathIndex;
        TextView tvValue;

        public WalletViewHolder(View view) {
            super(view);
            ItemSelectWalletNormalBinding bind = ItemSelectWalletNormalBinding.bind(view);
            this.llCard = bind.topCard;
            this.ivSelected = bind.assetStatus;
            this.tvName = bind.tvName;
            this.tvAddress = bind.tvAddress;
            this.ivCopy = bind.ivCopy;
            this.tvValue = bind.tvValue;
            this.ivLoading = bind.ivLoading;
            this.ivJump = bind.ivJump;
            this.tvPathIndex = bind.tvPathIndex;
            this.flagMultiSign = bind.flagMultiSign;
            this.ivType = bind.ivType;
            TouchDelegateUtils.expandViewTouchDelegate(this.ivCopy, 10);
            TouchDelegateUtils.expandViewTouchDelegate(this.ivJump, 10);
        }

        private void showPriceLoading(boolean z, boolean z2) {
            if (z) {
                this.ivLoading.setImageResource(R.mipmap.ic_loading_asset_header);
                this.ivLoading.updateState(LoadingView.State.LOADING);
                return;
            }
            this.ivLoading.updateState(LoadingView.State.GONE);
        }

        private void setTypeImage(int i) {
            this.ivType.setImageURI(new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(i)).build());
        }

        void onBind(SelectWalletBean selectWalletBean, boolean z, boolean z2, SelectWalletAdapter selectWalletAdapter) {
            onBind(selectWalletBean, z, z2, selectWalletAdapter, false);
        }

        void onBind(SelectWalletBean selectWalletBean, boolean z, boolean z2, SelectWalletAdapter selectWalletAdapter, boolean z3) {
            Wallet wallet = selectWalletBean.getWallet();
            this.flagMultiSign.setVisibility(selectWalletBean.getAccountType() == 1 ? View.VISIBLE : View.GONE);
            this.flagMultiSign.setWallet(wallet);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(UIUtils.dip2px(8.0f));
            gradientDrawable.setGradientType(0);
            gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            if (SpAPI.THIS.isCold()) {
                gradientDrawable.setColors(new int[]{-12486928, -9194507});
                setTypeImage(R.mipmap.ic_wallet_card_type_cold_normal);
            } else if (wallet.isSamsungWallet()) {
                gradientDrawable.setColors(new int[]{-15719808, -9729057});
                setTypeImage(R.mipmap.ic_wallet_card_type_sansung_normal);
            } else if (LedgerWallet.isLedger(wallet)) {
                gradientDrawable.setColors(new int[]{-15719808, -9729057});
                setTypeImage(R.mipmap.ic_wallet_card_type_ledger_normal);
            } else if (wallet.isWatchCold()) {
                gradientDrawable.setColors(new int[]{-13551260, -8287036});
                setTypeImage(R.mipmap.ic_wallet_card_type_watch_cold_normal);
            } else if (wallet.isWatchOnly()) {
                gradientDrawable.setColors(new int[]{-8099841, -4613137});
                setTypeImage(R.mipmap.ic_wallet_card_type_watch_normal);
            } else {
                gradientDrawable.setColors(new int[]{-12813069, -7885064});
                setTypeImage(R.mipmap.ic_wallet_card_type_hot_normal);
            }
            this.llCard.setBackground(gradientDrawable);
            this.ivSelected.setVisibility(selectWalletBean.isSelected() ? View.VISIBLE : View.GONE);
            this.tvName.setText(wallet.getWalletName());
            this.tvAddress.setText(wallet.getAddress());
            int i = fun2.$SwitchMap$com$tron$wallet$business$walletmanager$selectwallet$bean$SelectWalletBean$SearchedTarget[selectWalletBean.getSearchedTarget().ordinal()];
            if (i == 1) {
                this.tvName.setText(selectWalletBean.getSearchedString());
            } else if (i == 2) {
                this.tvAddress.setText(selectWalletBean.getSearchedString());
            }
            String str = "-- TRX";
            if (SpAPI.THIS.isCold() || wallet.isShieldedWallet()) {
                this.tvValue.setText("-- TRX");
                this.ivLoading.setVisibility(View.GONE);
            } else if (SpAPI.THIS.isShasta()) {
                this.tvValue.setText("Shasta");
                this.ivLoading.setVisibility(View.GONE);
            } else {
                if (z) {
                    this.tvValue.setText(this.itemView.getResources().getString(R.string.asset_hidden_string));
                } else {
                    TextView textView = this.tvValue;
                    if (!BigDecimalUtils.LessThan((Object) Double.valueOf(selectWalletBean.getBalance()), (Object) 0)) {
                        str = StringTronUtil.formatNumber3Truncate(BigDecimalUtils.toBigDecimal(Double.valueOf(selectWalletBean.getBalance()))) + " TRX";
                    }
                    textView.setText(str);
                }
                showPriceLoading(selectWalletBean.isUpdating(), selectWalletBean.isUpdateResult());
            }
            if (z3 && selectWalletBean.getHdTronRelationshipBean() != null) {
                this.tvPathIndex.setVisibility(View.VISIBLE);
                this.tvPathIndex.setText(this.itemView.getContext().getResources().getString(R.string.select_wallet_path, WalletPath.buildPathString(selectWalletBean.getHdTronRelationshipBean().getWalletPath())));
            } else {
                this.tvPathIndex.setText("");
                this.tvPathIndex.setVisibility(View.GONE);
            }
            addOnClickListener(this.llCard, selectWalletBean, selectWalletAdapter);
            addOnClickListener(this.ivCopy, selectWalletBean, selectWalletAdapter);
            addOnClickListener(this.tvAddress, selectWalletBean, selectWalletAdapter);
            if (!z2) {
                this.ivJump.setVisibility(selectWalletBean.getWallet().isShieldedWallet() ? View.GONE : View.VISIBLE);
                addOnClickListener(this.ivJump, selectWalletBean, selectWalletAdapter);
                return;
            }
            this.ivJump.setVisibility(View.GONE);
        }

        private void addOnClickListener(View view, final SelectWalletBean selectWalletBean, final SelectWalletAdapter selectWalletAdapter) {
            if (view != null) {
                if (!view.isClickable()) {
                    view.setClickable(true);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        if (selectWalletAdapter.getOnItemViewClickListener() != null) {
                            selectWalletAdapter.getOnItemViewClickListener().onViewClick(selectWalletAdapter, view2, WalletViewHolder.this.getAbsoluteAdapterPosition(), selectWalletBean);
                        }
                    }
                });
            }
        }
    }

    public static class WalletGroupViewHolder extends BaseViewHolder {
        ImageView ivTipsHd;
        LinearLayout llContent;
        private BasePopupView tipsWindow;
        TextView tvHd;
        TextView tvTotalBalance;

        public WalletGroupViewHolder(View view) {
            super(view);
            ItemSelectWalletGroupBinding bind = ItemSelectWalletGroupBinding.bind(view);
            this.tvHd = bind.tvHd;
            this.tvTotalBalance = bind.tvTotalBalance;
            this.llContent = bind.rlGroupContent;
            this.ivTipsHd = bind.ivTips;
        }

        void onBind(SelectWalletBean selectWalletBean, boolean z, SelectWalletAdapter selectWalletAdapter, boolean z2) {
            this.llContent.removeAllViews();
            BigDecimal bigDecimal = BigDecimal.ZERO;
            for (SelectWalletBean selectWalletBean2 : selectWalletBean.getRelationWallets()) {
                ViewGroup viewGroup = (ViewGroup) View.inflate(this.itemView.getContext(), R.layout.item_select_wallet_normal, null);
                RelativeLayout relativeLayout = (RelativeLayout) viewGroup.findViewById(R.id.top_card);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
                layoutParams.leftMargin = UIUtils.dip2px(10.0f);
                layoutParams.rightMargin = UIUtils.dip2px(10.0f);
                relativeLayout.setLayoutParams(layoutParams);
                this.llContent.addView(viewGroup);
                final WalletViewHolder walletViewHolder = new WalletViewHolder(viewGroup);
                walletViewHolder.onBind(selectWalletBean2, z, z2, selectWalletAdapter, true);
                walletViewHolder.ivLoading.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewDetachedFromWindow(View view) {
                    }

                    @Override
                    public void onViewAttachedToWindow(View view) {
                        if (view.getVisibility() == 0) {
                            walletViewHolder.ivLoading.updateState(walletViewHolder.ivLoading.getState());
                        }
                    }
                });
                bigDecimal = BigDecimalUtils.sum_(bigDecimal, Double.valueOf(BigDecimalUtils.LessThan((Object) Double.valueOf(selectWalletBean2.getBalance()), (Object) 0) ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : selectWalletBean2.getBalance()));
            }
            if (IRequest.isShasta()) {
                this.tvTotalBalance.setVisibility(View.INVISIBLE);
            } else {
                this.tvTotalBalance.setVisibility(View.VISIBLE);
                TextView textView = this.tvTotalBalance;
                textView.setText(this.itemView.getContext().getResources().getString(R.string.total_value, StringTronUtil.formatNumber3Truncate(bigDecimal)) + " TRX");
            }
            this.ivTipsHd.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.HD_EXPLAIN);
                    SpannableString spannableString = new SpannableString(WalletGroupViewHolder.this.itemView.getContext().getResources().getString(R.string.hd_tips_select_wallet) + " " + WalletGroupViewHolder.this.itemView.getContext().getResources().getString(R.string.hd_tips_select_wallet_learn));
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(WalletGroupViewHolder.this.itemView.getContext().getResources().getColor(R.color.blue_3c));
                            textPaint.setUnderlineText(false);
                        }

                        @Override
                        public void onClick(View view2) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.HD_EXPLAIN_DOC);
                            CommonWebActivityV3.start(WalletGroupViewHolder.this.itemView.getContext(), IRequest.getLearnHDUrl(), "", -2, false);
                            if (WalletGroupViewHolder.this.tipsWindow != null) {
                                WalletGroupViewHolder.this.tipsWindow.dismiss();
                            }
                        }
                    }, WalletGroupViewHolder.this.itemView.getContext().getResources().getString(R.string.hd_tips_select_wallet).length(), WalletGroupViewHolder.this.itemView.getContext().getResources().getString(R.string.hd_tips_select_wallet).length() + 1 + WalletGroupViewHolder.this.itemView.getContext().getResources().getString(R.string.hd_tips_select_wallet_learn).length(), 33);
                    WalletGroupViewHolder.this.tipsWindow = new MultiLineTextPopupView.Builder().setAnchor(view).setPreferredShowUp(true).setRequiredWidth(UIUtils.dip2px(288.0f)).addKeyValue(spannableString, "").build(WalletGroupViewHolder.this.itemView.getContext());
                    WalletGroupViewHolder.this.tipsWindow.show();
                }
            });
        }
    }
}
