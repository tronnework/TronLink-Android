package com.tron.wallet.business.tabmy.myhome.addressbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.business.tabmy.myhome.addressbook.AddressBookContract;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.AcAddressBookBinding;
import com.tronlinkpro.wallet.R;
public class AddressBookActivity extends BaseActivity<AddressBookPresenter, AddressBookModel> implements AddressBookContract.View {
    public static final String TAG_FROM_TYPE = "type_from_type";
    public static final String TYPE_FROM_MY = "type_from_my";
    public static final String TYPE_SELECT_ADDRESS = "type_select_address";
    private AcAddressBookBinding binding;
    Button btNext;
    private int oldHeight;
    private int oldWidth;
    RecyclerView rcList;
    View rlNoData;

    @Override
    public RecyclerView getRcList() {
        return this.rcList;
    }

    @Override
    protected void setLayout() {
        AcAddressBookBinding inflate = AcAddressBookBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 3);
        setHeaderBar(getString(R.string.address_book));
    }

    public void mappingId() {
        this.rcList = this.binding.rcList;
        this.btNext = this.binding.btNext;
        this.rlNoData = this.binding.rlNoData;
    }

    @Override
    public void setHeaderBarAndRightImage(String str, int i) {
        if (getHeaderHolder() != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getHeaderHolder().ivQr.getLayoutParams();
            this.oldWidth = layoutParams.width;
            this.oldHeight = layoutParams.height;
            setHeaderIconSize((int) Math.ceil(this.oldWidth * 1.2f), (int) Math.ceil(this.oldHeight * 1.2f));
        }
        super.setHeaderBarAndRightImage(str, i);
    }

    private void setHeaderIconSize(int i, int i2) {
        getHeaderHolder().ivQr.getLayoutParams().width = i;
        getHeaderHolder().ivQr.getLayoutParams().height = i2;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        int i = this.oldWidth;
        int i2 = this.oldHeight;
        if (i * i2 != 0) {
            setHeaderIconSize(i, i2);
        }
    }

    @Override
    protected void processData() {
        ((AddressBookPresenter) this.mPresenter).addSome();
        this.btNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((AddressBookPresenter) mPresenter).clickAddAddress();
            }
        });
    }

    public static void startForResult(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, AddressBookActivity.class);
        intent.putExtra(TAG_FROM_TYPE, str);
        activity.startActivityForResult(intent, i);
    }

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, AddressBookActivity.class);
        intent.putExtra(TAG_FROM_TYPE, str);
        context.startActivity(intent);
    }

    public static void start(Context context, BaseFragment baseFragment, String str, int i) {
        Intent intent = new Intent(context, AddressBookActivity.class);
        intent.putExtra(TAG_FROM_TYPE, str);
        baseFragment.goForResult(intent, i);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle, persistableBundle);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 2025) {
            showLoadingPage();
            ((AddressBookPresenter) this.mPresenter).getData();
        }
    }

    @Override
    public Intent getIIntent() {
        return getIntent();
    }

    @Override
    public void showNoData(boolean z) {
        this.rlNoData.setVisibility(z ? View.VISIBLE : View.GONE);
        this.rcList.setVisibility(z ? View.GONE : View.VISIBLE);
    }
}
