package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.bean.MessageBean;
import com.tron.wallet.common.utils.DateUtils;
import com.tronlinkpro.wallet.R;
import java.util.Date;
public class MessageHolder extends BaseHolder {
    FrameLayout flIcon;
    ImageView ivIcon;
    ImageView ivIconPoint;
    private Context mContext;
    TextView tvAddress;
    TextView tvClick;
    TextView tvContent;
    TextView tvTime;
    TextView tvTitle;
    View vLine;

    public MessageHolder(View view) {
        super(view);
        this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        this.flIcon = (FrameLayout) view.findViewById(R.id.fl_icon);
        this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
        this.tvTime = (TextView) view.findViewById(R.id.tv_content);
        this.tvContent = (TextView) view.findViewById(R.id.tv_content);
        this.tvClick = (TextView) view.findViewById(R.id.tv_click);
        this.tvAddress = (TextView) view.findViewById(R.id.tv_address);
        this.ivIconPoint = (ImageView) view.findViewById(R.id.iv_icon_point);
        this.vLine = view.findViewById(R.id.v_line);
    }

    public void bindHolder(Context context, MessageBean messageBean, int i, String str, int i2, int i3) {
        this.mContext = context;
        controlContractType(messageBean);
    }

    private void controlContractType(MessageBean messageBean) {
        String str = messageBean.contractType == null ? "other" : messageBean.contractType;
        this.tvClick.setVisibility(View.GONE);
        this.tvContent.setTypeface(Typeface.SANS_SERIF, 0);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1705044092:
                if (str.equals("WithdrawBalanceContract")) {
                    c = 0;
                    break;
                }
                break;
            case -1266283874:
                if (str.equals("friend")) {
                    c = 1;
                    break;
                }
                break;
            case -703089577:
                if (str.equals("FreezeBalanceContract")) {
                    c = 2;
                    break;
                }
                break;
            case -651921570:
                if (str.equals("UnfreezeBalanceContract")) {
                    c = 3;
                    break;
                }
                break;
            case 3560517:
                if (str.equals("tiyi")) {
                    c = 4;
                    break;
                }
                break;
            case 706457047:
                if (str.equals("TransferAssetContract")) {
                    c = 5;
                    break;
                }
                break;
            case 710366781:
                if (str.equals("TransferContract")) {
                    c = 6;
                    break;
                }
                break;
            case 1421429571:
                if (str.equals("TriggerSmartContract")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (messageBean.isRead) {
                    this.ivIcon.setImageResource(R.mipmap.gift_gray);
                } else {
                    this.ivIcon.setImageResource(R.mipmap.gift_black);
                }
                str = this.mContext.getString(R.string.reward_withdraw);
                break;
            case 1:
                if (messageBean.isRead) {
                    this.ivIcon.setImageResource(R.mipmap.friend_gray);
                } else {
                    this.ivIcon.setImageResource(R.mipmap.friend_black);
                }
                this.tvClick.setVisibility(View.VISIBLE);
                str = this.mContext.getString(R.string.friend_invitation);
                break;
            case 2:
                if (messageBean.isRead) {
                    this.ivIcon.setImageResource(R.mipmap.freeze_gray);
                } else {
                    this.ivIcon.setImageResource(R.mipmap.freeze_black);
                }
                str = this.mContext.getString(R.string.resource_freeze);
                break;
            case 3:
                if (messageBean.isRead) {
                    this.ivIcon.setImageResource(R.mipmap.freeze_gray);
                } else {
                    this.ivIcon.setImageResource(R.mipmap.freeze_black);
                }
                str = this.mContext.getString(R.string.resource_unfreeze);
                break;
            case 4:
                if (messageBean.isRead) {
                    this.ivIcon.setImageResource(R.mipmap.proposal_gray);
                } else {
                    this.ivIcon.setImageResource(R.mipmap.proposal_black);
                }
                if (messageBean.state.equals("APPROVED")) {
                    str = this.mContext.getString(R.string.proposal_has_entered);
                } else {
                    str = this.mContext.getString(R.string.proposal_has_expired);
                }
                this.tvContent.setTypeface(Typeface.SANS_SERIF, 1);
                break;
            case 5:
            case 6:
            case 7:
                if (messageBean.isRead) {
                    this.ivIcon.setImageResource(R.mipmap.transfer_gray);
                } else {
                    this.ivIcon.setImageResource(R.mipmap.transfer_black);
                }
                if (messageBean.direction == 2) {
                    str = this.mContext.getString(R.string.receipt_notice);
                    break;
                } else {
                    str = this.mContext.getString(R.string.transfer_notice);
                    break;
                }
        }
        if (messageBean.contractType != null && messageBean.contractType.equals("WithdrawBalanceContract")) {
            String string = this.mContext.getString(R.string.click_withdraw);
            int length = string.length();
            SpannableString spannableString = new SpannableString(messageBean.content + string);
            spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color.blue_13)), spannableString.length() - length, spannableString.length(), 33);
            this.tvContent.setText(spannableString);
        } else {
            this.tvContent.setText(messageBean.content);
        }
        this.tvTitle.setText(str);
        this.tvTime.setText(DateUtils.diffLanguageDateToString2(new Date(messageBean.time), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
        if (messageBean.address != null) {
            TextView textView = this.tvAddress;
            textView.setText(this.mContext.getString(R.string.wallet_address3) + messageBean.address);
        } else {
            this.tvAddress.setVisibility(View.GONE);
        }
        if (messageBean.isRead) {
            this.ivIconPoint.setVisibility(View.GONE);
            this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.gray_99));
            this.tvContent.setTextColor(this.mContext.getResources().getColor(R.color.gray_99));
            this.tvClick.setTextColor(this.mContext.getResources().getColor(R.color.gray_99));
            return;
        }
        this.ivIconPoint.setVisibility(View.GONE);
        this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.black_02));
        this.tvContent.setTextColor(this.mContext.getResources().getColor(R.color.black_02));
        this.tvClick.setTextColor(this.mContext.getResources().getColor(R.color.blue_13));
    }
}
