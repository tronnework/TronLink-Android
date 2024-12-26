package com.tron.wallet.business.permission;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.PermissionGroupBean;
import com.tron.wallet.common.components.flowlayout.TagAdapter;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import java.util.List;
public interface DoPermissionContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void clickPermissionFlowLayout();

        abstract void confirm(EditText editText, TextView textView, EditText editText2, TagFlowLayout tagFlowLayout, android.view.View view, ViewGroup viewGroup, android.view.View view2);

        abstract void onActivityResult(int i, int i2, Intent intent);

        abstract boolean validateKey(ViewGroup viewGroup);

        abstract boolean validateOperations(TagFlowLayout tagFlowLayout, android.view.View view);

        abstract boolean validatePermissionName(EditText editText, TextView textView, android.view.View view);

        abstract boolean validateThreshold(String str);
    }

    public interface View extends BaseView {
        void addKeyViewToContainer(String str, long j, int i);

        void adjustHasMoreForListener();

        TagAdapter getTagAdapter();

        void initAdapter(List<PermissionGroupBean.PermissionBean> list);

        void notifPermissionAdapter();

        void refreshControlAddressValue(String str);

        void refreshViewForPermissionNameThreshold(String str, long j);

        void refreshViewForPermissionType(int i, boolean z);

        void removeKeyContainerSubViews();

        void setHeaderBar(String str);

        void setThresholdTipViewVisiblity(int i);

        void setWeightTipViewVisiblity(int i);

        void showPermissionClear();
    }
}
