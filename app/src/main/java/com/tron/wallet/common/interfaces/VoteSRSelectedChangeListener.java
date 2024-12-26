package com.tron.wallet.common.interfaces;

import com.tron.wallet.business.vote.bean.WitnessOutput;
public interface VoteSRSelectedChangeListener {
    void onVoteSRClicked(String str, WitnessOutput.DataBean dataBean);

    void onVoteSRSelected(String str, WitnessOutput.DataBean dataBean, boolean z);

    void onVoteSRUnSelected(String str, WitnessOutput.DataBean dataBean);
}
