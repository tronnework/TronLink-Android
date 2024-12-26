package com.tron.wallet.db.bean;
public class AssetIssueContractDao {
    public String abbr;
    public String description;
    public long end_time;
    public Long id;
    public String name;
    public String num;
    public String owner_address;
    public int precision;
    public long start_time;
    public String total_supply;
    public String trx_num;
    public String url;

    public String getAbbr() {
        return this.abbr;
    }

    public String getDescription() {
        return this.description;
    }

    public long getEnd_time() {
        return this.end_time;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNum() {
        return this.num;
    }

    public String getOwner_address() {
        return this.owner_address;
    }

    public int getPrecision() {
        return this.precision;
    }

    public long getStart_time() {
        return this.start_time;
    }

    public String getTotal_supply() {
        return this.total_supply;
    }

    public String getTrx_num() {
        return this.trx_num;
    }

    public String getUrl() {
        return this.url;
    }

    public void setAbbr(String str) {
        this.abbr = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setEnd_time(long j) {
        this.end_time = j;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public void setOwner_address(String str) {
        this.owner_address = str;
    }

    public void setPrecision(int i) {
        this.precision = i;
    }

    public void setStart_time(long j) {
        this.start_time = j;
    }

    public void setTotal_supply(String str) {
        this.total_supply = str;
    }

    public void setTrx_num(String str) {
        this.trx_num = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public AssetIssueContractDao(Long l, String str, String str2, String str3, String str4, String str5, int i, String str6, long j, long j2, String str7, String str8) {
        this.id = l;
        this.owner_address = str;
        this.name = str2;
        this.abbr = str3;
        this.total_supply = str4;
        this.trx_num = str5;
        this.precision = i;
        this.num = str6;
        this.start_time = j;
        this.end_time = j2;
        this.description = str7;
        this.url = str8;
    }

    public AssetIssueContractDao() {
    }

    public String toString() {
        return "AssetIssueContractDao{id=" + this.id + ", name='" + this.name + "', precision=" + this.precision + '}';
    }
}
