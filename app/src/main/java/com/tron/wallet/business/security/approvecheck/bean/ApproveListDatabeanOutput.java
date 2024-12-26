package com.tron.wallet.business.security.approvecheck.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.bean.BaseOutput;
import java.util.List;
public class ApproveListDatabeanOutput extends BaseOutput {
    private ApproveListDatabean data;

    public ApproveListDatabean getData() {
        return this.data;
    }

    public void setData(ApproveListDatabean approveListDatabean) {
        this.data = approveListDatabean;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof ApproveListDatabeanOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ApproveListDatabeanOutput) {
            ApproveListDatabeanOutput approveListDatabeanOutput = (ApproveListDatabeanOutput) obj;
            if (approveListDatabeanOutput.canEqual(this)) {
                ApproveListDatabean data = getData();
                ApproveListDatabean data2 = approveListDatabeanOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        ApproveListDatabean data = getData();
        return 59 + (data == null ? 43 : data.hashCode());
    }

    @Override
    public String toString() {
        return "ApproveListDatabeanOutput(data=" + getData() + ")";
    }

    public static class ApproveListDatabean {
        @JsonProperty("approveAddrCount")
        private String approveAddrCount;
        @JsonProperty("approveContractCount")
        private String approveContractCount;
        @JsonProperty("projects")
        private List<ProjectsBean> projects;
        @JsonProperty("tokens")
        private List<TokensBean> tokens;
        @JsonProperty("totalCnyCount")
        private String totalCnyCount;
        @JsonProperty("totalUsdCount")
        private String totalUsdCount;

        public String getApproveAddrCount() {
            return this.approveAddrCount;
        }

        public String getApproveContractCount() {
            return this.approveContractCount;
        }

        public List<ProjectsBean> getProjects() {
            return this.projects;
        }

        public List<TokensBean> getTokens() {
            return this.tokens;
        }

        public String getTotalCnyCount() {
            return this.totalCnyCount;
        }

        public String getTotalUsdCount() {
            return this.totalUsdCount;
        }

        @JsonProperty("approveAddrCount")
        public void setApproveAddrCount(String str) {
            this.approveAddrCount = str;
        }

        @JsonProperty("approveContractCount")
        public void setApproveContractCount(String str) {
            this.approveContractCount = str;
        }

        @JsonProperty("projects")
        public void setProjects(List<ProjectsBean> list) {
            this.projects = list;
        }

        @JsonProperty("tokens")
        public void setTokens(List<TokensBean> list) {
            this.tokens = list;
        }

        @JsonProperty("totalCnyCount")
        public void setTotalCnyCount(String str) {
            this.totalCnyCount = str;
        }

        @JsonProperty("totalUsdCount")
        public void setTotalUsdCount(String str) {
            this.totalUsdCount = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ApproveListDatabean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ApproveListDatabean) {
                ApproveListDatabean approveListDatabean = (ApproveListDatabean) obj;
                if (approveListDatabean.canEqual(this)) {
                    String totalUsdCount = getTotalUsdCount();
                    String totalUsdCount2 = approveListDatabean.getTotalUsdCount();
                    if (totalUsdCount != null ? totalUsdCount.equals(totalUsdCount2) : totalUsdCount2 == null) {
                        String totalCnyCount = getTotalCnyCount();
                        String totalCnyCount2 = approveListDatabean.getTotalCnyCount();
                        if (totalCnyCount != null ? totalCnyCount.equals(totalCnyCount2) : totalCnyCount2 == null) {
                            String approveContractCount = getApproveContractCount();
                            String approveContractCount2 = approveListDatabean.getApproveContractCount();
                            if (approveContractCount != null ? approveContractCount.equals(approveContractCount2) : approveContractCount2 == null) {
                                String approveAddrCount = getApproveAddrCount();
                                String approveAddrCount2 = approveListDatabean.getApproveAddrCount();
                                if (approveAddrCount != null ? approveAddrCount.equals(approveAddrCount2) : approveAddrCount2 == null) {
                                    List<ProjectsBean> projects = getProjects();
                                    List<ProjectsBean> projects2 = approveListDatabean.getProjects();
                                    if (projects != null ? projects.equals(projects2) : projects2 == null) {
                                        List<TokensBean> tokens = getTokens();
                                        List<TokensBean> tokens2 = approveListDatabean.getTokens();
                                        return tokens != null ? tokens.equals(tokens2) : tokens2 == null;
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
            String totalUsdCount = getTotalUsdCount();
            int hashCode = totalUsdCount == null ? 43 : totalUsdCount.hashCode();
            String totalCnyCount = getTotalCnyCount();
            int hashCode2 = ((hashCode + 59) * 59) + (totalCnyCount == null ? 43 : totalCnyCount.hashCode());
            String approveContractCount = getApproveContractCount();
            int hashCode3 = (hashCode2 * 59) + (approveContractCount == null ? 43 : approveContractCount.hashCode());
            String approveAddrCount = getApproveAddrCount();
            int hashCode4 = (hashCode3 * 59) + (approveAddrCount == null ? 43 : approveAddrCount.hashCode());
            List<ProjectsBean> projects = getProjects();
            int hashCode5 = (hashCode4 * 59) + (projects == null ? 43 : projects.hashCode());
            List<TokensBean> tokens = getTokens();
            return (hashCode5 * 59) + (tokens != null ? tokens.hashCode() : 43);
        }

        public String toString() {
            return "ApproveListDatabeanOutput.ApproveListDatabean(totalUsdCount=" + getTotalUsdCount() + ", totalCnyCount=" + getTotalCnyCount() + ", approveContractCount=" + getApproveContractCount() + ", approveAddrCount=" + getApproveAddrCount() + ", projects=" + getProjects() + ", tokens=" + getTokens() + ")";
        }

        public static class ProjectsBean {
            @JsonProperty("approveAddressType")
            private String approveAddressType;
            @JsonProperty("logo")
            private String logo;
            @JsonProperty(AppMeasurementSdk.ConditionalUserProperty.NAME)
            private String name;
            @JsonProperty("projectAddress")
            private String projectAddress;
            @JsonProperty("projectId")
            private String projectId;
            @JsonProperty("tokenList")
            private List<TokenListBean> tokenList;
            @JsonProperty("totalAmount")
            private String totalAmount;
            @JsonProperty("totalCnyCount")
            private String totalCnyCount;
            @JsonProperty("totalUsdCount")
            private String totalUsdCount;

            public String getApproveAddressType() {
                return this.approveAddressType;
            }

            public String getLogo() {
                return this.logo;
            }

            public String getName() {
                return this.name;
            }

            public String getProjectAddress() {
                return this.projectAddress;
            }

            public String getProjectId() {
                return this.projectId;
            }

            public List<TokenListBean> getTokenList() {
                return this.tokenList;
            }

            public String getTotalAmount() {
                return this.totalAmount;
            }

            public String getTotalCnyCount() {
                return this.totalCnyCount;
            }

            public String getTotalUsdCount() {
                return this.totalUsdCount;
            }

            @JsonProperty("approveAddressType")
            public void setApproveAddressType(String str) {
                this.approveAddressType = str;
            }

            @JsonProperty("logo")
            public void setLogo(String str) {
                this.logo = str;
            }

            @JsonProperty(AppMeasurementSdk.ConditionalUserProperty.NAME)
            public void setName(String str) {
                this.name = str;
            }

            @JsonProperty("projectAddress")
            public void setProjectAddress(String str) {
                this.projectAddress = str;
            }

            @JsonProperty("projectId")
            public void setProjectId(String str) {
                this.projectId = str;
            }

            @JsonProperty("tokenList")
            public void setTokenList(List<TokenListBean> list) {
                this.tokenList = list;
            }

            @JsonProperty("totalAmount")
            public void setTotalAmount(String str) {
                this.totalAmount = str;
            }

            @JsonProperty("totalCnyCount")
            public void setTotalCnyCount(String str) {
                this.totalCnyCount = str;
            }

            @JsonProperty("totalUsdCount")
            public void setTotalUsdCount(String str) {
                this.totalUsdCount = str;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ProjectsBean;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof ProjectsBean) {
                    ProjectsBean projectsBean = (ProjectsBean) obj;
                    if (projectsBean.canEqual(this)) {
                        String name = getName();
                        String name2 = projectsBean.getName();
                        if (name != null ? name.equals(name2) : name2 == null) {
                            String logo = getLogo();
                            String logo2 = projectsBean.getLogo();
                            if (logo != null ? logo.equals(logo2) : logo2 == null) {
                                String totalAmount = getTotalAmount();
                                String totalAmount2 = projectsBean.getTotalAmount();
                                if (totalAmount != null ? totalAmount.equals(totalAmount2) : totalAmount2 == null) {
                                    String totalUsdCount = getTotalUsdCount();
                                    String totalUsdCount2 = projectsBean.getTotalUsdCount();
                                    if (totalUsdCount != null ? totalUsdCount.equals(totalUsdCount2) : totalUsdCount2 == null) {
                                        String totalCnyCount = getTotalCnyCount();
                                        String totalCnyCount2 = projectsBean.getTotalCnyCount();
                                        if (totalCnyCount != null ? totalCnyCount.equals(totalCnyCount2) : totalCnyCount2 == null) {
                                            String projectAddress = getProjectAddress();
                                            String projectAddress2 = projectsBean.getProjectAddress();
                                            if (projectAddress != null ? projectAddress.equals(projectAddress2) : projectAddress2 == null) {
                                                String approveAddressType = getApproveAddressType();
                                                String approveAddressType2 = projectsBean.getApproveAddressType();
                                                if (approveAddressType != null ? approveAddressType.equals(approveAddressType2) : approveAddressType2 == null) {
                                                    String projectId = getProjectId();
                                                    String projectId2 = projectsBean.getProjectId();
                                                    if (projectId != null ? projectId.equals(projectId2) : projectId2 == null) {
                                                        List<TokenListBean> tokenList = getTokenList();
                                                        List<TokenListBean> tokenList2 = projectsBean.getTokenList();
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
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }

            public int hashCode() {
                String name = getName();
                int hashCode = name == null ? 43 : name.hashCode();
                String logo = getLogo();
                int hashCode2 = ((hashCode + 59) * 59) + (logo == null ? 43 : logo.hashCode());
                String totalAmount = getTotalAmount();
                int hashCode3 = (hashCode2 * 59) + (totalAmount == null ? 43 : totalAmount.hashCode());
                String totalUsdCount = getTotalUsdCount();
                int hashCode4 = (hashCode3 * 59) + (totalUsdCount == null ? 43 : totalUsdCount.hashCode());
                String totalCnyCount = getTotalCnyCount();
                int hashCode5 = (hashCode4 * 59) + (totalCnyCount == null ? 43 : totalCnyCount.hashCode());
                String projectAddress = getProjectAddress();
                int hashCode6 = (hashCode5 * 59) + (projectAddress == null ? 43 : projectAddress.hashCode());
                String approveAddressType = getApproveAddressType();
                int hashCode7 = (hashCode6 * 59) + (approveAddressType == null ? 43 : approveAddressType.hashCode());
                String projectId = getProjectId();
                int hashCode8 = (hashCode7 * 59) + (projectId == null ? 43 : projectId.hashCode());
                List<TokenListBean> tokenList = getTokenList();
                return (hashCode8 * 59) + (tokenList != null ? tokenList.hashCode() : 43);
            }

            public String toString() {
                return "ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean(name=" + getName() + ", logo=" + getLogo() + ", totalAmount=" + getTotalAmount() + ", totalUsdCount=" + getTotalUsdCount() + ", totalCnyCount=" + getTotalCnyCount() + ", projectAddress=" + getProjectAddress() + ", approveAddressType=" + getApproveAddressType() + ", projectId=" + getProjectId() + ", tokenList=" + getTokenList() + ")";
            }

            public static class TokenListBean {
                @JsonProperty("amount")
                private String amount;
                @JsonProperty("assetId")
                private String assetId;
                @JsonProperty("balance")
                private int balance;
                @JsonProperty("cnyCount")
                private String cnyCount;
                @JsonProperty("operateTime")
                private Long operateTime;
                @JsonProperty("tokenAddress")
                private String tokenAddress;
                @JsonProperty("tokenCnyPrice")
                private String tokenCnyPrice;
                @JsonProperty("tokenDecimal")
                private Object tokenDecimal;
                @JsonProperty("tokenLogo")
                private String tokenLogo;
                @JsonProperty("tokenName")
                private String tokenName;
                @JsonProperty(AssetsConfig.TOKEN_SYMBOL)
                private String tokenSymbol;
                @JsonProperty(AssetsConfig.TOKEN_TYPE)
                private int tokenType;
                @JsonProperty("tokenUsdPrice")
                private String tokenUsdPrice;
                @JsonProperty("unlimited")
                private Boolean unlimited;
                @JsonProperty("usdCount")
                private String usdCount;

                public String getAmount() {
                    return this.amount;
                }

                public String getAssetId() {
                    return this.assetId;
                }

                public int getBalance() {
                    return this.balance;
                }

                public String getCnyCount() {
                    return this.cnyCount;
                }

                public Long getOperateTime() {
                    return this.operateTime;
                }

                public String getTokenAddress() {
                    return this.tokenAddress;
                }

                public String getTokenCnyPrice() {
                    return this.tokenCnyPrice;
                }

                public Object getTokenDecimal() {
                    return this.tokenDecimal;
                }

                public String getTokenLogo() {
                    return this.tokenLogo;
                }

                public String getTokenName() {
                    return this.tokenName;
                }

                public String getTokenSymbol() {
                    return this.tokenSymbol;
                }

                public int getTokenType() {
                    return this.tokenType;
                }

                public String getTokenUsdPrice() {
                    return this.tokenUsdPrice;
                }

                public Boolean getUnlimited() {
                    return this.unlimited;
                }

                public String getUsdCount() {
                    return this.usdCount;
                }

                @JsonProperty("amount")
                public void setAmount(String str) {
                    this.amount = str;
                }

                @JsonProperty("assetId")
                public void setAssetId(String str) {
                    this.assetId = str;
                }

                @JsonProperty("balance")
                public void setBalance(int i) {
                    this.balance = i;
                }

                @JsonProperty("cnyCount")
                public void setCnyCount(String str) {
                    this.cnyCount = str;
                }

                @JsonProperty("operateTime")
                public void setOperateTime(Long l) {
                    this.operateTime = l;
                }

                @JsonProperty("tokenAddress")
                public void setTokenAddress(String str) {
                    this.tokenAddress = str;
                }

                @JsonProperty("tokenCnyPrice")
                public void setTokenCnyPrice(String str) {
                    this.tokenCnyPrice = str;
                }

                @JsonProperty("tokenDecimal")
                public void setTokenDecimal(Object obj) {
                    this.tokenDecimal = obj;
                }

                @JsonProperty("tokenLogo")
                public void setTokenLogo(String str) {
                    this.tokenLogo = str;
                }

                @JsonProperty("tokenName")
                public void setTokenName(String str) {
                    this.tokenName = str;
                }

                @JsonProperty(AssetsConfig.TOKEN_SYMBOL)
                public void setTokenSymbol(String str) {
                    this.tokenSymbol = str;
                }

                @JsonProperty(AssetsConfig.TOKEN_TYPE)
                public void setTokenType(int i) {
                    this.tokenType = i;
                }

                @JsonProperty("tokenUsdPrice")
                public void setTokenUsdPrice(String str) {
                    this.tokenUsdPrice = str;
                }

                @JsonProperty("unlimited")
                public void setUnlimited(Boolean bool) {
                    this.unlimited = bool;
                }

                @JsonProperty("usdCount")
                public void setUsdCount(String str) {
                    this.usdCount = str;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof TokenListBean;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (obj instanceof TokenListBean) {
                        TokenListBean tokenListBean = (TokenListBean) obj;
                        if (tokenListBean.canEqual(this) && getTokenType() == tokenListBean.getTokenType() && getBalance() == tokenListBean.getBalance()) {
                            Boolean unlimited = getUnlimited();
                            Boolean unlimited2 = tokenListBean.getUnlimited();
                            if (unlimited != null ? unlimited.equals(unlimited2) : unlimited2 == null) {
                                Long operateTime = getOperateTime();
                                Long operateTime2 = tokenListBean.getOperateTime();
                                if (operateTime != null ? operateTime.equals(operateTime2) : operateTime2 == null) {
                                    String tokenAddress = getTokenAddress();
                                    String tokenAddress2 = tokenListBean.getTokenAddress();
                                    if (tokenAddress != null ? tokenAddress.equals(tokenAddress2) : tokenAddress2 == null) {
                                        String tokenSymbol = getTokenSymbol();
                                        String tokenSymbol2 = tokenListBean.getTokenSymbol();
                                        if (tokenSymbol != null ? tokenSymbol.equals(tokenSymbol2) : tokenSymbol2 == null) {
                                            String tokenName = getTokenName();
                                            String tokenName2 = tokenListBean.getTokenName();
                                            if (tokenName != null ? tokenName.equals(tokenName2) : tokenName2 == null) {
                                                Object tokenDecimal = getTokenDecimal();
                                                Object tokenDecimal2 = tokenListBean.getTokenDecimal();
                                                if (tokenDecimal != null ? tokenDecimal.equals(tokenDecimal2) : tokenDecimal2 == null) {
                                                    String tokenLogo = getTokenLogo();
                                                    String tokenLogo2 = tokenListBean.getTokenLogo();
                                                    if (tokenLogo != null ? tokenLogo.equals(tokenLogo2) : tokenLogo2 == null) {
                                                        String assetId = getAssetId();
                                                        String assetId2 = tokenListBean.getAssetId();
                                                        if (assetId != null ? assetId.equals(assetId2) : assetId2 == null) {
                                                            String tokenUsdPrice = getTokenUsdPrice();
                                                            String tokenUsdPrice2 = tokenListBean.getTokenUsdPrice();
                                                            if (tokenUsdPrice != null ? tokenUsdPrice.equals(tokenUsdPrice2) : tokenUsdPrice2 == null) {
                                                                String tokenCnyPrice = getTokenCnyPrice();
                                                                String tokenCnyPrice2 = tokenListBean.getTokenCnyPrice();
                                                                if (tokenCnyPrice != null ? tokenCnyPrice.equals(tokenCnyPrice2) : tokenCnyPrice2 == null) {
                                                                    String amount = getAmount();
                                                                    String amount2 = tokenListBean.getAmount();
                                                                    if (amount != null ? amount.equals(amount2) : amount2 == null) {
                                                                        String usdCount = getUsdCount();
                                                                        String usdCount2 = tokenListBean.getUsdCount();
                                                                        if (usdCount != null ? usdCount.equals(usdCount2) : usdCount2 == null) {
                                                                            String cnyCount = getCnyCount();
                                                                            String cnyCount2 = tokenListBean.getCnyCount();
                                                                            return cnyCount != null ? cnyCount.equals(cnyCount2) : cnyCount2 == null;
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
                    int tokenType = ((getTokenType() + 59) * 59) + getBalance();
                    Boolean unlimited = getUnlimited();
                    int hashCode = (tokenType * 59) + (unlimited == null ? 43 : unlimited.hashCode());
                    Long operateTime = getOperateTime();
                    int hashCode2 = (hashCode * 59) + (operateTime == null ? 43 : operateTime.hashCode());
                    String tokenAddress = getTokenAddress();
                    int hashCode3 = (hashCode2 * 59) + (tokenAddress == null ? 43 : tokenAddress.hashCode());
                    String tokenSymbol = getTokenSymbol();
                    int hashCode4 = (hashCode3 * 59) + (tokenSymbol == null ? 43 : tokenSymbol.hashCode());
                    String tokenName = getTokenName();
                    int hashCode5 = (hashCode4 * 59) + (tokenName == null ? 43 : tokenName.hashCode());
                    Object tokenDecimal = getTokenDecimal();
                    int hashCode6 = (hashCode5 * 59) + (tokenDecimal == null ? 43 : tokenDecimal.hashCode());
                    String tokenLogo = getTokenLogo();
                    int hashCode7 = (hashCode6 * 59) + (tokenLogo == null ? 43 : tokenLogo.hashCode());
                    String assetId = getAssetId();
                    int hashCode8 = (hashCode7 * 59) + (assetId == null ? 43 : assetId.hashCode());
                    String tokenUsdPrice = getTokenUsdPrice();
                    int hashCode9 = (hashCode8 * 59) + (tokenUsdPrice == null ? 43 : tokenUsdPrice.hashCode());
                    String tokenCnyPrice = getTokenCnyPrice();
                    int hashCode10 = (hashCode9 * 59) + (tokenCnyPrice == null ? 43 : tokenCnyPrice.hashCode());
                    String amount = getAmount();
                    int hashCode11 = (hashCode10 * 59) + (amount == null ? 43 : amount.hashCode());
                    String usdCount = getUsdCount();
                    int hashCode12 = (hashCode11 * 59) + (usdCount == null ? 43 : usdCount.hashCode());
                    String cnyCount = getCnyCount();
                    return (hashCode12 * 59) + (cnyCount != null ? cnyCount.hashCode() : 43);
                }

                public String toString() {
                    return "ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean.TokenListBean(tokenAddress=" + getTokenAddress() + ", tokenSymbol=" + getTokenSymbol() + ", tokenName=" + getTokenName() + ", tokenDecimal=" + getTokenDecimal() + ", tokenType=" + getTokenType() + ", tokenLogo=" + getTokenLogo() + ", assetId=" + getAssetId() + ", tokenUsdPrice=" + getTokenUsdPrice() + ", tokenCnyPrice=" + getTokenCnyPrice() + ", amount=" + getAmount() + ", usdCount=" + getUsdCount() + ", cnyCount=" + getCnyCount() + ", unlimited=" + getUnlimited() + ", operateTime=" + getOperateTime() + ", balance=" + getBalance() + ")";
                }
            }
        }

        public static class TokensBean {
            @JsonProperty("balance")
            private int balance;
            @JsonProperty("balanceStr")
            private String balanceStr;
            @JsonProperty("projectList")
            private List<ProjectListBean> projectList;
            @JsonProperty("tokenAddress")
            private String tokenAddress;
            @JsonProperty("tokenCnyPrice")
            private String tokenCnyPrice;
            @JsonProperty("tokenDecimal")
            private int tokenDecimal;
            @JsonProperty("tokenLogo")
            private String tokenLogo;
            @JsonProperty("tokenName")
            private String tokenName;
            @JsonProperty(AssetsConfig.TOKEN_SYMBOL)
            private String tokenSymbol;
            @JsonProperty(AssetsConfig.TOKEN_TYPE)
            private int tokenType;
            @JsonProperty("tokenUsdPrice")
            private String tokenUsdPrice;
            @JsonProperty("totalAmount")
            private String totalAmount;
            @JsonProperty("totalCnyCount")
            private String totalCnyCount;
            @JsonProperty("totalUsdCount")
            private String totalUsdCount;
            @JsonProperty("trc721Id")
            private String trc721Id;

            public int getBalance() {
                return this.balance;
            }

            public String getBalanceStr() {
                return this.balanceStr;
            }

            public List<ProjectListBean> getProjectList() {
                return this.projectList;
            }

            public String getTokenAddress() {
                return this.tokenAddress;
            }

            public String getTokenCnyPrice() {
                return this.tokenCnyPrice;
            }

            public int getTokenDecimal() {
                return this.tokenDecimal;
            }

            public String getTokenLogo() {
                return this.tokenLogo;
            }

            public String getTokenName() {
                return this.tokenName;
            }

            public String getTokenSymbol() {
                return this.tokenSymbol;
            }

            public int getTokenType() {
                return this.tokenType;
            }

            public String getTokenUsdPrice() {
                return this.tokenUsdPrice;
            }

            public String getTotalAmount() {
                return this.totalAmount;
            }

            public String getTotalCnyCount() {
                return this.totalCnyCount;
            }

            public String getTotalUsdCount() {
                return this.totalUsdCount;
            }

            public String getTrc721Id() {
                return this.trc721Id;
            }

            @JsonProperty("balance")
            public void setBalance(int i) {
                this.balance = i;
            }

            @JsonProperty("balanceStr")
            public void setBalanceStr(String str) {
                this.balanceStr = str;
            }

            @JsonProperty("projectList")
            public void setProjectList(List<ProjectListBean> list) {
                this.projectList = list;
            }

            @JsonProperty("tokenAddress")
            public void setTokenAddress(String str) {
                this.tokenAddress = str;
            }

            @JsonProperty("tokenCnyPrice")
            public void setTokenCnyPrice(String str) {
                this.tokenCnyPrice = str;
            }

            @JsonProperty("tokenDecimal")
            public void setTokenDecimal(int i) {
                this.tokenDecimal = i;
            }

            @JsonProperty("tokenLogo")
            public void setTokenLogo(String str) {
                this.tokenLogo = str;
            }

            @JsonProperty("tokenName")
            public void setTokenName(String str) {
                this.tokenName = str;
            }

            @JsonProperty(AssetsConfig.TOKEN_SYMBOL)
            public void setTokenSymbol(String str) {
                this.tokenSymbol = str;
            }

            @JsonProperty(AssetsConfig.TOKEN_TYPE)
            public void setTokenType(int i) {
                this.tokenType = i;
            }

            @JsonProperty("tokenUsdPrice")
            public void setTokenUsdPrice(String str) {
                this.tokenUsdPrice = str;
            }

            @JsonProperty("totalAmount")
            public void setTotalAmount(String str) {
                this.totalAmount = str;
            }

            @JsonProperty("totalCnyCount")
            public void setTotalCnyCount(String str) {
                this.totalCnyCount = str;
            }

            @JsonProperty("totalUsdCount")
            public void setTotalUsdCount(String str) {
                this.totalUsdCount = str;
            }

            @JsonProperty("trc721Id")
            public void setTrc721Id(String str) {
                this.trc721Id = str;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof TokensBean;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof TokensBean) {
                    TokensBean tokensBean = (TokensBean) obj;
                    if (tokensBean.canEqual(this) && getTokenDecimal() == tokensBean.getTokenDecimal() && getTokenType() == tokensBean.getTokenType() && getBalance() == tokensBean.getBalance()) {
                        String tokenAddress = getTokenAddress();
                        String tokenAddress2 = tokensBean.getTokenAddress();
                        if (tokenAddress != null ? tokenAddress.equals(tokenAddress2) : tokenAddress2 == null) {
                            String tokenSymbol = getTokenSymbol();
                            String tokenSymbol2 = tokensBean.getTokenSymbol();
                            if (tokenSymbol != null ? tokenSymbol.equals(tokenSymbol2) : tokenSymbol2 == null) {
                                String tokenName = getTokenName();
                                String tokenName2 = tokensBean.getTokenName();
                                if (tokenName != null ? tokenName.equals(tokenName2) : tokenName2 == null) {
                                    String tokenLogo = getTokenLogo();
                                    String tokenLogo2 = tokensBean.getTokenLogo();
                                    if (tokenLogo != null ? tokenLogo.equals(tokenLogo2) : tokenLogo2 == null) {
                                        String tokenUsdPrice = getTokenUsdPrice();
                                        String tokenUsdPrice2 = tokensBean.getTokenUsdPrice();
                                        if (tokenUsdPrice != null ? tokenUsdPrice.equals(tokenUsdPrice2) : tokenUsdPrice2 == null) {
                                            String trc721Id = getTrc721Id();
                                            String trc721Id2 = tokensBean.getTrc721Id();
                                            if (trc721Id != null ? trc721Id.equals(trc721Id2) : trc721Id2 == null) {
                                                String tokenCnyPrice = getTokenCnyPrice();
                                                String tokenCnyPrice2 = tokensBean.getTokenCnyPrice();
                                                if (tokenCnyPrice != null ? tokenCnyPrice.equals(tokenCnyPrice2) : tokenCnyPrice2 == null) {
                                                    String totalAmount = getTotalAmount();
                                                    String totalAmount2 = tokensBean.getTotalAmount();
                                                    if (totalAmount != null ? totalAmount.equals(totalAmount2) : totalAmount2 == null) {
                                                        String balanceStr = getBalanceStr();
                                                        String balanceStr2 = tokensBean.getBalanceStr();
                                                        if (balanceStr != null ? balanceStr.equals(balanceStr2) : balanceStr2 == null) {
                                                            String totalUsdCount = getTotalUsdCount();
                                                            String totalUsdCount2 = tokensBean.getTotalUsdCount();
                                                            if (totalUsdCount != null ? totalUsdCount.equals(totalUsdCount2) : totalUsdCount2 == null) {
                                                                String totalCnyCount = getTotalCnyCount();
                                                                String totalCnyCount2 = tokensBean.getTotalCnyCount();
                                                                if (totalCnyCount != null ? totalCnyCount.equals(totalCnyCount2) : totalCnyCount2 == null) {
                                                                    List<ProjectListBean> projectList = getProjectList();
                                                                    List<ProjectListBean> projectList2 = tokensBean.getProjectList();
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
                int tokenDecimal = ((((getTokenDecimal() + 59) * 59) + getTokenType()) * 59) + getBalance();
                String tokenAddress = getTokenAddress();
                int hashCode = (tokenDecimal * 59) + (tokenAddress == null ? 43 : tokenAddress.hashCode());
                String tokenSymbol = getTokenSymbol();
                int hashCode2 = (hashCode * 59) + (tokenSymbol == null ? 43 : tokenSymbol.hashCode());
                String tokenName = getTokenName();
                int hashCode3 = (hashCode2 * 59) + (tokenName == null ? 43 : tokenName.hashCode());
                String tokenLogo = getTokenLogo();
                int hashCode4 = (hashCode3 * 59) + (tokenLogo == null ? 43 : tokenLogo.hashCode());
                String tokenUsdPrice = getTokenUsdPrice();
                int hashCode5 = (hashCode4 * 59) + (tokenUsdPrice == null ? 43 : tokenUsdPrice.hashCode());
                String trc721Id = getTrc721Id();
                int hashCode6 = (hashCode5 * 59) + (trc721Id == null ? 43 : trc721Id.hashCode());
                String tokenCnyPrice = getTokenCnyPrice();
                int hashCode7 = (hashCode6 * 59) + (tokenCnyPrice == null ? 43 : tokenCnyPrice.hashCode());
                String totalAmount = getTotalAmount();
                int hashCode8 = (hashCode7 * 59) + (totalAmount == null ? 43 : totalAmount.hashCode());
                String balanceStr = getBalanceStr();
                int hashCode9 = (hashCode8 * 59) + (balanceStr == null ? 43 : balanceStr.hashCode());
                String totalUsdCount = getTotalUsdCount();
                int hashCode10 = (hashCode9 * 59) + (totalUsdCount == null ? 43 : totalUsdCount.hashCode());
                String totalCnyCount = getTotalCnyCount();
                int hashCode11 = (hashCode10 * 59) + (totalCnyCount == null ? 43 : totalCnyCount.hashCode());
                List<ProjectListBean> projectList = getProjectList();
                return (hashCode11 * 59) + (projectList != null ? projectList.hashCode() : 43);
            }

            public String toString() {
                return "ApproveListDatabeanOutput.ApproveListDatabean.TokensBean(tokenAddress=" + getTokenAddress() + ", tokenSymbol=" + getTokenSymbol() + ", tokenName=" + getTokenName() + ", tokenDecimal=" + getTokenDecimal() + ", tokenType=" + getTokenType() + ", tokenLogo=" + getTokenLogo() + ", tokenUsdPrice=" + getTokenUsdPrice() + ", trc721Id=" + getTrc721Id() + ", tokenCnyPrice=" + getTokenCnyPrice() + ", totalAmount=" + getTotalAmount() + ", balance=" + getBalance() + ", balanceStr=" + getBalanceStr() + ", totalUsdCount=" + getTotalUsdCount() + ", totalCnyCount=" + getTotalCnyCount() + ", projectList=" + getProjectList() + ")";
            }

            public static class ProjectListBean {
                @JsonProperty("amount")
                private String amount;
                @JsonProperty("approveAddressType")
                private String approveAddressType;
                @JsonProperty("cnyCount")
                private String cnyCount;
                @JsonProperty("logo")
                private String logo;
                @JsonProperty(AppMeasurementSdk.ConditionalUserProperty.NAME)
                private String name;
                @JsonProperty("operateTime")
                private Long operateTime;
                @JsonProperty("projectAddress")
                private String projectAddress;
                @JsonProperty("unlimited")
                private Boolean unlimited;
                @JsonProperty("usdCount")
                private String usdCount;

                public String getAmount() {
                    return this.amount;
                }

                public String getApproveAddressType() {
                    return this.approveAddressType;
                }

                public String getCnyCount() {
                    return this.cnyCount;
                }

                public String getLogo() {
                    return this.logo;
                }

                public String getName() {
                    return this.name;
                }

                public Long getOperateTime() {
                    return this.operateTime;
                }

                public String getProjectAddress() {
                    return this.projectAddress;
                }

                public Boolean getUnlimited() {
                    return this.unlimited;
                }

                public String getUsdCount() {
                    return this.usdCount;
                }

                @JsonProperty("amount")
                public void setAmount(String str) {
                    this.amount = str;
                }

                @JsonProperty("approveAddressType")
                public void setApproveAddressType(String str) {
                    this.approveAddressType = str;
                }

                @JsonProperty("cnyCount")
                public void setCnyCount(String str) {
                    this.cnyCount = str;
                }

                @JsonProperty("logo")
                public void setLogo(String str) {
                    this.logo = str;
                }

                @JsonProperty(AppMeasurementSdk.ConditionalUserProperty.NAME)
                public void setName(String str) {
                    this.name = str;
                }

                @JsonProperty("operateTime")
                public void setOperateTime(Long l) {
                    this.operateTime = l;
                }

                @JsonProperty("projectAddress")
                public void setProjectAddress(String str) {
                    this.projectAddress = str;
                }

                @JsonProperty("unlimited")
                public void setUnlimited(Boolean bool) {
                    this.unlimited = bool;
                }

                @JsonProperty("usdCount")
                public void setUsdCount(String str) {
                    this.usdCount = str;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ProjectListBean;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (obj instanceof ProjectListBean) {
                        ProjectListBean projectListBean = (ProjectListBean) obj;
                        if (projectListBean.canEqual(this)) {
                            Boolean unlimited = getUnlimited();
                            Boolean unlimited2 = projectListBean.getUnlimited();
                            if (unlimited != null ? unlimited.equals(unlimited2) : unlimited2 == null) {
                                Long operateTime = getOperateTime();
                                Long operateTime2 = projectListBean.getOperateTime();
                                if (operateTime != null ? operateTime.equals(operateTime2) : operateTime2 == null) {
                                    String name = getName();
                                    String name2 = projectListBean.getName();
                                    if (name != null ? name.equals(name2) : name2 == null) {
                                        String logo = getLogo();
                                        String logo2 = projectListBean.getLogo();
                                        if (logo != null ? logo.equals(logo2) : logo2 == null) {
                                            String amount = getAmount();
                                            String amount2 = projectListBean.getAmount();
                                            if (amount != null ? amount.equals(amount2) : amount2 == null) {
                                                String usdCount = getUsdCount();
                                                String usdCount2 = projectListBean.getUsdCount();
                                                if (usdCount != null ? usdCount.equals(usdCount2) : usdCount2 == null) {
                                                    String cnyCount = getCnyCount();
                                                    String cnyCount2 = projectListBean.getCnyCount();
                                                    if (cnyCount != null ? cnyCount.equals(cnyCount2) : cnyCount2 == null) {
                                                        String projectAddress = getProjectAddress();
                                                        String projectAddress2 = projectListBean.getProjectAddress();
                                                        if (projectAddress != null ? projectAddress.equals(projectAddress2) : projectAddress2 == null) {
                                                            String approveAddressType = getApproveAddressType();
                                                            String approveAddressType2 = projectListBean.getApproveAddressType();
                                                            return approveAddressType != null ? approveAddressType.equals(approveAddressType2) : approveAddressType2 == null;
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
                    Boolean unlimited = getUnlimited();
                    int hashCode = unlimited == null ? 43 : unlimited.hashCode();
                    Long operateTime = getOperateTime();
                    int hashCode2 = ((hashCode + 59) * 59) + (operateTime == null ? 43 : operateTime.hashCode());
                    String name = getName();
                    int hashCode3 = (hashCode2 * 59) + (name == null ? 43 : name.hashCode());
                    String logo = getLogo();
                    int hashCode4 = (hashCode3 * 59) + (logo == null ? 43 : logo.hashCode());
                    String amount = getAmount();
                    int hashCode5 = (hashCode4 * 59) + (amount == null ? 43 : amount.hashCode());
                    String usdCount = getUsdCount();
                    int hashCode6 = (hashCode5 * 59) + (usdCount == null ? 43 : usdCount.hashCode());
                    String cnyCount = getCnyCount();
                    int hashCode7 = (hashCode6 * 59) + (cnyCount == null ? 43 : cnyCount.hashCode());
                    String projectAddress = getProjectAddress();
                    int hashCode8 = (hashCode7 * 59) + (projectAddress == null ? 43 : projectAddress.hashCode());
                    String approveAddressType = getApproveAddressType();
                    return (hashCode8 * 59) + (approveAddressType != null ? approveAddressType.hashCode() : 43);
                }

                public String toString() {
                    return "ApproveListDatabeanOutput.ApproveListDatabean.TokensBean.ProjectListBean(name=" + getName() + ", logo=" + getLogo() + ", amount=" + getAmount() + ", usdCount=" + getUsdCount() + ", cnyCount=" + getCnyCount() + ", unlimited=" + getUnlimited() + ", projectAddress=" + getProjectAddress() + ", approveAddressType=" + getApproveAddressType() + ", operateTime=" + getOperateTime() + ")";
                }
            }
        }
    }
}
