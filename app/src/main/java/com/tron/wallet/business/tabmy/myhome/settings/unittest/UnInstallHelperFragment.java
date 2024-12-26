package com.tron.wallet.business.tabmy.myhome.settings.unittest;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.alibaba.fastjson.JSONObject;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.FileUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.messagesign.DappLocalActivity;
import com.tron.wallet.business.tabmy.myhome.settings.UnitTestActivity;
import com.tron.wallet.business.tabmy.myhome.settings.unittest.UnInstallHelperFragment;
import com.tron.wallet.common.bean.DataTransferOutput;
import com.tron.wallet.common.config.AppConstant;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcUninstallhelperBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import j$.util.Collection;
import j$.util.Objects;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.tron.walletserver.DuplicateNameException;
public class UnInstallHelperFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private AcUninstallhelperBinding binding;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;
    TextView tvContent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                LogUtils.i(activityResult.getResultCode() + "");
                if (activityResult.getResultCode() != -1) {
                    return;
                }
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(getActivity().getContentResolver().openInputStream(activityResult.getData().getData()));
                    char[] cArr = new char[1024];
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read > 0) {
                            sb.append(cArr, 0, read);
                        } else {
                            loadData(sb.toString());
                            return;
                        }
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                    toast("file read failed");
                }
            }
        });
    }

    @Override
    protected void processData() {
        setType(1);
        setHeaderBar("import and export of all wallets");
    }

    public class fun2 extends NoDoubleClickListener2 {
        fun2() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            showLoading("startExport");
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    UnInstallHelperFragment.2.this.lambda$onNoDoubleClick$1();
                }
            });
        }

        public void lambda$onNoDoubleClick$1() {
            Set<String> walletNames = WalletUtils.getWalletNames();
            ArrayList arrayList = new ArrayList();
            for (String str : walletNames) {
                if (!TextUtils.isEmpty(str)) {
                    SharedPreferences sharedPreferences = mContext.getSharedPreferences(str, 0);
                    DataTransferOutput.WalletData walletData = new DataTransferOutput.WalletData();
                    walletData.is_watch_only_setup_key = sharedPreferences.getBoolean("is_watch_only_setup_key", false);
                    walletData.isshield_key = sharedPreferences.getBoolean("isshield_key", false);
                    walletData.backup_key = sharedPreferences.getBoolean("backup_key", false);
                    walletData.wallet_name_key = sharedPreferences.getString(DappLocalActivity.WALLET_NAME_KEY, "");
                    walletData.wallet_address_key = sharedPreferences.getString("wallet_address_key", "");
                    walletData.shield__ob_key = sharedPreferences.getString("shield__ob_key", "");
                    walletData.pub_key = sharedPreferences.getString("pub_key", "");
                    walletData.wallet_keystore_key = sharedPreferences.getString("wallet_keystore_key", "");
                    walletData.wallet_newmnemonic_key = sharedPreferences.getString("wallet_newmnemonic_key", "");
                    walletData.wallet_mnemonicpath_key = sharedPreferences.getString("wallet_mnemonicpath_key", "");
                    walletData.wallet_createtype_key = sharedPreferences.getInt("wallet_createtype_key", 0);
                    walletData.mnemonic_length = sharedPreferences.getInt("mnemonic_length", 0);
                    walletData.wallet_createtime_key = sharedPreferences.getLong("wallet_createtime_key", 0L);
                    arrayList.add(walletData);
                }
            }
            List<HdTronRelationshipBean> QueryAll = DaoUtils.getLightInstance().QueryAll(HdTronRelationshipBean.class);
            DataTransferOutput dataTransferOutput = new DataTransferOutput();
            dataTransferOutput.dbData = QueryAll;
            dataTransferOutput.walletData = arrayList;
            final String gsonString = GsonUtils.toGsonString(dataTransferOutput);
            FileUtils.writeFile(gsonString, new File(AppConstant.APK_FILE_PATH, "clipboard.txt"), false);
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    UnInstallHelperFragment.2.this.lambda$onNoDoubleClick$0(gsonString);
                }
            });
        }

        public void lambda$onNoDoubleClick$0(String str) {
            dismissLoading();
            UIUtils.copy(str);
            tvContent.setText(str);
            toast("copied");
        }
    }

    public void setClick() {
        this.binding.bt1.setOnClickListener(new fun2());
        this.binding.bt2.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("*/*");
                intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"application/pdf", "application/vnd.oasis.opendocument.text", "text/plain"});
                intentActivityResultLauncher.launch(intent);
            }
        });
        this.binding.bt3.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ClipData primaryClip = ((ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE)).getPrimaryClip();
                if (primaryClip == null || primaryClip.getItemCount() <= 0) {
                    return;
                }
                String charSequence = primaryClip.getItemAt(0).getText().toString();
                toast("Read complete, ready to import");
                loadData(charSequence);
            }
        });
    }

    public void loadData(final String str) {
        try {
            showLoading("importing");
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$loadData$2(str);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
            dismissLoading();
            toast("import failed" + e.getMessage());
        }
    }

    public void lambda$loadData$2(String str) {
        final boolean z;
        DataTransferOutput dataTransferOutput = (DataTransferOutput) JSONObject.parseObject(str, DataTransferOutput.class);
        if (dataTransferOutput.walletData != null && !dataTransferOutput.walletData.isEmpty()) {
            final Set<String> allWallets = SpAPI.THIS.getAllWallets();
            final Set<String> allWalletsAddress = WalletUtils.getAllWalletsAddress();
            Collection.-EL.stream(dataTransferOutput.walletData).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$loadData$0(allWallets, allWalletsAddress, (DataTransferOutput.WalletData) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        if (dataTransferOutput.dbData != null && !dataTransferOutput.dbData.isEmpty()) {
            try {
                z = DaoUtils.getLightInstance().insertMultObject(dataTransferOutput.dbData);
            } catch (Exception e) {
                LogUtils.e(e);
            }
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$loadData$1(z);
                }
            });
        }
        z = false;
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$loadData$1(z);
            }
        });
    }

    public void lambda$loadData$0(Set set, Set set2, DataTransferOutput.WalletData walletData) {
        String str = walletData.wallet_name_key;
        String str2 = walletData.wallet_address_key;
        if (set.contains(str) || set2.contains(str2)) {
            return;
        }
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(str, 0).edit();
        edit.putBoolean("is_watch_only_setup_key", walletData.is_watch_only_setup_key);
        edit.putBoolean("isshield_key", walletData.isshield_key);
        edit.putBoolean("backup_key", walletData.backup_key);
        edit.putString(DappLocalActivity.WALLET_NAME_KEY, walletData.wallet_name_key);
        edit.putString("wallet_address_key", walletData.wallet_address_key);
        edit.putString("shield__ob_key", walletData.shield__ob_key);
        edit.putString("pub_key", walletData.pub_key);
        edit.putString("wallet_keystore_key", walletData.wallet_keystore_key);
        edit.putString("wallet_newmnemonic_key", walletData.wallet_newmnemonic_key);
        edit.putString("wallet_mnemonicpath_key", walletData.wallet_mnemonicpath_key);
        edit.putInt("wallet_createtype_key", walletData.wallet_createtype_key);
        edit.putInt("mnemonic_length", walletData.mnemonic_length);
        edit.putLong("wallet_createtime_key", walletData.wallet_createtime_key);
        edit.commit();
        try {
            SpAPI.THIS.setWallet(str);
        } catch (DuplicateNameException e) {
            LogUtils.e(e);
        }
    }

    public void lambda$loadData$1(boolean z) {
        dismissLoading();
        LogUtils.i("dataTransferOutput;" + z);
        toast("import complete, about to restart");
        System.exit(1);
        System.gc();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AcUninstallhelperBinding inflate = AcUninstallhelperBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        setType(1);
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.tvContent = this.binding.tvContent;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        if (getActivity() instanceof UnitTestActivity) {
            ((UnitTestActivity) getActivity()).onLeftButtonClick();
        }
    }
}
