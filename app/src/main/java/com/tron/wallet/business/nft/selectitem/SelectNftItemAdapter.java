package com.tron.wallet.business.nft.selectitem;

import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.SearchLoadMoreView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemNftListBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
public class SelectNftItemAdapter extends BaseQuickAdapter<NftItemInfo, SearchHolder> {
    private final LoadMoreView loadMore;
    private NftItemInfo selectedItem;

    public void setSelectedItem(NftItemInfo nftItemInfo) {
        this.selectedItem = nftItemInfo;
    }

    public SelectNftItemAdapter() {
        super(R.layout.item_nft_list, new ArrayList());
        SearchLoadMoreView searchLoadMoreView = new SearchLoadMoreView();
        this.loadMore = searchLoadMoreView;
        setLoadMoreView(searchLoadMoreView);
    }

    @Override
    public void convert(SearchHolder searchHolder, NftItemInfo nftItemInfo) {
        searchHolder.onBind(nftItemInfo, this.selectedItem);
        searchHolder.addOnClickListener(R.id.ll_nft_item);
    }

    public void setLoadMoreText(int i) {
        ((CustomLoadMoreView) this.loadMore).setNoMoreText(i, new Object[0]);
    }

    public static class SearchHolder extends BaseViewHolder {
        private ItemNftListBinding binding;
        SimpleDraweeView ivLogo;
        View ivSelected;
        TextView tvId;
        TextView tvName;

        public SearchHolder(View view) {
            super(view);
            mappingId(view);
        }

        private void mappingId(View view) {
            ItemNftListBinding bind = ItemNftListBinding.bind(view);
            this.binding = bind;
            this.ivLogo = bind.ivLogo;
            this.tvName = this.binding.tvName;
            this.tvId = this.binding.tvId;
            this.ivSelected = this.binding.ivArrowRight;
        }

        public void onBind(NftItemInfo nftItemInfo, NftItemInfo nftItemInfo2) {
            this.ivLogo.setImageURI(nftItemInfo.getImageUrl());
            int i = 8;
            if (StringTronUtil.isEmpty(nftItemInfo.getName())) {
                TextView textView = this.tvName;
                textView.setText("# " + nftItemInfo.getAssetId());
                this.tvId.setVisibility(View.GONE);
            } else {
                this.tvName.setText(nftItemInfo.getName());
                this.tvId.setVisibility(View.VISIBLE);
                TextView textView2 = this.tvId;
                textView2.setText("# " + nftItemInfo.getAssetId());
            }
            View view = this.ivSelected;
            if (nftItemInfo2 != null && nftItemInfo.getAssetId().equals(nftItemInfo2.getAssetId())) {
                i = 0;
            }
            view.setVisibility(i);
        }
    }
}
