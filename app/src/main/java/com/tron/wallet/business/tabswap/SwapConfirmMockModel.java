package com.tron.wallet.business.tabswap;

import com.tron.wallet.business.tabswap.SwapConfirmMockContract;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.mvp.SwapModel;
import java.util.List;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SwapConfirmMockModel implements SwapConfirmMockContract.Model {
    @Override
    public List<Protocol.Transaction> submitSwap(SwapInfoOutput swapInfoOutput, int i, SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, Wallet wallet) {
        return new SwapModel().submitSwap(swapInfoOutput, i, swapTokenBean, swapTokenBean2, wallet);
    }
}
