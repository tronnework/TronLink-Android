package com.tron.wallet.business.confirm.fg.component;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.bean.ResourceConsumedBean;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ResourceConsumedBuilder;
import com.tron.wallet.common.bean.ConfirmExtraTextClickable;
import com.tron.wallet.common.components.CenterSpaceImageSpan;
import com.tron.wallet.common.components.DashUnderLineTextView;
import com.tron.wallet.common.components.FeeResourceDetailView;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.config.FeeReporting;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.FeeUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.LayoutConfirmFeeResourceBinding;
import com.tron.wallet.databinding.PopupGlobalFeeDetailsBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import org.slf4j.Marker;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class GlobalFeeResourceView extends FrameLayout implements BaseConfirmFragment.AccountCallback {
    private static final String APPROXIMATE = "≈";
    private LayoutConfirmFeeResourceBinding binding;
    View btTotalConsumed;
    private long deductedBandwidth;
    private BigDecimal deductedBandwidthInTrx;
    private long deductedEnergy;
    private BigDecimal deductedEnergyInTrx;
    private final CompositeDisposable disposables;
    private FeeResourceCallback feeCallback;
    ImageView ivArrow;
    ImageView ivFeeLoading;
    View ivTips;
    View ivTipsFee;
    View ivTipsResource;
    View llOtherError;
    View llResourceAll;
    private BaseParam param;
    private ResourceConsumedBuilder resourceConsumedBuilder;
    FeeResourceDetailView resourceView;
    View rlFee;
    View rlResourceConsume;
    private boolean showResourceView;
    TextView tvFee;
    TextView tvFeePrice;
    TextView tvNotEnoughTip;
    TextView tvOtherError;
    TextView tvResConsumeBand;
    DashUnderLineTextView tvResConsumeEnergy;
    DashUnderLineTextView tvResConsumeEnergyUnder;
    TextView tvResConsumeLeft;
    TextView tvResConsumeMid;

    public interface FeeResourceCallback {
        void onGetFee(boolean z, double d, double d2, double d3);
    }

    public void setFeeResourceCallback(FeeResourceCallback feeResourceCallback) {
        this.feeCallback = feeResourceCallback;
    }

    public GlobalFeeResourceView(Context context) {
        this(context, null);
    }

    public GlobalFeeResourceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GlobalFeeResourceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.disposables = new CompositeDisposable();
        this.showResourceView = false;
        View inflate = View.inflate(context, R.layout.layout_confirm_fee_resource, null);
        addView(inflate, new FrameLayout.LayoutParams(-1, -1));
        mappingId(inflate);
    }

    public void mappingId(View view) {
        LayoutConfirmFeeResourceBinding bind = LayoutConfirmFeeResourceBinding.bind(view);
        this.binding = bind;
        this.tvFee = bind.tvFee;
        this.tvFeePrice = this.binding.tvFeePrice;
        this.tvResConsumeLeft = this.binding.tvResourceConsumeLeft;
        this.tvResConsumeBand = this.binding.tvConsumeResourceBandwidth;
        this.tvResConsumeMid = this.binding.tvConsumeResourceMid;
        this.tvResConsumeEnergy = this.binding.tvConsumeResourceEnergy;
        this.tvResConsumeEnergyUnder = this.binding.tvConsumeResourceEnergyUnder;
        this.tvOtherError = this.binding.tvErrorText;
        this.llOtherError = this.binding.llOtherError;
        this.rlFee = this.binding.rlFee;
        this.ivFeeLoading = this.binding.ivFeeLoading;
        this.rlResourceConsume = this.binding.rlResourceConsume;
        this.resourceView = this.binding.resourceInfoView;
        this.ivArrow = this.binding.ivArrowRight;
        this.ivTips = this.binding.ivTips;
        this.ivTipsResource = this.binding.ivTipsResource;
        this.ivTipsFee = this.binding.ivTipsFee;
        this.llResourceAll = this.binding.llResourceAll;
        this.tvNotEnoughTip = this.binding.tvContactEnergyNotEnoughTip;
        this.btTotalConsumed = this.binding.rlTotalConsumed;
    }

    public void bindData(BaseParam baseParam) {
        this.param = baseParam;
        if (baseParam == null || baseParam.getTransactionBean() == null || baseParam.getTransactionBean().getBytes() == null || baseParam.getTransactionBean().getBytes().isEmpty()) {
            this.llResourceAll.setVisibility(View.GONE);
            return;
        }
        this.resourceConsumedBuilder = new ResourceConsumedBuilder(baseParam);
        initView();
    }

    public void logEvent(String str) {
        try {
            AnalyticsHelper.logEventFormat(str, Integer.valueOf(Protocol.Transaction.parseFrom(this.param.getTransactionBean().getBytes().get(0)).getRawData().getContract(0).getType().equals(Protocol.Transaction.Contract.ContractType.TriggerSmartContract) ? 2 : 1));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void updateResourceAndAccount(BaseParam baseParam, Protocol.Transaction transaction, Protocol.Account account, final GrpcAPI.AccountResourceMessage accountResourceMessage) {
        if (baseParam instanceof DappDetailParam) {
            try {
                String owner = TransactionUtils.getOwner(transaction);
                Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                boolean z = false;
                if (transaction.getRawData().getContract(0).getPermissionId() == 0 && selectedPublicWallet != null && TextUtils.equals(owner, selectedPublicWallet.getAddress())) {
                    z = WalletUtils.checkHaveOwnerPermissions(selectedPublicWallet.getAddress(), account.getOwnerPermission());
                }
                baseParam.setHasOwnerPermission(z);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        this.resourceConsumedBuilder.asyncBuildTransactionResources(transaction, account, accountResourceMessage).subscribe(new IObserver(new ICallback<ResourceConsumedBean>() {
            @Override
            public void onResponse(String str, ResourceConsumedBean resourceConsumedBean) {
                setFeeView(resourceConsumedBean, accountResourceMessage, false);
            }

            @Override
            public void onFailure(String str, String str2) {
                GlobalFeeResourceView globalFeeResourceView = GlobalFeeResourceView.this;
                globalFeeResourceView.setFeeView(globalFeeResourceView.resourceConsumedBuilder.build(), accountResourceMessage, true);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                disposables.add(disposable);
            }
        }, "Build Resources"));
    }

    private void initView() {
        this.btTotalConsumed.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Context context = getContext();
                XPopup.Builder popupAnimation = new XPopup.Builder(context).maxWidth(XPopupUtils.getScreenWidth(context)).maxHeight((int) (XPopupUtils.getScreenHeight(context) * 0.6f)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation);
                GlobalFeeResourceView globalFeeResourceView = GlobalFeeResourceView.this;
                popupAnimation.asCustom(new ResourceTotalPopup(context, globalFeeResourceView.resourceConsumedBuilder.build())).show();
            }
        });
        this.ivTips.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Context context = view.getContext();
                new MultiLineTextPopupView.Builder().addKeyValue(context.getString(R.string.confirm_transaction_resource_tips_0), "").setItemPadding(UIUtils.dip2px(12.0f)).setRequiredWidth(XPopupUtils.getScreenWidth(context) - (UIUtils.dip2px(20.0f) * 2)).setPreferredShowUp(true).setAnchor(view).build(context).show();
                logEvent(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_COST_RESOURCE);
            }
        });
        this.ivTipsResource.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                Context context = view.getContext();
                new MultiLineTextPopupView.Builder().addKeyValue(context.getString(R.string.confirm_transaction_resource_tips_1), "").setItemPadding(UIUtils.dip2px(12.0f)).setRequiredWidth(XPopupUtils.getScreenWidth(context) - (UIUtils.dip2px(20.0f) * 2)).setPreferredShowUp(true).setAnchor(view).build(context).show();
            }
        });
        this.ivTipsFee.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                Context context = view.getContext();
                new MultiLineTextPopupView.Builder().addKeyValue(context.getString(R.string.confirm_transaction_resource_tips_2), "").setItemPadding(UIUtils.dip2px(12.0f)).setRequiredWidth(XPopupUtils.getScreenWidth(context) - (UIUtils.dip2px(20.0f) * 2)).setPreferredShowUp(true).setAnchor(view).build(context).show();
            }
        });
        this.ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initView$0(view);
            }
        });
        this.ivFeeLoading.setImageResource(R.mipmap.circle_loading_20);
        this.ivFeeLoading.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1000L);
        rotateAnimation.setRepeatCount(-1);
        this.ivFeeLoading.startAnimation(rotateAnimation);
    }

    public void lambda$initView$0(View view) {
        boolean z = !this.showResourceView;
        this.showResourceView = z;
        try {
            if (z) {
                ResourceConsumedBean build = this.resourceConsumedBuilder.build();
                this.ivArrow.setImageResource(R.mipmap.ic_arrow_up);
                this.resourceView.setVisibility(View.VISIBLE);
                this.resourceView.bindData(this.param, build, new Number[][]{new Number[]{Long.valueOf(this.deductedBandwidth), this.deductedBandwidthInTrx}, new Number[]{Long.valueOf(this.deductedEnergy), this.deductedEnergyInTrx}});
            } else {
                this.ivArrow.setImageResource(R.mipmap.ic_arrow_down);
                this.resourceView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        logEvent(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_FEE);
    }

    private void stopLoading() {
        this.ivFeeLoading.clearAnimation();
        this.ivFeeLoading.setVisibility(View.GONE);
    }

    public void setFeeView(ResourceConsumedBean resourceConsumedBean, GrpcAPI.AccountResourceMessage accountResourceMessage, boolean z) {
        CharSequence charSequence;
        String formatPrice3;
        long energyUserConsumed = resourceConsumedBean.getEnergyUserConsumed();
        resourceConsumedBean.getEnergyUserPenalty();
        long energyCreatorConsumed = resourceConsumedBean.getEnergyCreatorConsumed();
        long energyLimit = accountResourceMessage.getEnergyLimit() - accountResourceMessage.getEnergyUsed();
        long deductedBandwidth = resourceConsumedBean.getDeductedBandwidth();
        double availableBalance = resourceConsumedBean.getAvailableBalance();
        long bandWidthConsumed = resourceConsumedBean.getBandWidthConsumed();
        boolean isActive = resourceConsumedBean.isActive();
        this.llResourceAll.setVisibility(View.VISIBLE);
        if (resourceConsumedBean.getEnergyUserConsumedPercent() < 100 && resourceConsumedBean.getEnergyConsumed() > 0 && resourceConsumedBean.getEnergyConsumed() == resourceConsumedBean.getEnergyUserConsumed()) {
            this.tvNotEnoughTip.setVisibility(View.VISIBLE);
        }
        Pair<Long, Long> deductedEnergy = FeeUtils.getDeductedEnergy(energyUserConsumed, energyLimit);
        this.deductedEnergy = ((Long) deductedEnergy.second).longValue();
        this.deductedBandwidth = deductedBandwidth;
        this.deductedBandwidthInTrx = BigDecimalUtils.mul_(BigDecimalUtils.toBigDecimal(Long.valueOf(deductedBandwidth)), BigDecimalUtils.toBigDecimal(Double.valueOf(TronConfig.feeBandWidth)));
        this.deductedEnergyInTrx = BigDecimalUtils.mul_(BigDecimalUtils.toBigDecimal(Long.valueOf(this.deductedEnergy)), BigDecimalUtils.toBigDecimal(Double.valueOf(TronConfig.feeEnergy)));
        BigDecimal add = BigDecimal.ZERO.add(BigDecimal.valueOf(resourceConsumedBean.getMultiFee())).add(BigDecimal.valueOf(resourceConsumedBean.getActiveFee())).add(BigDecimal.valueOf(resourceConsumedBean.getSpecialContractFee())).add(BigDecimal.valueOf(resourceConsumedBean.getMemoFee())).add(this.deductedBandwidthInTrx).add(this.deductedEnergyInTrx);
        FeeReporting.addFee(this.param, add.doubleValue());
        if (BigDecimalUtils.MoreThan((Object) add, (Object) 0)) {
            charSequence = "";
            String format = String.format("%s%s TRX", (!(BigDecimalUtils.equalsZero((Number) this.deductedBandwidthInTrx) && BigDecimalUtils.equalsZero((Number) this.deductedEnergyInTrx)) && isActive) ? APPROXIMATE : "", StringTronUtil.subZeroAndDot(StringTronUtil.formatNumber(add.doubleValue(), 6)));
            this.rlFee.setVisibility(View.VISIBLE);
            this.tvFee.setText(format);
            BigDecimal mul_ = BigDecimalUtils.mul_(Double.valueOf(add.doubleValue()), Double.valueOf(SpAPI.THIS.getPrice()));
            if (BigDecimalUtils.LessThan(mul_, "0.001") && BigDecimalUtils.MoreThan(mul_, "0")) {
                StringBuilder sb = new StringBuilder("<0.001");
                sb.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                formatPrice3 = sb.toString();
            } else {
                formatPrice3 = StringTronUtil.formatPrice3(Double.valueOf(mul_.doubleValue()));
            }
            this.tvFeePrice.setText(formatPrice3);
        } else {
            charSequence = "";
            this.rlFee.setVisibility(View.GONE);
            this.resourceView.setVisibility(View.GONE);
        }
        double doubleValue = BigDecimalUtils.div_(Double.valueOf(resourceConsumedBean.getCurrentFeeLimit()), Double.valueOf(Math.pow(10.0d, 6.0d))).doubleValue();
        if (BigDecimalUtils.equalsZero((Number) Double.valueOf(doubleValue))) {
            doubleValue = 225.0d;
        }
        double d = doubleValue;
        boolean MoreThan = BigDecimalUtils.MoreThan(add, Double.valueOf(availableBalance));
        if (z) {
            this.llOtherError.setVisibility(View.VISIBLE);
            this.tvOtherError.setText(R.string.confirm_energy_cost_request_fail);
        } else if (MoreThan && this.rlFee.getVisibility() == 0) {
            BaseParam baseParam = this.param;
            if (baseParam != null && baseParam.isActives()) {
                this.llOtherError.setVisibility(View.VISIBLE);
                this.tvOtherError.setText(R.string.low_mutil_owner_balance);
            } else {
                this.llOtherError.setVisibility(View.VISIBLE);
                this.tvOtherError.setText(R.string.confirm_fee_low_balance);
            }
        } else if (BigDecimalUtils.moreThanOrEqual(BigDecimalUtils.mul_(BigDecimalUtils.toBigDecimal(Long.valueOf(energyUserConsumed)), Double.valueOf(TronConfig.feeEnergy)), Double.valueOf(d))) {
            this.llOtherError.setVisibility(View.VISIBLE);
            Context context = getContext();
            this.tvOtherError.setText(context.getString(R.string.confirm_fee_warning_energy_cost, StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(Double.valueOf(d))) + "TRX"));
        } else {
            this.llOtherError.setVisibility(View.GONE);
        }
        FeeResourceCallback feeResourceCallback = this.feeCallback;
        if (feeResourceCallback != null) {
            feeResourceCallback.onGetFee(!MoreThan, BigDecimalUtils.mul_(BigDecimalUtils.toBigDecimal(Long.valueOf(energyUserConsumed)), Double.valueOf(TronConfig.feeEnergy)).doubleValue(), BigDecimalUtils.mul_(BigDecimalUtils.toBigDecimal(Long.valueOf(energyCreatorConsumed)), Double.valueOf(TronConfig.feeEnergy)).doubleValue(), d);
        }
        long j = bandWidthConsumed - deductedBandwidth;
        boolean MoreThan2 = BigDecimalUtils.MoreThan((Object) Long.valueOf(j), (Object) 0);
        boolean MoreThan3 = BigDecimalUtils.MoreThan(deductedEnergy.first, (Object) 0);
        String format2 = String.format("%s %s", NumUtils.numFormatToK(j), AppContextUtil.getContext().getString(R.string.bandwidth));
        String format3 = String.format("%s %s", NumUtils.numFormatToK(((Long) deductedEnergy.first).longValue()), AppContextUtil.getContext().getString(R.string.energy));
        LogUtils.d("FeeResource", "formattedCostBandwidth： " + format2 + "  formattedCostEnergy:  " + format3);
        this.rlResourceConsume.setVisibility(View.VISIBLE);
        if (MoreThan2 && MoreThan3) {
            int screenWidth = (UIUtils.getScreenWidth(getContext()) - (this.tvResConsumeLeft.getMeasuredWidth() + UIUtils.dip2px(28.0f))) - UIUtils.dip2px(40.0f);
            TextPaint paint = this.tvResConsumeBand.getPaint();
            int measureText = (int) paint.measureText(format2 + Marker.ANY_NON_NULL_MARKER + format3);
            this.tvResConsumeBand.setText(format2);
            this.tvResConsumeMid.setVisibility(View.VISIBLE);
            if (measureText > screenWidth) {
                this.tvResConsumeEnergy.setText(charSequence);
                this.tvResConsumeEnergyUnder.setVisibility(View.VISIBLE);
                this.tvResConsumeEnergyUnder.setText(format3);
            } else {
                this.tvResConsumeEnergy.setVisibility(View.VISIBLE);
                this.tvResConsumeEnergy.setText(format3);
            }
        } else {
            CharSequence charSequence2 = charSequence;
            if (MoreThan2) {
                this.tvResConsumeBand.setText(format2);
                this.tvResConsumeMid.setVisibility(View.GONE);
            } else if (MoreThan3) {
                this.tvResConsumeMid.setVisibility(View.GONE);
                this.tvResConsumeBand.setText(charSequence2);
                this.tvResConsumeEnergy.setVisibility(View.VISIBLE);
                this.tvResConsumeEnergy.setText(format3);
            } else {
                this.rlResourceConsume.setVisibility(View.GONE);
            }
        }
        this.tvResConsumeEnergy.setEnableUnderDashLine(false);
        this.tvResConsumeEnergyUnder.setEnableUnderDashLine(false);
        stopLoading();
    }

    public void showEnergyConsumedDetail(View view, long j, long j2) {
        String str = getContext().getResources().getString(R.string.penalty_energy_will_be_used) + "  " + getContext().getResources().getString(R.string.learn_more) + " ";
        SpannableString spannableString = new SpannableString(str);
        CenterSpaceImageSpan centerSpaceImageSpan = new CenterSpaceImageSpan(getContext(), R.mipmap.ic_right_arrow_big, 2, UIUtils.dip2px(4.0f), UIUtils.dip2px(4.0f));
        int length = str.length();
        spannableString.setSpan(centerSpaceImageSpan, length - 1, length, 33);
        CharSequence[] charSequenceArr = {getContext().getResources().getString(R.string.normal_energy_used), getContext().getResources().getString(R.string.penalty_energy_used), spannableString};
        ConfirmExtraTextClickable confirmExtraTextClickable = new ConfirmExtraTextClickable();
        confirmExtraTextClickable.setRight(charSequenceArr[2]);
        confirmExtraTextClickable.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                lambda$showEnergyConsumedDetail$1(view2);
            }
        });
        confirmExtraTextClickable.setTextGravity(3);
        new MultiLineTextPopupView.Builder().setAnchor(view).setRequiredWidth((int) (UIUtils.getScreenWidth(getContext()) * 0.8f)).addKeyValue(charSequenceArr[0], StringTronUtil.formatNumber0(j) + " " + AppContextUtil.getContext().getString(R.string.energy)).addKeyValue(charSequenceArr[1], StringTronUtil.formatNumber0((double) j2) + " " + AppContextUtil.getContext().getString(R.string.energy)).addItem(confirmExtraTextClickable).setItemPadding(UIUtils.dip2px(5.0f)).setPreferredShowUp(true).setShowArrow(true).build(getContext()).show();
    }

    public void lambda$showEnergyConsumedDetail$1(View view) {
        UIUtils.toEnergyPenaltyDetails(getContext());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        CompositeDisposable compositeDisposable = this.disposables;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void onRefreshAccountComplete(boolean z, final Protocol.Transaction transaction, final Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
        if (z && pair != null) {
            BaseParam baseParam = this.param;
            if (!(baseParam instanceof TransferParam)) {
                updateResourceAndAccount(baseParam, transaction, (Protocol.Account) pair.first, (GrpcAPI.AccountResourceMessage) pair.second);
                return;
            }
            this.disposables.add(Observable.create(new ObservableOnSubscribe() {
                @Override
                public final void subscribe(ObservableEmitter observableEmitter) {
                    lambda$onRefreshAccountComplete$2(observableEmitter);
                }
            }).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$onRefreshAccountComplete$3(transaction, pair, obj);
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$onRefreshAccountComplete$4(transaction, pair, (Throwable) obj);
                }
            }));
        }
    }

    public void lambda$onRefreshAccountComplete$2(ObservableEmitter observableEmitter) throws Exception {
        try {
            boolean z = false;
            Protocol.Account queryAccount = TronAPI.queryAccount(StringTronUtil.decode58Check(((TransferParam) this.param).getToAddress()), false);
            if (queryAccount != null && queryAccount.toString().length() != 0) {
                z = true;
            }
            ((TransferParam) this.param).setAccountActive(z);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        observableEmitter.onNext(this.param);
        observableEmitter.onComplete();
    }

    public void lambda$onRefreshAccountComplete$3(Protocol.Transaction transaction, Pair pair, Object obj) throws Exception {
        updateResourceAndAccount(this.param, transaction, (Protocol.Account) pair.first, (GrpcAPI.AccountResourceMessage) pair.second);
    }

    public void lambda$onRefreshAccountComplete$4(Protocol.Transaction transaction, Pair pair, Throwable th) throws Exception {
        updateResourceAndAccount(this.param, transaction, (Protocol.Account) pair.first, (GrpcAPI.AccountResourceMessage) pair.second);
    }

    public class ResourceTotalPopup extends CenterPopupView {
        private PopupGlobalFeeDetailsBinding binding;
        View btFeeTips;
        View btResourceTip;
        View btnConfirm;
        private PopupGlobalFeeDetailsBinding detailsBinding;
        View ivResourceAdd;
        View ivUserAdd;
        View llActive;
        View llCreator;
        View llFeeBottom;
        View llFeeContract;
        View llFeeMemo;
        View llFeeMulti;
        private ResourceConsumedBean resourceConsumedBean;
        View rlFeeConsumed;
        TextView tvBandwidth;
        TextView tvCreatorEnergy;
        DashUnderLineTextView tvEnergy;
        TextView tvFee;
        TextView tvFeeActive;
        TextView tvFeeContract;
        TextView tvFeeContractName;
        TextView tvFeeMemo;
        TextView tvFeeMulti;
        TextView tvUserBandwidth;
        TextView tvUserEnergy;

        @Override
        public int getImplLayoutId() {
            return R.layout.popup_global_fee_details;
        }

        public ResourceTotalPopup(Context context, ResourceConsumedBean resourceConsumedBean) {
            super(context);
            this.resourceConsumedBean = resourceConsumedBean;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            mappingId(getPopupImplView());
            this.btResourceTip.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    Context context = view.getContext();
                    new MultiLineTextPopupView.Builder().addKeyValue(context.getString(R.string.transaction_resource_detail_tip), "").setItemPadding(UIUtils.dip2px(12.0f)).setOffsetX(UIUtils.dip2px(30.0f)).setRequiredWidth(XPopupUtils.getScreenWidth(context) - (UIUtils.dip2px(40.0f) * 2)).setPreferredShowUp(true).setAnchor(view).build(context).show();
                }
            });
            this.btFeeTips.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    Context context = view.getContext();
                    new MultiLineTextPopupView.Builder().addKeyValue(context.getString(R.string.transaction_resource_detail_fee_tip), "").setItemPadding(UIUtils.dip2px(12.0f)).setOffsetX(UIUtils.dip2px(30.0f)).setRequiredWidth(XPopupUtils.getScreenWidth(context) - UIUtils.dip2px(130.0f)).setPreferredShowUp(true).setAnchor(view).build(context).show();
                }
            });
            if (this.resourceConsumedBean.getBandWidthConsumed() > 0) {
                this.tvBandwidth.setText(String.format("%s %s", NumUtils.numFormatToK(this.resourceConsumedBean.getBandWidthConsumed()), AppContextUtil.getContext().getString(R.string.bandwidth)));
                this.tvBandwidth.setVisibility(View.VISIBLE);
            } else {
                this.tvBandwidth.setVisibility(View.GONE);
            }
            this.ivResourceAdd.setVisibility((this.resourceConsumedBean.getBandWidthConsumed() <= 0 || this.resourceConsumedBean.getEnergyConsumed() <= 0) ? View.GONE : View.VISIBLE);
            if (this.resourceConsumedBean.getEnergyConsumed() > 0) {
                this.tvEnergy.setText(String.format("%s %s", NumUtils.numFormatToK(this.resourceConsumedBean.getEnergyConsumed()), AppContextUtil.getContext().getString(R.string.energy)));
                this.tvEnergy.setVisibility(View.VISIBLE);
            } else {
                this.tvEnergy.setVisibility(View.GONE);
            }
            if (this.resourceConsumedBean.getEnergyPenalty() > 0) {
                this.tvEnergy.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        showEnergyConsumedDetail(view, ResourceTotalPopup.this.resourceConsumedBean.getEnergyConsumed() - ResourceTotalPopup.this.resourceConsumedBean.getEnergyPenalty(), ResourceTotalPopup.this.resourceConsumedBean.getEnergyPenalty());
                    }
                });
                this.tvEnergy.setEnableUnderDashLine(true);
            } else {
                this.tvEnergy.setEnableUnderDashLine(false);
            }
            if (this.resourceConsumedBean.getBandWidthConsumed() > 0) {
                this.tvUserBandwidth.setText(String.format("%s %s", NumUtils.numFormatToK(this.resourceConsumedBean.getBandWidthConsumed()), AppContextUtil.getContext().getString(R.string.bandwidth)));
            }
            if (this.resourceConsumedBean.getEnergyUserConsumed() > 0) {
                this.ivUserAdd.setVisibility(View.VISIBLE);
                this.tvUserEnergy.setText(String.format("%s %s", NumUtils.numFormatToK(this.resourceConsumedBean.getEnergyUserConsumed()), AppContextUtil.getContext().getString(R.string.energy)));
                this.tvUserEnergy.setVisibility(View.VISIBLE);
            } else {
                this.ivUserAdd.setVisibility(View.GONE);
                this.tvUserEnergy.setVisibility(View.GONE);
            }
            if (this.resourceConsumedBean.getEnergyConsumed() <= 0) {
                this.llCreator.setVisibility(View.GONE);
            } else if (this.resourceConsumedBean.getEnergyCreatorConsumed() > 0) {
                this.tvCreatorEnergy.setText(String.format("%s %s", NumUtils.numFormatToK(this.resourceConsumedBean.getEnergyCreatorConsumed()), AppContextUtil.getContext().getString(R.string.energy)));
                this.llCreator.setVisibility(View.VISIBLE);
            } else {
                TextView textView = this.tvCreatorEnergy;
                textView.setText("0 " + AppContextUtil.getContext().getString(R.string.energy));
                this.llCreator.setVisibility(View.VISIBLE);
            }
            BigDecimal add = BigDecimal.ZERO.add(BigDecimal.valueOf(this.resourceConsumedBean.getMultiFee())).add(BigDecimal.valueOf(this.resourceConsumedBean.getActiveFee())).add(BigDecimal.valueOf(this.resourceConsumedBean.getSpecialContractFee())).add(BigDecimal.valueOf(this.resourceConsumedBean.getMemoFee()));
            this.tvFee.setText(String.format("%s TRX", StringTronUtil.subZeroAndDot(StringTronUtil.formatNumber(add.doubleValue(), 6))));
            if (!BigDecimalUtils.MoreThan((Object) add, (Object) 0)) {
                this.rlFeeConsumed.setBackground(getContext().getDrawable(R.drawable.roundborder_ebedf0_4));
            }
            this.llFeeBottom.setVisibility(BigDecimalUtils.MoreThan((Object) add, (Object) 0) ? View.VISIBLE : View.GONE);
            if (this.resourceConsumedBean.getMultiFee() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                this.tvFeeMulti.setText(String.format("%s TRX", StringTronUtil.subZeroAndDot(StringTronUtil.formatNumber(this.resourceConsumedBean.getMultiFee(), 6))));
                this.llFeeMulti.setVisibility(View.VISIBLE);
            } else {
                this.llFeeMulti.setVisibility(View.GONE);
            }
            if (this.resourceConsumedBean.getActiveFee() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                this.tvFeeActive.setText(String.format("%s TRX", StringTronUtil.subZeroAndDot(StringTronUtil.formatNumber(this.resourceConsumedBean.getActiveFee(), 6))));
                this.llActive.setVisibility(View.VISIBLE);
            } else {
                this.llActive.setVisibility(View.GONE);
            }
            if (this.resourceConsumedBean.getSpecialContractFee() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                this.tvFeeContractName.setText(this.resourceConsumedBean.getSpecialContractName());
                this.tvFeeContract.setText(String.format("%s TRX", StringTronUtil.subZeroAndDot(StringTronUtil.formatNumber(this.resourceConsumedBean.getSpecialContractFee(), 6))));
                this.llFeeContract.setVisibility(View.VISIBLE);
            } else {
                this.llFeeContract.setVisibility(View.GONE);
            }
            if (this.resourceConsumedBean.getMemoFee() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                this.tvFeeMemo.setText(String.format("%s TRX", StringTronUtil.subZeroAndDot(StringTronUtil.formatNumber(this.resourceConsumedBean.getMemoFee(), 6))));
                this.llFeeMemo.setVisibility(View.VISIBLE);
            } else {
                this.llFeeMemo.setVisibility(View.GONE);
            }
            this.btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    GlobalFeeResourceView.ResourceTotalPopup.this.lambda$onCreate$0(view);
                }
            });
        }

        public void lambda$onCreate$0(View view) {
            dismiss();
        }

        private void mappingId(View view) {
            PopupGlobalFeeDetailsBinding bind = PopupGlobalFeeDetailsBinding.bind(view);
            this.detailsBinding = bind;
            this.btResourceTip = bind.ivTips;
            this.tvBandwidth = this.detailsBinding.tvConsumeResourceBandwidth;
            this.ivResourceAdd = this.detailsBinding.tvConsumeResourceMid;
            this.tvEnergy = this.detailsBinding.tvConsumeResourceEnergy;
            this.tvUserBandwidth = this.detailsBinding.tvConsumeUserBandwidth;
            this.ivUserAdd = this.detailsBinding.tvConsumeUserMid;
            this.tvUserEnergy = this.detailsBinding.tvConsumeUserEnergy;
            this.llCreator = this.detailsBinding.rlResourceCreator;
            this.tvCreatorEnergy = this.detailsBinding.tvConsumeCreatorEnergy;
            this.rlFeeConsumed = this.detailsBinding.rlFeeConsume;
            this.btFeeTips = this.detailsBinding.ivFeeTips;
            this.tvFee = this.detailsBinding.tvConsumedFeeTotal;
            this.llFeeMulti = this.detailsBinding.rlFeeMulti;
            this.tvFeeMulti = this.detailsBinding.tvFeeMultiValue;
            this.llActive = this.detailsBinding.rlFeeActive;
            this.tvFeeActive = this.detailsBinding.tvFeeActiveValue;
            this.llFeeContract = this.detailsBinding.rlFeeByContract;
            this.tvFeeContractName = this.detailsBinding.tvFeeByContract;
            this.tvFeeContract = this.detailsBinding.tvFeeByContactValue;
            this.llFeeMemo = this.detailsBinding.rlFeeMemo;
            this.tvFeeMemo = this.detailsBinding.tvFeeMemoValue;
            this.llFeeBottom = this.detailsBinding.llFeeBottom;
            this.btnConfirm = this.detailsBinding.btnConfirm;
        }
    }
}
