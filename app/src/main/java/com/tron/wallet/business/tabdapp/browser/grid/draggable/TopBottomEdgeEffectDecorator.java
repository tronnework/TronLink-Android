package com.tron.wallet.business.tabdapp.browser.grid.draggable;

import androidx.recyclerview.widget.RecyclerView;
public class TopBottomEdgeEffectDecorator extends BaseEdgeEffectDecorator {
    public TopBottomEdgeEffectDecorator(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    protected int getEdgeDirection(int i) {
        if (i != 0) {
            if (i == 1) {
                return 3;
            }
            throw new IllegalArgumentException();
        }
        return 1;
    }
}
