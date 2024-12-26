package com.tron.wallet.business.confirm.signed;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.confirm.sign.SignTransactionNewActivity;
import com.tron.wallet.common.adapter.SignTransactionTopAdapter;
import com.tron.wallet.common.bean.ColdFailLogBean;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.YellowEnergyProgressView;
import com.tron.wallet.common.components.dialog.CommonDialog;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.databinding.AcNewSignedTransactionBinding;
import com.tron.wallet.db.Controller.ColdFailLogBeanDaoManager;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.util.encoders.Hex;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SignedTransactionNewActivity extends BaseActivity<EmptyPresenter, EmptyModel> implements PermissionInterface {
    private String FROM_W;
    private String address;
    private String alpha;
    private AcNewSignedTransactionBinding binding;
    private CommonDialog commonDialog;
    private Gson gson;
    ImageView ivQr2;
    private PermissionHelper mPermissionHelper;
    private RxManager mRxmanager;
    private BaseParam.PageFrom pageFrom;
    private float progress;
    private QrBean qrBean;
    RecyclerView rcTop;
    RelativeLayout rlProgress;
    YellowEnergyProgressView scanProgress;
    private SignTransactionTopAdapter signTopAdapter;
    private Parcelable tokenBean;
    private String tokenID;
    Button toscan;
    TextView tv3501;
    TextView tvProgressCenter;
    TextView tvProgressRight;
    float currentIndex = 0.0f;
    float max = 4.0f;
    private String qrString = "";
    private Handler handler = new Handler();
    private Set<Integer> scanedIndexSet = new HashSet();
    private Runnable runnable = new Runnable() {
        @Override
        public final void run() {
            lambda$new$0();
        }
    };

    @Override
    public int getPermissionsRequestCode() {
        return 2001;
    }

    public void lambda$new$0() {
        int width = this.scanProgress.getWidth();
        this.tvProgressCenter.setVisibility(View.VISIBLE);
        float f = this.currentIndex / this.max;
        int dip2px = UIUtils.dip2px(27.0f);
        String formatNumber = StringTronUtil.formatNumber(100.0f * f, 0);
        TextView textView = this.tvProgressCenter;
        textView.setText(formatNumber + "%");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (this.currentIndex >= this.max) {
            layoutParams.addRule(11);
            layoutParams.setMargins(0, dip2px, 0, 0);
        } else {
            layoutParams.setMargins(((int) (width * f)) - UIUtils.dip2px(15.0f), dip2px, 0, 0);
        }
        this.tvProgressCenter.setLayoutParams(layoutParams);
        this.progress = f;
        this.scanProgress.setProgressValue(f);
    }

    public static void start(BaseActivity baseActivity, QrBean qrBean, TokenBean tokenBean, String str) {
        Intent intent = new Intent(baseActivity, SignedTransactionNewActivity.class);
        intent.putExtra(TronConfig.QR_CODE_DATA, qrBean);
        intent.putExtra(TronConfig.TOKEN_DATA, tokenBean);
        intent.putExtra(TronConfig.FROM_W, str);
        baseActivity.startActivityForResult(intent, TronConfig.TRANSACTION_SIGN_REQUEST_CODE);
    }

    public static void start2(BaseActivity baseActivity, QrBean qrBean, TokenBean tokenBean, String str, BaseParam.PageFrom pageFrom) {
        Intent intent = new Intent(baseActivity, SignedTransactionNewActivity.class);
        intent.putExtra(TronConfig.QR_CODE_DATA, qrBean);
        intent.putExtra(TronConfig.TOKEN_DATA, tokenBean);
        intent.putExtra(TronConfig.FROM_W, str);
        if (pageFrom != null) {
            intent.putExtra(TronConfig.PageFrom, pageFrom);
        }
        baseActivity.startActivityForResult(intent, TronConfig.TRANSACTION_SIGN_REQUEST_CODE);
    }

    @Override
    protected void setLayout() {
        AcNewSignedTransactionBinding inflate = AcNewSignedTransactionBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 6);
        setHeaderBar(getString(R.string.scan_signed_title));
        mappingId();
        setClick();
    }

    public void mappingId() {
        this.scanProgress = this.binding.scanProgress;
        this.rcTop = this.binding.rcTop;
        this.toscan = this.binding.toscan;
        this.tv3501 = this.binding.tv3501;
        this.tvProgressCenter = this.binding.tvProgressCenter;
        this.tvProgressRight = this.binding.tvProgressRight;
        this.rlProgress = this.binding.rlProgress;
        this.ivQr2 = this.binding.ivQr2;
    }

    @Override
    protected void processData() {
        Bundle extras = getIntent().getExtras();
        this.handler.postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$processData$1();
            }
        }, 200L);
        this.FROM_W = extras.getString(TronConfig.FROM_W);
        this.tokenBean = extras.getParcelable(TronConfig.TOKEN_DATA);
        this.pageFrom = (BaseParam.PageFrom) extras.getSerializable(TronConfig.PageFrom);
        updateHeaderTitle();
        this.gson = new Gson();
        if (extras != null) {
            try {
                QrBean qrBean = (QrBean) extras.getParcelable(TronConfig.QR_CODE_DATA);
                this.qrBean = qrBean;
                if (qrBean == null) {
                    addDatabase();
                }
                if (this.qrBean.gettN() != 1 && this.qrBean.gettN() != 0) {
                    this.max = this.qrBean.gettN();
                    if (this.qrBean.getcN() != 1) {
                        Toast(R.string.qr_detail_5);
                        exit();
                        return;
                    }
                    addAdapter();
                    updateProgress();
                    addListsner();
                    this.qrString += this.qrBean.getHexList().get(0);
                    return;
                }
                SignTransactionNewActivity.start(this, this.qrBean, null, this.FROM_W);
                exit();
            } catch (Exception e) {
                Toast(R.string.qr_detail_7);
                LogUtils.e(e);
                addDatabase(e.getMessage());
                exit();
            }
        }
    }

    public void lambda$processData$1() {
        this.scanProgress.setProgressValue(this.progress);
    }

    public void addListsner() {
        RxManager rxManager = new RxManager();
        this.mRxmanager = rxManager;
        rxManager.on(RB.RB_SIGNED_FINISH, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addListsner$2(obj);
            }
        });
    }

    public void lambda$addListsner$2(Object obj) throws Exception {
        finish();
    }

    public void updateHeaderTitle() {
        if (TronConfig.COLD_W.equals(this.FROM_W)) {
            setHeaderBar(getString(R.string.scan_signed_title));
        } else {
            setHeaderBar(getString(R.string.qr_detail_8));
        }
    }

    public void addAdapter() {
        if (this.max > 1.0f) {
            this.tv3501.setVisibility(View.VISIBLE);
            this.rlProgress.setVisibility(View.VISIBLE);
            this.rcTop.setVisibility(View.VISIBLE);
            this.rcTop.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                    rect.set(10, 0, 10, 0);
                }
            });
            this.rcTop.setLayoutManager(new LinearLayoutManager(this, 0, false));
            SignTransactionTopAdapter signTransactionTopAdapter = new SignTransactionTopAdapter((int) this.max);
            this.signTopAdapter = signTransactionTopAdapter;
            this.rcTop.setAdapter(signTransactionTopAdapter);
        }
    }

    public void addDatabase() {
        addDatabase("sign qr json is empty");
    }

    public void addDatabase(String str) {
        String str2 = "";
        try {
            Wallet selectedWallet = WalletUtils.getSelectedWallet();
            ColdFailLogBean coldFailLogBean = new ColdFailLogBean();
            coldFailLogBean.activityName = "SignedTransactionNewActivity";
            coldFailLogBean.methodName = "processData";
            coldFailLogBean.transactionStr = "";
            coldFailLogBean.aReturn = "";
            if (selectedWallet != null) {
                str2 = selectedWallet.getAddress();
            }
            coldFailLogBean.address = str2;
            coldFailLogBean.hasLoad = false;
            coldFailLogBean.time = System.currentTimeMillis();
            coldFailLogBean.error = str;
            ColdFailLogBeanDaoManager.addErrorLog(coldFailLogBean);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_qr2 || id == R.id.toscan) {
                    toConfirm();
                }
            }
        };
        this.binding.ivQr2.setOnClickListener(noDoubleClickListener);
        this.binding.toscan.setOnClickListener(noDoubleClickListener);
    }

    public void toConfirm() {
        if (this.qrBean != null && this.max == this.currentIndex) {
            try {
                String[] split = this.qrString.split("&&&");
                ArrayList arrayList = new ArrayList();
                for (String str : split) {
                    arrayList.add(str);
                }
                this.qrBean.setHexList(arrayList);
                this.qrBean.setAlpha(this.alpha);
                this.qrBean.setAddress(this.address);
                this.qrBean.setTokenId(this.tokenID);
                if (TronConfig.COLD_W.equals(this.FROM_W)) {
                    if (this.qrBean.getType() == 15) {
                        return;
                    }
                    if (this.qrBean.getType() != 98 && this.qrBean.getType() != 102 && this.qrBean.getType() != 104 && this.qrBean.getType() != 103 && this.qrBean.getType() != 101) {
                        ArrayList arrayList2 = new ArrayList();
                        for (String str2 : this.qrBean.getHexList()) {
                            arrayList2.add(Protocol.Transaction.parseFrom(Hex.decode(str2)));
                        }
                        ConfirmTransactionNewActivity.startActivity(this, ParamBuildUtils.getColdTransactionParamBuilder2(this.qrBean, arrayList2, this.pageFrom));
                        return;
                    }
                    QrBean qrBean = this.qrBean;
                    ConfirmTransactionNewActivity.startActivity(this, ParamBuildUtils.getColdSignMessageBuilder(qrBean, this.qrBean.getHexList().get(0), qrBean.getType()));
                    return;
                } else if (TronConfig.OB_W.equals(this.FROM_W)) {
                    QrBean qrBean2 = this.qrBean;
                    toBroadcast(qrBean2, this.gson.toJson(qrBean2));
                    return;
                } else {
                    return;
                }
            } catch (Exception e) {
                Toast(R.string.qr_detail_7);
                addDatabase(e.getMessage());
                return;
            }
        }
        requestPermissionToScan();
    }

    public void updateProgress() {
        SignTransactionTopAdapter signTransactionTopAdapter = this.signTopAdapter;
        if (signTransactionTopAdapter != null) {
            signTransactionTopAdapter.updateIndex((int) this.currentIndex);
        }
        float f = this.currentIndex + 1.0f;
        this.currentIndex = f;
        float f2 = this.max;
        if (f > f2) {
            this.currentIndex = f2;
        }
        if (f2 > 1.0f && this.currentIndex > 0.0f) {
            this.scanProgress.post(this.runnable);
        }
        if (this.max == this.currentIndex) {
            this.tvProgressRight.setVisibility(View.GONE);
            if (TronConfig.COLD_W.equals(this.FROM_W)) {
                this.ivQr2.setBackgroundResource(R.mipmap.qr_e);
                this.toscan.setText(R.string.qr_detail_14);
                return;
            }
            this.ivQr2.setBackgroundResource(R.mipmap.qr_b);
            this.toscan.setText(R.string.qr_detail_9);
        }
    }

    public void requestPermissionToScan() {
        PermissionHelper permissionHelper = new PermissionHelper(this, this);
        this.mPermissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            if (i == 2019) {
                exit();
            } else if (intent != null) {
                String stringExtra = intent.getStringExtra("SCAN_RESULT");
                this.qrBean = (QrBean) new Gson().fromJson(stringExtra,  QrBean.class);
                if (StringTronUtil.isEmpty(this.address)) {
                    this.address = this.qrBean.getAddress();
                }
                if (StringTronUtil.isEmpty(this.tokenID)) {
                    this.tokenID = this.qrBean.getTokenId();
                }
                if (StringTronUtil.isEmpty(this.alpha)) {
                    this.alpha = this.qrBean.getAlpha();
                }
                int cNVar = this.qrBean.getcN();
                int tNVar = this.qrBean.gettN();
                if (BigDecimalUtils.equalsZero((Number) Float.valueOf(this.max))) {
                    this.max = this.qrBean.gettN();
                }
                String str = this.qrBean.getHexList().get(0);
                float f = this.max;
                if (tNVar != f && f != 0.0f) {
                    Toast(R.string.qr_detail_7);
                    return;
                }
                float f2 = cNVar;
                float f3 = this.currentIndex;
                if (f2 - f3 <= 1.0f && f2 > f3) {
                    if (this.qrString != null && this.scanedIndexSet.contains(Integer.valueOf(this.qrBean.getcN()))) {
                        Toast(R.string.qr_detail_6);
                        return;
                    } else if ((tNVar == 0 || tNVar == 1) && TronConfig.OB_W.equals(this.FROM_W)) {
                        toBroadcast(this.qrBean, stringExtra);
                        return;
                    } else {
                        this.scanedIndexSet.add(Integer.valueOf(this.qrBean.getcN()));
                        this.qrString += str;
                        updateProgress();
                        return;
                    }
                }
                Toast(R.string.qr_detail_5);
            } else {
                super.onActivityResult(i, i2, intent);
            }
        } catch (Exception e) {
            Toast(R.string.qr_detail_7);
            addDatabase(e.getMessage());
        }
    }

    public void toBroadcast(QrBean qrBean, String str) {
        try {
            for (String str2 : qrBean.getHexList()) {
                Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(Hex.decode(str2));
                if (parseFrom.getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.ShieldedTransferContract) {
                    break;
                } else if (parseFrom != null && parseFrom.getSignatureCount() == 0) {
                    IToast.getIToast().showLongBottom(getString(R.string.signfirst));
                    return;
                }
            }
            Intent intent = new Intent();
            intent.putExtra(TronConfig.QR_CODE_DATA, str);
            intent.putExtra(TronConfig.TOKEN_DATA, this.tokenBean);
            setResult(TronConfig.TRANSACTION_SIGN_REQUEST_CODE, intent);
            finish();
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonDialog commonDialog = this.commonDialog;
        if (commonDialog != null) {
            commonDialog.dismiss();
        }
        RxManager rxManager = this.mRxmanager;
        if (rxManager != null) {
            rxManager.clear();
        }
        this.runnable = null;
        this.binding = null;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    @Override
    public void requestPermissionsSuccess() {
        toScan();
    }

    private void toScan() {
        ScannerActivity.start(this);
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.mPermissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }
}
