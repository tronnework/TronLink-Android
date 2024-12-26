package com.tron.wallet.business.assetshome.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.addassets2.MyAllAssetsActivity;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.common.adapter.holder.AssetsContentHolder;
import com.tron.wallet.common.adapter.holder.NftTokenViewHolder;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.TextLinkLoadMoreView;
import com.tron.wallet.common.components.animatorrecycleview.AnimatorUtil;
import com.tron.wallet.common.components.animatorrecycleview.ViewAnimator;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.List;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class AssetAdapter extends BaseQuickAdapter<TokenBean, BaseViewHolder> {
    private Protocol.Account account;
    private int currentDataType;
    private boolean hidePrivacy;
    private final TextLinkLoadMoreView loadMoreView;
    private ViewAnimator viewAnimator;

    @Override
    protected int getDefItemViewType(int i) {
        return this.currentDataType;
    }

    public AssetAdapter(Context context, int i) {
        super((int) R.layout.item_assets_adapter);
        this.mContext = context;
        this.currentDataType = i;
        TextLinkLoadMoreView textLinkLoadMoreView = new TextLinkLoadMoreView();
        this.loadMoreView = textLinkLoadMoreView;
        setLoadMoreView(textLinkLoadMoreView);
    }

    public void updateData(List<TokenBean> list, boolean z, Protocol.Account account) {
        this.account = account;
        this.hidePrivacy = z;
        setNewData(list);
    }

    @Override
    public void bindToRecyclerView(RecyclerView recyclerView) {
        super.bindToRecyclerView(recyclerView);
        this.viewAnimator = new ViewAnimator(recyclerView);
    }

    private void animateView(View view, int i) {
        this.viewAnimator.animateViewIfNecessary(i, view, AnimatorUtil.concatAnimators(getAnimators(view), ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f)));
    }

    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationY", getRecyclerView().getMeasuredHeight() >> 1, 0.0f)};
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        super.onBindViewHolder((AssetAdapter) baseViewHolder, i);
        if (baseViewHolder.getItemViewType() == 546 && this.loadMoreView.getLinkTextView() != null) {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null && selectedPublicWallet.isWatchNotPaired()) {
                baseViewHolder.itemView.setVisibility(View.GONE);
            } else {
                baseViewHolder.itemView.setVisibility(View.VISIBLE);
            }
            this.loadMoreView.getLinkTextView().setText(this.currentDataType == 1 ? R.string.no_more_asset_extra_nft : R.string.no_more_asset_nft_extra);
            this.loadMoreView.getLinkTextView().setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$onBindViewHolder$0(view);
                }
            });
        }
        this.viewAnimator.cancelExistingAnimation(baseViewHolder.itemView);
        animateView(baseViewHolder.itemView, i);
    }

    public void lambda$onBindViewHolder$0(View view) {
        MyAllAssetsActivity.startActivity(view.getContext(), this.currentDataType);
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new NftTokenViewHolder(viewGroup);
        }
        return new AssetsContentHolder(getItemView(this.mLayoutResId, viewGroup));
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, TokenBean tokenBean) {
        double price = SpAPI.THIS.getPrice();
        if (baseViewHolder instanceof NftTokenViewHolder) {
            ((NftTokenViewHolder) baseViewHolder).onBind(NftTokenBean.covertFromTokenBean(tokenBean), this.hidePrivacy);
        } else {
            ((AssetsContentHolder) baseViewHolder).bindHolder(baseViewHolder.itemView.getContext(), getData(), baseViewHolder.getAdapterPosition(), price, this.hidePrivacy, this.account);
        }
    }
}
