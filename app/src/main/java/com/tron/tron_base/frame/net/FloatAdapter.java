package com.tron.tron_base.frame.net;

import com.google.gson.stream.JsonReader;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import java.io.IOException;
class FloatAdapter extends NumberAdapter<Float> {
    @Override
    public Float readValue(JsonReader jsonReader) throws IOException {
        try {
            String nextString = jsonReader.nextString();
            if (StringTronUtil.canParseFloat(nextString)) {
                return Float.valueOf(Float.parseFloat(nextString));
            }
            return Float.valueOf(0.0f);
        } catch (Exception e) {
            LogUtils.e(e);
            return Float.valueOf(0.0f);
        }
    }
}
