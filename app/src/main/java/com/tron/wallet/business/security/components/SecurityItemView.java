package com.tron.wallet.business.security.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.business.security.environment.bean.ItemData;
import com.tronlinkpro.wallet.R;
public class SecurityItemView extends RelativeLayout {
    private ImageView ivRight;
    private ImageView ivStatus;
    private TextView tvDesc;
    private TextView tvStatus;

    public enum EnvironmentItem {
        Root,
        Simulator,
        Net,
        Clipboard,
        Screen,
        Official
    }

    public enum HomeItem {
        Environment,
        Token,
        Approve
    }

    public SecurityItemView(Context context) {
        super(context);
    }

    public SecurityItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SecurityItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SecurityItemView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        View inflate = View.inflate(context, R.layout.item_list_security_view, null);
        this.tvStatus = (TextView) inflate.findViewById(R.id.tv_status);
        this.tvDesc = (TextView) inflate.findViewById(R.id.tv_desc);
        this.ivStatus = (ImageView) inflate.findViewById(R.id.iv_status);
        this.ivRight = (ImageView) inflate.findViewById(R.id.iv_right);
        addView(inflate, new RelativeLayout.LayoutParams(-1, -2));
    }

    public void updateHomeText(ResultStatusEnum resultStatusEnum, HomeItem homeItem, HomeResource homeResource, int i) {
        try {
            updateHomeItemStatus(resultStatusEnum);
            int i2 = fun1.$SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem[homeItem.ordinal()];
            if (i2 == 1) {
                setHomeText(resultStatusEnum, homeResource.environmentResource, i);
            } else if (i2 == 2) {
                setHomeText(resultStatusEnum, homeResource.tokenResource, i);
            } else if (i2 == 3) {
                setHomeText(resultStatusEnum, homeResource.approveResource, i);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void updateEnvironmentText(ResultStatusEnum resultStatusEnum, EnvironmentItem environmentItem, EnvironmentResource environmentResource) {
        try {
            updateEnvironmentItemStatus(resultStatusEnum);
            switch (fun1.$SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$EnvironmentItem[environmentItem.ordinal()]) {
                case 1:
                    setEnvironmentText(resultStatusEnum, environmentResource.rootResource);
                    break;
                case 2:
                    setEnvironmentText(resultStatusEnum, environmentResource.simulatorResource);
                    break;
                case 3:
                    setEnvironmentText(resultStatusEnum, environmentResource.netResource);
                    break;
                case 4:
                    setEnvironmentText(resultStatusEnum, environmentResource.clipboardResource);
                    break;
                case 5:
                    setEnvironmentText(resultStatusEnum, environmentResource.screenResource);
                    break;
                case 6:
                    setEnvironmentText(resultStatusEnum, environmentResource.officialResource);
                    break;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$security$ResultStatusEnum;
        static final int[] $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$EnvironmentItem;
        static final int[] $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem;

        static {
            int[] iArr = new int[ResultStatusEnum.values().length];
            $SwitchMap$com$tron$wallet$business$security$ResultStatusEnum = iArr;
            try {
                iArr[ResultStatusEnum.Safe.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$ResultStatusEnum[ResultStatusEnum.Waring.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$ResultStatusEnum[ResultStatusEnum.Risk.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$ResultStatusEnum[ResultStatusEnum.Error.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[EnvironmentItem.values().length];
            $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$EnvironmentItem = iArr2;
            try {
                iArr2[EnvironmentItem.Root.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$EnvironmentItem[EnvironmentItem.Simulator.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$EnvironmentItem[EnvironmentItem.Net.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$EnvironmentItem[EnvironmentItem.Clipboard.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$EnvironmentItem[EnvironmentItem.Screen.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$EnvironmentItem[EnvironmentItem.Official.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr3 = new int[HomeItem.values().length];
            $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem = iArr3;
            try {
                iArr3[HomeItem.Environment.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem[HomeItem.Token.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$security$components$SecurityItemView$HomeItem[HomeItem.Approve.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    private void setHomeText(ResultStatusEnum resultStatusEnum, ItemData itemData, int i) {
        try {
            int i2 = fun1.$SwitchMap$com$tron$wallet$business$security$ResultStatusEnum[resultStatusEnum.ordinal()];
            if (i2 == 1) {
                this.tvStatus.setText(itemData.getTitle());
                this.tvDesc.setText(itemData.getSafeDescribe());
            } else if (i2 == 2) {
                this.tvStatus.setText(itemData.getTitle());
                this.tvDesc.setText(String.format(StringTronUtil.getResString(itemData.getWaringDescribe()), Integer.valueOf(i)));
            } else if (i2 == 3) {
                this.tvStatus.setText(itemData.getTitle());
                this.tvDesc.setText(String.format(StringTronUtil.getResString(itemData.getRiskDescribe()), Integer.valueOf(i)));
            } else if (i2 == 4) {
                this.tvStatus.setText(itemData.getErrorTitle());
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void setEnvironmentText(ResultStatusEnum resultStatusEnum, ItemData itemData) {
        try {
            if (itemData.getTitle() != 0) {
                this.tvStatus.setText(itemData.getTitle());
            }
            int i = fun1.$SwitchMap$com$tron$wallet$business$security$ResultStatusEnum[resultStatusEnum.ordinal()];
            if (i == 1) {
                if (itemData.getSafeDescribe() != 0) {
                    this.tvDesc.setText(itemData.getSafeDescribe());
                }
            } else if (i == 2) {
                if (itemData.getWaringDescribe() != 0) {
                    this.tvDesc.setText(itemData.getWaringDescribe());
                }
            } else if (i == 3 && itemData.getRiskDescribe() != 0) {
                this.tvDesc.setText(itemData.getRiskDescribe());
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void updateHomeItemStatus(ResultStatusEnum resultStatusEnum) {
        try {
            this.ivRight.setVisibility(View.VISIBLE);
            this.tvDesc.setVisibility(View.VISIBLE);
            this.tvStatus.setTextColor(getResources().getColor(R.color.black_23));
            int i = fun1.$SwitchMap$com$tron$wallet$business$security$ResultStatusEnum[resultStatusEnum.ordinal()];
            if (i == 1) {
                this.ivStatus.setImageResource(R.mipmap.icon_security_safe);
            } else if (i == 2) {
                this.ivStatus.setImageResource(R.mipmap.icon_security_waring);
            } else if (i == 3) {
                this.ivStatus.setImageResource(R.mipmap.icon_security_risk);
            } else if (i == 4) {
                this.tvDesc.setVisibility(View.GONE);
                this.ivRight.setVisibility(View.GONE);
                this.ivStatus.setImageResource(R.mipmap.icon_security_error);
                this.tvStatus.setTextColor(getResources().getColor(R.color.red_EC));
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void updateEnvironmentItemStatus(ResultStatusEnum resultStatusEnum) {
        try {
            this.ivRight.setVisibility(View.GONE);
            int i = fun1.$SwitchMap$com$tron$wallet$business$security$ResultStatusEnum[resultStatusEnum.ordinal()];
            if (i == 1) {
                this.ivStatus.setImageResource(R.mipmap.icon_security_safe);
                this.tvStatus.setTextColor(getResources().getColor(R.color.black_23));
            } else if (i == 2) {
                this.ivStatus.setImageResource(R.mipmap.icon_security_waring);
                this.tvStatus.setTextColor(getResources().getColor(R.color.yellow_FFA928));
            } else if (i == 3) {
                this.ivStatus.setImageResource(R.mipmap.icon_security_risk);
                this.tvStatus.setTextColor(getResources().getColor(R.color.red_EC));
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
