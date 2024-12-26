package com.tron.wallet.business.tabdapp.browser.grid.animator;

import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.base.RefactoredDefaultItemAnimator;
public class DraggableItemAnimator extends RefactoredDefaultItemAnimator {
    @Override
    public void onSetup() {
        super.onSetup();
        super.setSupportsChangeAnimations(false);
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        if (viewHolder == viewHolder2 && i == i3 && i2 == i4) {
            dispatchChangeFinished(viewHolder, true);
            return false;
        }
        return super.animateChange(viewHolder, viewHolder2, i, i2, i3, i4);
    }
}
