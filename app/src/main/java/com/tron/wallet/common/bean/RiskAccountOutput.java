package com.tron.wallet.common.bean;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
public class RiskAccountOutput extends BaseOutput {
    private Data data;

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof RiskAccountOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RiskAccountOutput) {
            RiskAccountOutput riskAccountOutput = (RiskAccountOutput) obj;
            if (riskAccountOutput.canEqual(this)) {
                Data data = getData();
                Data data2 = riskAccountOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        Data data = getData();
        return 59 + (data == null ? 43 : data.hashCode());
    }

    @Override
    public String toString() {
        return "RiskAccountOutput(data=" + getData() + ")";
    }

    public RiskAccountOutput() {
    }

    public RiskAccountOutput(Data data) {
        this.data = data;
    }

    public static class Data {
        private boolean risk;

        public boolean isRisk() {
            return this.risk;
        }

        public void setRisk(boolean z) {
            this.risk = z;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof Data;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Data) {
                Data data = (Data) obj;
                return data.canEqual(this) && isRisk() == data.isRisk();
            }
            return false;
        }

        public int hashCode() {
            return 59 + (isRisk() ? 79 : 97);
        }

        public String toString() {
            return "RiskAccountOutput.Data(risk=" + isRisk() + ")";
        }

        public Data() {
        }

        public Data(boolean z) {
            this.risk = z;
        }
    }

    public static RiskAccountOutput createDefault() {
        RiskAccountOutput riskAccountOutput = new RiskAccountOutput();
        Data data = new Data();
        data.setRisk(false);
        riskAccountOutput.setData(data);
        riskAccountOutput.setCode(0);
        riskAccountOutput.setMessage("");
        return riskAccountOutput;
    }
}
