package com.tron.wallet.common.adapter;

import android.widget.Filter;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class AdapterFilter extends Filter {
    private final RecyclerView.Adapter adapter;
    private final List<WitnessOutput.DataBean> backupData;
    private final List<WitnessOutput.DataBean> showData;

    public AdapterFilter(RecyclerView.Adapter adapter, List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2) {
        ArrayList arrayList = new ArrayList();
        this.backupData = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.showData = arrayList2;
        this.adapter = adapter;
        arrayList.addAll(list);
        arrayList2.addAll(list2);
    }

    @Override
    protected Filter.FilterResults performFiltering(CharSequence charSequence) {
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (charSequence == null || charSequence.length() == 0) {
            ArrayList arrayList = new ArrayList(this.backupData);
            filterResults.values = arrayList;
            filterResults.count = arrayList.size();
        } else {
            this.showData.clear();
            for (WitnessOutput.DataBean dataBean : this.backupData) {
                if (filtered(dataBean, charSequence)) {
                    this.showData.add(dataBean);
                }
            }
            ArrayList arrayList2 = new ArrayList(this.showData);
            filterResults.values = arrayList2;
            filterResults.count = arrayList2.size();
        }
        return filterResults;
    }

    private boolean filtered(WitnessOutput.DataBean dataBean, CharSequence charSequence) {
        boolean z = true;
        boolean z2 = dataBean.getName() != null && (dataBean.getName().contains(charSequence) || dataBean.getName().contains(charSequence.toString().toLowerCase()) || dataBean.getName().contains(charSequence.toString().toUpperCase()));
        if (z2 || dataBean.getUrl() == null) {
            return z2;
        }
        if (!dataBean.getUrl().contains(charSequence) && !dataBean.getUrl().contains(charSequence.toString().toLowerCase()) && !dataBean.getUrl().contains(charSequence.toString().toUpperCase())) {
            z = false;
        }
        return z;
    }

    @Override
    protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        this.showData.clear();
        this.showData.addAll((Collection) filterResults.values);
    }
}
