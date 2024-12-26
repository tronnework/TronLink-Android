package com.tron.wallet.business.transfer.component;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.tron.wallet.business.transfer.selectaddress.PageType;
import com.tron.wallet.business.transfer.selectaddress.ResourceStringProvider;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public class SimilarAddressView extends Fragment {
    static final String URL_CN = "https://tronlinkorg.zendesk.com/hc/zh-cn/articles/13216046504729-%E7%9B%B8%E5%90%8C%E5%B0%BE%E5%8F%B7%E5%9C%B0%E5%9D%80%E9%AA%97%E5%B1%80";
    static final String URL_EN = "https://tronlinkorg.zendesk.com/hc/en-us/articles/13216046504729";
    private Pair<CharSequence, CharSequence> prevSequence;
    private TextView tvDescription;
    private TextView tvInfo;
    private View tvLink;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.layout_similar_address, (ViewGroup) null);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.tvInfo = (TextView) view.findViewById(R.id.tv_info);
        this.tvLink = view.findViewById(R.id.tv_similar_address_link);
        this.tvDescription = (TextView) view.findViewById(R.id.tv_sub_content);
    }

    private void setTextLink() {
        View view = this.tvLink;
        if (view == null) {
            return;
        }
        view.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view2) {
                CommonWebActivityV3.start(view2.getContext(), TextUtils.equals(SpAPI.THIS.useLanguage(), "2") ? SimilarAddressView.URL_CN : SimilarAddressView.URL_EN, getString(R.string.similar_address_link), 0, false);
            }
        });
    }

    public void updateSimilarAddressInfo(final Pair<CharSequence, CharSequence> pair, PageType pageType) {
        int i;
        if (this.tvDescription != null && (i = ResourceStringProvider.getError(pageType).familiarAccount) > 0) {
            this.tvDescription.setText(i);
        }
        setTextLink();
        if (this.tvInfo == null || pair == null || TextUtils.isEmpty((CharSequence) pair.first)) {
            return;
        }
        Pair<CharSequence, CharSequence> pair2 = this.prevSequence;
        if (pair2 != null && TextUtils.equals((CharSequence) pair2.first, (CharSequence) pair.first) && TextUtils.equals((CharSequence) this.prevSequence.second, (CharSequence) pair.second)) {
            return;
        }
        this.tvInfo.setText((CharSequence) pair.first);
        if (TextUtils.isEmpty((CharSequence) pair.second)) {
            return;
        }
        this.tvInfo.post(new Runnable() {
            @Override
            public final void run() {
                lambda$updateSimilarAddressInfo$0(pair);
            }
        });
        this.prevSequence = pair;
    }

    public void lambda$updateSimilarAddressInfo$0(Pair pair) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvInfo, ((CharSequence) pair.second).toString(), true);
        String str = autoSplitText[0];
        this.tvInfo.setText(SpannableUtils.getTextColorSpannable(str, str.lastIndexOf(autoSplitText[1]), autoSplitText[1].length(), getResources().getColor(R.color.yellow_FFA928)));
    }
}
