package com.tron.wallet.common.components.popupwindow;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.assetshome.adapter.CustomAssetsAdapter;
import com.tron.wallet.common.bean.Token;
import com.tron.wallet.common.bean.token.TRC20Output;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.PopAssetsBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.AssetIssueContractDao;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SelectedAssetsWindow extends PopupWindow {
    private final List<TRC20Output> all20ListTemp;
    PopAssetsBinding binding;
    ImageView cancel;
    private Activity context;
    private CustomAssetsAdapter customAssetsAdapter;
    ImageView loadingView;
    private View mMenuView;
    RecyclerView rcList;
    Button reset;
    RelativeLayout rlLoading;
    private RxManager rxManager;
    private Wallet selectedWallet;
    private final List<Token> trc10Big;
    private List<String> trc10List;
    private final List<Token> trc10Small;
    private final List<TRC20Output> trc20Big;
    private List<String> trc20List;
    private final List<TRC20Output> trc20Small;

    public SelectedAssetsWindow(Activity activity, List<TRC20Output> list) {
        this.context = activity;
        View inflate = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.pop_assets, (ViewGroup) null);
        this.mMenuView = inflate;
        this.binding = PopAssetsBinding.bind(inflate);
        mappingIds();
        initClickListener();
        TouchDelegateUtils.expandViewTouchDelegate(this.cancel, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        setContentView(this.mMenuView);
        setWidth(UIUtils.getWindowWidth() - UIUtils.dip2px(64.0f));
        setHeight(-1);
        setFocusable(true);
        setAnimationStyle(R.style.AnimRight);
        setBackgroundDrawable(null);
        fitPopupWindowOverStatusBar(this, true);
        this.rcList.setLayoutManager(new LinearLayoutManager(activity, 1, false));
        this.trc10List = SpAPI.THIS.getTrc10List(SpAPI.THIS.getSelectedWallet());
        this.trc10Big = new ArrayList();
        this.trc10Small = new ArrayList();
        this.trc20List = new ArrayList();
        this.trc20Big = new ArrayList();
        this.trc20Small = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.all20ListTemp = arrayList;
        arrayList.addAll(list);
        showLoading(true);
    }

    private void initClickListener() {
        this.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Token token : trc10Small) {
                    token.setSelected(true);
                }
                for (Token token2 : trc10Big) {
                    token2.setSelected(true);
                }
                for (TRC20Output tRC20Output : trc20Big) {
                    tRC20Output.isSelected = true;
                }
                for (TRC20Output tRC20Output2 : trc20Small) {
                    tRC20Output2.isSelected = true;
                }
                SpAPI.THIS.setTrc10List(selectedWallet.getWalletName(), new ArrayList());
                SpAPI.THIS.setTrc20List(selectedWallet.getWalletName(), new ArrayList());
                SpAPI.THIS.setTrc10All(selectedWallet.getWalletName(), true);
                SpAPI.THIS.setTrc20All(selectedWallet.getWalletName(), true);
                rxManager = new RxManager();
                rxManager.post(Event.CUSTOM3, "333");
                if (customAssetsAdapter != null) {
                    customAssetsAdapter.nitifyData(trc10Big, trc10Small, trc20Big, trc20Small);
                }
                Toast.makeText(context, (int) R.string.reset_success, Toast.LENGTH_SHORT).show();
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void mappingIds() {
        this.cancel = this.binding.cancle;
        this.rcList = this.binding.rcList;
        this.reset = this.binding.reset;
        this.rlLoading = this.binding.rlLoading;
        this.loadingView = this.binding.loadingview;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        backgroundAlpha(1.0f);
    }

    public void backgroundAlpha(float f) {
        WindowManager.LayoutParams attributes = this.context.getWindow().getAttributes();
        attributes.alpha = f;
        this.context.getWindow().setAttributes(attributes);
    }

    public void fitPopupWindowOverStatusBar(PopupWindow popupWindow, boolean z) {
        try {
            Field declaredField = PopupWindow.class.getDeclaredField("mLayoutInScreen");
            declaredField.setAccessible(z);
            declaredField.set(popupWindow, Boolean.valueOf(z));
        } catch (IllegalAccessException e) {
            LogUtils.e(e);
        } catch (NoSuchFieldException e2) {
            LogUtils.e(e2);
        }
    }

    public void showLoading(boolean z) {
        if (z) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.loadingView, "rotation", 0.0f, 360.0f);
            ofFloat.setDuration(1000L);
            ofFloat.setRepeatCount(-1);
            ofFloat.setRepeatMode(1);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.start();
            this.rlLoading.setVisibility(View.VISIBLE);
            return;
        }
        this.rlLoading.setVisibility(View.GONE);
    }

    public void notifyData(List<TRC20Output> list) {
        boolean z;
        this.trc10Big.clear();
        this.trc10Small.clear();
        this.trc20Big.clear();
        this.trc20Small.clear();
        this.all20ListTemp.clear();
        this.all20ListTemp.addAll(list);
        this.trc10List = SpAPI.THIS.getTrc10List(SpAPI.THIS.getSelectedWallet());
        this.selectedWallet = WalletUtils.getSelectedWallet();
        this.trc20List = SpAPI.THIS.getTrc20List(this.selectedWallet.getWalletName());
        Protocol.Account account = WalletUtils.getAccount(this.context, this.selectedWallet.getWalletName());
        for (Map.Entry<String, Long> entry : account.getAssetV2Map().entrySet()) {
            if (entry.getValue().longValue() > 0) {
                if (this.trc10List.size() != 0) {
                    for (int i = 0; i < this.trc10List.size(); i++) {
                        if (this.trc10List.get(i).equals(entry.getKey())) {
                            this.trc10List.remove(i);
                            z = false;
                            break;
                        }
                    }
                }
                z = true;
                try {
                    AssetIssueContractDao assetIssueContractDao = (AssetIssueContractDao) DaoUtils.getDicInstance().QueryById(Long.parseLong(entry.getKey()), AssetIssueContractDao.class);
                    if (assetIssueContractDao != null) {
                        if (entry.getValue().longValue() > 10) {
                            this.trc10Big.add(new Token(assetIssueContractDao.getName(), entry.getValue().longValue(), z, Long.parseLong(entry.getKey())));
                        } else {
                            this.trc10Small.add(new Token(assetIssueContractDao.getName(), entry.getValue().longValue(), z, Long.parseLong(entry.getKey())));
                        }
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        }
        Collections.sort(this.trc10Big);
        Collections.sort(this.trc10Small);
        this.trc10Big.add(0, new Token("TRX", account.getBalance() / 1000000.0d, false));
        List<TRC20Output> list2 = this.all20ListTemp;
        if (list2 != null && list2.size() != 0) {
            for (TRC20Output tRC20Output : this.all20ListTemp) {
                tRC20Output.isSelected = true;
                if (this.trc20List.size() != 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= this.trc20List.size()) {
                            break;
                        } else if (this.trc20List.get(i2).equals(tRC20Output.contract_address)) {
                            tRC20Output.isSelected = false;
                            this.trc20List.remove(i2);
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                if (tRC20Output.amount / Math.pow(10.0d, tRC20Output.decimals) > 10.0d) {
                    this.trc20Big.add(tRC20Output);
                } else {
                    this.trc20Small.add(tRC20Output);
                }
            }
        }
        CustomAssetsAdapter customAssetsAdapter = this.customAssetsAdapter;
        if (customAssetsAdapter == null) {
            CustomAssetsAdapter customAssetsAdapter2 = new CustomAssetsAdapter(this.context, this.trc10Big, this.trc10Small, this.trc20Big, this.trc20Small);
            this.customAssetsAdapter = customAssetsAdapter2;
            this.rcList.setAdapter(customAssetsAdapter2);
        } else {
            customAssetsAdapter.nitifyData(this.trc10Big, this.trc10Small, this.trc20Big, this.trc20Small);
        }
        showLoading(false);
    }

    @Override
    public void showAtLocation(View view, int i, int i2, int i3) {
        backgroundAlpha(0.7f);
        super.showAtLocation(view, i, i2, i3);
    }
}
