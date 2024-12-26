package com.tron.wallet.business.tokendetail;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.generic.RoundingParams;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.customtokens.CustomTokensModel;
import com.tron.wallet.business.customtokens.bean.CustomTokenBean;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.customtokens.bean.QueryCustomTokenOutput;
import com.tron.wallet.business.tokendetail.mvp.ProjectIntroductionContract;
import com.tron.wallet.business.tokendetail.mvp.ProjectIntroductionModel;
import com.tron.wallet.business.tokendetail.mvp.ProjectIntroductionPresenter;
import com.tron.wallet.business.tokendetail.mvp.TokenSecureInfoOutput;
import com.tron.wallet.common.bean.Price;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.Trc20DetailBean;
import com.tron.wallet.common.components.CustomTokenNoFunctionView;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.ChainUtil;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.TokenItemUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcProjectIntroductionBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.AssetIssueContractDao;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import org.apache.commons.cli.HelpFormatter;
import org.tron.walletserver.Wallet;
public class ProjectIntroductionActivity extends BaseActivity<ProjectIntroductionPresenter, ProjectIntroductionModel> implements ProjectIntroductionContract.View {
    private static final String APPROXIMATE = "≈";
    TextView accuracy;
    TextView assetsTag;
    private AcProjectIntroductionBinding binding;
    TextView contractAddress;
    TextView endTime;
    TokenLogoDraweeView imgToken;
    View ivAssetsOfficial;
    ImageView ivCopy;
    ImageView ivIdCopy;
    ImageView ivNational;
    ImageView ivNoProxy;
    View ivOfficialImage;
    ImageView ivRankingTips;
    ImageView ivSecureBlackList;
    ImageView ivSecureCreateSelf;
    ImageView ivSecureHaveUrl;
    ImageView ivSecureOpenSource;
    ImageView ivTokenUrlCopy;
    ImageView ivTronscan;
    View layoutAssetsTag;
    LinearLayout liSecureInfo;
    RelativeLayout llAccuracy;
    RelativeLayout llAddress;
    RelativeLayout llDescribe;
    RelativeLayout llEndtime;
    RelativeLayout llId;
    LinearLayout llPrice;
    RelativeLayout llProjectName;
    RelativeLayout llPublisher;
    RelativeLayout llStartTime;
    RelativeLayout llTokenUrl;
    RelativeLayout llTotalCirculation;
    LinearLayout llTronscanRating;
    CustomTokenNoFunctionView noFunctionView;
    private boolean noTotalSupplyAbi;
    private NumberFormat numberFormat;
    TextView projectName;
    RelativeLayout rlNoProxy;
    ItemWarningTagView rlScam;
    RelativeLayout rlSecureBlackList;
    RelativeLayout rlSecureCreateSelf;
    RelativeLayout rlSecureHaveUrl;
    RelativeLayout rlSecureOpenSource;
    RelativeLayout rlTronscan;
    private RxManager rxManager;
    TextView startTime;
    private TokenBean tokenBean;
    TextView tokenDescribe;
    TextView tokenFullName;
    private long tokenID;
    TextView tokenName;
    TextView tokenPublisher;
    TextView tokenUrl;
    TextView totalCirculation;
    TextView tvMarket;
    TextView tvPrice;
    TextView tvRanking;
    TextView tvReport;
    TextView tvTokenId;
    TextView tvTronscan;
    private final String DEFAULT_SCAM_TEXT = HelpFormatter.DEFAULT_LONG_OPT_PREFIX;
    private boolean isTrc10 = true;
    private String tolkenUrlStr = "";
    private String isTrx = "";

    @Override
    protected void setLayout() {
        AcProjectIntroductionBinding inflate = AcProjectIntroductionBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 3);
        setHeaderBar(getString(R.string.project_detail));
    }

    public void mappingId() {
        this.imgToken = this.binding.ilHeader.imgToken;
        this.tokenName = this.binding.ilHeader.assetsName;
        this.tokenFullName = this.binding.ilHeader.assetsSubname;
        this.assetsTag = this.binding.ilHeader.assetsTag;
        this.layoutAssetsTag = this.binding.ilHeader.llAssetTag;
        this.ivAssetsOfficial = this.binding.ilHeader.ivAssetsOfficial;
        this.noFunctionView = this.binding.ilHeader.rcTips;
        this.projectName = this.binding.projectName;
        this.tvTokenId = this.binding.tokenId;
        this.tokenUrl = this.binding.tokenUrl;
        this.ivTokenUrlCopy = this.binding.ivUrlCopy;
        this.contractAddress = this.binding.contractAddress;
        this.llAddress = this.binding.rlAddress;
        this.tokenPublisher = this.binding.tokenPublisher;
        this.accuracy = this.binding.accuracy;
        this.llAccuracy = this.binding.rlAccuracy;
        this.tokenDescribe = this.binding.tokenDescribe;
        this.startTime = this.binding.startTime;
        this.endTime = this.binding.endTime;
        this.llEndtime = this.binding.rlEndtime;
        this.totalCirculation = this.binding.totalCirculation;
        this.llProjectName = this.binding.rlProjectName;
        this.llId = this.binding.rlId;
        this.llTokenUrl = this.binding.rlTokenUrl;
        this.llPublisher = this.binding.rlPublisher;
        this.llDescribe = this.binding.rlDescribe;
        this.llStartTime = this.binding.rlStartTime;
        this.llTotalCirculation = this.binding.rlTotalCirculation;
        this.tvPrice = this.binding.ilHeader.tvPrice;
        this.llPrice = this.binding.ilHeader.llPrice;
        this.tvMarket = this.binding.ilHeader.tvMarket;
        this.ivCopy = this.binding.ivCopy;
        this.rlTronscan = this.binding.ilHeader.rlTronscan;
        this.tvTronscan = this.binding.tvOpenInWebview;
        this.ivTronscan = this.binding.ivOpenInWebviewArraw;
        this.rlScam = this.binding.rlScam;
        this.ivOfficialImage = this.binding.ilHeader.ivOfficialImage;
        this.ivNational = this.binding.ilHeader.ivNational;
        this.ivIdCopy = this.binding.ivIdCopy;
        this.llTronscanRating = this.binding.ilHeader.llTronscanRating;
        this.tvRanking = this.binding.ilHeader.tvRanking;
        this.ivRankingTips = this.binding.ilHeader.ivRankingTips;
        this.tvReport = this.binding.tvProjectReport;
        this.liSecureInfo = this.binding.liSecureInfo;
        this.rlSecureBlackList = this.binding.rlSecureBlackList;
        this.ivSecureBlackList = this.binding.ivSecureBlackList;
        this.rlSecureCreateSelf = this.binding.rlSecureCreateSelf;
        this.ivSecureCreateSelf = this.binding.ivSecureCreateSelf;
        this.rlSecureHaveUrl = this.binding.rlSecureHaveUrl;
        this.ivSecureHaveUrl = this.binding.ivSecureHaveUrl;
        this.rlSecureOpenSource = this.binding.rlSecureOpenSource;
        this.ivSecureOpenSource = this.binding.ivSecureOpenSource;
        this.rlNoProxy = this.binding.rlNoProxy;
        this.ivNoProxy = this.binding.ivNoProxy;
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.TokenProjectDetailPage.TOKEN_PROJECT_DETAIL_SHOW);
        this.rxManager = new RxManager();
        this.isTrx = getIntent().getStringExtra(TronConfig.isTRX);
        this.tokenBean = (TokenBean) getIntent().getParcelableExtra(TronConfig.TRC);
        NumberFormat numberFormat = NumberFormat.getInstance();
        this.numberFormat = numberFormat;
        numberFormat.setGroupingUsed(false);
        new RoundingParams().setRoundAsCircle(true);
        NumberFormat.getInstance().setMaximumFractionDigits(6);
        this.tokenID = getIntent().getLongExtra(TronConfig.ID, 0L);
        if (this.tokenBean != null) {
            ((ProjectIntroductionPresenter) this.mPresenter).requestTokenInfo(this.isTrx, this.tokenBean, this.tokenID);
        }
        if (M.M_TRC20.equals(this.isTrx)) {
            this.isTrc10 = false;
            this.llAddress.setVisibility(View.VISIBLE);
            this.llAccuracy.setVisibility(View.VISIBLE);
            this.llEndtime.setVisibility(View.GONE);
            this.llId.setVisibility(View.GONE);
            this.imgToken.setTokenImage(this.tokenBean);
            if (this.tokenBean != null) {
                getCustomTokenInfo();
            }
        } else {
            this.isTrc10 = true;
            this.llAddress.setVisibility(View.GONE);
            this.llAccuracy.setVisibility(View.GONE);
            this.llId.setVisibility(View.VISIBLE);
            this.llEndtime.setVisibility(View.VISIBLE);
            this.imgToken.setTokenImage(this.tokenBean);
        }
        if (this.tokenBean != null) {
            String usdPrice = SpAPI.THIS.isUsdPrice() ? this.tokenBean.getUsdPrice() : this.tokenBean.getCnyPrice();
            if (this.tokenBean.getType() == 0) {
                usdPrice = String.valueOf(PriceUpdater.getTRX_price().getPrice());
            }
            if (StringTronUtil.isEmpty(usdPrice) || OfficialType.isScamOrUnSafeToken(this.tokenBean)) {
                TextView textView = this.tvPrice;
                StringBuilder sb = new StringBuilder();
                sb.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                sb.append("0");
                textView.setText(sb.toString());
            } else {
                double doubleValue = new BigDecimal(usdPrice).doubleValue();
                if (doubleValue < 1.0E-6d) {
                    TextView textView2 = this.tvPrice;
                    StringBuilder sb2 = new StringBuilder("<");
                    sb2.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                    sb2.append(" 0.000001");
                    textView2.setText(sb2.toString());
                } else {
                    TextView textView3 = this.tvPrice;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                    sb3.append(StringTronUtil.formatNumber6TruncatePlainScientific(doubleValue));
                    textView3.setText(sb3.toString());
                }
            }
            this.imgToken.setTokenImage(this.tokenBean);
            this.ivOfficialImage.setVisibility(this.tokenBean.getIsOfficial() == 1 ? View.VISIBLE : View.GONE);
            TokenItemUtil.initNationalFlagView(this.mContext, this.ivNational, this.tokenBean.getNational(), TextUtils.isEmpty(this.tokenBean.getShortName()) ? this.tokenBean.getName() : this.tokenBean.getShortName());
        }
        this.tokenDescribe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                if (motionEvent.getAction() == 1) {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
        this.tokenDescribe.setMovementMethod(ScrollingMovementMethod.getInstance());
        setCreditRanking();
        setWarningView();
        TokenItemUtil.initAssetsCreditRadingView(this, this.llTronscanRating, this.tokenBean, false);
    }

    private void setCreditRanking() {
        TokenBean tokenBean = this.tokenBean;
        if (tokenBean != null) {
            if (tokenBean.getTokenStatus() != 0) {
                this.llTronscanRating.setVisibility(View.GONE);
            }
            int i = this.tokenBean.isOfficial;
            if (i == -5) {
                String format = String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_scam));
                int indexOf = format.indexOf(":");
                if (indexOf < 0) {
                    indexOf = format.indexOf("：");
                }
                SpannableString spannableString = new SpannableString(format);
                if (indexOf > 0 && format.length() > 1) {
                    spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_EC)), indexOf + 1, format.length(), 33);
                }
                this.tvRanking.setText(spannableString);
            } else if (i == -4) {
                this.tvRanking.setText(String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_risk)));
            } else if (i == -3) {
                this.tvRanking.setText(String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_normal)));
            } else if (i == -2) {
                this.tvRanking.setText(String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_neural)));
            } else if (i != -1) {
                this.tvRanking.setText(String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_normal)));
            } else {
                this.tvRanking.setText(String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_unkown)));
            }
        }
        TokenItemUtil.initAssetsCreditRadingView(this, this.llTronscanRating, this.tokenBean, false);
    }

    private void setWarningView() {
        TokenItemUtil.initScamToDetailView(this, this.rlScam, this.tokenBean);
    }

    private void setTokenTag(int i) {
        this.layoutAssetsTag.setVisibility(View.VISIBLE);
        if (i == 0) {
            this.assetsTag.setVisibility(View.GONE);
            this.layoutAssetsTag.setVisibility(View.GONE);
        } else if (i == 1) {
            this.assetsTag.setBackground(this.mContext.getResources().getDrawable(R.drawable.roundborder_153c_2));
            this.assetsTag.setText(TronConfig.TRC10);
            this.assetsTag.setTextColor(this.mContext.getResources().getColor(R.color.blue_3c));
        } else if (i == 2) {
            this.assetsTag.setBackground(this.mContext.getResources().getDrawable(R.drawable.roundborder_1557_2));
            this.assetsTag.setText(TronConfig.TRC20);
            this.assetsTag.setTextColor(this.mContext.getResources().getColor(R.color.green_57));
        } else if (i == 5) {
            this.assetsTag.setBackground(this.mContext.getResources().getDrawable(R.drawable.roundborder_ffa928_2));
            this.assetsTag.setText("TRC721");
            this.assetsTag.setTextColor(this.mContext.getResources().getColor(R.color.orange_FFA9));
        } else {
            this.layoutAssetsTag.setVisibility(View.GONE);
        }
    }

    private void updateTrc10(long j) {
        AssetIssueContractDao assetIssueContractDao = (AssetIssueContractDao) DaoUtils.getDicInstance().QueryById(j, AssetIssueContractDao.class);
        if (assetIssueContractDao == null) {
            return;
        }
        if (!TronConfig.hasNet) {
            ToastUtil.getInstance().showToast(this, R.string.network_busy);
        }
        if (j == 1002000 && IRequest.isRelease() && SpAPI.THIS.getCurIsMainChain()) {
            this.tokenFullName.setText("(BitTorrent Old)");
            this.tokenName.setText("BTTOLD");
            this.projectName.setText("BitTorrent Old(BTTOLD)");
        } else {
            String name = assetIssueContractDao.getName();
            if (StringTronUtil.isEmpty(assetIssueContractDao.getName())) {
                this.tokenFullName.setText("");
                name = HelpFormatter.DEFAULT_LONG_OPT_PREFIX;
            } else {
                TextView textView = this.tokenFullName;
                textView.setText("(" + name + ")");
            }
            TextView textView2 = this.tokenName;
            if (!assetIssueContractDao.getAbbr().equals("")) {
                name = assetIssueContractDao.getAbbr();
            }
            textView2.setText(name);
            if (!TextUtils.isEmpty(assetIssueContractDao.getName())) {
                if (!TextUtils.isEmpty(assetIssueContractDao.getAbbr())) {
                    TextView textView3 = this.projectName;
                    textView3.setText(assetIssueContractDao.getName() + "(" + assetIssueContractDao.getAbbr() + ")");
                } else {
                    this.projectName.setText(assetIssueContractDao.getName());
                }
            }
        }
        this.imgToken.setImageResource(R.mipmap.ic_token_default_gray);
        setTokenTag(1);
        try {
            this.endTime.setText(DateUtils.longToString(assetIssueContractDao.getEnd_time(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
        } catch (ParseException e) {
            LogUtils.e(e);
        }
        try {
            this.startTime.setText(DateUtils.longToString(assetIssueContractDao.getStart_time(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
        } catch (ParseException e2) {
            LogUtils.e(e2);
        }
        TextView textView4 = this.tvTokenId;
        ImageView imageView = this.ivIdCopy;
        setText(textView4, imageView, assetIssueContractDao.getId() + "");
        if (OfficialType.isScamOrUnSafeToken(this.tokenBean)) {
            this.ivTokenUrlCopy.setVisibility(View.GONE);
            setText(this.tokenUrl, null, HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        } else {
            setText(this.tokenUrl, this.ivTokenUrlCopy, assetIssueContractDao.getUrl());
        }
        this.tolkenUrlStr = assetIssueContractDao.getUrl();
        setText(this.tokenPublisher, null, assetIssueContractDao.getOwner_address());
        if (OfficialType.isScamOrUnSafeToken(this.tokenBean)) {
            setText(this.tokenDescribe, null, HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        } else {
            setText(this.tokenDescribe, null, assetIssueContractDao.getDescription());
        }
        try {
            double parseDouble = StringTronUtil.parseDouble(String.valueOf(assetIssueContractDao.getTotal_supply()));
            int i = this.tokenBean.precision;
            if (i == 0) {
                i = assetIssueContractDao.precision;
            }
            if (i != 0) {
                this.totalCirculation.setText(this.numberFormat.format(parseDouble / Math.pow(10.0d, i)));
                return;
            }
            this.totalCirculation.setText(this.numberFormat.format(new BigDecimal(assetIssueContractDao.getTotal_supply())));
        } catch (Exception e3) {
            LogUtils.e(e3);
            this.totalCirculation.setText(this.numberFormat.format(new BigDecimal(assetIssueContractDao.getTotal_supply())));
        }
    }

    @Override
    public void updateSecureInfo(TokenSecureInfoOutput.TokenSecureInfoDataBean tokenSecureInfoDataBean) {
        TokenBean tokenBean;
        if (M.M_TRX.equals(this.isTrx)) {
            this.liSecureInfo.setVisibility(View.GONE);
        } else if (tokenSecureInfoDataBean != null) {
            if (tokenSecureInfoDataBean.getIsProxy() == -99) {
                this.rlNoProxy.setVisibility(View.GONE);
            } else if (tokenSecureInfoDataBean.getIsProxy() == 1) {
                this.rlNoProxy.setVisibility(View.VISIBLE);
                this.ivNoProxy.setImageResource(R.mipmap.token_secure_err);
            } else if (tokenSecureInfoDataBean.getIsProxy() == 0) {
                this.rlNoProxy.setVisibility(View.VISIBLE);
                this.ivNoProxy.setImageResource(R.mipmap.token_secure_ok);
            }
            if (tokenSecureInfoDataBean.getBlackListType() == -99 || tokenSecureInfoDataBean.getBlackListType() == 0) {
                this.rlSecureBlackList.setVisibility(View.GONE);
            } else if (tokenSecureInfoDataBean.getBlackListType() == 1) {
                this.rlSecureBlackList.setVisibility(View.VISIBLE);
                this.ivSecureBlackList.setImageResource(R.mipmap.token_secure_err);
            } else if (tokenSecureInfoDataBean.getBlackListType() == 2) {
                this.rlSecureBlackList.setVisibility(View.VISIBLE);
                this.ivSecureBlackList.setImageResource(R.mipmap.token_secure_ok);
            }
            if (tokenSecureInfoDataBean.getLevel() == -99 || ((tokenBean = this.tokenBean) != null && (tokenBean.getTokenStatus() == 1 || this.tokenBean.getTokenStatus() == 2))) {
                this.tvRanking.setVisibility(View.GONE);
                this.ivRankingTips.setVisibility(View.GONE);
            } else if (tokenSecureInfoDataBean.getLevel() == 0) {
                this.tvRanking.setText(String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_unkown)));
            } else if (tokenSecureInfoDataBean.getLevel() == 1) {
                this.tvRanking.setText(String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_neural)));
            } else if (tokenSecureInfoDataBean.getLevel() == 2) {
                this.tvRanking.setText(String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_normal)));
            } else if (tokenSecureInfoDataBean.getLevel() == 3) {
                this.tvRanking.setText(String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_risk)));
            } else if (tokenSecureInfoDataBean.getLevel() == 4 || tokenSecureInfoDataBean.getLevel() == 5) {
                String format = String.format(getString(R.string.asset_credit_rating), getString(R.string.asset_evaluate_type_scam));
                int indexOf = format.indexOf(":");
                if (indexOf < 0) {
                    indexOf = format.indexOf("：");
                }
                SpannableString spannableString = new SpannableString(format);
                if (indexOf > 0 && format.length() > 1) {
                    spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_EC)), indexOf + 1, format.length(), 33);
                }
                this.tvRanking.setText(spannableString);
            }
            if (tokenSecureInfoDataBean.getIncreaseTotalSupply() == -99 || tokenSecureInfoDataBean.getIncreaseTotalSupply() == 0) {
                this.rlSecureCreateSelf.setVisibility(View.GONE);
            } else if (tokenSecureInfoDataBean.getIncreaseTotalSupply() == 1) {
                this.rlSecureCreateSelf.setVisibility(View.VISIBLE);
                this.ivSecureCreateSelf.setImageResource(R.mipmap.token_secure_err);
            } else if (tokenSecureInfoDataBean.getIncreaseTotalSupply() == 2) {
                this.rlSecureCreateSelf.setVisibility(View.VISIBLE);
                this.ivSecureCreateSelf.setImageResource(R.mipmap.token_secure_ok);
            }
            if (tokenSecureInfoDataBean.getHasUrl() == -99) {
                this.rlSecureHaveUrl.setVisibility(View.GONE);
            } else if (tokenSecureInfoDataBean.getHasUrl() == 1) {
                this.rlSecureHaveUrl.setVisibility(View.VISIBLE);
                this.ivSecureHaveUrl.setImageResource(R.mipmap.token_secure_err);
            } else if (tokenSecureInfoDataBean.getHasUrl() == 0) {
                this.rlSecureHaveUrl.setVisibility(View.VISIBLE);
                this.ivSecureHaveUrl.setImageResource(R.mipmap.token_secure_ok);
            }
            if (tokenSecureInfoDataBean.getOpenSource() == -99) {
                this.rlSecureOpenSource.setVisibility(View.GONE);
            } else if (tokenSecureInfoDataBean.getOpenSource() == 1) {
                this.rlSecureOpenSource.setVisibility(View.VISIBLE);
                this.ivSecureOpenSource.setImageResource(R.mipmap.token_secure_ok);
            } else if (tokenSecureInfoDataBean.getOpenSource() == 0) {
                this.rlSecureOpenSource.setVisibility(View.VISIBLE);
                this.ivSecureOpenSource.setImageResource(R.mipmap.token_secure_err);
            }
        } else {
            this.liSecureInfo.setVisibility(View.GONE);
        }
    }

    public void updateByTokenBean(TokenBean tokenBean) {
        dismissLoadingPage();
        if (tokenBean != null) {
            if (!TronConfig.hasNet) {
                ToastUtil.getInstance().showToast(this, R.string.network_busy);
            }
            String name = tokenBean.getName();
            if (StringTronUtil.isEmpty(tokenBean.getName())) {
                this.tokenFullName.setText("");
                name = HelpFormatter.DEFAULT_LONG_OPT_PREFIX;
            } else {
                TextView textView = this.tokenFullName;
                textView.setText("(" + name + ")");
            }
            TextView textView2 = this.tokenName;
            if (!tokenBean.getShortName().equals("")) {
                name = tokenBean.getShortName();
            }
            textView2.setText(name);
            if (!StringTronUtil.isEmpty(tokenBean.logoUrl) && !tokenBean.logoUrl.endsWith("svg")) {
                this.imgToken.setTokenImage(tokenBean);
            } else {
                this.imgToken.setImageResource(AssetsConfig.getTokenDefaultLogoIconId(tokenBean));
            }
            if (!TextUtils.isEmpty(tokenBean.name)) {
                if (!TextUtils.isEmpty(tokenBean.getShortName())) {
                    if (M.M_TRX.equals(this.isTrx)) {
                        this.projectName.setText(tokenBean.name);
                    } else {
                        TextView textView3 = this.projectName;
                        textView3.setText(tokenBean.name + "(" + tokenBean.getShortName() + ")");
                    }
                } else {
                    this.projectName.setText(tokenBean.name);
                }
            } else if (!TextUtils.isEmpty(tokenBean.getShortName())) {
                this.projectName.setText(tokenBean.getShortName());
            }
            if (StringTronUtil.isEmpty(this.tokenBean.id)) {
                this.llId.setVisibility(View.GONE);
            }
            this.rlTronscan.setVisibility(View.GONE);
            if (M.M_TRC20.equals(this.isTrx)) {
                setTokenTag(2);
                this.llId.setVisibility(View.GONE);
            } else if (M.M_TRX.equals(this.isTrx)) {
                setTokenTag(0);
                this.llEndtime.setVisibility(View.GONE);
                this.llPublisher.setVisibility(View.GONE);
                this.llId.setVisibility(View.GONE);
                Price tRX_price = PriceUpdater.getTRX_price();
                this.llPrice.setVisibility(View.VISIBLE);
                this.tvMarket.setVisibility(View.GONE);
                TextView textView4 = this.tvPrice;
                StringBuilder sb = new StringBuilder();
                sb.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                sb.append(StringTronUtil.formatNumber6TruncatePlainScientific(tRX_price.getPrice()));
                textView4.setText(sb.toString());
            } else if (M.M_TRC10.equals(this.isTrx)) {
                setTokenTag(1);
                TextView textView5 = this.tvTokenId;
                ImageView imageView = this.ivIdCopy;
                setText(textView5, imageView, this.tokenID + "");
                this.llAddress.setVisibility(View.GONE);
                this.llId.setVisibility(View.VISIBLE);
            }
            if (StringTronUtil.isEmpty(tokenBean.getHomePage()) || OfficialType.isScamOrUnSafeToken(tokenBean)) {
                this.ivTokenUrlCopy.setVisibility(View.GONE);
                setText(this.tokenUrl, null, HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            } else {
                setText(this.tokenUrl, this.ivTokenUrlCopy, tokenBean.getHomePage());
            }
            this.tolkenUrlStr = tokenBean.getHomePage();
            setText(this.contractAddress, this.ivCopy, tokenBean.getContractAddress());
            TextView textView6 = this.accuracy;
            textView6.setText(tokenBean.getPrecision() + "");
            this.llAccuracy.setVisibility(View.VISIBLE);
            if (StringTronUtil.isEmpty(tokenBean.getTokenDesc()) || OfficialType.isScamOrUnSafeToken(tokenBean)) {
                setText(this.tokenDescribe, null, HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            } else {
                setText(this.tokenDescribe, null, tokenBean.getTokenDesc());
            }
            if (StringTronUtil.isEmpty(tokenBean.getIssueAddress())) {
                this.llPublisher.setVisibility(View.GONE);
            } else {
                setText(this.tokenPublisher, null, tokenBean.getIssueAddress());
            }
            if (StringTronUtil.isEmpty(tokenBean.getIssueTime())) {
                this.llStartTime.setVisibility(View.GONE);
            } else {
                setText(this.startTime, null, tokenBean.getIssueTime());
            }
            if (!this.noTotalSupplyAbi) {
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(tokenBean.getPrecision());
                try {
                    if (tokenBean.getPrecision() != 0) {
                        this.totalCirculation.setText(numberFormat.format(new BigDecimal(StringTronUtil.formatNumberTruncateNoDots(BigDecimalUtils.toBigDecimal(tokenBean.getTotalSupply()), tokenBean.getPrecision()))));
                    } else {
                        this.totalCirculation.setText(numberFormat.format(new BigDecimal(tokenBean.getTotalSupply())));
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                    this.totalCirculation.setText(tokenBean.getTotalSupply());
                }
            } else {
                this.totalCirculation.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            }
        }
        if (tokenBean != null && (tokenBean.getTokenStatus() == 1 || tokenBean.getTokenStatus() == 2)) {
            this.liSecureInfo.setVisibility(View.GONE);
            this.rlScam.setVisibility(View.GONE);
            return;
        }
        setWarningView();
    }

    @Override
    public void updateTrc10(Trc20DetailBean.Trc10TokensBean trc10TokensBean) {
        dismissLoadingPage();
        if (trc10TokensBean == null) {
            TokenBean tokenBean = this.tokenBean;
            if (tokenBean != null) {
                updateByTokenBean(tokenBean);
                return;
            } else {
                updateTrc10(this.tokenID);
                return;
            }
        }
        if (!TronConfig.hasNet) {
            ToastUtil.getInstance().showToast(this, R.string.network_busy);
        }
        if (this.tokenBean.getTokenStatus() == 0) {
            if (StringTronUtil.isEmpty(this.tokenBean.id)) {
                this.tokenBean.setName(trc10TokensBean.getAbbr());
                this.tokenBean.setShortName(trc10TokensBean.getName());
                this.tokenBean.setLogoUrl(trc10TokensBean.getImgUrl());
            } else {
                this.tokenBean.setName(trc10TokensBean.getName());
                this.tokenBean.setShortName(trc10TokensBean.getAbbr());
                this.tokenBean.setLogoUrl(trc10TokensBean.getImgUrl());
            }
        }
        TokenBean tokenBean2 = this.tokenBean;
        tokenBean2.setId(trc10TokensBean.getTokenID() + "");
        this.tokenBean.setHomePage(trc10TokensBean.getWebsite());
        this.tokenBean.setContractAddress("");
        this.tokenBean.setIssueAddress(trc10TokensBean.getOwnerAddress());
        this.tokenBean.setPrecision(trc10TokensBean.getPrecision().intValue());
        this.tokenBean.setDescription(trc10TokensBean.getDescription());
        this.tokenBean.setTokenDesc(trc10TokensBean.getDescription());
        try {
            this.tokenBean.setIssueTime(DateUtils.diffLanguageDate(trc10TokensBean.getStartTime().longValue()));
            this.tokenBean.setIssueTime(DateUtils.longToString(trc10TokensBean.getStartTime().longValue(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
        } catch (ParseException e) {
            LogUtils.e(e);
        }
        try {
            if (trc10TokensBean.getPrecision().intValue() != 0) {
                this.tokenBean.setTotalSupply(BigDecimalUtils.div_(trc10TokensBean.getTotalSupply(), Double.valueOf(Math.pow(10.0d, trc10TokensBean.getPrecision().intValue()))).toPlainString());
            } else {
                this.tokenBean.setTotalSupply(String.valueOf(trc10TokensBean.getTotalSupply()));
            }
        } catch (Throwable th) {
            SentryUtil.captureException(th);
        }
        updateByTokenBean(this.tokenBean);
    }

    @Override
    public void updateByTRC20TokenBean(Trc20DetailBean.Trc20TokensBean trc20TokensBean) {
        dismissLoadingPage();
        if (trc20TokensBean == null) {
            updateByTokenBean(this.tokenBean);
            return;
        }
        if (!TronConfig.hasNet) {
            ToastUtil.getInstance().showToast(this, R.string.network_busy);
        }
        if (this.tokenBean.getTokenStatus() == 0) {
            this.tokenBean.setName(trc20TokensBean.getName());
            this.tokenBean.setShortName(trc20TokensBean.getSymbol());
            this.tokenBean.setLogoUrl(trc20TokensBean.getIcon_url());
        }
        this.tokenBean.setHomePage(trc20TokensBean.getHome_page());
        this.tokenBean.setContractAddress(trc20TokensBean.getContract_address());
        this.tokenBean.setIssueAddress(trc20TokensBean.getIssue_address());
        this.tokenBean.setPrecision(trc20TokensBean.getDecimals());
        this.tokenBean.setIssueTime(trc20TokensBean.getIssue_time());
        this.tokenBean.setDescription(trc20TokensBean.getToken_desc());
        try {
            if (trc20TokensBean.getDecimals() != 0) {
                this.tokenBean.setTotalSupply(BigDecimalUtils.div_(trc20TokensBean.getTotal_supply_with_decimals(), Double.valueOf(Math.pow(10.0d, trc20TokensBean.getDecimals()))).toPlainString());
            } else {
                this.tokenBean.setTotalSupply(String.valueOf(trc20TokensBean.getTotal_supply()));
            }
        } catch (Throwable th) {
            SentryUtil.captureException(th);
        }
        updateByTokenBean(this.tokenBean);
    }

    private void setText(TextView textView, ImageView imageView, String str) {
        if (StringTronUtil.isEmpty(str) && imageView != null) {
            textView.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            if (imageView != null) {
                imageView.setVisibility(View.GONE);
                return;
            }
            return;
        }
        textView.setText(str);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEvent(AnalyticsHelper.TokenProjectDetailPage.TOKEN_PROJECT_DETAIL_CLICK_BACK);
        finish();
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                String name;
                switch (view.getId()) {
                    case R.id.iv_copy:
                        UIUtils.copy(contractAddress.getText().toString().trim());
                        Toast((int) R.string.copy_susscess);
                        return;
                    case R.id.iv_id_copy:
                        UIUtils.copy(tvTokenId.getText().toString().trim());
                        Toast((int) R.string.copy_susscess);
                        return;
                    case R.id.iv_open_in_webview_arraw:
                    case R.id.tv_open_in_webview:
                        AnalyticsHelper.logEvent(AnalyticsHelper.TokenProjectDetailPage.TOKEN_PROJECT_DETAIL_CLICK_VIEW_BROWSE);
                        if (M.M_TRC20.equals(isTrx)) {
                            ProjectIntroductionActivity projectIntroductionActivity = ProjectIntroductionActivity.this;
                            UIUtils.toTrc20TokenDetail(projectIntroductionActivity, projectIntroductionActivity.tokenBean.getContractAddress());
                            return;
                        } else if (M.M_TRX.equals(isTrx)) {
                            UIUtils.toTRXProtocol(ProjectIntroductionActivity.this);
                            return;
                        } else if (M.M_TRC10.equals(isTrx)) {
                            ProjectIntroductionActivity projectIntroductionActivity2 = ProjectIntroductionActivity.this;
                            UIUtils.toTrc10TokenProtocol(projectIntroductionActivity2, projectIntroductionActivity2.tokenBean.id);
                            return;
                        } else {
                            return;
                        }
                    case R.id.iv_url_copy:
                        UIUtils.copy(tokenUrl.getText().toString().trim());
                        Toast((int) R.string.copy_susscess);
                        return;
                    case R.id.token_url:
                        try {
                            if (TextUtils.isEmpty(tokenUrl.getText())) {
                                return;
                            }
                            if ((tokenBean == null || tokenBean.isOfficial != -5) && !TextUtils.equals(tokenUrl.getText(), HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                                if (!tolkenUrlStr.startsWith(ChainUtil.Request_HTTP) && !tolkenUrlStr.startsWith("https://")) {
                                    ProjectIntroductionActivity projectIntroductionActivity3 = ProjectIntroductionActivity.this;
                                    projectIntroductionActivity3.tolkenUrlStr = "https://" + tolkenUrlStr;
                                }
                                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(tolkenUrlStr)));
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            LogUtils.e(e);
                            return;
                        }
                    case R.id.tv_project_report:
                        AnalyticsHelper.logEvent(AnalyticsHelper.TokenProjectDetailPage.TOKEN_PROJECT_DETAIL_CLICK_REPORT);
                        int i = M.M_TRC20.equals(isTrx) ? 2 : 1;
                        String str = "";
                        if (M.M_TRC20.equals(isTrx)) {
                            if (tokenBean != null) {
                                str = tokenBean.getContractAddress();
                                name = StringTronUtil.isEmpty(tokenBean.getShortName()) ? tokenBean.getName() : tokenBean.getShortName();
                            }
                            name = "";
                        } else {
                            if (tokenBean != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(StringTronUtil.isEmpty(tokenBean.getId()) ? "0" : tokenBean.getId());
                                sb.append("");
                                str = sb.toString();
                                name = StringTronUtil.isEmpty(tokenBean.getShortName()) ? tokenBean.getName() : tokenBean.getShortName();
                            }
                            name = "";
                        }
                        ProjectIntroductionActivity projectIntroductionActivity4 = ProjectIntroductionActivity.this;
                        TokenReportActivity.start(projectIntroductionActivity4, projectIntroductionActivity4.tokenBean, str, i, name);
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.tokenUrl.setOnClickListener(noDoubleClickListener2);
        this.binding.ivCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.ivIdCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.ivUrlCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.tvProjectReport.setOnClickListener(noDoubleClickListener2);
        this.binding.ivOpenInWebviewArraw.setOnClickListener(noDoubleClickListener2);
        this.binding.tvOpenInWebview.setOnClickListener(noDoubleClickListener2);
    }

    private void getCustomTokenInfo() {
        if (this.tokenBean.getTokenStatus() != 0) {
            this.liSecureInfo.setVisibility(View.GONE);
            CustomTokensModel customTokensModel = new CustomTokensModel();
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null) {
                customTokensModel.queryCustomToken(selectedPublicWallet.getAddress(), this.tokenBean.getContractAddress()).subscribe(new IObserver(new ICallback<QueryCustomTokenOutput>() {
                    @Override
                    public void onFailure(String str, String str2) {
                    }

                    @Override
                    public void onResponse(String str, QueryCustomTokenOutput queryCustomTokenOutput) {
                        String[] split;
                        if (queryCustomTokenOutput == null || queryCustomTokenOutput.getData() == null) {
                            return;
                        }
                        CustomTokenBean data = queryCustomTokenOutput.getData();
                        ArrayList arrayList = new ArrayList();
                        String noFunctions = data.getNoFunctions();
                        if (!StringTronUtil.isEmpty(noFunctions) && (split = noFunctions.trim().split(",")) != null) {
                            for (String str2 : split) {
                                int noFunctionErrorStrId = CustomTokenStatus.getNoFunctionErrorStrId(str2);
                                if (noFunctionErrorStrId != 0) {
                                    arrayList.add(getResources().getString(noFunctionErrorStrId));
                                }
                                if (CustomTokenStatus.TOTAL_SUPPLY.equals(str2)) {
                                    noTotalSupplyAbi = true;
                                }
                            }
                        }
                        if (arrayList.size() > 0) {
                            noFunctionView.setVisibility(View.VISIBLE);
                            noFunctionView.setData(arrayList);
                        }
                        if (noTotalSupplyAbi) {
                            totalCirculation.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        rxManager.add(disposable);
                    }
                }, "getCustomTokenInfo"));
                return;
            }
            return;
        }
        this.liSecureInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.rxManager.clear();
    }
}
