package com.tron.wallet.business.confirm.core.pending;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.business.confirm.core.pending.fg.vote.VoteIntentIntegrator;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DelegatedResourceParam;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.stakev2.managementv2.ResourceManagementV2Activity;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.databinding.FgConfirmPendingBinding;
import com.tronlinkpro.wallet.R;
public class ConfirmPendingFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private BaseParam baseParam;
    private FgConfirmPendingBinding binding;
    View btnDoneLoading;
    View btnTransactionInfo;
    FrameLayout flResult;
    ImageView ivLoading;
    private byte[] rpcReturn;
    private State state;
    private int unsuccessfulTransactions;

    public static ConfirmPendingFragment getInstance(BaseParam baseParam) {
        ConfirmPendingFragment confirmPendingFragment = new ConfirmPendingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Keys.BaseParam, baseParam);
        confirmPendingFragment.setArguments(bundle);
        return confirmPendingFragment;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                view.getId();
            }
        };
        this.binding.ivBack.setOnClickListener(noDoubleClickListener);
        this.binding.layoutPendingLoading.btnDoneLoading.setOnClickListener(noDoubleClickListener);
    }

    @Override
    public void onKeyDownChild(int i, KeyEvent keyEvent) {
        BaseParam baseParam;
        if (i != 4 || (baseParam = this.baseParam) == null) {
            return;
        }
        int type = baseParam.getType();
        if (type != 1) {
            if (type == 2) {
                RxBus.getInstance().post(Event.BroadcastSuccess2, "");
                return;
            }
            if (type != 3) {
                if (type != 4) {
                    if (type != 9) {
                        if (type != 21) {
                            if (type != 22) {
                                switch (type) {
                                    case 34:
                                    case 37:
                                        break;
                                    case 35:
                                    case 36:
                                        ResourceManagementV2Activity.start(getContext(), ((DelegatedResourceParam) this.baseParam).getResourceType() != 0 ? 0 : 1);
                                        RxBus.getInstance().post(Event.BroadcastSuccess, "");
                                        return;
                                    default:
                                        return;
                                }
                            }
                        }
                    }
                }
                RxBus.getInstance().post(Event.BroadcastSuccess, "");
                return;
            }
            VoteIntentIntegrator.openVoteMainActivity(this.mContext, this.baseParam);
            return;
        }
        Intent intent = new Intent(this.mContext, MainTabActivity.class);
        intent.setFlags(32768);
        startActivity(intent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RxBus.getInstance().post(Event.BackToHome, "");
                RxBus.getInstance().post(RB.RB_ACCOUNTS, "");
            }
        }, 800L);
    }

    public void update(State state, byte[] bArr, BaseParam baseParam, int i) {
        this.state = state;
        this.rpcReturn = bArr;
        this.baseParam = baseParam;
        this.unsuccessfulTransactions = i;
        showNextPage();
    }

    @Override
    protected void processData() {
        this.flResult = (FrameLayout) this.mContext.findViewById(R.id.fl_result);
        parseData();
        AnimaUtil.animLoadingView(this.ivLoading);
        showButton();
        showNextPage();
        this.btnDoneLoading.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (baseParam == null) {
                    return;
                }
                int type = baseParam.getType();
                if (type != 1) {
                    if (type != 2) {
                        if (type != 3) {
                            if (type != 4) {
                                if (type != 9) {
                                    if (type != 21) {
                                        if (type != 22) {
                                            switch (type) {
                                                case 35:
                                                case 36:
                                                    ResourceManagementV2Activity.start(getContext(), ((DelegatedResourceParam) baseParam).getResourceType() != 0 ? 0 : 1);
                                                    RxBus.getInstance().post(Event.BroadcastSuccess, "");
                                                    break;
                                            }
                                            mContext.finish();
                                        }
                                    }
                                }
                            }
                        }
                        VoteIntentIntegrator.openVoteMainActivity(mContext, baseParam);
                        mContext.finish();
                    }
                    RxBus.getInstance().post(Event.BroadcastSuccess, "");
                    mContext.finish();
                }
                Intent intent = new Intent(mContext, MainTabActivity.class);
                intent.setFlags(32768);
                startActivity(intent);
                RxBus.getInstance().post(RB.RB_ACCOUNTS, "");
                RxBus.getInstance().post(Event.BackToHome, "");
                mContext.finish();
            }
        });
    }

    public void parseData() {
        Bundle arguments = getArguments();
        this.state = (State) arguments.getSerializable(Keys.StateKey);
        this.baseParam = (BaseParam) arguments.getParcelable(Keys.BaseParam);
    }

    private void showNextPage() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.confirm.core.pending.ConfirmPendingFragment.showNextPage():void");
    }

    private void showButton() {
        BaseParam baseParam = this.baseParam;
        if (baseParam == null) {
            exit();
            return;
        }
        int type = baseParam.getType();
        if (type == 1 || type == 9 || type == 21) {
            this.btnTransactionInfo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgConfirmPendingBinding inflate = FgConfirmPendingBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.ivLoading = this.binding.layoutPendingLoading.loadingIcon;
        this.btnTransactionInfo = this.binding.layoutPendingLoading.btnTransactionInfo;
        this.btnDoneLoading = this.binding.layoutPendingLoading.btnDoneLoading;
    }
}
