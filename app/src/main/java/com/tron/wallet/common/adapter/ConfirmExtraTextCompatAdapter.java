package com.tron.wallet.common.adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.common.adapter.ConfirmExtraTextCompatAdapter;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.utils.EllipsizeTextViewUtils;
import com.tron.wallet.databinding.ItemConfirmExtraClickableBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class ConfirmExtraTextCompatAdapter extends RecyclerView.Adapter<BaseHolder> implements BaseConfirmFragment.AccountCallback {
    private final GlobalFeeResourceView.FeeResourceCallback feeCallback;
    private List<ConfirmExtraText> list;
    private BaseParam param;
    private final RecyclerView recyclerView;

    private interface TYPE {
        public static final int FOOTER = 1;
        public static final int NORMAL = 0;
    }

    public ConfirmExtraTextCompatAdapter(RecyclerView recyclerView) {
        this.feeCallback = null;
        this.recyclerView = recyclerView;
    }

    public ConfirmExtraTextCompatAdapter(RecyclerView recyclerView, GlobalFeeResourceView.FeeResourceCallback feeResourceCallback) {
        this.feeCallback = feeResourceCallback;
        this.recyclerView = recyclerView;
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

    public void update(BaseParam baseParam, List<ConfirmExtraText> list) {
        this.param = baseParam;
        this.list = list;
        if (list == null) {
            this.list = new ArrayList();
        }
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_confirm_extra_clickable, viewGroup, false));
        }
        GlobalFeeResourceView globalFeeResourceView = new GlobalFeeResourceView(viewGroup.getContext());
        globalFeeResourceView.setFeeResourceCallback(this.feeCallback);
        return new FooterViewHolder(globalFeeResourceView);
    }

    @Override
    public void onBindViewHolder(BaseHolder baseHolder, int i) {
        try {
            if (baseHolder instanceof ViewHolder) {
                ((ViewHolder) baseHolder).bind(this.list.get(i));
            } else if (baseHolder instanceof FooterViewHolder) {
                ((FooterViewHolder) baseHolder).bind(this.param);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public int getItemCount() {
        if (this.feeCallback == null) {
            return this.list.size();
        }
        return this.list.size() + 1;
    }

    @Override
    public int getItemViewType(int i) {
        return i >= this.list.size() ? 1 : 0;
    }

    public static class FooterViewHolder extends BaseHolder {
        public FooterViewHolder(View view) {
            super(view);
        }

        void bind(BaseParam baseParam) {
            if (baseParam == null) {
                return;
            }
            try {
                ((GlobalFeeResourceView) this.itemView).bindData(baseParam);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public static class ViewHolder extends BaseHolder {
        ItemConfirmExtraClickableBinding binding;
        TextView tvLeft;
        TextView tvRight;

        public ViewHolder(View view) {
            super(view);
            ItemConfirmExtraClickableBinding bind = ItemConfirmExtraClickableBinding.bind(view);
            this.binding = bind;
            this.tvLeft = bind.tvLeft;
            this.tvRight = this.binding.tvRight;
        }

        void bind(final ConfirmExtraText confirmExtraText) {
            this.tvLeft.setText(confirmExtraText.getLeft());
            this.itemView.post(new Runnable() {
                @Override
                public final void run() {
                    ConfirmExtraTextCompatAdapter.ViewHolder.this.lambda$bind$0(confirmExtraText);
                }
            });
        }

        public void lambda$bind$0(ConfirmExtraText confirmExtraText) {
            TextView textView = this.tvRight;
            textView.setText(EllipsizeTextViewUtils.ellipseNameOnly(textView, confirmExtraText.getRight(), StringUtils.LF));
        }
    }
}
