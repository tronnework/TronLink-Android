package com.tron.wallet.business.ledger.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.ledger.manage.EquipmentBean;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivitySearchLedgerBinding;
import com.tron.wallet.ledger.bleclient.BleClientManager;
import com.tronlinkpro.wallet.R;
public class SearchLedgerActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String KEY_NEXT_ACTION = "from";
    public static final int NEXT_ACTION_BACK_SIGN = 1;
    public static final int NEXT_ACTION_IMPORT_ADDRESS = 0;
    ActivitySearchLedgerBinding binding;
    private FragmentManager fragmentManager;
    private int nextAction;
    private PairedLedgerFragment pairedLedgerFragment;
    private SearchLedgerFragment searchLedgerFragment;

    public void mappingId() {
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, SearchLedgerActivity.class));
    }

    public static void start(Context context, int i) {
        Intent intent = new Intent(context, SearchLedgerActivity.class);
        intent.putExtra("from", i);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        this.nextAction = getIntent().getIntExtra("from", 0);
        ActivitySearchLedgerBinding inflate = ActivitySearchLedgerBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setHeaderBar(getResources().getString(R.string.add_device_title));
        mappingId();
        setCommonTitle2(this.nextAction == 1 ? "" : getString(R.string.step_1_2));
        setCommonRight2(getString(R.string.tutorial));
        TextView textView = getHeaderHolder().tvCommonRight2;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.toHowUseLedger(SearchLedgerActivity.this);
            }
        });
        textView.setText(R.string.tutorial);
        textView.setBackground(getResources().getDrawable(R.drawable.roundborder_cdd1da_8));
        textView.setVisibility(View.VISIBLE);
        textView.setPadding(UIUtils.dip2px(10.0f), UIUtils.dip2px(2.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(2.0f));
        textView.setTextColor(getResources().getColor(R.color.black_23));
        getHeaderHolder().tvCommonTitle.setTextSize(22.0f);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onRightButtonClick() {
        super.onRightButtonClick();
    }

    @Override
    public void onCreate2(Bundle bundle) {
        super.onCreate2(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.fragmentManager = supportFragmentManager;
        if (bundle != null) {
            this.searchLedgerFragment = (SearchLedgerFragment) supportFragmentManager.getFragment(bundle, "SearchLedgerFragment");
            this.pairedLedgerFragment = (PairedLedgerFragment) this.fragmentManager.getFragment(bundle, "PairedLedgerFragment");
        }
        if (this.searchLedgerFragment == null) {
            this.searchLedgerFragment = SearchLedgerFragment.newInstance();
        }
        PairedLedgerFragment pairedLedgerFragment = this.pairedLedgerFragment;
        if (pairedLedgerFragment != null) {
            if (pairedLedgerFragment.isAdded()) {
                this.fragmentManager.beginTransaction().show(this.pairedLedgerFragment).hide(this.searchLedgerFragment).commitAllowingStateLoss();
            } else {
                this.fragmentManager.beginTransaction().add(R.id.ll_ledger, this.pairedLedgerFragment).show(this.pairedLedgerFragment).hide(this.searchLedgerFragment).commitAllowingStateLoss();
            }
        } else if (this.searchLedgerFragment.isAdded()) {
            this.fragmentManager.beginTransaction().show(this.searchLedgerFragment).commitAllowingStateLoss();
        } else {
            this.fragmentManager.beginTransaction().add(R.id.ll_ledger, this.searchLedgerFragment).show(this.searchLedgerFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        SearchLedgerFragment searchLedgerFragment = this.searchLedgerFragment;
        if (searchLedgerFragment != null) {
            this.fragmentManager.putFragment(bundle, "SearchLedgerFragment", searchLedgerFragment);
        }
        PairedLedgerFragment pairedLedgerFragment = this.pairedLedgerFragment;
        if (pairedLedgerFragment == null || !pairedLedgerFragment.isAdded()) {
            return;
        }
        this.fragmentManager.putFragment(bundle, "PairedLedgerFragment", this.pairedLedgerFragment);
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.ENTER_LEDGER_FLOW_ADD_NEW_DEVICE);
    }

    public void onDeviceConnected(EquipmentBean equipmentBean) {
        if (this.pairedLedgerFragment != null) {
            this.fragmentManager.beginTransaction().remove(this.pairedLedgerFragment).commitAllowingStateLoss();
        }
        this.pairedLedgerFragment = PairedLedgerFragment.newInstance(equipmentBean, this.nextAction);
        this.fragmentManager.beginTransaction().add(R.id.ll_ledger, this.pairedLedgerFragment).show(this.pairedLedgerFragment).disallowAddToBackStack().hide(this.searchLedgerFragment).commitAllowingStateLoss();
    }

    public void toSearchView() {
        FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        beginTransaction.remove(this.pairedLedgerFragment);
        beginTransaction.show(this.searchLedgerFragment).commitAllowingStateLoss();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        SearchLedgerFragment searchLedgerFragment = this.searchLedgerFragment;
        if (searchLedgerFragment != null) {
            searchLedgerFragment.onRequestPermissionResult(i, strArr, iArr);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.nextAction == 1) {
            BleClientManager.getInstance().destroyClient();
        }
        this.binding = null;
    }
}
