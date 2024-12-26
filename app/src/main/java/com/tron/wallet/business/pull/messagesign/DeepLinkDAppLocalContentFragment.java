package com.tron.wallet.business.pull.messagesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.databinding.FgLocaldappDetailBinding;
import java.util.ArrayList;
import org.tron.common.utils.ByteArray;
public class DeepLinkDAppLocalContentFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    public static final String TYPE_SIGN_712 = "TYPE_SIGN_712";
    public static final String TYPE_SIGN_HEX = "TYPE_SIGN_HEX";
    public static final String TYPE_SIGN_NON_HEX = "TYPE_SIGN_NON_HEX";
    private ArrayList<String> addressParseList;
    private FgLocaldappDetailBinding binding;
    View layoutTips;
    private String messageString;
    private String signType;
    LinearLayout tvMessageContent;
    private String unSignString;

    public static DeepLinkDAppLocalContentFragment getInstance(String str, String str2) {
        DeepLinkDAppLocalContentFragment deepLinkDAppLocalContentFragment = new DeepLinkDAppLocalContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ArgumentKeys.KEY_SIGN_STRING, str);
        bundle.putString(ArgumentKeys.KEY_SIGN_TYPE, str2);
        deepLinkDAppLocalContentFragment.setArguments(bundle);
        return deepLinkDAppLocalContentFragment;
    }

    public static DeepLinkDAppLocalContentFragment getInstance(String str, String str2, String str3, ArrayList<String> arrayList) {
        DeepLinkDAppLocalContentFragment deepLinkDAppLocalContentFragment = new DeepLinkDAppLocalContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ArgumentKeys.KEY_SIGN_STRING, str);
        bundle.putString(ArgumentKeys.KEY_MESSAGE_STRING, str2);
        bundle.putString(ArgumentKeys.KEY_SIGN_TYPE, str3);
        bundle.putStringArrayList(ArgumentKeys.KEY_ADDRESS_LIST, arrayList);
        deepLinkDAppLocalContentFragment.setArguments(bundle);
        return deepLinkDAppLocalContentFragment;
    }

    @Override
    protected void processData() {
        byte[] fromHexString;
        this.unSignString = getArguments().getString(ArgumentKeys.KEY_SIGN_STRING);
        this.messageString = getArguments().getString(ArgumentKeys.KEY_MESSAGE_STRING);
        this.signType = getArguments().getString(ArgumentKeys.KEY_SIGN_TYPE);
        this.addressParseList = getArguments().getStringArrayList(ArgumentKeys.KEY_ADDRESS_LIST);
        TypeDataParser typeDataParser = new TypeDataParser();
        String str = this.signType;
        if (str == "TYPE_SIGN_712") {
            try {
                for (TextView textView : typeDataParser.parserString(this.messageString, this.addressParseList)) {
                    this.tvMessageContent.addView(textView);
                }
                return;
            } catch (Exception e) {
                LogUtils.e(e);
                return;
            }
        }
        if (str == "TYPE_SIGN_HEX" && ((fromHexString = ByteArray.fromHexString(this.unSignString)) == null || fromHexString.length == 32)) {
            this.layoutTips.setVisibility(View.VISIBLE);
        }
        TextView newTextView = typeDataParser.getNewTextView();
        newTextView.setText(this.unSignString);
        this.tvMessageContent.addView(newTextView);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgLocaldappDetailBinding inflate = FgLocaldappDetailBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.tvMessageContent = this.binding.messageContent;
        this.layoutTips = this.binding.layoutTips;
    }
}
