package com.tron.wallet.business.tabmy.advancedfeatures.export;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.widget.NestedScrollView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.FileUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabmy.advancedfeatures.export.bean.ExportWalletBean;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.Md5Util;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.databinding.AcExportWatchWalletBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.cli.HelpFormatter;
import org.tron.common.utils.GsonFormatUtils;
import org.tron.net.CipherException;
import org.tron.walletserver.DuplicateNameException;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class ExportWatchWalletActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private static final String FILE_NAME = "tronlink-";
    private AcExportWatchWalletBinding binding;
    View btExport;
    View btImport;
    private String fileName;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;
    private PermissionHelper mPermissionHelper;
    private boolean threadWait = false;
    private int successLedgerNum = 0;
    private int successWatchNum = 0;
    private int successWatchColdNum = 0;

    @Override
    protected void setLayout() {
        AcExportWatchWalletBinding inflate = AcExportWatchWalletBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        NestedScrollView root = inflate.getRoot();
        mappingId();
        setView(root, 1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.btExport = this.binding.btExport;
        this.btImport = this.binding.btImport;
    }

    @Override
    protected void processData() {
        this.btExport.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                mPermissionHelper = new PermissionHelper(ExportWatchWalletActivity.this, new PermissionInterface() {
                    @Override
                    public int getPermissionsRequestCode() {
                        return 5001;
                    }

                    @Override
                    public String[] getPermissions() {
                        return new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
                    }

                    @Override
                    public void requestPermissionsSuccess() {
                        exportWatchWallet();
                    }

                    @Override
                    public void requestPermissionsFail() {
                        toast(getString(R.string.error_permission1));
                    }
                });
                if (Build.VERSION.SDK_INT < 33) {
                    mPermissionHelper.requestPermissions();
                } else {
                    exportWatchWallet();
                }
            }
        });
        this.btImport.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                mPermissionHelper = new PermissionHelper(ExportWatchWalletActivity.this, new PermissionInterface() {
                    @Override
                    public int getPermissionsRequestCode() {
                        return 5000;
                    }

                    @Override
                    public String[] getPermissions() {
                        return new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
                    }

                    @Override
                    public void requestPermissionsSuccess() {
                        toFileManager();
                    }

                    @Override
                    public void requestPermissionsFail() {
                        toast(getString(R.string.error_permission1));
                    }
                });
                if (Build.VERSION.SDK_INT < 33) {
                    mPermissionHelper.requestPermissions();
                } else {
                    toFileManager();
                }
            }
        });
        this.intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                if (activityResult.getResultCode() == -1) {
                    try {
                        Intent data = activityResult.getData();
                        if (data == null) {
                            return;
                        }
                        importWatchWallet(readFileUri(data.getData()));
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
            }
        });
    }

    public void toFileManager() {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"application/pdf", "application/vnd.oasis.opendocument.text", "text/plain"});
        this.intentActivityResultLauncher.launch(intent);
    }

    public String getFileName() {
        String dateToString = DateUtils.dateToString(new Date(), "yyyy-MM-dd-HH-mm");
        return FILE_NAME + dateToString + ".txt";
    }

    private boolean writeFile(String str, String str2) {
        return FileUtils.writeFile(str, new File(FileUtils.getRootDir(getIContext()), str2), false);
    }

    public java.lang.String readFileUri(android.net.Uri r7) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabmy.advancedfeatures.export.ExportWatchWalletActivity.readFileUri(android.net.Uri):java.lang.String");
    }

    public void exportWatchWallet() {
        if (this.threadWait) {
            Toast(getString(R.string.wallet_exporting));
            return;
        }
        this.threadWait = true;
        showLoadingDialog(getString(R.string.wallet_exporting));
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$exportWatchWallet$1();
            }
        });
    }

    public void lambda$exportWatchWallet$1() {
        String str;
        try {
            this.successLedgerNum = 0;
            this.successWatchNum = 0;
            this.successWatchColdNum = 0;
            ArrayList arrayList = new ArrayList();
            List<Wallet> allWallets = WalletUtils.getAllWallets();
            ExportWalletBean exportWalletBean = new ExportWalletBean();
            for (Wallet wallet : allWallets) {
                try {
                    if (wallet.isWatchOnly() && !wallet.isShieldedWallet() && !wallet.isSamsungWallet()) {
                        ExportWalletBean.WatchWallet watchWallet = new ExportWalletBean.WatchWallet();
                        watchWallet.setWalletName(wallet.getWalletName());
                        watchWallet.setWalletAddress(wallet.getAddress());
                        watchWallet.setCreateType(wallet.getCreateType() == -1 ? 4 : wallet.getCreateType());
                        watchWallet.setMnemonicPath(wallet.getMnemonicPathString());
                        arrayList.add(watchWallet);
                        if (wallet.isLedgerHDWallet()) {
                            this.successLedgerNum++;
                        } else if (wallet.isWatchCold()) {
                            this.successWatchColdNum++;
                        } else {
                            this.successWatchNum++;
                        }
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            if (arrayList.size() != 0) {
                exportWalletBean.setWatchWallets(arrayList);
                exportWalletBean.setWatchWalletsNum(arrayList.size());
                try {
                    str = Md5Util.md5(GsonUtils.toGsonString(exportWalletBean));
                } catch (Exception e2) {
                    LogUtils.e(e2);
                    str = null;
                }
                exportWalletBean.setMd5(str);
                String gsonString = GsonUtils.toGsonString(exportWalletBean);
                LogUtils.w("jsonFile:" + gsonString);
                String fileName = getFileName();
                this.fileName = fileName;
                writeFile(gsonString, fileName);
            }
        } catch (Exception e3) {
            LogUtils.e(e3);
        }
        this.threadWait = false;
        dismissLoadingDialog();
        if (this.successWatchNum == 0 && this.successWatchColdNum == 0 && this.successLedgerNum == 0) {
            toast(getString(R.string.export_no_watch_wallet));
        } else {
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$exportWatchWallet$0();
                }
            });
        }
    }

    public void lambda$exportWatchWallet$0() {
        ExportResultActivity.startExportDone(this.mContext, this.successWatchNum, this.successWatchColdNum, this.successLedgerNum, this.fileName);
        exit();
    }

    public void importWatchWallet(final String str) {
        LogUtils.w("jsonFile:" + str);
        if (StringTronUtil.isEmpty(str)) {
            Toast(R.string.import_file_error);
        } else if (this.threadWait) {
            Toast(getString(R.string.wallet_importing));
        } else {
            final long currentTimeMillis = System.currentTimeMillis();
            this.threadWait = true;
            showLoadingDialog(getString(R.string.wallet_importing));
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$importWatchWallet$4(str, currentTimeMillis);
                }
            });
        }
    }

    public void lambda$importWatchWallet$4(String str, final long j) {
        final String str2;
        try {
            this.successLedgerNum = 0;
            this.successWatchNum = 0;
            this.successWatchColdNum = 0;
            ArrayList arrayList = new ArrayList();
            Set<String> allWalletsAddress = WalletUtils.getAllWalletsAddress();
            ExportWalletBean exportWalletBean = (ExportWalletBean) GsonUtils.gsonToBean(str, ExportWalletBean.class);
            String md5 = exportWalletBean.getMd5();
            LogUtils.w("jsonFile-md5:" + md5);
            exportWalletBean.setMd5(ExportWalletBean.md5Default);
            String md52 = Md5Util.md5(GsonUtils.toGsonString(exportWalletBean));
            LogUtils.w("jsonFile-currentWalletMd5:" + md52);
            if (md5 != null && md5.equals(md52)) {
                for (ExportWalletBean.WatchWallet watchWallet : exportWalletBean.getWatchWallets()) {
                    try {
                        String walletAddress = watchWallet.getWalletAddress();
                        String walletName = watchWallet.getWalletName();
                        String mnemonicPath = watchWallet.getMnemonicPath();
                        int createType = watchWallet.getCreateType();
                        if (createType == -1) {
                            createType = 4;
                        }
                        if (!StringTronUtil.isAddressValid(walletAddress)) {
                            LogUtils.w("Error Address:" + walletAddress);
                            arrayList.add(watchWallet);
                        } else if (allWalletsAddress.contains(walletAddress)) {
                            LogUtils.w(StringTronUtil.getResString(R.string.error_existwallet) + walletAddress);
                            arrayList.add(watchWallet);
                        } else if (!checkInputName(walletName)) {
                            LogUtils.w("Error walletName:" + walletName);
                            arrayList.add(watchWallet);
                        } else {
                            if (WalletUtils.existWallet(walletName)) {
                                if (StringTronUtil.getStringCount(walletName) > 20) {
                                    walletName = StringTronUtil.substring(walletName, 10);
                                }
                                String key = WalletNameGeneratorUtils.getKey(createType, false);
                                int index = WalletNameGeneratorUtils.getIndex(key);
                                StringBuilder sb = new StringBuilder();
                                sb.append(walletName);
                                sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                                int i = index + 1;
                                sb.append(index);
                                String sb2 = sb.toString();
                                while (WalletUtils.existWallet(sb2)) {
                                    sb2 = walletName + HelpFormatter.DEFAULT_OPT_PREFIX + i;
                                    i++;
                                }
                                SpAPI.THIS.setWalletNameIndex(key, i);
                                walletName = sb2;
                            }
                            String subName = subName(walletName);
                            if (!saveWalletWithObserve(subName, walletAddress, mnemonicPath, createType)) {
                                LogUtils.w("Error save:" + subName);
                                arrayList.add(watchWallet);
                            } else if (8 == createType) {
                                this.successLedgerNum++;
                            } else if (9 == createType) {
                                this.successWatchColdNum++;
                            } else {
                                this.successWatchNum++;
                            }
                        }
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
                try {
                    str2 = GsonUtils.toGsonString(arrayList);
                } catch (Exception e2) {
                    LogUtils.e(e2);
                    str2 = "";
                }
                runOnUIThread(new OnMainThread() {
                    @Override
                    public final void doInUIThread() {
                        lambda$importWatchWallet$3(str2, j);
                    }
                });
            } else {
                dismissLoadingDialog();
                Toast("Error md5");
            }
        } catch (Exception e3) {
            LogUtils.e(e3);
            dismissLoadingDialog();
            Toast(R.string.import_file_error);
        }
        this.threadWait = false;
    }

    public void lambda$importWatchWallet$3(final String str, long j) {
        LogUtils.w("errorList:" + str);
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis < 1000) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public final void run() {
                    lambda$importWatchWallet$2(str);
                }
            }, 1000 - currentTimeMillis);
            return;
        }
        dismissLoadingDialog();
        ExportResultActivity.startImportDone(this.mContext, this.successWatchNum, this.successWatchColdNum, this.successLedgerNum, str);
        exit();
    }

    public void lambda$importWatchWallet$2(String str) {
        dismissLoadingDialog();
        ExportResultActivity.startImportDone(this.mContext, this.successWatchNum, this.successWatchColdNum, this.successLedgerNum, str);
        exit();
    }

    private boolean saveWalletWithObserve(String str, String str2, String str3, int i) {
        if (i == 8 || i == 4 || i == 9) {
            Wallet wallet = new Wallet();
            wallet.setWalletName(str);
            wallet.setAddress(str2);
            wallet.setCreateTime(System.currentTimeMillis());
            wallet.setWatchOnly(true);
            wallet.setMnemonicPath(str3);
            wallet.setCreateType(i);
            wallet.setIconRes(UserIconRandom.THIS.random());
            if (8 == i) {
                if (StringTronUtil.isEmpty(str3)) {
                    return false;
                }
                try {
                    GsonFormatUtils.gsonToBean(str3, WalletPath.class);
                } catch (Exception e) {
                    LogUtils.e(e);
                    return false;
                }
            }
            try {
                WalletUtils.saveWatchOnly(wallet);
                WalletUtils.setSelectedWallet(str);
                WalletNameGeneratorUtils.finish(4, false);
                return true;
            } catch (CipherException e2) {
                LogUtils.e(e2);
                return false;
            } catch (DuplicateNameException e3) {
                LogUtils.e(e3);
                return false;
            } catch (InvalidNameException e4) {
                LogUtils.e(e4);
                return false;
            }
        }
        return false;
    }

    private boolean checkInputName(String str) {
        return !StringTronUtil.isEmpty(str) && StringTronUtil.validataLegalString2(str);
    }

    public String subName(String str) {
        try {
            String trim = str.trim();
            int i = 0;
            for (int i2 = 0; i2 < trim.length(); i2++) {
                char charAt = trim.charAt(i2);
                i = (charAt < ' ' || charAt > 'z') ? i + 2 : i + 1;
                if (i > 28) {
                    str = trim.substring(0, i2);
                }
            }
            return StringTronUtil.trimEnd(str.toCharArray());
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
            return str;
        }
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionHelper permissionHelper = this.mPermissionHelper;
        if (permissionHelper == null || !permissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        exit();
    }
}
