package com.tron.wallet.common.adapter.proposal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.ProposalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
public class ProposalContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final int TYPE_PROPOSAL_DETAIL = 2;
    public static final int TYPE_PROPOSAL_LIST = 1;
    private final HashMap<Long, String> contentMap = new HashMap<>();
    private DecimalFormat decimalFormat;
    private Context mContext;
    private List<Long> mapKeyList;
    private List<Long> mapValueList;
    private final NumberFormat numberFormat;
    private Map<Long, Long> paramtersList;
    private int type_from;

    public HashMap<Long, String> getContentMap() {
        return this.contentMap;
    }

    public ProposalContentAdapter(Context context, Map<Long, Long> map, int i) {
        this.mContext = context;
        this.paramtersList = map;
        this.type_from = i;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(0);
        this.mapKeyList = new ArrayList(map.keySet());
        this.mapValueList = new ArrayList(map.values());
        this.decimalFormat = new DecimalFormat("#.######");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int i2 = this.type_from;
        if (i2 == 1) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_proposal_content, viewGroup, false));
        }
        if (i2 == 2) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_proposal_detail, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder != null) {
            try {
                Map<Long, Long> map = this.paramtersList;
                if (map != null && map.size() > 0) {
                    long longValue = this.mapKeyList.get(i).longValue();
                    String proposalContent = ProposalUtils.getProposalContent(longValue, this.mapValueList.get(i).longValue());
                    this.contentMap.put(Long.valueOf(longValue), proposalContent);
                    if (!StringTronUtil.isEmpty(proposalContent)) {
                        viewHolder.tvCommitteeContent.setVisibility(View.VISIBLE);
                        viewHolder.tvCommitteeContent.setText(proposalContent);
                    } else {
                        viewHolder.tvCommitteeContent.setVisibility(View.GONE);
                    }
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    @Override
    public int getItemCount() {
        List<Long> list = this.mapKeyList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void notify(Map<Long, Long> map) {
        this.paramtersList = map;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseHolder {
        TextView tvCommitteeContent;

        public ViewHolder(View view) {
            super(view);
            this.tvCommitteeContent = (TextView) view.findViewById(R.id.tv_committee_content);
        }
    }
}
