package com.tron.wallet.common.adapter.nft;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.common.components.CircularImageDraweeView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
class NftListHolder extends BaseViewHolder {
    ImageView ivArrow;
    CircularImageDraweeView ivLogo;
    TextView tvName;
    TextView tvTokenId;

    public NftListHolder(View view) {
        super(view);
        mappingId(view);
    }

    public void onBind(NftItemInfo nftItemInfo, String str) {
        if (!StringTronUtil.isEmpty(nftItemInfo.getLogoUrl())) {
            this.ivLogo.setImageURI(nftItemInfo.getLogoUrl());
        }
        this.tvTokenId.setText(String.format("#%s", nftItemInfo.getAssetId()));
        this.tvName.setText(nftItemInfo.getName());
        this.tvName.setVisibility(StringTronUtil.isEmpty(nftItemInfo.getName()) ? View.GONE : View.VISIBLE);
        if (TextUtils.isEmpty(nftItemInfo.getIntro())) {
            nftItemInfo.setIntro(this.tvName.getContext().getString(R.string.no_intro));
        }
    }

    private void mappingId(View view) {
        this.ivLogo = (CircularImageDraweeView) view.findViewById(R.id.iv_logo);
        this.tvName = (TextView) view.findViewById(R.id.tv_name);
        this.tvTokenId = (TextView) view.findViewById(R.id.tv_token_id);
        this.ivArrow = (ImageView) view.findViewById(R.id.iv_arrow_right);
    }
}
