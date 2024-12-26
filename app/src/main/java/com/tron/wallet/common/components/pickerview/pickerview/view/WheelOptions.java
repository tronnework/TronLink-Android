package com.tron.wallet.common.components.pickerview.pickerview.view;

import android.graphics.Typeface;
import android.view.View;
import com.tron.wallet.common.components.pickerview.pickerview.adapter.ArrayWheelAdapter;
import com.tron.wallet.common.components.pickerview.pickerview.listener.OnOptionsSelectChangeListener;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.listener.OnItemSelectedListener;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.view.WheelView;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class WheelOptions<T> {
    private boolean isRestoreItem;
    private boolean linkage = true;
    private List<T> mOptions1Items;
    private List<List<T>> mOptions2Items;
    private List<List<List<T>>> mOptions3Items;
    private OnOptionsSelectChangeListener optionsSelectChangeListener;
    private View view;
    private OnItemSelectedListener wheelListener_option1;
    private OnItemSelectedListener wheelListener_option2;
    private WheelView wv_option1;
    private WheelView wv_option2;
    private WheelView wv_option3;

    private void setLineSpacingMultiplier() {
    }

    public View getView() {
        return this.view;
    }

    public void setLinkage(boolean z) {
        this.linkage = z;
    }

    public void setOptionsSelectChangeListener(OnOptionsSelectChangeListener onOptionsSelectChangeListener) {
        this.optionsSelectChangeListener = onOptionsSelectChangeListener;
    }

    public void setView(View view) {
        this.view = view;
    }

    public WheelOptions(View view, boolean z) {
        this.isRestoreItem = z;
        this.view = view;
        this.wv_option1 = (WheelView) view.findViewById(R.id.options1);
        this.wv_option2 = (WheelView) view.findViewById(R.id.options2);
        this.wv_option3 = (WheelView) view.findViewById(R.id.options3);
    }

    public void setPicker(List<T> list, List<List<T>> list2, List<List<List<T>>> list3) {
        this.mOptions1Items = list;
        this.mOptions2Items = list2;
        this.mOptions3Items = list3;
        this.wv_option1.setAdapter(new ArrayWheelAdapter(list));
        this.wv_option1.setCurrentItem(0);
        List<List<T>> list4 = this.mOptions2Items;
        if (list4 != null) {
            this.wv_option2.setAdapter(new ArrayWheelAdapter(list4.get(0)));
        }
        WheelView wheelView = this.wv_option2;
        wheelView.setCurrentItem(wheelView.getCurrentItem());
        List<List<List<T>>> list5 = this.mOptions3Items;
        if (list5 != null) {
            this.wv_option3.setAdapter(new ArrayWheelAdapter(list5.get(0).get(0)));
        }
        WheelView wheelView2 = this.wv_option3;
        wheelView2.setCurrentItem(wheelView2.getCurrentItem());
        this.wv_option1.setIsOptions(true);
        this.wv_option2.setIsOptions(true);
        this.wv_option3.setIsOptions(true);
        if (this.mOptions2Items == null) {
            this.wv_option2.setVisibility(View.GONE);
        } else {
            this.wv_option2.setVisibility(View.VISIBLE);
        }
        if (this.mOptions3Items == null) {
            this.wv_option3.setVisibility(View.GONE);
        } else {
            this.wv_option3.setVisibility(View.VISIBLE);
        }
        this.wheelListener_option1 = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                int i2;
                if (mOptions2Items == null) {
                    if (optionsSelectChangeListener != null) {
                        optionsSelectChangeListener.onOptionsSelectChanged(wv_option1.getCurrentItem(), 0, 0);
                        return;
                    }
                    return;
                }
                if (isRestoreItem) {
                    i2 = 0;
                } else {
                    i2 = wv_option2.getCurrentItem();
                    if (i2 >= ((List) mOptions2Items.get(i)).size() - 1) {
                        i2 = ((List) mOptions2Items.get(i)).size() - 1;
                    }
                }
                wv_option2.setAdapter(new ArrayWheelAdapter((List) mOptions2Items.get(i)));
                wv_option2.setCurrentItem(i2);
                if (mOptions3Items != null) {
                    wheelListener_option2.onItemSelected(i2);
                } else if (optionsSelectChangeListener != null) {
                    optionsSelectChangeListener.onOptionsSelectChanged(i, i2, 0);
                }
            }
        };
        this.wheelListener_option2 = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                int i2 = 0;
                if (mOptions3Items != null) {
                    int currentItem = wv_option1.getCurrentItem();
                    if (currentItem >= mOptions3Items.size() - 1) {
                        currentItem = mOptions3Items.size() - 1;
                    }
                    if (i >= ((List) mOptions2Items.get(currentItem)).size() - 1) {
                        i = ((List) mOptions2Items.get(currentItem)).size() - 1;
                    }
                    if (!isRestoreItem) {
                        i2 = wv_option3.getCurrentItem() >= ((List) ((List) mOptions3Items.get(currentItem)).get(i)).size() + (-1) ? ((List) ((List) mOptions3Items.get(currentItem)).get(i)).size() - 1 : wv_option3.getCurrentItem();
                    }
                    wv_option3.setAdapter(new ArrayWheelAdapter((List) ((List) mOptions3Items.get(wv_option1.getCurrentItem())).get(i)));
                    wv_option3.setCurrentItem(i2);
                    if (optionsSelectChangeListener != null) {
                        optionsSelectChangeListener.onOptionsSelectChanged(wv_option1.getCurrentItem(), i, i2);
                    }
                } else if (optionsSelectChangeListener != null) {
                    optionsSelectChangeListener.onOptionsSelectChanged(wv_option1.getCurrentItem(), i, 0);
                }
            }
        };
        if (list != null && this.linkage) {
            this.wv_option1.setOnItemSelectedListener(this.wheelListener_option1);
        }
        if (list2 != null && this.linkage) {
            this.wv_option2.setOnItemSelectedListener(this.wheelListener_option2);
        }
        if (list3 == null || !this.linkage || this.optionsSelectChangeListener == null) {
            return;
        }
        this.wv_option3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                optionsSelectChangeListener.onOptionsSelectChanged(wv_option1.getCurrentItem(), wv_option2.getCurrentItem(), i);
            }
        });
    }

    public void setNPicker(List<T> list, List<T> list2, List<T> list3) {
        this.wv_option1.setAdapter(new ArrayWheelAdapter(list));
        this.wv_option1.setCurrentItem(0);
        if (list2 != null) {
            this.wv_option2.setAdapter(new ArrayWheelAdapter(list2));
        }
        WheelView wheelView = this.wv_option2;
        wheelView.setCurrentItem(wheelView.getCurrentItem());
        if (list3 != null) {
            this.wv_option3.setAdapter(new ArrayWheelAdapter(list3));
        }
        WheelView wheelView2 = this.wv_option3;
        wheelView2.setCurrentItem(wheelView2.getCurrentItem());
        this.wv_option1.setIsOptions(true);
        this.wv_option2.setIsOptions(true);
        this.wv_option3.setIsOptions(true);
        if (this.optionsSelectChangeListener != null) {
            this.wv_option1.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int i) {
                    optionsSelectChangeListener.onOptionsSelectChanged(i, wv_option2.getCurrentItem(), wv_option3.getCurrentItem());
                }
            });
        }
        if (list2 == null) {
            this.wv_option2.setVisibility(View.GONE);
        } else {
            this.wv_option2.setVisibility(View.VISIBLE);
            if (this.optionsSelectChangeListener != null) {
                this.wv_option2.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int i) {
                        optionsSelectChangeListener.onOptionsSelectChanged(wv_option1.getCurrentItem(), i, wv_option3.getCurrentItem());
                    }
                });
            }
        }
        if (list3 == null) {
            this.wv_option3.setVisibility(View.GONE);
            return;
        }
        this.wv_option3.setVisibility(View.VISIBLE);
        if (this.optionsSelectChangeListener != null) {
            this.wv_option3.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int i) {
                    optionsSelectChangeListener.onOptionsSelectChanged(wv_option1.getCurrentItem(), wv_option2.getCurrentItem(), i);
                }
            });
        }
    }

    public void setTextContentSize(int i) {
        float f = i;
        this.wv_option1.setTextSize(f);
        this.wv_option2.setTextSize(f);
        this.wv_option3.setTextSize(f);
    }

    public void setLabels(String str, String str2, String str3) {
        if (str != null) {
            this.wv_option1.setLabel(str);
        }
        if (str2 != null) {
            this.wv_option2.setLabel(str2);
        }
        if (str3 != null) {
            this.wv_option3.setLabel(str3);
        }
    }

    public void setTextXOffset(int i, int i2, int i3) {
        this.wv_option1.setTextXOffset(i);
        this.wv_option2.setTextXOffset(i2);
        this.wv_option3.setTextXOffset(i3);
    }

    public void setCyclic(boolean z) {
        this.wv_option1.setCyclic(z);
        this.wv_option2.setCyclic(z);
        this.wv_option3.setCyclic(z);
    }

    public void setTypeface(Typeface typeface) {
        this.wv_option1.setTypeface(typeface);
        this.wv_option2.setTypeface(typeface);
        this.wv_option3.setTypeface(typeface);
    }

    public void setCyclic(boolean z, boolean z2, boolean z3) {
        this.wv_option1.setCyclic(z);
        this.wv_option2.setCyclic(z2);
        this.wv_option3.setCyclic(z3);
    }

    public int[] getCurrentItems() {
        int[] iArr = new int[3];
        iArr[0] = this.wv_option1.getCurrentItem();
        List<List<T>> list = this.mOptions2Items;
        if (list != null && list.size() > 0) {
            iArr[1] = this.wv_option2.getCurrentItem() > this.mOptions2Items.get(iArr[0]).size() - 1 ? 0 : this.wv_option2.getCurrentItem();
        } else {
            iArr[1] = this.wv_option2.getCurrentItem();
        }
        List<List<List<T>>> list2 = this.mOptions3Items;
        if (list2 != null && list2.size() > 0) {
            iArr[2] = this.wv_option3.getCurrentItem() <= this.mOptions3Items.get(iArr[0]).get(iArr[1]).size() - 1 ? this.wv_option3.getCurrentItem() : 0;
        } else {
            iArr[2] = this.wv_option3.getCurrentItem();
        }
        return iArr;
    }

    public void setCurrentItems(int i, int i2, int i3) {
        if (this.linkage) {
            itemSelected(i, i2, i3);
            return;
        }
        this.wv_option1.setCurrentItem(i);
        this.wv_option2.setCurrentItem(i2);
        this.wv_option3.setCurrentItem(i3);
    }

    private void itemSelected(int i, int i2, int i3) {
        if (this.mOptions1Items != null) {
            this.wv_option1.setCurrentItem(i);
        }
        List<List<T>> list = this.mOptions2Items;
        if (list != null) {
            this.wv_option2.setAdapter(new ArrayWheelAdapter(list.get(i)));
            this.wv_option2.setCurrentItem(i2);
        }
        List<List<List<T>>> list2 = this.mOptions3Items;
        if (list2 != null) {
            this.wv_option3.setAdapter(new ArrayWheelAdapter(list2.get(i).get(i2)));
            this.wv_option3.setCurrentItem(i3);
        }
    }

    public void setLineSpacingMultiplier(float f) {
        this.wv_option1.setLineSpacingMultiplier(f);
        this.wv_option2.setLineSpacingMultiplier(f);
        this.wv_option3.setLineSpacingMultiplier(f);
    }

    public void setDividerColor(int i) {
        this.wv_option1.setDividerColor(i);
        this.wv_option2.setDividerColor(i);
        this.wv_option3.setDividerColor(i);
    }

    public void setDividerType(WheelView.DividerType dividerType) {
        this.wv_option1.setDividerType(dividerType);
        this.wv_option2.setDividerType(dividerType);
        this.wv_option3.setDividerType(dividerType);
    }

    public void setTextColorCenter(int i) {
        this.wv_option1.setTextColorCenter(i);
        this.wv_option2.setTextColorCenter(i);
        this.wv_option3.setTextColorCenter(i);
    }

    public void setTextColorOut(int i) {
        this.wv_option1.setTextColorOut(i);
        this.wv_option2.setTextColorOut(i);
        this.wv_option3.setTextColorOut(i);
    }

    public void isCenterLabel(boolean z) {
        this.wv_option1.isCenterLabel(z);
        this.wv_option2.isCenterLabel(z);
        this.wv_option3.isCenterLabel(z);
    }

    public void setItemsVisible(int i) {
        this.wv_option1.setItemsVisibleCount(i);
        this.wv_option2.setItemsVisibleCount(i);
        this.wv_option3.setItemsVisibleCount(i);
    }

    public void setAlphaGradient(boolean z) {
        this.wv_option1.setAlphaGradient(z);
        this.wv_option2.setAlphaGradient(z);
        this.wv_option3.setAlphaGradient(z);
    }
}
