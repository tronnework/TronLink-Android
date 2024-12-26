package com.tron.wallet.business.addassets2;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.addassets2.NewAssetsContract;
import com.tron.wallet.business.addassets2.adapter.AssetsListAdapter;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.interfaces.CloseClickListener;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.databinding.ItemAssetsBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class NewAssetsActivity extends BaseActivity<NewAssetsPresenter, NewAssetsModel> implements NewAssetsContract.View {
    private static final String TAG = "NewAssetsActivity";
    private AssetsListAdapter adapter;
    private ItemAssetsBinding binding;
    PtrHTFrameLayout dataLayout;
    RecyclerView mRecyclerView;
    TextView noDataDetailTextView;
    View noDataLayout;
    TextView noDataTextView;
    NoNetView noNetView;
    private boolean isClose = false;
    private CloseClickListener closeListener = new CloseClickListener() {
        @Override
        public void onClose() {
            isClose = true;
        }
    };

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, NewAssetsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ItemAssetsBinding inflate = ItemAssetsBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setHeaderBar(getResources().getString(R.string.assets_new), getResources().getString(R.string.ignore_all), getResources().getColor(R.color.blue_13));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.mRecyclerView = this.binding.rcList;
        this.dataLayout = this.binding.rcFrame;
        this.noDataLayout = this.binding.llNodata;
        this.noDataTextView = this.binding.tvNoData;
        this.noDataDetailTextView = this.binding.tvNoDataDetail;
        this.noNetView = this.binding.noNetView;
    }

    @Override
    protected void processData() {
        dismissLoadingPage();
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        AssetsListAdapter assetsListAdapter = new AssetsListAdapter(this, new AssetsListAdapter.ItemCallback() {
            @Override
            public void onItemLongClicked(View view, TokenBean tokenBean, int[] iArr, int i) {
                AssetsListAdapter.ItemCallback.-CC.$default$onItemLongClicked(this, view, tokenBean, iArr, i);
            }

            @Override
            public void onItemClicked(TokenBean tokenBean, int i) {
                ((NewAssetsPresenter) mPresenter).showAssetsDetail(tokenBean);
            }

            @Override
            public void onItemSelected(TokenBean tokenBean, int i) {
                ((NewAssetsPresenter) mPresenter).followAssets(tokenBean, i);
            }
        });
        this.adapter = assetsListAdapter;
        this.mRecyclerView.setAdapter(assetsListAdapter);
        ((SimpleItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        ((NewAssetsPresenter) this.mPresenter).getNewAssets();
    }

    public void lambda$processData$0(View view) {
        ((NewAssetsPresenter) this.mPresenter).getNewAssets();
    }

    @Override
    public void updateAssets(List<TokenBean> list) {
        this.adapter.notifyDataChanged(list);
    }

    @Override
    public void updateAssetsState(TokenBean tokenBean, int i) {
        this.adapter.notifyDataChanged(tokenBean, i);
    }

    @Override
    public void showNoDataView() {
        this.dataLayout.setVisibility(View.GONE);
        this.noDataLayout.setVisibility(View.VISIBLE);
        this.noDataTextView.setText(getResources().getString(R.string.assets_no_new));
        this.noDataDetailTextView.setText(getResources().getString(R.string.assets_no_new_detail));
        hideHeaderBarCommonRight();
    }

    @Override
    public void updateComplete() {
        this.dataLayout.refreshComplete();
    }

    @Override
    public void updateAssetsPrice() {
        this.adapter.notifyPriceChanged();
    }

    @Override
    public void showAssetsNoIgnore() {
        toast(getResources().getString(R.string.assets_no_ignore));
    }

    @Override
    public void showAssetsAddedView() {
        toast(getResources().getString(R.string.assets_added_to_home), R.mipmap.toast_icon_positive);
        FirebaseAnalytics.getInstance(getIContext()).logEvent("Click_new_asset_follow_token", null);
    }

    @Override
    public void onRightButtonClick() {
        super.onRightButtonClick();
        ((NewAssetsPresenter) this.mPresenter).ignoreAllNewAssets();
        FirebaseAnalytics.getInstance(getIContext()).logEvent("Click_new_asset_ignore_all", null);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.mPresenter != 0 && ((NewAssetsPresenter) this.mPresenter).isFirstEnter()) {
            updateStartView();
        }
    }

    private void updateStartView() {
        showAddPopWindow();
    }

    public void showNoReminderPopWindow() {
        if (this.isClose) {
            return;
        }
        PopupWindowUtil.showFirstEnterNoreminderPopupWindow(getIContext(), getHeaderHolder().tvCommonRight, this.closeListener);
    }

    private void showAddPopWindow() {
        if (this.isClose) {
            return;
        }
        PopupWindowUtil.showFirstEnterAddAssetPopupWindow(getIContext(), getHeaderHolder().tvCommonRight, this.closeListener).setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                showNoReminderPopWindow();
            }
        });
    }
}
