package com.tron.wallet.business.assetshome.tipview;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.wallet.business.transfer.multisignature.MultiSignatureManagerActivity;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.LlSigndealTipBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class MultiPermissionTipView implements ITipView {
    private static final String SP_FILE = "popup_timestamp";
    private static final long TIME_SEPARATOR = 604800000;
    private LlSigndealTipBinding binding;
    View btnGo;
    private String content;
    private Context context;
    View ivClose;
    private View.OnClickListener onClose;
    private View.OnClickListener onGo;
    private int state = 0;
    private View tipView;
    TextView tvDesc;
    TextView tvGo;
    TextView tvTitle;
    private String walletName;

    @Override
    public int getState() {
        return this.state;
    }

    public MultiPermissionTipView(Context context, View view, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        mappingId(view);
        this.context = context;
        this.tipView = view;
        this.onClose = onClickListener;
        this.onGo = onClickListener2;
    }

    public void mappingId(View view) {
        LlSigndealTipBinding bind = LlSigndealTipBinding.bind(view);
        this.binding = bind;
        this.tvTitle = bind.tvSignTitle;
        this.ivClose = this.binding.ivSignClose;
        this.tvDesc = this.binding.tvSignDesc;
        this.btnGo = this.binding.llSignArrow;
        this.tvGo = this.binding.tvGoNow;
    }

    public static void setCloseTimestamp(Context context, String str, long j) {
        SpUtils.setParam(SP_FILE, context, str, Long.valueOf(j));
    }

    private static long getCloseTimestamp(Context context, String str) {
        return ((Long) SpUtils.getParam(SP_FILE, context, str, 0L)).longValue();
    }

    private boolean outTimeToShow(String str) {
        return System.currentTimeMillis() - getCloseTimestamp(this.context, str) >= TIME_SEPARATOR;
    }

    private void initUI(String str, String str2) {
        this.walletName = str;
        this.tipView.setVisibility(View.VISIBLE);
        this.tvTitle.setText(R.string.account_multi_sign_title);
        TextView textView = this.tvDesc;
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        textView.setText(str2);
        this.tvGo.setText(R.string.go_to_view_permissions);
        this.ivClose.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                recordCloseTime(view.getContext());
                hide();
            }
        });
        this.btnGo.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (StringTronUtil.isEmpty(walletName)) {
                    hide();
                    return;
                }
                Wallet wallet = WalletUtils.getWallet(walletName);
                if (wallet == null) {
                    return;
                }
                if (wallet.isWatchNotPaired()) {
                    PairColdWalletDialog.showUp(context, null);
                    return;
                }
                MultiSignatureManagerActivity.start(context, wallet.getWalletName());
                recordCloseTime(view.getContext());
                hide();
            }
        });
    }

    public void recordCloseTime(Context context) {
        if (StringTronUtil.isEmpty(this.walletName)) {
            return;
        }
        setCloseTimestamp(context, this.walletName, System.currentTimeMillis());
    }

    @Override
    public String getId() {
        return TipViewType.MULTI_PERMISSION.getId();
    }

    @Override
    public int getPriority() {
        return TipViewType.MULTI_PERMISSION.getPriority();
    }

    @Override
    public void setState(int i, String... strArr) {
        if (i != 3 || strArr == null || strArr.length != 2) {
            this.state = 2;
            this.walletName = null;
            this.content = null;
        } else if (!outTimeToShow(strArr[0])) {
            this.state = 2;
        } else {
            this.walletName = strArr[0];
            this.content = strArr[1];
            this.state = 3;
        }
    }

    @Override
    public void show() {
        if (this.state == 3) {
            this.state = 4;
            initUI(this.walletName, this.content);
        }
    }

    @Override
    public void hide() {
        if (this.state != 4) {
            this.state = 2;
            return;
        }
        this.state = 5;
        this.tipView.setVisibility(View.GONE);
    }

    @Override
    public void onWalletChanged(Wallet wallet) {
        if (this.state == 4) {
            this.tipView.setVisibility(View.GONE);
        }
        this.state = 0;
    }
}
