package com.tron.wallet.business.mutil;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.widget.EditText;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.mutil.MultiSelectAddressContract;
import com.tron.wallet.business.mutil.bean.MultiAddressOutput;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.transfer.selectaddress.SelectAddressModel;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.Result2;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tron.walletserver.Wallet;
public class MultiSelectAddressPresenter extends MultiSelectAddressContract.Presenter {
    private Wallet selectedPublicWallet;
    private List<NameAddressExtraBean> httpMultiResultList = new ArrayList();
    private Map<String, MultiAddressOutput> checkPermissionAddressMap = new HashMap();
    private SelectAddressModel selectAddressModel = new SelectAddressModel();
    private Map<String, String> walletNameMap = new HashMap();

    @Override
    protected void onStart() {
        this.selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        this.mRxManager.on(Event.TRANSFER_INNER, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
        this.mRxManager.on(Event.BackToHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$2(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (this.mView != 0) {
            ((MultiSelectAddressContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        if (this.mView != 0) {
            ((MultiSelectAddressContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        if (this.mView != 0) {
            ((MultiSelectAddressContract.View) this.mView).exit();
        }
    }

    @Override
    public void getMultiAddress() {
        String address = this.selectedPublicWallet.getAddress();
        if (SpAPI.THIS.getCurIsMainChain()) {
            Observable.zip(((MultiSelectAddressContract.Model) this.mModel).getMultiAddress(address), ((MultiSelectAddressContract.Model) this.mModel).getAddressName(), new BiFunction() {
                @Override
                public final Object apply(Object obj, Object obj2) {
                    Pair lambda$getMultiAddress$3;
                    lambda$getMultiAddress$3 = lambda$getMultiAddress$3((Result2) obj, (Map) obj2);
                    return lambda$getMultiAddress$3;
                }
            }).subscribe(new IObserver(new ICallback<Pair<List<NameAddressExtraBean>, Map<String, MultiAddressOutput>>>() {
                @Override
                public void onResponse(String str, Pair<List<NameAddressExtraBean>, Map<String, MultiAddressOutput>> pair) {
                    List<NameAddressExtraBean> list = (List) pair.first;
                    checkPermissionAddressMap = (Map) pair.second;
                    if (list != null && !list.isEmpty()) {
                        httpMultiResultList = list;
                    }
                    ((MultiSelectAddressContract.View) mView).showDataView(list);
                }

                @Override
                public void onFailure(String str, String str2) {
                    ((MultiSelectAddressContract.View) mView).ToastError(R.string.time_out);
                    ((MultiSelectAddressContract.View) mView).showDataView(new ArrayList());
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "getMultiAddress"));
        }
    }

    public Pair lambda$getMultiAddress$3(Result2 result2, Map map) throws Exception {
        this.walletNameMap = map;
        List arrayList = new ArrayList();
        if (result2.getCode() == 0 && result2.getData() != null) {
            arrayList = (List) result2.getData();
        }
        boolean checkCurrentAccountPermission = ((MultiSelectAddressContract.Model) this.mModel).checkCurrentAccountPermission(this.selectedPublicWallet.getAddress(), this.selectedPublicWallet.getWalletName());
        Pair<List<NameAddressExtraBean>, Map<String, MultiAddressOutput>> fromMultiAddressOutput = NameAddressExtraBean.fromMultiAddressOutput(arrayList, map);
        if (checkCurrentAccountPermission) {
            MultiAddressOutput multiAddressOutput = new MultiAddressOutput();
            multiAddressOutput.setOwnerAddress(this.selectedPublicWallet.getAddress());
            MultiAddressOutput.PermissionOutput permissionOutput = new MultiAddressOutput.PermissionOutput();
            permissionOutput.setHas(true);
            multiAddressOutput.setOwnerPermission(permissionOutput);
            ((Map) fromMultiAddressOutput.second).put(this.selectedPublicWallet.getAddress(), multiAddressOutput);
        }
        return fromMultiAddressOutput;
    }

    @Override
    public String getPasteString() {
        CharSequence text;
        ClipData primaryClip = ((ClipboardManager) ((MultiSelectAddressContract.View) this.mView).getIContext().getSystemService(Context.CLIPBOARD_SERVICE)).getPrimaryClip();
        return (primaryClip == null || primaryClip.getItemCount() <= 0 || (text = primaryClip.getItemAt(0).getText()) == null) ? "" : text.toString();
    }

    @Override
    public void addTextChangedListener(final EditText editText) {
        editText.addTextChangedListener(new BaseTextWatcher(new BaseTextWatcher.AfterTextChangedCallback() {
            @Override
            public void afterTextChanged(Editable editable, TextWatcher textWatcher) {
                String inputAddress = ((MultiSelectAddressContract.View) mView).getInputAddress(editable.toString().trim());
                ((MultiSelectAddressContract.View) mView).updateEdiTextStatus(inputAddress);
                boolean considerNoInput = selectAddressModel.considerNoInput(inputAddress);
                int i = !TextUtils.isEmpty(inputAddress) ? 1 : 0;
                ((MultiSelectAddressContract.View) mView).showEdiTextError(false);
                editText.setTypeface(Typeface.defaultFromStyle(i));
                if (considerNoInput) {
                    ((MultiSelectAddressContract.View) mView).showDataView();
                } else if (!StringTronUtil.isAddressValid(inputAddress)) {
                    ((MultiSelectAddressContract.View) mView).showSearchView(doSearch(inputAddress));
                } else {
                    ((MultiSelectAddressContract.View) mView).showDataView();
                    Pair<Boolean, MultiAddressOutput> checkButtonStatus = checkButtonStatus(inputAddress);
                    boolean booleanValue = ((Boolean) checkButtonStatus.first).booleanValue();
                    MultiAddressOutput multiAddressOutput = (MultiAddressOutput) checkButtonStatus.second;
                    if (booleanValue) {
                        if (((MultiSelectAddressContract.View) mView).getMultiSourcePageEnum() != MultiSourcePageEnum.Vote && ((MultiSelectAddressContract.View) mView).getMultiSourcePageEnum() != MultiSourcePageEnum.Resources && ((MultiSelectAddressContract.View) mView).getMultiSourcePageEnum() != MultiSourcePageEnum.StakeV2) {
                            ((MultiSelectAddressContract.View) mView).showEdiTextError(false);
                        } else {
                            ((MultiSelectAddressContract.View) mView).showEdiTextError(true, multiAddressOutput);
                        }
                    } else {
                        ((MultiSelectAddressContract.View) mView).showEdiTextError(true, multiAddressOutput);
                    }
                }
                checkButtonStatus(inputAddress);
            }
        }));
    }

    @Override
    public MultiAddressOutput getMultiAddressOutputByAddress(String str) {
        if (str == null) {
            return null;
        }
        return this.checkPermissionAddressMap.get(str);
    }

    @Override
    public NameAddressExtraBean createNameAddressExtraBean(String str) {
        if (this.walletNameMap.containsKey(str)) {
            NameAddressExtraBean nameAddressExtraBean = new NameAddressExtraBean();
            nameAddressExtraBean.setAddress(str);
            nameAddressExtraBean.setIndentAddress(StringTronUtil.indentAddress(str, 6));
            nameAddressExtraBean.setName(this.walletNameMap.get(str));
            return nameAddressExtraBean;
        }
        return null;
    }

    public Pair<Boolean, MultiAddressOutput> checkButtonStatus(String str) {
        if (!SpAPI.THIS.getCurIsMainChain() && StringTronUtil.isAddressValid(str)) {
            ((MultiSelectAddressContract.View) this.mView).setButtonStatus(true);
            return new Pair<>(true, null);
        } else if (this.checkPermissionAddressMap.containsKey(str)) {
            MultiAddressOutput multiAddressOutput = this.checkPermissionAddressMap.get(str);
            if (multiAddressOutput.getOwnerPermission() != null && multiAddressOutput.getOwnerPermission().isHas()) {
                ((MultiSelectAddressContract.View) this.mView).setButtonStatus(true);
                return new Pair<>(true, multiAddressOutput);
            } else if (checkPermission(((MultiSelectAddressContract.View) this.mView).getMultiSourcePageEnum(), multiAddressOutput)) {
                ((MultiSelectAddressContract.View) this.mView).setButtonStatus(true);
                return new Pair<>(true, multiAddressOutput);
            } else {
                ((MultiSelectAddressContract.View) this.mView).setButtonStatus(false);
                return new Pair<>(false, multiAddressOutput);
            }
        } else {
            ((MultiSelectAddressContract.View) this.mView).setButtonStatus(false);
            return new Pair<>(false, null);
        }
    }

    public List<NameAddressExtraBean> doSearch(final String str) {
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(this.httpMultiResultList).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$doSearch$4(str, arrayList, (NameAddressExtraBean) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        Collections.sort(arrayList, new Comparator() {
            @Override
            public final int compare(Object obj, Object obj2) {
                return MultiSelectAddressPresenter.lambda$doSearch$5((NameAddressExtraBean) obj, (NameAddressExtraBean) obj2);
            }
        });
        return arrayList;
    }

    public void lambda$doSearch$4(String str, List list, NameAddressExtraBean nameAddressExtraBean) {
        NameAddressExtraBean copy = nameAddressExtraBean.copy();
        this.selectAddressModel.appendMatchedResult(str, list, copy, copy.getName() == null ? "" : copy.getName().toString(), copy.getAddress() == null ? "" : copy.getAddress().toString());
    }

    public static int lambda$doSearch$5(NameAddressExtraBean nameAddressExtraBean, NameAddressExtraBean nameAddressExtraBean2) {
        return nameAddressExtraBean2.getPriority().ordinal() - nameAddressExtraBean.getPriority().ordinal();
    }

    public static class fun3 {
        static final int[] $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum;

        static {
            int[] iArr = new int[MultiSourcePageEnum.values().length];
            $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum = iArr;
            try {
                iArr[MultiSourcePageEnum.Stake.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.UnStake.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.Vote.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.Resources.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.Transfer.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.StakeV2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public boolean checkPermission(com.tron.wallet.business.mutil.bean.MultiSourcePageEnum r3, com.tron.wallet.business.mutil.bean.MultiAddressOutput r4) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.mutil.MultiSelectAddressPresenter.checkPermission(com.tron.wallet.business.mutil.bean.MultiSourcePageEnum, com.tron.wallet.business.mutil.bean.MultiAddressOutput):boolean");
    }
}
