package com.tron.wallet.business.security.approvecheck.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.security.approvecheck.CancelApproveClickListener;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.List;
public class ApproveProjectListAdapter extends BaseQuickAdapter<ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean, BaseViewHolder> {
    Button btnCancelAuth;
    private CancelApproveClickListener itemCancelApproveClickListener;
    private Context mContext;
    private String mType;
    private NoDoubleClickListener noDoubleClickListener;
    NumberFormat numberFormat;

    public void setItemCancelApproveClickListener(CancelApproveClickListener cancelApproveClickListener) {
        this.itemCancelApproveClickListener = cancelApproveClickListener;
    }

    public ApproveProjectListAdapter(Context context, String str, NoDoubleClickListener noDoubleClickListener) {
        super((int) R.layout.item_approve_risk);
        this.mContext = context;
        this.mType = str;
        this.noDoubleClickListener = noDoubleClickListener;
        setEmptyView(createEmptyView(context));
    }

    private View createEmptyView(Context context) {
        NoNetView noNetView = new NoNetView(context);
        noNetView.setIcon(R.mipmap.ic_no_data_new).setReloadDescription(R.string.approve_no_risk).setReloadable(false);
        noNetView.setPadding(0, UIUtils.dip2px(60.0f), 0, 0);
        return noNetView;
    }

    public void updateData(List<ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean> list) {
        setNewData(list);
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean projectsBean) {
        if (baseViewHolder instanceof ApprovedProjectListDataHolder) {
            ((ApprovedProjectListDataHolder) baseViewHolder).onBind(this.mContext, projectsBean, this.mType, this.itemCancelApproveClickListener);
        }
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return new ApprovedProjectListDataHolder(getItemView(this.mLayoutResId, viewGroup));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        super.onBindViewHolder((ApproveProjectListAdapter) baseViewHolder, i);
    }
}
