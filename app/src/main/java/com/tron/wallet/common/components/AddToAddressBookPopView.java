package com.tron.wallet.common.components;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.wallet.business.migrate.MigrateActivityExternalSyntheticLambda2;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AddToAddressBookBinding;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
public class AddToAddressBookPopView extends CenterPopupView {
    private static final int MAX_NAME_BYTE_COUNT = 20;
    private static final int MAX_NOTE_BYTE_COUNT = 60;
    private final String address;
    private final AddressBookChangeCallback addressBookChangeCallback;
    AddToAddressBookBinding binding;
    private CompositeDisposable disposables;
    ErrorView errorView;
    TrimEditText etInputName;
    TrimEditText etInputNote;
    ImageView ivClearName;
    ImageView ivClearNote;
    TextView tvAddress;
    TextView tvCancel;
    TextView tvConfirm;

    public interface AddressBookChangeCallback {
        void onAddressBookChanged(String str, String str2, String str3);
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.add_to_address_book;
    }

    public AddToAddressBookPopView(Context context, String str, AddressBookChangeCallback addressBookChangeCallback) {
        super(context);
        this.address = str;
        this.disposables = new CompositeDisposable();
        this.addressBookChangeCallback = addressBookChangeCallback;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.binding = AddToAddressBookBinding.bind(getPopupImplView());
        mappingId();
        if (!TextUtils.isEmpty(this.address)) {
            this.tvAddress.setText(this.address);
        }
        initClickEvent();
    }

    private void mappingId() {
        this.tvAddress = this.binding.tvAddress;
        this.etInputName = this.binding.etInputName;
        this.etInputNote = this.binding.etInputNote;
        this.tvCancel = this.binding.tvCancel;
        this.tvConfirm = this.binding.tvOk;
        this.errorView = this.binding.errorView;
        this.ivClearName = this.binding.ivClearName;
        this.ivClearNote = this.binding.ivClearNote;
    }

    private void initClickEvent() {
        this.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initClickEvent$0(view);
            }
        });
        this.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initClickEvent$3(view);
            }
        });
        this.etInputName.addTextChangedListener(new BaseTextWatcher(new BaseTextWatcher.AfterTextChangedCallback() {
            @Override
            public final void afterTextChanged(Editable editable, TextWatcher textWatcher) {
                lambda$initClickEvent$4(editable, textWatcher);
            }
        }));
        this.etInputNote.addTextChangedListener(new BaseTextWatcher(new BaseTextWatcher.AfterTextChangedCallback() {
            @Override
            public final void afterTextChanged(Editable editable, TextWatcher textWatcher) {
                lambda$initClickEvent$5(editable, textWatcher);
            }
        }));
        this.ivClearName.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initClickEvent$6(view);
            }
        });
        this.ivClearNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initClickEvent$7(view);
            }
        });
    }

    public void lambda$initClickEvent$0(View view) {
        dismiss();
    }

    public void lambda$initClickEvent$3(View view) {
        this.disposables.add(Single.create(new SingleOnSubscribe() {
            @Override
            public final void subscribe(SingleEmitter singleEmitter) {
                lambda$initClickEvent$1(singleEmitter);
            }
        }).compose(RxSchedulers2.io_main_single()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initClickEvent$2((Pair) obj);
            }
        }, new MigrateActivityExternalSyntheticLambda2()));
    }

    public void lambda$initClickEvent$1(SingleEmitter singleEmitter) throws Exception {
        singleEmitter.onSuccess(saveAddress());
    }

    public void lambda$initClickEvent$2(Pair pair) throws Exception {
        AddressBookChangeCallback addressBookChangeCallback;
        AddressDao addressDao = (AddressDao) pair.second;
        if (((Boolean) pair.first).booleanValue() && (addressBookChangeCallback = this.addressBookChangeCallback) != null) {
            addressBookChangeCallback.onAddressBookChanged(addressDao.getAddress(), addressDao.getName(), addressDao.getDescription());
        }
        dismiss();
    }

    public void lambda$initClickEvent$4(Editable editable, TextWatcher textWatcher) {
        this.etInputName.removeTextChangedListener(textWatcher);
        CharSequence indentEditTextSize = indentEditTextSize(this.etInputName, editable, 20);
        this.etInputName.addTextChangedListener(textWatcher);
        boolean hasSameName = hasSameName(indentEditTextSize);
        toggleErrorView(hasSameName);
        TextUtils.isEmpty(indentEditTextSize);
        boolean z = true;
        boolean z2 = (indentEditTextSize == null || StringTronUtil.isNullOrEmpty(indentEditTextSize.toString())) ? true : true;
        this.tvConfirm.setEnabled((z2 || hasSameName) ? false : false);
        this.ivClearName.setVisibility(z2 ? View.GONE : View.VISIBLE);
    }

    public void lambda$initClickEvent$5(Editable editable, TextWatcher textWatcher) {
        this.etInputNote.removeTextChangedListener(textWatcher);
        indentEditTextSize(this.etInputNote, editable, 60);
        this.etInputNote.addTextChangedListener(textWatcher);
        this.ivClearNote.setVisibility(TextUtils.isEmpty(editable) ? View.GONE : View.VISIBLE);
    }

    public void lambda$initClickEvent$6(View view) {
        this.etInputName.setText("");
    }

    public void lambda$initClickEvent$7(View view) {
        this.etInputNote.setText("");
    }

    private boolean hasSameName(CharSequence charSequence) {
        return AddressController.getInstance().isNameExist(charSequence.toString());
    }

    private void toggleErrorView(boolean z) {
        if (z) {
            this.errorView.setVisibility(View.VISIBLE);
            this.errorView.updateWarning(ErrorView.Level.ERROR);
            return;
        }
        this.errorView.setVisibility(View.GONE);
    }

    private CharSequence indentEditTextSize(EditText editText, CharSequence charSequence, int i) {
        if (charSequence.toString().toCharArray().length <= i) {
            return charSequence;
        }
        int length = charSequence.length();
        CharSequence charSequence2 = "";
        while (true) {
            if (length <= 0) {
                break;
            }
            charSequence2 = charSequence.subSequence(0, length);
            if (charSequence2.toString().toCharArray().length <= i) {
                editText.setText(charSequence2);
                editText.setSelection(charSequence2.length());
                break;
            }
            length--;
        }
        return charSequence2;
    }

    private android.util.Pair<java.lang.Boolean, com.tron.wallet.db.bean.AddressDao> saveAddress() {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.components.AddToAddressBookPopView.saveAddress():android.util.Pair");
    }

    @Override
    public void onDismiss() {
        super.onDismiss();
        CompositeDisposable compositeDisposable = this.disposables;
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            this.disposables = null;
        }
        KeyboardUtils.hideSoftInput(this);
    }

    @Override
    public int getMaxWidth() {
        return UIUtils.getScreenWidth(getContext()) - (UIUtils.dip2px(15.0f) << 1);
    }

    public static void showUp(Context context, String str, AddressBookChangeCallback addressBookChangeCallback) {
        new XPopup.Builder(context).asCustom(new AddToAddressBookPopView(context, str, addressBookChangeCallback)).show();
    }
}
