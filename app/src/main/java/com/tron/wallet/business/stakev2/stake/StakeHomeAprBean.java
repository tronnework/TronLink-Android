package com.tron.wallet.business.stakev2.stake;

import com.tron.wallet.business.vote.bean.WitnessOutput;
public class StakeHomeAprBean {
    WitnessOutput myVotes;
    WitnessOutput top3Witness;

    public WitnessOutput getMyVotes() {
        return this.myVotes;
    }

    public WitnessOutput getTop3Witness() {
        return this.top3Witness;
    }

    public void setMyVotes(WitnessOutput witnessOutput) {
        this.myVotes = witnessOutput;
    }

    public void setTop3Witness(WitnessOutput witnessOutput) {
        this.top3Witness = witnessOutput;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof StakeHomeAprBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StakeHomeAprBean) {
            StakeHomeAprBean stakeHomeAprBean = (StakeHomeAprBean) obj;
            if (stakeHomeAprBean.canEqual(this)) {
                WitnessOutput myVotes = getMyVotes();
                WitnessOutput myVotes2 = stakeHomeAprBean.getMyVotes();
                if (myVotes != null ? myVotes.equals(myVotes2) : myVotes2 == null) {
                    WitnessOutput top3Witness = getTop3Witness();
                    WitnessOutput top3Witness2 = stakeHomeAprBean.getTop3Witness();
                    return top3Witness != null ? top3Witness.equals(top3Witness2) : top3Witness2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        WitnessOutput myVotes = getMyVotes();
        int hashCode = myVotes == null ? 43 : myVotes.hashCode();
        WitnessOutput top3Witness = getTop3Witness();
        return ((hashCode + 59) * 59) + (top3Witness != null ? top3Witness.hashCode() : 43);
    }

    public String toString() {
        return "StakeHomeAprBean(myVotes=" + getMyVotes() + ", top3Witness=" + getTop3Witness() + ")";
    }

    public StakeHomeAprBean(WitnessOutput witnessOutput, WitnessOutput witnessOutput2) {
        this.myVotes = witnessOutput;
        this.top3Witness = witnessOutput2;
    }
}
