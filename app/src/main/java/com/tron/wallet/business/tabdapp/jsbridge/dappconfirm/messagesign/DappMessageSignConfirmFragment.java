package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.messagesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappMetadataParam;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.tab.DappTransactionDetailFragment;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.tab.TransactionMetadataFragment;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.FgDappConfirmMessageSignBinding;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
public class DappMessageSignConfirmFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgDappConfirmMessageSignBinding binding;
    String contractTypeString;
    DappTransactionDetailFragment detailFragment;
    GlobalTitleHeaderView headerView;
    LinearLayout ivClose;
    SimpleDraweeView ivIcon;
    ImageView ivIconTag;
    LinearLayout llRoot;
    private DappMetadataParam metaParam;
    TransactionMetadataFragment metadataFragment;
    private NumberFormat numberFormat;
    private int resTitleId;
    TokenBean tokenBean;

    public static DappMessageSignConfirmFragment getInstance() {
        return new DappMessageSignConfirmFragment();
    }

    @Override
    protected void processData() {
        getArguments();
        try {
            bindDataToUI();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void bindDataToUI() {
        BaseParam baseParam = new BaseParam();
        baseParam.setTitle(R.string.confirmtransaction, R.string.confirmtransaction);
        baseParam.addInfoTitle(R.string.dapp_confirm_message_title, "https://app.define.one  Requesting signature ");
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgDappConfirmMessageSignBinding inflate = FgDappConfirmMessageSignBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.llRoot = (LinearLayout) this.binding.getRoot().findViewById(R.id.root);
        this.ivClose = (LinearLayout) this.binding.getRoot().findViewById(R.id.iv_close);
        this.headerView = this.binding.headerView;
        this.ivIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_icon);
        this.ivIconTag = (ImageView) this.binding.getRoot().findViewById(R.id.iv_icon_tag);
    }

    public void setClick() {
        this.ivClose.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((DappConfirmNewActivity) getActivity()).cancle();
            }
        });
    }
}
