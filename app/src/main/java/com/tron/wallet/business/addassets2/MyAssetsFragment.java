package com.tron.wallet.business.addassets2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.business.addassets2.MyAssetsContract;
import com.tron.wallet.business.addassets2.MyAssetsFragment;
import com.tron.wallet.business.addassets2.adapter.AssetsListAdapter;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.interfaces.OnItemSelectedListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemRcBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class MyAssetsFragment extends BaseFragment<MyAssetsPresenter, MyAssetsModel> implements MyAssetsContract.View {
    private static final String TAG = "MyAssetsFragment";
    private AssetsListAdapter adapter;
    private ItemRcBinding binding;
    private int dataType;
    View holderView;
    RecyclerView mRecyclerView;
    NoNetView noNetView;
    PtrHTFrameLayout ptrLayout;

    public static MyAssetsFragment newInstance() {
        return newInstance(0);
    }

    public static MyAssetsFragment newInstance(int i) {
        MyAssetsFragment myAssetsFragment = new MyAssetsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AssetsConfig.DATA_TYPE, i);
        myAssetsFragment.setArguments(bundle);
        return myAssetsFragment;
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(2);
        ItemRcBinding inflate = ItemRcBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.ptrLayout = this.binding.rcFrame;
        this.mRecyclerView = this.binding.rcList;
        this.holderView = this.binding.rcHolderList;
        this.noNetView = this.binding.netErrorView;
    }

    @Override
    protected void processData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.dataType = arguments.getInt(AssetsConfig.DATA_TYPE, 0);
        }
        dismissLoadingPage();
        ((MyAssetsPresenter) this.mPresenter).setIsAssets(this.dataType == 0);
        this.ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, mRecyclerView, view2);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ((MyAssetsPresenter) mPresenter).requestMyAssets(false);
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        AssetsListAdapter assetsListAdapter = new AssetsListAdapter(getContext(), this.dataType == 0 ? AssetsListAdapter.TagType.SHOW_ALL : AssetsListAdapter.TagType.DEFAULT, new AssetsListAdapter.ItemCallback() {
            @Override
            public void onItemClicked(TokenBean tokenBean, int i) {
                ((MyAssetsPresenter) mPresenter).showAssetsDetail(tokenBean);
            }

            @Override
            public void onItemSelected(TokenBean tokenBean, int i) {
                if (!WatchWalletVerifier.getInstance().getWatchWalletHasOwnerPermission()) {
                    WatchWalletVerifier.getInstance().confirmVerify(getIContext());
                    return;
                }
                AnalyticsHelper.AssetsManagerPage.logEvent(AnalyticsHelper.AssetsManagerPage.CLICK_ASSETS_MANAGER_PAGE_ADD_ASSET);
                ((MyAssetsPresenter) mPresenter).followAssets(tokenBean, i);
            }

            public class fun1 implements OnItemSelectedListener {
                final int val$position;
                final TokenBean val$tokenBean;

                fun1(TokenBean tokenBean, int i) {
                    this.val$tokenBean = tokenBean;
                    this.val$position = i;
                }

                @Override
                public void onItemSelected(PopupWindow popupWindow, int i, int i2, Object obj) {
                    if (!WatchWalletVerifier.getInstance().getWatchWalletHasOwnerPermission()) {
                        WatchWalletVerifier.getInstance().confirmVerify(getIContext());
                        popupWindow.dismiss();
                        return;
                    }
                    if (i2 == DeletePopup.DELETE_ASSET) {
                        ConfirmCustomPopupView.Builder builder = ConfirmCustomPopupView.getBuilder(getIContext());
                        String string = getResources().getString(this.val$tokenBean.getTokenStatus() == 0 ? R.string.delete_asset_confirm : R.string.custom_token_delete_title);
                        Object[] objArr = new Object[1];
                        objArr[0] = StringTronUtil.isEmpty(this.val$tokenBean.getShortName()) ? this.val$tokenBean.getName() : this.val$tokenBean.getShortName();
                        ConfirmCustomPopupView.Builder confirmText = builder.setTitle(String.format(string, objArr)).setInfo(getResources().getString(this.val$tokenBean.getTokenStatus() == 0 ? R.string.delete_asset_confirm_info : R.string.custom_token_delete_info)).setConfirmText(getResources().getString(R.string.confirm));
                        final TokenBean tokenBean = this.val$tokenBean;
                        final int i3 = this.val$position;
                        confirmText.setConfirmListener(new View.OnClickListener() {
                            @Override
                            public final void onClick(View view) {
                                MyAssetsFragment.2.1.this.lambda$onItemSelected$0(tokenBean, i3, view);
                            }
                        }).build().show();
                    } else if (i2 == DeletePopup.REMOVE_ASSET_FROM_HOME) {
                        AnalyticsHelper.AssetsManagerPage.logEvent(AnalyticsHelper.AssetsManagerPage.CLICK_ASSETS_MANAGER_PAGE_REMOVE_ASSET);
                        ((MyAssetsPresenter) mPresenter).unFollowAssets(this.val$tokenBean, this.val$position);
                    }
                    popupWindow.dismiss();
                }

                public void lambda$onItemSelected$0(TokenBean tokenBean, int i, View view) {
                    AnalyticsHelper.AssetsManagerPage.logEvent(AnalyticsHelper.AssetsManagerPage.CLICK_ASSETS_MANAGER_PAGE_DELETE_ASSET);
                    ((MyAssetsPresenter) mPresenter).deleteAssets(tokenBean, i);
                }
            }

            @Override
            public void onItemLongClicked(final View view, TokenBean tokenBean, int[] iArr, int i) {
                view.setSelected(true);
                new DeletePopup(getIContext(), tokenBean, new fun1(tokenBean, i), new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        view.setSelected(false);
                    }
                }).showAtTouchPoint(view, iArr);
            }
        });
        this.adapter = assetsListAdapter;
        this.mRecyclerView.setAdapter(assetsListAdapter);
        this.mRecyclerView.addItemDecoration(new ColorDividerItemDecoration());
        ((SimpleItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        showHolderView();
        ((MyAssetsPresenter) this.mPresenter).getMyAssets();
    }

    public void lambda$processData$0(View view) {
        ((MyAssetsPresenter) this.mPresenter).requestMyAssets();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mPresenter != 0) {
            ((MyAssetsPresenter) this.mPresenter).requestMyAssets(true);
        }
    }

    private void showHolderView() {
        this.holderView.setVisibility(View.VISIBLE);
    }

    private void hideHolderView() {
        this.holderView.setVisibility(View.GONE);
    }

    public void filterAndSort(boolean z, boolean z2) {
        if (this.mPresenter != 0) {
            ((MyAssetsPresenter) this.mPresenter).filterAndSort(z, z2);
        }
    }

    public void hideZeroAssets(boolean z) {
        if (this.mPresenter != 0) {
            ((MyAssetsPresenter) this.mPresenter).hideZeroAssets(z);
        }
    }

    @Override
    public void showNoDatasPage() {
        hideHolderView();
        super.showNoDatasPage();
    }

    @Override
    public void updateMyAssets(List<TokenBean> list) {
        hideHolderView();
        hideNoNetView();
        this.ptrLayout.setVisibility(View.VISIBLE);
        this.adapter.notifyDataChanged(list);
    }

    @Override
    public void showAssetsAddedView() {
        toast(getResources().getString(R.string.assets_added_to_home), R.mipmap.toast_icon_positive);
        FirebaseAnalytics.getInstance(getIContext()).logEvent("Click_add_token_my_follow", null);
    }

    @Override
    public void updateAssetsPrice() {
        this.adapter.notifyPriceChanged();
    }

    @Override
    public void updateComplete(boolean z) {
        this.ptrLayout.refreshComplete();
        if (z || this.adapter.getItemCount() <= 0) {
            return;
        }
        toast(getResources().getString(R.string.time_out));
    }

    @Override
    public void updateAssetsFollowState(TokenBean tokenBean, int i, boolean z) {
        if (z) {
            this.adapter.notifyDataRemoved(tokenBean, i);
        } else {
            this.adapter.notifyDataChanged(tokenBean, i);
        }
    }

    @Override
    public void showNoNetView() {
        this.noNetView.setVisibility(View.VISIBLE);
        this.noNetView.hideLoading();
        this.holderView.setVisibility(View.GONE);
        this.ptrLayout.setVisibility(View.GONE);
    }

    public void hideNoNetView() {
        this.noNetView.setVisibility(View.GONE);
    }

    public static class DeletePopup extends PopupWindow {
        public static int DELETE_ASSET = 1;
        public static int REMOVE_ASSET_FROM_HOME;
        private TokenBean bean;
        private Context context;
        private int popupHeight;
        private int popupWidth;

        public DeletePopup(Context context, final TokenBean tokenBean, final OnItemSelectedListener onItemSelectedListener, PopupWindow.OnDismissListener onDismissListener) {
            this.context = context;
            View inflate = LayoutInflater.from(context).inflate(R.layout.popup_del_assets, (ViewGroup) null);
            setContentView(inflate);
            setWidth(-2);
            setHeight(-2);
            setOutsideTouchable(true);
            setFocusable(true);
            setBackgroundDrawable(new ColorDrawable());
            setClippingEnabled(false);
            setOnDismissListener(onDismissListener);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_1);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_2);
            if (tokenBean.isSelected) {
                textView.setText(R.string.remove_asset_from_home);
                StateListDrawable stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[]{16842919}, context.getDrawable(R.drawable.roundborder_88_4_top));
                stateListDrawable.addState(new int[]{-16842919}, context.getDrawable(R.color.transparent));
                textView.setBackground(stateListDrawable);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        MyAssetsFragment.DeletePopup.this.lambda$new$0(onItemSelectedListener, tokenBean, view);
                    }
                });
                textView2.setText(R.string.delete_the_asset);
                StateListDrawable stateListDrawable2 = new StateListDrawable();
                stateListDrawable2.addState(new int[]{16842919}, context.getDrawable(R.drawable.roundborder_88_4_bottom));
                stateListDrawable2.addState(new int[]{-16842919}, context.getDrawable(R.color.transparent));
                textView2.setBackground(stateListDrawable2);
                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        MyAssetsFragment.DeletePopup.this.lambda$new$1(onItemSelectedListener, tokenBean, view);
                    }
                });
            } else {
                textView.setText(R.string.delete_the_asset);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        MyAssetsFragment.DeletePopup.this.lambda$new$2(onItemSelectedListener, tokenBean, view);
                    }
                });
                textView2.setVisibility(View.GONE);
            }
            inflate.measure(0, 0);
            this.popupHeight = inflate.getMeasuredHeight();
            this.popupWidth = inflate.getMeasuredWidth();
        }

        public void lambda$new$0(OnItemSelectedListener onItemSelectedListener, TokenBean tokenBean, View view) {
            onItemSelectedListener.onItemSelected(this, 0, REMOVE_ASSET_FROM_HOME, tokenBean);
        }

        public void lambda$new$1(OnItemSelectedListener onItemSelectedListener, TokenBean tokenBean, View view) {
            onItemSelectedListener.onItemSelected(this, 1, DELETE_ASSET, tokenBean);
        }

        public void lambda$new$2(OnItemSelectedListener onItemSelectedListener, TokenBean tokenBean, View view) {
            onItemSelectedListener.onItemSelected(this, 0, DELETE_ASSET, tokenBean);
        }

        public void showAtTouchPoint(View view, int[] iArr) {
            int height;
            int i;
            int[] iArr2 = new int[2];
            view.getLocationOnScreen(iArr2);
            if (iArr2[1] + iArr[1] + this.popupHeight + UIUtils.dip2px(15.0f) > UIUtils.getScreenHeight(this.context)) {
                height = (view.getHeight() - iArr[1]) + this.popupHeight;
            } else {
                height = view.getHeight() - iArr[1];
            }
            int i2 = -height;
            if (iArr2[0] + iArr[0] + this.popupWidth + UIUtils.dip2px(10.0f) > UIUtils.getScreenWidth(this.context)) {
                i = iArr[0] - this.popupWidth;
                if (i < UIUtils.dip2px(10.0f)) {
                    i = UIUtils.dip2px(10.0f);
                }
            } else {
                i = iArr[0];
            }
            showAsDropDown(view, i, i2);
        }
    }

    public class ColorDividerItemDecoration extends RecyclerView.ItemDecoration {
        private float mDividerHeight = UIUtils.dip2px(8.0f);
        private Paint mPaint;

        public ColorDividerItemDecoration() {
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setAntiAlias(true);
        }

        @Override
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            super.getItemOffsets(rect, view, recyclerView, state);
            if (recyclerView.getChildAdapterPosition(view) == ((AssetsListAdapter) recyclerView.getAdapter()).getLastNewAssetsPosition()) {
                rect.bottom = UIUtils.dip2px(8.0f);
            }
        }

        @Override
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDrawOver(canvas, recyclerView, state);
            int lastNewAssetsPosition = ((AssetsListAdapter) recyclerView.getAdapter()).getLastNewAssetsPosition();
            if (lastNewAssetsPosition > -1) {
                int childCount = recyclerView.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = recyclerView.getChildAt(i);
                    if (recyclerView.getChildAdapterPosition(childAt) == lastNewAssetsPosition) {
                        float bottom = childAt.getBottom() - UIUtils.dip2px(1.0f);
                        float paddingLeft = recyclerView.getPaddingLeft();
                        float bottom2 = childAt.getBottom() + this.mDividerHeight;
                        float width = recyclerView.getWidth() - recyclerView.getPaddingRight();
                        this.mPaint.setColor(getResources().getColor(R.color.white));
                        canvas.drawRect(paddingLeft, bottom, width, bottom2, this.mPaint);
                        this.mPaint.setColor(getResources().getColor(R.color.gray_02_2));
                        canvas.drawRect(paddingLeft, bottom, width, bottom2, this.mPaint);
                    }
                }
            }
        }
    }
}
