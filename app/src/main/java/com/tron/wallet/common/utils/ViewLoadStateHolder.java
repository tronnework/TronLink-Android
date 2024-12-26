package com.tron.wallet.common.utils;

import android.view.View;
public class ViewLoadStateHolder {
    private State current = State.ALL_GONE;
    private final View dataView;
    private final View noDataView;
    private final View noNetView;
    private final View placeHolderView;

    public enum State {
        NORMAL,
        ERROR,
        NO_DATA,
        PLACE_HOLDER,
        ALL_GONE
    }

    public State getState() {
        return this.current;
    }

    public ViewLoadStateHolder(View view, View view2, View view3, View view4) {
        this.placeHolderView = view;
        this.noNetView = view2;
        this.noDataView = view3;
        this.dataView = view4;
    }

    public void updateState(State state) {
        this.current = state;
        if (state.equals(State.NORMAL)) {
            View view = this.dataView;
            if (view != null) {
                view.setVisibility(View.VISIBLE);
            }
            View view2 = this.noNetView;
            if (view2 != null) {
                view2.setVisibility(View.GONE);
            }
            View view3 = this.placeHolderView;
            if (view3 != null) {
                view3.setVisibility(View.GONE);
            }
            View view4 = this.noDataView;
            if (view4 != null) {
                view4.setVisibility(View.GONE);
            }
        } else if (state.equals(State.ERROR)) {
            View view5 = this.placeHolderView;
            if (view5 != null) {
                view5.setVisibility(View.GONE);
            }
            View view6 = this.noNetView;
            if (view6 != null) {
                view6.setVisibility(View.VISIBLE);
            }
            View view7 = this.dataView;
            if (view7 != null) {
                view7.setVisibility(View.GONE);
            }
            View view8 = this.noDataView;
            if (view8 != null) {
                view8.setVisibility(View.GONE);
            }
        } else if (state.equals(State.PLACE_HOLDER)) {
            View view9 = this.placeHolderView;
            if (view9 != null) {
                view9.setVisibility(View.VISIBLE);
            }
            View view10 = this.dataView;
            if (view10 != null) {
                view10.setVisibility(View.GONE);
            }
            View view11 = this.noNetView;
            if (view11 != null) {
                view11.setVisibility(View.GONE);
            }
            View view12 = this.noDataView;
            if (view12 != null) {
                view12.setVisibility(View.GONE);
            }
        } else if (state.equals(State.NO_DATA)) {
            View view13 = this.noDataView;
            if (view13 != null) {
                view13.setVisibility(View.VISIBLE);
            }
            View view14 = this.noNetView;
            if (view14 != null) {
                view14.setVisibility(View.GONE);
            }
            View view15 = this.dataView;
            if (view15 != null) {
                view15.setVisibility(View.GONE);
            }
            View view16 = this.placeHolderView;
            if (view16 != null) {
                view16.setVisibility(View.GONE);
            }
        } else if (state.equals(State.ALL_GONE)) {
            View view17 = this.noDataView;
            if (view17 != null) {
                view17.setVisibility(View.GONE);
            }
            View view18 = this.noNetView;
            if (view18 != null) {
                view18.setVisibility(View.GONE);
            }
            View view19 = this.dataView;
            if (view19 != null) {
                view19.setVisibility(View.GONE);
            }
            View view20 = this.placeHolderView;
            if (view20 != null) {
                view20.setVisibility(View.GONE);
            }
        }
    }
}
