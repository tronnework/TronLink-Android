package com.tron.tron_base.frame.net;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.stream.JsonReader;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import java.io.IOException;
class DoubleAdapter extends NumberAdapter<Double> {
    @Override
    public Double readValue(JsonReader jsonReader) throws IOException {
        try {
            String nextString = jsonReader.nextString();
            if (StringTronUtil.canParseDouble(nextString)) {
                return Double.valueOf(Double.parseDouble(nextString));
            }
            return Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (Exception e) {
            LogUtils.e(e);
            return Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
    }
}
