package com.tron.wallet.business.walletmanager.relation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemRelationSelectBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class RelationAccountAdapter extends BaseQuickAdapter<Wallet, BaseViewHolder> {
    private Map<String, AccountBalanceOutput.DataBean.BalanceListBean> balanceList;
    Wallet currentData;
    private List<Wallet> datas;
    Context mContext;
    private OnItemClickListener mOnItemClickLitener;
    private final NumberFormat numberFormat;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public void setOnItemClickLitener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickLitener = onItemClickListener;
    }

    public RelationAccountAdapter(Context context, List<Wallet> list, Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map, Wallet wallet) {
        super(list);
        this.datas = list;
        this.currentData = wallet;
        this.balanceList = map;
        this.mContext = context;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
    }

    public void notifyData(List<Wallet> list, Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map) {
        this.datas = list;
        this.balanceList = map;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return new SingleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_relation_select, viewGroup, false));
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, Wallet wallet) {
        if (baseViewHolder instanceof SingleViewHolder) {
            final SingleViewHolder singleViewHolder = (SingleViewHolder) baseViewHolder;
            singleViewHolder.walletName.setText(wallet.getWalletName());
            WalletPath mnemonicPath = WalletUtils.getMnemonicPath(wallet.getMnemonicPathString(), wallet.getWalletName());
            TextView textView = singleViewHolder.index;
            textView.setText("Index " + mnemonicPath.accountIndex);
            singleViewHolder.address.setText(wallet.getAddress());
            if (mnemonicPath.account != 0 || mnemonicPath.change != 0) {
                singleViewHolder.tvPath.setText(WalletPath.buildPathString(mnemonicPath));
                singleViewHolder.tvPath.setVisibility(View.VISIBLE);
            } else {
                singleViewHolder.tvPath.setVisibility(View.GONE);
            }
            Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map = this.balanceList;
            if (map != null && map.get(wallet.getAddress()) != null) {
                TextView textView2 = singleViewHolder.balance;
                textView2.setText(StringTronUtil.formatNumber3Truncate(Double.valueOf(this.balanceList.get(wallet.getAddress()).getBalance())) + " TRX");
            }
            boolean equals = StringTronUtil.equals(wallet.getWalletName(), this.currentData.getWalletName());
            singleViewHolder.llRoot.setSelected(equals);
            singleViewHolder.icItemBack.setVisibility(equals ? View.VISIBLE : View.GONE);
            if (this.mOnItemClickLitener != null) {
                singleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (singleViewHolder.getAdapterPosition() <= -1 || datas == null || singleViewHolder.getAdapterPosition() >= datas.size()) {
                            return;
                        }
                        mOnItemClickLitener.onItemClick(singleViewHolder.itemView, singleViewHolder.getAdapterPosition());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.datas.size();
    }

    public class SingleViewHolder extends BaseViewHolder {
        TextView address;
        TextView balance;
        View icItemBack;
        RelativeLayout iconLayout;
        TextView index;
        ImageView ivCopy;
        View ivSelected;
        View llRoot;
        TextView tvPath;
        TextView walletName;

        public SingleViewHolder(View view) {
            super(view);
            ItemRelationSelectBinding bind = ItemRelationSelectBinding.bind(view);
            this.llRoot = bind.itemLayout;
            this.icItemBack = bind.icItemBack;
            this.walletName = bind.tvWalletName;
            this.iconLayout = bind.walletIcon;
            this.ivSelected = bind.assetStatus;
            this.balance = bind.tvBalance;
            this.address = bind.tvAddress;
            this.index = bind.tvIndex;
            this.ivCopy = bind.ivCopy;
            this.tvPath = bind.path;
            int dip2px = UIUtils.dip2px(20.0f);
            TouchDelegateUtils.expandViewTouchDelegate(this.ivCopy, dip2px, dip2px, dip2px, dip2px);
            addOnClickListener(R.id.iv_copy);
        }
    }
}
