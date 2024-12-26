package com.tron.wallet.business.walletmanager.createwallet;

import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.CreateSuccessActivity;
import com.tron.wallet.business.walletmanager.backupmnemonic.BackUpMnemonicActivity;
import com.tron.wallet.business.walletmanager.createwallet.CreateWalletContract;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import org.tron.net.CipherException;
import org.tron.walletserver.DuplicateNameException;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.InvalidPasswordException;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class CreateWalletPresenter extends CreateWalletContract.Presenter {
    private Wallet wallet;

    @Override
    public void create(final boolean z, final String str, final String str2) {
        if (StringTronUtil.isEmpty(str, str2)) {
            return;
        }
        ((CreateWalletContract.View) this.mView).showLoadingDialog();
        ((CreateWalletContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$create$1(z, str, str2);
            }
        });
    }

    public void lambda$create$1(boolean z, String str, String str2) {
        final boolean saveWallet = saveWallet(z, str, str2);
        ((CreateWalletContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$create$0(saveWallet);
            }
        });
    }

    public void lambda$create$0(boolean z) {
        ((CreateWalletContract.View) this.mView).dismissLoadingDialog();
        if (z) {
            ((CreateWalletContract.View) this.mView).showSecurityLayout();
        } else {
            SentryUtil.captureMessage("CreateWalletPresenter:create wallet error");
        }
    }

    @Override
    public void jumpSecurity() {
        CreateSuccessActivity.start(((CreateWalletContract.View) this.mView).getIContext(), this.wallet.getMnemonic(), this.wallet.getWalletName(), false);
        ((CreateWalletContract.View) this.mView).exit();
    }

    @Override
    public void setSecurityAction() {
        BackUpMnemonicActivity.startFromCreateOrNoT(((CreateWalletContract.View) this.mView).getIContext(), this.wallet.getMnemonic(), this.wallet.getWalletName(), true, false);
        ((CreateWalletContract.View) this.mView).exit();
    }

    private boolean saveWallet(boolean z, String str, String str2) {
        try {
            Wallet wallet = new Wallet(true);
            this.wallet = wallet;
            wallet.setWalletName(str);
            this.wallet.setCreateType(0);
            this.wallet.setCreateTime(System.currentTimeMillis());
            this.wallet.setIconRes(UserIconRandom.THIS.random());
            this.wallet.setMnemonicPath(JSON.toJSONString(new WalletPath()));
            WalletUtils.saveWallet(this.wallet, str2);
            WalletNameGeneratorUtils.finish(0, z);
            if (!z) {
                Wallet wallet2 = this.wallet;
                HDTronWalletController.insertWallet(wallet2, wallet2.getAddress());
                WalletUtils.setSelectedWallet(str);
            }
            if (SpAPI.THIS.isCold()) {
                SpAPI.THIS.setColdWalletSelected(str);
            }
            return true;
        } catch (CipherException e) {
            LogUtils.e(e);
            return false;
        } catch (DuplicateNameException e2) {
            LogUtils.e(e2);
            return false;
        } catch (InvalidNameException e3) {
            LogUtils.e(e3);
            return false;
        } catch (InvalidPasswordException e4) {
            LogUtils.e(e4);
            return false;
        } catch (Exception e5) {
            LogUtils.e(e5);
            return false;
        }
    }
}
