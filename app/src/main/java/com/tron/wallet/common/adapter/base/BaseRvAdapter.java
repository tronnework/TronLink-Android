package com.tron.wallet.common.adapter.base;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.adapter.base.BaseRvHolder;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
public class BaseRvAdapter<D, H extends BaseRvHolder<D>> extends RecyclerView.Adapter<H> {
    protected List<D> datas;
    private int initIndex;
    private final int layoutResource;

    public void setInitIndex(int i) {
        this.initIndex = i;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder((BaseRvAdapter<D, H>) ((BaseRvHolder) viewHolder), i);
    }

    public BaseRvAdapter(int i) {
        this.layoutResource = i;
    }

    public void setNewData(List<D> list) {
        this.datas = list;
        notifyDataSetChanged();
    }

    public void onBindViewHolder(H h, int i) {
        List<D> list = this.datas;
        if (list == null || i < 0 || i >= list.size()) {
            return;
        }
        h.onBind(this.datas.get(i), this.initIndex == i);
    }

    @Override
    public H onCreateViewHolder(ViewGroup viewGroup, int i) {
        return createViewHolder(View.inflate(viewGroup.getContext(), this.layoutResource, null));
    }

    @Override
    public int getItemCount() {
        List<D> list = this.datas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public D getItem(int i) {
        List<D> list = this.datas;
        if (list == null || i > list.size() || i < 0) {
            return null;
        }
        return this.datas.get(i);
    }

    private H createViewHolder(View view) {
        H createGenericKInstance;
        Class cls = null;
        for (Class<?> cls2 = getClass(); cls == null && cls2 != null; cls2 = cls2.getSuperclass()) {
            cls = getInstancedGenericKClass(cls2);
        }
        if (cls == null) {
            createGenericKInstance = (H) new BaseRvHolder(view);
        } else {
            createGenericKInstance = createGenericKInstance(cls, view);
        }
        return createGenericKInstance != null ? createGenericKInstance : (H) new BaseRvHolder(view);
    }

    private Class getInstancedGenericKClass(Class cls) {
        Type[] actualTypeArguments;
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            for (Type type : ((ParameterizedType) genericSuperclass).getActualTypeArguments()) {
                if (type instanceof Class) {
                    Class cls2 = (Class) type;
                    if (BaseRvHolder.class.isAssignableFrom(cls2)) {
                        return cls2;
                    }
                }
            }
            return null;
        }
        return null;
    }

    private H createGenericKInstance(Class cls, View view) {
        try {
            if (cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
                Constructor declaredConstructor = cls.getDeclaredConstructor(getClass(), View.class);
                declaredConstructor.setAccessible(true);
                return (H) declaredConstructor.newInstance(this, view);
            }
            Constructor declaredConstructor2 = cls.getDeclaredConstructor(View.class);
            declaredConstructor2.setAccessible(true);
            return (H) declaredConstructor2.newInstance(view);
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }
}
