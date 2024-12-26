package com.tron.wallet.business.tabmy.node;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.confirm.NodeTypeListAdapter;
import com.tron.wallet.business.tabmy.myhome.settings.NodeSpeedTestActivity;
import com.tron.wallet.common.components.ErrorBottomLayout;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.AcAddCustomNodeBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;
public class AddCustomNodeActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private String address;
    private AcAddCustomNodeBinding binding;
    Button btnDelete;
    Button btnNext;
    ErrorBottomLayout eetNodeIp;
    ErrorBottomLayout eetNodePort;
    EditText etNodeIP;
    EditText etNodePort;
    private int fullCount;
    ImageView mArrowIv;
    private NodeTypeListAdapter mNodeTypeAdapter;
    private String[] nodeTypes;
    RecyclerView recyclerView;
    RelativeLayout rlNodeSelect;
    private int solCount;
    TextView tvSelectedName;
    private boolean ipFlag = false;
    private boolean portFlag = false;
    private int mSelectedNodeType = 0;
    private HashMap<String, Boolean> mNodeTypeMap = new HashMap<>();

    @Override
    protected void setLayout() {
        AcAddCustomNodeBinding inflate = AcAddCustomNodeBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.add_custom_node));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.rlNodeSelect = this.binding.rlNodeTypeSelect;
        this.tvSelectedName = this.binding.tvSelectedName;
        this.recyclerView = this.binding.nodeTypeList;
        this.mArrowIv = this.binding.ivArrow;
        this.etNodeIP = this.binding.etNodeIp;
        this.eetNodeIp = this.binding.eetNodeIp;
        this.etNodePort = this.binding.etNodePort;
        this.eetNodePort = this.binding.eetNodePort;
        this.btnNext = this.binding.btNext;
        this.btnDelete = this.binding.btDelete;
    }

    @Override
    protected void processData() {
        Intent intent = getIntent();
        this.nodeTypes = getResources().getStringArray(R.array.node_type);
        reSetMap();
        this.mNodeTypeAdapter = new NodeTypeListAdapter(new ArrayList(Arrays.asList(this.nodeTypes)), this.mNodeTypeMap);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.mNodeTypeAdapter);
        this.mNodeTypeAdapter.setItemClickListener(new NodeTypeListAdapter.OnItemClickListener() {
            @Override
            public final void onItemClick(View view, int i) {
                lambda$processData$0(view, i);
            }
        });
        addEtcontentWatch();
        if (intent != null) {
            if (intent.getIntExtra(NodeSpeedTestActivity.CUSTOM_NODE_TYPE, -1) == 1) {
                this.tvSelectedName.setText(this.nodeTypes[1]);
                this.mSelectedNodeType = 1;
                this.mNodeTypeMap.put(this.nodeTypes[1], true);
            } else {
                this.tvSelectedName.setText(this.nodeTypes[0]);
                this.mSelectedNodeType = 0;
                this.mNodeTypeMap.put(this.nodeTypes[0], true);
            }
            if (intent.hasExtra(NodeSpeedTestActivity.CUSTOM_NODE_IP)) {
                String stringExtra = intent.getStringExtra(NodeSpeedTestActivity.CUSTOM_NODE_IP);
                int intExtra = intent.getIntExtra(NodeSpeedTestActivity.CUSTOM_NODE_PORT, -1);
                this.etNodeIP.setText(stringExtra);
                EditText editText = this.etNodePort;
                editText.setText(intExtra + "");
                this.btnDelete.setVisibility(View.VISIBLE);
            } else {
                this.btnDelete.setVisibility(View.GONE);
            }
            if (intent.hasExtra(NodeSpeedTestActivity.CUSTOM_NODE_ADDRESS)) {
                this.address = intent.getStringExtra(NodeSpeedTestActivity.CUSTOM_NODE_ADDRESS);
                setHeaderBar(getString(R.string.modify_custom_node));
            }
            this.fullCount = intent.getIntExtra(NodeSpeedTestActivity.FULL_NODE_COUNT, -1);
            this.solCount = intent.getIntExtra(NodeSpeedTestActivity.SOLIDITY_NODE_COUNT, -1);
        }
    }

    public void lambda$processData$0(View view, int i) {
        String[] strArr = this.nodeTypes;
        if (strArr.length > i) {
            this.tvSelectedName.setText(strArr[i]);
            this.mSelectedNodeType = i;
            reSetMap();
            this.mNodeTypeMap.put(this.nodeTypes[i], true);
            this.mNodeTypeAdapter.notifyDataSetChanged();
        }
        hidenPermissionLayout();
    }

    private void reSetMap() {
        for (String str : this.nodeTypes) {
            this.mNodeTypeMap.put(str, false);
        }
    }

    public void taggleShowPermissionLayout() {
        String[] strArr = this.nodeTypes;
        if (strArr == null || strArr.length <= 1) {
            return;
        }
        if (this.recyclerView.getVisibility() == 0) {
            hidenPermissionLayout();
            return;
        }
        this.recyclerView.setVisibility(View.VISIBLE);
        this.mArrowIv.setImageResource(R.mipmap.ic_close_row);
    }

    private void hidenPermissionLayout() {
        this.recyclerView.setVisibility(View.GONE);
        this.mArrowIv.setImageResource(R.mipmap.ic_open);
    }

    private void addEtcontentWatch() {
        this.rlNodeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taggleShowPermissionLayout();
            }
        });
        this.etNodeIP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                eetNodeIp.clearError();
                if (checkIP(editable.toString()) || checkHost(editable.toString())) {
                    ipFlag = true;
                } else {
                    ipFlag = false;
                }
                changeNext();
            }
        });
        this.etNodeIP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z || ipFlag) {
                    return;
                }
                eetNodeIp.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.enter_correct_ip));
            }
        });
        this.etNodePort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                eetNodePort.clearError();
                try {
                    if (!TextUtils.isEmpty(editable.toString()) && Integer.parseInt(editable.toString().trim()) >= 0 && Integer.parseInt(editable.toString().trim()) <= 65535) {
                        portFlag = true;
                    } else {
                        portFlag = false;
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                    eetNodePort.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.enter_correct_port));
                    portFlag = false;
                }
                changeNext();
            }
        });
        this.etNodePort.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z || portFlag) {
                    return;
                }
                eetNodePort.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.enter_correct_port));
            }
        });
    }

    public void changeNext() {
        if (this.ipFlag && this.portFlag) {
            this.btnNext.setEnabled(true);
        } else {
            this.btnNext.setEnabled(false);
        }
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.bt_delete) {
                    Intent intent = new Intent();
                    intent.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_IP, etNodeIP.getText().toString());
                    intent.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_TYPE, mSelectedNodeType);
                    intent.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_ADDRESS, address);
                    intent.putExtra(NodeSpeedTestActivity.DELETE_COUSTOM_NODE, true);
                    setResult(-1, intent);
                    finish();
                } else if (id != R.id.bt_next) {
                } else {
                    Intent intent2 = new Intent();
                    intent2.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_IP, etNodeIP.getText().toString());
                    intent2.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_PORT, Integer.parseInt(etNodePort.getText().toString()));
                    intent2.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_TYPE, mSelectedNodeType);
                    if (mSelectedNodeType == 0) {
                        if (fullCount >= 5) {
                            IToast.getIToast().showAsBottomn(R.string.cannot_add_more);
                            return;
                        }
                    } else if (solCount >= 5) {
                        IToast.getIToast().showAsBottomn(R.string.cannot_add_more);
                        return;
                    }
                    if (!TextUtils.isEmpty(address)) {
                        intent2.putExtra(NodeSpeedTestActivity.CUSTOM_NODE_ADDRESS, address);
                    }
                    setResult(-1, intent2);
                    finish();
                }
            }
        };
        this.binding.btDelete.setOnClickListener(noDoubleClickListener2);
        this.binding.btNext.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        setResult(0);
        finish();
    }

    public boolean checkIP(String str) {
        return Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$").matcher(str).matches();
    }

    public boolean checkHost(String str) {
        return Pattern.compile("[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?").matcher(str).matches();
    }
}
