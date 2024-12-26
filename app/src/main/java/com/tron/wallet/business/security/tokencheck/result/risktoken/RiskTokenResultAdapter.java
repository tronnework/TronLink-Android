package com.tron.wallet.business.security.tokencheck.result.risktoken;

import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.databinding.ItemTokenCheckRiskSearchResultListBinding;
import com.tronlinkpro.wallet.R;
public class RiskTokenResultAdapter extends BaseQuickAdapter<TokenBean, RiskTokenHolder> {
    private final ItemCallback itemCallback;

    public interface ItemCallback {
        void onItemClicked(TokenBean tokenBean, int i);
    }

    public RiskTokenResultAdapter(ItemCallback itemCallback) {
        super((int) R.layout.item_token_check_risk_search_result_list);
        this.itemCallback = itemCallback;
    }

    @Override
    public void convert(RiskTokenHolder riskTokenHolder, TokenBean tokenBean) {
        riskTokenHolder.onBind(tokenBean, getParentPosition(tokenBean), getItemCount(), this.itemCallback);
    }

    public static class RiskTokenHolder extends BaseViewHolder {
        private ItemTokenCheckRiskSearchResultListBinding binding;
        TokenLogoDraweeView logoIv;
        TextView tvName;
        TextView tvPrice;
        TextView tvType;

        private String getEllipsizedText(TextView textView, String str) {
            return str;
        }

        public RiskTokenHolder(View view) {
            super(view);
            mappingId(view);
        }

        private void mappingId(View view) {
            ItemTokenCheckRiskSearchResultListBinding bind = ItemTokenCheckRiskSearchResultListBinding.bind(view);
            this.binding = bind;
            this.tvName = bind.assetsName;
            this.logoIv = this.binding.ivLogo;
            this.tvType = (TextView) this.binding.getRoot().findViewById(R.id.type);
            this.tvPrice = this.binding.price;
        }

        public void onBind(final TokenBean tokenBean, final int i, int i2, final ItemCallback itemCallback) {
            if (tokenBean == null) {
                return;
            }
            this.logoIv.setImageURI(tokenBean.getLogoUrl());
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemCallback.onItemClicked(tokenBean, i);
                }
            });
        }
    }
}
