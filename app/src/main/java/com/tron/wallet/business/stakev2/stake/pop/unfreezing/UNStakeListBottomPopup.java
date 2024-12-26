package com.tron.wallet.business.stakev2.stake.pop.unfreezing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.PopupUnstakeBottomListBinding;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import org.tron.walletserver.Wallet;
public class UNStakeListBottomPopup extends BottomPopupView {
    private PopupUnstakeBottomListBinding binding;
    Button btnCancel;
    private Context context;
    ImageView ivPlaceholder;
    RecyclerView listView;
    View llRoot;
    NoNetView noDataView;
    private OnClickListener onClickListener;
    private OnDismissListener onDismissListener;
    private UnFreezingListV2Adapter resultAdapter;
    ArrayList<UnFreezingResourceBean> unFreezingList;

    public interface OnClickListener {
        void onClick(Wallet wallet);
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_unstake_bottom_list;
    }

    public UNStakeListBottomPopup(Context context, OnClickListener onClickListener, OnDismissListener onDismissListener, ArrayList<UnFreezingResourceBean> arrayList) {
        super(context);
        this.context = context;
        this.onClickListener = onClickListener;
        this.onDismissListener = onDismissListener;
        this.unFreezingList = arrayList;
    }

    public static void showPopup(Context context, OnClickListener onClickListener, OnDismissListener onDismissListener, ArrayList<UnFreezingResourceBean> arrayList) {
        ((UNStakeListBottomPopup) new XPopup.Builder(context).enableDrag(false).moveUpToKeyboard(false).asCustom(new UNStakeListBottomPopup(context, onClickListener, onDismissListener, arrayList))).show();
    }

    @Override
    public void onDismiss() {
        OnDismissListener onDismissListener = this.onDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        super.onDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.listView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.resultAdapter = new UnFreezingListV2Adapter();
        new RxManager().on(Event.CANCEL_UNSTAKE_FAILED, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onCreate$0(obj);
            }
        });
        this.resultAdapter.bindToRecyclerView(this.listView);
        ((SimpleItemAnimator) this.listView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.resultAdapter.setNewData(this.unFreezingList);
        this.resultAdapter.notifyDataSetChanged();
        ArrayList<UnFreezingResourceBean> arrayList = this.unFreezingList;
        if (arrayList == null || arrayList.size() == 0) {
            this.ivPlaceholder.setVisibility(View.GONE);
            this.noDataView.setVisibility(View.VISIBLE);
        } else {
            this.ivPlaceholder.setVisibility(View.GONE);
            this.listView.setVisibility(View.VISIBLE);
            this.noDataView.setVisibility(View.GONE);
        }
        this.btnCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(null);
                }
            }
        });
    }

    public void lambda$onCreate$0(Object obj) throws Exception {
        dismiss();
    }

    @Override
    public void addInnerContent() {
        View inflate = LayoutInflater.from(this.context).inflate(getImplLayoutId(), (ViewGroup) this.bottomPopupContainer, false);
        this.binding = PopupUnstakeBottomListBinding.bind(inflate);
        mappingId();
        setClick();
        this.bottomPopupContainer.addView(inflate);
    }

    public void mappingId() {
        this.llRoot = this.binding.root;
        this.listView = this.binding.rvList;
        this.noDataView = this.binding.noDataView;
        this.ivPlaceholder = this.binding.ivPlaceHolder;
        this.btnCancel = this.binding.btnCancelAllUnstake;
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_common_right) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchWalletPage.CLICK_CLOSE);
                    dismiss();
                } else if (id != R.id.root) {
                } else {
                    dismiss();
                }
            }
        };
        this.binding.ivCommonRight.setOnClickListener(noDoubleClickListener2);
        this.binding.root.setOnClickListener(noDoubleClickListener2);
    }
}
