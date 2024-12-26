package com.tron.wallet.business.security.tokencheck.controller;

import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import java.util.List;
public class IgnoreTokenManager {
    private IgnoreTokenController ignoreTokenController;

    public static class Nested {
        private static IgnoreTokenManager manager = new IgnoreTokenManager();
    }

    private IgnoreTokenManager() {
        this.ignoreTokenController = IgnoreTokenController.getInstance();
    }

    public boolean removeSingle(TokenCheckBean tokenCheckBean) {
        return this.ignoreTokenController.removeSingle(tokenCheckBean);
    }

    public List<TokenCheckBean> queryAll() {
        return this.ignoreTokenController.queryAll();
    }

    public boolean add(TokenCheckBean tokenCheckBean) {
        return this.ignoreTokenController.insertOrReplace(tokenCheckBean);
    }

    public static IgnoreTokenManager getInstance() {
        return Nested.manager;
    }
}
