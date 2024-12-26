package com.tron.wallet.business.finance.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tron.wallet.common.config.TronConfig;
import java.util.List;
public class MyFinanceTokenListBean {
    private int code;
    private MyFinanceTokenListDataBean data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public MyFinanceTokenListDataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(MyFinanceTokenListDataBean myFinanceTokenListDataBean) {
        this.data = myFinanceTokenListDataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof MyFinanceTokenListBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MyFinanceTokenListBean) {
            MyFinanceTokenListBean myFinanceTokenListBean = (MyFinanceTokenListBean) obj;
            if (myFinanceTokenListBean.canEqual(this) && getCode() == myFinanceTokenListBean.getCode()) {
                String message = getMessage();
                String message2 = myFinanceTokenListBean.getMessage();
                if (message != null ? message.equals(message2) : message2 == null) {
                    MyFinanceTokenListDataBean data = getData();
                    MyFinanceTokenListDataBean data2 = myFinanceTokenListBean.getData();
                    return data != null ? data.equals(data2) : data2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String message = getMessage();
        int code = ((getCode() + 59) * 59) + (message == null ? 43 : message.hashCode());
        MyFinanceTokenListDataBean data = getData();
        return (code * 59) + (data != null ? data.hashCode() : 43);
    }

    public String toString() {
        return "MyFinanceTokenListBean(code=" + getCode() + ", message=" + getMessage() + ", data=" + getData() + ")";
    }

    public static class MyFinanceTokenListDataBean {
        @JsonProperty("tokenList")
        private List<TokenListDTO> tokenList;
        @JsonProperty("total")
        private TotalDTO total;

        public List<TokenListDTO> getTokenList() {
            return this.tokenList;
        }

        public TotalDTO getTotal() {
            return this.total;
        }

        @JsonProperty("tokenList")
        public void setTokenList(List<TokenListDTO> list) {
            this.tokenList = list;
        }

        @JsonProperty("total")
        public void setTotal(TotalDTO totalDTO) {
            this.total = totalDTO;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof MyFinanceTokenListDataBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof MyFinanceTokenListDataBean) {
                MyFinanceTokenListDataBean myFinanceTokenListDataBean = (MyFinanceTokenListDataBean) obj;
                if (myFinanceTokenListDataBean.canEqual(this)) {
                    TotalDTO total = getTotal();
                    TotalDTO total2 = myFinanceTokenListDataBean.getTotal();
                    if (total != null ? total.equals(total2) : total2 == null) {
                        List<TokenListDTO> tokenList = getTokenList();
                        List<TokenListDTO> tokenList2 = myFinanceTokenListDataBean.getTokenList();
                        return tokenList != null ? tokenList.equals(tokenList2) : tokenList2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            TotalDTO total = getTotal();
            int hashCode = total == null ? 43 : total.hashCode();
            List<TokenListDTO> tokenList = getTokenList();
            return ((hashCode + 59) * 59) + (tokenList != null ? tokenList.hashCode() : 43);
        }

        public String toString() {
            return "MyFinanceTokenListBean.MyFinanceTokenListDataBean(total=" + getTotal() + ", tokenList=" + getTokenList() + ")";
        }

        public static class TotalDTO {
            @JsonProperty("financialAssetsCny")
            private String financialAssetsCny;
            @JsonProperty("financialAssetsUsd")
            private String financialAssetsUsd;
            @JsonProperty("financialIncomeCny")
            private String financialIncomeCny;
            @JsonProperty("financialIncomeUsd")
            private String financialIncomeUsd;
            @JsonProperty("tokenList")
            private List<TokenListDTO> tokenList;

            public String getFinancialAssetsCny() {
                return this.financialAssetsCny;
            }

            public String getFinancialAssetsUsd() {
                return this.financialAssetsUsd;
            }

            public String getFinancialIncomeCny() {
                return this.financialIncomeCny;
            }

            public String getFinancialIncomeUsd() {
                return this.financialIncomeUsd;
            }

            public List<TokenListDTO> getTokenList() {
                return this.tokenList;
            }

            @JsonProperty("financialAssetsCny")
            public void setFinancialAssetsCny(String str) {
                this.financialAssetsCny = str;
            }

            @JsonProperty("financialAssetsUsd")
            public void setFinancialAssetsUsd(String str) {
                this.financialAssetsUsd = str;
            }

            @JsonProperty("financialIncomeCny")
            public void setFinancialIncomeCny(String str) {
                this.financialIncomeCny = str;
            }

            @JsonProperty("financialIncomeUsd")
            public void setFinancialIncomeUsd(String str) {
                this.financialIncomeUsd = str;
            }

            @JsonProperty("tokenList")
            public void setTokenList(List<TokenListDTO> list) {
                this.tokenList = list;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof TotalDTO;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof TotalDTO) {
                    TotalDTO totalDTO = (TotalDTO) obj;
                    if (totalDTO.canEqual(this)) {
                        String financialAssetsCny = getFinancialAssetsCny();
                        String financialAssetsCny2 = totalDTO.getFinancialAssetsCny();
                        if (financialAssetsCny != null ? financialAssetsCny.equals(financialAssetsCny2) : financialAssetsCny2 == null) {
                            String financialAssetsUsd = getFinancialAssetsUsd();
                            String financialAssetsUsd2 = totalDTO.getFinancialAssetsUsd();
                            if (financialAssetsUsd != null ? financialAssetsUsd.equals(financialAssetsUsd2) : financialAssetsUsd2 == null) {
                                String financialIncomeUsd = getFinancialIncomeUsd();
                                String financialIncomeUsd2 = totalDTO.getFinancialIncomeUsd();
                                if (financialIncomeUsd != null ? financialIncomeUsd.equals(financialIncomeUsd2) : financialIncomeUsd2 == null) {
                                    String financialIncomeCny = getFinancialIncomeCny();
                                    String financialIncomeCny2 = totalDTO.getFinancialIncomeCny();
                                    if (financialIncomeCny != null ? financialIncomeCny.equals(financialIncomeCny2) : financialIncomeCny2 == null) {
                                        List<TokenListDTO> tokenList = getTokenList();
                                        List<TokenListDTO> tokenList2 = totalDTO.getTokenList();
                                        return tokenList != null ? tokenList.equals(tokenList2) : tokenList2 == null;
                                    }
                                    return false;
                                }
                                return false;
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
                String financialAssetsCny = getFinancialAssetsCny();
                int hashCode = financialAssetsCny == null ? 43 : financialAssetsCny.hashCode();
                String financialAssetsUsd = getFinancialAssetsUsd();
                int hashCode2 = ((hashCode + 59) * 59) + (financialAssetsUsd == null ? 43 : financialAssetsUsd.hashCode());
                String financialIncomeUsd = getFinancialIncomeUsd();
                int hashCode3 = (hashCode2 * 59) + (financialIncomeUsd == null ? 43 : financialIncomeUsd.hashCode());
                String financialIncomeCny = getFinancialIncomeCny();
                int hashCode4 = (hashCode3 * 59) + (financialIncomeCny == null ? 43 : financialIncomeCny.hashCode());
                List<TokenListDTO> tokenList = getTokenList();
                return (hashCode4 * 59) + (tokenList != null ? tokenList.hashCode() : 43);
            }

            public String toString() {
                return "MyFinanceTokenListBean.MyFinanceTokenListDataBean.TotalDTO(financialAssetsCny=" + getFinancialAssetsCny() + ", financialAssetsUsd=" + getFinancialAssetsUsd() + ", financialIncomeUsd=" + getFinancialIncomeUsd() + ", financialIncomeCny=" + getFinancialIncomeCny() + ", tokenList=" + getTokenList() + ")";
            }

            public static class TokenListDTO {
                @JsonProperty("percent")
                private String percent;
                @JsonProperty("tokenName")
                private String tokenName;

                public String getPercent() {
                    return this.percent;
                }

                public String getTokenName() {
                    return this.tokenName;
                }

                @JsonProperty("percent")
                public void setPercent(String str) {
                    this.percent = str;
                }

                @JsonProperty("tokenName")
                public void setTokenName(String str) {
                    this.tokenName = str;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof TokenListDTO;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (obj instanceof TokenListDTO) {
                        TokenListDTO tokenListDTO = (TokenListDTO) obj;
                        if (tokenListDTO.canEqual(this)) {
                            String tokenName = getTokenName();
                            String tokenName2 = tokenListDTO.getTokenName();
                            if (tokenName != null ? tokenName.equals(tokenName2) : tokenName2 == null) {
                                String percent = getPercent();
                                String percent2 = tokenListDTO.getPercent();
                                return percent != null ? percent.equals(percent2) : percent2 == null;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }

                public int hashCode() {
                    String tokenName = getTokenName();
                    int hashCode = tokenName == null ? 43 : tokenName.hashCode();
                    String percent = getPercent();
                    return ((hashCode + 59) * 59) + (percent != null ? percent.hashCode() : 43);
                }

                public String toString() {
                    return "MyFinanceTokenListBean.MyFinanceTokenListDataBean.TotalDTO.TokenListDTO(tokenName=" + getTokenName() + ", percent=" + getPercent() + ")";
                }
            }
        }

        public static class TokenListDTO {
            @JsonProperty("financialAssetsCny")
            private String financialAssetsCny;
            @JsonProperty("financialAssetsUsd")
            private String financialAssetsUsd;
            @JsonProperty(TronConfig.PRECISION)
            private String precision;
            @JsonProperty("projectList")
            private List<ProjectListDTO> projectList;
            @JsonProperty("tokenIcon")
            private String tokenIcon;
            @JsonProperty("tokenId")
            private String tokenId;
            @JsonProperty("tokenName")
            private String tokenName;

            public String getFinancialAssetsCny() {
                return this.financialAssetsCny;
            }

            public String getFinancialAssetsUsd() {
                return this.financialAssetsUsd;
            }

            public String getPrecision() {
                return this.precision;
            }

            public List<ProjectListDTO> getProjectList() {
                return this.projectList;
            }

            public String getTokenIcon() {
                return this.tokenIcon;
            }

            public String getTokenId() {
                return this.tokenId;
            }

            public String getTokenName() {
                return this.tokenName;
            }

            @JsonProperty("financialAssetsCny")
            public void setFinancialAssetsCny(String str) {
                this.financialAssetsCny = str;
            }

            @JsonProperty("financialAssetsUsd")
            public void setFinancialAssetsUsd(String str) {
                this.financialAssetsUsd = str;
            }

            @JsonProperty(TronConfig.PRECISION)
            public void setPrecision(String str) {
                this.precision = str;
            }

            @JsonProperty("projectList")
            public void setProjectList(List<ProjectListDTO> list) {
                this.projectList = list;
            }

            @JsonProperty("tokenIcon")
            public void setTokenIcon(String str) {
                this.tokenIcon = str;
            }

            @JsonProperty("tokenId")
            public void setTokenId(String str) {
                this.tokenId = str;
            }

            @JsonProperty("tokenName")
            public void setTokenName(String str) {
                this.tokenName = str;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof TokenListDTO;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof TokenListDTO) {
                    TokenListDTO tokenListDTO = (TokenListDTO) obj;
                    if (tokenListDTO.canEqual(this)) {
                        String tokenName = getTokenName();
                        String tokenName2 = tokenListDTO.getTokenName();
                        if (tokenName != null ? tokenName.equals(tokenName2) : tokenName2 == null) {
                            String tokenIcon = getTokenIcon();
                            String tokenIcon2 = tokenListDTO.getTokenIcon();
                            if (tokenIcon != null ? tokenIcon.equals(tokenIcon2) : tokenIcon2 == null) {
                                String tokenId = getTokenId();
                                String tokenId2 = tokenListDTO.getTokenId();
                                if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                                    String precision = getPrecision();
                                    String precision2 = tokenListDTO.getPrecision();
                                    if (precision != null ? precision.equals(precision2) : precision2 == null) {
                                        String financialAssetsCny = getFinancialAssetsCny();
                                        String financialAssetsCny2 = tokenListDTO.getFinancialAssetsCny();
                                        if (financialAssetsCny != null ? financialAssetsCny.equals(financialAssetsCny2) : financialAssetsCny2 == null) {
                                            String financialAssetsUsd = getFinancialAssetsUsd();
                                            String financialAssetsUsd2 = tokenListDTO.getFinancialAssetsUsd();
                                            if (financialAssetsUsd != null ? financialAssetsUsd.equals(financialAssetsUsd2) : financialAssetsUsd2 == null) {
                                                List<ProjectListDTO> projectList = getProjectList();
                                                List<ProjectListDTO> projectList2 = tokenListDTO.getProjectList();
                                                return projectList != null ? projectList.equals(projectList2) : projectList2 == null;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
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
                String tokenName = getTokenName();
                int hashCode = tokenName == null ? 43 : tokenName.hashCode();
                String tokenIcon = getTokenIcon();
                int hashCode2 = ((hashCode + 59) * 59) + (tokenIcon == null ? 43 : tokenIcon.hashCode());
                String tokenId = getTokenId();
                int hashCode3 = (hashCode2 * 59) + (tokenId == null ? 43 : tokenId.hashCode());
                String precision = getPrecision();
                int hashCode4 = (hashCode3 * 59) + (precision == null ? 43 : precision.hashCode());
                String financialAssetsCny = getFinancialAssetsCny();
                int hashCode5 = (hashCode4 * 59) + (financialAssetsCny == null ? 43 : financialAssetsCny.hashCode());
                String financialAssetsUsd = getFinancialAssetsUsd();
                int hashCode6 = (hashCode5 * 59) + (financialAssetsUsd == null ? 43 : financialAssetsUsd.hashCode());
                List<ProjectListDTO> projectList = getProjectList();
                return (hashCode6 * 59) + (projectList != null ? projectList.hashCode() : 43);
            }

            public String toString() {
                return "MyFinanceTokenListBean.MyFinanceTokenListDataBean.TokenListDTO(tokenName=" + getTokenName() + ", tokenIcon=" + getTokenIcon() + ", tokenId=" + getTokenId() + ", precision=" + getPrecision() + ", financialAssetsCny=" + getFinancialAssetsCny() + ", financialAssetsUsd=" + getFinancialAssetsUsd() + ", projectList=" + getProjectList() + ")";
            }

            public static class ProjectListDTO {
                @JsonProperty("apy")
                private String apy;
                @JsonProperty("financialAssetsCny")
                private String financialAssetsCny;
                @JsonProperty("financialAssetsUsd")
                private String financialAssetsUsd;
                @JsonProperty("financialAssetsValue")
                private String financialAssetsValue;
                @JsonProperty("financialIncomeCny")
                private String financialIncomeCny;
                @JsonProperty("financialIncomePrecision")
                private String financialIncomePrecision;
                @JsonProperty("financialIncomeTokenID")
                private String financialIncomeTokenID;
                @JsonProperty("financialIncomeTokenName")
                private String financialIncomeTokenName;
                @JsonProperty("financialIncomeUsd")
                private String financialIncomeUsd;
                @JsonProperty("financialIncomeValue")
                private String financialIncomeValue;
                @JsonProperty("projectIcon")
                private String projectIcon;
                @JsonProperty("projectId")
                private String projectId;
                @JsonProperty("projectName")
                private String projectName;

                public String getApy() {
                    return this.apy;
                }

                public String getFinancialAssetsCny() {
                    return this.financialAssetsCny;
                }

                public String getFinancialAssetsUsd() {
                    return this.financialAssetsUsd;
                }

                public String getFinancialAssetsValue() {
                    return this.financialAssetsValue;
                }

                public String getFinancialIncomeCny() {
                    return this.financialIncomeCny;
                }

                public String getFinancialIncomePrecision() {
                    return this.financialIncomePrecision;
                }

                public String getFinancialIncomeTokenID() {
                    return this.financialIncomeTokenID;
                }

                public String getFinancialIncomeTokenName() {
                    return this.financialIncomeTokenName;
                }

                public String getFinancialIncomeUsd() {
                    return this.financialIncomeUsd;
                }

                public String getFinancialIncomeValue() {
                    return this.financialIncomeValue;
                }

                public String getProjectIcon() {
                    return this.projectIcon;
                }

                public String getProjectId() {
                    return this.projectId;
                }

                public String getProjectName() {
                    return this.projectName;
                }

                @JsonProperty("apy")
                public void setApy(String str) {
                    this.apy = str;
                }

                @JsonProperty("financialAssetsCny")
                public void setFinancialAssetsCny(String str) {
                    this.financialAssetsCny = str;
                }

                @JsonProperty("financialAssetsUsd")
                public void setFinancialAssetsUsd(String str) {
                    this.financialAssetsUsd = str;
                }

                @JsonProperty("financialAssetsValue")
                public void setFinancialAssetsValue(String str) {
                    this.financialAssetsValue = str;
                }

                @JsonProperty("financialIncomeCny")
                public void setFinancialIncomeCny(String str) {
                    this.financialIncomeCny = str;
                }

                @JsonProperty("financialIncomePrecision")
                public void setFinancialIncomePrecision(String str) {
                    this.financialIncomePrecision = str;
                }

                @JsonProperty("financialIncomeTokenID")
                public void setFinancialIncomeTokenID(String str) {
                    this.financialIncomeTokenID = str;
                }

                @JsonProperty("financialIncomeTokenName")
                public void setFinancialIncomeTokenName(String str) {
                    this.financialIncomeTokenName = str;
                }

                @JsonProperty("financialIncomeUsd")
                public void setFinancialIncomeUsd(String str) {
                    this.financialIncomeUsd = str;
                }

                @JsonProperty("financialIncomeValue")
                public void setFinancialIncomeValue(String str) {
                    this.financialIncomeValue = str;
                }

                @JsonProperty("projectIcon")
                public void setProjectIcon(String str) {
                    this.projectIcon = str;
                }

                @JsonProperty("projectId")
                public void setProjectId(String str) {
                    this.projectId = str;
                }

                @JsonProperty("projectName")
                public void setProjectName(String str) {
                    this.projectName = str;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ProjectListDTO;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (obj instanceof ProjectListDTO) {
                        ProjectListDTO projectListDTO = (ProjectListDTO) obj;
                        if (projectListDTO.canEqual(this)) {
                            String projectName = getProjectName();
                            String projectName2 = projectListDTO.getProjectName();
                            if (projectName != null ? projectName.equals(projectName2) : projectName2 == null) {
                                String projectIcon = getProjectIcon();
                                String projectIcon2 = projectListDTO.getProjectIcon();
                                if (projectIcon != null ? projectIcon.equals(projectIcon2) : projectIcon2 == null) {
                                    String projectId = getProjectId();
                                    String projectId2 = projectListDTO.getProjectId();
                                    if (projectId != null ? projectId.equals(projectId2) : projectId2 == null) {
                                        String apy = getApy();
                                        String apy2 = projectListDTO.getApy();
                                        if (apy != null ? apy.equals(apy2) : apy2 == null) {
                                            String financialAssetsValue = getFinancialAssetsValue();
                                            String financialAssetsValue2 = projectListDTO.getFinancialAssetsValue();
                                            if (financialAssetsValue != null ? financialAssetsValue.equals(financialAssetsValue2) : financialAssetsValue2 == null) {
                                                String financialAssetsCny = getFinancialAssetsCny();
                                                String financialAssetsCny2 = projectListDTO.getFinancialAssetsCny();
                                                if (financialAssetsCny != null ? financialAssetsCny.equals(financialAssetsCny2) : financialAssetsCny2 == null) {
                                                    String financialAssetsUsd = getFinancialAssetsUsd();
                                                    String financialAssetsUsd2 = projectListDTO.getFinancialAssetsUsd();
                                                    if (financialAssetsUsd != null ? financialAssetsUsd.equals(financialAssetsUsd2) : financialAssetsUsd2 == null) {
                                                        String financialIncomeUsd = getFinancialIncomeUsd();
                                                        String financialIncomeUsd2 = projectListDTO.getFinancialIncomeUsd();
                                                        if (financialIncomeUsd != null ? financialIncomeUsd.equals(financialIncomeUsd2) : financialIncomeUsd2 == null) {
                                                            String financialIncomeCny = getFinancialIncomeCny();
                                                            String financialIncomeCny2 = projectListDTO.getFinancialIncomeCny();
                                                            if (financialIncomeCny != null ? financialIncomeCny.equals(financialIncomeCny2) : financialIncomeCny2 == null) {
                                                                String financialIncomeValue = getFinancialIncomeValue();
                                                                String financialIncomeValue2 = projectListDTO.getFinancialIncomeValue();
                                                                if (financialIncomeValue != null ? financialIncomeValue.equals(financialIncomeValue2) : financialIncomeValue2 == null) {
                                                                    String financialIncomeTokenID = getFinancialIncomeTokenID();
                                                                    String financialIncomeTokenID2 = projectListDTO.getFinancialIncomeTokenID();
                                                                    if (financialIncomeTokenID != null ? financialIncomeTokenID.equals(financialIncomeTokenID2) : financialIncomeTokenID2 == null) {
                                                                        String financialIncomeTokenName = getFinancialIncomeTokenName();
                                                                        String financialIncomeTokenName2 = projectListDTO.getFinancialIncomeTokenName();
                                                                        if (financialIncomeTokenName != null ? financialIncomeTokenName.equals(financialIncomeTokenName2) : financialIncomeTokenName2 == null) {
                                                                            String financialIncomePrecision = getFinancialIncomePrecision();
                                                                            String financialIncomePrecision2 = projectListDTO.getFinancialIncomePrecision();
                                                                            return financialIncomePrecision != null ? financialIncomePrecision.equals(financialIncomePrecision2) : financialIncomePrecision2 == null;
                                                                        }
                                                                        return false;
                                                                    }
                                                                    return false;
                                                                }
                                                                return false;
                                                            }
                                                            return false;
                                                        }
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                                return false;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
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
                    String projectName = getProjectName();
                    int hashCode = projectName == null ? 43 : projectName.hashCode();
                    String projectIcon = getProjectIcon();
                    int hashCode2 = ((hashCode + 59) * 59) + (projectIcon == null ? 43 : projectIcon.hashCode());
                    String projectId = getProjectId();
                    int hashCode3 = (hashCode2 * 59) + (projectId == null ? 43 : projectId.hashCode());
                    String apy = getApy();
                    int hashCode4 = (hashCode3 * 59) + (apy == null ? 43 : apy.hashCode());
                    String financialAssetsValue = getFinancialAssetsValue();
                    int hashCode5 = (hashCode4 * 59) + (financialAssetsValue == null ? 43 : financialAssetsValue.hashCode());
                    String financialAssetsCny = getFinancialAssetsCny();
                    int hashCode6 = (hashCode5 * 59) + (financialAssetsCny == null ? 43 : financialAssetsCny.hashCode());
                    String financialAssetsUsd = getFinancialAssetsUsd();
                    int hashCode7 = (hashCode6 * 59) + (financialAssetsUsd == null ? 43 : financialAssetsUsd.hashCode());
                    String financialIncomeUsd = getFinancialIncomeUsd();
                    int hashCode8 = (hashCode7 * 59) + (financialIncomeUsd == null ? 43 : financialIncomeUsd.hashCode());
                    String financialIncomeCny = getFinancialIncomeCny();
                    int hashCode9 = (hashCode8 * 59) + (financialIncomeCny == null ? 43 : financialIncomeCny.hashCode());
                    String financialIncomeValue = getFinancialIncomeValue();
                    int hashCode10 = (hashCode9 * 59) + (financialIncomeValue == null ? 43 : financialIncomeValue.hashCode());
                    String financialIncomeTokenID = getFinancialIncomeTokenID();
                    int hashCode11 = (hashCode10 * 59) + (financialIncomeTokenID == null ? 43 : financialIncomeTokenID.hashCode());
                    String financialIncomeTokenName = getFinancialIncomeTokenName();
                    int hashCode12 = (hashCode11 * 59) + (financialIncomeTokenName == null ? 43 : financialIncomeTokenName.hashCode());
                    String financialIncomePrecision = getFinancialIncomePrecision();
                    return (hashCode12 * 59) + (financialIncomePrecision != null ? financialIncomePrecision.hashCode() : 43);
                }

                public String toString() {
                    return "MyFinanceTokenListBean.MyFinanceTokenListDataBean.TokenListDTO.ProjectListDTO(projectName=" + getProjectName() + ", projectIcon=" + getProjectIcon() + ", projectId=" + getProjectId() + ", apy=" + getApy() + ", financialAssetsValue=" + getFinancialAssetsValue() + ", financialAssetsCny=" + getFinancialAssetsCny() + ", financialAssetsUsd=" + getFinancialAssetsUsd() + ", financialIncomeUsd=" + getFinancialIncomeUsd() + ", financialIncomeCny=" + getFinancialIncomeCny() + ", financialIncomeValue=" + getFinancialIncomeValue() + ", financialIncomeTokenID=" + getFinancialIncomeTokenID() + ", financialIncomeTokenName=" + getFinancialIncomeTokenName() + ", financialIncomePrecision=" + getFinancialIncomePrecision() + ")";
                }
            }
        }
    }
}
