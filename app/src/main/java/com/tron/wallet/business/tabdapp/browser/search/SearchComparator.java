package com.tron.wallet.business.tabdapp.browser.search;

import com.tron.wallet.business.tabdapp.browser.search.SearchDappResultBean;
import java.util.Comparator;
public class SearchComparator implements Comparator<SearchDappResultBean.SearchDappBean> {
    @Override
    public int compare(SearchDappResultBean.SearchDappBean searchDappBean, SearchDappResultBean.SearchDappBean searchDappBean2) {
        if (searchDappBean.getPriority().getValue() > searchDappBean2.getPriority().getValue()) {
            return -1;
        }
        if (searchDappBean.getPriority().getValue() == searchDappBean2.getPriority().getValue()) {
            if ((searchDappBean.getMatchUrlIndex() != -1 && searchDappBean2.getMatchUrlIndex() != -1 && searchDappBean.getMatchUrlIndex() < searchDappBean2.getMatchUrlIndex()) || (searchDappBean.getMatchUrlIndex() != -1 && searchDappBean2.getMatchUrlIndex() == -1)) {
                return -1;
            }
            if (searchDappBean.getMatchUrlIndex() == searchDappBean2.getMatchUrlIndex() && searchDappBean.getMatchTitleIndex() != -1 && searchDappBean2.getMatchTitleIndex() != -1) {
                if (searchDappBean.getMatchTitleIndex() < searchDappBean2.getMatchTitleIndex()) {
                    return -1;
                }
                if (searchDappBean.getMatchTitleIndex() == searchDappBean2.getMatchTitleIndex()) {
                    if (searchDappBean.getMatchUrlPercent() > searchDappBean2.getMatchUrlPercent()) {
                        return -1;
                    }
                    if (searchDappBean.getMatchUrlPercent() == searchDappBean2.getMatchUrlPercent() && searchDappBean.getMatchTitlePercent() > searchDappBean2.getMatchTitlePercent()) {
                        return -1;
                    }
                }
            }
        }
        return 1;
    }
}
