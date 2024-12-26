package com.tron.wallet.common.components;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxj.xpopup.core.BasePopupView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.tabmarket.home.LazyLoadFragment;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.SimpleListFragment;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.addressscam.AddressManager;
import com.tron.wallet.databinding.FragmentSimpleListBinding;
import com.tron.wallet.databinding.ItemAccountListBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
public class SimpleListFragment extends LazyLoadFragment<EmptyPresenter, EmptyModel> {
    private static final String KEY_HAS_EMPTY = "has_empty_view";
    private static final String NEED_CHECK_SCAM = "need_check_scam";
    private SimpleListAdapter adapter;
    private FragmentSimpleListBinding binding;
    private List<NameAddressExtraBean> cachedData;
    private int emptyTextRes;
    boolean needCheckScam = false;
    private Consumer<NameAddressExtraBean> onItemSelectCallback;
    RecyclerView rvList;

    public void setEmptyTextRes(int i) {
        this.emptyTextRes = i;
    }

    public static SimpleListFragment getInstance() {
        return getInstance(true, false);
    }

    public static SimpleListFragment getInstance(boolean z) {
        return getInstance(z, false);
    }

    public static SimpleListFragment getInstance(boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_HAS_EMPTY, z);
        bundle.putBoolean(NEED_CHECK_SCAM, z2);
        SimpleListFragment simpleListFragment = new SimpleListFragment();
        simpleListFragment.setArguments(bundle);
        return simpleListFragment;
    }

    public void updateData(List<NameAddressExtraBean> list, Consumer<NameAddressExtraBean> consumer) {
        if (isDetached()) {
            return;
        }
        this.onItemSelectCallback = consumer;
        SimpleListAdapter simpleListAdapter = this.adapter;
        if (simpleListAdapter == null) {
            this.cachedData = list;
            return;
        }
        simpleListAdapter.setNewData(list);
        this.adapter.setOnItemSelectCallback(consumer);
        if (this.needCheckScam) {
            checkAddressIsScam(list);
        }
    }

    public void insertData(NameAddressExtraBean... nameAddressExtraBeanArr) {
        SimpleListAdapter simpleListAdapter;
        if (isDetached() || (simpleListAdapter = this.adapter) == null) {
            return;
        }
        simpleListAdapter.addData((Collection) Arrays.asList(nameAddressExtraBeanArr));
    }

    @Override
    protected void processData() {
        boolean z;
        Bundle arguments = getArguments();
        if (arguments != null) {
            z = arguments.getBoolean(KEY_HAS_EMPTY);
            this.needCheckScam = arguments.getBoolean(NEED_CHECK_SCAM);
        } else {
            z = true;
        }
        SimpleListAdapter simpleListAdapter = new SimpleListAdapter();
        this.adapter = simpleListAdapter;
        if (z) {
            simpleListAdapter.setEmptyView(createEmptyView());
        }
        this.rvList.setLayoutManager(new WrapContentLinearLayoutManager(getContext(), 1, false));
        this.rvList.setAdapter(this.adapter);
        List<NameAddressExtraBean> list = this.cachedData;
        if (list != null) {
            updateData(list, this.onItemSelectCallback);
        }
    }

    private View createEmptyView() {
        NoNetView noNetView = new NoNetView(getContext());
        NoNetView icon = noNetView.setIcon(R.mipmap.ic_no_data_new);
        int i = this.emptyTextRes;
        if (i == 0) {
            i = R.string.no_send_record;
        }
        icon.setReloadDescription(i).setReloadable(false);
        noNetView.setPadding(0, UIUtils.dip2px(60.0f), 0, 0);
        return noNetView;
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentSimpleListBinding inflate = FragmentSimpleListBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        FrameLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    private void mappingId() {
        this.rvList = this.binding.rvList;
    }

    public static class SimpleListAdapter extends BaseQuickAdapter<NameAddressExtraBean, SimpleListViewHolder> {
        private HashMap<String, Map<String, Boolean>> map;
        private Consumer<NameAddressExtraBean> onItemSelectCallback;

        void setOnItemSelectCallback(Consumer<NameAddressExtraBean> consumer) {
            this.onItemSelectCallback = consumer;
        }

        public SimpleListAdapter() {
            super((int) R.layout.item_account_list);
        }

        @Override
        public void convert(final SimpleListViewHolder simpleListViewHolder, NameAddressExtraBean nameAddressExtraBean) {
            simpleListViewHolder.onBind(nameAddressExtraBean, this.map);
            simpleListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    SimpleListFragment.SimpleListAdapter.this.lambda$convert$0(simpleListViewHolder, view);
                }
            });
        }

        public void lambda$convert$0(SimpleListViewHolder simpleListViewHolder, View view) {
            NameAddressExtraBean item;
            if (this.onItemSelectCallback == null || (item = getItem(simpleListViewHolder.getAbsoluteAdapterPosition())) == null) {
                return;
            }
            this.onItemSelectCallback.accept(item.copy());
        }

        public void updateAddressListScamInfo(HashMap<String, Map<String, Boolean>> hashMap) {
            this.map = hashMap;
            notifyDataSetChanged();
        }
    }

    public static class SimpleListViewHolder extends BaseViewHolder {
        ItemAccountListBinding binding2;
        ImageView ivScam;
        ImageView ivScamAddress;
        private BasePopupView tipsWindow;
        TextView tvAddress;
        TextView tvExtra;
        TextView tvName;

        public SimpleListViewHolder(View view) {
            super(view);
            this.binding2 = ItemAccountListBinding.bind(view);
            mappingId();
        }

        private void mappingId() {
            this.tvName = this.binding2.tvName;
            this.tvAddress = this.binding2.tvAddress;
            this.tvExtra = this.binding2.tvExtra;
            this.ivScam = this.binding2.ivScam;
            this.ivScamAddress = this.binding2.ivScamAddress;
        }

        public void onBind(NameAddressExtraBean nameAddressExtraBean, HashMap<String, Map<String, Boolean>> hashMap) {
            this.tvName.setText(nameAddressExtraBean.getName());
            boolean isEmpty = TextUtils.isEmpty(nameAddressExtraBean.getName());
            if (isEmpty) {
                this.tvName.setVisibility(View.GONE);
                this.tvAddress.setText(nameAddressExtraBean.getAddress());
                this.tvAddress.setTextColor(this.itemView.getContext().getResources().getColor(R.color.black_23));
            } else {
                this.tvAddress.setText("(");
                this.tvAddress.append(nameAddressExtraBean.getAddress());
                this.tvAddress.append(")");
                this.tvName.setVisibility(View.VISIBLE);
                this.tvAddress.setTextColor(this.itemView.getContext().getResources().getColor(R.color.gray_6D778C));
            }
            if (TextUtils.isEmpty(nameAddressExtraBean.getExtra())) {
                this.tvExtra.setVisibility(View.GONE);
            } else {
                this.tvExtra.setText(nameAddressExtraBean.getExtra());
                this.tvExtra.setVisibility(View.VISIBLE);
            }
            if (hashMap == null || hashMap.isEmpty()) {
                this.ivScam.setVisibility(View.GONE);
                return;
            }
            Map<String, Boolean> map = hashMap.get(nameAddressExtraBean.getAddress());
            if (map == null || map.isEmpty() || map.get("risk") == null) {
                return;
            }
            boolean booleanValue = map.get("risk").booleanValue();
            if (isEmpty) {
                setScamView(booleanValue, this.ivScamAddress);
            } else {
                setScamView(booleanValue, this.ivScam);
            }
        }

        private void setScamView(boolean z, View view) {
            view.setVisibility(z ? View.VISIBLE : View.GONE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    SimpleListViewHolder.this.tipsWindow = new MultiLineTextPopupView.Builder().setAnchor(view2).setPreferredShowUp(true).setRequiredWidth(UIUtils.dip2px(243.0f)).addKeyValue(SimpleListViewHolder.this.itemView.getContext().getString(R.string.risk_account_tip), "").build(SimpleListViewHolder.this.itemView.getContext());
                    SimpleListViewHolder.this.tipsWindow.show();
                }
            });
        }

        private void resizeAddressMargin(boolean z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.tvAddress.getLayoutParams();
            layoutParams.leftMargin = z ? UIUtils.dip2px(4.0f) : 0;
            this.tvAddress.setLayoutParams(layoutParams);
        }
    }

    void checkAddressIsScam(List<NameAddressExtraBean> list) {
        ArrayList arrayList = new ArrayList();
        for (NameAddressExtraBean nameAddressExtraBean : list) {
            arrayList.add(nameAddressExtraBean.getAddress().toString());
        }
        AddressManager.checkAddressIsScam(arrayList, new AddressManager.AddressScamCall() {
            @Override
            public void error() {
            }

            @Override
            public void callback(HashMap<String, Map<String, Boolean>> hashMap) {
                adapter.updateAddressListScamInfo(hashMap);
            }
        });
    }
}
