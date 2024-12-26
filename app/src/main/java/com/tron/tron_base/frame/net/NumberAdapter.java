package com.tron.tron_base.frame.net;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.Number;
class NumberAdapter<T extends Number> extends TypeAdapter<T> {
    protected T readValue(JsonReader jsonReader) throws IOException {
        return null;
    }

    @Override
    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        write(jsonWriter, (JsonWriter) ((Number) obj));
    }

    public void write(JsonWriter jsonWriter, T t) throws IOException {
        if (t == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(t);
        }
    }

    @Override
    public T read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return readValue(jsonReader);
    }
}
