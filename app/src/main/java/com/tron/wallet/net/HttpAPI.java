package com.tron.wallet.net;

import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.addassets2.bean.BaseOutput;
import com.tron.wallet.business.addassets2.bean.DelMyAssetsOutput;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.NftDataOutput;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.asset.RecommendAssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftAssetOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftDetailOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftInfoOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTransferOutput;
import com.tron.wallet.business.customtokens.bean.AddCustomTokenOutput;
import com.tron.wallet.business.customtokens.bean.QueryCustomTokenOutput;
import com.tron.wallet.business.customtokens.bean.SyncCustomTokenOutput;
import com.tron.wallet.business.finance.bean.FinanceAccountBalanceOutput;
import com.tron.wallet.business.finance.bean.FinanceDataBean;
import com.tron.wallet.business.finance.bean.FinanceDataSummaryOutput;
import com.tron.wallet.business.maintab.bean.DeviceInfoBean;
import com.tron.wallet.business.mutil.bean.MultiAddressOutput;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckResultBean;
import com.tron.wallet.business.stakev2.bean.DelegatedResourceOutput;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeOutput;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailOutput;
import com.tron.wallet.business.tabdapp.bean.DappHotBean;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.business.tabdapp.bean.DappSearchBean;
import com.tron.wallet.business.tabdapp.bean.DappToMainFeeResponse;
import com.tron.wallet.business.tabdapp.browser.search.SearchDappResultBean;
import com.tron.wallet.business.tabdapp.home.bean.DAppBannerOutput;
import com.tron.wallet.business.tabdapp.home.bean.DAppListOutput;
import com.tron.wallet.business.tabdapp.jsbridge.BlackResultListBean;
import com.tron.wallet.business.tabmy.bean.CommunityOutput;
import com.tron.wallet.business.tabmy.myhome.settings.bean.MainNodeOutput;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeOutput;
import com.tron.wallet.business.tabmy.proposals.bean.ProposalListOutPut;
import com.tron.wallet.business.tabswap.bean.AppStatusOutput;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.business.tokendetail.mvp.TokenSecureInfoOutput;
import com.tron.wallet.business.vote.bean.RewardOutput;
import com.tron.wallet.business.vote.bean.SearchWitnessResult;
import com.tron.wallet.business.vote.bean.SrTotalVotesResponse;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.bean.AllTransferOutput;
import com.tron.wallet.common.bean.ChainparametersOutPut;
import com.tron.wallet.common.bean.DappJsOutput;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.bean.InfoBean;
import com.tron.wallet.common.bean.NoticeRemindBean;
import com.tron.wallet.common.bean.Result;
import com.tron.wallet.common.bean.Result2;
import com.tron.wallet.common.bean.RiskAccountOutput;
import com.tron.wallet.common.bean.TrxPriceOutput;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.bean.token.TransactionResult;
import com.tron.wallet.common.bean.token.TransferOutput;
import com.tron.wallet.common.bean.token.Trc20DetailBean;
import com.tron.wallet.common.bean.update.UpdateOutput;
import com.tron.wallet.common.bean.user.SplashOutput;
import com.tron.wallet.common.bean.user.SystemConfigOutput;
import com.tron.wallet.common.utils.addressscam.AddressScamResponseBean;
import com.tron.wallet.db.bean.DappAuthorizedProjectBean;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface HttpAPI {
    @POST("/api/activity/add")
    Observable<Object> addDappRecord(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("/api/address/reward")
    Observable<RewardOutput> canReward(@Query("address") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/transfer/accounts")
    Observable<AddressScamResponseBean> checkAddressIsScam(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/financial/check")
    Observable<Result2<AppStatusOutput>> checkAppStatus(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/stat/action")
    Observable<String> dataStat(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/api/activity/play_screen/deal")
    Observable<InfoBean> deal(@Body RequestBody requestBody);

    @GET("/api/dapp/v2/dapp/search")
    Observable<DappSearchBean> doSearchDapp(@Query("name") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/api/wallet/fail_transfer")
    Observable<Object> fail_transfer(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @POST("/api/wallet/v2/account/list")
    Observable<AccountBalanceOutput> getAccountInfosList(@Query("address") String str, @Query("bizType") int i, @Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @POST("/api/wallet/v2/account/list")
    Observable<AccountBalanceOutput> getAccountInfosList2(@Query("address") String str, @Query("bizType") int i, @Query("tx") boolean z, @Body RequestBody requestBody);

    @GET("/api/voting/v2/witness")
    Observable<WitnessOutput> getAll(@Query("sort_type") int i, @Query("has_all") int i2);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/nft/getAllCollection")
    Observable<NftAssetOutput> getAllCollections(@Query("address") String str, @Query("version") String str2);

    @GET("/api/simple-transaction")
    Observable<AllTransferOutput> getAllTRXTransfer(@Query("address") String str, @Query("to") String str2, @Query("from") String str3, @Query("start") int i, @Query("limit") int i2);

    @GET("/api/exchange/marketPair/list")
    Observable<AssetsHomeOutput> getAssetsData();

    @POST("/api/wallet/assetlist")
    Observable<AssetsHomeOutput> getAssetsData(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("/api/chainparameters")
    @Deprecated
    Observable<ChainparametersOutPut> getChainparameters();

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/nft/getCollectionList")
    Observable<NftInfoOutput> getCollection(@Query("address") String str, @Query("tokenAddress") String str2, @Query("pageIndex") int i, @Query("pageSize") int i2);

    @Headers({"Signature: true"})
    @GET("api/wallet/nft/getCollectionInfo")
    Observable<NftItemInfoOutput> getCollectionInfo(@Query("address") String str, @Query("tokenAddress") String str2, @Query("assetId") String str3);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @POST("api/wallet/nft/getCollectionInfos")
    Observable<NftDetailOutput> getCollectionInfos(@Query("address") String str, @Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("/api/transfer/v2/trc721")
    Observable<NftTransferOutput> getCollectionTransferList(@Query("address") String str, @Query("relatedAddress") String str2, @Query("trc20Id") String str3, @Query("start") int i, @Query("limit") int i2, @Query("direction") String str4, @Query("db_version") int i3);

    @GET("/api/v1/wallet/community")
    Observable<CommunityOutput> getCommunityContent();

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: false"})
    @GET("api/dapp/v2/authorized_project")
    Observable<Result<List<DappAuthorizedProjectBean>>> getDappAuthorizedProject();

    @GET("/api/dapp/v2/dapp/hot_search")
    Observable<DappHotBean> getDappHotList();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("/api/web/v1/tronweb")
    Observable<DappJsOutput> getDappJs();

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/dapp/v3/search")
    Observable<SearchDappResultBean> getDappSearch(@Query("word") String str, @Query("address") String str2);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("/api/transfer/dappToMainFee")
    Observable<DappToMainFeeResponse> getDappToMainFee();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("api/stake/from/{address}")
    Observable<StakeResourceDetailForMeOutput> getDelegateForMe(@Path("address") String str, @Query("resource") int i, @Query("stakeVersion") int i2);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("api/stake/delegate/{address}")
    Observable<StakeResourceDetailOutput> getDelegateForOther(@Path("address") String str, @Query("resource") int i, @Query("lockType") boolean z, @Query("pageIndex") long j);

    @Headers({"Signature: true"})
    @GET("/api/wallet/v2/getDelegatedResource")
    Observable<DelegatedResourceOutput> getDelegatedResource(@Query("address") String str, @Query("type") int i, @Query("sourceType") int i2, @Query("start") int i3, @Query("limit") int i4, @Query("reverse") boolean z);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/api/wallet/v2/assets")
    Observable<FinanceAccountBalanceOutput> getFinanceAccountBalance(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/financial/totalAssets")
    Observable<FinanceDataBean> getFinancialTotalAssets(@Body RequestBody requestBody);

    @GET("/api/activity/play_screen/info")
    Observable<InfoBean> getInfo();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/api/wallet/node_info")
    Observable<MainNodeOutput> getMainNodes(@Query("address") String str, @Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("/api/wallet/v2/auth")
    Observable<Result2<List<MultiAddressOutput>>> getMultiAddress(@Query("address") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/nft/allCollections")
    Observable<NftDataOutput> getMyAllCollections(@Query("address") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/financial/myFinancialProjectList")
    Observable<FinanceDataBean> getMyFinancialProjectList(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/financial/myFinancialTokenList")
    Observable<FinanceDataBean> getMyTokenFinancialList(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/api/v1/wallet/nodes")
    Observable<NodeOutput> getNodes(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("/api/v1/wallet/getNoticeRemind")
    Observable<NoticeRemindBean> getNoticeRemind();

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/v2/white")
    Observable<DappProjectIOutput> getProjectInfo(@Query("contract_address") String str, @Query("address") String str2);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("/api/proposal")
    @Deprecated
    Observable<ProposalListOutPut> getProposalList(@Query("sort") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("/api/wallet/multi/trx_record")
    Observable<DealSignOutput> getSignFailureTransfer(@Query("address") String str, @Query("start") int i, @Query("limit") int i2, @Query("state") int i3, @Query("netType") String str2);

    @GET("/api/v1/wallet/startup")
    Observable<SplashOutput> getSplashPage(@Query("width") String str, @Query("height") String str2);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("api/vote")
    @Deprecated
    Observable<SrTotalVotesResponse> getSrTotalVotes(@Query("limit") int i, @Query("start") int i2, @Query("candidate") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("api/stake/total/{address}")
    Observable<String> getStakeTotal(@Path("address") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("api/wallet/get_config")
    Observable<SystemConfigOutput> getSystemConfig();

    @GET("/api/token")
    Observable<Trc20DetailBean> getTRC10Detail(@Query("id") String str);

    @GET("/api/token_trc20")
    Observable<Trc20DetailBean> getTRC20Detail(@Query("contract") String str);

    @GET("/api/transfer/token10")
    Observable<Object> getTRX10Transfer(@Query("address") String str, @Query("start") int i, @Query("limit") int i2, @Query("start_timestamp") long j, @Query("end_timestamp") long j2, @Query("direction") int i3, @Query("reverse") boolean z, @Query("fee") boolean z2, @Query("filter_small") boolean z3, @Query("trc10Id") String str2, @Query("db_version") int i4);

    @GET("/api/transfer/trc20_status")
    Observable<Object> getTRX20Transfer(@Query("address") String str, @Query("start") int i, @Query("limit") int i2, @Query("start_timestamp") long j, @Query("end_timestamp") long j2, @Query("direction") int i3, @Query("reverse") boolean z, @Query("fee") boolean z2, @Query("filter_small") boolean z3, @Query("trc20Id") String str2, @Query("db_version") int i4);

    @GET("/api/transfer/trx")
    Observable<Object> getTRXTransfer(@Query("address") String str, @Query("start") int i, @Query("limit") int i2, @Query("start_timestamp") long j, @Query("end_timestamp") long j2, @Query("direction") int i3, @Query("reverse") boolean z, @Query("fee") boolean z2, @Query("filter_small") boolean z3, @Query("db_version") int i4);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/financial/tokenFinancialList")
    Observable<FinanceDataBean> getTokenFinancialList(@Body RequestBody requestBody);

    @Headers({"Accept: application/json", "Signature: true"})
    @GET("api/wallet/v2/tokenSecurityInfo")
    Observable<TokenSecureInfoOutput> getTokenSecurityInfo(@Query("tokenType") int i, @Query("tokenAddress") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/financial/totalAssets")
    Observable<FinanceDataSummaryOutput> getTotalAssets(@Body RequestBody requestBody);

    @GET("api/transaction-info")
    Observable<TransactionInfoBean> getTrasactionInfo(@Query("hash") String str);

    @GET("api/v1/wallet/getCoinCapTrxPrice")
    Observable<TrxPriceOutput> getTrxPrice();

    @GET("api/swap/v1/exchanges?type=1")
    Observable<SwapWhiteListOutput> getWhiteListTokens();

    @GET("/api/voting/v2/witness")
    Observable<WitnessOutput> getWitnessList();

    @GET("/api/voting/v2/witness")
    Observable<WitnessOutput> getWitnessList(@Query("page_size") int i, @Query("page_num") int i2, @Query("has_all") int i3, @Query("sort_type") int i4, @Query("address") String str, @Query("filterOutVoted") int i5);

    @GET("/api/voting/v2/witness")
    Observable<WitnessOutput> getWitnessListV2(@Query("page_size") int i, @Query("page_num") int i2, @Query("has_all") int i3, @Query("sort_type") int i4);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/wallet/getaccountresource")
    @Deprecated
    Observable<Object> getaccountresource(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/nft/search")
    Observable<NftInfoOutput> queryCollection(@Query("address") String str, @Query("tokenAddress") String str2, @Query("word") String str3);

    @Headers({"Accept: application/json", "Signature: true"})
    @GET("swap/router")
    Observable<SwapInfoOutput> querySwapV3Info(@Query("fromToken") String str, @Query("toToken") String str2, @Query("amountIn") String str3, @Query("typeList") String str4, @Query("source") String str5);

    @Headers({"Accept: application/json", "Signature: true"})
    @GET("justswap.json")
    Observable<SwapWhiteListOutput> querySwapV3TokenList();

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/v2/tokenSecurityList")
    Observable<TokenCheckResultBean> queryTokenSecurityList(@Query("address") String str);

    @GET("/api/transfer/account")
    Observable<RiskAccountOutput> requestAccountRisk(@Query("accountAddress") String str);

    @Headers({"Signature: true"})
    @POST("api/wallet/v2/token/add")
    Observable<AddCustomTokenOutput> requestAddCustomToken(@Body RequestBody requestBody);

    @Headers({"Accept: application/json", "Signature: true"})
    @GET("api/wallet/v2/approve/list")
    Observable<ApproveListDatabeanOutput> requestApproveList(@Query("type") String str, @Query("address") String str2);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/activity/website/blacklist")
    Observable<BlackResultListBean> requestBlackList();

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("/api/dapp/v3/banner")
    Observable<DAppBannerOutput> requestDAppBanner(@Query("address") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("/api/dapp/v3/classify")
    Observable<DAppListOutput> requestDAppList(@Query("classify") int i, @Query("address") String str);

    @Headers({"Signature: true"})
    @POST("api/wallet/v2/delAsset")
    Observable<DelMyAssetsOutput> requestDelAssets(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/v2/assetList")
    Observable<AssetsHomeOutput> requestFollowAssets(@Query("address") String str, @Query("version") String str2);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/v2/asset")
    Observable<AssetsQueryOutput> requestGetAsset(@Query("address") String str, @Query("tokenType") int i, @Query("tokenAddress") String str2);

    @Headers({"Signature: true"})
    @POST("api/wallet/v2/addAsset")
    Observable<FollowAssetsOutput> requestModifyFollowAssets(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/v2/allAssetList")
    Observable<AssetsDataOutput> requestMyAssets(@Query("address") String str);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/v2/newAssetList")
    Observable<AssetsDataOutput> requestNewAssets(@Query("address") String str);

    @Headers({"Signature: true"})
    @POST("api/wallet/v2/token/query")
    Observable<QueryCustomTokenOutput> requestQueryCustomToken(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("api/wallet/v2/search")
    Observable<AssetsDataOutput> requestSearchAssets(@Query("address") String str, @Query("keyWord") String str2, @Query("page") int i, @Query("count") int i2, @Query("type") int i3, @Query("version") String str3);

    @Headers({"Signature: true"})
    @POST("api/wallet/v2/token/sync")
    Observable<SyncCustomTokenOutput> requestSyncCustomToken(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/api/wallet/v2/tokenReport")
    Observable<BaseOutput> requestTokenReport(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Signature: true"})
    @GET("/api/wallet/v2/hot_token")
    Observable<RecommendAssetsHomeOutput> requestTopTokens(@Query("address") String str);

    @GET("/api/voting/v2/search")
    @Deprecated
    Observable<SearchWitnessResult> searchWitness(@Query("keyword") String str, @Query("sort_type") int i);

    @GET("/api/voting/v2/search")
    Observable<SearchWitnessResult> searchWitness(@Query("keyword") String str, @Query("sort_type") int i, @Query("address") String str2);

    @GET("api/wallet/push/UpdateDeviceInfo")
    Observable<DeviceInfoBean> sendDeviceInfo(@Query("address") String str, @Query("deviceType") String str2, @Query("deviceInfo") String str3, @Query("code") String str4);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/api/wallet/multi/transaction")
    Observable<TransactionResult> transaction(@Body RequestBody requestBody);

    @GET("/api/v1/wallet/v2/upgrade")
    Observable<UpdateOutput> update();

    @GET("https://list.tronlink.org/api/v1/wallet/v2/upgrade")
    Observable<UpdateOutput> updateOnLine();

    @GET("/dapphouseapp/checkAddress&address={address}&id={id}")
    @Deprecated
    Observable<Object> updateRecommendState(@Path("address") String str, @Path("id") String str2);

    @GET("/api/interchain-event")
    Observable<TransferOutput> withdrawOrdeposit(@Query("tokenAddress") String str, @Query("address") String str2, @Query("contractAddress") String str3, @Query("start") int i, @Query("limit") int i2, @Query("type") String str4);
}
