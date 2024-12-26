package com.tron.wallet.common.adapter.nft;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.addassets2.bean.nft.NftTypeInfo;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.CircularImageDraweeView;
import com.tron.wallet.common.components.CustomTokenNoFunctionView;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class NftHeaderHolder extends BaseViewHolder {
    ImageView ivArrow;
    ImageView ivCopy;
    CircularImageDraweeView ivLogo;
    CustomTokenNoFunctionView noFunctionView;
    RelativeLayout rlBg;
    TextView tvContractAddress;
    TextView tvContractAddressTitle;
    TextView tvName;

    public NftHeaderHolder(View view) {
        super(view);
        mappingId(view);
    }

    public void onBind(NftTypeInfo nftTypeInfo, TokenBean tokenBean, final String str, List<String> list) {
        if (nftTypeInfo == null) {
            return;
        }
        if (TextUtils.isEmpty(tokenBean.getLogoUrl())) {
            this.ivLogo.setImageResource(R.mipmap.ic_custom_token_logo_big);
        } else {
            this.ivLogo.setCircularImage(nftTypeInfo.getLogoUrl());
        }
        String fullName = nftTypeInfo.getFullName();
        if (!TextUtils.isEmpty(fullName)) {
            this.tvName.setText(fullName);
        } else {
            TextView textView = this.tvName;
            textView.setText(textView.getContext().getString(R.string.no_name_nft));
        }
        String tokenAddress = nftTypeInfo.getTokenAddress();
        if (!TextUtils.isEmpty(tokenAddress)) {
            this.tvContractAddress.setText(tokenAddress);
        } else {
            this.tvContractAddress.setVisibility(View.INVISIBLE);
            this.tvContractAddressTitle.setVisibility(View.INVISIBLE);
            this.ivCopy.setVisibility(View.INVISIBLE);
        }
        this.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onBind$0(str, view);
            }
        });
        this.ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onBind$1(str, view);
            }
        });
        if (tokenBean.getTokenStatus() == 1) {
            if (list != null) {
                this.noFunctionView.setVisibility(View.VISIBLE);
                this.noFunctionView.setData(list);
                return;
            }
            this.noFunctionView.setVisibility(View.GONE);
        } else if (!tokenBean.getTransferStatus()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.itemView.getContext().getString(R.string.not_support_transfer));
            this.noFunctionView.setVisibility(View.VISIBLE);
            this.noFunctionView.setData(arrayList);
        } else {
            this.noFunctionView.setVisibility(View.GONE);
        }
    }

    public void lambda$onBind$0(String str, View view) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonWebActivityV3.start(this.itemView.getContext(), str, this.itemView.getContext().getString(R.string.nft_project_detail), -2, false);
    }

    public void lambda$onBind$1(String str, View view) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonWebActivityV3.start(this.itemView.getContext(), str, this.itemView.getContext().getString(R.string.nft_project_detail), -2, false);
    }

    private void mappingId(View view) {
        this.tvContractAddressTitle = (TextView) view.findViewById(R.id.tv_contract_address_title);
        this.tvContractAddress = (TextView) view.findViewById(R.id.tv_contract_address);
        this.ivLogo = (CircularImageDraweeView) view.findViewById(R.id.iv_logo);
        this.tvName = (TextView) view.findViewById(R.id.tv_name);
        this.rlBg = (RelativeLayout) view.findViewById(R.id.rl_bg);
        this.ivCopy = (ImageView) view.findViewById(R.id.iv_copy);
        this.ivArrow = (ImageView) view.findViewById(R.id.ic_arrow);
        this.noFunctionView = (CustomTokenNoFunctionView) view.findViewById(R.id.rc_tips);
    }
}
