package com.tron.wallet.business.tabmy.myhome.settings;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.tabmy.myhome.settings.NodeSpeedContract;
import com.tron.wallet.business.tabmy.myhome.settings.bean.MainNodeOutput;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeBean;
import com.tron.wallet.business.tabmy.node.AddCustomNodeActivity;
import com.tron.wallet.common.adapter.NodeAdapter;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.CloneUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ActivityNodeSpeedTestBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.rpc.IGrpcClient;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
public class NodeSpeedTestActivity extends BaseActivity<NodeSpeedPresenter, NodeSpeedModel> implements NodeSpeedContract.View {
    private static final String CHAINBEAN = "CHAINBEAN";
    public static final String CUSTOM_NODE_ADDRESS = "custom_node_address";
    public static final String CUSTOM_NODE_IP = "custom_node_ip";
    public static final String CUSTOM_NODE_PORT = "custom_node_port";
    public static final String CUSTOM_NODE_TYPE = "custom_node_type";
    public static final String DELETE_COUSTOM_NODE = "delete_custom_node";
    public static final String FULL_NODE_COUNT = "full_node_count";
    private static final int REQUEST_ADD_CUSTOM_NODE = 16;
    public static final String SOLIDITY_NODE_COUNT = "solidity_node_count";
    private static final String TAG = "NodeSpeedTestActivity";
    private ActivityNodeSpeedTestBinding binding;
    Button btnAddNode;
    private ChainBean chainBean;
    private String curEnvironmentStr;
    private Map<String, MainNodeOutput.DataBean> customNodeListsMap;
    private boolean isCurShasta;
    private boolean isCurrentMainChain;
    private boolean isMainChain;
    private LinearLayoutManager layoutManager;
    private NodeAdapter mNodeAdapter;
    private NodeAdapter.OnNodeSelectedListener mOnNodeSelectedListener;
    RecyclerView rcList;
    TextView tvChindId;
    TextView tvFullNode;
    TextView tvSolidityNode;
    List<String> mainFullNode = new ArrayList();
    List<NodeBean> mainFullNodeList = new ArrayList();
    List<TextView> speedResults = new ArrayList();
    private boolean isFullNodeSetting = true;

    @Override
    protected void setLayout() {
        ActivityNodeSpeedTestBinding inflate = ActivityNodeSpeedTestBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.node_setting));
    }

    public void mappingId() {
        this.tvFullNode = this.binding.tvFullNode;
        this.tvSolidityNode = this.binding.tvSolidityNode;
        this.rcList = this.binding.rcList;
        this.btnAddNode = this.binding.btnAddNode;
        this.tvChindId = this.binding.tvChainId;
    }

    public static void start(Context context, ChainBean chainBean) {
        Intent intent = new Intent(context, NodeSpeedTestActivity.class);
        intent.putExtra(CHAINBEAN, chainBean);
        context.startActivity(intent);
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void processData() {
        HashMap hashMap;
        if (getIntent() != null) {
            ChainBean chainBean = (ChainBean) getIntent().getParcelableExtra(CHAINBEAN);
            this.chainBean = chainBean;
            if (chainBean != null) {
                if (chainBean.isMainChain) {
                    this.isMainChain = true;
                } else {
                    this.isMainChain = false;
                    if (!StringTronUtil.isEmpty(this.chainBean.chainId)) {
                        this.tvChindId.setVisibility(View.VISIBLE);
                        this.tvChindId.setText(String.format(getString(R.string.chain_id), this.chainBean.chainId));
                    }
                }
            }
        }
        initChainState();
        ChainBean currentChain = SpAPI.THIS.getCurrentChain();
        ChainBean chainBean2 = this.chainBean;
        if (chainBean2 != null && chainBean2.chainId != null && this.chainBean.chainId.equals("Shasta")) {
            hashMap = new HashMap();
            MainNodeOutput.DataBean dataBean = new MainNodeOutput.DataBean();
            dataBean.chainId = this.chainBean.chainId;
            dataBean.chainName = this.chainBean.chainName;
            dataBean.eventServer = this.chainBean.eventServer;
            dataBean.isMainChain = this.chainBean.isMainChain ? 1 : 0;
            dataBean.fullNode = new ArrayList();
            dataBean.fullNode.add(this.chainBean.fullNode);
            dataBean.mainChainContract = this.chainBean.mainChainContract;
            dataBean.sideChainContract = this.chainBean.sideChainContract;
            hashMap.put("MainChain", dataBean);
        } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            hashMap = new HashMap();
        } else {
            hashMap = (HashMap) SpAPI.THIS.getMainNodeList();
        }
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            int lastTestVersion = SpAPI.THIS.getLastTestVersion();
            ChainBean chainBean3 = this.chainBean;
            if (chainBean3 != null && chainBean3.chainId != null && this.chainBean.chainId.equals("Shasta")) {
                this.customNodeListsMap = SpAPI.THIS.getCustomNodeList(IRequest.NET_ENVIRONMENT.SHASTA.toString());
                this.curEnvironmentStr = IRequest.NET_ENVIRONMENT.SHASTA.toString();
            } else {
                this.curEnvironmentStr = getEnvironmentStr(lastTestVersion);
                this.customNodeListsMap = SpAPI.THIS.getCustomNodeList(this.curEnvironmentStr);
            }
        } else {
            ChainBean chainBean4 = this.chainBean;
            if (chainBean4 != null && chainBean4.chainId != null && this.chainBean.chainId.equals("Shasta")) {
                this.customNodeListsMap = SpAPI.THIS.getCustomNodeList(IRequest.NET_ENVIRONMENT.SHASTA.toString());
                this.curEnvironmentStr = IRequest.NET_ENVIRONMENT.SHASTA.toString();
            } else {
                this.curEnvironmentStr = IRequest.ENVIRONMENT.toString();
                this.customNodeListsMap = SpAPI.THIS.getCustomNodeList(IRequest.ENVIRONMENT.toString());
            }
        }
        if (this.customNodeListsMap.isEmpty()) {
            Map<String, MainNodeOutput.DataBean> map = (Map) CloneUtils.clone(hashMap);
            this.customNodeListsMap = map;
            for (Map.Entry<String, MainNodeOutput.DataBean> entry : map.entrySet()) {
                entry.getKey();
                MainNodeOutput.DataBean value = entry.getValue();
                if (value.fullNode != null) {
                    value.fullNode.clear();
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        if (this.isMainChain) {
            MainNodeOutput.DataBean dataBean2 = (MainNodeOutput.DataBean) hashMap.get("MainChain");
            if (dataBean2 == null) {
                List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
                if (IRequest.ENVIRONMENT != IRequest.NET_ENVIRONMENT.SHASTA && allChainJson != null && allChainJson.size() > 0) {
                    for (ChainBean chainBean5 : allChainJson) {
                        if (chainBean5 != null && chainBean5.isMainChain) {
                            this.mainFullNode.add(chainBean5.fullNode);
                        }
                    }
                }
            } else {
                this.mainFullNode.addAll(dataBean2.fullNode);
            }
            MainNodeOutput.DataBean dataBean3 = this.customNodeListsMap.get("MainChain");
            if (dataBean3 != null) {
                arrayList.addAll(dataBean3.fullNode);
            }
        } else {
            MainNodeOutput.DataBean dataBean4 = (MainNodeOutput.DataBean) hashMap.get(this.chainBean.chainName);
            if (dataBean4 != null) {
                this.mainFullNode.addAll(dataBean4.fullNode);
                MainNodeOutput.DataBean dataBean5 = this.customNodeListsMap.get(TronConfig.DApp_CHAIN_NAME);
                if (dataBean5 != null) {
                    arrayList.addAll(dataBean5.fullNode);
                } else {
                    MainNodeOutput.DataBean dataBean6 = this.customNodeListsMap.get(this.chainBean.chainName);
                    if (dataBean6 != null) {
                        arrayList.addAll(dataBean6.fullNode);
                    }
                }
            } else {
                MainNodeOutput.DataBean dataBean7 = this.customNodeListsMap.get(TronConfig.DApp_CHAIN_NAME);
                if (dataBean7 != null) {
                    arrayList.addAll(dataBean7.fullNode);
                }
            }
        }
        int testVersion = SpAPI.THIS.getTestVersion();
        String[] strArr = new String[0];
        ChainBean chainBean6 = this.chainBean;
        if (chainBean6 != null && chainBean6.chainId != null && this.chainBean.chainId.equals("Shasta")) {
            testVersion = 5;
        }
        ChainBean chainBean7 = this.chainBean;
        if (chainBean7 != null && chainBean7.chainId != null && !this.chainBean.chainId.equals("Shasta") && IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            testVersion = SpAPI.THIS.getLastTestVersion();
        }
        if (this.isMainChain) {
            if (testVersion == 1) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.main_node_list);
            } else if (testVersion == 2) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.prorelease_node_list);
            } else if (testVersion == 3) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.test_node_list);
            } else if (testVersion == 4) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.nile_node_list);
            } else if (testVersion == 5) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.shasta_node_list);
            }
        } else if (testVersion == 1) {
            strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.main_dapp_node_list);
        } else if (testVersion == 2) {
            strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.prorelease_dapp_node_list);
        } else if (testVersion == 3) {
            strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.test_dapp_node_list);
        } else if (testVersion == 4) {
            strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.nile_dapp_node_list);
        } else if (testVersion == 5) {
            strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.shasta_dapp_node_list);
        }
        if (this.mainFullNode.size() < 5) {
            for (int i = 0; i < strArr.length; i++) {
                if (!this.mainFullNode.contains(strArr[i])) {
                    this.mainFullNode.add(strArr[i]);
                }
            }
        }
        for (int i2 = 0; i2 < this.mainFullNode.size(); i2++) {
            NodeBean nodeBean = new NodeBean(this.mainFullNode.get(i2));
            if (this.mainFullNode.get(i2).equals(currentChain.fullNode)) {
                nodeBean.setSelected(true);
            } else if (IGrpcClient.THIS.getCurrentFullNode().equals(nodeBean.getAddress())) {
                nodeBean.setSelected(true);
            }
            nodeBean.setAddress(this.mainFullNode.get(i2));
            nodeBean.setType(0);
            nodeBean.setStatus(2);
            if (!existNode(nodeBean.getAddress(), nodeBean.getType(), false)) {
                this.mainFullNodeList.add(nodeBean);
            }
        }
        boolean z = false;
        for (int i3 = 0; i3 < this.mainFullNodeList.size(); i3++) {
            if (!this.mainFullNodeList.get(i3).isSelected() || z) {
                this.mainFullNodeList.get(i3).setSelected(false);
            } else {
                z = true;
            }
        }
        if (!checkHaveSelectedNode(true, 0)) {
            for (int i4 = 0; i4 < this.mainFullNodeList.size(); i4++) {
                if (this.mainFullNodeList.get(i4).getAddress().equals(IGrpcClient.THIS.getCurrentFullNode())) {
                    this.mainFullNodeList.get(i4).setSelected(true);
                }
            }
        }
        if (this.isMainChain) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            String customeFull = SpAPI.THIS.getCustomeFull();
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                NodeBean nodeBean2 = new NodeBean((String) arrayList.get(i5));
                if (((String) arrayList.get(i5)).equals(currentChain.fullNode)) {
                    nodeBean2.setSelected(true);
                }
                if (isCustomFull && ((String) arrayList.get(i5)).equals(customeFull) && currentChain != null && currentChain.fullNode.equals(customeFull)) {
                    resetNode(true, false);
                    nodeBean2.setSelected(true);
                }
                nodeBean2.setAddress((String) arrayList.get(i5));
                nodeBean2.setCustomed(true);
                nodeBean2.setType(0);
                nodeBean2.setStatus(2);
                if (!existNode(nodeBean2.getAddress(), nodeBean2.getType(), false)) {
                    this.mainFullNodeList.add(nodeBean2);
                }
            }
        } else {
            boolean isDappCustomFull = SpAPI.THIS.isDappCustomFull(this.curEnvironmentStr);
            String dappCustomeFull = SpAPI.THIS.getDappCustomeFull(this.curEnvironmentStr);
            SpAPI.THIS.getDappChainCustomeSolidity();
            for (int i6 = 0; i6 < arrayList.size(); i6++) {
                NodeBean nodeBean3 = new NodeBean((String) arrayList.get(i6));
                if (((String) arrayList.get(i6)).equals(currentChain.fullNode)) {
                    nodeBean3.setSelected(true);
                }
                if (isDappCustomFull && ((String) arrayList.get(i6)).equals(dappCustomeFull) && currentChain != null && currentChain.fullNode.equals(dappCustomeFull)) {
                    resetNode(true, false);
                    nodeBean3.setSelected(true);
                }
                nodeBean3.setAddress((String) arrayList.get(i6));
                nodeBean3.setCustomed(true);
                nodeBean3.setType(0);
                nodeBean3.setStatus(2);
                if (!existNode(nodeBean3.getAddress(), nodeBean3.getType(), false)) {
                    this.mainFullNodeList.add(nodeBean3);
                } else {
                    for (int i7 = 0; i7 < this.mainFullNodeList.size(); i7++) {
                        if (this.mainFullNodeList.get(i7).getAddress().equals(nodeBean3.getAddress())) {
                            this.mainFullNodeList.get(i7).setCustomed(true);
                        }
                    }
                }
            }
        }
        this.tvSolidityNode.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
        this.layoutManager = linearLayoutManager;
        this.rcList.setLayoutManager(linearLayoutManager);
        this.rcList.setHasFixedSize(true);
        this.rcList.setNestedScrollingEnabled(false);
        this.mOnNodeSelectedListener = new NodeAdapter.OnNodeSelectedListener() {
            @Override
            public void onNodeSelected(NodeBean nodeBean4) {
                LogUtils.d(NodeSpeedTestActivity.TAG, "onNodeSelected  " + nodeBean4.toString());
                if (nodeBean4.getStatus() == 2 || nodeBean4.getStatus() == 3) {
                    return;
                }
                controlNodeSelected(nodeBean4);
            }

            @Override
            public void onNodeEdit(NodeBean nodeBean4) {
                if (nodeBean4.isSelected()) {
                    IToast.getIToast().showAsBottomn(R.string.node_in_use);
                    return;
                }
                Intent intent = new Intent(NodeSpeedTestActivity.this, AddCustomNodeActivity.class);
                intent.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_IP, nodeBean4.getIp());
                intent.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_PORT, nodeBean4.getPort());
                intent.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_TYPE, nodeBean4.getType());
                intent.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_ADDRESS, nodeBean4.getAddress());
                goForResult(intent, 16);
            }
        };
        ChainBean chainBean8 = this.chainBean;
        if (chainBean8 != null && chainBean8.chainId != null && this.chainBean.chainId.equals("Shasta")) {
            this.mainFullNodeList.clear();
            NodeBean nodeBean4 = new NodeBean(getResources().getStringArray(R.array.shasta_node_list)[0]);
            if (nodeBean4.getAddress().equals(currentChain.fullNode)) {
                nodeBean4.setSelected(true);
            }
            this.mainFullNodeList.add(nodeBean4);
            setRightTextShow(false);
            this.btnAddNode.setVisibility(View.GONE);
        }
        NodeAdapter nodeAdapter = new NodeAdapter(this.mContext, this.mainFullNodeList, this.mOnNodeSelectedListener);
        this.mNodeAdapter = nodeAdapter;
        this.rcList.setAdapter(nodeAdapter);
        startAsyncNodeSpeedTest(true);
        RxManager rxManager = new RxManager();
        rxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0(obj);
            }
        });
        rxManager.on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$1(obj);
            }
        });
    }

    public void lambda$processData$0(Object obj) throws Exception {
        startAsyncNodeSpeedTest(true);
    }

    public void lambda$processData$1(Object obj) throws Exception {
        initChainState();
    }

    public void controlNodeSelected(NodeBean nodeBean) {
        ChainBean chainBean;
        if (IRequest.ENVIRONMENT != IRequest.NET_ENVIRONMENT.SHASTA || (chainBean = this.chainBean) == null || chainBean.chainId == null || !this.chainBean.chainId.equals("Shasta")) {
            ChainBean chainBean2 = this.chainBean;
            if (chainBean2 != null && chainBean2.chainId != null && this.chainBean.chainId.equals("Shasta")) {
                ((NodeSpeedPresenter) this.mPresenter).initSwitchDialog(1, nodeBean);
                return;
            }
            boolean z = this.isCurrentMainChain;
            if (z && this.isMainChain) {
                if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                    ((NodeSpeedPresenter) this.mPresenter).initSwitchDialog(0, nodeBean);
                } else {
                    startTestCustomNode(nodeBean, true);
                }
            } else if (z || this.isMainChain) {
                if (this.isMainChain) {
                    ((NodeSpeedPresenter) this.mPresenter).initSwitchDialog(0, nodeBean);
                } else {
                    ((NodeSpeedPresenter) this.mPresenter).initSwitchDialog(2, nodeBean);
                }
            } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                ((NodeSpeedPresenter) this.mPresenter).initSwitchDialog(2, nodeBean);
            } else {
                startTestCustomNode(nodeBean, true);
            }
        }
    }

    private void initChainState() {
        ChainBean currentChain = SpAPI.THIS.getCurrentChain();
        ChainBean chainBean = this.chainBean;
        if (chainBean == null) {
            if (chainBean == null) {
                this.chainBean = currentChain;
            }
            this.isMainChain = true;
            this.isCurrentMainChain = true;
        } else if (chainBean.isMainChain) {
            if (currentChain == null || currentChain.isMainChain) {
                this.isCurrentMainChain = true;
            } else {
                this.isCurrentMainChain = false;
            }
            this.isMainChain = true;
        } else if (currentChain == null || currentChain.isMainChain) {
            this.isCurrentMainChain = true;
        } else {
            this.isCurrentMainChain = false;
        }
    }

    private String getEnvironmentStr(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            return IRequest.NET_ENVIRONMENT.SHASTA.toString();
                        }
                        return IRequest.NET_ENVIRONMENT.RELEASE.toString();
                    }
                    return IRequest.NET_ENVIRONMENT.NILETEST.toString();
                }
                return IRequest.NET_ENVIRONMENT.TEST.toString();
            }
            return IRequest.NET_ENVIRONMENT.PRE_RELEASE.toString();
        }
        return IRequest.NET_ENVIRONMENT.RELEASE.toString();
    }

    private boolean checkHaveSelectedNode(boolean z, int i) {
        if (i == 0) {
            for (int i2 = 0; i2 < this.mainFullNodeList.size(); i2++) {
                if (this.mainFullNodeList.get(i2).isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNode.CLICK_CHANGE_NODE_CLICK_BACK);
        finish();
    }

    public void addCustomNode() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabmy.myhome.settings.NodeSpeedTestActivity.addCustomNode():void");
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        String str;
        boolean z;
        super.onActivityResult(i, i2, intent);
        if (i == 16 && i2 == -1 && intent != null) {
            int intExtra = intent.getIntExtra(CUSTOM_NODE_TYPE, -1);
            boolean booleanExtra = intent.getBooleanExtra(DELETE_COUSTOM_NODE, false);
            if (intent.hasExtra(CUSTOM_NODE_ADDRESS)) {
                str = intent.getStringExtra(CUSTOM_NODE_ADDRESS);
                z = true;
            } else {
                str = null;
                z = false;
            }
            if (booleanExtra) {
                if (TextUtils.isEmpty(str)) {
                    LogUtils.e(TAG, "address  is null");
                }
                for (int i3 = 0; i3 < this.mainFullNodeList.size(); i3++) {
                    if (this.mainFullNodeList.get(i3).getAddress().equals(str)) {
                        this.mainFullNodeList.remove(i3);
                        updateCustomNodeInfo();
                    }
                }
                IToast.getIToast().showAsBottomn(R.string.deleted_successfully);
                updateCustomNodeInfo();
                notifyNodeLatencyChange(0);
                return;
            }
            String stringExtra = intent.getStringExtra(CUSTOM_NODE_IP);
            int intExtra2 = intent.getIntExtra(CUSTOM_NODE_PORT, -1);
            if (z) {
                if (existNode(stringExtra + ":" + intExtra2, intExtra, true)) {
                    IToast.getIToast().showAsBottomn(R.string.modify_success);
                    return;
                }
                for (int i4 = 0; i4 < this.mainFullNodeList.size(); i4++) {
                    if (this.mainFullNodeList.get(i4).getAddress().equals(str)) {
                        NodeBean nodeBean = this.mainFullNodeList.get(i4);
                        if (nodeBean.getType() == intExtra) {
                            nodeBean.setIp(stringExtra);
                            nodeBean.setPort(intExtra2);
                            nodeBean.setAddress(stringExtra + ":" + intExtra2);
                            startTestNodeOnBackGround(nodeBean.getAddress(), true);
                        } else if (!checkCanAddMore(1)) {
                            IToast.getIToast().showAsBottomn(R.string.cannot_add_more);
                            return;
                        } else {
                            this.mainFullNodeList.remove(nodeBean);
                            NodeBean nodeBean2 = new NodeBean();
                            nodeBean2.setIp(stringExtra);
                            nodeBean2.setPort(intExtra2);
                            nodeBean2.setAddress(stringExtra + ":" + intExtra2);
                            nodeBean2.setCustomed(true);
                            nodeBean2.setType(1);
                            this.tvSolidityNode.performClick();
                            startTestNodeOnBackGround(nodeBean2.getAddress(), false);
                        }
                        notifyNodeLatencyChange(i4);
                        updateCustomNodeInfo();
                        IToast.getIToast().showAsBottomn(R.string.modify_success);
                    }
                }
                return;
            }
            if (intExtra == 0) {
                this.tvFullNode.performClick();
            } else {
                this.tvSolidityNode.performClick();
            }
            if (!checkCanAddMore(intExtra)) {
                IToast.getIToast().showAsBottomn(R.string.cannot_add_more);
                return;
            }
            NodeBean nodeBean3 = new NodeBean();
            nodeBean3.setIp(stringExtra);
            nodeBean3.setPort(intExtra2);
            if (existNode(stringExtra + ":" + intExtra2, intExtra, true)) {
                IToast.getIToast().showAsBottomn(R.string.node_exist);
                return;
            }
            nodeBean3.setAddress(stringExtra + ":" + intExtra2);
            nodeBean3.setCustomed(true);
            if (intExtra == 0) {
                nodeBean3.setType(0);
                this.mainFullNodeList.add(nodeBean3);
            }
            notifyNodeLatencyChange(-1);
            IToast.getIToast().showAsBottomn(R.string.add_success);
            updateCustomNodeInfo();
            startTestCustomNode(nodeBean3, false);
            controlNodeSelected(nodeBean3);
        }
    }

    private void startTestNodeOnBackGround(final String str, final boolean z) {
        runOnThreeThread(new OnBackground() {
            @Override
            public void doOnBackground() {
                startTestNode(str, z);
            }
        });
    }

    private boolean checkCanAddMore(int i) {
        if (this.customNodeListsMap != null) {
            ChainBean chainBean = this.chainBean;
            MainNodeOutput.DataBean dataBean = this.customNodeListsMap.get(chainBean != null ? chainBean.chainName : "MainChain");
            if (dataBean == null || dataBean.fullNode == null) {
                return true;
            }
            return i == 0 ? dataBean.fullNode.size() < 5 : dataBean.solidityNode == null || dataBean.solidityNode.size() < 5;
        }
        return true;
    }

    private boolean existNode(String str, int i, boolean z) {
        Map<String, MainNodeOutput.DataBean> map;
        if (i == 0) {
            for (int i2 = 0; i2 < this.mainFullNodeList.size(); i2++) {
                if (this.mainFullNodeList.get(i2).getAddress().equals(str)) {
                    return true;
                }
            }
        }
        if (z && (map = this.customNodeListsMap) != null && map.size() > 0) {
            for (String str2 : this.customNodeListsMap.keySet()) {
                MainNodeOutput.DataBean dataBean = this.customNodeListsMap.get(str2);
                if (dataBean != null && dataBean.fullNode != null) {
                    for (int i3 = 0; i3 < dataBean.fullNode.size(); i3++) {
                        if (str.equals(dataBean.fullNode.get(i3))) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }

    private void updateCustomNodeInfo() {
        ArrayList arrayList = new ArrayList();
        ChainBean chainBean = this.chainBean;
        String str = chainBean != null ? chainBean.chainName : "MainChain";
        MainNodeOutput.DataBean dataBean = this.customNodeListsMap.get(str);
        if (dataBean == null) {
            dataBean = new MainNodeOutput.DataBean();
            dataBean.chainId = "";
            dataBean.chainName = this.chainBean.chainName;
            dataBean.solidityNode = new ArrayList();
            dataBean.fullNode = new ArrayList();
            this.customNodeListsMap.put(str, dataBean);
        }
        dataBean.fullNode.clear();
        if (dataBean.solidityNode != null) {
            dataBean.solidityNode.clear();
        } else {
            dataBean.solidityNode = new ArrayList();
        }
        for (int i = 0; i < this.mainFullNodeList.size(); i++) {
            if (this.mainFullNodeList.get(i).isCustomed()) {
                dataBean.fullNode.add(this.mainFullNodeList.get(i).getAddress());
            }
        }
        arrayList.add(dataBean);
        for (Map.Entry<String, MainNodeOutput.DataBean> entry : this.customNodeListsMap.entrySet()) {
            if (!entry.getKey().equals(str)) {
                arrayList.add(entry.getValue());
            }
        }
        ChainBean chainBean2 = this.chainBean;
        if (chainBean2 != null && chainBean2.chainId != null && this.chainBean.chainId.equals("Shasta")) {
            SpAPI.THIS.setCustomNodeList(IRequest.NET_ENVIRONMENT.SHASTA.toString(), arrayList);
        } else {
            SpAPI.THIS.setCustomNodeList(this.curEnvironmentStr, arrayList);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_add_node) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNode.CLICK_CHANGE_NODE_ADD_CUSTOM_NODE);
                    addCustomNode();
                } else if (id != R.id.tv_full_node) {
                    if (id != R.id.tv_solidity_node) {
                        return;
                    }
                    tvFullNode.setTextColor(getResources().getColor(R.color.black_02_30));
                    tvFullNode.setBackgroundResource(R.drawable.roundborder_transparent);
                    tvSolidityNode.setTextColor(getResources().getColor(R.color.white));
                    tvSolidityNode.setBackgroundResource(R.drawable.roundborder_135dcd_14);
                } else {
                    tvSolidityNode.setTextColor(getResources().getColor(R.color.black_02_30));
                    tvSolidityNode.setBackgroundResource(R.drawable.roundborder_transparent);
                    tvFullNode.setTextColor(getResources().getColor(R.color.white));
                    tvFullNode.setBackgroundResource(R.drawable.roundborder_135dcd_14);
                    if (isFullNodeSetting) {
                        return;
                    }
                    isFullNodeSetting = true;
                    mNodeAdapter = null;
                    NodeSpeedTestActivity nodeSpeedTestActivity = NodeSpeedTestActivity.this;
                    NodeSpeedTestActivity nodeSpeedTestActivity2 = NodeSpeedTestActivity.this;
                    nodeSpeedTestActivity.mNodeAdapter = new NodeAdapter(nodeSpeedTestActivity2, nodeSpeedTestActivity2.mainFullNodeList, mOnNodeSelectedListener);
                    rcList.setAdapter(mNodeAdapter);
                }
            }
        };
        this.binding.tvFullNode.setOnClickListener(noDoubleClickListener2);
        this.binding.tvSolidityNode.setOnClickListener(noDoubleClickListener2);
        this.binding.btnAddNode.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void lambda$startTestCustomNode$2(NodeBean nodeBean) {
        if (this.isCurrentMainChain) {
            IGrpcClient.THIS.connectFullNode(nodeBean.getAddress());
            new RxManager().post(Event.NET_CHANGE, "");
        }
        resetNode(true, false);
        if (nodeBean.getType() == 0) {
            int i = 0;
            while (true) {
                if (i >= this.mainFullNodeList.size()) {
                    break;
                } else if (this.mainFullNodeList.get(i).equals(nodeBean)) {
                    if (this.mainFullNodeList.get(i).getAddress().equals(SpAPI.THIS.getCurrentChain().fullNode)) {
                        this.mainFullNodeList.get(i).setSelected(true);
                    } else if (IGrpcClient.THIS.getCurrentFullNode().equals(nodeBean.getAddress())) {
                        this.mainFullNodeList.get(i).setSelected(true);
                    }
                } else {
                    i++;
                }
            }
        }
        notifyNodeLatencyChange(0);
    }

    public void lambda$startTestCustomNode$3(NodeBean nodeBean) {
        boolean z;
        boolean z2;
        if (nodeBean.getType() == 0) {
            z = true;
            z2 = false;
        } else {
            z = false;
            z2 = true;
        }
        resetNode(z, z2);
        if (nodeBean.getType() == 0) {
            int i = 0;
            while (true) {
                if (i >= this.mainFullNodeList.size()) {
                    break;
                } else if (this.mainFullNodeList.get(i).equals(nodeBean)) {
                    if (this.mainFullNodeList.get(i).getAddress().equals(SpAPI.THIS.getCurrentChain().fullNode)) {
                        this.mainFullNodeList.get(i).setSelected(true);
                    } else if (this.mainFullNodeList.get(i).getAddress().equals(IGrpcClient.THIS.getCurrentFullNode())) {
                        this.mainFullNodeList.get(i).setSelected(true);
                    }
                } else {
                    i++;
                }
            }
        }
        notifyNodeLatencyChange(0);
    }

    private void resetNode(boolean z, boolean z2) {
        if (z) {
            for (int i = 0; i < this.mainFullNodeList.size(); i++) {
                this.mainFullNodeList.get(i).setSelected(false);
            }
        }
    }

    public void startTestCustomNode(final NodeBean nodeBean, final boolean z) {
        showLoadingDialog(getString(R.string.connecting));
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$startTestCustomNode$6(nodeBean, z);
            }
        });
    }

    public void lambda$startTestCustomNode$6(final NodeBean nodeBean, boolean z) {
        if (startTestNode(nodeBean.getAddress(), nodeBean.getType() == 0) <= 0) {
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    IToast.getIToast().showAsBottomn(R.string.node_connection_time_out);
                }
            });
        } else if (this.isMainChain) {
            if (nodeBean.isCustomed()) {
                if (nodeBean.getType() == 0) {
                    SpAPI.THIS.setIsCustomFull(true);
                    SpAPI.THIS.setCustomFullNode(nodeBean.getAddress());
                }
            } else if (nodeBean.getType() == 0) {
                SpAPI.THIS.setIsCustomFull(false);
                SpAPI.THIS.setCustomFullNode("");
            }
            if (this.isCurrentMainChain && nodeBean.getType() == 0) {
                SpAPI.THIS.setIP(nodeBean.getAddress());
            }
            if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                List<ChainBean> lastAllChainJson = SpAPI.THIS.getLastAllChainJson();
                for (int i = 0; i < lastAllChainJson.size(); i++) {
                    if (lastAllChainJson.get(i).isMainChain & this.isMainChain) {
                        lastAllChainJson.get(i).isSelect = true;
                        SpAPI.THIS.setIP(lastAllChainJson.get(i).fullNode);
                    } else {
                        lastAllChainJson.get(i).isSelect = false;
                    }
                }
                SpAPI.THIS.setAllChainJson(lastAllChainJson);
                SpAPI.THIS.setTestVersion(SpAPI.THIS.getLastTestVersion());
            } else {
                List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
                if (allChainJson != null && allChainJson.size() > 0) {
                    for (ChainBean chainBean : allChainJson) {
                        if (chainBean != null && chainBean.isMainChain) {
                            if (nodeBean.getType() == 0) {
                                chainBean.fullNode = nodeBean.getAddress();
                            } else {
                                chainBean.solidityNode = nodeBean.getAddress();
                            }
                        }
                    }
                }
                SpAPI.THIS.setAllChainJson(allChainJson);
            }
            if (z) {
                IGrpcClient.THIS.connectFullNode(nodeBean.getAddress());
            }
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$startTestCustomNode$2(nodeBean);
                }
            });
        } else {
            List<ChainBean> allChainJson2 = SpAPI.THIS.getAllChainJson();
            if (allChainJson2 != null && allChainJson2.size() > 0) {
                for (ChainBean chainBean2 : allChainJson2) {
                    if (chainBean2 != null && !chainBean2.isMainChain) {
                        if (nodeBean.getType() == 0) {
                            chainBean2.fullNode = nodeBean.getAddress();
                        } else {
                            chainBean2.solidityNode = nodeBean.getAddress();
                        }
                    }
                }
            }
            SpAPI.THIS.setAllChainJson(allChainJson2);
            if (nodeBean.isCustomed()) {
                if (nodeBean.getType() == 0) {
                    SpAPI.THIS.setIsDappChainCustomFull(this.curEnvironmentStr, true);
                    SpAPI.THIS.setDappCustomFullNode(this.curEnvironmentStr, nodeBean.getAddress());
                } else {
                    SpAPI.THIS.setIsDappCustomSol(true);
                    SpAPI.THIS.setDappChainCustomSolidity(nodeBean.getAddress());
                }
            } else if (nodeBean.getType() == 0) {
                SpAPI.THIS.setIsDappChainCustomFull(this.curEnvironmentStr, false);
                SpAPI.THIS.setDappCustomFullNode(this.curEnvironmentStr, "");
            }
            if (!this.isCurrentMainChain && nodeBean.getType() == 0) {
                SpAPI.THIS.setIP(nodeBean.getAddress());
            }
            if (z) {
                IGrpcClient.THIS.connectFullNode(nodeBean.getAddress());
            }
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$startTestCustomNode$3(nodeBean);
                }
            });
        }
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                lambda$startTestCustomNode$5();
            }
        });
    }

    public void lambda$startTestCustomNode$5() {
        dismissLoadingDialog();
    }

    public void startAsyncNodeSpeedTest(final boolean z) {
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$startAsyncNodeSpeedTest$7(z);
            }
        });
    }

    public void lambda$startAsyncNodeSpeedTest$7(boolean z) {
        try {
            if (this.isFullNodeSetting) {
                for (int i = 0; i < this.mainFullNodeList.size(); i++) {
                    String address = this.mainFullNodeList.get(i).getAddress();
                    LogUtils.d(TAG, "start test mainFullNode speed " + address);
                    startTestNode(address, true);
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public long startTestNode(final String str, boolean z) {
        final long startSocketSpeedNode = startSocketSpeedNode(str, z);
        if (startSocketSpeedNode > 0) {
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$startTestNode$8(str, startSocketSpeedNode);
                }
            });
        } else {
            LogUtils.i(TAG, "startTestNode Exception : ");
            if (this.isFullNodeSetting) {
                for (int i = 0; i < this.mainFullNodeList.size(); i++) {
                    if (this.mainFullNodeList.get(i).getAddress().equals(str)) {
                        NodeBean nodeBean = this.mainFullNodeList.get(i);
                        nodeBean.setStatus(3);
                        nodeBean.setLatency(-2);
                        notifyNodeLatencyChange(i);
                    }
                }
            }
        }
        return startSocketSpeedNode;
    }

    public void lambda$startTestNode$8(String str, long j) {
        if (this.isFullNodeSetting) {
            for (int i = 0; i < this.mainFullNodeList.size(); i++) {
                if (this.mainFullNodeList.get(i).getAddress().equals(str)) {
                    NodeBean nodeBean = this.mainFullNodeList.get(i);
                    nodeBean.setLatency((int) j);
                    nodeBean.setStatus(0);
                    notifyNodeLatencyChange(i);
                }
            }
        }
    }

    public long startSocketSpeedNode(final String str, boolean z) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        FutureTask futureTask = (FutureTask) newSingleThreadExecutor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                long currentTimeMillis = System.currentTimeMillis();
                long j = 0;
                Socket socket = null;
                try {
                    try {
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Exception e) {
                    e = e;
                }
                if (str.contains(":")) {
                    String[] split = str.split(":");
                    Socket socket2 = new Socket(split[0], Integer.parseInt(split[1]));
                    try {
                        socket2.isConnected();
                        j = System.currentTimeMillis() - currentTimeMillis;
                        LogUtils.i("alex", "test node runtime : " + j);
                        socket2.close();
                    } catch (Exception e2) {
                        e = e2;
                        socket = socket2;
                        LogUtils.i("alex", "test node runtime : " + e.toString());
                        if (socket != null) {
                            socket.close();
                        }
                        return Long.valueOf(j);
                    } catch (Throwable th2) {
                        th = th2;
                        socket = socket2;
                        if (socket != null) {
                            socket.close();
                        }
                        throw th;
                    }
                    return Long.valueOf(j);
                }
                throw new IllegalArgumentException("IP is Not correct");
            }
        });
        newSingleThreadExecutor.execute(futureTask);
        try {
            return ((Long) futureTask.get(6L, TimeUnit.SECONDS)).longValue();
        } catch (TimeoutException e) {
            LogUtils.e(e);
            return -1L;
        } catch (Exception e2) {
            LogUtils.e(e2);
            return 0L;
        }
    }

    private void notifyNodeLatencyChange(int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mNodeAdapter != null) {
                    mNodeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog(getString(R.string.connecting));
    }

    @Override
    public void dismisLoadingDialog() {
        super.dismissLoadingDialog();
    }
}
