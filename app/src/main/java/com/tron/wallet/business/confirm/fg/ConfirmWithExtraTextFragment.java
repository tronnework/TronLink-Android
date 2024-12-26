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
import com.tron.wallet.common.adapter.ConfirmExtraTextCompatAdapter;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.databinding.FgConfirmWithExtraTextBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class ConfirmWithExtraTextFragment extends BaseConfirmFragment<EmptyPresenter, EmptyModel> {
    private FgConfirmWithExtraTextBinding binding;
    GlobalConfirmButton btnConfirm;
    private ConfirmExtraTextCompatAdapter confirmExtraTextAdapter;
    GlobalTitleHeaderView headerView;
    private BaseParam param;
    View rootLayout;
    RecyclerView rvExtras;

    public static ConfirmWithExtraTextFragment getInstance(BaseParam baseParam) {
        ConfirmWithExtraTextFragment confirmWithExtraTextFragment = new ConfirmWithExtraTextFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        confirmWithExtraTextFragment.setArguments(bundle);
        return confirmWithExtraTextFragment;
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
            addAccountCallback(this.headerView, this.btnConfirm, this.confirmExtraTextAdapter);
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
        List<ConfirmExtraText> textLists = this.param.getTextLists();
        this.rvExtras.setVisibility(View.VISIBLE);
        if (this.param.isActives()) {
            ConfirmExtraText confirmExtraText = new ConfirmExtraText();
            confirmExtraText.setLeft(getString(R.string.contract_type));
            confirmExtraText.setRight(getString(R.string.multisign_transaction));
            textLists.add(0, confirmExtraText);
        }
        this.btnConfirm.onBind(this.param);
        this.confirmExtraTextAdapter = new ConfirmExtraTextCompatAdapter(this.rvExtras, this.btnConfirm);
        this.rvExtras.setLayoutManager(new WrapContentLinearLayoutManager(this.mContext));
        this.rvExtras.setAdapter(this.confirmExtraTextAdapter);
        this.confirmExtraTextAdapter.update(this.param, textLists);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgConfirmWithExtraTextBinding inflate = FgConfirmWithExtraTextBinding.inflate(getLayoutInflater());
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
        this.btnConfirm = this.binding.btnConfirm;
        this.rootLayout = this.binding.root;
    }
}
