package com.tron.wallet.business.upgraded;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.upgraded.confirm.HdUpdateConfirmActivity;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.config.UpgradeParamSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.AcUpdateHd1Binding;
import com.tronlinkpro.wallet.R;
public class HdUpdateActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private ActivityResultLauncher<Intent> LauncherConfirm;
    private AcUpdateHd1Binding binding;
    View btCancel;
    View btNext;

    @Override
    protected void setLayout() {
        AcUpdateHd1Binding inflate = AcUpdateHd1Binding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 1);
        setHeaderBar(getString(R.string.change_hd_wallet_title));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.btCancel = this.binding.btCancel;
        this.btNext = this.binding.btNext;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        exit();
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent("changeHD_description_show");
        this.LauncherConfirm = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                if (activityResult.getResultCode() == -1) {
                    exit();
                }
            }
        });
        this.btCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                showConfirmChangeHdPopWindow();
                AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.CHANGE_HD_DESCRIPTION_CLICK_CANCEL);
            }
        });
        this.btNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                LauncherConfirm.launch(new Intent(mContext, HdUpdateConfirmActivity.class));
                AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.CHANGE_HD_DESCRIPTION_CLICK_NEXT);
            }
        });
    }

    public void showConfirmChangeHdPopWindow() {
        ConfirmCustomPopupView.getBuilder(this).setTitle(getResources().getString(R.string.confirm_giveup_hd_title)).setInfo(getResources().getString(R.string.confirm_giveup_hd_info)).setCancelText(getResources().getString(R.string.need_to_consider)).setConfirmText(getResources().getString(R.string.confirm_to_give_up)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showConfirmChangeHdPopWindow$0(view);
            }
        }).build().show();
    }

    public void lambda$showConfirmChangeHdPopWindow$0(View view) {
        UpgradeParamSetting.setStatusHide();
        exit();
    }
}
