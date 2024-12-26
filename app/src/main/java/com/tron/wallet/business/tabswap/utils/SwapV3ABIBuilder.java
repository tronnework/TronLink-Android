package com.tron.wallet.business.tabswap.utils;

import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.utils.SwapV3ParamBuild;
import com.tron.wallet.common.utils.GsonUtils;
import java.util.ArrayList;
import java.util.List;
public class SwapV3ABIBuilder {
    public static String buildArgs(SwapInfoOutput swapInfoOutput, int i, SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, String str) {
        List<String> generatePath = SwapV3ParamBuild.generatePath(swapInfoOutput, i);
        List<String> generatePoolVersion = SwapV3ParamBuild.generatePoolVersion(swapInfoOutput, i);
        List<Integer> generateVersionLen = SwapV3ParamBuild.generateVersionLen(swapInfoOutput, i);
        List<Long> generateFees = SwapV3ParamBuild.generateFees(swapInfoOutput, i);
        ArrayList arrayList = new ArrayList();
        SwapV3ParamBuild.SwapTuple generateTuple = SwapV3ParamBuild.generateTuple(swapInfoOutput, i, swapTokenBean, swapTokenBean2, str);
        arrayList.add(generateTuple.getAmountIn());
        arrayList.add(generateTuple.getAmountOut());
        arrayList.add(generateTuple.getToAddress());
        arrayList.add(Long.valueOf(generateTuple.getDeadline()));
        return GsonUtils.toGsonString(generatePath) + "," + GsonUtils.toGsonString(generatePoolVersion) + "," + GsonUtils.toGsonString(generateVersionLen) + "," + GsonUtils.toGsonString(generateFees) + "," + GsonUtils.toGsonString(arrayList);
    }
}
