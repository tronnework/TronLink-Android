package com.tron.wallet.business.vote.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
public class VoteItemListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private String mSearchSrc;
    private boolean mShowVoteEditText;
    private HashMap<String, String> mVotes;
    private List<WitnessOutput.DataBean> mWitnessesFilteredList;
    private List<WitnessOutput.DataBean> mWitnessesList;
    private boolean showFiltered;
    private boolean isSearchFrom = false;
    private NumberFormat numberFormat = NumberFormat.getIntegerInstance();

    public HashMap<String, String> getmVotes() {
        return this.mVotes;
    }

    public boolean isShowFiltered() {
        return this.showFiltered;
    }

    public void setSearchFrom(boolean z) {
        this.isSearchFrom = z;
    }

    public void setmVotes(HashMap<String, String> hashMap) {
        this.mVotes = hashMap;
    }

    public void updateSearchSrc(String str) {
        this.mSearchSrc = str;
    }

    public VoteItemListAdapter(Context context, List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2, boolean z, boolean z2, HashMap<String, String> hashMap, String str) {
        this.mContext = context;
        this.mVotes = hashMap;
        this.mWitnessesList = list;
        this.showFiltered = z2;
        this.mWitnessesFilteredList = list2;
        this.mShowVoteEditText = z;
        this.mSearchSrc = str;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_candidate, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        boolean z;
        if (this.showFiltered) {
            z = this.mWitnessesFilteredList.size() == 1;
            viewHolder.bind(this.mWitnessesFilteredList.get(i), i, this.showFiltered);
            if (i == this.mWitnessesFilteredList.size() - 1) {
                viewHolder.itemView.setPadding(0, 0, 0, UIUtils.dip2px(50.0f));
            } else {
                viewHolder.itemView.setPadding(0, 0, 0, 0);
            }
        } else {
            z = this.mWitnessesList.size() == 1;
            viewHolder.bind(this.mWitnessesList.get(i), i, this.showFiltered);
            if (i == this.mWitnessesList.size() - 1) {
                viewHolder.itemView.setPadding(0, 0, 0, UIUtils.dip2px(50.0f));
            } else {
                viewHolder.itemView.setPadding(0, 0, 0, 0);
            }
        }
        if (z) {
            viewHolder.root.setBackgroundResource(R.drawable.ripple_shadow0);
            viewHolder.root.setPadding(0, DensityUtils.dp2px(this.mContext, 25.0f), 0, DensityUtils.dp2px(this.mContext, 25.0f));
        } else if (i == 0) {
            viewHolder.root.setBackgroundResource(R.drawable.ripple_shadow1);
            viewHolder.root.setPadding(0, DensityUtils.dp2px(this.mContext, 22.0f), 0, DensityUtils.dp2px(this.mContext, 5.0f));
        } else if (this.showFiltered) {
            if (i == this.mWitnessesFilteredList.size() - 1) {
                viewHolder.root.setBackgroundResource(R.drawable.ripple_shadow3);
                viewHolder.root.setPadding(0, DensityUtils.dp2px(this.mContext, 10.0f), 0, DensityUtils.dp2px(this.mContext, 25.0f));
                return;
            }
            viewHolder.root.setBackgroundResource(R.drawable.ripple_shadow2);
            viewHolder.root.setPadding(0, DensityUtils.dp2px(this.mContext, 0.0f), 0, DensityUtils.dp2px(this.mContext, 0.0f));
        } else if (i == this.mWitnessesList.size() - 1) {
            viewHolder.root.setBackgroundResource(R.drawable.ripple_shadow3);
            viewHolder.root.setPadding(0, DensityUtils.dp2px(this.mContext, 0.0f), 0, DensityUtils.dp2px(this.mContext, 25.0f));
        } else {
            viewHolder.root.setBackgroundResource(R.drawable.ripple_shadow2);
            viewHolder.root.setPadding(0, DensityUtils.dp2px(this.mContext, 0.0f), 0, DensityUtils.dp2px(this.mContext, 0.0f));
        }
    }

    @Override
    public int getItemCount() {
        if (this.showFiltered) {
            List<WitnessOutput.DataBean> list = this.mWitnessesFilteredList;
            if (list != null) {
                return list.size();
            }
            return 0;
        }
        List<WitnessOutput.DataBean> list2 = this.mWitnessesList;
        if (list2 != null) {
            return list2.size();
        }
        return 0;
    }

    public class ViewHolder extends BaseHolder {
        TextView address;
        EditText etInput;
        TextView id;
        private TextWatcher mTextWatcher;
        private WitnessOutput.DataBean mWitness;
        TextView rewardRatio;
        RelativeLayout rlInput;
        LinearLayout root;
        TextView tvPersonName;
        TextView voteCount;

        private void mappingId(View view) {
            this.id = (TextView) view.findViewById(R.id.id);
            this.tvPersonName = (TextView) view.findViewById(R.id.tv_person_name);
            this.address = (TextView) view.findViewById(R.id.address);
            this.voteCount = (TextView) view.findViewById(R.id.vote_count);
            this.etInput = (EditText) view.findViewById(R.id.et_input);
            this.root = (LinearLayout) view.findViewById(R.id.root);
            this.rlInput = (RelativeLayout) view.findViewById(R.id.rl_input);
            this.rewardRatio = (TextView) view.findViewById(R.id.reward_ratio);
        }

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void afterTextChanged(android.text.Editable r6) {
                    


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.vote.adapter.VoteItemListAdapter.ViewHolder.1.afterTextChanged(android.text.Editable):void");
                }
            };
            this.mTextWatcher = textWatcher;
            this.etInput.addTextChangedListener(textWatcher);
        }

        public void bind(final WitnessOutput.DataBean dataBean, int i, boolean z) {
            this.mWitness = dataBean;
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            if (dataBean != null) {
                this.id.setText(numberFormat.format(dataBean.getId() + 1));
                if (z) {
                    try {
                        if (!TextUtils.isEmpty(dataBean.getName()) && dataBean.getName().toLowerCase().contains(mSearchSrc.toLowerCase())) {
                            int indexOf = dataBean.getName().toLowerCase().indexOf(mSearchSrc.toLowerCase());
                            int length = mSearchSrc.length();
                            SpannableString spannableString = new SpannableString(dataBean.getName());
                            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#EEB023")), indexOf, length + indexOf, 18);
                            this.tvPersonName.setText(spannableString);
                        } else if (TextUtils.isEmpty(dataBean.getName()) && !TextUtils.isEmpty(dataBean.getUrl()) && dataBean.getUrl().toLowerCase().contains(mSearchSrc.toLowerCase())) {
                            int indexOf2 = dataBean.getUrl().toLowerCase().indexOf(mSearchSrc.toLowerCase());
                            int length2 = mSearchSrc.length();
                            SpannableString spannableString2 = new SpannableString(dataBean.getUrl());
                            spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#EEB023")), indexOf2, length2 + indexOf2, 18);
                            this.tvPersonName.setText(spannableString2);
                        } else {
                            this.tvPersonName.setText(TextUtils.isEmpty(dataBean.getName()) ? dataBean.getUrl() : dataBean.getName());
                        }
                    } catch (Exception e) {
                        LogUtils.e(e);
                        this.tvPersonName.setText(TextUtils.isEmpty(dataBean.getName()) ? dataBean.getUrl() : dataBean.getName());
                    }
                } else {
                    this.tvPersonName.setText(TextUtils.isEmpty(dataBean.getName()) ? dataBean.getUrl() : dataBean.getName());
                }
                if (z) {
                    try {
                        if (!TextUtils.isEmpty(dataBean.getAddress()) && dataBean.getAddress().toLowerCase().contains(mSearchSrc.toLowerCase())) {
                            int indexOf3 = dataBean.getAddress().toLowerCase().indexOf(mSearchSrc.toLowerCase());
                            int length3 = mSearchSrc.length();
                            SpannableString spannableString3 = new SpannableString(dataBean.getAddress());
                            spannableString3.setSpan(new ForegroundColorSpan(Color.parseColor("#EEB023")), indexOf3, length3 + indexOf3, 18);
                            this.address.setText(spannableString3);
                        } else {
                            this.address.setText(dataBean.getAddress());
                        }
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                        this.address.setText(dataBean.getAddress());
                    }
                } else {
                    this.address.setText(dataBean.getAddress());
                }
                this.voteCount.setText(numberFormat.format(dataBean.getLastCycleVotes()));
                this.address.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        UIUtils.copy(dataBean.getAddress());
                        IToast.getIToast().show(R.string.already_copy);
                    }
                });
                try {
                    TextView textView = this.rewardRatio;
                    textView.setText((100 - dataBean.getBrokerage()) + "%");
                } catch (Exception e3) {
                    this.rewardRatio.setText("0%");
                    LogUtils.e(e3);
                }
            }
            if (mVotes != null) {
                this.etInput.removeTextChangedListener(this.mTextWatcher);
                this.etInput.setText("");
                String str = (String) mVotes.get(this.address.getText().toString());
                if (!StringTronUtil.isEmpty(str) && !"0".equals(str)) {
                    this.etInput.setText(str);
                    this.rlInput.setBackgroundColor(mContext.getResources().getColor(R.color.blue_13_40));
                } else {
                    this.rlInput.setBackgroundColor(mContext.getResources().getColor(R.color.blue_13_8));
                }
                this.etInput.addTextChangedListener(this.mTextWatcher);
            }
            this.tvPersonName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WitnessOutput.DataBean dataBean2 = dataBean;
                    if (dataBean2 == null || TextUtils.isEmpty(dataBean2.getUrl())) {
                        return;
                    }
                    CommonWebActivityV3.start(mContext, dataBean.getUrl(), TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName(), -2, false);
                }
            });
        }
    }

    public void setShowFiltered(boolean z) {
        this.showFiltered = z;
        notifyDataSetChanged();
    }

    public void notifyData(List<WitnessOutput.DataBean> list) {
        this.mWitnessesFilteredList = list;
        notifyDataSetChanged();
    }

    public void notifyData(HashMap<String, String> hashMap) {
        this.mVotes = hashMap;
        notifyDataSetChanged();
    }

    public void notifyData(List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2) {
        this.mWitnessesList = list;
        notifyData(list2);
    }

    public void notifyData(List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2, boolean z) {
        this.showFiltered = z;
        this.mWitnessesList = list;
        notifyData(list2);
    }
}
