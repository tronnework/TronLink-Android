package com.tron.wallet.business.tabmy.proposals.bean;

import java.text.DecimalFormat;
public class SRDetailBean {
    private String address;
    private long blocks;
    private int code = 0;
    private String linkUrl;
    private String name;
    private String profit;
    private int rank;
    private double votePercent;
    private long voterNumber;
    private long votes;
    private String yield;

    public String getAddress() {
        return this.address;
    }

    public long getBlocks() {
        return this.blocks;
    }

    public int getCode() {
        return this.code;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public String getName() {
        return this.name;
    }

    public String getProfit() {
        return this.profit;
    }

    public int getRank() {
        return this.rank;
    }

    public double getVotePercent() {
        return this.votePercent;
    }

    public long getVoterNumber() {
        return this.voterNumber;
    }

    public long getVotes() {
        return this.votes;
    }

    public String getYield() {
        return this.yield;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setBlocks(long j) {
        this.blocks = j;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setLinkUrl(String str) {
        this.linkUrl = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setProfit(String str) {
        this.profit = str;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public void setVotePercent(double d) {
        this.votePercent = d;
    }

    public void setVoterNumber(long j) {
        this.voterNumber = j;
    }

    public void setVotes(long j) {
        this.votes = j;
    }

    public void setYield(String str) {
        this.yield = str;
    }

    public String getFormattedVotes() {
        return new DecimalFormat(",###").format(this.votes);
    }
}
