package com.tron.wallet.business.vote.util;

import com.tron.wallet.common.interfaces.VoteCountChangeListener;
public interface VoteCountChangeListenerV2 extends VoteCountChangeListener {

    public static class BaseImpl implements VoteCountChangeListenerV2 {
        @Override
        public void onVoteClearAll() {
        }

        @Override
        public void onVoteCountChanged(String str, long j) {
        }

        @Override
        public void onVoteCountChanged(String str, long j, long j2, boolean z) {
        }

        @Override
        public void onVoteSRClick() {
        }

        @Override
        public void onVoteSRDeleted(String str) {
        }
    }

    void onVoteCountChanged(String str, long j, long j2, boolean z);
}
