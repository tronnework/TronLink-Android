package com.tron.wallet.business.vote.fastvote;

import com.tron.wallet.business.vote.bean.WitnessOutput;
public class VoteWitnessBean {
    WitnessOutput.DataBean dataBean;
    boolean isTitle;
    boolean visibility;
    String voteCount;
    String votedCount;

    public WitnessOutput.DataBean getDataBean() {
        return this.dataBean;
    }

    public String getVoteCount() {
        return this.voteCount;
    }

    public String getVotedCount() {
        return this.votedCount;
    }

    public boolean isTitle() {
        return this.isTitle;
    }

    public boolean isVisibility() {
        return this.visibility;
    }

    public void setDataBean(WitnessOutput.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public void setTitle(boolean z) {
        this.isTitle = z;
    }

    public void setVisibility(boolean z) {
        this.visibility = z;
    }

    public void setVoteCount(String str) {
        this.voteCount = str;
    }

    public void setVotedCount(String str) {
        this.votedCount = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof VoteWitnessBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VoteWitnessBean) {
            VoteWitnessBean voteWitnessBean = (VoteWitnessBean) obj;
            if (voteWitnessBean.canEqual(this) && isVisibility() == voteWitnessBean.isVisibility() && isTitle() == voteWitnessBean.isTitle()) {
                WitnessOutput.DataBean dataBean = getDataBean();
                WitnessOutput.DataBean dataBean2 = voteWitnessBean.getDataBean();
                if (dataBean != null ? dataBean.equals(dataBean2) : dataBean2 == null) {
                    String votedCount = getVotedCount();
                    String votedCount2 = voteWitnessBean.getVotedCount();
                    if (votedCount != null ? votedCount.equals(votedCount2) : votedCount2 == null) {
                        String voteCount = getVoteCount();
                        String voteCount2 = voteWitnessBean.getVoteCount();
                        return voteCount != null ? voteCount.equals(voteCount2) : voteCount2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = (((isVisibility() ? 79 : 97) + 59) * 59) + (isTitle() ? 79 : 97);
        WitnessOutput.DataBean dataBean = getDataBean();
        int hashCode = (i * 59) + (dataBean == null ? 43 : dataBean.hashCode());
        String votedCount = getVotedCount();
        int hashCode2 = (hashCode * 59) + (votedCount == null ? 43 : votedCount.hashCode());
        String voteCount = getVoteCount();
        return (hashCode2 * 59) + (voteCount != null ? voteCount.hashCode() : 43);
    }

    public String toString() {
        return "VoteWitnessBean(dataBean=" + getDataBean() + ", votedCount=" + getVotedCount() + ", voteCount=" + getVoteCount() + ", visibility=" + isVisibility() + ", isTitle=" + isTitle() + ")";
    }
}
