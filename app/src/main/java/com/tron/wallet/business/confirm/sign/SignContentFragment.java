package com.tron.wallet.business.confirm.sign;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemSignTransactionBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
public class SignContentFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private ItemSignTransactionBinding binding;
    ImageView ivQr3;
    private String signString;

    public void setParam(String str) {
        this.signString = str;
    }

    @Override
    protected void processData() {
        if (StringTronUtil.isEmpty(this.signString)) {
            return;
        }
        Bitmap strToQR = WalletUtils.strToQR(this.signString, UIUtils.dip2px(220.0f), UIUtils.dip2px(200.0f), null);
        if (strToQR == null) {
            Toast(R.string.qr_too_big_mutilqr);
        } else {
            this.ivQr3.setImageBitmap(strToQR);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ItemSignTransactionBinding inflate = ItemSignTransactionBinding.inflate(layoutInflater, viewGroup, false);
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
        this.ivQr3 = this.binding.ivQr3;
    }

    public void updateBg(float f) {
        ImageView imageView = this.ivQr3;
        if (imageView != null) {
            imageView.setAlpha(f);
        }
    }
}
