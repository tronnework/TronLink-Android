package com.tron.wallet.common.utils;

import android.text.TextUtils;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class AddressNameUtils {
    private static AddressNameUtils instance;

    private AddressNameUtils() {
    }

    public static AddressNameUtils getInstance() {
        if (instance == null) {
            synchronized (AddressNameUtils.class) {
                if (instance == null) {
                    instance = new AddressNameUtils();
                }
            }
        }
        return instance;
    }

    public static String getFormatAddress(String str) {
        String nameByAddress = getInstance().getNameByAddress(str);
        return !TextUtils.isEmpty(nameByAddress) ? String.format(String.format("%s\n(%s)", nameByAddress, str), new Object[0]) : str;
    }

    public static String getFormatAddressSingleLine(String str) {
        String nameByAddress = getInstance().getNameByAddress(str);
        return !TextUtils.isEmpty(nameByAddress) ? String.format(String.format("%s(%s)", nameByAddress, str), new Object[0]) : str;
    }

    public static String getFormatAddress(String str, boolean z) {
        String nameByAddress = getInstance().getNameByAddress(str, z);
        return !TextUtils.isEmpty(nameByAddress) ? String.format(String.format("%s(%s)", nameByAddress, str), new Object[0]) : str;
    }

    public static void setMultiAddressTip(final TextView textView, final String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            str = String.format("%s (%s)", str2, str);
        }
        textView.setText(textView.getContext().getResources().getString(R.string.multi_controller_tips, str));
        textView.post(new Runnable() {
            @Override
            public final void run() {
                AddressNameUtils.lambda$setMultiAddressTip$0(textView, str);
            }
        });
        textView.setVisibility(View.VISIBLE);
    }

    public static void lambda$setMultiAddressTip$0(TextView textView, String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(textView, str);
        textView.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], textView.getContext().getResources().getColor(R.color.black_23)));
    }

    public String getNameByAddress(String str) {
        return getNameByAddress(str, true);
    }

    public String getNameByAddress(String str, boolean z) {
        if (str == null) {
            return "";
        }
        String loadFromAddressBook = loadFromAddressBook(str, z);
        return TextUtils.isEmpty(loadFromAddressBook) ? loadFromSp(str, z) : loadFromAddressBook;
    }

    private String loadFromSp(String str, boolean z) {
        try {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet == null || !TextUtils.equals(str, selectedPublicWallet.getAddress())) {
                Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
                return walletForAddress == null ? "" : walletForAddress.getWalletName();
            } else if (z) {
                return AppContextUtil.getContext().getString(R.string.current_account_address);
            } else {
                return selectedPublicWallet.getWalletName();
            }
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }

    private String loadFromAddressBook(String str, boolean z) {
        if (z) {
            try {
                Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                if (selectedPublicWallet != null && TextUtils.equals(str, selectedPublicWallet.getAddress())) {
                    return AppContextUtil.getContext().getString(R.string.current_account_address);
                }
            } catch (Exception e) {
                LogUtils.e(e);
                return "";
            }
        }
        String nameByAddress = AddressController.getInstance(AppContextUtil.getContext()).getNameByAddress(str);
        return nameByAddress == null ? "" : nameByAddress;
    }
}
