package com.tron.wallet.common.adapter.token;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class TokenListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private int selectPosition;
    private List<TokenBean> tokenBeans;

    public TokenListAdapter(Context context, List<TokenBean> list, int i) {
        this.tokenBeans = list;
        this.mContext = context;
        this.selectPosition = i;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_token_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String str;
        if (this.tokenBeans.get(i).getId() == null || this.tokenBeans.get(i).getId().isEmpty()) {
            str = "";
        } else {
            str = "(" + this.tokenBeans.get(i).getId() + ")";
        }
        if (this.tokenBeans.get(i).getType() == 2) {
            String str2 = this.tokenBeans.get(i).contractAddress;
            str = "(" + str2.substring(0, 4) + "****" + str2.substring(str2.length() - 4, str2.length()) + ")";
        }
        viewHolder.tokenId.setText(str);
        String str3 = "TRX";
        if (this.tokenBeans.get(i).getName() != null) {
            TextView textView = viewHolder.tokenName;
            if (this.tokenBeans.get(i).getType() != 0) {
                str3 = this.tokenBeans.get(i).getShortName().equals("") ? this.tokenBeans.get(i).getName() : this.tokenBeans.get(i).getShortName();
            }
            textView.setText(str3);
        } else {
            viewHolder.tokenName.setText("TRX");
        }
        if (i == this.selectPosition) {
            viewHolder.tokenName.setTextColor(this.mContext.getResources().getColor(R.color.blue_08));
            viewHolder.tokenId.setTextColor(this.mContext.getResources().getColor(R.color.blue_08));
            return;
        }
        viewHolder.tokenName.setTextColor(this.mContext.getResources().getColor(R.color.black_02));
        viewHolder.tokenId.setTextColor(this.mContext.getResources().getColor(R.color.gray_99));
    }

    @Override
    public int getItemCount() {
        List<TokenBean> list = this.tokenBeans;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void notifyData(List<TokenBean> list, int i) {
        this.tokenBeans = list;
        this.selectPosition = i;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends BaseHolder {
        TextView tokenId;
        TextView tokenName;

        private void mappingId(View view) {
            this.tokenName = (TextView) view.findViewById(R.id.token_name);
            this.tokenId = (TextView) view.findViewById(R.id.token_id);
        }

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }
    }
}
