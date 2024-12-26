package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.utils.TokenItemUtil;
import com.tron.wallet.databinding.ItemAssetsAdapterBinding;
import com.tronlinkpro.wallet.R;
import org.apache.commons.cli.HelpFormatter;
public class NftTokenViewHolder extends BaseViewHolder {
    RelativeLayout bgLogo;
    TokenLogoDraweeView ivLogo;
    View ivOfficialImage;
    ImageView ivScam;
    private Context mContext;
    private boolean mIsHidden;
    TextView tvCount;
    TextView tvName;
    TextView tvPrice;
    TextView tvSubName;

    public NftTokenViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assets_adapter, viewGroup, false));
        this.mContext = viewGroup.getContext();
        mappingId(ItemAssetsAdapterBinding.bind(this.itemView));
        this.tvPrice.setVisibility(View.GONE);
    }

    public NftTokenViewHolder(ViewGroup viewGroup, boolean z) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assets_adapter, viewGroup, false));
        mappingId(ItemAssetsAdapterBinding.bind(this.itemView));
        this.tvPrice.setVisibility(View.GONE);
        this.mIsHidden = z;
    }

    private void mappingId(ItemAssetsAdapterBinding itemAssetsAdapterBinding) {
        this.tvName = itemAssetsAdapterBinding.assetsName;
        this.ivLogo = itemAssetsAdapterBinding.ivLogo;
        this.tvCount = itemAssetsAdapterBinding.assetsCount;
        this.tvSubName = itemAssetsAdapterBinding.assetsSubName;
        this.tvPrice = itemAssetsAdapterBinding.assetsPrice;
        this.bgLogo = itemAssetsAdapterBinding.bgLogo;
        this.ivOfficialImage = itemAssetsAdapterBinding.ivOfficialImage;
        this.ivScam = itemAssetsAdapterBinding.ivScam;
    }

    public void onBind(NftTokenBean nftTokenBean, boolean z) {
        if (nftTokenBean == null) {
            return;
        }
        this.mIsHidden = z;
        this.tvName.setText(nftTokenBean.getShortName());
        GenericDraweeHierarchy hierarchy = this.ivLogo.getHierarchy();
        hierarchy.setFailureImage(R.mipmap.ic_nft_default);
        hierarchy.setPlaceholderImage(R.mipmap.ic_nft_default);
        if (TextUtils.isEmpty(nftTokenBean.getLogoUrl())) {
            this.ivLogo.setImageResource(AssetsConfig.getTokenDefaultLogoIconId(nftTokenBean));
        } else {
            this.ivLogo.setCircularImage(nftTokenBean.getLogoUrl());
        }
        if (this.mIsHidden) {
            this.tvCount.setText(this.mContext.getResources().getString(R.string.asset_hidden_string));
        } else {
            this.tvCount.setText(String.valueOf(nftTokenBean.getCount()));
        }
        this.tvSubName.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(nftTokenBean.getName())) {
            this.tvSubName.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        } else {
            this.tvSubName.setText(nftTokenBean.getName());
            this.tvSubName.setEllipsize(TextUtils.TruncateAt.END);
        }
        if (nftTokenBean != null && nftTokenBean.getIsOfficial() == 1) {
            this.ivOfficialImage.setVisibility(View.VISIBLE);
        } else {
            this.ivOfficialImage.setVisibility(View.GONE);
        }
        TokenItemUtil.initScamFlagView(this.mContext, this.ivScam, nftTokenBean.convertToTokenBean(), true);
    }
}
