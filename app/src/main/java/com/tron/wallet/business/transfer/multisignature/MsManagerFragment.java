package com.tron.wallet.business.transfer.multisignature;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.permission.ViewPermissionActivity;
import com.tron.wallet.common.bean.PermissionGroupBean;
import com.tron.wallet.common.components.flowlayout.FlowLayout;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tron.wallet.databinding.FragmentMsManagerBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.tron.protos.Protocol;
public class MsManagerFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private static final String TAG = "MsManagerFragment";
    private String address;
    FragmentMsManagerBinding binding;
    TagFlowLayout idFlowlayout;
    ImageView ivGp;
    LinearLayout llKeys;
    LinearLayout llOp2;
    View llOt;
    private boolean mIsCurrentFragment;
    private boolean mIsOwner;
    View mMoreView;
    TextView mOperationsDescView;
    private String name;
    private Protocol.Permission permission;
    LinearLayout root;
    TextView tvPermissionName;
    TextView tvTh;
    TextView tvUc;
    private ArrayList<PermissionGroupBean.PermissionBean> mAllPermissionArray = new ArrayList<>();
    private List<PermissionGroupBean.PermissionBean> mSelectedPermissionList = new ArrayList();
    private ArrayList<PermissionGroupBean> mAllPermissionGroupArray = new ArrayList<>();
    boolean isBreak = true;

    @Override
    protected void processData() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.transfer.multisignature.MsManagerFragment.processData():void");
    }

    public boolean lambda$processData$0(View view, int i, FlowLayout flowLayout) {
        ViewPermissionActivity.startActivity(this.mContext, this.mSelectedPermissionList, this.mAllPermissionGroupArray);
        return false;
    }

    public void lambda$processData$1(View view) {
        ViewPermissionActivity.startActivity(this.mContext, this.mSelectedPermissionList, this.mAllPermissionGroupArray);
    }

    private void mappingId() {
        this.idFlowlayout = this.binding.idFlowlayout;
        this.tvUc = this.binding.tvUc;
        this.ivGp = this.binding.ivGp;
        this.tvTh = this.binding.tvTh;
        this.llKeys = this.binding.llKeys;
        this.root = this.binding.root;
        this.tvPermissionName = this.binding.tvPermissionName;
        this.llOt = this.binding.rlOt;
        this.llOp2 = this.binding.llOp2;
        this.mMoreView = this.binding.rlMore;
        this.mOperationsDescView = this.binding.tvOperationsDesc;
    }

    private void getPermissionBeanForIds(List<Integer> list) {
        this.mSelectedPermissionList.clear();
        for (Integer num : list) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mAllPermissionArray.iterator();
            while (true) {
                if (it.hasNext()) {
                    PermissionGroupBean.PermissionBean next = it.next();
                    if (num.intValue() == next.getId()) {
                        this.mSelectedPermissionList.add(next);
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentMsManagerBinding inflate = FragmentMsManagerBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    public void changeView(final boolean z) {
        LinearLayout linearLayout = this.root;
        if (linearLayout == null) {
            if (this.isBreak) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeView(z);
                        isBreak = false;
                    }
                }, 50L);
                return;
            }
            return;
        }
        if (!z) {
            linearLayout.setBackgroundResource(R.drawable.roundborder_white_10);
        } else if (this.mIsOwner) {
            linearLayout.setBackgroundResource(R.mipmap.ms_bg_owner);
        } else {
            linearLayout.setBackgroundResource(R.mipmap.ms_bg_new);
        }
        this.isBreak = true;
    }
}
