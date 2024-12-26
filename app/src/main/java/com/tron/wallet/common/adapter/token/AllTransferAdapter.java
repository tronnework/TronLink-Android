package com.tron.wallet.common.adapter.token;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.AllTransferOutput;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.AssetIssueContractDao;
import com.tron.wallet.db.dao.DaoUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
public class AllTransferAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<AllTransferOutput.DataBean> mLists;
    private final NumberFormat numberFormat;

    public AllTransferAdapter(List<AllTransferOutput.DataBean> list, Context context) {
        this.mLists = list;
        this.mContext = context;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_alltransfer, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.bind(i, this.mLists.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str;
                String str2;
                if (SpAPI.THIS.getCurrentChain() != null && SpAPI.THIS.getCurrentChain().isMainChain) {
                    str = TronConfig.TRONSCANHOST_MAINCHAIN + ((AllTransferOutput.DataBean) mLists.get(i)).hash;
                } else {
                    str = TronConfig.TRONSCANHOST_DAPPCHAIN + ((AllTransferOutput.DataBean) mLists.get(i)).hash;
                }
                if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
                    str2 = str + "?lang=zh";
                } else {
                    str2 = str + "?lang=en";
                }
                CommonWebActivityV3.start(mContext, str2, "2".equals(SpAPI.THIS.useLanguage()) ? StringTronUtil.getResString(R.string.transfer_doc) : "Transaction Details", -2, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        List<AllTransferOutput.DataBean> list = this.mLists;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void notifyData(List<AllTransferOutput.DataBean> list) {
        this.mLists = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseHolder {
        RelativeLayout rootView;
        TextView tvAddress;
        TextView tvConfirm;
        TextView tvContractTitle;
        TextView tvOne;
        TextView tvSubOne;
        TextView tvThree;
        TextView tvTime;
        TextView tvTwo;
        TextView tvTwoSub;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        private void mappingId(View view) {
            this.tvAddress = (TextView) view.findViewById(R.id.tv_address);
            this.tvConfirm = (TextView) view.findViewById(R.id.confirm);
            this.tvTime = (TextView) view.findViewById(R.id.tv_time);
            this.tvContractTitle = (TextView) view.findViewById(R.id.tv_contract_title);
            this.tvOne = (TextView) view.findViewById(R.id.tv_one);
            this.tvTwo = (TextView) view.findViewById(R.id.tv_two);
            this.tvThree = (TextView) view.findViewById(R.id.tv_three);
            this.rootView = (RelativeLayout) view.findViewById(R.id.root_view);
            this.tvSubOne = (TextView) view.findViewById(R.id.tv_one_sub);
            this.tvTwoSub = (TextView) view.findViewById(R.id.tv_two_sub);
        }

        public void bind(int r21, com.tron.wallet.common.bean.AllTransferOutput.DataBean r22) {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.adapter.token.AllTransferAdapter.ViewHolder.bind(int, com.tron.wallet.common.bean.AllTransferOutput$DataBean):void");
        }
    }

    public int getPrecision(String str) {
        AssetIssueContractDao assetIssueContractDao;
        if (TextUtils.isEmpty(str) || (assetIssueContractDao = (AssetIssueContractDao) DaoUtils.getDicInstance().QueryById(Long.parseLong(str), AssetIssueContractDao.class)) == null) {
            return 0;
        }
        return assetIssueContractDao.getPrecision();
    }
}
