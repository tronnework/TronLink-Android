package com.tron.wallet.business.tabdapp.jsbridge;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.DgDappAuthorizedBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.ledger.LedgerWallet;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class DappAuthorizedPopWindow {
    private DgDappAuthorizedBinding binding;
    View.OnClickListener blackListener;
    private final View contentView;
    OnClick copyListener;
    boolean isApprove;
    boolean isBlack;
    ImageView ivBlack;
    ImageView ivClose;
    ImageView ivWallet;
    Activity mContext;
    BasePopupView popupView;
    private PopupWindow popupWindow;
    OnClick rejectListener;
    RelativeLayout rlTop;
    TextView tvApprove;
    TextView tvHost;
    TextView tvReject;
    TextView tvTips;
    TextView tvTipsAllow;
    TextView tvTipsConform;
    TextView tvTrxNum;
    TextView tvWalletAddress;
    TextView tvWalletName;
    private String url;
    View walletAddressLayout;

    public interface OnClick {
        void OnClickListener();
    }

    public DappAuthorizedPopWindow setApproveListener(View.OnClickListener onClickListener) {
        this.blackListener = onClickListener;
        return this;
    }

    public DappAuthorizedPopWindow setCopyListener(OnClick onClick) {
        this.copyListener = onClick;
        return this;
    }

    public DappAuthorizedPopWindow setRejectListener(OnClick onClick) {
        this.rejectListener = onClick;
        return this;
    }

    public DappAuthorizedPopWindow(Activity activity) {
        this.mContext = activity;
        View inflate = LayoutInflater.from(activity).inflate(R.layout.dg_dapp_authorized, (ViewGroup) null);
        this.contentView = inflate;
        int dip2px = UIUtils.dip2px(10.0f);
        mappingId(inflate);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivClose, dip2px, dip2px, dip2px, dip2px);
        addClick();
    }

    public void mappingId(View view) {
        DgDappAuthorizedBinding bind = DgDappAuthorizedBinding.bind(view);
        this.tvHost = bind.tvHost;
        this.ivWallet = bind.icWallet;
        this.tvWalletName = bind.tvWalletName;
        this.tvTrxNum = bind.tvTrxNum;
        this.tvWalletAddress = bind.tvWalletAddress;
        this.walletAddressLayout = bind.walletAddressLayout;
        this.tvReject = bind.tvReject;
        this.tvApprove = bind.tvApprove;
        this.ivClose = bind.ivClose;
        this.ivBlack = bind.ivIsBlack;
        this.tvTips = bind.tvTips;
        this.tvTipsConform = bind.tvBottom;
        this.tvTipsAllow = bind.tvBottom2;
        this.rlTop = bind.top;
    }

    public void addClick() {
        this.contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int top = contentView.findViewById(R.id.top).getTop();
                int bottom = contentView.findViewById(R.id.top).getBottom();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == 1 && (y < top || y > bottom)) {
                    dismiss();
                }
                return true;
            }
        });
        this.tvReject.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                DappAuthorizedPopWindow dappAuthorizedPopWindow = DappAuthorizedPopWindow.this;
                dappAuthorizedPopWindow.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_CONFIRM_PAGE_DISALLOW, dappAuthorizedPopWindow.isBlack);
                dismiss();
            }
        });
        this.ivClose.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                DappAuthorizedPopWindow dappAuthorizedPopWindow = DappAuthorizedPopWindow.this;
                dappAuthorizedPopWindow.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_CONFIRM_PAGE_CLOSE_BTN, dappAuthorizedPopWindow.isBlack);
                dismiss();
            }
        });
        this.walletAddressLayout.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                copyListener.OnClickListener();
            }
        });
        this.ivBlack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                DappAuthorizedPopWindow dappAuthorizedPopWindow = DappAuthorizedPopWindow.this;
                dappAuthorizedPopWindow.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_CONFIRM_PAGE_TIPS, dappAuthorizedPopWindow.isBlack);
                PopupWindowUtil.showPopupText(mContext, String.format(mContext.getResources().getString(R.string.dapp_aothorized_black_tips_pop), new Object[0]), ivBlack, true);
            }
        });
        this.tvApprove.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (isBlack) {
                    showAlertPopWindow();
                    return;
                }
                blackListener.onClick(view);
                if (isBlack) {
                    DappAuthorizedPopWindow dappAuthorizedPopWindow = DappAuthorizedPopWindow.this;
                    dappAuthorizedPopWindow.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_CONFIRM_PAGE_ONLY_ONCE_ALLOW, dappAuthorizedPopWindow.isBlack);
                    return;
                }
                DappAuthorizedPopWindow dappAuthorizedPopWindow2 = DappAuthorizedPopWindow.this;
                dappAuthorizedPopWindow2.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_CONFIRM_PAGE_ALLOW, dappAuthorizedPopWindow2.isBlack);
            }
        });
        this.rlTop.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 0 && i == 4) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    public DappAuthorizedPopWindow setHost(String str) {
        if (str != null) {
            this.url = str;
            this.tvHost.setText(str);
        }
        return this;
    }

    public DappAuthorizedPopWindow setWalletName(String str) {
        if (str != null) {
            this.tvWalletName.setText(str);
        }
        return this;
    }

    public DappAuthorizedPopWindow setWalletIcon(Wallet wallet) {
        int i;
        if (SpAPI.THIS.isCold()) {
            i = R.mipmap.icon_dapp_cold;
        } else if (wallet.isSamsungWallet()) {
            i = R.mipmap.icon_dapp_samsung;
        } else if (LedgerWallet.isLedger(wallet)) {
            i = R.mipmap.icon_dapp_ledger;
        } else if (!wallet.isWatchOnly() || wallet.isWatchCold()) {
            i = (wallet.isWatchOnly() && wallet.isWatchCold()) ? R.mipmap.icon_dapp_observe_cold : R.mipmap.icon_dapp_hot;
        } else {
            i = R.mipmap.icon_dapp_observe;
        }
        this.ivWallet.setImageResource(i);
        return this;
    }

    public DappAuthorizedPopWindow setTrxNum(String str) {
        if (str != null) {
            this.tvTrxNum.setText(str);
        }
        return this;
    }

    public DappAuthorizedPopWindow setWalletAddress(String str) {
        if (str != null) {
            this.tvWalletAddress.setText(str);
        }
        return this;
    }

    public DappAuthorizedPopWindow setisInBlackList(boolean z) {
        this.isBlack = z;
        this.ivBlack.setVisibility(z ? View.VISIBLE : View.GONE);
        this.tvTips.setVisibility(z ? View.VISIBLE : View.GONE);
        this.tvTipsConform.setVisibility(z ? View.GONE : View.VISIBLE);
        this.tvTipsAllow.setVisibility(z ? View.GONE : View.VISIBLE);
        if (z) {
            this.tvApprove.setText(this.mContext.getResources().getString(R.string.dapp_approve_only_this_time));
        }
        return this;
    }

    public DappAuthorizedPopWindow setCanceledOnTouchOutside(boolean z) {
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            popupWindow.setOutsideTouchable(z);
        }
        return this;
    }

    public void show() {
        if (this.popupWindow == null) {
            PopupWindow popupWindow = new PopupWindow(this.contentView, -1, -1, true);
            this.popupWindow = popupWindow;
            popupWindow.setBackgroundDrawable(new ColorDrawable(this.mContext.getResources().getColor(R.color.black_0E_60)));
        }
        if (this.popupWindow.isShowing()) {
            this.popupWindow.dismiss();
        }
        if (this.mContext.isDestroyed() || this.mContext.isFinishing()) {
            return;
        }
        this.popupWindow.showAtLocation(this.mContext.getWindow().getDecorView(), 80, 0, 0);
    }

    public void dismiss() {
        if (this.popupWindow.isShowing()) {
            if (!this.isApprove) {
                this.rejectListener.OnClickListener();
            }
            this.popupWindow.dismiss();
        }
    }

    public boolean isShowing() {
        return this.popupWindow.isShowing();
    }

    public void showAlertPopWindow() {
        BasePopupView asCustom = new XPopup.Builder(this.mContext).maxWidth(XPopupUtils.getScreenWidth(this.mContext)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(this.mContext) {
            @Override
            public int getImplLayoutId() {
                return R.layout.network_switch_pop;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                ((TextView) findViewById(R.id.tv_title)).setText(R.string.risk_warning);
                TextView textView = (TextView) findViewById(R.id.tv_cancle);
                textView.setText(R.string.cancel);
                Button button = (Button) findViewById(R.id.btn_confirm);
                button.setText(R.string.dapp_approve_only_this_time);
                ((TextView) findViewById(R.id.tv_content)).setText(R.string.dapp_is_black_tips_pop);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_CONFIRM_PAGE_ONLY_ONCE_CANCEL_IN_POP, isBlack);
                        popupView.dismiss();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isApprove = true;
                        logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_CONFIRM_PAGE_ONLY_ONCE_ALLOW_IN_POP, isBlack);
                        popupView.dismiss();
                        blackListener.onClick(view);
                    }
                });
            }
        });
        this.popupView = asCustom;
        asCustom.show();
    }

    public void logEvent(String str, boolean z) {
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(z ? 2 : 1);
        AnalyticsHelper.logEventFormat(str, objArr);
    }
}
