package com.tron.wallet.business.permission;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.wallet.common.bean.PermissionGroupBean;
import com.tron.wallet.common.components.flowlayout.FlowLayout;
import com.tron.wallet.common.components.flowlayout.TagAdapter;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcSelectPermissionBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class SelectPermissionActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final int CHOOSE_PERMISSION_REQUESTCODE = 10010;
    private static final String INTENT_PARAM_ALL_PERMISSIONGOUP = "INTENT_PARAM_ALL_PERMISSIONGOUP";
    public static final String INTENT_PARAM_SELECTED_PERMISSION = "INTENT_PARAM_SELECTED_PERMISSION";
    private static final String TAG = "SelectPermissionActivity";
    private AcSelectPermissionBinding binding;
    TextView mAccountPermissionTv;
    private List<PermissionGroupBean> mAllPermissionGroupList;
    TextView mBalancePermissionTv;
    TextView mBancorPermissionTv;
    TextView mContractPermissionTv;
    private LayoutInflater mInflater;
    TextView mRepresentativesPermissionTv;
    TextView mResourcePermissionTv;
    private TagAdapter mSelectedAdapter;
    TagFlowLayout mSelectedPermissionFlowLayout;
    private List<PermissionGroupBean.PermissionBean> mSelectedPermissionList;
    TextView mTrc10PermissionTv;
    private TagAdapter mUnSelectedAccountAdapter;
    TagFlowLayout mUnSelectedAccountFlowLayout;
    private List<PermissionGroupBean.PermissionBean> mUnSelectedAccountPermissionList;
    private TagAdapter mUnSelectedBalanceAdapter;
    TagFlowLayout mUnSelectedBalanceFlowLayout;
    private List<PermissionGroupBean.PermissionBean> mUnSelectedBalancePermissionList;
    private TagAdapter mUnSelectedBancorAdapter;
    TagFlowLayout mUnSelectedBancorFlowLayout;
    private List<PermissionGroupBean.PermissionBean> mUnSelectedBancorPermissionList;
    private TagAdapter mUnSelectedContractAdapter;
    TagFlowLayout mUnSelectedContractFlowLayout;
    private List<PermissionGroupBean.PermissionBean> mUnSelectedContractPermissionList;
    private TagAdapter mUnSelectedRepresentativesAdapter;
    TagFlowLayout mUnSelectedRepresentativesFlowLayout;
    private List<PermissionGroupBean.PermissionBean> mUnSelectedRepresentativesPermissionList;
    private TagAdapter mUnSelectedResourceAdapter;
    TagFlowLayout mUnSelectedResourceFlowLayout;
    private List<PermissionGroupBean.PermissionBean> mUnSelectedResourcePermissionList;
    private TagAdapter mUnSelectedTrc10Adapter;
    TagFlowLayout mUnSelectedTrc10FlowLayout;
    private List<PermissionGroupBean.PermissionBean> mUnSelectedTrc10PermissionList;

    @Override
    protected void setLayout() {
        AcSelectPermissionBinding inflate = AcSelectPermissionBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        getHeaderHolder().ivCommonTitle2.setVisibility(View.VISIBLE);
        getHeaderHolder().ivCommonTitle2.setImageDrawable(getDrawable(R.mipmap.ic_question_mask));
        getHeaderHolder().ivCommonTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.toHowGetPermission(SelectPermissionActivity.this);
            }
        });
        setHeaderBar(getIContext().getResources().getString(R.string.select_permissions));
    }

    public void mappingId() {
        this.mSelectedPermissionFlowLayout = this.binding.flSelectedPermission;
        this.mBalancePermissionTv = this.binding.tvBalancePermission;
        this.mResourcePermissionTv = this.binding.tvResourcePermission;
        this.mAccountPermissionTv = this.binding.tvAccountPermission;
        this.mTrc10PermissionTv = this.binding.tvTrc10Permission;
        this.mContractPermissionTv = this.binding.tvContractPermission;
        this.mRepresentativesPermissionTv = this.binding.tvRepresentativesPermission;
        this.mBancorPermissionTv = this.binding.tvBancorPermission;
        this.mUnSelectedBalanceFlowLayout = this.binding.flBalancePermission;
        this.mUnSelectedResourceFlowLayout = this.binding.flResourcePermission;
        this.mUnSelectedAccountFlowLayout = this.binding.flAccountPermission;
        this.mUnSelectedTrc10FlowLayout = this.binding.flTrc10Permission;
        this.mUnSelectedContractFlowLayout = this.binding.flContractPermission;
        this.mUnSelectedRepresentativesFlowLayout = this.binding.flRepresentativesPermission;
        this.mUnSelectedBancorFlowLayout = this.binding.flBancorPermission;
    }

    @Override
    protected void processData() {
        setClick();
        this.mInflater = LayoutInflater.from(this);
        initData();
        initSelectedFlowLayout();
        initUnSelectedBalanceFlowLayout();
        initUnSelectedResourceFlowLayout();
        initUnSelectedAccountFlowLayout();
        initUnSelectedTrc10FlowLayout();
        initUnSelectedContractFlowLayout();
        initUnSelectedRepresentativesFlowLayout();
        initUnSelectedBancorFlowLayout();
    }

    private void initData() {
        getIntentParam();
        getUnSelectedBalancePermision();
        getUnSelectedResourcePermision();
        getUnSelectedAccountPermision();
        getUnSelectedTrc10Permision();
        getUnSelectedContractPermision();
        getUnSelectedRepresentativesPermision();
        getUnSelectedBancorPermision();
    }

    private void getIntentParam() {
        this.mSelectedPermissionList = getIntent().getParcelableArrayListExtra("INTENT_PARAM_SELECTED_PERMISSION");
        this.mAllPermissionGroupList = getIntent().getParcelableArrayListExtra("INTENT_PARAM_ALL_PERMISSIONGOUP");
    }

    public static void startActivity(Activity activity, List<PermissionGroupBean.PermissionBean> list, List<PermissionGroupBean> list2) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("INTENT_PARAM_SELECTED_PERMISSION", (ArrayList) list);
        intent.putParcelableArrayListExtra("INTENT_PARAM_ALL_PERMISSIONGOUP", (ArrayList) list2);
        intent.setClass(activity, SelectPermissionActivity.class);
        activity.startActivityForResult(intent, 10010);
    }

    public void initSelectedAdapter(List<PermissionGroupBean.PermissionBean> list) {
        this.mSelectedAdapter = new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, PermissionGroupBean.PermissionBean permissionBean) {
                View inflate = mInflater.inflate(R.layout.flowlayout_selected_operations, (ViewGroup) flowLayout, false);
                TextView textView = (TextView) inflate.findViewById(R.id.tv_operation);
                String display_name_zh = permissionBean.getDisplay_name_zh();
                if (LanguageUtils.languageEN(AppContextUtil.getContext())) {
                    display_name_zh = permissionBean.getDisplay_name_en();
                }
                textView.setText(display_name_zh);
                textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_6_blue135));
                return inflate;
            }
        };
    }

    private void initSelectedFlowLayout() {
        initSelectedAdapter(this.mSelectedPermissionList);
        this.mSelectedPermissionFlowLayout.setAdapter(this.mSelectedAdapter);
        this.mSelectedPermissionFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                onClickSelectFlowLayout(i);
                return false;
            }
        });
    }

    public void onClickSelectFlowLayout(int i) {
        PermissionGroupBean.PermissionBean remove = this.mSelectedPermissionList.remove(i);
        switch (remove.getType()) {
            case 0:
                this.mUnSelectedBalancePermissionList.add(remove);
                this.mUnSelectedBalanceAdapter.notifyDataChanged();
                this.mBalancePermissionTv.setVisibility(View.VISIBLE);
                this.mUnSelectedBalanceFlowLayout.setVisibility(View.VISIBLE);
                break;
            case 1:
                this.mUnSelectedResourcePermissionList.add(remove);
                this.mUnSelectedResourceAdapter.notifyDataChanged();
                this.mAccountPermissionTv.setVisibility(View.VISIBLE);
                this.mUnSelectedResourceFlowLayout.setVisibility(View.VISIBLE);
            case 2:
                this.mUnSelectedAccountPermissionList.add(remove);
                this.mUnSelectedAccountAdapter.notifyDataChanged();
                this.mAccountPermissionTv.setVisibility(View.VISIBLE);
                this.mUnSelectedAccountFlowLayout.setVisibility(View.VISIBLE);
                break;
            case 3:
                this.mUnSelectedTrc10PermissionList.add(remove);
                this.mUnSelectedTrc10Adapter.notifyDataChanged();
                this.mTrc10PermissionTv.setVisibility(View.VISIBLE);
                this.mUnSelectedTrc10FlowLayout.setVisibility(View.VISIBLE);
                break;
            case 4:
                this.mUnSelectedContractPermissionList.add(remove);
                this.mUnSelectedContractAdapter.notifyDataChanged();
                this.mContractPermissionTv.setVisibility(View.VISIBLE);
                this.mUnSelectedContractFlowLayout.setVisibility(View.VISIBLE);
                break;
            case 5:
                this.mUnSelectedRepresentativesPermissionList.add(remove);
                this.mUnSelectedRepresentativesAdapter.notifyDataChanged();
                this.mRepresentativesPermissionTv.setVisibility(View.VISIBLE);
                this.mUnSelectedRepresentativesFlowLayout.setVisibility(View.VISIBLE);
                break;
            case 6:
                this.mUnSelectedBancorPermissionList.add(remove);
                this.mUnSelectedBancorAdapter.notifyDataChanged();
                this.mBancorPermissionTv.setVisibility(View.VISIBLE);
                this.mUnSelectedBancorFlowLayout.setVisibility(View.VISIBLE);
                break;
        }
        this.mSelectedAdapter.notifyDataChanged();
    }

    private void initUnSelectedBalanceFlowLayout() {
        List<PermissionGroupBean.PermissionBean> list = this.mUnSelectedBalancePermissionList;
        if (list == null || list.size() == 0) {
            this.mBalancePermissionTv.setVisibility(View.GONE);
            this.mUnSelectedBalanceFlowLayout.setVisibility(View.GONE);
        }
        initUnSelectedBalanceAdapter(this.mUnSelectedBalancePermissionList);
        this.mUnSelectedBalanceFlowLayout.setAdapter(this.mUnSelectedBalanceAdapter);
        this.mUnSelectedBalanceFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                SelectPermissionActivity selectPermissionActivity = SelectPermissionActivity.this;
                selectPermissionActivity.onClickUnSelectedFlowLayout(selectPermissionActivity.mUnSelectedBalancePermissionList, mUnSelectedBalanceAdapter, mBalancePermissionTv, mUnSelectedBalanceFlowLayout, i);
                return false;
            }
        });
    }

    public void onClickUnSelectedFlowLayout(List<PermissionGroupBean.PermissionBean> list, TagAdapter tagAdapter, TextView textView, TagFlowLayout tagFlowLayout, int i) {
        this.mSelectedPermissionList.add(list.remove(i));
        this.mSelectedAdapter.notifyDataChanged();
        tagAdapter.notifyDataChanged();
        if (list == null || list.size() == 0) {
            textView.setVisibility(View.GONE);
            tagFlowLayout.setVisibility(View.GONE);
            return;
        }
        textView.setVisibility(View.VISIBLE);
        tagFlowLayout.setVisibility(View.VISIBLE);
    }

    public void initUnSelectedBalanceAdapter(List<PermissionGroupBean.PermissionBean> list) {
        this.mUnSelectedBalanceAdapter = new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, PermissionGroupBean.PermissionBean permissionBean) {
                TextView textView = (TextView) mInflater.inflate(R.layout.flowlayout_operations, (ViewGroup) flowLayout, false);
                String display_name_zh = permissionBean.getDisplay_name_zh();
                if (LanguageUtils.languageEN(AppContextUtil.getContext())) {
                    display_name_zh = permissionBean.getDisplay_name_en();
                }
                textView.setText(display_name_zh);
                textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_6_blue135));
                return textView;
            }
        };
    }

    public void initUnSelectedResourceAdapter(List<PermissionGroupBean.PermissionBean> list) {
        this.mUnSelectedResourceAdapter = new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, PermissionGroupBean.PermissionBean permissionBean) {
                TextView textView = (TextView) mInflater.inflate(R.layout.flowlayout_operations, (ViewGroup) flowLayout, false);
                String display_name_zh = permissionBean.getDisplay_name_zh();
                if (LanguageUtils.languageEN(AppContextUtil.getContext())) {
                    display_name_zh = permissionBean.getDisplay_name_en();
                }
                textView.setText(display_name_zh);
                textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_6_blue135));
                return textView;
            }
        };
    }

    private void getUnSelectedBalancePermision() {
        List<PermissionGroupBean.PermissionBean> list;
        List<PermissionGroupBean> list2 = this.mAllPermissionGroupList;
        if (list2 == null || list2.size() < 1 || (list = this.mAllPermissionGroupList.get(0).getList()) == null || list.size() <= 0) {
            return;
        }
        if (this.mSelectedPermissionList == null) {
            this.mUnSelectedBalancePermissionList = list;
            return;
        }
        if (this.mUnSelectedBalancePermissionList == null) {
            this.mUnSelectedBalancePermissionList = new ArrayList();
        }
        for (PermissionGroupBean.PermissionBean permissionBean : list) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mSelectedPermissionList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getId() == permissionBean.getId()) {
                        break;
                    }
                } else {
                    this.mUnSelectedBalancePermissionList.add(permissionBean);
                    break;
                }
            }
        }
    }

    private void initUnSelectedResourceFlowLayout() {
        List<PermissionGroupBean.PermissionBean> list = this.mUnSelectedResourcePermissionList;
        if (list == null || list.size() == 0) {
            this.mResourcePermissionTv.setVisibility(View.GONE);
            this.mUnSelectedResourceFlowLayout.setVisibility(View.GONE);
        }
        initUnSelectedResourceAdapter(this.mUnSelectedResourcePermissionList);
        this.mUnSelectedResourceFlowLayout.setAdapter(this.mUnSelectedResourceAdapter);
        this.mUnSelectedResourceFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                SelectPermissionActivity selectPermissionActivity = SelectPermissionActivity.this;
                selectPermissionActivity.onClickUnSelectedFlowLayout(selectPermissionActivity.mUnSelectedResourcePermissionList, mUnSelectedResourceAdapter, mResourcePermissionTv, mUnSelectedResourceFlowLayout, i);
                return false;
            }
        });
    }

    private void initUnSelectedAccountFlowLayout() {
        List<PermissionGroupBean.PermissionBean> list = this.mUnSelectedAccountPermissionList;
        if (list == null || list.size() == 0) {
            this.mAccountPermissionTv.setVisibility(View.GONE);
            this.mUnSelectedAccountFlowLayout.setVisibility(View.GONE);
        }
        initUnSelectedAccountAdapter(this.mUnSelectedAccountPermissionList);
        this.mUnSelectedAccountFlowLayout.setAdapter(this.mUnSelectedAccountAdapter);
        this.mUnSelectedAccountFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                SelectPermissionActivity selectPermissionActivity = SelectPermissionActivity.this;
                selectPermissionActivity.onClickUnSelectedFlowLayout(selectPermissionActivity.mUnSelectedAccountPermissionList, mUnSelectedAccountAdapter, mAccountPermissionTv, mUnSelectedAccountFlowLayout, i);
                return false;
            }
        });
    }

    public void initUnSelectedAccountAdapter(List<PermissionGroupBean.PermissionBean> list) {
        this.mUnSelectedAccountAdapter = new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, PermissionGroupBean.PermissionBean permissionBean) {
                TextView textView = (TextView) mInflater.inflate(R.layout.flowlayout_operations, (ViewGroup) flowLayout, false);
                String display_name_zh = permissionBean.getDisplay_name_zh();
                if (LanguageUtils.languageEN(AppContextUtil.getContext())) {
                    display_name_zh = permissionBean.getDisplay_name_en();
                }
                textView.setText(display_name_zh);
                textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_6_blue135));
                return textView;
            }
        };
    }

    private void getUnSelectedAccountPermision() {
        List<PermissionGroupBean.PermissionBean> list;
        List<PermissionGroupBean> list2 = this.mAllPermissionGroupList;
        if (list2 == null || list2.size() < 2 || (list = this.mAllPermissionGroupList.get(2).getList()) == null || list.size() <= 0) {
            return;
        }
        if (this.mSelectedPermissionList == null) {
            this.mUnSelectedAccountPermissionList = list;
            return;
        }
        if (this.mUnSelectedAccountPermissionList == null) {
            this.mUnSelectedAccountPermissionList = new ArrayList();
        }
        for (PermissionGroupBean.PermissionBean permissionBean : list) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mSelectedPermissionList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getId() == permissionBean.getId()) {
                        break;
                    }
                } else {
                    this.mUnSelectedAccountPermissionList.add(permissionBean);
                    break;
                }
            }
        }
    }

    private void getUnSelectedResourcePermision() {
        List<PermissionGroupBean.PermissionBean> list;
        List<PermissionGroupBean> list2 = this.mAllPermissionGroupList;
        if (list2 == null || list2.size() < 2 || (list = this.mAllPermissionGroupList.get(1).getList()) == null || list.size() <= 0) {
            return;
        }
        if (this.mSelectedPermissionList == null) {
            this.mUnSelectedResourcePermissionList = list;
            return;
        }
        if (this.mUnSelectedResourcePermissionList == null) {
            this.mUnSelectedResourcePermissionList = new ArrayList();
        }
        for (PermissionGroupBean.PermissionBean permissionBean : list) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mSelectedPermissionList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getId() == permissionBean.getId()) {
                        break;
                    }
                } else {
                    this.mUnSelectedResourcePermissionList.add(permissionBean);
                    break;
                }
            }
        }
    }

    private void initUnSelectedTrc10FlowLayout() {
        List<PermissionGroupBean.PermissionBean> list = this.mUnSelectedTrc10PermissionList;
        if (list == null || list.size() == 0) {
            this.mTrc10PermissionTv.setVisibility(View.GONE);
            this.mUnSelectedTrc10FlowLayout.setVisibility(View.GONE);
        }
        initUnSelectedTrc10Adapter(this.mUnSelectedTrc10PermissionList);
        this.mUnSelectedTrc10FlowLayout.setAdapter(this.mUnSelectedTrc10Adapter);
        this.mUnSelectedTrc10FlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                SelectPermissionActivity selectPermissionActivity = SelectPermissionActivity.this;
                selectPermissionActivity.onClickUnSelectedFlowLayout(selectPermissionActivity.mUnSelectedTrc10PermissionList, mUnSelectedTrc10Adapter, mTrc10PermissionTv, mUnSelectedTrc10FlowLayout, i);
                return false;
            }
        });
    }

    public void initUnSelectedTrc10Adapter(List<PermissionGroupBean.PermissionBean> list) {
        this.mUnSelectedTrc10Adapter = new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, PermissionGroupBean.PermissionBean permissionBean) {
                TextView textView = (TextView) mInflater.inflate(R.layout.flowlayout_operations, (ViewGroup) flowLayout, false);
                String display_name_zh = permissionBean.getDisplay_name_zh();
                if (LanguageUtils.languageEN(AppContextUtil.getContext())) {
                    display_name_zh = permissionBean.getDisplay_name_en();
                }
                textView.setText(display_name_zh);
                textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_6_blue135));
                return textView;
            }
        };
    }

    private void getUnSelectedTrc10Permision() {
        List<PermissionGroupBean.PermissionBean> list;
        List<PermissionGroupBean> list2 = this.mAllPermissionGroupList;
        if (list2 == null || list2.size() < 3 || (list = this.mAllPermissionGroupList.get(3).getList()) == null || list.size() <= 0) {
            return;
        }
        if (this.mSelectedPermissionList == null) {
            this.mUnSelectedTrc10PermissionList = list;
            return;
        }
        if (this.mUnSelectedTrc10PermissionList == null) {
            this.mUnSelectedTrc10PermissionList = new ArrayList();
        }
        for (PermissionGroupBean.PermissionBean permissionBean : list) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mSelectedPermissionList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getId() == permissionBean.getId()) {
                        break;
                    }
                } else {
                    this.mUnSelectedTrc10PermissionList.add(permissionBean);
                    break;
                }
            }
        }
    }

    private void initUnSelectedContractFlowLayout() {
        List<PermissionGroupBean.PermissionBean> list = this.mUnSelectedContractPermissionList;
        if (list == null || list.size() == 0) {
            this.mContractPermissionTv.setVisibility(View.GONE);
            this.mUnSelectedContractFlowLayout.setVisibility(View.GONE);
        }
        initUnSelectedContractAdapter(this.mUnSelectedContractPermissionList);
        this.mUnSelectedContractFlowLayout.setAdapter(this.mUnSelectedContractAdapter);
        this.mUnSelectedContractFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                SelectPermissionActivity selectPermissionActivity = SelectPermissionActivity.this;
                selectPermissionActivity.onClickUnSelectedFlowLayout(selectPermissionActivity.mUnSelectedContractPermissionList, mUnSelectedContractAdapter, mContractPermissionTv, mUnSelectedContractFlowLayout, i);
                return false;
            }
        });
    }

    public void initUnSelectedContractAdapter(List<PermissionGroupBean.PermissionBean> list) {
        this.mUnSelectedContractAdapter = new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, PermissionGroupBean.PermissionBean permissionBean) {
                TextView textView = (TextView) mInflater.inflate(R.layout.flowlayout_operations, (ViewGroup) flowLayout, false);
                String display_name_zh = permissionBean.getDisplay_name_zh();
                if (LanguageUtils.languageEN(AppContextUtil.getContext())) {
                    display_name_zh = permissionBean.getDisplay_name_en();
                }
                textView.setText(display_name_zh);
                textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_6_blue135));
                return textView;
            }
        };
    }

    private void getUnSelectedContractPermision() {
        List<PermissionGroupBean.PermissionBean> list;
        List<PermissionGroupBean> list2 = this.mAllPermissionGroupList;
        if (list2 == null || list2.size() < 4 || (list = this.mAllPermissionGroupList.get(4).getList()) == null || list.size() <= 0) {
            return;
        }
        if (this.mSelectedPermissionList == null) {
            this.mUnSelectedContractPermissionList = list;
            return;
        }
        if (this.mUnSelectedContractPermissionList == null) {
            this.mUnSelectedContractPermissionList = new ArrayList();
        }
        for (PermissionGroupBean.PermissionBean permissionBean : list) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mSelectedPermissionList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getId() == permissionBean.getId()) {
                        break;
                    }
                } else {
                    this.mUnSelectedContractPermissionList.add(permissionBean);
                    break;
                }
            }
        }
    }

    private void initUnSelectedRepresentativesFlowLayout() {
        List<PermissionGroupBean.PermissionBean> list = this.mUnSelectedRepresentativesPermissionList;
        if (list == null || list.size() == 0) {
            this.mRepresentativesPermissionTv.setVisibility(View.GONE);
            this.mUnSelectedRepresentativesFlowLayout.setVisibility(View.GONE);
        }
        initUnSelectedRepresentativesAdapter(this.mUnSelectedRepresentativesPermissionList);
        this.mUnSelectedRepresentativesFlowLayout.setAdapter(this.mUnSelectedRepresentativesAdapter);
        this.mUnSelectedRepresentativesFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                SelectPermissionActivity selectPermissionActivity = SelectPermissionActivity.this;
                selectPermissionActivity.onClickUnSelectedFlowLayout(selectPermissionActivity.mUnSelectedRepresentativesPermissionList, mUnSelectedRepresentativesAdapter, mRepresentativesPermissionTv, mUnSelectedRepresentativesFlowLayout, i);
                return false;
            }
        });
    }

    public void initUnSelectedRepresentativesAdapter(List<PermissionGroupBean.PermissionBean> list) {
        this.mUnSelectedRepresentativesAdapter = new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, PermissionGroupBean.PermissionBean permissionBean) {
                TextView textView = (TextView) mInflater.inflate(R.layout.flowlayout_operations, (ViewGroup) flowLayout, false);
                String display_name_zh = permissionBean.getDisplay_name_zh();
                if (LanguageUtils.languageEN(AppContextUtil.getContext())) {
                    display_name_zh = permissionBean.getDisplay_name_en();
                }
                textView.setText(display_name_zh);
                textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_6_blue135));
                return textView;
            }
        };
    }

    private void getUnSelectedRepresentativesPermision() {
        List<PermissionGroupBean.PermissionBean> list;
        List<PermissionGroupBean> list2 = this.mAllPermissionGroupList;
        if (list2 == null || list2.size() < 5 || (list = this.mAllPermissionGroupList.get(5).getList()) == null || list.size() <= 0) {
            return;
        }
        if (this.mSelectedPermissionList == null) {
            this.mUnSelectedRepresentativesPermissionList = list;
            return;
        }
        if (this.mUnSelectedRepresentativesPermissionList == null) {
            this.mUnSelectedRepresentativesPermissionList = new ArrayList();
        }
        for (PermissionGroupBean.PermissionBean permissionBean : list) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mSelectedPermissionList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getId() == permissionBean.getId()) {
                        break;
                    }
                } else {
                    this.mUnSelectedRepresentativesPermissionList.add(permissionBean);
                    break;
                }
            }
        }
    }

    private void initUnSelectedBancorFlowLayout() {
        List<PermissionGroupBean.PermissionBean> list = this.mUnSelectedBancorPermissionList;
        if (list == null || list.size() == 0) {
            this.mBancorPermissionTv.setVisibility(View.GONE);
            this.mUnSelectedBancorFlowLayout.setVisibility(View.GONE);
        }
        initUnSelectedBancorAdapter(this.mUnSelectedBancorPermissionList);
        this.mUnSelectedBancorFlowLayout.setAdapter(this.mUnSelectedBancorAdapter);
        this.mUnSelectedBancorFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                SelectPermissionActivity selectPermissionActivity = SelectPermissionActivity.this;
                selectPermissionActivity.onClickUnSelectedFlowLayout(selectPermissionActivity.mUnSelectedBancorPermissionList, mUnSelectedBancorAdapter, mBancorPermissionTv, mUnSelectedBancorFlowLayout, i);
                return false;
            }
        });
    }

    public void initUnSelectedBancorAdapter(List<PermissionGroupBean.PermissionBean> list) {
        this.mUnSelectedBancorAdapter = new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, PermissionGroupBean.PermissionBean permissionBean) {
                TextView textView = (TextView) mInflater.inflate(R.layout.flowlayout_operations, (ViewGroup) flowLayout, false);
                String display_name_zh = permissionBean.getDisplay_name_zh();
                if (LanguageUtils.languageEN(AppContextUtil.getContext())) {
                    display_name_zh = permissionBean.getDisplay_name_en();
                }
                textView.setText(display_name_zh);
                textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_6_blue135));
                return textView;
            }
        };
    }

    private void getUnSelectedBancorPermision() {
        List<PermissionGroupBean.PermissionBean> list;
        List<PermissionGroupBean> list2 = this.mAllPermissionGroupList;
        if (list2 == null || list2.size() < 6 || (list = this.mAllPermissionGroupList.get(6).getList()) == null || list.size() <= 0) {
            return;
        }
        if (this.mSelectedPermissionList == null) {
            this.mUnSelectedBancorPermissionList = list;
            return;
        }
        if (this.mUnSelectedBancorPermissionList == null) {
            this.mUnSelectedBancorPermissionList = new ArrayList();
        }
        for (PermissionGroupBean.PermissionBean permissionBean : list) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mSelectedPermissionList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getId() == permissionBean.getId()) {
                        break;
                    }
                } else {
                    this.mUnSelectedBancorPermissionList.add(permissionBean);
                    break;
                }
            }
        }
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    public void setClick() {
        this.binding.btConfirm.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("INTENT_PARAM_SELECTED_PERMISSION", (ArrayList) mSelectedPermissionList);
                setResult(-1, intent);
                finish();
            }
        });
    }
}
