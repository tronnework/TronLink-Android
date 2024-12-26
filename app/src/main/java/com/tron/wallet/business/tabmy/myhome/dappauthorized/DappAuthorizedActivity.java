package com.tron.wallet.business.tabmy.myhome.dappauthorized;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.wallet.business.tabmy.myhome.dappauthorized.DappAuthorizedActivity;
import com.tron.wallet.common.adapter.DappAuthorizedAdapter;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.AcAuthorizedDappBinding;
import com.tron.wallet.db.Controller.DappAuthorizedController;
import com.tron.wallet.db.bean.DAppNonOfficialAuthorizedProjectBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.tron.walletserver.Wallet;
public class DappAuthorizedActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static String TYPE_WALLET_NAME = "TYPE_WALLET_NAME";
    private DappAuthorizedAdapter authorizedAdapter;
    private List<DAppNonOfficialAuthorizedProjectBean> authorizedList;
    private AcAuthorizedDappBinding binding;
    Button btCancel;
    View rlNodapp;
    RecyclerView rvAuthorizedList;
    TextView tvNoDapp;
    View tvRight;
    TextView tvTextHint;
    private Wallet wallet;
    private List<DAppNonOfficialAuthorizedProjectBean> awaitCancelList = new ArrayList();
    private DappAuthorizedAdapter.SelectedListener selectedListener = new fun4();

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, DappAuthorizedActivity.class);
        intent.putExtra(TYPE_WALLET_NAME, str);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcAuthorizedDappBinding inflate = AcAuthorizedDappBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.rvAuthorizedList = this.binding.rvAuthorizedList;
        this.btCancel = this.binding.btCancel;
        this.tvTextHint = this.binding.tvTextHint;
        this.tvNoDapp = this.binding.noDataView.tvNoDapp;
        this.rlNodapp = this.binding.noDataView.rlNodapp;
        this.tvRight = this.binding.tvCommonRight;
    }

    public class fun1 extends NoDoubleClickListener2 {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            int id = view.getId();
            if (id == R.id.ll_common_left) {
                exit();
            } else if (id == R.id.tv_common_right && authorizedList != null) {
                awaitCancelList.clear();
                Collection.-EL.stream(authorizedList).forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        DappAuthorizedActivity.1.this.lambda$onNoDoubleClick$0((DAppNonOfficialAuthorizedProjectBean) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_MANAGER_PAGE_ALL);
                refreshListData();
            }
        }

        public void lambda$onNoDoubleClick$0(DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean) {
            dAppNonOfficialAuthorizedProjectBean.setSelected(true);
            awaitCancelList.add(dAppNonOfficialAuthorizedProjectBean);
        }
    }

    public void setClick() {
        1 r0 = new fun1();
        this.binding.llCommonLeft.setOnClickListener(r0);
        this.binding.tvCommonRight.setOnClickListener(r0);
    }

    @Override
    protected void processData() {
        String stringExtra = getIntent().getStringExtra(TYPE_WALLET_NAME);
        if (stringExtra == null) {
            finish();
            return;
        }
        Wallet wallet = WalletUtils.getWallet(stringExtra);
        this.wallet = wallet;
        if (wallet == null) {
            finish();
            return;
        }
        updateUI(stringExtra);
        onClick();
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$processData$0();
            }
        });
    }

    public void lambda$processData$0() {
        List<DAppNonOfficialAuthorizedProjectBean> queryNonOfficialAuthorizedProjectList = DappAuthorizedController.queryNonOfficialAuthorizedProjectList(this.wallet.getAddress());
        this.authorizedList = queryNonOfficialAuthorizedProjectList;
        if (queryNonOfficialAuthorizedProjectList != null && !queryNonOfficialAuthorizedProjectList.isEmpty()) {
            refreshListData();
        } else {
            runOnUIThread(new OnMainThread() {
                @Override
                public void doInUIThread() {
                    checkNoData();
                }
            });
        }
    }

    private void updateUI(String str) {
        String format = String.format(getResources().getString(R.string.dapps_allow_access_to_wallet_name), str);
        if (format != null) {
            this.tvTextHint.setText(format);
        }
        this.tvNoDapp.setText(R.string.no_records);
    }

    public class fun3 extends NoDoubleClickListener {
        fun3() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            final ArrayList arrayList = new ArrayList(awaitCancelList);
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    DappAuthorizedController.deleteNonOfficialAuthorizedProjectList(arrayList);
                }
            });
            Collection.-EL.stream(arrayList).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    DappAuthorizedActivity.3.this.lambda$onNoDoubleClick$1((DAppNonOfficialAuthorizedProjectBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            awaitCancelList.clear();
            refreshListData();
            AnalyticsHelper.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_MANAGER_PAGE_ITEM);
        }

        public void lambda$onNoDoubleClick$1(DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean) {
            authorizedList.remove(dAppNonOfficialAuthorizedProjectBean);
        }
    }

    private void onClick() {
        this.btCancel.setOnClickListener(new fun3());
    }

    public void refreshListData() {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$refreshListData$1();
            }
        });
    }

    public void lambda$refreshListData$1() {
        if (this.awaitCancelList.size() > 0) {
            this.btCancel.setEnabled(true);
        } else {
            this.btCancel.setEnabled(false);
        }
        checkNoData();
        DappAuthorizedAdapter dappAuthorizedAdapter = this.authorizedAdapter;
        if (dappAuthorizedAdapter == null) {
            this.authorizedAdapter = new DappAuthorizedAdapter(this.authorizedList, this.selectedListener);
            this.rvAuthorizedList.setLayoutManager(new LinearLayoutManager(this, 1, false));
            this.rvAuthorizedList.setAdapter(this.authorizedAdapter);
            return;
        }
        dappAuthorizedAdapter.refreshData(this.authorizedList);
    }

    public void checkNoData() {
        List<DAppNonOfficialAuthorizedProjectBean> list = this.authorizedList;
        if (list == null || list.size() == 0) {
            this.rlNodapp.setVisibility(View.VISIBLE);
            this.tvRight.setVisibility(View.GONE);
            this.btCancel.setVisibility(View.GONE);
            return;
        }
        this.tvRight.setVisibility(View.VISIBLE);
        this.rlNodapp.setVisibility(View.GONE);
        this.btCancel.setVisibility(View.VISIBLE);
    }

    public class fun4 implements DappAuthorizedAdapter.SelectedListener {
        fun4() {
        }

        @Override
        public void onSelectedListener(final DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean) {
            if (authorizedList == null) {
                return;
            }
            Collection.-EL.stream(authorizedList).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    DappAuthorizedActivity.4.this.lambda$onSelectedListener$0(dAppNonOfficialAuthorizedProjectBean, (DAppNonOfficialAuthorizedProjectBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }

        public void lambda$onSelectedListener$0(DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean, DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean2) {
            if (dAppNonOfficialAuthorizedProjectBean2.getUrl().equals(dAppNonOfficialAuthorizedProjectBean.getUrl())) {
                if (!dAppNonOfficialAuthorizedProjectBean2.isSelected()) {
                    dAppNonOfficialAuthorizedProjectBean2.setSelected(true);
                    awaitCancelList.add(dAppNonOfficialAuthorizedProjectBean2);
                } else {
                    dAppNonOfficialAuthorizedProjectBean2.setSelected(false);
                    awaitCancelList.remove(dAppNonOfficialAuthorizedProjectBean2);
                }
            }
            refreshListData();
        }
    }
}
