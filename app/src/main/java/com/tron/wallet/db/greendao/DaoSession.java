package com.tron.wallet.db.greendao;

import com.tron.wallet.business.addassets2.bean.KVBean;
import com.tron.wallet.business.addassets2.bean.TokenSortBean;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomePriceBean;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.bean.nft.NftTypeInfo;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserSearchBean;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabBean;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabHistoryBean;
import com.tron.wallet.business.tabdapp.browser.bean.DAppVisitHistoryBean;
import com.tron.wallet.business.tabdapp.browser.bean.MostVisitDAppBean;
import com.tron.wallet.business.tabdapp.dapphistory.bean.DAppDataBean;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordBean;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.common.bean.ColdFailLogBean;
import com.tron.wallet.common.bean.RecentSendBean;
import com.tron.wallet.common.bean.RowsBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.UnAddedTokenBean;
import com.tron.wallet.db.Controller.DappBlackBean;
import com.tron.wallet.db.bean.AccountTotalBalanceBean;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.bean.AssetIssueContractDao;
import com.tron.wallet.db.bean.DAppNonOfficialAuthorizedProjectBean;
import com.tron.wallet.db.bean.DappAuthorizedProjectBean;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.bean.JustSwapBean;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import com.tron.wallet.db.bean.TranscationBean;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;
public class DaoSession extends AbstractDaoSession {
    private final AccountTotalBalanceBeanDao accountTotalBalanceBeanDao;
    private final DaoConfig accountTotalBalanceBeanDaoConfig;
    private final AddressDaoDao addressDaoDao;
    private final DaoConfig addressDaoDaoConfig;
    private final AssetIssueContractDaoDao assetIssueContractDaoDao;
    private final DaoConfig assetIssueContractDaoDaoConfig;
    private final AssetsHomeDataDao assetsHomeDataDao;
    private final DaoConfig assetsHomeDataDaoConfig;
    private final AssetsHomePriceBeanDao assetsHomePriceBeanDao;
    private final DaoConfig assetsHomePriceBeanDaoConfig;
    private final BackupRecordBeanDao backupRecordBeanDao;
    private final DaoConfig backupRecordBeanDaoConfig;
    private final BleRepoDeviceDao bleRepoDeviceDao;
    private final DaoConfig bleRepoDeviceDaoConfig;
    private final BrowserBookMarkBeanDao browserBookMarkBeanDao;
    private final DaoConfig browserBookMarkBeanDaoConfig;
    private final BrowserHistoryBeanDao browserHistoryBeanDao;
    private final DaoConfig browserHistoryBeanDaoConfig;
    private final BrowserSearchBeanDao browserSearchBeanDao;
    private final DaoConfig browserSearchBeanDaoConfig;
    private final BrowserTabBeanDao browserTabBeanDao;
    private final DaoConfig browserTabBeanDaoConfig;
    private final BrowserTabHistoryBeanDao browserTabHistoryBeanDao;
    private final DaoConfig browserTabHistoryBeanDaoConfig;
    private final ColdFailLogBeanDao coldFailLogBeanDao;
    private final DaoConfig coldFailLogBeanDaoConfig;
    private final DAppDataBeanDao dAppDataBeanDao;
    private final DaoConfig dAppDataBeanDaoConfig;
    private final DAppNonOfficialAuthorizedProjectBeanDao dAppNonOfficialAuthorizedProjectBeanDao;
    private final DaoConfig dAppNonOfficialAuthorizedProjectBeanDaoConfig;
    private final DAppVisitHistoryBeanDao dAppVisitHistoryBeanDao;
    private final DaoConfig dAppVisitHistoryBeanDaoConfig;
    private final DappAuthorizedProjectBeanDao dappAuthorizedProjectBeanDao;
    private final DaoConfig dappAuthorizedProjectBeanDaoConfig;
    private final DappBlackBeanDao dappBlackBeanDao;
    private final DaoConfig dappBlackBeanDaoConfig;
    private final HdTronRelationshipBeanDao hdTronRelationshipBeanDao;
    private final DaoConfig hdTronRelationshipBeanDaoConfig;
    private final JustSwapBeanDao justSwapBeanDao;
    private final DaoConfig justSwapBeanDaoConfig;
    private final KVBeanDao kVBeanDao;
    private final DaoConfig kVBeanDaoConfig;
    private final MostVisitDAppBeanDao mostVisitDAppBeanDao;
    private final DaoConfig mostVisitDAppBeanDaoConfig;
    private final NftItemInfoDao nftItemInfoDao;
    private final DaoConfig nftItemInfoDaoConfig;
    private final NftTokenBeanDao nftTokenBeanDao;
    private final DaoConfig nftTokenBeanDaoConfig;
    private final NftTypeInfoDao nftTypeInfoDao;
    private final DaoConfig nftTypeInfoDaoConfig;
    private final RecentSendBeanDao recentSendBeanDao;
    private final DaoConfig recentSendBeanDaoConfig;
    private final RowsBeanDao rowsBeanDao;
    private final DaoConfig rowsBeanDaoConfig;
    private final TRXAccountBalanceBeanDao tRXAccountBalanceBeanDao;
    private final DaoConfig tRXAccountBalanceBeanDaoConfig;
    private final TokenBeanDao tokenBeanDao;
    private final DaoConfig tokenBeanDaoConfig;
    private final TokenCheckBeanDao tokenCheckBeanDao;
    private final DaoConfig tokenCheckBeanDaoConfig;
    private final TokenSortBeanDao tokenSortBeanDao;
    private final DaoConfig tokenSortBeanDaoConfig;
    private final TransactionMessageDao transactionMessageDao;
    private final DaoConfig transactionMessageDaoConfig;
    private final TranscationBeanDao transcationBeanDao;
    private final DaoConfig transcationBeanDaoConfig;
    private final UnAddedTokenBeanDao unAddedTokenBeanDao;
    private final DaoConfig unAddedTokenBeanDaoConfig;

    public AccountTotalBalanceBeanDao getAccountTotalBalanceBeanDao() {
        return this.accountTotalBalanceBeanDao;
    }

    public AddressDaoDao getAddressDaoDao() {
        return this.addressDaoDao;
    }

    public AssetIssueContractDaoDao getAssetIssueContractDaoDao() {
        return this.assetIssueContractDaoDao;
    }

    public AssetsHomeDataDao getAssetsHomeDataDao() {
        return this.assetsHomeDataDao;
    }

    public AssetsHomePriceBeanDao getAssetsHomePriceBeanDao() {
        return this.assetsHomePriceBeanDao;
    }

    public BackupRecordBeanDao getBackupRecordBeanDao() {
        return this.backupRecordBeanDao;
    }

    public BleRepoDeviceDao getBleRepoDeviceDao() {
        return this.bleRepoDeviceDao;
    }

    public BrowserBookMarkBeanDao getBrowserBookMarkBeanDao() {
        return this.browserBookMarkBeanDao;
    }

    public BrowserHistoryBeanDao getBrowserHistoryBeanDao() {
        return this.browserHistoryBeanDao;
    }

    public BrowserSearchBeanDao getBrowserSearchBeanDao() {
        return this.browserSearchBeanDao;
    }

    public BrowserTabBeanDao getBrowserTabBeanDao() {
        return this.browserTabBeanDao;
    }

    public BrowserTabHistoryBeanDao getBrowserTabHistoryBeanDao() {
        return this.browserTabHistoryBeanDao;
    }

    public ColdFailLogBeanDao getColdFailLogBeanDao() {
        return this.coldFailLogBeanDao;
    }

    public DAppDataBeanDao getDAppDataBeanDao() {
        return this.dAppDataBeanDao;
    }

    public DAppNonOfficialAuthorizedProjectBeanDao getDAppNonOfficialAuthorizedProjectBeanDao() {
        return this.dAppNonOfficialAuthorizedProjectBeanDao;
    }

    public DAppVisitHistoryBeanDao getDAppVisitHistoryBeanDao() {
        return this.dAppVisitHistoryBeanDao;
    }

    public DappAuthorizedProjectBeanDao getDappAuthorizedProjectBeanDao() {
        return this.dappAuthorizedProjectBeanDao;
    }

    public DappBlackBeanDao getDappBlackBeanDao() {
        return this.dappBlackBeanDao;
    }

    public HdTronRelationshipBeanDao getHdTronRelationshipBeanDao() {
        return this.hdTronRelationshipBeanDao;
    }

    public JustSwapBeanDao getJustSwapBeanDao() {
        return this.justSwapBeanDao;
    }

    public KVBeanDao getKVBeanDao() {
        return this.kVBeanDao;
    }

    public MostVisitDAppBeanDao getMostVisitDAppBeanDao() {
        return this.mostVisitDAppBeanDao;
    }

    public NftItemInfoDao getNftItemInfoDao() {
        return this.nftItemInfoDao;
    }

    public NftTokenBeanDao getNftTokenBeanDao() {
        return this.nftTokenBeanDao;
    }

    public NftTypeInfoDao getNftTypeInfoDao() {
        return this.nftTypeInfoDao;
    }

    public RecentSendBeanDao getRecentSendBeanDao() {
        return this.recentSendBeanDao;
    }

    public RowsBeanDao getRowsBeanDao() {
        return this.rowsBeanDao;
    }

    public TRXAccountBalanceBeanDao getTRXAccountBalanceBeanDao() {
        return this.tRXAccountBalanceBeanDao;
    }

    public TokenBeanDao getTokenBeanDao() {
        return this.tokenBeanDao;
    }

    public TokenCheckBeanDao getTokenCheckBeanDao() {
        return this.tokenCheckBeanDao;
    }

    public TokenSortBeanDao getTokenSortBeanDao() {
        return this.tokenSortBeanDao;
    }

    public TransactionMessageDao getTransactionMessageDao() {
        return this.transactionMessageDao;
    }

    public TranscationBeanDao getTranscationBeanDao() {
        return this.transcationBeanDao;
    }

    public UnAddedTokenBeanDao getUnAddedTokenBeanDao() {
        return this.unAddedTokenBeanDao;
    }

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        DaoConfig clone = map.get(KVBeanDao.class).clone();
        this.kVBeanDaoConfig = clone;
        clone.initIdentityScope(identityScopeType);
        DaoConfig clone2 = map.get(TokenSortBeanDao.class).clone();
        this.tokenSortBeanDaoConfig = clone2;
        clone2.initIdentityScope(identityScopeType);
        DaoConfig clone3 = map.get(AssetsHomeDataDao.class).clone();
        this.assetsHomeDataDaoConfig = clone3;
        clone3.initIdentityScope(identityScopeType);
        DaoConfig clone4 = map.get(AssetsHomePriceBeanDao.class).clone();
        this.assetsHomePriceBeanDaoConfig = clone4;
        clone4.initIdentityScope(identityScopeType);
        DaoConfig clone5 = map.get(NftItemInfoDao.class).clone();
        this.nftItemInfoDaoConfig = clone5;
        clone5.initIdentityScope(identityScopeType);
        DaoConfig clone6 = map.get(NftTokenBeanDao.class).clone();
        this.nftTokenBeanDaoConfig = clone6;
        clone6.initIdentityScope(identityScopeType);
        DaoConfig clone7 = map.get(NftTypeInfoDao.class).clone();
        this.nftTypeInfoDaoConfig = clone7;
        clone7.initIdentityScope(identityScopeType);
        DaoConfig clone8 = map.get(TransactionMessageDao.class).clone();
        this.transactionMessageDaoConfig = clone8;
        clone8.initIdentityScope(identityScopeType);
        DaoConfig clone9 = map.get(TokenCheckBeanDao.class).clone();
        this.tokenCheckBeanDaoConfig = clone9;
        clone9.initIdentityScope(identityScopeType);
        DaoConfig clone10 = map.get(BrowserBookMarkBeanDao.class).clone();
        this.browserBookMarkBeanDaoConfig = clone10;
        clone10.initIdentityScope(identityScopeType);
        DaoConfig clone11 = map.get(BrowserHistoryBeanDao.class).clone();
        this.browserHistoryBeanDaoConfig = clone11;
        clone11.initIdentityScope(identityScopeType);
        DaoConfig clone12 = map.get(BrowserSearchBeanDao.class).clone();
        this.browserSearchBeanDaoConfig = clone12;
        clone12.initIdentityScope(identityScopeType);
        DaoConfig clone13 = map.get(BrowserTabBeanDao.class).clone();
        this.browserTabBeanDaoConfig = clone13;
        clone13.initIdentityScope(identityScopeType);
        DaoConfig clone14 = map.get(BrowserTabHistoryBeanDao.class).clone();
        this.browserTabHistoryBeanDaoConfig = clone14;
        clone14.initIdentityScope(identityScopeType);
        DaoConfig clone15 = map.get(DAppVisitHistoryBeanDao.class).clone();
        this.dAppVisitHistoryBeanDaoConfig = clone15;
        clone15.initIdentityScope(identityScopeType);
        DaoConfig clone16 = map.get(MostVisitDAppBeanDao.class).clone();
        this.mostVisitDAppBeanDaoConfig = clone16;
        clone16.initIdentityScope(identityScopeType);
        DaoConfig clone17 = map.get(DAppDataBeanDao.class).clone();
        this.dAppDataBeanDaoConfig = clone17;
        clone17.initIdentityScope(identityScopeType);
        DaoConfig clone18 = map.get(BackupRecordBeanDao.class).clone();
        this.backupRecordBeanDaoConfig = clone18;
        clone18.initIdentityScope(identityScopeType);
        DaoConfig clone19 = map.get(BleRepoDeviceDao.class).clone();
        this.bleRepoDeviceDaoConfig = clone19;
        clone19.initIdentityScope(identityScopeType);
        DaoConfig clone20 = map.get(ColdFailLogBeanDao.class).clone();
        this.coldFailLogBeanDaoConfig = clone20;
        clone20.initIdentityScope(identityScopeType);
        DaoConfig clone21 = map.get(RecentSendBeanDao.class).clone();
        this.recentSendBeanDaoConfig = clone21;
        clone21.initIdentityScope(identityScopeType);
        DaoConfig clone22 = map.get(RowsBeanDao.class).clone();
        this.rowsBeanDaoConfig = clone22;
        clone22.initIdentityScope(identityScopeType);
        DaoConfig clone23 = map.get(TokenBeanDao.class).clone();
        this.tokenBeanDaoConfig = clone23;
        clone23.initIdentityScope(identityScopeType);
        DaoConfig clone24 = map.get(UnAddedTokenBeanDao.class).clone();
        this.unAddedTokenBeanDaoConfig = clone24;
        clone24.initIdentityScope(identityScopeType);
        DaoConfig clone25 = map.get(DappBlackBeanDao.class).clone();
        this.dappBlackBeanDaoConfig = clone25;
        clone25.initIdentityScope(identityScopeType);
        DaoConfig clone26 = map.get(AccountTotalBalanceBeanDao.class).clone();
        this.accountTotalBalanceBeanDaoConfig = clone26;
        clone26.initIdentityScope(identityScopeType);
        DaoConfig clone27 = map.get(AddressDaoDao.class).clone();
        this.addressDaoDaoConfig = clone27;
        clone27.initIdentityScope(identityScopeType);
        DaoConfig clone28 = map.get(AssetIssueContractDaoDao.class).clone();
        this.assetIssueContractDaoDaoConfig = clone28;
        clone28.initIdentityScope(identityScopeType);
        DaoConfig clone29 = map.get(DAppNonOfficialAuthorizedProjectBeanDao.class).clone();
        this.dAppNonOfficialAuthorizedProjectBeanDaoConfig = clone29;
        clone29.initIdentityScope(identityScopeType);
        DaoConfig clone30 = map.get(DappAuthorizedProjectBeanDao.class).clone();
        this.dappAuthorizedProjectBeanDaoConfig = clone30;
        clone30.initIdentityScope(identityScopeType);
        DaoConfig clone31 = map.get(HdTronRelationshipBeanDao.class).clone();
        this.hdTronRelationshipBeanDaoConfig = clone31;
        clone31.initIdentityScope(identityScopeType);
        DaoConfig clone32 = map.get(JustSwapBeanDao.class).clone();
        this.justSwapBeanDaoConfig = clone32;
        clone32.initIdentityScope(identityScopeType);
        DaoConfig clone33 = map.get(TRXAccountBalanceBeanDao.class).clone();
        this.tRXAccountBalanceBeanDaoConfig = clone33;
        clone33.initIdentityScope(identityScopeType);
        DaoConfig clone34 = map.get(TranscationBeanDao.class).clone();
        this.transcationBeanDaoConfig = clone34;
        clone34.initIdentityScope(identityScopeType);
        KVBeanDao kVBeanDao = new KVBeanDao(clone, this);
        this.kVBeanDao = kVBeanDao;
        TokenSortBeanDao tokenSortBeanDao = new TokenSortBeanDao(clone2, this);
        this.tokenSortBeanDao = tokenSortBeanDao;
        AssetsHomeDataDao assetsHomeDataDao = new AssetsHomeDataDao(clone3, this);
        this.assetsHomeDataDao = assetsHomeDataDao;
        AssetsHomePriceBeanDao assetsHomePriceBeanDao = new AssetsHomePriceBeanDao(clone4, this);
        this.assetsHomePriceBeanDao = assetsHomePriceBeanDao;
        NftItemInfoDao nftItemInfoDao = new NftItemInfoDao(clone5, this);
        this.nftItemInfoDao = nftItemInfoDao;
        NftTokenBeanDao nftTokenBeanDao = new NftTokenBeanDao(clone6, this);
        this.nftTokenBeanDao = nftTokenBeanDao;
        NftTypeInfoDao nftTypeInfoDao = new NftTypeInfoDao(clone7, this);
        this.nftTypeInfoDao = nftTypeInfoDao;
        TransactionMessageDao transactionMessageDao = new TransactionMessageDao(clone8, this);
        this.transactionMessageDao = transactionMessageDao;
        TokenCheckBeanDao tokenCheckBeanDao = new TokenCheckBeanDao(clone9, this);
        this.tokenCheckBeanDao = tokenCheckBeanDao;
        BrowserBookMarkBeanDao browserBookMarkBeanDao = new BrowserBookMarkBeanDao(clone10, this);
        this.browserBookMarkBeanDao = browserBookMarkBeanDao;
        BrowserHistoryBeanDao browserHistoryBeanDao = new BrowserHistoryBeanDao(clone11, this);
        this.browserHistoryBeanDao = browserHistoryBeanDao;
        BrowserSearchBeanDao browserSearchBeanDao = new BrowserSearchBeanDao(clone12, this);
        this.browserSearchBeanDao = browserSearchBeanDao;
        BrowserTabBeanDao browserTabBeanDao = new BrowserTabBeanDao(clone13, this);
        this.browserTabBeanDao = browserTabBeanDao;
        BrowserTabHistoryBeanDao browserTabHistoryBeanDao = new BrowserTabHistoryBeanDao(clone14, this);
        this.browserTabHistoryBeanDao = browserTabHistoryBeanDao;
        DAppVisitHistoryBeanDao dAppVisitHistoryBeanDao = new DAppVisitHistoryBeanDao(clone15, this);
        this.dAppVisitHistoryBeanDao = dAppVisitHistoryBeanDao;
        MostVisitDAppBeanDao mostVisitDAppBeanDao = new MostVisitDAppBeanDao(clone16, this);
        this.mostVisitDAppBeanDao = mostVisitDAppBeanDao;
        DAppDataBeanDao dAppDataBeanDao = new DAppDataBeanDao(clone17, this);
        this.dAppDataBeanDao = dAppDataBeanDao;
        BackupRecordBeanDao backupRecordBeanDao = new BackupRecordBeanDao(clone18, this);
        this.backupRecordBeanDao = backupRecordBeanDao;
        BleRepoDeviceDao bleRepoDeviceDao = new BleRepoDeviceDao(clone19, this);
        this.bleRepoDeviceDao = bleRepoDeviceDao;
        ColdFailLogBeanDao coldFailLogBeanDao = new ColdFailLogBeanDao(clone20, this);
        this.coldFailLogBeanDao = coldFailLogBeanDao;
        RecentSendBeanDao recentSendBeanDao = new RecentSendBeanDao(clone21, this);
        this.recentSendBeanDao = recentSendBeanDao;
        RowsBeanDao rowsBeanDao = new RowsBeanDao(clone22, this);
        this.rowsBeanDao = rowsBeanDao;
        TokenBeanDao tokenBeanDao = new TokenBeanDao(clone23, this);
        this.tokenBeanDao = tokenBeanDao;
        UnAddedTokenBeanDao unAddedTokenBeanDao = new UnAddedTokenBeanDao(clone24, this);
        this.unAddedTokenBeanDao = unAddedTokenBeanDao;
        DappBlackBeanDao dappBlackBeanDao = new DappBlackBeanDao(clone25, this);
        this.dappBlackBeanDao = dappBlackBeanDao;
        AccountTotalBalanceBeanDao accountTotalBalanceBeanDao = new AccountTotalBalanceBeanDao(clone26, this);
        this.accountTotalBalanceBeanDao = accountTotalBalanceBeanDao;
        AddressDaoDao addressDaoDao = new AddressDaoDao(clone27, this);
        this.addressDaoDao = addressDaoDao;
        AssetIssueContractDaoDao assetIssueContractDaoDao = new AssetIssueContractDaoDao(clone28, this);
        this.assetIssueContractDaoDao = assetIssueContractDaoDao;
        DAppNonOfficialAuthorizedProjectBeanDao dAppNonOfficialAuthorizedProjectBeanDao = new DAppNonOfficialAuthorizedProjectBeanDao(clone29, this);
        this.dAppNonOfficialAuthorizedProjectBeanDao = dAppNonOfficialAuthorizedProjectBeanDao;
        DappAuthorizedProjectBeanDao dappAuthorizedProjectBeanDao = new DappAuthorizedProjectBeanDao(clone30, this);
        this.dappAuthorizedProjectBeanDao = dappAuthorizedProjectBeanDao;
        HdTronRelationshipBeanDao hdTronRelationshipBeanDao = new HdTronRelationshipBeanDao(clone31, this);
        this.hdTronRelationshipBeanDao = hdTronRelationshipBeanDao;
        JustSwapBeanDao justSwapBeanDao = new JustSwapBeanDao(clone32, this);
        this.justSwapBeanDao = justSwapBeanDao;
        TRXAccountBalanceBeanDao tRXAccountBalanceBeanDao = new TRXAccountBalanceBeanDao(clone33, this);
        this.tRXAccountBalanceBeanDao = tRXAccountBalanceBeanDao;
        TranscationBeanDao transcationBeanDao = new TranscationBeanDao(clone34, this);
        this.transcationBeanDao = transcationBeanDao;
        registerDao(KVBean.class, kVBeanDao);
        registerDao(TokenSortBean.class, tokenSortBeanDao);
        registerDao(AssetsHomeData.class, assetsHomeDataDao);
        registerDao(AssetsHomePriceBean.class, assetsHomePriceBeanDao);
        registerDao(NftItemInfo.class, nftItemInfoDao);
        registerDao(NftTokenBean.class, nftTokenBeanDao);
        registerDao(NftTypeInfo.class, nftTypeInfoDao);
        registerDao(TransactionMessage.class, transactionMessageDao);
        registerDao(TokenCheckBean.class, tokenCheckBeanDao);
        registerDao(BrowserBookMarkBean.class, browserBookMarkBeanDao);
        registerDao(BrowserHistoryBean.class, browserHistoryBeanDao);
        registerDao(BrowserSearchBean.class, browserSearchBeanDao);
        registerDao(BrowserTabBean.class, browserTabBeanDao);
        registerDao(BrowserTabHistoryBean.class, browserTabHistoryBeanDao);
        registerDao(DAppVisitHistoryBean.class, dAppVisitHistoryBeanDao);
        registerDao(MostVisitDAppBean.class, mostVisitDAppBeanDao);
        registerDao(DAppDataBean.class, dAppDataBeanDao);
        registerDao(BackupRecordBean.class, backupRecordBeanDao);
        registerDao(BleRepoDevice.class, bleRepoDeviceDao);
        registerDao(ColdFailLogBean.class, coldFailLogBeanDao);
        registerDao(RecentSendBean.class, recentSendBeanDao);
        registerDao(RowsBean.class, rowsBeanDao);
        registerDao(TokenBean.class, tokenBeanDao);
        registerDao(UnAddedTokenBean.class, unAddedTokenBeanDao);
        registerDao(DappBlackBean.class, dappBlackBeanDao);
        registerDao(AccountTotalBalanceBean.class, accountTotalBalanceBeanDao);
        registerDao(AddressDao.class, addressDaoDao);
        registerDao(AssetIssueContractDao.class, assetIssueContractDaoDao);
        registerDao(DAppNonOfficialAuthorizedProjectBean.class, dAppNonOfficialAuthorizedProjectBeanDao);
        registerDao(DappAuthorizedProjectBean.class, dappAuthorizedProjectBeanDao);
        registerDao(HdTronRelationshipBean.class, hdTronRelationshipBeanDao);
        registerDao(JustSwapBean.class, justSwapBeanDao);
        registerDao(TRXAccountBalanceBean.class, tRXAccountBalanceBeanDao);
        registerDao(TranscationBean.class, transcationBeanDao);
    }

    public void clear() {
        this.kVBeanDaoConfig.clearIdentityScope();
        this.tokenSortBeanDaoConfig.clearIdentityScope();
        this.assetsHomeDataDaoConfig.clearIdentityScope();
        this.assetsHomePriceBeanDaoConfig.clearIdentityScope();
        this.nftItemInfoDaoConfig.clearIdentityScope();
        this.nftTokenBeanDaoConfig.clearIdentityScope();
        this.nftTypeInfoDaoConfig.clearIdentityScope();
        this.transactionMessageDaoConfig.clearIdentityScope();
        this.tokenCheckBeanDaoConfig.clearIdentityScope();
        this.browserBookMarkBeanDaoConfig.clearIdentityScope();
        this.browserHistoryBeanDaoConfig.clearIdentityScope();
        this.browserSearchBeanDaoConfig.clearIdentityScope();
        this.browserTabBeanDaoConfig.clearIdentityScope();
        this.browserTabHistoryBeanDaoConfig.clearIdentityScope();
        this.dAppVisitHistoryBeanDaoConfig.clearIdentityScope();
        this.mostVisitDAppBeanDaoConfig.clearIdentityScope();
        this.dAppDataBeanDaoConfig.clearIdentityScope();
        this.backupRecordBeanDaoConfig.clearIdentityScope();
        this.bleRepoDeviceDaoConfig.clearIdentityScope();
        this.coldFailLogBeanDaoConfig.clearIdentityScope();
        this.recentSendBeanDaoConfig.clearIdentityScope();
        this.rowsBeanDaoConfig.clearIdentityScope();
        this.tokenBeanDaoConfig.clearIdentityScope();
        this.unAddedTokenBeanDaoConfig.clearIdentityScope();
        this.dappBlackBeanDaoConfig.clearIdentityScope();
        this.accountTotalBalanceBeanDaoConfig.clearIdentityScope();
        this.addressDaoDaoConfig.clearIdentityScope();
        this.assetIssueContractDaoDaoConfig.clearIdentityScope();
        this.dAppNonOfficialAuthorizedProjectBeanDaoConfig.clearIdentityScope();
        this.dappAuthorizedProjectBeanDaoConfig.clearIdentityScope();
        this.hdTronRelationshipBeanDaoConfig.clearIdentityScope();
        this.justSwapBeanDaoConfig.clearIdentityScope();
        this.tRXAccountBalanceBeanDaoConfig.clearIdentityScope();
        this.transcationBeanDaoConfig.clearIdentityScope();
    }
}
