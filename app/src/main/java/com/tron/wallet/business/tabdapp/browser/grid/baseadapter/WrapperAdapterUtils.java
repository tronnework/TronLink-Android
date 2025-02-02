package com.tron.wallet.business.tabdapp.browser.grid.baseadapter;

import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.base.AdapterPath;
import com.tron.wallet.business.tabdapp.browser.grid.base.AdapterPathSegment;
import com.tron.wallet.business.tabdapp.browser.grid.base.UnwrapPositionResult;
import com.tron.wallet.business.tabdapp.browser.grid.swipeable.SimpleWrapperAdapter;
import java.util.ArrayList;
import java.util.List;
public class WrapperAdapterUtils {
    private WrapperAdapterUtils() {
    }

    public static <T> T findWrappedAdapter(RecyclerView.Adapter adapter, Class<T> cls) {
        if (cls.isInstance(adapter)) {
            return cls.cast(adapter);
        }
        if (adapter instanceof SimpleWrapperAdapter) {
            return (T) findWrappedAdapter(((SimpleWrapperAdapter) adapter).getWrappedAdapter(), cls);
        }
        return null;
    }

    public static <T> T findWrappedAdapter(RecyclerView.Adapter adapter, Class<T> cls, int i) {
        AdapterPath adapterPath = new AdapterPath();
        if (unwrapPosition(adapter, null, null, i, adapterPath) == -1) {
            return null;
        }
        for (AdapterPathSegment adapterPathSegment : adapterPath.segments()) {
            if (cls.isInstance(adapterPathSegment.adapter)) {
                return cls.cast(adapterPathSegment.adapter);
            }
        }
        return null;
    }

    public static RecyclerView.Adapter releaseAll(RecyclerView.Adapter adapter) {
        return releaseCyclically(adapter);
    }

    private static RecyclerView.Adapter releaseCyclically(RecyclerView.Adapter adapter) {
        if (adapter instanceof WrapperAdapter) {
            WrapperAdapter wrapperAdapter = (WrapperAdapter) adapter;
            ArrayList arrayList = new ArrayList();
            wrapperAdapter.getWrappedAdapters(arrayList);
            wrapperAdapter.release();
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                releaseCyclically(arrayList.get(size));
            }
            arrayList.clear();
            return adapter;
        }
        return adapter;
    }

    public static int unwrapPosition(RecyclerView.Adapter adapter, int i) {
        return unwrapPosition(adapter, null, i);
    }

    public static int unwrapPosition(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2, int i) {
        return unwrapPosition(adapter, adapter2, null, i, null);
    }

    public static int unwrapPosition(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2, Object obj, int i) {
        return unwrapPosition(adapter, adapter2, obj, i, null);
    }

    public static int unwrapPosition(RecyclerView.Adapter adapter, AdapterPathSegment adapterPathSegment, int i, AdapterPath adapterPath) {
        return unwrapPosition(adapter, adapterPathSegment.adapter, adapterPathSegment.tag, i, adapterPath);
    }

    public static int unwrapPosition(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2, Object obj, int i, AdapterPath adapterPath) {
        UnwrapPositionResult unwrapPositionResult = new UnwrapPositionResult();
        if (adapterPath != null) {
            adapterPath.clear();
        }
        if (adapter == null) {
            return -1;
        }
        Object obj2 = null;
        if (adapterPath != null) {
            adapterPath.append(new AdapterPathSegment(adapter, null));
        }
        while (true) {
            if (i != -1 && adapter != adapter2) {
                if (adapter instanceof WrapperAdapter) {
                    unwrapPositionResult.clear();
                    ((WrapperAdapter) adapter).unwrapPosition(unwrapPositionResult, i);
                    i = unwrapPositionResult.position;
                    obj2 = unwrapPositionResult.tag;
                    if (unwrapPositionResult.isValid() && adapterPath != null) {
                        adapterPath.append(unwrapPositionResult);
                    }
                    adapter = unwrapPositionResult.adapter;
                    if (adapter == null) {
                        break;
                    }
                } else if (adapter2 != null) {
                    i = -1;
                }
            } else {
                break;
            }
        }
        if (adapter2 != null && adapter != adapter2) {
            i = -1;
        }
        if (obj != null && obj2 != obj) {
            i = -1;
        }
        if (i == -1 && adapterPath != null) {
            adapterPath.clear();
        }
        return i;
    }

    public static int wrapPosition(AdapterPath adapterPath, RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2, int i) {
        List<AdapterPathSegment> segments = adapterPath.segments();
        int size = segments.size();
        int i2 = adapter == null ? size - 1 : -1;
        int i3 = adapter2 == null ? 0 : -1;
        if (adapter != null || adapter2 != null) {
            for (int i4 = 0; i4 < size; i4++) {
                AdapterPathSegment adapterPathSegment = segments.get(i4);
                if (adapter != null && adapterPathSegment.adapter == adapter) {
                    i2 = i4;
                }
                if (adapter2 != null && adapterPathSegment.adapter == adapter2) {
                    i3 = i4;
                }
            }
        }
        if (i2 == -1 || i3 == -1 || i3 > i2) {
            return -1;
        }
        return wrapPosition(adapterPath, i2, i3, i);
    }

    public static int wrapPosition(AdapterPath adapterPath, int i, int i2, int i3) {
        List<AdapterPathSegment> segments = adapterPath.segments();
        while (i > i2) {
            i3 = ((WrapperAdapter) segments.get(i - 1).adapter).wrapPosition(segments.get(i), i3);
            if (i3 == -1) {
                break;
            }
            i--;
        }
        return i3;
    }
}
