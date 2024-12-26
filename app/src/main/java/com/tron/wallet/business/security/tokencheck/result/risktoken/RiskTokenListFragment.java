package com.tron.wallet.business.security.tokencheck.result.risktoken;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.business.security.tokencheck.TokenCheckActivity;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckResultBean;
import com.tron.wallet.business.security.tokencheck.result.risktoken.RiskTokenListContract;
import com.tron.wallet.business.security.tokencheck.result.risktoken.RiskTokenListFragment;
import com.tron.wallet.business.security.tokencheck.view.TokenCheckAdapter;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.interfaces.OnItemSelectedListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.RiskTokenListFragmentBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
public class RiskTokenListFragment extends BaseFragment<RiskTokenListPresenter, RiskTokenListModel> implements RiskTokenListContract.View {
    public static String TOKEN_CHECK_RESULT = "token_check_result";
    public static String TYPE_CHECK_RISK = "type_check_risk";
    public static int TYPE_MIDDLE_RISK = 1;
    public static int TYPE_RISK;
    private RiskTokenListFragmentBinding binding;
    EditText mSearchEt;
    ArrayList<TokenCheckBean> mTokenList;
    RecyclerView recyclerView;
    int riskPageType;
    TokenCheckAdapter tokenCheckAdapter;
    TokenCheckResultBean tokenCheckResultBean;

    public static void lambda$deletePopWindow$1(View view) {
    }

    public static void lambda$ignorePopWindow$3(View view) {
    }

    @Override
    public void updateTokenList() {
    }

    public static RiskTokenListFragment getInstance(TokenCheckResultBean tokenCheckResultBean, int i) {
        RiskTokenListFragment riskTokenListFragment = new RiskTokenListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TOKEN_CHECK_RESULT, tokenCheckResultBean);
        bundle.putInt(TYPE_CHECK_RISK, i);
        riskTokenListFragment.setArguments(bundle);
        return riskTokenListFragment;
    }

    @Override
    protected void processData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.tokenCheckResultBean = (TokenCheckResultBean) arguments.getSerializable(TOKEN_CHECK_RESULT);
            int i = arguments.getInt(TYPE_CHECK_RISK);
            this.riskPageType = i;
            if (i == TYPE_RISK) {
                this.mTokenList = (ArrayList) this.tokenCheckResultBean.getData().getHighRiskList();
            } else if (i == TYPE_MIDDLE_RISK) {
                this.mTokenList = (ArrayList) this.tokenCheckResultBean.getData().getMediumRiskList();
            }
        }
        initSearchEt();
        setListener();
        initTokenListView();
    }

    private void initTokenListView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TokenCheckAdapter tokenCheckAdapter = new TokenCheckAdapter(getActivity(), this.riskPageType, this.mTokenList, new TokenCheckAdapter.ItemCallback() {
            @Override
            public void onItemClicked(TokenCheckBean tokenCheckBean, int i) {
            }

            @Override
            public void onItemSelected(TokenCheckBean tokenCheckBean, int i) {
            }

            @Override
            public void onItemLongClicked(final View view, final TokenCheckBean tokenCheckBean, int[] iArr, final int i) {
                new DeletePopup(getIContext(), null, new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(PopupWindow popupWindow, int i2, int i3, Object obj) {
                        if (i3 == DeletePopup.DELETE) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_DELETE);
                            deletePopWindow(tokenCheckBean, i);
                        } else if (i3 == DeletePopup.IGNORE) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_IGNORE);
                            ignorePopWindow(tokenCheckBean, i);
                        }
                        popupWindow.dismiss();
                    }
                }, new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        view.setSelected(false);
                    }
                }).showAtTouchPoint(view, iArr);
            }
        });
        this.tokenCheckAdapter = tokenCheckAdapter;
        this.recyclerView.setAdapter(tokenCheckAdapter);
        ((SimpleItemAnimator) this.recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.tokenCheckAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            }
        });
        this.tokenCheckAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                return true;
            }
        });
        this.mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tokenCheckAdapter.filterList(editable.toString());
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RiskTokenListFragmentBinding inflate = RiskTokenListFragmentBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.mSearchEt = this.binding.etSearch;
        this.recyclerView = this.binding.tokenList;
    }

    private void initSearchEt() {
        this.mSearchEt.setInputType(1);
        this.mSearchEt.setImeOptions(6);
        this.mSearchEt.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_SEARCH);
            }
        });
        this.mSearchEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_SEARCH);
                }
            }
        });
    }

    private void setListener() {
        this.mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String trim = editable.toString().trim();
                if (StringTronUtil.isEmpty(trim)) {
                    ((RiskTokenListPresenter) mPresenter).resetTokenList();
                } else {
                    ((RiskTokenListPresenter) mPresenter).searchToken(trim);
                }
            }
        });
    }

    public void deletePopWindow(final TokenCheckBean tokenCheckBean, final int i) {
        ConfirmCustomPopupView.Builder builder = ConfirmCustomPopupView.getBuilder(getContext());
        String string = getResources().getString(R.string.token_check_delete_confirm_title);
        Object[] objArr = new Object[1];
        objArr[0] = StringTronUtil.isEmpty(tokenCheckBean.getShortName()) ? tokenCheckBean.getName() : tokenCheckBean.getShortName();
        builder.setTitle(String.format(string, objArr)).setInfo(getResources().getString(R.string.token_check_delete_confirm_info)).setBtnStyle(1).setConfirmText(getString(R.string.ok2)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$deletePopWindow$0(tokenCheckBean, i, view);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                RiskTokenListFragment.lambda$deletePopWindow$1(view);
            }
        }).build().show();
    }

    public void lambda$deletePopWindow$0(TokenCheckBean tokenCheckBean, int i, View view) {
        ((TokenCheckActivity) getActivity()).deleteToken(tokenCheckBean, this.riskPageType);
        this.tokenCheckAdapter.remove(i);
    }

    public void ignorePopWindow(final TokenCheckBean tokenCheckBean, final int i) {
        String string = getResources().getString(R.string.token_check_ignore_confirm_info);
        if (this.riskPageType == TYPE_MIDDLE_RISK) {
            string = getResources().getString(R.string.token_check_ignore_confirm_middle_risk_info);
        }
        ConfirmCustomPopupView.Builder builder = ConfirmCustomPopupView.getBuilder(getContext());
        String string2 = getResources().getString(R.string.token_check_ignore_confirm_title);
        Object[] objArr = new Object[1];
        objArr[0] = StringTronUtil.isEmpty(tokenCheckBean.getShortName()) ? tokenCheckBean.getName() : tokenCheckBean.getShortName();
        builder.setTitle(String.format(string2, objArr)).setInfo(string).setBtnStyle(1).setConfirmText(getString(R.string.ok2)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$ignorePopWindow$2(tokenCheckBean, i, view);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                RiskTokenListFragment.lambda$ignorePopWindow$3(view);
            }
        }).build().show();
    }

    public void lambda$ignorePopWindow$2(TokenCheckBean tokenCheckBean, int i, View view) {
        ((TokenCheckActivity) getActivity()).ignoreToken(tokenCheckBean, this.riskPageType);
        this.tokenCheckAdapter.getData().remove(i);
        this.tokenCheckAdapter.notifyDataSetChanged();
    }

    public void resetSearch() {
        this.mSearchEt.setText("");
        SoftHideKeyBoardUtil.closeKeybord(getActivity());
    }

    public static class DeletePopup extends PopupWindow {
        public static int DELETE = 1;
        public static int IGNORE;
        private TokenBean bean;
        private Context context;
        private int popupHeight;
        private int popupWidth;

        public DeletePopup(Context context, final TokenCheckBean tokenCheckBean, final OnItemSelectedListener onItemSelectedListener, PopupWindow.OnDismissListener onDismissListener) {
            this.context = context;
            View inflate = LayoutInflater.from(context).inflate(R.layout.popup_del_assets, (ViewGroup) null);
            setContentView(inflate);
            setWidth(-2);
            setHeight(-2);
            setOutsideTouchable(true);
            setFocusable(true);
            setBackgroundDrawable(new ColorDrawable());
            setClippingEnabled(false);
            setOnDismissListener(onDismissListener);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_1);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_2);
            textView.setText(R.string.token_check_item_menu_ignore);
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, context.getDrawable(R.drawable.roundborder_88_4_top));
            stateListDrawable.addState(new int[]{-16842919}, context.getDrawable(R.color.transparent));
            textView.setBackground(stateListDrawable);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    RiskTokenListFragment.DeletePopup.this.lambda$new$0(onItemSelectedListener, tokenCheckBean, view);
                }
            });
            textView2.setText(R.string.token_check_item_menu_delete);
            StateListDrawable stateListDrawable2 = new StateListDrawable();
            stateListDrawable2.addState(new int[]{16842919}, context.getDrawable(R.drawable.roundborder_88_4_bottom));
            stateListDrawable2.addState(new int[]{-16842919}, context.getDrawable(R.color.transparent));
            textView2.setBackground(stateListDrawable2);
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    RiskTokenListFragment.DeletePopup.this.lambda$new$1(onItemSelectedListener, tokenCheckBean, view);
                }
            });
            inflate.measure(0, 0);
            this.popupHeight = inflate.getMeasuredHeight();
            this.popupWidth = inflate.getMeasuredWidth();
        }

        public void lambda$new$0(OnItemSelectedListener onItemSelectedListener, TokenCheckBean tokenCheckBean, View view) {
            onItemSelectedListener.onItemSelected(this, 0, IGNORE, tokenCheckBean);
        }

        public void lambda$new$1(OnItemSelectedListener onItemSelectedListener, TokenCheckBean tokenCheckBean, View view) {
            onItemSelectedListener.onItemSelected(this, 1, DELETE, tokenCheckBean);
        }

        public void showAtTouchPoint(View view, int[] iArr) {
            int height;
            int i;
            int[] iArr2 = new int[2];
            view.getLocationOnScreen(iArr2);
            if (iArr2[1] + iArr[1] + this.popupHeight + UIUtils.dip2px(15.0f) > UIUtils.getScreenHeight(this.context)) {
                height = (view.getHeight() - iArr[1]) + this.popupHeight;
            } else {
                height = view.getHeight() - iArr[1];
            }
            int i2 = -height;
            if (iArr2[0] + iArr[0] + this.popupWidth + UIUtils.dip2px(10.0f) > UIUtils.getScreenWidth(this.context)) {
                i = iArr[0] - this.popupWidth;
                if (i < UIUtils.dip2px(10.0f)) {
                    i = UIUtils.dip2px(10.0f);
                }
            } else {
                i = iArr[0];
            }
            showAsDropDown(view, i, i2);
        }
    }
}
