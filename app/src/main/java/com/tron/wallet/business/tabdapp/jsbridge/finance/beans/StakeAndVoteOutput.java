package com.tron.wallet.business.tabdapp.jsbridge.finance.beans;
public class StakeAndVoteOutput {
    private String errorMessage;
    private long numberForVotes;
    private String stakeHash;
    private boolean successfullyFrozen;
    private boolean successfullyVoted;

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public long getNumberForVotes() {
        return this.numberForVotes;
    }

    public String getStakeHash() {
        return this.stakeHash;
    }

    public boolean isSuccessfullyFrozen() {
        return this.successfullyFrozen;
    }

    public boolean isSuccessfullyVoted() {
        return this.successfullyVoted;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setNumberForVotes(long j) {
        this.numberForVotes = j;
    }

    public void setStakeHash(String str) {
        this.stakeHash = str;
    }

    public void setSuccessfullyFrozen(boolean z) {
        this.successfullyFrozen = z;
    }

    public void setSuccessfullyVoted(boolean z) {
        this.successfullyVoted = z;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof StakeAndVoteOutput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StakeAndVoteOutput) {
            StakeAndVoteOutput stakeAndVoteOutput = (StakeAndVoteOutput) obj;
            if (stakeAndVoteOutput.canEqual(this) && isSuccessfullyFrozen() == stakeAndVoteOutput.isSuccessfullyFrozen() && isSuccessfullyVoted() == stakeAndVoteOutput.isSuccessfullyVoted() && getNumberForVotes() == stakeAndVoteOutput.getNumberForVotes()) {
                String errorMessage = getErrorMessage();
                String errorMessage2 = stakeAndVoteOutput.getErrorMessage();
                if (errorMessage != null ? errorMessage.equals(errorMessage2) : errorMessage2 == null) {
                    String stakeHash = getStakeHash();
                    String stakeHash2 = stakeAndVoteOutput.getStakeHash();
                    return stakeHash != null ? stakeHash.equals(stakeHash2) : stakeHash2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = (((isSuccessfullyFrozen() ? 79 : 97) + 59) * 59) + (isSuccessfullyVoted() ? 79 : 97);
        long numberForVotes = getNumberForVotes();
        String errorMessage = getErrorMessage();
        int hashCode = (((i * 59) + ((int) (numberForVotes ^ (numberForVotes >>> 32)))) * 59) + (errorMessage == null ? 43 : errorMessage.hashCode());
        String stakeHash = getStakeHash();
        return (hashCode * 59) + (stakeHash != null ? stakeHash.hashCode() : 43);
    }

    public String toString() {
        return "StakeAndVoteOutput(successfullyFrozen=" + isSuccessfullyFrozen() + ", successfullyVoted=" + isSuccessfullyVoted() + ", errorMessage=" + getErrorMessage() + ", numberForVotes=" + getNumberForVotes() + ", stakeHash=" + getStakeHash() + ")";
    }
}
