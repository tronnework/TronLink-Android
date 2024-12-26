package com.tron.wallet.business.ledger.manage;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcEquipmentManageBinding;
import com.tron.wallet.ledger.bleclient.BleClientManager;
import com.tronlinkpro.wallet.R;
public class EquipmentManageActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    AppBarLayout appBar;
    private AcEquipmentManageBinding binding;
    SimpleDraweeViewGif gifImage;
    private EquipmentFragment mEquipmentFragment;
    private FragmentManager manager;
    CollapsingToolbarLayout toolBarLayout;
    Toolbar toolbar;
    View tvTutorial;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, EquipmentManageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        setBackground(getResources().getColor(R.color.white), R.mipmap.bg_createwallet);
        AcEquipmentManageBinding inflate = AcEquipmentManageBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    public void mappingId() {
        this.toolbar = this.binding.toolbar;
        this.tvTutorial = this.binding.tvTutorial;
        this.toolBarLayout = this.binding.toolbarLayout;
        this.gifImage = this.binding.gifImage;
        this.appBar = this.binding.appBar;
    }

    @Override
    protected void processData() {
        setSupportActionBar(this.toolbar);
        this.toolBarLayout.setTitle(getString(R.string.ledger));
        this.gifImage.setGif(R.drawable.ledger_title, 1);
        this.mEquipmentFragment = new EquipmentFragment();
        initViews();
    }

    private void initViews() {
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initViews$0(view);
            }
        });
        this.tvTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_MANAGE_TUTORIAL);
                UIUtils.toHowUseLedger(EquipmentManageActivity.this);
            }
        });
        this.manager = getSupportFragmentManager();
        this.mEquipmentFragment = new EquipmentFragment();
        this.manager.beginTransaction().replace(R.id.content, this.mEquipmentFragment).commitAllowingStateLoss();
    }

    public void lambda$initViews$0(View view) {
        exit();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        EquipmentFragment equipmentFragment = this.mEquipmentFragment;
        if (equipmentFragment != null) {
            equipmentFragment.onRequestPermissionResult(i, strArr, iArr);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BleClientManager.getInstance().destroyClient();
        this.binding = null;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }
}
