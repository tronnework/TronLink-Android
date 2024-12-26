package com.tron.wallet.business.stakev2.unstake;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.stakev2.ResState;
import com.tron.wallet.business.stakev2.unstake.UnStakeV2Contract;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tron.wallet.common.components.StakePercentView;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.KeyboardUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.databinding.AcUnstakeV2Binding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class UnStakeV2Activity extends BaseActivity<UnStakeV2Presenter, UnStakeV2Model> implements UnStakeV2Contract.View {
    View arrowBottom;
    View arrowTop;
    private AcUnstakeV2Binding binding;
    View btNext;
    EditText etInput;
    private boolean isMultiSign;
    View llErrorTips;
    StakePercentView percentView;
    private ResSwitch resSwitch;
    View rlTabBandwidth;
    View rlTabEnergy;
    View rlUnStake;
    View toManager;
    TextView tvAvailableUnStake;
    TextView tvBottomHint;
    TextView tvControlTips;
    TextView tvErrorTips;
    TextView tvResDesc;
    TextView tvResNew;
    TextView tvResOld;
    TextView tvTabBandwidth;
    TextView tvTabEnergy;
    TextView tvUnAvailable;
    TextView tvVoteNew;
    TextView tvVoteOld;
    StakeHeaderView unStakeHeader;

    public enum Error {
        None,
        Min1,
        MoreThan,
        UnStake32
    }

    public static void start(Activity activity, Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        Intent intent = new Intent(activity, UnStakeV2Activity.class);
        intent.putExtra(UnStakeV2Presenter.Key_AccountResourceMessage, accountResourceMessage);
        intent.putExtra(UnStakeV2Presenter.Key_Account, account);
        activity.startActivity(intent);
    }

    public static void startFromMultiSign(Activity activity, String str, Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        Intent intent = new Intent(activity, UnStakeV2Activity.class);
        intent.putExtra(UnStakeV2Presenter.Key_ControlAddress, str);
        intent.putExtra(UnStakeV2Presenter.Key_AccountResourceMessage, accountResourceMessage);
        intent.putExtra(UnStakeV2Presenter.Key_Account, account);
        activity.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcUnstakeV2Binding inflate = AcUnstakeV2Binding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setView(root, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.unStakeHeader = this.binding.unStakeHeader;
        this.percentView = this.binding.percentView;
        this.rlTabEnergy = this.binding.header.rlTabEnergy;
        this.rlTabBandwidth = this.binding.header.rlTabBandwidth;
        this.tvTabEnergy = this.binding.header.tvTabEnergy;
        this.tvTabBandwidth = this.binding.header.tvTabBandwidth;
        this.tvAvailableUnStake = this.binding.tvAvailableUnStake;
        this.tvResOld = this.binding.bottom.tvResOld;
        this.tvResNew = this.binding.bottom.tvResNew;
        this.tvVoteOld = this.binding.bottom.tvVoteOld;
        this.tvVoteNew = this.binding.bottom.tvVoteNew;
        this.tvUnAvailable = this.binding.tvUnAvailable;
        this.tvResDesc = this.binding.bottom.tvResDesc;
        this.toManager = this.binding.toManager;
        this.btNext = this.binding.btNext;
        this.etInput = this.binding.etInput;
        this.llErrorTips = this.binding.liErrorTips;
        this.tvErrorTips = this.binding.tvErrorTips;
        this.tvControlTips = this.binding.tvControlTips;
        this.tvBottomHint = this.binding.tvBottomHint;
        this.rlUnStake = this.binding.rlUnStake;
        this.arrowBottom = this.binding.bottom.arrowBottom;
        this.arrowTop = this.binding.bottom.arrowTop;
    }

    @Override
    protected void processData() {
        updateMultiTitle();
        this.resSwitch = new ResSwitch(this.rlTabEnergy, this.rlTabBandwidth, this.tvTabEnergy, this.tvTabBandwidth);
        addClick();
        ((UnStakeV2Presenter) this.mPresenter).loadData();
    }

    private void addClick() {
        TextDotUtils.setTextChangedListener_Dot(this.etInput);
        this.unStakeHeader.setOnHeaderClickListener(new StakeHeaderView.OnHeaderClickListener() {
            @Override
            public void onQuestion() {
                StakeHeaderView.OnHeaderClickListener.-CC.$default$onQuestion(this);
            }

            @Override
            public void onRightClick() {
                StakeHeaderView.OnHeaderClickListener.-CC.$default$onRightClick(this);
            }

            @Override
            public void onLeftClick() {
                KeyboardUtil.closeKeybord((Activity) mContext);
                finish();
            }
        });
        this.percentView.setOnClickPercentListener(((UnStakeV2Presenter) this.mPresenter).OnClickPercentListener());
        this.resSwitch.setOnResSwitchListener(((UnStakeV2Presenter) this.mPresenter).OnResSwitchListener());
        this.btNext.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((UnStakeV2Presenter) mPresenter).next(!isMultiSign);
            }
        });
        this.toManager.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((UnStakeV2Presenter) mPresenter).toManager(isMultiSign);
            }
        });
        this.etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ((UnStakeV2Presenter) mPresenter).afterTextChanged(editable);
            }
        });
    }

    @Override
    public Intent getIIntent() {
        return getIntent();
    }

    public void updateMultiTitle() {
        String format;
        final String stringExtra = getIIntent().getStringExtra(UnStakeV2Presenter.Key_ControlAddress);
        boolean z = !StringTronUtil.isEmpty(stringExtra);
        this.isMultiSign = z;
        if (z) {
            this.unStakeHeader.setHeader(getString(R.string.unstake_multi_sign), null, "");
            this.tvControlTips.setVisibility(View.VISIBLE);
            this.toManager.setVisibility(View.GONE);
            String formatAddressSingleLine = AddressNameUtils.getFormatAddressSingleLine(stringExtra);
            if (!TextUtils.isEmpty(formatAddressSingleLine)) {
                stringExtra = formatAddressSingleLine;
            }
            this.tvControlTips.setText(getString(R.string.unstake_multi_sign_controller_tips, new Object[]{stringExtra}));
            this.tvControlTips.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$updateMultiTitle$0(stringExtra);
                }
            });
        } else {
            this.unStakeHeader.setHeader(getString(R.string.un_stake_v2_title), null, "");
            this.tvControlTips.setVisibility(View.GONE);
            this.toManager.setVisibility(View.VISIBLE);
        }
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            format = String.format(StringTronUtil.getResString(R.string.un_stake_v2_bottom), Integer.valueOf(TronSetting.stakeExpireDay), Integer.valueOf(TronSetting.stakeExpireDay));
        } else {
            format = String.format(StringTronUtil.getResString(R.string.un_stake_v2_bottom), Integer.valueOf(TronSetting.stakeExpireDay));
        }
        this.tvBottomHint.setText(format);
    }

    public void lambda$updateMultiTitle$0(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvControlTips, str);
        this.tvControlTips.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    @Override
    public void updateUI(UnStakeV2Bean unStakeV2Bean, ResState resState) {
        String bigDecimalUtils = BigDecimalUtils.toString(Long.valueOf(resState == ResState.Energy ? unStakeV2Bean.getAvailableUnFreezeEnergy() : unStakeV2Bean.getAvailableUnFreezeBandwidth()));
        String bigDecimalUtils2 = BigDecimalUtils.toString(Long.valueOf(resState == ResState.Energy ? unStakeV2Bean.getEnergySum() : unStakeV2Bean.getBandwidthSum()));
        String bigDecimalUtils3 = BigDecimalUtils.toString(Long.valueOf(unStakeV2Bean.getVotesSum()));
        String bigDecimalUtils4 = BigDecimalUtils.toString(Long.valueOf(resState == ResState.Energy ? unStakeV2Bean.getUnAvailableUnFreezeEnergy() : unStakeV2Bean.getUnAvailableUnFreezeBandwidth()));
        this.tvResDesc.setText(resState == ResState.Energy ? R.string.energy : R.string.bandwidth);
        TextView textView = this.tvAvailableUnStake;
        textView.setText(TextDotUtils.getDotText(bigDecimalUtils) + " TRX");
        this.tvResOld.setText(TextDotUtils.getDotText(bigDecimalUtils2));
        this.tvVoteOld.setText(TextDotUtils.getDotText(bigDecimalUtils3));
        TextView textView2 = this.tvUnAvailable;
        textView2.setText(TextDotUtils.getDotText(bigDecimalUtils4) + " TRX");
        if (resState == ResState.Energy) {
            this.rlUnStake.setVisibility(unStakeV2Bean.getUnAvailableUnFreezeEnergy() > 0 ? View.VISIBLE : View.GONE);
        } else {
            this.rlUnStake.setVisibility(unStakeV2Bean.getUnAvailableUnFreezeBandwidth() > 0 ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void updateInput(String str, String str2) {
        this.tvResNew.setText(TextDotUtils.getDotText(str));
        this.tvVoteNew.setText(TextDotUtils.getDotText(str2));
    }

    @Override
    public void hideArrow(boolean z) {
        int i = 8;
        int i2 = z ? 8 : 0;
        try {
            this.arrowTop.setVisibility(i2);
            this.tvResOld.setVisibility(i2);
            if (!z) {
                i = 0;
            }
            this.arrowBottom.setVisibility(i);
            this.tvVoteOld.setVisibility(i);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public String getInputText() {
        return this.etInput.getText().toString().trim();
    }

    @Override
    public void setInputText(String str) {
        this.etInput.setText(str);
    }

    @Override
    public void resetPercent(ResState resState) {
        this.percentView.setPowerState(resState);
        setButtonEnable(false);
    }

    @Override
    public void setButtonEnable(boolean z) {
        this.btNext.setEnabled(z);
    }

    @Override
    public void showError(Error error) {
        if (error == Error.None) {
            this.llErrorTips.setVisibility(View.GONE);
            return;
        }
        this.llErrorTips.setVisibility(View.VISIBLE);
        int i = fun5.$SwitchMap$com$tron$wallet$business$stakev2$unstake$UnStakeV2Activity$Error[error.ordinal()];
        if (i == 1) {
            this.tvErrorTips.setText(R.string.un_stake_v2_error_1);
        } else if (i == 2) {
            this.tvErrorTips.setText(R.string.un_stake_v2_error_2);
        } else if (i != 3) {
        } else {
            this.tvErrorTips.setText(R.string.un_stake_v2_error_3);
        }
    }

    static class fun5 {
        static final int[] $SwitchMap$com$tron$wallet$business$stakev2$unstake$UnStakeV2Activity$Error;

        static {
            int[] iArr = new int[Error.values().length];
            $SwitchMap$com$tron$wallet$business$stakev2$unstake$UnStakeV2Activity$Error = iArr;
            try {
                iArr[Error.Min1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$unstake$UnStakeV2Activity$Error[Error.MoreThan.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$unstake$UnStakeV2Activity$Error[Error.UnStake32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
