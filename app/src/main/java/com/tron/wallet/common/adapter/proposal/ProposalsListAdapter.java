package com.tron.wallet.common.adapter.proposal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabmy.proposals.proposaldetail.ProposalsDetailActivity;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.adapter.proposal.ProposalsAdapter;
import com.tron.wallet.common.components.countdownview.CountdownView;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.ProposalUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.tron.protos.Protocol;
public class ProposalsListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ProposalsAdapter.ProposalClickListener clickListener;
    private final SparseArray<HashMap<Long, String>> contentMap;
    private DecimalFormat decimalFormat;
    private List<WitnessOutput.DataBean> mCommitteeList;
    private Context mContext;
    private String mCurrentAddress;
    private String mEditInput;
    private LinearLayoutManager manager;
    private List<Long> mapKeyList;
    private List<Long> mapValueList;
    private HashMap<String, WitnessOutput.DataBean> nameMap;
    private final NumberFormat numberFormat;
    private List<Protocol.Proposal> proposalList;

    public void setNameMap(HashMap<String, WitnessOutput.DataBean> hashMap, List<WitnessOutput.DataBean> list) {
        this.nameMap = hashMap;
        this.mCommitteeList = list;
    }

    public ProposalsListAdapter(Context context, List<Protocol.Proposal> list, String str, ProposalsAdapter.ProposalClickListener proposalClickListener, String str2) {
        SparseArray<HashMap<Long, String>> sparseArray = new SparseArray<>();
        this.contentMap = sparseArray;
        this.mContext = context;
        this.proposalList = list;
        this.mCurrentAddress = str;
        this.clickListener = proposalClickListener;
        this.mEditInput = str2;
        this.manager = new LinearLayoutManager(context, 1, false);
        this.decimalFormat = new DecimalFormat("#.######");
        this.mapKeyList = new ArrayList();
        this.mapKeyList = new ArrayList();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(0);
        sparseArray.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_proposals, viewGroup, false));
    }

    private void setContentProposalTime(ViewHolder viewHolder, long j) {
        try {
            viewHolder.tvCreateTime.setText(DateUtils.longToString(j, "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final boolean z;
        int i2;
        if (viewHolder != null) {
            final Protocol.Proposal proposal = this.proposalList.get(i);
            if (proposal != null) {
                TextView textView = viewHolder.tvProposalsId;
                textView.setText("#" + proposal.getProposalId() + "");
                setContentProposalTime(viewHolder, proposal.getExpirationTime());
                try {
                    if (proposal.getState().name().equals("APPROVED")) {
                        viewHolder.tvProposalsState.setText(R.string.effective);
                        viewHolder.tvProposalsState.setTextColor(this.mContext.getResources().getColor(R.color.green_23));
                        viewHolder.tvProposalsState.setBackgroundResource(R.drawable.roundborder_239e6c_10_15);
                        viewHolder.cvCountdownView.setVisibility(View.GONE);
                        viewHolder.tvCreateTime.setVisibility(View.VISIBLE);
                        viewHolder.titleValidVotes.setVisibility(View.GONE);
                        viewHolder.numValidVotes.setVisibility(View.GONE);
                        viewHolder.ivAgree.setVisibility(View.GONE);
                    } else if (proposal.getState().name().equals("DISAPPROVED")) {
                        viewHolder.tvProposalsState.setText(R.string.ineffective);
                        viewHolder.tvProposalsState.setTextColor(this.mContext.getResources().getColor(R.color.gray_66));
                        viewHolder.tvProposalsState.setBackgroundResource(R.drawable.roundborder_666666_10_15);
                        viewHolder.cvCountdownView.setVisibility(View.GONE);
                        viewHolder.tvCreateTime.setVisibility(View.VISIBLE);
                        viewHolder.titleValidVotes.setVisibility(View.GONE);
                        viewHolder.numValidVotes.setVisibility(View.GONE);
                        viewHolder.ivAgree.setVisibility(View.GONE);
                    } else if (proposal.getState().name().equals("CANCELED")) {
                        viewHolder.tvProposalsState.setText(R.string.canceled);
                        viewHolder.tvProposalsState.setTextColor(this.mContext.getResources().getColor(R.color.gray_99));
                        viewHolder.tvProposalsState.setBackgroundResource(R.drawable.roundborder_999999_8_15);
                        viewHolder.cvCountdownView.setVisibility(View.GONE);
                        viewHolder.tvCreateTime.setVisibility(View.VISIBLE);
                        viewHolder.titleValidVotes.setVisibility(View.GONE);
                        viewHolder.numValidVotes.setVisibility(View.GONE);
                        viewHolder.ivAgree.setVisibility(View.GONE);
                    } else {
                        viewHolder.tvProposalsState.setText(R.string.in_voting);
                        viewHolder.tvProposalsState.setTextColor(this.mContext.getResources().getColor(R.color.blue_13));
                        viewHolder.tvProposalsState.setBackgroundResource(R.drawable.roundborder_135dcd_10_15);
                        viewHolder.cvCountdownView.setVisibility(View.VISIBLE);
                        viewHolder.tvCreateTime.setVisibility(View.GONE);
                        viewHolder.ivAgree.setVisibility(View.VISIBLE);
                        viewHolder.titleValidVotes.setVisibility(View.VISIBLE);
                        viewHolder.numValidVotes.setVisibility(View.VISIBLE);
                    }
                } catch (Resources.NotFoundException e) {
                    LogUtils.e(e);
                }
                String encode58Check = StringTronUtil.encode58Check(proposal.getProposerAddress().toByteArray());
                if (!StringTronUtil.isEmpty(encode58Check)) {
                    HashMap<String, WitnessOutput.DataBean> hashMap = this.nameMap;
                    if (hashMap != null && hashMap.size() > 0) {
                        final WitnessOutput.DataBean dataBean = this.nameMap.get(encode58Check);
                        if (dataBean != null) {
                            if (!StringTronUtil.isEmpty(dataBean.getName())) {
                                if (!StringTronUtil.isEmpty(this.mEditInput) && dataBean.getName().toLowerCase().contains(this.mEditInput)) {
                                    int indexOf = dataBean.getName().toLowerCase().indexOf(this.mEditInput.toLowerCase());
                                    int length = this.mEditInput.length();
                                    SpannableString spannableString = new SpannableString(dataBean.getName());
                                    spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#EEB023")), indexOf, length + indexOf, 18);
                                    viewHolder.tvProposalsName.setText(spannableString);
                                } else {
                                    viewHolder.tvProposalsName.setText(dataBean.getName());
                                }
                                viewHolder.tvProposalsName.setOnClickListener(new NoDoubleClickListener() {
                                    @Override
                                    protected void onNoDoubleClick(View view) {
                                        if (TextUtils.isEmpty(dataBean.getUrl())) {
                                            return;
                                        }
                                        CommonWebActivityV3.start(mContext, dataBean.getUrl(), TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName(), -2, false);
                                    }
                                });
                            } else if (!StringTronUtil.isEmpty(dataBean.getUrl())) {
                                if (!StringTronUtil.isEmpty(this.mEditInput) && dataBean.getUrl().toLowerCase().contains(this.mEditInput)) {
                                    int indexOf2 = dataBean.getUrl().toLowerCase().indexOf(this.mEditInput.toLowerCase());
                                    int length2 = this.mEditInput.length();
                                    SpannableString spannableString2 = new SpannableString(dataBean.getUrl());
                                    spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#EEB023")), indexOf2, length2 + indexOf2, 18);
                                    viewHolder.tvProposalsName.setText(spannableString2);
                                } else {
                                    viewHolder.tvProposalsName.setText(dataBean.getUrl());
                                }
                                viewHolder.tvProposalsName.setOnClickListener(new NoDoubleClickListener() {
                                    @Override
                                    protected void onNoDoubleClick(View view) {
                                        CommonWebActivityV3.start(mContext, dataBean.getUrl(), TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName(), -2, false);
                                    }
                                });
                            } else if (!StringTronUtil.isEmpty(dataBean.getAddress())) {
                                if (!StringTronUtil.isEmpty(this.mEditInput) && dataBean.getAddress().toLowerCase().contains(this.mEditInput)) {
                                    int indexOf3 = dataBean.getAddress().toLowerCase().indexOf(this.mEditInput.toLowerCase());
                                    int length3 = this.mEditInput.length();
                                    SpannableString spannableString3 = new SpannableString(dataBean.getAddress());
                                    spannableString3.setSpan(new ForegroundColorSpan(Color.parseColor("#EEB023")), indexOf3, length3 + indexOf3, 18);
                                    viewHolder.tvProposalsName.setText(spannableString3);
                                } else {
                                    viewHolder.tvProposalsName.setText(dataBean.getAddress());
                                }
                            }
                        }
                    } else if (!StringTronUtil.isEmpty(encode58Check)) {
                        if (!StringTronUtil.isEmpty(this.mEditInput) && encode58Check.toLowerCase().contains(this.mEditInput)) {
                            int indexOf4 = encode58Check.toLowerCase().indexOf(this.mEditInput.toLowerCase());
                            int length4 = this.mEditInput.length();
                            SpannableString spannableString4 = new SpannableString(encode58Check);
                            spannableString4.setSpan(new ForegroundColorSpan(Color.parseColor("#EEB023")), indexOf4, length4 + indexOf4, 18);
                            viewHolder.tvProposalsName.setText(spannableString4);
                        } else {
                            viewHolder.tvProposalsName.setText(encode58Check);
                        }
                    }
                }
                List<ByteString> approvalsList = proposal.getApprovalsList();
                ArrayList arrayList = new ArrayList();
                if (approvalsList != null && approvalsList.size() > 0) {
                    int size = approvalsList.size();
                    TextView textView2 = viewHolder.numAllVotes;
                    textView2.setText(approvalsList.size() + "");
                    int i3 = 0;
                    z = false;
                    while (i3 < size) {
                        String encode58Check2 = StringTronUtil.encode58Check(approvalsList.get(i3).toByteArray());
                        if (!StringTronUtil.isEmpty(encode58Check2) && !StringTronUtil.isEmpty(this.mCurrentAddress) && encode58Check2.equals(this.mCurrentAddress)) {
                            z = true;
                        }
                        List<WitnessOutput.DataBean> list = this.mCommitteeList;
                        int size2 = list == null ? 0 : list.size();
                        int i4 = 0;
                        while (true) {
                            i2 = size;
                            if (i4 >= size2) {
                                break;
                            }
                            if (i4 < 27) {
                                WitnessOutput.DataBean dataBean2 = this.mCommitteeList.get(i4);
                                if (!StringTronUtil.isEmpty(encode58Check2) && dataBean2 != null && !StringTronUtil.isEmpty(dataBean2.getAddress()) && encode58Check2.equals(dataBean2.getAddress())) {
                                    arrayList.add(approvalsList.get(i3));
                                    break;
                                }
                            }
                            i4++;
                            size = i2;
                        }
                        i3++;
                        size = i2;
                    }
                    TextView textView3 = viewHolder.numValidVotes;
                    textView3.setText(arrayList.size() + "");
                } else {
                    viewHolder.numAllVotes.setText("0");
                    viewHolder.numValidVotes.setText("0");
                    z = false;
                }
                if (proposal.getState().name().equals("APPROVED") || proposal.getState().name().equals("CANCELED") || proposal.getState().name().equals("DISAPPROVED")) {
                    if (z) {
                        viewHolder.ivAgree.setImageResource(R.mipmap.proposals_ineffective_agree);
                    } else {
                        viewHolder.ivAgree.setImageResource(R.mipmap.proposals_ineffective_unagree);
                    }
                } else if (z) {
                    viewHolder.ivAgree.setImageResource(R.mipmap.proposals_voting_agree);
                } else {
                    viewHolder.ivAgree.setImageResource(R.mipmap.proposal_voting_unagree);
                }
                Map<Long, Long> parametersMap = proposal.getParametersMap();
                if (parametersMap != null && parametersMap.size() > 0) {
                    if (parametersMap.containsKey(28L) || parametersMap.containsKey(8L)) {
                        viewHolder.setVisibility(false);
                        return;
                    }
                    viewHolder.setVisibility(true);
                    String initProposalsContent = initProposalsContent(viewHolder.getAbsoluteAdapterPosition(), parametersMap);
                    if (!StringTronUtil.isEmpty(initProposalsContent)) {
                        viewHolder.tvCommitteeContent.setText(initProposalsContent);
                        try {
                            if (viewHolder.tvCommitteeContent.getLineCount() > 3) {
                                int lineEnd = viewHolder.tvCommitteeContent.getLayout().getLineEnd(3);
                                viewHolder.tvCommitteeContent.setText(((Object) viewHolder.tvCommitteeContent.getText().subSequence(0, lineEnd - 3)) + "...");
                            }
                        } catch (Exception e2) {
                            LogUtils.e(e2);
                            viewHolder.tvCommitteeContent.setText(initProposalsContent);
                        }
                        viewHolder.tvCommitteeContent.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder.tvCommitteeContent.setVisibility(View.GONE);
                    }
                }
                viewHolder.rlAgree.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        if (proposal.getState().name().equals("APPROVED") || proposal.getState().name().equals("CANCELED") || proposal.getState().name().equals("DISAPPROVED") || proposal.getExpirationTime() < System.currentTimeMillis() || clickListener == null) {
                            return;
                        }
                        clickListener.onAgreeClick(proposal, z, getContentMap(viewHolder.getAbsoluteAdapterPosition()));
                    }
                });
                viewHolder.itemView.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        ProposalsDetailActivity.start(mContext, proposal, z, mCurrentAddress, mCommitteeList);
                    }
                });
                viewHolder.cvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                    @Override
                    public void onEnd(CountdownView countdownView) {
                        try {
                            if (clickListener != null) {
                                clickListener.onTimeEnd();
                            }
                        } catch (Exception e3) {
                            LogUtils.e(e3);
                        }
                    }
                });
            }
            if (this.proposalList.size() == 1) {
                viewHolder.ivTop.setBackgroundResource(R.mipmap.shadow_proposal_top);
                viewHolder.ivBottom.setBackgroundResource(R.mipmap.shadow_proposal_bottom);
            } else if (i != 0 && i == this.proposalList.size() - 1) {
                viewHolder.ivTop.setBackgroundResource(R.mipmap.shadow_proposal_middle_top);
                viewHolder.ivBottom.setBackgroundResource(R.mipmap.shadow_proposal_bottom);
            } else if (i == 0) {
                viewHolder.ivTop.setBackgroundResource(R.mipmap.shadow_proposal_top);
                viewHolder.ivBottom.setBackgroundResource(R.mipmap.shadow_proposal_middle_bottom);
            } else {
                viewHolder.ivTop.setBackgroundResource(R.mipmap.shadow_proposal_middle_top);
                viewHolder.ivBottom.setBackgroundResource(R.mipmap.shadow_proposal_middle_bottom);
            }
        }
    }

    public void notifyData(List<Protocol.Proposal> list, String str, String str2) {
        this.proposalList = list;
        this.mCurrentAddress = str;
        this.mEditInput = str2;
        this.contentMap.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow((ProposalsListAdapter) viewHolder);
        Protocol.Proposal proposal = this.proposalList.get(viewHolder.getAdapterPosition());
        if (proposal != null) {
            if (proposal.getState().name().equals("APPROVED") || proposal.getState().name().equals("CANCELED") || proposal.getState().name().equals("DISAPPROVED")) {
                viewHolder.cvCountdownView.setVisibility(View.GONE);
                viewHolder.tvCreateTime.setVisibility(View.VISIBLE);
                setContentProposalTime(viewHolder, proposal.getExpirationTime());
                return;
            }
            long expirationTime = proposal.getExpirationTime() - System.currentTimeMillis();
            if (viewHolder != null) {
                viewHolder.refreshTime(expirationTime, proposal.getExpirationTime());
            }
        }
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow((ProposalsListAdapter) viewHolder);
        viewHolder.getCvCountdownView().stop();
    }

    @Override
    public int getItemCount() {
        List<Protocol.Proposal> list = this.proposalList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends BaseHolder {
        CountdownView cvCountdownView;
        ImageView ivAgree;
        ImageView ivBottom;
        ImageView ivLine;
        ImageView ivTop;
        LinearLayout llCommittee;
        TextView numAllVotes;
        TextView numValidVotes;
        RelativeLayout rlAgree;
        RelativeLayout rlTime;
        TextView titleAllVotes;
        TextView titleValidVotes;
        TextView tvCommitteeContent;
        TextView tvCreateTime;
        TextView tvProposalsId;
        TextView tvProposalsName;
        TextView tvProposalsState;

        public CountdownView getCvCountdownView() {
            return this.cvCountdownView;
        }

        private void mappingId(View view) {
            this.tvProposalsId = (TextView) view.findViewById(R.id.tv_proposals_id);
            this.tvProposalsState = (TextView) view.findViewById(R.id.tv_proposals_state);
            this.llCommittee = (LinearLayout) view.findViewById(R.id.ll_committee);
            this.cvCountdownView = (CountdownView) view.findViewById(R.id.cv_countdownView);
            this.tvCreateTime = (TextView) view.findViewById(R.id.tv_create_time);
            this.rlTime = (RelativeLayout) view.findViewById(R.id.rl_time);
            this.ivLine = (ImageView) view.findViewById(R.id.iv_line);
            this.titleAllVotes = (TextView) view.findViewById(R.id.title_all_votes);
            this.numAllVotes = (TextView) view.findViewById(R.id.num_all_votes);
            this.titleValidVotes = (TextView) view.findViewById(R.id.title_valid_votes);
            this.numValidVotes = (TextView) view.findViewById(R.id.num_valid_votes);
            this.rlAgree = (RelativeLayout) view.findViewById(R.id.rl_agree);
            this.ivAgree = (ImageView) view.findViewById(R.id.iv_agree);
            this.tvProposalsName = (TextView) view.findViewById(R.id.tv_proposals_name);
            this.tvCommitteeContent = (TextView) view.findViewById(R.id.tv_committee_content);
            this.ivTop = (ImageView) view.findViewById(R.id.iv_top);
            this.ivBottom = (ImageView) view.findViewById(R.id.iv_bottom);
        }

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void setVisibility(boolean z) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
            if (z) {
                layoutParams.height = -2;
                layoutParams.width = -1;
                this.itemView.setVisibility(View.VISIBLE);
            } else {
                this.itemView.setVisibility(View.GONE);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
            this.itemView.setLayoutParams(layoutParams);
        }

        public void refreshTime(long j, long j2) {
            try {
                if (j > 0) {
                    this.cvCountdownView.setVisibility(View.VISIBLE);
                    this.tvCreateTime.setVisibility(View.GONE);
                    this.cvCountdownView.start(j);
                } else {
                    this.cvCountdownView.setVisibility(View.GONE);
                    this.tvCreateTime.setVisibility(View.VISIBLE);
                    try {
                        this.tvCreateTime.setText(DateUtils.longToString(j2, "yyyy-MM-dd HH:mm:ss"));
                    } catch (ParseException e) {
                        LogUtils.e(e);
                    }
                }
            } catch (Exception e2) {
                SentryUtil.captureException(e2);
                LogUtils.e(e2);
            }
        }
    }

    private String initProposalsContent(int i, Map<Long, Long> map) {
        String str = "";
        try {
            this.mapKeyList = new ArrayList(map.keySet());
            this.mapValueList = new ArrayList(map.values());
            if (map != null && map.size() > 0) {
                HashMap<Long, String> hashMap = new HashMap<>();
                int i2 = 0;
                while (i2 < this.mapKeyList.size()) {
                    long longValue = this.mapKeyList.get(i2).longValue();
                    String proposalContent = ProposalUtils.getProposalContent(longValue, this.mapValueList.size() > i2 ? this.mapValueList.get(i2).longValue() : 0L);
                    if (i2 < 3) {
                        if (!StringTronUtil.isEmpty(proposalContent)) {
                            str = str + proposalContent;
                        }
                        if (i2 < this.mapKeyList.size() - 1) {
                            str = str + StringUtils.LF;
                        }
                    }
                    hashMap.put(Long.valueOf(longValue), proposalContent);
                    this.contentMap.put(i, hashMap);
                    i2++;
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return str;
    }

    public HashMap<Long, String> getContentMap(int i) {
        return this.contentMap.get(i);
    }
}
