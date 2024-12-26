package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.tabmy.proposals.bean.MakeProposalsBean;
import com.tron.wallet.common.adapter.proposal.MakeProposalsAdapter;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
public class ProposalsSelectViewHolder extends BaseHolder {
    private static final String TAG = "ProposalsSelectViewHold";
    ImageView iv;
    ImageView ivRow;
    LinearLayout llProposalsContent;
    LinearLayout llSelect;
    private boolean mCanEdit;
    private Context mContext;
    private HashMap<Long, Long> mParameters;
    private MakeProposalsAdapter.ParamerChangeListener paramerListener;
    RelativeLayout rlSelect;
    LinearLayout rlTop;
    TextView tvLine;
    TextView tvReset;
    TextView tvSlect0;
    TextView tvSlect1;
    TextView tvStatue;
    TextView tvTop;
    TextView tvTopContent;

    public ProposalsSelectViewHolder(View view, Context context, HashMap<Long, Long> hashMap, MakeProposalsAdapter.ParamerChangeListener paramerChangeListener, boolean z) {
        super(view);
        this.mCanEdit = true;
        mappingId(view);
        this.mContext = context;
        this.mParameters = hashMap;
        this.paramerListener = paramerChangeListener;
        this.mCanEdit = z;
    }

    public void bind(final MakeProposalsBean makeProposalsBean, int i) {
        this.ivRow.setVisibility(View.VISIBLE);
        if (makeProposalsBean != null) {
            this.iv.setImageResource(R.drawable.gray_circle);
            TextView textView = this.tvTop;
            textView.setText("#" + makeProposalsBean.proposalId);
            this.tvTopContent.setText(makeProposalsBean.proposalsCentent);
            initText(makeProposalsBean);
            if (this.mCanEdit) {
                this.rlSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (llSelect.getVisibility() == 8) {
                                llSelect.setVisibility(View.VISIBLE);
                                changeRow(true);
                            } else {
                                llSelect.setVisibility(View.GONE);
                                changeRow(false);
                            }
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                    }
                });
            } else {
                this.ivRow.setVisibility(View.INVISIBLE);
            }
            this.tvSlect0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    llSelect.setVisibility(View.GONE);
                    if (makeProposalsBean.selectValue == 1) {
                        tvReset.setVisibility(View.VISIBLE);
                        makeProposalsBean.selectValue = 0L;
                        initText(makeProposalsBean);
                        mParameters.put(Long.valueOf(makeProposalsBean.proposalId), 0L);
                        if (paramerListener != null) {
                            paramerListener.onParamerChange(mParameters);
                            return;
                        }
                        return;
                    }
                    tvReset.setVisibility(View.GONE);
                }
            });
            this.tvSlect1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    llSelect.setVisibility(View.GONE);
                    if (makeProposalsBean.selectValue == 0) {
                        tvReset.setVisibility(View.VISIBLE);
                        makeProposalsBean.selectValue = 1L;
                        initText(makeProposalsBean);
                        mParameters.put(Long.valueOf(makeProposalsBean.proposalId), 1L);
                        if (paramerListener != null) {
                            paramerListener.onParamerChange(mParameters);
                            return;
                        }
                        return;
                    }
                    tvReset.setVisibility(View.GONE);
                }
            });
            this.tvReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvReset.setVisibility(View.GONE);
                    MakeProposalsBean makeProposalsBean2 = makeProposalsBean;
                    makeProposalsBean2.selectValue = makeProposalsBean2.value;
                    mParameters.remove(Long.valueOf(makeProposalsBean.proposalId));
                    if (paramerListener != null) {
                        paramerListener.onParamerChange(mParameters);
                    }
                    initText(makeProposalsBean);
                }
            });
        }
    }

    public void initText(MakeProposalsBean makeProposalsBean) {
        if (makeProposalsBean.selectValue == 0) {
            this.tvSlect0.setTextColor(this.mContext.getResources().getColor(R.color.black_02));
            this.tvSlect1.setTextColor(this.mContext.getResources().getColor(R.color.black_02_50));
        } else {
            this.tvSlect0.setTextColor(this.mContext.getResources().getColor(R.color.black_02_50));
            this.tvSlect1.setTextColor(this.mContext.getResources().getColor(R.color.black_02));
        }
        switch ((int) makeProposalsBean.proposalId) {
            case 14:
            case 18:
            case 21:
            case 32:
            case 44:
                this.tvStatue.setText(makeProposalsBean.selectValue == 0 ? StringTronUtil.getResString(R.string.not_allow) : StringTronUtil.getResString(R.string.allow));
                this.tvSlect0.setText(StringTronUtil.getResString(R.string.not_allow));
                this.tvSlect1.setText(StringTronUtil.getResString(R.string.allow));
                break;
            case 24:
            case 30:
            case 39:
            case 40:
            case 41:
            case 48:
            case 49:
            case 51:
            case 52:
            case 53:
            case 59:
            case 63:
            case 65:
            case 66:
            case 67:
            case 69:
            case 71:
            case 72:
                this.tvStatue.setText(makeProposalsBean.selectValue == 0 ? StringTronUtil.getResString(R.string.not_open) : StringTronUtil.getResString(R.string.open));
                this.tvSlect0.setText(StringTronUtil.getResString(R.string.not_open));
                this.tvSlect1.setText(StringTronUtil.getResString(R.string.open));
                break;
            case 35:
                this.tvStatue.setText(makeProposalsBean.selectValue == 0 ? StringTronUtil.getResString(R.string.propose_not_ban) : StringTronUtil.getResString(R.string.propose_ban));
                this.tvSlect0.setText(StringTronUtil.getResString(R.string.propose_not_ban));
                this.tvSlect1.setText(StringTronUtil.getResString(R.string.propose_ban));
                break;
        }
        changeRow(false);
        if (makeProposalsBean.value != makeProposalsBean.selectValue) {
            this.tvReset.setVisibility(View.VISIBLE);
        } else {
            this.tvReset.setVisibility(View.GONE);
        }
    }

    public void changeRow(boolean z) {
        if (!z) {
            this.ivRow.setImageResource(R.mipmap.ic_open);
        } else {
            this.ivRow.setImageResource(R.mipmap.ic_close_row);
        }
    }

    private void mappingId(View view) {
        this.iv = (ImageView) view.findViewById(R.id.iv);
        this.tvTop = (TextView) view.findViewById(R.id.tv_top);
        this.tvTopContent = (TextView) view.findViewById(R.id.tv_top_content);
        this.tvReset = (TextView) view.findViewById(R.id.tv_reset);
        this.rlTop = (LinearLayout) view.findViewById(R.id.rl_top);
        this.tvStatue = (TextView) view.findViewById(R.id.tv_statue);
        this.ivRow = (ImageView) view.findViewById(R.id.iv_row);
        this.rlSelect = (RelativeLayout) view.findViewById(R.id.rl_select);
        this.tvSlect0 = (TextView) view.findViewById(R.id.tv_slect_0);
        this.tvSlect1 = (TextView) view.findViewById(R.id.tv_slect_1);
        this.llSelect = (LinearLayout) view.findViewById(R.id.ll_select);
        this.llProposalsContent = (LinearLayout) view.findViewById(R.id.ll_proposals_content);
        this.tvLine = (TextView) view.findViewById(R.id.tv_line);
    }
}
