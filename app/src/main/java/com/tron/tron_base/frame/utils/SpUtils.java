package com.tron.tron_base.frame.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class SpUtils {
    public static void setParam(String str, Context context, String str2, Object obj) {
        if (obj != null) {
            String simpleName = obj.getClass().getSimpleName();
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            if ("String".equals(simpleName)) {
                edit.putString(str2, (String) obj);
            } else if ("Integer".equals(simpleName)) {
                edit.putInt(str2, ((Integer) obj).intValue());
            } else if ("Boolean".equals(simpleName)) {
                edit.putBoolean(str2, ((Boolean) obj).booleanValue());
            } else if ("Float".equals(simpleName)) {
                edit.putFloat(str2, ((Float) obj).floatValue());
            } else if ("Long".equals(simpleName)) {
                edit.putLong(str2, ((Long) obj).longValue());
            } else if ("HashSet".equals(simpleName)) {
                edit.putStringSet(str2, (Set) obj);
            }
            edit.commit();
        }
    }

    public static Object getParam(String str, Context context, String str2, Object obj) {
        if (obj != null) {
            String simpleName = obj.getClass().getSimpleName();
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            if ("String".equals(simpleName)) {
                return sharedPreferences.getString(str2, (String) obj);
            }
            if ("Integer".equals(simpleName)) {
                return Integer.valueOf(sharedPreferences.getInt(str2, ((Integer) obj).intValue()));
            }
            if ("Boolean".equals(simpleName)) {
                return Boolean.valueOf(sharedPreferences.getBoolean(str2, ((Boolean) obj).booleanValue()));
            }
            if ("Float".equals(simpleName)) {
                return Float.valueOf(sharedPreferences.getFloat(str2, ((Float) obj).floatValue()));
            }
            if ("Long".equals(simpleName)) {
                return Long.valueOf(sharedPreferences.getLong(str2, ((Long) obj).longValue()));
            }
            if ("HashSet".equals(simpleName)) {
                return sharedPreferences.getStringSet(str2, (Set) obj);
            }
        }
        return null;
    }

    public static void remove(String str, Context context, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.remove(str2);
        edit.commit();
    }

    public static void removeAll(String str, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.clear();
        edit.commit();
    }

    public static void putHashMapData(String str, Context context, String str2, Map<String, String> map) {
        JSONArray jSONArray = new JSONArray();
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        if (map != null) {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                try {
                    jSONObject.put(entry.getKey(), entry.getValue());
                } catch (JSONException unused) {
                }
            }
            jSONArray.put(jSONObject);
            edit.putString(str2, jSONArray.toString());
        } else {
            edit.putString(str2, null);
        }
        edit.commit();
    }

    public static Map<String, String> getHashMapData(String str, Context context, String str2) {
        HashMap hashMap = new HashMap();
        try {
            JSONArray jSONArray = new JSONArray(context.getSharedPreferences(str, 0).getString(str2, ""));
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONArray names = jSONObject.names();
                if (names != null) {
                    for (int i2 = 0; i2 < names.length(); i2++) {
                        String string = names.getString(i2);
                        hashMap.put(string, jSONObject.getString(string));
                    }
                }
            }
        } catch (JSONException unused) {
        }
        return hashMap;
    }
}
