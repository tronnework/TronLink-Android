package com.tron.wallet.business.pull.exception;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.tron.wallet.business.addwallet.AddWalletActivityV2;
import com.tron.wallet.business.pull.PullParam;
import com.tron.wallet.business.pull.PullResult;
import com.tron.wallet.business.pull.PullResultCode;
import com.tron.wallet.business.pull.PullResultHelper;
import com.tron.wallet.business.pull.component.PullDetailView;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tronlinkpro.wallet.R;
public class NoFromAddressView extends PullDetailView {
    private PullParam pullParam;

    public NoFromAddressView(Context context, PullParam pullParam) {
        super(context);
        this.pullParam = pullParam;
    }

    @Override
    public PullResultCode checkDataValid() {
        return PullResultCode.SUCCESS;
    }

    @Override
    public void init() {
        this.btnConfirm.setText(this.activity.getString(R.string.pull_to_import));
        this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                activity.startActivity(new Intent(activity, AddWalletActivityV2.class));
                PullResult pullResult = new PullResult();
                pullResult.setCode(PullResultCode.FAIL_NO_FROM_ADDRESS.getCode());
                pullResult.setMessage(PullResultCode.FAIL_NO_FROM_ADDRESS.getMessage());
                PullResultHelper.callBackToDApp(pullParam.getCallbackUrl(), pullResult);
                ((Activity) activity).finish();
            }
        });
        this.btnCancel.setText(this.activity.getString(R.string.pull_back_to_dapp));
        this.btnCancel.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                PullResult pullResult = new PullResult();
                pullResult.setCode(PullResultCode.FAIL_NO_FROM_ADDRESS.getCode());
                pullResult.setMessage(PullResultCode.FAIL_NO_FROM_ADDRESS.getMessage());
                PullResultHelper.callBackToDApp(pullParam.getCallbackUrl(), pullResult);
                ((AppCompatActivity) activity).moveTaskToBack(true);
                ((AppCompatActivity) activity).finish();
            }
        });
    }
}
