package com.tron.tron_base.frame.net;

import com.google.gson.stream.JsonReader;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import java.io.IOException;
class LongAdapter extends NumberAdapter<Long> {
    @Override
    public Long readValue(JsonReader jsonReader) throws IOException {
        try {
            String nextString = jsonReader.nextString();
            if (StringTronUtil.canParseLong(nextString)) {
                return Long.valueOf(Long.parseLong(nextString));
            }
            return 0L;
        } catch (Exception e) {
            LogUtils.e(e);
            return 0L;
        }
    }
}
