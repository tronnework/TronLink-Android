package com.tron.wallet.business.tabmy.proposals.bean;
public class MakeProposalsBean {
    public String company;
    public String inputContent;
    public boolean isSelect;
    public String key;
    public String netContent;
    public long proposalId;
    public String proposalsCentent;
    public long selectValue;
    public int type;
    public long value;

    public interface Type {
        public static final int EDITABLE = 0;
        public static final int SELECTABLE = 1;
    }

    public String toString() {
        return "MakeProposalsBean{proposalId=" + this.proposalId + ", proposalsCentent='" + this.proposalsCentent + "', type=" + this.type + ", value=" + this.value + ", key='" + this.key + "', company='" + this.company + "', selectValue=" + this.selectValue + ", isSelect=" + this.isSelect + ", netContent='" + this.netContent + "', inputContent='" + this.inputContent + "'}";
    }
}
