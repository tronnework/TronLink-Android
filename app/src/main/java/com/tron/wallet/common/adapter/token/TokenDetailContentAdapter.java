package com.tron.wallet.common.adapter.token;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.LoadingView;
import com.tron.wallet.common.components.TransactionScamStatusImageView;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.tron.walletserver.Wallet;
public class TokenDetailContentAdapter extends BaseQuickAdapter<TransactionHistoryBean, ViewHolder> {
    private boolean flag;
    private int index;
    private String isTrx;
    private Context mContext;
    private HashMap<String, Boolean> mContractMap;
    private OnItemClickListener mItemClickListener;
    protected List<TransactionHistoryBean> mList;
    private TokenBean mToken;
    private int mTotal;
    private NumberFormat numberFormat;
    private final Wallet selectedWallet;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public void setContractMap(HashMap<String, Boolean> hashMap) {
        this.mContractMap = hashMap;
    }

    public void setmItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public TokenDetailContentAdapter(TokenBean tokenBean, List<TransactionHistoryBean> list, HashMap<String, Boolean> hashMap, int i, String str, Context context, int i2) {
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
        this.mContractMap = hashMap;
        setLoadMoreView(new CustomLoadMoreView());
    }

    public void notifyData(List<TransactionHistoryBean> list, int i, String str, int i2) {
        this.flag = true;
        this.mList = list;
        this.isTrx = str;
        this.index = i2;
        this.mTotal = i;
        setNewData(list);
        notifyDataSetChanged();
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

    public boolean isFromAdddressContract(TransactionHistoryBean transactionHistoryBean) {
        HashMap<String, Boolean> hashMap;
        return (transactionHistoryBean == null || transactionHistoryBean.from == null || (hashMap = this.mContractMap) == null || !hashMap.containsKey(transactionHistoryBean.from) || !this.mContractMap.get(transactionHistoryBean.from).booleanValue()) ? false : true;
    }

    public class ViewHolder extends BaseViewHolder {
        TextView address;
        TextView contractTag;
        TextView countTv;
        View innerLayout;
        TransactionScamStatusImageView ivStateScam;
        TextView leftBracket;
        LinearLayout liAddresses;
        View liPending;
        View llPending;
        LoadingView loadingView;
        TextView rightBracket;
        View rootLayout;
        TextView shortAddress;
        TextView time;
        TextView tvName;
        TextView tvPending;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        private void mappingId(View view) {
            this.liAddresses = (LinearLayout) view.findViewById(R.id.li_address);
            this.contractTag = (TextView) view.findViewById(R.id.tv_contract_tag);
            this.address = (TextView) view.findViewById(R.id.address);
            this.ivStateScam = (TransactionScamStatusImageView) view.findViewById(R.id.iv_status_scam);
            this.time = (TextView) view.findViewById(R.id.time);
            this.countTv = (TextView) view.findViewById(R.id.tv_count);
            this.rootLayout = view.findViewById(R.id.root);
            this.innerLayout = view.findViewById(R.id.rl_inner);
            this.tvPending = (TextView) view.findViewById(R.id.tv_pending);
            this.liPending = view.findViewById(R.id.li_pending);
            this.llPending = view.findViewById(R.id.ll_pending);
            this.loadingView = (LoadingView) view.findViewById(R.id.iv_pending);
            this.leftBracket = (TextView) view.findViewById(R.id.left_bracket);
            this.tvName = (TextView) view.findViewById(R.id.name);
            this.shortAddress = (TextView) view.findViewById(R.id.short_address);
            this.rightBracket = (TextView) view.findViewById(R.id.right_bracket);
        }

        public void bind(com.tron.wallet.common.bean.token.TransactionHistoryBean r21, final int r22) {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.adapter.token.TokenDetailContentAdapter.ViewHolder.bind(com.tron.wallet.common.bean.token.TransactionHistoryBean, int):void");
        }
    }

    public int getTextViewWidth(TextView textView) {
        return getTextViewWidth(textView, 4);
    }

    public int getTextViewWidth(TextView textView, int i) {
        return (int) (textView.getPaint().measureText(textView.getText().toString()) + textView.getPaddingLeft() + textView.getPaddingRight() + DensityUtils.dp2px(this.mContext, i));
    }

    public float setTextWithExspiredWidth(TextView textView, String str, int i) {
        if (textView == null || str == null || str.length() == 0) {
            return 0.0f;
        }
        TextPaint paint = textView.getPaint();
        float f = i;
        if (paint.measureText(str) < f) {
            textView.setText(str);
            return paint.measureText(str);
        }
        for (int length = str.length() / 2; length > 0; length--) {
            String indentAddress = StringTronUtil.indentAddress(str, length);
            if (paint.measureText(indentAddress) < f) {
                textView.setText(indentAddress);
                return paint.measureText(indentAddress);
            }
        }
        textView.setText(str);
        return paint.measureText(str);
    }

    public String getDisPlayName(NameAddressExtraBean nameAddressExtraBean) {
        if (nameAddressExtraBean != null) {
            if (nameAddressExtraBean.getName().length() > 7) {
                if (nameAddressExtraBean.getName().length() > 16) {
                    return StringTronUtil.indentAddress(nameAddressExtraBean.getName().toString(), 6);
                }
                return nameAddressExtraBean.getName().toString();
            }
            return nameAddressExtraBean.getName().toString();
        }
        return "";
    }

    private boolean isNickName(String str) {
        char[] charArray;
        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (c >= 19968 && c <= 40869) {
                return true;
            }
        }
        return false;
    }

    public String getDisPlayAddress(NameAddressExtraBean nameAddressExtraBean) {
        return nameAddressExtraBean != null ? StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) : "";
    }

    public boolean isUnlimited(String str, TokenBean tokenBean) {
        return BigDecimalUtils.MoreThan(new BigDecimal(str), new BigDecimal(Math.pow(10.0d, (tokenBean != null ? tokenBean.getPrecision() : 0) + 18)));
    }
}
