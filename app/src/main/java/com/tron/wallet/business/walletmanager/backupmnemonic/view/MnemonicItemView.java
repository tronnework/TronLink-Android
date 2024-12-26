package com.tron.wallet.business.walletmanager.backupmnemonic.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tron.wallet.business.walletmanager.backupmnemonic.bean.MnemonicWord;
import com.tron.wallet.business.walletmanager.backupmnemonic.bean.VerifyItem;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class MnemonicItemView {
    private ClickListener clickListener;
    private String clickWord;
    private final View inflate;
    private VerifyItem item;
    TextView tvItem1;
    TextView tvItem2;
    TextView tvItem3;
    TextView tvNumber;
    private MnemonicWord word1;
    private MnemonicWord word2;
    private MnemonicWord word3;

    public interface ClickListener {
        void onClick();
    }

    public View getView() {
        return this.inflate;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public MnemonicItemView(Context context, VerifyItem verifyItem, ViewGroup viewGroup) {
        this.item = verifyItem;
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_verify_mnemonic, viewGroup, false);
        this.inflate = inflate;
        this.tvNumber = (TextView) inflate.findViewById(R.id.tv_number);
        this.tvItem1 = (TextView) inflate.findViewById(R.id.tv_item1);
        this.tvItem2 = (TextView) inflate.findViewById(R.id.tv_item2);
        this.tvItem3 = (TextView) inflate.findViewById(R.id.tv_item3);
        onBind(verifyItem);
    }

    private void onBind(VerifyItem verifyItem) {
        if (verifyItem == null) {
            return;
        }
        this.tvNumber.setText(String.format(StringTronUtil.getResString(R.string.create_wallet_hint_11), Integer.valueOf(verifyItem.getNum() + 1)));
        if (verifyItem.getWords() == null || verifyItem.getWords().size() < 3) {
            return;
        }
        List<MnemonicWord> words = verifyItem.getWords();
        MnemonicWord mnemonicWord = words.get(0);
        this.word1 = mnemonicWord;
        this.tvItem1.setText(mnemonicWord.word);
        this.tvItem1.setSelected(false);
        this.word2 = words.get(1);
        this.tvItem2.setText(words.get(1).word);
        this.tvItem2.setSelected(false);
        MnemonicWord mnemonicWord2 = words.get(2);
        this.word3 = mnemonicWord2;
        this.tvItem3.setText(mnemonicWord2.word);
        this.tvItem3.setSelected(false);
        this.tvItem1.setOnClickListener(getOnClickListener());
        this.tvItem2.setOnClickListener(getOnClickListener());
        this.tvItem3.setOnClickListener(getOnClickListener());
    }

    public void refreshData(VerifyItem verifyItem) {
        this.item = verifyItem;
        this.clickWord = "";
        onBind(verifyItem);
    }

    public boolean check() {
        VerifyItem verifyItem;
        return (StringTronUtil.isEmpty(this.clickWord) || (verifyItem = this.item) == null || !this.clickWord.equals(verifyItem.getAnswer())) ? false : true;
    }

    public boolean isClick() {
        return !StringTronUtil.isEmpty(this.clickWord);
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                resetStatus();
                switch (view.getId()) {
                    case R.id.tv_item1:
                        tvItem1.setSelected(true);
                        MnemonicItemView mnemonicItemView = MnemonicItemView.this;
                        mnemonicItemView.clickWord = mnemonicItemView.word1.word;
                        break;
                    case R.id.tv_item2:
                        tvItem2.setSelected(true);
                        MnemonicItemView mnemonicItemView2 = MnemonicItemView.this;
                        mnemonicItemView2.clickWord = mnemonicItemView2.word2.word;
                        break;
                    case R.id.tv_item3:
                        tvItem3.setSelected(true);
                        MnemonicItemView mnemonicItemView3 = MnemonicItemView.this;
                        mnemonicItemView3.clickWord = mnemonicItemView3.word3.word;
                        break;
                }
                if (clickListener != null) {
                    clickListener.onClick();
                }
            }
        };
    }

    public void resetStatus() {
        this.tvItem1.setSelected(false);
        this.tvItem2.setSelected(false);
        this.tvItem3.setSelected(false);
    }
}
