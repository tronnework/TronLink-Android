package com.tron.tron_base.frame.net;

import com.google.gson.stream.JsonReader;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import java.io.IOException;
class IntegerAdapter extends NumberAdapter<Integer> {
    @Override
    public Integer readValue(JsonReader jsonReader) throws IOException {
        try {
            String nextString = jsonReader.nextString();
            if (StringTronUtil.canParseInt(nextString)) {
                return Integer.valueOf(Integer.parseInt(nextString));
            }
            return 0;
        } catch (Exception e) {
            LogUtils.e(e);
            return 0;
        }
    }
}
