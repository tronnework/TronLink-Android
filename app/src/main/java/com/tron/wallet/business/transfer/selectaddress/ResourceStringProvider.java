package com.tron.wallet.business.transfer.selectaddress;

import android.content.Context;
import com.tronlinkpro.wallet.R;
public class ResourceStringProvider {

    public static class ErrorString {
        public int familiarAccount;
        public int multiSignAccount;
    }

    static class ResourceString {
        String title = "";
        int tabRecent = 0;
        int tabEmptyRecent = 0;

        ResourceString() {
        }
    }

    public static ResourceString getPageTitle(Context context, PageType pageType) {
        ResourceString resourceString = new ResourceString();
        if (pageType == PageType.TRANSFER) {
            resourceString.title = context.getString(R.string.send);
            resourceString.tabRecent = R.string.recently_send;
            resourceString.tabEmptyRecent = R.string.no_send_record;
        } else if (pageType == PageType.DELEGATE_BANDWIDTH) {
            resourceString.title = context.getString(R.string.delegate_bandwidth);
            resourceString.tabRecent = R.string.recent_delegate;
            resourceString.tabEmptyRecent = R.string.no_delegate_record;
        } else if (pageType == PageType.DELEGATE_ENERGY) {
            resourceString.title = context.getString(R.string.delegate_energy);
            resourceString.tabRecent = R.string.recent_delegate;
            resourceString.tabEmptyRecent = R.string.no_delegate_record;
        }
        return resourceString;
    }

    public static ErrorString getError(PageType pageType) {
        ErrorString errorString = new ErrorString();
        if (pageType == PageType.TRANSFER) {
            errorString.multiSignAccount = R.string.permission_warning_multi_sign;
            errorString.familiarAccount = R.string.similar_address_warning_content;
        } else if (pageType == PageType.DELEGATE_BANDWIDTH || pageType == PageType.DELEGATE_ENERGY) {
            errorString.multiSignAccount = R.string.permission_warning_multi_sign_delegate;
            errorString.familiarAccount = R.string.similar_address_warning_content_delegate;
        }
        return errorString;
    }
}
