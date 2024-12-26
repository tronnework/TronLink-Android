package com.tron.wallet.business.vote.bean;
public class SrTotalVotesResponse {
    private int total;
    private long totalVotes;

    public int getTotal() {
        return this.total;
    }

    public long getTotalVotes() {
        return this.totalVotes;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public void setTotalVotes(long j) {
        this.totalVotes = j;
    }
}
