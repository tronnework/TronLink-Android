package com.tron.wallet.business.confirm.sign;

import com.google.gson.Gson;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.sign.SignTransactionNewContract;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class SignTransactionNewModel implements SignTransactionNewContract.Model {
    private static final int QR_LIMIT_SIZE = 1000;
    private boolean isBiggerThanQrLimitSize;

    @Override
    public boolean isBiggerThanQrLimitSize() {
        return this.isBiggerThanQrLimitSize;
    }

    @Override
    public List<String> subSign(QrBean qrBean) {
        Gson gson;
        int type;
        String alpha;
        String tokenId;
        String address;
        String str;
        Iterator<String> it;
        boolean z;
        boolean qRMultiImageIsOpen;
        int i;
        ArrayList arrayList = new ArrayList();
        try {
            gson = new Gson();
            type = qrBean.getType();
            alpha = qrBean.getAlpha();
            tokenId = qrBean.getTokenId();
            address = qrBean.getAddress();
            str = "";
            while (qrBean.getHexList().iterator().hasNext()) {
                str = str + it.next() + "&&&";
            }
            z = true;
            qRMultiImageIsOpen = SpAPI.THIS.isCold() ? true : SpAPI.THIS.getQRMultiImageIsOpen();
            i = 0;
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (str.length() >= 1000 && qRMultiImageIsOpen) {
            int ceil = (int) Math.ceil(str.length() / 1000.0f);
            int i2 = 0;
            int i3 = 1000;
            while (i3 < str.length()) {
                i3 = str.length() - i > 1000 ? i + 1000 : str.length();
                QrBean qrBean2 = new QrBean();
                i2++;
                qrBean2.setcN(i2);
                qrBean2.settN(ceil);
                if (ceil == i2) {
                    if (!StringTronUtil.isEmpty(alpha)) {
                        qrBean2.setAlpha(alpha);
                    }
                    if (!StringTronUtil.isEmpty(tokenId)) {
                        qrBean2.setTokenId(tokenId);
                    }
                    qrBean2.setType(type);
                }
                if (ceil == i2 || i2 == 1) {
                    qrBean2.setAddress(address);
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(str.substring(i, i3));
                qrBean2.setHexList(arrayList2);
                arrayList.add(gson.toJson(qrBean2));
                i = i3;
            }
            return arrayList;
        }
        if (str.length() < 1000) {
            z = false;
        }
        this.isBiggerThanQrLimitSize = z;
        arrayList.add(gson.toJson(qrBean));
        return arrayList;
    }
}
