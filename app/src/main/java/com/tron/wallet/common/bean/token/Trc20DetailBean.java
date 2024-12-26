package com.tron.wallet.common.bean.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.common.config.TronConfig;
import java.util.List;
public class Trc20DetailBean {
    private List<Trc10TokensBean> data;
    private int total;
    private List<Trc20TokensBean> trc20_tokens;

    public static class Trc20TokensBean {
        private String contract_address;
        private int decimals;
        private String gain;
        private String git_hub;
        private String home_page;
        private String icon_url;
        private int index;
        private String issue_address;
        private String issue_time;
        private int issue_ts;
        private String name;
        private String price;
        private int price_trx;
        private String social_media;
        private List<?> social_media_list;
        private String symbol;
        private String token_desc;
        private int total_supply;
        private String total_supply_str;
        private String total_supply_with_decimals;
        private int volume;
        private int volume24h;
        private String white_paper;

        public String getContract_address() {
            return this.contract_address;
        }

        public int getDecimals() {
            return this.decimals;
        }

        public String getGain() {
            return this.gain;
        }

        public String getGit_hub() {
            return this.git_hub;
        }

        public String getHome_page() {
            return this.home_page;
        }

        public String getIcon_url() {
            return this.icon_url;
        }

        public int getIndex() {
            return this.index;
        }

        public String getIssue_address() {
            return this.issue_address;
        }

        public String getIssue_time() {
            return this.issue_time;
        }

        public int getIssue_ts() {
            return this.issue_ts;
        }

        public String getName() {
            return this.name;
        }

        public String getPrice() {
            return this.price;
        }

        public int getPrice_trx() {
            return this.price_trx;
        }

        public String getSocial_media() {
            return this.social_media;
        }

        public List<?> getSocial_media_list() {
            return this.social_media_list;
        }

        public String getSymbol() {
            return this.symbol;
        }

        public String getToken_desc() {
            return this.token_desc;
        }

        public int getTotal_supply() {
            return this.total_supply;
        }

        public String getTotal_supply_str() {
            return this.total_supply_str;
        }

        public String getTotal_supply_with_decimals() {
            return this.total_supply_with_decimals;
        }

        public int getVolume() {
            return this.volume;
        }

        public int getVolume24h() {
            return this.volume24h;
        }

        public String getWhite_paper() {
            return this.white_paper;
        }

        public void setContract_address(String str) {
            this.contract_address = str;
        }

        public void setDecimals(int i) {
            this.decimals = i;
        }

        public void setGain(String str) {
            this.gain = str;
        }

        public void setGit_hub(String str) {
            this.git_hub = str;
        }

        public void setHome_page(String str) {
            this.home_page = str;
        }

        public void setIcon_url(String str) {
            this.icon_url = str;
        }

        public void setIndex(int i) {
            this.index = i;
        }

        public void setIssue_address(String str) {
            this.issue_address = str;
        }

        public void setIssue_time(String str) {
            this.issue_time = str;
        }

        public void setIssue_ts(int i) {
            this.issue_ts = i;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setPrice(String str) {
            this.price = str;
        }

        public void setPrice_trx(int i) {
            this.price_trx = i;
        }

        public void setSocial_media(String str) {
            this.social_media = str;
        }

        public void setSocial_media_list(List<?> list) {
            this.social_media_list = list;
        }

        public void setSymbol(String str) {
            this.symbol = str;
        }

        public void setToken_desc(String str) {
            this.token_desc = str;
        }

        public void setTotal_supply(int i) {
            this.total_supply = i;
        }

        public void setTotal_supply_str(String str) {
            this.total_supply_str = str;
        }

        public void setTotal_supply_with_decimals(String str) {
            this.total_supply_with_decimals = str;
        }

        public void setVolume(int i) {
            this.volume = i;
        }

        public void setVolume24h(int i) {
            this.volume24h = i;
        }

        public void setWhite_paper(String str) {
            this.white_paper = str;
        }
    }

    public List<Trc10TokensBean> getData() {
        return this.data;
    }

    public int getTotal() {
        return this.total;
    }

    public List<Trc20TokensBean> getTrc20_tokens() {
        return this.trc20_tokens;
    }

    public void setData(List<Trc10TokensBean> list) {
        this.data = list;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public void setTrc20_tokens(List<Trc20TokensBean> list) {
        this.trc20_tokens = list;
    }

    public static class Trc10TokensBean {
        @JsonProperty("abbr")
        private String abbr;
        @JsonProperty("available")
        private Integer available;
        @JsonProperty("canShow")
        private Integer canShow;
        @JsonProperty("country")
        private String country;
        @JsonProperty("dateCreated")
        private Long dateCreated;
        @JsonProperty("description")
        private String description;
        @JsonProperty("endTime")
        private Long endTime;
        @JsonProperty("frozen")
        private List<?> frozen;
        @JsonProperty("frozenPercentage")
        private Integer frozenPercentage;
        @JsonProperty("frozenTotal")
        private Integer frozenTotal;
        @JsonProperty("github")
        private String github;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("imgUrl")
        private String imgUrl;
        @JsonProperty("index")
        private Integer index;
        @JsonProperty("isBlack")
        private Boolean isBlack;
        @JsonProperty("issued")
        private Double issued;
        @JsonProperty("issuedPercentage")
        private Double issuedPercentage;
        @JsonProperty(FirebaseAnalytics.Param.LEVEL)
        private String level;
        @JsonProperty("market_info")
        private MarketInfoDTO marketInfo;
        @JsonProperty(AppMeasurementSdk.ConditionalUserProperty.NAME)
        private String name;
        @JsonProperty("new_social_media")
        private String newSocialMedia;
        @JsonProperty("nrOfTokenHolders")
        private Integer nrOfTokenHolders;
        @JsonProperty("num")
        private Integer num;
        @JsonProperty("ownerAddress")
        private String ownerAddress;
        @JsonProperty("pairUrl")
        private String pairUrl;
        @JsonProperty("participated")
        private String participated;
        @JsonProperty("percentage")
        private Integer percentage;
        @JsonProperty(TronConfig.PRECISION)
        private Integer precision;
        @JsonProperty(FirebaseAnalytics.Param.PRICE)
        private Integer price;
        @JsonProperty("rank_order")
        private Integer rankOrder;
        @JsonProperty("remainingPercentage")
        private Integer remainingPercentage;
        @JsonProperty("social_media")
        private List<SocialMediaDTO> socialMedia;
        @JsonProperty("startTime")
        private Long startTime;
        @JsonProperty("tokenID")
        private Integer tokenID;
        @JsonProperty("tokenPriceLine")
        private TokenPriceLineDTO tokenPriceLine;
        @JsonProperty(CustomTokenStatus.TOTAL_SUPPLY)
        private Double totalSupply;
        @JsonProperty("totalTransactions")
        private Integer totalTransactions;
        @JsonProperty("totalTurnOver")
        private Double totalTurnOver;
        @JsonProperty("transfer24h")
        private Integer transfer24h;
        @JsonProperty("transfer24h_rate")
        private Double transfer24hRate;
        @JsonProperty("url")
        private String url;
        @JsonProperty("vip")
        private Boolean vip;
        @JsonProperty("voteScore")
        private Integer voteScore;
        @JsonProperty("website")
        private String website;
        @JsonProperty("white_paper")
        private String whitePaper;

        public String getAbbr() {
            return this.abbr;
        }

        public Integer getAvailable() {
            return this.available;
        }

        public Boolean getBlack() {
            return this.isBlack;
        }

        public Integer getCanShow() {
            return this.canShow;
        }

        public String getCountry() {
            return this.country;
        }

        public Long getDateCreated() {
            return this.dateCreated;
        }

        public String getDescription() {
            return this.description;
        }

        public Long getEndTime() {
            return this.endTime;
        }

        public List<?> getFrozen() {
            return this.frozen;
        }

        public Integer getFrozenPercentage() {
            return this.frozenPercentage;
        }

        public Integer getFrozenTotal() {
            return this.frozenTotal;
        }

        public String getGithub() {
            return this.github;
        }

        public Integer getId() {
            return this.id;
        }

        public String getImgUrl() {
            return this.imgUrl;
        }

        public Integer getIndex() {
            return this.index;
        }

        public Boolean getIsBlack() {
            return this.isBlack;
        }

        public Double getIssued() {
            return this.issued;
        }

        public Double getIssuedPercentage() {
            return this.issuedPercentage;
        }

        public String getLevel() {
            return this.level;
        }

        public MarketInfoDTO getMarketInfo() {
            return this.marketInfo;
        }

        public String getName() {
            return this.name;
        }

        public String getNewSocialMedia() {
            return this.newSocialMedia;
        }

        public Integer getNrOfTokenHolders() {
            return this.nrOfTokenHolders;
        }

        public Integer getNum() {
            return this.num;
        }

        public String getOwnerAddress() {
            return this.ownerAddress;
        }

        public String getPairUrl() {
            return this.pairUrl;
        }

        public String getParticipated() {
            return this.participated;
        }

        public Integer getPercentage() {
            return this.percentage;
        }

        public Integer getPrecision() {
            return this.precision;
        }

        public Integer getPrice() {
            return this.price;
        }

        public Integer getRankOrder() {
            return this.rankOrder;
        }

        public Integer getRemainingPercentage() {
            return this.remainingPercentage;
        }

        public List<SocialMediaDTO> getSocialMedia() {
            return this.socialMedia;
        }

        public Long getStartTime() {
            return this.startTime;
        }

        public Integer getTokenID() {
            return this.tokenID;
        }

        public TokenPriceLineDTO getTokenPriceLine() {
            return this.tokenPriceLine;
        }

        public Double getTotalSupply() {
            return this.totalSupply;
        }

        public Integer getTotalTransactions() {
            return this.totalTransactions;
        }

        public Double getTotalTurnOver() {
            return this.totalTurnOver;
        }

        public Integer getTransfer24h() {
            return this.transfer24h;
        }

        public Double getTransfer24hRate() {
            return this.transfer24hRate;
        }

        public String getUrl() {
            return this.url;
        }

        public Boolean getVip() {
            return this.vip;
        }

        public Integer getVoteScore() {
            return this.voteScore;
        }

        public String getWebsite() {
            return this.website;
        }

        public String getWhitePaper() {
            return this.whitePaper;
        }

        public void setAbbr(String str) {
            this.abbr = str;
        }

        public void setAvailable(Integer num) {
            this.available = num;
        }

        public void setBlack(Boolean bool) {
            this.isBlack = bool;
        }

        public void setCanShow(Integer num) {
            this.canShow = num;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public void setDateCreated(Long l) {
            this.dateCreated = l;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public void setEndTime(Long l) {
            this.endTime = l;
        }

        public void setFrozen(List<?> list) {
            this.frozen = list;
        }

        public void setFrozenPercentage(Integer num) {
            this.frozenPercentage = num;
        }

        public void setFrozenTotal(Integer num) {
            this.frozenTotal = num;
        }

        public void setGithub(String str) {
            this.github = str;
        }

        public void setId(Integer num) {
            this.id = num;
        }

        public void setImgUrl(String str) {
            this.imgUrl = str;
        }

        public void setIndex(Integer num) {
            this.index = num;
        }

        @JsonProperty("isBlack")
        public void setIsBlack(Boolean bool) {
            this.isBlack = bool;
        }

        public void setIssued(Double d) {
            this.issued = d;
        }

        public void setIssuedPercentage(Double d) {
            this.issuedPercentage = d;
        }

        public void setLevel(String str) {
            this.level = str;
        }

        public void setMarketInfo(MarketInfoDTO marketInfoDTO) {
            this.marketInfo = marketInfoDTO;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setNewSocialMedia(String str) {
            this.newSocialMedia = str;
        }

        public void setNrOfTokenHolders(Integer num) {
            this.nrOfTokenHolders = num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public void setOwnerAddress(String str) {
            this.ownerAddress = str;
        }

        public void setPairUrl(String str) {
            this.pairUrl = str;
        }

        public void setParticipated(String str) {
            this.participated = str;
        }

        public void setPercentage(Integer num) {
            this.percentage = num;
        }

        public void setPrecision(Integer num) {
            this.precision = num;
        }

        public void setPrice(Integer num) {
            this.price = num;
        }

        public void setRankOrder(Integer num) {
            this.rankOrder = num;
        }

        public void setRemainingPercentage(Integer num) {
            this.remainingPercentage = num;
        }

        public void setSocialMedia(List<SocialMediaDTO> list) {
            this.socialMedia = list;
        }

        public void setStartTime(Long l) {
            this.startTime = l;
        }

        public void setTokenID(Integer num) {
            this.tokenID = num;
        }

        public void setTokenPriceLine(TokenPriceLineDTO tokenPriceLineDTO) {
            this.tokenPriceLine = tokenPriceLineDTO;
        }

        public void setTotalSupply(Double d) {
            this.totalSupply = d;
        }

        public void setTotalTransactions(Integer num) {
            this.totalTransactions = num;
        }

        public void setTotalTurnOver(Double d) {
            this.totalTurnOver = d;
        }

        public void setTransfer24h(Integer num) {
            this.transfer24h = num;
        }

        public void setTransfer24hRate(Double d) {
            this.transfer24hRate = d;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public void setVip(Boolean bool) {
            this.vip = bool;
        }

        public void setVoteScore(Integer num) {
            this.voteScore = num;
        }

        public void setWebsite(String str) {
            this.website = str;
        }

        public void setWhitePaper(String str) {
            this.whitePaper = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof Trc10TokensBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Trc10TokensBean) {
                Trc10TokensBean trc10TokensBean = (Trc10TokensBean) obj;
                if (trc10TokensBean.canEqual(this)) {
                    Integer totalTransactions = getTotalTransactions();
                    Integer totalTransactions2 = trc10TokensBean.getTotalTransactions();
                    if (totalTransactions != null ? totalTransactions.equals(totalTransactions2) : totalTransactions2 == null) {
                        Integer tokenID = getTokenID();
                        Integer tokenID2 = trc10TokensBean.getTokenID();
                        if (tokenID != null ? tokenID.equals(tokenID2) : tokenID2 == null) {
                            Double totalTurnOver = getTotalTurnOver();
                            Double totalTurnOver2 = trc10TokensBean.getTotalTurnOver();
                            if (totalTurnOver != null ? totalTurnOver.equals(totalTurnOver2) : totalTurnOver2 == null) {
                                Integer precision = getPrecision();
                                Integer precision2 = trc10TokensBean.getPrecision();
                                if (precision != null ? precision.equals(precision2) : precision2 == null) {
                                    Integer num = getNum();
                                    Integer num2 = trc10TokensBean.getNum();
                                    if (num != null ? num.equals(num2) : num2 == null) {
                                        Integer available = getAvailable();
                                        Integer available2 = trc10TokensBean.getAvailable();
                                        if (available != null ? available.equals(available2) : available2 == null) {
                                            Double issuedPercentage = getIssuedPercentage();
                                            Double issuedPercentage2 = trc10TokensBean.getIssuedPercentage();
                                            if (issuedPercentage != null ? issuedPercentage.equals(issuedPercentage2) : issuedPercentage2 == null) {
                                                Integer nrOfTokenHolders = getNrOfTokenHolders();
                                                Integer nrOfTokenHolders2 = trc10TokensBean.getNrOfTokenHolders();
                                                if (nrOfTokenHolders != null ? nrOfTokenHolders.equals(nrOfTokenHolders2) : nrOfTokenHolders2 == null) {
                                                    Integer voteScore = getVoteScore();
                                                    Integer voteScore2 = trc10TokensBean.getVoteScore();
                                                    if (voteScore != null ? voteScore.equals(voteScore2) : voteScore2 == null) {
                                                        Long dateCreated = getDateCreated();
                                                        Long dateCreated2 = trc10TokensBean.getDateCreated();
                                                        if (dateCreated != null ? dateCreated.equals(dateCreated2) : dateCreated2 == null) {
                                                            Integer price = getPrice();
                                                            Integer price2 = trc10TokensBean.getPrice();
                                                            if (price != null ? price.equals(price2) : price2 == null) {
                                                                Integer percentage = getPercentage();
                                                                Integer percentage2 = trc10TokensBean.getPercentage();
                                                                if (percentage != null ? percentage.equals(percentage2) : percentage2 == null) {
                                                                    Integer transfer24h = getTransfer24h();
                                                                    Integer transfer24h2 = trc10TokensBean.getTransfer24h();
                                                                    if (transfer24h != null ? transfer24h.equals(transfer24h2) : transfer24h2 == null) {
                                                                        Long startTime = getStartTime();
                                                                        Long startTime2 = trc10TokensBean.getStartTime();
                                                                        if (startTime != null ? startTime.equals(startTime2) : startTime2 == null) {
                                                                            Integer id = getId();
                                                                            Integer id2 = trc10TokensBean.getId();
                                                                            if (id != null ? id.equals(id2) : id2 == null) {
                                                                                Double issued = getIssued();
                                                                                Double issued2 = trc10TokensBean.getIssued();
                                                                                if (issued != null ? issued.equals(issued2) : issued2 == null) {
                                                                                    Boolean vip = getVip();
                                                                                    Boolean vip2 = trc10TokensBean.getVip();
                                                                                    if (vip != null ? vip.equals(vip2) : vip2 == null) {
                                                                                        Double totalSupply = getTotalSupply();
                                                                                        Double totalSupply2 = trc10TokensBean.getTotalSupply();
                                                                                        if (totalSupply != null ? totalSupply.equals(totalSupply2) : totalSupply2 == null) {
                                                                                            Integer frozenTotal = getFrozenTotal();
                                                                                            Integer frozenTotal2 = trc10TokensBean.getFrozenTotal();
                                                                                            if (frozenTotal != null ? frozenTotal.equals(frozenTotal2) : frozenTotal2 == null) {
                                                                                                Integer canShow = getCanShow();
                                                                                                Integer canShow2 = trc10TokensBean.getCanShow();
                                                                                                if (canShow != null ? canShow.equals(canShow2) : canShow2 == null) {
                                                                                                    Integer index = getIndex();
                                                                                                    Integer index2 = trc10TokensBean.getIndex();
                                                                                                    if (index != null ? index.equals(index2) : index2 == null) {
                                                                                                        Double transfer24hRate = getTransfer24hRate();
                                                                                                        Double transfer24hRate2 = trc10TokensBean.getTransfer24hRate();
                                                                                                        if (transfer24hRate != null ? transfer24hRate.equals(transfer24hRate2) : transfer24hRate2 == null) {
                                                                                                            Integer frozenPercentage = getFrozenPercentage();
                                                                                                            Integer frozenPercentage2 = trc10TokensBean.getFrozenPercentage();
                                                                                                            if (frozenPercentage != null ? frozenPercentage.equals(frozenPercentage2) : frozenPercentage2 == null) {
                                                                                                                Boolean isBlack = getIsBlack();
                                                                                                                Boolean isBlack2 = trc10TokensBean.getIsBlack();
                                                                                                                if (isBlack != null ? isBlack.equals(isBlack2) : isBlack2 == null) {
                                                                                                                    Integer rankOrder = getRankOrder();
                                                                                                                    Integer rankOrder2 = trc10TokensBean.getRankOrder();
                                                                                                                    if (rankOrder != null ? rankOrder.equals(rankOrder2) : rankOrder2 == null) {
                                                                                                                        Integer remainingPercentage = getRemainingPercentage();
                                                                                                                        Integer remainingPercentage2 = trc10TokensBean.getRemainingPercentage();
                                                                                                                        if (remainingPercentage != null ? remainingPercentage.equals(remainingPercentage2) : remainingPercentage2 == null) {
                                                                                                                            Long endTime = getEndTime();
                                                                                                                            Long endTime2 = trc10TokensBean.getEndTime();
                                                                                                                            if (endTime != null ? endTime.equals(endTime2) : endTime2 == null) {
                                                                                                                                String country = getCountry();
                                                                                                                                String country2 = trc10TokensBean.getCountry();
                                                                                                                                if (country != null ? country.equals(country2) : country2 == null) {
                                                                                                                                    String participated = getParticipated();
                                                                                                                                    String participated2 = trc10TokensBean.getParticipated();
                                                                                                                                    if (participated != null ? participated.equals(participated2) : participated2 == null) {
                                                                                                                                        String description = getDescription();
                                                                                                                                        String description2 = trc10TokensBean.getDescription();
                                                                                                                                        if (description != null ? description.equals(description2) : description2 == null) {
                                                                                                                                            String pairUrl = getPairUrl();
                                                                                                                                            String pairUrl2 = trc10TokensBean.getPairUrl();
                                                                                                                                            if (pairUrl != null ? pairUrl.equals(pairUrl2) : pairUrl2 == null) {
                                                                                                                                                String abbr = getAbbr();
                                                                                                                                                String abbr2 = trc10TokensBean.getAbbr();
                                                                                                                                                if (abbr != null ? abbr.equals(abbr2) : abbr2 == null) {
                                                                                                                                                    String website = getWebsite();
                                                                                                                                                    String website2 = trc10TokensBean.getWebsite();
                                                                                                                                                    if (website != null ? website.equals(website2) : website2 == null) {
                                                                                                                                                        String github = getGithub();
                                                                                                                                                        String github2 = trc10TokensBean.getGithub();
                                                                                                                                                        if (github != null ? github.equals(github2) : github2 == null) {
                                                                                                                                                            String level = getLevel();
                                                                                                                                                            String level2 = trc10TokensBean.getLevel();
                                                                                                                                                            if (level != null ? level.equals(level2) : level2 == null) {
                                                                                                                                                                List<?> frozen = getFrozen();
                                                                                                                                                                List<?> frozen2 = trc10TokensBean.getFrozen();
                                                                                                                                                                if (frozen != null ? frozen.equals(frozen2) : frozen2 == null) {
                                                                                                                                                                    String url = getUrl();
                                                                                                                                                                    String url2 = trc10TokensBean.getUrl();
                                                                                                                                                                    if (url != null ? url.equals(url2) : url2 == null) {
                                                                                                                                                                        MarketInfoDTO marketInfo = getMarketInfo();
                                                                                                                                                                        MarketInfoDTO marketInfo2 = trc10TokensBean.getMarketInfo();
                                                                                                                                                                        if (marketInfo != null ? marketInfo.equals(marketInfo2) : marketInfo2 == null) {
                                                                                                                                                                            String imgUrl = getImgUrl();
                                                                                                                                                                            String imgUrl2 = trc10TokensBean.getImgUrl();
                                                                                                                                                                            if (imgUrl != null ? imgUrl.equals(imgUrl2) : imgUrl2 == null) {
                                                                                                                                                                                String newSocialMedia = getNewSocialMedia();
                                                                                                                                                                                String newSocialMedia2 = trc10TokensBean.getNewSocialMedia();
                                                                                                                                                                                if (newSocialMedia != null ? newSocialMedia.equals(newSocialMedia2) : newSocialMedia2 == null) {
                                                                                                                                                                                    TokenPriceLineDTO tokenPriceLine = getTokenPriceLine();
                                                                                                                                                                                    TokenPriceLineDTO tokenPriceLine2 = trc10TokensBean.getTokenPriceLine();
                                                                                                                                                                                    if (tokenPriceLine != null ? tokenPriceLine.equals(tokenPriceLine2) : tokenPriceLine2 == null) {
                                                                                                                                                                                        String name = getName();
                                                                                                                                                                                        String name2 = trc10TokensBean.getName();
                                                                                                                                                                                        if (name != null ? name.equals(name2) : name2 == null) {
                                                                                                                                                                                            String ownerAddress = getOwnerAddress();
                                                                                                                                                                                            String ownerAddress2 = trc10TokensBean.getOwnerAddress();
                                                                                                                                                                                            if (ownerAddress != null ? ownerAddress.equals(ownerAddress2) : ownerAddress2 == null) {
                                                                                                                                                                                                String whitePaper = getWhitePaper();
                                                                                                                                                                                                String whitePaper2 = trc10TokensBean.getWhitePaper();
                                                                                                                                                                                                if (whitePaper != null ? whitePaper.equals(whitePaper2) : whitePaper2 == null) {
                                                                                                                                                                                                    List<SocialMediaDTO> socialMedia = getSocialMedia();
                                                                                                                                                                                                    List<SocialMediaDTO> socialMedia2 = trc10TokensBean.getSocialMedia();
                                                                                                                                                                                                    return socialMedia != null ? socialMedia.equals(socialMedia2) : socialMedia2 == null;
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
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            Integer totalTransactions = getTotalTransactions();
            int hashCode = totalTransactions == null ? 43 : totalTransactions.hashCode();
            Integer tokenID = getTokenID();
            int hashCode2 = ((hashCode + 59) * 59) + (tokenID == null ? 43 : tokenID.hashCode());
            Double totalTurnOver = getTotalTurnOver();
            int hashCode3 = (hashCode2 * 59) + (totalTurnOver == null ? 43 : totalTurnOver.hashCode());
            Integer precision = getPrecision();
            int hashCode4 = (hashCode3 * 59) + (precision == null ? 43 : precision.hashCode());
            Integer num = getNum();
            int hashCode5 = (hashCode4 * 59) + (num == null ? 43 : num.hashCode());
            Integer available = getAvailable();
            int hashCode6 = (hashCode5 * 59) + (available == null ? 43 : available.hashCode());
            Double issuedPercentage = getIssuedPercentage();
            int hashCode7 = (hashCode6 * 59) + (issuedPercentage == null ? 43 : issuedPercentage.hashCode());
            Integer nrOfTokenHolders = getNrOfTokenHolders();
            int hashCode8 = (hashCode7 * 59) + (nrOfTokenHolders == null ? 43 : nrOfTokenHolders.hashCode());
            Integer voteScore = getVoteScore();
            int hashCode9 = (hashCode8 * 59) + (voteScore == null ? 43 : voteScore.hashCode());
            Long dateCreated = getDateCreated();
            int hashCode10 = (hashCode9 * 59) + (dateCreated == null ? 43 : dateCreated.hashCode());
            Integer price = getPrice();
            int hashCode11 = (hashCode10 * 59) + (price == null ? 43 : price.hashCode());
            Integer percentage = getPercentage();
            int hashCode12 = (hashCode11 * 59) + (percentage == null ? 43 : percentage.hashCode());
            Integer transfer24h = getTransfer24h();
            int hashCode13 = (hashCode12 * 59) + (transfer24h == null ? 43 : transfer24h.hashCode());
            Long startTime = getStartTime();
            int hashCode14 = (hashCode13 * 59) + (startTime == null ? 43 : startTime.hashCode());
            Integer id = getId();
            int hashCode15 = (hashCode14 * 59) + (id == null ? 43 : id.hashCode());
            Double issued = getIssued();
            int hashCode16 = (hashCode15 * 59) + (issued == null ? 43 : issued.hashCode());
            Boolean vip = getVip();
            int hashCode17 = (hashCode16 * 59) + (vip == null ? 43 : vip.hashCode());
            Double totalSupply = getTotalSupply();
            int hashCode18 = (hashCode17 * 59) + (totalSupply == null ? 43 : totalSupply.hashCode());
            Integer frozenTotal = getFrozenTotal();
            int hashCode19 = (hashCode18 * 59) + (frozenTotal == null ? 43 : frozenTotal.hashCode());
            Integer canShow = getCanShow();
            int hashCode20 = (hashCode19 * 59) + (canShow == null ? 43 : canShow.hashCode());
            Integer index = getIndex();
            int hashCode21 = (hashCode20 * 59) + (index == null ? 43 : index.hashCode());
            Double transfer24hRate = getTransfer24hRate();
            int hashCode22 = (hashCode21 * 59) + (transfer24hRate == null ? 43 : transfer24hRate.hashCode());
            Integer frozenPercentage = getFrozenPercentage();
            int hashCode23 = (hashCode22 * 59) + (frozenPercentage == null ? 43 : frozenPercentage.hashCode());
            Boolean isBlack = getIsBlack();
            int hashCode24 = (hashCode23 * 59) + (isBlack == null ? 43 : isBlack.hashCode());
            Integer rankOrder = getRankOrder();
            int hashCode25 = (hashCode24 * 59) + (rankOrder == null ? 43 : rankOrder.hashCode());
            Integer remainingPercentage = getRemainingPercentage();
            int hashCode26 = (hashCode25 * 59) + (remainingPercentage == null ? 43 : remainingPercentage.hashCode());
            Long endTime = getEndTime();
            int hashCode27 = (hashCode26 * 59) + (endTime == null ? 43 : endTime.hashCode());
            String country = getCountry();
            int hashCode28 = (hashCode27 * 59) + (country == null ? 43 : country.hashCode());
            String participated = getParticipated();
            int hashCode29 = (hashCode28 * 59) + (participated == null ? 43 : participated.hashCode());
            String description = getDescription();
            int hashCode30 = (hashCode29 * 59) + (description == null ? 43 : description.hashCode());
            String pairUrl = getPairUrl();
            int hashCode31 = (hashCode30 * 59) + (pairUrl == null ? 43 : pairUrl.hashCode());
            String abbr = getAbbr();
            int hashCode32 = (hashCode31 * 59) + (abbr == null ? 43 : abbr.hashCode());
            String website = getWebsite();
            int hashCode33 = (hashCode32 * 59) + (website == null ? 43 : website.hashCode());
            String github = getGithub();
            int hashCode34 = (hashCode33 * 59) + (github == null ? 43 : github.hashCode());
            String level = getLevel();
            int hashCode35 = (hashCode34 * 59) + (level == null ? 43 : level.hashCode());
            List<?> frozen = getFrozen();
            int hashCode36 = (hashCode35 * 59) + (frozen == null ? 43 : frozen.hashCode());
            String url = getUrl();
            int hashCode37 = (hashCode36 * 59) + (url == null ? 43 : url.hashCode());
            MarketInfoDTO marketInfo = getMarketInfo();
            int hashCode38 = (hashCode37 * 59) + (marketInfo == null ? 43 : marketInfo.hashCode());
            String imgUrl = getImgUrl();
            int hashCode39 = (hashCode38 * 59) + (imgUrl == null ? 43 : imgUrl.hashCode());
            String newSocialMedia = getNewSocialMedia();
            int hashCode40 = (hashCode39 * 59) + (newSocialMedia == null ? 43 : newSocialMedia.hashCode());
            TokenPriceLineDTO tokenPriceLine = getTokenPriceLine();
            int hashCode41 = (hashCode40 * 59) + (tokenPriceLine == null ? 43 : tokenPriceLine.hashCode());
            String name = getName();
            int hashCode42 = (hashCode41 * 59) + (name == null ? 43 : name.hashCode());
            String ownerAddress = getOwnerAddress();
            int hashCode43 = (hashCode42 * 59) + (ownerAddress == null ? 43 : ownerAddress.hashCode());
            String whitePaper = getWhitePaper();
            int hashCode44 = (hashCode43 * 59) + (whitePaper == null ? 43 : whitePaper.hashCode());
            List<SocialMediaDTO> socialMedia = getSocialMedia();
            return (hashCode44 * 59) + (socialMedia != null ? socialMedia.hashCode() : 43);
        }

        public String toString() {
            return "Trc20DetailBean.Trc10TokensBean(country=" + getCountry() + ", totalTransactions=" + getTotalTransactions() + ", tokenID=" + getTokenID() + ", totalTurnOver=" + getTotalTurnOver() + ", participated=" + getParticipated() + ", precision=" + getPrecision() + ", num=" + getNum() + ", available=" + getAvailable() + ", description=" + getDescription() + ", pairUrl=" + getPairUrl() + ", issuedPercentage=" + getIssuedPercentage() + ", nrOfTokenHolders=" + getNrOfTokenHolders() + ", voteScore=" + getVoteScore() + ", dateCreated=" + getDateCreated() + ", price=" + getPrice() + ", percentage=" + getPercentage() + ", transfer24h=" + getTransfer24h() + ", startTime=" + getStartTime() + ", id=" + getId() + ", issued=" + getIssued() + ", abbr=" + getAbbr() + ", vip=" + getVip() + ", website=" + getWebsite() + ", github=" + getGithub() + ", level=" + getLevel() + ", totalSupply=" + getTotalSupply() + ", frozenTotal=" + getFrozenTotal() + ", frozen=" + getFrozen() + ", canShow=" + getCanShow() + ", index=" + getIndex() + ", url=" + getUrl() + ", transfer24hRate=" + getTransfer24hRate() + ", marketInfo=" + getMarketInfo() + ", frozenPercentage=" + getFrozenPercentage() + ", imgUrl=" + getImgUrl() + ", isBlack=" + getIsBlack() + ", newSocialMedia=" + getNewSocialMedia() + ", tokenPriceLine=" + getTokenPriceLine() + ", rankOrder=" + getRankOrder() + ", remainingPercentage=" + getRemainingPercentage() + ", name=" + getName() + ", ownerAddress=" + getOwnerAddress() + ", endTime=" + getEndTime() + ", whitePaper=" + getWhitePaper() + ", socialMedia=" + getSocialMedia() + ")";
        }

        public static class MarketInfoDTO {
            @JsonProperty("fPrecision")
            private Integer fPrecision;
            @JsonProperty("fShortName")
            private String fShortName;
            @JsonProperty("fTokenAddr")
            private String fTokenAddr;
            @JsonProperty("filter")
            private Boolean filter;
            @JsonProperty("gain")
            private Double gain;
            @JsonProperty("marketCap24hGain")
            private Double marketCap24hGain;
            @JsonProperty("pairUrl")
            private String pairUrl;
            @JsonProperty("priceFrom")
            private String priceFrom;
            @JsonProperty("priceInTrx")
            private Double priceInTrx;
            @JsonProperty("priceInUsd")
            private Double priceInUsd;
            @JsonProperty("sPrecision")
            private Integer sPrecision;
            @JsonProperty("sShortName")
            private String sShortName;
            @JsonProperty("sTokenAddr")
            private String sTokenAddr;
            @JsonProperty("volume24hGain")
            private Double volume24hGain;
            @JsonProperty("volume24hInTrx")
            private Double volume24hInTrx;

            public Boolean getFilter() {
                return this.filter;
            }

            public Double getGain() {
                return this.gain;
            }

            public Double getMarketCap24hGain() {
                return this.marketCap24hGain;
            }

            public String getPairUrl() {
                return this.pairUrl;
            }

            public String getPriceFrom() {
                return this.priceFrom;
            }

            public Double getPriceInTrx() {
                return this.priceInTrx;
            }

            public Double getPriceInUsd() {
                return this.priceInUsd;
            }

            public Double getVolume24hGain() {
                return this.volume24hGain;
            }

            public Double getVolume24hInTrx() {
                return this.volume24hInTrx;
            }

            public Integer getfPrecision() {
                return this.fPrecision;
            }

            public String getfShortName() {
                return this.fShortName;
            }

            public String getfTokenAddr() {
                return this.fTokenAddr;
            }

            public Integer getsPrecision() {
                return this.sPrecision;
            }

            public String getsShortName() {
                return this.sShortName;
            }

            public String getsTokenAddr() {
                return this.sTokenAddr;
            }

            public void setFilter(Boolean bool) {
                this.filter = bool;
            }

            public void setGain(Double d) {
                this.gain = d;
            }

            public void setMarketCap24hGain(Double d) {
                this.marketCap24hGain = d;
            }

            public void setPairUrl(String str) {
                this.pairUrl = str;
            }

            public void setPriceFrom(String str) {
                this.priceFrom = str;
            }

            public void setPriceInTrx(Double d) {
                this.priceInTrx = d;
            }

            public void setPriceInUsd(Double d) {
                this.priceInUsd = d;
            }

            public void setVolume24hGain(Double d) {
                this.volume24hGain = d;
            }

            public void setVolume24hInTrx(Double d) {
                this.volume24hInTrx = d;
            }

            public void setfPrecision(Integer num) {
                this.fPrecision = num;
            }

            public void setfShortName(String str) {
                this.fShortName = str;
            }

            public void setfTokenAddr(String str) {
                this.fTokenAddr = str;
            }

            public void setsPrecision(Integer num) {
                this.sPrecision = num;
            }

            public void setsShortName(String str) {
                this.sShortName = str;
            }

            public void setsTokenAddr(String str) {
                this.sTokenAddr = str;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof MarketInfoDTO;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof MarketInfoDTO) {
                    MarketInfoDTO marketInfoDTO = (MarketInfoDTO) obj;
                    if (marketInfoDTO.canEqual(this)) {
                        Integer num = getsPrecision();
                        Integer num2 = marketInfoDTO.getsPrecision();
                        if (num != null ? num.equals(num2) : num2 == null) {
                            Double priceInUsd = getPriceInUsd();
                            Double priceInUsd2 = marketInfoDTO.getPriceInUsd();
                            if (priceInUsd != null ? priceInUsd.equals(priceInUsd2) : priceInUsd2 == null) {
                                Double volume24hGain = getVolume24hGain();
                                Double volume24hGain2 = marketInfoDTO.getVolume24hGain();
                                if (volume24hGain != null ? volume24hGain.equals(volume24hGain2) : volume24hGain2 == null) {
                                    Double gain = getGain();
                                    Double gain2 = marketInfoDTO.getGain();
                                    if (gain != null ? gain.equals(gain2) : gain2 == null) {
                                        Boolean filter = getFilter();
                                        Boolean filter2 = marketInfoDTO.getFilter();
                                        if (filter != null ? filter.equals(filter2) : filter2 == null) {
                                            Integer num3 = getfPrecision();
                                            Integer num4 = marketInfoDTO.getfPrecision();
                                            if (num3 != null ? num3.equals(num4) : num4 == null) {
                                                Double marketCap24hGain = getMarketCap24hGain();
                                                Double marketCap24hGain2 = marketInfoDTO.getMarketCap24hGain();
                                                if (marketCap24hGain != null ? marketCap24hGain.equals(marketCap24hGain2) : marketCap24hGain2 == null) {
                                                    Double priceInTrx = getPriceInTrx();
                                                    Double priceInTrx2 = marketInfoDTO.getPriceInTrx();
                                                    if (priceInTrx != null ? priceInTrx.equals(priceInTrx2) : priceInTrx2 == null) {
                                                        Double volume24hInTrx = getVolume24hInTrx();
                                                        Double volume24hInTrx2 = marketInfoDTO.getVolume24hInTrx();
                                                        if (volume24hInTrx != null ? volume24hInTrx.equals(volume24hInTrx2) : volume24hInTrx2 == null) {
                                                            String str = getsTokenAddr();
                                                            String str2 = marketInfoDTO.getsTokenAddr();
                                                            if (str != null ? str.equals(str2) : str2 == null) {
                                                                String pairUrl = getPairUrl();
                                                                String pairUrl2 = marketInfoDTO.getPairUrl();
                                                                if (pairUrl != null ? pairUrl.equals(pairUrl2) : pairUrl2 == null) {
                                                                    String str3 = getfShortName();
                                                                    String str4 = marketInfoDTO.getfShortName();
                                                                    if (str3 != null ? str3.equals(str4) : str4 == null) {
                                                                        String priceFrom = getPriceFrom();
                                                                        String priceFrom2 = marketInfoDTO.getPriceFrom();
                                                                        if (priceFrom != null ? priceFrom.equals(priceFrom2) : priceFrom2 == null) {
                                                                            String str5 = getfTokenAddr();
                                                                            String str6 = marketInfoDTO.getfTokenAddr();
                                                                            if (str5 != null ? str5.equals(str6) : str6 == null) {
                                                                                String str7 = getsShortName();
                                                                                String str8 = marketInfoDTO.getsShortName();
                                                                                return str7 != null ? str7.equals(str8) : str8 == null;
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
                    return false;
                }
                return false;
            }

            public int hashCode() {
                Integer num = getsPrecision();
                int hashCode = num == null ? 43 : num.hashCode();
                Double priceInUsd = getPriceInUsd();
                int hashCode2 = ((hashCode + 59) * 59) + (priceInUsd == null ? 43 : priceInUsd.hashCode());
                Double volume24hGain = getVolume24hGain();
                int hashCode3 = (hashCode2 * 59) + (volume24hGain == null ? 43 : volume24hGain.hashCode());
                Double gain = getGain();
                int hashCode4 = (hashCode3 * 59) + (gain == null ? 43 : gain.hashCode());
                Boolean filter = getFilter();
                int hashCode5 = (hashCode4 * 59) + (filter == null ? 43 : filter.hashCode());
                Integer num2 = getfPrecision();
                int hashCode6 = (hashCode5 * 59) + (num2 == null ? 43 : num2.hashCode());
                Double marketCap24hGain = getMarketCap24hGain();
                int hashCode7 = (hashCode6 * 59) + (marketCap24hGain == null ? 43 : marketCap24hGain.hashCode());
                Double priceInTrx = getPriceInTrx();
                int hashCode8 = (hashCode7 * 59) + (priceInTrx == null ? 43 : priceInTrx.hashCode());
                Double volume24hInTrx = getVolume24hInTrx();
                int hashCode9 = (hashCode8 * 59) + (volume24hInTrx == null ? 43 : volume24hInTrx.hashCode());
                String str = getsTokenAddr();
                int hashCode10 = (hashCode9 * 59) + (str == null ? 43 : str.hashCode());
                String pairUrl = getPairUrl();
                int hashCode11 = (hashCode10 * 59) + (pairUrl == null ? 43 : pairUrl.hashCode());
                String str2 = getfShortName();
                int hashCode12 = (hashCode11 * 59) + (str2 == null ? 43 : str2.hashCode());
                String priceFrom = getPriceFrom();
                int hashCode13 = (hashCode12 * 59) + (priceFrom == null ? 43 : priceFrom.hashCode());
                String str3 = getfTokenAddr();
                int hashCode14 = (hashCode13 * 59) + (str3 == null ? 43 : str3.hashCode());
                String str4 = getsShortName();
                return (hashCode14 * 59) + (str4 != null ? str4.hashCode() : 43);
            }

            public String toString() {
                return "Trc20DetailBean.Trc10TokensBean.MarketInfoDTO(sTokenAddr=" + getsTokenAddr() + ", sPrecision=" + getsPrecision() + ", priceInUsd=" + getPriceInUsd() + ", pairUrl=" + getPairUrl() + ", volume24hGain=" + getVolume24hGain() + ", gain=" + getGain() + ", fShortName=" + getfShortName() + ", filter=" + getFilter() + ", fPrecision=" + getfPrecision() + ", priceFrom=" + getPriceFrom() + ", marketCap24hGain=" + getMarketCap24hGain() + ", priceInTrx=" + getPriceInTrx() + ", volume24hInTrx=" + getVolume24hInTrx() + ", fTokenAddr=" + getfTokenAddr() + ", sShortName=" + getsShortName() + ")";
            }
        }

        public static class TokenPriceLineDTO {
            @JsonProperty("data")
            private List<DataDTO> data;
            @JsonProperty("total")
            private Integer total;

            public List<DataDTO> getData() {
                return this.data;
            }

            public Integer getTotal() {
                return this.total;
            }

            public void setData(List<DataDTO> list) {
                this.data = list;
            }

            public void setTotal(Integer num) {
                this.total = num;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof TokenPriceLineDTO;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof TokenPriceLineDTO) {
                    TokenPriceLineDTO tokenPriceLineDTO = (TokenPriceLineDTO) obj;
                    if (tokenPriceLineDTO.canEqual(this)) {
                        Integer total = getTotal();
                        Integer total2 = tokenPriceLineDTO.getTotal();
                        if (total != null ? total.equals(total2) : total2 == null) {
                            List<DataDTO> data = getData();
                            List<DataDTO> data2 = tokenPriceLineDTO.getData();
                            return data != null ? data.equals(data2) : data2 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }

            public int hashCode() {
                Integer total = getTotal();
                int hashCode = total == null ? 43 : total.hashCode();
                List<DataDTO> data = getData();
                return ((hashCode + 59) * 59) + (data != null ? data.hashCode() : 43);
            }

            public String toString() {
                return "Trc20DetailBean.Trc10TokensBean.TokenPriceLineDTO(total=" + getTotal() + ", data=" + getData() + ")";
            }

            public static class DataDTO {
                @JsonProperty("priceUsd")
                private String priceUsd;
                @JsonProperty("time")
                private String time;

                public String getPriceUsd() {
                    return this.priceUsd;
                }

                public String getTime() {
                    return this.time;
                }

                public void setPriceUsd(String str) {
                    this.priceUsd = str;
                }

                public void setTime(String str) {
                    this.time = str;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof DataDTO;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (obj instanceof DataDTO) {
                        DataDTO dataDTO = (DataDTO) obj;
                        if (dataDTO.canEqual(this)) {
                            String priceUsd = getPriceUsd();
                            String priceUsd2 = dataDTO.getPriceUsd();
                            if (priceUsd != null ? priceUsd.equals(priceUsd2) : priceUsd2 == null) {
                                String time = getTime();
                                String time2 = dataDTO.getTime();
                                return time != null ? time.equals(time2) : time2 == null;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }

                public int hashCode() {
                    String priceUsd = getPriceUsd();
                    int hashCode = priceUsd == null ? 43 : priceUsd.hashCode();
                    String time = getTime();
                    return ((hashCode + 59) * 59) + (time != null ? time.hashCode() : 43);
                }

                public String toString() {
                    return "Trc20DetailBean.Trc10TokensBean.TokenPriceLineDTO.DataDTO(priceUsd=" + getPriceUsd() + ", time=" + getTime() + ")";
                }
            }
        }

        public static class SocialMediaDTO {
            @JsonProperty(AppMeasurementSdk.ConditionalUserProperty.NAME)
            private String name;
            @JsonProperty("url")
            private String url;

            public String getName() {
                return this.name;
            }

            public String getUrl() {
                return this.url;
            }

            public void setName(String str) {
                this.name = str;
            }

            public void setUrl(String str) {
                this.url = str;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof SocialMediaDTO;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof SocialMediaDTO) {
                    SocialMediaDTO socialMediaDTO = (SocialMediaDTO) obj;
                    if (socialMediaDTO.canEqual(this)) {
                        String name = getName();
                        String name2 = socialMediaDTO.getName();
                        if (name != null ? name.equals(name2) : name2 == null) {
                            String url = getUrl();
                            String url2 = socialMediaDTO.getUrl();
                            return url != null ? url.equals(url2) : url2 == null;
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
                String url = getUrl();
                return ((hashCode + 59) * 59) + (url != null ? url.hashCode() : 43);
            }

            public String toString() {
                return "Trc20DetailBean.Trc10TokensBean.SocialMediaDTO(name=" + getName() + ", url=" + getUrl() + ")";
            }
        }
    }
}
