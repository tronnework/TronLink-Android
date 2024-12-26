package com.tron.wallet.business.ledger.manage;

import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.ledger.manage.EquipmentAdapter;
import com.tron.wallet.databinding.ItemEquipmentBinding;
import com.tronlinkpro.wallet.R;
public class EquipmentItemHolder extends BaseViewHolder {
    private RotateAnimation animation;
    private ItemEquipmentBinding binding;
    ImageView iv_icon;
    LinearLayout lll_setting;
    RelativeLayout rl_Edit;
    RelativeLayout rl_content;
    TextView tvDisconnect;
    TextView tvImportAddress;
    TextView tvName;
    TextView tvTag;

    public EquipmentItemHolder(View view) {
        super(view);
        mappingId(view);
    }

    public void mappingId(View view) {
        ItemEquipmentBinding bind = ItemEquipmentBinding.bind(view);
        this.binding = bind;
        this.rl_Edit = bind.equipmentEdit;
        this.iv_icon = this.binding.editIcon;
        this.tvName = this.binding.equipmentName;
        this.lll_setting = this.binding.setting;
        this.rl_content = this.binding.rlContent;
        this.tvDisconnect = this.binding.tvDisconnect;
        this.tvImportAddress = this.binding.tvImportAddress;
        this.tvTag = this.binding.statusTag;
    }

    public void onBind(Context context, EquipmentBean equipmentBean, EquipmentAdapter.TYPE type) {
        if (equipmentBean.isConnected() && type.equals(EquipmentAdapter.TYPE.MANAGE)) {
            this.lll_setting.setVisibility(View.VISIBLE);
            this.rl_content.setBackground(this.itemView.getContext().getResources().getDrawable(R.drawable.shape_equipment_bg));
            this.iv_icon.setImageResource(R.mipmap.equipment_edit_connected);
            this.tvName.setTextColor(context.getResources().getColor(R.color.white));
            this.tvTag.setVisibility(View.VISIBLE);
        } else {
            this.lll_setting.setVisibility(View.GONE);
            this.rl_content.setBackground(null);
            this.iv_icon.setImageResource(R.mipmap.equipment_edit);
            this.tvName.setTextColor(context.getResources().getColor(R.color.black_02));
            this.tvTag.setVisibility(View.GONE);
        }
        if (type.equals(EquipmentAdapter.TYPE.MANAGE)) {
            this.rl_Edit.setVisibility(View.VISIBLE);
        } else {
            this.rl_Edit.setVisibility(View.GONE);
        }
        if (type == EquipmentAdapter.TYPE.SEARCH && equipmentBean.isConnecting()) {
            this.rl_Edit.setVisibility(View.VISIBLE);
            this.iv_icon.setImageResource(R.mipmap.circle_loading_20);
            this.iv_icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            this.animation = rotateAnimation;
            rotateAnimation.setFillAfter(true);
            this.animation.setInterpolator(new LinearInterpolator());
            this.animation.setDuration(1000L);
            this.animation.setRepeatCount(-1);
            this.iv_icon.startAnimation(this.animation);
        } else if (type == EquipmentAdapter.TYPE.SEARCH && equipmentBean.isConnected()) {
            this.rl_Edit.setVisibility(View.VISIBLE);
            this.iv_icon.setImageResource(R.mipmap.ic_green_right);
        }
        this.tvName.setText(equipmentBean.getDevice().getName());
    }
}
