package com.tron.wallet.business.transfer.share;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.common.utils.UrlUtils;
import java.util.HashMap;
import java.util.Map;
public class UrlParamBean {
    private final Map<String, String> params = new HashMap();

    public UrlParamBean setHash(String str) {
        this.params.put("hash", str);
        return this;
    }

    public UrlParamBean setLanCode(String str) {
        this.params.put("language", str);
        return this;
    }

    public UrlParamBean setWalletAddress(String str) {
        this.params.put("walletAddress", str);
        return this;
    }

    public UrlParamBean setEventType(String str) {
        this.params.put("event_type", str);
        return this;
    }

    public UrlParamBean setTokenName(String str) {
        this.params.put("tokenName", UrlUtils.urlEncode(str));
        return this;
    }

    public UrlParamBean setDecimals(String str) {
        this.params.put("decimals", str);
        return this;
    }

    public String buildUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(IRequest.getShareUrl());
        sb.append("?");
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        return sb.subSequence(0, sb.length() - 1).toString();
    }
}
