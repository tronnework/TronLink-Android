package com.tron.wallet.business.tabmy.advancedfeatures.export;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.FileUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabmy.advancedfeatures.export.bean.ExportWalletBean;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityExportWalletResultBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class ExportResultActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private static final String KEY_FAILED_LIST = "failed_list";
    private static final String KEY_FILE_PATH = "file_path";
    private static final String KEY_LEDGER_WALLET_SIZE = "ledger_wallet_size";
    private static final String KEY_PAGE_TYPE = "page_type";
    private static final String KEY_WATCH_COLD_SIZE = "watch_cold_size";
    private static final String KEY_WATCH_WALLET_SIZE = "watch_wallet_size";
    public static final int TYPE_EXPORT = 0;
    public static final int TYPE_IMPORT = 1;
    private ActivityExportWalletResultBinding binding;
    View btnDone;
    View llFailedList;
    View llTitleView;
    View rlExportResult;
    View rlImportResult;
    RecyclerView rvFailed;
    TextView tvSubtitle;
    TextView tvSubtitleExport;
    TextView tvTitle;

    public static void startImportDone(Context context, int i, int i2, int i3, String str) {
        Intent intent = new Intent(context, ExportResultActivity.class);
        intent.putExtra(KEY_PAGE_TYPE, 1);
        intent.putExtra(KEY_WATCH_WALLET_SIZE, i);
        intent.putExtra(KEY_WATCH_COLD_SIZE, i2);
        intent.putExtra(KEY_LEDGER_WALLET_SIZE, i3);
        intent.putExtra(KEY_FAILED_LIST, str);
        context.startActivity(intent);
    }

    public static void startExportDone(Context context, int i, int i2, int i3, String str) {
        Intent intent = new Intent(context, ExportResultActivity.class);
        intent.putExtra(KEY_PAGE_TYPE, 0);
        intent.putExtra(KEY_WATCH_WALLET_SIZE, i);
        intent.putExtra(KEY_WATCH_COLD_SIZE, i2);
        intent.putExtra(KEY_LEDGER_WALLET_SIZE, i3);
        intent.putExtra(KEY_FILE_PATH, str);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityExportWalletResultBinding inflate = ActivityExportWalletResultBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.llTitleView = this.binding.llTitle;
        this.tvTitle = this.binding.tvTitle;
        this.tvSubtitle = this.binding.tvSubtitle;
        this.llFailedList = this.binding.llFailedList;
        this.rvFailed = this.binding.rvFailed;
        this.rlExportResult = this.binding.rlResultExport;
        this.rlImportResult = this.binding.rlResultImport;
        this.tvSubtitleExport = this.binding.tvSubtitleExport;
        this.btnDone = this.binding.btnDone;
    }

    @Override
    protected void processData() {
        int intExtra = getIntent().getIntExtra(KEY_PAGE_TYPE, 0);
        int intExtra2 = getIntent().getIntExtra(KEY_WATCH_WALLET_SIZE, 0);
        int intExtra3 = getIntent().getIntExtra(KEY_LEDGER_WALLET_SIZE, 0);
        int intExtra4 = getIntent().getIntExtra(KEY_WATCH_COLD_SIZE, 0);
        if (intExtra == 1) {
            this.rlExportResult.setVisibility(View.GONE);
            this.rlImportResult.setVisibility(View.VISIBLE);
            if (intExtra3 > 0) {
                this.tvSubtitle.setText(getString(R.string.export_result_subtitle, new Object[]{Integer.valueOf(intExtra2), Integer.valueOf(intExtra4), Integer.valueOf(intExtra3)}));
            } else {
                this.tvSubtitle.setText(getString(R.string.export_result_no_ledger, new Object[]{Integer.valueOf(intExtra2), Integer.valueOf(intExtra4)}));
            }
            String stringExtra = getIntent().getStringExtra(KEY_FAILED_LIST);
            List<ExportWalletBean.WatchWallet> arrayList = new ArrayList<>();
            boolean isEmpty = TextUtils.isEmpty(stringExtra);
            if (!isEmpty) {
                try {
                    arrayList = JSON.parseArray(stringExtra, ExportWalletBean.WatchWallet.class);
                    isEmpty = arrayList.isEmpty();
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.llTitleView.getLayoutParams();
            if (!isEmpty) {
                layoutParams.topMargin = UIUtils.dip2px(40.0f);
                layoutParams.removeRule(13);
                this.llTitleView.setLayoutParams(layoutParams);
                this.llFailedList.setVisibility(View.VISIBLE);
                updateFailedListUI(arrayList);
            } else {
                layoutParams.topMargin = 0;
                layoutParams.addRule(13);
                this.llTitleView.setLayoutParams(layoutParams);
                this.llFailedList.setVisibility(View.GONE);
            }
        } else if (intExtra == 0) {
            String stringExtra2 = getIntent().getStringExtra(KEY_FILE_PATH);
            if (stringExtra2 == null) {
                stringExtra2 = "";
            }
            String str = FileUtils.getRootDir(getIContext()) + "/" + stringExtra2;
            this.rlExportResult.setVisibility(View.VISIBLE);
            this.rlImportResult.setVisibility(View.GONE);
            if (intExtra3 > 0) {
                this.tvSubtitleExport.setText(getString(R.string.import_result_subtitle, new Object[]{Integer.valueOf(intExtra2), Integer.valueOf(intExtra4), Integer.valueOf(intExtra3), str}));
            } else {
                this.tvSubtitleExport.setText(getString(R.string.import_result_no_ledger, new Object[]{Integer.valueOf(intExtra2), Integer.valueOf(intExtra4), str}));
            }
        }
        this.btnDone.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                exit();
            }
        });
    }

    private void updateFailedListUI(List<ExportWalletBean.WatchWallet> list) {
        this.rvFailed.setLayoutManager(new WrapContentLinearLayoutManager(this, 1, false));
        this.rvFailed.setAdapter(new WalletAdapter(list));
    }

    public static class WalletItemHolder extends BaseViewHolder {
        private final TextView tvAddress;
        private final TextView tvName;

        WalletItemHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.tvAddress = (TextView) view.findViewById(R.id.tv_address);
        }

        void onBind(ExportWalletBean.WatchWallet watchWallet) {
            this.tvName.setText(watchWallet.getWalletName());
            TextView textView = this.tvAddress;
            textView.setText("(" + watchWallet.getWalletAddress() + ")");
        }
    }

    public static class WalletAdapter extends BaseQuickAdapter<ExportWalletBean.WatchWallet, WalletItemHolder> {
        public WalletAdapter(List<ExportWalletBean.WatchWallet> list) {
            super(R.layout.layout_item_export_wallet, list);
        }

        @Override
        public void convert(WalletItemHolder walletItemHolder, ExportWalletBean.WatchWallet watchWallet) {
            walletItemHolder.onBind(watchWallet);
        }
    }
}
