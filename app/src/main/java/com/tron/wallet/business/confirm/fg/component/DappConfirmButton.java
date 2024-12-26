package com.tron.wallet.business.confirm.fg.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.addressscam.ScamAddressPopWindowCallback;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class DappConfirmButton extends GlobalConfirmButton {
    private String approveAmount;
    private boolean hasApproveTitle;
    private boolean hasResourceContent;
    private boolean isApprove;
    private boolean isApproveAccount;

    public static void lambda$showUpdatePermissionPopWindow$1(View view) {
    }

    public void setApprove(boolean z) {
        this.isApprove = z;
    }

    public void setApproveAccount(boolean z) {
        this.isApproveAccount = z;
    }

    public void setApproveAmount(String str) {
        this.approveAmount = str;
    }

    public void setHasApproveTitle(boolean z) {
        this.hasApproveTitle = z;
    }

    public void setHasResourceContent(boolean z) {
        this.hasResourceContent = z;
    }

    public DappConfirmButton(Context context) {
        super(context);
    }

    public DappConfirmButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DappConfirmButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void updateClickEvent(final boolean z, final double d, final double d2, final double d3) {
        if (this.param == null || this.param.getTransactionBean().getBytes().isEmpty()) {
            return;
        }
        this.finishGetFee = true;
        if (this.finishGetScam || !this.loading) {
            if (this.loading) {
                this.animation.cancel();
                this.ivLoading.clearAnimation();
                this.ivLoading.setVisibility(View.GONE);
                if (this.isScam) {
                    this.btnConfirm.setBackgroundResource(R.drawable.selector_bg_red);
                }
            }
            setHasResourceContent(true);
            setEnabled(true);
        }
        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((param instanceof DappDetailParam) && !((DappDetailParam) param).isDetail() && ((DappDetailParam) param).isApprove()) {
                    if (getContext() instanceof DappConfirmNewActivity) {
                        ((DappConfirmNewActivity) getContext()).jumpToNext();
                        return;
                    } else if (getContext() instanceof DeepLinkDappConfirmActivity) {
                        ((DeepLinkDappConfirmActivity) getContext()).jumpToNext();
                        return;
                    } else {
                        return;
                    }
                }
                DappConfirmButton dappConfirmButton = DappConfirmButton.this;
                dappConfirmButton.cheatTokenConfirmEvent(dappConfirmButton.param);
                if (isScam) {
                    PopupWindowUtil.showScamAddressPopWindow(getContext(), address, new ScamAddressPopWindowCallback() {
                        @Override
                        public void cancel() {
                        }

                        @Override
                        public void continueDo() {
                            doTransation(z, d, d2, d3);
                        }
                    });
                } else if (((DappDetailParam) param).isUpdatePermission()) {
                    showUpdatePermissionPopWindow(z, d, d2, d3);
                } else {
                    doTransation(z, d, d2, d3);
                }
            }
        });
    }

    public void showUpdatePermissionPopWindow(final boolean z, final double d, final double d2, final double d3) {
        ConfirmCustomPopupView.getBuilder(getContext()).setTitle(getResources().getString(R.string.safe_tip)).setIcon(R.mipmap.ic_network_sec_title).setInfo(getResources().getString(R.string.pop_update_permission_safe_confirm_info)).setBtnStyle(1).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showUpdatePermissionPopWindow$0(z, d, d2, d3, view);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DappConfirmButton.lambda$showUpdatePermissionPopWindow$1(view);
            }
        }).setSupportConfirmBtnCountDown(true, R.string.pop_update_permission_safe_confirm_btn).setConfirmBtnCountDownSecond(5).build().show();
    }

    public void lambda$showUpdatePermissionPopWindow$0(boolean z, double d, double d2, double d3, View view) {
        doTransation(z, d, d2, d3);
    }

    @Override
    public void onBind(BaseParam baseParam) {
        if (baseParam == null) {
            return;
        }
        this.param = baseParam;
        try {
            if ((baseParam instanceof DappDetailParam) && ((DappDetailParam) baseParam).isApprove() && !((DappDetailParam) baseParam).isDetail()) {
                this.tvConfirm.setText(R.string.commit_dapp_next);
                if (StringTronUtil.isNullOrEmpty(this.approveAmount)) {
                    this.btnConfirm.setEnabled(false);
                    return;
                } else {
                    this.btnConfirm.setEnabled(true);
                    return;
                }
            }
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            int createType = selectedPublicWallet.getCreateType();
            if (createType == 8 && baseParam.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.request_from_ledger);
            } else if (createType == 7 && baseParam.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.confirm);
            } else if (selectedPublicWallet.isWatchOnly() && baseParam.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.create_transcation_qr);
            } else {
                this.tvConfirm.setText(R.string.confirm);
            }
            if (this.finishGetFee) {
                this.btnConfirm.setEnabled(true);
            } else {
                this.btnConfirm.setEnabled(false);
            }
            setupBottomWarning(baseParam);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void showFeeWarningDialog(int i, int i2, final BaseParam baseParam) {
        ConfirmCustomPopupView.getBuilder(getContext()).setBtnStyle(0).setTitle(getContext().getString(i)).setInfo(getContext().getString(i2)).setInfoGravity(3).setConfirmText(getContext().getString(R.string.confirm)).setCancelText(getContext().getString(R.string.cancel)).setAutoDismissOutSide(false).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showFeeWarningDialog$2(baseParam, view);
            }
        }).build().show();
    }

    public void lambda$showFeeWarningDialog$2(BaseParam baseParam, View view) {
        enterConfirm(getContext(), baseParam);
    }

    public void showFeeWarningDialog(int i, int i2, final BaseParam baseParam, int i3) {
        ConfirmCustomPopupView.getBuilder(getContext()).setBtnStyle(0).setTitle(getContext().getString(i)).setInfo(getContext().getString(i2)).setInfoGravity(i3).setConfirmText(getContext().getString(R.string.confirm)).setCancelText(getContext().getString(R.string.cancel)).setAutoDismissOutSide(false).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showFeeWarningDialog$3(baseParam, view);
            }
        }).build().show();
    }

    public void lambda$showFeeWarningDialog$3(BaseParam baseParam, View view) {
        enterConfirm(getContext(), baseParam);
    }

    public void showFeeWarningDialog(int i, String str, final BaseParam baseParam, int i2) {
        ConfirmCustomPopupView.getBuilder(getContext()).setBtnStyle(0).setTitle(getContext().getString(i)).setInfo(str).setInfoGravity(i2).setConfirmText(getContext().getString(R.string.confirm)).setCancelText(getContext().getString(R.string.cancel)).setAutoDismissOutSide(false).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showFeeWarningDialog$4(baseParam, view);
            }
        }).build().show();
    }

    public void lambda$showFeeWarningDialog$4(BaseParam baseParam, View view) {
        enterConfirm(getContext(), baseParam);
    }

    @Override
    public void setEnabled(boolean z) {
        if (this.isApprove) {
            if ((this.param instanceof DappDetailParam) && !((DappDetailParam) this.param).isDetail()) {
                if (StringTronUtil.isNullOrEmpty(this.approveAmount)) {
                    this.btnConfirm.setEnabled(false);
                    return;
                } else {
                    this.btnConfirm.setEnabled(z);
                    return;
                }
            } else if (this.hasApproveTitle && this.hasResourceContent && z) {
                this.btnConfirm.setEnabled(true);
                return;
            } else {
                this.btnConfirm.setEnabled(false);
                return;
            }
        }
        this.btnConfirm.setEnabled(z);
    }

    public void cheatTokenConfirmEvent(BaseParam baseParam) {
        try {
            if (((DappDetailParam) baseParam).getTokenBean() == null || ((DappDetailParam) baseParam).getTokenBean().isOfficial != -5) {
                return;
            }
            AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_CHEAT_TRADE, Integer.valueOf(((DappDetailParam) baseParam).getContractType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract.getNumber() ? 2 : 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doTransation(boolean z, double d, double d2, double d3) {
        if (this.isApproveAccount) {
            if (!z) {
                showFeeWarningDialog(R.string.confirm_fee_warning_title_dapp, R.string.confirm_fee_warning_dapp_approve_account_fee_not_enough, this.param);
            } else if (BigDecimalUtils.moreThanOrEqual(Double.valueOf(BigDecimalUtils.sum(d, d2)), Double.valueOf(d3))) {
                Context context = getContext();
                showFeeWarningDialog(R.string.confirm_fee_warning_title_dapp, context.getString(R.string.confirm_fee_warning_over_cost_dapp_approve_account_150, StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(Double.valueOf(d3))) + " TRX"), this.param);
            } else {
                showFeeWarningDialog(R.string.confirm_fee_warning_title_dapp, R.string.confirm_fee_warning_dapp_approve_account, this.param, 17);
            }
        } else if (!z) {
            showFeeWarningDialog(R.string.confirm_fee_warning_title, R.string.confirm_fee_warning_consume_trx, this.param);
        } else if (BigDecimalUtils.moreThanOrEqual(Double.valueOf(BigDecimalUtils.sum(d, d2)), Double.valueOf(d3))) {
            Context context2 = getContext();
            showFeeWarningDialog(R.string.confirm_fee_warning_title, context2.getString(R.string.confirm_fee_warning_over_cost, StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(Double.valueOf(d3))) + " TRX"), this.param, 17);
        } else {
            enterConfirm(getContext(), this.param);
        }
    }
}
