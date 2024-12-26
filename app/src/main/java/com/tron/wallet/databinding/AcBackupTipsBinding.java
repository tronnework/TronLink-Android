package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcBackupTipsBinding implements ViewBinding {
    public final TextView answerTitle;
    public final TextView answerTitleContent1;
    public final ImageView answerTitleContent1Icon;
    public final TextView answerTitleContent2;
    public final ImageView answerTitleContent2Icon;
    public final TextView answerTitleContent3;
    public final ImageView answerTitleContent3Icon;
    public final TextView btnNext;
    public final RelativeLayout nextLayout;
    public final CheckBox noMore;
    private final RelativeLayout rootView;
    public final ScrollView scrollLayout;
    public final TextView whatTitle;
    public final TextView whatTitleContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcBackupTipsBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, ImageView imageView, TextView textView3, ImageView imageView2, TextView textView4, ImageView imageView3, TextView textView5, RelativeLayout relativeLayout2, CheckBox checkBox, ScrollView scrollView, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.answerTitle = textView;
        this.answerTitleContent1 = textView2;
        this.answerTitleContent1Icon = imageView;
        this.answerTitleContent2 = textView3;
        this.answerTitleContent2Icon = imageView2;
        this.answerTitleContent3 = textView4;
        this.answerTitleContent3Icon = imageView3;
        this.btnNext = textView5;
        this.nextLayout = relativeLayout2;
        this.noMore = checkBox;
        this.scrollLayout = scrollView;
        this.whatTitle = textView6;
        this.whatTitleContent = textView7;
    }

    public static AcBackupTipsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcBackupTipsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_backup_tips, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcBackupTipsBinding bind(View view) {
        int i = R.id.answer_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.answer_title);
        if (textView != null) {
            i = R.id.answer_title_content1;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.answer_title_content1);
            if (textView2 != null) {
                i = R.id.answer_title_content1_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.answer_title_content1_icon);
                if (imageView != null) {
                    i = R.id.answer_title_content2;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.answer_title_content2);
                    if (textView3 != null) {
                        i = R.id.answer_title_content2_icon;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.answer_title_content2_icon);
                        if (imageView2 != null) {
                            i = R.id.answer_title_content3;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.answer_title_content3);
                            if (textView4 != null) {
                                i = R.id.answer_title_content3_icon;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.answer_title_content3_icon);
                                if (imageView3 != null) {
                                    i = R.id.btn_next;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_next);
                                    if (textView5 != null) {
                                        i = R.id.next_layout;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.next_layout);
                                        if (relativeLayout != null) {
                                            i = R.id.no_more;
                                            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.no_more);
                                            if (checkBox != null) {
                                                i = R.id.scroll_layout;
                                                ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.scroll_layout);
                                                if (scrollView != null) {
                                                    i = R.id.what_title;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.what_title);
                                                    if (textView6 != null) {
                                                        i = R.id.what_title_content;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.what_title_content);
                                                        if (textView7 != null) {
                                                            return new AcBackupTipsBinding((RelativeLayout) view, textView, textView2, imageView, textView3, imageView2, textView4, imageView3, textView5, relativeLayout, checkBox, scrollView, textView6, textView7);
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
