package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tronlinkpro.wallet.R;
public final class AcStakeHomeBinding implements ViewBinding {
    public final TextView allStakeCountTitle;
    public final TextView allStakeTrxCount;
    public final ConstraintLayout bandwidthCount;
    public final TextView bandwidthTitle;
    public final TextView bandwidthTrx;
    public final TextView bandwidthTrxUnit;
    public final TextView btnStake;
    public final TextView btnStakeNoV2;
    public final TextView btnUnstake;
    public final RelativeLayout cardNoStakeV2;
    public final RelativeLayout cardStakeV1;
    public final RelativeLayout cardStakeV2;
    public final TextView currentApr;
    public final RelativeLayout energyBandProgress;
    public final ConstraintLayout energyCount;
    public final TextView energyTitle;
    public final TextView energyTrx;
    public final View gradualRedView;
    public final ImageView iconStakeV1;
    public final ImageView ivQuestionV1;
    public final ImageView ivResourceGo;
    public final ImageView ivStake;
    public final ImageView ivVoteGo;
    public final ConstraintLayout llAction;
    public final ConstraintLayout llResource;
    public final TextView moreTitle;
    public final NoStakeLayoutBinding noStakeLayout;
    public final TextView noStakeV2LearnMore;
    public final LinearLayout noStakeV2Tips;
    public final LinearLayout resourceBar;
    public final RelativeLayout resourceLayout;
    public final TextView resourceTitle;
    private final RelativeLayout rootView;
    public final ScrollView scrollLayout;
    public final StakeHeaderView stakeHeader;
    public final LinearLayout stakeV1Layout;
    public final View stakeV2Line;
    public final View stakeV2LineBottom;
    public final RelativeLayout titleV1Layout;
    public final RelativeLayout titleV2Layout;
    public final RelativeLayout topView;
    public final TextView tvBandwidth;
    public final TextView tvBandwidthBar;
    public final TextView tvBandwidthTotal;
    public final TextView tvEnergy;
    public final TextView tvEnergyBar;
    public final TextView tvEnergyTotal;
    public final TextView tvLearnStakeV2;
    public final TextView tvMultiWarning;
    public final TextView tvStakeV1Count;
    public final TextView tvStakeV1CountUnit;
    public final TextView tvStakeV2Count;
    public final TextView tvStakeV2CountUnit;
    public final TextView tvTitleNoV2;
    public final TextView tvTitleV1;
    public final TextView tvTitleV2;
    public final TextView tvV1Unfreeze;
    public final TextView tvV2CanWithdraw;
    public final TextView tvVoteEntrance;
    public final TextView unStakingCount;
    public final RelativeLayout unstakeLayout;
    public final RelativeLayout unstakingLayout;
    public final ImageView v1StakeArrow;
    public final TextView v2CanWithdrawUnit;
    public final ImageView v2UnstakeArrow;
    public final TextView v2UnstakeUnit;
    public final RelativeLayout viewPlaceHolder;
    public final View viewTopBg;
    public final TextView voteAprTitle;
    public final TextView voteEntranceTitle;
    public final RelativeLayout voteLayout;
    public final TextView voteRights;
    public final TextView voteTitle;
    public final TextView withDrawAvailableTrx;
    public final RelativeLayout withdrawLayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcStakeHomeBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, ConstraintLayout constraintLayout, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView9, RelativeLayout relativeLayout5, ConstraintLayout constraintLayout2, TextView textView10, TextView textView11, View view, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, TextView textView12, NoStakeLayoutBinding noStakeLayoutBinding, TextView textView13, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout6, TextView textView14, ScrollView scrollView, StakeHeaderView stakeHeaderView, LinearLayout linearLayout3, View view2, View view3, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, TextView textView27, TextView textView28, TextView textView29, TextView textView30, TextView textView31, TextView textView32, TextView textView33, RelativeLayout relativeLayout10, RelativeLayout relativeLayout11, ImageView imageView6, TextView textView34, ImageView imageView7, TextView textView35, RelativeLayout relativeLayout12, View view4, TextView textView36, TextView textView37, RelativeLayout relativeLayout13, TextView textView38, TextView textView39, TextView textView40, RelativeLayout relativeLayout14) {
        this.rootView = relativeLayout;
        this.allStakeCountTitle = textView;
        this.allStakeTrxCount = textView2;
        this.bandwidthCount = constraintLayout;
        this.bandwidthTitle = textView3;
        this.bandwidthTrx = textView4;
        this.bandwidthTrxUnit = textView5;
        this.btnStake = textView6;
        this.btnStakeNoV2 = textView7;
        this.btnUnstake = textView8;
        this.cardNoStakeV2 = relativeLayout2;
        this.cardStakeV1 = relativeLayout3;
        this.cardStakeV2 = relativeLayout4;
        this.currentApr = textView9;
        this.energyBandProgress = relativeLayout5;
        this.energyCount = constraintLayout2;
        this.energyTitle = textView10;
        this.energyTrx = textView11;
        this.gradualRedView = view;
        this.iconStakeV1 = imageView;
        this.ivQuestionV1 = imageView2;
        this.ivResourceGo = imageView3;
        this.ivStake = imageView4;
        this.ivVoteGo = imageView5;
        this.llAction = constraintLayout3;
        this.llResource = constraintLayout4;
        this.moreTitle = textView12;
        this.noStakeLayout = noStakeLayoutBinding;
        this.noStakeV2LearnMore = textView13;
        this.noStakeV2Tips = linearLayout;
        this.resourceBar = linearLayout2;
        this.resourceLayout = relativeLayout6;
        this.resourceTitle = textView14;
        this.scrollLayout = scrollView;
        this.stakeHeader = stakeHeaderView;
        this.stakeV1Layout = linearLayout3;
        this.stakeV2Line = view2;
        this.stakeV2LineBottom = view3;
        this.titleV1Layout = relativeLayout7;
        this.titleV2Layout = relativeLayout8;
        this.topView = relativeLayout9;
        this.tvBandwidth = textView15;
        this.tvBandwidthBar = textView16;
        this.tvBandwidthTotal = textView17;
        this.tvEnergy = textView18;
        this.tvEnergyBar = textView19;
        this.tvEnergyTotal = textView20;
        this.tvLearnStakeV2 = textView21;
        this.tvMultiWarning = textView22;
        this.tvStakeV1Count = textView23;
        this.tvStakeV1CountUnit = textView24;
        this.tvStakeV2Count = textView25;
        this.tvStakeV2CountUnit = textView26;
        this.tvTitleNoV2 = textView27;
        this.tvTitleV1 = textView28;
        this.tvTitleV2 = textView29;
        this.tvV1Unfreeze = textView30;
        this.tvV2CanWithdraw = textView31;
        this.tvVoteEntrance = textView32;
        this.unStakingCount = textView33;
        this.unstakeLayout = relativeLayout10;
        this.unstakingLayout = relativeLayout11;
        this.v1StakeArrow = imageView6;
        this.v2CanWithdrawUnit = textView34;
        this.v2UnstakeArrow = imageView7;
        this.v2UnstakeUnit = textView35;
        this.viewPlaceHolder = relativeLayout12;
        this.viewTopBg = view4;
        this.voteAprTitle = textView36;
        this.voteEntranceTitle = textView37;
        this.voteLayout = relativeLayout13;
        this.voteRights = textView38;
        this.voteTitle = textView39;
        this.withDrawAvailableTrx = textView40;
        this.withdrawLayout = relativeLayout14;
    }

    public static AcStakeHomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcStakeHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_stake_home, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcStakeHomeBinding bind(View view) {
        int i = R.id.all_stake_count_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.all_stake_count_title);
        if (textView != null) {
            i = R.id.all_stake_trx_count;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.all_stake_trx_count);
            if (textView2 != null) {
                i = R.id.bandwidth_count;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.bandwidth_count);
                if (constraintLayout != null) {
                    i = R.id.bandwidth_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.bandwidth_title);
                    if (textView3 != null) {
                        i = R.id.bandwidth_trx;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.bandwidth_trx);
                        if (textView4 != null) {
                            i = R.id.bandwidth_trx_unit;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.bandwidth_trx_unit);
                            if (textView5 != null) {
                                i = R.id.btn_stake;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_stake);
                                if (textView6 != null) {
                                    i = R.id.btn_stake_no_v2;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_stake_no_v2);
                                    if (textView7 != null) {
                                        i = R.id.btn_unstake;
                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_unstake);
                                        if (textView8 != null) {
                                            i = R.id.card_no_stake_v2;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.card_no_stake_v2);
                                            if (relativeLayout != null) {
                                                i = R.id.card_stake_v1;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.card_stake_v1);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.card_stake_v2;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.card_stake_v2);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.current_apr;
                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.current_apr);
                                                        if (textView9 != null) {
                                                            i = R.id.energy_band_progress;
                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.energy_band_progress);
                                                            if (relativeLayout4 != null) {
                                                                i = R.id.energy_count;
                                                                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.energy_count);
                                                                if (constraintLayout2 != null) {
                                                                    i = R.id.energy_title;
                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.energy_title);
                                                                    if (textView10 != null) {
                                                                        i = R.id.energy_trx;
                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.energy_trx);
                                                                        if (textView11 != null) {
                                                                            i = R.id.gradual_red_view;
                                                                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.gradual_red_view);
                                                                            if (findChildViewById != null) {
                                                                                i = R.id.icon_stake_v1;
                                                                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_stake_v1);
                                                                                if (imageView != null) {
                                                                                    i = R.id.iv_question_v1;
                                                                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_question_v1);
                                                                                    if (imageView2 != null) {
                                                                                        i = R.id.iv_resource_go;
                                                                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_resource_go);
                                                                                        if (imageView3 != null) {
                                                                                            i = R.id.iv_stake;
                                                                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_stake);
                                                                                            if (imageView4 != null) {
                                                                                                i = R.id.iv_vote_go;
                                                                                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_vote_go);
                                                                                                if (imageView5 != null) {
                                                                                                    i = R.id.ll_action;
                                                                                                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                                                                                                    if (constraintLayout3 != null) {
                                                                                                        i = R.id.ll_resource;
                                                                                                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_resource);
                                                                                                        if (constraintLayout4 != null) {
                                                                                                            i = R.id.more_title;
                                                                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.more_title);
                                                                                                            if (textView12 != null) {
                                                                                                                i = R.id.no_stake_layout;
                                                                                                                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.no_stake_layout);
                                                                                                                if (findChildViewById2 != null) {
                                                                                                                    NoStakeLayoutBinding bind = NoStakeLayoutBinding.bind(findChildViewById2);
                                                                                                                    i = R.id.no_stake_v2_learn_more;
                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.no_stake_v2_learn_more);
                                                                                                                    if (textView13 != null) {
                                                                                                                        i = R.id.no_stake_v2_tips;
                                                                                                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.no_stake_v2_tips);
                                                                                                                        if (linearLayout != null) {
                                                                                                                            i = R.id.resource_bar;
                                                                                                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.resource_bar);
                                                                                                                            if (linearLayout2 != null) {
                                                                                                                                i = R.id.resource_layout;
                                                                                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.resource_layout);
                                                                                                                                if (relativeLayout5 != null) {
                                                                                                                                    i = R.id.resource_title;
                                                                                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.resource_title);
                                                                                                                                    if (textView14 != null) {
                                                                                                                                        i = R.id.scroll_layout;
                                                                                                                                        ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.scroll_layout);
                                                                                                                                        if (scrollView != null) {
                                                                                                                                            i = R.id.stake_header;
                                                                                                                                            StakeHeaderView stakeHeaderView = (StakeHeaderView) ViewBindings.findChildViewById(view, R.id.stake_header);
                                                                                                                                            if (stakeHeaderView != null) {
                                                                                                                                                i = R.id.stake_v1_layout;
                                                                                                                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.stake_v1_layout);
                                                                                                                                                if (linearLayout3 != null) {
                                                                                                                                                    i = R.id.stake_v2_line;
                                                                                                                                                    View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.stake_v2_line);
                                                                                                                                                    if (findChildViewById3 != null) {
                                                                                                                                                        i = R.id.stake_v2_line_bottom;
                                                                                                                                                        View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.stake_v2_line_bottom);
                                                                                                                                                        if (findChildViewById4 != null) {
                                                                                                                                                            i = R.id.title_v1_layout;
                                                                                                                                                            RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.title_v1_layout);
                                                                                                                                                            if (relativeLayout6 != null) {
                                                                                                                                                                i = R.id.title_v2_layout;
                                                                                                                                                                RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.title_v2_layout);
                                                                                                                                                                if (relativeLayout7 != null) {
                                                                                                                                                                    i = R.id.top_view;
                                                                                                                                                                    RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top_view);
                                                                                                                                                                    if (relativeLayout8 != null) {
                                                                                                                                                                        i = R.id.tv_bandwidth;
                                                                                                                                                                        TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth);
                                                                                                                                                                        if (textView15 != null) {
                                                                                                                                                                            i = R.id.tv_bandwidth_bar;
                                                                                                                                                                            TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth_bar);
                                                                                                                                                                            if (textView16 != null) {
                                                                                                                                                                                i = R.id.tv_bandwidth_total;
                                                                                                                                                                                TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth_total);
                                                                                                                                                                                if (textView17 != null) {
                                                                                                                                                                                    i = R.id.tv_energy;
                                                                                                                                                                                    TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy);
                                                                                                                                                                                    if (textView18 != null) {
                                                                                                                                                                                        i = R.id.tv_energy_bar;
                                                                                                                                                                                        TextView textView19 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_bar);
                                                                                                                                                                                        if (textView19 != null) {
                                                                                                                                                                                            i = R.id.tv_energy_total;
                                                                                                                                                                                            TextView textView20 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_total);
                                                                                                                                                                                            if (textView20 != null) {
                                                                                                                                                                                                i = R.id.tv_learn_stake_v2;
                                                                                                                                                                                                TextView textView21 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_learn_stake_v2);
                                                                                                                                                                                                if (textView21 != null) {
                                                                                                                                                                                                    i = R.id.tv_multi_warning;
                                                                                                                                                                                                    TextView textView22 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                                                                                                                                                                                    if (textView22 != null) {
                                                                                                                                                                                                        i = R.id.tv_stake_v1_count;
                                                                                                                                                                                                        TextView textView23 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_v1_count);
                                                                                                                                                                                                        if (textView23 != null) {
                                                                                                                                                                                                            i = R.id.tv_stake_v1_count_unit;
                                                                                                                                                                                                            TextView textView24 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_v1_count_unit);
                                                                                                                                                                                                            if (textView24 != null) {
                                                                                                                                                                                                                i = R.id.tv_stake_v2_count;
                                                                                                                                                                                                                TextView textView25 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_v2_count);
                                                                                                                                                                                                                if (textView25 != null) {
                                                                                                                                                                                                                    i = R.id.tv_stake_v2_count_unit;
                                                                                                                                                                                                                    TextView textView26 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_v2_count_unit);
                                                                                                                                                                                                                    if (textView26 != null) {
                                                                                                                                                                                                                        i = R.id.tv_title_no_v2;
                                                                                                                                                                                                                        TextView textView27 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_no_v2);
                                                                                                                                                                                                                        if (textView27 != null) {
                                                                                                                                                                                                                            i = R.id.tv_title_v1;
                                                                                                                                                                                                                            TextView textView28 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_v1);
                                                                                                                                                                                                                            if (textView28 != null) {
                                                                                                                                                                                                                                i = R.id.tv_title_v2;
                                                                                                                                                                                                                                TextView textView29 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_v2);
                                                                                                                                                                                                                                if (textView29 != null) {
                                                                                                                                                                                                                                    i = R.id.tv_v1_unfreeze;
                                                                                                                                                                                                                                    TextView textView30 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_v1_unfreeze);
                                                                                                                                                                                                                                    if (textView30 != null) {
                                                                                                                                                                                                                                        i = R.id.tv_v2_can_withdraw;
                                                                                                                                                                                                                                        TextView textView31 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_v2_can_withdraw);
                                                                                                                                                                                                                                        if (textView31 != null) {
                                                                                                                                                                                                                                            i = R.id.tv_vote_entrance;
                                                                                                                                                                                                                                            TextView textView32 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_entrance);
                                                                                                                                                                                                                                            if (textView32 != null) {
                                                                                                                                                                                                                                                i = R.id.un_staking_count;
                                                                                                                                                                                                                                                TextView textView33 = (TextView) ViewBindings.findChildViewById(view, R.id.un_staking_count);
                                                                                                                                                                                                                                                if (textView33 != null) {
                                                                                                                                                                                                                                                    i = R.id.unstake_layout;
                                                                                                                                                                                                                                                    RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.unstake_layout);
                                                                                                                                                                                                                                                    if (relativeLayout9 != null) {
                                                                                                                                                                                                                                                        i = R.id.unstaking_layout;
                                                                                                                                                                                                                                                        RelativeLayout relativeLayout10 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.unstaking_layout);
                                                                                                                                                                                                                                                        if (relativeLayout10 != null) {
                                                                                                                                                                                                                                                            i = R.id.v1_stake_arrow;
                                                                                                                                                                                                                                                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.v1_stake_arrow);
                                                                                                                                                                                                                                                            if (imageView6 != null) {
                                                                                                                                                                                                                                                                i = R.id.v2_can_withdraw_unit;
                                                                                                                                                                                                                                                                TextView textView34 = (TextView) ViewBindings.findChildViewById(view, R.id.v2_can_withdraw_unit);
                                                                                                                                                                                                                                                                if (textView34 != null) {
                                                                                                                                                                                                                                                                    i = R.id.v2_unstake_arrow;
                                                                                                                                                                                                                                                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.v2_unstake_arrow);
                                                                                                                                                                                                                                                                    if (imageView7 != null) {
                                                                                                                                                                                                                                                                        i = R.id.v2_unstake_unit;
                                                                                                                                                                                                                                                                        TextView textView35 = (TextView) ViewBindings.findChildViewById(view, R.id.v2_unstake_unit);
                                                                                                                                                                                                                                                                        if (textView35 != null) {
                                                                                                                                                                                                                                                                            i = R.id.view_place_holder;
                                                                                                                                                                                                                                                                            RelativeLayout relativeLayout11 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.view_place_holder);
                                                                                                                                                                                                                                                                            if (relativeLayout11 != null) {
                                                                                                                                                                                                                                                                                i = R.id.view_top_bg;
                                                                                                                                                                                                                                                                                View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.view_top_bg);
                                                                                                                                                                                                                                                                                if (findChildViewById5 != null) {
                                                                                                                                                                                                                                                                                    i = R.id.vote_apr_title;
                                                                                                                                                                                                                                                                                    TextView textView36 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_apr_title);
                                                                                                                                                                                                                                                                                    if (textView36 != null) {
                                                                                                                                                                                                                                                                                        i = R.id.vote_entrance_title;
                                                                                                                                                                                                                                                                                        TextView textView37 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_entrance_title);
                                                                                                                                                                                                                                                                                        if (textView37 != null) {
                                                                                                                                                                                                                                                                                            i = R.id.vote_layout;
                                                                                                                                                                                                                                                                                            RelativeLayout relativeLayout12 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.vote_layout);
                                                                                                                                                                                                                                                                                            if (relativeLayout12 != null) {
                                                                                                                                                                                                                                                                                                i = R.id.vote_rights;
                                                                                                                                                                                                                                                                                                TextView textView38 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_rights);
                                                                                                                                                                                                                                                                                                if (textView38 != null) {
                                                                                                                                                                                                                                                                                                    i = R.id.vote_title;
                                                                                                                                                                                                                                                                                                    TextView textView39 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_title);
                                                                                                                                                                                                                                                                                                    if (textView39 != null) {
                                                                                                                                                                                                                                                                                                        i = R.id.withDraw_available_trx;
                                                                                                                                                                                                                                                                                                        TextView textView40 = (TextView) ViewBindings.findChildViewById(view, R.id.withDraw_available_trx);
                                                                                                                                                                                                                                                                                                        if (textView40 != null) {
                                                                                                                                                                                                                                                                                                            i = R.id.withdraw_layout;
                                                                                                                                                                                                                                                                                                            RelativeLayout relativeLayout13 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.withdraw_layout);
                                                                                                                                                                                                                                                                                                            if (relativeLayout13 != null) {
                                                                                                                                                                                                                                                                                                                return new AcStakeHomeBinding((RelativeLayout) view, textView, textView2, constraintLayout, textView3, textView4, textView5, textView6, textView7, textView8, relativeLayout, relativeLayout2, relativeLayout3, textView9, relativeLayout4, constraintLayout2, textView10, textView11, findChildViewById, imageView, imageView2, imageView3, imageView4, imageView5, constraintLayout3, constraintLayout4, textView12, bind, textView13, linearLayout, linearLayout2, relativeLayout5, textView14, scrollView, stakeHeaderView, linearLayout3, findChildViewById3, findChildViewById4, relativeLayout6, relativeLayout7, relativeLayout8, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27, textView28, textView29, textView30, textView31, textView32, textView33, relativeLayout9, relativeLayout10, imageView6, textView34, imageView7, textView35, relativeLayout11, findChildViewById5, textView36, textView37, relativeLayout12, textView38, textView39, textView40, relativeLayout13);
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
