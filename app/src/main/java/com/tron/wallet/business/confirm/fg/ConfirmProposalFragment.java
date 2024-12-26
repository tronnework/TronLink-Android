package com.tron.wallet.business.confirm.fg;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FgConfirmProposalBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class ConfirmProposalFragment extends BaseConfirmFragment<EmptyPresenter, EmptyModel> {
    private FgConfirmProposalBinding binding;
    GlobalConfirmButton btnConfirm;
    GlobalTitleHeaderView headerView;
    private BaseParam param;
    GlobalFeeResourceView resourceView;
    RelativeLayout rlType;
    View rootLayout;
    RecyclerView rvExtras;
    TextView tvContentTitle;

    public static ConfirmProposalFragment getInstance(BaseParam baseParam) {
        ConfirmProposalFragment confirmProposalFragment = new ConfirmProposalFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        confirmProposalFragment.setArguments(bundle);
        return confirmProposalFragment;
    }

    @Override
    public void processData() {
        super.processData();
        BaseParam baseParam = this.baseParam;
        this.param = baseParam;
        if (baseParam == null) {
            return;
        }
        try {
            bindDataToUI();
            addAccountCallback(this.headerView, this.resourceView, this.btnConfirm);
            ensureAccount();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void bindDataToUI() {
        if (this.param == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.rootLayout.getLayoutParams();
        layoutParams.height = this.param.getLayoutHeight();
        this.rootLayout.setLayoutParams(layoutParams);
        this.headerView.bindData(this.param);
        if (this.param.getTextLists() == null || this.param.getTextLists().size() == 0) {
            this.rvExtras.setVisibility(View.GONE);
            this.tvContentTitle.setVisibility(View.GONE);
        } else {
            List<ConfirmExtraText> textLists = this.param.getTextLists();
            if (this.param.isActives()) {
                this.rlType.setVisibility(View.VISIBLE);
            } else {
                this.rlType.setVisibility(View.GONE);
            }
            if (textLists.size() != 0) {
                this.tvContentTitle.setVisibility(View.VISIBLE);
                this.rvExtras.setVisibility(View.VISIBLE);
                SimpleTextAdapter simpleTextAdapter = new SimpleTextAdapter(textLists);
                this.rvExtras.setLayoutManager(new WrapContentLinearLayoutManager(this.mContext));
                this.rvExtras.setAdapter(simpleTextAdapter);
                this.rvExtras.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
                        if (i <= 0) {
                            return;
                        }
                        rect.set(0, UIUtils.dip2px(6.0f), 0, 0);
                    }
                });
            } else {
                this.tvContentTitle.setVisibility(View.GONE);
            }
        }
        this.resourceView.bindData(this.param);
        this.btnConfirm.onBind(this.param);
        this.resourceView.setFeeResourceCallback(this.btnConfirm);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgConfirmProposalBinding inflate = FgConfirmProposalBinding.inflate(layoutInflater, viewGroup, false);
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
        this.rvExtras = this.binding.rvExtras;
        this.headerView = this.binding.headerView;
        this.resourceView = this.binding.resourceView;
        this.tvContentTitle = this.binding.tvContentTitle;
        this.rootLayout = this.binding.root;
        this.rlType = this.binding.rlType;
        this.btnConfirm = this.binding.buttonConfirm;
    }

    public static class SimpleTextAdapter extends RecyclerView.Adapter {
        private List<ConfirmExtraText> data;

        SimpleTextAdapter(List<ConfirmExtraText> list) {
            this.data = list;
            if (list == null) {
                this.data = new ArrayList();
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SimpleTextHolder(viewGroup.getContext());
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof SimpleTextHolder) {
                ConfirmExtraText confirmExtraText = this.data.get(i);
                ((SimpleTextHolder) viewHolder).onBind(String.format("%s %s", confirmExtraText.getLeft(), confirmExtraText.getRight()));
            }
        }

        @Override
        public int getItemCount() {
            return this.data.size();
        }
    }

    private static class SimpleTextHolder extends BaseHolder {
        TextView tvContent;

        public SimpleTextHolder(Context context) {
            super(new TextView(context));
        }

        void onBind(String str) {
            try {
                TextView textView = (TextView) this.itemView;
                this.tvContent = textView;
                textView.setTextColor(this.itemView.getContext().getResources().getColor(R.color.black_02));
                this.tvContent.setTextSize(14.0f);
                this.tvContent.setMaxLines(3);
                this.tvContent.setText(str);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }
}
