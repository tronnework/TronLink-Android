package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeBean;
import com.tron.wallet.common.adapter.NodeAdapter;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
public class NodeHolder extends BaseHolder {
    ConstraintLayout constraintLayout;
    private Context context;
    TextView customNode;
    ImageView ivNodeSelect;
    TextView latency;
    TextView latencyDesc;
    AppCompatImageView latencyDot;
    private NodeBean mBean;
    private ArrayList<NodeBean> mNodeList;
    private int mPosition;
    ImageView nodeEdit;
    TextView nodeIP;
    TextView nodePort;
    TextView nodePortDesc;
    private NodeAdapter.OnNodeSelectedListener onNodeSelectedListener;
    TextView timeOut;

    public NodeHolder(View view) {
        super(view);
        this.constraintLayout = (ConstraintLayout) view.findViewById(R.id.constraintLayout);
        this.nodeIP = (TextView) view.findViewById(R.id.node_ip);
        this.customNode = (TextView) view.findViewById(R.id.custom_node);
        this.ivNodeSelect = (ImageView) view.findViewById(R.id.iv_node_selected);
        this.nodePortDesc = (TextView) view.findViewById(R.id.tv_node_port_desc);
        this.nodePort = (TextView) view.findViewById(R.id.tv_node_port);
        this.latencyDesc = (TextView) view.findViewById(R.id.custom_latency_desc);
        this.latencyDot = (AppCompatImageView) view.findViewById(R.id.latency_speed_dot);
        this.latency = (TextView) view.findViewById(R.id.custom_latency);
        this.timeOut = (TextView) view.findViewById(R.id.tv_node_timeout);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_node_edit);
        this.nodeEdit = imageView;
        Collection.-EL.stream(Arrays.asList(imageView, this.constraintLayout, this.ivNodeSelect)).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$0((View) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public void lambda$new$0(View view) {
        view.setOnClickListener(getOnClickListener());
    }

    public void bindHolder(Context context, NodeBean nodeBean, ArrayList<NodeBean> arrayList, int i, NodeAdapter.OnNodeSelectedListener onNodeSelectedListener) {
        this.context = context;
        this.mBean = nodeBean;
        this.onNodeSelectedListener = onNodeSelectedListener;
        this.mNodeList = arrayList;
        this.mPosition = i;
        initView();
        this.nodeIP.setText(this.mBean.getIp());
        TextView textView = this.nodePort;
        textView.setText(this.mBean.getPort() + "");
        if (this.mBean.isCustomed()) {
            this.customNode.setVisibility(View.VISIBLE);
        } else {
            this.customNode.setVisibility(View.GONE);
        }
        if (this.mBean.getLatency() > 0) {
            this.latency.setVisibility(View.VISIBLE);
            this.latency.setTextColor(this.context.getResources().getColor(R.color.black_23));
            if (nodeBean.getStatus() == 0) {
                TextView textView2 = this.latency;
                textView2.setText(this.mBean.getLatency() + "ms");
            } else if (nodeBean.getStatus() == 1) {
                TextView textView3 = this.latency;
                textView3.setText(this.mBean.getLatency() + "ms");
            } else if (nodeBean.getStatus() == 2) {
                this.latency.setText(R.string.connecting);
            } else if (nodeBean.getStatus() == 3) {
                this.latency.setVisibility(View.GONE);
                this.latencyDesc.setVisibility(View.GONE);
                this.timeOut.setVisibility(View.VISIBLE);
            }
        } else {
            this.latency.setVisibility(View.VISIBLE);
            this.latency.setText(R.string.connecting);
            this.latency.setTextColor(this.context.getResources().getColor(R.color.gray_9B));
        }
        if (nodeBean.getStatus() == 3) {
            this.latency.setVisibility(View.GONE);
            this.latencyDesc.setVisibility(View.GONE);
            this.timeOut.setVisibility(View.VISIBLE);
            this.nodeIP.setTextColor(this.context.getResources().getColor(R.color.gray_9B));
            this.nodePort.setTextColor(this.context.getResources().getColor(R.color.gray_9B));
            this.nodePortDesc.setTextColor(this.context.getResources().getColor(R.color.gray_9B));
            this.ivNodeSelect.setVisibility(View.GONE);
            this.ivNodeSelect.setEnabled(false);
        } else if (nodeBean.getStatus() == 2) {
            this.ivNodeSelect.setVisibility(View.VISIBLE);
            this.ivNodeSelect.setEnabled(false);
        } else {
            this.ivNodeSelect.setVisibility(View.VISIBLE);
        }
        if (nodeBean.isCustomed()) {
            this.nodeEdit.setVisibility(View.VISIBLE);
        }
        if (nodeBean.isSelected()) {
            this.ivNodeSelect.setVisibility(View.VISIBLE);
            this.ivNodeSelect.setImageResource(R.mipmap.ic_check_selected);
        } else {
            this.ivNodeSelect.setImageResource(R.mipmap.ic_check_unselect);
        }
        if (nodeBean.getLatency() < 200) {
            this.latencyDot.setImageResource(R.drawable.node_speed_dot_fast);
        } else if (nodeBean.getLatency() > 500) {
            this.latencyDot.setImageResource(R.drawable.node_speed_dot_slow);
        } else {
            this.latencyDot.setImageResource(R.drawable.node_speed_dot_medium);
        }
        this.latencyDesc.setVisibility(View.GONE);
        if (this.mBean.getLatency() > 0) {
            if (nodeBean.getStatus() == 0) {
                this.latencyDot.setVisibility(View.VISIBLE);
                return;
            } else if (nodeBean.getStatus() == 1) {
                this.latencyDot.setVisibility(View.VISIBLE);
                return;
            } else if (nodeBean.getStatus() == 2) {
                this.latencyDot.setVisibility(View.GONE);
                return;
            } else if (nodeBean.getStatus() == 3) {
                this.latencyDot.setVisibility(View.GONE);
                return;
            } else {
                return;
            }
        }
        this.latencyDot.setVisibility(View.GONE);
    }

    private void initView() {
        this.customNode.setVisibility(View.GONE);
        this.timeOut.setVisibility(View.GONE);
        this.ivNodeSelect.setImageResource(R.mipmap.ic_check_unselect);
        this.nodeEdit.setVisibility(View.GONE);
        this.ivNodeSelect.setVisibility(View.VISIBLE);
        this.ivNodeSelect.setEnabled(true);
        this.latency.setVisibility(View.VISIBLE);
        this.latencyDesc.setVisibility(View.VISIBLE);
        this.nodeIP.setTextColor(this.context.getResources().getColor(R.color.black_23));
        this.nodePort.setTextColor(this.context.getResources().getColor(R.color.black_23));
        this.nodePortDesc.setTextColor(this.context.getResources().getColor(R.color.gray_9B));
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.constraintLayout) {
                    if (id == R.id.iv_node_edit) {
                        if (onNodeSelectedListener != null) {
                            onNodeSelectedListener.onNodeEdit(mBean);
                            return;
                        }
                        return;
                    } else if (id != R.id.iv_node_selected) {
                        return;
                    }
                }
                if (onNodeSelectedListener != null) {
                    onNodeSelectedListener.onNodeSelected(mBean);
                }
            }
        };
    }
}
