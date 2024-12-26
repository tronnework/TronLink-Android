package com.tron.wallet.business.confirm.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.common.adapter.ConfirmUnfreezeContentAdapter;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.databinding.FgConfirmUnfreezeBinding;
public class ConfirmUnfreezeFragment extends BaseConfirmFragment<EmptyPresenter, EmptyModel> {
    private FgConfirmUnfreezeBinding binding;
    GlobalConfirmButton btnConfirm;
    private ConfirmUnfreezeContentAdapter contentAdapter;
    GlobalTitleHeaderView headerView;
    private BaseParam param;
    View rootLayout;
    RecyclerView rvExtras;

    public static ConfirmUnfreezeFragment getInstance(BaseParam baseParam) {
        ConfirmUnfreezeFragment confirmUnfreezeFragment = new ConfirmUnfreezeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        confirmUnfreezeFragment.setArguments(bundle);
        return confirmUnfreezeFragment;
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
            addAccountCallback(this.headerView, this.btnConfirm, this.contentAdapter);
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
        boolean z = (this.param.getTextLists() == null || this.param.getTextLists().size() == 0) ? false : true;
        this.btnConfirm.onBind(this.param);
        if (!z) {
            this.rvExtras.setVisibility(View.GONE);
        } else if (this.param.getTextLists().size() != 0) {
            this.rvExtras.setVisibility(View.VISIBLE);
            this.contentAdapter = new ConfirmUnfreezeContentAdapter(this.rvExtras, this.btnConfirm);
            this.rvExtras.setLayoutManager(new WrapContentLinearLayoutManager(this.mContext));
            this.rvExtras.setAdapter(this.contentAdapter);
            this.contentAdapter.updateData(this.param);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgConfirmUnfreezeBinding inflate = FgConfirmUnfreezeBinding.inflate(layoutInflater, viewGroup, false);
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
        this.rootLayout = this.binding.root;
        this.btnConfirm = this.binding.btnConfirm;
    }
}
