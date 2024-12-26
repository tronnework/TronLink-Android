package com.tron.wallet.business.nft;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.LoadingView;
import com.tron.wallet.databinding.ItemTrxTransferBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.tron.walletserver.Wallet;
public class NftHistoryAdapter extends BaseQuickAdapter<TransactionHistoryBean, ViewHolder> {
    private boolean flag;
    private int index;
    private String isTrx;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    protected List<TransactionHistoryBean> mList;
    private TokenBean mToken;
    private int mTotal;
    private NumberFormat numberFormat;
    private final Wallet selectedWallet;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public void setmItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public NftHistoryAdapter(TokenBean tokenBean, List<TransactionHistoryBean> list, int i, String str, Context context, int i2) {
        super(R.layout.item_trx_transfer, list);
        this.flag = false;
        this.mList = list;
        this.mContext = context;
        this.selectedWallet = WalletUtils.getSelectedWallet();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
        this.index = i2;
        this.isTrx = str;
        this.mTotal = i;
        this.mToken = tokenBean;
        setLoadMoreView(new CustomLoadMoreView());
    }

    public void notifyData(List<TransactionHistoryBean> list, int i, String str, int i2) {
        this.flag = true;
        this.mList = list;
        this.isTrx = str;
        this.index = i2;
        this.mTotal = i;
        setNewData(list);
    }

    @Override
    public void bindToRecyclerView(RecyclerView recyclerView) {
        super.bindToRecyclerView(recyclerView);
        disableLoadMoreIfNotFullPage(recyclerView);
    }

    @Override
    public void convert(ViewHolder viewHolder, TransactionHistoryBean transactionHistoryBean) {
        viewHolder.bind(transactionHistoryBean, viewHolder.getAdapterPosition());
    }

    public class ViewHolder extends BaseViewHolder {
        TextView address;
        private ItemTrxTransferBinding binding;
        TextView countTv;
        View innerLayout;
        View llPending;
        LoadingView loadingView;
        View rootLayout;
        TextView time;
        TextView tvPending;

        public ViewHolder(View view) {
            super(view);
            ItemTrxTransferBinding bind = ItemTrxTransferBinding.bind(view);
            this.binding = bind;
            this.address = bind.address;
            this.time = this.binding.time;
            this.countTv = this.binding.tvCount;
            this.rootLayout = this.binding.root;
            this.innerLayout = this.binding.rlInner;
            this.tvPending = this.binding.tvPending;
            this.llPending = this.binding.llPending;
            this.loadingView = this.binding.ivPending;
        }

        public void bind(com.tron.wallet.common.bean.token.TransactionHistoryBean r17, final int r18) {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.nft.NftHistoryAdapter.ViewHolder.bind(com.tron.wallet.common.bean.token.TransactionHistoryBean, int):void");
        }
    }
}
