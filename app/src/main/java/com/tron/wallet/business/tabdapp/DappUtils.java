package com.tron.wallet.business.tabdapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabdapp.dapphistory.DAppDataManager;
import com.tron.wallet.business.tabdapp.dappsearch.DappSearchPresenter;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.DappOptions;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.bean.update.UploadHistoryResponse;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.components.dialog.ShowContentCopyDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.interfaces.OnConfirmListener;
import com.tron.wallet.common.utils.ChainUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.DappAuthorizedController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.UUID;
import org.tron.walletserver.Wallet;
public class DappUtils {
    private Activity activity;
    private int classifyId;
    private String horiz;
    private int id;
    private String image;
    private boolean isAnonymous;
    private int isWhite;
    private Wallet mWallet;
    private String name;
    private String slogan;
    private String url;
    private boolean isSearchUrl = false;
    private RxManager rxManager = new RxManager();

    public void setSearchUrl(boolean z) {
        this.isSearchUrl = z;
    }

    public DappUtils(Activity activity) {
        this.activity = activity;
    }

    public void destory() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }

    public void checkAccount(String str, String str2, String str3, int i, int i2, boolean z, String str4) {
        this.name = str;
        this.url = str2;
        this.image = str3;
        this.classifyId = i2;
        this.id = i;
        this.isAnonymous = z;
        this.slogan = str4;
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mWallet = selectedWallet;
        if (selectedWallet == null) {
            return;
        }
        if (selectedWallet.isShieldedWallet()) {
            showShieldDialog();
        } else {
            checkAuthorized();
        }
    }

    public void checkAccount(String str, String str2, String str3, int i, int i2, boolean z, String str4, int i3) {
        this.isWhite = i3;
        checkAccount(str, str2, str3, i, i2, z, str4);
    }

    private void checkAuthorized() {
        if (!SpAPI.THIS.getDappName(this.name)) {
            showDialogDapp();
            return;
        }
        initHitoryUpload();
        toWebView();
    }

    public void init(String str, String str2, String str3, int i, int i2, boolean z, String str4) {
        this.name = str;
        this.url = str2;
        this.image = str3;
        this.classifyId = i2;
        this.id = i;
        this.isAnonymous = z;
        this.slogan = str4;
        this.mWallet = WalletUtils.getSelectedWallet();
    }

    public void assetsCheckAuthorized(String str, final OnConfirmListener onConfirmListener) {
        String str2;
        try {
            str2 = Uri.parse(DappSearchPresenter.getFixedUrl(str)).getHost();
        } catch (Exception e) {
            LogUtils.e(e);
            str2 = "";
        }
        if (!StringTronUtil.isEmpty(str2) && DappAuthorizedController.queryIsAuthorized(str2)) {
            showDialogDapp(onConfirmListener);
            return;
        }
        String format = String.format(this.activity.getString(R.string.asset_scan_unknown_link_content), str);
        SpannableString spannableString = new SpannableString(format);
        int indexOf = format.indexOf(str);
        spannableString.setSpan(new StyleSpan(1), indexOf, str.length() + indexOf, 17);
        spannableString.setSpan(new ForegroundColorSpan(this.activity.getResources().getColor(R.color.black_23)), indexOf, str.length() + indexOf, 33);
        Activity activity = this.activity;
        ShowContentCopyDialog.showUp(activity, activity.getString(R.string.asset_scan_unknown_link_title), spannableString, null, this.activity.getString(R.string.continue_visit), new OnConfirmListener() {
            @Override
            public void onClick() {
                initHitoryUpload();
                OnConfirmListener onConfirmListener2 = onConfirmListener;
                if (onConfirmListener2 != null) {
                    onConfirmListener2.onClick();
                }
            }
        }, null, true);
    }

    private void showShieldDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.activity);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.dg_shield_dapp).build();
        View view = builder.getView();
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck);
        ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
            }
        });
        ((TextView) view.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
                toSelectAccount();
            }
        });
        build.show();
    }

    public void toSelectAccount() {
        if (!SpAPI.THIS.getDappName(this.name)) {
            showDialogDapp();
        } else {
            toWebView();
        }
    }

    private void showDialogDapp() {
        showDialogDapp(null);
    }

    private void showDialogDapp(final OnConfirmListener onConfirmListener) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.activity);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.dapp_dialog).build();
        View view = builder.getView();
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck);
        ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
            }
        });
        ((TextView) view.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
                SpAPI.THIS.setDappName(name);
                initHitoryUpload();
                OnConfirmListener onConfirmListener2 = onConfirmListener;
                if (onConfirmListener2 != null) {
                    onConfirmListener2.onClick();
                } else {
                    toWebView();
                }
            }
        });
        build.show();
    }

    public void initHitoryUpload() {
        if (this.classifyId != 0) {
            UploadHistoryResponse uploadHistoryResponse = new UploadHistoryResponse();
            uploadHistoryResponse.setDapp_name(this.name);
            uploadHistoryResponse.setDapp_id(this.id);
            this.rxManager.post(Event.DAPP_CLICK_HISTORY, uploadHistoryResponse);
        }
    }

    protected void toWebView() {
        String str;
        DAppDataManager.getInstance().clearDataCache();
        String uuid = UUID.randomUUID().toString();
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null) {
            DAppDataManager.getInstance().putData(uuid, DAppDataManager.getInstance().getBuilder().setAddress(selectedWallet.getAddress()).setId(this.id).setClassifyId(this.classifyId).setName(this.name).setHomeUrl(this.url).setImageUrl(this.image).setIsAnonymous(this.isAnonymous ? 1 : 0).setSlogan(this.slogan).buildDAppDataBean());
        }
        this.rxManager.post(Event.DAPP_SEARCH_TO_WEB, this.name);
        if (this.url.contains(ChainUtil.Request_HTTP) || this.url.contains("https://")) {
            str = this.url;
        } else {
            str = "https://" + this.url;
        }
        DappOptions.DappOptionsBuild dappOptionsBuild = new DappOptions.DappOptionsBuild();
        dappOptionsBuild.addIcon(this.image);
        if (this.isAnonymous) {
            dappOptionsBuild.addInjectZTron(true);
        }
        WebOptions.WebOptionsBuild webOptionsBuild = new WebOptions.WebOptionsBuild();
        try {
            webOptionsBuild.addCode(this.isWhite);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        webOptionsBuild.addDappOptions(dappOptionsBuild.build());
        CommonWebActivityV3.start((Context) this.activity, str, this.name, true, webOptionsBuild.build());
    }
}
