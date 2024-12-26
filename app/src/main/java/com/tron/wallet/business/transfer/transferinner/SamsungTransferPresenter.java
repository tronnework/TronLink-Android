package com.tron.wallet.business.transfer.transferinner;

import android.widget.Button;
import android.widget.EditText;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.transfer.transferinner.TransferContract;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.tron.protos.Protocol;
public class SamsungTransferPresenter {
    private Protocol.Account account;
    private List<Protocol.Permission> activePermissionList;
    private byte[] address;
    private int id;
    private boolean isActives = false;
    private String isTrx;
    private final TransferContract.View mView;
    private TokenBean token;
    private CopyOnWriteArrayList<TokenBean> tokenBeanList;

    public void send(String str, byte[] bArr, EditText editText, double d, TokenBean tokenBean, double d2, String str2, boolean z, boolean z2, String str3, int i, EditText editText2, Button button) {
    }

    public SamsungTransferPresenter(TransferContract.View view) {
        this.mView = view;
    }

    public void getSamsungAccount(final String str) {
        this.mView.runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getSamsungAccount$1(str);
            }
        });
    }

    public void lambda$getSamsungAccount$1(final String str) {
        try {
            byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(str);
            this.address = decodeFromBase58Check;
            this.account = TronAPI.queryAccount(decodeFromBase58Check, false);
        } catch (Exception e) {
            LogUtils.e(e);
            this.isActives = false;
        }
        this.mView.runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$getSamsungAccount$0(str);
            }
        });
    }

    public void lambda$getSamsungAccount$0(String str) {
        Protocol.Account account = this.account;
        if (account == null || account.toString().length() == 0) {
            this.isActives = false;
            return;
        }
        this.activePermissionList = this.account.getActivePermissionList();
        if (str.equals(WalletUtils.getSelectedWallet().getAddress())) {
            Protocol.Permission ownerPermission = this.account.getOwnerPermission();
            if (ownerPermission == null || ownerPermission.toString().length() == 0) {
                this.isActives = false;
            } else if (ownerPermission.getKeysList().size() == 1) {
                if (StringTronUtil.encode58Check(ownerPermission.getKeysList().get(0).getAddress().toByteArray()).equals(str)) {
                    this.isActives = false;
                }
            } else {
                for (int i = 0; i < ownerPermission.getKeysList().size(); i++) {
                    if (StringTronUtil.encode58Check(ownerPermission.getKeysList().get(i).getAddress().toByteArray()).equals(str) && ownerPermission.getKeysList().get(i).getWeight() >= this.account.getOwnerPermission().getThreshold()) {
                        this.isActives = false;
                    }
                }
            }
        } else if (this.activePermissionList.size() == 0) {
            this.isActives = false;
        } else {
            this.id = this.activePermissionList.get(0).getId();
            this.isActives = true;
        }
    }

    private Protocol.Transaction setTime(int i, Protocol.Transaction transaction) {
        Protocol.Transaction.Builder builder = transaction.toBuilder();
        Protocol.Transaction.raw.Builder builder2 = transaction.getRawData().toBuilder();
        builder2.setExpiration(System.currentTimeMillis() + (i * 60000));
        builder.setRawData(builder2.build());
        return builder.build();
    }
}
