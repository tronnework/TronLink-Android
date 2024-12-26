package com.tron.wallet.business.reminder;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.reminder.bean.DealBean;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.InfoBean;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import j$.util.Comparator;
import j$.util.List$-CC;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToIntFunction;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.time.DateUtils;
public class PushMessageReminder extends BaseReminder {
    private static final int HOUR_TO_MSEC = 3600000;
    public static final String ID = "PushMessageReminder";
    private InfoBean infoBean;
    private InfoBean.DataBean target;

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void start() {
        WalletUtils.getSelectedWallet();
        ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getInfo().compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<InfoBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str, InfoBean infoBean) {
                ReminderState reminderState = ReminderState.UNNEEDED_SHOW;
                if (infoBean != null && infoBean.getCode() == 0 && infoBean.getData() != null && infoBean.getData().size() != 0) {
                    infoBean = infoBean;
                    String pushMessageData = SpAPI.THIS.getPushMessageData();
                    if (!TextUtils.isEmpty(pushMessageData)) {
                        try {
                            List parseArray = JSON.parseArray(pushMessageData, InfoBean.DataBean.class);
                            for (InfoBean.DataBean dataBean : infoBean.getData()) {
                                Iterator it = parseArray.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        InfoBean.DataBean dataBean2 = (InfoBean.DataBean) it.next();
                                        if (dataBean2.getPlayId() == dataBean.getPlayId()) {
                                            dataBean.setLastShowTimestamp(dataBean2.getLastShowTimestamp());
                                            dataBean.setShowCounter(dataBean2.getShowCounter());
                                            break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                    }
                    PushMessageReminder pushMessageReminder = PushMessageReminder.this;
                    pushMessageReminder.target = pushMessageReminder.findReminderData(infoBean.getData());
                    if (target != null && System.currentTimeMillis() - target.getLastShowTimestamp() >= target.getFrequencyHour() * DateUtils.MILLIS_PER_HOUR) {
                        reminderState = ReminderState.NEED_SHOW;
                        target.setShowCounter(target.getShowCounter() + 1);
                        target.setLastShowTimestamp(System.currentTimeMillis());
                    }
                    SpAPI.THIS.setPushMessageData(JSON.toJSONString(infoBean.getData()));
                }
                setState(reminderState);
            }

            @Override
            public void onFailure(String str, String str2) {
                try {
                    List parseArray = JSON.parseArray(SpAPI.THIS.getPushMessageData(), InfoBean.DataBean.class);
                    PushMessageReminder pushMessageReminder = PushMessageReminder.this;
                    pushMessageReminder.target = pushMessageReminder.findReminderData(parseArray);
                    if (target == null) {
                        setState(ReminderState.UNNEEDED_SHOW);
                    } else if (System.currentTimeMillis() - target.getLastShowTimestamp() <= target.getPlayLimit() * DateUtils.MILLIS_PER_HOUR) {
                        setState(ReminderState.UNNEEDED_SHOW);
                    } else {
                        setState(ReminderState.NEED_SHOW);
                        target.setLastShowTimestamp(System.currentTimeMillis());
                        target.setShowCounter(target.getShowCounter() + 1);
                        SpAPI.THIS.setPushMessageData(JSON.toJSONString(parseArray));
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                    setState(ReminderState.UNNEEDED_SHOW);
                }
            }
        }, ID));
    }

    @Nullable
    public InfoBean.DataBean findReminderData(List<InfoBean.DataBean> list) {
        if (list.isEmpty()) {
            return null;
        }
        List$-CC.$default$sort(list, Collections.reverseOrder(Comparator.-CC.comparingInt(new ToIntFunction() {
            @Override
            public final int applyAsInt(Object obj) {
                return ((InfoBean.DataBean) obj).getPlayId();
            }
        })));
        for (InfoBean.DataBean dataBean : list) {
            if (dataBean.getShowCounter() < dataBean.getPlayLimit()) {
                return dataBean;
            }
        }
        return null;
    }

    public void report(int i) {
        DealBean dealBean = new DealBean();
        dealBean.setPlayId(i);
        ((HttpAPI) IRequest.getAPI(HttpAPI.class)).deal(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(dealBean))).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<InfoBean>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, InfoBean infoBean) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }
        }, ID));
    }

    @Override
    public void show(final Context context) {
        if (this.target == null) {
            return;
        }
        super.show(context);
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(false).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.usdt_dialog).build();
        build.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public final void onDismiss(DialogInterface dialogInterface) {
                lambda$show$0(dialogInterface);
            }
        });
        View view = builder.getView();
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.iv_pic);
        View findViewById = view.findViewById(R.id.iv_close);
        InfoBean.DataBean dataBean = this.target;
        final int playId = dataBean.getPlayId();
        final String urlLink = dataBean.getUrlLink();
        try {
            simpleDraweeView.setImageURI(Uri.parse(dataBean.getImageLink()));
        } catch (Exception e) {
            LogUtils.e(e);
        }
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                FirebaseAnalytics.getInstance(context).logEvent("Click_Popup", null);
                report(playId);
                build.dismiss();
                if (StringTronUtil.isEmpty(urlLink)) {
                    return;
                }
                CommonWebActivityV3.start(context, urlLink, "", -2, true);
            }
        });
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                FirebaseAnalytics.getInstance(context).logEvent("Close_Popup", null);
                report(playId);
                build.dismiss();
            }
        });
        build.show();
        build.setCanceledOnTouchOutside(false);
    }

    public void lambda$show$0(DialogInterface dialogInterface) {
        setState(ReminderState.HAS_SHOW);
    }
}
