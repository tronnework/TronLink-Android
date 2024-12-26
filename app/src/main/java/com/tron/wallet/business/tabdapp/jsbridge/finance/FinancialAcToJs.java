package com.tron.wallet.business.tabdapp.jsbridge.finance;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.assetshome.AssetsPresenter;
import com.tron.wallet.business.finance.FinanceFragment;
import com.tron.wallet.business.finance.bean.SwitchWalletResult;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.FTronInput;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.RecordDetailInput;
import com.tron.wallet.business.transfer.TokenDetailActivity;
import com.tron.wallet.business.transfer.TransactionDetailActivity;
import com.tron.wallet.business.tronpower.management.ResourceManagementActivity;
import com.tron.wallet.business.walletmanager.selectwallet.finance.SelectWalletFinanceAllBottomPopup;
import com.tron.wallet.business.walletmanager.selectwallet.finance.SelectWalletFinanceSwitchBottomPopup;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.components.MyWebView;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import org.apache.commons.cli.HelpFormatter;
import org.tron.walletserver.Wallet;
public class FinancialAcToJs implements IFinacialJs {
    public static final String TAG = "FinacialAcToJs";
    protected Activity activity;
    protected FinancialModel finacialModel;
    protected Handler handler;
    protected String icon;
    private SelectWalletFinanceAllBottomPopup selectPop;
    private SelectWalletFinanceSwitchBottomPopup selectSwitchPop;
    protected String title;
    protected MyWebView webView;
    protected String weburl;
    private transient boolean switchWalletLock = false;
    private transient boolean selectWalletLock = false;

    public FinancialAcToJs(Activity activity, Handler handler, String str, MyWebView myWebView, String str2, String str3) {
        this.activity = activity;
        this.handler = handler;
        this.weburl = str;
        this.title = str2;
        this.icon = str3;
        this.webView = myWebView;
        this.finacialModel = new FinancialModel(myWebView, handler);
        LogUtils.d("FinacialAcToJs", "FinacialAcToJs");
    }

    @JavascriptInterface
    public void switchWallet(String str) {
        LogUtils.d("FinacialAcToJs", Keys.OV__switchWallet);
        Handler handler = this.handler;
        if (handler != null) {
            this.handler.sendMessage(handler.obtainMessage(257, str));
        }
    }

    @JavascriptInterface
    public void gotoNativeStake() {
        LogUtils.d("FinacialAcToJs", "gotoNativeStake");
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendEmptyMessage(FinanceFragment.GOTO_NATIVE_STAKE);
        }
    }

    @JavascriptInterface
    public String isShowFinanceAmount() {
        return GsonUtils.toGsonString(Boolean.valueOf(!this.activity.getSharedPreferences(AssetsPresenter.KEY_SP, 0).getBoolean(AssetsPresenter.KEY_ASSET_STATUS, false)));
    }

    @JavascriptInterface
    public void setShowFinanceAmount(boolean z) {
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(FinanceFragment.SHOW_FINANCE_AMOUNT, Boolean.valueOf(z)));
        }
    }

    @JavascriptInterface
    public String getCurrentFinanceAmount() {
        SwitchWalletResult switchWalletResult = new SwitchWalletResult();
        if (SpAPI.THIS.getFinanceIsAllAccount()) {
            switchWalletResult.setAll(true);
        } else {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            switchWalletResult.setAll(false);
            switchWalletResult.setName(selectedPublicWallet.getWalletName());
            switchWalletResult.setAddress(selectedPublicWallet.getAddress());
        }
        return GsonUtils.toGsonString(switchWalletResult);
    }

    @JavascriptInterface
    public void gotoFinanceHome() {
        LogUtils.d("FinacialAcToJs", "gotoFinanceHome");
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendEmptyMessage(259);
        }
    }

    @JavascriptInterface
    public int getStatusBarHeight() {
        return UIUtils.getStatusBarHeight();
    }

    @JavascriptInterface
    public int getStatusBarDPHeight() {
        return UIUtils.px2dip(UIUtils.getStatusBarHeight());
    }

    @JavascriptInterface
    public void sortFinanceProjectToken(int i) {
        Handler handler = this.handler;
        if (handler != null) {
            this.handler.sendMessage(handler.obtainMessage(FinanceFragment.FINANCE_SORT, Integer.valueOf(i)));
        }
    }

    @Override
    @JavascriptInterface
    public void getData(String str, String str2) {
        getData(str, null, str2);
    }

    @Override
    @JavascriptInterface
    public String getAddressList() {
        return GsonUtils.toGsonString(this.finacialModel.getAllNativeWallets());
    }

    @Override
    @JavascriptInterface
    public void getData(String str, String str2, String str3) {
        char c;
        Activity activity = this.activity;
        if (activity == null || activity.isDestroyed() || this.activity.isFinishing() || this.finacialModel.checkParamsEmpty(str, str3)) {
            return;
        }
        switch (str.hashCode()) {
            case -1408207997:
                if (str.equals(Keys.Key_assets)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1251756057:
                if (str.equals(Keys.Key__totalAssets)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -80104453:
                if (str.equals(Keys.Key_justLend)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -35207346:
                if (str.equals(Keys.Key_tokenFinancialList)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 232962778:
                if (str.equals(Keys.Key_myFinancialProjectList)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 254197370:
                if (str.equals(Keys.Key_myFinancialTokenList)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.finacialModel.getTotalAssets(str2, str3);
        } else if (c == 1) {
            this.finacialModel.getTokenFinancialList(str2, str3);
        } else if (c == 3) {
            this.finacialModel.getMyTokenFinancialList(str2, str3);
        } else if (c != 4) {
        } else {
            this.finacialModel.getMyFinancialProjectList(str2, str3);
        }
    }

    @Override
    @JavascriptInterface
    public void openView(String str, String str2, String str3) {
        Activity activity = this.activity;
        if (activity == null || activity.isDestroyed() || this.activity.isFinishing()) {
            return;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1983070683:
                if (str.equals(Keys.OV__resources)) {
                    c = 0;
                    break;
                }
                break;
            case -1897408618:
                if (str.equals(Keys.OV__stake)) {
                    c = 1;
                    break;
                }
                break;
            case -1601743737:
                if (str.equals(Keys.OV__stakeAndVote)) {
                    c = 2;
                    break;
                }
                break;
            case -1462634904:
                if (str.equals(Keys.OV__alertMask)) {
                    c = 3;
                    break;
                }
                break;
            case 3417674:
                if (str.equals("open")) {
                    c = 4;
                    break;
                }
                break;
            case 3625706:
                if (str.equals(Keys.OV__vote)) {
                    c = 5;
                    break;
                }
                break;
            case 1339670605:
                if (str.equals(Keys.OV__switchWallet)) {
                    c = 6;
                    break;
                }
                break;
            case 1399664642:
                if (str.equals(Keys.OV__recordDetail)) {
                    c = 7;
                    break;
                }
                break;
            case 2137638933:
                if (str.equals(Keys.OV__selectWallet)) {
                    c = '\b';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                LogUtils.d("FinacialAcToJs", "OV__resources param: " + str2);
                ResourceManagementActivity.start(this.activity);
                return;
            case 1:
                LogUtils.d("FinacialAcToJs", "OV__stake param: " + str2);
                Message obtain = Message.obtain();
                obtain.obj = str3;
                obtain.what = FinanceFragment.STAKE;
                Handler handler = this.handler;
                if (handler != null) {
                    handler.sendMessage(obtain);
                    return;
                }
                return;
            case 2:
                LogUtils.d("FinacialAcToJs", "OV__stakeAndVote param: " + str2);
                stakeAndVote(str3, str2);
                return;
            case 3:
                LogUtils.d("FinacialAcToJs", "OV__AlertMask param: " + str2);
                alertMask(str2);
                return;
            case 4:
                LogUtils.d("FinacialAcToJs", "OV__open param: " + str2);
                open(str2, str3);
                return;
            case 5:
                LogUtils.d("FinacialAcToJs", "OV__vote param: " + str2);
                Message obtain2 = Message.obtain();
                obtain2.obj = str3;
                obtain2.what = FinanceFragment.VOTE;
                this.handler.sendMessage(obtain2);
                return;
            case 6:
                LogUtils.d("FinacialAcToJs", "OV__switchWallet param: " + str2);
                if (this.switchWalletLock) {
                    return;
                }
                this.switchWalletLock = true;
                switchWallet(str2, str3);
                return;
            case 7:
                LogUtils.d("FinacialAcToJs", "OV__recordDetail param: " + str2);
                recordDetail(str2, str3);
                return;
            case '\b':
                LogUtils.d("FinacialAcToJs", "OV__selectWallet param: " + str2);
                if (this.selectWalletLock) {
                    return;
                }
                this.selectWalletLock = true;
                selectWallet(str2, str3);
                return;
            default:
                return;
        }
    }

    private void open(java.lang.String r4, java.lang.String r5) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.jsbridge.finance.FinancialAcToJs.open(java.lang.String, java.lang.String):void");
    }

    private void alertMask(String str) {
        FTronInput fTronInput;
        if (StringTronUtil.isEmpty(str)) {
            return;
        }
        try {
            fTronInput = (FTronInput) GsonUtils.gsonToBean(str, FTronInput.class);
        } catch (Exception e) {
            LogUtils.e(e);
            fTronInput = null;
        }
        if (fTronInput == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = Boolean.valueOf(fTronInput.isShow());
        obtain.what = 26;
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendMessage(obtain);
        }
    }

    private void recordDetail(String str, String str2) {
        this.finacialModel.checkParamsEmpty(str, str2);
        RecordDetailInput recordDetailInput = null;
        if (!StringTronUtil.isEmpty(str)) {
            try {
                recordDetailInput = (RecordDetailInput) GsonUtils.gsonToBean(str, RecordDetailInput.class);
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (recordDetailInput == null) {
                return;
            }
        }
        TokenBean tokenBean = new TokenBean();
        if (recordDetailInput.getJump() == 1) {
            TransactionHistoryBean transactionHistoryBean = new TransactionHistoryBean();
            transactionHistoryBean.setAmount(BigDecimalUtils.mul_(Double.valueOf(Math.pow(10.0d, recordDetailInput.getDecimals())), recordDetailInput.getAmount()).toPlainString());
            transactionHistoryBean.setHash(recordDetailInput.getHash());
            transactionHistoryBean.setMark(HelpFormatter.DEFAULT_OPT_PREFIX);
            transactionHistoryBean.setDecimals(recordDetailInput.getDecimals());
            transactionHistoryBean.setFrom(recordDetailInput.getFrom());
            transactionHistoryBean.setTo(recordDetailInput.getTo());
            tokenBean.type = recordDetailInput.getTokenType();
            tokenBean.shortName = recordDetailInput.getTokenSymbol();
            TransactionDetailActivity.start(this.activity, transactionHistoryBean, tokenBean, recordDetailInput.getAmount());
            return;
        }
        tokenBean.setType(0);
        TokenDetailActivity.start(this.activity, tokenBean);
    }

    private void stakeAndVote(final java.lang.String r4, java.lang.String r5) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.jsbridge.finance.FinancialAcToJs.stakeAndVote(java.lang.String, java.lang.String):void");
    }

    private void selectWallet(String str, final String str2) {
        FTronInput fTronInput;
        this.finacialModel.checkParamsEmpty(str, str2);
        if (!StringTronUtil.isEmpty(str)) {
            try {
                fTronInput = (FTronInput) GsonUtils.gsonToBean(str, FTronInput.class);
            } catch (Exception e) {
                LogUtils.e(e);
                fTronInput = null;
            }
            if (fTronInput == null) {
                this.selectWalletLock = false;
                return;
            }
            Wallet walletForAddress = StringTronUtil.isEmpty(fTronInput.getCurrentAddress()) ? null : WalletUtils.getWalletForAddress(fTronInput.getCurrentAddress());
            if (walletForAddress == null) {
                walletForAddress = WalletUtils.getSelectedPublicWallet();
            }
            Wallet wallet = walletForAddress;
            if (this.selectSwitchPop == null) {
                this.selectSwitchPop = SelectWalletFinanceSwitchBottomPopup.showPopup(this.activity, wallet, fTronInput.getTokenId(), "", fTronInput.getProjectId(), new SelectWalletFinanceSwitchBottomPopup.OnClickListener() {
                    @Override
                    public final void onClick(Wallet wallet2) {
                        lambda$selectWallet$0(str2, wallet2);
                    }
                }, new SelectWalletFinanceSwitchBottomPopup.OnDismissListener() {
                    @Override
                    public final void onDismiss() {
                        lambda$selectWallet$1(str2);
                    }
                });
                return;
            }
            return;
        }
        this.selectSwitchPop.dismiss();
        this.selectSwitchPop = null;
    }

    public void lambda$selectWallet$0(String str, Wallet wallet) {
        SwitchWalletResult switchWalletResult = new SwitchWalletResult();
        if (wallet == null) {
            switchWalletResult.setAll(true);
        } else {
            switchWalletResult.setAddress(wallet.getAddress());
            switchWalletResult.setName(wallet.getWalletName());
        }
        this.finacialModel.loadJs(str, GsonUtils.toGsonString(switchWalletResult));
        this.selectWalletLock = false;
        this.selectSwitchPop = null;
    }

    public void lambda$selectWallet$1(String str) {
        this.finacialModel.loadCancelJs(str);
        this.selectSwitchPop = null;
        this.selectWalletLock = false;
    }

    private void switchWallet(java.lang.String r4, final java.lang.String r5) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.jsbridge.finance.FinancialAcToJs.switchWallet(java.lang.String, java.lang.String):void");
    }

    public void lambda$switchWallet$2(String str, Wallet wallet) {
        SwitchWalletResult switchWalletResult = new SwitchWalletResult();
        if (wallet == null) {
            switchWalletResult.setAll(true);
            SpAPI.THIS.setFinanceIsAllAccount(true);
        } else {
            switchWalletResult.setAddress(wallet.getAddress());
            SpAPI.THIS.setFinanceIsAllAccount(false);
            switchWalletResult.setName(wallet.getWalletName());
        }
        this.finacialModel.loadJs(str, GsonUtils.toGsonString(switchWalletResult));
        this.switchWalletLock = false;
        this.selectPop = null;
    }

    public void lambda$switchWallet$3(String str) {
        this.finacialModel.loadCancelJs(str);
        this.selectPop = null;
        this.switchWalletLock = false;
    }

    public void clear() {
        this.finacialModel.clear();
    }
}
