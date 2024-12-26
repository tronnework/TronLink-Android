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
import com.tron.wallet.databinding.AcViewPermissionBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ViewPermissionActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String INTENT_PARAM_ALL_PERMISSIONGOUP = "INTENT_PARAM_ALL_PERMISSIONGOUP";
    public static final String INTENT_PARAM_SELECTED_PERMISSION = "INTENT_PARAM_SELECTED_PERMISSION";
    private static final String TAG = "ViewPermissionActivity";
    private AcViewPermissionBinding binding;
    TagFlowLayout mAccountFlowLayout;
    TextView mAccountPermissionTv;
    TagFlowLayout mBalanceFlowLayout;
    TextView mBalancePermissionTv;
    TagFlowLayout mBancorFlowLayout;
    TextView mBancorPermissionTv;
    TagFlowLayout mContractFlowLayout;
    TextView mContractPermissionTv;
    private LayoutInflater mInflater;
    TagFlowLayout mRepresentativesFlowLayout;
    TextView mRepresentativesPermissionTv;
    TagFlowLayout mResourceFlowLayout;
    TextView mResourcePermissionTv;
    TagFlowLayout mTrc10FlowLayout;
    TextView mTrc10PermissionTv;
    private List<PermissionGroupBean.PermissionBean> mBalancePermissionList = new ArrayList();
    private List<PermissionGroupBean.PermissionBean> mResourcePermissionList = new ArrayList();
    private List<PermissionGroupBean.PermissionBean> mAccountPermissionList = new ArrayList();
    private List<PermissionGroupBean.PermissionBean> mTrc10PermissionList = new ArrayList();
    private List<PermissionGroupBean.PermissionBean> mContractPermissionList = new ArrayList();
    private List<PermissionGroupBean.PermissionBean> mRepresentativesPermissionList = new ArrayList();
    private List<PermissionGroupBean.PermissionBean> mBancorPermissionList = new ArrayList();
    private ArrayList<PermissionGroupBean> mAllPermissionGroupList = new ArrayList<>();
    private List<PermissionGroupBean.PermissionBean> mSelectedPermissionList = new ArrayList();

    @Override
    protected void setLayout() {
        AcViewPermissionBinding inflate = AcViewPermissionBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        getHeaderHolder().ivCommonTitle2.setVisibility(View.VISIBLE);
        getHeaderHolder().ivCommonTitle2.setImageDrawable(getDrawable(R.mipmap.ic_question_mask));
        getHeaderHolder().ivCommonTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.toHowGetPermission(ViewPermissionActivity.this);
            }
        });
        setHeaderBar(getIContext().getResources().getString(R.string.operations2));
    }

    public void mappingId() {
        this.mBalanceFlowLayout = this.binding.flBalancePermission;
        this.mAccountFlowLayout = this.binding.flAccountPermission;
        this.mResourceFlowLayout = this.binding.flResourcePermission;
        this.mTrc10FlowLayout = this.binding.flTrc10Permission;
        this.mContractFlowLayout = this.binding.flContractPermission;
        this.mRepresentativesFlowLayout = this.binding.flRepresentativesPermission;
        this.mBancorFlowLayout = this.binding.flBancorPermission;
        this.mBalancePermissionTv = this.binding.tvBalancePermission;
        this.mAccountPermissionTv = this.binding.tvAccountPermission;
        this.mResourcePermissionTv = this.binding.tvResourcePermission;
        this.mTrc10PermissionTv = this.binding.tvTrc10Permission;
        this.mContractPermissionTv = this.binding.tvContractPermission;
        this.mRepresentativesPermissionTv = this.binding.tvRepresentativesPermission;
        this.mBancorPermissionTv = this.binding.tvBancorPermission;
    }

    @Override
    protected void processData() {
        setClick();
        this.mInflater = LayoutInflater.from(this);
        this.mAllPermissionGroupList = getIntent().getParcelableArrayListExtra(INTENT_PARAM_ALL_PERMISSIONGOUP);
        this.mSelectedPermissionList = getIntent().getParcelableArrayListExtra("INTENT_PARAM_SELECTED_PERMISSION");
        getPermision(this.mBalancePermissionList, 0);
        getPermision(this.mResourcePermissionList, 1);
        getPermision(this.mAccountPermissionList, 2);
        getPermision(this.mTrc10PermissionList, 3);
        getPermision(this.mContractPermissionList, 4);
        getPermision(this.mRepresentativesPermissionList, 5);
        getPermision(this.mBancorPermissionList, 6);
        initFlowLayout(this.mBalancePermissionList, this.mBalancePermissionTv, this.mBalanceFlowLayout);
        initFlowLayout(this.mResourcePermissionList, this.mResourcePermissionTv, this.mResourceFlowLayout);
        initFlowLayout(this.mAccountPermissionList, this.mAccountPermissionTv, this.mAccountFlowLayout);
        initFlowLayout(this.mTrc10PermissionList, this.mTrc10PermissionTv, this.mTrc10FlowLayout);
        initFlowLayout(this.mContractPermissionList, this.mContractPermissionTv, this.mContractFlowLayout);
        initFlowLayout(this.mRepresentativesPermissionList, this.mRepresentativesPermissionTv, this.mRepresentativesFlowLayout);
        initFlowLayout(this.mBancorPermissionList, this.mBancorPermissionTv, this.mBancorFlowLayout);
    }

    public static void startActivity(Activity activity, List<PermissionGroupBean.PermissionBean> list, List<PermissionGroupBean> list2) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("INTENT_PARAM_SELECTED_PERMISSION", (ArrayList) list);
        intent.putParcelableArrayListExtra(INTENT_PARAM_ALL_PERMISSIONGOUP, (ArrayList) list2);
        intent.setClass(activity, ViewPermissionActivity.class);
        activity.startActivity(intent);
    }

    public void setClick() {
        this.binding.btGotIt.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
    }

    private void getPermision(List<PermissionGroupBean.PermissionBean> list, int i) {
        List<PermissionGroupBean.PermissionBean> list2;
        list.clear();
        ArrayList<PermissionGroupBean> arrayList = this.mAllPermissionGroupList;
        if (arrayList == null || arrayList.size() < i + 1 || (list2 = this.mAllPermissionGroupList.get(i).getList()) == null || list2.size() <= 0) {
            return;
        }
        if (this.mSelectedPermissionList == null) {
            list.addAll(list2);
            return;
        }
        for (PermissionGroupBean.PermissionBean permissionBean : list2) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mSelectedPermissionList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getId() == permissionBean.getId()) {
                        list.add(permissionBean);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    private void initFlowLayout(List<PermissionGroupBean.PermissionBean> list, TextView textView, TagFlowLayout tagFlowLayout) {
        if (list == null || list.size() == 0) {
            textView.setVisibility(View.GONE);
            tagFlowLayout.setVisibility(View.GONE);
        }
        tagFlowLayout.setAdapter(initUnSelectedBalanceAdapter(list));
    }

    public TagAdapter initUnSelectedBalanceAdapter(List<PermissionGroupBean.PermissionBean> list) {
        return new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
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

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }
}
