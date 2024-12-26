package com.tron.wallet.business.walletmanager.backupmnemonic;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.tabmy.walletmanage.MnemonicIndexView;
import com.tron.wallet.business.walletmanager.CreateSuccessActivity;
import com.tron.wallet.business.walletmanager.backupmnemonic.bean.MnemonicWord;
import com.tron.wallet.business.walletmanager.backupmnemonic.bean.VerifyItem;
import com.tron.wallet.business.walletmanager.backupmnemonic.view.MnemonicItemView;
import com.tron.wallet.business.walletmanager.createwallet.view.TimelineView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.AcVerifyMnemonicBinding;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import org.tron.common.bip39.BIP39;
public class VerifyMnemonicActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AcVerifyMnemonicBinding binding;
    TextView btNext;
    private boolean fromCreate;
    MnemonicIndexView mGridIndexView;
    private String[] mMnemonicList;
    TimelineView timelineView;
    LinearLayout verifyContentView;
    private String walletName;
    private List<Integer> randomIndexList = new ArrayList();
    private List<MnemonicItemView> itemViewList = new ArrayList();
    RxManager mRxManager = new RxManager();
    private List<VerifyItem> verifyItemList = new ArrayList();

    public static void start(Context context, String str, String str2, boolean z) {
        Intent intent = new Intent(context, VerifyMnemonicActivity.class);
        intent.putExtra(TronConfig.WALLET_extra, str2);
        intent.putExtra(TronConfig.WALLET_DATA, str);
        intent.putExtra(TronConfig.WALLET_DATA2, z);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcVerifyMnemonicBinding inflate = AcVerifyMnemonicBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setHeaderBarAndRightTv(getString(R.string.verify_mnemonic_title), getString(R.string.review_mnemonic));
    }

    public void mappingId() {
        this.btNext = this.binding.btNext;
        this.mGridIndexView = this.binding.indexGrid;
        this.verifyContentView = this.binding.verifyContent;
        this.timelineView = this.binding.timeLineLayout;
    }

    @Override
    protected void processData() {
        this.timelineView.setStepTwo();
        this.timelineView.setWhiteBg();
        String stringExtra = getIntent().getStringExtra(TronConfig.WALLET_extra);
        this.walletName = getIntent().getStringExtra(TronConfig.WALLET_DATA);
        this.fromCreate = getIntent().getBooleanExtra(TronConfig.WALLET_DATA2, false);
        splitMnemonics(stringExtra);
        addItemView();
        setButton();
        if (this.fromCreate) {
            return;
        }
        this.timelineView.setVisibility(View.GONE);
    }

    public void addItemView() {
        randomWords();
        this.mGridIndexView.updateSelectedChildes(this.randomIndexList);
        if (this.verifyItemList.size() != 3) {
            return;
        }
        for (int i = 0; i < this.verifyItemList.size(); i++) {
            VerifyItem verifyItem = this.verifyItemList.get(i);
            if (this.itemViewList.size() == 3) {
                this.itemViewList.get(i).refreshData(verifyItem);
            } else {
                MnemonicItemView mnemonicItemView = new MnemonicItemView(this.mContext, verifyItem, this.verifyContentView);
                mnemonicItemView.setClickListener(new MnemonicItemView.ClickListener() {
                    @Override
                    public final void onClick() {
                        lambda$addItemView$0();
                    }
                });
                this.verifyContentView.addView(mnemonicItemView.getView(), i);
                this.itemViewList.add(mnemonicItemView);
            }
        }
    }

    public void setButton() {
        this.btNext.setEnabled(false);
        this.btNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (itemViewList.size() != 3) {
                    return;
                }
                int i = 0;
                for (MnemonicItemView mnemonicItemView : itemViewList) {
                    if (mnemonicItemView.check()) {
                        i++;
                    }
                }
                if (i == 3) {
                    setBackUpState();
                    mRxManager.post(Event.BACKUP, Event.BACKUP);
                    if (fromCreate) {
                        toSuccess();
                        return;
                    } else {
                        toMain();
                        return;
                    }
                }
                addItemView();
                btNext.setEnabled(false);
                ToastError(R.string.select_error);
            }
        });
    }

    public void lambda$addItemView$0() {
        if (this.itemViewList.size() != 3) {
            return;
        }
        int i = 0;
        for (MnemonicItemView mnemonicItemView : this.itemViewList) {
            if (mnemonicItemView.isClick()) {
                i++;
            }
        }
        if (i == 3) {
            this.btNext.setEnabled(true);
        } else {
            this.btNext.setEnabled(false);
        }
    }

    public void setBackUpState() {
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$setBackUpState$2();
            }
        });
    }

    public void lambda$setBackUpState$2() {
        Collection.-EL.stream(HDTronWalletController.queryWalletRelationship(this.walletName)).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                SpAPI.THIS.setBackUp((String) obj, true);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public void randomWords() {
        this.verifyItemList.clear();
        this.randomIndexList.clear();
        do {
            int nextInt = new Random().nextInt(12);
            if (!this.randomIndexList.contains(Integer.valueOf(nextInt))) {
                this.randomIndexList.add(Integer.valueOf(nextInt));
            }
        } while (this.randomIndexList.size() != 3);
        Collections.sort(this.randomIndexList);
        getWrongRandomEnglish();
        if (this.verifyItemList.size() == 3 && this.randomIndexList.size() == 3) {
            for (int i = 0; i < this.verifyItemList.size(); i++) {
                VerifyItem verifyItem = this.verifyItemList.get(i);
                int intValue = this.randomIndexList.get(i).intValue();
                verifyItem.setNum(intValue);
                verifyItem.setAnswer(this.mMnemonicList[intValue]);
                int nextInt2 = new Random().nextInt(3);
                verifyItem.getWords().get(nextInt2).word = this.mMnemonicList[intValue];
            }
        }
    }

    public void splitMnemonics(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.mMnemonicList = str.split("\\s+");
                return;
            } catch (Exception e) {
                LogUtils.e(e);
                return;
            }
        }
        toast("Error: couldn't generate recovery phrase");
        finish();
    }

    private void getWrongRandomEnglish() {
        for (int i = 0; i < 3 && this.verifyItemList.size() != 3; i++) {
            VerifyItem verifyItem = new VerifyItem();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < 3; i2++) {
                getWrongWord(arrayList);
            }
            verifyItem.setWords(arrayList);
            this.verifyItemList.add(verifyItem);
        }
    }

    private void getWrongWord(List<MnemonicWord> list) {
        addWord(BIP39.english[new Random().nextInt(BIP39.english.length - 1)], list);
    }

    private void addWord(String str, List<MnemonicWord> list) {
        if (!isContainWord(str, list)) {
            MnemonicWord mnemonicWord = new MnemonicWord();
            mnemonicWord.word = str;
            list.add(mnemonicWord);
            return;
        }
        getWrongWord(list);
    }

    private boolean isContainWord(String str, List<MnemonicWord> list) {
        if (str == null) {
            return false;
        }
        for (MnemonicWord mnemonicWord : list) {
            if (str.equals(mnemonicWord.word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onLeftButtonClick() {
        finish();
    }

    @Override
    public void onRightTextClick() {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.mRxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
        this.binding = null;
    }

    public void toSuccess() {
        CreateSuccessActivity.start(this.mContext, "", "", true);
        exit();
    }

    public void toMain() {
        Intent intent = new Intent(this.mContext, MainTabActivity.class);
        intent.setFlags(67108864);
        if (getIntent().getBooleanExtra(TronConfig.WALLET_DATA2, false)) {
            intent.putExtra(TronConfig.SHIELD_IS_TRC20, true);
        }
        go(intent);
        exit();
    }
}
