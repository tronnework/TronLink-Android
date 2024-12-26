package com.tron.wallet.business.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.common.adapter.holder.MessageHolder;
import com.tron.wallet.common.bean.MessageBean;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {
    private Context mContext;
    private OnItemClickListener mItemClickListener = null;
    private String mWalletName;
    private List<MessageBean> messageBeanList;
    private int state;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public void setmItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public MessageAdapter(Context context, List<MessageBean> list, int i, String str) {
        this.mContext = context;
        this.messageBeanList = list;
        this.state = i;
        this.mWalletName = str;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final MessageHolder messageHolder = new MessageHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message, viewGroup, false));
        messageHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(view, messageHolder.getAdapterPosition());
            }
        });
        return messageHolder;
    }

    @Override
    public void onBindViewHolder(MessageHolder messageHolder, int i) {
        MessageBean messageBean = this.messageBeanList.get(i);
        if (messageBean != null) {
            messageHolder.bindHolder(this.mContext, messageBean, this.state, this.mWalletName, i, this.messageBeanList.size());
        }
    }

    @Override
    public int getItemCount() {
        List<MessageBean> list = this.messageBeanList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void notifyData(List<MessageBean> list) {
        this.messageBeanList = list;
        notifyDataSetChanged();
    }
}
