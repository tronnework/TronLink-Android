package com.tron.wallet.business.walletmanager.importwallet.rv;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.common.adapter.holder.swap.TokenHolder;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public class AddressItemHolder extends BaseViewHolder {
    public AppCompatCheckBox cbCheck;
    TextView tvAddress;
    TextView tvBalance;
    TextView tvCustom;
    TextView tvImported;
    TextView tvIndex;
    TextView tvPath;
    View viewHolder;

    public AddressItemHolder(View view) {
        super(view);
        this.cbCheck = (AppCompatCheckBox) view.findViewById(R.id.iv_select_tag);
        this.tvAddress = (TextView) view.findViewById(R.id.tv_address);
        this.tvBalance = (TextView) view.findViewById(R.id.tv_balance);
        this.tvIndex = (TextView) view.findViewById(R.id.iv_idx);
        this.tvCustom = (TextView) view.findViewById(R.id.tv_custom);
        this.tvPath = (TextView) view.findViewById(R.id.tv_path);
        this.tvImported = (TextView) view.findViewById(R.id.tv_imported);
        this.viewHolder = view.findViewById(R.id.view_holder);
        UIUtils.dip2px(10.0f);
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                lambda$new$0(view2);
            }
        });
    }

    public void lambda$new$0(View view) {
        AppCompatCheckBox appCompatCheckBox = this.cbCheck;
        appCompatCheckBox.setChecked(!appCompatCheckBox.isChecked());
    }

    public void onBind(AddressItem addressItem) {
        this.cbCheck.setOnCheckedChangeListener(null);
        this.cbCheck.setChecked(addressItem.isSelected());
        this.tvAddress.setText(addressItem.getAddress());
        if (SpAPI.THIS.isCold()) {
            this.tvBalance.setVisibility(View.GONE);
        } else {
            this.tvBalance.setText(String.format(this.itemView.getContext().getString(R.string.balance_is), BigDecimalUtils.LessThan((Object) Double.valueOf(addressItem.getBalance()), (Object) 0) ? TokenHolder.PLACE_HOLDER : StringTronUtil.formatNumber6Truncate(BigDecimalUtils.toBigDecimal(Double.valueOf(addressItem.getBalance())))));
            this.tvBalance.setVisibility(View.VISIBLE);
        }
        this.tvIndex.setText(String.valueOf(addressItem.getIndex()));
        updateCustomPath(false);
        this.tvPath.setText(String.format(this.itemView.getContext().getString(R.string.mnemonic_string), addressItem.getPathStr()));
        this.cbCheck.setEnabled(true ^ addressItem.isExisted());
        if (addressItem.isExisted()) {
            this.tvAddress.setTextColor(AppContextUtil.getContext().getResources().getColor(R.color.gray_9B));
            this.tvBalance.setTextColor(AppContextUtil.getContext().getResources().getColor(R.color.gray_9B));
            this.tvImported.setVisibility(View.VISIBLE);
        } else {
            this.tvAddress.setTextColor(AppContextUtil.getContext().getResources().getColor(R.color.black_23));
            this.tvBalance.setTextColor(AppContextUtil.getContext().getResources().getColor(R.color.black_23));
            this.tvImported.setVisibility(View.GONE);
        }
        changeToNonHD(addressItem.isNonHd());
    }

    private void setColorSpan(String str, String str2) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.itemView.getContext().getResources().getColor(R.color.black_02));
        String format = String.format(str, str2);
        SpannableString spannableString = new SpannableString(format);
        int indexOf = format.indexOf(str2);
        if (indexOf < 0 || indexOf >= spannableString.length()) {
            return;
        }
        spannableString.setSpan(foregroundColorSpan, indexOf, str2.length() + indexOf, 33);
        this.tvBalance.setText(spannableString);
    }

    public void updateCustomPath(boolean z) {
        this.tvCustom.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    public void changeToNonHD(boolean z) {
        if (z) {
            this.tvIndex.setVisibility(View.GONE);
            this.tvPath.setVisibility(View.GONE);
            this.viewHolder.setVisibility(View.VISIBLE);
            return;
        }
        this.tvIndex.setVisibility(View.VISIBLE);
        this.tvPath.setVisibility(View.VISIBLE);
        this.viewHolder.setVisibility(View.GONE);
    }
}
