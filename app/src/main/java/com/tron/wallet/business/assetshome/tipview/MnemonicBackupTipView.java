package com.tron.wallet.business.assetshome.tipview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.LlSigndealTipBinding;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class MnemonicBackupTipView implements ITipView {
    private View backupView;
    private LlSigndealTipBinding binding;
    View btnGo;
    View ivClose;
    private View.OnClickListener onClose;
    private View.OnClickListener onGo;
    private int state = 0;
    TextView tvDesc;
    TextView tvGo;
    TextView tvTitle;

    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public void setState(int i, String... strArr) {
        this.state = i;
    }

    public MnemonicBackupTipView(Context context, View view, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        mappingId(view);
        this.backupView = view;
        this.onClose = onClickListener;
        this.onGo = onClickListener2;
    }

    private void mappingId(View view) {
        LlSigndealTipBinding bind = LlSigndealTipBinding.bind(view);
        this.binding = bind;
        this.tvTitle = bind.tvSignTitle;
        this.ivClose = this.binding.ivSignClose;
        this.tvDesc = this.binding.tvSignDesc;
        this.btnGo = this.binding.llSignArrow;
        this.tvGo = this.binding.tvGoNow;
    }

    private void initUI() {
        this.backupView.setVisibility(View.VISIBLE);
        this.tvTitle.setText(R.string.safe_tip);
        this.tvDesc.setText(R.string.safe_backup_desc);
        this.tvGo.setText(R.string.immediate_backup);
        this.ivClose.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onClose != null) {
                    onClose.onClick(backupView);
                }
                hide();
            }
        });
        this.btnGo.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onGo != null) {
                    onGo.onClick(backupView);
                }
            }
        });
    }

    @Override
    public String getId() {
        return TipViewType.MNEMONIC_BACKUP.getId();
    }

    @Override
    public int getPriority() {
        return TipViewType.MNEMONIC_BACKUP.getPriority();
    }

    @Override
    public void show() {
        if (this.state == 3) {
            this.state = 4;
            initUI();
        }
    }

    @Override
    public void hide() {
        if (this.state != 4) {
            this.state = 2;
            return;
        }
        this.state = 5;
        this.backupView.setVisibility(View.GONE);
    }

    @Override
    public void onWalletChanged(Wallet wallet) {
        if (this.state == 4) {
            this.backupView.setVisibility(View.GONE);
        }
        this.state = 0;
    }
}
