package com.tron.wallet.business.vote.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.vote.adapter.BatchVoteDialogeAdapter;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.business.vote.util.VoteCountChangeListenerV2;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.interfaces.VoteCountChangeListener;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
public class BatchVoteDialogeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private long balance;
    private final Handler handler;
    private final Dialog hostDialog;
    private final Context mContext;
    private String mFilter;
    private List<VoteCountChangeListener> listeners = new ArrayList();
    private HashMap<String, String> mVotes = new HashMap<>();
    private List<WitnessOutput.DataBean> mWitnessesList = new ArrayList();

    public void addListener(VoteCountChangeListener voteCountChangeListener) {
        this.listeners.add(voteCountChangeListener);
    }

    public BatchVoteDialogeAdapter(Context context, Dialog dialog, VoteCountChangeListener voteCountChangeListener) {
        this.mContext = context;
        this.hostDialog = dialog;
        addListener(voteCountChangeListener);
        this.handler = new Handler();
    }

    public long checkInputNumber(CharSequence charSequence) {
        try {
            if (charSequence.length() > 0) {
                return Long.parseLong(charSequence.toString());
            }
            return 0L;
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    public void setData(HashMap<String, String> hashMap, List<WitnessOutput.DataBean> list, String str, long j) {
        this.balance = j;
        this.mVotes.clear();
        this.mWitnessesList.clear();
        this.mVotes.putAll(hashMap);
        this.mWitnessesList.addAll(list);
        this.mFilter = str;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_candidate_dialog, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(this.mWitnessesList.get(i), i, StringTronUtil.isEmpty(this.mFilter));
    }

    @Override
    public int getItemCount() {
        HashMap<String, String> hashMap = this.mVotes;
        if (hashMap != null) {
            return hashMap.size();
        }
        return 0;
    }

    public void notifyClearAll() {
        if (this.mWitnessesList.size() > 0) {
            Iterator<WitnessOutput.DataBean> it = this.mWitnessesList.iterator();
            int i = 0;
            while (it.hasNext()) {
                WitnessOutput.DataBean next = it.next();
                String str = this.mVotes.get(next.getAddress());
                if (!TextUtils.isEmpty(str)) {
                    notifyValueChanged(next.getAddress(), 0L, -checkInputNumber(str));
                    this.mVotes.remove(next.getAddress());
                    it.remove();
                    notifyItemRemoved(i);
                }
                i++;
            }
        }
    }

    public void notifyValueChanged(String str, long j, long j2) {
        for (VoteCountChangeListener voteCountChangeListener : this.listeners) {
            if (voteCountChangeListener instanceof VoteCountChangeListenerV2) {
                ((VoteCountChangeListenerV2) voteCountChangeListener).onVoteCountChanged(str, j, j2, true);
            } else {
                voteCountChangeListener.onVoteCountChanged(str, j);
            }
        }
    }

    public class ViewHolder extends BaseHolder {
        EditText etInput;
        ImageView icDeleteCandidate;
        private TextWatcher mTextWatcher;
        private WitnessOutput.DataBean mWitness;
        TextView tvPersonName;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
            final long[] jArr = new long[1];
            BaseTextWatcher baseTextWatcher = new BaseTextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    super.beforeTextChanged(charSequence, i, i2, i3);
                    jArr[0] = checkInputNumber(charSequence);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    long checkInputNumber = checkInputNumber(editable.toString());
                    if (checkInputNumber > balance) {
                        ViewHolder.this.etInput.removeTextChangedListener(this);
                        checkInputNumber = balance;
                        ViewHolder.this.etInput.setText(String.valueOf(balance));
                        ViewHolder.this.etInput.setSelection(String.valueOf(balance).length());
                        ViewHolder.this.etInput.addTextChangedListener(this);
                    }
                    long j = checkInputNumber - jArr[0];
                    String address = ViewHolder.this.mWitness != null ? ViewHolder.this.mWitness.getAddress() : null;
                    if (address != null && mVotes != null && checkInputNumber > 0) {
                        mVotes.put(address, String.valueOf(checkInputNumber));
                    }
                    notifyValueChanged(address, checkInputNumber, j);
                    jArr[0] = checkInputNumber;
                }
            };
            this.mTextWatcher = baseTextWatcher;
            this.etInput.addTextChangedListener(baseTextWatcher);
            this.etInput.setLongClickable(false);
            this.etInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public final void onFocusChange(View view2, boolean z) {
                    BatchVoteDialogeAdapter.ViewHolder.this.lambda$new$1(view2, z);
                }
            });
        }

        public void lambda$new$1(View view, boolean z) {
            if (z) {
                return;
            }
            Editable text = this.etInput.getText();
            if (TextUtils.isEmpty(text) || checkInputNumber(text) == 0) {
                handler.post(new Runnable() {
                    @Override
                    public final void run() {
                        BatchVoteDialogeAdapter.ViewHolder.this.lambda$new$0();
                    }
                });
            }
        }

        public void lambda$new$0() {
            int adapterPosition = getAdapterPosition();
            if (adapterPosition < 0) {
                return;
            }
            mVotes.remove(this.mWitness.getAddress());
            BatchVoteDialogeAdapter batchVoteDialogeAdapter = BatchVoteDialogeAdapter.this;
            batchVoteDialogeAdapter.notifyValueChanged(((WitnessOutput.DataBean) batchVoteDialogeAdapter.mWitnessesList.get(adapterPosition)).getAddress(), 0L, 0L);
            mWitnessesList.remove(this.mWitness);
            if (hostDialog == null || !hostDialog.isShowing()) {
                return;
            }
            notifyItemRemoved(adapterPosition);
        }

        public void bind(final WitnessOutput.DataBean dataBean, int i, boolean z) {
            this.mWitness = dataBean;
            NumberFormat.getInstance(Locale.US);
            String name = dataBean.getName();
            if (TextUtils.isEmpty(name)) {
                name = dataBean.getUrl();
            }
            if (TextUtils.isEmpty(name)) {
                name = dataBean.getAddress();
            }
            if (dataBean != null) {
                if (z) {
                    try {
                        if (!TextUtils.isEmpty(dataBean.getName()) && dataBean.getName().toLowerCase().contains(mFilter.toLowerCase())) {
                            int indexOf = dataBean.getName().toLowerCase().indexOf(mFilter.toLowerCase());
                            int length = mFilter.length();
                            SpannableString spannableString = new SpannableString(dataBean.getName());
                            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#EEB023")), indexOf, length + indexOf, 18);
                            this.tvPersonName.setText(spannableString);
                        } else if (TextUtils.isEmpty(dataBean.getName()) && !TextUtils.isEmpty(dataBean.getUrl()) && dataBean.getUrl().toLowerCase().contains(mFilter.toLowerCase())) {
                            int indexOf2 = dataBean.getUrl().toLowerCase().indexOf(mFilter.toLowerCase());
                            int length2 = mFilter.length();
                            SpannableString spannableString2 = new SpannableString(dataBean.getUrl());
                            spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#EEB023")), indexOf2, length2 + indexOf2, 18);
                            this.tvPersonName.setText(spannableString2);
                        } else {
                            this.tvPersonName.setText(name);
                        }
                    } catch (Exception e) {
                        LogUtils.e(e);
                        this.tvPersonName.setText(name);
                    }
                } else {
                    this.tvPersonName.setText(name);
                }
            }
            if (mVotes != null) {
                this.etInput.removeTextChangedListener(this.mTextWatcher);
                this.etInput.setText("");
                String str = (String) mVotes.get(this.mWitness.getAddress());
                if (!StringTronUtil.isEmpty(str) && !"0".equals(str)) {
                    this.etInput.setText(str);
                }
                this.etInput.addTextChangedListener(this.mTextWatcher);
            }
            this.tvPersonName.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    BatchVoteDialogeAdapter.ViewHolder.this.lambda$bind$2(dataBean, view);
                }
            });
            this.icDeleteCandidate.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    BatchVoteDialogeAdapter.ViewHolder.this.lambda$bind$3(dataBean, view);
                }
            });
        }

        public void lambda$bind$2(WitnessOutput.DataBean dataBean, View view) {
            if (dataBean == null || TextUtils.isEmpty(dataBean.getUrl())) {
                return;
            }
            CommonWebActivityV3.start(mContext, dataBean.getUrl(), TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName(), -2, false);
        }

        public void lambda$bind$3(WitnessOutput.DataBean dataBean, View view) {
            int adapterPosition;
            if (mVotes == null || dataBean == null || (adapterPosition = getAdapterPosition()) < 0) {
                return;
            }
            long checkInputNumber = checkInputNumber(this.etInput.getText());
            String address = ((WitnessOutput.DataBean) mWitnessesList.get(adapterPosition)).getAddress();
            mVotes.remove(address);
            mWitnessesList.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
            notifyValueChanged(address, 0L, -checkInputNumber);
        }

        private void mappingId(View view) {
            this.tvPersonName = (TextView) view.findViewById(R.id.tv_person_name);
            this.etInput = (EditText) view.findViewById(R.id.et_input);
            this.icDeleteCandidate = (ImageView) view.findViewById(R.id.iv_delete_VR);
        }
    }
}
