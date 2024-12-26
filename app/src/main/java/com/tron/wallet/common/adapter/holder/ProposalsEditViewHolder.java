package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabmy.proposals.bean.MakeProposalsBean;
import com.tron.wallet.common.adapter.proposal.MakeProposalsAdapter;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
public class ProposalsEditViewHolder extends BaseHolder {
    private static final String TAG = "ProposalsEditViewHolder";
    private String beforInput;
    TextView companyProposals;
    private String content;
    private DecimalFormat decimalFormat;
    ErrorEdiTextLayout eetProposals;
    EditText etProposals;
    ImageView iv;
    LinearLayout llProposals;
    private BigDecimal mAmount;
    private boolean mCanEdit;
    private Context mContext;
    private HashMap<Long, Long> mParameters;
    private TextWatcher mTextWatcher;
    private List<Long> merrorInputIds;
    private NumberFormat numberFormat;
    private NumberFormat numberFormat0;
    private MakeProposalsAdapter.ParamerChangeListener paramerListener;
    LinearLayout rlTop;
    TextView tvLine;
    TextView tvReset;
    TextView tvTop;
    TextView tvTopContent;

    public ProposalsEditViewHolder(View view, Context context, HashMap<Long, Long> hashMap, MakeProposalsAdapter.ParamerChangeListener paramerChangeListener, List<Long> list, boolean z) {
        super(view);
        this.content = "";
        this.mCanEdit = true;
        mappingId(view);
        this.mContext = context;
        this.mParameters = hashMap;
        this.merrorInputIds = list;
        this.paramerListener = paramerChangeListener;
        this.mCanEdit = z;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(6);
        NumberFormat numberInstance2 = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat0 = numberInstance2;
        numberInstance2.setMaximumFractionDigits(0);
        this.decimalFormat = new DecimalFormat("#.######");
    }

    public void bind(final MakeProposalsBean makeProposalsBean, int i) {
        if (makeProposalsBean != null) {
            if (makeProposalsBean.proposalId == 19) {
                try {
                    this.etProposals.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            if (this.mCanEdit) {
                this.etProposals.setEnabled(true);
            } else {
                this.etProposals.setEnabled(false);
            }
            TextView textView = this.tvTop;
            textView.setText("#" + makeProposalsBean.proposalId);
            this.tvTopContent.setText(makeProposalsBean.proposalsCentent);
            this.companyProposals.setText(makeProposalsBean.company);
            initValue(this.mContext, makeProposalsBean);
            judgeRange(makeProposalsBean, this.etProposals.getText().toString());
            this.eetProposals.setTextError3(this.mContext.getResources().getString(R.string.error_input_proposal));
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                    ProposalsEditViewHolder proposalsEditViewHolder = ProposalsEditViewHolder.this;
                    proposalsEditViewHolder.beforInput = proposalsEditViewHolder.etProposals.getText().toString().trim();
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    eetProposals.hideError3();
                    merrorInputIds.remove(Long.valueOf(makeProposalsBean.proposalId));
                    if (paramerListener != null) {
                        paramerListener.onErrorTipShow(merrorInputIds);
                    }
                    try {
                        judgeRange(makeProposalsBean, etProposals.getText().toString());
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                    }
                }
            };
            this.mTextWatcher = textWatcher;
            this.etProposals.addTextChangedListener(textWatcher);
        }
        this.tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvReset.setVisibility(View.GONE);
                MakeProposalsBean makeProposalsBean2 = makeProposalsBean;
                makeProposalsBean2.selectValue = makeProposalsBean2.value;
                MakeProposalsBean makeProposalsBean3 = makeProposalsBean;
                makeProposalsBean3.inputContent = makeProposalsBean3.netContent;
                mParameters.remove(Long.valueOf(makeProposalsBean.proposalId));
                if (paramerListener != null) {
                    paramerListener.onParamerChange(mParameters);
                }
                ProposalsEditViewHolder proposalsEditViewHolder = ProposalsEditViewHolder.this;
                proposalsEditViewHolder.initValue(proposalsEditViewHolder.mContext, makeProposalsBean);
            }
        });
    }

    public void judgeRange(com.tron.wallet.business.tabmy.proposals.bean.MakeProposalsBean r14, java.lang.String r15) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.adapter.holder.ProposalsEditViewHolder.judgeRange(com.tron.wallet.business.tabmy.proposals.bean.MakeProposalsBean, java.lang.String):void");
    }

    private void judgeInputValue_sun(MakeProposalsBean makeProposalsBean, BigDecimal bigDecimal, String str, String str2, String str3) {
        try {
            BigDecimal bigDecimal2 = new BigDecimal("1000000");
            BigDecimal bigDecimal3 = new BigDecimal(str3);
            int compareWithZero = compareWithZero(bigDecimal, new BigDecimal(str2));
            int compareWithZero2 = compareWithZero(bigDecimal, bigDecimal3);
            if (compareWithZero >= 0 && compareWithZero2 != 1) {
                if (str.equals(makeProposalsBean.netContent)) {
                    return;
                }
                long longValue = bigDecimal.multiply(bigDecimal2).longValue();
                if (makeProposalsBean.value != longValue) {
                    List<Long> list = this.merrorInputIds;
                    if (list != null) {
                        list.remove(Long.valueOf(makeProposalsBean.proposalId));
                    }
                    MakeProposalsAdapter.ParamerChangeListener paramerChangeListener = this.paramerListener;
                    if (paramerChangeListener != null) {
                        paramerChangeListener.onErrorTipShow(this.merrorInputIds);
                    }
                    this.mParameters.put(Long.valueOf(makeProposalsBean.proposalId), Long.valueOf(longValue));
                    makeProposalsBean.selectValue = longValue;
                    return;
                }
                return;
            }
            try {
                makeProposalsBean.selectValue = bigDecimal.multiply(bigDecimal2).longValue();
                HashMap<Long, Long> hashMap = this.mParameters;
                if (hashMap != null) {
                    hashMap.remove(Long.valueOf(makeProposalsBean.proposalId));
                }
                this.eetProposals.setTextError3(this.mContext.getResources().getString(R.string.error_input_proposal));
                this.eetProposals.showError3();
                if (!this.merrorInputIds.contains(Long.valueOf(makeProposalsBean.proposalId))) {
                    this.merrorInputIds.add(Long.valueOf(makeProposalsBean.proposalId));
                }
                MakeProposalsAdapter.ParamerChangeListener paramerChangeListener2 = this.paramerListener;
                if (paramerChangeListener2 != null) {
                    paramerChangeListener2.onErrorTipShow(this.merrorInputIds);
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    private void judgeInputValue_nomal(MakeProposalsBean makeProposalsBean, BigDecimal bigDecimal, String str, String str2, String str3) {
        try {
            BigDecimal bigDecimal2 = new BigDecimal("1000000");
            BigDecimal bigDecimal3 = new BigDecimal(str3);
            int compareWithZero = compareWithZero(bigDecimal, new BigDecimal(str2));
            int compareWithZero2 = compareWithZero(bigDecimal, bigDecimal3);
            if (compareWithZero >= 0 && compareWithZero2 != 1) {
                if (str.equals(makeProposalsBean.netContent)) {
                    return;
                }
                long longValue = bigDecimal.longValue();
                if (makeProposalsBean.value != longValue) {
                    List<Long> list = this.merrorInputIds;
                    if (list != null) {
                        list.remove(Long.valueOf(makeProposalsBean.proposalId));
                    }
                    MakeProposalsAdapter.ParamerChangeListener paramerChangeListener = this.paramerListener;
                    if (paramerChangeListener != null) {
                        paramerChangeListener.onErrorTipShow(this.merrorInputIds);
                    }
                    this.mParameters.put(Long.valueOf(makeProposalsBean.proposalId), Long.valueOf(longValue));
                    makeProposalsBean.selectValue = longValue;
                    return;
                }
                return;
            }
            try {
                makeProposalsBean.selectValue = bigDecimal.multiply(bigDecimal2).longValue();
                HashMap<Long, Long> hashMap = this.mParameters;
                if (hashMap != null) {
                    hashMap.remove(Long.valueOf(makeProposalsBean.proposalId));
                }
                this.eetProposals.setTextError3(this.mContext.getResources().getString(R.string.error_input_proposal));
                this.eetProposals.showError3();
                if (!this.merrorInputIds.contains(Long.valueOf(makeProposalsBean.proposalId))) {
                    this.merrorInputIds.add(Long.valueOf(makeProposalsBean.proposalId));
                }
                MakeProposalsAdapter.ParamerChangeListener paramerChangeListener2 = this.paramerListener;
                if (paramerChangeListener2 != null) {
                    paramerChangeListener2.onErrorTipShow(this.merrorInputIds);
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    private void judgeInputValue(MakeProposalsBean makeProposalsBean, BigDecimal bigDecimal, String str, long j, long j2) {
        try {
            BigDecimal bigDecimal2 = new BigDecimal(j2 + "");
            int compareWithZero = compareWithZero(bigDecimal, new BigDecimal(j + ""));
            int compareWithZero2 = compareWithZero(bigDecimal, bigDecimal2);
            if (compareWithZero >= 0 && compareWithZero2 != 1) {
                if (str.equals(makeProposalsBean.netContent)) {
                    return;
                }
                long longValue = bigDecimal.longValue();
                if (makeProposalsBean.value != longValue) {
                    List<Long> list = this.merrorInputIds;
                    if (list != null) {
                        list.remove(Long.valueOf(makeProposalsBean.proposalId));
                    }
                    MakeProposalsAdapter.ParamerChangeListener paramerChangeListener = this.paramerListener;
                    if (paramerChangeListener != null) {
                        paramerChangeListener.onErrorTipShow(this.merrorInputIds);
                    }
                    this.mParameters.put(Long.valueOf(makeProposalsBean.proposalId), Long.valueOf(longValue));
                    makeProposalsBean.selectValue = longValue;
                    return;
                }
                return;
            }
            try {
                makeProposalsBean.selectValue = bigDecimal.longValue();
                HashMap<Long, Long> hashMap = this.mParameters;
                if (hashMap != null) {
                    hashMap.remove(Long.valueOf(makeProposalsBean.proposalId));
                }
                this.eetProposals.setTextError3(this.mContext.getResources().getString(R.string.error_input_proposal));
                this.eetProposals.showError3();
                if (!this.merrorInputIds.contains(Long.valueOf(makeProposalsBean.proposalId))) {
                    this.merrorInputIds.add(Long.valueOf(makeProposalsBean.proposalId));
                }
                MakeProposalsAdapter.ParamerChangeListener paramerChangeListener2 = this.paramerListener;
                if (paramerChangeListener2 != null) {
                    paramerChangeListener2.onErrorTipShow(this.merrorInputIds);
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    public void initValue(android.content.Context r9, com.tron.wallet.business.tabmy.proposals.bean.MakeProposalsBean r10) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.adapter.holder.ProposalsEditViewHolder.initValue(android.content.Context, com.tron.wallet.business.tabmy.proposals.bean.MakeProposalsBean):void");
    }

    private int compareWithZero(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.compareTo(bigDecimal2);
    }

    private void mappingId(View view) {
        this.iv = (ImageView) view.findViewById(R.id.iv);
        this.tvTop = (TextView) view.findViewById(R.id.tv_top);
        this.tvTopContent = (TextView) view.findViewById(R.id.tv_top_content);
        this.tvReset = (TextView) view.findViewById(R.id.tv_reset);
        this.rlTop = (LinearLayout) view.findViewById(R.id.rl_top);
        this.etProposals = (EditText) view.findViewById(R.id.et_proposals);
        this.companyProposals = (TextView) view.findViewById(R.id.company_proposals);
        this.eetProposals = (ErrorEdiTextLayout) view.findViewById(R.id.eet_proposals);
        this.llProposals = (LinearLayout) view.findViewById(R.id.ll_proposals);
        this.tvLine = (TextView) view.findViewById(R.id.tv_line);
    }
}
