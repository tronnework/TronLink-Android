package org.tron.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class GsonFormatUtils {
    private static Gson gson = new Gson();

    private GsonFormatUtils() {
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

    public static <T> List<T> gsonToListFixDouble(String str, Class<T> cls) {
        try {
            return (List) new GsonBuilder().registerTypeAdapter(new TypeToken<List<Object>>() {
            }.getType(), new DoubleTypeAdapter()).create().fromJson(str, new TypeToken<List<Object>>() {
            }.getType());
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static class DoubleTypeAdapter extends TypeAdapter<Object> {
        private final TypeAdapter<Object> delegate = new Gson().getAdapter(Object.class);

        @Override
        public void write(JsonWriter jsonWriter, Object obj) throws IOException {
            this.delegate.write(jsonWriter, obj);
        }

        @Override
        public Object read(JsonReader jsonReader) throws IOException {
            switch (fun4.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()]) {
                case 1:
                    ArrayList arrayList = new ArrayList();
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        arrayList.add(read(jsonReader));
                    }
                    jsonReader.endArray();
                    return arrayList;
                case 2:
                    LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        linkedTreeMap.put(jsonReader.nextName(), read(jsonReader));
                    }
                    jsonReader.endObject();
                    return linkedTreeMap;
                case 3:
                    return jsonReader.nextString();
                case 4:
                    String nextString = jsonReader.nextString();
                    if (nextString.indexOf(46) != -1) {
                        return Double.valueOf(jsonReader.nextDouble());
                    }
                    return new BigDecimal(nextString).toPlainString();
                case 5:
                    return Boolean.valueOf(jsonReader.nextBoolean());
                case 6:
                    jsonReader.nextNull();
                    return null;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    static class fun4 {
        static final int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
