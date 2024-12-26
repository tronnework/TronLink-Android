package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.messagesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FgLocaldappMetadataBinding;
import com.tronlinkpro.wallet.R;
public class DAppLocalMetaDataFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgLocaldappMetadataBinding binding;
    private String dataHex;
    private String hashDomain;
    private String hashMessage;
    TextView tvMessageContent;
    TextView tvMessageContentDomain;
    TextView tvMessageContentMessage;

    public static DAppLocalMetaDataFragment getInstance(String str, String str2, String str3) {
        DAppLocalMetaDataFragment dAppLocalMetaDataFragment = new DAppLocalMetaDataFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ArgumentKeys.KEY_HASH_DOMAIN, str);
        bundle.putString(ArgumentKeys.KEY_HASH_MESSAGE, str2);
        bundle.putString(ArgumentKeys.KEY_HEX_DATA, str3);
        dAppLocalMetaDataFragment.setArguments(bundle);
        return dAppLocalMetaDataFragment;
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.tv_copy_hex) {
                    UIUtils.copy(dataHex);
                    Toast((int) R.string.already_copy);
                } else if (id == R.id.tv_domain_copy) {
                    UIUtils.copy(hashDomain);
                    Toast((int) R.string.already_copy);
                } else if (id != R.id.tv_message_copy) {
                } else {
                    UIUtils.copy(hashMessage);
                    Toast((int) R.string.already_copy);
                }
            }
        };
        this.binding.tvDomainCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.tvMessageCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.tvCopyHex.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    protected void processData() {
        this.hashDomain = getArguments().getString(ArgumentKeys.KEY_HASH_DOMAIN);
        this.hashMessage = getArguments().getString(ArgumentKeys.KEY_HASH_MESSAGE);
        this.dataHex = getArguments().getString(ArgumentKeys.KEY_HEX_DATA);
        try {
            this.tvMessageContentDomain.setText(this.hashDomain);
            this.tvMessageContentMessage.setText(this.hashMessage);
            this.tvMessageContent.setText(this.dataHex);
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgLocaldappMetadataBinding inflate = FgLocaldappMetadataBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.tvMessageContentDomain = this.binding.messageContentDomain;
        this.tvMessageContentMessage = this.binding.messageContentMessage;
        this.tvMessageContent = this.binding.messageContent;
    }
}
