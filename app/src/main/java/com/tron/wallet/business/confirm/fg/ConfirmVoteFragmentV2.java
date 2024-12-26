package com.tron.wallet.business.confirm.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.EllipsizeTextViewUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgConfirmVote2Binding;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.StringUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.WitnessContract;
public class ConfirmVoteFragmentV2 extends BaseConfirmFragment<EmptyPresenter, EmptyModel> {
    private FgConfirmVote2Binding binding;
    GlobalConfirmButton btnConfirm;
    private SimpleTextAdapter confirmExtraTextAdapter;
    GlobalTitleHeaderView headerView;
    ImageView ivArrow;
    View llArrow;
    private Animation loadingAnimation;
    private boolean needShowSrList;
    private NumberFormat numberFormat;
    private VoteParam param;
    GlobalFeeResourceView resourceView;
    RelativeLayout rlType;
    View rootLayout;
    RecyclerView rvExtras;
    private RxManager rxManager;
    TextView tvTip;
    TextView tvVoteAddress;
    TextView tvVoteSr;
    private HashMap<String, String> votedAddressMap;
    private HashMap<String, String> votedNameMap;

    public static ConfirmVoteFragmentV2 getInstance(BaseParam baseParam) {
        ConfirmVoteFragmentV2 confirmVoteFragmentV2 = new ConfirmVoteFragmentV2();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        confirmVoteFragmentV2.setArguments(bundle);
        return confirmVoteFragmentV2;
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgConfirmVote2Binding inflate = FgConfirmVote2Binding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    public void mappingId() {
        this.tvVoteAddress = this.binding.tvVoteAddress;
        this.tvVoteSr = this.binding.tvVoteSr;
        this.llArrow = this.binding.llArrow;
        this.ivArrow = this.binding.ivArrowRight;
        this.rvExtras = this.binding.rvExtras;
        this.headerView = this.binding.headerView;
        this.resourceView = this.binding.resourceView;
        this.rootLayout = this.binding.root;
        this.rlType = this.binding.rlType;
        this.tvTip = this.binding.tvTip;
        this.btnConfirm = this.binding.buttonConfirm;
    }

    @Override
    public void processData() {
        super.processData();
        if (this.baseParam == null || !(this.baseParam instanceof VoteParam)) {
            return;
        }
        this.param = (VoteParam) this.baseParam;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(0);
        this.rxManager = new RxManager();
        HashMap<String, String> addressWeightMap = this.param.getAddressWeightMap();
        this.votedAddressMap = addressWeightMap;
        if (addressWeightMap == null) {
            this.votedAddressMap = new HashMap<>();
        }
        HashMap<String, String> nameWeightMap = this.param.getNameWeightMap();
        this.votedNameMap = nameWeightMap;
        if (nameWeightMap == null) {
            this.votedNameMap = new HashMap<>();
        }
        if (this.votedAddressMap.size() == 0) {
            parseVotedAddressMap();
        }
        try {
            bindDataToUI();
            addAccountCallback(this.headerView, this.resourceView, this.btnConfirm);
            ensureAccount();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (this.votedAddressMap.size() <= 0 || this.votedNameMap.size() != 0) {
            return;
        }
        asyncGetWitnessList();
    }

    private void bindDataToUI() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.confirm.fg.ConfirmVoteFragmentV2.bindDataToUI():void");
    }

    private void toLoadingWitnessName() {
        this.llArrow.setClickable(false);
        this.ivArrow.setImageResource(R.mipmap.circle_loading_20);
        this.ivArrow.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.loadingAnimation = rotateAnimation;
        rotateAnimation.setFillAfter(true);
        this.loadingAnimation.setInterpolator(new LinearInterpolator());
        this.loadingAnimation.setDuration(1000L);
        this.loadingAnimation.setRepeatCount(-1);
        this.ivArrow.startAnimation(this.loadingAnimation);
    }

    public void onLoadingWitnessNameFinished(boolean z) {
        this.loadingAnimation.cancel();
        this.ivArrow.clearAnimation();
        this.llArrow.setClickable(true);
        this.ivArrow.setImageResource(R.mipmap.ic_arrow_down);
        this.ivArrow.setScaleType(ImageView.ScaleType.CENTER);
    }

    private void parseVotedAddressMap() {
        try {
            WitnessContract.VoteWitnessContract voteWitnessContract = (WitnessContract.VoteWitnessContract) TransactionUtils.unpackContract(Protocol.Transaction.parseFrom(this.param.getTransactionBean().getBytes().get(0)).getRawData().getContract(0), WitnessContract.VoteWitnessContract.class);
            if (voteWitnessContract != null) {
                this.param.setFromAddress(StringTronUtil.encode58Check(voteWitnessContract.getOwnerAddress().toByteArray()));
                for (WitnessContract.VoteWitnessContract.Vote vote : voteWitnessContract.getVotesList()) {
                    HashMap<String, String> hashMap = this.votedAddressMap;
                    String encode58Check = StringTronUtil.encode58Check(vote.getVoteAddress().toByteArray());
                    hashMap.put(encode58Check, "" + vote.getVoteCount());
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
        }
    }

    public List<ConfirmExtraText> buildListData() {
        ArrayList<ConfirmExtraText> arrayList = new ArrayList();
        for (String str : this.votedAddressMap.keySet()) {
            ConfirmExtraText confirmExtraText = new ConfirmExtraText();
            confirmExtraText.setLeft(StringTronUtil.isEmpty(this.votedNameMap.get(str)) ? str : this.votedNameMap.get(str));
            confirmExtraText.setRight(this.votedAddressMap.get(str));
            arrayList.add(confirmExtraText);
        }
        Collections.sort(arrayList, new Comparator<ConfirmExtraText>() {
            @Override
            public int compare(ConfirmExtraText confirmExtraText2, ConfirmExtraText confirmExtraText3) {
                try {
                    return Long.valueOf(confirmExtraText3.getRight()).compareTo(Long.valueOf(confirmExtraText2.getRight()));
                } catch (Throwable th) {
                    LogUtils.e(th);
                    return 0;
                }
            }
        });
        for (ConfirmExtraText confirmExtraText2 : arrayList) {
            try {
                confirmExtraText2.setRight(this.numberFormat.format(Long.valueOf(confirmExtraText2.getRight())));
            } catch (Exception e) {
                SentryUtil.captureException(e);
            }
        }
        return arrayList;
    }

    private void asyncFormatAddress(final String str) {
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                String formatAddress;
                formatAddress = AddressNameUtils.getFormatAddress(str);
                return formatAddress;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<String>() {
            @Override
            public void onResponse(String str2, String str3) {
                if (str3.indexOf("(") >= 0) {
                    tvVoteAddress.setText(EllipsizeTextViewUtils.ellipseNameOnly(tvVoteAddress, str3, StringUtils.LF));
                } else {
                    tvVoteAddress.setText(str3);
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                tvVoteAddress.setText(StringTronUtil.indentAddress(str, 6));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add(disposable);
            }
        }, "formatAddress"));
    }

    private void asyncGetWitnessList() {
        toLoadingWitnessName();
        requestWitnessList().map(new Function() {
            @Override
            public final Object apply(Object obj) {
                HashMap lambda$asyncGetWitnessList$1;
                lambda$asyncGetWitnessList$1 = lambda$asyncGetWitnessList$1((WitnessOutput) obj);
                return lambda$asyncGetWitnessList$1;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<HashMap<String, String>>() {
            @Override
            public void onResponse(String str, HashMap<String, String> hashMap) {
                if (hashMap != null && needShowSrList) {
                    confirmExtraTextAdapter.setData(buildListData());
                }
                onLoadingWitnessNameFinished(hashMap != null);
            }

            @Override
            public void onFailure(String str, String str2) {
                onLoadingWitnessNameFinished(false);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add(disposable);
            }
        }, "requestWitnessList"));
    }

    public HashMap lambda$asyncGetWitnessList$1(WitnessOutput witnessOutput) throws Exception {
        if (witnessOutput != null && witnessOutput.getData() != null) {
            for (String str : this.votedAddressMap.keySet()) {
                Iterator<WitnessOutput.DataBean> it = witnessOutput.getData().iterator();
                while (true) {
                    if (it.hasNext()) {
                        WitnessOutput.DataBean next = it.next();
                        if (str.equals(next.getAddress())) {
                            this.votedNameMap.put(str, next.getName());
                            break;
                        }
                    }
                }
            }
        }
        return this.votedNameMap;
    }

    private Observable<WitnessOutput> requestWitnessList() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getWitnessList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
        this.binding = null;
    }

    public static class SimpleTextAdapter extends RecyclerView.Adapter {
        private List<ConfirmExtraText> data;

        SimpleTextAdapter(List<ConfirmExtraText> list) {
            this.data = list;
            if (list == null) {
                this.data = new ArrayList();
            }
        }

        public void setData(List<ConfirmExtraText> list) {
            this.data = list;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SimpleTextHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_confirm_extra_inner_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof SimpleTextHolder) {
                ((SimpleTextHolder) viewHolder).onBind(this.data.get(i));
            }
        }

        @Override
        public int getItemCount() {
            return this.data.size();
        }
    }

    private static class SimpleTextHolder extends BaseHolder {
        private final TextView tvLeft;
        private final TextView tvRight;

        public SimpleTextHolder(View view) {
            super(view);
            this.tvLeft = (TextView) view.findViewById(R.id.tv_left);
            this.tvRight = (TextView) view.findViewById(R.id.tv_right);
        }

        void onBind(ConfirmExtraText confirmExtraText) {
            this.tvLeft.setText(String.format("%d.%s", Integer.valueOf(getAbsoluteAdapterPosition() + 1), confirmExtraText.getLeft()));
            this.tvRight.setText(confirmExtraText.getRight());
        }
    }
}
