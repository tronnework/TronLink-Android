package com.tron.wallet.business.addassets2.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.tron.wallet.business.addassets2.adapter.AssetsListAdapter;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.SearchLoadMoreView;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.util.List;
import java.util.Set;
public class SearchResultAdapter extends BaseQuickAdapter<TokenBean, SearchHolder> {
    private Set<Integer> disableTokenTypes;
    private boolean isShowFlag;
    private final AssetsListAdapter.ItemCallback itemCallback;
    private final LoadMoreView loadMore;
    private int switchIconSelectedResId;
    private int switchIconUnSelectedResId;

    public void setDisableTokenType(Set<Integer> set) {
        this.disableTokenTypes = set;
    }

    public void setSwitchIcon(int i, int i2) {
        this.switchIconSelectedResId = i;
        this.switchIconUnSelectedResId = i2;
    }

    public SearchResultAdapter(final AssetsListAdapter.ItemCallback itemCallback, boolean z) {
        super((int) R.layout.item_assets_list);
        SearchLoadMoreView searchLoadMoreView = new SearchLoadMoreView();
        this.loadMore = searchLoadMoreView;
        setLoadMoreView(searchLoadMoreView);
        this.itemCallback = itemCallback;
        this.isShowFlag = z;
        setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                lambda$new$0(itemCallback, baseQuickAdapter, view, i);
            }
        });
    }

    public void lambda$new$0(AssetsListAdapter.ItemCallback itemCallback, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        TokenBean item;
        if (view.getId() == R.id.iv_switch) {
            TokenBean item2 = getItem(i);
            if (item2 == null || itemCallback == null) {
                return;
            }
            itemCallback.onItemClicked(item2, i);
        } else if (view.getId() != R.id.rl_inner || (item = getItem(i)) == null || itemCallback == null) {
        } else {
            itemCallback.onItemSelected(item, i);
        }
    }

    public void notifyAssetAddState(TokenBean tokenBean, int i) {
        List<TokenBean> data = getData();
        if (i >= data.size() || i < 0) {
            return;
        }
        data.get(i).isSelected = tokenBean.isSelected;
        notifyItemChanged(i);
    }

    @Override
    public void convert(SearchHolder searchHolder, TokenBean tokenBean) {
        if (tokenBean != null && tokenBean.getType() == 0) {
            searchHolder.itemView.getLayoutParams().height = UIUtils.dip2px(85.0f);
        }
        searchHolder.onBind(tokenBean, getParentPosition(tokenBean), getItemCount());
        int i = 8;
        if (this.switchIconSelectedResId != 0) {
            if (tokenBean.isSelected) {
                searchHolder.actualHolder.switchIv.setImageResource(this.switchIconSelectedResId);
                searchHolder.actualHolder.switchIv.setVisibility(View.VISIBLE);
            } else if (this.switchIconUnSelectedResId != 0) {
                searchHolder.actualHolder.switchIv.setImageResource(this.switchIconUnSelectedResId);
                searchHolder.actualHolder.switchIv.setVisibility(View.VISIBLE);
            } else {
                searchHolder.actualHolder.switchIv.setVisibility(View.GONE);
            }
        }
        View view = searchHolder.actualHolder.vDisable;
        Set<Integer> set = this.disableTokenTypes;
        if (set != null && set.contains(Integer.valueOf(tokenBean.getType()))) {
            i = 0;
        }
        view.setVisibility(i);
        searchHolder.addOnClickListener(R.id.rl_inner);
        searchHolder.addOnClickListener(R.id.iv_switch);
    }

    public void setLoadMoreText(int i) {
        ((CustomLoadMoreView) this.loadMore).setNoMoreText(i, new Object[0]);
    }

    public class SearchHolder extends BaseViewHolder {
        private final AssetsListAdapter.ViewHolder actualHolder;

        public SearchHolder(View view) {
            super(view);
            this.actualHolder = new AssetsListAdapter.ViewHolder(view, isShowFlag);
        }

        public void onBind(TokenBean tokenBean, int i, int i2) {
            this.actualHolder.onBind(this.itemView.getContext(), tokenBean, i, i2, AssetsListAdapter.TagType.SHOW_ALL);
        }
    }
}
