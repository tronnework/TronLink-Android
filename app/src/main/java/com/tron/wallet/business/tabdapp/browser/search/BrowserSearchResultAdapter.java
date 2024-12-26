package com.tron.wallet.business.tabdapp.browser.search;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.tabdapp.browser.search.SearchDappResultBean;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemDappSearchResultListBinding;
import com.tronlinkpro.wallet.R;
public class BrowserSearchResultAdapter extends BaseQuickAdapter<SearchDappResultBean.SearchDappBean, SearchHolder> {
    private final ItemCallback itemCallback;

    public interface ItemCallback {
        void onItemClicked(SearchDappResultBean.SearchDappBean searchDappBean, int i);
    }

    public BrowserSearchResultAdapter(final ItemCallback itemCallback) {
        super((int) R.layout.item_dapp_search_result_list);
        this.itemCallback = itemCallback;
        setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                lambda$new$0(itemCallback, baseQuickAdapter, view, i);
            }
        });
    }

    public void lambda$new$0(ItemCallback itemCallback, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        SearchDappResultBean.SearchDappBean item;
        if (view.getId() != R.id.iv_arrows || (item = getItem(i)) == null || itemCallback == null) {
            return;
        }
        itemCallback.onItemClicked(item, i);
    }

    @Override
    public void convert(SearchHolder searchHolder, SearchDappResultBean.SearchDappBean searchDappBean) {
        searchHolder.onBind(searchDappBean, getParentPosition(searchDappBean), getItemCount(), this.itemCallback);
    }

    public static class SearchHolder extends BaseViewHolder {
        private ItemDappSearchResultListBinding binding;
        private ForegroundColorSpan foregroundColorSpan;
        ImageView ivArrow;
        TokenLogoDraweeView logoIv;
        TextView tvName;
        TextView tvUrl;
        private ForegroundColorSpan urlForegroundColorSpan;

        private String getEllipsizedText(TextView textView, String str) {
            return str;
        }

        public SearchHolder(View view) {
            super(view);
            mappingId(view);
            int dip2px = UIUtils.dip2px(10.0f);
            TouchDelegateUtils.expandViewTouchDelegate(this.ivArrow, dip2px, dip2px, dip2px, dip2px);
            this.foregroundColorSpan = new ForegroundColorSpan(view.getContext().getResources().getColor(R.color.blue_3c));
            this.urlForegroundColorSpan = new ForegroundColorSpan(view.getContext().getResources().getColor(R.color.blue_3c));
        }

        public void mappingId(View view) {
            ItemDappSearchResultListBinding bind = ItemDappSearchResultListBinding.bind(view);
            this.logoIv = bind.ivLogo;
            this.tvName = bind.name;
            this.tvUrl = bind.url;
            this.ivArrow = bind.ivArrows;
        }

        private void updateName(SearchDappResultBean.SearchDappBean searchDappBean, String str) {
            if (TextUtils.isEmpty(searchDappBean.getName())) {
                this.tvName.setVisibility(View.GONE);
                return;
            }
            int indexOf = getEllipsizedText(this.tvName, searchDappBean.getName()).toLowerCase().indexOf(str);
            if (indexOf == -1) {
                this.tvName.setText(searchDappBean.getName());
                return;
            }
            SpannableString spannableString = new SpannableString(searchDappBean.getName());
            spannableString.setSpan(this.foregroundColorSpan, indexOf, str.length() + indexOf, 33);
            this.tvName.setText(spannableString);
        }

        private void updateUrl(SearchDappResultBean.SearchDappBean searchDappBean, String str) {
            if (TextUtils.isEmpty(searchDappBean.getHomeUrl())) {
                this.tvUrl.setText(searchDappBean.getHomeUrl());
                return;
            }
            int indexOf = getEllipsizedText(this.tvUrl, searchDappBean.getHomeUrl()).toLowerCase().indexOf(str);
            if (indexOf == -1) {
                this.tvUrl.setText(searchDappBean.getHomeUrl());
                return;
            }
            SpannableString spannableString = new SpannableString(searchDappBean.getHomeUrl());
            spannableString.setSpan(this.urlForegroundColorSpan, indexOf, str.length() + indexOf, 33);
            this.tvUrl.setText(spannableString);
        }

        public void onBind(final SearchDappResultBean.SearchDappBean searchDappBean, final int i, int i2, final ItemCallback itemCallback) {
            if (searchDappBean == null) {
                return;
            }
            updateName(searchDappBean, searchDappBean.getKeyword());
            this.logoIv.setImageURI(searchDappBean.getImageUrl());
            updateUrl(searchDappBean, searchDappBean.getKeyword());
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemCallback.onItemClicked(searchDappBean, i);
                }
            });
        }
    }
}
