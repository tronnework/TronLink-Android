package com.tron.wallet.common.interfaces;
public interface VoteCountChangeListener {
    void onVoteClearAll();

    void onVoteCountChanged(String str, long j);

    void onVoteSRClick();

    void onVoteSRDeleted(String str);
}
