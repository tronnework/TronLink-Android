package com.tron.wallet.business.tabmy;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcConvertmnemonicBinding;
import com.tronlinkpro.wallet.R;
import org.tron.common.bip39.BIP39;
import org.tron.common.bip39.ValidationException;
import org.tron.common.utils.ByteArray;
public class ConvertMnemonicActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AcConvertmnemonicBinding binding;
    TextView copy;
    EditText etInnertitle;
    ImageView ivCopy;
    LinearLayout llBottom;
    private String pass;
    TextView tvPrivatekey;

    @Override
    protected void processData() {
    }

    @Override
    protected void setLayout() {
        AcConvertmnemonicBinding inflate = AcConvertmnemonicBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        ScrollView root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.convert5));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.etInnertitle = this.binding.etInnertitle;
        this.tvPrivatekey = this.binding.tvPrivatekey;
        this.copy = this.binding.copy;
        this.ivCopy = this.binding.ivCopy;
        this.llBottom = this.binding.llBottom;
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.bt_convert) {
                    if (id != R.id.copy) {
                        return;
                    }
                    if (!StringTronUtil.isEmpty(pass)) {
                        UIUtils.copy(pass, true);
                        copy.setText(R.string.already_copy);
                        ivCopy.setBackgroundResource(R.mipmap.ic_copy);
                        copy.setTextColor(getResources().getColor(R.color.gray_B8));
                        return;
                    }
                    ToastAsBottom(R.string.trx_empty);
                    return;
                }
                String text = StringTronUtil.getText(etInnertitle);
                if (!StringTronUtil.isEmpty(text)) {
                    String[] split = text.split("\\s+");
                    if (split != null && split.length == 24) {
                        try {
                            pass = ByteArray.toHexString(BIP39.decode(text, "pass"));
                            if (StringTronUtil.isEmpty(pass)) {
                                return;
                            }
                            llBottom.setVisibility(View.VISIBLE);
                            tvPrivatekey.setText(pass);
                            UIUtils.hideSoftKeyBoard((Activity) mContext);
                            return;
                        } catch (ValidationException e) {
                            ToastAsBottom(R.string.convert6);
                            llBottom.setVisibility(View.GONE);
                            LogUtils.e(e);
                            return;
                        }
                    }
                    ToastAsBottom(R.string.convert_mnemon_error);
                    llBottom.setVisibility(View.GONE);
                    return;
                }
                ToastAsBottom(R.string.trx_empty);
                llBottom.setVisibility(View.GONE);
            }
        };
        this.binding.btConvert.setOnClickListener(noDoubleClickListener2);
        this.binding.copy.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }
}
