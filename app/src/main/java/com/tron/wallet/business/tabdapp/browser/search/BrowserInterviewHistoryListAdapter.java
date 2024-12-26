package com.tron.wallet.business.tabdapp.browser.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.tabdapp.browser.bean.DAppVisitHistoryBean;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.ItemInterviewHistoryListBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class BrowserInterviewHistoryListAdapter extends RecyclerView.Adapter<BaseHolder> {
    private Context context;
    public ItemCallback itemCallback;
    private int[] touchLocation = new int[2];
    protected List<DAppVisitHistoryBean> historyBeanList = new ArrayList();

    public interface ItemCallback {
        void deleteItem(DAppVisitHistoryBean dAppVisitHistoryBean, int i);

        void onItemClicked(DAppVisitHistoryBean dAppVisitHistoryBean, int i);
    }

    public List<DAppVisitHistoryBean> getHistoryBeanList() {
        return this.historyBeanList;
    }

    public BrowserInterviewHistoryListAdapter(Context context, ItemCallback itemCallback) {
        this.context = context;
        this.itemCallback = itemCallback;
    }

    public void notifyDataChanged(List<DAppVisitHistoryBean> list) {
        this.historyBeanList = list;
        notifyDataSetChanged();
    }

    public void notifyDataRemoved(DAppVisitHistoryBean dAppVisitHistoryBean, int i) {
        this.historyBeanList.remove(dAppVisitHistoryBean);
        notifyItemRemoved(i);
    }

    public void notifyDataChanged(DAppVisitHistoryBean dAppVisitHistoryBean, int i) {
        notifyItemChanged(i);
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new InterviewHistoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_interview_history_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final BaseHolder baseHolder, int i) {
        InterviewHistoryViewHolder interviewHistoryViewHolder = (InterviewHistoryViewHolder) baseHolder;
        interviewHistoryViewHolder.onBind(this.context, this.historyBeanList.get(i));
        baseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onBindViewHolder$0(baseHolder, view);
            }
        });
        baseHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$onBindViewHolder$1;
                lambda$onBindViewHolder$1 = lambda$onBindViewHolder$1(view, motionEvent);
                return lambda$onBindViewHolder$1;
            }
        });
        interviewHistoryViewHolder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCallback.deleteItem(historyBeanList.get(baseHolder.getAdapterPosition()), baseHolder.getAdapterPosition());
            }
        });
    }

    public void lambda$onBindViewHolder$0(BaseHolder baseHolder, View view) {
        ItemCallback itemCallback = this.itemCallback;
        if (itemCallback != null) {
            itemCallback.onItemClicked(this.historyBeanList.get(baseHolder.getAdapterPosition()), baseHolder.getAdapterPosition());
        }
    }

    public boolean lambda$onBindViewHolder$1(View view, MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 0) {
            this.touchLocation[0] = (int) motionEvent.getX();
            this.touchLocation[1] = (int) motionEvent.getY();
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return this.historyBeanList.size();
    }

    public static class InterviewHistoryViewHolder extends BaseHolder {
        private ItemInterviewHistoryListBinding binding;
        ImageView deleteIv;
        TokenLogoDraweeView logoIv;
        TextView tvName;
        TextView tvUrl;

        public InterviewHistoryViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemInterviewHistoryListBinding bind = ItemInterviewHistoryListBinding.bind(view);
            this.logoIv = bind.ivLogo;
            this.tvName = bind.name;
            this.tvUrl = bind.url;
            this.deleteIv = bind.ivDeleteView;
        }

        protected void setupToolView(ImageView imageView, DAppVisitHistoryBean dAppVisitHistoryBean) {
            TouchDelegateUtils.expandViewTouchDelegate(imageView, 20, 20, 20, 20);
        }

        public void onBind(Context context, DAppVisitHistoryBean dAppVisitHistoryBean) {
            if (StringTronUtil.isEmpty(dAppVisitHistoryBean.getTitle())) {
                this.tvUrl.setVisibility(View.GONE);
                this.tvName.setText(dAppVisitHistoryBean.getUrl());
            } else {
                this.tvName.setText(dAppVisitHistoryBean.getTitle());
                this.tvUrl.setText(dAppVisitHistoryBean.getUrl());
            }
            this.logoIv.setImageURI(dAppVisitHistoryBean.getIcon());
            setupToolView(this.deleteIv, dAppVisitHistoryBean);
        }
    }
}
