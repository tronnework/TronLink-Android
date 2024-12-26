package com.tron.wallet.business.walletmanager.backupmnemonic.fg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.backupmnemonic.BackUpMnemonicActivity;
import com.tron.wallet.business.walletmanager.backupmnemonic.Page;
import com.tron.wallet.business.walletmanager.backupmnemonic.ShowQRActivity;
import com.tron.wallet.business.walletmanager.backupmnemonic.VerifyMnemonicActivity;
import com.tron.wallet.common.adapter.user.BackMnemonicAdapter;
import com.tron.wallet.common.components.WriteMnemonicItemDecoration;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.databinding.FgBackupMnemonicShowBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import org.tron.walletserver.Wallet;
public class ShowMnemonicFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgBackupMnemonicShowBinding binding;
    Button btSaved;
    private boolean isFromCreate;
    View llHideQrcode;
    private String mnemonics;
    private Dialog qrDialog;
    RecyclerView rlMnemonic;
    View rlQrcodeButton;
    TextView tvTip;
    private Wallet wallet;
    private String walletName;

    @Override
    protected void processData() {
        if (getArguments() != null) {
            this.mnemonics = getArguments().getString(TronConfig.WALLET_extra);
            this.walletName = getArguments().getString(TronConfig.WALLET_DATA);
            this.isFromCreate = getArguments().getBoolean("is_show_timeline_view");
            this.wallet = WalletUtils.getWallet(this.walletName);
            if (!TextUtils.isEmpty(this.mnemonics) && this.wallet != null) {
                updateMnemonic(this.mnemonics, false);
            } else {
                exit();
                return;
            }
        }
        this.rlQrcodeButton.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ShowQRActivity.start(mContext, mnemonics);
            }
        });
        this.btSaved.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (!wallet.isBackUp() && wallet.getCreateType() == 0) {
                    VerifyMnemonicActivity.start(mContext, walletName, mnemonics, isFromCreate);
                    return;
                }
                ((EmptyPresenter) mPresenter).mRxManager.post(Event.BACKUP, "backup");
                exit();
            }
        });
        this.tvTip.setText(String.format(getString(R.string.create_wallet_hint_8), getString(R.string.mnemonic)));
        this.llHideQrcode.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((BackUpMnemonicActivity) getActivity()).JumpTo(Page.BackUpShowPrevious);
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgBackupMnemonicShowBinding inflate = FgBackupMnemonicShowBinding.inflate(layoutInflater, viewGroup, false);
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
        this.rlMnemonic = this.binding.rlMnemonic;
        this.rlQrcodeButton = this.binding.rlQrcode;
        this.btSaved = this.binding.btSaved;
        this.tvTip = this.binding.tvTip;
        this.llHideQrcode = this.binding.llHideQrcode;
    }

    private void updateMnemonic(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    toast("Error: couldn't generate recovery phrase");
                    getActivity().finish();
                    return;
                } catch (Exception e) {
                    LogUtils.e(e);
                    SentryUtil.captureException(e);
                    return;
                }
            }
            return;
        }
        String[] split = str.split("\\s+");
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            arrayList.add(str2);
        }
        this.rlMnemonic.setNestedScrollingEnabled(false);
        this.rlMnemonic.addItemDecoration(new WriteMnemonicItemDecoration());
        this.rlMnemonic.setLayoutManager(new GridLayoutManager((Context) this.mContext, 3, 1, false));
        this.rlMnemonic.setAdapter(new BackMnemonicAdapter(arrayList));
    }

    public static ShowMnemonicFragment getInstance(String str, String str2, boolean z) {
        ShowMnemonicFragment showMnemonicFragment = new ShowMnemonicFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TronConfig.WALLET_extra, str);
        bundle.putString(TronConfig.WALLET_DATA, str2);
        bundle.putBoolean("is_show_timeline_view", z);
        showMnemonicFragment.setArguments(bundle);
        return showMnemonicFragment;
    }
}
