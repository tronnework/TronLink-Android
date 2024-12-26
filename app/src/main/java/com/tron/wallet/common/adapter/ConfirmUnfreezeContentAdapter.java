package com.tron.wallet.common.adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.FreezeUnFreezeParam;
import com.tron.wallet.common.adapter.ConfirmUnfreezeContentAdapter;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.EllipsizeTextViewUtils;
import com.tron.wallet.databinding.ItemConfirmExtraClickableBinding;
import com.tron.wallet.databinding.ItemConfirmExtraInnerListBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class ConfirmUnfreezeContentAdapter extends RecyclerView.Adapter<BaseViewHolder> implements BaseConfirmFragment.AccountCallback {
    private static final int TYPE_FOOTER_RESOURCE = 2;
    private static final int TYPE_INNER_LIST = 1;
    private static final int TYPE_NORMAL = 0;
    private boolean expand = false;
    private final GlobalFeeResourceView.FeeResourceCallback feeCallback;
    List<ConfirmExtraText> mList;
    private BaseParam param;
    private final RecyclerView recyclerView;
    private int releasedResourceTypeCount;
    private List<ConfirmExtraText> unfreezeResources;

    private int getExtraItemCount() {
        return this.expand ? 2 : 1;
    }

    public ConfirmUnfreezeContentAdapter(RecyclerView recyclerView, GlobalFeeResourceView.FeeResourceCallback feeResourceCallback) {
        this.recyclerView = recyclerView;
        this.feeCallback = feeResourceCallback;
    }

    public void updateData(BaseParam baseParam) {
        this.param = baseParam;
        if (baseParam instanceof FreezeUnFreezeParam) {
            FreezeUnFreezeParam freezeUnFreezeParam = (FreezeUnFreezeParam) baseParam;
            this.unfreezeResources = freezeUnFreezeParam.unfreezeResources;
            this.releasedResourceTypeCount = freezeUnFreezeParam.releasedResourceTypeCount;
        }
        if (this.unfreezeResources == null) {
            this.unfreezeResources = new ArrayList();
        }
        List<ConfirmExtraText> textLists = baseParam.getTextLists();
        this.mList = textLists;
        if (textLists == null) {
            this.mList = new ArrayList();
        }
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            return new InnerRvHolder(from.inflate(R.layout.confirm_unfreeze_inner_rv, viewGroup, false));
        }
        if (i == 2) {
            GlobalFeeResourceView globalFeeResourceView = new GlobalFeeResourceView(viewGroup.getContext());
            globalFeeResourceView.setFeeResourceCallback(this.feeCallback);
            return new FooterViewHolder(globalFeeResourceView);
        }
        final ViewHolder viewHolder = new ViewHolder(from.inflate(R.layout.item_confirm_extra_clickable, viewGroup, false));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onCreateViewHolder$1(viewHolder, view);
            }
        });
        return viewHolder;
    }

    public void lambda$onCreateViewHolder$1(final ViewHolder viewHolder, View view) {
        if (viewHolder.isClickable) {
            this.expand = !this.expand;
            viewHolder.itemView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$onCreateViewHolder$0(viewHolder);
                }
            });
        }
    }

    public void lambda$onCreateViewHolder$0(ViewHolder viewHolder) {
        viewHolder.toggleArrow(this.expand);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        if (i >= getItemCount() || i < 0) {
            return;
        }
        if (baseViewHolder instanceof InnerRvHolder) {
            ((InnerRvHolder) baseViewHolder).onBind(this.unfreezeResources);
        } else if (baseViewHolder instanceof FooterViewHolder) {
            ((FooterViewHolder) baseViewHolder).onBind(this.param);
        } else if (baseViewHolder instanceof ViewHolder) {
            boolean z = true;
            try {
                ((ViewHolder) baseViewHolder).bind(this.mList.get(getDataPosition(i)), (this.unfreezeResources.size() <= 1 || baseViewHolder.getAbsoluteAdapterPosition() != getClickableIndex()) ? false : false);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    @Override
    public int getItemViewType(int i) {
        if (this.expand && i == getClickableIndex() + 1) {
            return 1;
        }
        return i == getItemCount() - 1 ? 2 : 0;
    }

    @Override
    public int getItemCount() {
        List<ConfirmExtraText> list = this.mList;
        if (list == null) {
            return 1;
        }
        return list.size() + getExtraItemCount();
    }

    private int getClickableIndex() {
        return this.releasedResourceTypeCount + (this.param.isActives() ? 1 : 0);
    }

    private int getDataPosition(int i) {
        return (!this.expand || i <= getClickableIndex() + 2) ? i : i - 1;
    }

    @Override
    public void onRefreshAccountComplete(boolean z, Protocol.Transaction transaction, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            RecyclerView.ViewHolder findViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(getItemCount() - 1);
            if (findViewHolderForAdapterPosition instanceof FooterViewHolder) {
                ((GlobalFeeResourceView) findViewHolderForAdapterPosition.itemView).onRefreshAccountComplete(z, transaction, pair);
            }
        }
    }

    public static class InnerRvHolder extends BaseViewHolder {
        private final InnerAdapter accountsAdapter;

        public InnerRvHolder(View view) {
            super(view);
            view.setBackgroundResource(R.drawable.roundborder_f5f6f7_10);
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(recyclerView.getContext()));
            InnerAdapter innerAdapter = new InnerAdapter();
            this.accountsAdapter = innerAdapter;
            recyclerView.setAdapter(innerAdapter);
        }

        void onBind(List<ConfirmExtraText> list) {
            this.accountsAdapter.setNewData(list);
        }
    }

    public static class InnerAdapter extends BaseQuickAdapter<ConfirmExtraText, InnerViewHolder> {
        public InnerAdapter() {
            super((int) R.layout.item_confirm_extra_inner_list);
        }

        @Override
        public void convert(InnerViewHolder innerViewHolder, ConfirmExtraText confirmExtraText) {
            innerViewHolder.onBind(confirmExtraText);
        }
    }

    public static class InnerViewHolder extends BaseViewHolder {
        ItemConfirmExtraInnerListBinding binding;
        private final TextView tvLeft;
        private final TextView tvRight;

        public InnerViewHolder(View view) {
            super(view);
            ItemConfirmExtraInnerListBinding bind = ItemConfirmExtraInnerListBinding.bind(view);
            this.binding = bind;
            this.tvLeft = bind.tvLeft;
            this.tvRight = this.binding.tvRight;
        }

        void onBind(ConfirmExtraText confirmExtraText) {
            String format = String.format("%d.%s", Integer.valueOf(getAbsoluteAdapterPosition() + 1), confirmExtraText.getLeft());
            int indexOf = format.indexOf("(");
            if (indexOf >= 0) {
                try {
                    EllipsizeTextViewUtils.ellipseAndSet(this.tvLeft, format, indexOf + 1);
                } catch (Exception e) {
                    this.tvLeft.setText(format);
                    LogUtils.e(e);
                }
            } else {
                this.tvLeft.setText(format);
            }
            this.tvRight.setText(confirmExtraText.getRight());
        }
    }

    public static class ViewHolder extends BaseViewHolder {
        ItemConfirmExtraClickableBinding binding;
        boolean isClickable;
        ImageView ivRight;
        TextView tvLeft;
        TextView tvRight;

        public ViewHolder(View view) {
            super(view);
            ItemConfirmExtraClickableBinding bind = ItemConfirmExtraClickableBinding.bind(view);
            this.binding = bind;
            this.tvLeft = bind.tvLeft;
            this.tvRight = this.binding.tvRight;
            this.ivRight = this.binding.ivArrowRight;
        }

        void toggleArrow(boolean z) {
            if (z) {
                this.ivRight.setImageResource(R.mipmap.ic_arrow_up);
            } else {
                this.ivRight.setImageResource(R.mipmap.ic_arrow_down);
            }
        }

        void bind(final ConfirmExtraText confirmExtraText, boolean z) {
            this.isClickable = z;
            this.ivRight.setVisibility(z ? View.VISIBLE : View.GONE);
            this.itemView.setClickable(z);
            this.tvLeft.setText(confirmExtraText.getLeft());
            this.itemView.post(new Runnable() {
                @Override
                public final void run() {
                    ConfirmUnfreezeContentAdapter.ViewHolder.this.lambda$bind$0(confirmExtraText);
                }
            });
        }

        public void lambda$bind$0(ConfirmExtraText confirmExtraText) {
            TextView textView = this.tvRight;
            textView.setText(EllipsizeTextViewUtils.ellipseNameOnly(textView, confirmExtraText.getRight(), StringUtils.LF));
        }
    }

    public static class FooterViewHolder extends BaseViewHolder {
        GlobalFeeResourceView feeView;
        boolean isLoaded;

        public FooterViewHolder(View view) {
            super(view);
            this.isLoaded = false;
            this.feeView = (GlobalFeeResourceView) view;
        }

        void onBind(BaseParam baseParam) {
            if ((baseParam instanceof FreezeUnFreezeParam) && !this.isLoaded) {
                try {
                    this.feeView.bindData(baseParam);
                    this.isLoaded = true;
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        }
    }
}
