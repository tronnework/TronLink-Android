package com.tron.wallet.common.adapter.proposal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.tabmy.proposals.bean.MakeProposalsBean;
import com.tron.wallet.common.adapter.holder.ProposalsEditViewHolder;
import com.tron.wallet.common.adapter.holder.ProposalsSelectViewHolder;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
public class MakeProposalsAdapter extends RecyclerView.Adapter<BaseHolder> {
    private static final int TYPE_EDIT = 0;
    private static final int TYPE_SELECT = 1;
    private List<MakeProposalsBean> beansList;
    private List<Long> errorProposalIds;
    private boolean mCanEdit;
    private Context mContext;
    private ParamerChangeListener mParamerListener;
    private HashMap<Long, Long> mParameters;

    public interface ParamerChangeListener {
        void onErrorTipShow(List<Long> list);

        void onParamerChange(HashMap<Long, Long> hashMap);
    }

    public MakeProposalsAdapter(Context context, List<MakeProposalsBean> list, HashMap<Long, Long> hashMap, ParamerChangeListener paramerChangeListener, List<Long> list2, boolean z) {
        this.mContext = context;
        this.beansList = filterData(list);
        this.mParameters = hashMap;
        this.mParamerListener = paramerChangeListener;
        this.errorProposalIds = list2;
        this.mCanEdit = z;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            return new ProposalsSelectViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_make_proposals_select, (ViewGroup) null), this.mContext, this.mParameters, this.mParamerListener, this.mCanEdit);
        }
        return new ProposalsEditViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_make_proposals, (ViewGroup) null), this.mContext, this.mParameters, this.mParamerListener, this.errorProposalIds, this.mCanEdit);
    }

    @Override
    public void onBindViewHolder(BaseHolder baseHolder, int i) {
        baseHolder.setIsRecyclable(false);
        if (baseHolder != null) {
            MakeProposalsBean makeProposalsBean = this.beansList.get(i);
            if (baseHolder instanceof ProposalsEditViewHolder) {
                ((ProposalsEditViewHolder) baseHolder).bind(makeProposalsBean, i);
            } else if (baseHolder instanceof ProposalsSelectViewHolder) {
                ((ProposalsSelectViewHolder) baseHolder).bind(makeProposalsBean, i);
            }
        }
    }

    @Override
    public int getItemViewType(int i) {
        int i2 = this.beansList.get(i).type;
        if (i2 != 0) {
            if (i2 != 1) {
                return super.getItemViewType(i);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        List<MakeProposalsBean> list = this.beansList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void notify(List<MakeProposalsBean> list) {
        this.beansList = filterData(list);
        notifyDataSetChanged();
    }

    private List<MakeProposalsBean> filterData(List<MakeProposalsBean> list) {
        return (List) Collection.-EL.stream(list).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                return MakeProposalsAdapter.lambda$filterData$0((MakeProposalsBean) obj);
            }
        }).collect(Collectors.toList());
    }

    public static boolean lambda$filterData$0(MakeProposalsBean makeProposalsBean) {
        return makeProposalsBean.type == 0 || makeProposalsBean.selectValue == 0;
    }
}
