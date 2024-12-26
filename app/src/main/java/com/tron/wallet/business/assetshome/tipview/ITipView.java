package com.tron.wallet.business.assetshome.tipview;

import org.tron.walletserver.Wallet;
public interface ITipView {
    public static final int STATE_CHECKING = 1;
    public static final int STATE_HAS_SHOW = 5;
    public static final int STATE_INIT = 0;
    public static final int STATE_NEED_SHOW = 3;
    public static final int STATE_SHOWING = 4;
    public static final int STATE_UNNEEDED_SHOW = 2;

    String getId();

    int getPriority();

    int getState();

    void hide();

    void onWalletChanged(Wallet wallet);

    void setState(int i, String... strArr);

    void show();
}
