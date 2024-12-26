package com.tron.wallet.common.components.dialog;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.vote.adapter.BatchVoteDialogeAdapter;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.util.VoteCountChangeListenerV2;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.interfaces.VoteCountChangeListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.BottomSheetDialogBinding;
import com.tron.wallet.databinding.ItemBatchVoteDialogDetailBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class BatchVoteDetailDialog extends AppCompatDialog {
    public static final int RADIUS_OF_HALF_VIEW_HEIGHT = -1;
    public static final int RADIUS_OF_HALF_VIEW_WIDTH = -2;
    private int HIDE_RADIUS_SIDE_BOTTOM;
    private int HIDE_RADIUS_SIDE_LEFT;
    private int HIDE_RADIUS_SIDE_RIGHT;
    private int HIDE_RADIUS_SIDE_TOP;
    TextView avail_votes;
    BottomSheetDialogBinding binding;
    TextView btClearAll;
    Button btnVote;
    ImageView carLogo;
    ItemBatchVoteDialogDetailBinding itemBatchVoteDialogDetailBinding;
    ImageView iv_back;
    View layoutCar;
    private View.OnClickListener listener;
    LinearLayout ll_back;
    private int mHideRadiusSide;
    private boolean mIsOutlineExcludePadding;
    private boolean mIsShowBorderOnlyBeforeL;
    private int mOutlineInsetBottom;
    private int mOutlineInsetLeft;
    private int mOutlineInsetRight;
    private int mOutlineInsetTop;
    private int mRadius;
    private LinearLayout mRootView;
    private float mShadowAlpha;
    private int mShadowColor;
    private int mShadowElevation;
    private boolean mShouldUseRadiusArray;
    private long mTotalMyVotes;
    RecyclerView recyclerView;
    private BatchVoteDialogeAdapter rvAdapter;
    private HashMap<String, String> tmpVotes;
    private List<WitnessOutput.DataBean> tmpWitnesses;
    TextView tvNotEnough;
    TextView tvTotalCount;
    TextView tvVoteSRAmount;
    private VoteCountChangeListener voteCountChangedListener;

    public int getRealRadius() {
        return this.mRadius;
    }

    public void setOnVoteButtonClickListener(View.OnClickListener onClickListener) {
        this.listener = onClickListener;
    }

    public void setVoteCountChange(VoteCountChangeListener voteCountChangeListener) {
        this.voteCountChangedListener = voteCountChangeListener;
    }

    public BatchVoteDetailDialog(Context context) {
        this(context, R.style.ActionSheetDialogStyle);
    }

    public BatchVoteDetailDialog(Context context, int i) {
        super(context, i);
        this.mIsShowBorderOnlyBeforeL = true;
        this.mShadowElevation = 0;
        this.mShadowColor = ViewCompat.MEASURED_STATE_MASK;
        this.mOutlineInsetLeft = 0;
        this.mOutlineInsetRight = 0;
        this.mOutlineInsetTop = 0;
        this.mOutlineInsetBottom = 0;
        this.mIsOutlineExcludePadding = false;
        this.mHideRadiusSide = 0;
        this.HIDE_RADIUS_SIDE_TOP = 1;
        this.HIDE_RADIUS_SIDE_RIGHT = 2;
        this.HIDE_RADIUS_SIDE_BOTTOM = 3;
        this.HIDE_RADIUS_SIDE_LEFT = 4;
        supportRequestWindowFeature(1);
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.bottom_sheet_dialog, (ViewGroup) null);
        BottomSheetDialogBinding bind = BottomSheetDialogBinding.bind(viewGroup);
        this.binding = bind;
        bind.coordinator.setLayoutParams(new FrameLayout.LayoutParams(-1, (UIUtils.getScreenHeight(getContext()) * 7) / 10));
        this.mRootView = this.binding.bottomSheet;
        this.binding.touchOutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
        this.mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        super.setContentView(viewGroup, new ViewGroup.LayoutParams(-1, -2));
    }

    private void mappingIds() {
        this.recyclerView = this.itemBatchVoteDialogDetailBinding.recyclerView;
        this.ll_back = this.itemBatchVoteDialogDetailBinding.llBack;
        this.iv_back = this.itemBatchVoteDialogDetailBinding.ivBack;
        this.avail_votes = this.itemBatchVoteDialogDetailBinding.availVotes;
        this.tvTotalCount = this.itemBatchVoteDialogDetailBinding.layoutCar.tvTotalVoteCount;
        this.tvVoteSRAmount = this.itemBatchVoteDialogDetailBinding.layoutCar.voteSRAmount;
        this.btnVote = this.itemBatchVoteDialogDetailBinding.layoutCar.btnBatchVote;
        this.tvNotEnough = this.itemBatchVoteDialogDetailBinding.layoutCar.notEnough;
        this.btClearAll = this.itemBatchVoteDialogDetailBinding.tvClearAll;
        this.carLogo = this.itemBatchVoteDialogDetailBinding.layoutCar.voteCarLogo;
        this.layoutCar = this.itemBatchVoteDialogDetailBinding.layoutCar.layoutCar;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.clearFlags(67108864);
            window.setLayout(-1, -2);
        }
        ViewCompat.requestApplyInsets(this.mRootView);
    }

    public void addContentView(View view) {
        this.mRootView.addView(view, new LinearLayout.LayoutParams(-1, -2));
    }

    public void addContentView(int i) {
        LayoutInflater.from(this.mRootView.getContext()).inflate(i, (ViewGroup) this.mRootView, true);
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }

    public void initViews(int i) {
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.item_batch_vote_dialog_detail, (ViewGroup) null);
        this.itemBatchVoteDialogDetailBinding = ItemBatchVoteDialogDetailBinding.bind(viewGroup);
        mappingIds();
        View.OnClickListener onClickListener = this.listener;
        if (onClickListener != null) {
            this.btnVote.setOnClickListener(onClickListener);
        }
        View.OnClickListener onClickListener2 = new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initViews$0(view);
            }
        };
        this.ll_back.setOnClickListener(onClickListener2);
        this.iv_back.setOnClickListener(onClickListener2);
        this.carLogo.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.vote_car_logo_bg));
        this.recyclerView.setOverScrollMode(2);
        this.recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
        addContentView(viewGroup);
        initOutline();
        Window window = getWindow();
        window.setGravity(80);
        window.setAttributes(window.getAttributes());
        this.layoutCar.setBackground(new ColorDrawable(getContext().getResources().getColor(R.color.transparent)));
    }

    public void lambda$initViews$0(View view) {
        dismiss();
    }

    public void initOutline() {
        this.mRootView.setElevation(this.mShadowElevation);
        if (Build.VERSION.SDK_INT >= 28) {
            this.mRootView.setOutlineAmbientShadowColor(this.mShadowColor);
            this.mRootView.setOutlineSpotShadowColor(this.mShadowColor);
        }
        this.mRootView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int i;
                int i2;
                int i3;
                int i4;
                int width = view.getWidth();
                int height = view.getHeight();
                if (width == 0 || height == 0) {
                    return;
                }
                int realRadius = getRealRadius();
                boolean z = false;
                if (mShouldUseRadiusArray) {
                    if (mHideRadiusSide == HIDE_RADIUS_SIDE_LEFT) {
                        i3 = 0 - realRadius;
                        i = width;
                        i2 = height;
                    } else if (mHideRadiusSide != HIDE_RADIUS_SIDE_TOP) {
                        if (mHideRadiusSide == HIDE_RADIUS_SIDE_RIGHT) {
                            width += realRadius;
                        } else if (mHideRadiusSide == HIDE_RADIUS_SIDE_BOTTOM) {
                            height += realRadius;
                        }
                        i = width;
                        i2 = height;
                        i3 = 0;
                    } else {
                        i4 = 0 - realRadius;
                        i = width;
                        i2 = height;
                        i3 = 0;
                        outline.setRoundRect(i3, i4, i, i2, realRadius);
                        return;
                    }
                    i4 = 0;
                    outline.setRoundRect(i3, i4, i, i2, realRadius);
                    return;
                }
                int i5 = mOutlineInsetTop;
                int max = Math.max(i5 + 1, height - mOutlineInsetBottom);
                int i6 = mOutlineInsetLeft;
                int i7 = width - mOutlineInsetRight;
                if (mIsOutlineExcludePadding) {
                    i6 += view.getPaddingLeft();
                    i5 += view.getPaddingTop();
                    i7 = Math.max(i6 + 1, i7 - view.getPaddingRight());
                    max = Math.max(i5 + 1, max - view.getPaddingBottom());
                }
                int i8 = i7;
                int i9 = max;
                int i10 = i5;
                int i11 = i6;
                float f = mShadowAlpha;
                if (mShadowElevation == 0) {
                    f = 1.0f;
                }
                outline.setAlpha(f);
                if (realRadius <= 0) {
                    outline.setRect(i11, i10, i8, i9);
                } else {
                    outline.setRoundRect(i11, i10, i8, i9, realRadius);
                }
                mRootView.setClipToOutline((mRadius == -2 || mRadius == -1 || mRadius > 0) ? true : true);
                mRootView.invalidate();
            }
        });
    }

    public void showWithDatas(final long j, long j2, long j3, HashMap<String, String> hashMap, List<WitnessOutput.DataBean> list, String str) {
        this.mTotalMyVotes = j3;
        this.avail_votes.setText(String.valueOf(j));
        if (this.rvAdapter == null) {
            BatchVoteDialogeAdapter batchVoteDialogeAdapter = new BatchVoteDialogeAdapter(getContext(), this, this.voteCountChangedListener);
            this.rvAdapter = batchVoteDialogeAdapter;
            this.recyclerView.setAdapter(batchVoteDialogeAdapter);
            this.recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
                    if (i > 0) {
                        rect.top = UIUtils.dip2px(10.0f);
                    }
                }
            });
            this.rvAdapter.addListener(new VoteCountChangeListenerV2.BaseImpl() {
                @Override
                public void onVoteCountChanged(String str2, long j4, long j5, boolean z) {
                    super.onVoteCountChanged(str2, j4, j5, z);
                    mTotalMyVotes += j5;
                    tvTotalCount.setText(String.valueOf(mTotalMyVotes));
                    boolean z2 = mTotalMyVotes > j;
                    tvNotEnough.setVisibility(z2 ? View.VISIBLE : View.GONE);
                    carLogo.setEnabled(!z2);
                    tvTotalCount.setEnabled(!z2);
                    avail_votes.setEnabled(!z2);
                    tvVoteSRAmount.setText(String.valueOf(rvAdapter.getItemCount()));
                    btnVote.setEnabled(!z2);
                    if (mTotalMyVotes <= 0) {
                        tvVoteSRAmount.setVisibility(View.GONE);
                        carLogo.setEnabled(false);
                        btnVote.setEnabled(false);
                        return;
                    }
                    tvVoteSRAmount.setVisibility(View.VISIBLE);
                    btnVote.setEnabled(!z2);
                }
            });
        }
        this.tmpVotes = new HashMap<>();
        this.tmpWitnesses = new ArrayList();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (Long.parseLong(value) > 0) {
                this.tmpVotes.put(key, value);
                Iterator<WitnessOutput.DataBean> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        WitnessOutput.DataBean next = it.next();
                        if (TextUtils.equals(next.getAddress(), key)) {
                            this.tmpWitnesses.add(next);
                            break;
                        }
                    } else {
                        WitnessOutput.DataBean dataBean = new WitnessOutput.DataBean();
                        dataBean.setAddress(key);
                        this.tmpWitnesses.add(dataBean);
                        break;
                    }
                }
            }
        }
        this.rvAdapter.setData(this.tmpVotes, this.tmpWitnesses, str, j);
        this.tvTotalCount.setText(String.valueOf(j3));
        boolean z = j >= j3;
        this.tvNotEnough.setVisibility(!z ? View.VISIBLE : View.GONE);
        this.carLogo.setEnabled(z);
        this.tvTotalCount.setEnabled(z);
        this.tvVoteSRAmount.setText(String.valueOf(this.tmpVotes.size()));
        this.tvVoteSRAmount.setVisibility(View.VISIBLE);
        this.btnVote.setEnabled(z);
        show();
    }

    public void onClick(View view) {
        this.binding.getRoot().findViewById(R.id.tv_clear_all).setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view2) {
                if (view2.getId() != R.id.tv_clear_all) {
                    return;
                }
                clearAllAndDismiss();
            }
        });
    }

    public void clearAllAndDismiss() {
        BatchVoteDialogeAdapter batchVoteDialogeAdapter = this.rvAdapter;
        if (batchVoteDialogeAdapter != null) {
            batchVoteDialogeAdapter.notifyClearAll();
        }
        dismiss();
    }
}
