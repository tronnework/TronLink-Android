package com.tron.wallet.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class GsonUtils {
    private static Gson gson = new Gson();

    private GsonUtils() {
    }

    public static String toGsonString(Object obj) {
        Gson gson2 = gson;
        if (gson2 != null) {
            return gson2.toJson(obj);
        }
        return null;
    }

    public static <T> T gsonToBean(String str, Class<T> cls) {
        Gson gson2 = gson;
        if (gson2 != null) {
            return (T) gson2.fromJson(str,  cls);
        }
        return null;
    }

    public static <T> List<T> gsonToList(String str, Class<T> cls) {
        Gson gson2 = gson;
        if (gson2 != null) {
            return (List) gson2.fromJson(str, new TypeToken<List<T>>() {
            }.getType());
        }
        return null;
    }

    public static <T> List<Map<String, T>> changeGsonToListMaps(String str) {
        return (List) new Gson().fromJson(str, new TypeToken<List<Map<String, T>>>() {
        }.getType());
    }

    public static <T> List<T> jsonToItemList(String str, Class<T> cls) {
        JsonArray asJsonArray = new JsonParser().parse(str).getAsJsonArray();
        ArrayList arrayList = new ArrayList();
        Gson gson2 = new Gson();
        Iterator<JsonElement> it = asJsonArray.iterator();
        while (it.hasNext()) {
            arrayList.add(gson2.fromJson(it.next(),  cls));
        }
        return arrayList;
    }

    public static <T> Map<String, T> changeGsonToMap(String str) {
        return (Map) new Gson().fromJson(str, new TypeToken<Map<String, T>>() {
        }.getType());
    }
}
