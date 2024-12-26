package com.tron.wallet.business.confirm.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.StakeAndVoteParam;
import com.tron.wallet.common.adapter.ConfirmExtraTextCompatAdapter;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgConfirmStakeVoteBinding;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
public class ConfirmStakeAndVoteFragment extends BaseConfirmFragment<EmptyPresenter, EmptyModel> {
    private FgConfirmStakeVoteBinding binding;
    GlobalConfirmButton btnConfirm;
    private SimpleTextAdapter confirmExtraTextAdapter;
    GlobalTitleHeaderView headerView;
    private ConfirmExtraTextCompatAdapter infoListAdapter;
    ImageView ivArrow;
    View llArrow;
    private Animation loadingAnimation;
    private boolean needShowSrList;
    private NumberFormat numberFormat;
    private StakeAndVoteParam param;
    GlobalFeeResourceView resourceView;
    View rootLayout;
    RecyclerView rvExtras;
    RecyclerView rvInfo;
    TextView tvTip;
    TextView tvVoteSr;
    private HashMap<String, String> votedAddressMap;
    private HashMap<String, String> votedNameMap;

    public static ConfirmStakeAndVoteFragment getInstance(BaseParam baseParam) {
        ConfirmStakeAndVoteFragment confirmStakeAndVoteFragment = new ConfirmStakeAndVoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        confirmStakeAndVoteFragment.setArguments(bundle);
        return confirmStakeAndVoteFragment;
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgConfirmStakeVoteBinding inflate = FgConfirmStakeVoteBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.rvInfo = this.binding.rvInfo;
        this.tvVoteSr = this.binding.tvVoteSr;
        this.llArrow = this.binding.llArrow;
        this.ivArrow = this.binding.ivArrowRight;
        this.rvExtras = this.binding.rvExtras;
        this.headerView = this.binding.headerView;
        this.resourceView = this.binding.resourceView;
        this.rootLayout = this.binding.root;
        this.tvTip = this.binding.tvTip;
        this.btnConfirm = this.binding.buttonConfirm;
    }

    @Override
    public void processData() {
        super.processData();
        if (this.baseParam == null || !(this.baseParam instanceof StakeAndVoteParam)) {
            return;
        }
        this.param = (StakeAndVoteParam) this.baseParam;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(0);
        HashMap<String, String> srcOrderedVoteMap = this.param.getVoteBean().getSrcOrderedVoteMap();
        this.votedAddressMap = srcOrderedVoteMap;
        if (srcOrderedVoteMap == null) {
            this.votedAddressMap = new HashMap<>();
        }
        HashMap<String, String> srNameMap = this.param.getVoteBean().getSrNameMap();
        this.votedNameMap = srNameMap;
        if (srNameMap == null) {
            this.votedNameMap = new HashMap<>();
        }
        try {
            bindDataToUI();
            addAccountCallback(this.headerView, this.resourceView, this.btnConfirm);
            ensureAccount();
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    private void bindDataToUI() {
        ViewGroup.LayoutParams layoutParams = this.rootLayout.getLayoutParams();
        layoutParams.height = this.param.getLayoutHeight();
        this.rootLayout.setLayoutParams(layoutParams);
        this.headerView.bindData(this.param);
        List<ConfirmExtraText> textLists = this.param.getTextLists();
        if (this.param.isActives()) {
            ConfirmExtraText confirmExtraText = new ConfirmExtraText();
            confirmExtraText.setLeft(getString(R.string.contract_type));
            confirmExtraText.setRight(getString(R.string.multisign_transaction));
            textLists.add(0, confirmExtraText);
        }
        this.infoListAdapter = new ConfirmExtraTextCompatAdapter(this.rvInfo);
        this.rvInfo.setLayoutManager(new WrapContentLinearLayoutManager(this.mContext));
        this.rvInfo.setAdapter(this.infoListAdapter);
        this.infoListAdapter.update(this.param, textLists);
        this.needShowSrList = true;
        this.tvVoteSr.setText(getString(R.string.vote_sr_count, Integer.valueOf(this.votedAddressMap.size())));
        this.llArrow.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (needShowSrList) {
                    if (rvExtras.getVisibility() == 0) {
                        ivArrow.setImageResource(R.mipmap.ic_arrow_down);
                        rvExtras.setVisibility(View.GONE);
                        return;
                    }
                    ivArrow.setImageResource(R.mipmap.ic_arrow_up);
                    rvExtras.setVisibility(View.VISIBLE);
                }
            }
        });
        if (this.needShowSrList) {
            this.confirmExtraTextAdapter = new SimpleTextAdapter(buildListData());
            this.rvExtras.setLayoutManager(new WrapContentLinearLayoutManager(this.mContext));
            this.rvExtras.setAdapter(this.confirmExtraTextAdapter);
        }
        this.resourceView.bindData(this.param);
        this.btnConfirm.onBind(this.param, true, "");
        this.resourceView.setFeeResourceCallback(this.btnConfirm);
    }

    private List<ConfirmExtraText> buildListData() {
        ArrayList<ConfirmExtraText> arrayList = new ArrayList();
        for (String str : this.votedAddressMap.keySet()) {
            ConfirmExtraText confirmExtraText = new ConfirmExtraText();
            confirmExtraText.setLeft(StringTronUtil.isEmpty(this.votedNameMap.get(str)) ? str : this.votedNameMap.get(str));
            confirmExtraText.setRight(this.votedAddressMap.get(str));
            arrayList.add(confirmExtraText);
        }
        Collections.sort(arrayList, new Comparator<ConfirmExtraText>() {
            @Override
            public int compare(ConfirmExtraText confirmExtraText2, ConfirmExtraText confirmExtraText3) {
                try {
                    return Long.valueOf(confirmExtraText3.getRight()).compareTo(Long.valueOf(confirmExtraText2.getRight()));
                } catch (Throwable th) {
                    LogUtils.e(th);
                    return 0;
                }
            }
        });
        for (ConfirmExtraText confirmExtraText2 : arrayList) {
            try {
                confirmExtraText2.setRight(this.numberFormat.format(Long.valueOf(confirmExtraText2.getRight())));
            } catch (Exception e) {
                SentryUtil.captureException(e);
            }
        }
        return arrayList;
    }

    public static class SimpleTextAdapter extends RecyclerView.Adapter {
        private List<ConfirmExtraText> data;

        SimpleTextAdapter(List<ConfirmExtraText> list) {
            this.data = list;
            if (list == null) {
                this.data = new ArrayList();
            }
        }

        public void setData(List<ConfirmExtraText> list) {
            this.data = list;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SimpleTextHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_confirm_extra_inner_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof SimpleTextHolder) {
                ((SimpleTextHolder) viewHolder).onBind(this.data.get(i));
            }
        }

        @Override
        public int getItemCount() {
            return this.data.size();
        }
    }

    private static class SimpleTextHolder extends BaseHolder {
        private final TextView tvLeft;
        private final TextView tvRight;

        public SimpleTextHolder(View view) {
            super(view);
            this.tvLeft = (TextView) view.findViewById(R.id.tv_left);
            this.tvRight = (TextView) view.findViewById(R.id.tv_right);
        }

        void onBind(ConfirmExtraText confirmExtraText) {
            this.tvLeft.setText(String.format("%d.%s", Integer.valueOf(getAbsoluteAdapterPosition() + 1), confirmExtraText.getLeft()));
            this.tvRight.setText(confirmExtraText.getRight());
        }
    }
}
