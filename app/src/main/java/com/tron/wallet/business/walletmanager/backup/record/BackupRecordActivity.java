package com.tron.wallet.business.walletmanager.backup.record;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordContract;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.databinding.AcBackupRecordBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class BackupRecordActivity extends BaseActivity<BackupRecordPresenter, BackupRecordModel> implements BackupRecordContract.View {
    public static String NEED_SHOW_CURRENT_TAG = "need_show_current_tag";
    private BackupRecordAdapter backupRecordAdapter;
    private AcBackupRecordBinding binding;
    private boolean isShieldManage;
    private boolean mNeedShowCurrentTag;
    RecyclerView mRecyclerView;
    View noDataView;

    public static void start(Context context, boolean z, boolean z2) {
        Intent intent = new Intent(context, BackupRecordActivity.class);
        intent.putExtra(NEED_SHOW_CURRENT_TAG, z);
        intent.putExtra(TronConfig.SHIELD_IS_TRC20, z2);
        context.startActivity(intent);
    }

    public static void start(Context context) {
        start(context, false, false);
    }

    @Override
    protected void setLayout() {
        AcBackupRecordBinding inflate = AcBackupRecordBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setHeaderBar(getString(R.string.backup_records_title));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.mRecyclerView = this.binding.backupList;
        this.noDataView = this.binding.noDataView;
    }

    @Override
    protected void processData() {
        this.mNeedShowCurrentTag = getIntent().getBooleanExtra(NEED_SHOW_CURRENT_TAG, false);
        this.isShieldManage = getIntent().getBooleanExtra(TronConfig.SHIELD_IS_TRC20, false);
        ((BackupRecordPresenter) this.mPresenter).getBackupRecords();
    }

    @Override
    public void updateUI(List<BackupRecordBean> list) {
        BackupRecordAdapter backupRecordAdapter = new BackupRecordAdapter(this, list, this.mNeedShowCurrentTag, this.isShieldManage);
        this.backupRecordAdapter = backupRecordAdapter;
        this.mRecyclerView.setAdapter(backupRecordAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.mRecyclerView.setVisibility(View.VISIBLE);
        this.noDataView.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataView() {
        this.mRecyclerView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        exit();
    }
}
