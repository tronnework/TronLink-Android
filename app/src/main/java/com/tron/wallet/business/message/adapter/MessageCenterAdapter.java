package com.tron.wallet.business.message.adapter;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class MessageCenterAdapter extends BaseQuickAdapter<TransactionMessage, MessageViewHolder> {
    private List<TransactionMessage> data;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MessageCenterAdapter(Context context, List<TransactionMessage> list) {
        super(R.layout.item_message_center, list);
        this.mContext = context;
        this.data = list;
        setLoadMoreView(new CustomLoadMoreView());
    }

    @Override
    public void bindToRecyclerView(RecyclerView recyclerView) {
        super.bindToRecyclerView(recyclerView);
        disableLoadMoreIfNotFullPage(recyclerView);
    }

    @Override
    public void convert(MessageViewHolder messageViewHolder, TransactionMessage transactionMessage) {
        messageViewHolder.bindHolder(this.mContext, transactionMessage, this.mOnItemClickListener);
    }
}
