package com.tron.wallet.business.stakev2.managementv2.pop;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeBean;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemDelegateForMeBinding;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
public class DelegateForMeHolder extends BaseViewHolder {
    View divider;
    NumberFormat numberFormat;
    TextView tvAmount;
    TextView tvContractTag;
    TextView tvFrom;
    TextView tvFromAddress;

    public DelegateForMeHolder(View view) {
        super(view);
        mappingId(view);
        this.numberFormat = NumberFormat.getInstance();
    }

    public void mappingId(View view) {
        ItemDelegateForMeBinding bind = ItemDelegateForMeBinding.bind(view);
        this.tvAmount = bind.tvAmount;
        this.tvFrom = bind.tvFrom;
        this.tvContractTag = bind.tvContractTag;
        this.tvFromAddress = bind.tvFromAddress;
        this.divider = bind.divider;
    }

    public void onBind(Context context, StakeResourceDetailForMeBean stakeResourceDetailForMeBean) {
        if (stakeResourceDetailForMeBean != null) {
            this.tvContractTag.setVisibility(View.GONE);
            this.tvFromAddress.setVisibility(View.GONE);
            this.tvAmount.setText(NumUtils.numFormatToK(Long.parseLong(stakeResourceDetailForMeBean.getFromResourceBalance())));
            if (AddressMapUtils.getInstance().isContainsAddress(stakeResourceDetailForMeBean.getFromAccount())) {
                NameAddressExtraBean nameAddress = AddressMapUtils.getInstance().getNameAddress(stakeResourceDetailForMeBean.getFromAccount());
                if (stakeResourceDetailForMeBean.getAccountType() == 1) {
                    TextView textView = this.tvFrom;
                    String string = context.getString(R.string.stake_resource_from);
                    textView.setText(String.format(string, getWalletName(nameAddress) + "("));
                    this.tvContractTag.setVisibility(View.VISIBLE);
                    this.tvFromAddress.setVisibility(View.VISIBLE);
                    TextView textView2 = this.tvFromAddress;
                    textView2.setText(StringTronUtil.indentAddress(nameAddress.getAddress().toString(), 6) + ")");
                    return;
                }
                this.tvFrom.setText(String.format(context.getString(R.string.stake_resource_from), getDisPlayName(nameAddress)));
            } else if (stakeResourceDetailForMeBean.getAccountType() != 1) {
                this.tvFrom.setText(String.format(context.getString(R.string.stake_resource_from), stakeResourceDetailForMeBean.getFromAccount()));
            } else {
                this.tvFrom.setText(String.format(context.getString(R.string.stake_resource_from), ""));
                this.tvContractTag.setVisibility(View.VISIBLE);
                this.tvFromAddress.setVisibility(View.VISIBLE);
                this.tvFromAddress.setText(stakeResourceDetailForMeBean.getFromAccount());
            }
        }
    }

    private String getDisPlayName(NameAddressExtraBean nameAddressExtraBean) {
        if (nameAddressExtraBean != null) {
            if (nameAddressExtraBean.getName().length() > 7) {
                if (nameAddressExtraBean.getName().length() > 16) {
                    return StringTronUtil.indentAddress(nameAddressExtraBean.getName().toString(), 6) + " (" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
                }
                return nameAddressExtraBean.getName().toString() + " (" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
            }
            return nameAddressExtraBean.getName().toString() + " (" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
        }
        return "";
    }

    private String getWalletName(NameAddressExtraBean nameAddressExtraBean) {
        if (nameAddressExtraBean != null) {
            if (nameAddressExtraBean.getName().length() > 7) {
                if (nameAddressExtraBean.getName().length() > 16) {
                    return StringTronUtil.indentAddress(nameAddressExtraBean.getName().toString(), 6);
                }
                return nameAddressExtraBean.getName().toString();
            }
            return nameAddressExtraBean.getName().toString();
        }
        return "";
    }
}
