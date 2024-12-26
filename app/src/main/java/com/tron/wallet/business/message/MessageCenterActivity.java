package com.tron.wallet.business.message;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.message.MessageCenterContract;
import com.tron.wallet.common.components.LoadMoreRecyclerView;
import com.tron.wallet.common.components.MessageHeaderView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.AcMessageCenterBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class MessageCenterActivity extends BaseActivity<MessageCenterPresenter, MessageCenterModel> implements MessageCenterContract.View {
    private AcMessageCenterBinding binding;
    View llNoDataView;
    View mHolderView;
    NoNetView mNoNetView;
    LoadMoreRecyclerView mRecyclerView;
    MessageHeaderView messageHeader;
    View noNetContainer;
    TextView tvNoData;

    @Override
    public View getHolderView() {
        return this.mHolderView;
    }

    @Override
    public LoadMoreRecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    @Override
    public void onPointerCaptureChanged(boolean z) {
    }

    @Override
    public void updateUI(List<TransactionMessage> list) {
    }

    @Override
    public void onBackPressed() {
        int i;
        if (Build.VERSION.SDK_INT >= 23) {
            List<ActivityManager.AppTask> appTasks = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getAppTasks();
            if (appTasks.size() == 1) {
                i = appTasks.get(0).getTaskInfo().numActivities;
                if (i == 1) {
                    go(MainTabActivity.class);
                    finish();
                    return;
                }
            }
        }
        super.onBackPressed();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MessageCenterActivity.class));
    }

    @Override
    protected void setLayout() {
        AcMessageCenterBinding inflate = AcMessageCenterBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    public void mappingId() {
        this.messageHeader = this.binding.messageHeader;
        this.mRecyclerView = this.binding.tokenList;
        this.mHolderView = this.binding.rcHolderList;
        this.noNetContainer = this.binding.tvNoNet;
        this.mNoNetView = this.binding.noNetView;
        this.tvNoData = this.binding.tvNoData;
        this.llNoDataView = this.binding.llNodata;
    }

    public void showV2PopWindow() {
        PopupWindowUtil.showPopupText(this.mContext, getResources().getString(R.string.message_center_no_Watch_tip), this.messageHeader.ivQuestion, true);
    }

    @Override
    protected void processData() {
        this.messageHeader.setHeader(getString(R.string.message_center), null, null);
        this.messageHeader.showQuestion();
        this.messageHeader.setOnHeaderClickListener(new MessageHeaderView.OnHeaderClickListener() {
            @Override
            public void onLeftClick() {
                onBackPressed();
            }

            @Override
            public void onQuestion() {
                showV2PopWindow();
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.messageHeader.ivQuestion, 5, 10, 10, 10);
        ((MessageCenterPresenter) this.mPresenter).getMessages();
    }

    @Override
    public void showNoNetError(boolean z) {
        if (z) {
            if (this.mRecyclerView.getVisibility() == 8) {
                this.noNetContainer.setVisibility(View.VISIBLE);
                return;
            }
            return;
        }
        this.noNetContainer.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView(boolean z) {
        if (z) {
            this.noNetContainer.setVisibility(View.GONE);
            this.llNoDataView.setVisibility(View.VISIBLE);
            return;
        }
        this.llNoDataView.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        ((MessageCenterPresenter) this.mPresenter).updateAllReadStatus();
        super.onDestroy();
        this.binding = null;
    }
}
