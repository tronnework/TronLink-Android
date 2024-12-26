package com.tron.wallet.business.addassets2.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.addassets2.adapter.AssetsListAdapter;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.RVItemTouchHelper;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class HomeAssetAdapter extends AssetsListAdapter implements RVItemTouchHelper.ItemTouchListener {
    private final List<TokenBean> cacheTokens;
    private final SparseArray<TokenBean> changedTokens;
    private int state;

    public interface State {
        public static final int NORMAL = 1;
        public static final int SORT = 2;
    }

    public SparseArray<TokenBean> getChangedTokens() {
        return this.changedTokens;
    }

    public boolean isStateSort() {
        return this.state == 2;
    }

    public HomeAssetAdapter(Context context, AssetsListAdapter.ItemCallback itemCallback) {
        super(context, itemCallback);
        this.state = 1;
        this.cacheTokens = new ArrayList();
        this.changedTokens = new SparseArray<>();
    }

    public void updateOperationState(boolean z) {
        this.state = z ? 2 : 1;
        this.changedTokens.clear();
        if (this.tokens == null) {
            return;
        }
        notifyDataSetChanged();
    }

    public void onSortCancel() {
        this.tokens.clear();
        this.tokens.addAll(this.cacheTokens);
        notifyDataSetChanged();
    }

    public void onSortConfirm() {
        if (this.tokens.size() > 0 && (this.tokens.get(0).type != 0 || this.tokens.get(0).top != 2)) {
            this.changedTokens.append(0, this.tokens.get(0));
        }
        for (int i = 1; i < this.tokens.size(); i++) {
            this.changedTokens.append(i, this.tokens.get(i));
        }
        this.cacheTokens.clear();
        this.cacheTokens.addAll(this.tokens);
    }

    @Override
    public void notifyDataChanged(List<TokenBean> list) {
        super.notifyDataChanged(list);
        this.cacheTokens.clear();
        this.cacheTokens.addAll(list);
    }

    @Override
    public void onMove(RecyclerView recyclerView, int i, int i2) {
        if (i >= this.tokens.size() || i2 >= this.tokens.size() || this.tokens.get(i).type == 0 || this.tokens.get(i).top == 2 || this.tokens.get(i2).type == 0 || this.tokens.get(i2).top == 2) {
            return;
        }
        notifyItemMoved(i, i2);
        TokenBean remove = this.tokens.remove(i);
        if (i2 < this.tokens.size()) {
            this.tokens.add(i2, remove);
        } else {
            this.tokens.add(remove);
        }
    }

    @Override
    public void onSelect(RecyclerView.ViewHolder viewHolder, boolean z) {
        View view = viewHolder.itemView;
        view.setBackgroundColor(view.getContext().getResources().getColor(z ? R.color.black_02_06 : R.color.transparent));
    }

    @Override
    public BaseHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new HomeViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assets_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final BaseHolder baseHolder, int i) {
        super.onBindViewHolder(baseHolder, i);
        if (!(baseHolder instanceof HomeViewHolder) || i < 0 || i >= this.tokens.size()) {
            return;
        }
        int i2 = this.state;
        if (i2 == 2) {
            ((HomeViewHolder) baseHolder).showSort(this.tokens.get(i));
        } else if (i2 == 1) {
            ((HomeViewHolder) baseHolder).showNormal(this.tokens.get(i));
        }
        ((HomeViewHolder) baseHolder).switchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onBindViewHolder$0(baseHolder, view);
            }
        });
    }

    public void lambda$onBindViewHolder$0(BaseHolder baseHolder, View view) {
        int adapterPosition;
        if (this.state == 1 && this.itemCallback != null && (adapterPosition = baseHolder.getAdapterPosition()) != -1 && adapterPosition < this.tokens.size()) {
            this.itemCallback.onItemSelected(this.tokens.get(adapterPosition), baseHolder.getAdapterPosition());
        }
    }

    public static class HomeViewHolder extends AssetsListAdapter.ViewHolder {
        public HomeViewHolder(View view) {
            super(view);
        }

        @Override
        public void setupToolView(ImageView imageView, TokenBean tokenBean) {
            super.setupToolView(imageView, tokenBean);
            if (!tokenBean.isSelected) {
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setImageResource(R.mipmap.ic_manual_sort_normal);
            }
        }

        public void showSort(TokenBean tokenBean) {
            if (tokenBean.type == 0 || tokenBean.top == 2) {
                return;
            }
            this.switchIv.setImageResource(R.mipmap.ic_manual_sort_normal);
        }

        public void showNormal(TokenBean tokenBean) {
            showSort(tokenBean);
        }
    }
}
