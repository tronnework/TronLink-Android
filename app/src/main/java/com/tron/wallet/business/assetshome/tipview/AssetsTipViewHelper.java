package com.tron.wallet.business.assetshome.tipview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.tron.walletserver.Wallet;
public class AssetsTipViewHelper {
    private List<ITipView> tipViews = new ArrayList();

    public void setTipView(ITipView iTipView) {
        this.tipViews.add(iTipView);
    }

    public void onWalletChanged(Wallet wallet) {
        for (ITipView iTipView : this.tipViews) {
            iTipView.onWalletChanged(wallet);
        }
    }

    public void showTipView(ITipView iTipView, String... strArr) {
        iTipView.setState(3, strArr);
        if (iTipView.getState() == 3) {
            boolean z = true;
            for (ITipView iTipView2 : this.tipViews) {
                if (iTipView2.getPriority() < iTipView.getPriority()) {
                    if (iTipView2.getState() != 0) {
                        if (iTipView2.getState() == 3 || iTipView2.getState() == 4) {
                            iTipView.setState(2, new String[0]);
                        }
                    }
                    z = false;
                }
            }
            if (z) {
                hideShowingTipViews(iTipView);
                iTipView.show();
                return;
            }
            return;
        }
        findNeedShowTipView();
    }

    public void hideTipView(ITipView iTipView) {
        if (iTipView != null) {
            iTipView.hide();
        }
        findNeedShowTipView();
    }

    private void hideShowingTipViews(ITipView iTipView) {
        for (ITipView iTipView2 : this.tipViews) {
            if (!iTipView2.getId().equals(iTipView.getId())) {
                if (iTipView2.getState() == 3) {
                    iTipView2.setState(2, new String[0]);
                } else if (iTipView2.getState() == 4) {
                    iTipView2.hide();
                }
            }
        }
    }

    private void findNeedShowTipView() {
        Iterator<ITipView> it = this.tipViews.iterator();
        ITipView iTipView = null;
        ITipView iTipView2 = null;
        while (true) {
            if (!it.hasNext()) {
                iTipView = iTipView2;
                break;
            }
            ITipView next = it.next();
            if (next.getState() == 0) {
                break;
            } else if (next.getState() == 3 && (iTipView2 == null || iTipView2.getPriority() > next.getPriority())) {
                iTipView2 = next;
            }
        }
        if (iTipView != null) {
            iTipView.show();
        }
    }
}
