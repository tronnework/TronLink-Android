package com.tron.wallet.business.confirm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.databinding.ItemPermissionListBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.HashMap;
import org.tron.protos.Protocol;
public class PermissionListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "PermissionListAdapter";
    private OnItemClickListener itemClickListener;
    private String mCurWalletAddress = WalletUtils.getSelectedWallet().address;
    private HashMap<String, Boolean> mPermissionEnableMap;
    private ArrayList<Protocol.Permission> mPermissionList;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public PermissionListAdapter(ArrayList<Protocol.Permission> arrayList, HashMap<String, Boolean> hashMap) {
        this.mPermissionList = arrayList;
        this.mPermissionEnableMap = hashMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_permission_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Protocol.Permission permission = this.mPermissionList.get(i);
        viewHolder.tokenName.setText(permission.getPermissionName());
        if (permission.getKeysList() != null) {
            boolean booleanValue = this.mPermissionEnableMap.get(String.valueOf(permission.getId())).booleanValue();
            LogUtils.d(TAG, "inPermissionGroup:" + booleanValue + "  mCurWalletAddress:" + this.mCurWalletAddress);
            if (booleanValue) {
                viewHolder.itemView.setClickable(true);
                viewHolder.tokenName.setTextColor(viewHolder.tokenName.getContext().getResources().getColor(R.color.gray_02));
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemClickListener.onItemClick(view, i);
                    }
                });
                return;
            }
            viewHolder.itemView.setClickable(false);
            viewHolder.tokenName.setTextColor(viewHolder.tokenName.getContext().getResources().getColor(R.color.gray_02_50));
            viewHolder.itemView.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return this.mPermissionList.size();
    }

    public static class ViewHolder extends BaseHolder {
        private ItemPermissionListBinding binding;
        TextView tokenName;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemPermissionListBinding bind = ItemPermissionListBinding.bind(view);
            this.binding = bind;
            this.tokenName = bind.tvPermissionName;
        }
    }
}
