package com.tron.wallet.business.message.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.business.message.adapter.MessageCenterAdapter;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemMessageCenterBinding;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import org.apache.commons.cli.HelpFormatter;
public class MessageViewHolder extends BaseViewHolder {
    private ItemMessageCenterBinding binding;
    ImageView mIvIcon;
    TextView mTvDate;
    TextView mTvOutAccount;
    TextView mTvOutAccountTitle;
    TextView mTvReceiveAccount;
    TextView mTvReceiveAccountTitle;
    TextView mTvTitle;
    View rootLayout;

    public MessageViewHolder(View view) {
        super(view);
        mappingId(view);
    }

    public void mappingId(View view) {
        ItemMessageCenterBinding bind = ItemMessageCenterBinding.bind(view);
        this.binding = bind;
        this.mIvIcon = bind.ivIcon;
        this.mTvTitle = this.binding.tvTitle;
        this.mTvOutAccountTitle = this.binding.tvOutAccountTitle;
        this.mTvOutAccount = this.binding.tvOutAccount;
        this.mTvReceiveAccountTitle = this.binding.tvAccountReceiveTitle;
        this.mTvReceiveAccount = this.binding.tvAccountReceive;
        this.mTvDate = this.binding.tvDate;
        this.rootLayout = this.binding.itemLayout;
    }

    public void bindHolder(Context context, TransactionMessage transactionMessage, final MessageCenterAdapter.OnItemClickListener onItemClickListener) {
        String sb;
        String string;
        String string2;
        String sb2;
        String amountWithPrecision = getAmountWithPrecision(transactionMessage.getTokenType() == 0, transactionMessage.getAmount(), transactionMessage.getDecimal());
        this.mTvOutAccount.setText(StringTronUtil.isNullOrEmpty(transactionMessage.getFromAddress()) ? HelpFormatter.DEFAULT_LONG_OPT_PREFIX : transactionMessage.getFromAddress());
        this.mTvReceiveAccount.setText(StringTronUtil.isNullOrEmpty(transactionMessage.getToAddress()) ? HelpFormatter.DEFAULT_LONG_OPT_PREFIX : transactionMessage.getToAddress());
        int type = transactionMessage.getType();
        if (type == 0) {
            if (transactionMessage.getTokenType() == 5) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(StringTronUtil.isNullOrEmpty(transactionMessage.getTokenSymbol()) ? "" : transactionMessage.getTokenSymbol());
                sb3.append(StringTronUtil.isNullOrEmpty(transactionMessage.getAssetId()) ? " " : transactionMessage.getAssetId());
                sb = sb3.toString();
            } else {
                StringBuilder sb4 = new StringBuilder();
                if (StringTronUtil.isNullOrEmpty(amountWithPrecision)) {
                    amountWithPrecision = HelpFormatter.DEFAULT_LONG_OPT_PREFIX;
                }
                sb4.append(amountWithPrecision);
                sb4.append(" ");
                sb4.append(StringTronUtil.isNullOrEmpty(transactionMessage.getTokenSymbol()) ? HelpFormatter.DEFAULT_LONG_OPT_PREFIX : transactionMessage.getTokenSymbol());
                sb = sb4.toString();
            }
            if (transactionMessage.getRevert() == 1 || !StringTronUtil.equals(transactionMessage.getContract_ret(), TransactionMessage.TYPE_SUCCESS)) {
                if (transactionMessage.getTokenType() == 5) {
                    string = context.getResources().getString(R.string.message_center_failed_send);
                } else {
                    string = context.getResources().getString(R.string.transfer_fail);
                }
                TextView textView = this.mTvTitle;
                textView.setText(sb + " " + string);
                this.mIvIcon.setImageResource(R.mipmap.message_fail_icon);
            } else {
                if (transactionMessage.getTokenType() == 5) {
                    string2 = context.getResources().getString(R.string.message_center_successful_send);
                } else {
                    string2 = context.getResources().getString(R.string.message_center_successful_transferred);
                }
                TextView textView2 = this.mTvTitle;
                textView2.setText(sb + " " + string2);
                this.mIvIcon.setImageResource(R.mipmap.message_transfer_icon);
            }
        } else if (type == 1) {
            if (transactionMessage.getTokenType() == 5) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(StringTronUtil.isNullOrEmpty(transactionMessage.getTokenSymbol()) ? "" : transactionMessage.getTokenSymbol());
                sb5.append(StringTronUtil.isNullOrEmpty(transactionMessage.getAssetId()) ? " " : transactionMessage.getAssetId());
                sb2 = sb5.toString();
            } else {
                StringBuilder sb6 = new StringBuilder();
                if (StringTronUtil.isNullOrEmpty(amountWithPrecision)) {
                    amountWithPrecision = HelpFormatter.DEFAULT_LONG_OPT_PREFIX;
                }
                sb6.append(amountWithPrecision);
                sb6.append(" ");
                sb6.append(StringTronUtil.isNullOrEmpty(transactionMessage.getTokenSymbol()) ? HelpFormatter.DEFAULT_LONG_OPT_PREFIX : transactionMessage.getTokenSymbol());
                sb2 = sb6.toString();
            }
            if (transactionMessage.getRevert() == 1 || !StringTronUtil.equals(transactionMessage.getContract_ret(), TransactionMessage.TYPE_SUCCESS)) {
                this.mIvIcon.setImageResource(R.mipmap.message_fail_icon);
                TextView textView3 = this.mTvTitle;
                textView3.setText(sb2 + " " + context.getResources().getString(R.string.payment_fail));
            } else {
                TextView textView4 = this.mTvTitle;
                textView4.setText(sb2 + " " + context.getResources().getString(R.string.payment_success));
                this.mIvIcon.setImageResource(R.mipmap.message_payment_icon);
            }
        }
        if (transactionMessage.getTransferTime() == 0) {
            this.mTvDate.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        } else if (DateUtils.isCurrentYear(Long.valueOf(transactionMessage.getTransferTime()))) {
            this.mTvDate.setText(DateUtils.diffLanguageDateWithoutYear(transactionMessage.getTransferTime()));
        } else {
            this.mTvDate.setText(DateUtils.diffLanguageDateHHMM(transactionMessage.getTransferTime()));
        }
        int state = transactionMessage.getState();
        int i = R.color.gray_9B;
        if (state == 0) {
            this.mTvTitle.setTextColor(context.getResources().getColor(R.color.black_23));
            this.mTvDate.setTextColor(context.getResources().getColor(R.color.gray_9B));
            this.mTvOutAccountTitle.setTextColor(context.getResources().getColor(transactionMessage.getType() == 0 ? R.color.black_23 : R.color.gray_9B));
            this.mTvOutAccount.setTextColor(context.getResources().getColor(transactionMessage.getType() == 0 ? R.color.black_23 : R.color.gray_9B));
            this.mTvReceiveAccountTitle.setTextColor(context.getResources().getColor(transactionMessage.getType() == 1 ? R.color.black_23 : R.color.gray_9B));
            TextView textView5 = this.mTvReceiveAccount;
            Resources resources = context.getResources();
            if (transactionMessage.getType() == 1) {
                i = R.color.black_23;
            }
            textView5.setTextColor(resources.getColor(i));
        } else if (transactionMessage.getState() == 1) {
            this.mTvTitle.setTextColor(context.getResources().getColor(R.color.gray_9B));
            this.mTvDate.setTextColor(context.getResources().getColor(R.color.gray_9B));
            this.mTvOutAccountTitle.setTextColor(context.getResources().getColor(R.color.gray_9B));
            this.mTvOutAccount.setTextColor(context.getResources().getColor(R.color.gray_9B));
            this.mTvReceiveAccountTitle.setTextColor(context.getResources().getColor(R.color.gray_9B));
            this.mTvReceiveAccount.setTextColor(context.getResources().getColor(R.color.gray_9B));
        }
        this.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, getLayoutPosition());
            }
        });
    }

    private String getAmountWithPrecision(boolean z, String str, long j) {
        String plainScientificNotation = StringTronUtil.plainScientificNotation(new BigDecimal(str).divide(new BigDecimal(z ? 1000000.0d : Math.pow(10.0d, j))));
        return BigDecimalUtils.between(plainScientificNotation, 0, Double.valueOf(1.0E-6d)) ? "<0.000001" : plainScientificNotation;
    }
}
