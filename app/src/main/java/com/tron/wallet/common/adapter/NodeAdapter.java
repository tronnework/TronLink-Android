package com.tron.wallet.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeBean;
import com.tron.wallet.common.adapter.holder.NodeHolder;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class NodeAdapter extends RecyclerView.Adapter<NodeHolder> {
    private Context mContext;
    private ArrayList<NodeBean> nodeList;
    private OnNodeSelectedListener onNodeSelectedListener;

    public interface OnNodeSelectedListener {
        void onNodeEdit(NodeBean nodeBean);

        void onNodeSelected(NodeBean nodeBean);
    }

    public NodeAdapter(Context context, List<NodeBean> list, OnNodeSelectedListener onNodeSelectedListener) {
        this.nodeList = new ArrayList<>();
        this.mContext = context;
        this.nodeList = (ArrayList) list;
        this.onNodeSelectedListener = onNodeSelectedListener;
    }

    @Override
    public NodeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new NodeHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_node, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(NodeHolder nodeHolder, int i) {
        NodeBean nodeBean = this.nodeList.get(i);
        if (nodeBean == null || nodeHolder == null) {
            return;
        }
        nodeHolder.bindHolder(this.mContext, nodeBean, this.nodeList, i, this.onNodeSelectedListener);
    }

    @Override
    public int getItemCount() {
        ArrayList<NodeBean> arrayList = this.nodeList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }
}
