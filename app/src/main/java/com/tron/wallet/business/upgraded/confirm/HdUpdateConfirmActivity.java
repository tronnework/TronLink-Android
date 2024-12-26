package com.tron.wallet.business.upgraded.confirm;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.pending.AnimaUtil;
import com.tron.wallet.business.upgraded.adapter.UpgradeHdAdapter;
import com.tron.wallet.business.upgraded.adapter.UpgradeHdListAdapter;
import com.tron.wallet.business.upgraded.bean.UpgradeHdBean;
import com.tron.wallet.business.upgraded.confirm.HdUpdateConfirmContract;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcUpdateHd2Binding;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
public class HdUpdateConfirmActivity extends BaseActivity<HdUpdateConfirmPresenter, HdUpdateConfirmModel> implements HdUpdateConfirmContract.View {
    private AcUpdateHd2Binding binding;
    Button btNext;
    View contentView;
    private UpgradeHdBean currentHd;
    View hdItem;
    ImageView ivArrow;
    ImageView ivLoading;
    View ivSelectStatus;
    View loadingView;
    View mask;
    private ArrayList<UpgradeHdBean> nonHdList;
    private UpgradeHdBean nonHdSelected;
    RecyclerView rcContent;
    RecyclerView rcInnerContent;
    View rlArrow;
    TextView tvAssets;
    View tvCurrentHdTitle;
    TextView tvName;
    TextView tvRelated;
    View tvSelectHdTitle;
    private UpgradeHdAdapter upgradeHdAdapter;
    private UpgradeHdListAdapter upgradeHdListAdapter;

    @Override
    protected void setLayout() {
        AcUpdateHd2Binding inflate = AcUpdateHd2Binding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setHeaderBar(getString(R.string.change_hd_wallet_title));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.ivLoading = this.binding.ivLoading;
        this.loadingView = this.binding.loadingView;
        this.contentView = this.binding.contentView;
        this.mask = this.binding.itemUpgrade.mask;
        this.tvName = this.binding.itemUpgrade.tvName;
        this.tvAssets = this.binding.itemUpgrade.tvAssets;
        this.tvRelated = this.binding.itemUpgrade.tvRelated;
        this.rcInnerContent = this.binding.itemUpgrade.rcInnerContent;
        this.rlArrow = this.binding.itemUpgrade.rlArrow;
        this.ivArrow = this.binding.itemUpgrade.ivArrow;
        this.btNext = this.binding.btNext;
        this.rcContent = this.binding.rcContent;
        this.ivSelectStatus = this.binding.itemUpgrade.ivSelectStatus;
        this.tvCurrentHdTitle = this.binding.tvCurrentHdTitle;
        this.tvSelectHdTitle = this.binding.tvSelectHdTitle;
        this.hdItem = this.binding.itemUpgrade.root;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        exit();
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.CHANGE_HD_LIST_SHOW);
        AnimaUtil.animLoadingView(this.ivLoading);
        ((HdUpdateConfirmPresenter) this.mPresenter).getData();
    }

    @Override
    public void refreshData(List<UpgradeHdBean> list) {
        this.ivLoading.clearAnimation();
        this.loadingView.setVisibility(View.GONE);
        this.contentView.setVisibility(View.VISIBLE);
        refreshCurrentHd(list);
        refreshList(list);
        this.btNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                enabledButton(false);
                ((HdUpdateConfirmPresenter) mPresenter).onNext(currentHd, nonHdSelected);
                AnalyticsHelper.logEvent("changeHD_description_show");
            }
        });
    }

    @Override
    public void enabledButton(boolean z) {
        this.btNext.setEnabled(z);
    }

    public void refreshCurrentHd(List<UpgradeHdBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0 && !list.get(0).isNonHd()) {
                    UpgradeHdBean upgradeHdBean = list.get(0);
                    this.currentHd = upgradeHdBean;
                    this.tvName.setText(upgradeHdBean.getTitle());
                    TextView textView = this.tvAssets;
                    textView.setText(StringTronUtil.formatNumber3Truncate(this.currentHd.getBalance()) + " TRX");
                    this.tvRelated.setText(String.valueOf(this.currentHd.getUpgradeHdLists() == null ? "---" : Integer.valueOf(this.currentHd.getUpgradeHdLists().size())));
                    if (this.upgradeHdListAdapter == null) {
                        this.upgradeHdListAdapter = new UpgradeHdListAdapter();
                        this.rcInnerContent.setLayoutManager(new LinearLayoutManager(this, 1, false));
                        this.rcInnerContent.setAdapter(this.upgradeHdListAdapter);
                    }
                    this.upgradeHdListAdapter.setNewData(this.currentHd.getUpgradeHdLists());
                    setVisibility(8, this.ivSelectStatus);
                    this.rlArrow.setOnClickListener(new NoDoubleClickListener2() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            currentHd.setExpand(!currentHd.isExpand());
                            HdUpdateConfirmActivity hdUpdateConfirmActivity = HdUpdateConfirmActivity.this;
                            hdUpdateConfirmActivity.expandList(hdUpdateConfirmActivity.currentHd);
                        }
                    });
                    return;
                }
            } catch (Exception e) {
                LogUtils.e(e);
                return;
            }
        }
        setVisibility(8, this.tvCurrentHdTitle, this.hdItem);
    }

    public void refreshList(List<UpgradeHdBean> list) {
        ArrayList<UpgradeHdBean> arrayList = new ArrayList<>();
        this.nonHdList = arrayList;
        arrayList.addAll(list);
        ArrayList<UpgradeHdBean> arrayList2 = this.nonHdList;
        if (arrayList2 != null && arrayList2.size() > 0 && !this.nonHdList.get(0).isNonHd()) {
            this.nonHdList.remove(0);
        }
        ArrayList<UpgradeHdBean> arrayList3 = this.nonHdList;
        if (arrayList3 == null || arrayList3.size() == 0) {
            setVisibility(8, this.tvSelectHdTitle);
            return;
        }
        this.upgradeHdAdapter = new UpgradeHdAdapter();
        this.rcContent.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        this.rcContent.setAdapter(this.upgradeHdAdapter);
        this.nonHdSelected = this.nonHdList.get(0);
        this.upgradeHdAdapter.setNewData(this.nonHdList);
        this.upgradeHdAdapter.setOnItemViewClickListener(new UpgradeHdAdapter.OnItemViewClickListener() {
            @Override
            public final void onViewClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                lambda$refreshList$1(baseQuickAdapter, view, i);
            }
        });
        this.btNext.setEnabled(true);
    }

    public void lambda$refreshList$1(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        try {
            UpgradeHdBean upgradeHdBean = this.nonHdList.get(i);
            int id = view.getId();
            if (id == R.id.rl_arrow) {
                upgradeHdBean.setExpand(!upgradeHdBean.isExpand());
            } else if (id == R.id.root) {
                Collection.-EL.stream(this.nonHdList).forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        ((UpgradeHdBean) obj).setSelected(false);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
                upgradeHdBean.setSelected(true);
                this.nonHdSelected = upgradeHdBean;
            }
            this.upgradeHdAdapter.setNewData(this.nonHdList);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void expandList(UpgradeHdBean upgradeHdBean) {
        setVisibility(upgradeHdBean.isExpand() ? 0 : 8, this.rcInnerContent, this.mask);
        this.ivArrow.setImageResource(upgradeHdBean.isExpand() ? R.mipmap.top_arrow : R.mipmap.bottom_arrow);
    }

    public void setVisibility(int i, View... viewArr) {
        if (viewArr.length == 0) {
            return;
        }
        for (View view : viewArr) {
            view.setVisibility(i);
        }
    }
}
